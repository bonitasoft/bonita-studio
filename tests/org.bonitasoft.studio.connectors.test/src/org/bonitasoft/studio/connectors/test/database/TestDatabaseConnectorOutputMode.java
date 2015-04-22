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
package org.bonitasoft.studio.connectors.test.database;

import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.connector.model.definition.Category;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.connectors.repository.ConnectorDefRepositoryStore;
import org.bonitasoft.studio.properties.i18n.Messages;
import org.bonitasoft.studio.test.swtbot.util.SWTBotTestUtil;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.eclipse.gef.finder.SWTBotGefTestCase;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.junit.Test;
import org.junit.runner.RunWith;


/**
 * @author Romain Bioteau
 *
 */
@RunWith(SWTBotJunit4ClassRunner.class)
public class TestDatabaseConnectorOutputMode extends SWTBotGefTestCase implements SWTBotConstants {

    private static final String DATA_NAME_1 = "myData1";
	private static final String QUERY1 = "SELECT col1 from MyTable WHERE id='${"+DATA_NAME_1+"}'";
	private static final String QUERY2 = "SELECT col1,col2,col3 from MyTable";
	private static final String JDBC_DB_CONNECTOR_ID = "database-jdbc";
	private static final String DB_CATEGORY_ID = "database";
	private static final String GENERIC_DB_CATEGORY_ID = "generic";

	@Test
    public void testPatternExpressionViewer(){
		SWTBotTestUtil.createNewDiagram(bot);
		bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_DATA).show();
        SWTBotTestUtil.selectTabbedPropertyView(bot, SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_DATA_VARIABLES);
        bot.button(Messages.Add).click();
		SWTBotTestUtil.addNewData(bot, DATA_NAME_1, "Text", false, null);
		String connectorLabel = getConnectorLabel(JDBC_DB_CONNECTOR_ID);
		String connectorVersion = getConnectorVersion(JDBC_DB_CONNECTOR_ID);
		String[] dbCategoryLabel = getCategoryLabels(new String[]{DB_CATEGORY_ID,GENERIC_DB_CATEGORY_ID});
		addDBConnectorWithPatternExpression(connectorLabel, connectorVersion,dbCategoryLabel,"patternDBConnector");
		bot.styledText().setText(QUERY1);
		bot.button(IDialogConstants.NEXT_LABEL).click();
	
