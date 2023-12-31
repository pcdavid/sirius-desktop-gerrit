/*******************************************************************************
 * Copyright (c) 2007, 2019 THALES GLOBAL SERVICES and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.tools.api.interpreter;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.sirius.common.tools.DslCommonPlugin;
import org.eclipse.sirius.common.tools.Messages;
import org.eclipse.sirius.common.tools.api.contentassist.ContentContext;
import org.eclipse.sirius.common.tools.api.contentassist.ContentInstanceContext;
import org.eclipse.sirius.common.tools.api.contentassist.ContentProposal;
import org.eclipse.sirius.common.tools.api.contentassist.IProposalProvider;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.common.tools.internal.assist.ContentContextHelper;
import org.eclipse.sirius.common.tools.internal.assist.ProposalProviderRegistry;
import org.eclipse.sirius.common.tools.internal.interpreter.DefaultConverter;
import org.eclipse.sirius.common.tools.internal.interpreter.DefaultInterpreterProvider;
import org.eclipse.sirius.ecore.extender.business.api.accessor.MetamodelDescriptor;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;

/**
 * Compound interpreter.
 *
 * @author ymortier
 */
public final class CompoundInterpreter implements IInterpreter, IProposalProvider, TypedValidation {

    /** The shared instance of the registry. */
    public static final CompoundInterpreter INSTANCE = new CompoundInterpreter();

    /**
     * Name of the extension point attribute that contains our qualified class
     * name.
     */
    private static final String ENGINE_ATTRIBUTE_CLASS = "interpreterProviderClass"; //$NON-NLS-1$

    /** The extension point. */
    private static final String INTERPRETER_EXTENSION_POINT = "org.eclipse.sirius.common.expressionInterpreter"; //$NON-NLS-1$

    /** Externalized here to avoid too many distinct usages. */
    private static final String TAG_ENGINE = "expressionInterpreterProvider"; //$NON-NLS-1$

    /** The registered interpreters. */
    private final Map<IInterpreterProvider, IInterpreter> providers;

    /** Keep track of the interpreter identifiers. */
    private final Map<IInterpreterProvider, String> interpreterIdentifiers;

    /** The default interpreter to fall back to when no installed provider handles a given expression. */
    private DefaultInterpreterProvider fallbackInterpreter;

    /** The variables manager. */
    private final VariableManager variableManager;

    /**
     * If viewpoint knows of any additional metamodel that may be necessary for
     * the interpreter, they'll be registered here.
     */
    private Collection<MetamodelDescriptor> additionalMetamodels = new LinkedHashSet<>();

    /** The dependencies. */
    private final List<String> dependencies;

    /** The model accessor. */
    private ModelAccessor modelAccessor;

    /** The cross referencer. */
    private ECrossReferenceAdapter crossReferencer;

    /**
     * <code>true</code> if the {@link IInterpreter}s are loaded from extensions
     * that are defined in other plugins.
     */
    private boolean extensionsLoaded;

    private Map<Object, Object> properties;


    /**
     * The default constructor.
     */
    private CompoundInterpreter() {
        this.providers = new HashMap<IInterpreterProvider, IInterpreter>();
        this.variableManager = new VariableManager();
        this.dependencies = new LinkedList<String>();
        this.interpreterIdentifiers = new HashMap<>();
        this.properties = new HashMap<>();
        this.fallbackInterpreter = new DefaultInterpreterProvider();
    }

    /**
     * Creates a generic interpreter that will delegate the evaluation to
     * concrete interpreters.
     *
     * @return the created interpreter.
     */
    public static IInterpreter createGenericInterpreter() {
        return new CompoundInterpreter();
    }
    
    @Override
    public IConverter getConverter() {
        return new DefaultConverter();
    }

    @Override
    public Object evaluate(EObject target, String expression) throws EvaluationException {
        IInterpreter interpreter = getInterpreterForExpression(expression);
        return interpreter.evaluate(target, expression);
    }

