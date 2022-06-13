/*******************************************************************************
 * Copyright (C) 2018 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.team.repository;

import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.common.repository.Messages;
import org.bonitasoft.studio.common.repository.ProjectFileChangeListener;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.team.TeamPlugin;
import org.eclipse.egit.core.GitProvider;
import org.eclipse.egit.ui.internal.branch.BranchOperationUI;
import org.eclipse.egit.ui.internal.dialogs.CheckoutDialog;
import org.eclipse.egit.ui.internal.selection.SelectionUtils;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;

public class ProjectFileChangeListenerEx extends ProjectFileChangeListener {

    public static final String VALIDATE_REPO_VERSION_AFTER_SWITCH_BRANCH = "VALIDATE_REPO_VERSION_AFTER_SWITCH_BRANCH";
    public static boolean migrationDialogOpened = false;

    public ProjectFileChangeListenerEx(AbstractRepository repository) {
        super(repository);
    }

    @SuppressWarnings("restriction")
    @Override
    protected void openMigrationDialog() {
        if (repository.isShared(GitProvider.ID)) {
            openGitMigrationDialog();
        } else {
            super.openMigrationDialog();
        }
    }

    @SuppressWarnings("restriction")
    private void openGitMigrationDialog() {
        Display.getDefault().asyncExec(() -> {
            if (!migrationDialogOpened) {
                migrationDialogOpened = true;
                boolean migrate = MessageDialog.open(MessageDialog.INFORMATION,
                        Display.getDefault().getActiveShell(),
                        Messages.migrationTitle,
                        String.format(Messages.mustMigrationMsg,
                                ProductVersion.CURRENT_VERSION),
                        SWT.NONE, Messages.migrate, org.bonitasoft.studio.team.i18n.Messages.switchIntoNewbranch) == 0; // default index is 0 -> migrate
                migrationDialogOpened = false;
                if (migrate) {
                    repository.runMigrationInDialog();
                } else {
                    // open switch branch dialog
                    // if he leaves or click cancel, then re open migration dialog
                    org.eclipse.jgit.lib.Repository gitRepository = getGitRepository(repository);
                    CheckoutDialog checkoutDialog = new CheckoutDialog(Display.getDefault().getActiveShell(), gitRepository);
                    checkoutDialog.open();
                    if (checkoutDialog.getReturnCode() == CheckoutDialog.OK) {
                        TeamPlugin.getDefault().getPreferenceStore()
                                .setValue(ProjectFileChangeListenerEx.VALIDATE_REPO_VERSION_AFTER_SWITCH_BRANCH, true);
                        BranchOperationUI
                                .checkout(gitRepository, checkoutDialog.getRefName())
                                .start();
                    } else {
                        openGitMigrationDialog();
                    }
                }
            }
        });
    }

    @SuppressWarnings("restriction")
    private org.eclipse.jgit.lib.Repository getGitRepository(AbstractRepository repository) {
        IStructuredSelection selection = new StructuredSelection(repository.getProject());
        return SelectionUtils.getRepository(selection);
    }
}
