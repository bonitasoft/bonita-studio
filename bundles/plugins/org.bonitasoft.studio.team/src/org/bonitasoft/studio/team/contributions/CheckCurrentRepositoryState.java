/*******************************************************************************
 * Copyright (C) 2009, 2013 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.team.contributions;

import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.common.extension.IPostStartupContribution;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.Messages;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.team.repository.Repository;
import org.bonitasoft.studio.team.ui.handler.SwitchRepositoriesWorkspaceHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;

public class CheckCurrentRepositoryState implements IPostStartupContribution {

    @Override
    public void execute() {
        AbstractRepository repository = RepositoryManager.getInstance().getCurrentRepository();
        String version = repository.getVersion();
        if (!ProductVersion.sameMinorVersion(version) || ProductVersion.canBeMigrated(version)) {
            int returnCode = MessageDialog.open(MessageDialog.ERROR,
                    Display.getDefault().getActiveShell(),
                    Messages.repositoryError,
                    String.format(Messages.repositoryVersionErrorMsg, repository.getName(), repository.getVersion(),
                            ProductVersion.CURRENT_VERSION),
                    SWT.NONE,
                    getButtonLabels(version));
            if (returnCode == 0 && ProductVersion.canBeMigrated(version)) {
                repository.runMigrationInDialog();
            } else {
                try {
                    new SwitchRepositoriesWorkspaceHandler().execute(new ExecutionEvent());
                } catch (ExecutionException e) {
                    BonitaStudioLog.error(e);
                }

            }
        } else {
            if (repository instanceof Repository && repository.isShared("org.eclipse.team.svn.core.svnnature")) {
                ((Repository) repository).startUpdateJob();
            }
        }
    }

    private String[] getButtonLabels(String version) {
        return ProductVersion.canBeMigrated(version)
                ? new String[] { Messages.migrate, org.bonitasoft.studio.team.i18n.Messages.switchRepository }
                : new String[] { org.bonitasoft.studio.team.i18n.Messages.switchRepository };
    }

}
