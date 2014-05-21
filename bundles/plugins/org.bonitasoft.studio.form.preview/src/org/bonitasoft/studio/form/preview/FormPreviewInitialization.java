/**
 * Copyright (C) 2012 BonitaSoft S.A.
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

package org.bonitasoft.studio.form.preview;

import java.util.List;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.form.Form;
import org.bonitasoft.studio.model.form.Widget;
import org.bonitasoft.studio.repository.themes.ApplicationLookNFeelFileStore;
import org.eclipse.ui.internal.browser.IBrowserDescriptor;

/**
 *@Author Aur�lie Zara
 *
 *
 */
public class FormPreviewInitialization extends
		AbstractFormPreviewInitialization {

	/**
	 * @param form
	 * @param lookNFeel
	 * @param browser
	 */
	public FormPreviewInitialization(Form form,
			ApplicationLookNFeelFileStore lookNFeel, IBrowserDescriptor browser) {
		super(form, lookNFeel, browser);
		
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.form.preview.AbstractFormPreviewInitialization#initializeAllWidgets(org.bonitasoft.studio.model.form.Form)
	 */
	@Override
	protected void initializeAllWidgets(Form formCopy) {
		List<Widget> widgets = ModelHelper.getAllWidgetInsideForm(formCopy);
		WidgetSwitch widgetSwitch = new WidgetSwitch();
		for (Widget widget:widgets){
			deleteAllOperations(widget);
			if (!widget.getDependOn().isEmpty()){
				Expression exprDisplayDependentWidgetOnly = widget.getDisplayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition();
				exprDisplayDependentWidgetOnly.setContent("true");
				exprDisplayDependentWidgetOnly.setReturnType(Boolean.class.getName());
				Expression exprDisplayAfterEventDependsOnCondiditionScript = widget.getDisplayAfterEventDependsOnConditionScript();
				exprDisplayAfterEventDependsOnCondiditionScript.setContent("true");
				exprDisplayAfterEventDependsOnCondiditionScript.setReturnType(Boolean.class.getName());
			}
			widget = (Widget)widgetSwitch.doSwitch(widget);
		}

	}

}
