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
package org.bonitasoft.studio.tests.importer.bpmn2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.bonitasoft.bpm.model.process.MainProcess;
import org.bonitasoft.bpm.model.process.Pool;
import org.bonitasoft.studio.swtbot.framework.projectExplorer.ProjectExplorerBot;
import org.bonitasoft.studio.swtbot.framework.rule.SWTGefBotRule;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditor;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SWTBotJunit4ClassRunner.class)
public class ImportBPMMNFileIT {

    private final SWTGefBot bot = new SWTGefBot();

    @Rule
    public SWTGefBotRule botRule = new SWTGefBotRule(bot);

    @Test
    public void testImportBPMN2FromContextualMenu() throws InterruptedException, IOException {
        new ProjectExplorerBot(bot).importBPMNFile().selectBPMN2Importer()
                .setArchive(ImportBPMMNFileIT.class.getResource("standardProcess.bpmn"))
                .finish();

        final SWTBotEditor botEditor = bot.activeEditor();
        final SWTBotGefEditor gmfEditor = bot.gefEditor(botEditor.getTitle());

        final IGraphicalEditPart part = (IGraphicalEditPart) gmfEditor.mainEditPart().part();
        final MainProcess model = (MainProcess) part.resolveSemanticElement();
        final Pool pool = (Pool) model.getElements().get(0);
        assertNotNull("no pool found (import failed?)", pool);
        assertEquals("standardProcess (1.0)", botEditor.getTitle());
    }

}
