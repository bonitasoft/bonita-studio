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
package org.bonitasoft.studio.diagram.form.custom.commands;

import org.bonitasoft.studio.model.form.FormFactory;
import org.bonitasoft.studio.model.form.FormPackage;
import org.bonitasoft.studio.model.form.Widget;
import org.bonitasoft.studio.model.form.WidgetLayoutInfo;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequest.ViewDescriptor;
import org.eclipse.gmf.runtime.emf.type.core.commands.SetValueCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.SetRequest;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @author Aurelien Pupier
 * 
 */
public class GridLayoutCreateCommandInForm extends AbstractGridLayoutCreateCommand {
	
	/**
	 * @param editingDomain
	 * @param viewDescriptor
	 * @param containerView
	 * @param column 
	 * @param line 
	 */
	public GridLayoutCreateCommandInForm(TransactionalEditingDomain editingDomain, ViewDescriptor viewDescriptor, View containerView, int line, int column) {
		super(editingDomain, viewDescriptor, containerView, line, column);
	}

	
	@Override
	protected void createAtTheRigthPlace(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		/*Set the correct index (line,column) on the widget*/	
		WidgetLayoutInfo widgetLayoutInfo = FormFactory.eINSTANCE.createWidgetLayoutInfo();
		widgetLayoutInfo.setColumn(column);
		widgetLayoutInfo.setLine(line);
		if(createdView.getElement() instanceof Widget){
			if(((Widget) createdView.getElement()).getWidgetLayoutInfo() == null){
				SetRequest setRequest = new SetRequest(createdView.getElement(), FormPackage.eINSTANCE.getWidget_WidgetLayoutInfo(), widgetLayoutInfo);
				SetValueCommand setCommand = new SetValueCommand(setRequest);
				setCommand.execute(monitor, info);
			}	
		}
		
	}

}
