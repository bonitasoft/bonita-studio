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

import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.engine.BOSEngineManager;
import org.bonitasoft.studio.la.LivingApplicationPlugin;
import org.bonitasoft.studio.la.application.core.BonitaPagesRegistry;
import org.bonitasoft.studio.la.application.handler.NewApplicationHandler;
import org.bonitasoft.studio.la.application.repository.ApplicationRepositoryStore;
import org.bonitasoft.studio.swtbot.framework.application.BotApplicationWorkbenchWindow;
import org.bonitasoft.studio.swtbot.framework.la.BotApplicationEditor;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SWTBotJunit4ClassRunner.class)
public class ApplicationDescriptorOverviewIT {

    private final SWTGefBot bot = new SWTGefBot();

    private RepositoryAccessor repositoryAccessor;

    private boolean initPref;

    @Before
    public void init() throws Exception {
        repositoryAccessor = RepositoryManager.getInstance().getAccessor();
        BOSEngineManager.getInstance().start();

        initPref = LivingApplicationPlugin.getDefault().getPreferenceStore()
                .getBoolean(NewApplicationHandler.DO_NOT_SHOW_HELP_MESSAGE_DIALOG);
        LivingApplicationPlugin.getDefault().getPreferenceStore()
                .setValue(NewApplicationHandler.DO_NOT_SHOW_HELP_MESSAGE_DIALOG, true);
        BonitaPagesRegistry.getInstance().getPages();
    }

    @After
    public void resetPreference() {
        LivingApplicationPlugin.getDefault().getPreferenceStore()
                .setValue(NewApplicationHandler.DO_NOT_SHOW_HELP_MESSAGE_DIALOG, initPref);
    }

    @Test
    public void should_deploy_an_application_descriptor_from_overview() {
        final BotApplicationWorkbenchWindow workBenchBot = new BotApplicationWorkbenchWindow(bot);
        workBenchBot.newApplicationContainer()
                .add()
                .withToken("app1")
                .withDisplayName("My App")
                .withVersion("1.0.0")
                .create()
                .save()
                .close();
        final BotApplicationEditor botApplicationEditor = workBenchBot.openApplication()
                .select(NewApplicationHandler.DEFAULT_FILE_NAME + ".xml" + "  ../apps/app1")
                .open();
        botApplicationEditor.deploy();

        workBenchBot.deleteApplicationDescriptor()
                .select(NewApplicationHandler.DEFAULT_FILE_NAME + ".xml" + "  ../apps/app1").delete();
    }

    @Test
    public void should_delete_an_application_descriptor_from_overview() {
        final BotApplicationWorkbenchWindow workBenchBot = new BotApplicationWorkbenchWindow(bot);
        workBenchBot.newApplicationContainer()
                .add()
                .withToken("app2")
                .withDisplayName("My App")
                .withVersion("1.0.0")
                .create()
                .save()
                .close();
        final BotApplicationEditor botApplicationEditor = workBenchBot.openApplication()
                .select(NewApplicationHandler.DEFAULT_FILE_NAME + ".xml" + "  ../apps/app2")
                .open();
        botApplicationEditor.delete();
        assertTrue(repositoryAccessor.getRepositoryStore(ApplicationRepositoryStore.class)
                .getChildren().stream()
                .noneMatch(applicationFileStore -> Objects.equals(applicationFileStore.getName(),
                        NewApplicationHandler.DEFAULT_FILE_NAME + ".xml")));
    }

}
