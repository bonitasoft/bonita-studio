/**
 * Copyright (C) 2009 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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

package org.bonitasoft.studio.test.swtbot.suite;

import org.bonitasoft.studio.common.extension.BonitaStudioExtensionRegistryManager;
import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.connector.model.definition.provider.ConnectorEditPlugin;
import org.bonitasoft.studio.connector.model.definition.wizard.AbstractDefinitionWizard;
import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.bonitasoft.studio.tests.IHeapDumper;
import org.bonitasoft.studio.tests.actors.ActorDefinitionTranslationsTest;
import org.bonitasoft.studio.tests.actors.ActorFilterDefinitionTest;
import org.bonitasoft.studio.tests.actors.ActorFilterDefinitionWizardPageTest;
import org.bonitasoft.studio.tests.actors.ActorFilterEditionTest;
import org.bonitasoft.studio.tests.actors.ActorFilterImplementationTest;
import org.bonitasoft.studio.tests.actors.OrganizationCreationTest;
import org.bonitasoft.studio.tests.actors.SWTBotActorFilterExportTests;
import org.bonitasoft.studio.tests.applicationDescriptor.OpenExistingApplicationIT;
import org.bonitasoft.studio.tests.bar.TestMenus;
import org.bonitasoft.studio.tests.bug.TestBugsSWTBot;
import org.bonitasoft.studio.tests.configuration.ConfigurationDialogIT;
import org.bonitasoft.studio.tests.connectors.ConnectorDefinitionTranslationsTest;
import org.bonitasoft.studio.tests.connectors.ConnectorDefinitionWizardPageTest;
import org.bonitasoft.studio.tests.connectors.ConnectorEditionTest;
import org.bonitasoft.studio.tests.connectors.ConnectorImplementationTest;
import org.bonitasoft.studio.tests.connectors.SWTBotConnectorDefinitionTest;
import org.bonitasoft.studio.tests.connectors.SWTBotConnectorExportTests;
import org.bonitasoft.studio.tests.connectors.TestDatabaseConnectorOutputMode;
import org.bonitasoft.studio.tests.connectors.TestLoadSaveConnectorConfiguration;
import org.bonitasoft.studio.tests.connectors.TestTextAreaInConnectorWizard;
import org.bonitasoft.studio.tests.debug.TestDebugFeature;
import org.bonitasoft.studio.tests.diagram.DiagramTests;
import org.bonitasoft.studio.tests.diagram.FormsDiagramTests;
import org.bonitasoft.studio.tests.diagram.TestDeleteDiagrams;
import org.bonitasoft.studio.tests.diagram.TestDeleteTaskWithForm;
import org.bonitasoft.studio.tests.diagram.TestOpenDiagram;
import org.bonitasoft.studio.tests.diagram.TestRenameDiagram;
import org.bonitasoft.studio.tests.diagram.TestUndoRedoStackLimit;
import org.bonitasoft.studio.tests.dialog.OpenNameAndVersionDiagramForDiagramTest;
import org.bonitasoft.studio.tests.dialog.OpenNameAndVersionDiagramTest;
import org.bonitasoft.studio.tests.dialog.OpenNameAndVersionForDiagramDialogTest;
import org.bonitasoft.studio.tests.expressionEditor.TestPatternExpressionViewer;
import org.bonitasoft.studio.tests.pagetemplate.PageTemplateEditionIT;
import org.bonitasoft.studio.tests.processzoo.examples.TestWebPurchase;
import org.bonitasoft.studio.tests.properties.TestConditionExpression;
import org.bonitasoft.studio.tests.properties.TestDecisionTable;
import org.bonitasoft.studio.tests.properties.TestThrowCatchMessage;
import org.bonitasoft.studio.tests.validator.TestAddValidatorToProcessAndRun;
import org.bonitasoft.studio.util.test.BonitaSuite;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.VoidResult;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.browser.WebBrowserUIPlugin;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(BonitaSuite.class)
@Suite.SuiteClasses({
        OpenExistingApplicationIT.class,
        TestMenus.class,
        OpenNameAndVersionForDiagramDialogTest.class,
        TestBugsSWTBot.class,
        DiagramTests.class,
        TestRenameDiagram.class,
        TestDatabaseConnectorOutputMode.class,
        TestPatternExpressionViewer.class,
        TestLoadSaveConnectorConfiguration.class,
        TestConditionExpression.class,
        ConfigurationDialogIT.class,
        OrganizationCreationTest.class,
        TestDeleteDiagrams.class,
        ActorDefinitionTranslationsTest.class,
        ActorFilterDefinitionTest.class,
        ActorFilterDefinitionWizardPageTest.class,
        ActorFilterEditionTest.class,
        ActorFilterImplementationTest.class,
        SWTBotActorFilterExportTests.class,
        ConnectorEditionTest.class,
        ConnectorDefinitionWizardPageTest.class,
        ConnectorImplementationTest.class,
        TestTextAreaInConnectorWizard.class,
        SWTBotConnectorExportTests.class,
        SWTBotConnectorDefinitionTest.class,
        TestDecisionTable.class,
        TestThrowCatchMessage.class,
        TestWebPurchase.class,
        TestAddValidatorToProcessAndRun.class,
        FormsDiagramTests.class,
        PageTemplateEditionIT.class,
        TestUndoRedoStackLimit.class,
        TestOpenDiagram.class,
        TestDeleteTaskWithForm.class,
        TestDebugFeature.class,
        OpenNameAndVersionDiagramTest.class,
        OpenNameAndVersionDiagramForDiagramTest.class,
        //WORKAROUND: put at the end otherwise when doing a new run after, it fails
        ConnectorDefinitionTranslationsTest.class
})

public class AllSWTBotTests {

    @BeforeClass
    public static void setUp() {
        BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore()
                .setValue(BonitaPreferenceConstants.CONSOLE_BROWSER_CHOICE, BonitaPreferenceConstants.INTERNAL_BROWSER);
        BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore()
                .setValue(BonitaPreferenceConstants.ASK_RENAME_ON_FIRST_SAVE, false);
        ConnectorEditPlugin.getPlugin().getPreferenceStore()
                .setValue(AbstractDefinitionWizard.HIDE_CONNECTOR_DEFINITION_CHANGE_WARNING, true);
        WebBrowserUIPlugin.getInstance().getPreferenceStore()
                .setValue(BonitaPreferenceConstants.CONSOLE_BROWSER_CHOICE, BonitaPreferenceConstants.INTERNAL_BROWSER);
        FileActionDialog.setDisablePopup(true);
        UIThreadRunnable.syncExec(new VoidResult() {

            @Override
            public void run() {
                PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell()
                        .forceActive();
            }
        });

    }

    @AfterClass
    public static void tearDown() {
        for (final IConfigurationElement elem : BonitaStudioExtensionRegistryManager.getInstance().getConfigurationElements(
                "org.bonitasoft.studio.tests.heapdump")) {
            IHeapDumper dumper;
            try {
                dumper = (IHeapDumper) elem.createExecutableExtension("class");
                dumper.dumpHeap(AllSWTBotTests.class.getSimpleName() + ".hprof", false);
            } catch (final CoreException e) {
                BonitaStudioLog.error(e);
            }
        }
    }

}
