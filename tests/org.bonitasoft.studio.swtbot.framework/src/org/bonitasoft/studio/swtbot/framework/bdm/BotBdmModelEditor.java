/*******************************************************************************
 * Copyright (C) 2019 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.swtbot.framework.bdm;

import java.util.stream.Stream;

import org.bonitasoft.studio.businessobject.editor.editor.ui.control.attribute.AttributeEditionControl;
import org.bonitasoft.studio.businessobject.editor.editor.ui.control.businessObject.BusinessObjectList;
import org.bonitasoft.studio.businessobject.helper.PackageHelper;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.businessobject.ui.wizard.BusinessDataModelWizardPage;
import org.bonitasoft.studio.businessobject.ui.wizard.editingsupport.FieldTypeEditingSupport;
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
        editor.save();
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
        renamePackage(PackageHelper.DEFAULT_PACKAGE_NAME, packageName);
        renameBusinessObject(packageName, BusinessDataModelWizardPage.DEFAULT_BUSINESS_OBJECT_NAME, businessObject);
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
        bot.waitUntil(treeItemAvailable(getBusinessObjectTree(), oldName));
        getBusinessObjectTree().getTreeItem(oldName).click();
        bot.textWithId(SWTBOT_ID_BO_NAME_TEXTEDITOR).setText(newName);
        getBusinessObjectTree().pressShortcut(Keystrokes.CR);
        return this;
    }

    public BotBdmModelEditor addBusinessObject(String packageName, String name) {
        selectPackage(packageName);
        bot.toolbarButtonWithId(BusinessObjectList.ADD_BO_BUTTON_ID).click();
        renameBusinessObject(packageName, BusinessDataModelWizardPage.DEFAULT_BUSINESS_OBJECT_NAME, name);
        return this;
    }

    public BotBdmModelEditor renameBusinessObject(String packageName, String oldName, String newName) {
        SWTBotTree businessObjectTree = getBusinessObjectTree();
        bot.waitUntil(treeItemAvailable(businessObjectTree, packageName));
        SWTBotTreeItem packageItem = businessObjectTree.getTreeItem(packageName);
        packageItem.expand();
        bot.waitUntil(nodeAvailable(packageItem, oldName));
        packageItem.getNode(oldName).click();
        bot.textWithId(SWTBOT_ID_BO_NAME_TEXTEDITOR).setText(newName);
        getBusinessObjectTree().pressShortcut(Keystrokes.CR);
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
        selectBusinessObject(packageName, businessObject);
        bot.toolbarButtonWithId(AttributeEditionControl.ADD_ATTRIBUTE_BUTTON_ID).click();
        getAttributeTable(packageName, businessObject).getTableItem(AttributeEditionControl.DEFAULT_FIELD_NAME).click();
        bot.textWithId(SWTBOT_ID_ATTRIBUTE_NAME_TEXTEDITOR).setText(attribute);
        setType(packageName, businessObject, attribute, type);
        return this;
    }

    public BotBdmModelEditor renameAttribute(String packageName, String businessObject, String oldAttributeName,
            String newAttributeName) {
        SWTBotTable attributeTable = getAttributeTable(packageName, businessObject);
        attributeTable.getTableItem(oldAttributeName).click();
        bot.textWithId(SWTBOT_ID_ATTRIBUTE_NAME_TEXTEDITOR).setText(newAttributeName);
        getBusinessObjectTree().pressShortcut(Keystrokes.CR);
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

    public BotBdmModelEditor setType(String packageName, String businessObject, String attribute, String type) {
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
    public BotBdmModelEditor setAttributeLength(String packageName, String businessObject, String attribute, String length) {
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
        attributeTable.select(attribute);
        bot.comboBoxWithLabel(Messages.relation).setSelection(relationType);
        return this;
    }

    private void selectPackage(String packageName) {
        getBusinessObjectTree().getTreeItem(packageName).select();
    }

    private void selectBusinessObject(String packageName, String businessObject) {
        SWTBotTreeItem packageItem = getBusinessObjectTree().getTreeItem(packageName);
        packageItem.expand();
        packageItem.getNode(businessObject).select();
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
                .withFailureMessage(() -> String.format("The node %s isn't available", item))
                .create();
    }

}
