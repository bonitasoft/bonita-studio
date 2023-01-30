/**
 * Copyright (C) 2020 BonitaSoft S.A.
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
package org.bonitasoft.studio.expression.editor.widget;

import org.eclipse.jface.fieldassist.IControlContentAdapter;
import org.eclipse.jface.fieldassist.IControlContentAdapter2;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;

public class LabelContentAdapter implements IControlContentAdapter, IControlContentAdapter2 {

    @Override
    public String getControlContents(Control control) {
        return ((Label) control).getText();
    }

    @Override
    public void setControlContents(Control control, String text,
            int cursorPosition) {
        ((Label) control).setText(text);
    }

    @Override
    public void insertControlContents(Control control, String text,
            int cursorPosition) {
        ((Label) control).setText(text);
    }

    @Override
    public int getCursorPosition(Control control) {
        return 0;
    }

    @Override
    public Rectangle getInsertionBounds(Control control) {
        Label text = (Label) control;
        Point caretOrigin = new Point(0, 0);
        // We fudge the y pixels due to problems with getCaretLocation
        // See https://bugs.eclipse.org/bugs/show_bug.cgi?id=52520
        return new Rectangle(caretOrigin.x + text.getBounds().x,
                caretOrigin.y + text.getBounds().y + 3, 1, text.getBounds().height);
    }

    @Override
    public void setCursorPosition(Control control, int position) {
       
    }

    /**
     * @see org.eclipse.jface.fieldassist.IControlContentAdapter2#getSelection(org.eclipse.swt.widgets.Control)
     * @since 3.4
     */
    @Override
    public Point getSelection(Control control) {
        return new Point(0, 0);
    }

    /**
     * @see org.eclipse.jface.fieldassist.IControlContentAdapter2#setSelection(org.eclipse.swt.widgets.Control,
     *      org.eclipse.swt.graphics.Point)
     * @since 3.4
     */
    @Override
    public void setSelection(Control control, Point range) {
    }

}
