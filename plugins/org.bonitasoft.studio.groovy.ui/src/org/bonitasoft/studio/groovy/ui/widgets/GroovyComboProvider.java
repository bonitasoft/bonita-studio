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
package org.bonitasoft.studio.groovy.ui.widgets;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.groovy.GroovyUtil;
import org.bonitasoft.studio.groovy.ui.Messages;
import org.bonitasoft.studio.groovy.ui.dialog.BonitaGroovyEditorDialog;
import org.bonitasoft.studio.model.process.Element;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.widgets.Display;

/**
 * @author Romain Bioteau
 *
 */
public class GroovyComboProvider extends AbstractComboElementsProvider implements ComboElementsProvider {

	public static final String SKIP_GROOVY_PROVIDER = "Skip Groovy combo provider";

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.groovy.widgets.ComboElementsProvider#appliesTo(org.bonitasoft.studio.groovy.widgets.Element)
	 */
	public boolean appliesTo(TextOrData textOrData) {
		if (Boolean.TRUE.equals(textOrData.getControl().getData(SKIP_GROOVY_PROVIDER))) {
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.groovy.widgets.ComboElementsProvider#getElements(org.bonitasoft.studio.groovy.widgets.TextOrData, org.bonitasoft.studio.groovy.widgets.Element, java.util.Collection)
	 */
	public List<String> getElements(TextOrData textOrData, Element eObject, Collection<EClass> eClasses) {
		List<String> res = new ArrayList<String>();
		res.add(Messages.editorButtonLabel);
		return res;
	}


	public String proceed(TextOrData textOrData) {
		String value = textOrData.getText() ;
		if (Messages.editorButtonLabel.equals(value)) {
			String res = openGroovyEditor(textOrData, textOrData.getPreviousEntry());
			if (textOrData.showGroovyPrefixSuffix() && !res.startsWith(GroovyUtil.GROOVY_PREFIX)) {
				res = GroovyUtil.GROOVY_PREFIX + res + GroovyUtil.GROOVY_SUFFIX;
			} else if (!textOrData.showGroovyPrefixSuffix() && res.startsWith(GroovyUtil.GROOVY_PREFIX)) {
				res = res.substring(2, res.length() - 1);
			}
			textOrData.setText(res);
			return res;
		}
		return null ;
	}

	/**
	 * @param textOrData
	 * @return
	 */
	public String openGroovyEditor(TextOrData textOrData, String initialValue) {
		BonitaGroovyEditorDialog dialog = null;
		try {
		
			if(initialValue.startsWith("[") && initialValue.endsWith("]")){
				initialValue = "" ;
			}
			dialog = new BonitaGroovyEditorDialog(Display.getDefault().getActiveShell(), initialValue,textOrData.getElement());
			dialog.setBlockOnOpen(true);     
		} catch (Exception e1) {
			BonitaStudioLog.error(e1);
		}
		if (dialog != null && dialog.open() == Dialog.OK) {
			textOrData.setText(dialog.getExpression());
			return dialog.getExpression() ;
		}else{
			textOrData.revertValue();
			return textOrData.getPreviousEntry() ;
		}
	}

}
