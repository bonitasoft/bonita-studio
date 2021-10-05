/*******************************************************************************
 * Copyright (C) 2019 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.swtbot.framework.bdm;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.bonitasoft.studio.businessobject.editor.editor.ui.control.attribute.AttributeEditionControl;
import org.bonitasoft.studio.businessobject.editor.editor.ui.control.businessObject.BusinessObjectList;
import org.bonitasoft.studio.businessobject.editor.editor.ui.editingSupport.FieldTypeEditingSupport;
import org.bonitasoft.studio.businessobject.helper.PackageHelper;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.swtbot.framework.BotBase;
import org.bonitasoft.studio.swtbot.framework.ConditionBuilder;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotMultiPageEditor;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.keyboard.Keystrokes;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotCCombo;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTable;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTableItem;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

public class BotBdmModelEditor extends BotBase {

    private SWTBotMultiPageEditor editor;
    private BotBdmEditor botBdmEditor;

    public BotBdmModelEditor(SWTGefBot bot, SWTBotMultiPageEditor editor, BotBdmEditor botBdmEditor) {
        super(bot);
        this.editor = editor;
        this.botBdmEditor = botBdmEditor;
    }

    public BotBdmModelEditor modelPage() {
        return botBdmEditor.modelPage();
    }

    public BotBdmConstraintsEditor constraintsPage() {
        return botBdmEditor.constraintsPage();
    }

    public BotBdmQueriesEditor queriesPage() {
        return botBdmEditor.queriesPage();
    }

    public BotBdmIndexesEditor indexesPage() {
        return botBdmEditor.indexesPage();
    }

    public BotBdmModelEditor save() {
        botBdmEditor.save();
        return this;
    }

    public BotBdmModelEditor deploy() {
        botBdmEditor.deploy();
        return this;
    }

    public void close() {
        editor.close();
    }

    // Model methods

    public BotBdmModelEditor withPackage(String packageName) {
        bot.textWithLabel(Messages.packageName).setText(packageName);
        return this;
    }

    public BotBdmModelEditor addPackage(String packageName, String businessObject) {
        bot.toolbarButtonWithId(BusinessObjectList.ADD_PACKAGE_BUTTON_ID).click();
        renamePackage(PackageHelper.defaultPackageName(), packageName);
        renameBusinessObject(packageName, BusinessObjectList.DEFAULT_BO_NAME, businessObject);
        return this;
    }

    public BotBdmModelEditor removePackage(String packageName) {
        selectPackage(packageName);
        bot.toolbarButtonWithId(BusinessObjectList.REMOVE_BUTTON_ID).click();
        bot.waitUntil(Conditions.shellIsActive(Messages.deletePackageConfirmTitle));
        SWTBotShell activeShell = bot.activeShell();
        activeShell.bot().button(IDialogConstants.YES_LABEL).click();
        bot.waitUntil(Conditions.shellCloses(activeShell));
        return this;
    }

    public BotBdmModelEditor renamePackage(String oldName, String newName) {
        SWTBotTree businessObjectTree = getBusinessObjectTree();
        bot.sleep(200);
        SWTBotTreeItem packageItem = businessObjectTree.getTreeItem(oldName);
        bot.sleep(200);
        packageItem
                .expand()
                .click();
        bot.text(oldName)
                .setText(newName)
                .pressShortcut(Keystrokes.CR);
        bot.waitUntil(new ConditionBuilder()
                .withTest(() -> businessObjectTree.getTreeItem(newName) != null)
                .withFailureMessage(() -> String.format("Failed to rename package from %s to %s", oldName, newName))
                .create());
        return this;
    }

    public BotBdmModelEditor addBusinessObject(String packageName, String name) {
        selectPackage(packageName);
        bot.toolbarButtonWithId(BusinessObjectList.ADD_BO_BUTTON_ID).click();
        renameBusinessObject(packageName, BusinessObjectList.DEFAULT_BO_NAME, name);
        return this;
    }

    public BotBdmModelEditor renameBusinessObject(String packageName, String oldName, String newName) {
        SWTBotTree businessObjectTree = getBusinessObjectTree();
        bot.sleep(200);
        SWTBotTreeItem packageItem = businessObjectTree.getTreeItem(packageName);
        bot.sleep(200);
        packageItem.expand();
        bot.waitUntil(nodeAvailable(packageItem, oldName));
        SWTBotTreeItem node = packageItem.getNode(oldName);
        node.click();
        bot.text(oldName)
                .setText(newName)
                .pressShortcut(Keystrokes.CR);
        businessObjectTree.setFocus();
        bot.waitUntil(new ConditionBuilder()
                .withTest(() -> businessObjectTree.getTreeItem(packageName).getNode(newName) != null)
                .withFailureMessage(() -> String.format("Failed to rename business object from %s to %s", oldName, newName))
                .create());
        return this;
    }

    public BotBdmModelEditor removeBusinessObject(String packageName, String name) {
        SWTBotTree businessObjectTree = getBusinessObjectTree();
        bot.waitUntil(treeItemAvailable(businessObjectTree, packageName));
        SWTBotTreeItem packageItem = businessObjectTree.getTreeItem(packageName);
        packageItem.expand();
        bot.waitUntil(nodeAvailable(packageItem, name));
        packageItem.getNode(name).select();
        bot.toolbarButtonWithId(BusinessObjectList.REMOVE_BUTTON_ID).click();
        bot.waitUntil(Conditions.shellIsActive(Messages.deleteBOConfirmTitle));
        SWTBotShell activeShell = bot.activeShell();
        activeShell.bot().button(IDialogConstants.YES_LABEL).click();
        bot.waitUntil(Conditions.shellCloses(activeShell));
        return this;
    }

    public BotBdmModelEditor addAttribute(String packageName, String businessObject, String attribute, String type) {
        SWTBotTable attributeTable = getAttributeTable(packageName, businessObject);
        attributeTable.unselect();
        bot.waitUntil(new DefaultCondition() {

            @Override
            public boolean test() throws Exception {
                return attributeTable.selectionCount() == 0;
            }

            @Override
            public String getFailureMessage() {
                return "Attribute table should have no selection";
            }
        });
        bot.toolbarButtonWithId(AttributeEditionControl.ADD_ATTRIBUTE_BUTTON_ID).click();
        bot.textWithId(SWTBOT_ID_ATTRIBUTE_NAME_TEXTEDITOR)
                .setText(attribute)
                .pressShortcut(Keystrokes.CR);
        try {
            setType(packageName, businessObject, attributeTable.getTableItem(attribute), type, attributeTable);
            return this;
        } catch (WidgetNotFoundException e) {
            bot.captureScreenshot(String.format("screenshots/addBDMAttribute%s.jpg", System.currentTimeMillis()));
            throw e;
        }
    }

    public BotBdmModelEditor renameAttribute(String packageName, String businessObject, String oldAttributeName,
            String newAttributeName) {
        SWTBotTable attributeTable = getAttributeTable(packageName, businessObject);
        bot.waitUntil(Conditions.widgetIsEnabled(attributeTable));
        bot.waitUntil(new DefaultCondition() {

            @Override
            public boolean test() throws Exception {
                attributeTable.getTableItem(oldAttributeName).select();
                return attributeTable.selectionCount() == 1;
            }

            @Override
            public String getFailureMessage() {
                return String.format("Cannot select '%s' row in attribute table", oldAttributeName);
            }
        });
        attributeTable.getTableItem(oldAttributeName).click();
        bot.waitUntil(Conditions.widgetIsEnabled(bot.textWithId(SWTBOT_ID_ATTRIBUTE_NAME_TEXTEDITOR)));
        bot.textWithId(SWTBOT_ID_ATTRIBUTE_NAME_TEXTEDITOR)
                .setText(newAttributeName)
                .pressShortcut(Keystrokes.CR);
        attributeTable.setFocus();
        return this;
    }

    public BotBdmModelEditor removeAttribute(String packageName, String businessObject, String attributeName) {
        SWTBotTable attributeTable = getAttributeTable(packageName, businessObject);
        attributeTable.getTableItem(attributeName).select();
        bot.toolbarButtonWithId(AttributeEditionControl.REMOVE_ATTRIBUTE_BUTTON_ID).click();
        bot.waitUntil(Conditions.shellIsActive(Messages.deleteFieldConfirmTitle));
        SWTBotShell activeShell = bot.activeShell();
        activeShell.bot().button(IDialogConstants.YES_LABEL).click();
        bot.waitUntil(Conditions.shellCloses(activeShell));
        return this;
    }

    public BotBdmModelEditor setType(String packageName, String businessObject, SWTBotTableItem item, String type,
            SWTBotTable attributeTable) {
        SWTBotShell activeShell = bot.activeShell();
        item.click(1);
        SWTBot activeBot = activeShell.bot();
        SWTBotCCombo ccomboBoxInGroup = activeBot.ccomboBoxWithId(FieldTypeEditingSupport.TYPE_COMBO_EDITOR_ID);
        activeBot.waitUntil(new ConditionBuilder()
                .withTest(() -> Stream.of(ccomboBoxInGroup.items()).anyMatch(type::equals))
                .withFailureMessage(() -> String.format("Type '%s' not found in combo", type))
                .create());
        ccomboBoxInGroup.setSelection(type);
        return this;
    }

    /**
     * The attribute type has to be a String!
     */
    public BotBdmModelEditor setAttributeLength(String packageName, String businessObject, String attribute,
            String length) {
        SWTBotTable attributeTable = getAttributeTable(packageName, businessObject);
        attributeTable.select(attribute);
        bot.comboBoxWithLabel(Messages.length).setText(length);
        return this;
    }

    public BotBdmModelEditor setMandatory(String packageName, String businessObject, String attribute) {
        getAttributeTable(packageName, businessObject).getTableItem(attribute).click(3);
        return this;
    }

    public BotBdmModelEditor setMultiple(String packageName, String businessObject, String attribute) {
        getAttributeTable(packageName, businessObject).getTableItem(attribute).click(2);
        return this;
    }

    /**
     * The attribute type has to be an other business object!
     */
    public BotBdmModelEditor setRelationType(String packageName, String businessObject, String attribute,
            final String relationType) {
        SWTBotTable attributeTable = getAttributeTable(packageName, businessObject);
        bot.waitUntil(new DefaultCondition() {

            @Override
            public boolean test() throws Exception {
                attributeTable.getTableItem(attribute).select();
                return attributeTable.selectionCount() == 1;
            }

            @Override
            public String getFailureMessage() {
                return String.format("Cannot select '%s' row in attribute table", attribute);
            }
        });

        bot.comboBoxWithLabel(Messages.relation).setSelection(relationType);
        return this;
    }

    private void selectPackage(String packageName) {
        getBusinessObjectTree().getTreeItem(packageName).select();
    }

    public BotBdmModelEditor selectBusinessObject(String packageName, String businessObject) {
        SWTBotTreeItem packageItem = getBusinessObjectTree().getTreeItem(packageName);
        if (!packageItem.isExpanded()) {
            packageItem.expand();
        }
        SWTBotTreeItem node = packageItem.getNode(businessObject);
        if (!node.isSelected()) {
            node.select();
        }
        return this;
    }

    private SWTBotTable getAttributeTable(String packageName, String businessObject) {
        selectBusinessObject(packageName, businessObject);
        return bot.tableWithId(AttributeEditionControl.ATTRIBUTE_LIST_VIEWER_ID);
    }

    private SWTBotTree getBusinessObjectTree() {
        return bot.treeWithId(BusinessObjectList.BUSINESS_OBJECT_LIST_VIEWER_ID);
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
                .withFailureMessage(() -> String.format("The node %s isn't available. Available items are %s", item,
                        Stream.of(tree.getAllItems()).map(SWTBotTreeItem::getText).collect(Collectors.joining(","))))
                .create();
    }

}
