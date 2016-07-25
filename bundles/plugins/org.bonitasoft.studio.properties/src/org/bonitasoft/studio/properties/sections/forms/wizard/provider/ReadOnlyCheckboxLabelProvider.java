/**
 * Copyright (C) 2012-2013 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.properties.sections.forms.wizard.provider;

import org.bonitasoft.studio.common.jface.AbstractCheckboxLabelProvider;
import org.bonitasoft.studio.diagram.form.custom.model.WidgetMapping;
import org.bonitasoft.studio.model.form.FileWidget;
import org.bonitasoft.studio.model.form.FormField;
import org.bonitasoft.studio.model.form.Group;
import org.bonitasoft.studio.model.form.Widget;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.swt.graphics.Image;

public class ReadOnlyCheckboxLabelProvider extends AbstractCheckboxLabelProvider {

    public ReadOnlyCheckboxLabelProvider(final ColumnViewer viewer) {
        super(viewer);
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.jface.AbstractCheckboxLabelProvider#isSelected(java.lang.Object)
     */
    @Override
    protected boolean isSelected(final Object element) {
        if (element instanceof WidgetMapping) {
            return ((WidgetMapping) element).isReadOnly();
        }
        return false;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.jface.AbstractCheckboxLabelProvider#isEnabled(java.lang.Object)
     */
    @Override
    protected boolean isEnabled(final Object element) {
        final Widget widget = ((WidgetMapping) element).getWidgetType();
        return (widget instanceof FormField || widget instanceof Group) && !(widget instanceof FileWidget);
    }

    @Override
    public Image getImage(final Object element) {
        final Widget widget = ((WidgetMapping) element).getWidgetType();
        if (widget == null) {
            return null;
        }
        return super.getImage(element);
    }

}
