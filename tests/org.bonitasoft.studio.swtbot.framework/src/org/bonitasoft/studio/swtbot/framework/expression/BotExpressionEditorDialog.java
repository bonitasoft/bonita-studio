/*******************************************************************************
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.swtbot.framework.expression;

import org.bonitasoft.studio.expression.editor.i18n.Messages;
import org.bonitasoft.studio.swtbot.framework.BotDialog;
import org.bonitasoft.studio.swtbot.framework.widget.BotTableWidget;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;

/**
 * Expression editor dialog.
 *
 * @author Joachim Segala
 */
public class BotExpressionEditorDialog extends BotDialog {

    private SWTBotShell activeShell;

	public BotExpressionEditorDialog(final SWTGefBot bot, SWTBotShell activeShell) {
        super(bot, Messages.editExpression);
        this.activeShell =  activeShell;
    }

    public BotScriptExpressionEditor selectScriptTab() {
        bot.tableWithLabel(Messages.expressionTypeLabel).select("Script");
        bot.waitUntilWidgetAppears(Conditions.waitForWidget(WidgetMatcherFactory.widgetOfType(StyledText.class)));
        return new BotScriptExpressionEditor(bot, this);
    }

    public BotVariableExpressionEditor selectVariableTab() {
        bot.tableWithLabel(Messages.expressionTypeLabel).select("Variable");
        bot.waitUntilWidgetAppears(Conditions.waitForWidget(WidgetMatcherFactory.widgetOfType(Table.class)));
        return new BotVariableExpressionEditor(bot, this);
    }

    public BotConstantExpressionEditor selectConstantType() {
        bot.tableWithLabel(Messages.expressionTypeLabel).select("Constant");
        bot.waitUntilWidgetAppears(Conditions.waitForWidget(WidgetMatcherFactory.widgetOfType(Text.class)));
        return new BotConstantExpressionEditor(bot, this);
    }

    public BotConditionExpressionEditor selectConditionExpressionType() {
        bot.tableWithLabel(Messages.expressionTypeLabel).select("Comparison");
        bot.waitUntilWidgetAppears(Conditions.waitForWidget(WidgetMatcherFactory.widgetOfType(StyledText.class)));
        return new BotConditionExpressionEditor(bot, this);
    }

    public BotFormFieldExpressionEditor selectFormFieldType() {
        bot.tableWithLabel(Messages.expressionTypeLabel).select("Form field");
        bot.waitUntilWidgetAppears(Conditions.waitForWidget(WidgetMatcherFactory.widgetOfType(Table.class)));
        return new BotFormFieldExpressionEditor(bot, this);
    }

    public BotContractInputExpressionEditor selectContractInputType() {
        bot.tableWithLabel(Messages.expressionTypeLabel)
                .select(org.bonitasoft.studio.contract.i18n.Messages.contractInputTypeLabel);
        bot.waitUntilWidgetAppears(Conditions.waitForWidget(WidgetMatcherFactory.widgetOfType(Table.class)));
        return new BotContractInputExpressionEditor(bot, this);
    }

    public BotTableWidget listAvailableTypes() {
        return new BotTableWidget(bot.tableWithLabel(Messages.expressionTypeLabel));
    }
    
    @Override
    public void cancel() {
    	super.cancel();
    	activeShell.setFocus();
    }
    
    @Override
    public void ok() {
    	super.ok();
    	activeShell.setFocus();
    }

}
