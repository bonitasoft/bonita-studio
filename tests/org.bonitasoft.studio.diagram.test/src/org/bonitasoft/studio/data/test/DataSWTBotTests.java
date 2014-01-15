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
package org.bonitasoft.studio.data.test;

import static org.bonitasoft.studio.expression.editor.i18n.Messages.editExpression;
import static org.bonitasoft.studio.expression.editor.i18n.Messages.expressionTypeLabel;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.DataTypeLabels;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.data.i18n.Messages;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.Activity;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.IntegerType;
import org.bonitasoft.studio.model.process.Lane;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.test.swtbot.util.SWTBotTestUtil;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.gef.finder.SWTBotGefTestCase;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditor;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTable;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Baptiste Mesta
 *
 */
@RunWith(SWTBotJunit4ClassRunner.class)
public class DataSWTBotTests extends SWTBotGefTestCase {


    /**
     * @throws Exception
     */
    @Test
    public void testCreateData() throws Exception {
        SWTBotTestUtil.createNewDiagram(bot);
        SWTBotEditor botEditor = bot.activeEditor();
        SWTBotGefEditor gmfEditor = bot.gefEditor(botEditor.getTitle());

        IGraphicalEditPart part = (IGraphicalEditPart)gmfEditor.mainEditPart().part();
        MainProcess model = (MainProcess)part.resolveSemanticElement();
        Pool pool = (Pool)model.getElements().get(0);

        gmfEditor.getEditPart(pool.getName()).parent().select();
        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_GENERAL).show();
        addDataOnSelectedElementWithName("newData");

        assertTrue("no data added",pool.getData().size()==1);
        assertTrue("wrong data added",pool.getData().get(0).getName().equals("newData"));

