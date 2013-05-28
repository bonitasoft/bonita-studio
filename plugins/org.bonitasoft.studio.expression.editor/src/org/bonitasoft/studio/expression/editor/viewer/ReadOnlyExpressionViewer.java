/**
 * Copyright (C) 2012-2013 BonitaSoft S.A.
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
package org.bonitasoft.studio.expression.editor.viewer;

import org.bonitasoft.studio.expression.editor.i18n.Messages;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

/**
 * @author Romain Bioteau
 *
 */
public class ReadOnlyExpressionViewer extends ExpressionViewer {

	public ReadOnlyExpressionViewer(Composite composite, int style,
			TabbedPropertySheetWidgetFactory widgetFactory,
			EditingDomain editingDomain, EReference expressionReference) {
		super(composite, style, widgetFactory, editingDomain, expressionReference);
	}
	
	@Override
	protected void createTextControl(int style,
			TabbedPropertySheetWidgetFactory widgetFactory) {
		super.createTextControl(style | SWT.READ_ONLY , widgetFactory);
		getTextControl().setBackground(Display.getDefault().getSystemColor(SWT.COLOR_WHITE));
		getTextControl().setMessage(Messages.selectTarget);
	}
	
	@Override
	protected void manageNatureProviderAndAutocompletionProposal(Object input) {
		super.manageNatureProviderAndAutocompletionProposal(input);
		setProposalsFiltering(false);
	}
	
	@Override
	protected void bindEditableText(IObservableValue typeObservable) {

	}
	
	@Override
	protected ToolItem createEditToolItem(ToolBar tb) {
		return null;
	}
	

}
