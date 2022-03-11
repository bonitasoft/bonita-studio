/**
 * Copyright (C) 2012-2014 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.tests.actors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.bonitasoft.studio.common.Messages;
import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.provider.ConnectorDefinitionRegistry;
import org.bonitasoft.studio.common.repository.provider.ExtendedCategory;
import org.bonitasoft.studio.common.repository.provider.ExtendedConnectorDefinition;
import org.bonitasoft.studio.connector.model.definition.Category;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.connector.model.definition.wizard.ConnectorDefinitionTreeLabelProvider;
import org.bonitasoft.studio.identity.actors.repository.ActorFilterDefRepositoryStore;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.bonitasoft.studio.preferences.pages.BonitaAdvancedPreferencePage;
import org.bonitasoft.studio.swtbot.framework.conditions.SelectNodeUnder;
import org.bonitasoft.studio.swtbot.framework.rule.SWTGefBotRule;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SWTBotJunit4ClassRunner.class)
public class ActorFilterEditionTest {

    private SWTGefBot bot = new SWTGefBot();

    @Rule
    public SWTGefBotRule rule = new SWTGefBotRule(bot);

    @Before
    public void setUp() throws Exception {
        BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore()
                .setValue(BonitaAdvancedPreferencePage.HIDE_CONNECTOR_DEFINITION_CHANGE_WARNING, true);
    }

    public void createActorFilterDefinition(final String id, final String version) {
        SWTBotActorFilterUtil.activateActorFilterDefinitionShell(bot);
        SWTBotActorFilterUtil.createActorFilterDefinition(bot, id, version);
        bot.button(IDialogConstants.FINISH_LABEL).click();
    }

    public void removeActorFilterDefinition(final String name, final String version) {
        SWTBotActorFilterUtil.activateActorFilterDefEditionShell(bot);
        final SWTBotTreeItem expandedUncategorizedNode = bot.tree().expandNode("Uncategorized");
        bot.waitUntil(new SelectNodeUnder(bot, name + " (" + version + ")", UNCATEGORIZED_LABEL), 10000);
        assertNotNull("could not find" + name + " (" + version + ")",
                expandedUncategorizedNode.getNode(name + " (" + version + ")"));
        expandedUncategorizedNode.select(name + " (" + version + ")");
        bot.button("Delete").click();
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
        bot.waitUntil(new SelectNodeUnder(bot, id + " (" + version + ")", UNCATEGORIZED_LABEL), 10000, 1000);

        bot.button(Messages.edit).click();
        assertEquals(bot.textWithLabel("Definition id *").getText(), id);
        assertEquals(bot.textWithLabel("Version *").getText(), version);
        bot.textWithLabel("Definition id *").setText(id2);
        bot.waitUntil(new DefaultCondition() {

            @Override
            public boolean test() throws Exception {
                return id2.equals(bot.textWithLabel("Definition id *").getText());
            }

            @Override
            public String getFailureMessage() {
                return "Definition id was not updated with text " + id2 + ". The current value is:"
                        + bot.textWithLabel("Definition id *").getText();
            }

        }, 10000);
        bot.button(IDialogConstants.FINISH_LABEL).click();
        final ActorFilterDefRepositoryStore store = RepositoryManager
                .getInstance().getRepositoryStore(
                        ActorFilterDefRepositoryStore.class);
        final ConnectorDefinition connectorDef = store.getDefinition(id, version);
        final ConnectorDefinition connectorDef2 = store.getDefinition(id2, version);
        assertNull("the actorDef with previous id shouldn't exist anymore",
                connectorDef);
        assertNotNull("the actorDef with new id does not exist", connectorDef2);
        removeActorFilterDefinition(id2, version);
    }

    final String UNCATEGORIZED_LABEL = "Uncategorized";

    @Test
    public void testVersionEdit() throws Exception {
        final String id = "testEdit3";
        final String version = "1.0.0";
        final String version2 = "1.0.1";
        createActorFilterDefinition(id, version);
        SWTBotActorFilterUtil.activateActorFilterDefEditionShell(bot);
        bot.tree().setFocus();
        final String subNodeLabel = id + " (" + version + ")";
        bot.waitUntil(new SelectNodeUnder(bot, subNodeLabel, UNCATEGORIZED_LABEL), 10000, 1000);

        bot.button(Messages.edit).click();
        bot.textWithLabel("Version *").setText(version2);
        bot.button(IDialogConstants.FINISH_LABEL).click();
        final ActorFilterDefRepositoryStore store = RepositoryManager
                .getInstance().getRepositoryStore(
                        ActorFilterDefRepositoryStore.class);
        final ConnectorDefinition connectorDef = store.getDefinition(id, version);
        final ConnectorDefinition connectorDef2 = store.getDefinition(id, version2);
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
        bot.waitUntil(new SelectNodeUnder(bot, id + " (" + version + ")", UNCATEGORIZED_LABEL), 10000);
        assertTrue(id + " does not exist in tree viewer",
                bot.tree().expandNode("Uncategorized").select(id + " (" + version + ")").isEnabled());
        bot.tree().select("Uncategorized").expandNode("Uncategorized")
                .select(id + " (" + version + ")");
        bot.button(Messages.edit).click();
        bot.treeWithLabel(org.bonitasoft.studio.connector.model.i18n.Messages.categoryLabel).select(0);
        bot.button(IDialogConstants.FINISH_LABEL).click();
        final ActorFilterDefRepositoryStore store = RepositoryManager
                .getInstance().getRepositoryStore(
                        ActorFilterDefRepositoryStore.class);
        ConnectorDefinitionRegistry connectorDefinitionRegistry = store.getResourceProvider().getConnectorDefinitionRegistry();
        final ExtendedConnectorDefinition connectorDef = connectorDefinitionRegistry.find(id,version).orElse(null);
        assertEquals("category size should be equal to 1", connectorDef
                .getCategory().size(), 1);
        SWTBotActorFilterUtil.activateActorFilterDefEditionShell(bot);
        Category c = connectorDef.getCategory().get(0);
        String categoryLabel = connectorDefinitionRegistry.find(c).map(ExtendedCategory::getLabel).orElse(c.getId());
        if (categoryLabel == null) {
            categoryLabel = c.getId();
        }
        final String connectorLabel = new ConnectorDefinitionTreeLabelProvider().getText(connectorDef);
        assertNotNull(
                "could not find " + connectorLabel,
                bot.tree().getTreeItem(categoryLabel).expand().getNode(connectorLabel));
        bot.button(IDialogConstants.CANCEL_LABEL).click();
    }

}
