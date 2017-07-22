/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.tests.diagram;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.properties.i18n.Messages.activityType_serviceTask;

import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.bonitasoft.studio.swtbot.framework.application.BotApplicationWorkbenchWindow;
import org.bonitasoft.studio.swtbot.framework.application.BotOpenDiagramDialog;
import org.bonitasoft.studio.swtbot.framework.diagram.BotProcessDiagramPerspective;
import org.bonitasoft.studio.swtbot.framework.widget.BotTreeWidget;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestOpenDiagram {

    private boolean askRename;
    private boolean disablePopup;
    private SWTGefBot bot = new SWTGefBot();

    @After
    public void tearDown() throws Exception {
        bot.saveAllEditors();
        BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore()
                .setValue(BonitaPreferenceConstants.ASK_RENAME_ON_FIRST_SAVE, askRename);
        FileActionDialog.setDisablePopup(disablePopup);
    }

    @Before
    public void setUp() throws Exception {
        disablePopup = FileActionDialog.getDisablePopup();
        FileActionDialog.setDisablePopup(true);
        askRename = BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore()
                .getBoolean(BonitaPreferenceConstants.ASK_RENAME_ON_FIRST_SAVE);
        BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore()
                .setValue(BonitaPreferenceConstants.ASK_RENAME_ON_FIRST_SAVE, false);
    }

    @Test
    public void testDeleteDiagramWhenEditorIsDirty() {
        final BotApplicationWorkbenchWindow botApplicationWorkbenchWindow = new BotApplicationWorkbenchWindow(bot);
        final BotProcessDiagramPerspective botProcessDiagramPerspective = botApplicationWorkbenchWindow.createNewDiagram();
        botProcessDiagramPerspective.activeProcessDiagramEditor().selectDiagram();
        botProcessDiagramPerspective.getDiagramPropertiesPart().selectGeneralTab().selectDiagramTab()
                .setName("OpenDiagramDelete1");

        // set editor dirty
        botProcessDiagramPerspective.activeProcessDiagramEditor().selectElement("Step1");
        botProcessDiagramPerspective.getDiagramPropertiesPart().selectGeneralTab().selectGeneralTab()
                .setTaskType(activityType_serviceTask);

        final BotOpenDiagramDialog openDialog = botApplicationWorkbenchWindow.open();
        final BotTreeWidget diagramList = openDialog.diagramList();

        assertThat(diagramList.getSWTBotWidget().hasItems())
                .overridingErrorMessage("Error: no item in the table of Open Diagram Shell").isTrue();
        final int nbItems = diagramList.getSWTBotWidget().rowCount();
        final String diagramName = "OpenDiagramDelete1" + " (1.0)";
        diagramList.select(diagramName);
        openDialog.delete();

        assertThat(diagramList.getSWTBotWidget().rowCount()).isEqualTo(nbItems - 1);

        openDialog.cancel();
    }

}
