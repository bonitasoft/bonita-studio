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
package org.bonitasoft.studio.commands.test;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.diagram.custom.editPolicies.NextElementEditPolicy;
import org.bonitasoft.studio.model.process.Activity;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.EnumType;
import org.bonitasoft.studio.model.process.Lane;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.StringType;
import org.bonitasoft.studio.model.process.diagram.form.part.FormDiagramEditor;
import org.bonitasoft.studio.model.process.diagram.part.ProcessDiagramEditor;
import org.bonitasoft.studio.test.swtbot.util.SWTBotTestUtil;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.common.util.EList;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.SWTBotGefTestCase;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditor;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotMenu;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @author Mickael Istria
 *
 */
public class CopyPasteTests extends SWTBotGefTestCase {

    @Override
    @Before
    public void setUp() {
        bot.saveAllEditors();
        bot.closeAllEditors();
    }

    @Override
    @After
    public void tearDown() {
        bot.activeEditor().saveAndClose();
        bot.closeAllEditors();
    }

    @Test
    public void testCopyPaste() {
        SWTBotTestUtil.createNewDiagram(bot);
        SWTBotGefEditor editor1 = bot.gefEditor(bot.activeEditor().getTitle());
        SWTBotGefEditPart part = editor1.getEditPart("Step1").parent();
        part.select();
        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_GENERAL).show();
        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_GENERAL).setFocus();
        SWTBotTestUtil.selectTabbedPropertyView(bot, "General");
        //bot.viewByTitle("Details").bot().text(0).typeText("Test");
        NextElementEditPolicy policy = (NextElementEditPolicy) part.part().getEditPolicy(NextElementEditPolicy.NEXT_ELEMENT_ROLE);
        IFigure textAnnotationFigure = policy.getTextAnnotationFigure();
        Point location = textAnnotationFigure.getBounds().getCenter().getCopy();
        textAnnotationFigure.translateToAbsolute(location);
        editor1.drag(location.x, location.y, 200, 200);
        part.select();
        editor1.clickContextMenu("Copy");
        SWTBotTestUtil.createNewDiagram(bot);
        SWTBotGefEditor editor2 = bot.gefEditor(bot.activeEditor().getTitle());
        editor2.click(200, 200);
        editor2.clickContextMenu("Paste");
        String label1 = getLabelFromEditor(editor1);
        String label2 = getLabelFromEditor(editor2);
        editor2.saveAndClose();
        editor1.saveAndClose();

        SWTBotMenu open = bot.menu("Diagram").menu("Open...");
        open.click();
        bot.tree().select(label1);
        bot.button("Delete").click();
        bot.button("Yes").click();
        bot.tree().select(label2);
        bot.button("Open").click();
        editor2 = bot.gefEditor(bot.activeEditor().getTitle());
        part = editor2.getEditPart("Step1").parent();
        editor2.drag(part, 100, 100);
        bot.activeEditor().save();
        bot.sleep(1000);
        assertFalse(editor2.isDirty());
    }

    @Test
    @Ignore
    public void testBug2610() throws Exception {
        SWTBotTestUtil.importProcessWIthPathFromClass(bot, "ProcessWithData_1_0.bos", "Bonita 6.x", "ProcessWithData", this.getClass(), false);
        SWTBotGefEditor editor = bot.gefEditor(bot.activeEditor().getTitle());
        SWTBotGefEditPart part = editor.getEditPart("StepWithData");
        part.click();
        editor.clickContextMenu("Copy");
        final SWTBotGefEditPart poolPart = part.parent().parent();
        poolPart.click();
        editor.clickContextMenu("Paste");
        bot.waitUntil(new DefaultCondition() {
			
			public boolean test() throws Exception {
				return findNewActivityPart(poolPart) != null;
			}
			
			public String getFailureMessage() {
				return "Can't find the new Activity EditPart that just have been pasted";
			}
		});
        SWTBotGefEditPart newPart = findNewActivityPart(poolPart);
        
        final IGraphicalEditPart activityEditPart = (IGraphicalEditPart)newPart.part();
		Activity activity = (Activity) activityEditPart.resolveSemanticElement();
        final EList<Data> activityDatas = activity.getData();
		final Data firstData = activityDatas.get(0);
		Assert.assertNotNull("Data type not copied", firstData.getDataType());
        Assert.assertTrue("Bad Copied DataType", firstData.getDataType() instanceof StringType);
        final Data secondData = activityDatas.get(1);
		Assert.assertNotNull("Data type not copied", secondData.getDataType());
        Assert.assertTrue("Bad Copied DataType", secondData.getDataType() instanceof EnumType);
    }

	private SWTBotGefEditPart findNewActivityPart(
			final SWTBotGefEditPart poolPart) {
		SWTBotGefEditPart newPart = null;
        for (SWTBotGefEditPart child : poolPart.children()) {
            if (child.sourceConnections().size() == 0 && child.targetConnections().size() == 0) {
                newPart = child;
            }
        }
		return newPart;
	}

    @Test
    public void testMultipleCopyPaste() throws Exception {
        SWTBotTestUtil.createNewDiagram(bot);
        SWTBotGefEditor editor1 = bot.gefEditor(bot.activeEditor().getTitle());
        SWTBotGefEditPart stepPart = editor1.getEditPart("Step1").parent();
        SWTBotGefEditPart startPart = editor1.getEditPart("Start1").parent();
        editor1.select(stepPart, startPart);
        editor1.clickContextMenu("Copy");
        final SWTBotGefEditPart lanePart = stepPart.parent(/*Compartment*/).parent();
        editor1.select(lanePart);
        editor1.clickContextMenu("Paste");
        Lane lane = (Lane) ((IGraphicalEditPart)lanePart.part()).resolveSemanticElement();
        assertEquals("Not same number of nodes as expected", 4, lane.getElements().size());
        assertEquals("Not same number of transitions as expected", 2, ModelHelper.getParentProcess(lane).getConnections().size());
    }

    /**
     * @param editor1
     * @return
     */
    private String getLabelFromEditor(SWTBotGefEditor editor1) {
        MainProcess proc = (MainProcess) ((ProcessDiagramEditor) editor1.getReference().getEditor(false)).getDiagramEditPart().resolveSemanticElement();
        return proc.getName() + " (" + proc.getVersion() + ")";
    }

    @Test
    public void testCopyPasteToAnotherDiagram() throws Exception {
        SWTBotTestUtil.createNewDiagram(bot);
        {
            SWTBotGefEditor editor1 = bot.gefEditor(bot.activeEditor().getTitle());
            SWTBotGefEditPart stepPart = editor1.getEditPart("Step1").parent();
            SWTBotGefEditPart startPart = editor1.getEditPart("Start1").parent();
            editor1.select(stepPart, startPart);
            editor1.clickContextMenu("Copy");
        }
        SWTBotTestUtil.createNewDiagram(bot);
        {
            SWTBotGefEditor editor2 = bot.gefEditor(bot.activeEditor().getTitle());
            SWTBotGefEditPart stepPart2 = editor2.getEditPart("Step1").parent();
            final SWTBotGefEditPart lanePart = stepPart2.parent().parent();
            editor2.select(lanePart);
            editor2.clickContextMenu("Paste");
            Lane lane = (Lane) ((IGraphicalEditPart)lanePart.part()).resolveSemanticElement();
            assertEquals("Not same number of nodes as expected", 4, lane.getElements().size());
            assertEquals("Not same number of transitions as expected", 2, ModelHelper.getParentProcess(lane).getConnections().size());
        }
    }

    @Test
    public void testCopyPasteToAnotherDiagramWithLane() throws Exception {
        SWTBotTestUtil.createNewDiagram(bot);
        {
            SWTBotGefEditor editor1 = bot.gefEditor(bot.activeEditor().getTitle());
            SWTBotGefEditPart stepPart = editor1.getEditPart("Step1").parent();
            SWTBotGefEditPart startPart = editor1.getEditPart("Start1").parent();
            editor1.select(stepPart, startPart);
            editor1.clickContextMenu("Copy");
        }
        SWTBotTestUtil.createNewDiagram(bot);
        {
            SWTBotGefEditor editor2 = bot.gefEditor(bot.activeEditor().getTitle());
            SWTBotGefEditPart stepPart2 = editor2.getEditPart("Step1").parent();
            editor2.activateTool("Lane");
            editor2.click(100, 100);
            stepPart2 = editor2.getEditPart("Step1").parent();
            final SWTBotGefEditPart lanePart2 = stepPart2.parent().parent();
            lanePart2.select();
            editor2.clickContextMenu("Paste");
            Lane lane = (Lane) ((IGraphicalEditPart)lanePart2.part()).resolveSemanticElement();
            assertEquals("Not same number of nodes as expected", 4, lane.getElements().size());
            assertEquals("Not same number of transitions as expected", 2, ((Pool)lane.eContainer()).getConnections().size());
        }
    }

    @Test
    public void testCopyPasteWithForms() throws Exception {
        // Test bug 3103
        SWTBotTestUtil.createNewDiagram(bot);
        final String title = bot.activeEditor().getTitle();
        SWTBotGefEditor editor1 = bot.gefEditor(title);
        //   editor1.click(100, 100);
        SWTBotGefEditPart stepPart = editor1.getEditPart("Step1").parent();
        final SWTBotGefEditPart poolPart = stepPart.parent().parent();
        SWTBotTestUtil.createFormWhenOnAProcessWithStep(bot, editor1, "Step1");
        editor1.show();
        editor1.select(stepPart);

        //FIXME: need to unselect, reselect to have conextual menu enablement refreshed
        editor1.select(poolPart);
        editor1.select(stepPart);
        editor1.clickContextMenu("Copy");
        editor1.select(poolPart);
        editor1.clickContextMenu("Paste");

        SWTBotGefEditPart copyStepPart = editor1.getEditPart("Copy of Step1").parent();
        editor1.select(copyStepPart);


        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_APPLICATION).show();
        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_APPLICATION).setFocus();

        bot.treeWithId(SWTBotConstants.APPLICATION_SECTION_FORMS_SELECTION_TREE).getTreeItem("Step1").select();
        bot.button("Edit").click();

        assertTrue("Not form editor open", bot.activeEditor().getReference().getEditor(false) instanceof FormDiagramEditor);
    }
}
