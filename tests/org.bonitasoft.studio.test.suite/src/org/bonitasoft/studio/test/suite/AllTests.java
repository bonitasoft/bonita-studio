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
package org.bonitasoft.studio.test.suite;

import org.bonitasoft.studio.common.ConsoleColors;
import org.bonitasoft.studio.common.extension.BonitaStudioExtensionRegistryManager;
import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.engine.BOSEngineManager;
import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.bonitasoft.studio.tests.CloseAllEditors;
import org.bonitasoft.studio.tests.EngineConfigurationIT;
import org.bonitasoft.studio.tests.IHeapDumper;
import org.bonitasoft.studio.tests.ProductVersionIT;
import org.bonitasoft.studio.tests.TestBugSave;
import org.bonitasoft.studio.tests.TestFullScenario;
import org.bonitasoft.studio.tests.TestInitialWorkspace;
import org.bonitasoft.studio.tests.TestPathSize;
import org.bonitasoft.studio.tests.actors.TestExportOrganization;
import org.bonitasoft.studio.tests.actors.TestImportActorFilter;
import org.bonitasoft.studio.tests.actors.TestImportOrganization;
import org.bonitasoft.studio.tests.bar.TestOperationHistory;
import org.bonitasoft.studio.tests.bar.TestShowEngineLog;
import org.bonitasoft.studio.tests.conditions.TestConditions;
import org.bonitasoft.studio.tests.conditions.TestDecisionTableToGroovy;
import org.bonitasoft.studio.tests.configuration.TestConfigurationSynhronizer;
import org.bonitasoft.studio.tests.connectors.DriverAssociationContributionIT;
import org.bonitasoft.studio.tests.connectors.TestImportConnector;
import org.bonitasoft.studio.tests.document.TestDocumentRefactoring;
import org.bonitasoft.studio.tests.engine.TestJavaDoc;
import org.bonitasoft.studio.tests.engine.TestSubprocessEventExport;
import org.bonitasoft.studio.tests.exporter.TestAutoLogin;
import org.bonitasoft.studio.tests.exporter.TestBonitaExportModel;
import org.bonitasoft.studio.tests.form.TestFileWidgetMigration;
import org.bonitasoft.studio.tests.groovy.TestFunctionRepository;
import org.bonitasoft.studio.tests.importer.api.ProcBuilderTests;
import org.bonitasoft.studio.tests.importer.bonita4.TestImportXPDL;
import org.bonitasoft.studio.tests.importer.bpmn2.TestImportBPMN2;
import org.bonitasoft.studio.tests.importer.extensions.TestImportExtensionPoint;
import org.bonitasoft.studio.tests.importer.jpdl.TestJBPMImport;
import org.bonitasoft.studio.tests.repository.TestAddJar;
import org.bonitasoft.studio.tests.repository.TestExtensionProject;
import org.bonitasoft.studio.tests.repository.TestImportExportAndDeleteRepository;
import org.bonitasoft.studio.tests.repository.TestImportRepository;
import org.bonitasoft.studio.tests.repository.TestRepositoryLocation;
import org.bonitasoft.studio.tests.subprocess.TestSubprocess;
import org.bonitasoft.studio.util.test.BonitaSuite;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.ui.internal.browser.WebBrowserUIPlugin;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(BonitaSuite.class)
@Suite.SuiteClasses({
        ProductVersionIT.class,
        TestInitialWorkspace.class,
        TestExtensionProject.class,
        TestFunctionRepository.class,
        EngineConfigurationIT.class,
        TestFullScenario.class,
        TestSubprocess.class,
        TestConditions.class,
        TestPathSize.class,
        TestImportConnector.class,
        TestImportActorFilter.class,
        TestImportOrganization.class,
        TestExportOrganization.class,
        TestDocumentRefactoring.class,
        TestConfigurationSynhronizer.class,
        TestImportExportAndDeleteRepository.class,
        TestFileWidgetMigration.class,
        TestImportExtensionPoint.class,
        ProcBuilderTests.class,
        TestImportXPDL.class,
        TestImportRepository.class,
        TestImportBPMN2.class,
        TestJBPMImport.class,
        TestAddJar.class,
        TestJavaDoc.class,
        TestOperationHistory.class,
        TestDecisionTableToGroovy.class,
        TestSubprocessEventExport.class,
        TestBonitaExportModel.class,
        TestRepositoryLocation.class,
        TestShowEngineLog.class,
        TestAutoLogin.class,
        DriverAssociationContributionIT.class,
        CloseAllEditors.class,
        TestBugSave.class
})
public class AllTests {

    @BeforeClass
    public static void setUp() {
        System.out.println(String.format("\uD83D\uDC22 Running Test Suite %s%s%s", ConsoleColors.PURPLE_BOLD,
                AllTests.class.getName(), ConsoleColors.RESET));
        BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore().setValue(
                BonitaPreferenceConstants.CONSOLE_BROWSER_CHOICE,
                BonitaPreferenceConstants.INTERNAL_BROWSER);
        BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore()
                .setValue(BonitaPreferenceConstants.ASK_RENAME_ON_FIRST_SAVE, false);
        WebBrowserUIPlugin.getInstance().getPreferenceStore().setValue(BonitaPreferenceConstants.CONSOLE_BROWSER_CHOICE,
                BonitaPreferenceConstants.INTERNAL_BROWSER);
        FileActionDialog.setDisablePopup(true);
        BOSEngineManager.getInstance().start();
    }

    @AfterClass
    public static void tearDown() {
        for (final IConfigurationElement elem : BonitaStudioExtensionRegistryManager.getInstance()
                .getConfigurationElements("org.bonitasoft.studio.tests.heapdump")) {
            IHeapDumper dumper;
            try {
                dumper = (IHeapDumper) elem.createExecutableExtension("class");
                dumper.dumpHeap(AllTests.class.getSimpleName() + ".hprof", false);
            } catch (final CoreException e) {
                BonitaStudioLog.error(e);
            }
        }
    }

}
