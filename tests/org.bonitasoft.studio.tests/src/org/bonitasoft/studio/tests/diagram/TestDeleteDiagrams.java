/**
 * Copyright (C) 2014 BonitaSoft S.A.
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

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.diagram.custom.i18n.Messages;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.swtbot.framework.SWTBotTestUtil;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.utils.TableCollection;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotMenu;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author aurelie zara
 */
@RunWith(SWTBotJunit4ClassRunner.class)
public class TestDeleteDiagrams {

    private final int nbDiagrams = 4;
    // Before and After
    private static boolean disablePopup;

    private final SWTGefBot bot = new SWTGefBot();

    @BeforeClass
    public static void setUpBeforeClass() {
        disablePopup = FileActionDialog.getDisablePopup();
        FileActionDialog.setDisablePopup(true);
    }

    @AfterClass
    public static void tearDownAfterClass() {
        FileActionDialog.setDisablePopup(disablePopup);
    }

    @After
    public void tearDown() {
        bot.saveAllEditors();
    }

    @Test
    public void testDeleteDiagrams() {

        final DiagramRepositoryStore diagramStore = RepositoryManager.getInstance()
                .getRepositoryStore(DiagramRepositoryStore.class);

        final int nbDiagramsInRepository = diagramStore.getChildren().size();

        final SWTBotMenu diagramMenu = bot.menu("File");
        final List<String> newDiagramsName = new ArrayList<>();
        for (int i = 0; i < nbDiagrams; i++) {
            SWTBotTestUtil.createNewDiagram(bot);
            bot.waitUntil(Conditions.widgetIsEnabled(diagramMenu), 40000);
            newDiagramsName.add(bot.activeEditor().getTitle());
        }
        assertEquals("4 diagrams should have been created", nbDiagrams, newDiagramsName.size());

        final int nbEditors = bot.editors().size();
        final String currentDiagramName = bot.activeEditor().getTitle();
        bot.waitUntil(Conditions.widgetIsEnabled(bot.menu("File")), 10000);
        bot.menu("File").menu("Delete diagram...").click();
        bot.waitUntil(Conditions.shellIsActive(Messages.DeleteDiagramWizardPage_title), 10000);

        final SWTBotTree tree = bot.tree();
        assertEquals("the list of diagrams should contain 4 items", nbDiagramsInRepository + nbDiagrams,
                tree.getAllItems().length);

        final TableCollection selection = tree.selection();
        assertEquals("only " + currentDiagramName + " should be selected in the tree viewer", 1, selection.rowCount());
        assertEquals("diagram " + currentDiagramName + " should be selected", currentDiagramName, selection.get(0, 0));

        tree.select(newDiagramsName.get(1), newDiagramsName.get(2), newDiagramsName.get(3));

        bot.button(Messages.removeProcessLabel).click();

        bot.waitUntil(Conditions.shellIsActive(Messages.confirmProcessDeleteTitle));
        bot.button(IDialogConstants.YES_LABEL).click();
        bot.waitUntil(new ICondition() {

            @Override
            public boolean test() throws Exception {
                return nbEditors - 3 == bot.editors().size();
            }

            @Override
            public void init(final SWTBot bot) {

            }

            @Override
            public String getFailureMessage() {
                return "editors have not been closed after deleted diagrams";
            }
        }, 40000, 100);

        assertEquals("deleted diagrams are still in repository", nbDiagramsInRepository + 1,
                diagramStore.getChildren().size());

    }

}
