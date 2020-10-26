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

import org.bonitasoft.studio.swtbot.framework.application.BotApplicationWorkbenchWindow;
import org.bonitasoft.studio.swtbot.framework.conditions.AssertionCondition;
import org.bonitasoft.studio.swtbot.framework.diagram.BotProcessDiagramPerspective;
import org.bonitasoft.studio.swtbot.framework.diagram.general.connectors.BotAddConnectorDialog;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.keyboard.Keyboard;
import org.eclipse.swtbot.swt.finder.keyboard.KeyboardFactory;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SWTBotJunit4ClassRunner.class)
public class RichTextEditorIT {

    private SWTGefBot bot = new SWTGefBot();

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
        bot.browser().setFocus();
        bot.sleep(1000); // need to wait for CKEditor to be loaded
        Keyboard keyboard = KeyboardFactory.getSWTKeyboard();
        keyboard.typeText("Hello world");
        
        bot.cTabItem("Plain text").activate();
        bot.waitUntil(new AssertionCondition() {
            
            @Override
            protected void makeAssert() throws Exception {
                assertThat(bot.styledText().getText()).contains("<p>Hello world</p>");
            }
        });
        connectorDialog.finish();
    }
}
