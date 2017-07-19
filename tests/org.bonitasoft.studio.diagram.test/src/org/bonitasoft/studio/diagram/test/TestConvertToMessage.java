/**
 * Copyright (C) 2010-2012 BonitaSoft S.A.
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
package org.bonitasoft.studio.diagram.test;

import static org.junit.Assert.assertTrue;

import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.form.Widget;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.MessageInfoEditPart;
import org.bonitasoft.studio.test.swtbot.util.SWTBotTestUtil;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditor;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotCombo;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotMenu;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SWTBotJunit4ClassRunner.class)
public class TestConvertToMessage {

    private final SWTGefBot bot = new SWTGefBot();

    @Test
    @Ignore
    public void testConvertToMessage() throws InterruptedException {
        //bug 1983
        /* Create a new form */
        final SWTBotMenu menu = bot.menu("File");
        SWTBotTestUtil.createNewDiagram(bot);
        SWTBotEditor botEditor = bot.activeEditor();
        SWTBotGefEditor gmfEditor = bot.gefEditor(botEditor.getTitle());
        SWTBotTestUtil.createFormWhenOnAProcessWithStep(bot, gmfEditor, "Step1");

        /* Create a Checkbox */
        botEditor = bot.activeEditor();
        gmfEditor = bot.gefEditor(botEditor.getTitle());
        gmfEditor.activateTool("Checkbox");
        /* move depends on which tool we used, it seems that it begins from its. */
        gmfEditor.click(200, 200);

        //FIXME workaround for the empty properties the first time we open it (remove it if it's fixed)
        gmfEditor.getEditPart("Submit1").parent().select();
        gmfEditor.getEditPart("Checkbox1").parent().select();

        /* Search the Combo for switch type */
        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_FORM_GENERAL).show();
        SWTBotTestUtil.selectTabbedPropertyView(bot, "General");
        final SWTBotView generalProperties = bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_FORM_GENERAL);
        generalProperties.bot().textWithLabel("Show label").setText("testDisplayLabel");
        final SWTBotGefEditor gefEditor = gmfEditor;
        bot.waitUntil(new DefaultCondition() {

            @Override
            public boolean test() throws Exception {
                final Expression displayLabel = ((Widget) (((IGraphicalEditPart) gefEditor.getEditPart("testDisplayLabel")
                        .parent().part()).resolveSemanticElement())).getDisplayLabel();
                return displayLabel.getContent().equals("testDisplayLabel");
            }

            @Override
            public String getFailureMessage() {
                return "no display label set";
            }
        });
        final SWTBotCombo combo = generalProperties.bot().comboBox("Checkbox");
        /* Change type to duration */
        combo.setSelection("Message");
        /* Search the duration Editpart */
        //TODO : search directly the Editpart
        final SWTBotGefEditPart mainPart = gmfEditor.mainEditPart();
        boolean found = false;
        EditPart part = null;
        for (final SWTBotGefEditPart p : mainPart.children()) {
            if (p.part() instanceof MessageInfoEditPart) {
                found = true;
                part = p.part();
                break;
            }
        }

        menu.menu("Save").click();

        assertTrue("The convert is not working on form diagram.", found);
        assertTrue("", ((Widget) ((IGraphicalEditPart) part).resolveSemanticElement()).getDisplayLabel() == null);
    }
}
