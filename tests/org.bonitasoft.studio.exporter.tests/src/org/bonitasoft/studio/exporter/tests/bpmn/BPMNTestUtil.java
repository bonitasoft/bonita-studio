package org.bonitasoft.studio.exporter.tests.bpmn;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.Map;

import junit.framework.Assert;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.exporter.bpmn.transfo.BonitaToBPMN;
import org.bonitasoft.studio.exporter.extension.BonitaModelExporterImpl;
import org.bonitasoft.studio.exporter.extension.IBonitaModelExporter;
import org.bonitasoft.studio.importer.bpmn.BPMNToProc;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.diagram.edit.parts.MainProcessEditPart;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gmf.runtime.common.ui.services.editor.EditorService;
import org.eclipse.gmf.runtime.diagram.ui.OffscreenEditPartFactory;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.emf.core.GMFEditingDomainFactory;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.omg.spec.bpmn.di.util.DiResourceFactoryImpl;
import org.omg.spec.bpmn.model.DocumentRoot;

/**
 * @author Aurelien Pupier
 *
 */
public class BPMNTestUtil {

    public static File importFileWithName(Class<?> clazz, final String bpmnFileName) throws IOException {
        URL bpmnResource = FileLocator.toFileURL(clazz.getResource(bpmnFileName));
        return importBPMNFile(bpmnResource);
    }

    protected static File importBPMNFile(URL bpmnResource) {
        BPMNToProc bpmnToProc = new BPMNToProc(new File(bpmnResource.getFile()).getAbsolutePath());
        File destFile = bpmnToProc.createDiagram(bpmnResource, new NullProgressMonitor());
        return destFile;
    }

    public static MainProcessEditPart getMainProcessEditPart(Diagram diag) {

        /*
         * need to get the URI after save because the name can change as it is
         * synchronized with the MainProcess name
         */
        URI uri = EcoreUtil.getURI(diag);

        /* open the process editor */
        DiagramEditor processEditor = (DiagramEditor) EditorService.getInstance().openEditor(new URIEditorInput(uri, diag.getName()));
        return (MainProcessEditPart)processEditor.getDiagramEditPart();
    }

    public static org.eclipse.emf.common.util.URI toEMFURI(File file) throws MalformedURLException {
        org.eclipse.emf.common.util.URI res = URI.createURI(file.toURI().toString());
        return res;
    }

    public static  MainProcess importBPMNFile(DocumentRoot model2)
            throws MalformedURLException {
        File reImportedFile = BPMNTestUtil.importBPMNFile(new File(model2.eResource().getURI().toFileString()).toURI().toURL());
        ResourceSet resourceSet = new ResourceSetImpl();
        GMFEditingDomainFactory.getInstance().createEditingDomain(resourceSet);
        Resource resource = resourceSet.getResource(BPMNTestUtil.toEMFURI(reImportedFile), true);
        MainProcess mainProcess = (MainProcess)resource.getContents().get(0);
        return mainProcess;
    }

    public static DocumentRoot exportToBpmn(final DiagramFileStore newDiagramFileStore) throws IOException {
        final Diagram diagramFor = ModelHelper.getDiagramFor(newDiagramFileStore.getContent(),null);
        ResourceSet rSet = diagramFor.eResource().getResourceSet() ;
        GMFEditingDomainFactory.getInstance().createEditingDomain(rSet) ;
        DiagramEditPart dep;
        try{
            dep = OffscreenEditPartFactory.getInstance().createDiagramEditPart(diagramFor,  newDiagramFileStore.getOpenedEditor().getSite().getShell());
        } catch(Exception ex){
            dep = OffscreenEditPartFactory.getInstance().createDiagramEditPart(diagramFor,  newDiagramFileStore.getOpenedEditor().getSite().getShell());
        }
        MainProcessEditPart mped = (MainProcessEditPart) dep;
        IBonitaModelExporter exporter = new BonitaModelExporterImpl(mped);
        File bpmnFileExported = File.createTempFile("testBpmnExport", ".bpmn");
        final boolean transformed = new BonitaToBPMN().transform(exporter, bpmnFileExported, new NullProgressMonitor());
        Assert.assertTrue("Error during export", transformed);

        ResourceSet resourceSet1 = new ResourceSetImpl();
        final Map<String, Object> extensionToFactoryMap = resourceSet1.getResourceFactoryRegistry().getExtensionToFactoryMap();
        final DiResourceFactoryImpl diResourceFactoryImpl = new DiResourceFactoryImpl();
        extensionToFactoryMap.put("bpmn", diResourceFactoryImpl);
        Resource resource2 = resourceSet1.createResource(URI.createFileURI(bpmnFileExported.getAbsolutePath()));
        resource2.load(Collections.emptyMap());

        DocumentRoot model2 = (DocumentRoot) resource2.getContents().get(0);
        return model2;
    }

}
