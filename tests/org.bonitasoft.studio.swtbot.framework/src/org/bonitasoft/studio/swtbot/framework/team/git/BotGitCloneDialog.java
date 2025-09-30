/*******************************************************************************
 * Copyright (C) 2018 Bonitasoft S.A.
 * Bonitasoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.swtbot.framework.team.git;

import java.text.MessageFormat;

import org.bonitasoft.studio.swtbot.framework.BotWizardDialog;
import org.bonitasoft.studio.swtbot.framework.conditions.ShellWithRegexIsActive;
import org.bonitasoft.studio.team.git.i18n.Messages;
import org.eclipse.egit.ui.internal.UIText;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotButton;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;

public class BotGitCloneDialog extends BotWizardDialog {

    public BotGitCloneDialog(SWTGefBot bot) {
        super(bot, UIText.GitCloneWizard_title);
    }

    public BotGitCloneDialog setURI(String uri) {
        bot.textWithLabel(UIText.AbstractConfigureRemoteDialog_UriLabel).setText(uri);
        return this;
    }

    public BotGitCloneDialog selectFileProtocol() {
        bot.comboBoxWithLabel(UIText.RepositorySelectionPage_promptScheme + ":").setSelection("file");
        return this;
    }

    public BotGitCloneDialog next() {
        return (BotGitCloneDialog) super.next();
    }

    public void finishWithMigration() {
        bot.waitUntil(Conditions.widgetIsEnabled(bot.button(IDialogConstants.FINISH_LABEL)), 5000);
        final SWTBotShell activeShell = bot.activeShell();
        bot.button(IDialogConstants.FINISH_LABEL).click();
        bot.waitUntil(Conditions.shellIsActive(Messages.confirmMigratonTitle), 30000);
        bot.button(IDialogConstants.YES_LABEL).click();
        // Wait for project migration steps dialog & click 'Execute All'
        var regex = "\\Q"
                + MessageFormat.format(org.bonitasoft.studio.common.repository.Messages.projectMigration, "\\E\\d+\\Q")
                + "\\E";
        bot.waitUntil(new ShellWithRegexIsActive(regex), 30000);
        SWTBotButton executeAllButton = bot
                .button(org.bonitasoft.studio.common.repository.Messages.projectMigrationExecuteAllSteps);
        bot.waitUntil(Conditions.widgetIsEnabled(executeAllButton), 5000);
        executeAllButton.click();

        bot.waitUntil(Conditions.shellCloses(activeShell), 120000);
        bot.waitUntil(Conditions.shellIsActive(Messages.repositoryClonedTitle));
        bot.shell(Messages.repositoryClonedTitle).activate();
        bot.button(IDialogConstants.CLOSE_LABEL).click();
    }

    @Override
    public void finish() {
        bot.waitUntil(Conditions.widgetIsEnabled(bot.button(IDialogConstants.FINISH_LABEL)), 5000);
        final SWTBotShell activeShell = bot.activeShell();
        bot.button(IDialogConstants.FINISH_LABEL).click();
        bot.waitUntil(Conditions.shellCloses(activeShell), 120000);
        bot.waitUntil(Conditions.shellIsActive(Messages.repositoryClonedTitle));
        bot.shell(Messages.repositoryClonedTitle).activate();
        bot.button(IDialogConstants.CLOSE_LABEL).click();
    }

}
