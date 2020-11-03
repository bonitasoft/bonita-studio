/*******************************************************************************
 * Copyright (C) 2018 Bonitasoft S.A.
 * Bonitasoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.swtbot.framework.team.git;

import org.bonitasoft.studio.swtbot.framework.BotWizardDialog;
import org.bonitasoft.studio.team.git.i18n.Messages;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;

public class BotShareRepoDialog extends BotWizardDialog {

    public BotShareRepoDialog(SWTGefBot bot) {
        super(bot, Messages.shareWithGit);
    }

    public BotShareRepoDialog setRepositoryName(String repositoryName) {
        bot.text().setText(repositoryName);
        return this;
    }

    public BotGitInitDialog share() {
        bot.waitUntil(Conditions.widgetIsEnabled(bot.button(Messages.share)), 5000);
        final SWTBotShell activeShell = bot.activeShell();
        bot.button(Messages.share).click();
        bot.waitUntil(Conditions.shellCloses(activeShell), 190000);
        return new BotGitInitDialog(bot);
    }

}
