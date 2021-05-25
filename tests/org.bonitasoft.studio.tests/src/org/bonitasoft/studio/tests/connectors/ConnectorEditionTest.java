/**
 * Copyright (C) 2012-2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.tests.connectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.core.maven.AddDependencyOperation;
import org.bonitasoft.studio.common.repository.provider.ConnectorDefinitionRegistry;
import org.bonitasoft.studio.common.repository.provider.ExtendedCategory;
import org.bonitasoft.studio.common.repository.provider.ExtendedConnectorDefinition;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.connector.model.definition.wizard.ConnectorDefinitionTreeLabelProvider;
import org.bonitasoft.studio.connector.model.i18n.Messages;
import org.bonitasoft.studio.connectors.repository.ConnectorDefRepositoryStore;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.bonitasoft.studio.preferences.pages.BonitaAdvancedPreferencePage;
import org.bonitasoft.studio.swtbot.framework.SWTBotConnectorTestUtil;
import org.bonitasoft.studio.swtbot.framework.conditions.SelectNodeUnder;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SWTBotJunit4ClassRunner.class)
public class ConnectorEditionTest {

    private SWTGefBot bot = new SWTGefBot();

    public void removeConnectorDefinition(final String name, final String version) {
        SWTBotConnectorTestUtil.activateConnectorDefEditionShell(bot);
        bot.tree().setFocus();
        bot.waitUntil(new SelectNodeUnder(bot, name + " (" + version + ")", "Uncategorized"), 10000, 1000);

        assertNotNull("could not find" + name + " (" + version + ")", bot
                .tree().select("Uncategorized").expandNode("Uncategorized")
                .getNode(name + " (" + version + ")"));
        bot.tree().select("Uncategorized").expandNode("Uncategorized").select(name + " (" + version + ")");
        bot.button("Delete").click();
        if (!FileActionDialog.getDisablePopup()) {
            bot.waitUntil(Conditions.shellIsActive("Delete?"));
            bot.button(IDialogConstants.YES_LABEL).click();
        }
        bot.button(IDialogConstants.CANCEL_LABEL).click();
    }

    @Before
    public void setUp() throws Exception {
        FileActionDialog.setDisablePopup(true);
        new AddDependencyOperation("org.bonitasoft.connectors", "bonita-connector-groovy", "1.1.3")
                .run(AbstractRepository.NULL_PROGRESS_MONITOR);
        BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore()
                .setValue(BonitaAdvancedPreferencePage.HIDE_CONNECTOR_DEFINITION_CHANGE_WARNING, true);
    }

    @Test
    public void testIdRenameEdit() throws Exception {
        final String id = "testEdit";
        final String version = "1.0.0";
        final String id2 = "testEdit2";
        SWTBotConnectorTestUtil.activateConnectorDefinitionShell(bot);
        SWTBotConnectorTestUtil.createConnectorDefinition(bot, id, version);
        bot.button(IDialogConstants.FINISH_LABEL).click();
        SWTBotConnectorTestUtil.activateConnectorDefEditionShell(bot);
        bot.tree().setFocus();
        bot.waitUntil(new SelectNodeUnder(bot, id + " (" + version + ")", "Uncategorized"), 10000);
        bot.button(Messages.Edit).click();
        assertEquals(bot.textWithLabel("Definition id *").getText(), id);
        assertEquals(bot.textWithLabel("Version *").getText(), version);
        bot.textWithLabel("Definition id *").setText(id2);
        bot.button(IDialogConstants.FINISH_LABEL).click();
        final ConnectorDefRepositoryStore store = RepositoryManager.getInstance()
                .getRepositoryStore(ConnectorDefRepositoryStore.class);
        final ConnectorDefinition connectorDef = store.getDefinition(id, version);
        final ConnectorDefinition connectorDef2 = store.getDefinition(id2, version);
        assertNull("the connectorDef with previous id shouldn't exist anymore",
                connectorDef);
        assertNotNull("the connectorDef with new id does not exist",
                connectorDef2);
        removeConnectorDefinition(id2, version);
    }

    @Test
    public void testVersionEdit() throws Exception {
        final String id = "testEdit3";
        final String version = "1.0.0";
        final String version2 = "1.0.1";
        SWTBotConnectorTestUtil.activateConnectorDefinitionShell(bot);
        SWTBotConnectorTestUtil.createConnectorDefinition(bot, id, version);
        bot.button(IDialogConstants.FINISH_LABEL).click();
        SWTBotConnectorTestUtil.activateConnectorDefEditionShell(bot);
        bot.tree().setFocus();
        bot.waitUntil(new SelectNodeUnder(bot, id + " (" + version + ")", "Uncategorized"), 10000);
        bot.button(Messages.Edit).click();
        bot.textWithLabel("Version *").setText(version2);
        bot.button(IDialogConstants.FINISH_LABEL).click();
        final ConnectorDefRepositoryStore store = RepositoryManager
                .getInstance().getRepositoryStore(
                        ConnectorDefRepositoryStore.class);
        final ConnectorDefinition connectorDef = store.getDefinition(id, version);
        final ConnectorDefinition connectorDef2 = store.getDefinition(id, version2);
        assertNull(
                "the connectorDef with previous version shouldn't exist anymore",
                connectorDef);
        assertNotNull("the connectorDef with new version does not exist",
                connectorDef2);
        removeConnectorDefinition(id, version2);
    }

    @Test
    public void testAddCategory() throws Exception {
        final String id = "testEdit4";
        final String version = "1.0.0";

        SWTBotConnectorTestUtil.activateConnectorDefinitionShell(bot);
        SWTBotConnectorTestUtil.createConnectorDefinition(bot, id, version);
        bot.button(IDialogConstants.FINISH_LABEL).click();
        SWTBotConnectorTestUtil.activateConnectorDefEditionShell(bot);
        bot.tree().setFocus();
        bot.waitUntil(new SelectNodeUnder(bot, id + " (" + version + ")", "Uncategorized"), 10000);
        bot.button(Messages.Edit).click();
        bot.treeWithLabel(Messages.categoryLabel).select(0);
        bot.button(IDialogConstants.FINISH_LABEL).click();
        final ConnectorDefRepositoryStore store = RepositoryManager
                .getInstance().getRepositoryStore(ConnectorDefRepositoryStore.class);
        ConnectorDefinitionRegistry connectorDefinitionRegistry = store.getResourceProvider()
                .getConnectorDefinitionRegistry();
        final ExtendedConnectorDefinition connectorDef = connectorDefinitionRegistry.find(id, version).orElse(null);
        assertEquals("category size should be equal to 1", 1, connectorDef.getCategory().size());
        SWTBotConnectorTestUtil.activateConnectorDefEditionShell(bot);
        ExtendedCategory category = connectorDefinitionRegistry.find(connectorDef.getCategory().get(0)).orElse(null);
        String categoryLabel = category.getLabel() == null ? category.getId() : category.getLabel();
        final String connectorLabel = new ConnectorDefinitionTreeLabelProvider().getText(connectorDef);
        assertNotNull("could not find " + connectorLabel,
                bot.tree().getTreeItem(categoryLabel).expand().getNode(connectorLabel));
        bot.button(IDialogConstants.CANCEL_LABEL).click();
    }

}
