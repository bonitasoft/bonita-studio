/*******************************************************************************
 * Copyright (C) 2019 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.swtbot.framework.bdm;

import org.bonitasoft.studio.businessobject.editor.editor.ui.control.businessObject.BusinessObjectList;
import org.bonitasoft.studio.businessobject.editor.editor.ui.control.index.IndexControl;
import org.bonitasoft.studio.businessobject.editor.editor.ui.control.index.IndexEditionControl;
import org.bonitasoft.studio.swtbot.framework.BotBase;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotMultiPageEditor;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.keyboard.Keystrokes;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTable;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

public class BotBdmIndexesEditor extends BotBase {

    private SWTBotMultiPageEditor editor;
    private BotBdmEditor botBdmEditor;

    public BotBdmIndexesEditor(SWTGefBot bot, SWTBotMultiPageEditor editor, BotBdmEditor botBdmEditor) {
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

    public BotBdmIndexesEditor save() {
        editor.save();
        return this;
    }

    public BotBdmIndexesEditor deploy() {
        botBdmEditor.deploy();
        return this;
    }

    public void close() {
        editor.close();
    }

    public BotBdmIndexesEditor addIndex(String packageName, String businessObject, String indexName,
            String... selectFields) {
        selectBusinessObject(packageName, businessObject);
        bot.toolbarButtonWithId(IndexControl.ADD_INDEX_BUTTON_ID).click();
        bot.textWithId(SWTBOT_ID_UNIQUE_INDEX_NAME_TEXTEDITOR).setText(indexName);
        getIndexesTable().pressShortcut(Keystrokes.CR);
        editIndex(packageName, businessObject, indexName, selectFields);
        return this;
    }

    public BotBdmIndexesEditor editIndex(String packageName, String businessObject, String indexName,
            String... selectFields) {
        selectBusinessObject(packageName, businessObject);
        SWTBotTable indexesTable = getIndexesTable();
        indexesTable.select(indexName);
        SWTBotTable availableFieldsTable = getAvailableFieldsTable();
        SWTBotTable indexedFieldsTable = getIndexedFieldsTable();
        // clean indexed table
        int count = indexedFieldsTable.rowCount();
        for (int i = 0; i < count; i++) {
            indexedFieldsTable.getTableItem(0).dragAndDrop(availableFieldsTable);
        }
        // Add selected fields
        for (String field : selectFields) {
            availableFieldsTable.getTableItem(field).dragAndDrop(indexedFieldsTable);
        }
        return this;
    }

    public BotBdmIndexesEditor moveField(String packageName, String businessObject, String indexName, String field,
            int position) {
        selectBusinessObject(packageName, businessObject);
        SWTBotTable indexesTable = getIndexesTable();
        indexesTable.select(indexName);
        SWTBotTable indexedFieldsTable = getIndexedFieldsTable();
        indexedFieldsTable.getTableItem(field).dragAndDrop(indexedFieldsTable.getTableItem(position));
        return this;
    }

    private SWTBotTable getIndexesTable() {
        return bot.tableWithId(IndexControl.INDEX_LIST_VIEWER_ID);
    }

    private SWTBotTable getIndexedFieldsTable() {
        return bot.tableWithId(IndexEditionControl.INDEXED_FIELDS_VIEWER_ID);
    }

    private SWTBotTable getAvailableFieldsTable() {
        return bot.tableWithId(IndexEditionControl.AVAILABLE_FIELDS_VIEWER_ID);
    }

    private void selectBusinessObject(String packageName, String businessObject) {
        SWTBotTreeItem packageItem = getBusinessObjectTree().getTreeItem(packageName);
        packageItem.expand();
        packageItem.getNode(businessObject).select();
    }

    private SWTBotTree getBusinessObjectTree() {
        return bot.treeWithId(BusinessObjectList.BUSINESS_OBJECT_LIST_VIEWER_ID);
    }

}
