/*******************************************************************************
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.swtbot.framework.diagram.general.operations;

import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
import org.bonitasoft.studio.swtbot.framework.BotBase;
import org.bonitasoft.studio.swtbot.framework.expression.BotExpressionEditorDialog;
import org.bonitasoft.studio.test.swtbot.util.SWTBotTestUtil;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.SWTBot;

/**
 * One operation row.
 * 
 * @author Joachim Segala
 */
public class BotOperationComposite extends BotBase {

    private final SWTBot localBot;

    public BotOperationComposite(final SWTGefBot bot, final SWTBot localBot) {
        super(bot);
        this.localBot = localBot;
    }

    public void selectLeftOperand(final String pName, final String pType) {
        SWTBotTestUtil.selectExpressionProposal(localBot, pName, pType, 0);
    }

    public void selectRightOperand(final String pName, final String pType) {
        SWTBotTestUtil.selectExpressionProposal(localBot, pName, pType, 1);
    }

    public BotExpressionEditorDialog editRightOperand() {
        localBot.toolbarButtonWithId(ExpressionViewer.SWTBOT_ID_EDITBUTTON, 0).click();
        return new BotExpressionEditorDialog(bot);
    }

}
