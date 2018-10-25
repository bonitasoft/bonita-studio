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
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.bonitasoft.engine.api.ProcessAPI;
import org.bonitasoft.engine.bpm.process.ActivationState;
import org.bonitasoft.engine.bpm.process.ProcessDeploymentInfo;
import org.bonitasoft.engine.bpm.process.ProcessDeploymentInfoSearchDescriptor;
import org.bonitasoft.engine.search.SearchOptionsBuilder;
import org.bonitasoft.engine.session.APISession;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.engine.BOSEngineManager;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.swtbot.framework.projectExplorer.ProjectExplorerBot;
import org.bonitasoft.studio.swtbot.framework.projectExplorer.diagram.DiagramProjectExplorerBot;
import org.bonitasoft.studio.swtbot.framework.rule.SWTGefBotRule;
import org.bonitasoft.studio.ui.util.StringIncrementer;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotMenu;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SWTBotJunit4ClassRunner.class)
public class ProjectExplorerDiagramIT {

    private static final String DEFAULT_VERSION = "1.0";

    private SWTGefBot bot = new SWTGefBot();
    private ProjectExplorerBot projectExplorerBot;
    private DiagramProjectExplorerBot diagramBot;
    private RepositoryAccessor repositoryAccessor;

    @Rule
    public SWTGefBotRule botRule = new SWTGefBotRule(bot);

    @Before
    public void init() throws Exception {
        projectExplorerBot = new ProjectExplorerBot(bot);
        diagramBot = new DiagramProjectExplorerBot(bot);
        repositoryAccessor = new RepositoryAccessor();
        repositoryAccessor.init();
        BOSEngineManager.getInstance().start();
    }

    @Test
    public void should_manage_diagram_from_explorer() throws Exception {
        createDiagramFromProject();
        projectExplorerBot.showExplorerView();
        String diagramName = createDiagramFromDiagramFolder(diagramBot);
        openDiagramFromExplorer(diagramName);
        duplicateAndDeleteDiagramFromExplorer(diagramName);
        String newName = diagramName + "Renamed";
        String newPoolName = "poolTestExplorer";
        renameDiagramFromExplorer(diagramName, newName, newPoolName);
        deployDiagramFromExplorer(newName, newPoolName);
        validateBuildMenuAvailable(newName, newPoolName);
        validateRunMenuAvailable(newName, newPoolName);
    }

    private void validateRunMenuAvailable(String diagramName, String poolName) {
        validateMenuAvailable(diagramName, poolName, "Run");
    }

    private void validateBuildMenuAvailable(String diagramName, String poolName) {
        validateMenuAvailable(diagramName, poolName, "Build");
    }

    private void validateMenuAvailable(String diagramName, String poolName, String action) {
        SWTBotTreeItem diagramTreeItem = diagramBot.getDiagramTreeItem(diagramName, DEFAULT_VERSION);
        bot.waitUntil(projectExplorerBot.contextMenuAvailable(diagramTreeItem, action));
        SWTBotMenu contextMenu = diagramTreeItem.contextMenu(action);
        assertThat(contextMenu.menuItems()).hasSize(1);
        assertThat(contextMenu.menuItems().get(0)).isEqualTo(poolName);
    }

    private void deployDiagramFromExplorer(String diagramName, String poolName) throws Exception {
        diagramBot.deploy(diagramName, DEFAULT_VERSION);
        BOSEngineManager manager = BOSEngineManager.getInstance();
        APISession session = null;
        try {
            session = manager.loginDefaultTenant(Repository.NULL_PROGRESS_MONITOR);
            ProcessAPI processAPI = BOSEngineManager.getInstance().getProcessAPI(session);
            List<ProcessDeploymentInfo> result = processAPI
                    .searchProcessDeploymentInfos(new SearchOptionsBuilder(0, 10)
                            .filter(ProcessDeploymentInfoSearchDescriptor.DISPLAY_NAME, poolName).done())
                    .getResult();
            assertThat(result).hasSize(1);
            assertThat(result.get(0).getDisplayName()).isEqualTo(poolName);
            assertThat(result.get(0).getActivationState()).isEqualTo(ActivationState.ENABLED);
        } finally {
            manager.logoutDefaultTenant(session);
        }
    }

    private void renameDiagramFromExplorer(String diagramName, String newName, String newPoolName) {
        diagramBot.renameDiagram(diagramName, newName, DEFAULT_VERSION, newPoolName);
        projectExplorerBot.waitUntilActiveEditorTitleIs(getDiagramEditorTitle(newName), Optional.empty());
        assertDiagramExist(newName);
        assertDiagramDoesntExist(diagramName);
    }

    private void duplicateAndDeleteDiagramFromExplorer(String diagramName) {
        diagramBot.duplicateDiagram(diagramName, DEFAULT_VERSION);
        String newName = diagramName + "-copy";
        projectExplorerBot.waitUntilActiveEditorTitleIs(getDiagramEditorTitle(newName), Optional.empty());
        assertDiagramExist(newName);
        diagramBot.deleteDiagram(newName, DEFAULT_VERSION);
        assertDiagramDoesntExist(newName);
    }

    private void openDiagramFromExplorer(String diagramName) {
        bot.activeEditor().close();
        diagramBot.openDiagram(diagramName, DEFAULT_VERSION);
        projectExplorerBot.waitUntilActiveEditorTitleIs(getDiagramEditorTitle(diagramName), Optional.empty());
    }

    private String createDiagramFromDiagramFolder(DiagramProjectExplorerBot diagramBot) {
        String newDiagramName = getNewDiagramName();
        diagramBot.newDiagram();
        projectExplorerBot.waitUntilActiveEditorTitleIs(getDiagramEditorTitle(newDiagramName), Optional.empty());
        assertDiagramExist(newDiagramName);
        return newDiagramName;
    }

    private void createDiagramFromProject() {
        String newDiagramName = getNewDiagramName();
        projectExplorerBot.newDiagram();
        projectExplorerBot.waitUntilActiveEditorTitleIs(getDiagramEditorTitle(newDiagramName), Optional.empty());
        assertDiagramExist(newDiagramName);
    }

    private String getDiagramEditorTitle(String name) {
        return String.format("%s (%s)", name, DEFAULT_VERSION);
    }

    private String getNewDiagramName() {
        List<String> existingDiagrams = repositoryAccessor.getRepositoryStore(DiagramRepositoryStore.class).getChildren()
                .stream()
                .map(DiagramFileStore::getContent)
                .filter(diagram -> Objects.equals(diagram.getVersion(), DEFAULT_VERSION))
                .map(MainProcess::getName)
                .collect(Collectors.toList());
        return StringIncrementer.getIncrementedString(
                org.bonitasoft.studio.diagram.custom.i18n.Messages.newFilePrefix,
                existingDiagrams);
    }

    private void assertDiagramExist(String diagramName) {
        String fileName = String.format("%s-%s.proc", diagramName, DEFAULT_VERSION);
        assertThat(repositoryAccessor.getRepositoryStore(DiagramRepositoryStore.class).getChildren())
                .flatExtracting(DiagramFileStore::getName)
                .contains(fileName);
    }

    private void assertDiagramDoesntExist(String diagramName) {
        String fileName = String.format("%s-%s.proc", diagramName, DEFAULT_VERSION);
        assertThat(repositoryAccessor.getRepositoryStore(DiagramRepositoryStore.class).getChildren())
                .flatExtracting(DiagramFileStore::getName)
                .doesNotContain(fileName);
    }

}
