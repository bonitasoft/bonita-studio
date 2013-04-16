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
package org.bonitasoft.studio.connectors.test;


import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.connectors.repository.ConnectorConfRepositoryStore;
import org.bonitasoft.studio.test.swtbot.util.SWTBotTestUtil;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swtbot.eclipse.gef.finder.SWTBotGefTestCase;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotStyledText;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTable;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.bonitasoft.studio.dependencies.i18n.Messages.selectMissingJarTitle;


/**
 * @author Baptiste Mesta
 *
 */
public class TestConnectorsTest extends SWTBotGefTestCase {


    private static long timeout;


    @BeforeClass
    public static void setUpBeforeClass() {
        timeout = SWTBotPreferences.TIMEOUT;
        //more timeout because the dialog takes time to open
        SWTBotPreferences.TIMEOUT = 40000;
    }

    @AfterClass
    public static void tearDownAfterClass() {
        SWTBotPreferences.TIMEOUT = timeout;
    }

    /**
     * This test ensures that the Test connector work with groovy connector
     * @throws InterruptedException
     */
    @Test
    public void testTestGroovyConnector() throws InterruptedException {
    	SWTBotTestUtil.addDataType(bot, "voiture", "Voiture");
    	
    	String groovyScript = "import org.bonitasoft.voiture.Voiture;\nVoiture v=new Voiture();\nv.setAttr(\"\")\nreturn v;";
    	testingGroovyConnector(bot, groovyScript, "org.bonitasoft.voiture", "Voiture : Voiture  [attr: ]");
    }

	/**
	 * 
	 */
	protected void testingGroovyConnector(SWTGefBot bot, String groovyScript, String jarToSelect, String result) {
		selectGroovyScriptConnector();
    	SWTBotStyledText groovyText = bot.styledText();
    	Assert.assertNotNull("StyledText not found", groovyText);
    	groovyText.setText(groovyScript);
    	Assert.assertTrue("Test button should be enabled.", bot.button("Test").isEnabled());
    	bot.button("Test").click();

    	bot.waitUntil(Conditions.shellIsActive(selectMissingJarTitle));
    	SWTBotTable table = bot.table();
    	Assert.assertNotNull("Jar Table is not found.", table);
    	if(jarToSelect.isEmpty()){
    		table.getTableItem(0).check();
    	}else{
    		table.getTableItem(jarToSelect).check();
    	}
    	bot.button(IDialogConstants.OK_LABEL).click();

    	ConnectorConfRepositoryStore ccrs = (ConnectorConfRepositoryStore)RepositoryManager.getInstance().getRepositoryStore(ConnectorConfRepositoryStore.class);
    	if(ccrs.getFilterConfigurationsFor("GroovyConnector", "1.0").size() > 0)
		bot.button(IDialogConstants.FINISH_LABEL).click();
    	
    	bot.waitUntil(Conditions.shellIsActive("Results"), 5000);
    	
    	assertEquals("Invalid ", bot.tree().getAllItems()[0].getText(), result);
    	
    	bot.button("< Back").click();


    	bot.button(IDialogConstants.CANCEL_LABEL).click();
	}

	private void selectGroovyScriptConnector(){
		bot.menu("Development").menu("Connectors").menu("Test connector...").click();
    	bot.waitUntil(Conditions.shellIsActive("Test connector"));
    	final SWTBotTreeItem script = bot.tree().getTreeItem("Script").expand();

    	String grovyScript = null;
    	for (String child : script.getNodes()) {
    		if (child.contains("Groovy")) {
    			grovyScript = child;
    			break;
    		}
    	}
    	script.getNode(grovyScript).select();
    	Assert.assertTrue("Next button should be enabled.", bot.button("Next >").isEnabled());
    	bot.button("Next >").click();
	}
	
	
	@Test
	public void testingGroovyConnectorWithModifiedOutput(){
		final String script="\"mon script\"";
		final String script2="result+\" montre la transformation de l'output dans un connecteur\"";
		final String result = "mon script montre la transformation de l'output dans un connecteur";
		selectGroovyScriptConnector();
		SWTBotStyledText groovyText = bot.styledText();
    	Assert.assertNotNull("StyledText not found", groovyText);
    	groovyText.setText(script);
    	Assert.assertTrue("Test button should be enabled.", bot.button("Test").isEnabled());
    	Assert.assertTrue("next button should be enabled",bot.button("Next >").isEnabled());
    	bot.button("Next >").click();
    	SWTBotTestUtil.clickOnPenToEditExpression(bot, 0);
    	SWTBotTestUtil.setScriptExpression(bot, "result", script2, String.class.getName());
    	bot.button("Test").click();
    	bot.waitUntil(Conditions.shellIsActive(selectMissingJarTitle));
    	bot.button(IDialogConstants.OK_LABEL).click();
    	bot.waitUntil(Conditions.shellIsActive("Results"), 30000);
    	Assert.assertEquals(result, bot.text().getText());
    	bot.button("< Back").click();
    	bot.button(IDialogConstants.CANCEL_LABEL).click();
    	
	}

    
}

