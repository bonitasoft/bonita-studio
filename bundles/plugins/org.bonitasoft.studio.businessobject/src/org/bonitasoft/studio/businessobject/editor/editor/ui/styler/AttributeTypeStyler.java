/**
 * Copyright (C) 2019 Bonitasoft S.A.
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
package org.bonitasoft.studio.businessobject.editor.editor.ui.styler;

import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.StyledString.Styler;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.TextStyle;
import org.eclipse.swt.widgets.Display;

public class AttributeTypeStyler extends Styler {

    Color color;

    public AttributeTypeStyler(RGB colorRGB) {
        color = new Color(Display.getDefault(), colorRGB);
    }

    public AttributeTypeStyler(Color color) {
        this.color = color;
    }

    @Override
    public void applyStyles(TextStyle textStyle) {
        textStyle.foreground = color;
        textStyle.font = JFaceResources.getFontRegistry().getItalic(JFaceResources.DEFAULT_FONT);
    }

    public void dispose() {
        color.dispose();
    }
}
