/**
 * Copyright (C) 2011 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.tests.document;

import org.bonitasoft.engine.api.ProcessAPI;
import org.bonitasoft.engine.bpm.process.ProcessInstance;
import org.bonitasoft.engine.session.APISession;
import org.bonitasoft.studio.engine.BOSEngineManager;
import org.bonitasoft.studio.engine.command.RunProcessCommand;
import org.bonitasoft.studio.test.swtbot.util.SWTBotTestUtil;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.swtbot.eclipse.gef.finder.SWTBotGefTestCase;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Romain Bioteau
 *
 */
@RunWith(SWTBotJunit4ClassRunner.class)
public class TestRunWithDocument extends SWTBotGefTestCase {

    @Test
    public void testRunWithDocument() throws Exception {
        SWTBotTestUtil.importProcessWIthPathFromClass(bot, "ProcessWithAttachment_1_0.bos", "Bonita 6.x and 7.x", "ProcessWithAttachment", TestRunWithDocument.class, false);
        runAndCheckResult();
    }

    protected void runAndCheckResult() throws Exception {
        new RunProcessCommand(true).execute(new ExecutionEvent());
        APISession session = BOSEngineManager.getInstance().loginDefaultTenant(new NullProgressMonitor());
        ProcessAPI processAPI = BOSEngineManager.getInstance().getProcessAPI(session) ;
        long processDefinitionId = processAPI.getProcessDefinitionId("ProcessWithAttachment", "1.0");
        final ProcessInstance instance = processAPI.startProcess(processDefinitionId);

        assertNotNull("Document TestInternalDocument is missing",processAPI.getDocumentAtProcessInstantiation(instance.getId(), "TestInternalDocument"));
        assertNotNull("Document Test External Document is missing",processAPI.getDocumentAtProcessInstantiation(instance.getId(), "TestInternalDocument"));
        BOSEngineManager.getInstance().logoutDefaultTenant(session);
    }

    @Test
    public void testRunTwiceWithDocument() throws Exception {
        testRunWithDocument();
        runAndCheckResult();
    }
}
