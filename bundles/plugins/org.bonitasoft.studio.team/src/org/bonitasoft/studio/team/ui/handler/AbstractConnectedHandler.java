/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.team.ui.handler;

import java.lang.reflect.InvocationTargetException;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.team.repository.CheckConnectionOperation;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.ui.PlatformUI;


public abstract class AbstractConnectedHandler extends AbstractHandler {

    /*
     * (non-Javadoc)
     * @see org.eclipse.core.commands.AbstractHandler#isEnabled()
     */
    @Override
    public boolean isEnabled() {
        if (PlatformUI.isWorkbenchRunning()) {
            try {
                final CheckConnectionOperation checkConnectionOperation = new CheckConnectionOperation(RepositoryManager.getInstance().getCurrentRepository().getProject());
                checkConnectionOperation.run(AbstractRepository.NULL_PROGRESS_MONITOR);
                return checkConnectionOperation.getStatus().isOK();
            } catch (InvocationTargetException | InterruptedException e) {
                BonitaStudioLog.error(e);
            }
        }
        return false;
    }

}
