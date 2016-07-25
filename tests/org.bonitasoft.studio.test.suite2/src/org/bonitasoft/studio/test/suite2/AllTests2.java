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

import junit.framework.TestSuite;

import org.bonitasoft.studio.actors.tests.TestProvidedActorFilterDefinitionAndImplementation;
import org.bonitasoft.studio.application.test.TestExportProcessBar;
import org.bonitasoft.studio.application.test.deploycommand.TestDeployCommand;
import org.bonitasoft.studio.application.test.duplicatecommand.TestDuplicateCommand;
import org.bonitasoft.studio.common.extension.BonitaStudioExtensionRegistryManager;
import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.connectors.test.TestProvidedDefinitionAndImplementation;
import org.bonitasoft.studio.connectors.test.TestTestConnectorOperation;
import org.bonitasoft.studio.connectors.test.TestWebserviceVersionForBPMNImport;
import org.bonitasoft.studio.engine.test.TestUserFilterMatchingEngineVersion;
import org.bonitasoft.studio.exporter.tests.application.TestHtmlTemplateGenerator;
import org.bonitasoft.studio.exporter.tests.bpmn.BPMNDataExportImportTest;
import org.bonitasoft.studio.exporter.tests.bpmn.BPMNImportExportTest;
import org.bonitasoft.studio.exporter.tests.bpmn.BPMNSequenceFlowConditionExportImportTest;
import org.bonitasoft.studio.exporter.tests.form.TestFormsExporter;
import org.bonitasoft.studio.importer.test.bos.TestBOSArchiveImport;
import org.bonitasoft.studio.migration.tests.MigrationReportPDFExportTest;
import org.bonitasoft.studio.migration.tests.document.DocumentMigrationIT;
import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.bonitasoft.studio.properties.test.TestLookAndFeel;
import org.bonitasoft.studio.simulation.tests.TestSimulationResourceRepository;
import org.bonitasoft.studio.tests.CloseAllEditors;
import org.bonitasoft.studio.tests.IHeapDumper;
import org.bonitasoft.studio.tests.api.TestBonitaAPI;
import org.bonitasoft.studio.tests.bar.ExportBarIT;
import org.bonitasoft.studio.tests.bug.TestBugs;
import org.bonitasoft.studio.tests.businessobject.DeployBDROperationIT;
import org.bonitasoft.studio.tests.connectors.GroovyConnectorIT;
import org.bonitasoft.studio.tests.connectors.TestDatabaseConnectorResulset;
import org.bonitasoft.studio.tests.data.DataRefactorIT;
import org.bonitasoft.studio.tests.form.TestCommands;
import org.bonitasoft.studio.tests.groovy.TestGroovyScriptExpressionEditor;
import org.bonitasoft.studio.tests.parameter.TestParametersRefactoring;
import org.bonitasoft.studio.tests.parameter.TestProcessParameters;
import org.bonitasoft.studio.tests.perspectives.TestAutomaticSwitchPerspective;
import org.bonitasoft.studio.tests.processzoo.TestProcessZoo;
import org.bonitasoft.studio.tests.refactoring.widget.TestRefactorWidgetOperation;
import org.bonitasoft.studio.tests.refactoring.widget.TestRemoveWidgetReferencesOperation;
import org.bonitasoft.studio.tests.repository.UIDesignerWorkspaceIntegrationIT;
import org.bonitasoft.studio.tests.timer.TestNonInterruptingBoundaryTimerEvent;
import org.bonitasoft.studio.themes.tests.TestCSSModel;
import org.bonitasoft.studio.themes.tests.TestDirtyState;
import org.bonitasoft.studio.themes.tests.TestThemeRepository;
import org.bonitasoft.studio.util.test.BonitaJunit4TestSuite;
import org.bonitasoft.studio.validation.test.TestTokenDispatcher;
import org.bonitasoft.studio.validation.test.TestValidationConstraints;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.ui.internal.browser.WebBrowserUIPlugin;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author Romain Bioteau
 */
@RunWith(BonitaJunit4TestSuite.class)
@Suite.SuiteClasses({
        TestBugs.class,//must be the first (to check for zoomContributionItem at startup)
        TestProcessZoo.class,
        TestProvidedDefinitionAndImplementation.class,
        TestProvidedActorFilterDefinitionAndImplementation.class,
        TestFormsExporter.class,
        TestHtmlTemplateGenerator.class,
        TestCommands.class,
        TestDatabaseConnectorResulset.class,
        TestAutomaticSwitchPerspective.class,
        TestBOSArchiveImport.class,
        TestSimulationResourceRepository.class,
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
        // keep it at the end because if it fails it might be in an infinite loop
        TestDeployCommand.class,
        MigrationReportPDFExportTest.class,
        TestValidationConstraints.class,
        TestTestConnectorOperation.class,
        TestRefactorWidgetOperation.class,
        TestRemoveWidgetReferencesOperation.class,
        TestWebserviceVersionForBPMNImport.class,
        TestTokenDispatcher.class,
        DocumentMigrationIT.class,
        GroovyConnectorIT.class,
        DeployBDROperationIT.class,
        TestProcessParameters.class,
        TestParametersRefactoring.class,
        ExportBarIT.class,
        UIDesignerWorkspaceIntegrationIT.class,
        CloseAllEditors.class
})
public class AllTests2 extends TestSuite {

    @BeforeClass
    public static void setUp() {
        BonitaStudioLog.info("AllTests2", "org.bonitasoft.studio.test.suite2");
        BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore()
                .setValue(BonitaPreferenceConstants.CONSOLE_BROWSER_CHOICE, BonitaPreferenceConstants.INTERNAL_BROWSER);
        BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore().setValue(BonitaPreferenceConstants.ASK_RENAME_ON_FIRST_SAVE, false);
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
