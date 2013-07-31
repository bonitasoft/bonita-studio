/**
 * Copyright (C) 2010-2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.application.test;

import junit.framework.TestCase;

import org.eclipse.core.runtime.Platform;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

/**
 * @author Romain Bioteau
 */
public class TestMenus extends TestCase {

    public void testNbMenus() {
        Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
        String menus = "";
        int nbRunMenus = 0;
        for (MenuItem item : shell.getMenuBar().getItems()) {
            menus += "\n"+item.getText();
            if (item.getText().toLowerCase().trim().equals("run")) {
                nbRunMenus++;
            }
        }
        assertEquals("Run menu should not appears", 0, nbRunMenus);
        if(Platform.getProduct().getId().equals("org.bonitasoft.studioEx.product")){
            assertEquals("Menu bar polluted by third-party menus.\n available menu:"+menus,12, shell.getMenuBar().getItemCount());
        } else if(Platform.getProduct().getId().equals("org.bonitasoft.studio.product")){
            assertEquals("Menu bar polluted by third-party menus.\n available menu:"+menus, 8, shell.getMenuBar().getItemCount());
        }
    }


}
