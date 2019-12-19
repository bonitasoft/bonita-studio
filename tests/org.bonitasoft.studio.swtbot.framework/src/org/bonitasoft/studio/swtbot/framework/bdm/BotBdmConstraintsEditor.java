/*******************************************************************************
 * Copyright (C) 2019 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.swtbot.framework.bdm;

import java.util.Arrays;
import java.util.List;

import org.bonitasoft.studio.businessobject.editor.editor.ui.control.businessObject.BusinessObjectList;
import org.bonitasoft.studio.businessobject.editor.editor.ui.control.constraint.ConstraintEditionControl;
import org.bonitasoft.studio.swtbot.framework.BotBase;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotMultiPageEditor;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.keyboard.Keystrokes;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTable;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTableItem;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

public class BotBdmConstraintsEditor extends BotBase {

    private SWTBotMultiPageEditor editor;
    private BotBdmEditor botBdmEditor;

    public BotBdmConstraintsEditor(SWTGefBot bot, SWTBotMultiPageEditor editor, BotBdmEditor botBdmEditor) {
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

    public BotBdmConstraintsEditor save() {
        editor.save();
        return this;
    }

    public BotBdmConstraintsEditor deploy() {
        botBdmEditor.deploy();
        return this;
    }

    public void close() {
        editor.close();
    }

    public BotBdmConstraintsEditor addConstraint(String packageName, String businessObject, String constraintName,
            String... selectFields) {
        selectBusinessObject(packageName, businessObject);
        bot.toolbarButtonWithId(ConstraintEditionControl.ADD_CONSTRAINT_BUTTON_ID).click();
        bot.textWithId(SWTBOT_ID_UNIQUE_CONSTRAINT_NAME_TEXTEDITOR).setText(constraintName);
        getConstraintsTable().pressShortcut(Keystrokes.CR);
        return editConstraint(packageName, businessObject, constraintName, selectFields);
    }

    public BotBdmConstraintsEditor editConstraint(String packageName, String businessObject, String constraintName,
            String... selectFields) {
        selectBusinessObject(packageName, businessObject);
        getConstraintsTable().select(constraintName);
        SWTBotTable constraintsEditionTable = getConstraintsEditionTable();
        List<String> toCheck = Arrays.asList(selectFields);
        for (int i = 0; i < constraintsEditionTable.rowCount(); i++) {
            SWTBotTableItem tableItem = constraintsEditionTable.getTableItem(i);
            if (toCheck.contains(tableItem.getText())) {
                tableItem.check();
            } else {
                tableItem.uncheck();
            }
        }
        return this;
    }

    private SWTBotTable getConstraintsTable() {
        return bot.tableWithId(ConstraintEditionControl.CONSTRAINTS_LIST_VIEWER_ID);
    }

    private SWTBotTable getConstraintsEditionTable() {
        return bot.tableWithId(ConstraintEditionControl.CONSTRAINT_EDITION_VIEWER_ID);
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
