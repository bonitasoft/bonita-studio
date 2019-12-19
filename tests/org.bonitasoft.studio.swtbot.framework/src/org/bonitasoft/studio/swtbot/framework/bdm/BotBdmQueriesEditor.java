/*******************************************************************************
 * Copyright (C) 2019 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.swtbot.framework.bdm;

import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;

import org.bonitasoft.studio.businessobject.editor.editor.ui.control.businessObject.BusinessObjectList;
import org.bonitasoft.studio.businessobject.editor.editor.ui.control.query.QueryDetailsControl;
import org.bonitasoft.studio.businessobject.editor.editor.ui.control.query.QueryEditionControl;
import org.bonitasoft.studio.businessobject.editor.editor.ui.editingSupport.QueryParameterTypeEditingSupport;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.swtbot.framework.BotBase;
import org.bonitasoft.studio.swtbot.framework.ConditionBuilder;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotMultiPageEditor;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotCCombo;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTable;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

public class BotBdmQueriesEditor extends BotBase {

    private SWTBotMultiPageEditor editor;
    private BotBdmEditor botBdmEditor;

    public BotBdmQueriesEditor(SWTGefBot bot, SWTBotMultiPageEditor editor, BotBdmEditor botBdmEditor) {
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

    public BotBdmQueriesEditor save() {
        editor.save();
        return this;
    }

    public BotBdmQueriesEditor deploy() {
        botBdmEditor.deploy();
        return this;
    }

    public void close() {
        editor.close();
    }

    public BotBdmQueriesEditor addCustomQuery(String packageName,
            String businessObject,
            String queryName,
            String content,
            Map<String, String> queryParam,
            String returnType) {
        selectBusinessObject(packageName, businessObject);
        bot.toolbarButtonWithId(QueryEditionControl.ADD_QUERY_BUTTON_ID).click();
        bot.textWithId(SWTBOT_ID_QUERY_NAME_TEXTEDITOR).setText(queryName);
        setContent(queryName, content);
        setParameters(queryName, queryParam);
        setReturnType(queryName, returnType);
        return this;
    }

    public BotBdmQueriesEditor setReturnType(String queryName, String returnType) {
        selectCustomQuery(queryName);
        bot.comboBoxWithLabel(Messages.queryResultType).setSelection(returnType);
        return this;
    }

    public BotBdmQueriesEditor setParameters(String queryName, Map<String, String> queryParam) {
        selectCustomQuery(queryName);
        // remove all existing parameters
        SWTBotTable queryParametersTable = getQueryParametersTable();
        int[] indexes = new int[queryParametersTable.rowCount()];
        for (int i = 0; i < indexes.length; i++) {
            indexes[i] = i;
        }
        queryParametersTable.select(indexes);
        bot.toolbarButtonWithId(QueryDetailsControl.REMOVE_PARAM_BUTTON_ID).click();

        //add required parameters
        for (Entry<String, String> paramEntry : queryParam.entrySet()) {
            bot.toolbarButtonWithId(QueryDetailsControl.ADD_PARAM_BUTTON_ID).click();
            getQueryParametersTable().getTableItem(Messages.parameter).click();
            bot.textWithId(SWTBOT_ID_QUERY_PARAM_NAME_TEXTEDITOR).setText(paramEntry.getKey());
            setType(queryName, paramEntry.getKey(), paramEntry.getValue());
        }
        return this;
    }

    private BotBdmQueriesEditor setType(String queryName, String parameterName, String parameterType) {
        SWTBotShell activeShell = bot.activeShell();
        selectCustomQuery(queryName);
        getQueryParametersTable().getTableItem(parameterName).click(1);
        SWTBot activeBot = activeShell.bot();
        SWTBotCCombo typeComboBox = activeBot
                .ccomboBoxWithId(QueryParameterTypeEditingSupport.QUERY_PARAM_TYPE_COMBO_EDITOR_ID);
        activeBot.waitUntil(new ConditionBuilder()
                .withTest(() -> Stream.of(typeComboBox.items()).anyMatch(parameterType::equals))
                .withFailureMessage(() -> String.format("The type %s doesn't exist", parameterType))
                .create());
        typeComboBox.setSelection(parameterType);
        return this;
    }

    public BotBdmQueriesEditor setContent(String queryName, String content) {
        selectCustomQuery(queryName);
        bot.textWithId(QueryDetailsControl.QUERY_CONTENT_WIDGET_ID).setText(content);
        return this;
    }

    private void selectCustomQuery(String queryName) {
        SWTBotTree tree = getQueryListTree();
        tree.select(Messages.customQueriesOption);
        tree.expandNode(Messages.customQueriesOption);
        bot.waitUntil(new ConditionBuilder()
                .withTest(() -> {
                    try {
                        tree.getTreeItem(Messages.customQueriesOption).getNode(queryName);
                        return true;
                    } catch (WidgetNotFoundException e) {
                        return false;
                    }
                })
                .withFailureMessage(() -> String.format("Can't find custom query %s", queryName))
                .create());
        tree.getTreeItem(Messages.customQueriesOption).getNode(queryName).select();
    }

    private SWTBotTree getQueryListTree() {
        return bot.treeWithId(QueryEditionControl.QUERY_LIST_VIEWER_ID);
    }

    private SWTBotTable getQueryParametersTable() {
        return bot.tableWithId(QueryDetailsControl.QUERY_PARAMETERS_VIEWER_ID);
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
