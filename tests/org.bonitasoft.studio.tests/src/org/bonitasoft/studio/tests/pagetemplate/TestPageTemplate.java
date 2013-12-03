/**
 * Copyright (C) 2011-2013 BonitaSoft S.A.
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
package org.bonitasoft.studio.tests.pagetemplate;

import org.bonitasoft.studio.form.properties.i18n.Messages;
import org.bonitasoft.studio.test.swtbot.util.SWTBotTestUtil;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.gef.finder.SWTBotGefTestCase;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditor;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.eclipse.wst.sse.ui.StructuredTextEditor;

/**
 * @author Aurelien Pupier
 *
 */
public class TestPageTemplate extends SWTBotGefTestCase {

    protected String editorTextContent;


    public void testUsePageTemplate(){
        SWTBotGefEditor formGefEditor = addUsageOfDefaultPageTemplate();

        formGefEditor.activateTool("Checkbox");
        formGefEditor.click(200,200);
        formGefEditor.save();

        formGefEditor.mainEditPart().select();
        checkTextInsideHtml("<div id=\"Checkbox1\">");

        /*Modify widget name*/
        SWTBotGefEditPart checkboxPart = formGefEditor.getEditPart("Checkbox1").parent();
        formGefEditor.select(checkboxPart);
        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_FORM_GENERAL).show();
        SWTBotTestUtil.selectTabbedPropertyView(bot, "General");
        bot.button("Edit...").click();
        //bot.waitUntil(Conditions.shellIsActive(Messages.))
        bot.text("Checkbox1").setText("CheckboxModified");
        bot.button(IDialogConstants.OK_LABEL).click();
        bot.activeEditor().save();


        /*check it has opened, content and close it to come back to form editor*/
        formGefEditor.mainEditPart().click();
        checkTextInsideHtml("<div id=\"CheckboxModified\">");//the line should be at the end


        /*Check restore*/
        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_FORM_GENERAL).show();
        SWTBotTestUtil.selectTabbedPropertyView(bot, "General");
        bot.button(Messages.Restore).click();
        bot.waitUntil(Conditions.shellIsActive(Messages.confirm_title));
        bot.button(IDialogConstants.OK_LABEL).click();

        /*check it has opened, content and close it to come back to form editor*/
        formGefEditor.mainEditPart().select();
        checkTextInsideHtml("<div id=\"CheckboxModified\">");//the line should be better


        /*Add and delete a widget, check that there is a warning popup*/
        formGefEditor.activateTool("Date");
        formGefEditor.click(200,100);
        formGefEditor.save();

        formGefEditor.getEditPart("Date1").parent().select();
        formGefEditor.clickContextMenu("Delete");
        bot.waitUntil(Conditions.shellIsActive("Warning"));
        bot.button(IDialogConstants.OK_LABEL).click();
        bot.activeEditor().save();
        final SWTGefBot gefBot = bot;
        bot.waitUntil(new DefaultCondition() {
			
			public boolean test() throws Exception {				
				return !gefBot.activeEditor().isDirty();
			}
			
			public String getFailureMessage() {
				return "The editor is still dirty.";
			}
		});
        /*check it has opened, content and close it to come back to form editor*/
        formGefEditor.mainEditPart().select();
        checkTextInsideHtml("<div id=\"Date1\">");


        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_FORM_GENERAL).show();
        SWTBotTestUtil.selectTabbedPropertyView(bot, "General");
        bot.button(Messages.Clear).click();
        bot.waitUntil(Conditions.shellIsActive(Messages.confirm_title));
        bot.button(IDialogConstants.OK_LABEL).click();
        bot.activeEditor().save();

        assertFalse(bot.button(Messages.Edit).isEnabled());
        assertFalse(bot.button(Messages.Clear).isEnabled());
    }

    private void checkTextInsideHtml(final String textToCheck) {
        bot.button(Messages.Edit).click();
        StructuredTextEditor editorPart;
        editorPart = (StructuredTextEditor) bot.activeEditor().getReference().getEditor(false);
        final StyledText textWidget2 = editorPart.getTextViewer().getTextWidget();

        Display.getDefault().syncExec(new Runnable() {

            public void run() {
                editorTextContent = textWidget2.getText();
            }
        });
        /*Check the content*/
        System.out.println(editorTextContent);
        assertTrue("The generated html is not well-formed. It doesn't contain "+textToCheck+"\nCurrent text:\n"+editorTextContent,editorTextContent.contains(textToCheck));
        bot.activeEditor().close();
    }

    private SWTBotGefEditor addUsageOfDefaultPageTemplate() {
        bot.closeAllEditors();
        bot.waitUntil(new ICondition() {

            public boolean test() throws Exception {
                return bot.editors().size() == 0;
            }

            public void init(SWTBot bot) {
                // TODO Auto-generated method stub

            }

            public String getFailureMessage() {
                return "Some editors has not been closed properly.";
            }
        });
        SWTBotTestUtil.createNewDiagram(bot);
        SWTBotGefEditor gefEditor = bot.gefEditor(bot.activeEditor().getTitle());

        SWTBotEditor formEditor = SWTBotTestUtil.createFormWhenOnAProcessWithStep(bot, gefEditor, "Step1");
        SWTBotGefEditor formGefEditor = bot.gefEditor(formEditor.getTitle());


        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_FORM_GENERAL).show();

        SWTBotTestUtil.selectTabbedPropertyView(bot, "General");

        bot.button(Messages.Restore).click();

        /*There is a long-running operation before so need a waituntil*/
        bot.waitUntil(new ICondition() {

            public boolean test() throws Exception {
                return bot.button(Messages.Edit).isEnabled();
            }

            public void init(SWTBot bot) {
            }

            public String getFailureMessage() {
                return "Edit button never goes enable.";
            }
        },15000,100);
        /*Check that edit and clear button are enabled*/
        assertTrue(bot.button(Messages.Edit).isEnabled());
        assertTrue(bot.button(Messages.Clear).isEnabled());
        return formGefEditor;
    }

}
