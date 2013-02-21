/**
 * Copyright (C) 2012 BonitaSoft S.A.
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
package org.bonitasoft.studio.connectors.test.swtbot;

import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.connectors.repository.ConnectorConfRepositoryStore;
import org.bonitasoft.studio.test.swtbot.util.SWTBotTestUtil;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swtbot.eclipse.gef.finder.SWTBotGefTestCase;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author aurelie
 *
 */
@RunWith(SWTBotJunit4ClassRunner.class)
public class TestLoadSaveConnectorConfiguration extends SWTBotGefTestCase {
	
	 @Before
	  public void disablePopup() {
	     FileActionDialog.setDisablePopup(true);
	    }


	public void createConnector(String connectorDefinitionId) {
        final String widgetId = "textWidget";
        final String pageId = "connectorDefPageId";
        SWTBotConnectorTestUtil.activateConnectorDefinitionShell(bot);
        bot.textWithLabel("Definition id *").setText(connectorDefinitionId);
        bot.button(IDialogConstants.NEXT_LABEL).click();
        bot.button("Add...").click();
        bot.button(IDialogConstants.NEXT_LABEL).click();
        bot.button("Add...").click();
        bot.textWithLabel("Page id *").setText(pageId);
        bot.button("Add...").click();
        bot.textWithLabel("Widget id*").setText(widgetId);
        bot.textWithLabel("Display name").setText("text");
        bot.comboBoxWithLabel("Widget type").setSelection("Text");
        bot.comboBoxWithLabel("Input *").setSelection(0);
        bot.button(IDialogConstants.OK_LABEL).click();
        bot.button("Apply").click();
        bot.button(IDialogConstants.NEXT_LABEL).click();
        bot.button("Add...").click();
        bot.button(IDialogConstants.FINISH_LABEL).click();
    }
	
	@Test
	public void testSaveLoadConnectorConfiguration(){
		final String connectorDefId="testLoadConnectorDefinition";
		final String version = "1.0.0";
        final String name = "testLoadConnector";
        final String name2="testLoadConnector2";
        final String dataName = "dataLoadConnector";
        final String saveName="testLoadConnectorConfig";
	    createConnector(connectorDefId);
	    SWTBotTestUtil.createNewDiagram(bot);
	    bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_GENERAL).show();
	    createData(dataName);
	    SWTBotConnectorTestUtil.addConnectorToPool(bot, connectorDefId, name, dataName, version);
	    bot.table().select(
                name + " -- " + connectorDefId + " (" + version
                + ") -- ON_FINISH");
	    bot.button("Edit...").click();
	    bot.button(IDialogConstants.NEXT_LABEL).click();
	    bot.toolbarButton("Save").click();
	    bot.textWithLabel("Name *").setText(saveName);
	    bot.button(IDialogConstants.FINISH_LABEL).click();
	    bot.button(IDialogConstants.FINISH_LABEL).click();
	    //add a new connector and load previous configuration connector
	    SWTBotTestUtil.selectTabbedPropertyView(bot, "Connectors");
        bot.button("Add...").click();
        bot.tree().setFocus();
        bot.tree().expandNode("Uncategorized").select(connectorDefId + " (" + version + ")");
        bot.button(IDialogConstants.NEXT_LABEL).click();
        bot.textWithLabel("Name *").setText(name2);
        bot.button(IDialogConstants.NEXT_LABEL).click();
        bot.toolbarButton("Load").click();
        assertFalse("finish button should be disabled",bot.button(IDialogConstants.FINISH_LABEL).isEnabled());
        bot.tree().setFocus();
        bot.tree().getTreeItem(saveName).select();
 
        bot.button(IDialogConstants.FINISH_LABEL).click();
	    bot.button(IDialogConstants.NEXT_LABEL).click();
	    assertEquals("text field should be completed with hello world",bot.textWithLabel("text").getText(),"hello world");
	    //remove configuration
	    bot.toolbarButton("Load").click();
	    assertFalse("remove button should be disabled",bot.button("Remove").isEnabled());
	    bot.tree().setFocus();
	    bot.tree().getTreeItem(saveName).select();
	    bot.button("Remove").click();
	    bot.button(IDialogConstants.CANCEL_LABEL).click();
	    bot.button(IDialogConstants.FINISH_LABEL).click();
	    bot.saveAllEditors();
	    ConnectorConfRepositoryStore store = (ConnectorConfRepositoryStore)RepositoryManager.getInstance().getRepositoryStore(ConnectorConfRepositoryStore.class);
	    assertEquals("no configuration should be in connector configuration repository store",store.getChildren().size(),0);
	}
	
	
	  private void createData(String dataName) {
	        SWTBotTestUtil.selectTabbedPropertyView(bot, "Data");
	        bot.button("Add...").click();
	        assertFalse(IDialogConstants.FINISH_LABEL + " should be disabled", bot
	                .button(IDialogConstants.FINISH_LABEL).isEnabled());
	        bot.textWithLabel("Name").setText(dataName);
	        assertTrue(IDialogConstants.FINISH_LABEL + " should be disabled", bot
	                .button(IDialogConstants.FINISH_LABEL).isEnabled());
	        bot.button(IDialogConstants.FINISH_LABEL).click();
	    }
	  
	
}
