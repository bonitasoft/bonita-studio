/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.swtbot.framework.team;

import org.bonitasoft.studio.swtbot.framework.application.menu.AbstractBotMenu;
import org.bonitasoft.studio.swtbot.framework.team.git.BotGitMenu;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;

public class BotTeamMenu extends AbstractBotMenu {

    public BotTeamMenu(final SWTGefBot bot) {
        super(bot);
    }

    public BotGitMenu gitMenu() {
        openMenu("Git");
        return new BotGitMenu(bot);
    }

}
