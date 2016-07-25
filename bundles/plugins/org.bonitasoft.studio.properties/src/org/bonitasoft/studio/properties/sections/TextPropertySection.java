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
package org.bonitasoft.studio.properties.sections;

import org.bonitasoft.studio.common.properties.AbstractBonitaDescriptionSection;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

/**
 * 
 * @author Charles Souillard
 */
public abstract class TextPropertySection extends AbstractBonitaDescriptionSection {

	private Text text;
	
	/**
	 * The width of the text area
	 */
	public int textBoxSize = 100;
	
    public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
        super.createControls(parent, aTabbedPropertySheetPage);
        Composite composite = getWidgetFactory().createFlatFormComposite(parent);
        
        FormData data;

        this.text = getWidgetFactory().createText(composite, getDefaultValue()); //$NON-NLS-1$
        
        data = new FormData();
        data.left = new FormAttachment(0, STANDARD_LABEL_WIDTH);
        data.right = new FormAttachment(textBoxSize, 0);
        data.top = new FormAttachment(0, ITabbedPropertyConstants.VSPACE);
        this.text.setLayoutData(data);

        CLabel labelLabel = getWidgetFactory().createCLabel(composite, getPropertyDefinition().getName()); //$NON-NLS-1$
        data = new FormData();
        data.left = new FormAttachment(0, 0);
        data.right = new FormAttachment(text, -ITabbedPropertyConstants.HSPACE);
        data.top = new FormAttachment(text, 0, SWT.CENTER);
        labelLabel.setLayoutData(data);
    }

    public abstract EAttribute getPropertyDefinition();
    
    public String getDefaultValue() {
    	if (eObject != null) {
    		return eObject.eGet(getPropertyDefinition()).toString();
    	} else {
    		return null;
    	}
    }
}
