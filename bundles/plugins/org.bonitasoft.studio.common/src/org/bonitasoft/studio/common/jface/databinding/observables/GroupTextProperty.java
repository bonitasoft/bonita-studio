/**
 * Copyright (C) 2016 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.common.jface.databinding.observables;

import org.eclipse.jface.databinding.swt.WidgetValueProperty;
import org.eclipse.swt.widgets.Group;

public class GroupTextProperty extends WidgetValueProperty {

    /*
     * (non-Javadoc)
     * @see org.eclipse.core.databinding.property.value.IValueProperty#getValueType()
     */
    @Override
    public Object getValueType() {
        return String.class;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.core.databinding.property.value.SimpleValueProperty#doGetValue(java.lang.Object)
     */
    @Override
    protected Object doGetValue(Object source) {
        return ((Group) source).getText();
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.core.databinding.property.value.SimpleValueProperty#doSetValue(java.lang.Object, java.lang.Object)
     */
    @Override
    protected void doSetValue(Object source, Object value) {
        ((Group) source).setText((String) (value == null ? "" : value)); //$NON-NLS-1$
    }

    @Override
    public String toString() {
        return "Group.text <String>"; //$NON-NLS-1$
    }

}
