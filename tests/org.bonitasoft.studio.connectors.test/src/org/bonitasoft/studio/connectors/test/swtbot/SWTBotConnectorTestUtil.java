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

import junit.framework.Assert;

import org.bonitasoft.studio.test.swtbot.util.SWTBotTestUtil;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

/**
 * @author Aur�lie Zara
 * 
 */
/**
 * @author aurelie
 *
 */
/**
 * @author aurelie
 *
 */
public class SWTBotConnectorTestUtil {

    /**
     * use it to access to the wizard "New definition..." (menu
     * DEvelopement>Connectors>New definition...)
     * 
     * @param bot
     */
    public static void activateConnectorDefinitionShell(SWTBot bot) {
        bot.waitUntil(Conditions.widgetIsEnabled(bot.menu("Development")),10000);
        bot.menu("Development").menu("Connectors").menu("New definition...")
        .click();
        bot.waitUntil(Conditions.shellIsActive("New definition..."),10000);
    }

    /**
     * use it to access to the wizard "New implementation..." (menu
     * DEvelopement>Connectors>Edit definition)
     * 
     * @param bot
     */
    public static void activateConnectorImplementationShell(SWTBot bot) {
        bot.waitUntil(Conditions.widgetIsEnabled(bot.menu("Development")),10000);
        bot.menu("Development").menu("Connectors")
        .menu("New implementation...").click();
        bot.waitUntil(Conditions.shellIsActive("New connector implementation"),10000);
    }

    /**
     * use it to access to the wizard "Edit definition" (menu
     * DEvelopement>Connectors>Edit definition)
     * 
     * @param bot
     */
    public static void activateConnectorDefEditionShell(SWTBot bot) {
        bot.waitUntil(Conditions.widgetIsEnabled(bot.menu("Development")),10000);
        bot.menu("Development").menu("Connectors").menu("Edit definition...")
        .click();
        bot.waitUntil(Conditions.shellIsActive("Select a connector definition"),10000);
    }
    
    /**
     * use it to access to the wizard "Test connector" (menu
     * DEvelopement>Connectors>Test connector...)
     * 
     * @param bot
     */
    public static void activateConnectorTestWizard(SWTBot bot) {
        bot.waitUntil(Conditions.widgetIsEnabled(bot.menu("Development")),10000);
        bot.menu("Development").menu("Connectors").menu("Test connector...")
        .click();
        bot.waitUntil(Conditions.shellIsActive("Test connector"),10000);
    }

    /**
     * use it when the wizard "New  definition" is active. (menu
     * development>connectors>New definition...)
     * 
     * @param bot
     * @param id
     * @param version
     */
    public static void createConnectorDefinition(final SWTBot bot, final String id,
            final String version) {
        bot.textWithLabel("Definition id *").setText(id);
		bot.waitUntil(new ICondition() {
			
			public boolean test() throws Exception {
				return id.equals(bot.textWithLabel("Definition id *").getText());
			}
			
			public void init(SWTBot bot) {
				
			}
			
			public String getFailureMessage() {
				return null;
			}
		},10000);
        bot.textWithLabel("Version *").setText(version);
	bot.waitUntil(new ICondition() {
			
			public boolean test() throws Exception {
				return version.equals(bot.textWithLabel("Version *").getText());
			}
			
			public void init(SWTBot bot) {
				
			}
			
			public String getFailureMessage() {
				return null;
			}
		},10000);
    }

    /**
     * use it when the wizard "New  definition" is active. (menu
     * development>connectors>New definition...)
     * 
     * @param bot
     * @param categoryId
     * @throws Exception
     */
    public static void createNewCategory(SWTBot bot, String categoryId) {
        bot.waitUntil(Conditions.widgetIsEnabled((bot.button("New..."))),10000);
        bot.button("New...").click();
        bot.waitUntil(Conditions.widgetIsEnabled(bot.activeShell()));
        Assert.assertFalse("ok button should be desabled",
                bot.button(IDialogConstants.OK_LABEL).isEnabled());
        bot.textWithLabel("Id").setText(categoryId);
        bot.textWithLabel("Display name").setText(categoryId);
        bot.button(IDialogConstants.OK_LABEL).click();
    }

