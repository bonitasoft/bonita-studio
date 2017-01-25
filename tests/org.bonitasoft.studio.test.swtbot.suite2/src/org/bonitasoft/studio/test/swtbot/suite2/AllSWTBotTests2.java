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

import org.bonitasoft.expressions.test.TestConnectorExpression;
import org.bonitasoft.studio.actors.tests.SWTbot.TestOrganizationPassword;
import org.bonitasoft.studio.commands.test.CopyPasteTests;
import org.bonitasoft.studio.commands.test.DeleteCommandTest;
import org.bonitasoft.studio.commands.test.DuplicateCommandTest;
import org.bonitasoft.studio.commands.test.ExtractAsSubprocessTest;
import org.bonitasoft.studio.common.extension.BonitaStudioExtensionRegistryManager;
import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.configuration.test.swtbot.ProcessDependenciesConfigurationIT;
import org.bonitasoft.studio.connectors.test.swtbot.ConnectorEditedInAsingleCommandIT;
import org.bonitasoft.studio.diagram.test.ConvertActivityTypeWithBoundariesIT;
import org.bonitasoft.studio.diagram.test.NewRunTest;
import org.bonitasoft.studio.diagram.test.SharedEditingDomainTests;
import org.bonitasoft.studio.diagram.test.TestBoundariesCreation;
import org.bonitasoft.studio.diagram.test.TestBug1640;
import org.bonitasoft.studio.diagram.test.TestConvertToMessage;
import org.bonitasoft.studio.diagram.test.TestMoveBetweenLane;
import org.bonitasoft.studio.diagram.test.TestSave;
import org.bonitasoft.studio.engine.test.bar.BarExporterTest;
import org.bonitasoft.studio.exporter.tests.bos.ExportBosArchiveIT;
import org.bonitasoft.studio.migration.tests.MigrationReporTest;
import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.bonitasoft.studio.properties.test.CallActivityMappingIT;
import org.bonitasoft.studio.properties.test.TestResources;
import org.bonitasoft.studio.tests.IHeapDumper;
import org.bonitasoft.studio.tests.businessobject.CreateDeployExportBusinessObjectIT;
import org.bonitasoft.studio.tests.conditions.SWTBOTTestConditions;
import org.bonitasoft.studio.tests.contract.ContractIT;
import org.bonitasoft.studio.tests.data.DataWizardIT;
import org.bonitasoft.studio.tests.dialog.OpenNameAndVersionForDiagramDialogTest;
import org.bonitasoft.studio.tests.dialog.TestDuplicate;
import org.bonitasoft.studio.tests.document.TestDocument;
import org.bonitasoft.studio.tests.document.TestRunWithDocument;
import org.bonitasoft.studio.tests.draw2d.TestLifeCycleWidget;
import org.bonitasoft.studio.tests.form.DeleteWidgetWithContextMenuIT;
import org.bonitasoft.studio.tests.groovy.TestBonitaGroovyEditorDialog;
import org.bonitasoft.studio.tests.iteration.MultiInstantiationIT;
import org.bonitasoft.studio.tests.migration.BarImportReportIT;
import org.bonitasoft.studio.tests.refactoring.widget.RefactorWidgetIT;
import org.bonitasoft.studio.tests.timer.TestTimer;
import org.bonitasoft.studio.util.test.BonitaSuite;
import org.bonitasoft.studio.validators.test.swtbot.TestCreateValidatorWizard;
import org.bonitasoft.studio.validators.test.swtbot.TestFormValidatorIT;
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
        TestBug1640.class,
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
        BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore()
                .setValue(BonitaPreferenceConstants.CONSOLE_BROWSER_CHOICE, BonitaPreferenceConstants.INTERNAL_BROWSER);
        BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore().setValue(BonitaPreferenceConstants.ASK_RENAME_ON_FIRST_SAVE, false);
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
