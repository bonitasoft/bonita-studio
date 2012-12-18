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
package org.bonitasoft.studio.application.actions;

import java.lang.reflect.InvocationTargetException;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.model.form.Form;
import org.bonitasoft.studio.model.process.PageFlow;
import org.bonitasoft.studio.model.process.diagram.form.part.FormDiagramEditor;
import org.bonitasoft.studio.model.process.diagram.part.ProcessDiagramEditor;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.IProgressService;

public class PreviewFormHandler extends AbstractHandler {

    private Form form = null;

    public PreviewFormHandler(Form form) {
        this.form = form;
    }


    /**
     * Default constructor used in the menu bar
     */
    public PreviewFormHandler() {
    }


    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        IProgressService progressManager = PlatformUI.getWorkbench().getProgressService();
        if(form == null){//if the constructor doesn't initialize it.
            if (PlatformUI.isWorkbenchRunning()) {
                IEditorPart editor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
                if (editor instanceof FormDiagramEditor) {
                    FormDiagramEditor formEditor = (FormDiagramEditor) editor;
                    if (formEditor.getDiagram().getElement() instanceof Form) {
                        form = (Form) formEditor.getDiagram().getElement();
                    }
                } else if (editor instanceof ProcessDiagramEditor) {
                    // cal it on the first form if a task is selected
                    ProcessDiagramEditor processEditor = (ProcessDiagramEditor) editor;
                    Object selection = ((IStructuredSelection) processEditor.getSite().getSelectionProvider().getSelection()).getFirstElement();
                    if (selection instanceof IGraphicalEditPart) {
                        EObject semanticObject = ((IGraphicalEditPart) selection).resolveSemanticElement();
                        if (semanticObject instanceof PageFlow) {
                            EList<Form> forms = ((PageFlow) semanticObject).getForm();
                            if (forms.size() > 0) {
                                form = forms.get(0);
                            }
                        }
                    }
                }
            }
        }
        if(form!=null){
            IRunnableWithProgress runnable = new IRunnableWithProgress() {

                @Override
                public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
                    //					try {
                    //						monitor.beginTask(Messages.generatePreview, 2);
                    //				//		final File preview = ((PreviewForm) ExporterService.getInstance().getExporterService(SERVICE_TYPE.FormPreview)).previewForm(form,null);
                    //						monitor.setTaskName(Messages.openBrowser);
                    //					//	new OpenBrowserCommand(preview.toURL(), BonitaPreferenceConstants.APPLICATION_BROWSER_ID, Messages.formPreview).execute(null);
                    //					} catch (ExecutionException e) {
                    //						BonitaStudioLog.log(e);
                    //					} catch (MalformedURLException e) {
                    //						BonitaStudioLog.log(e);
                    //					}
                }
            };

            try {
                progressManager.busyCursorWhile(runnable);
            } catch (InvocationTargetException e) {
                BonitaStudioLog.error(e);
            } catch (InterruptedException e) {
                BonitaStudioLog.error(e);
            } finally {
                form = null;//reset to null in order to be used with the constructor
            }
        }

        return null;

    }

    @Override
    public boolean isEnabled() {
        return false;
        //		form = null ;
        //		if(PlatformUI.isWorkbenchRunning())
        //		{
        //			//does not refresh properly just checking editor for now
        //			IEditorPart editor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
        //			if(editor instanceof ProcessDiagramEditor){
        //				ProcessDiagramEditor processEditor = (ProcessDiagramEditor) editor;
        //				Object selection = ((IStructuredSelection) processEditor.getSite().getSelectionProvider().getSelection()).getFirstElement();
        //				if (selection instanceof IGraphicalEditPart) {
        //					EObject semanticObject = ((IGraphicalEditPart) selection).resolveSemanticElement();
        //					if (semanticObject instanceof PageFlow) {
        //						EList<Form> forms = ((PageFlow) semanticObject).getForm();
        //						if (forms.size() > 0) {
        //							this.form = forms.get(0);
        //						}
        //					}
        //				}
        //
        //			}else if(editor instanceof FormDiagramEditor ){
        //				FormDiagramEditor formEditor = (FormDiagramEditor) editor;
        //				if (formEditor.getDiagram().getElement() instanceof Form) {
        //					this.form = (Form) formEditor.getDiagram().getElement();
        //				}
        //			}
        //			return  form != null ;
        //		}
        //		return false;
    }

}
