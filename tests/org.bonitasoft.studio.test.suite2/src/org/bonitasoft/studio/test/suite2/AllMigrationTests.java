/**
 * Copyright (C) 2009-2012 BonitaSoft S.A.
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

import org.bonitasoft.studio.common.extension.BonitaStudioExtensionRegistryManager;
import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.engine.BOSEngineManager;
import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.bonitasoft.studio.tests.CloseAllEditors;
import org.bonitasoft.studio.tests.IHeapDumper;
import org.bonitasoft.studio.tests.importer.TestBarImporterInput;
import org.bonitasoft.studio.tests.importer.TestSimpleMigrationUseCase;
import org.bonitasoft.studio.tests.importer.attachmentDataImport.AttachmentDataImportTest;
import org.bonitasoft.studio.tests.importer.connector.TestConnectorMigrationUseCase;
import org.bonitasoft.studio.tests.importer.connector.TestCustomConnectorMigrationUseCase;
import org.bonitasoft.studio.tests.importer.examples.Test59ExampleImport;
import org.bonitasoft.studio.tests.importer.messagesImport.CorrelationMigrationTest;
import org.bonitasoft.studio.tests.migration.EdaptHistoryIT;
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
        EdaptHistoryIT.class,
        TestBarImporterInput.class,
        TestSimpleMigrationUseCase.class,
        TestConnectorMigrationUseCase.class,
        TestCustomConnectorMigrationUseCase.class,
        Test59ExampleImport.class,
        AttachmentDataImportTest.class,
        CorrelationMigrationTest.class,
        CloseAllEditors.class
})
public class AllMigrationTests extends TestSuite {

    @BeforeClass
    public static void setUp() {
        BonitaStudioLog.info(AllMigrationTests.class.getName(), "org.bonitasoft.studio.test.suite2");
        BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore()
                .setValue(BonitaPreferenceConstants.CONSOLE_BROWSER_CHOICE, BonitaPreferenceConstants.INTERNAL_BROWSER);
        BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore()
                .setValue(BonitaPreferenceConstants.ASK_RENAME_ON_FIRST_SAVE, false);
        WebBrowserUIPlugin.getInstance().getPreferenceStore()
                .setValue(BonitaPreferenceConstants.CONSOLE_BROWSER_CHOICE, BonitaPreferenceConstants.INTERNAL_BROWSER);
        FileActionDialog.setDisablePopup(true);
        BOSEngineManager.getInstance().start();
    }

    @AfterClass
    public static void tearDown() {
        for (final IConfigurationElement elem : BonitaStudioExtensionRegistryManager.getInstance().getConfigurationElements(
                "org.bonitasoft.studio.tests.heapdump")) {
            IHeapDumper dumper;
            try {
                dumper = (IHeapDumper) elem.createExecutableExtension("class");
                dumper.dumpHeap(AllMigrationTests.class.getSimpleName() + ".hprof", false);
            } catch (final CoreException e) {
                BonitaStudioLog.error(e);
            }
        }
    }
}
