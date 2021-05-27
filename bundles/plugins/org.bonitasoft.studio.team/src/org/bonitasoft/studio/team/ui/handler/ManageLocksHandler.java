/*******************************************************************************
 * Copyright (C) 2009, 2013 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.team.ui.handler;

import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.team.core.SVNLockManager;
import org.bonitasoft.studio.team.repository.Repository;
import org.bonitasoft.studio.team.ui.dialog.LockDialogModel;
import org.bonitasoft.studio.team.ui.dialog.ManageLocksDialog;
import org.bonitasoft.studio.team.ui.provider.TeamRepositoryLabelDecorator;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.PlatformUI;

public class ManageLocksHandler extends AbstractConnectedHandler {

    /*
     * (non-Javadoc)
     * @see org.eclipse.core.commands.IHandler#execute(org.eclipse.core.commands.ExecutionEvent)
     */
    @Override
    public Object execute(final ExecutionEvent event) throws ExecutionException {
        new ManageLocksDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
                new LockDialogModel(
                        (Repository) RepositoryManager.getInstance().getCurrentRepository(),
                        new SVNLockManager(RepositoryManager.getInstance().getRepositoryStore(DiagramRepositoryStore.class)),
                        new TeamRepositoryLabelDecorator())).open();
        return null;
    }

}
