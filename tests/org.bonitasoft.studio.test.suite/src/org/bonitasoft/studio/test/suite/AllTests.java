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

import org.bonitasoft.studio.common.extension.BonitaStudioExtensionRegistryManager;
import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.bonitasoft.studio.profiles.ProfilePlugin;
import org.bonitasoft.studio.profiles.manager.BonitaProfilesManager;
import org.bonitasoft.studio.tests.CloseAllEditors;
import org.bonitasoft.studio.tests.EngineConfigurationIT;
import org.bonitasoft.studio.tests.IHeapDumper;
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
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.DeviceData;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Path;
import org.eclipse.swt.graphics.Pattern;
import org.eclipse.swt.graphics.Region;
import org.eclipse.swt.graphics.TextLayout;
import org.eclipse.swt.graphics.Transform;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.internal.browser.WebBrowserUIPlugin;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(BonitaSuite.class)
@Suite.SuiteClasses({
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
        BonitaStudioLog.info("AllTests", "org.bonitasoft.studio.tests");
        BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore().setValue(
                BonitaPreferenceConstants.CONSOLE_BROWSER_CHOICE,
                BonitaPreferenceConstants.INTERNAL_BROWSER);
        BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore()
                .setValue(BonitaPreferenceConstants.ASK_RENAME_ON_FIRST_SAVE, false);
        WebBrowserUIPlugin.getInstance().getPreferenceStore().setValue(BonitaPreferenceConstants.CONSOLE_BROWSER_CHOICE,
                BonitaPreferenceConstants.INTERNAL_BROWSER);
        FileActionDialog.setDisablePopup(true);
        ProfilePlugin.getDefault().getPreferenceStore().setValue(BonitaProfilesManager.SHOW_SELECT_PROFILE, false);
    }

    private static Object[] oldObjects = new Object[0];
    private static Error[] oldErrors = new Error[0];
    private static Object[] objects = new Object[0];
    private static Error[] errors = new Error[0];

    public static void refreshAll() {
        oldObjects = new Object[0];
        oldErrors = new Error[0];
        resourceSnaphot();
        oldObjects = objects;
        oldErrors = errors;
    }

    public static String refreshLabel() {
        int colors = 0, cursors = 0, fonts = 0, gcs = 0, images = 0;
        int paths = 0, patterns = 0, regions = 0, textLayouts = 0, transforms = 0;
        for (int i = 0; i < objects.length; i++) {
            final Object object = objects[i];
            if (object instanceof Color) {
                colors++;
            }
            if (object instanceof Cursor) {
                cursors++;
            }
            if (object instanceof Font) {
                fonts++;
            }
            if (object instanceof GC) {
                gcs++;
            }
            if (object instanceof Image) {
                images++;
            }
            if (object instanceof Path) {
                paths++;
            }
            if (object instanceof Pattern) {
                patterns++;
            }
            if (object instanceof Region) {
                regions++;
            }
            if (object instanceof TextLayout) {
                textLayouts++;
            }
            if (object instanceof Transform) {
                transforms++;
            }
        }
        String string = "";
        if (colors != 0) {
            string += colors + " Color(s)\n";
        }
        if (cursors != 0) {
            string += cursors + " Cursor(s)\n";
        }
        if (fonts != 0) {
            string += fonts + " Font(s)\n";
        }
        if (gcs != 0) {
            string += gcs + " GC(s)\n";
        }
        if (images != 0) {
            string += images + " Image(s)\n";
        }
        if (paths != 0) {
            string += paths + " Paths(s)\n";
        }
        if (patterns != 0) {
            string += patterns + " Pattern(s)\n";
        }
        if (regions != 0) {
            string += regions + " Region(s)\n";
        }
        if (textLayouts != 0) {
            string += textLayouts + " TextLayout(s)\n";
        }
        if (transforms != 0) {
            string += transforms + " Transform(s)\n";
        }
        if (string.length() != 0) {
            string = string.substring(0, string.length() - 1);
        }
        return string;
    }

    public static void resourceSnaphot() {
        final Display display = Display.getDefault();
        final DeviceData info = display.getDeviceData();
        if (!info.tracking) {
            BonitaStudioLog.warning("Device is not tracking resource allocation", "org.bonitasoft.studio.test.suite");
        }
        final Object[] newObjects = info.objects;
        final Error[] newErrors = info.errors;
        final Object[] diffObjects = new Object[newObjects.length];
        final Error[] diffErrors = new Error[newErrors.length];
        int count = 0;
        for (int i = 0; i < newObjects.length; i++) {
            int index = 0;
            while (index < oldObjects.length) {
                if (newObjects[i] == oldObjects[index]) {
                    break;
                }
                index++;
            }
            if (index == oldObjects.length) {
                diffObjects[count] = newObjects[i];
                diffErrors[count] = newErrors[i];
                count++;
            }
        }
        objects = new Object[count];
        errors = new Error[count];
        System.arraycopy(diffObjects, 0, objects, 0, count);
        System.arraycopy(diffErrors, 0, errors, 0, count);
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
