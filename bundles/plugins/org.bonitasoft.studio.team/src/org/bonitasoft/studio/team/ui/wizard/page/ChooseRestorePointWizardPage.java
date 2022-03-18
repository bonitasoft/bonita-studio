/*******************************************************************************
 * Copyright (C) 2009, 2013 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.team.ui.wizard.page;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.bonitasoft.studio.common.jface.BonitaErrorDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.team.TeamRepositoryUtil;
import org.bonitasoft.studio.team.i18n.Messages;
import org.bonitasoft.studio.team.operations.CreateWorkspaceBackupOperation;
import org.bonitasoft.studio.team.repository.Repository;
import org.bonitasoft.studio.team.ui.dialog.DynamicLabelWizardDialog;
import org.bonitasoft.studio.team.ui.provider.RestorePointContentProvider;
import org.bonitasoft.studio.team.ui.provider.RestorePointLabelProvider;
import org.bonitasoft.studio.team.ui.wizard.ResotrePointWizard;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.team.svn.core.connector.SVNConnectorException;
import org.eclipse.team.svn.core.operation.remote.DeleteResourcesOperation;
import org.eclipse.team.svn.core.resource.IRepositoryContainer;
import org.eclipse.team.svn.core.resource.IRepositoryResource;

/**
 * @author Romain Bioteau
 */
public class ChooseRestorePointWizardPage extends WizardPage {

    private TableViewer restorePointList;
    private String selectedWorkspace;
    private Button deleteRestorePointButton;

    public ChooseRestorePointWizardPage(final String selectedWorkspace) {
        super(ChooseRestorePointWizardPage.class.getName());
        setTitle(Messages.restorePointTitle);
        setDescription(Messages.restorePointDesc);
        setImageDescriptor(Pics.getWizban());
        this.selectedWorkspace = selectedWorkspace;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public void createControl(final Composite parent) {
        final Composite composite = new Composite(parent, SWT.NONE);
        composite.setLayout(new GridLayout(1, false));
        restorePointList = new TableViewer(composite, SWT.SINGLE | SWT.BORDER | SWT.V_SCROLL);
        restorePointList.setContentProvider(new RestorePointContentProvider());
        restorePointList.setLabelProvider(new RestorePointLabelProvider());
        restorePointList.addSelectionChangedListener(new ISelectionChangedListener() {

            @Override
            public void selectionChanged(final SelectionChangedEvent event) {
                getContainer().updateButtons();
                updateDeleteButton();
            }
        });

        if (selectedWorkspace != null) {
            restorePointList.setInput(repositoryContainer());
        }

        restorePointList.setSorter(new ViewerSorter() {

            @Override
            public int compare(final Viewer viewer, final Object e1, final Object e2) {
                return getRestorePointDate(e2.toString()).compareTo(getRestorePointDate(e1.toString()));
            }
        });
        restorePointList.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        deleteRestorePointButton = new Button(composite, SWT.FLAT);
        deleteRestorePointButton.setText(Messages.deleteRestorePoint);
        deleteRestorePointButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                if (!restorePointList.getSelection().isEmpty() && deleteQuestion(restorePointList.getSelection())) {
                    final IRepositoryContainer res = (IRepositoryContainer) ((IStructuredSelection) restorePointList.getSelection()).getFirstElement();
                    final DeleteResourcesOperation deleteOP = new DeleteResourcesOperation(new IRepositoryResource[] { res }, "Delete Restore Point");
                    deleteOP.run(new NullProgressMonitor());
                    restorePointList.setInput(repositoryContainer());
                }
            }
        });
        updateDeleteButton();
        setControl(composite);
    }

    private IRepositoryContainer repositoryContainer() {
        try {
            return TeamRepositoryUtil.getRemoteRepository((Repository) RepositoryManager.getInstance()
                    .getRepository(selectedWorkspace));
        } catch (final SVNConnectorException e) {
            new BonitaErrorDialog(getShell(), Messages.invalidRemoteLocationTitle, Messages.invalidRemoteLocationMsg, e).open();
            return null;
        }
    }

    protected boolean deleteQuestion(final ISelection selection) {
        final IRepositoryContainer repo = (IRepositoryContainer) ((IStructuredSelection) restorePointList.getSelection()).getFirstElement();
        return MessageDialog.openConfirm(Display.getDefault().getActiveShell(), Messages.deleteRestorePointTitle,
                Messages.bind(Messages.deleteRestorePointMsg, new RestorePointLabelProvider().getText(repo)));
    }

    protected void updateDeleteButton() {
        if (restorePointList.getSelection().isEmpty()) {
            deleteRestorePointButton.setEnabled(false);
        } else {
            deleteRestorePointButton.setEnabled(true);
        }
    }

    @Override
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
        if (((ResotrePointWizard) getWizard()).getSelectedWorkspace() != null) {
            selectedWorkspace = ((ResotrePointWizard) getWizard()).getSelectedWorkspace();
            final String repositoryName = ((ResotrePointWizard) getWizard()).getSelectedWorkspace();
            try {
                restorePointList.setInput(TeamRepositoryUtil.getRemoteRepository((Repository) RepositoryManager.getInstance().getRepository(
                        repositoryName)));
            } catch (final SVNConnectorException e) {
                new BonitaErrorDialog(getShell(), Messages.invalidRemoteLocationTitle, Messages.invalidRemoteLocationMsg, e).open();
            }
        }
        if (visible) {
            ((DynamicLabelWizardDialog) getContainer()).setFinishLabel(Messages.restoreLabel);
        }
    }

    protected Date getRestorePointDate(final String label) {
        final int i = label.lastIndexOf('_');
        final String timestamp = label.substring(i + 1, label.length());
        final SimpleDateFormat formater = new SimpleDateFormat(CreateWorkspaceBackupOperation.RESOTRE_POINT_TIMESTAMP_PATTERN);
        Date date = null;
        try {
            date = formater.parse(timestamp);
        } catch (final ParseException e) {
            BonitaStudioLog.error(e);
        }

        return date;

    }

    @Override
    public boolean canFlipToNextPage() {
        return false;
    }

    @Override
    public boolean isPageComplete() {
        return super.isPageComplete() && selectedWorkspace != null && restorePointList != null && !restorePointList.getSelection().isEmpty()
                && getContainer().getCurrentPage().equals(this);
    }

    public String getSelectedWorkspace() {
        return selectedWorkspace;
    }

    public IRepositoryContainer getRemoteWorkspaceResource() {
        return (IRepositoryContainer) ((IStructuredSelection) restorePointList.getSelection()).getFirstElement();
    }

}
