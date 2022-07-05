/**
 * Copyright (C) 2009 BonitaSoft S.A.
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
package org.bonitasoft.studio.properties.sections.lane;

import org.bonitasoft.studio.common.gmf.tools.GMFTools;
import org.bonitasoft.studio.common.properties.ExtensibleGridPropertySection;
import org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.Lane;
import org.bonitasoft.studio.model.process.diagram.part.ProcessDiagramEditor;
import org.bonitasoft.studio.properties.sections.general.ProcessElementNameContribution;
import org.eclipse.core.databinding.observable.ChangeEvent;
import org.eclipse.core.databinding.observable.IChangeListener;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

/**
 * @author Mickael Istria
 *
 */
public class LaneNameSectionContribution extends ProcessElementNameContribution implements IExtensibleGridPropertySectionContribution {

	public LaneNameSectionContribution(
			TabbedPropertySheetPage tabbedPropertySheetPage, ExtensibleGridPropertySection extensibleGridPropertySection) {
		super(tabbedPropertySheetPage);
	}

	@Override
	protected void createBinding(EMFDataBindingContext context) {
		super.createBinding(context);
		observable.addChangeListener(new IChangeListener() {

			public void handleChange(ChangeEvent event) {
				if(element != null){
					IEditorPart part = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor() ;
					if(part instanceof ProcessDiagramEditor){
						IGraphicalEditPart ep = GMFTools.findEditPart(((ProcessDiagramEditor)part).getDiagramEditPart(), element) ;
						ep.refresh();
					}
				}
			}
		}) ;
	}

	@Override
	public boolean isRelevantFor(EObject eObject) {
		return super.isRelevantFor(eObject) && eObject instanceof Lane;
	}

	public void setEObject(EObject object) {
		this.element = (Element) object;
	}


	public void setSelection(ISelection selection) {
		this.selection = selection;

	}

}
