/**
 * Copyright (C) 2013 BonitaSoft S.A.
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
package org.bonitasoft.studio.tests.exporter.bpmn;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.bonitasoft.studio.assertions.StatusAssert;
import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.common.model.IModelSearch;
import org.bonitasoft.studio.common.model.ModelSearch;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.connectors.repository.ConnectorDefRepositoryStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.exporter.bpmn.transfo.BonitaToBPMNExporter;
import org.bonitasoft.studio.exporter.bpmn.transfo.OSGIConnectorTransformationXSLProvider;
import org.bonitasoft.studio.exporter.extension.BonitaModelExporterImpl;
import org.bonitasoft.studio.exporter.extension.IBonitaModelExporter;
import org.bonitasoft.studio.model.process.ANDGateway;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.InclusiveGateway;
import org.bonitasoft.studio.model.process.Lane;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.XORGateway;
import org.bonitasoft.studio.model.process.diagram.edit.parts.MainProcessEditPart;
import org.bonitasoft.studio.swtbot.framework.application.BotApplicationWorkbenchWindow;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditor;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.omg.spec.bpmn.di.util.DiResourceFactoryImpl;
import org.omg.spec.bpmn.model.DocumentRoot;

@RunWith(SWTBotJunit4ClassRunner.class)
public class BPMNGatewayExportImportTest {

    private MainProcess mainProcessAfterReimport;

    private static final String AND_GATEWAY_NAME = "Gate1";
    private static final String INC_GATEWAY_NAME = "Gateway1";
    private static final String XOR_GATEWAY_NAME = "Gate3";

    private final SWTGefBot bot = new SWTGefBot();

    @Rule
    public TemporaryFolder tmpFolder = new TemporaryFolder();

    private Resource resource;

    @Test
    public void shouldGatewaysHaveBeenExportedImported() {
        final Lane lane = (Lane) ((Pool) mainProcessAfterReimport.getElements().get(0)).getElements().get(0);
        ANDGateway andGatewayAfterReimport = null;
        InclusiveGateway incGatewayAfterReimport = null;
        XORGateway xorGatewayAfterReimport = null;
        for (final Element element : lane.getElements()) {
            if (element instanceof ANDGateway) {
                andGatewayAfterReimport = (ANDGateway) element;
            }
            if (element instanceof InclusiveGateway) {
               incGatewayAfterReimport = (InclusiveGateway) element;
            }
            if (element instanceof XORGateway) {
               xorGatewayAfterReimport = (XORGateway) element;
            }
        }
        
        assertThat(xorGatewayAfterReimport).isNotNull();
        assertThat(incGatewayAfterReimport).isNotNull();
        assertThat(andGatewayAfterReimport).isNotNull();
        
        assertEquals("XOR (Exclusive) Gateway name not correct", XOR_GATEWAY_NAME, xorGatewayAfterReimport.getName());
        assertEquals("OR (Inclusive) Gateway name not correct", INC_GATEWAY_NAME, incGatewayAfterReimport.getName());
        assertEquals("AND (Parallel) Gateway name not correct", AND_GATEWAY_NAME, andGatewayAfterReimport.getName());
    }

    @Before
    public void prepareTest() throws IOException {
        new BotApplicationWorkbenchWindow(bot)
                .importBOSArchive()
                .setArchive(BPMNGatewayExportImportTest.class.getResource("diagramToTestGateways-6.0.bos"))
                .currentRepository()
                .next()
                .next()
                .finish();

        final SWTBotGefEditor editor1 = bot.gefEditor(bot.activeEditor().getTitle());
        final SWTBotGefEditPart step1Part = editor1.getEditPart(AND_GATEWAY_NAME).parent();
        final MainProcessEditPart mped = (MainProcessEditPart) step1Part.part().getRoot().getChildren().get(0);

        DiagramRepositoryStore dStore = RepositoryManager.getInstance().getRepositoryStore(DiagramRepositoryStore.class);
        ConnectorDefRepositoryStore connectorDefStore = RepositoryManager.getInstance()
                .getRepositoryStore(ConnectorDefRepositoryStore.class);
        List<AbstractProcess> allProcesses = dStore.getAllProcesses();
        IModelSearch modelSearch = new ModelSearch(() -> allProcesses, connectorDefStore::getDefinitions);

        final IBonitaModelExporter exporter = new BonitaModelExporterImpl(mped.resolveSemanticElement().eResource(),
                modelSearch);
        final File bpmnFileExported = tmpFolder.newFile("testGateway.bpmn");
        BonitaToBPMNExporter bonitaToBPMNExporter = new BonitaToBPMNExporter();
        bonitaToBPMNExporter.export(exporter, modelSearch, bpmnFileExported, new OSGIConnectorTransformationXSLProvider(), ProductVersion.CURRENT_VERSION);
        StatusAssert.assertThat(bonitaToBPMNExporter.getStatus()).hasSeverity(IStatus.INFO);

        final ResourceSet resourceSet1 = new ResourceSetImpl();
        final Map<String, Object> extensionToFactoryMap = resourceSet1.getResourceFactoryRegistry()
                .getExtensionToFactoryMap();
        final DiResourceFactoryImpl diResourceFactoryImpl = new DiResourceFactoryImpl();
        extensionToFactoryMap.put("bpmn", diResourceFactoryImpl);
        resource = resourceSet1.createResource(URI.createFileURI(bpmnFileExported.getAbsolutePath()));
        resource.load(Collections.emptyMap());

        final DocumentRoot model2 = (DocumentRoot) resource.getContents().get(0);

        Display.getDefault().syncExec(() -> {
            try {
                mainProcessAfterReimport = BPMNTestUtil.importBPMNFile(model2);
            } catch (final MalformedURLException e) {
                e.printStackTrace();
            }
        });
    }

    @After
    public void clean() {
        resource.unload();
    }
}
