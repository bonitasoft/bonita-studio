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

import java.io.File;
import java.net.MalformedURLException;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import org.bonitasoft.bonita2bpmn.extension.BonitaModelExporterImpl;
import org.bonitasoft.bonita2bpmn.extension.IBonitaModelExporter;
import org.bonitasoft.bonita2bpmn.transfo.BonitaToBPMNExporter;
import org.bonitasoft.bonita2bpmn.transfo.ConnectorTransformationXSLProvider;
import org.bonitasoft.bpm.model.process.MainProcess;
import org.bonitasoft.bpm.model.process.SequenceFlow;
import org.bonitasoft.bpm.model.util.IModelSearch;
import org.bonitasoft.bpm.model.util.ModelSearch;
import org.bonitasoft.studio.assertions.StatusAssert;
import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.connectors.repository.ConnectorDefRepositoryStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.model.process.diagram.edit.parts.MainProcessEditPart;
import org.bonitasoft.studio.swtbot.framework.application.BotApplicationWorkbenchWindow;
import org.bonitasoft.studio.swtbot.framework.rule.SWTGefBotRule;
import org.bonitasoft.studio.tests.util.ProjectUtil;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditor;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.omg.spec.bpmn.di.util.DiResourceFactoryImpl;
import org.omg.spec.bpmn.model.DocumentRoot;

@RunWith(SWTBotJunit4ClassRunner.class)
public class BPMNSequenceFlowDefaultFlowExportImportTest {

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
    public void testDefaultFlow() throws Exception {
        new BotApplicationWorkbenchWindow(bot)
                .importBOSArchive()
                .setArchive(BPMNConnectorExportImportTest.class.getResource("MyDiagramToTestDefaultFlowInBPMN-1.0.bos"))
                .currentRepository()
                .next()
                .next()
                .finish();

        final SWTBotGefEditor editor1 = bot.gefEditor(bot.activeEditor().getTitle());
        final SWTBotGefEditPart step1Part = editor1.getEditPart("Step1").parent();
        final MainProcessEditPart mped = (MainProcessEditPart) step1Part.part().getRoot().getChildren().get(0);
        DiagramRepositoryStore dStore = RepositoryManager.getInstance()
                .getRepositoryStore(DiagramRepositoryStore.class);
        ConnectorDefRepositoryStore connectorDefStore = RepositoryManager.getInstance()
                .getRepositoryStore(ConnectorDefRepositoryStore.class);
        IModelSearch modelSearch = new ModelSearch(dStore::getAllProcesses);
        final IBonitaModelExporter exporter = new BonitaModelExporterImpl(mped.resolveSemanticElement().eResource(),
                modelSearch);
        final File bpmnFileExported = tmpFolder.newFile("PoolToTestDefaultFlowInBPMN.bpmn");
        BonitaToBPMNExporter bonitaToBPMNExporter = new BonitaToBPMNExporter();
        bonitaToBPMNExporter.export(exporter, modelSearch, connectorDefStore::getDefinitions, bpmnFileExported,
                ConnectorTransformationXSLProvider.DEFAULT, ProductVersion.CURRENT_VERSION);
        StatusAssert.assertThat(bonitaToBPMNExporter.getStatus()).hasSeverity(IStatus.INFO);

        final ResourceSet resourceSet1 = new ResourceSetImpl();
        final Map<String, Object> extensionToFactoryMap = resourceSet1.getResourceFactoryRegistry()
                .getExtensionToFactoryMap();
        final DiResourceFactoryImpl diResourceFactoryImpl = new DiResourceFactoryImpl();
        extensionToFactoryMap.put("bpmn", diResourceFactoryImpl);
        var resource = resourceSet1.createResource(URI.createFileURI(bpmnFileExported.getAbsolutePath()));
        resource.load(Collections.emptyMap());

        var bpmnModel = (DocumentRoot) resource.getContents().get(0);
        var modelRef = new AtomicReference<MainProcess>();
        Display.getDefault().syncExec(() -> {
            try {
                modelRef.set(BPMNTestUtil.importBPMNFile(bpmnModel));
            } catch (final MalformedURLException e) {
                throw new RuntimeException(e);
            }
        });

        assertThat(ModelHelper.getAllElementOfTypeIn(modelRef.get(), SequenceFlow.class))
                .filteredOn(SequenceFlow::isIsDefault)
                .hasSize(2);
    }

}
