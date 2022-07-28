/**
 * Copyright (C) 2018 Bonitasoft S.A.
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
package org.bonitasoft.studio.common.widgets;

import java.util.Objects;

import org.eclipse.swt.SWT;

public class GTKStyleHandler {

    private GTKStyleHandler() {
        //private constructor
    }

    public static int removeBorderFlag(int style) {
        return isGTK() ? style ^ SWT.BORDER : style;
    }

    public static boolean isGTK3() {
        String gtkVersion = System.getProperty("org.eclipse.swt.internal.gtk.version");
        return gtkVersion != null && gtkVersion.startsWith("3");
    }

    private static boolean isGTK() {
        return Objects.equals(System.getProperty("osgi.ws"), "gtk");
    }

    public static int replaceSingleWithWrap(int style) {
        if (isGTK3()) {
            //      style = style ^ SWT.SINGLE;
            //     style = style | SWT.WRAP;
            return style;
        }
        return style;
    }
}
