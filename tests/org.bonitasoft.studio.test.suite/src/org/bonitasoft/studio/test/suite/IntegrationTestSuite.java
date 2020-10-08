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

import org.bonitasoft.studio.tests.CloseAllEditors;
import org.bonitasoft.studio.tests.EngineConfigurationIT;
import org.bonitasoft.studio.tests.ProductVersionIT;
import org.bonitasoft.studio.tests.TestFullScenario;
import org.bonitasoft.studio.tests.TestInitialWorkspace;
import org.bonitasoft.studio.tests.TestPatchedBundles;
import org.bonitasoft.studio.tests.TestPathSize;
import org.bonitasoft.studio.tests.TestSpecificWizardIds;
import org.bonitasoft.studio.tests.actors.TestExportOrganization;
import org.bonitasoft.studio.tests.actors.TestImportActorFilter;
import org.bonitasoft.studio.tests.actors.TestImportOrganization;
import org.bonitasoft.studio.tests.actors.TestProvidedActorFilterDefinitionAndImplementation;
import org.bonitasoft.studio.tests.api.TestBonitaAPI;
import org.bonitasoft.studio.tests.bar.ExportBarIT;
import org.bonitasoft.studio.tests.bar.TestExportProcessBar;
import org.bonitasoft.studio.tests.bar.TestOperationHistory;
import org.bonitasoft.studio.tests.bar.TestShowEngineLog;
import org.bonitasoft.studio.tests.bug.TestBugs;
import org.bonitasoft.studio.tests.businessobject.DeployBDROperationIT;
import org.bonitasoft.studio.tests.businessobject.ImportLegacyBDMIT;
import org.bonitasoft.studio.tests.conditions.TestConditions;
import org.bonitasoft.studio.tests.conditions.TestDecisionTableToGroovy;
import org.bonitasoft.studio.tests.configuration.TestConfigurationSynhronizer;
import org.bonitasoft.studio.tests.connectors.DriverAssociationContributionIT;
import org.bonitasoft.studio.tests.connectors.GroovyConnectorIT;
import org.bonitasoft.studio.tests.connectors.TestDatabaseConnectorResulset;
import org.bonitasoft.studio.tests.connectors.TestImportConnector;
import org.bonitasoft.studio.tests.connectors.TestProvidedDefinitionAndImplementation;
import org.bonitasoft.studio.tests.connectors.TestTestConnectorOperation;
import org.bonitasoft.studio.tests.connectors.TestWebserviceVersionForBPMNImport;
import org.bonitasoft.studio.tests.data.DataRefactorIT;
import org.bonitasoft.studio.tests.deploy.TestDeployCommand;
import org.bonitasoft.studio.tests.designer.FragmentCreationIT;
import org.bonitasoft.studio.tests.designer.UIDArtifactCreationIT;
import org.bonitasoft.studio.tests.document.RefactorDocumentOperationTest;
import org.bonitasoft.studio.tests.document.TestDocumentRefactoring;
import org.bonitasoft.studio.tests.engine.StudioShutdownIT;
import org.bonitasoft.studio.tests.engine.TestJavaDoc;
import org.bonitasoft.studio.tests.engine.TestSubprocessEventExport;
import org.bonitasoft.studio.tests.engine.TestUserFilterMatchingEngineVersion;
import org.bonitasoft.studio.tests.exporter.bpmn.BPMNDataExportImportTest;
import org.bonitasoft.studio.tests.exporter.bpmn.BPMNImportExportTest;
import org.bonitasoft.studio.tests.exporter.bpmn.BPMNSequenceFlowConditionExportImportTest;
import org.bonitasoft.studio.tests.groovy.TestFunctionRepository;
import org.bonitasoft.studio.tests.groovy.TestGroovyScriptExpressionEditor;
import org.bonitasoft.studio.tests.importer.api.ProcBuilderTests;
import org.bonitasoft.studio.tests.importer.bos.TestBOSArchiveImport;
import org.bonitasoft.studio.tests.importer.bpmn2.TestImportBPMN2;
import org.bonitasoft.studio.tests.importer.extensions.TestImportExtensionPoint;
import org.bonitasoft.studio.tests.migration.DocumentMigrationIT;
import org.bonitasoft.studio.tests.migration.EdaptHistoryIT;
import org.bonitasoft.studio.tests.parameter.TestParametersRefactoring;
import org.bonitasoft.studio.tests.parameter.TestProcessParameters;
import org.bonitasoft.studio.tests.processzoo.TestProcessZoo;
import org.bonitasoft.studio.tests.properties.TestMessageRefactoring;
import org.bonitasoft.studio.tests.repository.TestAddJar;
import org.bonitasoft.studio.tests.repository.TestExtensionProject;
import org.bonitasoft.studio.tests.repository.TestImportExportAndDeleteRepository;
import org.bonitasoft.studio.tests.repository.TestImportRepository;
import org.bonitasoft.studio.tests.repository.TestRepositoryLocation;
import org.bonitasoft.studio.tests.repository.TestSwitchRepository;
import org.bonitasoft.studio.tests.repository.UIDesignerWorkspaceIntegrationIT;
import org.bonitasoft.studio.tests.restApiExtension.BuildAndDeployRestAPIExtensionIT;
import org.bonitasoft.studio.tests.restApiExtension.CreateRestAPIExtensionProjectIT;
import org.bonitasoft.studio.tests.restApiExtension.ExportRestAPIExtensionProjectIT;
import org.bonitasoft.studio.tests.restApiExtension.RestAPIExtensionMarkerResolutionIT;
import org.bonitasoft.studio.tests.searchindex.TestRunSearchIndex;
import org.bonitasoft.studio.tests.subprocess.TestSubprocess;
import org.bonitasoft.studio.tests.timer.TestNonInterruptingBoundaryTimerEvent;
import org.bonitasoft.studio.tests.validation.TestTokenDispatcher;
import org.bonitasoft.studio.tests.validation.TestValidationConstraints;
import org.bonitasoft.studio.util.test.BonitaSuite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(BonitaSuite.class)
@Suite.SuiteClasses({
        TestPatchedBundles.class,
        TestSpecificWizardIds.class,
        EdaptHistoryIT.class,
        TestBugs.class,
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
        RefactorDocumentOperationTest.class,
        TestConfigurationSynhronizer.class,
        TestImportExportAndDeleteRepository.class,
        TestImportExtensionPoint.class,
        ProcBuilderTests.class,
        TestImportRepository.class,
        TestImportBPMN2.class,
        TestAddJar.class,
        TestJavaDoc.class,
        TestOperationHistory.class,
        TestDecisionTableToGroovy.class,
        TestSubprocessEventExport.class,
        TestRepositoryLocation.class,
        TestShowEngineLog.class,
        DriverAssociationContributionIT.class,
        TestProcessZoo.class,
        TestProvidedDefinitionAndImplementation.class,
        TestProvidedActorFilterDefinitionAndImplementation.class,
        TestDatabaseConnectorResulset.class,
        TestBOSArchiveImport.class,
        TestGroovyScriptExpressionEditor.class,
        TestExportProcessBar.class,
        BPMNImportExportTest.class,
        BPMNDataExportImportTest.class,
        BPMNSequenceFlowConditionExportImportTest.class,
        DataRefactorIT.class,
        TestBonitaAPI.class,
        TestNonInterruptingBoundaryTimerEvent.class,
        TestUserFilterMatchingEngineVersion.class,
        TestDeployCommand.class,
        TestValidationConstraints.class,
        TestTestConnectorOperation.class,
        TestWebserviceVersionForBPMNImport.class,
        TestTokenDispatcher.class,
        DocumentMigrationIT.class,
        GroovyConnectorIT.class,
        DeployBDROperationIT.class,
        ImportLegacyBDMIT.class,
        TestProcessParameters.class,
        TestParametersRefactoring.class,
        ExportBarIT.class,
        UIDesignerWorkspaceIntegrationIT.class,
        CloseAllEditors.class,
        TestMessageRefactoring.class,
        UIDArtifactCreationIT.class,
        FragmentCreationIT.class,
        TestSwitchRepository.class,
        CreateRestAPIExtensionProjectIT.class,
        RestAPIExtensionMarkerResolutionIT.class,
        BuildAndDeployRestAPIExtensionIT.class,
        ExportRestAPIExtensionProjectIT.class,
        TestRunSearchIndex.class,
        StudioShutdownIT.class
})
public class IntegrationTestSuite {

}
