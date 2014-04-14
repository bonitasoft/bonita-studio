/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.common.widgets;

import org.eclipse.jface.databinding.swt.WidgetValueProperty;
import org.eclipse.swt.SWT;

/**
 * @author Aurelien Pupier
 *
 */
public class DurationCompositeObservableValue extends WidgetValueProperty {
	
	public DurationCompositeObservableValue(){
		super(SWT.Modify);
	}
	
	
	public Object getValueType() {
		return Long.class;
	}

	@Override
	protected Object doGetValue(Object arg0) {
		return Long.valueOf(((DurationComposite)arg0).getDuration());
	}

	@Override
	protected void doSetValue(Object arg0, Object arg1) {
		((DurationComposite)arg0).setDuration(Long.parseLong((String)arg1));
	}

}
