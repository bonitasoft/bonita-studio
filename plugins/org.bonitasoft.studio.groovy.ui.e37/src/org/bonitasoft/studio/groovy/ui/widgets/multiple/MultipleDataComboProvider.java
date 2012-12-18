/**
 * Copyright (C) 2010 BonitaSoft S.A.
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
package org.bonitasoft.studio.groovy.ui.widgets.multiple;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.bonitasoft.studio.groovy.GroovyUtil;
import org.bonitasoft.studio.groovy.ui.widgets.AbstractComboElementsProvider;
import org.bonitasoft.studio.groovy.ui.widgets.ComboElementsProvider;
import org.bonitasoft.studio.groovy.ui.widgets.TextOrData;
import org.bonitasoft.studio.groovy.ui.widgets.TextOrData.Type;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.Element;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.jface.dialogs.Dialog;

/**
 * @author Mickael Istria
 *
 */
public class MultipleDataComboProvider extends AbstractComboElementsProvider implements ComboElementsProvider {

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.groovy.widgets.ComboElementsProvider#appliesTo(org.bonitasoft.studio.groovy.widgets.TextOrData)
	 */
	public boolean appliesTo(TextOrData textOrData) {
		return true;
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.groovy.widgets.ComboElementsProvider#getElements(org.bonitasoft.studio.groovy.widgets.TextOrData, org.bonitasoft.studio.model.process.Element, java.util.Collection)
	 */
	public List<String> getElements(TextOrData textOrData, Element eObject, Collection<EClass> eClasses) {
		return Collections.emptyList();
	}


	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.groovy.widgets.ComboElementsProvider#proceed(org.bonitasoft.studio.groovy.widgets.TextOrData)
	 */
	public String proceed(TextOrData textOrData) {
		Data data = textOrData.getSelectedData();
		if (data != null && data.isMultiple()) {
			if (textOrData.getIOType() == Type.READ) {
				SelectGetterOnMultipleDataDialog dialog = new SelectGetterOnMultipleDataDialog(textOrData, data);
				if (dialog.open() == Dialog.OK) {
					if (!dialog.getScript().equals(textOrData.getText())) {
						String script = dialog.getScript();
						while (GroovyUtil.isGroovyExpression(script)) {
							script = GroovyUtil.toSimpleExpression(script) ;
						}
						script = GroovyUtil.toGroovyExpression(script) ;
						textOrData.setText(script) ;
						return script;
					}
				} else {
					textOrData.revertValue();
				}
			} else if (textOrData.getIOType() == Type.WRITE) {
				SelectWriteToMultipleDataDialog dialog = new SelectWriteToMultipleDataDialog(textOrData, data);
				if (dialog.open() == Dialog.OK) {
					if (!dialog.getScript().equals(textOrData.getText())) {
						textOrData.setText(dialog.getScript()) ;
						return dialog.getScript();
					}
				} else {
					textOrData.revertValue();
				}
			}
		}
		return null;
	}

}
