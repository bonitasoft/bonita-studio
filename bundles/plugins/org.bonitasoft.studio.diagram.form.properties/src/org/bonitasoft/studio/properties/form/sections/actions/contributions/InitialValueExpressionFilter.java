/**
 * Copyright (C) 2013 BonitaSoft S.A.
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
import org.bonitasoft.studio.common.emf.tools.WidgetHelper;
import org.bonitasoft.studio.expression.editor.filter.AvailableExpressionTypeFilter;
import org.bonitasoft.studio.expression.editor.formfield.FormFieldExpressionProvider;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.form.Form;
import org.bonitasoft.studio.model.form.Widget;
import org.bonitasoft.studio.model.process.PageFlow;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.viewers.Viewer;

/**
 * @author Romain Bioteau
 *
 */
public class InitialValueExpressionFilter extends AvailableExpressionTypeFilter {

	public InitialValueExpressionFilter(String[] contentTypes) {
		super(contentTypes);
	}
	
	private Widget widget;

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
	 */
	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		if(element instanceof FormFieldExpressionProvider){
			return true;
		}
		boolean select = super.select(viewer, parentElement, element);
		if(!select){
			select = selectFormFieldExpressionFromOtherFormOfPageflow(element);
		}
		return select;
	}

	protected boolean selectFormFieldExpressionFromOtherFormOfPageflow(
			Object element) {
		if(element instanceof Expression 
				&& ExpressionConstants.FORM_FIELD_TYPE.equals(((Expression) element).getType())){
			if(widget != null){
				Form form = ModelHelper.getParentForm(widget);
				if(form != null){
					Expression expression = (Expression) element;
					PageFlow pageflow = getPageFlow(form);
					if(pageflow != null){
						for(Form f : pageflow.getForm()){
							if(!EcoreUtil.equals(f, form)){
								for(Widget w : ModelHelper.getAllAccessibleWidgetInsideForm(f)){
									if(expression.getName().equals(WidgetHelper.FIELD_PREFIX+w.getName())){
										return true;
									}
								}
							}
						}
					}
				}
			}
		}
		return false;
	}

	private PageFlow getPageFlow(Form form) {
		if(form != null){
			EObject eContainer = form.eContainer();
			if(eContainer instanceof PageFlow){
				return (PageFlow) eContainer;
			}
		}
		return null;
	}

	public void setWidget(Widget widget) {
		this.widget = widget ;
	}

}
