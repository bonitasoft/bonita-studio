/**
 * Copyright (C) 2009-2014 Bonitasoft S.A.
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
package org.bonitasoft.studio.test.suite2;

import org.bonitasoft.studio.common.ConsoleColors;
import org.bonitasoft.studio.common.extension.BonitaStudioExtensionRegistryManager;
import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.bonitasoft.studio.tests.CloseAllEditors;
import org.bonitasoft.studio.tests.IHeapDumper;
import org.bonitasoft.studio.tests.actors.TestProvidedActorFilterDefinitionAndImplementation;
import org.bonitasoft.studio.tests.api.TestBonitaAPI;
import org.bonitasoft.studio.tests.bar.ExportBarIT;
import org.bonitasoft.studio.tests.bar.TestExportProcessBar;
import org.bonitasoft.studio.tests.bug.TestBugs;
import org.bonitasoft.studio.tests.businessobject.DeployBDROperationIT;
import org.bonitasoft.studio.tests.businessobject.ImportLegacyBDMIT;
import org.bonitasoft.studio.tests.connectors.GroovyConnectorIT;
import org.bonitasoft.studio.tests.connectors.TestDatabaseConnectorResulset;
import org.bonitasoft.studio.tests.connectors.TestProvidedDefinitionAndImplementation;
import org.bonitasoft.studio.tests.connectors.TestTestConnectorOperation;
import org.bonitasoft.studio.tests.connectors.TestWebserviceVersionForBPMNImport;
import org.bonitasoft.studio.tests.data.DataRefactorIT;
import org.bonitasoft.studio.tests.deploy.TestDeployCommand;
import org.bonitasoft.studio.tests.duplicate.TestDuplicateCommand;
import org.bonitasoft.studio.tests.engine.TestUserFilterMatchingEngineVersion;
import org.bonitasoft.studio.tests.exporter.TestFormsExporter;
import org.bonitasoft.studio.tests.exporter.TestHtmlTemplateGenerator;
import org.bonitasoft.studio.tests.exporter.bpmn.BPMNDataExportImportTest;
import org.bonitasoft.studio.tests.exporter.bpmn.BPMNImportExportTest;
import org.bonitasoft.studio.tests.exporter.bpmn.BPMNSequenceFlowConditionExportImportTest;
import org.bonitasoft.studio.tests.form.TestCommands;
import org.bonitasoft.studio.tests.groovy.TestGroovyScriptExpressionEditor;
import org.bonitasoft.studio.tests.importer.bos.TestBOSArchiveImport;
import org.bonitasoft.studio.tests.migration.DocumentMigrationIT;
import org.bonitasoft.studio.tests.migration.MigrationReportPDFExportTest;
import org.bonitasoft.studio.tests.parameter.TestParametersRefactoring;
import org.bonitasoft.studio.tests.parameter.TestProcessParameters;
import org.bonitasoft.studio.tests.perspectives.TestAutomaticSwitchPerspective;
import org.bonitasoft.studio.tests.processzoo.TestProcessZoo;
import org.bonitasoft.studio.tests.properties.TestLookAndFeel;
import org.bonitasoft.studio.tests.refactoring.widget.TestRefactorWidgetOperation;
import org.bonitasoft.studio.tests.repository.UIDesignerWorkspaceIntegrationIT;
import org.bonitasoft.studio.tests.theme.TestCSSModel;
import org.bonitasoft.studio.tests.theme.TestDirtyState;
import org.bonitasoft.studio.tests.theme.TestThemeRepository;
import org.bonitasoft.studio.tests.timer.TestNonInterruptingBoundaryTimerEvent;
import org.bonitasoft.studio.tests.validation.TestTokenDispatcher;
import org.bonitasoft.studio.tests.validation.TestValidationConstraints;
import org.bonitasoft.studio.util.test.BonitaSuite;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.ui.internal.browser.WebBrowserUIPlugin;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import junit.framework.TestSuite;

@RunWith(BonitaSuite.class)
@Suite.SuiteClasses({
        TestBugs.class, //must be the first (to check for zoomContributionItem at startup)
        TestProcessZoo.class,
        TestProvidedDefinitionAndImplementation.class,
        TestProvidedActorFilterDefinitionAndImplementation.class,
        TestFormsExporter.class,
        TestHtmlTemplateGenerator.class,
        TestCommands.class,
        TestDatabaseConnectorResulset.class,
        TestAutomaticSwitchPerspective.class,
        TestBOSArchiveImport.class,
        TestLookAndFeel.class,
        TestGroovyScriptExpressionEditor.class,
        TestExportProcessBar.class,
        TestThemeRepository.class,
        TestDirtyState.class,
        TestCSSModel.class,
        TestDuplicateCommand.class,
        BPMNImportExportTest.class,
        BPMNDataExportImportTest.class,
        BPMNSequenceFlowConditionExportImportTest.class,
        DataRefactorIT.class,
        TestBonitaAPI.class,
        TestNonInterruptingBoundaryTimerEvent.class,
        TestUserFilterMatchingEngineVersion.class,
        TestDeployCommand.class,
        MigrationReportPDFExportTest.class,
        TestValidationConstraints.class,
        TestTestConnectorOperation.class,
        TestRefactorWidgetOperation.class,
        TestWebserviceVersionForBPMNImport.class,
        TestTokenDispatcher.class,
        DocumentMigrationIT.class,
        GroovyConnectorIT.class,
        DeployBDROperationIT.class,
        ImportLegacyBDMIT.class,
        TestProcessParameters.class,
        TestParametersRefactoring.class,
        ExportBarIT.class,
        UIDesignerWorkspaceIntegrationIT.class,
        CloseAllEditors.class
})

public class AllTests2 extends TestSuite {

    @BeforeClass
    public static void setUp() {
        System.out.println(String.format("\uD83D\uDC22 Running Test Suite %s%s%s", ConsoleColors.PURPLE_BOLD,
                AllTests2.class.getName(), ConsoleColors.RESET));
        BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore()
                .setValue(BonitaPreferenceConstants.CONSOLE_BROWSER_CHOICE, BonitaPreferenceConstants.INTERNAL_BROWSER);
        BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore()
                .setValue(BonitaPreferenceConstants.ASK_RENAME_ON_FIRST_SAVE, false);
        WebBrowserUIPlugin.getInstance().getPreferenceStore()
                .setValue(BonitaPreferenceConstants.CONSOLE_BROWSER_CHOICE, BonitaPreferenceConstants.INTERNAL_BROWSER);
        FileActionDialog.setDisablePopup(true);
    }

    @AfterClass
    public static void tearDown() {
        for (final IConfigurationElement elem : BonitaStudioExtensionRegistryManager.getInstance().getConfigurationElements(
                "org.bonitasoft.studio.tests.heapdump")) {
            IHeapDumper dumper;
            try {
                dumper = (IHeapDumper) elem.createExecutableExtension("class");
                dumper.dumpHeap(AllTests2.class.getSimpleName() + ".hprof", false);
            } catch (final CoreException e) {
                BonitaStudioLog.error(e);
            }
        }
    }
}
