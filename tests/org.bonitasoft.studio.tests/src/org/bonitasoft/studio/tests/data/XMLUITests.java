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
package org.bonitasoft.studio.tests.data;

import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.swtbot.framework.SWTBotTestUtil;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.gef.finder.SWTBotGefTestCase;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditor;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotCombo;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotMenu;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

/**
 * @author Baptiste Mesta
 */
public class XMLUITests extends SWTBotGefTestCase implements SWTBotConstants {

    private SWTBotGefEditor gmfEditor;
    private Pool pool;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        SWTBotMenu menu = bot.menu("Diagram");
        menu.menu("New").click();
        SWTBotEditor botEditor = bot.activeEditor();
        gmfEditor = bot.gefEditor(botEditor.getTitle());
        IGraphicalEditPart part = (IGraphicalEditPart) gmfEditor.mainEditPart().part();
        MainProcess model = (MainProcess) part.resolveSemanticElement();
        pool = (Pool) model.getElements().get(0);

        gmfEditor.getEditPart(pool.getName()).parent().select();
        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_GENERAL).show();
        SWTBotTestUtil.selectTabbedPropertyView(bot, "Data");

        bot.button("Add...").click();
        bot.text().setText("xmlData");
        bot.comboBox().setSelection("XML");
        bot.comboBox(1).setSelection(0);
        bot.comboBox(2).setSelection(0);
        bot.button("Finish").click();
    };

    /**
     * test that the set variable connector have the right expression when using xml data
     */
    public void testUseSetVariableConnectorWithXmlData() {
        executeTestOnConnector("Set Variable -- Set a process or step variable");
    }

    /**
     * @param nodeText
     */
    private void executeTestOnConnector(String nodeText) {
        gmfEditor.getEditPart("Step1").parent().select().part();
        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_GENERAL).show();
        SWTBotTestUtil.selectTabbedPropertyView(bot, "Connectors");
        bot.button("Add...").click();
        final SWTBotTree tree = bot.tree();
        tree.expandNode("Bonita", false);
        final SWTBotTreeItem treeItem = tree.getTreeItem("Bonita");
        treeItem.getNode(nodeText).select();
        bot.button("Next >").click();
        bot.text().setText("setVariableWithXmlTest");
        bot.button("Next >").click();
        final SWTBotCombo comboBox = bot.comboBox();
        comboBox.setSelection("${xmlData}...");
        final SWTBotTree tree2 = bot.tree();
        tree2.expandNode("Whole variable", false);
        final SWTBotTreeItem treeItem2 = tree2.getTreeItem("Whole variable");
        treeItem2.getNode("Body").select();
        bot.button("OK").click();
        final String result = comboBox.getText();
        bot.button("Cancel").click();
        assertEquals("xmlData$/Body", result);
    }

    /*
     * (non-Javadoc)
     * @see junit.framework.TestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        gmfEditor.saveAndClose();
        super.tearDown();
    }

}
