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
package org.bonitasoft.studio.groovy.ui.widgets.pojo;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.bonitasoft.studio.common.BonitaConstants;
import org.bonitasoft.studio.groovy.ui.widgets.AbstractComboElementsProvider;
import org.bonitasoft.studio.groovy.ui.widgets.ComboElementsProvider;
import org.bonitasoft.studio.groovy.ui.widgets.TextOrData;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.JavaObjectData;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;

/**
 * @author Mickael Istria
 *
 */
public class JavaObjectComboProvider extends AbstractComboElementsProvider implements ComboElementsProvider {

	public static final String ADDITIONAL_DATA_LISTENER = "JavaObjectComboProvider.AdditionalDataListener";
	
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
		if (data != null && data instanceof JavaObjectData && !data.isMultiple()) {
			JavaObjectData javaData = (JavaObjectData)data;
			TextOrData.Type actualType = textOrData.getIOType();
			if (actualType == null) {
				actualType = TextOrData.Type.READ;
			}
			Shell shell = textOrData.getControl().getShell();
			if (actualType == TextOrData.Type.READ) { 
				BrowseJavaDialog dialog = new BrowseJavaDialog(shell, "", javaData, textOrData) ;
				int code = dialog.open() ;
				if(Dialog.OK == code){
					String value = dialog.getScript();
					textOrData.setText(value) ;
					return value  ;
				}else{
					textOrData.revertValue() ;
				}
			} else if (actualType == TextOrData.Type.WRITE) {
				BrowseWriteToJavaDialog dialog = new BrowseWriteToJavaDialog(shell, textOrData.getText(), javaData.getClassName(), textOrData.getText());
				if (dialog.open() == Dialog.OK) {
					ModifyListener listener = (ModifyListener) textOrData.getControl().getData(ADDITIONAL_DATA_LISTENER);
					String path = dialog.generateJavaAdditionalPath();
					if (listener != null) {
						Event e = new Event();
						e.widget = textOrData.getControl();
						ModifyEvent event = new ModifyEvent(e);
						event.data = path;
						listener.modifyText(event);
					}
					if (path != null && !path.isEmpty()) {
						textOrData.setText(javaData.getName() + BonitaConstants.JAVA_VAR_SEPARATOR + dialog.generateJavaAdditionalPath()) ;
						return javaData.getName() + BonitaConstants.JAVA_VAR_SEPARATOR + dialog.generateJavaAdditionalPath();
					} else {
						textOrData.setText(javaData.getName()) ;
						return javaData.getName();
					}
				} else {
					textOrData.revertValue() ;
				}
			}
		}
		return null;
	}

}
