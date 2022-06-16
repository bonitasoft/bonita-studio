/**
 * Copyright (C) 2012 BonitaSoft S.A.
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
package org.bonitasoft.studio.configuration.ui.handler;

import java.util.List;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.configuration.preferences.ConfigurationPreferenceConstants;
import org.bonitasoft.studio.configuration.ui.wizard.ConfigurationWizard;
import org.bonitasoft.studio.configuration.ui.wizard.ConfigurationWizardDialog;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.MainProcess;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;

/**
 * @author Romain Bioteau
 */
public class ConfigureHandler extends AbstractHandler {

    private IStatus status;

    public ConfigureHandler() {

    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.core.commands.IHandler#execute(org.eclipse.core.commands.ExecutionEvent)
     */
    @Override
    public Object execute(final ExecutionEvent event) throws ExecutionException {
        status = Status.OK_STATUS;
        Display.getDefault().syncExec(new Runnable() {

            @Override
            public void run() {
                String configuration = null;
                AbstractProcess process = null;
                if (event != null) {
                    configuration = event.getParameter("configuration");
                    process = (AbstractProcess) event.getParameters().get("process");
                }
                if (configuration == null || configuration.isEmpty()) {
                    configuration = ConfigurationPreferenceConstants.LOCAL_CONFIGURAITON;
                }

                MainProcess diagram = null;

                if (process == null) {
                    process = getSelectedProcess();
                    diagram = getCurrentDiagram();
                } else {
                    diagram = (MainProcess) process.eContainer();
                }

                final ConfigurationWizard wizard = new ConfigurationWizard(diagram, process, configuration);
                final ConfigurationWizardDialog dialog = new ConfigurationWizardDialog(Display.getDefault().getActiveShell(),
                        wizard);
                if (dialog.open() != Dialog.OK) {
                    status = Status.CANCEL_STATUS;
                } else {
                    status = Status.OK_STATUS;
                }
            }
        });
        return status;
    }

    private MainProcess getCurrentDiagram() {
        final IEditorPart part = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
        if (part != null && part instanceof DiagramEditor) {
            final EObject root = ((DiagramEditor) part).getDiagramEditPart().resolveSemanticElement();
            return ModelHelper.getMainProcess(root);
        }
        return null;
    }

    @SuppressWarnings("rawtypes")
    private AbstractProcess getSelectedProcess() {
        if (PlatformUI.getWorkbench().getActiveWorkbenchWindow() != null
                && PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage() != null) {
            final IEditorPart part = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
            if (part != null && part instanceof DiagramEditor) {
                final DiagramEditPart diagramEditPart = ((DiagramEditor) part).getDiagramEditPart();
                if (diagramEditPart != null) {
                    final List selection = ((DiagramEditor) part).getDiagramGraphicalViewer().getSelectedEditParts();
                    if (!selection.isEmpty() && selection.get(0) instanceof IGraphicalEditPart) {
                        final IGraphicalEditPart selectedPart = (IGraphicalEditPart) selection.get(0);
                        return ModelHelper.getParentProcess(selectedPart.resolveSemanticElement());
                    }
                }
            }
        }
        return null;
    }

    @Override
    public boolean isEnabled() {
        return getSelectedProcess() != null;
    }

}