		checkEnalbledChoices();
    }


	private void addDBConnectorWithPatternExpression(String connectorLabel,
			String connectorVersion, String[] dbCategoryLabel,String connectorName) {
		//SWTBotConnectorTestUtil.addConnectorToPool(bot, connectorLabel,connectorVersion,dbCategoryLabel, connectorName);
		bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_EXECUTION).show();
		SWTBotTestUtil.selectTabbedPropertyView(bot, SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_EXECUTION_CONNECTORS_IN);
		bot.button("Add...").click();
		bot.text().setText(connectorLabel);
		bot.table().select(0);
		bot.button(IDialogConstants.NEXT_LABEL).click();
		bot.textWithLabel("Name *").setText(connectorName);
		bot.waitUntil(Conditions.widgetIsEnabled(bot.button(IDialogConstants.NEXT_LABEL)),5000);
		bot.button(IDialogConstants.NEXT_LABEL).click();
		bot.button(IDialogConstants.NEXT_LABEL).click();
		bot.button(IDialogConstants.NEXT_LABEL).click();
	}


	
	private void checkEnalbledChoices() {
		assertFalse(bot.checkBox(org.bonitasoft.studio.connectors.i18n.Messages.alwaysUseScriptingMode).isEnabled());
		assertTrue(bot.radio(org.bonitasoft.studio.connectors.i18n.Messages.graphicalMode).isEnabled());
		assertTrue(bot.radio(org.bonitasoft.studio.connectors.i18n.Messages.scriptMode).isEnabled());
		assertTrue(bot.radio(org.bonitasoft.studio.connectors.i18n.Messages.singleValue).isEnabled());
		assertTrue(bot.radio(org.bonitasoft.studio.connectors.i18n.Messages.oneRowNCol).isEnabled());
		assertTrue(bot.radio(org.bonitasoft.studio.connectors.i18n.Messages.nRowOneCol).isEnabled());
		assertTrue(bot.radio(org.bonitasoft.studio.connectors.i18n.Messages.nRowsNcolumns).isSelected());
		
		bot.button(IDialogConstants.NEXT_LABEL).click();
		assertEquals("tableResult",bot.textWithLabel(org.bonitasoft.studio.connectors.i18n.Messages.takeValueOf).getText());
		bot.button(IDialogConstants.BACK_LABEL).click();
		
		bot.radio(org.bonitasoft.studio.connectors.i18n.Messages.singleValue).click();
		bot.button(IDialogConstants.NEXT_LABEL).click();
		assertEquals("col1",bot.textWithLabel(org.bonitasoft.studio.connectors.i18n.Messages.takeValueOf).getText());
		bot.button(IDialogConstants.BACK_LABEL).click();
		
		bot.radio(org.bonitasoft.studio.connectors.i18n.Messages.oneRowNCol).click();
		bot.button(IDialogConstants.NEXT_LABEL).click();
		assertEquals("col1",bot.textWithLabel(org.bonitasoft.studio.connectors.i18n.Messages.takeValueOf).getText());
		bot.button(IDialogConstants.BACK_LABEL).click();
		
		
		bot.radio(org.bonitasoft.studio.connectors.i18n.Messages.nRowOneCol).click();
		bot.button(IDialogConstants.NEXT_LABEL).click();
		assertEquals("col1",bot.textWithLabel(org.bonitasoft.studio.connectors.i18n.Messages.takeValueOf).getText());
		bot.button(IDialogConstants.BACK_LABEL).click();
		
		bot.radio(org.bonitasoft.studio.connectors.i18n.Messages.scriptMode).click();
		bot.button(IDialogConstants.NEXT_LABEL).click();
		assertEquals("resultset",bot.textWithId(SWTBOT_ID_EXPRESSIONVIEWER_TEXT,1).getText());
		bot.button(IDialogConstants.BACK_LABEL).click();
		
		bot.checkBox(org.bonitasoft.studio.connectors.i18n.Messages.alwaysUseScriptingMode).click();
		bot.button(IDialogConstants.BACK_LABEL).click();
		bot.styledText().setText(QUERY2);
		bot.button(IDialogConstants.NEXT_LABEL).click();
		
		assertTrue(bot.radio(org.bonitasoft.studio.connectors.i18n.Messages.scriptMode).isSelected());
		assertTrue(bot.checkBox(org.bonitasoft.studio.connectors.i18n.Messages.alwaysUseScriptingMode).isChecked());
	
		assertFalse(bot.radio(org.bonitasoft.studio.connectors.i18n.Messages.singleValue).isEnabled());
		assertFalse(bot.radio(org.bonitasoft.studio.connectors.i18n.Messages.oneRowNCol).isEnabled());
		assertFalse(bot.radio(org.bonitasoft.studio.connectors.i18n.Messages.nRowOneCol).isEnabled());
		assertFalse(bot.radio(org.bonitasoft.studio.connectors.i18n.Messages.nRowsNcolumns).isSelected());
		
		bot.radio(org.bonitasoft.studio.connectors.i18n.Messages.graphicalMode).click();
		assertFalse(bot.radio(org.bonitasoft.studio.connectors.i18n.Messages.singleValue).isEnabled());
		assertTrue(bot.radio(org.bonitasoft.studio.connectors.i18n.Messages.oneRowNCol).isEnabled());
		assertFalse(bot.radio(org.bonitasoft.studio.connectors.i18n.Messages.nRowOneCol).isEnabled());
		assertTrue(bot.radio(org.bonitasoft.studio.connectors.i18n.Messages.nRowsNcolumns).isSelected());
		assertFalse(bot.checkBox(org.bonitasoft.studio.connectors.i18n.Messages.alwaysUseScriptingMode).isEnabled());
		assertFalse(bot.checkBox(org.bonitasoft.studio.connectors.i18n.Messages.alwaysUseScriptingMode).isChecked());
		
		bot.radio(org.bonitasoft.studio.connectors.i18n.Messages.oneRowNCol).click();
		bot.button(IDialogConstants.NEXT_LABEL).click();
		assertTrue(bot.text("col1").isVisible());
		assertTrue(bot.text("col2").isVisible());
		assertTrue(bot.text("col3").isVisible());
		bot.button(IDialogConstants.FINISH_LABEL).click();
		bot.activeEditor().save();
	}


	private String[] getCategoryLabels(String[] categoryIds) {
		String[] res = new String[categoryIds.length];
		for(int i = 0; i < categoryIds.length; i++){
			final String categoryIdToSearch = categoryIds[i];
			ConnectorDefRepositoryStore defSore = (ConnectorDefRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(ConnectorDefRepositoryStore.class);
			for(Category c : defSore.getResourceProvider().getAllCategories()){
				if(c.getId().equals(categoryIdToSearch)){
					res[i] = defSore.getResourceProvider().getCategoryLabel(c);
					continue;
				}
			}
		}
		return res;
	}

	private String getConnectorVersion(String connectorId) {
		ConnectorDefRepositoryStore defSore = (ConnectorDefRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(ConnectorDefRepositoryStore.class);
		for(ConnectorDefinition def : defSore.getDefinitions()){
			if(def.getId().equals(connectorId)){
				return def.getVersion();
			}
		}
		return null;
	}

	private String getConnectorLabel(String connectorId) {
		ConnectorDefRepositoryStore defSore = (ConnectorDefRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(ConnectorDefRepositoryStore.class);
		for(ConnectorDefinition def : defSore.getDefinitions()){
			if(def.getId().equals(connectorId)){
				return defSore.getResourceProvider().getConnectorDefinitionLabel(def);
			}
		}
		return null;
	}

}
