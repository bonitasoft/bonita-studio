/**
 * Copyright (C) 2012 BonitaSoft S.A.
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
package org.bonitasoft.studio.exporter.tests.bpmn;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Collections;
import java.util.Map;

import org.bonitasoft.studio.exporter.bpmn.transfo.BonitaToBPMN;
import org.bonitasoft.studio.exporter.extension.BonitaModelExporterImpl;
import org.bonitasoft.studio.exporter.extension.IBonitaModelExporter;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.process.CallActivity;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.InputMapping;
import org.bonitasoft.studio.model.process.Lane;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.OutputMapping;
import org.bonitasoft.studio.model.process.Pool;
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
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.spec.bpmn.di.util.DiResourceFactoryImpl;
import org.omg.spec.bpmn.model.DocumentRoot;

@RunWith(SWTBotJunit4ClassRunner.class)
public class BPMNExportImportDataMappingTest {

    private static MainProcess mainProcessAfterReimport;
    private static boolean isInitalized = false;
    private static CallActivity callActivity;

    private final SWTGefBot bot = new SWTGefBot();

    @Before
    public void init() throws IOException {
        if (!isInitalized) {
            prepareTest();
        }
        isInitalized = true;
    }

    @Test
    public void testInputMappingWithTextVariable() {
        for (final InputMapping im : callActivity.getInputMappings()) {
            if ("childTextData".equals(im.getSubprocessTarget())) {
                final Expression processSourceData = im.getProcessSource();
                assertNotNull(processSourceData);
                assertEquals("parentTextData", processSourceData.getName());
                return;
            }
        }
        fail("Text input data mapping lost");
    }

    @Test
    public void testInputMappingWithBooleanVariable() {
        for (final InputMapping im : callActivity.getInputMappings()) {
            if ("childBooleanData".equals(im.getSubprocessTarget())) {
                final Expression processSourceData = im.getProcessSource();
                assertNotNull(processSourceData);
                assertEquals("parentBooleanData", processSourceData.getName());
                return;
            }
        }
        fail("Boolean input data mapping lost");
    }

    @Test
    public void testOutputMappingWithTextVariable() {
        for (final OutputMapping im : callActivity.getOutputMappings()) {
            if ("childTextData".equals(im.getSubprocessSource())) {
                final Data processSourceData = im.getProcessTarget();
                assertNotNull(processSourceData);
                assertEquals("parentTextData", processSourceData.getName());
                return;
            }
        }
        fail("Text data mapping lost");
    }

    @Test
    public void testOutputMappingWithBooleanVariable() {
        for (final OutputMapping im : callActivity.getOutputMappings()) {
            if ("childBooleanData".equals(im.getSubprocessSource())) {
                final Data processSourceData = im.getProcessTarget();
                assertNotNull(processSourceData);
                assertEquals("parentBooleanData", processSourceData.getName());
                return;
            }
        }
        fail("Boolean output data mapping lost");
    }

    protected void prepareTest() throws IOException {
        new BotApplicationWorkbenchWindow(bot).importBOSArchive()
                .setArchive(
                        BPMNExportImportDataMappingTest.class.getResource("testBPMNDataMapping-1.0.bos"))
                .finish();

        final SWTBotGefEditor editor1 = bot.gefEditor(bot.activeEditor().getTitle());
        final SWTBotGefEditPart step1Part = editor1.getEditPart("Step1").parent();
        final MainProcessEditPart mped = (MainProcessEditPart) step1Part.part().getRoot().getChildren().get(0);
        final IBonitaModelExporter exporter = new BonitaModelExporterImpl(mped);
        final File bpmnFileExported = File.createTempFile("testSingleConnectorOnServiceTask", ".bpmn");
        final boolean transformed = new BonitaToBPMN().transform(exporter, bpmnFileExported, new NullProgressMonitor());
        assertTrue("Error during export", transformed);

        final ResourceSet resourceSet1 = new ResourceSetImpl();
        final Map<String, Object> extensionToFactoryMap = resourceSet1.getResourceFactoryRegistry()
                .getExtensionToFactoryMap();
        final DiResourceFactoryImpl diResourceFactoryImpl = new DiResourceFactoryImpl();
        extensionToFactoryMap.put("bpmn", diResourceFactoryImpl);
        final Resource resource2 = resourceSet1.createResource(URI.createFileURI(bpmnFileExported.getAbsolutePath()));
        resource2.load(Collections.emptyMap());

        final DocumentRoot model2 = (DocumentRoot) resource2.getContents().get(0);

        Display.getDefault().syncExec(new Runnable() {

            @Override
            public void run() {
                try {
                    mainProcessAfterReimport = BPMNTestUtil.importBPMNFile(model2);
                } catch (final MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        });

        for (final Element element : ((Lane) ((Pool) mainProcessAfterReimport.getElements().get(0)).getElements().get(0))
                .getElements()) {
            if (element instanceof CallActivity) {
                callActivity = (CallActivity) element;
                break;
            }
        }
    }

}
