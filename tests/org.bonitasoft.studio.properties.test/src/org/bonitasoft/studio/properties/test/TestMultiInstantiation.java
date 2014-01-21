/**
 * Copyright (C) 2011 BonitaSoft S.A.
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
package org.bonitasoft.studio.properties.test;

import static org.bonitasoft.studio.data.i18n.Messages.addData;
import static org.bonitasoft.studio.data.i18n.Messages.classLabel;
import static org.bonitasoft.studio.data.i18n.Messages.datatypeLabel;
import static org.bonitasoft.studio.data.i18n.Messages.defaultValueLabel;
import static org.bonitasoft.studio.data.i18n.Messages.deleteDataDialogTitle;
import static org.bonitasoft.studio.data.i18n.Messages.name;
import static org.bonitasoft.studio.data.i18n.Messages.newVariable;
import static org.bonitasoft.studio.form.properties.i18n.Messages.Action_InitialValue;
import static org.bonitasoft.studio.form.properties.i18n.Messages.formFieldType;
import static org.bonitasoft.studio.properties.i18n.Messages.addFormTitle;
import junit.framework.Assert;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.Task;
import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.bonitasoft.studio.properties.i18n.Messages;
import org.bonitasoft.studio.test.swtbot.util.SWTBotTestUtil;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.eclipse.gef.finder.SWTBotGefTestCase;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditor;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTable;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


/**
 * @author Florine Boudin
 *
 */
public class TestMultiInstantiation extends SWTBotGefTestCase implements SWTBotConstants {

    private static final String DATA_NAME_LABEL = name +" *";
	private static final String PAGEFLOW_LABEL = "Pageflow";
	private static boolean disablePopup;

    @BeforeClass
    public static void setUpBeforeClass() {
        disablePopup = FileActionDialog.getDisablePopup();
        FileActionDialog.setDisablePopup(true);
    }


    @AfterClass
    public static void tearDownAfterClass() {
        FileActionDialog.setDisablePopup(disablePopup);
    }
    
   

    @Override
    @Before
    public void setUp() {
    	 BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore().setValue(BonitaPreferenceConstants.ASK_RENAME_ON_FIRST_SAVE, false);
    }
    
    @Override
    @After
    public void tearDown() {
        bot.saveAllEditors();
    }

    final String defaultEmployeeActor = "Employee actor";
    final String defaultEmployeeActorDefinition = org.bonitasoft.studio.diagram.custom.Messages.initiatorDescription;


    final String loopMultiInstanceSection = "Iteration";

    /** Test the Cardinality case of multiInstance Task
     * @throws ExecutionException
     * 
     */
    @Test
    public void testMultiInstanceCardinality() throws ExecutionException  {

        // create Diagram
        SWTBotTestUtil.createNewDiagram(bot);
        SWTBotEditor botEditor = bot.activeEditor();
        SWTBotGefEditor gmfEditor = bot.gefEditor(botEditor.getTitle());

        AbstractProcess proc = ModelHelper.getParentProcess(((IGraphicalEditPart)gmfEditor.getEditPart("Step1").part()).resolveSemanticElement());
        gmfEditor.getEditPart(proc.getName()).click();

        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_GENERAL).show();

        // DATA
        addIntegerData("nbTicketsAvailable", "20");


        SWTBotTestUtil.selectInitiatorinActor(bot, defaultEmployeeActor);

        gmfEditor.getEditPart("Step1").click();