    @Override
    public boolean provides(final String expression) {
        return getProviderForExpression(expression) != null;
    }

    /**
     * Returns the provider to use for the given expression.
     *
     * @param expression
     *            an expression to evaluate.
     * @return the provider to use for the given expression.
     */
    public IInterpreterProvider getProviderForExpression(final String expression) {
        for (final IInterpreterProvider provider : getProviders()) {
            if (provider.provides(expression)) {
                return provider;
            }
        }
        return fallbackInterpreter;
    }

    /**
     * Returns the unique identifier associated with the given interpreter.
     *
     * @param interpreter
     *            The interpreter of which we need the unique identifier.
     * @return The unique identifier associated with the given interpreter,
     *         <code>null</code> if none.
     */
    public String getInterpreterID(final IInterpreter interpreter) {
        if (EMFPlugin.IS_ECLIPSE_RUNNING && interpreter != null) {
            // We need the provider, the whole extension point should be
            // refactored
            IInterpreterProvider interpreterProvider = null;
            Iterator<Map.Entry<IInterpreterProvider, IInterpreter>> entryIterator = providers.entrySet().iterator();
            while (interpreterProvider == null && entryIterator.hasNext()) {
                final Map.Entry<IInterpreterProvider, IInterpreter> entry = entryIterator.next();
                // instance equality do not suffice as long as the interpreter
                // is not a singleton (see
                // CompoundInterpreter.createGenericInterpreter, each session,
                // ...)
                if (entry.getValue() == interpreter) {
                    interpreterProvider = entry.getKey();
                } else if (entry.getValue() != null && entry.getValue().getClass() == interpreter.getClass()) {
                    interpreterProvider = entry.getKey();
                }
            }

            if (interpreterProvider != null) {
                return interpreterIdentifiers.get(interpreterProvider);
            }
        }
        return null;
    }

    /**
     * Returns the interpreter for the given expression.
     *
     * @param expression
     *            the expression.
     * @return the interpreter for the given expression.
     */
    public IInterpreter getInterpreterForExpression(final String expression) {
        IInterpreter result = fallbackInterpreter;
        final IInterpreterProvider provider = getProviderForExpression(expression);
        if (provider != null) {
            result = this.providers.get(provider);
            if (result == null) {
                result = provider.createInterpreter();
                for (Entry<Object, Object> entry : this.properties.entrySet()) {
                    result.setProperty(entry.getKey(), entry.getValue());
                }
                result.activateMetamodels(additionalMetamodels);
                this.variableManager.setVariables(result);
                for (final String dependency : this.dependencies) {
                    result.addImport(dependency);
                }
                result.setModelAccessor(this.modelAccessor);
                if (this.crossReferencer != null) {
                    result.setCrossReferencer(this.crossReferencer);
                }
                if (provider != fallbackInterpreter) {
                    this.providers.put(provider, result);
                }

            }
        }
        return result;
    }

    /**
     * Returns all registered interpreters.
     *
     * @return all registered interpreters.
     */
    public Set<IInterpreterProvider> getProviders() {
        if (!this.extensionsLoaded) {
            this.loadExtensions();
        }
        return this.providers.keySet();
    }

    /**
     * Registers a provider.
     *
     * @param provider
     *            the provider to register.
     */
    public void registerProvider(final IInterpreterProvider provider) {
        if (!this.getProviders().contains(provider)) {
            this.providers.put(provider, null);
        }
    }

    /**
     * Removes a provider from the registry.
     *
     * @param provider
     *            the provider to remove.
     */
    public void removeInterpreter(final IInterpreterProvider provider) {
        this.providers.remove(provider);
    }

    /**
     * Loads all interpreters that are declared in extensions.
     */
    private void loadExtensions() {
        parseExtensionMetadata();
        this.extensionsLoaded = true;
    }

