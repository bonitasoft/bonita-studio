/**
 * Copyright (C) 2021 Bonitasoft S.A.
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
package org.bonitasoft.studio.tests.project;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.core.ActiveOrganizationProvider;
import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.la.LivingApplicationPlugin;
import org.bonitasoft.studio.la.application.handler.NewApplicationHandler;
import org.bonitasoft.studio.la.application.repository.ApplicationFileStore;
import org.bonitasoft.studio.la.application.repository.ApplicationRepositoryStore;
import org.bonitasoft.studio.swtbot.framework.application.BotApplicationWorkbenchWindow;
import org.bonitasoft.studio.swtbot.framework.rule.SWTGefBotRule;
import org.bonitasoft.studio.tests.util.ProjectUtil;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SWTBotJunit4ClassRunner.class)
public class ProjectOverviewdIT {

    private final SWTGefBot bot = new SWTGefBot();

    @Rule
    public SWTGefBotRule rule = new SWTGefBotRule(bot);

    private static RepositoryAccessor repositoryAccessor;

    @BeforeClass
    public static void setUp() throws CoreException {
        repositoryAccessor = RepositoryManager.getInstance().getAccessor();
        ProjectUtil.cleanProject();
        LivingApplicationPlugin.getDefault().getPreferenceStore()
                .setValue(NewApplicationHandler.DO_NOT_SHOW_HELP_MESSAGE_DIALOG, true);
    }

    @AfterClass
    public static void cleanUp() throws CoreException {
        ProjectUtil.cleanProject();
    }

    @Test
    public void should_manage_diagrams_from_dashboard() {
        var worbenchBot = new BotApplicationWorkbenchWindow(bot);
        var dashboardBot = worbenchBot.openProjectDetails().toDashboardView();
        dashboardBot.createDiagram().closeActiveDiagram();
        List<DiagramFileStore> diagrams = repositoryAccessor.getRepositoryStore(DiagramRepositoryStore.class).getChildren();
        assertThat(diagrams).isNotEmpty();
        dashboardBot.openDiagram(diagrams.get(0).getName()).closeActiveDiagram();
    }

    @Test
    public void should_manage_bdm_from_dashboard() {
        var worbenchBot = new BotApplicationWorkbenchWindow(bot);
        var dashboardBot = worbenchBot.openProjectDetails().toDashboardView();
        dashboardBot.createBdm().close();
        dashboardBot.openBdm().close();
    }

    @Test
    public void should_manage_orga_from_dashboard() {
        var worbenchBot = new BotApplicationWorkbenchWindow(bot);
        var dashboardBot = worbenchBot.openProjectDetails().toDashboardView();
        var orgaEditorBot = dashboardBot.openActiveOrga();
        assertThat(bot.activeEditor().getTitle())
                .isEqualTo(new ActiveOrganizationProvider().getActiveOrganizationFileName());
        orgaEditorBot.close();
    }

    @Test
    public void should_manage_applications_from_dashboard() {
        var worbenchBot = new BotApplicationWorkbenchWindow(bot);
        var dashboardBot = worbenchBot.openProjectDetails().toDashboardView();
        dashboardBot.createApplicationFile().close();
        List<ApplicationFileStore> apps = repositoryAccessor
                .getRepositoryStore(ApplicationRepositoryStore.class).getChildren();
        assertThat(apps).isNotEmpty();
        dashboardBot.openApplicationFile(apps.get(0).getName()).close();
    }

}
