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

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.stream.Collectors;

import org.apache.maven.model.Dependency;
import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.core.maven.MavenProjectHelper;
import org.bonitasoft.studio.common.repository.core.maven.model.ProjectMetadata;
import org.bonitasoft.studio.common.repository.provider.ExtendedConnectorDefinition;
import org.bonitasoft.studio.connectors.repository.ConnectorDefRepositoryStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.identity.actors.repository.ActorFilterDefRepositoryStore;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorParameter;
import org.bonitasoft.studio.model.process.Connector;
import org.bonitasoft.studio.swtbot.framework.application.BotApplicationWorkbenchWindow;
import org.bonitasoft.studio.swtbot.framework.conditions.AssertionCondition;
import org.bonitasoft.studio.swtbot.framework.projectExplorer.ProjectExplorerBot;
import org.bonitasoft.studio.swtbot.framework.rule.SWTGefBotRule;
import org.bonitasoft.studio.tests.importer.bos.ImportBOSArchiveWizardIT;
import org.bonitasoft.studio.tests.util.ProjectUtil;
import org.bonitasoft.studio.tests.util.ResourceMarkerHelper;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.keyboard.Keystrokes;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTableItem;
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
    public void setUp() throws CoreException {
        repositoryAccessor = RepositoryManager.getInstance().getAccessor();
        mavenProjectHelper = new MavenProjectHelper();
        ProjectUtil.removeUserExtensions();
    }

    @After
    public void cleanUp() throws CoreException {
        ProjectUtil.removeUserExtensions();
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

    @Test
    public void shouldUpdateEmailConnectorDefinitions() throws IOException {
        var connectorDefinitionRegistry = repositoryAccessor.getCurrentRepository()
                .getRepositoryStore(ConnectorDefRepositoryStore.class)
                .getResourceProvider()
                .getConnectorDefinitionRegistry();

        var worbenchBot = new BotApplicationWorkbenchWindow(bot);
        var botImportBOSDialog = worbenchBot.importBOSArchive()
                .setArchive(ImportBOSArchiveWizardIT.class.getResource("/EmailConnectorUpdate.bos"));

        assertThat(bot.textWithId(SWTBotConstants.SWTBOT_ID_NEW_PROJECT_NAME_TEXT).getText())
                .isEqualTo("EmailConnectorUpdate");
        assertThat(bot.textWithId(SWTBotConstants.SWTBOT_ID_NEW_PROJECT_VERSION_TEXT).getText())
                .isEqualTo("1.0.0-SNAPSHOT");
        assertThat(bot.textWithId(SWTBotConstants.SWTBOT_ID_NEW_PROJECT_GROUPID_TEXT).getText())
                .isEqualTo("org.bonitasoft.test");
        assertThat(bot.textWithId(SWTBotConstants.SWTBOT_ID_NEW_PROJECT_ARTIFACTID_TEXT).getText())
                .isEqualTo("email-connector-update");
        assertThat(bot.textWithId(SWTBotConstants.SWTBOT_ID_NEW_PROJECT_DESCRIPTION_TEXT).getText())
                .isEqualTo("A test project for connector update");

        botImportBOSDialog
                .currentRepository()
                .next()
                .next()
                .importArchive();

        var localDepStore = repositoryAccessor.getCurrentRepository().getLocalDependencyStore();
        assertThat(localDepStore
                .dependencyPath(dependency("org.bonitasoft.connectors", "bonita-connector-email", "1.1.0"))).exists();

        var projectDetailsBot = worbenchBot.openProjectDetails();
        projectDetailsBot
                .findExtensionCardByArtifactId("bonita-connector-email")
                .updateToLatest();

        bot.waitUntil(Conditions.shellIsActive(Messages.updateProcessesTitle), 20000);

        var updateConnectorItem = bot.tree().getAllItems()[0];
        assertThat(updateConnectorItem.getText()).isEqualTo(
                String.format(org.bonitasoft.studio.connector.model.i18n.Messages.definitionUpdateChangeDescription,
                        "email",
                        "1.0.0",
                        "1.2.0",
                        1));
        updateConnectorItem.expand();
        assertThat(updateConnectorItem.getNodes())
                .contains("new input 'trustCertificate' added. (Default value: false)")
                .contains("new input 'returnPath' added.");

        bot.button(IDialogConstants.ABORT_LABEL).click();
        bot.waitWhile(Conditions.shellIsActive(JFaceResources.getString("ProgressMonitorDialog.title")), 15000);

        assertThat(connectorDefinitionRegistry.find("email", "1.0.0")).isPresent();
        assertThat(connectorDefinitionRegistry.find("email", "1.2.0")).isEmpty();

        projectDetailsBot
                .findExtensionCardByArtifactId("bonita-connector-email")
                .updateToLatest();

        bot.waitUntil(Conditions.shellIsActive(Messages.updateProcessesTitle), 20000);

        bot.button(IDialogConstants.PROCEED_LABEL).click();
        bot.waitUntil(new AssertionCondition() {

            @Override
            protected void makeAssert() throws Exception {
                assertThat(connectorDefinitionRegistry.find("email", "1.0.0")).isEmpty();
                assertThat(connectorDefinitionRegistry.find("email", "1.2.0")).isPresent();
                var diagramStore = repositoryAccessor.getRepositoryStore(DiagramRepositoryStore.class);
                var process = diagramStore.findProcess("EmailConnectorUpdate", "1.0");
                assertThat(process).isNotNull();
                var connectors = ModelHelper.getAllElementOfTypeIn(process, Connector.class);
                assertThat(connectors).hasSize(1);
                var emailConnector = connectors.get(0);
                assertThat(emailConnector.getDefinitionId())
                        .isEqualTo("email");
                assertThat(emailConnector.getDefinitionVersion())
                        .isEqualTo("1.2.0");
                assertThat(emailConnector.getConfiguration().getParameters())
                        .extracting(ConnectorParameter::getKey)
                        .contains("returnPath", "trustCertificate");
            }
        });

        projectDetailsBot
                .findExtensionCardByArtifactId("bonita-connector-email")
                .remove();

        bot.waitUntil(Conditions.shellIsActive(Messages.updateProcessesTitle), 20000);
        var removeConnectorItem = bot.tree().getAllItems()[0];
        assertThat(removeConnectorItem.getText()).isEqualTo(
                String.format(Messages.definitionRemovedDescription,
                        "email",
                        "1.2.0",
                        1));
        bot.button(IDialogConstants.PROCEED_LABEL).click();
        bot.waitUntil(new AssertionCondition() {

            @Override
            protected void makeAssert() throws Exception {
                var diagramStore = repositoryAccessor.getRepositoryStore(DiagramRepositoryStore.class);
                var process = diagramStore.findProcess("EmailConnectorUpdate", "1.0");
                assertThat(process).isNotNull();
                var connectors = ModelHelper.getAllElementOfTypeIn(process, Connector.class);
                assertThat(connectors).isEmpty();
            }
        });

        worbenchBot.importBOSArchive()
                .setArchive(ImportBOSArchiveWizardIT.class.getResource("/EmailConnectorUpdate.bos"))
                .currentRepository()
                .next()
                .next()
                .importArchive();

        //        assertThat(importBOSDialog.canFinish()).isFalse();
        //        var dependenciesTree = importBOSDialog.dependenciesPreviewPage().getDependenciesTree();

        IProject project = repositoryAccessor.getCurrentRepository().getProject();
        bot.waitUntil(new AssertionCondition() {

            @Override
            protected void makeAssert() throws Exception {
                assertThat(ResourceMarkerHelper.findErrors(project)).isEmpty();
            }
        });

        projectDetailsBot = worbenchBot.openProjectDetails();
        var extensionCardBot = projectDetailsBot
                .findExtensionCardByArtifactId("bonita-connector-email");
        var maximizedCard = extensionCardBot.maximize();

        var findUsageBot = maximizedCard.findUsage("email", "1.0.0");
        assertThat(findUsageBot.getTree().getAllItems()).isNotEmpty();
        findUsageBot.cancel();
        maximizedCard.minimize();

        extensionCardBot.updateToLatest();
        bot.waitUntil(Conditions.shellIsActive(Messages.updateProcessesTitle), 20000);

        bot.button(IDialogConstants.IGNORE_LABEL).click();
        bot.waitUntil(new AssertionCondition() {

            @Override
            protected void makeAssert() throws Exception {
                assertThat(ResourceMarkerHelper.findErrors(project))
                        .contains(NLS.bind(
                                org.bonitasoft.studio.validation.i18n.Messages.Validation_ConnectorDefUpdateRequired,
                                "email",
                                "email--1.0.0"));
            }
        });

        var importDialogBot = worbenchBot.importBOSArchive()
                .setArchive(ImportBOSArchiveWizardIT.class.getResource("/EmailConnectorUpdate.bos"))
                .currentRepository()
                .next()
                .next();
        importDialogBot.waitUntilFinishIsDisabled();
        var dependenciesTable = importDialogBot.dependenciesPreviewPage().getDependenciesTable();
        SWTBotTableItem tableItem = dependenciesTable.getTableItem(0);
        assertThat(tableItem.getText(3)).isEqualTo(org.bonitasoft.studio.common.repository.Messages.conflicting);
        tableItem.click(3);
        bot.ccomboBox()
                .setSelection(String.format(org.bonitasoft.studio.common.repository.Messages.ourVersion, "1.1.0"));
        bot.ccomboBox().pressShortcut(Keystrokes.CR);
        importDialogBot.waitUntilFinishIsEnabled();
        importDialogBot.importArchive();
        
        new ProjectExplorerBot(bot).validate();
        bot.waitUntil(Conditions.shellIsActive(org.bonitasoft.studio.validation.i18n.Messages.validationTitle));
        bot.button(IDialogConstants.OK_LABEL).click();
        bot.waitUntil(new AssertionCondition() {

            @Override
            protected void makeAssert() throws Exception {
                assertThat(ResourceMarkerHelper.findErrors(project)).isEmpty();
            }
        });
    }

    private Dependency dependency(String groupId, String artifactId, String version) {
        var dep = new Dependency();
        dep.setGroupId(groupId);
        dep.setArtifactId(artifactId);
        dep.setVersion(version);
        return dep;
    }

}
