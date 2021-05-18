/**
 * Copyright (C) 2009 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.engine.command;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.engine.ui.wizard.ExportBarWizard;
import org.bonitasoft.studio.model.process.MainProcess;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;

/**
 * @author Romain Bioteau
 */
public class ExportAsBarFileHandler extends AbstractHandler {

    /**
     * @return a List<File> of the all the created bar or proc
     * @see org.eclipse.core.commands.IHandler#execute(org.eclipse.core.commands.ExecutionEvent)
     */
    @Override
    public Object execute(final ExecutionEvent event) throws ExecutionException {
        if (PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().saveAllEditors(true)) {
            DiagramRepositoryStore diagramRepositoryStore = RepositoryManager.getInstance()
                    .getRepositoryStore(DiagramRepositoryStore.class);
            List<MainProcess> allDiagrams = new ArrayList<>();
            try {
                PlatformUI.getWorkbench().getProgressService().run(true, false, monitor -> {
                    diagramRepositoryStore.computeProcesses(monitor).stream()
                            .map(ModelHelper::getMainProcess)
                            .distinct()
                            .forEach(allDiagrams::add);
                    monitor.done();
                });
            } catch (InvocationTargetException | InterruptedException e) {
                throw new ExecutionException("Failed to retrieved all diagrams", e);
            }
            ExportBarWizard exportWizard = new ExportBarWizard(allDiagrams);
            WizardDialog dialog = new WizardDialog(Display.getDefault().getActiveShell(), exportWizard);
            dialog.open();
            diagramRepositoryStore.resetComputedProcesses();
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.core.commands.AbstractHandler#isEnabled()
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

}
