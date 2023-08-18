/**
 * Copyright (C) 2014 Bonitasoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.bonitasoft.bpm.model.process.MainProcess;
import org.bonitasoft.bpm.model.process.Pool;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.swtbot.framework.SWTBotTestUtil;
import org.bonitasoft.studio.swtbot.framework.application.BotApplicationWorkbenchWindow;
import org.bonitasoft.studio.swtbot.framework.projectExplorer.ProjectExplorerBot;
import org.bonitasoft.studio.swtbot.framework.rule.SWTGefBotRule;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SWTBotJunit4ClassRunner.class)
public class TestRenameDiagram {

    private final SWTGefBot bot = new SWTGefBot();

    @Rule
    public SWTGefBotRule botRule = new SWTGefBotRule(bot);

    @Test
    public void testRenameMenu() {
        var applicationWorkbenchWindow = new BotApplicationWorkbenchWindow(bot);
        var gmfEditor = applicationWorkbenchWindow.createNewDiagram().activeProcessDiagramEditor().getGmfEditor();
        final MainProcess diagram = (MainProcess) ((IGraphicalEditPart) gmfEditor.mainEditPart().part())
                .resolveSemanticElement();
        final String originalName = diagram.getName();
        final String newName = originalName + " renamed" + System.currentTimeMillis();
        new ProjectExplorerBot(bot).diagram().renameDiagram(originalName, newName, "1.0", null);

        assertEquals(newName + " (1.0)", bot.activeEditor().getTitle());
        assertFalse("Editor is dirty", bot.activeEditor().isDirty());
    }

    @Test
    public void testRenameDiagramOnce() throws Exception {
        var activeProcessDiagramEditor = new BotApplicationWorkbenchWindow(bot)
                .createNewDiagram()
                .activeProcessDiagramEditor();
        
        var pool = activeProcessDiagramEditor.getSelectedSemanticElement();
        assertThat(pool).isInstanceOf(Pool.class);
        assertThat(ModelHelper.getMainProcess(pool).getName()).isNotEqualTo("NewDiagramName");
        
        SWTBotTestUtil.changeDiagramName(bot, "NewDiagramName");
        activeProcessDiagramEditor.getGmfEditor().getSWTBotGefViewer().mainEditPart().select();
        var diagram = activeProcessDiagramEditor.getSelectedSemanticElement();
        assertThat(((MainProcess) diagram).getName()).isEqualTo("NewDiagramName");
    }

}
