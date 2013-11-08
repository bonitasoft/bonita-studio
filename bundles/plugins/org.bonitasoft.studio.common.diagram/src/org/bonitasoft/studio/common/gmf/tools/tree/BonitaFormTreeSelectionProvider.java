/**
 * Copyright (C) 2011 BonitaSoft S.A.
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
package org.bonitasoft.studio.common.gmf.tools.tree;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.form.Form;
import org.bonitasoft.studio.model.form.SubmitFormButton;
import org.bonitasoft.studio.model.form.Validator;
import org.bonitasoft.studio.model.form.Widget;
import org.bonitasoft.studio.model.process.Connector;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

/**
 * @author Romain Bioteau
 *
 */
public class BonitaFormTreeSelectionProvider {


	private static BonitaFormTreeSelectionProvider INSTANCE ;


	private BonitaFormTreeSelectionProvider(){

	}

	public static BonitaFormTreeSelectionProvider getInstance(){
		if(INSTANCE == null){
			INSTANCE = new BonitaFormTreeSelectionProvider(); 
		}
		return INSTANCE ;
	}

	public void fireSelectionChanged(IGraphicalEditPart ep ,EObject element){
		TabbedPropertySheetPage page;
		try {
			page = getTabbedPropertySheetPage(element);
			page.selectionChanged(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor(), new StructuredSelection(ep)) ;
			page.setSelectedTab(getTabIdForElement(element)) ;

		} catch (PartInitException e) {
			BonitaStudioLog.error(e) ;
		}
	}

	private String getTabIdForElement(EObject element) {
		if(element instanceof Widget || element instanceof Form){
			return "Form.GeneralTab" ;
		}else if(element instanceof Validator){
			return "Form.ValidatorsTab" ;
		}else if(element instanceof Expression && element.eContainer() instanceof SubmitFormButton){
			return "Form.ButtonActionsTab" ;
		}else if(element instanceof Expression && (element.eContainer() instanceof Form)){
			return "Form.FormActionsTab" ;
		}else if(element instanceof Expression){
			return "Form.DataTab" ;
		}else if(element instanceof Connector){
			return "Form.FormConnectorTab" ;
		}
		return "Form.GeneralTab";
	}

	private TabbedPropertySheetPage getTabbedPropertySheetPage(EObject element) throws PartInitException {
		IViewPart viewPart = null ;
		for(IViewReference vr : PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getViewReferences() ){
			if(vr.getId().equals("org.bonitasoft.studio.views.properties.form.general")){
				viewPart = vr.getView(true) ;
			}
		}
		if(viewPart != null){
			viewPart.getViewSite().getWorkbenchWindow().getActivePage().showView(viewPart.getSite().getId()) ;
			return (TabbedPropertySheetPage) viewPart.getAdapter(TabbedPropertySheetPage.class);
		}
		return null;
	}

}
