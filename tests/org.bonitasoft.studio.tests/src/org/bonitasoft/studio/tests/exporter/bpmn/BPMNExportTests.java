/**
 * Copyright (C) 2010-2011 BonitaSoft S.A.
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
package org.bonitasoft.studio.tests.exporter.bpmn;

import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.util.Collections;

import org.bonitasoft.bonita2bpmn.extension.BonitaModelExporterImpl;
import org.bonitasoft.bonita2bpmn.extension.IBonitaModelExporter;
import org.bonitasoft.bonita2bpmn.transfo.BonitaToBPMNExporter;
import org.bonitasoft.bonita2bpmn.transfo.ConnectorTransformationXSLProvider;
import org.bonitasoft.bpm.model.process.MainProcess;
import org.bonitasoft.bpm.model.util.IModelSearch;
import org.bonitasoft.bpm.model.util.ModelSearch;
import org.bonitasoft.studio.assertions.StatusAssert;
import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.connectors.repository.ConnectorDefRepositoryStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.model.process.diagram.part.ProcessDiagramEditor;
import org.bonitasoft.studio.swtbot.framework.application.BotApplicationWorkbenchWindow;
import org.bonitasoft.studio.swtbot.framework.projectExplorer.ProjectExplorerBot;
import org.bonitasoft.studio.swtbot.framework.rule.SWTGefBotRule;
import org.bonitasoft.studio.tests.util.ProjectUtil;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.junit.After;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.omg.spec.bpmn.model.DocumentRoot;
import org.omg.spec.bpmn.model.TDefinitions;
import org.omg.spec.bpmn.model.TFlowElement;
import org.omg.spec.bpmn.model.TProcess;
import org.omg.spec.bpmn.model.TRootElement;
import org.omg.spec.bpmn.model.TSequenceFlow;
import org.omg.spec.bpmn.model.util.ModelResourceFactoryImpl;

@RunWith(SWTBotJunit4ClassRunner.class)
public class BPMNExportTests {

    private final SWTGefBot bot = new SWTGefBot();

    @Rule
    public TemporaryFolder tmpFolder = new TemporaryFolder();

    @Rule
    public SWTGefBotRule swtGefBotRule = new SWTGefBotRule(bot);

    @After
    public void cleanup() throws CoreException {
        ProjectUtil.cleanProject();
    }

    @Test
    public void testExportToBPMN() throws Exception {
        new BotApplicationWorkbenchWindow(bot)
                .importBOSArchive()
                .setArchive(BPMNExportTests.class.getResource("TestExportToBPMNDiagram_1_0.bos"))
                .currentRepository()
                .next()
                .next()
                .finish();

        var explorer = new ProjectExplorerBot(bot);
        explorer.diagram().openDiagram("TestExportToBPMNDiagram", "1.0");

        final MainProcess c = (MainProcess) ((ProcessDiagramEditor) bot.activeEditor().getReference()
                .getPart(false)).getDiagram().getElement();
        DiagramRepositoryStore dStore = RepositoryManager.getInstance()
                .getRepositoryStore(DiagramRepositoryStore.class);
        ConnectorDefRepositoryStore connectorDefStore = RepositoryManager.getInstance()
                .getRepositoryStore(ConnectorDefRepositoryStore.class);
        IModelSearch modelSearch = new ModelSearch(dStore::getAllProcesses);

        final IBonitaModelExporter exporter = new BonitaModelExporterImpl(c.eResource(), modelSearch);
        final File bpmnFileExported = tmpFolder.newFile("withAllExported.bpmn");
        BonitaToBPMNExporter bonitaToBPMNExporter = new BonitaToBPMNExporter();
        bonitaToBPMNExporter.export(exporter, modelSearch, connectorDefStore::getDefinitions, bpmnFileExported,
                ConnectorTransformationXSLProvider.DEFAULT, ProductVersion.CURRENT_VERSION);
        StatusAssert.assertThat(bonitaToBPMNExporter.getStatus()).hasSeverity(IStatus.INFO);

        final BufferedReader reader = new BufferedReader(new FileReader(bpmnFileExported));
        String line = ""; //$NON-NLS-1$
        boolean correctEncoding = false;
        while ((line = reader.readLine()) != null) {
            Assert.assertFalse("Serialized as XMI...", line.toLowerCase().contains("xsi:type=\"model:tUserTask\""));
            if (line.contains("encoding=\"UTF-8\"")) {
                correctEncoding = true;
            }
        }
        reader.close();

        assertTrue("Bad xml file encding", correctEncoding);

        final ModelResourceFactoryImpl factory = new ModelResourceFactoryImpl();
        final Resource resource = factory.createResource(URI.createFileURI(bpmnFileExported.getAbsolutePath()));
        resource.load(Collections.EMPTY_MAP);
        final TDefinitions bpmnProcess = ((DocumentRoot) resource.getContents().get(0)).getDefinitions();
        TProcess bpmnPool1 = null, bpmnPool2 = null;
        for (final TRootElement el : bpmnProcess.getRootElement()) {
            if (el instanceof TProcess) {
                final TProcess process = (TProcess) el;
                if (process.getName().equals("Pool1")) {
                    bpmnPool1 = process;
                } else if (process.getName().equals("Pool2")) {
                    bpmnPool2 = process;
                }
            }
        }
        boolean conditionFalseFound = false;
        for (final TFlowElement el : bpmnPool1.getFlowElement()) {
            if (el instanceof TSequenceFlow) {
                final TSequenceFlow sequenceFlow = (TSequenceFlow) el;
                if (sequenceFlow.getConditionExpression() != null) {
                    conditionFalseFound = conditionFalseFound
                            || sequenceFlow.getConditionExpression().getMixed().get(0).getValue().toString()
                                    .equals("false");
                }
            }
        }
        assertTrue("Could not find the condition on the transition", conditionFalseFound);

        // clean
        Files.deleteIfExists(new File(resource.getURI().toFileString()).toPath());
    }

}
