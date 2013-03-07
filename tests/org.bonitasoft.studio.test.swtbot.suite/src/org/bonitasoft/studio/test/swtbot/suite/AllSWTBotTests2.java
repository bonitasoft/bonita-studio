package org.bonitasoft.studio.test.swtbot.suite;

import org.bonitasoft.expressions.test.TestConnectorExpression;
import org.bonitasoft.studio.commands.test.CopyPasteTests;
import org.bonitasoft.studio.commands.test.DuplicateCommandTest;
import org.bonitasoft.studio.commands.test.ExtractAsSubprocessTest;
import org.bonitasoft.studio.common.extension.BonitaStudioExtensionRegistryManager;
import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.configuration.test.swtbot.TestProcessDependencies;
import org.bonitasoft.studio.connectors.test.swtbot.ConnectorConfigurationTest;
import org.bonitasoft.studio.data.test.DataSWTBotTests;
import org.bonitasoft.studio.diagram.form.custom.tests.TestBug1682;
import org.bonitasoft.studio.diagram.test.NewRunTest;
import org.bonitasoft.studio.diagram.test.SharedEditingDomainTests;
import org.bonitasoft.studio.diagram.test.TestBoundariesCreation;
import org.bonitasoft.studio.diagram.test.TestBug1640;
import org.bonitasoft.studio.diagram.test.TestConvertToMessage;
import org.bonitasoft.studio.diagram.test.TestImportAndOverrideWithEditorOpen;
import org.bonitasoft.studio.diagram.test.TestMoveBetweenLane;
import org.bonitasoft.studio.diagram.test.TestSave;
import org.bonitasoft.studio.engine.test.bar.BarExporterTest;
import org.bonitasoft.studio.exporter.tests.bpmn.BPMNConnectorExportImportTest;
import org.bonitasoft.studio.exporter.tests.bpmn.BPMNEventSubProcessExportImportTest;
import org.bonitasoft.studio.exporter.tests.bpmn.BPMNExportTests;
import org.bonitasoft.studio.exporter.tests.bpmn.BPMNGatewayExportImportTest;
import org.bonitasoft.studio.exporter.tests.bpmn.BPMNSequenceFlowDefaultFlowExportImportTest;
import org.bonitasoft.studio.groovy.tests.TestBonitaGroovyEditorDialog;
import org.bonitasoft.studio.migration.tests.MigrationReporTest;
import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.bonitasoft.studio.properties.test.TestMultiInstantiation;
import org.bonitasoft.studio.properties.test.TestResources;
import org.bonitasoft.studio.repository.test.swtbot.TestExportBosArchive;
import org.bonitasoft.studio.tests.IHeapDumper;
import org.bonitasoft.studio.tests.attachment.TestRunWithDocument;
import org.bonitasoft.studio.tests.dialog.OpenNameAndVersionForDiagramDialogTest;
import org.bonitasoft.studio.tests.dialog.TestDuplicate;
import org.bonitasoft.studio.tests.draw2d.TestLifeCycleWidget;
import org.bonitasoft.studio.tests.timer.TestTimer;
import org.bonitasoft.studio.util.test.BonitaTestSuite;
import org.bonitasoft.studio.validators.test.swtbot.TestCreateValidatorWizard;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.ui.internal.browser.WebBrowserUIPlugin;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Copyright (C) 2009 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */



@RunWith(BonitaTestSuite.class)
@Suite.SuiteClasses({
    TestImportAndOverrideWithEditorOpen.class,
    TestBug1640.class,
    NewRunTest.class,
    TestBug1682.class,
    DataSWTBotTests.class,
    TestConvertToMessage.class,
    ExtractAsSubprocessTest.class,
    BPMNExportTests.class,
    BPMNSequenceFlowDefaultFlowExportImportTest.class,
    SharedEditingDomainTests.class,
    TestBoundariesCreation.class,
    TestResources.class,
    OpenNameAndVersionForDiagramDialogTest.class,
    TestDuplicate.class,
    CopyPasteTests.class,
    TestCreateValidatorWizard.class,
    TestLifeCycleWidget.class,
    ConnectorConfigurationTest.class,
    TestConnectorExpression.class,
    TestMultiInstantiation.class,
    BPMNConnectorExportImportTest.class,
    BPMNGatewayExportImportTest.class,
    BPMNEventSubProcessExportImportTest.class,
    BarExporterTest.class,
    TestRunWithDocument.class,
    DuplicateCommandTest.class,
    TestExportBosArchive.class,
    TestSave.class,
    TestMoveBetweenLane.class,
    MigrationReporTest.class,
    TestTimer.class,
    TestBonitaGroovyEditorDialog.class,
    TestProcessDependencies.class
})
public class AllSWTBotTests2 {


    @BeforeClass
    public static void setUp() {
        BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore().setValue(BonitaPreferenceConstants.CONSOLE_BROWSER_CHOICE, BonitaPreferenceConstants.INTERNAL_BROWSER);
        WebBrowserUIPlugin.getInstance().getPreferenceStore().setValue(BonitaPreferenceConstants.CONSOLE_BROWSER_CHOICE, BonitaPreferenceConstants.INTERNAL_BROWSER);
        FileActionDialog.setDisablePopup(true);
    }
    @AfterClass
    public static void tearDown() {
        for(IConfigurationElement elem : BonitaStudioExtensionRegistryManager.getInstance().getConfigurationElements("org.bonitasoft.studio.tests.heapdump")){
            IHeapDumper dumper;
            try {
                dumper = (IHeapDumper) elem.createExecutableExtension("class");
                dumper.dumpHeap(AllSWTBotTests2.class.getSimpleName()+".hprof", false);
            } catch (CoreException e) {
                BonitaStudioLog.error(e);
            }
        }
    }

}
