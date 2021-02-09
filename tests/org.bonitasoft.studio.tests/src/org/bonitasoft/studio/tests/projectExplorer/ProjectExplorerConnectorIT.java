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

import java.util.Optional;

import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.swtbot.framework.BotDialog;
import org.bonitasoft.studio.swtbot.framework.projectExplorer.ConnectorDefinitionProjectExplorerBot;
import org.bonitasoft.studio.swtbot.framework.projectExplorer.ConnectorImplementationProjectExplorerBot;
import org.bonitasoft.studio.swtbot.framework.projectExplorer.ProjectExplorerBot;
import org.bonitasoft.studio.swtbot.framework.rule.SWTGefBotRule;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SWTBotJunit4ClassRunner.class)
public class ProjectExplorerConnectorIT {

    private static final String VERSION = "1.0.0";
    private SWTGefBot bot = new SWTGefBot();
    private ProjectExplorerBot projectExplorerBot;
    private RepositoryAccessor repositoryAccessor;

    @Rule
    public SWTGefBotRule botRule = new SWTGefBotRule(bot);

    @Before
    public void init() throws Exception {
        projectExplorerBot = new ProjectExplorerBot(bot);
        repositoryAccessor = new RepositoryAccessor();
        repositoryAccessor.init();
    }

    @Test
    public void should_manage_connectors_from_explorer() {
        ConnectorDefinitionProjectExplorerBot connectorDefBot = projectExplorerBot.connectorDefinition();
        ConnectorImplementationProjectExplorerBot connectorImplBot = projectExplorerBot.connectorImplementation();

        String defid1 = "projectExplorerConnectorDef";
        String defid2 = "projectExplorerConnectorDef2";
        String implemId1 = "projectExplorerImplementationId";
        String implemId2 = "projectExplorerImplementationId2";
        String className1 = "implemClassName1";
        String className2 = "implemClassName2";

        projectExplorerBot.newConnectorDefinition()
                .withDefinitionId(defid1)
                .withDefinitionVersion(VERSION)
                .finish();
        connectorDefBot.getConnectorDefFolderTreeItem().expand();
        connectorDefBot.getConnectorDefFolderTreeItem().collapse();
        connectorDefBot.openConnectorDefinition(defid1, VERSION).cancel();
        connectorDefBot.renameConnectorDefinition(defid1, VERSION).withDefinitionId(defid2).finish();
        connectorDefBot.newConnectorDefinition()
                .withDefinitionId(defid1)
                .withDefinitionVersion(VERSION)
                .finish();

        projectExplorerBot.newConnectorImplementation()
                .selectDefinition(defid1)
                .nextPage()
                .withImplementationId(implemId1)
                .withImplementationVersion(VERSION)
                .withClassName(className1)
                .finish();
        projectExplorerBot.waitUntilActiveEditorTitleIs(className1, Optional.of(".java"));
        connectorImplBot.openConnectorImplementation(implemId1, VERSION).cancel();
        connectorImplBot.newConnectorImplementation()
                .selectDefinition(defid1)
                .nextPage()
                .withImplementationId(implemId2)
                .withImplementationVersion(VERSION)
                .withClassName(className2)
                .finish();
        projectExplorerBot.waitUntilActiveEditorTitleIs(className2, Optional.of(".java"));

        BotDialog botDialog = connectorImplBot.exportConnectorImplementation();
        assertThat(bot.table().containsItem(toFullImplementationName(implemId1, className1))).isTrue();
        botDialog.cancel();

        botDialog = connectorImplBot.exportConnectorImplementation(implemId1, VERSION);
        assertThat(bot.table().rowCount()).isEqualTo(1);
        assertThat(bot.table().containsItem(toFullImplementationName(implemId1, className1))).isTrue();
        botDialog.cancel();

        botDialog = connectorDefBot.exportConnectorFromDefinition(defid1, VERSION);
        assertThat(bot.table().rowCount()).isEqualTo(2);
        assertThat(bot.table().containsItem(toFullImplementationName(implemId1, className1))).isTrue();
        assertThat(bot.table().containsItem(toFullImplementationName(implemId2, className2))).isTrue();
        botDialog.cancel();

        connectorImplBot.deleteConnectorImplementation(implemId2, VERSION);
        connectorImplBot.deleteConnectorImplementation(implemId1, VERSION);
        connectorDefBot.deleteConnectorDefinition(defid2, VERSION);
        connectorDefBot.deleteConnectorDefinition(defid1, VERSION);
    }

    private String toFullImplementationName(String implemId, String className) {
        return String.format("%s (%s) -- org.mycompany.connector.%s", implemId, VERSION, className);
    }

}
