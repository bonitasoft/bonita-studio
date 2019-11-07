/**
 * Copyright (C) 2010 BonitaSoft S.A.
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
package org.bonitasoft.studio.tests;

import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import junit.framework.TestCase;

/**
 * @author Baptiste Mesta
 */
public class CloseAllEditors extends TestCase {

    public void testNoDirtyEditors() throws Exception {
        //fail("editors were not closed: "+comment);
    }

    /*
     * (non-Javadoc)
     * @see junit.framework.TestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        for (IWorkbenchWindow window : PlatformUI.getWorkbench().getWorkbenchWindows()) {
            for (IWorkbenchPage page : window.getPages()) {
                page.saveAllEditors(false);

            }
        }
    }

}
