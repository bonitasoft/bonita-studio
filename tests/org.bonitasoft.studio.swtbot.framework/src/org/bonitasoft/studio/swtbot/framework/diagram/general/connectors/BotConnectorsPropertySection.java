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
import org.bonitasoft.studio.swtbot.framework.BotBase;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;

/**
 * Connectors property section.
 * 
 * @author Joachim Segala
 */
public class BotConnectorsPropertySection extends BotBase {

    public BotConnectorsPropertySection(final SWTGefBot bot) {
        super(bot);
    }

    public BotAddConnectorDialog addConnector() {
        bot.button(Messages.add).click();
        return new BotAddConnectorDialog(bot);
    }

    public BotEditConnectorDialog editConnector(final int index, final String expectedWizardTitleOnOpen) {
        bot.table().select(index);
        bot.button(Messages.Edit).click();
        return new BotEditConnectorDialog(bot, expectedWizardTitleOnOpen);
    }

}