    /**
     * This will parse the currently running platform for extensions and store
     * all the match engines that can be found.
     */
    private void parseExtensionMetadata() {
        if (EMFPlugin.IS_ECLIPSE_RUNNING) {
            final IExtension[] extensions = Platform.getExtensionRegistry().getExtensionPoint(CompoundInterpreter.INTERPRETER_EXTENSION_POINT).getExtensions();
            for (IExtension extension : extensions) {
                final IConfigurationElement[] configElements = extension.getConfigurationElements();
                for (IConfigurationElement configElement : configElements) {
                    final IInterpreterProvider desc = parseEngine(configElement);
                    if (desc != null) {
                        providers.put(desc, null);
                        interpreterIdentifiers.put(desc, extension.getUniqueIdentifier());
                    }
                }
            }
        }
    }

    private IInterpreterProvider parseEngine(final IConfigurationElement configElement) {
        if (!configElement.getName().equals(CompoundInterpreter.TAG_ENGINE)) {
            return null;
        }
        IInterpreterProvider desc = null;
        try {
            desc = (IInterpreterProvider) configElement.createExecutableExtension(CompoundInterpreter.ENGINE_ATTRIBUTE_CLASS);
        } catch (final CoreException e) {
            DslCommonPlugin.getDefault().error(
                    MessageFormat.format(Messages.CompoundInterpreter_impossibleToCreateInterpreter, configElement.getAttribute(CompoundInterpreter.ENGINE_ATTRIBUTE_CLASS)), e);
        }
        return desc;
    }

    @Override
    public void activateMetamodels(Collection<MetamodelDescriptor> metamodels) {
        this.additionalMetamodels.addAll(metamodels);
        for (final IInterpreter interpreter : this.providers.values()) {
            if (interpreter != null) {
                interpreter.activateMetamodels(metamodels);
            }
        }
    }

    @Override
    public void addImport(final String dependency) {
        this.dependencies.add(dependency);
        for (final IInterpreter interpreter : this.providers.values()) {
            if (interpreter != null) {
                interpreter.addImport(dependency);
            }
        }
    }

    @Override
    public void clearImports() {
        this.dependencies.clear();
        for (final IInterpreter interpreter : this.providers.values()) {
            if (interpreter != null) {
                interpreter.clearImports();
            }
        }
    }

    @Override
    public void setProperty(final Object key, final Object value) {
        this.properties.put(key, value);
        for (final IInterpreter interpreter : this.providers.values()) {
            if (interpreter != null) {
                interpreter.setProperty(key, value);
            }
        }
    }

    @Override
    public void clearVariables() {
        this.variableManager.clearVariables();
        for (final IInterpreter interpreter : this.providers.values()) {
            if (interpreter != null) {
                interpreter.clearVariables();
            }
        }
    }

    @Override
    public void dispose() {
        for (final IInterpreter interpreter : this.providers.values()) {
            if (interpreter != null) {
                interpreter.dispose();
            }
        }
        this.variableManager.clearVariables();
        this.dependencies.clear();
        this.providers.clear();
        this.properties.clear();
        this.additionalMetamodels.clear();
        this.extensionsLoaded = false;
        this.modelAccessor = null;
    }

    @Override
    public Object getVariable(final String name) {
        return this.variableManager.getVariable(name);
    }

    @Override
    public void setVariable(final String name, final Object value) {
        this.variableManager.setVariable(name, value);
        for (final IInterpreter interpreter : this.providers.values()) {
            if (interpreter != null) {
                interpreter.setVariable(name, value);
            }
        }
    }

    @Override
    public void unSetVariable(final String name) {
        this.variableManager.unSetVariable(name);
        for (final IInterpreter interpreter : this.providers.values()) {
            if (interpreter != null) {
                interpreter.unSetVariable(name);
            }
        }
    }

    @Override
    public Map<String, ?> getVariables() {
        final Map<String, Object> result = this.variableManager.getVariables();
        for (final IInterpreter interpreter : this.providers.values()) {
            if (interpreter != null) {
                for (final Map.Entry<String, ?> entry : interpreter.getVariables().entrySet()) {
                    result.put(entry.getKey(), entry.getValue());
                }
            }
        }
        return result;
    }

