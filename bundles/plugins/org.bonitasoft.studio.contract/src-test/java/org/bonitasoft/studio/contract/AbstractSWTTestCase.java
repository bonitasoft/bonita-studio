/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.contract;

import org.bonitasoft.studio.common.jface.databinding.DefaultRealm;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;


/**
 * @author Romain Bioteau
 *
 */
public abstract class AbstractSWTTestCase {

    protected Display display;
    private Shell headlessShell;
    private DefaultRealm defaultRealm;

    protected Composite createDisplayAndRealm() {
        display = new Display();
        headlessShell = new Shell(display);
        headlessShell.setLayout(new GridLayout(1, true));
        final GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
        headlessShell.setLayoutData(gridData);
        final Composite parent = new Composite(headlessShell, SWT.NONE);
        defaultRealm = new DefaultRealm(display);
        return parent;
    }

    protected Image createImage() {
        final Color red = new Color(display, new RGB(255, 0, 0));
        final Image image = new Image(display, 100, 100);
        final GC gc = new GC(image);
        gc.setBackground(red);
        gc.fillRectangle(image.getBounds());
        gc.dispose();
        return image;
    }

    protected Color createColor() {
        return new Color(display, new RGB(255, 0, 0));
    }

    protected void dispose() {
        if (defaultRealm != null) {
            defaultRealm.dispose();
        }
        if (headlessShell != null) {
            headlessShell.dispose();
        }
        if (display != null) {
            display.dispose();
        }
    }

}
