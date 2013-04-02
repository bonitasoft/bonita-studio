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
package org.bonitasoft.studio.actors.tests.SWTbot;

import org.bonitasoft.studio.actors.ActorsPlugin;
import org.bonitasoft.studio.actors.repository.ActorFilterDefRepositoryStore;
import org.bonitasoft.studio.common.Messages;
import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.connector.model.definition.Category;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.connector.model.definition.wizard.ConnectorDefinitionTreeLabelProvider;
import org.bonitasoft.studio.connector.model.i18n.DefinitionResourceProvider;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swtbot.eclipse.gef.finder.SWTBotGefTestCase;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Aurelie Zara
 * 
 */

@RunWith(SWTBotJunit4ClassRunner.class)
public class ActorFilterEditionTest extends SWTBotGefTestCase {

    public void createActorFilterDefinition(String id, String version) {
        SWTBotActorFilterUtil.activateActorFilterDefinitionShell(bot);
        SWTBotActorFilterUtil.createActorFilterDefinition(bot, id, version);
        bot.button(IDialogConstants.FINISH_LABEL).click();
    }

    public void removeActorFilterDefinition(String name, String version) {
        SWTBotActorFilterUtil.activateActorFilterDefEditionShell(bot);
        bot.waitUntil(Conditions.widgetIsEnabled(bot
                .tree().expandNode("Uncategorized")
                .getNode(name + " (" + version + ")")),10000);
        bot.tree().expandNode("Uncategorized").select(name + " (" + version + ")");
        assertNotNull("could not find" + name + " (" + version + ")", bot
                .tree().expandNode("Uncategorized")
                .getNode(name + " (" + version + ")"));
        bot.tree().expandNode("Uncategorized")
        .select(name + " (" + version + ")");
        bot.button("Remove").click();
        if (!FileActionDialog.getDisablePopup()) {
            bot.button(IDialogConstants.YES_LABEL).click();
        }
        bot.button(IDialogConstants.CANCEL_LABEL).click();
    }

    
    @Test
    public void testIdRenameEdit() throws Exception {
        final String id = "testEdit1";
        final String version = "1.0.0";
        final String id2 = "testEdit2";
        createActorFilterDefinition(id, version);
        SWTBotActorFilterUtil.activateActorFilterDefEditionShell(bot);
        bot.tree().setFocus();
        bot.waitUntil(new ICondition() {

			public boolean test() throws Exception {
				bot.tree().select("Uncategorized").expandNode("Uncategorized").select(id + " (" + version + ")");
				return bot.tree().selectionCount() > 0;
			}

			public void init(SWTBot bot) {

			}

			public String getFailureMessage() {
				return "Cannot select tree item";
			}
		},10000,1000);
        
       

        bot.button(Messages.edit).click();
        assertEquals(bot.textWithLabel("Definition id *").getText(), id);
        assertEquals(bot.textWithLabel("Version *").getText(), version);
        bot.textWithLabel("Definition id *").setText(id2);
        bot.waitUntil(new ICondition(){

			@Override
			public boolean test() throws Exception {
				// TODO Auto-generated method stub
				return id2.equals(bot.textWithLabel("Definition id *").getText());
			}

			@Override
			public void init(SWTBot bot) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public String getFailureMessage() {
				// TODO Auto-generated method stub
				return null;
			}
        	
        },10000);
        bot.button(IDialogConstants.FINISH_LABEL).click();
        ActorFilterDefRepositoryStore store = (ActorFilterDefRepositoryStore) RepositoryManager
                .getInstance().getRepositoryStore(
                        ActorFilterDefRepositoryStore.class);
        ConnectorDefinition connectorDef = store.getDefinition(id, version);
        ConnectorDefinition connectorDef2 = store.getDefinition(id2, version);
        assertNull("the actorDef with previous id shouldn't exist anymore",
                connectorDef);
        assertNotNull("the actorDef with new id does not exist", connectorDef2);
        removeActorFilterDefinition(id2, version);
    }

    @Test
    public void testVersionEdit() throws Exception {
        final String id = "testEdit3";
        final String version = "1.0.0";
        final String version2 = "1.0.1";
        createActorFilterDefinition(id, version);
        SWTBotActorFilterUtil.activateActorFilterDefEditionShell(bot);
        bot.tree().setFocus();
        bot.waitUntil(new ICondition() {

			public boolean test() throws Exception {
				bot.tree().select("Uncategorized").expandNode("Uncategorized").select(id + " (" + version + ")");
				return bot.tree().selectionCount() > 0;
			}

			public void init(SWTBot bot) {

			}

			public String getFailureMessage() {
				return "Cannot select tree item";
			}
		},10000,1000);
       
     
        bot.button(Messages.edit).click();
        bot.textWithLabel("Version *").setText(version2);
        bot.button(IDialogConstants.FINISH_LABEL).click();
        ActorFilterDefRepositoryStore store = (ActorFilterDefRepositoryStore) RepositoryManager
                .getInstance().getRepositoryStore(
                        ActorFilterDefRepositoryStore.class);
        ConnectorDefinition connectorDef = store.getDefinition(id, version);
        ConnectorDefinition connectorDef2 = store.getDefinition(id, version2);
        assertNull("the   previous ActorDef version  shouldn't exist anymore",
                connectorDef);
        assertNotNull("the new actorDef version does not exist", connectorDef2);
        removeActorFilterDefinition(id, version2);
    }

    @Test
    public void testAddCategory() throws Exception {
        final String id = "testEdit4";
        final String version = "1.0.0";
        createActorFilterDefinition(id, version);
        SWTBotActorFilterUtil.activateActorFilterDefEditionShell(bot);
        bot.waitUntil(Conditions.widgetIsEnabled(bot.tree().expandNode("Uncategorized")
        .select(id + " (" + version + ")")),10000);
        assertTrue(id+" does not exist in tree viewer", bot.tree().expandNode("Uncategorized")
        .select(id + " (" + version + ")").isEnabled());
        bot.tree().select("Uncategorized").expandNode("Uncategorized")
        .select(id + " (" + version + ")");
        bot.button(Messages.edit).click();
        bot.button("Add...").click();
        SWTBotActorFilterUtil.addCategory(bot);
        bot.button(IDialogConstants.FINISH_LABEL).click();
        ActorFilterDefRepositoryStore store = (ActorFilterDefRepositoryStore) RepositoryManager
                .getInstance().getRepositoryStore(
                        ActorFilterDefRepositoryStore.class);
        ConnectorDefinition connectorDef = store.getDefinition(id, version);
        assertEquals("category size should be equal to 1", connectorDef
                .getCategory().size(), 1);
        DefinitionResourceProvider messageProvider = DefinitionResourceProvider
                .getInstance(store, ActorsPlugin.getDefault().getBundle());
        SWTBotActorFilterUtil.activateActorFilterDefEditionShell(bot);
        final Category category = connectorDef.getCategory().get(0);
        String categoryLabel = messageProvider.getCategoryLabel(category);
        if(categoryLabel == null){
            categoryLabel = category.getId();
        }
        final String connectorLabel =  new ConnectorDefinitionTreeLabelProvider(messageProvider).getText(connectorDef);

        assertNotNull(
                "could not find " + connectorLabel,
                bot.tree().getTreeItem(categoryLabel).expand().getNode(connectorLabel));
        bot.button(IDialogConstants.CANCEL_LABEL).click();
    }

}