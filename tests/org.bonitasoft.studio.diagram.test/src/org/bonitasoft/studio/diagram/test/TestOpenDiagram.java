package org.bonitasoft.studio.diagram.test;

import static org.bonitasoft.studio.diagram.custom.i18n.Messages.confirmProcessDeleteTitle;
import static org.bonitasoft.studio.diagram.custom.i18n.Messages.openProcessWizardPage_title;
import static org.bonitasoft.studio.diagram.custom.i18n.Messages.removeProcessLabel;
import static org.bonitasoft.studio.properties.i18n.Messages.activityType_serviceTask;
import static org.bonitasoft.studio.properties.i18n.Messages.activityType;

import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.test.swtbot.util.SWTBotTestUtil;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.eclipse.gef.finder.SWTBotGefTestCase;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditor;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotButton;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;


public class TestOpenDiagram extends SWTBotGefTestCase {


    // Before and After
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
    @After
    public void tearDown(){
        bot.saveAllEditors();
    }


    @Test
    public void testDeleteDiagramWhenEditorIsDirty(){

    	// editor
    	SWTBotTestUtil.createNewDiagram(bot);
    	SWTBotTestUtil.changeDiagramName(bot, "Step1", "OpenDiagramDelete1");

    	// set editor dirty
    	SWTBotEditor botEditor = bot.activeEditor();
    	SWTBotGefEditor gmfEditor = bot.gefEditor(botEditor.getTitle());

    	gmfEditor.getEditPart("Step1").click();
    	SWTBotView generalView = bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_GENERAL);

    	generalView.show();
    	generalView.setFocus();

    	SWTBotTestUtil.selectTabbedPropertyView(bot, "General");
    	bot.comboBoxWithLabel(activityType).setSelection(activityType_serviceTask);
    	
    	
    	bot.toolbarButton(Messages.OpenProcessButtonLabel).click();
    	bot.waitUntil(Conditions.shellIsActive(openProcessWizardPage_title));
    	
    	
    	SWTBotTree tree = bot.tree();
    	SWTBotButton cancelButton = bot.button(IDialogConstants.CANCEL_LABEL);

    	
    	Assert.assertTrue("Error: no item in the table of Open Diagram Shell", tree.hasItems());
    	int nbItems = tree.rowCount();

    	String diagramName = "OpenDiagramDelete1"+" (1.0)";
    	try{
    		SWTBotTreeItem item = tree.getTreeItem(diagramName);
    		item.select();
    		
    	}catch(WidgetNotFoundException e){
        	Assert.assertTrue("Error: diagram OpenDiagramDelete1 not found in the table of Open Diagram Shell", false);
    	}
    	
    	
    	bot.button(removeProcessLabel).click();
    	
    	bot.waitUntil(Conditions.shellIsActive(confirmProcessDeleteTitle));
    	bot.button(IDialogConstants.YES_LABEL).click();
    	
    	bot.waitUntil(Conditions.shellIsActive(openProcessWizardPage_title));
    	Assert.assertEquals("Error", nbItems-1, tree.rowCount());
    	
    	cancelButton.click();
    	
    	
    	for(SWTBotEditor editor : bot.editors()){
    		Assert.assertFalse("Error: Editor "+diagramName+" should not be in the tree.",editor.getTitle().equals(diagramName));
    	}
    	
    }


}
