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
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.common.jface;

import org.eclipse.jface.resource.FontRegistry;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Display;

/**
 * @author Baptiste Mesta
 * 
 */
public class BonitaStudioFontRegistry {

    private static FontRegistry fontRegistry;

    public static Font getCommentsFont() {
        return getFont("bonita_font_comments", 8, SWT.NORMAL);
    }

    public static Font getNormalFont() {
        return Display.getDefault().getSystemFont();
    }

    public static Font getHighlightedFont() {
        final FontData[] data = Display.getDefault().getSystemFont().getFontData();
        int height = 8;
        if (data.length > 0 && data[0].height > 0) {
            height = (int) data[0].height;
        }
        return getFont("bonita_highlight_font", height, SWT.NORMAL);
    }

    public static Font getTransientDataFont() {
        return getFont("bonita_font_transientdata", 10, SWT.ITALIC);
    }

    public static Font getDateDataFont() {
        return getFont("bonita_font_date", 10, SWT.BOLD);
    }

    public static Font getPreferenceTitleFont() {
        return getFont("bonita_preference_tilte", 12, SWT.BOLD);
    }

    public static Font getActiveFont() {
        return getFont("bonita_active_font", 10, SWT.BOLD);
    }

    public static Font getMonospaceFont() {
        Font font = JFaceResources.getFontRegistry().get("monospace_font");
        if (font == null || font.equals(JFaceResources.getFontRegistry().get(JFaceResources.DEFAULT_FONT))) {
            font = new Font(Display.getDefault(), "Monospaced", 10, SWT.NONE);
            JFaceResources.getFontRegistry().put("monospace_font", font.getFontData());
        }
        return font;
    }

    /**
     * @param fontID
     * @param style
     * @param size
     * @return
     *         the font with style and size + cache it into font registry
     * 
     */
    private static Font getFont(final String fontID, final int size, final int style) {
        if (fontRegistry == null) {
            fontRegistry = JFaceResources.getFontRegistry();
        }
        if (fontRegistry.hasValueFor(fontID)) {
            return fontRegistry.get(fontID);
        } else {
            final FontData fd = new FontData(fontID, size, style);
            fontRegistry.put(fontID, new FontData[] { fd });
            return fontRegistry.get(fontID);
        }
    }

    public static Font getItalicFont() {
        return getFont("italic_font", 9, SWT.ITALIC);
    }

    public static Font getBoldFont() {
        return getFont("bold_font", 9, SWT.BOLD);
    }

}
