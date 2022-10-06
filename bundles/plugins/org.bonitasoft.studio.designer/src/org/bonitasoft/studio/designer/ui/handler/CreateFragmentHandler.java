/*******************************************************************************
 * Copyright (C) 2018 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.designer.ui.handler;

import java.lang.reflect.InvocationTargetException;

import javax.inject.Named;

import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.designer.core.PageDesignerURLFactory;
import org.bonitasoft.studio.designer.core.operation.CreateFragmentOperation;
import org.bonitasoft.studio.designer.i18n.Messages;
import org.bonitasoft.studio.ui.dialog.ExceptionDialogHandler;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.IProgressService;

public class CreateFragmentHandler extends AbstractHandler {

    @Execute
    public void create(@Named(IServiceConstants.ACTIVE_SHELL) Shell shell,
            PageDesignerURLFactory urlFactory, RepositoryAccessor repositoryAccessor) {
        CreateFragmentOperation operation = new CreateFragmentOperation(urlFactory, repositoryAccessor);
        IProgressService progressService = PlatformUI.getWorkbench().getProgressService();
        try {
            progressService.run(true, false, operation);
        } catch (InvocationTargetException | InterruptedException e) {
            new ExceptionDialogHandler().openErrorDialog(shell, Messages.createFragmentFailed, e);
        }
    }

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        create(Display.getDefault().getActiveShell(), PageDesignerURLFactory.INSTANCE, RepositoryManager.getInstance().getAccessor());
        return null;
    }

}
