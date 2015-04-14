/**
 * Copyright (C) 2015 BonitaSoft S.A.
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
package org.bonitasoft.studio.common.diagram.tools;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;

/**
 * @author Romain Bioteau
 */
public class ColorRegistry {

    static final Color COMPARTMENT_FEEDBACK_COLOR = new Color(PlatformUI.getWorkbench().getDisplay(), 240, 245, 245);
    static final Color ACTIVITY_BLUE = new Color(Display.getCurrent(), 54, 107, 163);
    static final Color GATEWAY_DARK_GREEN = new Color(Display.getCurrent(), 151, 170, 36);
    static final Color STARTEVENT_DARK_GREEN = new Color(Display.getCurrent(), 106, 172, 37);
    static final Color ENDEVENT_DARK_RED = new Color(Display.getCurrent(), 131, 20, 29);
}
