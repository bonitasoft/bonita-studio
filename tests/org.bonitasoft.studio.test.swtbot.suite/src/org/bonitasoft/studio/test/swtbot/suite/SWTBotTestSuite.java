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

package org.bonitasoft.studio.test.swtbot.suite;

import org.bonitasoft.studio.tests.actors.ActorDefinitionTranslationsTest;
import org.bonitasoft.studio.tests.actors.ActorFilterDefinitionTest;
import org.bonitasoft.studio.tests.actors.ActorFilterDefinitionWizardPageTest;
import org.bonitasoft.studio.tests.actors.ActorFilterEditionTest;
import org.bonitasoft.studio.tests.actors.ActorFilterImplementationTest;
import org.bonitasoft.studio.tests.actors.OrganizationCreationTest;
import org.bonitasoft.studio.tests.actors.SWTBotActorFilterExportTests;
import org.bonitasoft.studio.tests.actors.TestOrganizationPassword;
import org.bonitasoft.studio.tests.applicationDescriptor.OpenExistingApplicationIT;
import org.bonitasoft.studio.tests.bar.BarExporterTest;
import org.bonitasoft.studio.tests.bar.TestMenus;
import org.bonitasoft.studio.tests.businessobject.CreateDeployExportBusinessObjectIT;
import org.bonitasoft.studio.tests.conditions.SWTBOTTestConditions;
import org.bonitasoft.studio.tests.configuration.ConfigurationDialogIT;
import org.bonitasoft.studio.tests.configuration.JavaDependenciesConfigurationIT;
import org.bonitasoft.studio.tests.connectors.ConnectorDefinitionTranslationsTest;
import org.bonitasoft.studio.tests.connectors.ConnectorDefinitionWizardPageTest;
import org.bonitasoft.studio.tests.connectors.ConnectorEditedInAsingleCommandIT;
import org.bonitasoft.studio.tests.connectors.ConnectorEditionTest;
import org.bonitasoft.studio.tests.connectors.ConnectorImplementationTest;
import org.bonitasoft.studio.tests.connectors.SWTBotConnectorDefinitionTest;
import org.bonitasoft.studio.tests.connectors.SWTBotConnectorExportTests;
import org.bonitasoft.studio.tests.connectors.TestDatabaseConnectorOutputMode;
import org.bonitasoft.studio.tests.connectors.TestLoadSaveConnectorConfiguration;
import org.bonitasoft.studio.tests.connectors.TestTextAreaInConnectorWizard;
import org.bonitasoft.studio.tests.contract.ContractIT;
import org.bonitasoft.studio.tests.data.DataWizardIT;
import org.bonitasoft.studio.tests.deploy.DeployWizardIT;
import org.bonitasoft.studio.tests.diagram.ConvertActivityTypeWithBoundariesIT;
import org.bonitasoft.studio.tests.diagram.CopyPasteTests;
import org.bonitasoft.studio.tests.diagram.DeleteCommandTest;
import org.bonitasoft.studio.tests.diagram.DiagramTests;
import org.bonitasoft.studio.tests.diagram.DuplicateCommandTest;
import org.bonitasoft.studio.tests.diagram.ExtractAsSubprocessTest;
import org.bonitasoft.studio.tests.diagram.NewRunTest;
import org.bonitasoft.studio.tests.diagram.TestBoundariesCreation;
import org.bonitasoft.studio.tests.diagram.TestDeleteDiagrams;
import org.bonitasoft.studio.tests.diagram.TestMoveBetweenLane;
import org.bonitasoft.studio.tests.diagram.TestOpenDiagram;
import org.bonitasoft.studio.tests.diagram.TestRenameDiagram;
import org.bonitasoft.studio.tests.diagram.TestSave;
import org.bonitasoft.studio.tests.diagram.TestUndoRedoStackLimit;
import org.bonitasoft.studio.tests.dialog.OpenNameAndVersionDiagramForDiagramTest;
import org.bonitasoft.studio.tests.dialog.OpenNameAndVersionForDiagramDialogTest;
import org.bonitasoft.studio.tests.document.TestDocument;
import org.bonitasoft.studio.tests.document.TestRunWithDocument;
import org.bonitasoft.studio.tests.draw2d.TestLifeCycleWidget;
import org.bonitasoft.studio.tests.duplicate.TestDuplicate;
import org.bonitasoft.studio.tests.exporter.ExportBosArchiveIT;
import org.bonitasoft.studio.tests.exporter.bpmn.BPMNConnectorExportImportTest;
import org.bonitasoft.studio.tests.exporter.bpmn.BPMNEventSubProcessExportImportTest;
import org.bonitasoft.studio.tests.exporter.bpmn.BPMNExportTests;
import org.bonitasoft.studio.tests.exporter.bpmn.BPMNGatewayExportImportTest;
import org.bonitasoft.studio.tests.exporter.bpmn.BPMNSequenceFlowDefaultFlowExportImportTest;
import org.bonitasoft.studio.tests.expressionEditor.TestConnectorExpression;
import org.bonitasoft.studio.tests.expressionEditor.TestPatternExpressionViewer;
import org.bonitasoft.studio.tests.iteration.MultiInstantiationIT;
import org.bonitasoft.studio.tests.processzoo.examples.TestWebPurchase;
import org.bonitasoft.studio.tests.projectExplorer.ProjectExplorerBdmIT;
import org.bonitasoft.studio.tests.projectExplorer.ProjectExplorerConnectorIT;
import org.bonitasoft.studio.tests.projectExplorer.ProjectExplorerDiagramIT;
import org.bonitasoft.studio.tests.projectExplorer.ProjectExplorerLivingApplicationIT;
import org.bonitasoft.studio.tests.projectExplorer.ProjectExplorerOrganizationIT;
import org.bonitasoft.studio.tests.properties.CallActivityMappingIT;
import org.bonitasoft.studio.tests.properties.TestConditionExpression;
import org.bonitasoft.studio.tests.properties.TestDecisionTable;
import org.bonitasoft.studio.tests.properties.TestThrowCatchMessage;
import org.bonitasoft.studio.tests.timer.TestTimer;
import org.bonitasoft.studio.util.test.BonitaSuite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(BonitaSuite.class)
@Suite.SuiteClasses({
        ProjectExplorerDiagramIT.class,
        ProjectExplorerOrganizationIT.class,
        ProjectExplorerBdmIT.class,
        ProjectExplorerLivingApplicationIT.class,
        ProjectExplorerConnectorIT.class,
        DiagramTests.class,
        OpenExistingApplicationIT.class,
        TestMenus.class,
        OpenNameAndVersionForDiagramDialogTest.class,
        TestRenameDiagram.class,
        TestDatabaseConnectorOutputMode.class,
        TestPatternExpressionViewer.class,
        TestLoadSaveConnectorConfiguration.class,
        TestConditionExpression.class,
        ConfigurationDialogIT.class,
        TestDeleteDiagrams.class,
        ActorDefinitionTranslationsTest.class,
        ActorFilterDefinitionTest.class,
        ActorFilterDefinitionWizardPageTest.class,
        ActorFilterEditionTest.class,
        ActorFilterImplementationTest.class,
        SWTBotActorFilterExportTests.class,
        ConnectorEditionTest.class,
        ConnectorDefinitionWizardPageTest.class,
        ConnectorImplementationTest.class,
        TestTextAreaInConnectorWizard.class,
        SWTBotConnectorExportTests.class,
        SWTBotConnectorDefinitionTest.class,
        TestDecisionTable.class,
        TestThrowCatchMessage.class,
        TestWebPurchase.class,
        TestUndoRedoStackLimit.class,
        TestOpenDiagram.class,
        OpenNameAndVersionDiagramForDiagramTest.class,
        ConnectorDefinitionTranslationsTest.class,
        CopyPasteTests.class,
        DeleteCommandTest.class,
        NewRunTest.class,
        DataWizardIT.class,
        SWTBOTTestConditions.class,
        ExtractAsSubprocessTest.class,
        TestBoundariesCreation.class,
        OpenNameAndVersionForDiagramDialogTest.class,
        TestDuplicate.class,
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
        TestTimer.class,
        ConnectorEditedInAsingleCommandIT.class,
        JavaDependenciesConfigurationIT.class,
        ContractIT.class,
        TestOrganizationPassword.class,
        CallActivityMappingIT.class,
        CreateDeployExportBusinessObjectIT.class,
        ConvertActivityTypeWithBoundariesIT.class,
        BPMNExportTests.class,
        BPMNSequenceFlowDefaultFlowExportImportTest.class,
        BPMNConnectorExportImportTest.class,
        BPMNGatewayExportImportTest.class,
        BPMNEventSubProcessExportImportTest.class,
        DeployWizardIT.class,
        OrganizationCreationTest.class
})
public class SWTBotTestSuite {

}
