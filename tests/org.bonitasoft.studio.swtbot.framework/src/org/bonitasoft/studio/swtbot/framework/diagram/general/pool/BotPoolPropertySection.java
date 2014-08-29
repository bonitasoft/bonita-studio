/*******************************************************************************
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.swtbot.framework.diagram.general.pool;

import org.bonitasoft.studio.common.Messages;
import org.bonitasoft.studio.swtbot.framework.BotBase;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;

/**
 * Pool property section.
 * 
 * @author Joachim Segala
 */
public class BotPoolPropertySection extends BotBase {

    public BotPoolPropertySection(final SWTGefBot bot) {
        super(bot);
    }

    /**
     * Edit pool name & version if not null.
     * 
     * @param pName
     * @param pVersion
     */
    public void editPool(final String pName, final String pVersion) {
        bot.waitUntil(Conditions.widgetIsEnabled(bot.button(Messages.edit)));
        bot.button(Messages.edit).click();

        // Open new Shell
        bot.waitUntil(Conditions.shellIsActive(Messages.openNameAndVersionDialogTitle));

        if (pName != null && pName != "") {
            bot.textWithLabel(Messages.name).setText(pName);
        }
        if (pVersion != null && pVersion != "") {
            bot.textWithLabel(Messages.version).setText(pVersion);
        }
        bot.button(IDialogConstants.OK_LABEL).click();
    }
}
