/**
 * Copyright (C) 2011 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.decision.ui;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.preferences.PreferenceUtil;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;

/**
 * @author Mickael Istria
 */
public class DecisionTableGridPaintListener implements PaintListener {

    private static final Color[] COLORS;
    private static Color SELECTION_COLOR;

    static {
        LocalResourceManager manager = new LocalResourceManager(JFaceResources.getResources());
        SELECTION_COLOR = Display.getDefault().getSystemColor(SWT.COLOR_LIST_SELECTION);
        if (PreferenceUtil.isDarkTheme()) {
            COLORS = new Color[] {
                    manager.createColor(new RGB(30, 30, 30)),
                    manager.createColor(new RGB(43, 43, 43))
            };

            int red = (int) (SELECTION_COLOR.getRed() * 0.5);
            int green = (int) (SELECTION_COLOR.getGreen() * 0.5);
            int blue = (int) (SELECTION_COLOR.getBlue() * 0.5);
            SELECTION_COLOR = manager.createColor(new RGB(red, green, blue));
        } else {
            COLORS = new Color[] {
                    Display.getCurrent().getSystemColor(SWT.COLOR_WHITE),
                    manager.createColor(new RGB(240, 240, 240))
            };
        }
    }

    private int selectedRowIndex = -1;

    public DecisionTableGridPaintListener() {
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.swt.events.PaintListener#paintControl(org.eclipse.swt.events.PaintEvent)
     */
    @Override
    public void paintControl(final PaintEvent event) {
        Composite composite = (Composite) event.widget;
        Point size = composite.getSize();
        List<List<Control>> controls = getChildrenByRow(composite);
        int i = 0;

        for (List<Control> line : controls) {
            if (selectedRowIndex != -1 && controls.indexOf(line) == selectedRowIndex + 1) {
                event.gc.setBackground(SELECTION_COLOR);
            } else {
                event.gc.setBackground(COLORS[i]);
            }

            for (Control control : line) {
                if (controls.indexOf(line) == 0) {
                    event.gc.fillRectangle(new Rectangle(0, 0, size.x, size.y));
                } else {
                    event.gc.fillRectangle(new Rectangle(0, control.getLocation().y - 3, size.x, size.y - 3));
                }
            }
            i = (i + 1) % 2;
        }
    }

    public Color getColorForControl(Composite parent, Control control) {
        List<List<Control>> controls = getChildrenByRow(parent);
        int i = 0;
        for (List<Control> line : controls) {
            if (selectedRowIndex != -1 && controls.indexOf(line) == selectedRowIndex + 1 && line.contains(control)) {
                return Display.getDefault().getSystemColor(SWT.COLOR_LIST_SELECTION);
            }
            if (line.contains(control)) {
                return COLORS[i % 2];
            }
            i++;
        }
        return null;
    }

    public void setSelectedRow(int selIndex) {
        this.selectedRowIndex = selIndex;
    }

    public List<List<Control>> getChildrenByRow(Composite composite) {
        GridLayout layout = (GridLayout) composite.getLayout();
        int nbColumns = layout.numColumns;
        Control[] children = composite.getChildren();
        ArrayList<List<Control>> res = new ArrayList<>();
        int currentCol = 0;
        List<Control> currentLine = new ArrayList<>();
        res.add(currentLine);
        for (Control child : children) {
            if (currentCol == nbColumns) {
                currentLine = new ArrayList<>();
                res.add(currentLine);
                currentCol = 0;
            }
            currentLine.add(child);
            if (child.getLayoutData() != null) {
                GridData gridData = (GridData) child.getLayoutData();
                currentCol += gridData.horizontalSpan;
            } else {
                currentCol++;
            }
        }
        return res;
    }

}