        SWTBotView propertyBot =  bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_GENERAL);
        propertyBot.show();
        propertyBot.setFocus();


        SWTBotTestUtil.selectActorInHumanTask(bot, defaultEmployeeActor+" -- "+defaultEmployeeActorDefinition);



        // MULTI-INSTANCIATION PROPERTIES
        SWTBotTestUtil.selectTabbedPropertyView(bot, loopMultiInstanceSection);

        // select the multi-instantiate radio button
        bot.radio(Messages.isMultiInstantiated).click();

        // set the cardinality
        bot.radio(Messages.multiInstance_useCardinality).click();
        bot.text(1).setText("15");

        // set completion condition
        bot.toolbarButtonWithId(ExpressionViewer.SWTBOT_ID_EDITBUTTON, 1).click();

        setScriptBooleanExpression(  "isThereTickets",  "nbTicketsAvailable==0");

        // Set local variable of the number of tickets asked
        // MULTI-INSTANCIATION PROPERTIES

        addIntegerData("nbTickets", "0");

        // FILL THE FORM
        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_APPLICATION).show();
        SWTBotTestUtil.selectTabbedPropertyView(bot, PAGEFLOW_LABEL);

        // Add a new Form
        bot.button("Add...").click();
        bot.waitUntil(Conditions.shellIsActive(addFormTitle));
        bot.button(IDialogConstants.FINISH_LABEL).click();


        gmfEditor = bot.gefEditor("Step1");

        // configure the nbTicketsAvailable widget to a Message field
        setWidgetProperties(gmfEditor,  "Nb Tickets Available",  null, "Message", null,0,  null,null);
        bot.toolbarButtonWithId(ExpressionViewer.SWTBOT_ID_EDITBUTTON, 0).click();
        SWTBotTestUtil.setScriptExpression( bot, "nbTicketsAvailable",  "\"Only \"+nbTicketsAvailable+\" tickets available.\"",  "java.lang.String" );


        // configure the nbTickets widget to a text field
        setWidgetProperties(gmfEditor,  "Nb Tickets",  "Nbr de Tickets à reserver", "Text field", "0",  1, "nbTickets","java.lang.Integer");
        bot.toolbarButtonWithId(ExpressionViewer.SWTBOT_ID_EDITBUTTON, 1).click();
        SWTBotTestUtil.setScriptExpression( bot, "nbTickets",  "Integer.valueOf(field_nbTickets1)",  "java.lang.Integer" );

        // Save the form
        bot.saveAllEditors();
        IStatus status = SWTBotTestUtil.selectAndRunFirstPoolFound(bot);
        assertTrue(status.getMessage(),status.isOK());
    }

    /** Test the Collection case of multiInstance Task
     * 
     */
    @Test
    public void testMultiInstanceCollection()  {

        // create Diagram
        SWTBotTestUtil.createNewDiagram(bot);
        SWTBotEditor botEditor = bot.activeEditor();
        SWTBotGefEditor gmfEditor = bot.gefEditor(botEditor.getTitle());

        // get the pool
        AbstractProcess proc = ModelHelper.getParentProcess(((IGraphicalEditPart)gmfEditor.getEditPart("Step1").part()).resolveSemanticElement());
        gmfEditor.getEditPart(proc.getName()).click();

        // set the actor
        SWTBotTestUtil.selectInitiatorinActor(bot, defaultEmployeeActor);


        // Add a new Data java.util.List type to add a user list
        addIntegerData("nbTicketsAvailable", "20");

        addJavaObjectData("vip", null, "java.util.List");
        bot.toolbarButtonWithId(ExpressionViewer.SWTBOT_ID_EDITBUTTON, 0).click();
        SWTBotTestUtil.setScriptExpression( bot,  "vipScript",  "[\"Armelle\",\"Ben\",\"Cedric\",\"Damien\"]",  "java.util.List" );
        bot.waitUntil(Conditions.shellIsActive(newVariable));
        bot.button(IDialogConstants.FINISH_LABEL).click();

        addJavaObjectData("alreadyVip", null, "java.util.List");
        bot.toolbarButtonWithId(ExpressionViewer.SWTBOT_ID_EDITBUTTON, 0).click();
        SWTBotTestUtil.setScriptExpression( bot,  "alreadyVipScript",  "[null]",  "java.util.List" );
        bot.waitUntil(Conditions.shellIsActive(newVariable));
        bot.button(IDialogConstants.FINISH_LABEL).click();

        bot.menu("Diagram").menu("Save").click();


        // Add MultiInstance on The human Task
        gmfEditor.getEditPart("Step1").click();

        SWTBotView propertyBot =  bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_GENERAL);
        propertyBot.show();
        propertyBot.setFocus();


        // Add a Integer Data in the Human Task
        addIntegerData("nbTickets", "0");

        // Add a Text Data in the Human Task
        addTextData("vipName", "");

        // Set properties of Multi-Instance
        propertyBot.show();
        propertyBot.setFocus();


        SWTBotTestUtil.selectActorInHumanTask(bot, defaultEmployeeActor+" -- "+defaultEmployeeActorDefinition);

        bot.menu("Diagram").menu("Save").click();
        SWTBotTestUtil.selectTabbedPropertyView(bot, loopMultiInstanceSection);

        // select the multi-instantiate radio buton
        bot.radio(Messages.isMultiInstantiated).click();

        bot.radio(Messages.multiInstance_useCollection).click();

        Assert.assertTrue(bot.comboBox(0).isEnabled());
        bot.comboBox(0).setSelection("vip -- java.util.List");
        bot.sleep(500);
        // set Input Data
        bot.comboBoxWithLabel(Messages.multiInstance_inputData).setSelection("vipName -- Text");
        // set ouput data
        bot.comboBoxWithLabel(Messages.multiInstance_outputData).setSelection("vipName -- Text");
        // set output results
        bot.comboBoxWithLabel(Messages.multiInstance_listOutputDataLabel).setSelection("alreadyVip -- java.util.List");

        // Edit the Completion condition

        // check the button to edit completion condition exists
        Assert.assertTrue(bot.toolbarButtonWithId(ExpressionViewer.SWTBOT_ID_EDITBUTTON, 1).isEnabled());
        bot.toolbarButtonWithId(ExpressionViewer.SWTBOT_ID_EDITBUTTON, 1).click();

        setScriptBooleanExpression("isOK", "(vip.isEmpty())||(nbTicketsAvailable==0)");

        // Add operation to update the number of available tickets
        SWTBotTestUtil.selectTabbedPropertyView(bot, "Operations");
        bot.button("Add").click();

     
        SWTBotTestUtil.setOutputStorageExpressionByName(bot,"vip","java.util.List",0);
        bot.toolbarButtonWithId(ExpressionViewer.SWTBOT_ID_EDITBUTTON, 0).click();
        String expressionScript = "List vipList = new ArrayList(vip)\nvipList.remove(vipName)\nreturn vipList";
        SWTBotTestUtil.setScriptExpression( bot,"removeUser", expressionScript, "java.util.List");

        bot.menu("Diagram").menu("Save").click();
        bot.menu("Diagram").menu("Close").click();


    }

    /** Test the erase possibility in comboBox in Collection case of MultiInstance
     * 
     */
    @Test
    public void testMultiInstanceEraseButton()  {


        // create Diagram
        SWTBotTestUtil.createNewDiagram(bot);
        SWTBotEditor botEditor = bot.activeEditor();
        SWTBotGefEditor gmfEditor = bot.gefEditor(botEditor.getTitle());

        // get the pool
        AbstractProcess proc = ModelHelper.getParentProcess(((IGraphicalEditPart)gmfEditor.getEditPart("Step1").part()).resolveSemanticElement());
        gmfEditor.getEditPart(proc.getName()).click();

        addJavaObjectData("vip", null, "java.util.List");
        bot.toolbarButtonWithId(ExpressionViewer.SWTBOT_ID_EDITBUTTON, 0).click();
        SWTBotTestUtil.setScriptExpression( bot,  "vipScript",  "[\"Armelle\",\"Ben\",\"Cedric\",\"Damien\"]",  "java.util.List" );
        bot.waitUntil(Conditions.shellIsActive(newVariable));
        bot.button(IDialogConstants.FINISH_LABEL).click();

        bot.menu("Diagram").menu("Save").click();


        // Add MultiInstance on The human Task
        gmfEditor.getEditPart("Step1").click();

        SWTBotView propertyBot =  bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_GENERAL);
        propertyBot.show();
        propertyBot.setFocus();

        // Add a Text Data in the Human Task
        addTextData("vipName", "");

        // Set properties of Multi-Instance
        propertyBot.show();
        propertyBot.setFocus();

        bot.menu("Diagram").menu("Save").click();
        SWTBotTestUtil.selectTabbedPropertyView(bot, loopMultiInstanceSection);
        bot.sleep(2000);
        // select the multi-instantiate radio buton
        bot.radio(Messages.isMultiInstantiated).click();

        bot.radio(Messages.multiInstance_useCollection).click();

        // set collection
        Assert.assertTrue(bot.comboBox(0).isEnabled());
        bot.comboBox(0).setSelection("vip -- java.util.List");
        // set Input Data
        bot.comboBoxWithLabel(Messages.multiInstance_inputData).setSelection("vipName -- Text");
        // set ouput data
        bot.comboBoxWithLabel(Messages.multiInstance_outputData).setSelection("vipName -- Text");
        // set output results
        bot.comboBoxWithLabel(Messages.multiInstance_listOutputDataLabel).setSelection("vip -- java.util.List");

        // erase collection
        Assert.assertTrue(bot.toolbarButtonWithTooltip(Messages.clearSelection, 0).isEnabled());
        bot.toolbarButtonWithTooltip(Messages.clearSelection, 0).click();
        Assert.assertTrue("Error: Collection is not erased !", bot.comboBox(0).getText().isEmpty());


        // erase input data
        Assert.assertTrue(bot.toolbarButtonWithTooltip(Messages.clearSelection, 1).isEnabled());
        bot.toolbarButtonWithTooltip(Messages.clearSelection, 1).click();
        Assert.assertTrue("Error: Input Data is not erased !",bot.comboBox(1).getText().isEmpty());

        // erase ouput Data
        Assert.assertTrue(bot.toolbarButtonWithTooltip(Messages.clearSelection, 2).isEnabled());
        bot.toolbarButtonWithTooltip(Messages.clearSelection, 2).click();
        Assert.assertTrue("Error: Output Data is not erased !",bot.comboBox(2).getText().isEmpty());

        // erase output Collection
        Assert.assertTrue(bot.toolbarButtonWithTooltip(Messages.clearSelection, 3).isEnabled());
        bot.toolbarButtonWithTooltip(Messages.clearSelection, 3).click();
        Assert.assertTrue("Error: Output Collection is not erased !",bot.comboBox(3).getText().isEmpty());


        bot.menu("Diagram").menu("Save").click();
        bot.menu("Diagram").menu("Close").click();
    }

    /** Test the erase possibility in comboBox in Collection case of MultiInstance
     * 
     */
    @Test
    public void testMultiInstanceUpdateComboBoxAfterDataRemoved()  {


        // create Diagram
        SWTBotTestUtil.createNewDiagram(bot);
        SWTBotEditor botEditor = bot.activeEditor();
        SWTBotGefEditor gmfEditor = bot.gefEditor(botEditor.getTitle());

        // get the pool
        final SWTBotGefEditPart editPart = gmfEditor.getEditPart("Step1");
        AbstractProcess proc = ModelHelper.getParentProcess(((IGraphicalEditPart)editPart.part()).resolveSemanticElement());
        gmfEditor.getEditPart(proc.getName()).click();

        addJavaObjectData("vip", null, "java.util.List");
        bot.toolbarButtonWithId(ExpressionViewer.SWTBOT_ID_EDITBUTTON, 0).click();
        SWTBotTestUtil.setScriptExpression( bot,  "vipScript",  "[\"Armelle\",\"Ben\",\"Cedric\",\"Damien\"]",  "java.util.List" );
        bot.waitUntil(Conditions.shellIsActive(newVariable));
        bot.button(IDialogConstants.FINISH_LABEL).click();

        addJavaObjectData("vip2", null, "java.util.List");
        bot.toolbarButtonWithId(ExpressionViewer.SWTBOT_ID_EDITBUTTON, 0).click();
        SWTBotTestUtil.setScriptExpression( bot,  "vip2Script",  "[\"A\",\"B\",\"C\",\"D\"]",  "java.util.List" );
        bot.waitUntil(Conditions.shellIsActive(newVariable));
        bot.button(IDialogConstants.FINISH_LABEL).click();


        bot.menu("Diagram").menu("Save").click();


        // Add MultiInstance on The human Task
        editPart.click();
        IGraphicalEditPart ep = (IGraphicalEditPart) editPart.part();
        Task task = (Task) ep.resolveSemanticElement();

        SWTBotView propertyBot =  bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_GENERAL);
        propertyBot.show();
        propertyBot.setFocus();

        // Add a Text Data in the Human Task
        addTextData("vipName", "");

        // Add a Text Data in the Human Task
        addIntegerData("nbTickets", "0");

        bot.menu("Diagram").menu("Save").click();

        // Set properties of Multi-Instance
        propertyBot.show();
        propertyBot.setFocus();

        SWTBotTestUtil.selectTabbedPropertyView(bot, loopMultiInstanceSection);
        bot.sleep(2000);
        // select the multi-instantiate radio buton
        bot.radio(Messages.isMultiInstantiated).click();

        bot.radio(Messages.multiInstance_useCollection).click();

        // set collection
        Assert.assertTrue(bot.comboBox(0).isEnabled());
        bot.comboBox(0).setSelection("vip -- java.util.List");
        // set Input Data
        bot.comboBoxWithLabel(Messages.multiInstance_inputData).setSelection("vipName -- Text");
        // set ouput data
        bot.comboBoxWithLabel(Messages.multiInstance_outputData).setSelection("vipName -- Text");
        // set output results
        bot.comboBoxWithLabel(Messages.multiInstance_listOutputDataLabel).setSelection("vip -- java.util.List");


        // Check references of input collection, input data, outputdata and output result exist
        assertNotNull("Error: Input Collection used in the MultiInstantiation is not referenced in the Model.", task.getMultiInstantiation().getCollectionDataToMultiInstantiate());
        assertNotNull("Error: Input Data used in the MultiInstantiation is not referenced in the Model.", task.getMultiInstantiation().getInputData());
        assertNotNull("Error: Output Data used in the MultiInstantiation is not referenced in the Model.", task.getMultiInstantiation().getOutputData());
        assertNotNull("Error: Output Result used in the MultiInstantiation is not referenced in the Model.", task.getMultiInstantiation().getListDataContainingOutputResults());

        // remove vip collection and vipName Text
        gmfEditor.getEditPart(proc.getName()).click();
        bot.sleep(2000);
        propertyBot.show();
        propertyBot.setFocus();
        SWTBotTestUtil.selectTabbedPropertyView(bot, "Data");
        SWTBotTable table = bot.table();
        table.select("vip -- java.util.List -- Default value: vipScript");
        bot.button("Remove").click();
        bot.waitUntil(Conditions.shellIsActive(deleteDataDialogTitle));
        bot.button(IDialogConstants.OK_LABEL).click();



        // Check empty comboBox in MultiInstance
        editPart.click();
        bot.sleep(2000);
        propertyBot.show();
        propertyBot.setFocus();
        SWTBotTestUtil.selectTabbedPropertyView(bot, "Data");
        table = bot.table();
        table.select("vipName -- Text");
        bot.button("Remove").click();
        bot.waitUntil(Conditions.shellIsActive(deleteDataDialogTitle));
        bot.button(IDialogConstants.OK_LABEL).click();


        assertNull("Error: Input Collection is still referenced in the MultiInstantiation Model after being removed.", task.getMultiInstantiation().getCollectionDataToMultiInstantiate());
        assertNull("Error: Input Data is still referenced in the MultiInstantiation Model after being removed.", task.getMultiInstantiation().getInputData());
        assertNull("Error: Output Data is still referenced in the MultiInstantiation Model after being removed.", task.getMultiInstantiation().getOutputData());
        assertNull("Error: Output Result is still referenced in the MultiInstantiation Model after being removed.", task.getMultiInstantiation().getListDataContainingOutputResults());

        SWTBotTestUtil.selectTabbedPropertyView(bot, loopMultiInstanceSection);
        bot.sleep(2000);
        // check Collection empty
        Assert.assertTrue("Error: Collection is not empty !", bot.comboBox(0).getText().isEmpty());

        // check input data empty
        Assert.assertTrue("Error: Input Data is not empty !",bot.comboBox(1).getText().isEmpty());

        // check ouput Data empty
        Assert.assertTrue("Error: Output Data is not empty !",bot.comboBox(2).getText().isEmpty());

        // check output Collection empty
        Assert.assertTrue("Error: Output Collection is not empty !",bot.comboBox(3).getText().isEmpty());


        bot.menu("Diagram").menu("Save").click();
        bot.menu("Diagram").menu("Close").click();
    }
    /**
     * 
     * @param gmfEditor
     * @param widgetName
     * @param widgetLabel
     * @param widgetFieldType
     * @param initValue
     * @param outputOperation
     */
    private void setWidgetProperties(SWTBotGefEditor gmfEditor, String widgetName, String widgetLabel, String widgetFieldType,
            String initValue, int outputViewerIndex,String outputOperation,String outputOperationType) {

        gmfEditor.getEditPart(widgetName).click();
        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_FORM_GENERAL).show();
        // set name and type
        SWTBotTestUtil.selectTabbedPropertyView(bot, "General");
        if(widgetLabel!=null){
            bot.text(3).setText(widgetLabel);
        }

        bot.comboBoxWithLabel(formFieldType).setSelection(widgetFieldType);
        // set and initialize data
        SWTBotTestUtil.selectTabbedPropertyView(bot, "Data");
        if(initValue!=null){
            bot.textWithLabel(Action_InitialValue).setText(initValue);
        }
        if(outputOperation!=null){
        	SWTBotTestUtil.setOutputStorageExpressionByName(bot, outputOperation,outputOperationType, outputViewerIndex);
        }
    }



    /**
     * 
     * @param scriptName
     * @param expression
     */
    public void setScriptBooleanExpression( String scriptName, String expression ){
        SWTBotTestUtil.setScriptExpression( bot, scriptName,  expression, null );
    }

    /**
     * 
     * @param dataName
     * @param defaultValue
     */
    public void addIntegerData(String dataName, String defaultValue){
        addData( dataName,  defaultValue, "Integer");
    }

    /**
     * 
     * @param dataName
     * @param defaultValue
     */
    public void addTextData(String dataName, String defaultValue){
        addData( dataName,  defaultValue, "Text");
    }

    /**
     * 
     * @param dataName
     * @param defaultValue
     * @param classType
     */
    public void addJavaObjectData(String dataName, String defaultValue, String classType){
        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_GENERAL).show();

        // DATA
        SWTBotTestUtil.selectTabbedPropertyView(bot, "Data");

        // Add a new variable
        bot.button(addData).click();
        bot.waitUntil(Conditions.shellIsActive(newVariable));
        bot.shell(newVariable);

        // set global value
        bot.textWithLabel(DATA_NAME_LABEL).setText(dataName);
        bot.comboBoxWithLabel(datatypeLabel).setSelection("Java Object");
        bot.textWithLabel(classLabel).setText(classType);
    }

    /**
     * 
     * @param dataName
     * @param defaultValue
     * @param dataType
     */
    public void addData(String dataName, String defaultValue, String dataType){
        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_GENERAL).show();

        // DATA
        SWTBotTestUtil.selectTabbedPropertyView(bot, "Data");


        // Add a new variable
        bot.button(addData).click();
        bot.waitUntil(Conditions.shellIsActive(newVariable));
        bot.shell(newVariable);

        // set global value
        bot.textWithLabel(DATA_NAME_LABEL).setText(dataName);
        bot.comboBoxWithLabel(datatypeLabel).setSelection(dataType);
        bot.textWithLabel(defaultValueLabel).setText(defaultValue);

        bot.button(IDialogConstants.FINISH_LABEL).click();
    }


}
