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
import org.bonitasoft.studio.tests.actors.TestOrganizationPassword;
import org.bonitasoft.studio.tests.bar.BarExporterTest;
import org.bonitasoft.studio.tests.businessobject.CreateDeployExportBusinessObjectIT;
import org.bonitasoft.studio.tests.conditions.SWTBOTTestConditions;
import org.bonitasoft.studio.tests.configuration.ProcessDependenciesConfigurationIT;
import org.bonitasoft.studio.tests.connectors.ConnectorEditedInAsingleCommandIT;
import org.bonitasoft.studio.tests.contract.ContractIT;
import org.bonitasoft.studio.tests.data.DataWizardIT;
import org.bonitasoft.studio.tests.diagram.ConvertActivityTypeWithBoundariesIT;
import org.bonitasoft.studio.tests.diagram.CopyPasteTests;
import org.bonitasoft.studio.tests.diagram.DeleteCommandTest;
import org.bonitasoft.studio.tests.diagram.DuplicateCommandTest;
import org.bonitasoft.studio.tests.diagram.ExtractAsSubprocessTest;
import org.bonitasoft.studio.tests.diagram.NewRunTest;
import org.bonitasoft.studio.tests.diagram.SharedEditingDomainTests;
import org.bonitasoft.studio.tests.diagram.TestBoundariesCreation;
import org.bonitasoft.studio.tests.diagram.TestConvertToMessage;
import org.bonitasoft.studio.tests.diagram.TestMoveBetweenLane;
import org.bonitasoft.studio.tests.diagram.TestSave;
import org.bonitasoft.studio.tests.dialog.OpenNameAndVersionForDiagramDialogTest;
import org.bonitasoft.studio.tests.document.TestDocument;
import org.bonitasoft.studio.tests.document.TestRunWithDocument;
import org.bonitasoft.studio.tests.draw2d.TestLifeCycleWidget;
import org.bonitasoft.studio.tests.duplicate.TestDuplicate;
import org.bonitasoft.studio.tests.exporter.ExportBosArchiveIT;
import org.bonitasoft.studio.tests.expressionEditor.TestConnectorExpression;
import org.bonitasoft.studio.tests.form.DeleteWidgetWithContextMenuIT;
import org.bonitasoft.studio.tests.groovy.TestBonitaGroovyEditorDialog;
import org.bonitasoft.studio.tests.iteration.MultiInstantiationIT;
import org.bonitasoft.studio.tests.migration.BarImportReportIT;
import org.bonitasoft.studio.tests.migration.MigrationReporTest;
import org.bonitasoft.studio.tests.properties.CallActivityMappingIT;
import org.bonitasoft.studio.tests.properties.TestResources;
import org.bonitasoft.studio.tests.refactoring.widget.RefactorWidgetIT;
import org.bonitasoft.studio.tests.timer.TestTimer;
import org.bonitasoft.studio.tests.validator.TestCreateValidatorWizard;
import org.bonitasoft.studio.tests.validator.TestFormValidatorIT;
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
        TestBonitaGroovyEditorDialog.class,
        CopyPasteTests.class,
        DeleteCommandTest.class,
        NewRunTest.class,
        DeleteWidgetWithContextMenuIT.class,
        DataWizardIT.class,
        SWTBOTTestConditions.class,
        TestConvertToMessage.class,
        ExtractAsSubprocessTest.class,
        SharedEditingDomainTests.class,
        TestBoundariesCreation.class,
        TestResources.class,
        OpenNameAndVersionForDiagramDialogTest.class,
        TestDuplicate.class,
        TestCreateValidatorWizard.class,
        TestLifeCycleWidget.class,
        TestConnectorExpression.class,
        MultiInstantiationIT.class,
        BarExporterTest.class,
        ExportBosArchiveIT.class,
        TestRunWithDocument.class,
        TestDocument.class,
        DuplicateCommandTest.class,
        TestSave.class,
        TestMoveBetweenLane.class,
        MigrationReporTest.class,
        TestTimer.class,
        ConnectorEditedInAsingleCommandIT.class,
        ProcessDependenciesConfigurationIT.class,
        ContractIT.class,
        RefactorWidgetIT.class,
        TestFormValidatorIT.class,
        TestOrganizationPassword.class,
        BarImportReportIT.class,
        CallActivityMappingIT.class,
        CreateDeployExportBusinessObjectIT.class,
        ConvertActivityTypeWithBoundariesIT.class
})
public class AllSWTBotTests2 {

    @BeforeClass
    public static void setUp() {
        System.out.println(String.format("\uD83D\uDC22 Running Test Suite %s%s%s", ConsoleColors.PURPLE_BOLD,
                AllSWTBotTests2.class.getName(), ConsoleColors.RESET));
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
                dumper.dumpHeap(AllSWTBotTests2.class.getSimpleName() + ".hprof", false);
            } catch (final CoreException e) {
                BonitaStudioLog.error(e);
            }
        }
    }

}
