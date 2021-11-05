/*******************************************************************************
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.swtbot.framework.diagram.general.pool;

import org.bonitasoft.studio.properties.i18n.Messages;
import org.bonitasoft.studio.swtbot.framework.BotBase;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;

/**
 * Pool property section.
 */
public class BotPoolPropertySection extends BotBase {

    public BotPoolPropertySection(final SWTGefBot bot) {
        super(bot);
    }

    public BotPoolPropertySection setName(String name) {
        bot.toolbarButtonWithId("org.bonitasoft.studio.ui.widget.textWidget.editButton", 0).click();
        bot.textWithLabel(Messages.name).setText(name);
        bot.toolbarButtonWithId("org.bonitasoft.studio.ui.widget.textWidget.validateEdit").click();
        return this;
    }

    public BotPoolPropertySection setVersion(String version) {
        bot.toolbarButtonWithId("org.bonitasoft.studio.ui.widget.textWidget.editButton", 1).click();
        bot.textWithLabel(Messages.version).setText(version);
        bot.toolbarButtonWithId("org.bonitasoft.studio.ui.widget.textWidget.validateEdit").click();
        return this;
    }

    public BotPoolPropertySection setDisplayName(String displayName) {
        bot.textWithLabel(Messages.displayName).setText(displayName);
        return this;
    }

    public BotPoolPropertySection setDescription(String description) {
        bot.textWithLabel(Messages.description).setText(description);
        return this;
    }

}
