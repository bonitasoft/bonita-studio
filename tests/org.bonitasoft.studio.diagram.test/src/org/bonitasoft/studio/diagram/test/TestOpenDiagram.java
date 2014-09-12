package org.bonitasoft.studio.diagram.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.properties.i18n.Messages.activityType_serviceTask;

import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.bonitasoft.studio.swtbot.framework.application.BotApplicationWorkbenchWindow;
import org.bonitasoft.studio.swtbot.framework.application.BotOpenDiagramDialog;
import org.bonitasoft.studio.swtbot.framework.diagram.BotProcessDiagramPerspective;
import org.bonitasoft.studio.swtbot.framework.widget.BotTreeWidget;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.gef.finder.SWTBotGefTestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class TestOpenDiagram extends SWTBotGefTestCase {

    private boolean askRename;
    private boolean disablePopup;


    @Override
    @After
    public void tearDown() throws Exception {
        bot.saveAllEditors();
        BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore().setValue(BonitaPreferenceConstants.ASK_RENAME_ON_FIRST_SAVE, askRename);
        FileActionDialog.setDisablePopup(disablePopup);
    }

    @Before
    @Override
    public void setUp() throws Exception {
        super.setUp();
        disablePopup = FileActionDialog.getDisablePopup();
        FileActionDialog.setDisablePopup(true);
        askRename = BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore().getBoolean(BonitaPreferenceConstants.ASK_RENAME_ON_FIRST_SAVE);
        BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore().setValue(BonitaPreferenceConstants.ASK_RENAME_ON_FIRST_SAVE, false);
    }


    @Test
    public void testDeleteDiagramWhenEditorIsDirty(){
        final BotApplicationWorkbenchWindow botApplicationWorkbenchWindow = new BotApplicationWorkbenchWindow(bot);
        final BotProcessDiagramPerspective botProcessDiagramPerspective = botApplicationWorkbenchWindow.createNewDiagram();
        botProcessDiagramPerspective.activeProcessDiagramEditor().selectDiagram();
        botProcessDiagramPerspective.getDiagramPropertiesPart().selectGeneralTab().selectDiagramTab().setName("OpenDiagramDelete1");


        // set editor dirty
        botProcessDiagramPerspective.activeProcessDiagramEditor().selectElement("Step1");
        botProcessDiagramPerspective.getDiagramPropertiesPart().selectGeneralTab().selectGeneralTab().setTaskType(activityType_serviceTask);

        final BotOpenDiagramDialog openDialog = botApplicationWorkbenchWindow.open();
        final BotTreeWidget diagramList = openDialog.diagramList();

        assertThat(diagramList.getSWTBotWidget().hasItems()).overridingErrorMessage("Error: no item in the table of Open Diagram Shell").isTrue();
        final int nbItems = diagramList.getSWTBotWidget().rowCount();
        final String diagramName = "OpenDiagramDelete1"+" (1.0)";
        diagramList.select(diagramName);
        openDialog.delete();

        assertThat(diagramList.getSWTBotWidget().rowCount()).isEqualTo(nbItems - 1);

        openDialog.cancel();

        for(final SWTBotEditor editor : bot.editors()){
            assertThat(editor.getTitle().equals(diagramName)).overridingErrorMessage("Error: Editor " + diagramName + " should not be in the tree.").isFalse();
        }

    }


}
