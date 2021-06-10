/*******************************************************************************
 * Copyright (C) 2009, 2013 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.team.ui.dialog;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;

import org.bonitasoft.studio.common.CommandExecutor;
import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.bonitasoft.studio.common.repository.provider.RepositoryLabelProvider;
import org.bonitasoft.studio.team.i18n.Messages;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.IProgressService;

/**
 * @author Baptiste Mesta
 */
public class SwitchRepositoryDialog extends Dialog {

    private static final String NEW_PROJECT_COMMAND_ID = "org.bonitasoft.studio.application.newproject.command";
    private IRepository selectedRepository;
    private TableViewer repositoryList;
    private final boolean force;
    private Button delete;

    /**
     * @param parentShell
     * @param force
     */
    public SwitchRepositoryDialog(final Shell parentShell, final boolean force) {
        super(parentShell);
        this.force = force;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected Control createDialogArea(final Composite parent) {
        final Composite composite = (Composite) super.createDialogArea(parent);
        final Label nameLabel = new Label(composite, SWT.NONE);
        nameLabel.setText(Messages.team_selectWorkspace);
        createViewerForRepositoryList(composite);

        final Composite buttonComposite = new Composite(composite, SWT.NONE);
        buttonComposite.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.FILL).create());
        buttonComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());

        createNewLocalRepo(buttonComposite);
        createDeleteButton(buttonComposite);
        return composite;
    }

    private void createNewLocalRepo(final Composite buttonComposite) {
        final Button createNewLocalRepo = new Button(buttonComposite, SWT.FLAT);
        createNewLocalRepo.setText(Messages.createNewLocalRepo);
        createNewLocalRepo.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                SwitchRepositoryDialog.this.setReturnCode(IDialogConstants.CANCEL_ID);
                SwitchRepositoryDialog.this.close();
                new CommandExecutor().executeCommand(NEW_PROJECT_COMMAND_ID, Collections.emptyMap());
            }
        });

    }

    protected void createViewerForRepositoryList(final Composite composite) {
        repositoryList = new TableViewer(composite, SWT.SINGLE | SWT.BORDER);
        repositoryList.setContentProvider(ArrayContentProvider.getInstance());
        ColumnViewerToolTipSupport.enableFor(repositoryList);
        repositoryList.setLabelProvider(new RepositoryLabelProvider());
        repositoryList.setInput(RepositoryManager.getInstance().getAllRepositories());
        repositoryList.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        repositoryList.addSelectionChangedListener(new ISelectionChangedListener() {

            @Override
            public void selectionChanged(final SelectionChangedEvent event) {
                if (!((StructuredSelection) event.getSelection()).isEmpty()) {
                    final IRepository repository = (IRepository) ((StructuredSelection) event.getSelection())
                            .getFirstElement();
                    if (ProductVersion.sameMinorVersion(repository.getVersion())
                            && (force || !repository.equals(RepositoryManager.getInstance().getCurrentRepository()))) {
                        updateOKButton(true);
                    } else {
                        updateOKButton(false);
                    }
                }
                updateDeleteButton();
            }
        });
        repositoryList.addDoubleClickListener(new IDoubleClickListener() {

            @Override
            public void doubleClick(final DoubleClickEvent event) {
                if (getButton(IDialogConstants.OK_ID).isEnabled()) {
                    buttonPressed(IDialogConstants.OK_ID);
                    okPressed();
                }
            }
        });
    }

    protected void updateOKButton(final boolean enabled) {
        getButton(IDialogConstants.OK_ID).setEnabled(enabled);
    }

    protected void createDeleteButton(final Composite buttonComposite) {
        delete = new Button(buttonComposite, SWT.FLAT);
        delete.setText(Messages.removeRepository);
        delete.addListener(SWT.Selection, e -> deleteRepository(buttonComposite.getShell()));
        updateDeleteButton();
    }

    private void deleteRepository(Shell shell) {
        final IRepository repository = (IRepository) ((StructuredSelection) repositoryList.getSelection())
                .getFirstElement();
        if (MessageDialog
                .openConfirm(shell, Messages.confirmDeleteRepositoryTitle,
                        String.format(Messages.confirmDeleteRepository, repository.getName()))) {
            try {
                final IProgressService progressManager = PlatformUI.getWorkbench().getProgressService();
                progressManager.run(false, false, new IRunnableWithProgress() {

                    @Override
                    public void run(final IProgressMonitor monitor)
                            throws InvocationTargetException, InterruptedException {
                        monitor.beginTask(Messages.removeRepository, IProgressMonitor.UNKNOWN);
                        repository.delete(monitor);
                    }
                });

                repositoryList.setInput(RepositoryManager.getInstance().getAllRepositories());
                repositoryList.setSelection(new StructuredSelection(RepositoryManager.getInstance().getCurrentRepository()));
            } catch (final Exception e) {
                BonitaStudioLog.error(e);
            }
        }
    }

    protected void updateDeleteButton() {
        if (!((StructuredSelection) repositoryList.getSelection()).isEmpty()) {
            final IRepository repository = (IRepository) ((StructuredSelection) repositoryList.getSelection())
                    .getFirstElement();
            delete.setEnabled(!repository.equals(RepositoryManager.getInstance().getCurrentRepository()));
        } else {
            delete.setEnabled(false);
        }
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.dialogs.Dialog#createButtonsForButtonBar(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected void createButtonsForButtonBar(final Composite parent) {
        // create OK and Cancel buttons by default
        createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
        if (!force) {
            createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
        }
    }

    /**
     * @return
     */
    public IRepository getRepository() {
        return selectedRepository;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.dialogs.Dialog#buttonPressed(int)
     */
    @Override
    protected void buttonPressed(final int buttonId) {
        selectedRepository = (IRepository) ((StructuredSelection) repositoryList.getSelection()).getFirstElement();
        super.buttonPressed(buttonId);
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets.Shell)
     */
    @Override
    protected void configureShell(final Shell newShell) {
        newShell.setMinimumSize(400, 300);
        super.configureShell(newShell);
        newShell.setText(Messages.switchRepositoryDialogTitle);
    }

}
