/**
 * Copyright (C) 2010-2012 BonitaSoft S.A.
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
package org.bonitasoft.studio.diagram.test;

import java.util.Arrays;
import java.util.Vector;

import org.bonitasoft.studio.common.Messages;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.diagram.custom.editPolicies.ActivitySwitchEditPolicy;
import org.bonitasoft.studio.diagram.custom.figures.SlideMenuBarFigure;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
import org.bonitasoft.studio.model.process.Activity;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.Lane;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.diagram.part.ProcessDiagramEditor;
import org.bonitasoft.studio.test.swtbot.util.SWTBotTestUtil;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.gef.finder.SWTBotGefTestCase;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditor;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotCheckBox;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTable;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.bonitasoft.studio.properties.i18n.Messages.addForm;
import static org.bonitasoft.studio.properties.i18n.Messages.addFormTitle;
import static org.bonitasoft.studio.properties.i18n.Messages.activityType;
import static org.bonitasoft.studio.properties.i18n.Messages.activityType_task;
import static org.bonitasoft.studio.data.i18n.Messages.newVariable;
import static org.bonitasoft.studio.data.i18n.Messages.datatypeLabel;
import static org.bonitasoft.studio.data.i18n.Messages.name;
import static org.bonitasoft.studio.form.properties.i18n.Messages.formFieldType;
/**
 * @author Mickael Istria
 * @author Florine Boudin
 *
 */
@RunWith(SWTBotJunit4ClassRunner.class)
public class DiagramTests extends SWTBotGefTestCase {
	
	private static final String PAGEFLOW_LABEL = "Pageflow";

	//@Test TODO reactivate me
	public void testBug1678() {
		SWTBotTestUtil.createNewDiagram(bot);
		SWTBotEditor botEditor = bot.activeEditor();
		SWTBotGefEditor gmfEditor = bot.gefEditor(botEditor.getTitle());
		SWTBotGefEditPart part = getPartRecursively(gmfEditor.rootEditPart(), "Step1");
		part.select();
		Assert.assertTrue(bot.menu("Edit").menu("Copy").isEnabled());
	}
	
	@Test
	public void testDiagramTest() throws ExecutionException {
		SWTBotTestUtil.createNewDiagram(bot);
		SWTBotEditor botEditor = bot.activeEditor();
		SWTBotGefEditor gmfEditor = bot.gefEditor(botEditor.getTitle());
		gmfEditor.activateTool(Messages.Step_title);
		gmfEditor.click(200, 200);
		bot.waitUntil(Conditions.widgetIsEnabled(bot.toolbarButton("Save")));
		bot.toolbarButton("Save").click();
		//bot.menu("Diagram").menu("Save").click();
		IGraphicalEditPart part = (IGraphicalEditPart)gmfEditor.mainEditPart().part();
		MainProcess model = (MainProcess)part.resolveSemanticElement();
		Pool pool = (Pool)model.getElements().get(0);
		Lane lane = (Lane)pool.getElements().get(0);
		Assert.assertEquals("Lane should contain 3 nodes", 3, lane.getElements().size());
	}
	
	@Test
	public void testConvert() throws Exception {
		SWTBotTestUtil.createNewDiagram(bot);
		SWTBotEditor botEditor = bot.activeEditor();
		SWTBotGefEditor gmfEditor = bot.gefEditor(botEditor.getTitle());
		ProcessDiagramEditor processEditor = (ProcessDiagramEditor) gmfEditor.getReference().getEditor(false);
		//gmfEditor
		SWTBotGefEditPart part = getPartRecursively(gmfEditor.rootEditPart(), "Step1");
		part.select();
		IGraphicalEditPart graphicalEditPart = (IGraphicalEditPart) part.part();
		ActivitySwitchEditPolicy convertPolicy = (ActivitySwitchEditPolicy) graphicalEditPart.getEditPolicy(ActivitySwitchEditPolicy.SWITCH_TYPE_ROLE);
		Class<?> beforeClass = graphicalEditPart.resolveSemanticElement().getClass();
		SlideMenuBarFigure toolbarFigure = convertPolicy.getToolbarFigure();
		int x = toolbarFigure.getBounds().x + toolbarFigure.getBounds().width / 2;
		int y = toolbarFigure.getBounds().y + toolbarFigure.getBounds().height / 2;
		gmfEditor.click(x, y);
		y += toolbarFigure.getBounds().height; // move cursor down to first sub item
		gmfEditor.click(x, y);
		//bot.activeEditor().save();
		bot.waitUntil(Conditions.widgetIsEnabled(bot.toolbarButton("Save")));
		bot.toolbarButton("Save").click();
		graphicalEditPart = (IGraphicalEditPart) processEditor.getDiagramGraphicalViewer().getSelectedEditParts().get(0);
		Activity newModelElement = (Activity)graphicalEditPart.resolveSemanticElement();
		Assert.assertEquals("Step1", newModelElement.getName());
		Assert.assertNotSame(beforeClass, newModelElement.getClass());
	}
	
