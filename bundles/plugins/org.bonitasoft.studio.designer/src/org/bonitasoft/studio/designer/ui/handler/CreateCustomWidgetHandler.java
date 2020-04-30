/**
 * Copyright (C) 2018 Bonitasoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.designer.ui.handler;

import java.lang.reflect.InvocationTargetException;

import javax.inject.Named;

import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.filestore.AbstractFileStore;
import org.bonitasoft.studio.designer.core.PageDesignerURLFactory;
import org.bonitasoft.studio.designer.core.operation.CreateCustomWidgetOperation;
import org.bonitasoft.studio.designer.core.repository.WebWidgetRepositoryStore;
import org.bonitasoft.studio.designer.i18n.Messages;
import org.bonitasoft.studio.ui.dialog.ExceptionDialogHandler;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.IProgressService;

public class CreateCustomWidgetHandler extends AbstractHandler {

    @Execute
    public void create(@Named(IServiceConstants.ACTIVE_SHELL) Shell shell,
            PageDesignerURLFactory urlFactory, RepositoryAccessor repositoryAccessor) {
        CreateCustomWidgetOperation operation = new CreateCustomWidgetOperation(urlFactory, repositoryAccessor);
        IProgressService progressService = PlatformUI.getWorkbench().getProgressService();
        try {
            progressService.run(true, false, operation);
            repositoryAccessor.getRepositoryStore(WebWidgetRepositoryStore.class).getResource().refreshLocal(IResource.DEPTH_ONE, new NullProgressMonitor());
            AbstractFileStore.refreshExplorerView();
        } catch (InvocationTargetException | InterruptedException e) {
            new ExceptionDialogHandler().openErrorDialog(shell, Messages.createWidgetFailed, e);
        } catch (CoreException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        RepositoryAccessor repositoryAccessor = new RepositoryAccessor();
        repositoryAccessor.init();
        create(Display.getDefault().getActiveShell(), PageDesignerURLFactory.INSTANCE, repositoryAccessor);
        return null;
    }
}
