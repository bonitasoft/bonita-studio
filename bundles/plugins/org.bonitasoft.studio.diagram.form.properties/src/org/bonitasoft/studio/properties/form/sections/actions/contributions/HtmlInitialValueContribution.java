/**
 * Copyright (C) 2009 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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

import org.bonitasoft.studio.model.form.HtmlWidget;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

/**
 * 
 * @author Baptiste Mesta
 * 
 */
public class HtmlInitialValueContribution extends InitialValueContribution {


    /* (non-Javadoc)
     * @see org.bonitasoft.studio.properties.form.sections.actions.contributions.InitialValueContribution#isRelevantFor(org.eclipse.emf.ecore.EObject)
     */
    @Override
    public boolean isRelevantFor(EObject eObject) {
        return eObject instanceof HtmlWidget;
    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.properties.form.sections.actions.contributions.InitialValueContribution#createAllowHtmlButton(org.eclipse.swt.widgets.Composite, org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory)
     */
    @Override
    protected void createAllowHtmlButton(Composite composite, TabbedPropertySheetWidgetFactory widgetFactory) {

    }

}
