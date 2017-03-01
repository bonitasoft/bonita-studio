/**
 * Copyright (C) 2011-2013 BonitaSoft S.A.
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
package org.bonitasoft.studio.tests.pagetemplate;

import static org.assertj.core.api.Assertions.assertThat;

import org.bonitasoft.studio.form.properties.i18n.Messages;
import org.bonitasoft.studio.swtbot.framework.application.BotApplicationWorkbenchWindow;
import org.bonitasoft.studio.swtbot.framework.application.editor.BotHTMLEditor;
import org.bonitasoft.studio.swtbot.framework.conditions.AssertionCondition;
import org.bonitasoft.studio.swtbot.framework.diagram.BotProcessDiagramPerspective;
import org.bonitasoft.studio.swtbot.framework.diagram.general.form.general.BotGeneralPropertySection;
import org.bonitasoft.studio.swtbot.framework.rule.SWTGefBotRule;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Aurelien Pupier
 */
@RunWith(SWTBotJunit4ClassRunner.class)
public class PageTemplateEditionIT {

    private SWTGefBot bot = new SWTGefBot();

    @Rule
    public SWTGefBotRule botRule = new SWTGefBotRule(bot);

    @Test
    public void testUsePageTemplate() {
        final BotApplicationWorkbenchWindow botApplicationWorkbenchWindow = new BotApplicationWorkbenchWindow(bot);
        final BotProcessDiagramPerspective botProcessDiagramPerspective = botApplicationWorkbenchWindow.createNewDiagram();
        botProcessDiagramPerspective.activeProcessDiagramEditor().selectElement("Step1");
        botProcessDiagramPerspective.getDiagramPropertiesPart().selectApplicationTab().selectPageflowTab().addForm()
                .finish();

        botProcessDiagramPerspective.activeFormDiagramEditor().addWidget("Checkbox", 1, 1).save().selectForm();
        final BotGeneralPropertySection botGeneralTab = botProcessDiagramPerspective.getFormPropertiesPart()
                .selectGeneralTab().selectGeneralTab();
        botGeneralTab.useLayoutGeneratedFromDesign();
        bot.waitUntil(Conditions.widgetIsEnabled(bot.button(Messages.Edit)), 20000, 100);
        assertThat(bot.button(Messages.Clear).isEnabled());
        checkTextInsideHtml(botGeneralTab, "<div id=\"Checkbox1\">");

        /* Modify widget id */
        botProcessDiagramPerspective.activeFormDiagramEditor().selectElement("Checkbox1");
        botGeneralTab.setName("CheckboxModified");
        botApplicationWorkbenchWindow.save();

        /* check it has opened, content and close it to come back to form editor */
        botProcessDiagramPerspective.activeFormDiagramEditor().selectForm();
        checkTextInsideHtml(botGeneralTab, "<div id=\"CheckboxModified\">");//the line should be at the end

        botGeneralTab.useLayoutGeneratedFromDesign();
        bot.waitUntil(Conditions.shellIsActive(Messages.confirm_title));
        bot.button(IDialogConstants.OK_LABEL).click();

        /* check it has opened, content and close it to come back to form editor */
        checkTextInsideHtml(botGeneralTab, "<div id=\"CheckboxModified\">");//the line should be better

        /* Add and delete a widget, check that there is a warning popup */
        botProcessDiagramPerspective.activeFormDiagramEditor().addWidget("Date", 1, 2);
        botApplicationWorkbenchWindow.save();
        botProcessDiagramPerspective.activeFormDiagramEditor().selectWidget("Date1").clickContextMenu("Delete");
        bot.button(IDialogConstants.OK_LABEL).click();
        bot.waitUntil(Conditions.shellIsActive("Warning"));
        bot.button(IDialogConstants.OK_LABEL).click();
        //  botProcessDiagramPerspective.activeFormDiagramEditor().save();
        botApplicationWorkbenchWindow.save();
        /* check it has opened, content and close it to come back to form editor */
        botProcessDiagramPerspective.activeFormDiagramEditor().selectForm();
        checkTextInsideHtml(botGeneralTab, "<div id=\"Date1\">");

        botGeneralTab.clear();
        // botProcessDiagramPerspective.activeFormDiagramEditor().save();
        botApplicationWorkbenchWindow.save();
        assertThat(bot.button(Messages.Edit).isEnabled()).isFalse();
        assertThat(bot.button(Messages.Clear).isEnabled()).isFalse();
    }

    private void checkTextInsideHtml(final BotGeneralPropertySection botGeneralTab, final String textToCheck) {
        final BotHTMLEditor botHTMLEditor = botGeneralTab.editTemplate();
        bot.waitUntil(new AssertionCondition() {

            @Override
            protected void makeAssert() throws Exception {
                assertThat(botHTMLEditor.getEditorContent()).contains(textToCheck);
            }
        });
        botHTMLEditor.close();
    }

}
