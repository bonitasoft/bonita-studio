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
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Collections;
import java.util.Map;

import org.bonitasoft.studio.assertions.StatusAssert;
import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.model.IModelSearch;
import org.bonitasoft.studio.common.model.ModelSearch;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.connectors.repository.ConnectorDefRepositoryStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.exporter.bpmn.transfo.BonitaToBPMNExporter;
import org.bonitasoft.studio.exporter.extension.BonitaModelExporterImpl;
import org.bonitasoft.studio.exporter.extension.IBonitaModelExporter;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.TableExpression;
import org.bonitasoft.studio.model.process.CatchMessageEvent;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.Lane;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.Message;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ThrowMessageEvent;
import org.bonitasoft.studio.model.process.diagram.edit.parts.MainProcessEditPart;
import org.bonitasoft.studio.swtbot.framework.application.BotApplicationWorkbenchWindow;
import org.bonitasoft.studio.swtbot.framework.rule.SWTGefBotRule;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.EList;
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
public class BPMNExportImportMessageDataTest {

    private static MainProcess mainProcessAfterReimport;
    private static ThrowMessageEvent throwMessageEvent;
    private static CatchMessageEvent catchMessageEvent;

    private final SWTGefBot bot = new SWTGefBot();

    private Resource resource;

    @Rule
    public TemporaryFolder tmpFolder = new TemporaryFolder();

    @Rule
    public final SWTGefBotRule rule = new SWTGefBotRule(bot);

    @Test
    public void testDataDefined() {
        final Message message = throwMessageEvent.getEvents().get(0);
        final TableExpression messageContent = message.getMessageContent();
        final EList<Expression> messageDataLine = messageContent
                .getExpressions()
                .get(0)
                .getExpressions();
        assertEquals("messageContentId1", messageDataLine.get(0));
        assertNotNull(messageDataLine.get(1));
    }

    @Test
    public void testDataMappedCorrectlyOnThrow() {
        final Message message = throwMessageEvent.getEvents().get(0);
        final TableExpression messageContent = message.getMessageContent();
        final EList<Expression> messageDataLine = messageContent
                .getExpressions()
                .get(0)
                .getExpressions();
        final Expression messageValue = messageDataLine.get(1);
        assertEquals("data1", messageValue.getName());
        assertEquals(ExpressionConstants.VARIABLE_TYPE, messageValue.getType());
    }

    @Test
    public void testDataMappedCorrectlyOnCatch() {
        final String eventId = catchMessageEvent.getEvent();
        final Message message = throwMessageEvent.getEvents().get(0);
        assertEquals("Event name of catch not well defined", message.getName(), eventId);
    }

    @Before
    public void prepareTest() throws IOException {
        new BotApplicationWorkbenchWindow(bot)
                .importBOSArchive()
                .setArchive(BPMNExportImportMessageDataTest.class.getResource("MessageDataTestValue-1.0.bos"))
                .finish();

        final SWTBotGefEditor editor1 = bot.gefEditor(bot.activeEditor().getTitle());
        final SWTBotGefEditPart step1Part = editor1.getEditPart("Step1").parent();
        final MainProcessEditPart mped = (MainProcessEditPart) step1Part.part().getRoot().getChildren().get(0);
        DiagramRepositoryStore dStore = RepositoryManager.getInstance().getRepositoryStore(DiagramRepositoryStore.class);
        ConnectorDefRepositoryStore connectorDefStore = RepositoryManager.getInstance()
                .getRepositoryStore(ConnectorDefRepositoryStore.class);
        IModelSearch modelSearch = new ModelSearch(() -> dStore.getAllProcesses(), () -> connectorDefStore.getDefinitions());

        final IBonitaModelExporter exporter = new BonitaModelExporterImpl(mped.resolveSemanticElement().eResource(),
                modelSearch);
        final File bpmnFileExported = tmpFolder.newFile("testMessageDataTestValue.bpmn");
        BonitaToBPMNExporter bonitaToBPMNExporter = new BonitaToBPMNExporter();
        bonitaToBPMNExporter.export(exporter, modelSearch, bpmnFileExported);
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

        for (final Element element : ((Lane) ((Pool) mainProcessAfterReimport.getElements().get(0)).getElements().get(0))
                .getElements()) {
            if (element instanceof ThrowMessageEvent) {
                throwMessageEvent = (ThrowMessageEvent) element;
                break;
            }
        }

        for (final Element element : ((Pool) mainProcessAfterReimport.getElements().get(1)).getElements()) {
            if (element instanceof CatchMessageEvent) {
                catchMessageEvent = (CatchMessageEvent) element;
                break;
            }
        }
    }

    @After
    public void clean() {
        resource.unload();
    }

}
