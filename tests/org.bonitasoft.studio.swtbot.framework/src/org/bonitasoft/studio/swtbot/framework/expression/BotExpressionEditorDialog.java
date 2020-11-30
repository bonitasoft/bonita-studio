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
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
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
        this.activeShell = activeShell;
    }

    public BotScriptExpressionEditor selectScriptTab() {
        bot.tabItem("Script").activate();
        bot.waitUntilWidgetAppears(Conditions.waitForWidget(WidgetMatcherFactory.widgetOfType(StyledText.class)));
        return new BotScriptExpressionEditor(bot, this);
    }


    public BotConditionExpressionEditor selectConditionExpressionType() {
        bot.tabItem("Comparison").activate();
        bot.waitUntilWidgetAppears(Conditions.waitForWidget(WidgetMatcherFactory.widgetOfType(StyledText.class)));
        return new BotConditionExpressionEditor(bot, this);
    }

    public boolean isTypeAvailable(String type) {
        try {
            bot.tabItem(type);
            return true;
        } catch (WidgetNotFoundException e) {
            return false;
        }
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
