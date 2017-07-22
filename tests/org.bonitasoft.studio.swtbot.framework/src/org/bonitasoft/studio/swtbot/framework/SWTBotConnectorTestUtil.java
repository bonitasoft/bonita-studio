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

import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotCombo;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.junit.Assert;

/**
 * @author Aur�lie Zara
 */
public class SWTBotConnectorTestUtil {

    private static SWTBotTreeItem categoryItem;
    private static List<String> nodes;

    /**
     * use it to access to the wizard "New definition..." (menu
     * DEvelopement>Connectors>New definition...)
     * 
     * @param bot
     */
    public static void activateConnectorDefinitionShell(SWTBot bot) {
        bot.waitUntil(Conditions.widgetIsEnabled(bot.menu("Development")), 10000);
        bot.menu("Development").menu("Connectors").menu("New definition...").click();
        bot.waitUntil(Conditions.shellIsActive("New connector definition"), 10000);
    }

    /**
     * use it to access to the wizard "New implementation..." (menu
     * DEvelopement>Connectors>Edit definition)
     * 
     * @param bot
     */
    public static void activateConnectorImplementationShell(SWTBot bot) {
        bot.waitUntil(Conditions.widgetIsEnabled(bot.menu("Development")), 10000);
        bot.menu("Development").menu("Connectors").menu("New implementation...").click();
        bot.waitUntil(Conditions.shellIsActive("New connector implementation"), 10000);
    }

    /**
     * use it to access to the wizard "Edit definition" (menu
     * DEvelopement>Connectors>Edit definition)
     * 
     * @param bot
     */
    public static void activateConnectorDefEditionShell(SWTBot bot) {
        bot.waitUntil(Conditions.widgetIsEnabled(bot.menu("Development")), 10000);
        bot.menu("Development").menu("Connectors").menu("Edit definition...").click();
        bot.waitUntil(Conditions.shellIsActive("Select a connector definition"), 10000);
    }

    /**
     * use it to access to the wizard "Test connector" (menu
     * DEvelopement>Connectors>Test connector...)
     * 
     * @param bot
     */
    public static void activateConnectorTestWizard(SWTBot bot) {
        bot.waitUntil(Conditions.widgetIsEnabled(bot.menu("Development")), 10000);
        bot.menu("Development").menu("Connectors").menu("Test connector...").click();
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
     * use it to access to the wizard "Import connector" (menu
     * Developement>Connectors>Import)
     * 
     * @param bot
     */
    public static void activateConnectorImportShell(SWTBot bot) {
        bot.waitUntil(Conditions.widgetIsEnabled(bot.menu("Development")), 10000);
        bot.menu("Development").menu("Connectors").menu("Import connector...").click();
        bot.waitUntil(Conditions.shellIsActive("Import connector archive"), 10000);
    }

    /**
     * use it to access to the wizard "Export connector" (menu Development<Connector>Export)
     */
    public static void activateExportConnectorShell(SWTBot bot) {
        bot.menu("Development").menu("Connectors").menu("Export connector...").click();
        bot.waitUntil(Conditions.shellIsActive("Export connector"), 10000);
        bot.activeShell().setFocus();
    }

    /**
     * use it to create a connector def and impl (no window should be opened)
     * 
     * @param bot
     * @param id
     * @param version
     * @param className
     * @param packageName
     */
    public static void createConnectorDefAndImpl(final SWTWorkbenchBot bot, String id, String version, String className,
            String packageName) {
        final int nbEditorsBefore = bot.editors().size();
        activateConnectorDefinitionShell(bot);
        createConnectorDefinition(bot, id, version);
        bot.button(IDialogConstants.FINISH_LABEL).click();
        activateConnectorImplementationShell(bot);
        bot.table().select(id);
        final SWTBotCombo comboBoxToSelectVersion = bot.comboBoxWithLabel("Definition version");
        if (comboBoxToSelectVersion.isEnabled()) {
            comboBoxToSelectVersion.setSelection(version);
        }
        bot.button(IDialogConstants.NEXT_LABEL).click();
        bot.textWithLabel("Class name *").setText(className);
        bot.textWithLabel("Package *").setText(packageName);
        bot.button(IDialogConstants.FINISH_LABEL).click();
        bot.waitUntil(new ICondition() {

            @Override
            public boolean test() throws Exception {
                return nbEditorsBefore + 1 == bot.editors().size();
            }

            @Override
            public void init(SWTBot bot) {
            }

            @Override
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
