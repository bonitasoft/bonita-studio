/**
 * Copyright (C) 2019 Bonitasoft S.A.
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

import static org.assertj.core.api.Assertions.assertThat;

import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.core.maven.AddDependencyOperation;
import org.bonitasoft.studio.swtbot.framework.application.BotApplicationWorkbenchWindow;
import org.bonitasoft.studio.swtbot.framework.conditions.AssertionCondition;
import org.bonitasoft.studio.swtbot.framework.diagram.BotProcessDiagramPerspective;
import org.bonitasoft.studio.swtbot.framework.diagram.general.connectors.BotAddConnectorDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.keyboard.Keyboard;
import org.eclipse.swtbot.swt.finder.keyboard.KeyboardFactory;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotBrowser;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

@Ignore
@RunWith(SWTBotJunit4ClassRunner.class)
public class RichTextEditorIT {

    private SWTGefBot bot = new SWTGefBot();

    @Before
    public void setUp() throws Exception {
        new AddDependencyOperation("org.bonitasoft.connectors", "bonita-connector-email", "1.3.0")
                .run(AbstractRepository.NULL_PROGRESS_MONITOR);
    }

    @Test
    public void should_rich_text_editor_write_html_output() throws Exception {
        BotApplicationWorkbenchWindow botApplicationWorkbenchWindow = new BotApplicationWorkbenchWindow(bot);
        BotProcessDiagramPerspective diagramEditor = botApplicationWorkbenchWindow
                .createNewDiagram();

        BotAddConnectorDialog connectorDialog = diagramEditor.getDiagramPropertiesPart()
                .selectExecutionTab()
                .selectConnectorsInTab()
                .addConnector();

        connectorDialog.selectConnectorInCategory("Messaging", "Email (SMTP)");
        connectorDialog.next();
        connectorDialog.setName("email notif");
        connectorDialog.next(); // Connection page
        connectorDialog.next(); // Adress page
        bot.text(0).setText("from");
        bot.text(1).setText("to");
        connectorDialog.next(); // Message page

        bot.text(0).setText("Title");

        assertThat(bot.cTabItem("Rich text").isActive()).isTrue();
        bot.sleep(1000); // need to wait for CKEditor to be loaded

        SWTBotBrowser browser = bot.browser();
        Display.getDefault().syncExec(() -> {
            Browser control = browser.widget;
            Event event = new Event();
            event.type = SWT.MouseMove;
            Point textLocation = control.toDisplay(10, 150);
            event.x = textLocation.x;
            event.y = textLocation.y;
            control.getDisplay().post(event);

            event = new Event();
            event.type = SWT.MouseDown;
            event.button = 1;
            control.getDisplay().post(event);

            event = new Event();
            event.type = SWT.MouseUp;
            event.button = 1;
            control.getDisplay().post(event);
        });
        Keyboard keyboard = KeyboardFactory.getSWTKeyboard();
        keyboard.typeText("HELLO WORLD");
        bot.cTabItem("Plain text").activate();
        bot.waitUntil(new AssertionCondition() {

            @Override
            protected void makeAssert() throws Exception {
                assertThat(bot.styledText().getText()).contains("<p>HELLO WORLD</p>");
            }
        });
        connectorDialog.finish();
    }
}
