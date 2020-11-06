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

import org.bonitasoft.studio.swtbot.framework.BotDialog;
import org.bonitasoft.studio.team.git.i18n.Messages;
import org.eclipse.egit.ui.internal.UIText;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.waits.Conditions;

public class BotGitInitDialog extends BotDialog {

    public BotGitInitDialog(SWTGefBot bot) {
        super(bot, Messages.shareRepositoryProgressTitle);
    }

    public BotGitInitDialog commitMessage(String commitMessage) {
        bot.textWithLabel(Messages.firstCommitMessage).setText(commitMessage);
        return this;
    }

    public BotGitAddRemoteDialog pushBranch() {
        bot.button(Messages.commitAndPush).click();
        bot.waitUntil(Conditions.shellIsActive(MessageFormat.format(UIText.PushBranchWizard_WindowTitle, "master")));
        return new BotGitAddRemoteDialog(bot);
    }

}
