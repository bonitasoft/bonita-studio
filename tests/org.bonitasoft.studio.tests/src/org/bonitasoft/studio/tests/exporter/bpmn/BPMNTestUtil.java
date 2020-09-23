package org.bonitasoft.studio.tests.exporter.bpmn;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.Map;

import org.bonitasoft.studio.assertions.StatusAssert;
import org.bonitasoft.studio.common.editingdomain.CustomDiagramEditingDomainFactory;
import org.bonitasoft.studio.common.model.IModelSearch;
import org.bonitasoft.studio.common.model.ModelSearch;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.connectors.repository.ConnectorDefRepositoryStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.exporter.bpmn.transfo.BonitaToBPMNExporter;
import org.bonitasoft.studio.exporter.extension.BonitaModelExporterImpl;
import org.bonitasoft.studio.exporter.extension.IBonitaModelExporter;
import org.bonitasoft.studio.importer.bpmn.BPMNToProc;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.diagram.edit.parts.MainProcessEditPart;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gmf.runtime.common.ui.services.editor.EditorService;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.omg.spec.bpmn.di.util.DiResourceFactoryImpl;
import org.omg.spec.bpmn.model.DocumentRoot;

/**
 * @author Aurelien Pupier
 */
public class BPMNTestUtil {

    public static File importFileWithName(final Class<?> clazz, final String bpmnFileName) throws IOException {
        return importBPMNFile(FileLocator.toFileURL(clazz.getResource(bpmnFileName)));
    }

    protected static File importBPMNFile(final URL bpmnResource) {
        final BPMNToProc bpmnToProc = new BPMNToProc(new File(bpmnResource.getFile()).getAbsolutePath());
        final File destFile = bpmnToProc.createDiagram(bpmnResource, new NullProgressMonitor());
        destFile.deleteOnExit();
        return destFile;
    }

    public static MainProcessEditPart getMainProcessEditPart(final Diagram diag) {

        /*
         * need to get the URI after save because the name can change as it is
         * synchronized with the MainProcess name
         */
        final URI uri = EcoreUtil.getURI(diag);

        /* open the process editor */
        final DiagramEditor processEditor = (DiagramEditor) EditorService.getInstance()
                .openEditor(new URIEditorInput(uri, diag.getName()));
        return (MainProcessEditPart) processEditor.getDiagramEditPart();
    }

    public static org.eclipse.emf.common.util.URI toEMFURI(final File file) {
        final org.eclipse.emf.common.util.URI res = URI.createURI(file.toURI().toString());
        return res;
    }

    public static MainProcess importBPMNFile(final DocumentRoot model2) throws MalformedURLException {
        final File reImportedFile = BPMNTestUtil
                .importBPMNFile(new File(model2.eResource().getURI().toFileString()).toURI().toURL());
        reImportedFile.deleteOnExit();
        final ResourceSet resourceSet = new ResourceSetImpl();
        CustomDiagramEditingDomainFactory.getInstance().createEditingDomain(resourceSet);
        final Resource resource = resourceSet.getResource(BPMNTestUtil.toEMFURI(reImportedFile), true);
        new File(resource.getURI().toFileString()).deleteOnExit();
        final MainProcess mainProcess = (MainProcess) resource.getContents().get(0);
        return mainProcess;
    }

    public static DocumentRoot exportToBpmn(final Resource resource) throws IOException {
        DiagramRepositoryStore dStore = RepositoryManager.getInstance().getRepositoryStore(DiagramRepositoryStore.class);
        ConnectorDefRepositoryStore connectorDefStore = RepositoryManager.getInstance()
                .getRepositoryStore(ConnectorDefRepositoryStore.class);
        IModelSearch modelSearch = new ModelSearch(() -> dStore.getAllProcesses(), () -> connectorDefStore.getDefinitions());
        final IBonitaModelExporter exporter = new BonitaModelExporterImpl(resource, modelSearch);
        final File bpmnFileExported = File.createTempFile("testBpmnExport", ".bpmn");
        bpmnFileExported.deleteOnExit();
        BonitaToBPMNExporter bonitaToBPMNExporter = new BonitaToBPMNExporter();
        bonitaToBPMNExporter.export(exporter, modelSearch, bpmnFileExported);
        StatusAssert.assertThat(bonitaToBPMNExporter.getStatus()).hasSeverity(IStatus.INFO);

        final ResourceSet resourceSet1 = new ResourceSetImpl();
        final Map<String, Object> extensionToFactoryMap = resourceSet1.getResourceFactoryRegistry()
                .getExtensionToFactoryMap();
        final DiResourceFactoryImpl diResourceFactoryImpl = new DiResourceFactoryImpl();
        extensionToFactoryMap.put("bpmn", diResourceFactoryImpl);
        final Resource resource2 = resourceSet1.createResource(URI.createFileURI(bpmnFileExported.getAbsolutePath()));
        resource2.load(Collections.emptyMap());
        new File(resource2.getURI().toString()).deleteOnExit();

        final DocumentRoot model2 = (DocumentRoot) resource2.getContents().get(0);
        return model2;
    }

}
