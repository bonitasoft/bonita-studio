/**
 * Copyright (C) 2016 Bonitasoft S.A.
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
package org.bonitasoft.studio.tests.applicationDescriptor;

import static org.junit.Assert.assertTrue;

import java.util.Objects;

import org.bonitasoft.engine.session.APISession;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.engine.BOSEngineManager;
import org.bonitasoft.studio.la.repository.ApplicationRepositoryStore;
import org.bonitasoft.studio.swtbot.framework.application.BotApplicationWorkbenchWindow;
import org.bonitasoft.studio.swtbot.framework.la.BotApplicationEditor;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ApplicationDescriptorOverviewIT {

    private final SWTGefBot bot = new SWTGefBot();

    private RepositoryAccessor repositoryAccessor;
    private IProgressMonitor monitor;
    private BOSEngineManager engineManager;
    private APISession session;

    @Before
    public void init() throws Exception {
        engineManager = BOSEngineManager.getInstance();
        session = engineManager.loginDefaultTenant(monitor);
        monitor = new NullProgressMonitor();
        repositoryAccessor = new RepositoryAccessor();
        repositoryAccessor.init();
    }

    @After
    public void closeSession() throws Exception {
        if (session != null) {
            engineManager.logoutDefaultTenant(session);
        }
    }

    @Test
    public void should_deploy_an_application_descriptor_from_overview() {
        BotApplicationWorkbenchWindow workBenchBot = new BotApplicationWorkbenchWindow(bot);
        createApplication(workBenchBot);
        BotApplicationEditor botApplicationEditor = workBenchBot.openApplication().open("app1.xml  ../apps/app1");
        botApplicationEditor.deploy();

        workBenchBot.deleteApplicationDescriptor().delete("app1.xml  ../apps/app1");
    }

    @Test
    public void should_delete_an_application_descriptor_from_overview() {
        BotApplicationWorkbenchWindow workBenchBot = new BotApplicationWorkbenchWindow(bot);
        createApplication(workBenchBot);
        BotApplicationEditor botApplicationEditor = workBenchBot.openApplication().open("app1.xml  ../apps/app1");
        botApplicationEditor.delete();
        assertTrue(repositoryAccessor.getRepositoryStore(ApplicationRepositoryStore.class)
                .getChildren().stream()
                .noneMatch(applicationFileStore -> Objects.equals(applicationFileStore.getName(), "app1.xml")));
    }

    private void createApplication(BotApplicationWorkbenchWindow workBenchBot) {
        workBenchBot.newApplication().createApplication("app1", "My App").close();
    }

}
