/**
 * Copyright (C) 2017 Bonitasoft S.A.
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

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.stream.Collectors;

import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.la.LivingApplicationPlugin;
import org.bonitasoft.studio.la.application.handler.NewApplicationHandler;
import org.bonitasoft.studio.la.application.repository.ApplicationFileStore;
import org.bonitasoft.studio.la.application.repository.ApplicationRepositoryStore;
import org.bonitasoft.studio.swtbot.framework.application.BotApplicationWorkbenchWindow;
import org.bonitasoft.studio.swtbot.framework.la.OpenApplicationWizardBot;
import org.bonitasoft.studio.swtbot.framework.rule.SWTGefBotRule;
import org.bonitasoft.studio.ui.util.StringIncrementer;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SWTBotJunit4ClassRunner.class)
public class OpenExistingApplicationIT {

    private final SWTGefBot bot = new SWTGefBot();
    private boolean initPref;
    private RepositoryAccessor repositoryAccessor;

    @Rule
    public SWTGefBotRule botRule = new SWTGefBotRule(bot);

    @Before
    public void init() {
        repositoryAccessor = RepositoryManager.getInstance().getAccessor();
        initPref = LivingApplicationPlugin.getDefault().getPreferenceStore()
                .getBoolean(NewApplicationHandler.DO_NOT_SHOW_HELP_MESSAGE_DIALOG);
        LivingApplicationPlugin.getDefault().getPreferenceStore()
                .setValue(NewApplicationHandler.DO_NOT_SHOW_HELP_MESSAGE_DIALOG, true);
    }

    @After
    public void resetPreference() {
        LivingApplicationPlugin.getDefault().getPreferenceStore()
                .setValue(NewApplicationHandler.DO_NOT_SHOW_HELP_MESSAGE_DIALOG, initPref);
    }

    @Test
    public void should_create_and_open_applications() {
        final BotApplicationWorkbenchWindow workBenchBot = new BotApplicationWorkbenchWindow(bot);
        String applicationName1 = createApplication(workBenchBot);
        String applicationName2 = createApplication(workBenchBot);

        workBenchBot.openApplication().select(applicationName1 + ".xml").finish();
        final SWTBotEditor app1Editor = bot.activeEditor();
        assertEquals(applicationName1 + ".xml", app1Editor.getTitle());
        app1Editor.close();

        workBenchBot.openApplication()
                .select(applicationName1 + ".xml", applicationName2 + ".xml")
                .finish();
        assertEquals(2, bot.editors().size());

        deleteApplications(workBenchBot, applicationName1 + ".xml", applicationName2 + ".xml");
    }

    @Test
    public void should_rename_file_using_open_menu() {
        final BotApplicationWorkbenchWindow workBenchBot = new BotApplicationWorkbenchWindow(bot);
        String appName = createApplication(workBenchBot);
        OpenApplicationWizardBot openApplicationWizardBot = workBenchBot.openApplication();
        openApplicationWizardBot.select(appName + ".xml");
        openApplicationWizardBot.rename("custom_name").select("custom_name.xml").finish();
        workBenchBot.deleteApplicationDescriptor()
                .select("custom_name.xml")
                .delete();
    }

    private void deleteApplications(BotApplicationWorkbenchWindow workBenchBot, String... applications) {
        workBenchBot.deleteApplicationDescriptor()
                .select(applications)
                .delete();
    }

    private String createApplication(BotApplicationWorkbenchWindow workBenchBot) {
        String newName = findNewApplicationName();
        workBenchBot.newApplicationDescriptorFile();
        bot.activeEditor().close();
        return newName;
    }

    private String findNewApplicationName() {
        List<String> existingApplicationNameList = repositoryAccessor.getRepositoryStore(ApplicationRepositoryStore.class)
                .getChildren().stream().map(ApplicationFileStore::getDisplayName).collect(Collectors.toList());
        String newName = StringIncrementer.getNextIncrement(NewApplicationHandler.DEFAULT_FILE_NAME,
                existingApplicationNameList);
        return newName;
    }
}