    /**
     * use it to access to the wizard "Import connector" (menu
     * Developement>Connectors>Import)
     * 
     * @param bot
     */
    public static void activateConnectorImportShell(SWTBot bot) {
        bot.waitUntil(Conditions.widgetIsEnabled(bot.menu("Development")),10000);
        bot.menu("Development").menu("Connectors").menu("Import connector...").click();
        bot.waitUntil(Conditions.shellIsActive("Import connector archive"),10000);
    }
    
    
    /**
     * use it to access to the wizard "Export connector" (menu Development<Connector>Export)
     */
    public static void activateExportConnectorShell(SWTBot bot){
    	bot.menu("Development").menu("Connectors").menu("Export connector...").click();
    	bot.waitUntil(Conditions.shellIsActive("Export connector"),10000);
    	bot.activeShell().setFocus();
    }
    
    
    /**
     * use it to create a connector def and impl (no window should be opened)
     * @param bot
     * @param id
     * @param version
     * @param className
     * @param packageName
     */
    public static void createConnectorDefAndImpl(final SWTWorkbenchBot bot, String id,String version,String className,String packageName){
    	final int nbEditorsBefore = bot.editors().size();
    	activateConnectorDefinitionShell(bot);
    	createConnectorDefinition(bot, id, version);
    	bot.button(IDialogConstants.FINISH_LABEL).click();
    	activateConnectorImplementationShell(bot);
    	 bot.textWithLabel("Implementation id *").setText(id);
         bot.comboBoxWithLabel("Definition *").setSelection(id+" ("+version+")");
         bot.textWithLabel("Class name *").setText(className);
         bot.textWithLabel("Package *").setText(packageName);
         bot.button(IDialogConstants.FINISH_LABEL).click();
         bot.waitUntil(new ICondition() {

             public boolean test() throws Exception {
                 return nbEditorsBefore +1 == bot.editors().size();
             }

             public void init(SWTBot bot) {
             }

             public String getFailureMessage() {
                 return "Editor for implementation has not been opened.";
             }
         }, 30000);
    }
    
    /**
     * connector configuration input widget should be a text field or a text area
     * 
     * @param bot
     * @param connectorDefinitionId
     * @param name
     * @param dataName
     * @param version
     */
    public static void addConnectorToPool(final SWTBot bot,final String connectorDefinitionLabel,final String version,final String categoryLabel,final String connectorName) {
        SWTBotTestUtil.selectTabbedPropertyView(bot, "Connectors");
        bot.button("Add...").click();
        Assert.assertFalse(IDialogConstants.NEXT_LABEL + " should be disabled", bot
                .button(IDialogConstants.NEXT_LABEL).isEnabled());
        Assert.assertFalse(IDialogConstants.FINISH_LABEL + " should be disabled", bot
                .button(IDialogConstants.FINISH_LABEL).isEnabled());
       SWTBotTreeItem categoryItem = bot.tree().expandNode(categoryLabel);
       String cNode = null;
       for(String node : categoryItem.getNodes()){
    	   if(node.startsWith(connectorDefinitionLabel + " (" + version + ")")){
    		 cNode =  node ;
    		 break;
    	   }
       }
       Assert.assertNotNull("Connector "+connectorDefinitionLabel + " (" + version + ") not found in category "+categoryLabel ,cNode);
       final String nodeToSelect = cNode;
       bot.waitUntil(new ICondition() {

			public boolean test() throws Exception {
				bot.tree().select(categoryLabel).expandNode(categoryLabel).select(nodeToSelect);
				String selection = bot.tree().selection().get(0,0);
				return selection != null &&  selection.startsWith(connectorDefinitionLabel);
			}

			public void init(SWTBot bot) {

			}

			public String getFailureMessage() {
				return "Cannot select tree item";
			}
		},10000,1000);
        Assert.assertTrue(IDialogConstants.NEXT_LABEL + " should be enabled", bot
                .button(IDialogConstants.NEXT_LABEL).isEnabled());
        Assert.assertFalse(IDialogConstants.FINISH_LABEL + " should be disabled", bot
                .button(IDialogConstants.FINISH_LABEL).isEnabled());
        bot.button(IDialogConstants.NEXT_LABEL).click();
        Assert.assertFalse(IDialogConstants.FINISH_LABEL + " should be disabled", bot
                .button(IDialogConstants.FINISH_LABEL).isEnabled());
        bot.textWithLabel("Name *").setText(connectorName);
        bot.waitUntil(Conditions.widgetIsEnabled(bot.button(IDialogConstants.NEXT_LABEL)),5000);
    }
    
}
