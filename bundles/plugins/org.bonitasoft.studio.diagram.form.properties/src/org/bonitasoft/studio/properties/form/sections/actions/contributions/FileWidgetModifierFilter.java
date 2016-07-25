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
package org.bonitasoft.studio.properties.form.sections.actions.contributions;

import org.bonitasoft.studio.common.emf.tools.WidgetModifiersSwitch;
import org.bonitasoft.studio.model.form.FileWidget;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

final class FileWidgetModifierFilter extends ViewerFilter {

    private final FileWidget widget;

    public FileWidgetModifierFilter(final FileWidget widget) {
        this.widget = widget;
    }

    @Override
    public boolean select(final Viewer arg0, final Object arg1, final Object arg2) {
        if (widget.isDuplicate()) {
            return !WidgetModifiersSwitch.ENGINE_DOCUMENT_QUALIFIED_NAME.equals(arg2);
        } else {
            return WidgetModifiersSwitch.ENGINE_DOCUMENT_QUALIFIED_NAME.equals(arg2);
        }
    }
}