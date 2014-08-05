/**
 * Copyright (C) 2014 BonitaSoft S.A.
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
package org.bonitasoft.studio.diagram.test;

import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.diagram.custom.Messages;
import org.bonitasoft.studio.test.swtbot.util.SWTBotTestUtil;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.eclipse.gef.finder.SWTBotGefTestCase;
import org.eclipse.swtbot.swt.finder.utils.TableCollection;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author aurelie zara
 */
public class TestDeleteDiagrams extends SWTBotGefTestCase {

    private final int nbDiagrams = 4;
    // Before and After
    private static boolean disablePopup;

    @BeforeClass
    public static void setUpBeforeClass() {
        disablePopup = FileActionDialog.getDisablePopup();
        FileActionDialog.setDisablePopup(true);
    }

    @AfterClass
    public static void tearDownAfterClass() {

        FileActionDialog.setDisablePopup(disablePopup);
    }

    @Override
    @After
    public void tearDown() {
        bot.saveAllEditors();
    }
    @Test
    public void testDeleteDiagrams() {
        for (int i = 0; i < nbDiagrams; i++) {
            SWTBotTestUtil.createNewDiagram(bot);
        }
        final String currentDiagramName = bot.activeEditor().getTitle();
        bot.waitUntil(Conditions.widgetIsEnabled(bot.menu("Diagram")), 10000);
        bot.menu("Diagram").menu("Delete...").click();
        bot.waitUntil(Conditions.shellIsActive(Messages.DeleteDiagramWizardPage_title), 10000);
        final SWTBotTree tree = bot.tree();
        assertEquals("the list of diagrams should contain 4 items", nbDiagrams, tree.getAllItems().length);
        final TableCollection selection = tree.selection();
        assertEquals("only " + currentDiagramName + " should be selected in the tree viewer", 1, selection.rowCount());
        assertEquals("diagram " + currentDiagramName + " should be selected", currentDiagramName, selection.get(0, 0));
        tree.select(tree.cell(1, 0), tree.cell(2, 0), tree.cell(3, 0));
        bot.button(Messages.removeProcessLabel).click();
    }

}
