/**
 * Copyright (C) 2010-2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.tests.diagram;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.gmf.tools.GMFTools;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.model.process.Lane;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.swtbot.framework.SWTBotTestUtil;
import org.bonitasoft.studio.swtbot.framework.application.BotApplicationWorkbenchWindow;
import org.bonitasoft.studio.swtbot.framework.conditions.EditorOpenCondition;
import org.bonitasoft.studio.swtbot.framework.diagram.BotProcessDiagramPerspective;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditor;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SWTBotJunit4ClassRunner.class)
public class ExtractAsSubprocessTest {

    private final SWTGefBot bot = new SWTGefBot();

    public class OneMoreEditor extends DefaultCondition {

        private final SWTGefBot bot;
        private final int size;

        /**
         * @param bot
         * @param size
         */
        public OneMoreEditor(final SWTGefBot bot, final int size) {
            this.bot = bot;
            this.size = size;
        }

        /*
         * (non-Javadoc)
         * @see org.eclipse.swtbot.swt.finder.waits.ICondition#test()
         */
        @Override
        public boolean test() throws Exception {
            if (bot.activeShell().getText().toLowerCase().startsWith("overwrite")) {
                bot.button(IDialogConstants.OK_LABEL).click();
            }
            return bot.editors().size() > size;
        }

        /*
         * (non-Javadoc)
         * @see org.eclipse.swtbot.swt.finder.waits.ICondition#getFailureMessage()
         */
        @Override
        public String getFailureMessage() {
            return "No new editor opened";
        }

    }

    private final DiagramRepositoryStore store = RepositoryManager.getInstance()
            .getRepositoryStore(DiagramRepositoryStore.class);

    @Before
    public void setUp() throws Exception {
        bot.closeAllEditors();
    }

    @After
    public void tearDown() throws Exception {
        bot.saveAllEditors();
        bot.closeAllEditors();
    }

    @Test
    public void testExtractAsSubprocess() throws Exception {
        SWTBotTestUtil.createNewDiagram(bot);
        final SWTBotGefEditor editor1 = bot.gefEditor(bot.activeEditor().getTitle());
        final SWTBotGefEditPart stepPart = editor1.getEditPart("Step1").parent();
        final SWTBotGefEditPart startPart = editor1.getEditPart("Start1").parent();
        editor1.select(stepPart, startPart);
        final SWTBotGefEditPart poolPart = stepPart.parent(/* Compartment */).parent().parent().parent();
        editor1.clickContextMenu("Extract subprocess");
        editor1.select(poolPart);
        final Pool pool = (Pool) ((IGraphicalEditPart) poolPart.part()).resolveSemanticElement();
        assertEquals("Not same number of nodes in main as expected", 1, pool.getElements().size());
        assertEquals("Not same number of transitions in main as expected", 0, pool.getConnections().size());
        final Pool subprocessPool = (Pool) ((MainProcess) pool.eContainer()).getElements().get(1);
        assertEquals("Not same number of nodes as expected", 2, subprocessPool.getElements().size());
        assertEquals("Not same number of transitions as expected", 1, subprocessPool.getConnections().size());
    }

    @Test
    public void testExtractAsSubprocessFromLane() throws Exception {
        // Test Bug 3100
        SWTBotTestUtil.createNewDiagram(bot);
        final SWTBotGefEditor editor1 = bot.gefEditor(bot.activeEditor().getTitle());
        editor1.activateTool("Lane");
        editor1.click(100, 100);
        final SWTBotGefEditPart stepPart = editor1.getEditPart("Step1").parent();
        final SWTBotGefEditPart startPart = editor1.getEditPart("Start1").parent();
        editor1.select(stepPart, startPart);
        final SWTBotGefEditPart lanePart = stepPart.parent(/* Compartment */).parent();
        editor1.clickContextMenu("Extract subprocess");
        editor1.select(lanePart);
        final Lane lane = (Lane) ((IGraphicalEditPart) lanePart.part()).resolveSemanticElement();
        final Pool pool = (Pool) lane.eContainer();
        assertEquals("Not same number of nodes in main as expected", 1, lane.getElements().size());
        assertEquals("Not same number of transitions in main as expected", 0, pool.getConnections().size());
        final Pool subprocessPool = (Pool) ((MainProcess) pool.eContainer()).getElements().get(1);
        assertEquals("Not same number of nodes as expected", 2, subprocessPool.getElements().size());
        assertEquals("Not same number of transitions as expected", 1, subprocessPool.getConnections().size());
    }

    @Test
    public void testAddMissingConnectionsAndBoundaries() throws Exception {
        importProcess();

        final SWTBotGefEditor editor1 = bot.gefEditor(bot.activeEditor().getTitle());
        final IGraphicalEditPart step1Part = (IGraphicalEditPart) editor1.getEditPart("Step1").parent().part();
        final IGraphicalEditPart step2Part = (IGraphicalEditPart) editor1.getEditPart("Step2").parent().part();
        final List<IGraphicalEditPart> list = new ArrayList<>();
        list.add(step2Part);
        list.add(step1Part);
        assertEquals("Util method does not work", 5, GMFTools.addMissingConnectionsAndBoundaries(list).size());
    }

    @Test
    public void testExtractSubprocessWithBoundary() throws Exception {
        // Test bug 3102
        importProcess();

        final String title = bot.activeEditor().getTitle();
        final SWTBotGefEditor editor1 = bot.gefEditor(title);
        final SWTBotGefEditPart step1Part = editor1.getEditPart("Step1").parent();
        final SWTBotGefEditPart step2Part = editor1.getEditPart("Step2").parent();
        final SWTBotGefEditPart error1Part = editor1.getEditPart("Error1").parent();
        editor1.select(step1Part, step2Part, error1Part);
        final SWTBotGefEditPart poolPart = step1Part.parent().parent();
        bot.sleep(100);
        editor1.clickContextMenu("Extract subprocess");

        final Lane lane = (Lane) ((IGraphicalEditPart) poolPart.part()).resolveSemanticElement();
        //use a waitUntil in order to wait UI operation to finish
        bot.waitUntil(new DefaultCondition() {

            @Override
            public boolean test() throws Exception {
                return 2 == lane.getElements().size();
            }

            @Override
            public String getFailureMessage() {
                return "Not same number of nodes in main as expected";
            }
        });
        assertEquals("Not same number of transitions in main as expected", 1,
                ((Pool) lane.eContainer()).getConnections().size());
        final Pool subprocessPool = (Pool) ModelHelper.getMainProcess(lane).getElements().get(1);
        assertEquals("Not same number of nodes as expected", 2, subprocessPool.getElements().size());
        assertEquals("Not same number of transitions as expected", 2, subprocessPool.getConnections().size());
    }

    @Test
    public void testExtractSubprocessWithBoundary2() throws Exception {
        // Test bug 3102, selecting only the 2 steps, and not the boundary
        importProcess();

        final SWTBotGefEditor editor1 = bot.gefEditor(bot.activeEditor().getTitle());
        final SWTBotGefEditPart step1Part = editor1.getEditPart("Step1").parent();
        final SWTBotGefEditPart step2Part = editor1.getEditPart("Step2").parent();
        editor1.select(step1Part, step2Part);
        final SWTBotGefEditPart poolPart = step1Part.parent().parent();
        bot.sleep(100);
        editor1.clickContextMenu("Extract subprocess");

        final Lane lane = (Lane) ((IGraphicalEditPart) poolPart.part()).resolveSemanticElement();

        //use a waitUntil in order to wait UI operation to finish
        bot.waitUntil(new DefaultCondition() {

            @Override
            public boolean test() throws Exception {
                return 2 == lane.getElements().size();
            }

            @Override
            public String getFailureMessage() {
                return "Not same number of nodes in main as expected";
            }
        });
        assertEquals("Not same number of transitions in main as expected", 1,
                ((Pool) lane.eContainer()).getConnections().size());
        final Pool subprocessPool = (Pool) ModelHelper.getMainProcess(lane).getElements().get(1);
        assertEquals("Not same number of nodes as expected", 2, subprocessPool.getElements().size());
        assertEquals("Not same number of transitions as expected", 2, subprocessPool.getConnections().size());
    }

    private void importProcess() throws IOException {
        new BotApplicationWorkbenchWindow(bot).importBOSArchive()
                .setArchive(ExtractAsSubprocessTest.class.getResource("BoundaryProcess_1_0.bos"))
                .currentRepository()
                .next()
                .next()
                .finish();
        
        final BotApplicationWorkbenchWindow workbenchBot = new BotApplicationWorkbenchWindow(bot);
        workbenchBot.open().selectDiagram("BoundaryProcess", "1.0").open();

        bot.waitUntil(new EditorOpenCondition(store.getChild("BoundaryProcess-1.0.proc", true).getResource()));
        SWTBotTestUtil.waitUntilRootShellIsActive(bot);
    }

}
