/**
 * Copyright (C) 2018 Bonitasoft S.A.
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
package org.bonitasoft.studio.groovy.ui.handler;

import org.bonitasoft.studio.common.repository.filestore.AbstractFileStore;
import org.bonitasoft.studio.common.repository.filestore.FileStoreFinder;
import org.bonitasoft.studio.groovy.ui.wizard.NewGroovyClassWizard;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;


public class NewGroovyScript extends AbstractHandler {

    private FileStoreFinder selectionFinder = new FileStoreFinder();

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        final NewGroovyClassWizard wizard = new NewGroovyClassWizard();
        wizard.init(PlatformUI.getWorkbench(), (IStructuredSelection) selectionFinder.getSelectionInExplorer());
        new WizardDialog(Display.getDefault().getActiveShell(), wizard).open();
        AbstractFileStore.refreshExplorerView();
        return null;
    }

}
