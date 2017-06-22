/**
 * Copyright (C) 2010 BonitaSoft S.A.
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
package org.bonitasoft.studio.diagram.test;

import static org.junit.Assert.assertEquals;

import org.bonitasoft.studio.form.properties.i18n.Messages;
import org.bonitasoft.studio.model.form.CheckBoxSingleFormField;
import org.bonitasoft.studio.model.form.Form;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.DurationFormFieldEditPart;
import org.bonitasoft.studio.test.swtbot.util.SWTBotTestUtil;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditor;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotCombo;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotText;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SWTBotJunit4ClassRunner.class)
public class FormsDiagramTests {

    private final SWTGefBot bot = new SWTGefBot();

    @Test
    public void testFormsDiagramTest() throws ExecutionException, InterruptedException {
        SWTBotTestUtil.createNewDiagram(bot);
        SWTBotEditor botEditor = bot.activeEditor();
        SWTBotGefEditor gmfEditor = bot.gefEditor(botEditor.getTitle());
        SWTBotTestUtil.createFormWhenOnAProcessWithStep(bot, gmfEditor, "Step1");

        botEditor = bot.activeEditor();
        gmfEditor = bot.gefEditor(botEditor.getTitle());

        gmfEditor.activateTool("Checkbox");
        /* move depends on which tool we used, it seems that it begins from its. */
        gmfEditor.click(200, 200);
        gmfEditor.clickContextMenu("Copy");
        gmfEditor.click(500, 100);
        gmfEditor.clickContextMenu("Paste");

        bot.menu("File").menu("Save").click();

        final IGraphicalEditPart mainEditPart = (IGraphicalEditPart) gmfEditor.mainEditPart().part();
        int count = 0;
        for (final Object o : mainEditPart.getChildren()) {
            if (o instanceof IGraphicalEditPart) {
                final EObject eObject = ((IGraphicalEditPart) o).resolveSemanticElement();
                if (eObject instanceof CheckBoxSingleFormField) {
                    count++;
                }
            }
        }
        bot.activeEditor().close();
        Assert.assertEquals("The copy doesn't work. There is not correct number of Checkbox.", 2, count);
    }

    @Test
    public void testConvertInForms() {

        /* Create a new form */
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
        gmfEditor.getEditPart("Submit1").parent().select();
        final SWTBotGefEditPart ep = gmfEditor.getEditPart("Checkbox1");
        if (ep != null && ep.parent() != null) {
            ep.parent().select();
        }
        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_FORM_GENERAL).show();
        /* Search the Combo for switch type */
        SWTBotTestUtil.selectTabbedPropertyView(bot, "General");
        final SWTBotView generalProperties = bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_FORM_GENERAL);

        SWTBotCombo combo = null;
        try {
            combo = generalProperties.bot().comboBox("Checkbox");
            /* Change type to duration */
            combo.setSelection("Duration");
        } catch (final Exception e) {

        }
        /* Search the duration Editpart */
        //TODO : search directly the Editpart
        final SWTBotGefEditPart mainPart = gmfEditor.mainEditPart();
        boolean found = false;
        for (final SWTBotGefEditPart p : mainPart.children()) {
            if (p.part() instanceof DurationFormFieldEditPart) {
                found = true;
                break;
            }
        }

        bot.menu("File").menu("Save").click();

        bot.activeEditor().close();
        Assert.assertTrue("The convert is not working on form diagram.", found);
    }

    /**
     * @see bug 5730
     * @throws InterruptedException
     */
    @Test
    public void testGridSizeSetTo0() throws InterruptedException {
        /* Create a new form */
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
        gmfEditor.getEditPart("Submit1").parent().select();
        final SWTBotGefEditPart ep = gmfEditor.getEditPart("Checkbox1");
        if (ep != null && ep.parent() != null) {
            ep.parent().select();
        }

        final SWTBotView viewByTitle = bot.viewByTitle("Appearance");
        viewByTitle.show();
        SWTBotTestUtil.selectTabbedPropertyView(bot, "Grid");
        bot.saveAllEditors();
        final SWTBotText widthTextBox = viewByTitle.bot().textWithLabel(Messages.AppearanceSection_ColumnWidth);
        final String text = widthTextBox.getText();
        assertEquals("", text);
        Form form = (Form) ((IGraphicalEditPart) (gmfEditor.mainEditPart().part())).resolveSemanticElement();
        assertEquals(0, form.getColumns().size());
        widthTextBox.setText("0");
        bot.saveAllEditors();
        form = (Form) ((IGraphicalEditPart) (gmfEditor.mainEditPart().part())).resolveSemanticElement();
        assertEquals("0", form.getColumns().get(0).getWidth());
        widthTextBox.setText("");
        bot.saveAllEditors();
        form = (Form) ((IGraphicalEditPart) (gmfEditor.mainEditPart().part())).resolveSemanticElement();
        assertEquals(0, form.getColumns().size());
        bot.saveAllEditors();
        gmfEditor.close();
    }

}
