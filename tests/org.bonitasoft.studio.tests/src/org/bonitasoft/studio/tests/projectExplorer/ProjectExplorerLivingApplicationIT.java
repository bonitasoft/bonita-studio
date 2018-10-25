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

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.bonitasoft.engine.api.ApplicationAPI;
import org.bonitasoft.engine.business.application.Application;
import org.bonitasoft.engine.search.SearchOptionsBuilder;
import org.bonitasoft.engine.session.APISession;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.engine.BOSEngineManager;
import org.bonitasoft.studio.la.LivingApplicationPlugin;
import org.bonitasoft.studio.la.application.core.ImportApplicationAction;
import org.bonitasoft.studio.la.application.handler.NewApplicationHandler;
import org.bonitasoft.studio.la.application.repository.ApplicationFileStore;
import org.bonitasoft.studio.la.application.repository.ApplicationRepositoryStore;
import org.bonitasoft.studio.swtbot.framework.projectExplorer.ProjectExplorerBot;
import org.bonitasoft.studio.swtbot.framework.projectExplorer.la.LivingApplicationProjectExplorerBot;
import org.bonitasoft.studio.swtbot.framework.rule.SWTGefBotRule;
import org.bonitasoft.studio.ui.util.StringIncrementer;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

public class ProjectExplorerLivingApplicationIT {

    private static BOSEngineManager engineManager;
    private static APISession session;

    private SWTGefBot bot = new SWTGefBot();
    private RepositoryAccessor repositoryAccessor;

    @Rule
    public SWTGefBotRule botRule = new SWTGefBotRule(bot);

    @BeforeClass
    public static void init() throws Exception {
        engineManager = BOSEngineManager.getInstance();
        session = engineManager.loginDefaultTenant(new NullProgressMonitor());
        LivingApplicationPlugin.getDefault().getPreferenceStore()
                .setValue(NewApplicationHandler.DO_NOT_SHOW_HELP_MESSAGE_DIALOG, true);
    }

    @AfterClass
    public static void logout() {
        if (session != null) {
            engineManager.logoutDefaultTenant(session);
        }
    }

    @Before
    public void initRepositoryAccessor() throws Exception {
        repositoryAccessor = new RepositoryAccessor();
        repositoryAccessor.init();
    }

    @Test
    public void should_manage_living_application_from_explorer() throws Exception {
        ProjectExplorerBot projectExplorerBot = new ProjectExplorerBot(bot);
        LivingApplicationProjectExplorerBot livingApplicationBot = projectExplorerBot.livingApplication();

        String appName = findNewApplicationName();
        projectExplorerBot.newLivingApplication();
        projectExplorerBot.waitUntilActiveEditorTitleIs(appName);
        validateApplicatonExists(appName);
        bot.activeEditor().close();
        livingApplicationBot.openApplication(appName);
        projectExplorerBot.waitUntilActiveEditorTitleIs(appName);
        bot.activeEditor().close();

        appName = findNewApplicationName();
        livingApplicationBot.newLivingApplication();
        projectExplorerBot.waitUntilActiveEditorTitleIs(appName);
        validateApplicatonExists(appName);

        String newName = findNewApplicationName();
        livingApplicationBot.renameApplication(appName, newName);
        projectExplorerBot.waitUntilActiveEditorTitleIs(newName);
        validateApplicatonExists(newName);
        validateApplicatonDoesntExist(appName);

        livingApplicationBot.deleteApplication(newName);
        validateApplicatonDoesntExist(newName);

        livingApplicationBot.exportApplication().cancel();

        String applicationToDeploy = FileLocator
                .toFileURL(ProjectExplorerLivingApplicationIT.class.getResource("/testExplorerApplicationFile.xml"))
                .getFile();
        new ImportApplicationAction().importApplication(repositoryAccessor, new File(applicationToDeploy));
        projectExplorerBot.livingApplication().deployApplication("testExplorerApplicationFile");
        ApplicationAPI applicationAPI = engineManager.getApplicationAPI(session);
        Stream<Application> deployedAppsStream = applicationAPI
                .searchApplications(new SearchOptionsBuilder(0, 20).done()).getResult().stream();
        assertThat(deployedAppsStream).extracting(Application::getToken).contains("testExplorerApplication");
    }

    private void validateApplicatonDoesntExist(String name) {
        assertThat(repositoryAccessor.getRepositoryStore(ApplicationRepositoryStore.class).getChildren())
                .flatExtracting(ApplicationFileStore::getDisplayName)
                .doesNotContain(name);
    }

    private void validateApplicatonExists(String name) {
        assertThat(repositoryAccessor.getRepositoryStore(ApplicationRepositoryStore.class).getChildren())
                .flatExtracting(ApplicationFileStore::getDisplayName)
                .contains(name);
    }

    private String findNewApplicationName() {
        List<String> existingApplicationNameList = repositoryAccessor.getRepositoryStore(ApplicationRepositoryStore.class)
                .getChildren().stream().map(ApplicationFileStore::getDisplayName).collect(Collectors.toList());
        String newName = StringIncrementer.getIncrementedString(NewApplicationHandler.DEFAULT_FILE_NAME,
                existingApplicationNameList);
        return newName;
    }

}
