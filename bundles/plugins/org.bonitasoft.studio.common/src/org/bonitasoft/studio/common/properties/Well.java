/**
 * Copyright (C) 2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.common.properties;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.forms.widgets.FormToolkit;

public class Well extends Composite {

    private static Color infoSeprator = new Color(Display.getDefault(), 51, 102, 153);
    private static Color infoBackground = new Color(Display.getDefault(), 229, 245, 255);

    private static Color warningSeprator = new Color(Display.getDefault(), 255, 204, 51);
    private static Color warningBackground = new Color(Display.getDefault(), 255, 255, 204);

    private static Color errorSeprator = new Color(Display.getDefault(), 204, 0, 0);
    private static Color errorBackground = new Color(Display.getDefault(), 255, 229, 229);

    private final Label label;

    public Well(final Composite parent, final String text, final FormToolkit toolkit, final int severity) {
        super(parent, SWT.NONE);
        setLayout(GridLayoutFactory.fillDefaults().extendedMargins(10, 10, 5, 8).create());

        addPaintListener(new PaintListener() {

            @Override
            public void paintControl(final PaintEvent e) {
                e.gc.setForeground(separatorColor(toolkit, severity));
                e.gc.setLineWidth(2);
                e.gc.drawLine(5, -5, 5, e.height + 10);

                e.gc.setBackground(backgroundColor(toolkit, severity));
                e.gc.fillRectangle(6, -5, e.width, e.height + 10);
            }

        });
        toolkit.adapt(this);
        label = toolkit.createLabel(this, text, SWT.WRAP);
        label.setBackground(backgroundColor(toolkit, severity));
        label.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).hint(parent.computeSize(SWT.DEFAULT, SWT.DEFAULT).x, SWT.DEFAULT)
                .create());
    }

    private Color separatorColor(final FormToolkit toolkit, final int style) {
        switch (style) {
            case IStatus.WARNING:
                return warningSeprator;
            case IStatus.ERROR:
                return errorSeprator;
            case IStatus.INFO:
            default:
                return infoSeprator;
        }
    }

    private Color backgroundColor(final FormToolkit toolkit, final int style) {
        switch (style) {
            case IStatus.WARNING:
                return warningBackground;
            case IStatus.ERROR:
                return errorBackground;
            case IStatus.INFO:
            default:
                return infoBackground;
        }
    }

    public void setText(final String text) {
        label.setText(text);
    }

}
