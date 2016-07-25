/**
 * Copyright (C) 2011-2012 BonitaSoft S.A.
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
package org.bonitasoft.studio.properties.form.sections.options.contributions;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.properties.AbstractPropertySectionContribution;
import org.bonitasoft.studio.common.properties.ExtensibleGridPropertySection;
import org.bonitasoft.studio.expression.editor.filter.AvailableExpressionTypeFilter;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
import org.bonitasoft.studio.form.properties.i18n.Messages;
import org.bonitasoft.studio.model.form.FormPackage;
import org.bonitasoft.studio.model.form.Widget;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.edit.EMFEditProperties;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.databinding.swt.IWidgetValueProperty;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.databinding.viewers.ViewerProperties;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

/**
 * @author Baptiste Mesta
 *
 */
public class InjectWidgetContribution extends AbstractPropertySectionContribution {

	private Button checkbox;
	private ExpressionViewer expressionViewer;
	private EMFDataBindingContext context;
	private final IWidgetValueProperty widgetValuePropertySelection  = WidgetProperties.selection();
	private final UpdateValueStrategy never = new UpdateValueStrategy(UpdateValueStrategy.POLICY_NEVER);


	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution#isRelevantFor(org.eclipse.emf.ecore.EObject)
	 */
	public boolean isRelevantFor(final EObject eObject) {
		return eObject instanceof Widget;
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution#refresh()
	 */
	public void refresh() {

	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution#getLabel()
	 */
	public String getLabel() {
		return Messages.injectWidgetIf;
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution#createControl(org.eclipse.swt.widgets.Composite, org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory, org.bonitasoft.studio.common.properties.ExtensibleGridPropertySection)
	 */
	public void createControl(final Composite composite, final TabbedPropertySheetWidgetFactory widgetFactory, final ExtensibleGridPropertySection extensibleGridPropertySection) {
		composite.setLayout(new GridLayout(2, false));
		composite.setLayoutData(GridDataFactory.fillDefaults().grab(true,false).create()) ;
		checkbox = widgetFactory.createButton(composite, "", SWT.CHECK);
		expressionViewer = new ExpressionViewer(composite, SWT.BORDER, widgetFactory,editingDomain, FormPackage.Literals.WIDGET__INJECT_WIDGET_SCRIPT);
		expressionViewer.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true,false).create()) ;
		expressionViewer.addFilter(new AvailableExpressionTypeFilter(new String[]{ExpressionConstants.PARAMETER_TYPE,ExpressionConstants.VARIABLE_TYPE,ExpressionConstants.SCRIPT_TYPE,ExpressionConstants.CONSTANT_TYPE,ExpressionConstants.FORM_FIELD_TYPE,ExpressionConstants.DOCUMENT_TYPE} ));
		//bind
		if(context != null){
			context.dispose();
		}
		if(expressionViewer != null && ! expressionViewer.getControl().isDisposed()){
			context = new EMFDataBindingContext();
			expressionViewer.setInput(getEObject());
			context.bindValue(ViewerProperties.singleSelection().observe(expressionViewer)
					, EMFEditProperties.value(editingDomain, FormPackage.Literals.WIDGET__INJECT_WIDGET_SCRIPT).observe(getEObject()));

			context.bindValue(widgetValuePropertySelection.observe(checkbox),EMFEditProperties.value(editingDomain, FormPackage.Literals.WIDGET__INJECT_WIDGET_CONDITION).observe(getEObject()));
			context.bindValue(WidgetProperties.enabled().observe(expressionViewer.getControl()),EMFEditProperties.value(editingDomain, FormPackage.Literals.WIDGET__INJECT_WIDGET_CONDITION).observe(getEObject()),
					never,
					null);
		}
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution#dispose()
	 */
	public void dispose() {
		if(context != null){
			context.dispose();
		}
	}

}
