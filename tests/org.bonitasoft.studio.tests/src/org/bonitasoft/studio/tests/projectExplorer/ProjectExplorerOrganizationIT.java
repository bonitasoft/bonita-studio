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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.engine.BOSEngineManager;
import org.bonitasoft.studio.identity.i18n.Messages;
import org.bonitasoft.studio.identity.organization.repository.OrganizationFileStore;
import org.bonitasoft.studio.identity.organization.repository.OrganizationRepositoryStore;
import org.bonitasoft.studio.swtbot.framework.organization.BotOrganizationEditor;
import org.bonitasoft.studio.swtbot.framework.projectExplorer.ProjectExplorerBot;
import org.bonitasoft.studio.swtbot.framework.rule.SWTGefBotRule;
import org.bonitasoft.studio.ui.util.StringIncrementer;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SWTBotJunit4ClassRunner.class)
public class ProjectExplorerOrganizationIT {

    private SWTGefBot bot = new SWTGefBot();
    private RepositoryAccessor repositoryAccessor;
    private Optional<String> ext = Optional.of("." + OrganizationRepositoryStore.ORGANIZATION_EXT);

    @Rule
    public SWTGefBotRule botRule = new SWTGefBotRule(bot);

    @Before
    public void init() throws Exception {
        repositoryAccessor = RepositoryManager.getInstance().getAccessor();
        BOSEngineManager.getInstance().start();
    }

    @Test
    public void should_manage_organization_from_explorer() {
        ProjectExplorerBot projectExplorerBot = new ProjectExplorerBot(bot);
        String orgaName = findNewOrgaName();

        projectExplorerBot.newOrganization();
        projectExplorerBot.waitUntilActiveEditorTitleIs(orgaName, ext);
        validateOrgaExists(orgaName);
        bot.activeEditor().close();
        projectExplorerBot.organization().deleteOrganization(orgaName);
        validateOrgaDoesntExists(orgaName);

        projectExplorerBot.organization().newOrganization();
        projectExplorerBot.waitUntilActiveEditorTitleIs(orgaName, ext);
        validateOrgaExists(orgaName);
        bot.activeEditor().close();
        projectExplorerBot.organization().deleteOrganization(orgaName);
        validateOrgaDoesntExists(orgaName);

        projectExplorerBot.organization().exportOrganization().cancel();
        projectExplorerBot.organization().exportOrganization("ACME").cancel();
        BotOrganizationEditor organizationEditor = projectExplorerBot.organization().openOrganization("ACME");
        organizationEditor.close();

        projectExplorerBot.organization().renameOrganization("ACME", "ACME2");
        validateOrgaDoesntExists("ACME");
        validateOrgaExists("ACME2");

        projectExplorerBot.organization().renameOrganization("ACME2", "ACME");
        validateOrgaDoesntExists("ACME2");
        validateOrgaExists("ACME");

        projectExplorerBot.organization().deployOrganization("ACME", "walter.bates");
    }

    private void validateOrgaExists(String name) {
        assertThat(repositoryAccessor.getRepositoryStore(OrganizationRepositoryStore.class).getChildren())
                .flatExtracting(OrganizationFileStore::getDisplayName)
                .contains(name);
    }

    private void validateOrgaDoesntExists(String name) {
        assertThat(repositoryAccessor.getRepositoryStore(OrganizationRepositoryStore.class).getChildren())
                .flatExtracting(OrganizationFileStore::getDisplayName)
                .doesNotContain(name);
    }

    private String findNewOrgaName() {
        List<String> existingOrgaNameList = repositoryAccessor.getRepositoryStore(OrganizationRepositoryStore.class)
                .getChildren().stream().map(OrganizationFileStore::getDisplayName).collect(Collectors.toList());
        String newName = StringIncrementer.getNextIncrement(Messages.defaultOrganizationName,
                existingOrgaNameList);
        return newName;
    }

}
