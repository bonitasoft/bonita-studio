/*******************************************************************************
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.swtbot.framework.composite;

import java.util.List;

import org.bonitasoft.studio.expression.editor.operation.OperationViewer;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
import org.bonitasoft.studio.swtbot.framework.BotBase;
import org.bonitasoft.studio.swtbot.framework.SWTBotTestUtil;
import org.bonitasoft.studio.swtbot.framework.expression.BotExpressionEditorDialog;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.eclipse.gef.finder.matchers.IsInstanceOf;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.hamcrest.Matcher;

/**
 * One operation row.
 *
 * @author Joachim Segala
 */
public class BotOperationComposite extends BotBase {

    private final SWTBot localBot;

    public BotOperationComposite(final SWTGefBot bot, final int pIndex) {
        super(bot);
        final Matcher<OperationViewer> matcher = new IsInstanceOf<OperationViewer>(OperationViewer.class);
        final List<OperationViewer> composites = bot.getFinder().findControls(matcher);
        localBot = new SWTBot(composites.get(pIndex));
    }

    public void selectLeftOperand(final String pName, final String pType) {
        SWTBotTestUtil.selectExpressionProposal(localBot, pName, pType, 0);
    }

    public String getLeftOperand() {
        return localBot.text(0).getText();
    }

    public String getRightOperand() {
        return localBot.text(1).getText();
    }

    public void selectRightOperand(final String pName, final String pType) {
        SWTBotTestUtil.selectExpressionProposal(localBot, pName, pType, 1);
    }

    public BotExpressionEditorDialog editRightOperand() {
        SWTBotShell activeShell = bot.activeShell();
        localBot.toolbarButtonWithId(ExpressionViewer.SWTBOT_ID_EDITBUTTON, 0).click();
        return new BotExpressionEditorDialog(bot, activeShell);
    }

    public String getSelectedOperator() {
        return localBot.link().getText().replace("<A>", "").replace("</A>", "");
    }

    public BotSelectOperatorDialog editOperator() {
        localBot.link().click();
        return new BotSelectOperatorDialog(bot);
    }

}
