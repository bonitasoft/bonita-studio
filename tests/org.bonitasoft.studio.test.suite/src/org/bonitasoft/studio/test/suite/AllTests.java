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
package org.bonitasoft.studio.test.suite;

import org.bonitasoft.studio.actors.tests.importer.TestImportActorFilter;
import org.bonitasoft.studio.actors.tests.organization.TestExportOrganization;
import org.bonitasoft.studio.actors.tests.organization.TestImportOrganization;
import org.bonitasoft.studio.application.test.TestMenus;
import org.bonitasoft.studio.application.test.TestOperationHistory;
import org.bonitasoft.studio.application.test.TestShowEngineLog;
import org.bonitasoft.studio.common.extension.BonitaStudioExtensionRegistryManager;
import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.configuration.test.synchronizer.TestConfigurationSynhronizer;
import org.bonitasoft.studio.connectors.test.importer.TestImportConnector;
import org.bonitasoft.studio.decision.core.test.TestDecisionTableToGroovy;
import org.bonitasoft.studio.engine.test.TestCheckTenantAfterStartup;
import org.bonitasoft.studio.engine.test.TestJavaDoc;
import org.bonitasoft.studio.engine.test.TestSubprocessEventExport;
import org.bonitasoft.studio.exporter.tests.api.TestBonitaExportModel;
import org.bonitasoft.studio.exporter.tests.autologin.TestAutoLogin;
import org.bonitasoft.studio.groovy.tests.TestFunctionRepository;
import org.bonitasoft.studio.importer.jpdl.tests.TestBug1618;
import org.bonitasoft.studio.importer.jpdl.tests.TestJBPMImport;
import org.bonitasoft.studio.importer.test.api.ProcBuilderTests;
import org.bonitasoft.studio.importer.test.bonita4.TestBug1625;
import org.bonitasoft.studio.importer.test.bonita4.TestImportXPDL;
import org.bonitasoft.studio.importer.test.bpmn2.TestImportBPMN2;
import org.bonitasoft.studio.importer.test.extensions.TestImportExtensionPoint;
import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.bonitasoft.studio.profiles.ProfilePlugin;
import org.bonitasoft.studio.profiles.manager.BonitaProfilesManager;
import org.bonitasoft.studio.repository.test.TestAddJar;
import org.bonitasoft.studio.repository.test.TestExtensionProject;
import org.bonitasoft.studio.repository.test.TestImportExportAndDeleteRepository;
import org.bonitasoft.studio.repository.test.TestImportRepository;
import org.bonitasoft.studio.repository.test.TestRepositoryLocation;
import org.bonitasoft.studio.simulation.tests.TestSimulationExporter;
import org.bonitasoft.studio.simulation.tests.TestSimulationLoadProfileRepository;
import org.bonitasoft.studio.tests.CloseAllEditors;
import org.bonitasoft.studio.tests.IHeapDumper;
import org.bonitasoft.studio.tests.TestBugSave;
import org.bonitasoft.studio.tests.TestFullScenario;
import org.bonitasoft.studio.tests.TestPathSize;
import org.bonitasoft.studio.tests.TestVersion;
import org.bonitasoft.studio.tests.conditions.TestConditions;
import org.bonitasoft.studio.tests.subprocess.TestSubprocess;
import org.bonitasoft.studio.util.test.BonitaJunit4TestSuite;
import org.bonitasoft.studio.workspace.test.TestInitialWorkspace;
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
    TestCheckTenantAfterStartup.class,
    TestInitialWorkspace.class,
    TestMenus.class,
    TestExtensionProject.class,
    TestFunctionRepository.class,
    TestFullScenario.class, // Must be the first to manipulate processes of the suite
    TestSubprocess.class,
    TestConditions.class,
    TestPathSize.class,
    TestImportConnector.class,
    TestImportActorFilter.class,
    TestImportOrganization.class,
    TestExportOrganization.class,
    TestConfigurationSynhronizer.class,
    TestImportExportAndDeleteRepository.class,
    TestSimulationLoadProfileRepository.class,
    TestSimulationExporter.class,
    TestBugSave.class,
    TestVersion.class,
    TestImportExtensionPoint.class,
    ProcBuilderTests.class,
    TestImportXPDL.class,
    TestImportRepository.class,
    TestBug1625.class,
    TestImportBPMN2.class,
    TestJBPMImport.class,
    TestBug1618.class,
    TestAddJar.class,
    TestJavaDoc.class,
    TestOperationHistory.class,
    TestDecisionTableToGroovy.class,
    TestSubprocessEventExport.class,
    TestBonitaExportModel.class,
    TestRepositoryLocation.class,
    TestShowEngineLog.class,
    CloseAllEditors.class,
    TestAutoLogin.class
})
public class AllTests {

    @BeforeClass
    public static void setUp() {
        BonitaStudioLog.info("AllTests","org.bonitasoft.studio.tests");
        BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore().setValue(BonitaPreferenceConstants.CONSOLE_BROWSER_CHOICE, BonitaPreferenceConstants.INTERNAL_BROWSER);
        WebBrowserUIPlugin.getInstance().getPreferenceStore().setValue(BonitaPreferenceConstants.CONSOLE_BROWSER_CHOICE, BonitaPreferenceConstants.INTERNAL_BROWSER);
        FileActionDialog.setDisablePopup(true);
        ProfilePlugin.getDefault().getPreferenceStore().setValue(BonitaProfilesManager.SHOW_SELECT_PROFILE,false) ;
    }

    @AfterClass
    public static void tearDown() {
        for(IConfigurationElement elem : BonitaStudioExtensionRegistryManager.getInstance().getConfigurationElements("org.bonitasoft.studio.tests.heapdump")){
            IHeapDumper dumper;
            try {
                dumper = (IHeapDumper) elem.createExecutableExtension("class");
                dumper.dumpHeap(AllTests.class.getSimpleName()+".hprof", false);
            } catch (CoreException e) {
                BonitaStudioLog.error(e);
            }
        }
    }

}
