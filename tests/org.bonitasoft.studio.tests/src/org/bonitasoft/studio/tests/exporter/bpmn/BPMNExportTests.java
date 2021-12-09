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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Collections;
import java.util.List;

import org.bonitasoft.studio.assertions.StatusAssert;
import org.bonitasoft.studio.common.model.IModelSearch;
import org.bonitasoft.studio.common.model.ModelSearch;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.connectors.repository.ConnectorDefRepositoryStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.exporter.bpmn.transfo.BonitaToBPMNExporter;
import org.bonitasoft.studio.exporter.bpmn.transfo.OSGIConnectorTransformationXSLProvider;
import org.bonitasoft.studio.exporter.extension.BonitaModelExporterImpl;
import org.bonitasoft.studio.exporter.extension.IBonitaModelExporter;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.diagram.part.ProcessDiagramEditor;
import org.bonitasoft.studio.swtbot.framework.SWTBotConnectorTestUtil;
import org.bonitasoft.studio.swtbot.framework.application.BotApplicationWorkbenchWindow;
import org.bonitasoft.studio.swtbot.framework.projectExplorer.ProjectExplorerBot;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotMenu;
import org.eclipse.ui.IEditorReference;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
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
        List<AbstractProcess> allProcesses = dStore.getAllProcesses();
        IModelSearch modelSearch = new ModelSearch(() -> allProcesses, () -> connectorDefStore.getDefinitions());

        final IBonitaModelExporter exporter = new BonitaModelExporterImpl(c.eResource(), modelSearch);
        final File bpmnFileExported = tmpFolder.newFile("withAllExported.bpmn");
        BonitaToBPMNExporter bonitaToBPMNExporter = new BonitaToBPMNExporter();
        bonitaToBPMNExporter.export(exporter, modelSearch, bpmnFileExported,
                new OSGIConnectorTransformationXSLProvider());
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
        new File(resource.getURI().toFileString()).delete();
    }

    private boolean menuBPMN2found = false;

    @Test
    public void testBPMN2MenuPresent() {
        final SWTBotMenu processMenu = bot.menu("File");
        final SWTBotMenu exportAsMenu = processMenu.menu("Export as").click();
        final MenuItem mi = exportAsMenu.widget;
        Display.getDefault().syncExec(() -> {
            mi.getMenu().notifyListeners(SWT.Show, new Event());
            final MenuItem[] mis = mi.getMenu().getItems();
            for (final MenuItem menuItem : mis) {
                final String menuText = menuItem.getText();
                menuBPMN2found = menuBPMN2found || "BPMN 2.0...".equals(menuText);
            }
        });

        assertTrue("BPMN 2.0 menu is not present", menuBPMN2found);
    }

    @Test
    public void testBPMN2MenuPresentAfterOepningAnotherEditor() {
        final String id = "testBPMN2MenuPresentAfterOpeningAnotherEditor";
        final String className = "MyConnectorImpl" + System.currentTimeMillis();
        final String packageName = "org.bonita.connector.test";
        SWTBotConnectorTestUtil.createConnectorDefAndImpl(bot, id, "1.0.0", className, packageName);

        bot.waitUntil(Conditions.waitForEditor(new BaseMatcher<IEditorReference>() {

            @Override
            public boolean matches(final Object item) {
                return "org.eclipse.jdt.ui.CompilationUnitEditor".equals(((IEditorReference) item).getId());
            }

            @Override
            public void describeTo(final Description description) {

            }

        }), 10000);
        final SWTBotEditor activeEditor = bot.activeEditor();
        assertEquals("org.eclipse.jdt.ui.CompilationUnitEditor", activeEditor.getReference().getId());

        final SWTBotMenu processMenu = bot.menu("File");

        final SWTBotMenu exportAsMenu = processMenu.menu("Export as").click();
        final MenuItem mi = exportAsMenu.widget;
        Display.getDefault().syncExec(() -> {
            mi.getMenu().notifyListeners(SWT.Show, new Event());
            final MenuItem[] mis = mi.getMenu().getItems();

            for (final MenuItem menuItem : mis) {
                final String menuText = menuItem.getText();
                menuBPMN2found = menuBPMN2found || "BPMN 2.0...".equals(menuText);
            }
        });

        assertTrue("BPMN 2.0 menu is not present", menuBPMN2found);
    }

}
