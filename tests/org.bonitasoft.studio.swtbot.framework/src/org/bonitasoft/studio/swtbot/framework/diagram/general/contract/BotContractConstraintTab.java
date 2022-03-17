/*******************************************************************************
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.swtbot.framework.diagram.general.contract;

import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.contract.i18n.Messages;
import org.bonitasoft.studio.swtbot.framework.BotBase;
import org.bonitasoft.studio.swtbot.framework.widget.BotTableWidget;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;

/**
 * Recurrence property section.
 *
 * @author Romain Bioteau
 */
public class BotContractConstraintTab extends BotBase {

    private int row;

    public BotContractConstraintTab(final SWTGefBot bot) {
        super(bot);
    }

    public BotTableWidget constraintTable() {
        return new BotTableWidget(bot.tableWithId(SWTBotConstants.SWTBOT_ID_CONTRACT_CONSTRAINT_TABLE));
    }

    public BotContractConstraintRow add() {
        bot.button(Messages.add).click();
        Display.getDefault().syncExec(new Runnable() {

            @Override
            public void run() {
                final Table table = constraintTable().getSWTBotWidget().widget;
                row = table.getSelectionIndex();

            }
        });

        return new BotContractConstraintRow(bot, row);
    }

    public BotContractConstraintTab remove() {
        bot.button(Messages.remove).click();
        if (!FileActionDialog.getDisablePopup()) {
            bot.waitUntil(Conditions.shellIsActive(Messages.removeConstraintConfirmationTitle));
            bot.button(IDialogConstants.OK_LABEL).click();
        }
        return this;
    }

}
