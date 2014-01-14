/**
 * Copyright (C) 2009-2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.properties.form.sections.actions.contributions;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution;
import org.bonitasoft.studio.expression.editor.filter.AvailableExpressionTypeFilter;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.form.FormPackage;
import org.bonitasoft.studio.model.form.ImageWidget;
import org.eclipse.emf.databinding.edit.EMFEditProperties;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.layout.GridLayout;

/**
 * 
 * @author Baptiste Mesta
 *
 */
public class ImageWidgetInitialValueContribution extends InitialValueContribution implements IExtensibleGridPropertySectionContribution {

	public boolean isRelevantFor(EObject eObject) {
		return eObject instanceof ImageWidget;
	}

	protected GridLayout getCompositeLayout() {
		GridLayout layout = new GridLayout(2, false);
        layout.marginHeight = MARGIN_HEIGHT;
        layout.marginWidth = MARGIN_WIDTH;
		return layout;
	}

	protected void updateViewerInput(){
		if(expressionViewer != null && !expressionViewer.getControl().isDisposed()){
			Expression input = ((ImageWidget)widget).getImgPath() ;
			if(input == null){
				input = ExpressionFactory.eINSTANCE.createExpression() ;
				editingDomain.getCommandStack().execute(SetCommand.create(editingDomain, widget, FormPackage.Literals.IMAGE_WIDGET__IMG_PATH, input));
			}
			expressionViewer.setEditingDomain(editingDomain) ;
			expressionViewer.setInput(widget) ;
			dataBindingContext.bindValue(
					ViewersObservables.observeSingleSelection(expressionViewer),
					EMFEditProperties.value(editingDomain, FormPackage.Literals.IMAGE_WIDGET__IMG_PATH).observe(input));

			expressionViewer.setSelection(new StructuredSelection(input)) ;		
		}
	}

	@Override
	protected AvailableExpressionTypeFilter getExpressionViewerFilter() {
		AvailableExpressionTypeFilter filter = super.getExpressionViewerFilter();
		if(!ModelHelper.isInEntryPageFlowOnAPool(widget)){
			filter.getContentTypes().add(ExpressionConstants.DOCUMENT_REF_TYPE);
		}
		return filter;
	}

}
