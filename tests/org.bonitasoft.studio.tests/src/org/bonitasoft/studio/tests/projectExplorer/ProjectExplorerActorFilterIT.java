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

import org.bonitasoft.studio.swtbot.framework.BotDialog;
import org.bonitasoft.studio.swtbot.framework.projectExplorer.ActorFilterDefinitionProjectExplorerBot;
import org.bonitasoft.studio.swtbot.framework.projectExplorer.ActorFilterImplementationProjectExplorerBot;
import org.bonitasoft.studio.swtbot.framework.projectExplorer.ProjectExplorerBot;
import org.bonitasoft.studio.swtbot.framework.rule.SWTGefBotRule;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class ProjectExplorerActorFilterIT {

    private static final String VERSION = "1.0.0";
    private SWTGefBot bot = new SWTGefBot();
    private ProjectExplorerBot projectExplorerBot;

    @Rule
    public SWTGefBotRule botRule = new SWTGefBotRule(bot);

    @Before
    public void init() throws Exception {
        projectExplorerBot = new ProjectExplorerBot(bot);
    }

    @Test
    public void should_manage_connectors_from_explorer() {
        ActorFilterDefinitionProjectExplorerBot actorFilterDefBot = projectExplorerBot.actorFilterDefinition();
        ActorFilterImplementationProjectExplorerBot actorFilterImplBot = projectExplorerBot.actorFilterImplementation();

        String defid1 = "projectExplorerActorDef";
        String defid2 = "projectExplorerActorDef2";
        String implemId1 = "projectExplorerActorImplementationId";
        String implemId2 = "projectExplorerActorImplementationId2";
        String className1 = "actorImplemClassName1";
        String className2 = "actoIimplemClassName2";

        projectExplorerBot.newActorFilterDefinition()
                .withDefinitionId(defid1)
                .withDefinitionVersion(VERSION)
                .finish();
        actorFilterDefBot.getActorFilterDefFolderTreeItem().expand();
        actorFilterDefBot.getActorFilterDefFolderTreeItem().collapse();
        actorFilterDefBot.openActorFilterDefinition(defid1, VERSION).cancel();
        actorFilterDefBot.renameActorFilterDefinition(defid1, VERSION).withDefinitionId(defid2).finish();
        actorFilterDefBot.newConnectorDefinition()
                .withDefinitionId(defid1)
                .withDefinitionVersion(VERSION)
                .finish();

        projectExplorerBot.newActorFilterImplementation()
                .selectDefinition(defid1)
                .nextPage()
                .withImplementationId(implemId1)
                .withImplementationVersion(VERSION)
                .withClassName(className1)
                .finish();
        projectExplorerBot.waitUntilActiveEditorTitleIs(className1, Optional.of(".java"));
        actorFilterImplBot.openActorFilterImplementation(implemId1, VERSION).cancel();
        actorFilterImplBot.newActorFilterImplementation()
                .selectDefinition(defid1)
                .nextPage()
                .withImplementationId(implemId2)
                .withImplementationVersion(VERSION)
                .withClassName(className2)
                .finish();
        projectExplorerBot.waitUntilActiveEditorTitleIs(className2, Optional.of(".java"));

        BotDialog botDialog = actorFilterImplBot.exportActorFilterImplementation();
        assertThat(bot.table().containsItem(toFullImplementationName(implemId1, className1))).isTrue();
        botDialog.cancel();

        botDialog = actorFilterImplBot.exportActorFilterImplementation(implemId1, VERSION);
        assertThat(bot.table().rowCount()).isEqualTo(1);
        assertThat(bot.table().containsItem(toFullImplementationName(implemId1, className1))).isTrue();
        botDialog.cancel();

        botDialog = actorFilterDefBot.exportActorFilterFromDefinition(defid1, VERSION);
        assertThat(bot.table().rowCount()).isEqualTo(2);
        assertThat(bot.table().containsItem(toFullImplementationName(implemId1, className1))).isTrue();
        assertThat(bot.table().containsItem(toFullImplementationName(implemId2, className2))).isTrue();
        botDialog.cancel();

        actorFilterImplBot.deleteActorFilterImplementation(implemId2, VERSION);
        actorFilterImplBot.deleteActorFilterImplementation(implemId1, VERSION);
        actorFilterDefBot.deleteActorFilterDefinition(defid2, VERSION);
        actorFilterDefBot.deleteActorFilterDefinition(defid1, VERSION);
    }

    private String toFullImplementationName(String implemId, String className) {
        return String.format("%s (%s) -- org.mycompany.connector.%s", implemId, VERSION, className);
    }

}
