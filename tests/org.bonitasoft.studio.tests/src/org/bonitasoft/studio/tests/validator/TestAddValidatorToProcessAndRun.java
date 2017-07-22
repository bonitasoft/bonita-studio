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
package org.bonitasoft.studio.tests.validator;

import static org.junit.Assert.assertTrue;

import org.bonitasoft.studio.swtbot.framework.SWTBotTestUtil;
import org.bonitasoft.studio.swtbot.framework.rule.LegacySWTGefBotRule;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditor;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SWTBotJunit4ClassRunner.class)
public class TestAddValidatorToProcessAndRun {

    private SWTGefBot bot = new SWTGefBot();

    @Rule
    public LegacySWTGefBotRule botRule = new LegacySWTGefBotRule(bot);

    @Test
    public void addValidatorOnFieldAndRun() throws Exception {
        SWTBotTestUtil.createNewDiagram(bot);
        SWTBotEditor botEditor = bot.activeEditor();
        SWTBotGefEditor gmfEditor = bot.gefEditor(botEditor.getTitle());
        SWTBotTestUtil.createFormWhenOnAProcessWithStep(bot, gmfEditor, "Step1");
        botEditor = bot.activeEditor();
        gmfEditor = bot.gefEditor(botEditor.getTitle());
        gmfEditor.activateTool("Text area");
        gmfEditor.click(200, 200);
        SWTBotTestUtil.selectTabbedPropertyView(bot, "Validators");
        bot.button("Add...").click();
        bot.comboBoxWithLabel("Validator type *").setSelection("Length");
        bot.textWithLabel("Error message *").setText("text is too long");
        bot.sleep(1000);
        runProcess();
    }

    @Test
    public void addValidatorOnPageAndRun() throws Exception {
        SWTBotTestUtil.createNewDiagram(bot);
        final SWTBotEditor botEditor = bot.activeEditor();
        final SWTBotGefEditor gmfEditor = bot.gefEditor(botEditor.getTitle());
        SWTBotTestUtil.createFormWhenOnAProcessWithStep(bot, gmfEditor, "Step1");
        SWTBotTestUtil.selectTabbedPropertyView(bot, "Validators");
        bot.button("Add...").click();
        bot.comboBoxWithLabel("Validator type *").setSelection("Groovy expression");
        bot.textWithLabel("Error message *").setText("error");
        bot.sleep(1000);
        runProcess();
    }

    @Test
    public void createAndAddValidatorOnFiedAndRun() throws Exception {
        final String className = "MyValidatorTest1";
        final String displayName = "validatorTest1";
        final String packageName = "test.validator";
        SWTBotValidatorTestUtil.createValidatorOnField(bot, displayName, className, packageName);
        SWTBotTestUtil.createNewDiagram(bot);
        SWTBotValidatorTestUtil.addValidatorOnField(bot, displayName);
        runProcess();
    }

    @Test
    public void createAndAddValidatorOnPageAndRun() throws Exception {
        final String className = "MyValidatorTest2";
        final String displayName = "validatorTest2";
        final String packageName = "test.validator";
        SWTBotValidatorTestUtil.createValidatorOnPage(bot, className, packageName, displayName);
        SWTBotTestUtil.createNewDiagram(bot);
        SWTBotValidatorTestUtil.addValidatorOnPage(bot, displayName);
        runProcess();
    }

    private void runProcess() throws ExecutionException {
        final IStatus status = SWTBotTestUtil.selectAndRunFirstPoolFound(bot);
        assertTrue(status.getMessage(), status.isOK());
    }
}
