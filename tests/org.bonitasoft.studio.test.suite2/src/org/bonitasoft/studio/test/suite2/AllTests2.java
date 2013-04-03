/**
 * Copyright (C) 2009-2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
import org.bonitasoft.studio.diagram.form.custom.tests.TestCommands;
import org.bonitasoft.studio.exporter.tests.application.TestHtmlTemplateGenerator;
import org.bonitasoft.studio.exporter.tests.bpmn.BPMNDataExportImportTest;
import org.bonitasoft.studio.exporter.tests.bpmn.BPMNImportExportTest;
import org.bonitasoft.studio.exporter.tests.bpmn.BPMNSequenceFlowConditionExportImportTest;
import org.bonitasoft.studio.exporter.tests.form.TestFormsExporter;
import org.bonitasoft.studio.exporter.tests.preview.TestPreviewForm;
import org.bonitasoft.studio.importer.test.bos.TestBOSArchiveImport;
import org.bonitasoft.studio.migration.tests.MigrationReportPDFExportTest;
import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.bonitasoft.studio.simulation.tests.TestSimulationResourceRepository;
import org.bonitasoft.studio.tests.CloseAllEditors;
import org.bonitasoft.studio.tests.IHeapDumper;
import org.bonitasoft.studio.tests.TestURLs;
import org.bonitasoft.studio.tests.api.TestBonitaAPI;
import org.bonitasoft.studio.tests.bug.TestBugs;
import org.bonitasoft.studio.tests.data.TestDataRefactor;
import org.bonitasoft.studio.tests.perspectives.TestAutomaticSwitchPerspective;
import org.bonitasoft.studio.tests.processzoo.TestProcessZoo;
import org.bonitasoft.studio.themes.tests.TestCSSModel;
import org.bonitasoft.studio.themes.tests.TestDirtyState;
import org.bonitasoft.studio.themes.tests.TestThemeRepository;
import org.bonitasoft.studio.util.test.BonitaJunit4TestSuite;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.ui.internal.browser.WebBrowserUIPlugin;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author Romain Bioteau
 *
 */
@RunWith(BonitaJunit4TestSuite.class)
@Suite.SuiteClasses({
    TestBugs.class,//must be the first (to check for zoomContributionItem at startup)
    TestProcessZoo.class,
    TestProvidedDefinitionAndImplementation.class,
    TestProvidedActorFilterDefinitionAndImplementation.class,
    TestFormsExporter.class,
    TestHtmlTemplateGenerator.class,
    TestPreviewForm.class,
    TestCommands.class,
    TestAutomaticSwitchPerspective.class,
    TestBOSArchiveImport.class,
    TestSimulationResourceRepository.class,
    TestExportProcessBar.class,
    BPMNImportExportTest.class,
    TestThemeRepository.class,
    TestDirtyState.class,
    TestCSSModel.class,
    TestURLs.class,
    TestDuplicateCommand.class,
    BPMNDataExportImportTest.class,
    BPMNSequenceFlowConditionExportImportTest.class,
    TestDataRefactor.class,
    TestBonitaAPI.class,
    // keep it at the end because if it fails it might be in an infinite loop
    TestDeployCommand.class,
    MigrationReportPDFExportTest.class,
    CloseAllEditors.class,
})
public class AllTests2 extends TestSuite {

    @BeforeClass
    public static void setUp() {
        BonitaStudioLog.info("AllTests2","org.bonitasoft.studio.test.suite2");
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
                dumper.dumpHeap(AllTests2.class.getSimpleName()+".hprof", false);
            } catch (CoreException e) {
                BonitaStudioLog.error(e);
            }
        }
    }
}
