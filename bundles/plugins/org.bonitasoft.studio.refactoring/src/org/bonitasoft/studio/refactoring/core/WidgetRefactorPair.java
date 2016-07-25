/**
 * Copyright (C) 2014 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.refactoring.core;

import org.bonitasoft.studio.common.emf.tools.WidgetHelper;
import org.bonitasoft.studio.model.form.Widget;

public class WidgetRefactorPair extends RefactorPair<Widget, Widget> {

	public WidgetRefactorPair(Widget newValue, Widget oldValue) {
		super(newValue, oldValue);
	}
	
	@Override
	public String getOldValueName() {
		return WidgetHelper.FIELD_PREFIX+getOldValue().getName();
	}
	
	@Override
	public String getNewValueName() {
		final Widget newValue = getNewValue();
		if(newValue != null){
			return WidgetHelper.FIELD_PREFIX+newValue.getName();
		} else {
			return super.getNewValueName();
		}
	}

}
