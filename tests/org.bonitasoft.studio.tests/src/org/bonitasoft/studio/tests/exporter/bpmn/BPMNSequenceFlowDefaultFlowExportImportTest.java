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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.bonitasoft.studio.exporter.bpmn.transfo.BonitaToBPMN;
import org.bonitasoft.studio.exporter.extension.BonitaModelExporterImpl;
import org.bonitasoft.studio.exporter.extension.IBonitaModelExporter;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.SequenceFlow;
import org.bonitasoft.studio.model.process.diagram.edit.parts.MainProcessEditPart;
import org.bonitasoft.studio.swtbot.framework.application.BotApplicationWorkbenchWindow;
import org.eclipse.core.runtime.NullProgressMonitor;
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
public class BPMNSequenceFlowDefaultFlowExportImportTest {

    private final SWTGefBot bot = new SWTGefBot();
    private MainProcess mainProcessAfterReimport;
    List<SequenceFlow> sequenceFlows = new ArrayList<>();
    Resource resource;

    @Rule
    public TemporaryFolder tmpFolder = new TemporaryFolder();

    @Test
    public void testDefaultFlow() {
        int numberOfDefaultFlow = 0;
        for (final SequenceFlow sequenceFlow : sequenceFlows) {
            if (sequenceFlow.isIsDefault()) {
                numberOfDefaultFlow++;
            }
        }
        assertEquals("Default has not been exported/imported succesfully in BPMN2", 2, numberOfDefaultFlow);
    }

    @Before
    public void prepareTest() throws IOException {
        new BotApplicationWorkbenchWindow(bot)
                .importBOSArchive()
                .setArchive(BPMNConnectorExportImportTest.class.getResource("MyDiagramToTestDefaultFlowInBPMN-1.0.bos"))
                .finish();

        final SWTBotGefEditor editor1 = bot.gefEditor(bot.activeEditor().getTitle());
        final SWTBotGefEditPart step1Part = editor1.getEditPart("Step1").parent();
        final MainProcessEditPart mped = (MainProcessEditPart) step1Part.part().getRoot().getChildren().get(0);
        final IBonitaModelExporter exporter = new BonitaModelExporterImpl(mped);
        final File bpmnFileExported = tmpFolder.newFile("PoolToTestDefaultFlowInBPMN.bpmn");
        final boolean transformed = new BonitaToBPMN().transform(exporter, bpmnFileExported, new NullProgressMonitor());
        assertTrue("Error during export", transformed);

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

        for (final Element element : ((Pool) mainProcessAfterReimport.getElements().get(0)).getConnections()) {
            if (element instanceof SequenceFlow) {
                sequenceFlows.add((SequenceFlow) element);
            }
        }
    }

    @After
    public void clean() {
        if (resource != null) {
            resource.unload();
        }
    }

}
