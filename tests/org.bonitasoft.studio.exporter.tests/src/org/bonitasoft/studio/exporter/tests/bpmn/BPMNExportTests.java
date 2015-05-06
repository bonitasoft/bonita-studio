/**
 * Copyright (C) 2010-2011 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.exporter.tests.bpmn;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Collections;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.connectors.test.swtbot.SWTBotConnectorTestUtil;
import org.bonitasoft.studio.exporter.bpmn.transfo.BonitaToBPMN;
import org.bonitasoft.studio.exporter.extension.BonitaModelExporterImpl;
import org.bonitasoft.studio.exporter.extension.IBonitaModelExporter;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.diagram.edit.parts.MainProcessEditPart;
import org.bonitasoft.studio.model.process.diagram.part.ProcessDiagramEditor;
import org.bonitasoft.studio.test.swtbot.util.SWTBotTestUtil;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.gmf.runtime.diagram.ui.OffscreenEditPartFactory;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.gef.finder.SWTBotGefTestCase;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotMenu;
import org.eclipse.ui.IEditorReference;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.Assert;
import org.omg.spec.bpmn.model.DocumentRoot;
import org.omg.spec.bpmn.model.TDefinitions;
import org.omg.spec.bpmn.model.TFlowElement;
import org.omg.spec.bpmn.model.TProcess;
import org.omg.spec.bpmn.model.TRootElement;
import org.omg.spec.bpmn.model.TSequenceFlow;
import org.omg.spec.bpmn.model.util.ModelResourceFactoryImpl;



/**
 * @author Mickael Istria
 * @contributor Aurelien Pupier
 */
public class BPMNExportTests extends SWTBotGefTestCase {

    final String resFileLocation = System.getProperty("user.home") + File.separator + "TestExportToBPMNDiagram-1.0.bpmn";
    private DiagramEditPart dep;

    @Override
    public void setUp() {
        new File(resFileLocation).delete();
    }

    public void testExportToBPMN() throws Exception {
        SWTBotTestUtil.importProcessWIthPathFromClass(bot, "TestExportToBPMNDiagram_1_0.bos", "Bonita 6.x and 7.x", "TestExportToBPMN", this.getClass(), false);

        final MainProcess mainProcess = (MainProcess)((ProcessDiagramEditor)bot.activeEditor().getReference().getPart(false)).getDiagram().getElement();
        final Diagram diagramFor = ModelHelper.getDiagramFor(mainProcess);

        Display.getDefault().syncExec(new Runnable() {

            public void run() {
                try{
                    dep = OffscreenEditPartFactory.getInstance().createDiagramEditPart(diagramFor, new Shell());
                } catch(final Exception ex){
                    dep = OffscreenEditPartFactory.getInstance().createDiagramEditPart(diagramFor, new Shell());
                }
            }
        });

        final MainProcessEditPart mped = (MainProcessEditPart) dep;
        final IBonitaModelExporter exporter = new BonitaModelExporterImpl(mped) ;
        final File bpmnFileExported = File.createTempFile("withAllExported", ".bpmn");
        final boolean transformed = new BonitaToBPMN().transform(exporter, bpmnFileExported, new NullProgressMonitor());
        assertTrue("Export failed", transformed);

        //
        final BufferedReader reader = new BufferedReader(new FileReader(bpmnFileExported));
        String line = ""; //$NON-NLS-1$
        boolean correctEncoding = false;
        while((line = reader.readLine()) != null) {
            Assert.assertFalse("Serialized as XMI...", line.toLowerCase().contains("xsi:type=\"model:tUserTask\""));
            if(line.contains("encoding=\"UTF-8\"")){
                correctEncoding = true;
            }
        }
        reader.close();

        assertTrue("Bad xml file encding", correctEncoding);

        final ModelResourceFactoryImpl factory = new ModelResourceFactoryImpl();
        final Resource resource = factory.createResource(URI.createFileURI(bpmnFileExported.getAbsolutePath()));
        resource.load(Collections.EMPTY_MAP);
        final TDefinitions bpmnProcess = ((DocumentRoot)resource.getContents().get(0)).getDefinitions();
        TProcess bpmnPool1 = null, bpmnPool2 = null;
        for (final TRootElement el : bpmnProcess.getRootElement()) {
            if (el instanceof TProcess) {
                final TProcess process = (TProcess)el;
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
                final TSequenceFlow sequenceFlow = (TSequenceFlow)el;
                if (sequenceFlow.getConditionExpression() != null) {
                    conditionFalseFound = conditionFalseFound || sequenceFlow.getConditionExpression().getMixed().get(0).getValue().toString().equals("false");
                }
            }
        }
        assertTrue("Could not find the condition on the transition", conditionFalseFound);


        // clean
        new File(resource.getURI().toFileString()).delete();
    }

    private boolean menuBPMN2found = false;

    public void testBPMN2MenuPresent(){
        final SWTBotMenu processMenu = bot.menu("Diagram");
        final SWTBotMenu exportAsMenu = processMenu.menu("Export as").click();
        final MenuItem mi = exportAsMenu.widget;
        Display.getDefault().syncExec(new Runnable() {

            public void run() {
                mi.getMenu().notifyListeners(SWT.Show, new Event()) ;
                final MenuItem[] mis = mi.getMenu().getItems();
                System.out.println("Menu present in "+mi.getText());
                for (final MenuItem mi: mis) {
                    final String menuText = mi.getText();
                    System.out.println(menuText);
                    menuBPMN2found = menuBPMN2found || "BPMN 2.0...".equals(menuText);
                }

            }
        });

        assertTrue("BPMN 2.0 menu is not present", menuBPMN2found);
        /*Can't do it with swtbot so use duirect API, not very nice but should work locally and on the CI*/
        //exportAsMenu.menu("BPMN 2.0...");//check that th emenu exists
    }

    public void testBPMN2MenuPresentAfterOepningAnotherEditor(){
    	final String id = "testBPMN2MenuPresentAfterOpeningAnotherEditor";
        final String className = "MyConnectorImpl"+System.currentTimeMillis();
        final String packageName = "org.bonita.connector.test";
        SWTBotConnectorTestUtil.createConnectorDefAndImpl(bot, id, "1.0.0", className, packageName);

        bot.waitUntil(Conditions.waitForEditor(new BaseMatcher<IEditorReference>() {

            public boolean matches(final Object item) {
                return "org.eclipse.jdt.ui.CompilationUnitEditor".equals(((IEditorReference)item).getId());
            }

            public void describeTo(final Description description) {

            }

        }), 10000);
        final SWTBotEditor activeEditor = bot.activeEditor();
        assertEquals("org.eclipse.jdt.ui.CompilationUnitEditor",activeEditor.getReference().getId());


        final SWTBotMenu processMenu = bot.menu("Diagram");

        final SWTBotMenu exportAsMenu = processMenu.menu("Export as").click();
        final MenuItem mi = exportAsMenu.widget;
        Display.getDefault().syncExec(new Runnable() {

            public void run() {
                mi.getMenu().notifyListeners(SWT.Show, new Event()) ;
                final MenuItem[] mis = mi.getMenu().getItems();

                for (final MenuItem mi: mis) {
                    final String menuText = mi.getText();
                    menuBPMN2found = menuBPMN2found || "BPMN 2.0...".equals(menuText);
                }

            }
        });

        assertTrue("BPMN 2.0 menu is not present", menuBPMN2found);
        /*Can't do it with swtbot so use duirect API, not very nice but should work locally and on the CI*/
        //exportAsMenu.menu("BPMN 2.0...");//check that th emenu exists
    }

}
