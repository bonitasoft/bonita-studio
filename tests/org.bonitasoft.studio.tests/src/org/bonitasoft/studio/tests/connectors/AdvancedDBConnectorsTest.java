/*******************************************************************************
 * Copyright (C) 2010, 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.tests.connectors;

import java.io.UnsupportedEncodingException;

import org.assertj.core.api.Assertions;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.core.DatabaseHandler;
import org.bonitasoft.studio.engine.BOSEngineManager;
import org.bonitasoft.studio.swtbot.framework.application.BotApplicationWorkbenchWindow;
import org.bonitasoft.studio.swtbot.framework.diagram.BotProcessDiagramPerspective;
import org.bonitasoft.studio.swtbot.framework.diagram.execution.BotDatabaseAccesInformationWizardPage;
import org.bonitasoft.studio.swtbot.framework.diagram.execution.BotGraphicalQueryBuilderWizardpage;
import org.bonitasoft.studio.swtbot.framework.diagram.general.connectors.BotAddConnectorDialog;
import org.bonitasoft.studio.swtbot.framework.diagram.general.connectors.BotEditConnectorDialog;
import org.bonitasoft.studio.swtbot.framework.diagram.general.data.BotDataPropertySection;
import org.bonitasoft.studio.swtbot.framework.rule.SWTGefBotRule;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SWTBotJunit4ClassRunner.class)
public class AdvancedDBConnectorsTest {

    private final String connectorLabel = "H2 1.3 JDBC 4 database query";
    private final String query = "SELECT * FROM USER_ WHERE \"username\" = '${myData}' AND \"id\" = ${myIntData}";

    private final SWTGefBot bot = new SWTGefBot();

    @Rule
    public final SWTGefBotRule botRule = new SWTGefBotRule(bot);

    @Test
    public void testAdvancedDBConnectorsWithGraphicalQueryBuilderContainingVariable()
            throws Exception {
        BOSEngineManager.getInstance().start();
        final BotApplicationWorkbenchWindow botApplicationWorkbenchWindow = new BotApplicationWorkbenchWindow(bot);
        final BotProcessDiagramPerspective diagramPerspective = botApplicationWorkbenchWindow.createNewDiagram();
        final BotDataPropertySection poolDataTab = diagramPerspective.getDiagramPropertiesPart().selectDataTab()
                .selectPoolDataTab();
        poolDataTab.addData().setName("myData").finish();
        poolDataTab.addData().setName("myIntData").setType("Integer").finish();

        createConnectorWithQueryUsingData(botApplicationWorkbenchWindow, diagramPerspective);

        editConnectorAndCheckDataAreStillCorrect(diagramPerspective);
    }

    protected void editConnectorAndCheckDataAreStillCorrect(final BotProcessDiagramPerspective diagramPerspective) {
        final BotEditConnectorDialog editConnector = diagramPerspective.getDiagramPropertiesPart().selectExecutionTab()
                .selectConnectorsInTab()
                .editConnector(0, connectorLabel + " (1.0.0)");
        editConnector.next(/* Add or select database driver */).next(/* Dev time Database access information */);
        final BotDatabaseAccesInformationWizardPage daiwp = new BotDatabaseAccesInformationWizardPage(bot);
        daiwp.connectToDatabase().ok(false);

        editConnector.next(/* Runtime Database access information */).next(/* Graphical query builder */);

        final BotGraphicalQueryBuilderWizardpage graphicalQueryBuilderWizardpage = new BotGraphicalQueryBuilderWizardpage(
                bot);
        Assertions.assertThat(graphicalQueryBuilderWizardpage.getQuery())
                .contains("SELECT *")
                .contains("FROM USER_")
                .contains("WHERE \"username\" = '${myData}' AND \"id\" = ${myIntData}");

        editConnector.finish();
    }

    protected void createConnectorWithQueryUsingData(final BotApplicationWorkbenchWindow botApplicationWorkbenchWindow,
            final BotProcessDiagramPerspective diagramPerspective) throws UnsupportedEncodingException {
        final BotAddConnectorDialog connectorWizard = diagramPerspective.getDiagramPropertiesPart().selectExecutionTab()
                .selectConnectorsInTab()
                .addConnector();
        connectorWizard.selectConnector(connectorLabel);
        connectorWizard.next(/* General information */).setName("TestGraphicalEditor");
        connectorWizard.next(/* Add or select database driver */).next(/* Dev time Database access information */);

        final BotDatabaseAccesInformationWizardPage daiwp = new BotDatabaseAccesInformationWizardPage(bot);

        final DatabaseHandler databaseHandler = RepositoryManager.getInstance().getCurrentRepository().getDatabaseHandler();
        SWTBotShell activeShell = bot.activeShell();
        daiwp.connectToDatabase()
                .configure(
                        "org.h2.Driver",
                        String.format(
                                "jdbc:h2:file:%s/bonita_journal.db;MVCC=TRUE;DB_CLOSE_ON_EXIT=FALSE;IGNORECASE=TRUE;AUTO_SERVER=TRUE;",
                                databaseHandler.getDBLocation().getAbsolutePath()),
                        "sa",
                        "")
                .ok(true);
        activeShell.setFocus();
        final BotAddConnectorDialog wizardBot = connectorWizard.next(/* Runtime Database access information */);
        bot.waitUntil(Conditions.widgetIsEnabled(bot.button(IDialogConstants.NEXT_LABEL)), 30000); //Wait for connection to db
        wizardBot.next(/* Graphical query builder */);

        final BotGraphicalQueryBuilderWizardpage graphicalQueryBuilderWizardpage = new BotGraphicalQueryBuilderWizardpage(
                bot);
        graphicalQueryBuilderWizardpage.addTable("PUBLIC", "USER_");

        graphicalQueryBuilderWizardpage.writeQuery(query);

        connectorWizard.finish();

        botApplicationWorkbenchWindow.save();
    }

}
