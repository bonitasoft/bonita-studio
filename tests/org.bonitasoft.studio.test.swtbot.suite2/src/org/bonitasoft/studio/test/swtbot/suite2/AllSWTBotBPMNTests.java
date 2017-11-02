/**
 * Copyright (C) 2009-2015 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.test.swtbot.suite2;

import org.bonitasoft.studio.common.ConsoleColors;
import org.bonitasoft.studio.common.extension.BonitaStudioExtensionRegistryManager;
import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.bonitasoft.studio.tests.IHeapDumper;
import org.bonitasoft.studio.tests.exporter.bpmn.BPMNConnectorExportImportTest;
import org.bonitasoft.studio.tests.exporter.bpmn.BPMNEventSubProcessExportImportTest;
import org.bonitasoft.studio.tests.exporter.bpmn.BPMNExportTests;
import org.bonitasoft.studio.tests.exporter.bpmn.BPMNGatewayExportImportTest;
import org.bonitasoft.studio.tests.exporter.bpmn.BPMNSequenceFlowDefaultFlowExportImportTest;
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
        BPMNExportTests.class,
        BPMNSequenceFlowDefaultFlowExportImportTest.class,
        BPMNConnectorExportImportTest.class,
        BPMNGatewayExportImportTest.class,
        BPMNEventSubProcessExportImportTest.class
})
public class AllSWTBotBPMNTests {

    @BeforeClass
    public static void setUp() {
        System.out.println(String.format("\uD83D\uDC22 Running Test Suite %s%s%s", ConsoleColors.PURPLE_BOLD,
                AllSWTBotBPMNTests.class.getName(), ConsoleColors.RESET));
        BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore()
                .setValue(BonitaPreferenceConstants.CONSOLE_BROWSER_CHOICE, BonitaPreferenceConstants.INTERNAL_BROWSER);
        BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore()
                .setValue(BonitaPreferenceConstants.ASK_RENAME_ON_FIRST_SAVE, false);
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
                dumper.dumpHeap(AllSWTBotBPMNTests.class.getSimpleName() + ".hprof", false);
            } catch (final CoreException e) {
                BonitaStudioLog.error(e);
            }
        }
    }

}
