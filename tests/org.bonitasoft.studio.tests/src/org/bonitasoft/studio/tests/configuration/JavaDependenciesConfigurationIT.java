/**
 * Copyright (C) 2013-2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.tests.configuration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.bonitasoft.studio.configuration.i18n.Messages;
import org.bonitasoft.studio.swtbot.framework.SWTBotTestUtil;
import org.bonitasoft.studio.swtbot.framework.application.BotApplicationWorkbenchWindow;
import org.bonitasoft.studio.swtbot.framework.diagram.configuration.BotConfigureDialog;
import org.bonitasoft.studio.swtbot.framework.rule.SWTGefBotRule;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SWTBotJunit4ClassRunner.class)
public class JavaDependenciesConfigurationIT {

    private final SWTGefBot bot = new SWTGefBot();

    @Rule
    public SWTGefBotRule botRule = new SWTGefBotRule(bot);

    @Test
    public void testImportAndRunProcessWithLotOfDependencies() throws Exception {
        new BotApplicationWorkbenchWindow(bot).importBOSArchive()
                .setArchive(JavaDependenciesConfigurationIT.class.getResource("DiagramWithLotOfDependencies-1.0.bos"))
                .currentRepository()
                .next()
                .next()
                .finish();

        final IStatus status = SWTBotTestUtil.selectAndRunFirstPoolFound(bot);
        assertTrue(status.getMessage(), status.isOK());
    }

    @Test
    public void should_have_connector_dependencies() throws Exception {
        new BotApplicationWorkbenchWindow(bot).importBOSArchive()
                .setArchive(
                        JavaDependenciesConfigurationIT.class.getResource("CustomConnectorWithSources-1.0.bos"))
                .currentRepository()
                .next()
                .next()
                .finish();

        final BotConfigureDialog configureBot = new BotApplicationWorkbenchWindow(bot).configure();
        final List<String> items = configureBot.selectJavaDependencies().selectTreeView().items();
        assertThat(items).contains(Messages.connector, Messages.others);
        configureBot.cancel();
    }

}
