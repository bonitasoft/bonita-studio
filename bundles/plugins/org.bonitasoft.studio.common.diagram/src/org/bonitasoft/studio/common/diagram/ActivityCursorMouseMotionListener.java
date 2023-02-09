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
package org.bonitasoft.studio.common.diagram;

import org.bonitasoft.studio.pics.Pics;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseMotionListener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;


public class ActivityCursorMouseMotionListener implements MouseMotionListener {

    @Override
    public void mouseMoved(MouseEvent me) {
    }

    @Override
    public void mouseHover(MouseEvent me) {

    }

    @Override
    public void mouseExited(MouseEvent me) {
        final Figure source = (Figure) me.getSource();
        source.setCursor(Display.getDefault().getSystemCursor(SWT.CURSOR_ARROW));
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        final Figure source = (Figure) me.getSource();
        source.setCursor(Pics.getOpenedHandCursor());
    }

    @Override
    public void mouseDragged(MouseEvent me) {
    }
}
