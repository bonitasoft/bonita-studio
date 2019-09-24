/**
 * Copyright (C) 2017 Bonitasoft S.A.
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
package org.bonitasoft.studio.swtbot.framework.bdm;

import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;

import org.bonitasoft.studio.businessobject.helper.PackageHelper;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.businessobject.ui.wizard.BusinessDataModelWizardPage;
import org.bonitasoft.studio.businessobject.ui.wizard.editingsupport.FieldTypeEditingSupport;
import org.bonitasoft.studio.swtbot.framework.BotWizardDialog;
import org.bonitasoft.studio.swtbot.framework.ConditionBuilder;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.keyboard.Keystrokes;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotCCombo;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTable;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotText;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.eclipse.swtbot.swt.finder.widgets.TimeoutException;

public class DefineBdmWizardBot extends BotWizardDialog {

    public DefineBdmWizardBot(SWTGefBot bot, String dialogTitle) {
        super(bot, dialogTitle);
    }

    public DefineBdmWizardBot addPackage(String packageName, String businessObject) {
        bot.buttonInGroup(Messages.addPackage, Messages.listOfBusinessObjects).click();
        renamePackage(PackageHelper.DEFAULT_PACKAGE_NAME, packageName);
        return renameBusinessObject(packageName, BusinessDataModelWizardPage.DEFAULT_BUSINESS_OBJECT_NAME, businessObject);
    }

    public DefineBdmWizardBot renamePackage(String oldName, String newName) {
        bot.waitUntil(treeItemAvailable(getBusinessObjectTree(), oldName));
        getBusinessObjectTree().getTreeItem(oldName).click();
        SWTBotText text = bot.textWithId(SWTBOT_ID_BO_NAME_TEXTEDITOR);
        text.typeText(newName);
        text.pressShortcut(Keystrokes.CR);
        return this;
    }

    public DefineBdmWizardBot renameBusinessObject(String packageName, String oldName, String newName) {
        SWTBotTree businessObjectTree = getBusinessObjectTree();
        bot.waitUntil(treeItemAvailable(businessObjectTree, packageName));
        SWTBotTreeItem packageItem = businessObjectTree.getTreeItem(packageName);
        packageItem.expand();
        bot.waitUntil(nodeAvailable(packageItem, oldName));
        packageItem.getNode(oldName).click();
        SWTBotText text = bot.textWithId(SWTBOT_ID_BO_NAME_TEXTEDITOR);
        text.typeText(newName);
        text.pressShortcut(Keystrokes.CR);
        return this;
    }

    public DefineBdmWizardBot addBusinessObject(String packageName, String name) {
        selectPackage(packageName);
        bot.buttonInGroup(Messages.addBusinessObject, Messages.listOfBusinessObjects).click();
        bot.textWithId(SWTBOT_ID_BO_NAME_TEXTEDITOR).setText(name);
        return this;
    }

    public DefineBdmWizardBot addAttribute(String packageName, String businessObject, String attribute, String type) {
        selectBusinessObject(packageName, businessObject);
        bot.tabItem(Messages.attributes).activate();
        bot.buttonInGroup(Messages.add, businessObject).click();
        bot.textWithId(SWTBOT_ID_ATTRIBUTE_NAME_TEXTEDITOR).typeText(attribute);
        setType(packageName, businessObject, attribute, type);
        return this;
    }

    public DefineBdmWizardBot renameAttribute(String packageName, String businessObject, String oldAttributeName,
            String newAttributeName) {
        SWTBotTable attributeTable = getAttributeTable(packageName, businessObject);
        attributeTable.getTableItem(oldAttributeName).click();
        bot.textWithId(SWTBOT_ID_ATTRIBUTE_NAME_TEXTEDITOR).typeText(newAttributeName);
        return this;
    }

    public DefineBdmWizardBot setType(String packageName, String businessObject, String attribute, String type) {
        SWTBotTable attributeTable = getAttributeTable(packageName, businessObject);
        SWTBotShell activeShell = bot.activeShell();

        attributeTable.getTableItem(attribute).click(1);
        SWTBot activeBot = activeShell.bot();
        SWTBotCCombo ccomboBoxInGroup = activeBot.ccomboBoxWithId(FieldTypeEditingSupport.TYPE_COMBO_EDITOR_ID);
        activeBot.waitUntil(new DefaultCondition() {

            @Override
            public boolean test() throws Exception {
                return Stream.of(ccomboBoxInGroup.items()).anyMatch(type::equals);
            }

            @Override
            public String getFailureMessage() {
                return String.format("No attribute type '%s' found in combo", type);
            }
        });
        ccomboBoxInGroup.setSelection(type);
        return this;
    }

    /**
     * The attribute type has to be a String!
     */
    public DefineBdmWizardBot setAttributeLength(String packageName, String businessObject, String attribute,
            String length) {
        SWTBotTable attributeTable = getAttributeTable(packageName, businessObject);
        attributeTable.select(attribute);
        bot.ccomboBoxInGroup(Messages.bind(Messages.detailsFor, attribute)).setText(length);
        return this;
    }

    public DefineBdmWizardBot setMandatory(String packageName, String businessObject, String attribute) {
        getAttributeTable(packageName, businessObject).getTableItem(attribute).click(3);
        return this;
    }

    public DefineBdmWizardBot setMultiple(String packageName, String businessObject, String attribute) {
        getAttributeTable(packageName, businessObject).getTableItem(attribute).click(2);
        return this;
    }

    /**
     * The attribute type has to be an other business object!
     */
    public DefineBdmWizardBot setRelationType(String packageName, String businessObject, String attribute,
            final String relationType) {
        SWTBotTable attributeTable = getAttributeTable(packageName, businessObject);
        attributeTable.select(attribute);
        bot.comboBoxInGroup(Messages.bind(Messages.detailsFor, attribute)).setSelection(relationType);
        return this;
    }

    public DefineBdmWizardBot addConstraint(String packageName, String businessObject, String constraintName,
            String... selectFields) {
        selectBusinessObject(packageName, businessObject);
        bot.tabItem(Messages.constraints).activate();
        bot.buttonInGroup(Messages.add, businessObject).click();
        bot.textWithId(SWTBOT_ID_UNIQUE_CONSTRAINT_NAME_TEXTEDITOR).typeText(constraintName);
        editConstraint(packageName, businessObject, constraintName, selectFields);
        return this;
    }

    public DefineBdmWizardBot editConstraint(String packageName, String businessObject, String constraintName,
            String... selectFields) {
        SWTBotShell activeShell = bot.activeShell();
        SWTBotTable constraintsTable = getConstraintsTable(packageName, businessObject);
        constraintsTable.getTableItem(constraintName).click(1);
        bot.waitUntilWidgetAppears(new ConditionBuilder()
                .withTest(() -> {
                    try {
                        bot.button("...");
                        return true;
                    } catch (WidgetNotFoundException e) {
                        return false;
                    }
                }).create());
        bot.button("...").click();
        bot.waitUntil(Conditions.shellIsActive(Messages.selectUniqueConstraintFieldsTitle));
        Arrays.asList(selectFields).forEach(field -> bot.table().getTableItem(field).check());
        bot.button(IDialogConstants.OK_LABEL).click();
        activeShell.setFocus();
        return this;
    }

    public DefineBdmWizardBot addIndex(String packageName, String businessObject, String indexName, String... selectFields) {
        selectBusinessObject(packageName, businessObject);
        bot.tabItem(Messages.indexes).activate();
        bot.buttonInGroup(Messages.add, businessObject).click();
        bot.textWithId(SWTBOT_ID_UNIQUE_INDEX_NAME_TEXTEDITOR).typeText(indexName);
        editIndex(packageName, businessObject, indexName, selectFields);
        return this;
    }

    public DefineBdmWizardBot editIndex(String packageName, String businessObject, String indexName,
            String... selectFields) {
        SWTBotTable indexesTable = getIndexesTable(packageName, businessObject);
        indexesTable.getTableItem(indexName).click(1);
        SWTBotShell activeShell = bot.activeShell();
        bot.button("...").click();
        bot.waitUntil(Conditions.shellIsActive(Messages.bind(Messages.selectIndexFieldsTitle, indexName)));
        bot.tableWithLabel(Messages.availableAttributes).select(selectFields);
        bot.button(Messages.add).click();
        bot.tableWithLabel(Messages.indexedAttributes).select(selectFields);
        bot.button(Messages.up).click();
        bot.button(IDialogConstants.OK_LABEL).click();
        activeShell.setFocus();
        return this;
    }

    public DefineBdmWizardBot addCustomQuery(String packageName,
            String businessObject,
            String queryName,
            String content,
            Map<String, String> queryParam,
            String returnType,
            int index) {
        SWTBotTable customQueriesTable = getCustomQueriesTable(packageName, businessObject);
        bot.buttonInGroup(Messages.add, businessObject).click();

        bot.textWithId(SWTBOT_ID_QUERY_NAME_TEXTEDITOR).typeText(queryName);
        customQueriesTable.click(index, 2);

        SWTBotShell activeShell = bot.activeShell();
        bot.button("...").click();
        bot.waitUntil(Conditions.shellIsActive(Messages.newQueryTitle));
        bot.styledText().setText(content);

        // remove all existing parameters
        final SWTBotTable paramTableBot = bot.table();
        int[] indexes = new int[paramTableBot.rowCount()];
        for (int i = 0; i < indexes.length; i++) {
            indexes[i] = i;
        }
        paramTableBot.select(indexes);
        bot.button(Messages.delete).click();

        //add required parameters
        for (final Entry<String, String> paramEntry : queryParam.entrySet()) {
            bot.button(Messages.add).click();
            bot.textWithId(SWTBOT_ID_QUERY_PARAM_NAME_TEXTEDITOR).typeText(paramEntry.getKey());
            paramTableBot.getTableItem(paramEntry.getKey()).click(1);
            bot.ccomboBox().setSelection(paramEntry.getValue());
            bot.ccomboBox().pressShortcut(Keystrokes.CR);
        }

        bot.comboBoxWithLabel(Messages.queryResultType).setSelection(returnType);
        bot.button(IDialogConstants.OK_LABEL).click();
        activeShell.setFocus();
        return this;
    }

    public void deploy() {
        bot.button(IDialogConstants.FINISH_LABEL).click();
        try {
            bot.waitUntil(Conditions.shellIsActive(Messages.bdmCompatibilityTitle));
            bot.button(IDialogConstants.OK_LABEL).click();
        } catch (TimeoutException e) {
            // pop up doesn't appear -> there is no bdm yet, nothing to do
        }
        bot.waitUntil(Conditions.shellIsActive(Messages.bdmDeployedTitle));
        bot.button(IDialogConstants.OK_LABEL).click();
    }

    private SWTBotTree getBusinessObjectTree() {
        return bot.treeInGroup(Messages.listOfBusinessObjects);
    }

    private void selectBusinessObject(String packageName, String businessObject) {
        SWTBotTreeItem packageItem = getBusinessObjectTree().getTreeItem(packageName);
        packageItem.expand();
        packageItem.getNode(businessObject).select();
    }

    private void selectPackage(String packageName) {
        getBusinessObjectTree().getTreeItem(packageName).select();
    }

    private SWTBotTable getAttributeTable(String packageName, String businessObject) {
        selectBusinessObject(packageName, businessObject);
        bot.tabItem(Messages.attributes).activate();
        return bot.tableInGroup(businessObject);
    }

    private SWTBotTable getConstraintsTable(String packageName, String businessObject) {
        selectBusinessObject(packageName, businessObject);
        bot.tabItem(Messages.constraints).activate();
        bot.waitUntil(new ConditionBuilder()
                .withTest(() -> {
                    try {
                        bot.tableInGroup(businessObject);
                        return true;
                    } catch (WidgetNotFoundException e) {
                        return false;
                    }
                })
                .withFailureMessage(() -> "Constraints table viewer not found")
                .create());
        return bot.tableInGroup(businessObject);
    }

    private SWTBotTable getIndexesTable(String packageName, String businessObject) {
        selectBusinessObject(packageName, businessObject);
        bot.tabItem(Messages.indexes).activate();
        return bot.tableInGroup(businessObject);
    }

    private SWTBotTable getCustomQueriesTable(String packageName, String businessObject) {
        selectBusinessObject(packageName, businessObject);
        bot.tabItem(Messages.queries).activate();
        bot.radio(Messages.customQueriesOption).click();
        return bot.tableInGroup(businessObject);
    }

    public ICondition nodeAvailable(SWTBotTreeItem item, String node) {
        return new ConditionBuilder()
                .withTest(() -> {
                    try {
                        item.getNode(node);
                        return true;
                    } catch (WidgetNotFoundException e) {
                        return false;
                    }
                })
                .withFailureMessage(() -> String.format("The node %s of '%s' isn't available", node, item))
                .create();
    }

    public ICondition treeItemAvailable(SWTBotTree tree, String item) {
        return new ConditionBuilder()
                .withTest(() -> {
                    try {
                        tree.getTreeItem(item);
                        return true;
                    } catch (WidgetNotFoundException e) {
                        return false;
                    }
                })
                .withFailureMessage(() -> String.format("The node %s isn't available", item))
                .create();
    }
}
