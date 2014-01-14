/**
 * Copyright (C) 2011-2013 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.debug.command;

import java.util.Set;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.jface.CustomWizardDialog;
import org.bonitasoft.studio.debug.i18n.Messages;
import org.bonitasoft.studio.debug.wizard.DebugProcessWizard;
import org.bonitasoft.studio.engine.command.RunProcessCommand;
import org.bonitasoft.studio.model.form.Form;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.diagram.form.part.FormDiagramEditor;
import org.bonitasoft.studio.model.process.diagram.part.ProcessDiagramEditor;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;

/**
 * @author Romain Bioteau
 *
 */
public class DebugProcessCommand extends AbstractHandler {


	private MainProcess selectedProcess;
	private boolean runSynchronously;

	public DebugProcessCommand() {
		this(false);
	}
	
	public DebugProcessCommand(boolean runSynchronously) {
		this.runSynchronously = runSynchronously;
	}

	public Object execute(ExecutionEvent event) throws ExecutionException {
		IEditorPart editorPart = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();

		if (editorPart instanceof ProcessDiagramEditor ) {
			org.bonitasoft.studio.model.process.diagram.part.ValidateAction.runValidation(((DiagramEditor)editorPart).getDiagram());
			Object selection = ((IStructuredSelection)editorPart.getSite().getSelectionProvider().getSelection()).getFirstElement() ;
			if(selection instanceof IGraphicalEditPart){
				EObject semanticObject = ((IGraphicalEditPart)selection).resolveSemanticElement() ;
				if(semanticObject instanceof Element){
					selectedProcess = ModelHelper.getMainProcess(semanticObject) ;
				}
			}
		}else if(editorPart instanceof FormDiagramEditor){
			org.bonitasoft.studio.model.process.diagram.form.part.ValidateAction.runValidation(((DiagramEditor)editorPart).getDiagram());
			DiagramEditPart formDiagram = ((DiagramDocumentEditor)editorPart).getDiagramEditPart();
			Form form = (Form) formDiagram.resolveSemanticElement();
			selectedProcess = ModelHelper.getMainProcess(form.eContainer());
		}
		DebugProcessWizard wizard =	new DebugProcessWizard(selectedProcess) ;
		CustomWizardDialog dialog = new CustomWizardDialog(Display.getDefault().getActiveShell(),wizard , Messages.DebugProcessButtonLabel) ;
		if(dialog.open() == Dialog.OK){
			Set<EObject> exludedObject = wizard.getExcludedObjects() ;
			new RunProcessCommand(exludedObject, runSynchronously).execute(event) ;
		}
		return null;
	}

	@Override
	public boolean isEnabled() {
		MainProcess process =  (MainProcess) getProcessInEditor() ;
		return process != null && process.isEnableValidation() ;
	}

	protected MainProcess getProcessInEditor() {
		if( PlatformUI.getWorkbench().getActiveWorkbenchWindow() != null && PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage() != null){
			IEditorPart editor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor() ;
			boolean isADiagram = editor != null && editor instanceof DiagramEditor;
			if(isADiagram){
				EObject root = ((DiagramEditor)editor).getDiagramEditPart().resolveSemanticElement() ;
				MainProcess mainProc = ModelHelper.getMainProcess(root) ;
				return mainProc ;
			}
		}
		return null;
	}
}
