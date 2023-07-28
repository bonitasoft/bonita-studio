/**
 * Copyright (C) 2012 BonitaSoft S.A.
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

package org.bonitasoft.studio.swtbot.framework;

import java.util.List;

import org.bonitasoft.studio.common.CommandExecutor;
import org.bonitasoft.studio.common.ui.jface.SWTBotConstants;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.junit.Assert;

/**
 * @author Aur�lie Zara
 */
public class SWTBotConnectorTestUtil {

    private static final String TEST_COMMAND = "org.bonitasoft.studio.connectors.testConnector";

    private static SWTBotTreeItem categoryItem;
    private static List<String> nodes;
    private static CommandExecutor commandExecutor = new CommandExecutor();


    /**
     * use it to access to the wizard "Test connector" (menu
     * DEvelopement>Connectors>Test connector...)
     * 
     * @param bot
     */
    public static void activateConnectorTestWizard(SWTBot bot) {
        bot.getDisplay().asyncExec(() -> commandExecutor.executeCommand(TEST_COMMAND, null));
        bot.waitUntil(Conditions.shellIsActive("Test connector"), 10000);
    }

    /**
     * use it when the wizard "New definition" is active. (menu
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

            @Override
            public boolean test() throws Exception {
                return id.equals(bot.textWithLabel("Definition id *").getText());
            }

            @Override
            public void init(SWTBot bot) {

            }

            @Override
            public String getFailureMessage() {
                return null;
            }
        }, 10000);
        bot.textWithLabel("Version *").setText(version);
        bot.waitUntil(new ICondition() {

            @Override
            public boolean test() throws Exception {
                return version.equals(bot.textWithLabel("Version *").getText());
            }

            @Override
            public void init(SWTBot bot) {

            }

            @Override
            public String getFailureMessage() {
                return null;
            }
        }, 10000);
    }

    /**
     * use it when the wizard "New definition" is active. (menu
     * development>connectors>New definition...)
     * 
     * @param bot
     * @param categoryId
     * @throws Exception
     */
    public static void createNewCategory(SWTBot bot, String categoryId) {
        bot.waitUntil(Conditions.widgetIsEnabled((bot.button("New..."))), 10000);
        SWTBotShell activeShell = bot.activeShell();
        bot.button("New...").click();
        bot.waitUntil(Conditions.widgetIsEnabled(bot.activeShell()));
        Assert.assertFalse("ok button should be desabled",
                bot.button(IDialogConstants.OK_LABEL).isEnabled());
        bot.textWithLabel("Id").setText(categoryId);
        bot.textWithLabel("Display name").setText(categoryId);
        bot.button(IDialogConstants.OK_LABEL).click();
        activeShell.setFocus();
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
    public static void addConnectorToPool(final SWTBot bot, final String connectorDefinitionLabel, final String version,
            final String[] categoryPathLabels, final String connectorName) {
        SWTBotTestUtil.selectTabbedPropertyView(bot, "Connectors");
        bot.button("Add...").click();
        Assert.assertFalse(IDialogConstants.NEXT_LABEL + " should be disabled", bot
                .button(IDialogConstants.NEXT_LABEL).isEnabled());
        Assert.assertFalse(IDialogConstants.FINISH_LABEL + " should be disabled", bot
                .button(IDialogConstants.FINISH_LABEL).isEnabled());
        categoryItem = null;
        nodes = null;

        for (int i = 0; i < categoryPathLabels.length; i++) {

            final String categoryLabel = categoryPathLabels[i];
            bot.waitUntil(new ICondition() {

                @Override
                public boolean test() throws Exception {
                    if (categoryItem == null) {
                        bot.tree().collapseNode(categoryLabel);
                        categoryItem = bot.tree().expandNode(categoryLabel, true);
                    } else {
                        categoryItem.collapseNode(categoryLabel);
                        categoryItem = categoryItem.expandNode(categoryLabel);
                    }

                    nodes = categoryItem.getNodes();
                    if (!nodes.isEmpty() && nodes.get(0).isEmpty()) {
                        return false;
                    }
                    return !nodes.isEmpty();
                }

                @Override
                public void init(SWTBot bot) {

                }

                @Override
                public String getFailureMessage() {
                    return "Category " + categoryLabel + " has no children\n" + "Current category item is "
                            + categoryItem != null ? categoryItem.getText() : "null";
                }
            }, 10000, 1000);
        }

        String cNode = null;
        for (final String node : nodes) {
            if (node.startsWith(connectorDefinitionLabel) && node.contains(version)) {
                cNode = node;
                break;
            }
        }
        Assert.assertNotNull("Connector " + connectorDefinitionLabel + " (" + version + ") not found in category path "
                + categoryPathLabels.toString() + " containing children:\n" + nodes, cNode);
        final String nodeToSelect = cNode;
        bot.waitUntil(new ICondition() {

            @Override
            public boolean test() throws Exception {
                categoryItem.select(nodeToSelect);
                final String selection = bot.tree().selection().get(0, 0);
                return selection != null && selection.startsWith(connectorDefinitionLabel);
            }

            @Override
            public void init(SWTBot bot) {

            }

            @Override
            public String getFailureMessage() {
                return "Cannot select tree item";
            }
        }, 10000, 1000);
        Assert.assertTrue(IDialogConstants.NEXT_LABEL + " should be enabled", bot
                .button(IDialogConstants.NEXT_LABEL).isEnabled());
        Assert.assertFalse(IDialogConstants.FINISH_LABEL + " should be disabled", bot
                .button(IDialogConstants.FINISH_LABEL).isEnabled());
        bot.button(IDialogConstants.NEXT_LABEL).click();
        Assert.assertFalse(IDialogConstants.FINISH_LABEL + " should be disabled", bot
                .button(IDialogConstants.FINISH_LABEL).isEnabled());
        bot.textWithLabel("Name *").setText(connectorName);
        bot.waitUntil(Conditions.widgetIsEnabled(bot.button(IDialogConstants.NEXT_LABEL)), 5000);
    }

    public static void selectDefinitionInConnectorShell(final SWTBot bot, final String definition) {
        bot.treeWithId(SWTBotConstants.SWTBOT_ID_EXPLORER_LEFT_TREE).select(0);
        bot.waitUntil(Conditions.tableHasRows(bot.tableWithId(SWTBotConstants.SWTBOT_ID_EXPLORER_RIGHT_TABLE), 1));
        bot.tableWithId(SWTBotConstants.SWTBOT_ID_EXPLORER_RIGHT_TABLE).select(definition);
        bot.button(IDialogConstants.NEXT_LABEL).click();
    }

}
