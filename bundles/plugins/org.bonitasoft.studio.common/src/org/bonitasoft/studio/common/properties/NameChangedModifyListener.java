/**
 * Copyright (C) 2009 BonitaSoft S.A.
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
package org.bonitasoft.studio.common.properties;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * @author Mickael Istria
 *
 */
public class NameChangedModifyListener implements ModifyListener {

	private static boolean happened = false;
	
	/* (non-Javadoc)
	 * @see org.eclipse.swt.events.ModifyListener#modifyText(org.eclipse.swt.events.ModifyEvent)
	 */
	public void modifyText(ModifyEvent e) {
		if (!happened && isValid(((Text)e.widget).getText().toLowerCase())) {
			happened = true;
			performEvent();
		}
	}

	/**
	 * 
	 */
	private void performEvent() {
		MessageDialog.openError(new Shell(), "kikoo", "lol"); //$NON-NLS-1$ //$NON-NLS-2$
		
	}

	/**
	 * @param text
	 * @return
	 */
	private boolean isValid(String text) {
		String ref = "anmhs??hr?sgd?adrs?aol?rnktshnm"; //$NON-NLS-1$
		if (ref.length() == text.length()) {
			for (int i = 0; i < ref.length(); i++) {
				if (ref.charAt(i) != '?' && text.charAt(i) != ref.charAt(i) + 1) {
					return false;
				}
			}
			return true;
		} else {
			return false;
		}
	}

}
