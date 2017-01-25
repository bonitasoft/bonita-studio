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
package org.bonitasoft.studio.tests.document;

import static org.junit.Assert.assertNotNull;

import org.bonitasoft.engine.api.ProcessAPI;
import org.bonitasoft.engine.bpm.process.ProcessInstance;
import org.bonitasoft.engine.session.APISession;
import org.bonitasoft.studio.engine.BOSEngineManager;
import org.bonitasoft.studio.engine.command.RunProcessCommand;
import org.bonitasoft.studio.swtbot.framework.application.BotApplicationWorkbenchWindow;
import org.bonitasoft.studio.swtbot.framework.rule.SWTGefBotRule;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SWTBotJunit4ClassRunner.class)
public class TestRunWithDocument {

    private final SWTGefBot bot = new SWTGefBot();

    @Rule
    public SWTGefBotRule rule = new SWTGefBotRule(bot);

    @Test
    public void testRunWithDocument() throws Exception {
        new BotApplicationWorkbenchWindow(bot).importBOSArchive()
                .setArchive(
                        TestRunWithDocument.class.getResource("ProcessWithAttachment_1_0.bos"))
                .finish();
        runAndCheckResult();
    }

    protected void runAndCheckResult() throws Exception {
        new RunProcessCommand(true).execute(new ExecutionEvent());
        final APISession session = BOSEngineManager.getInstance().loginDefaultTenant(new NullProgressMonitor());
        final ProcessAPI processAPI = BOSEngineManager.getInstance().getProcessAPI(session);
        final long processDefinitionId = processAPI.getProcessDefinitionId("ProcessWithAttachment", "1.0");
        final ProcessInstance instance = processAPI.startProcess(processDefinitionId);

        assertNotNull("Document TestInternalDocument is missing",
                processAPI.getDocumentAtProcessInstantiation(instance.getId(), "TestInternalDocument"));
        assertNotNull("Document Test External Document is missing",
                processAPI.getDocumentAtProcessInstantiation(instance.getId(), "TestInternalDocument"));
        BOSEngineManager.getInstance().logoutDefaultTenant(session);
    }

    @Test
    public void testRunTwiceWithDocument() throws Exception {
        testRunWithDocument();
        runAndCheckResult();
    }
}
