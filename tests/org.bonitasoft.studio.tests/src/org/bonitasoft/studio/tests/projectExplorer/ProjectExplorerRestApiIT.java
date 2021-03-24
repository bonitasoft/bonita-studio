/*******************************************************************************
 * Copyright (C) 2018 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.tests.projectExplorer;

import static org.assertj.core.api.Assertions.assertThat;

import org.bonitasoft.studio.assertions.StatusAssert;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.engine.BOSEngineManager;
import org.bonitasoft.studio.maven.model.RestAPIExtensionArchetypeConfiguration;
import org.bonitasoft.studio.rest.api.extension.core.maven.CreateRestAPIExtensionProjectOperation;
import org.bonitasoft.studio.rest.api.extension.core.repository.RestAPIExtensionRepositoryStore;
import org.bonitasoft.studio.swtbot.framework.projectExplorer.RestApiProjectExplorerBot;
import org.bonitasoft.studio.swtbot.framework.rule.SWTGefBotRule;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.m2e.core.MavenPlugin;
import org.eclipse.m2e.core.project.ProjectImportConfiguration;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class ProjectExplorerRestApiIT {

    private SWTGefBot bot = new SWTGefBot();
    private RestApiProjectExplorerBot restAPIExplorerBot;
    private RepositoryAccessor repositoryAccessor;

    @Rule
    public SWTGefBotRule botRule = new SWTGefBotRule(bot);

    @Before
    public void init() throws Exception {
        restAPIExplorerBot = new RestApiProjectExplorerBot(bot);
        repositoryAccessor = RepositoryManager.getInstance().getAccessor();
        BOSEngineManager.getInstance().start();
    }

    @Test
    public void should_manage_rest_api_from_exporer() throws Exception {
        String packageName = "com.company.rest.api";
        String name1 = "rest api 1";
        String projectName = "myRestAPI1";
        String pathTemplate1 = "pathTemplate1";

        createRestApi(packageName, name1, projectName, pathTemplate1);
        restAPIExplorerBot.openRestApiPomFile(projectName);
        restAPIExplorerBot.buildRestApi(projectName);
        restAPIExplorerBot.runRestApiTests(projectName);
        restAPIExplorerBot.deployRestAPi(projectName);
        restAPIExplorerBot.deleteRestApi(projectName);

        RestAPIExtensionRepositoryStore restAPIExtensionRepositoryStore = repositoryAccessor
                .getRepositoryStore(RestAPIExtensionRepositoryStore.class);
        assertThat(restAPIExtensionRepositoryStore.getChildren()).isEmpty();
    }

    private void createRestApi(String packageName, String name, String projectName, String pathTemplate) throws Exception {
        RestAPIExtensionArchetypeConfiguration defaultArchetypeConfiguration = RestAPIExtensionArchetypeConfiguration
                .defaultArchetypeConfiguration();
        defaultArchetypeConfiguration.setBonitaVersion("7.11.2");
        defaultArchetypeConfiguration.setGroupId(packageName);
        defaultArchetypeConfiguration.setPageName(projectName);
        defaultArchetypeConfiguration.setPageDisplayName(name);
        defaultArchetypeConfiguration.setPathTemplate(pathTemplate);
        final CreateRestAPIExtensionProjectOperation operation = new CreateRestAPIExtensionProjectOperation(
                RepositoryManager.getInstance().getRepositoryStore(RestAPIExtensionRepositoryStore.class),
                MavenPlugin.getProjectConfigurationManager(),
                new ProjectImportConfiguration(),
                defaultArchetypeConfiguration);

        operation.run(AbstractRepository.NULL_PROGRESS_MONITOR);

        Job.getJobManager().join(CreateRestAPIExtensionProjectOperation.class, AbstractRepository.NULL_PROGRESS_MONITOR);

        StatusAssert.assertThat(operation.getStatus()).isOK();
        assertThat(operation.getProjects()).hasSize(1);
    }
}
