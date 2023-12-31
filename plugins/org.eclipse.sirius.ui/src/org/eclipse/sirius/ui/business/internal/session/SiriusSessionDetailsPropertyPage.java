/*******************************************************************************
 * Copyright (c) 2022 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.ui.business.internal.session;

import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.Adapters;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.sirius.business.api.query.DRepresentationQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.internal.image.ImageDependenciesAnnotationHelper;
import org.eclipse.sirius.business.internal.query.SessionDetailsReport;
import org.eclipse.sirius.business.internal.session.danalysis.DAnalysisSessionImpl;
import org.eclipse.sirius.ui.business.api.session.SessionUIManager;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.provider.Messages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.PropertyPage;

/**
 * {@link PropertyPage} presenting information related to the Sirius session of the selected aird file.
 * 
 * @author lfasani
 */
public class SiriusSessionDetailsPropertyPage extends PropertyPage {

    private Text text;

    private IFile resourceAird;

    private Session session;

    private Button computeDependenciesButton;

    @Override
    protected Control createContents(Composite parent) {
        noDefaultAndApplyButton();

        Composite composite = new Composite(parent, SWT.NONE);
        GridLayout layout = new GridLayout();
        layout.marginWidth = 0;
        layout.marginHeight = 0;
        composite.setLayout(layout);
        GridData data = new GridData(GridData.FILL);
        data.grabExcessHorizontalSpace = true;
        composite.setLayoutData(data);

        createSessionDetailsSection(composite);

        initialize();

        return composite;
    }

    private void initialize() {
        resourceAird = Adapters.adapt(getElement(), IFile.class);

        URI airdURI = URI.createPlatformResourceURI(resourceAird.getFullPath().toString(), true);

        session = SessionManager.INSTANCE.getExistingSession(airdURI);
        computeSessionDetails(Messages.SiriusSessionDetailsPropertyPage_computeSessionDetails, false);
        computeDependenciesButton.setEnabled(session != null);
    }

    private String getSessionInformation() {
        SessionDetailsReport sessionQuery = new SessionDetailsReport(resourceAird);
        String formattedInformation = sessionQuery.getSessionFormattedInformation();

        // Add part about the opened editors
        if (session != null) {
            StringBuilder informations = new StringBuilder();
            List<DRepresentation> openedRepresentations = SessionUIManager.INSTANCE.getUISession(session).getEditors().stream().map(editor -> editor.getRepresentation()).collect(Collectors.toList());
            String cr = System.lineSeparator();
            String tab = "  "; //$NON-NLS-1$
            informations.append(cr + MessageFormat.format(Messages.SiriusSessionDetailsPropertyPage_repOpenedInEditor, openedRepresentations.size()) + cr);
            openedRepresentations.stream().forEach(rep -> {
                informations.append(tab);
                if (rep != null) {
                    sessionQuery.addRepresentationDescriptorSimpleInfo(informations, new DRepresentationQuery(rep).getRepresentationDescriptor());
                } else {
                    informations.append("null"); //$NON-NLS-1$
                }
                informations.append(cr);
            });

            formattedInformation = formattedInformation + informations.toString();
        }

        return formattedInformation;
    }

    private void createSessionDetailsSection(Composite parent) {
        Composite sessionDetailsComposite = new Composite(parent, SWT.NONE);
        GridLayout layout = new GridLayout();
        layout.marginHeight = 0;
        layout.marginWidth = 0;
        sessionDetailsComposite.setLayout(layout);
        sessionDetailsComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        // Necessary to have the tooltip even if the button is disabled
        Composite compositeForTooltip = new Composite(sessionDetailsComposite, SWT.NONE);
        compositeForTooltip.setLayoutData(new GridData());
        compositeForTooltip.setLayout(new FillLayout());

        computeDependenciesButton = new Button(compositeForTooltip, SWT.NONE);
        computeDependenciesButton.setText(Messages.SiriusSessionDetailsPropertyPage_computeDependenciesButton);
        computeDependenciesButton.setToolTipText(Messages.SiriusSessionDetailsPropertyPage_computeDependenciesButtonTooltip);
        compositeForTooltip.setToolTipText(Messages.SiriusSessionDetailsPropertyPage_computeDependenciesButtonTooltip);
        computeDependenciesButton.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, false, false));
        computeDependenciesButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                String description = Messages.SiriusSessionDetailsPropertyPage_confirmComputingDependenciesDescriptionDialog;
                String title = Messages.SiriusSessionDetailsPropertyPage_confirmComputingDependenciesTitleDialog;
                Shell shell = Display.getCurrent().getActiveShell();

                boolean computeImageDepencies = MessageDialog.openQuestion(shell, title, description);
                if (computeImageDepencies) {
                    computeSessionDetails(Messages.SiriusSessionDetailsPropertyPage_computeDependenciesSessionDetails, computeImageDepencies);
                }
            }
        });

        // CHECKSTYLE:OFF
        text = new Text(sessionDetailsComposite, SWT.MULTI | SWT.BORDER | SWT.READ_ONLY | SWT.V_SCROLL | SWT.NO_FOCUS | SWT.H_SCROLL);
        // CHECKSTYLE:ON
        text.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_LIST_BACKGROUND));
        GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.VERTICAL_ALIGN_FILL);
        gridData.grabExcessVerticalSpace = true;
        gridData.grabExcessHorizontalSpace = true;
        text.setLayoutData(gridData);
        text.setFont(JFaceResources.getTextFont());
        text.setText(Messages.SiriusSessionDetailsPropertyPage_computingSessionDetails);

        Button copyToClipboard = new Button(sessionDetailsComposite, SWT.NONE);
        copyToClipboard.setText(Messages.SiriusSessionDetailsPropertyPage_copyToClipboard);
        copyToClipboard.setLayoutData(new GridData(SWT.TRAIL, SWT.FILL, false, false));
        copyToClipboard.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                Clipboard clipboard = null;
                try {
                    clipboard = new Clipboard(getShell().getDisplay());
                    clipboard.setContents(new Object[] { text.getText() }, new Transfer[] { TextTransfer.getInstance() });
                } finally {
                    if (clipboard != null) {
                        clipboard.dispose();
                    }
                }
            }
        });
    }

    private void computeSessionDetails(String jobName, boolean updateImageProjectDependencies) {
        Job job = new Job(jobName) {
            @Override
            protected IStatus run(IProgressMonitor monitor) {
                if (updateImageProjectDependencies && session != null) {
                    session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(session.getTransactionalEditingDomain()) {

                        @Override
                        protected void doExecute() {
                            new ImageDependenciesAnnotationHelper((DAnalysisSessionImpl) session).updateAllImageProjectsDependencies();
                        }
                    });
                }
                String sessionInformation = getSessionInformation();
                Display.getDefault().asyncExec(new Runnable() {
                    @Override
                    public void run() {
                        if (!text.isDisposed()) {
                            text.setText(sessionInformation);
                        }
                    }
                });
                return Status.OK_STATUS;
            }
        };
        job.setUser(true);
        job.schedule();
    }
}
