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
package org.bonitasoft.studio.tests.bug.bug4440;

import org.eclipse.swtbot.swt.finder.SWTBotTestCase;
import org.junit.Test;

/**
 * @author Mickael Istria
 */
public class TestBug4440 extends SWTBotTestCase {

    @Test
    public void testBug() throws Exception {
        /*
         * in same diagram, pool1 has task1 that send a message to a task2 in pool2.
         * all version of these are 1.2.
         * export both processes.
         * in the studio change all versions diagram, pool1 pool2.
         * import .bar generated with older version.
         * click on taks1, general, Message, select the message, press Edit...
         * The target process is blank.
         */
    }
}
