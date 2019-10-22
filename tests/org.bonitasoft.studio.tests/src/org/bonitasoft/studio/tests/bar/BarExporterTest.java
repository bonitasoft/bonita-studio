/**
 * Copyright (C) 2012-2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.tests.bar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.ProjectUtil;
import org.bonitasoft.studio.engine.i18n.Messages;
import org.bonitasoft.studio.swtbot.framework.SWTBotTestUtil;
import org.bonitasoft.studio.swtbot.framework.conditions.BonitaBPMConditions;
import org.bonitasoft.studio.swtbot.framework.conditions.ShellIsActiveWithThreadSTacksOnFailure;
import org.bonitasoft.studio.swtbot.framework.diagram.BotProcessDiagramPropertiesViewFolder;
import org.bonitasoft.studio.swtbot.framework.diagram.execution.BotExecutionDiagramPropertiesView;
import org.bonitasoft.studio.swtbot.framework.draw.BotGefProcessDiagramEditor;
import org.bonitasoft.studio.swtbot.framework.rule.SWTGefBotRule;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditor;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SWTBotJunit4ClassRunner.class)
public class BarExporterTest {

    final public static String EditorTitleRegex = "(.*)\\s\\((.*)\\)";

    private final SWTGefBot bot = new SWTGefBot();

    @Rule
    public SWTGefBotRule gefBotRule = new SWTGefBotRule(bot);

    @Test
    public void testServerBuild() {

        SWTBotTestUtil.createNewDiagram(bot);
        final SWTBotEditor activeEditor = bot.activeEditor();
        final String editorTitle = activeEditor.getTitle();
        assertFalse("Error: first diagram name is empty.", editorTitle.isEmpty());

        final BotExecutionDiagramPropertiesView executionTab = new BotProcessDiagramPropertiesViewFolder(bot)
                .selectExecutionTab();
        executionTab.selectInstantiationFormTab().selectNone();
        new BotGefProcessDiagramEditor(bot).selectElement("Step1");
        executionTab.selectFormTab().selectNone();

        // get the GEF editor to activate tools
        final SWTBotGefEditor gmfEditor = bot.gefEditor(editorTitle);

        // Create 2 Pools
        gmfEditor.activateTool("Pool");
        gmfEditor.click(200, 500);

        new BotProcessDiagramPropertiesViewFolder(bot).selectExecutionTab().selectInstantiationFormTab().selectNone();

        gmfEditor.activateTool("Pool");
        gmfEditor.click(200, 800);

        new BotProcessDiagramPropertiesViewFolder(bot).selectExecutionTab().selectInstantiationFormTab().selectNone();

        // Save Diagram
        bot.menu("File").menu("Save").click();
        bot.waitUntil(BonitaBPMConditions.noPopupActive());

        // Menu Server > Build...
        bot.menu("File").menu("Export as").menu("Business Archive (.bar)...").click();

        // shell 'Build'
        final SWTBotShell shell = bot.shell(Messages.buildTitle);
        bot.waitUntil(Conditions.shellIsActive(Messages.buildTitle));

        // select and check created Diagram in the Tree
        final SWTBotTree tree = bot.treeWithLabel("Select processes to export");
        final SWTBotTreeItem diagramTreeItem = tree.getTreeItem(editorTitle);

        // check the diagram to export
        diagramTreeItem.select().check();
        diagramTreeItem.expand();

        // Number of pool in the diagram
        final int poolListSize = diagramTreeItem.getItems().length;
        assertEquals("Error: Diagram must contain 3 Pools.", 3, poolListSize);

        // unselect the first pool of the list

        diagramTreeItem.getNode(0).select();
        diagramTreeItem.getNode(0).uncheck();

        final List<String> poolTitleList = new ArrayList<>(3);
        final int poolTitleListSize = poolTitleList.size();

        // check the selected pools
        for (int i = 1; i < poolTitleListSize; i++) {
            final SWTBotTreeItem poolTreeItem = diagramTreeItem.getNode(i);
            poolTitleList.set(i, poolTreeItem.getText());
            final String poolName = getItemName(poolTitleList.get(i));

            // test the good pool is checked
            assertFalse("Error: Pool " + i + " should be checked.", !poolTreeItem.isChecked());
            assertFalse("Error: Pool selected is not the good one.", !poolName.equals("Pool" + i));
        }
        // create tmp directory to write diagrams
        final File tmpBarFolder = new File(ProjectUtil.getBonitaStudioWorkFolder(), "testExportBar");
        tmpBarFolder.deleteOnExit();
        tmpBarFolder.mkdirs();
        //set the path where files are saved
        final String tmpBarFolderPath = tmpBarFolder.getAbsolutePath();
        bot.comboBoxWithLabel(Messages.destinationPath + " *").setText(tmpBarFolderPath);

        // click 'Finish' button to close 'Build' shell
        bot.waitUntil(Conditions.widgetIsEnabled(bot.button(IDialogConstants.FINISH_LABEL)));
        bot.button(IDialogConstants.FINISH_LABEL).click();

        //  click 'OK' button to close 'Export' shell
        bot.waitUntil(new ShellIsActiveWithThreadSTacksOnFailure(Messages.exportSuccessTitle), 10000);
        bot.button(IDialogConstants.OK_LABEL).click();

        // wait the shell to close before checking creation of files
        bot.waitUntil(Conditions.shellCloses(shell));

        // check pools files exist
        for (int i = 1; i < poolTitleListSize; i++) {
            final String tmpPoolTitle = poolTitleList.get(i);
            final String poolFileName = getItemName(tmpPoolTitle) + "--" + getItemVersion(tmpPoolTitle) + ".bar";
            final File poolFile = new File(tmpBarFolderPath, poolFileName);
            poolFile.deleteOnExit();
            assertTrue("Error: The Pool export must exist", poolFile.exists());
            assertTrue("Error: The Pool export must be a file", poolFile.isFile());
        }
    }

    /**
     * Return the name of a Diagram from the Title of the Editor
     *
     * @param s title of the editor
     * @return the name of the diagram
     *         <p>
     *         <b>Example:</b>
     *         <p>
     *         entry : "MyDiagram (1.0)"<br>
     *         return : "MyDiagram"
     */
    public String getItemName(final String s) {
        return s.replaceFirst(EditorTitleRegex, "$1");
    }

    /**
     * Return the version of a Diagram from the Title of the Editor
     *
     * @param s title of the editor
     * @return the name of the diagram
     *         <p>
     *         <b>Example:</b>
     *         <p>
     *         entry : "MyDiagram (1.0)"<br>
     *         return : "1.0"
     */
    public String getItemVersion(final String s) {
        return s.replaceFirst(EditorTitleRegex, "$2");
    }

}
