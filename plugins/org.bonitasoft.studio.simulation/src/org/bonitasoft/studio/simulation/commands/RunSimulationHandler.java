/**
 * Copyright (C) 2010 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.simulation.commands;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.jface.CustomWizardDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.model.form.Form;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.diagram.form.part.FormDiagramEditor;
import org.bonitasoft.studio.model.process.diagram.part.ProcessDiagramEditor;
import org.bonitasoft.studio.simulation.i18n.Messages;
import org.bonitasoft.studio.simulation.wizards.RunSimulationWizard;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.browser.IWebBrowser;
import org.eclipse.ui.browser.IWorkbenchBrowserSupport;
import org.eclipse.ui.internal.browser.WebBrowserPreference;
import org.eclipse.ui.internal.browser.WebBrowserUIPlugin;
import org.eclipse.ui.progress.IProgressService;

public class RunSimulationHandler extends AbstractHandler {

    private AbstractProcess selectedProcess;

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        if(isEnabled()){
            RunSimulationWizard wizard = new RunSimulationWizard(selectedProcess);

            CustomWizardDialog wizardDialog = new CustomWizardDialog(Display.getCurrent().getActiveShell(), wizard, Messages.run);

            if (wizardDialog.open() == IDialogConstants.OK_ID) {
                IProgressService progressManager = PlatformUI.getWorkbench().getProgressService();

                SimulationWithMonitorRunner runnable = new SimulationWithMonitorRunner(wizard.getSelectedProcess() , wizard.getPath(), wizard.getLoadProfileId(),wizard.getTimespan());
                try {
                    progressManager.busyCursorWhile(runnable);

                    if(!runnable.isCancelled() &&  runnable.getReportFile()  != null){
                        String name = runnable.getReportFile() ;

                        IFileStore fileStore = EFS.getLocalFileSystem().getStore(new Path(name));

                        if (!fileStore.fetchInfo().isDirectory() && fileStore.fetchInfo().exists()) {
                            IWorkbenchPage page=  PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
                            try {
                                IPreferenceStore webStore = WebBrowserUIPlugin.getInstance().getPreferenceStore();
                                int oldValue = webStore.getInt("browser-choice") ;
                                webStore.setValue("browser-choice",WebBrowserPreference.INTERNAL) ;

                                IWebBrowser browser = PlatformUI.getWorkbench().getBrowserSupport().createBrowser(IWorkbenchBrowserSupport.AS_EDITOR,"",fileStore.getName(),""); //$NON-NLS-1$

                                try {
                                    browser.openURL(new URL("file","",name));
                                } catch (MalformedURLException e) {
                                    BonitaStudioLog.error(e);
                                }
                                webStore.setValue("browser-choice",oldValue) ;

                            } catch (PartInitException e) {
                                BonitaStudioLog.error(e) ;
                            }
                        }
                    }

                } catch (InvocationTargetException e) {
                    BonitaStudioLog.error(e);
                } catch (InterruptedException e) {
                    BonitaStudioLog.error(e);
                }
            }
        }
        return null ;
    }

    @Override
    public boolean isEnabled() {
        IEditorPart editorPart = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
        selectedProcess = null ;
        if (editorPart instanceof ProcessDiagramEditor) {
            Object selection = ((IStructuredSelection) editorPart.getSite().getSelectionProvider().getSelection()).getFirstElement();
            if (selection instanceof IGraphicalEditPart) {
                EObject semanticObject = ((IGraphicalEditPart) selection).resolveSemanticElement();
                if (semanticObject instanceof Element) {
                    selectedProcess = ModelHelper.getParentProcess(semanticObject);
                }
            }
        } else if (editorPart instanceof FormDiagramEditor) {
            DiagramEditPart formDiagram = ((DiagramDocumentEditor) editorPart).getDiagramEditPart();
            Form form = (Form) formDiagram.resolveSemanticElement();
            selectedProcess = ModelHelper.getParentProcess(form.eContainer());
        }
        return selectedProcess != null;
    }

}
