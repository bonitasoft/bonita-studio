/*******************************************************************************
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.swtbot.framework.diagram.general.connectors;

import org.bonitasoft.studio.connectors.i18n.Messages;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
import org.bonitasoft.studio.swtbot.framework.BotWizardDialog;
import org.bonitasoft.studio.swtbot.framework.SWTBotTestUtil;
import org.bonitasoft.studio.swtbot.framework.expression.BotExpressionEditorDialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTable;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotText;

/**
 * Add connector dialog.
 *
 * @author Joachim Segala
 */
public class BotAddConnectorDialog extends BotWizardDialog {

    public BotAddConnectorDialog(final SWTGefBot bot) {
        super(bot, Messages.connectors);
    }

    public BotAddConnectorDialog(final SWTGefBot bot, final String expectedWizardTitle) {
        super(bot, expectedWizardTitle);
    }

    /**
     * Select the connector in the right list.
     *
     * @param pConnectorId
     */
    public void selectConnector(final String pConnectorId) {
        bot.tree().select("All");
        bot.waitUntil(new DefaultCondition() {

            @Override
            public boolean test() throws Exception {
                return "All".equals(bot.tree().selection().get(0).get(0));
            }

            @Override
            public String getFailureMessage() {
                return "Root element of the tree not selected.";
            }
        });
        bot.table().select(pConnectorId);
    }

    public void selectConnectorInCategory(String categoryName, final String pConnectorId) {
        bot.tree().getTreeItem("All").select(categoryName);
        bot.table().select(pConnectorId);
    }

    /**
     * Search for connector.
     *
     * @param pConnectorId
     */
    public void searchConnector(final String pConnectorId) {
        bot.text().setText(pConnectorId);
    }

    /**
     * Set the connector name.
     *
     * @param pName
     */
    public void setName(final String pName) {
        bot.textWithLabel(org.bonitasoft.studio.connector.model.i18n.Messages.dataNameLabel).setText(pName);
    }

    /**
     * Set the connector description.
     *
     * @param pDescription
     */
    public void setDescription(final String pDescription) {
        bot.textWithLabel(org.bonitasoft.studio.connector.model.i18n.Messages.dataDescriptionLabel).setText(pDescription);
    }

    /**
     * Select the action to do if the connector fail.
     *
     * @param pAction
     */
    public void selectIfConnectorFails(final String pAction) {
        bot.comboBoxWithLabel(Messages.connectorCrashLabel).setSelection(pAction);
    }

    /**
     * Set the named error of the fail of the connector.
     *
     * @param pNamedError
     */
    public void setNamedError(final String pNamedError) {
        bot.textWithLabel(Messages.connectorFails_namedError).setText(pNamedError);
    }

    /**
     * Click on the pen in order to edit the input.
     * Works for basic inputs.
     *
     * @param pIndex
     * @return the expression editor popup
     */
    public BotExpressionEditorDialog editInput(final int pIndex) {
        return edit(pIndex);
    }

    /**
     * Click on the pen in order to edit the output.
     *
     * @param pIndex
     * @return the expression editor popup
     */
    public BotExpressionEditorDialog editOutput(final int pIndex) {
        return edit(pIndex);
    }

    /**
     * Click on the pen in order to edit the script.
     *
     * @param pIndex
     * @return the expression editor popup
     */
    public BotExpressionEditorDialog editScript(final int pIndex) {
        return edit(pIndex);
    }

    protected BotExpressionEditorDialog edit(final int pIndex) {
    	SWTBotShell activeShell = bot.activeShell();
        bot.toolbarButtonWithId(ExpressionViewer.SWTBOT_ID_EDITBUTTON, pIndex).click();
        return new BotExpressionEditorDialog(bot,activeShell);
    }

    /**
     * Set text for styled text widget.
     * (example: DB script)
     *
     * @param pExpression
     */
    public void setStyledText(final String pExpression) {
        bot.styledText().setText(pExpression);
    }

    /**
     * in wizard "Output operations definition",
     * Select the connector output mode "Graphical"
     */
    public void selectGraphicalMode() {
        bot.waitUntil(Conditions.widgetIsEnabled(bot.radio(Messages.graphicalMode)), 5000);
        bot.radio(Messages.graphicalMode).click();
    }

    /**
     * in wizard "Output operations definition",
     * Select the connector output mode "Scripting"
     */
    public void selectScriptingMode() {
        bot.waitUntil(Conditions.widgetIsEnabled(bot.radio(Messages.scriptMode)), 5000);
        bot.radio(Messages.scriptMode).click();
    }

    /**
     * Click on next.
     */
    @Override
    public BotAddConnectorDialog next() {
        bot.waitUntil(Conditions.widgetIsEnabled(bot.button(IDialogConstants.NEXT_LABEL)), 5000);
        bot.button(IDialogConstants.NEXT_LABEL).click();
        return this;
    }

    /**
     * Switch editor for the query
     */
    public void switchEditor() {
        bot.link(0).click(); //the link 'switch editor' of index 0
        bot.sleep(1000);
        bot.button("Yes").click(); // Confirm button
    }

    /**
     * Select the variable to store the connector output.
     *
     * @param pVariableId
     */
    public void selectOutputVariable(final String pVariableId) {
        final SWTBotText text = bot.text(0);
        text.setFocus();
        bot.toolbarButtonWithId("expressionViewerDropDown", 0).click();
        final SWTBot proposalBot = bot.shellWithId("expressionViewerProposalShell").bot();
        final SWTBotTable proposalTAble = proposalBot.tableWithId("expressionViewerProposalTable");
        proposalTAble.select(pVariableId);; //1st value
        SWTBotTestUtil.pressEnter();
    }

    /**
     * Set the Command separator text field in the connector widget.
     * This is part of a connector specific definition, no i18n message available
     *
     * @param commandSep
     */
    public void setCommandSeparator(final String commandSep) {
        bot.textWithLabel("Command separator").setText(commandSep);
    }

}
