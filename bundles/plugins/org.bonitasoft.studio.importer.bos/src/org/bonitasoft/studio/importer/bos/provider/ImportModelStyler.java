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
package org.bonitasoft.studio.importer.bos.provider;

import org.bonitasoft.studio.ui.ColorConstants;
import org.eclipse.jface.viewers.StyledString.Styler;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.TextStyle;
import org.eclipse.swt.widgets.Display;

public class ImportModelStyler {

    public static class ConflictStyler extends Styler {

        private final Color fForegroundColor;

        public ConflictStyler() {
            fForegroundColor = new Color(Display.getCurrent(), ColorConstants.ERROR_RGB);
        }

        @Override
        public void applyStyles(TextStyle textStyle) {
            textStyle.foreground = fForegroundColor;
        }

        public void dispose() {
            fForegroundColor.dispose();
        }
    }

    public static class SameContentStyler extends Styler {

        private final Color fForegroundColor;

        public SameContentStyler() {
            fForegroundColor = Display.getDefault().getSystemColor(SWT.COLOR_DARK_GRAY);
        }

        @Override
        public void applyStyles(TextStyle textStyle) {
            textStyle.foreground = fForegroundColor;
        }

    }
    
    public static class NotImportedStyler extends Styler {

        private final Color fForegroundColor;

        public NotImportedStyler() {
            fForegroundColor = Display.getDefault().getSystemColor(SWT.COLOR_DARK_GRAY);
        }

        @Override
        public void applyStyles(TextStyle textStyle) {
            textStyle.foreground = fForegroundColor;
            textStyle.strikeout = true;
        }

    }

    public ConflictStyler createConflictStyler() {
        return new ConflictStyler();
    }

    public Styler createSameContentStyler() {
        return new SameContentStyler();
    }
    
    public Styler createNotImportedStyler() {
        return new NotImportedStyler();
    }
}
