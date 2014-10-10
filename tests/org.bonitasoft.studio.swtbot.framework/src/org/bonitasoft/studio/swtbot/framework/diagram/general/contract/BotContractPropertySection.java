/*******************************************************************************
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.swtbot.framework.diagram.general.contract;

import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.contract.i18n.Messages;
import org.bonitasoft.studio.swtbot.framework.BotBase;
import org.bonitasoft.studio.swtbot.framework.widget.BotTreeWidget;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;

/**
 * Recurrence property section.
 *
 * @author Romain Bioteau
 */
public class BotContractPropertySection extends BotBase {

    public BotContractPropertySection(final SWTGefBot bot) {
        super(bot);
    }

    public BotTreeWidget inputTable() {
        return new BotTreeWidget(bot.treeWithId(SWTBotConstants.SWTBOT_ID_CONTRACT_INPUT_TREE));
    }

    public BotContractInputRow add() {
        bot.button(Messages.add).click();
        return new BotContractInputRow(bot, bot.tree().rowCount() - 1);
    }

    public BotContractPropertySection remove() {
        bot.button(Messages.remove).click();
        bot.waitUntil(Conditions.shellIsActive(Messages.removeInputConfirmationTitle));
        bot.button(IDialogConstants.OK_LABEL).click();
        return this;
    }



}
