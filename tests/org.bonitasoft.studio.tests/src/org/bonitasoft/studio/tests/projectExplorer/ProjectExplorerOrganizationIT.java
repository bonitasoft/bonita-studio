/**
 * Copyright (C) 2018 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.tests.projectExplorer;

import static org.assertj.core.api.Assertions.assertThat;

import org.bonitasoft.engine.session.APISession;
import org.bonitasoft.studio.actors.repository.OrganizationRepositoryStore;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.engine.BOSEngineManager;
import org.bonitasoft.studio.swtbot.framework.organization.BotManageOrganizationWizard;
import org.bonitasoft.studio.swtbot.framework.projectExplorer.ProjectExplorerBot;
import org.bonitasoft.studio.swtbot.framework.rule.SWTGefBotRule;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SWTBotJunit4ClassRunner.class)
public class ProjectExplorerOrganizationIT {

    private static BOSEngineManager engineManager;
    private static APISession session;

    private SWTGefBot bot;
    private ProjectExplorerBot projectExplorerBot;
    private RepositoryAccessor repositoryAccessor;

    @Rule
    public SWTGefBotRule botRule;

    @BeforeClass
    public static void init() throws Exception {
        engineManager = BOSEngineManager.getInstance();
        session = engineManager.loginDefaultTenant(new NullProgressMonitor());
    }

    @AfterClass
    public static void resetPreference() {
        if (session != null) {
            engineManager.logoutDefaultTenant(session);
        }
    }

    @Before
    public void initBot() throws Exception {
        bot = new SWTGefBot();
        projectExplorerBot = new ProjectExplorerBot(bot);
        botRule = new SWTGefBotRule(bot);
        repositoryAccessor = new RepositoryAccessor();
        repositoryAccessor.init();
    }

    @Test
    public void should_open_organization_wizard_when_creating_organization_from_explorer() {
        projectExplorerBot.newOrganization().cancel();
        projectExplorerBot.organization().newOrganization().cancel();
    }

    @Test
    public void should_open_export_orga_wizard_when_export_orga_from_explorer() {
        projectExplorerBot.organization().exportOrganization().cancel();
        projectExplorerBot.organization().exportOrganization("ACME").cancel();
    }

    @Test
    public void should_open_orga_wizard_on_group_page_when_open_a_specific_orga_from_explorer() {
        BotManageOrganizationWizard organizationWizard = projectExplorerBot.organization()
                .openOrganization("ACME");
        assertThat(bot.button("Add group").isEnabled()).isTrue();
        organizationWizard.cancel();
    }

    @Test
    public void should_rename_orga_from_explorer() {
        projectExplorerBot.organization().renameOrganization("ACME", "ACME2");
        assertThat(repositoryAccessor
                .getRepositoryStore(OrganizationRepositoryStore.class)
                .getChild("ACME.organization")).isNull();
        assertThat(repositoryAccessor
                .getRepositoryStore(OrganizationRepositoryStore.class)
                .getChild("ACME2.organization")).isNotNull();

        projectExplorerBot.organization().renameOrganization("ACME2", "ACME");
        assertThat(repositoryAccessor
                .getRepositoryStore(OrganizationRepositoryStore.class)
                .getChild("ACME2.organization")).isNull();
        assertThat(repositoryAccessor
                .getRepositoryStore(OrganizationRepositoryStore.class)
                .getChild("ACME.organization")).isNotNull();
    }

    @Test
    public void should_deploy_orga_from_explorer() {
        projectExplorerBot.organization().deployOrganization("ACME", "walter.bates");
    }

}
