/**
 * Copyright (C) 2009 BonitaSoft S.A.
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
package org.bonitasoft.studio.tests.stress;

import org.bonitasoft.studio.application.actions.OpenConsoleCommand;

import junit.framework.TestCase;

/**
 * @author Mickael Istria
 */
public class TestRunStress extends TestCase {

    public void testStress() throws Exception {
        // starts console 3 times to ensure everything is loaded
        new OpenConsoleCommand(true).execute(null);
        new OpenConsoleCommand(true).execute(null);
        new OpenConsoleCommand(true).execute(null);

        long before = Runtime.getRuntime().totalMemory();
        long after = Runtime.getRuntime().totalMemory();
        long delta = 0;
        for (int i = 0; i < 20; i++) {
            new OpenConsoleCommand(true).execute(null);
            long timestamp = System.currentTimeMillis();
            do {
                after = Runtime.getRuntime().totalMemory();
                delta = after - before;
                assertTrue("Too much memory was used at execution " + i + "! : " + delta, delta < 10000000);
            } while (System.currentTimeMillis() - timestamp < 10000); // run a new console every 4 seconds
        }
        assertTrue("Too much memory was used! : " + delta, delta < 10000000);
    }
}
