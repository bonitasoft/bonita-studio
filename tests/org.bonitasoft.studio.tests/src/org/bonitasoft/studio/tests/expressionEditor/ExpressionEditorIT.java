/**
 * Copyright (C) 2012 BonitaSoft S.A.
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
package org.bonitasoft.studio.tests.expressionEditor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.bpm.model.expression.assertions.ExpressionAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.bonitasoft.bpm.model.process.Data;
import org.bonitasoft.bpm.model.process.Pool;
import org.bonitasoft.bpm.model.util.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.ui.jface.SWTBotConstants;
import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.swtbot.framework.application.BotApplicationWorkbenchWindow;
import org.bonitasoft.studio.swtbot.framework.expression.BotExpressionEditorDialog;
import org.bonitasoft.studio.swtbot.framework.expression.BotScriptExpressionEditor;
import org.bonitasoft.studio.swtbot.framework.rule.SWTGefBotRule;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SWTBotJunit4ClassRunner.class)
public class ExpressionEditorIT implements SWTBotConstants {

    private final SWTGefBot bot = new SWTGefBot();

    @Rule
    public SWTGefBotRule rule = new SWTGefBotRule(bot);

    @Before
    public void cleanup() throws Exception {
        var currentRepository = RepositoryManager.getInstance().getCurrentRepository().orElseThrow();
        currentRepository.getRepositoryStore(DiagramRepositoryStore.class)
                .getChildren()
                .forEach(DiagramFileStore::delete);
    }


    @Test
    public void testExpressionEditorConstantExpressionTyping() throws Exception {
        var workbenchWindow = new BotApplicationWorkbenchWindow(bot);

        var diagramEditor = workbenchWindow.createNewDiagram();
        diagramEditor.activeProcessDiagramEditor()
                .selectElement("Pool");
        var contractTab = diagramEditor
                .getDiagramPropertiesPart()
                .selectExecutionTab()
                .selectContractTab();

        contractTab.selectInputTab()
                .add()
                .setName("myContractInput");

        diagramEditor
                .getDiagramPropertiesPart()
                .selectDataTab()
                .selectPoolDataTab()
                .addData()
                .setName("myProcessData")
                .typeDefaultValueExpression("myContractInput")
                .finishAfter(300);

        EObject pool = diagramEditor.activeProcessDiagramEditor().getSelectedSemanticElement();
        assertThat(pool).isInstanceOf(Pool.class);

        var data = ModelHelper.getAllElementOfTypeIn(pool, Data.class);
        assertThat(data)
                .hasSize(1)
                .extracting(Data::getDefaultValue)
                .allSatisfy(defaultValue -> assertThat(defaultValue)
                        .hasType(ExpressionConstants.CONSTANT_TYPE)
                        .hasNoReferencedElements());
    }

    private void editGroovyEditor(int buttonIndex, String inputName, String inputtype, String scriptName,
            String groovyScript) {
        SWTBotShell activeShell = bot.activeShell();

        bot.toolbarButtonWithId(SWTBOT_ID_EDITBUTTON, buttonIndex).click();
        BotScriptExpressionEditor editorDialog = new BotExpressionEditorDialog(bot, activeShell)
                .selectScriptTab()
                .setName(scriptName)
                .setScriptContent(groovyScript);
        assertFalse("return type combobox should be disabled", bot.comboBoxWithLabel("Return type").isEnabled());
        assertEquals("return type should be" + inputtype, bot.comboBoxWithLabel("Return type").getText(), inputtype);
        editorDialog.ok();
        activeShell.setFocus();
        assertEquals("wrong value for " + inputName, bot.textWithLabel(inputName).getText(), scriptName);
    }
}
