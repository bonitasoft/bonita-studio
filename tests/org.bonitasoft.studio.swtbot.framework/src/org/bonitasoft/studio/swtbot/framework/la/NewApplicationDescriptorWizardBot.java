/*******************************************************************************
 * Copyright (C) 2017 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel � 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.swtbot.framework.la;

import org.bonitasoft.studio.la.i18n.Messages;
import org.bonitasoft.studio.swtbot.framework.BotWizardDialog;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTable;

public class NewApplicationDescriptorWizardBot extends BotWizardDialog {

    public NewApplicationDescriptorWizardBot(SWTGefBot bot, String dialogTitle) {
        super(bot, dialogTitle);
    }

    public NewApplicationDescriptorWizardBot addNewMode() {
        bot.radio(Messages.addNew).click();
        return this;
    }

    public NewApplicationDescriptorWizardBot addFromMode() {
        bot.radio(Messages.addFromExistingAppDescriptor).click();
        return this;
    }

    /**
     * addNew mode needed
     */
    public NewApplicationDescriptorWizardBot withDisplayName(String displayName) {
        bot.textWithLabel(Messages.displayName).setText(displayName);
        return this;
    }

    /**
     * addNew mode needed
     */
    public NewApplicationDescriptorWizardBot withToken(String token) {
        bot.textWithLabel(Messages.applicationToken).setText(token);
        return this;
    }

    /**
     * addNew mode needed
     */
    public NewApplicationDescriptorWizardBot withVersion(String version) {
        bot.textWithLabel(Messages.version).setText(version);
        return this;
    }

    /**
     * addFrom mode needed
     */
    public NewApplicationDescriptorWizardBot withNewToken(String newToken) {
        bot.textWithLabel(Messages.newApplicationDescriptorToken).setText(newToken);
        return this;
    }

    /**
     * addFrom mode needed
     */
    public NewApplicationDescriptorWizardBot selectExistingApplication(String application) {
        table().select(application);
        return this;
    }

    public SWTBotTable table() {
        bot.waitUntil(Conditions.widgetIsEnabled(bot.table()));
        return bot.table();
    }

    public BotApplicationEditor create() {
        finish();
        return new BotApplicationEditor(bot, bot.activeEditor());
    }

    @Override
    public void finish() {
        bot.waitUntil(Conditions.widgetIsEnabled(bot.button(Messages.add)));
        final SWTBotShell activeShell = bot.activeShell();
        bot.button(Messages.add).click();
        bot.waitUntil(Conditions.shellCloses(activeShell));
    }

}