    @Override
    public void setModelAccessor(final ModelAccessor modelAccessor) {
        this.modelAccessor = modelAccessor;
        for (final IInterpreter interpreter : this.providers.values()) {
            if (interpreter != null) {
                interpreter.setModelAccessor(modelAccessor);
            }
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.common.tools.api.contentassist.IProposalProvider#getProposals(org.eclipse.sirius.common.tools.api.interpreter.IInterpreter,
     *      org.eclipse.sirius.common.tools.api.contentassist.ContentContext)
     */
    @Override
    public List<ContentProposal> getProposals(IInterpreter extendedInterpreter, ContentContext context) {
        /*
         * "interpreter" parameter is "this". We need the actual interpreter for
         * that expression (blame it on the interpreter that is not really an
         * interpreter :p).
         */
        List<ContentProposal> proposals = new ArrayList<>();
        IInterpreter interpreter = getInterpreterForExpression(context.getContents());
        if (interpreter != null) {
            if (interpreter instanceof IProposalProvider) {
                proposals.addAll(((IProposalProvider) interpreter).getProposals(interpreter, context));
            }
            final List<IProposalProvider> proposalProviders = ProposalProviderRegistry.getProvidersFor(interpreter);
            for (IProposalProvider provider : proposalProviders) {
                proposals.addAll(provider.getProposals(interpreter, context));
            }

            if (interpreter == fallbackInterpreter) {
                // The default interpreter is used when there is no other
                // interpreter available for the context. Try to find if the
                // context matches empty expression from one or several
                // interpreters prefixes.
                // see https://bugs.eclipse.org/bugs/show_bug.cgi?id=428770
                proposals.addAll(getEmptyExpressionProposals(context.getContents()));
            }
        }
        return proposals;
    }

    /**
     * Return allways null. use {@link CompoundInterpreter#getAllPrefixes()}
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.common.tools.api.interpreter.IInterpreter#getPrefix()
     * @see CompoundInterpreter#getAllPrefixes()
     */
    @Override
    public String getPrefix() {
        // return null the compound interpreter
        return null;
    }

    /**
     * Return allways null. use
     * {@link CompoundInterpreter#getAllNewEmtpyExpressions()} {@inheritDoc}
     *
     * @see org.eclipse.sirius.common.tools.api.interpreter.IInterpreter#getNewEmtpyExpression()
     * @see CompoundInterpreter#getAllNewEmtpyExpressions()
     */
    @Override
    public ContentProposal getNewEmtpyExpression() {
        return null;
    }

    /**
     * Get all the available empty expressions proposals.
     *
     * @return the list of all available prefixes. If there is none, the list
     *         will be empty.
     * @since 0.9.0
     */
    public List<ContentProposal> getAllNewEmtpyExpressions() {
        List<ContentProposal> prefixes = new ArrayList<>();

        final List<IProposalProvider> proposalProviders = ProposalProviderRegistry.getAllProviders();
        for (IProposalProvider provider : proposalProviders) {
            ContentProposal newEmtpyExpressionProposal = provider.getNewEmtpyExpression();
            if (newEmtpyExpressionProposal != null) {
                prefixes.add(newEmtpyExpressionProposal);
            }
        }

        return prefixes;
    }

    /**
     * Return always null. use {@link CompoundInterpreter#getVariable(String)}
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.common.tools.api.interpreter.IInterpreter#getVariablePrefix()
     */
    @Override
    public String getVariablePrefix() {
        return null;
    }

    /**
     * Get the prefix to use for variables.
     *
     * @param expression
     *            the expression.
     * @return the prefix to use for variables
     * @since 0.9.0
     */
    public String getVariablePrefix(final String expression) {
        final IInterpreter interpreter = getInterpreterForExpression(expression);
        return interpreter.getVariablePrefix();
    }

    /**
     * Get all the available prefixes.
     *
     * @return the list of all available prefixes. If there is none, the list
     *         will be empty.
     */
    public List<String> getAllPrefixes() {
        final ArrayList<String> prefixes = new ArrayList<String>();
        for (final IInterpreterProvider interpreterProvider : getProviders()) {
            if (interpreterProvider != null) {
                /* create an interpreter only for the prefix => no cache */
                final IInterpreter interpreter = interpreterProvider.createInterpreter();
                prefixes.add(interpreter.getPrefix());
            }
        }
        prefixes.trimToSize();
        return prefixes;
    }

    @Override
    public void setCrossReferencer(final ECrossReferenceAdapter crossReferencer) {
        this.crossReferencer = crossReferencer;
        for (final IInterpreter interpreter : this.providers.values()) {
            if (interpreter != null) {
                interpreter.setCrossReferencer(crossReferencer);
            }
        }
    }

    @Override
    public List<ContentProposal> getProposals(IInterpreter extendedInterpreter, ContentInstanceContext context) {
        /*
         * "interpreter" parameter is "this". We need the actual interpreter for
         * that expression (blame it on the interpreter that is not really an
         * interpreter :p).
         */
        List<ContentProposal> proposals = new ArrayList<>();
        IInterpreter interpreterForExpression = getInterpreterForExpression(context.getTextSoFar());
        if (interpreterForExpression != null) {
            if (interpreterForExpression instanceof IProposalProvider) {
                proposals.addAll(((IProposalProvider) interpreterForExpression).getProposals(interpreterForExpression, context));
            }
            final List<IProposalProvider> proposalProviders = ProposalProviderRegistry.getProvidersFor(interpreterForExpression);
            for (IProposalProvider provider : proposalProviders) {
                proposals.addAll(provider.getProposals(interpreterForExpression, context));
            }

            if (interpreterForExpression == fallbackInterpreter) {
                // The default interpreter is used when there is no other
                // interpreter available for the context. Try to find if the
                // context matches empty expression from one or several
                // interpreters prefixes.
                // see https://bugs.eclipse.org/bugs/show_bug.cgi?id=428770
                proposals.addAll(getEmptyExpressionProposals(context.getTextSoFar()));
            }
        }
        return proposals;
    }

    @Override
    public Collection<String> getImports() {
        return Collections.<String> unmodifiableCollection(this.dependencies);
    }

    @Override
    public void removeImport(String dependency) {
        this.dependencies.remove(dependency);
        for (IInterpreter interpreter : this.providers.values()) {
            if (interpreter != null) {
                interpreter.removeImport(dependency);
            }
        }
    }

    @Override
    public Collection<IInterpreterStatus> validateExpression(IInterpreterContext context, String expression) {
        final IInterpreter interpreter = getInterpreterForExpression(expression);
        return interpreter.validateExpression(context, expression);
    }

    @Override
    public ValidationResult analyzeExpression(IInterpreterContext context, String expression) {
        final IInterpreter interpreter = getInterpreterForExpression(expression);
        if (interpreter instanceof TypedValidation) {
            return ((TypedValidation) interpreter).analyzeExpression(context, expression);
        }
        ValidationResult result = new ValidationResult();
        result.addAllStatus(interpreter.validateExpression(context, expression));
        return result;
    }

    @Override
    public boolean supportsValidation() {
        return true;
    }

    /**
     * Get proposals that match context for available empty expressions.
     *
     * @param context
     *            context to match
     * @return list of proposal for empty expressions
     */
    private List<ContentProposal> getEmptyExpressionProposals(String context) {
        List<ContentProposal> proposals = new ArrayList<>();

        // Provides all interpreters compatible with the context
        final List<IProposalProvider> proposalProviders = ProposalProviderRegistry.getAllProviders();
        for (IProposalProvider provider : proposalProviders) {
            ContentProposal emptyExpression = provider.getNewEmtpyExpression();

            if (StringUtil.isEmpty(context) || ContentContextHelper.matchEmptyExpression(context, emptyExpression)) {
                proposals.add(emptyExpression);
            }
        }

        return proposals;
    }

}
