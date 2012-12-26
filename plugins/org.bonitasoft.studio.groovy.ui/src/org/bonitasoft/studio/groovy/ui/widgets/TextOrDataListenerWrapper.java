/**
 * Copyright (C) 2011 BonitaSoft S.A.
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

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

/**
 * @author Romain Bioteau
 *
 */
public class TextOrDataListenerWrapper implements SelectionListener {

		private final SelectionListener next;
		private final String item;
		private final TextAreaSuperCombo combo ;

		/**
		 * @param next
		 * @param item
		 */
		public TextOrDataListenerWrapper(SelectionListener next, String item,TextAreaSuperCombo combo) {
			this.next = next;
			this.item = item;
			this.combo = combo ;
		}
		public void widgetSelected(SelectionEvent e) {
			combo.setTextOnCombo(item);
			next.widgetSelected(e);
			combo.removeTextOnCombo(item);

		}
		public void widgetDefaultSelected(SelectionEvent e) {
		}
	}
