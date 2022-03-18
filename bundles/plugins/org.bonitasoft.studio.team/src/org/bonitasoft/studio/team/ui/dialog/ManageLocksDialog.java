/*******************************************************************************
 * Copyright (C) 2009, 2013 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 *      BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 *      or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.team.ui.dialog;

import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.ui.viewer.RepositoryTreeViewer;
import org.bonitasoft.studio.team.i18n.Messages;
import org.bonitasoft.studio.team.ui.provider.SharedRepositoryTreeContentProvider;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.DecoratingLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.team.svn.core.utility.SVNUtility;

/**
 * @author Baptiste Mesta
 *
 */
public class ManageLocksDialog extends Dialog {

    private TreeViewer viewer;
    private Button unLockButton;
    private Button lockButton;

    private final LockDialogModel lockDialogModel;

    /**
     * @param parentShell
     */
    public ManageLocksDialog(final Shell parentShell,
            final LockDialogModel lockDialogModel) {
        super(parentShell);
        this.lockDialogModel = lockDialogModel;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected Control createDialogArea(final Composite parent) {
        final Composite control = (Composite) super.createDialogArea(parent);
        control.setLayout(new GridLayout(2, true));
        createTreeViewer(control);
        viewer.setInput(lockDialogModel.getSharedRepository().getAllShareableStores());
        viewer.addFilter(filterProvidedWidgets());
        viewer.getControl().setLayoutData(GridDataFactory.fillDefaults().span(2, 1).grab(true, true).create());

        lockButton = new Button(control, SWT.FLAT);
        lockButton.setText(Messages.lockLabel);
        lockButton.setFont(JFaceResources.getDialogFont());
        setButtonLayoutData(lockButton);

        unLockButton = new Button(control, SWT.FLAT);
        unLockButton.setText(Messages.unlockLabel);
        unLockButton.setFont(JFaceResources.getDialogFont());
        setButtonLayoutData(unLockButton);

        updateLockButtons(null);

        addLockButtonListener(lockButton);
        addUnlockButtonListener(unLockButton);
        return control;
    }

    private ViewerFilter filterProvidedWidgets() {
        return new ViewerFilter() {

            @Override
            public boolean select(Viewer viewer, Object parentElement, Object element) {
                if (element instanceof IRepositoryFileStore) {
                    return !SVNUtility.isIgnored(((IRepositoryFileStore) element).getResource());
                }
                return true;
            }
        };
    }

    protected void addUnlockButtonListener(final Button button) {
        button.addSelectionListener(new SelectionAdapter() {
            /* (non-Javadoc)
             * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
             */
            @Override
            public void widgetSelected(final SelectionEvent e) {
                final Object fileStore = ((IStructuredSelection)viewer.getSelection()).getFirstElement();
                unlockSelection(fileStore);
            }

        });
    }

    protected void unlockSelection(final Object fileStore) {
        if (fileStore instanceof IRepositoryFileStore) {
            lockDialogModel.handleUnlockSelection((IRepositoryFileStore) fileStore);
            updateLockButtons((IStructuredSelection) viewer.getSelection());
            viewer.refresh(fileStore);
        }
    }

    protected void addLockButtonListener(final Button button) {
        button.addSelectionListener(new SelectionAdapter() {
            /* (non-Javadoc)
             * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
             */
            @Override
            public void widgetSelected(final SelectionEvent e) {
                final Object fileStore = ((IStructuredSelection)viewer.getSelection()).getFirstElement();
                lockSelection(fileStore);
            }

        });
    }

    protected void lockSelection(final Object fileStore) {
        if (fileStore instanceof IRepositoryFileStore) {
            lockDialogModel.handleLockSelection((IRepositoryFileStore) fileStore);
            updateLockButtons((IStructuredSelection) viewer.getSelection());
            viewer.refresh(fileStore);
        }
    }

    protected void updateLockButtons(final IStructuredSelection selection) {
        updateLockEnablement(lockDialogModel.isLockEnabled(selection));
        updateUnlockEnablement(lockDialogModel.isUnLockEnabled(selection));
    }

    protected void updateLockEnablement(final boolean isEnabled) {
        lockButton.setEnabled(isEnabled);
    }

    protected void updateUnlockEnablement(final boolean isEnabled) {
        unLockButton.setEnabled(isEnabled);
    }

    @Override
    protected void okPressed() {
        lockDialogModel.applyChanges();
        super.okPressed();
    }


    /**
     * @param composite
     */
    private void createTreeViewer(final Composite composite) {
        viewer = new RepositoryTreeViewer(composite, SWT.BORDER);
        viewer.setContentProvider(new SharedRepositoryTreeContentProvider(lockDialogModel.getDecorator()));
        viewer.setLabelProvider(new DecoratingLabelProvider((ILabelProvider) viewer.getLabelProvider(), lockDialogModel.getDecorator()));
        viewer.addSelectionChangedListener(new ISelectionChangedListener() {

            @Override
            public void selectionChanged(final SelectionChangedEvent event) {
                updateLockButtons(null);
                if(((IStructuredSelection)event.getSelection()).size()==1){
                    updateLockButtons((IStructuredSelection) event.getSelection());
                }
            }
        });
    }


    protected TreeViewer getViewer() {
        return viewer;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets.Shell)
     */
    @Override
    protected void configureShell(final Shell newShell) {
        super.configureShell(newShell);
        newShell.setText(Messages.manageLocks_title);
        newShell.setMinimumSize(400, 500);
    }

}
