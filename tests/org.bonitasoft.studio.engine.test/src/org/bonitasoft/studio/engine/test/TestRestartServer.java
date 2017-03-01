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
package org.bonitasoft.studio.engine.test;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.engine.BOSEngineManager;
import org.bonitasoft.studio.engine.BOSWebServerManager;
import org.eclipse.core.runtime.NullProgressMonitor;

import junit.framework.TestCase;

/**
 * @author Romain Bioteau
 */
public class TestRestartServer extends TestCase {

    private static final int NB_CONSOLE_RESTART = 10;

    public void testRestartServerMany() throws Exception {
        BOSEngineManager.getInstance().start();
        BonitaStudioLog.log("Restart Server Test :");
        //Detect OOM errors & unlocked thread and CLassloaders
        for (int i = 0; i < NB_CONSOLE_RESTART; i++) {

            BOSWebServerManager.getInstance().resetServer(new NullProgressMonitor());
            BonitaStudioLog.log(" Server restarted " + i + " times");
        }
    }
}
