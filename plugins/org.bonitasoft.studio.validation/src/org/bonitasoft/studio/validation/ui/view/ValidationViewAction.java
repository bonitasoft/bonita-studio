/**
 * Copyright (C) 2013 BonitaSoft S.A.
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
package org.bonitasoft.studio.validation.ui.view;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.validation.BatchValidationOperation;
import org.bonitasoft.studio.validation.i18n.Messages;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.IProgressService;

/**
 * @author Florine Boudin
 *
 */
public class ValidationViewAction extends Action {

	private TableViewer tableViewer;
	private IWorkbenchPage activePage ;

	/**
	 * @param tableViewer the tableViewer to set
	 */
	public void setTableViewer(TableViewer tableViewer) {
		this.tableViewer = tableViewer;
	}

	/**
	 * @param activePage the activePage to set
	 */
	public void setActivePage(IWorkbenchPage activePage) {
		setText(Messages.validationViewValidateButtonLabel);
		this.activePage = activePage;
	}

	/**
	 * 
	 */
	public ValidationViewAction() {
		super();
	}

	/**
	 * 
	 */
	@Override
	public void run() {
		Set<Diagram> toValidate = new HashSet<Diagram>();
		final IEditorPart ieditor = activePage.getActiveEditor();
		if(ieditor instanceof DiagramEditor ){
		
			Resource resource = ((DiagramEditor) ieditor).getDiagramEditPart().resolveSemanticElement().eResource();
			for(EObject content : resource.getContents()){
				if(content instanceof Diagram){
					toValidate.add((Diagram) content);
				}
			}
		}
		final BatchValidationOperation validateOperation = new BatchValidationOperation();
		validateOperation.setDiagramToValidate(toValidate);
		final IProgressService service = PlatformUI.getWorkbench().getProgressService() ;

		Display.getDefault().syncExec(new Runnable() {

			public void run() {
				try {
					service.run(true, false, validateOperation );
				} catch (InvocationTargetException e) {
					BonitaStudioLog.error(e);
				} catch (InterruptedException e) {
					BonitaStudioLog.error(e);
				}
			}
		});

		if(tableViewer!=null){
			tableViewer.setInput(ieditor);
		}
	}

}
