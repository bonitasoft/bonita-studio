/**
 * Copyright (C) 2010 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.diagram.test;

import org.bonitasoft.studio.common.Messages;
import org.bonitasoft.studio.test.swtbot.util.SWTBotTestUtil;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.gef.finder.SWTBotGefTestCase;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditor;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotButton;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotText;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.PartInitException;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Baptiste Mesta
 * 
 */
@RunWith(SWTBotJunit4ClassRunner.class)
public class SharedEditingDomainTests extends SWTBotGefTestCase {

    @Test
    public void testOtherDiagramsAreDirty() throws ExecutionException {
        SWTBotTestUtil.createNewDiagram(bot);
        SWTBotEditor botEditor = bot.activeEditor();
        String editorName = botEditor.getTitle();
        SWTBotGefEditor gmfEditor = bot.gefEditor(editorName);
        SWTBotEditor form1Editor = SWTBotTestUtil.createFormWhenOnAProcessWithStep(bot, gmfEditor, "Step1");
        botEditor.show();
        SWTBotEditor form2Editor = SWTBotTestUtil.createFormWhenOnAProcessWithStep(bot, gmfEditor, "Step1");
        botEditor.show();
        SWTBotEditor form3Editor = SWTBotTestUtil.createFormWhenOnAProcessWithStep(bot, gmfEditor, "Step1");
        botEditor.show();

        assertTrue("Process editor dirty", !botEditor.isDirty());

        assertTrue("Form editor dirty", !form1Editor.isDirty());
        assertTrue("Form editor dirty", !form2Editor.isDirty());
        assertTrue("Form editor dirty", !form3Editor.isDirty());

        gmfEditor.activateTool(Messages.Step_title);
        gmfEditor.click(200, 200);

        assertTrue("Process editor not dirty", botEditor.isDirty());

        assertTrue("Form editor not dirty", form1Editor.isDirty());
        assertTrue("Form editor not dirty", form2Editor.isDirty());
        assertTrue("Form editor not dirty", form3Editor.isDirty());

        gmfEditor.save();

        assertTrue("Process editor dirty", !botEditor.isDirty());

        assertTrue("Form editor dirty", !form1Editor.isDirty());
        assertTrue("Form editor dirty", !form2Editor.isDirty());
        assertTrue("Form editor dirty", !form3Editor.isDirty());
    }

    @Test
    public void testChangeDiagramNameWithFormOpen() throws ExecutionException, PartInitException, InterruptedException {
        SWTBotTestUtil.createNewDiagram(bot);
        SWTBotEditor botEditor = bot.activeEditor();
        String editorName = botEditor.getTitle();
        SWTBotGefEditor gmfEditor = bot.gefEditor(editorName);
        SWTBotEditor form1Editor = SWTBotTestUtil.createFormWhenOnAProcessWithStep(bot, gmfEditor, "Step1");
        botEditor.show();

        SWTBotGefEditPart rootEditPart = gmfEditor.rootEditPart();
        rootEditPart.select();
        gmfEditor.click(10, 10);
        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_GENERAL).show();
        SWTBotButton editButton = bot.button();
        editButton.click();
        SWTBotText nameText = bot.text(0) ;
        String text2 = nameText.getText() ;
        nameText.setText(text2 + "modifiedName");
        bot.button(IDialogConstants.OK_LABEL).click() ;
        Thread.sleep(1500);


        assertTrue("Process editor dirty", !bot.activeEditor().isDirty());
        assertTrue("Process editor has not the correct title", bot.activeEditor().getTitle().contains("modifiedName"));

    }

    @Test
    public void testChangeDiagramNameWithFormOpenAndFocused() throws ExecutionException, PartInitException, InterruptedException {
        SWTBotTestUtil.createNewDiagram(bot);
        SWTBotEditor botEditor = bot.activeEditor();
        String editorName = botEditor.getTitle();
        SWTBotGefEditor gmfEditor = bot.gefEditor(editorName);
        SWTBotEditor form1Editor = SWTBotTestUtil.createFormWhenOnAProcessWithStep(bot, gmfEditor, "Step1");
        botEditor.show();

        SWTBotGefEditPart rootEditPart = gmfEditor.rootEditPart();
        rootEditPart.select();
        gmfEditor.click(10, 10);
        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_GENERAL).show();
        SWTBotButton editButton = bot.button();
        editButton.click();
        SWTBotText nameText = bot.text(0) ;
        String text2 = nameText.getText() ;
        nameText.setText(text2 + "modifiedName2");
        bot.button(IDialogConstants.OK_LABEL).click() ;
        Thread.sleep(1500);

        assertTrue("Process editor dirty", !botEditor.isDirty());
        assertTrue("Form editor dirty", !form1Editor.isDirty());
        IEditorInput editorInput = bot.activeEditor().getReference().getEditorInput();
        assertTrue("Input of editor not changed", editorInput.getName().contains("modifiedName2"));
    }
}