	/** Create & run a 4 tasks process
	 * @author Florine Boudin
	 * @throws Exception
	 */
	@Test
	public void test4TasksDiagram() throws Exception {

		// create a new process
		SWTBotTestUtil.createNewDiagram(bot);
		
		//Create 3 variables
		SWTBotEditor botEditor = bot.activeEditor();
		SWTBotGefEditor gmfEditor = bot.gefEditor(botEditor.getTitle());
		gmfEditor.click(200, 200);
		
		bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_GENERAL).show();
		SWTBotTestUtil.selectTabbedPropertyView(bot, "Data");
		
		// Create 3 new variables
		setNewVariable("varText"   , "Text"   , false);
		setNewVariable("varBoolean", "Boolean", false);
		setNewVariable("varInteger", "Integer", false);
		
		// check 3 variables where created
		bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_GENERAL).show();
		SWTBotTestUtil.selectTabbedPropertyView(bot, "Data");
		
		// check the table has 3 variables
		SWTBotTable table = bot.table(0);
		Assert.assertEquals("Error: wrong number of variable created", 3, table.rowCount());

		final Vector<String> taskNameList = new Vector<String>(Arrays.asList(new String[]{"Step1", "Step2", "Step3", "Step4"}));
		// Create 3 human tasks
		int i=400;
		for(String taskName : taskNameList){

			setTaskAsHuman(gmfEditor, taskName);

			if(taskName!="Step4"){
				// create new Task
				System.out.println("s = "+taskName);
				Assert.assertNotNull("Error: No "+taskName+" task found.",gmfEditor.getEditPart(taskName));
				SWTBotTestUtil.selectTaskFromSelectedElementAndDragIt(gmfEditor, taskName, new Point(i,110));
				i+=200;
			}

		}
		bot.menu("Diagram").menu("Save");

		// check the gmfEditor has 4 human tasks
		IGraphicalEditPart ig = (IGraphicalEditPart) gmfEditor.mainEditPart().part();
		MainProcess mp = (MainProcess) ig.resolveSemanticElement();

		
		int nbHumanTasks = ModelHelper.getAllItemsOfType(mp, ProcessPackage.Literals.TASK).size();
		Assert.assertEquals("Error: wrong number of tasks in the process.", 4, nbHumanTasks);


		int nbTransistions = ModelHelper.getAllItemsOfType(mp, ProcessPackage.Literals.CONNECTION).size();
		Assert.assertEquals("Error: wrong number of connections in the process.", 4, nbTransistions);
		
		// For the 3 first tasks, add a form
		int itmp=0;
		// create a form
		for(String nametask : taskNameList){

			if (nametask!="Step4") {
				//System.out.println("s = "+s);
				gmfEditor.getEditPart(nametask).click();
				bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_APPLICATION).show();
				SWTBotTestUtil.selectTabbedPropertyView(bot, PAGEFLOW_LABEL);

				// add a form
				bot.button(addForm, 0).click();
				// first shell "Add form..."
				bot.waitUntil(Conditions.shellIsActive(addFormTitle));

				if (SWTBotTestUtil.testingBosSp()) {
					//second shell "Add form..."
					bot.button(IDialogConstants.NEXT_LABEL).click();
				}

				// remove 2 of 3 variables in the form
				for (int j = 0; j < 3; j++) {
					if (j != itmp) {
						bot.checkBox(j).deselect();
					}
				}
				// last shell "Add form..."
				bot.button(IDialogConstants.FINISH_LABEL).click();
				
				// add script to conver to an integer on "Step3"
				if(nametask.equals("Step3")){
					SWTBotGefEditor formEditor = bot.gefEditor(bot.activeEditor().getTitle());
					formEditor.getEditPart("varInteger").click();
					bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_FORM_GENERAL).show();
					SWTBotTestUtil.selectTabbedPropertyView(bot, "Data");
					bot.toolbarButtonWithId(ExpressionViewer.SWTBOT_ID_EDITBUTTON, 1).click();
					String valueOf = "Integer.valueOf(field_varInteger)";
					SWTBotTestUtil.setScriptExpression(bot, "theInteger", valueOf, Integer.class.getName());
					bot.waitUntil(new ICondition() {
						
						public boolean test() throws Exception {
							return bot.textWithId(ExpressionViewer.SWTBOT_ID_EXPRESSIONVIEWER_TEXT,2).getText().equals("theInteger");
						}
						
						public void init(SWTBot bot) {
				
						}
						
						public String getFailureMessage() {
							return "Expression not set properly";
						}
					});
				}
				
				
				bot.activeEditor().saveAndClose();
				itmp++;
			}
		}
		
		
		
		
		// Create the form for the 4th task
		gmfEditor.getEditPart("Step4").click();
		
		bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_APPLICATION).show();
		SWTBotTestUtil.selectTabbedPropertyView(bot, PAGEFLOW_LABEL);
		
		bot.button(addForm,0).click();
		
		// first shell "Add form..."
		bot.waitUntil(Conditions.shellIsActive(addFormTitle));

		if(SWTBotTestUtil.testingBosSp()){
			//second shell "Add form..."
			bot.button(IDialogConstants.NEXT_LABEL).click();
		}
		
		// last shell "Add form..."
		bot.button(IDialogConstants.FINISH_LABEL).click();
		
		SWTBotGefEditor formEditor = bot.gefEditor(bot.activeEditor().getTitle());
		
		final String[] varTab = new String[]{"varText", "varBoolean", "varInteger"};
		for(String s : varTab){

			formEditor.getEditPart(s).click();
			bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_FORM_GENERAL).show();
			SWTBotTestUtil.selectTabbedPropertyView(bot, "General");

			// "Field type"
			bot.comboBoxWithLabel(formFieldType).setSelection("Text");
			bot.activeEditor().save();
		}
		
		bot.activeEditor().saveAndClose();
		IStatus status = SWTBotTestUtil.selectAndRunFirstPoolFound(bot);
		assertTrue(status.getMessage(),status.isOK());

	}

	/** Change the type of a Task to be 'Human'
	 * @param gmfEditor
	 * @param nameTask name of the Task
	 */
	private void setTaskAsHuman(SWTBotGefEditor gmfEditor, String nameTask) {
		gmfEditor.getEditPart(nameTask).click();

		// set the Task as Human Task
		bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_GENERAL).show();
		SWTBotTestUtil.selectTabbedPropertyView(bot, "General");

		// "Task type" , "Human"
		bot.comboBoxWithLabel(activityType).setSelection(activityType_task);
	}

	/** Add and set a new variable in the data entry of the General tab of a Task
	 * @param varName name of the variable
	 * @param varType type of the variable : "Text", "Integer", "String", "Boolean", etc...
	 * @param autoGenerateForm  BOS-SP only : true if the checkBox must be selected, else false
	 */
	private void setNewVariable(String varName, String varType, boolean autoGenerateForm ) {
		bot.button("Add...").click();
		
		// open shell "New variable"
		bot.waitUntil(Conditions.shellIsActive(newVariable));
		
		// "Name"
		bot.textWithLabel(name).setText(varName);
		
		// "Data type"
		bot.comboBoxWithLabel(datatypeLabel).setSelection(varType);
		
		if(SWTBotTestUtil.testingBosSp()){
			SWTBotCheckBox cb = bot.checkBox("Auto-generate form");
			if(cb.isChecked() && !autoGenerateForm){
				cb.deselect();		
			}else if(!cb.isChecked() && autoGenerateForm){
				cb.select();
			}
		}
	
		bot.button(IDialogConstants.FINISH_LABEL).click();
	}
	
	public SWTBotGefEditPart getPartRecursively(SWTBotGefEditPart from, String label) {
		for (SWTBotGefEditPart child : from.children()) {
			Element model = (Element) ((IGraphicalEditPart)child.part()).resolveSemanticElement();
			if (model.getName().equals(label)) {
				return child;
			}
		}
		SWTBotGefEditPart res = null;
		for (SWTBotGefEditPart child : from.children()) {
			res = getPartRecursively(child, label);
			if (res != null) {
				return res;
			}
		}
		return res;
	}
}
