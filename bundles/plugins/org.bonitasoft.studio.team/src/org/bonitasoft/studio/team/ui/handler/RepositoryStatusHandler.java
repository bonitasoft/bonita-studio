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
import org.bonitasoft.studio.team.repository.Repository;
import org.bonitasoft.studio.team.ui.dialog.SVNRepositoryStatusDialog;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.team.svn.core.SVNTeamPlugin;

/**
 * @author Baptiste Mesta
 */
public class RepositoryStatusHandler extends AbstractHandler {

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        Repository currentRepository = (Repository) RepositoryManager.getInstance().getCurrentRepository();
        Shell activeShell = Display.getDefault().getActiveShell();
        return new SVNRepositoryStatusDialog(activeShell).open();
    }

    @Override
    public boolean isEnabled() {
        return RepositoryManager.getInstance().getCurrentRepository().isShared(SVNTeamPlugin.NATURE_ID);
    }

}
