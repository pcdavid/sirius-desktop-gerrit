/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.tools.api.editing;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.impl.TransactionChangeRecorder;
import org.eclipse.emf.transaction.impl.TransactionalEditingDomainImpl;
import org.eclipse.emf.transaction.impl.TransactionalEditingDomainImpl.FactoryImpl;

/**
 * The default editing domain provider. It is a WorkspaceEditingDomainFactory to
 * be connected to the IOperationHistory of the workbench.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 * 
 * @since 0.9.0
 */
public class DefaultEditingDomainFactory extends FactoryImpl implements IEditingDomainFactory {

    /**
     * {@inheritDoc}
     */
    public TransactionalEditingDomain createEditingDomain() {
        TransactionalEditingDomain editingDomain = new TransactionalEditingDomainImpl(new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE)) {

            @Override
            protected TransactionChangeRecorder createChangeRecorder(ResourceSet rset) {
                return new TransactionChangeRecorder(this, rset) {

                    public void notifyChanged(Notification notification) {
                        if (!notification.isTouch()) {
                            super.notifyChanged(notification);
                        }
                    }
                };
            }
        };

        mapResourceSet(editingDomain);
        editingDomain.addResourceSetListener(new FileStatusPrecommitListener());
        return editingDomain;
    }

}
