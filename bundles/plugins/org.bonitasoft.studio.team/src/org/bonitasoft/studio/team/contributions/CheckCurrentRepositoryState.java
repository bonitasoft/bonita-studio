/*******************************************************************************
 * Copyright (C) 2009, 2013 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.team.contributions;

import java.lang.reflect.InvocationTargetException;

import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.common.extension.IPostStartupContribution;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.Messages;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.team.ui.handler.SwitchRepositoriesWorkspaceHandler;
import org.bonitasoft.studio.ui.dialog.ExceptionDialogHandler;
import org.bonitasoft.studio.validation.ModelFileCompatibilityValidator;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;

public class CheckCurrentRepositoryState implements IPostStartupContribution {

    @Override
    public void execute() {
        AbstractRepository repository = RepositoryManager.getInstance().getCurrentRepository();
        String version = repository.getVersion();
        if (!ProductVersion.sameVersion(version)) {
            if (!ProductVersion.sameMinorVersion(version) && !ProductVersion.canBeMigrated(version)) {
                openSwitchProjectDialog(repository);
            } else {
                try {
                    ModelFileCompatibilityValidator validateModelCompatibility = new ModelFileCompatibilityValidator(
                            repository)
                                    .addResourceMarkers();
                    PlatformUI.getWorkbench().getProgressService().run(true, false, validateModelCompatibility::run);
                    if (validateModelCompatibility.getStatus().getSeverity() == IStatus.ERROR) {
                        openSwitchProjectDialog(repository);
                    } else if (validateModelCompatibility.getStatus().getSeverity() == IStatus.WARNING) {
                        openMigrationRequiredDialog(repository);
                    }
                } catch (InvocationTargetException | InterruptedException e) {
                    new ExceptionDialogHandler().openErrorDialog(Display.getDefault().getActiveShell(), e.getMessage(),
                            e);
                }
            }
        }
    }

    private void openMigrationRequiredDialog(AbstractRepository repository) {
        int buttonIndex = MessageDialog.open(MessageDialog.WARNING,
                Display.getDefault().getActiveShell(),
                Messages.confirmMigratonTitle,
                Messages.confirmMigraton,
                SWT.NONE,
                Messages.migrate, org.bonitasoft.studio.team.i18n.Messages.switchRepository);
        if (buttonIndex == 0) {
            repository.runMigrationInDialog();
        } else {
            try {
                new SwitchRepositoriesWorkspaceHandler().execute(new ExecutionEvent());
            } catch (ExecutionException e) {
                BonitaStudioLog.error(e);
            }
        }
    }

    private void openSwitchProjectDialog(AbstractRepository repository) {
        MessageDialog.open(MessageDialog.ERROR,
                Display.getDefault().getActiveShell(),
                Messages.repositoryError,
                String.format(Messages.repositoryVersionErrorMsg, repository.getName(), repository.getVersion(),
                        ProductVersion.CURRENT_VERSION),
                SWT.NONE,
                org.bonitasoft.studio.team.i18n.Messages.switchRepository);
        try {
            new SwitchRepositoriesWorkspaceHandler().execute(new ExecutionEvent());
        } catch (ExecutionException e) {
            BonitaStudioLog.error(e);
        }
    }

}
