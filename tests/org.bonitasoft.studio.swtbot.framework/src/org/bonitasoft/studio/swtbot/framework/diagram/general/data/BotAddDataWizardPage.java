/*******************************************************************************
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.swtbot.framework.diagram.general.data;

import org.bonitasoft.studio.data.i18n.Messages;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.waits.Conditions;

/**
 * Add data dialog.
 *
 * @author Joachim Segala
 */
public class BotAddDataWizardPage extends AbstractBotDataWizardPage {

    public BotAddDataWizardPage(final SWTGefBot bot) {
        super(bot);
        bot.waitUntil(Conditions.shellIsActive(Messages.newVariable));
        bot.shell(Messages.newVariable).activate();
    }
}
