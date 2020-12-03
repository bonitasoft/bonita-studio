/*******************************************************************************
 * Copyright (C) 2018 Bonitasoft S.A.
 * Bonitasoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.swtbot.framework.team.git;

import org.bonitasoft.studio.swtbot.framework.application.menu.AbstractBotMenu;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;

public class BotGitMenu extends AbstractBotMenu {

    public BotGitMenu(SWTGefBot bot) {
        super(bot);
    }

    public BotShareRepoDialog shareWithGit() {
        openMenu("Share with Git");
        return new BotShareRepoDialog(bot);
    }

    public BotGitCloneDialog gitClone() {
        openMenu("Clone");
        return new BotGitCloneDialog(bot);
    }

}
