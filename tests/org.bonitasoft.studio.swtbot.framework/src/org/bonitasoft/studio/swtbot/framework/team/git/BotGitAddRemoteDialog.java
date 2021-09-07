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
import org.eclipse.egit.ui.internal.UIText;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;

public class BotGitAddRemoteDialog extends BotWizardDialog {

    public BotGitAddRemoteDialog(SWTGefBot bot) {
        super(bot, MessageFormat.format(UIText.PushBranchWizard_WindowTitle, "master"));
    }

    public BotGitAddRemoteDialog setURI(String uri) {
        bot.textWithLabel(UIText.AbstractConfigureRemoteDialog_UriLabel).setText(uri);
        return this;
    }

    public BotGitAddRemoteDialog selectFileProtocol() {
        bot.comboBoxWithLabel(UIText.RepositorySelectionPage_promptScheme + ":").setSelection("file");
        return this;
    }

}
