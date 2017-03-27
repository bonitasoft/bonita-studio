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

import org.bonitasoft.studio.swtbot.framework.application.BotApplicationWorkbenchWindow;
import org.bonitasoft.studio.swtbot.framework.la.DeleteApplicationWizardBot;
import org.bonitasoft.studio.swtbot.framework.rule.SWTGefBotRule;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SWTBotJunit4ClassRunner.class)
public class OpenExistingApplicationIT {

    private final SWTGefBot bot = new SWTGefBot();

    @Rule
    public SWTGefBotRule botRule = new SWTGefBotRule(bot);

    @Test
    public void should_create_and_open_applications() {
        final BotApplicationWorkbenchWindow workBenchBot = new BotApplicationWorkbenchWindow(bot);
        createApplications(workBenchBot);

        workBenchBot.openApplication().select("file1.xml").finish();
        final SWTBotEditor app1Editor = bot.activeEditor();
        assertEquals("file1.xml", app1Editor.getTitle());
        app1Editor.close();

        workBenchBot.openApplication()
                .select("file1.xml", "file2.xml")
                .finish();
        assertEquals(2, bot.editors().size());

        deleteApplications(workBenchBot);
    }

    private void deleteApplications(BotApplicationWorkbenchWindow workBenchBot) {
        final DeleteApplicationWizardBot deleteApplicationBot = workBenchBot.deleteApplicationDescriptor();
        deleteApplicationBot.select("file1.xml", "file2.xml")
                .delete();
    }

    private void createApplications(BotApplicationWorkbenchWindow workBenchBot) {
        workBenchBot.newApplicationContainer()
                .withFilename("file1")
                .finish();
        bot.activeEditor().close();

        workBenchBot.newApplicationContainer()
                .withFilename("file2")
                .finish();
        bot.activeEditor().close();
    }
}
