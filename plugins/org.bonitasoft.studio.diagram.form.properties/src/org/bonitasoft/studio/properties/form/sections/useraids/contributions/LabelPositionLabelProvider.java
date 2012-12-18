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
package org.bonitasoft.studio.properties.form.sections.useraids.contributions;

import org.bonitasoft.studio.form.properties.i18n.Messages;
import org.bonitasoft.studio.model.form.LabelPosition;
import org.eclipse.jface.viewers.LabelProvider;

/**
 * @author Mickael Istria
 *
 */
public class LabelPositionLabelProvider extends LabelProvider {

	@Override
	public String getText(Object item) {
		if (item == LabelPosition.UP) {
			return Messages.aboveWidget;
		} else if (item == LabelPosition.LEFT) {
			return Messages.leftToWidget;
		} else if (item == LabelPosition.RIGHT) {
			return Messages.rightToWidget;
		} else if (item == LabelPosition.DOWN) {
			return Messages.belowWidget;
		}
		return super.getText(item);
	}
}
