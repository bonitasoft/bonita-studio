/**
 * Copyright (C) 2019 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.application.handler;

import java.lang.reflect.InvocationTargetException;

import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.application.operation.DeployProjectOperation;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;

public class DeployProjectHandler {

    @Execute
    public void buildProject(RepositoryAccessor repositoryAccessor) throws InvocationTargetException, InterruptedException {
        DeployProjectOperation operation = new DeployProjectOperation(repositoryAccessor);
        PlatformUI.getWorkbench().getProgressService().run(true, false, operation);
        if (!operation.getStatus().isOK()) {
            MessageDialog.openError(Display.getDefault().getActiveShell(), Messages.deployErrorTitle,
                    operation.getStatus().getMessage());
            BonitaStudioLog.error(operation.getStatus().getMessage(), operation.getStatus().getException());
        }
    }

}