        bot.menu("Diagram").menu("Close").click();

    }

    @Test
    public void testEditData() throws Exception {
        SWTBotTestUtil.importProcessWIthPathFromClass(bot, "ProcessWithData_1_0.bos", "Bonita 6.x", "ProcessWithData", this.getClass(), false);

        SWTBotEditor botEditor = bot.activeEditor();
        SWTBotGefEditor gmfEditor = bot.gefEditor(botEditor.getTitle());

        IGraphicalEditPart part = (IGraphicalEditPart)gmfEditor.mainEditPart().part();
        MainProcess model = (MainProcess)part.resolveSemanticElement();
        Pool pool = (Pool)model.getElements().get(0);

        gmfEditor.getEditPart(pool.getName()).parent().select();

        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_GENERAL).show();
        SWTBotTestUtil.selectTabbedPropertyView(bot, "Data");

        Data firstData = pool.getData().get(0);


        bot.table().select(firstData.getName()+" -- "+firstData.getDataType().getName());
        // button("Edit...")
        bot.button(Messages.updateData).click();
        bot.text().setText("newName");
        bot.comboBox().setSelection("Integer");
        bot.button(IDialogConstants.OK_LABEL).click();

        bot.menu("Diagram").menu("Save").click();

        firstData = pool.getData().get(0);
        assertTrue("wrong rename",firstData.getName().equals("newName"));
        assertTrue("wrong change type",firstData.getDataType() instanceof IntegerType);

        bot.menu("Diagram").menu("Close").click();
    }



    @Test
    public void testRemoveData() throws Exception {

        SWTBotTestUtil.createNewDiagram(bot);

        SWTBotEditor botEditor = bot.activeEditor();
        SWTBotGefEditor gmfEditor = bot.gefEditor(botEditor.getTitle());

        IGraphicalEditPart part = (IGraphicalEditPart)gmfEditor.mainEditPart().part();
        MainProcess model = (MainProcess)part.resolveSemanticElement();
        Pool pool = (Pool)model.getElements().get(0);

        gmfEditor.getEditPart(pool.getName()).parent().select();

        addDataOnSelectedElementWithName("newData");
        addDataOnSelectedElementWithName("dataToNotBeRemoved");

        int nbData = pool.getData().size();
        Data firstData = pool.getData().get(0);
        bot.table().select(firstData.getName()+" -- "+firstData.getDataType().getName());
        // button("Remove")
        bot.button(Messages.removeData).click();
        bot.button(IDialogConstants.OK_LABEL).click();

        bot.menu("Diagram").menu("Save").click();

        assertEquals("data not removed",nbData -1, pool.getData().size());
        assertFalse("the wrong data was removed",firstData.equals(pool.getData().get(0)));

        bot.menu("Diagram").menu("Close").click();
    }

    @Test
    public void testMoveData() throws Exception {
        Pool pool = createProcessWithData();
        Lane lane = (Lane)pool.getElements().get(0);
        SWTBotEditor botEditor = bot.activeEditor();
        SWTBotGefEditor gmfEditor = bot.gefEditor(botEditor.getTitle());

        SWTBotGefEditPart parent = gmfEditor.getEditPart("Step1").parent();
        final Activity step = (Activity) ((IGraphicalEditPart) parent.part()).resolveSemanticElement();
        SWTBotGefEditPart poolPart = gmfEditor.getEditPart(pool.getName()).parent();
        poolPart.select();
        poolPart.click();
        addDataOnSelectedElementWithName("dataToMove");
        int nbPoolData = pool.getData().size();
        final int nbStepData = step.getData().size();
        bot.table().select("dataToMove -- Text");
        // button("Move...")
        bot.button(Messages.moveData).click();
        bot.tree().getTreeItem("Pool "+pool.getName()).getNode("Lane "+lane.getName()).select("Task Step1");
        bot.button(IDialogConstants.FINISH_LABEL).click();
        bot.menu("Diagram").menu("Save").click();
        bot.waitUntil(new DefaultCondition() {
			
			public boolean test() throws Exception {
				return nbStepData +1 == step.getData().size();
			}
			
			public String getFailureMessage() {
				return "data not removed";
			}
		});
        assertEquals("data not added",nbPoolData -1, pool.getData().size());
        bot.menu("Diagram").menu("Close").click();
    }


    private Pool createProcessWithData() {
        SWTBotTestUtil.createNewDiagram(bot);
        SWTBotEditor botEditor = bot.activeEditor();
        SWTBotGefEditor gmfEditor = bot.gefEditor(botEditor.getTitle());


        IGraphicalEditPart part = (IGraphicalEditPart)gmfEditor.mainEditPart().part();
        MainProcess model = (MainProcess)part.resolveSemanticElement();
        Pool pool = (Pool)model.getElements().get(0);

        gmfEditor.getEditPart(pool.getName()).parent().select();
        String dataName = "newData";
        addDataOnSelectedElementWithName(dataName);
        return pool;
    }

    /**Add a Text Data
     * 
     * @param dataName
     */
    private void addDataOnSelectedElementWithName(String dataName) {
        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_GENERAL).show();
        SWTBotTestUtil.selectTabbedPropertyView(bot, "Data");

        bot.button("Add...").click();
        // Shell "New variable"
        bot.waitUntil(Conditions.shellIsActive(Messages.newVariable));

        bot.textWithLabel(Messages.name+" *").setText(dataName);
        bot.waitUntil(Conditions.widgetIsEnabled(bot.button(IDialogConstants.FINISH_LABEL)));
        bot.button(IDialogConstants.FINISH_LABEL).click();
        bot.menu("Diagram").menu("Save").click();
    }

    @Test
    public void testDataDefaultValueReturnType() throws Exception {
        SWTBotTestUtil.createNewDiagram(bot);
        SWTBotEditor botEditor = bot.activeEditor();
        SWTBotGefEditor gmfEditor = bot.gefEditor(botEditor.getTitle());

        IGraphicalEditPart part = (IGraphicalEditPart)gmfEditor.mainEditPart().part();
        MainProcess model = (MainProcess)part.resolveSemanticElement();
        Pool pool = (Pool)model.getElements().get(0);

        gmfEditor.getEditPart(pool.getName()).parent().select();
        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_GENERAL).show();

        SWTBotTestUtil.selectTabbedPropertyView(bot, "Data");

        bot.button("Add...").click();
        // Shell "New variable"
        bot.waitUntil(Conditions.shellIsActive(Messages.newVariable));

        final String dataName = "myDataName";
        bot.textWithLabel(Messages.name +" *").setText(dataName);

        String defaultValue = "test return type";
        bot.textWithLabel(Messages.defaultValueLabel).setText(defaultValue);
        bot.sleep(500);

        bot.toolbarButtonWithId(ExpressionViewer.SWTBOT_ID_EDITBUTTON).click();
        assertEquals("Expression return type should be "+String.class.getName(),String.class.getName(),bot.comboBoxWithLabel(Messages.returnType).getText());
        bot.button(IDialogConstants.OK_LABEL).click();


        bot.comboBoxWithLabel(Messages.datatypeLabel).setSelection(DataTypeLabels.integerDataType);

        defaultValue = "50";
        bot.textWithLabel(Messages.defaultValueLabel).setText(defaultValue);
        bot.sleep(500);
        bot.toolbarButtonWithId(ExpressionViewer.SWTBOT_ID_EDITBUTTON).click();
        assertEquals("Expression return type should be "+Integer.class.getName(),Integer.class.getName(),bot.comboBoxWithLabel(Messages.returnType).getText());

        bot.button(IDialogConstants.OK_LABEL).click();

        bot.waitUntil(Conditions.widgetIsEnabled(bot.button(IDialogConstants.FINISH_LABEL)));
        bot.button(IDialogConstants.FINISH_LABEL).click();
        bot.menu("Diagram").menu("Save").click();


        bot.menu("Diagram").menu("Close").click();
    }
    
    @Test
    public void testDatacantBeInitializeByItself(){
    	SWTBotTestUtil.createNewDiagram(bot);
    	SWTBotTestUtil.changeDiagramName(bot, "Step1", "DataInit");
    	SWTBotEditor botEditor = bot.activeEditor();
    	SWTBotGefEditor gmfEditor = bot.gefEditor(botEditor.getTitle());
    	AbstractProcess proc = ModelHelper.getParentProcess(((IGraphicalEditPart)gmfEditor.getEditPart("Step1").part()).resolveSemanticElement());

    	List<String> step1DataList = new ArrayList<String>(2);
    	step1DataList.add("varS1_1"+" -- "+"Text");
    	step1DataList.add("varS1_2"+" -- "+"Integer");
    	List<String> step2DataList = new ArrayList<String>(2);
    	step2DataList.add("varS2_1"+" -- "+"Text");
    	step2DataList.add("varS2_2"+" -- "+"Integer");
    	List<String> procDataList  = new ArrayList<String>(2);
    	procDataList.add("procVar_1"+" -- "+"Text");
    	procDataList.add("procVar_2"+" -- "+"Integer");

    	// add data to Process
    	gmfEditor.getEditPart(proc.getName());
    	getDataSection( bot );
    	bot.button("Add...").click();
    	SWTBotTestUtil.addNewData(bot, "procVar_1", "Text", false, null);
    	bot.button("Add...").click();
    	SWTBotTestUtil.addNewData(bot, "procVar_2", "Integer", false, null);

    	SWTBotTestUtil.selectTaskFromSelectedElementAndDragIt(gmfEditor, "Step1", new Point(400, 100));

    	// set data on step1 Task
    	gmfEditor.getEditPart("Step1").click();
    	getDataSection( bot );
    	bot.button("Add...").click();
    	SWTBotTestUtil.addNewData(bot, "varS1_1", "Text", false, null);
    	bot.button("Add...").click();
    	SWTBotTestUtil.addNewData(bot, "varS1_2", "Integer", false, null);

    	// set data on step2 Task
    	gmfEditor.getEditPart("Step2").click();
    	getDataSection( bot );
    	bot.button("Add...").click();
    	SWTBotTestUtil.addNewData(bot, "varS2_1", "Text", false, null);
    	bot.button("Add...").click();
    	SWTBotTestUtil.addNewData(bot, "varS2_2", "Integer", false, null);
    	bot.menu("Diagram").menu("Save").click();

    	// check only process variables are available in tasks data edit expression
    	gmfEditor.getEditPart("Step1").click();
    	getDataSection( bot );
    	SWTBotTable dataTable = bot.table();
    	
    	// Test on var varS1_2
    	dataTable.select("varS1_1"+" -- "+"Text");
    	bot.button("Edit...").click();
    	bot.waitUntil(Conditions.shellIsActive("Edit variable"));
    	bot.toolbarButtonWithId(ExpressionViewer.SWTBOT_ID_EDITBUTTON, 0).click();
    	bot.waitUntil(Conditions.shellIsActive(editExpression));
    	bot.tableWithLabel(expressionTypeLabel).select("Variable");
    	SWTBotTable tableVar = bot.table(1);
		Assert.assertFalse("Error: Task data can't be initialized by itself", tableVar.containsItem("varS1_1"+" -- "+"Text"));
		Assert.assertFalse("Error: Task data can't be initialized by a sibling task data", tableVar.containsItem("varS1_2"+" -- "+"Integer"));
    	for(int j=0;j<2;j++){
    		Assert.assertFalse("Error: Task data can't be initialized by task data", tableVar.containsItem(step2DataList.get(j)));
    		Assert.assertTrue("Error: Task data sould be initialized by Process data", tableVar.containsItem(procDataList.get(j)));
    	}
    	bot.button(IDialogConstants.CANCEL_LABEL).click();
    	bot.button(IDialogConstants.CANCEL_LABEL).click();

    	
    	// Test on var varS1_2
    	dataTable.select("varS1_2"+" -- "+"Integer");
    	bot.button("Edit...").click();
    	bot.waitUntil(Conditions.shellIsActive("Edit variable"));
    	bot.toolbarButtonWithId(ExpressionViewer.SWTBOT_ID_EDITBUTTON, 0).click();
    	bot.waitUntil(Conditions.shellIsActive(editExpression));
    	bot.tableWithLabel(expressionTypeLabel).select("Variable");
    	tableVar = bot.table(1);
		Assert.assertFalse("Error: Task data can't be initialized by itself", tableVar.containsItem("varS1_2"+" -- "+"Integer"));
		Assert.assertFalse("Error: Task data can't be initialized by a sibling task data", tableVar.containsItem("varS1_1"+" -- "+"Text"));
    	for(int j=0;j<2;j++){
    		Assert.assertFalse("Error: Task data can't be initialized by task data", tableVar.containsItem(step2DataList.get(j)));
    		Assert.assertTrue("Error: Task data sould be initialized by Process data", tableVar.containsItem(procDataList.get(j)));
    	}
    	bot.button(IDialogConstants.CANCEL_LABEL).click();
    	bot.button(IDialogConstants.CANCEL_LABEL).click();

    }
    
    @Test
    public void testCreateDataWithExistingId(){
    	//Add the data myData on pool
    	String dataName = "myData";
    	String dataName1 = "myData1";
    	
    	SWTBotTestUtil.createNewDiagram(bot);
        SWTBotEditor botEditor = bot.activeEditor();
        SWTBotGefEditor gmfEditor = bot.gefEditor(botEditor.getTitle());
        String diagramTitle=botEditor.getTitle();
        getDataSection(bot);
        bot.button(Messages.addData).click();
        bot.textWithLabel(Messages.name+" *").setText(dataName);
        assertTrue("finish button should be enabled",bot.button(IDialogConstants.FINISH_LABEL).isEnabled());
        bot.button(IDialogConstants.FINISH_LABEL).click();
        
        //try to add a data myData on step
        SWTBotTestUtil.selectEventOnProcess(bot, gmfEditor, "Step1");
        getDataSection(bot);
        bot.button(Messages.addData).click();
        bot.textWithLabel(Messages.name+" *").setText(dataName);
        assertFalse("finish button should be enabled",bot.button(IDialogConstants.FINISH_LABEL).isEnabled());
        bot.textWithLabel(Messages.name+" *").setText(dataName1);
        assertTrue("finish button should be enabled",bot.button(IDialogConstants.FINISH_LABEL).isEnabled());
        bot.button(IDialogConstants.FINISH_LABEL).click();
        
      
        
        //add a second task and add a data named myData1
        bot.editorByTitle(diagramTitle).show();
        bot.editorByTitle(diagramTitle).setFocus();
        SWTBotTestUtil.selectElementInContextualPaletteAndDragIt(gmfEditor, "Step1",SWTBotTestUtil.CONTEXTUALPALETTE_STEP,new Point(550,180));
        getDataSection(bot);
        bot.button(Messages.addData).click();
        bot.textWithLabel(Messages.name+" *").setText(dataName1);
        assertTrue("finish button should be enabled",bot.button(IDialogConstants.FINISH_LABEL).isEnabled());
        bot.button(IDialogConstants.FINISH_LABEL).click();
        
    }
    
    public static void getDataSection(SWTGefBot bot ){
        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_GENERAL).show();
        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_GENERAL).setFocus();
        SWTBotTestUtil.selectTabbedPropertyView(bot, "Data");
    }

}
