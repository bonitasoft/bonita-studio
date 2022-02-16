/*******************************************************************************
 * Copyright (C) 2009, 2013 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.team.ui.handler;

import java.lang.reflect.InvocationTargetException;

import org.bonitasoft.studio.common.jface.BonitaErrorDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.team.i18n.Messages;
import org.bonitasoft.studio.team.operations.UpdateRepositoryResourcesOperation;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.IProgressService;

public class UpdateWorkspaceHandler extends AbstractConnectedHandler {

    @Override
    public Object execute(final ExecutionEvent event) throws ExecutionException {
        final IProgressService service = PlatformUI.getWorkbench().getProgressService();
        try {
            final UpdateRepositoryResourcesOperation updateRepositoryResourcesOperation = new UpdateRepositoryResourcesOperation(
                    RepositoryManager.getInstance().getCurrentRepository().getProject());
            service.run(true, false, updateRepositoryResourcesOperation);
        } catch (final InvocationTargetException | InterruptedException e) {
            BonitaStudioLog.error(e);
            new BonitaErrorDialog(Display.getDefault().getActiveShell(), Messages.updateErrorTitle,
                    Messages.updateErrorMessage, e).open();
        }

        return null;
    }

}
