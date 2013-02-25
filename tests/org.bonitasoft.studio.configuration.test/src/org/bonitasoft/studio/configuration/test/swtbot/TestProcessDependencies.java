/**
 * Copyright (C) 2013 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.configuration.test.swtbot;

import java.io.IOException;

import org.bonitasoft.studio.test.swtbot.util.SWTBotTestUtil;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.swtbot.eclipse.gef.finder.SWTBotGefTestCase;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.junit.Test;
import org.junit.runner.RunWith;


/**
 * @author Florine Boudin
 *
 */
@RunWith(SWTBotJunit4ClassRunner.class)
public class TestProcessDependencies extends SWTBotGefTestCase {

	
    @Test
    public void testImportAndRunProcessWithLotOfDependencies() throws IOException, ExecutionException{
    	
    	SWTBotTestUtil.importProcessWIthPathFromClass(bot, "DiagramWithLotOfDependencies-1.0.bos", "BOS Archive", "MyDiagram1", getClass(), false);

		IStatus status = SWTBotTestUtil.selectAndRunFirstPoolFound(bot);
		assertTrue(status.getMessage(), status.isOK());

    }

}
