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

import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.engine.BOSEngineManager;
import org.bonitasoft.studio.rest.api.extension.core.repository.RestAPIExtensionRepositoryStore;
import org.bonitasoft.studio.swtbot.framework.projectExplorer.RestApiProjectExplorerBot;
import org.bonitasoft.studio.swtbot.framework.rule.SWTGefBotRule;
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
        repositoryAccessor = new RepositoryAccessor();
        repositoryAccessor.init();
        BOSEngineManager.getInstance().start();
    }

    @Test
    public void should_manage_rest_api_from_exporer() {
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
        
        RestAPIExtensionRepositoryStore restAPIExtensionRepositoryStore = repositoryAccessor.getRepositoryStore(RestAPIExtensionRepositoryStore.class);
        assertThat(restAPIExtensionRepositoryStore.getChildren()).isEmpty();
    }

    private void createRestApi(String packageName, String name, String projectName, String pathTemplate) {
        restAPIExplorerBot.createRestApiFromProjectFolder().withName(name)
                .withPackage(packageName)
                .withProjectName(projectName)
                .nextPage()
                .withPathTemplate(pathTemplate)
                .create();
    }
}
