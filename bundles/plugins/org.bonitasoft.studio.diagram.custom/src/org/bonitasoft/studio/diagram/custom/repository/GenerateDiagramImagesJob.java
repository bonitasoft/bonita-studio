/*******************************************************************************
 * Copyright (C) 2019 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.diagram.custom.repository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.diagram.edit.parts.PoolEditPart;
import org.bonitasoft.studio.model.process.diagram.part.ProcessDiagramEditorPlugin;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.gmf.runtime.diagram.ui.OffscreenEditPartFactory;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.image.ImageFileFormat;
import org.eclipse.gmf.runtime.diagram.ui.render.util.CopyToImageUtil;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.progress.UIJob;

public class GenerateDiagramImagesJob extends UIJob {

    private CopyToImageUtil copyToImageUtil = new CopyToImageUtil();
    private IFolder targetFolder;
    private MainProcess diagram;

    public GenerateDiagramImagesJob(Display jobDisplay, MainProcess diagram, IFolder targetFolder) {
        super(jobDisplay, String.format("Generate %s (%s) diagram images...", diagram.getName(), diagram.getVersion()));
        this.diagram = diagram;
        this.targetFolder = targetFolder;
    }

    @Override
    public IStatus runInUIThread(IProgressMonitor monitor) {
        IFolder diagramsFolder = targetFolder.getFolder("diagrams");
        try {
            diagramsFolder.refreshLocal(IResource.DEPTH_INFINITE, monitor);
        } catch (CoreException e) {
           return e.getStatus();
        }
        IFolder processesFolder = targetFolder.getFolder("processes");
        try {
            processesFolder.refreshLocal(IResource.DEPTH_INFINITE, monitor);
        } catch (CoreException e) {
           return e.getStatus();
        }
        Resource emfResource = diagram.eResource();
        String fileName = URI.decode(emfResource.getURI().lastSegment());
        emfResource.getContents().stream()
                .filter(Diagram.class::isInstance)
                .map(Diagram.class::cast)
                .findFirst()
                .ifPresent(d -> exportDiagramImage(d,
                        diagramsFolder.getLocation().append(fileName.replaceAll(".proc", ".png")),
                        ImageFileFormat.PNG, monitor));
        emfResource.getContents().stream()
                .filter(Diagram.class::isInstance)
                .map(Diagram.class::cast)
                .findFirst()
                .ifPresent(d -> exportPoolImage(d, processesFolder.getLocation(), monitor));
        return Status.OK_STATUS;
    }

    private void exportPoolImage(Diagram diagram, IPath outputPath, IProgressMonitor monitor) {
        Shell shell = new Shell();
        try {
            DiagramEditPart diagramEditPart = new OffscreenEditPartFactory().createDiagramEditPart(diagram, shell,
                    ProcessDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
            for (PoolEditPart poolEditPart : poolEditParts(diagramEditPart)) {
                Pool process = (Pool) poolEditPart.resolveSemanticElement();
                copyToImageUtil.copyToImage(diagramEditPart, Collections.singletonList(poolEditPart),
                        outputPath.append(String.format("%s-%s.png",
                                process.getName(), process.getVersion())),
                        ImageFileFormat.PNG,
                        Repository.NULL_PROGRESS_MONITOR);
            }
        } catch (CoreException e) {
            BonitaStudioLog.error(e);
        } finally {
            shell.dispose();
        }
    }

    private List<PoolEditPart> poolEditParts(DiagramEditPart diagramEditPart) {
        return (List<PoolEditPart>) diagramEditPart.getPrimaryEditParts().stream()
                .filter(PoolEditPart.class::isInstance)
                .map(PoolEditPart.class::cast)
                .collect(Collectors.toList());
    }

    private void exportDiagramImage(Diagram diagram, IPath outputPath, ImageFileFormat imageFormat,
            IProgressMonitor monitor) {
        try {
            copyToImageUtil.copyToImage(
                    diagram, outputPath, imageFormat, Repository.NULL_PROGRESS_MONITOR,
                    ProcessDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
        } catch (CoreException e) {
            BonitaStudioLog.error(e);
        }
    }

}
