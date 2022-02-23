/**
 * Copyright (C) 2014-2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.tests.connectors;

import org.assertj.core.api.Assertions;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.core.maven.AddDependencyOperation;
import org.bonitasoft.studio.swtbot.framework.application.BotApplicationWorkbenchWindow;
import org.bonitasoft.studio.swtbot.framework.diagram.BotProcessDiagramPerspective;
import org.bonitasoft.studio.swtbot.framework.diagram.general.connectors.BotAddConnectorDialog;
import org.bonitasoft.studio.swtbot.framework.diagram.general.connectors.BotConnectorsPropertySection;
import org.bonitasoft.studio.swtbot.framework.diagram.general.connectors.BotEditConnectorDialog;
import org.bonitasoft.studio.swtbot.framework.expression.BotScriptExpressionEditor;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SWTBotJunit4ClassRunner.class)
public class ConnectorEditedInAsingleCommandIT {

    private SWTGefBot bot = new SWTGefBot();

    @Before
    public void setUp() throws Exception {
        new AddDependencyOperation("org.bonitasoft.connectors", "bonita-connector-groovy", "1.1.4")
                .run(AbstractRepository.NULL_PROGRESS_MONITOR);
    }

    @Test
    public void testSingleCommandWhileEditingConnectorConfiguration() {
        final BotApplicationWorkbenchWindow botApplicationWorkbenchWindow = new BotApplicationWorkbenchWindow(bot);
        final BotProcessDiagramPerspective botProcessDiagramPerspective = botApplicationWorkbenchWindow
                .createNewDiagram();
        final BotConnectorsPropertySection selectConnectorsTab = botProcessDiagramPerspective.getDiagramPropertiesPart()
                .selectExecutionTab()
                .selectConnectorsInTab();
        final BotAddConnectorDialog addConnector = selectConnectorsTab.addConnector();
        addConnector.selectConnectorInCategory("Script", "Groovy");
        addConnector.next().setName("testSingleTransaction");
        new BotScriptExpressionEditor(bot, addConnector.next().editScript(0)).setName("scriptName")
                .setScriptContent("dummy content").ok();
        addConnector.finish();
        botProcessDiagramPerspective.activeProcessDiagramEditor().getGmfEditor().save();

        final BotEditConnectorDialog botEditConnectorDialog = selectConnectorsTab.editConnector(0,
                "Groovy (1.0.1)");
        botEditConnectorDialog.setName("updatedName");
        botEditConnectorDialog.next().finish();

        botApplicationWorkbenchWindow.editMenu().undo();

        Assertions.assertThat(botProcessDiagramPerspective.activeProcessDiagramEditor().getGmfEditor().isDirty())
                .isFalse();
    }

}
