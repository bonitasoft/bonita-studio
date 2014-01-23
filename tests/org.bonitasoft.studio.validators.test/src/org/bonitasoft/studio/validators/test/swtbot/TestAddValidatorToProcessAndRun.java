/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 *
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
package org.bonitasoft.studio.validators.test.swtbot;

import org.bonitasoft.studio.test.swtbot.util.SWTBotTestUtil;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.gef.finder.SWTBotGefTestCase;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditor;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author aurelie Zara
 *
 */
@SuppressWarnings("unused")
@RunWith(SWTBotJunit4ClassRunner.class)
public class TestAddValidatorToProcessAndRun extends SWTBotGefTestCase{

    @Test
    public void addValidatorOnFieldAndRun() throws Exception{
        SWTBotTestUtil.createNewDiagram(bot);
        SWTBotEditor botEditor = bot.activeEditor();
        SWTBotGefEditor gmfEditor = bot.gefEditor(botEditor.getTitle());
        SWTBotTestUtil.createFormWhenOnAProcessWithStep(bot, gmfEditor, "Step1");
        botEditor = bot.activeEditor();
        gmfEditor = bot.gefEditor(botEditor.getTitle());
        gmfEditor.activateTool("Text area");
        gmfEditor.click( 200, 200);
        SWTBotTestUtil.selectTabbedPropertyView(bot, "Validators");
        bot.button("Add...").click();
        bot.comboBoxWithLabel("Validator type *").setSelection("Length");
        bot.textWithLabel("Error message *").setText("text is too long");
        bot.sleep(1000);
        runProcess();
    }


    @Test
    public void addValidatorOnPageAndRun() throws Exception{
        SWTBotTestUtil.createNewDiagram(bot);
        SWTBotEditor botEditor = bot.activeEditor();
        SWTBotGefEditor gmfEditor = bot.gefEditor(botEditor.getTitle());
        SWTBotTestUtil.createFormWhenOnAProcessWithStep(bot, gmfEditor, "Step1");
        SWTBotTestUtil.selectTabbedPropertyView(bot, "Validators");
        bot.button("Add...").click();
        bot.comboBoxWithLabel("Validator type *").setSelection("Regular expression");
        bot.textWithLabel("Error message *").setText("error");
        bot.sleep(1000);
        runProcess();
    }

    @Test
    public void createAndAddValidatorOnFiedAndRun() throws Exception {
        String className="MyValidatorTest1";
        String displayName="validatorTest1";
        String packageName="test.validator";
        SWTBotValidatorTestUtil.createValidatorOnField(bot, displayName, className, packageName);
        SWTBotTestUtil.createNewDiagram(bot);
        SWTBotValidatorTestUtil.addValidatorOnField(bot,displayName);
        runProcess();
    }

    @Test
    public void createAndAddValidatorOnPageAndRun() throws Exception {
        String className="MyValidatorTest2";
        String displayName="validatorTest2";
        String packageName="test.validator";
        SWTBotValidatorTestUtil.createValidatorOnPage(bot, className, packageName,displayName);
        SWTBotTestUtil.createNewDiagram(bot);
        SWTBotValidatorTestUtil.addValidatorOnPage(bot,displayName);
        runProcess();
    }

    private static boolean testingBosSp() {
        return Platform.getBundle("org.bonitasoft.studioEx.console.libs") != null;
    }


    @After
    public void closeEditors(){
        bot.saveAllEditors();
        bot.closeAllEditors();
    }

    private void runProcess() throws ExecutionException{
        IStatus status = SWTBotTestUtil.selectAndRunFirstPoolFound(bot);
        assertTrue(status.getMessage(), status.isOK());
    }
}
