/**
 * Copyright (C) 2021 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.tests.project;

import static java.util.function.Predicate.not;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Collectors;

import org.apache.maven.model.Model;
import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.core.maven.MavenProjectHelper;
import org.bonitasoft.studio.common.repository.core.maven.RemoveDependencyOperation;
import org.bonitasoft.studio.common.repository.core.maven.model.ProjectDefaultConfiguration;
import org.bonitasoft.studio.common.repository.core.maven.model.ProjectMetadata;
import org.bonitasoft.studio.common.repository.provider.ExtendedConnectorDefinition;
import org.bonitasoft.studio.connectors.repository.ConnectorDefRepositoryStore;
import org.bonitasoft.studio.identity.actors.repository.ActorFilterDefRepositoryStore;
import org.bonitasoft.studio.swtbot.framework.application.BotApplicationWorkbenchWindow;
import org.bonitasoft.studio.swtbot.framework.conditions.AssertionCondition;
import org.bonitasoft.studio.swtbot.framework.rule.SWTGefBotRule;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SWTBotJunit4ClassRunner.class)
public class ProjectCompositionIT {

    private final SWTGefBot bot = new SWTGefBot();

    @Rule
    public SWTGefBotRule rule = new SWTGefBotRule(bot);

    private RepositoryAccessor repositoryAccessor;

    private MavenProjectHelper mavenProjectHelper;

    @Before
    public void setUp() throws Exception {
        repositoryAccessor = RepositoryManager.getInstance().getAccessor();
        mavenProjectHelper = new MavenProjectHelper();
        removeExtensions();
    }

    @After
    public void cleanUp() throws Exception {
        removeExtensions();
    }

    private void removeExtensions() throws Exception {
        IProject project = repositoryAccessor.getCurrentRepository().getProject();
        Model mavenModel = mavenProjectHelper.getMavenModel(project);
        var dependenciesToRemove = mavenModel.getDependencies()
                .stream()
                .filter(not(ProjectDefaultConfiguration::isInternalDependency))
                .collect(Collectors.toList());
        if (!dependenciesToRemove.isEmpty()) {
            new RemoveDependencyOperation(dependenciesToRemove).run(new NullProgressMonitor());
        }
    }

    @Test
    public void shouldEditProjectMetadata() {
        var worbenchBot = new BotApplicationWorkbenchWindow(bot);

        var description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
        var projectDetailsBot = worbenchBot.openProjectDetails();
        projectDetailsBot
                .editProjectMetadata()
                .setVersion("2.0.0-SNAPSHOT")
                .setDescription(description)
                .modify();

        var metadata = ProjectMetadata.read(repositoryAccessor.getCurrentRepository().getProject());
        assertThat(metadata.getVersion()).isEqualTo("2.0.0-SNAPSHOT");
        assertThat(metadata.getDescription()).isEqualTo(description);
        assertThat(bot.styledTextWithId(SWTBotConstants.SWTBOT_ID_PROJECT_DETAILS_TITLE).getText())
                .isEqualTo(String.format("%s %s", metadata.getName(), metadata.getVersion()));
        assertThat(bot.labelWithId(SWTBotConstants.SWTBOT_ID_PROJECT_DETAILS_DESCRIPTION).getText())
                .isEqualTo(description);
    }

    @Test
    public void shouldAddAndUpdateExtensionsFromMarketplace() throws CoreException {
        var connectorDefinitionRegistry = repositoryAccessor.getCurrentRepository()
                .getRepositoryStore(ConnectorDefRepositoryStore.class)
                .getResourceProvider()
                .getConnectorDefinitionRegistry();
        var actorFilterDefinitionRegistry = repositoryAccessor.getCurrentRepository()
                .getRepositoryStore(ActorFilterDefRepositoryStore.class)
                .getResourceProvider()
                .getConnectorDefinitionRegistry();

        var worbenchBot = new BotApplicationWorkbenchWindow(bot);

        var projectDetailsBot = worbenchBot.openProjectDetails();
        projectDetailsBot
                .openMarketplace()
                .selectConnectorExtensionType()
                .selectExtensionByArtifactId("bonita-connector-email")
                .selectActorFilterExtensionType()
                .selectExtensionByArtifactId("bonita-actorfilter-initiator")
                .install();

        bot.waitUntil(new AssertionCondition() {

            @Override
            protected void makeAssert() throws Exception {
                assertThat(connectorDefinitionRegistry.getDefinitions().stream()
                        .map(ExtendedConnectorDefinition::getId))
                                .anyMatch("email"::equals);
                assertThat(actorFilterDefinitionRegistry.getDefinitions().stream()
                        .map(ExtendedConnectorDefinition::getId))
                                .anyMatch("bonita-actorfilter-initiator"::equals);
            }
        });

        projectDetailsBot
                .addConnectorExtension()
                .manual()
                .setGroupId("org.bonitasoft.connectors")
                .setArtifactId("bonita-connector-groovy")
                .setVersion("1.1.2")
                .finish();

        projectDetailsBot
                .findExtensionCardByArtifactId("bonita-connector-groovy")
                .updateToLatest();
        bot.waitWhile(Conditions.shellIsActive(JFaceResources.getString("ProgressMonitorDialog.title")), 15000);

        var project = repositoryAccessor.getCurrentRepository().getProject();
        var mavenModel = mavenProjectHelper.getMavenModel(project);
        var groovyConnectorDependencies = mavenModel.getDependencies().stream()
                .filter(d -> d.getArtifactId().equals("bonita-connector-groovy"))
                .collect(Collectors.toList());
        assertThat(groovyConnectorDependencies)
                .hasSize(1)
                .extracting("version").doesNotContain("1.1.2");

        projectDetailsBot
                .findExtensionCardByArtifactId("bonita-connector-groovy")
                .remove();
        bot.waitWhile(Conditions.shellIsActive(JFaceResources.getString("ProgressMonitorDialog.title")), 15000);
        
        bot.waitUntil(new AssertionCondition() {

            @Override
            protected void makeAssert() throws Exception {
                assertThat(connectorDefinitionRegistry.getDefinitions().stream()
                        .map(ExtendedConnectorDefinition::getId))
                                .noneMatch("scripting-groovy-script"::equals);
            }
        }, 15000);
       
    }

}
