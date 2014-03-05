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
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.gef.finder.SWTBotGefTestCase;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditor;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.eclipse.swtbot.swt.finder.waits.ICondition;

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
        saveEditorAndWaitNoMoreDirtyState();

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
        bot.button(IDialogConstants.OK_LABEL).click();
        bot.waitUntil(Conditions.shellIsActive("Warning"));
        bot.button(IDialogConstants.OK_LABEL).click();
        saveEditorAndWaitNoMoreDirtyState();
        /*check it has opened, content and close it to come back to form editor*/
        formGefEditor.mainEditPart().select();
        checkTextInsideHtml("<div id=\"Date1\">");


        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_FORM_GENERAL).show();
        SWTBotTestUtil.selectTabbedPropertyView(bot, "General");
        bot.button(Messages.Clear).click();
        bot.waitUntil(Conditions.shellIsActive(Messages.confirm_title));
        bot.button(IDialogConstants.OK_LABEL).click();
        saveEditorAndWaitNoMoreDirtyState();
        
        assertFalse("Edit button is still enabled", bot.button(Messages.Edit).isEnabled());
        assertFalse("Clear button is still enabled", bot.button(Messages.Clear).isEnabled());
    }

	private SWTGefBot saveEditorAndWaitNoMoreDirtyState() {
		final SWTGefBot gefBot = bot;
		gefBot.activeEditor().save();
        bot.waitUntil(new DefaultCondition() {
			
			public boolean test() throws Exception {				
				return !gefBot.activeEditor().isDirty();
			}
			
			public String getFailureMessage() {
				return "The editor is still dirty after the save.";
			}
		});
		return gefBot;
	}

    private void checkTextInsideHtml(final String textToCheck) {
        bot.button(Messages.Edit).click();
        bot.waitUntil(new ICondition() {
			
			public boolean test() throws Exception {
				editorTextContent = bot.styledText().getText();
				return editorTextContent.contains(textToCheck);
			}
			
			public void init(SWTBot bot) {
			}
			
			public String getFailureMessage() {
				return "The generated html is not well-formed. It doesn't contain "+textToCheck+"\nCurrent text:\n"+editorTextContent;
			}
		},10000,500);
        bot.activeEditor().close();
    }

    private SWTBotGefEditor addUsageOfDefaultPageTemplate() {
        bot.closeAllEditors();
        bot.waitUntil(new ICondition() {

            public boolean test() throws Exception {
                return bot.editors().isEmpty();
            }

            public void init(SWTBot bot) {
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
        bot.waitUntil(Conditions.widgetIsEnabled(bot.button(Messages.Edit)),20000,100);
        assertTrue("Clear button is not enabled", bot.button(Messages.Clear).isEnabled());
        return formGefEditor;
    }

}
