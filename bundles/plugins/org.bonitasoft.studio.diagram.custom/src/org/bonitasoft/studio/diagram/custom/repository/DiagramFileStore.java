/**
 * Copyright (C) 2012-2014 BonitaSoft S.A.
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
package org.bonitasoft.studio.diagram.custom.repository;

import static com.google.common.base.Predicates.instanceOf;
import static com.google.common.collect.Iterables.filter;
import static com.google.common.collect.Iterables.find;
import static com.google.common.collect.Sets.newHashSet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.bonitasoft.engine.session.APISession;
import org.bonitasoft.studio.common.editingdomain.BonitaResourceSetInfoDelegate;
import org.bonitasoft.studio.common.emf.tools.EMFResourceUtil;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.filestore.EMFFileStore;
import org.bonitasoft.studio.common.repository.model.IBuildable;
import org.bonitasoft.studio.common.repository.model.IDeployable;
import org.bonitasoft.studio.common.repository.model.IRenamable;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.diagram.custom.Activator;
import org.bonitasoft.studio.diagram.custom.i18n.Messages;
import org.bonitasoft.studio.migration.model.report.MigrationReportPackage;
import org.bonitasoft.studio.migration.model.report.Report;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.diagram.edit.parts.PoolEditPart;
import org.bonitasoft.studio.model.process.diagram.part.ProcessDiagramEditor;
import org.bonitasoft.studio.model.process.diagram.part.ProcessDiagramEditorUtil;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gmf.runtime.common.ui.services.editor.EditorService;
import org.eclipse.gmf.runtime.diagram.core.listener.DiagramEventBroker;
import org.eclipse.gmf.runtime.diagram.core.listener.NotificationListener;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

import com.google.common.base.Function;
import com.google.common.base.Predicates;

/**
 * @author Romain Bioteau
 */
public class DiagramFileStore extends EMFFileStore implements IDeployable, IRenamable, IBuildable {

    public static final String PROC_EXT = "proc";
    public static final String DEPLOY_DIAGRAM_COMMAND = "org.bonitasoft.studio.engine.deployDiagramCommand";
    public static final String RENAME_DIAGRAM_COMMAND = "org.bonitasoft.studio.application.command.rename";
    public static final String BUILD_DIAGRAM_COMMAND = "org.bonitasoft.studio.engine.command.buildDiagram";
    public static final String VALIDATE_DIAGRAM_COMMAND = "org.bonitasoft.studio.validation.command.headlessDiagramValidation";

    private final NotificationListener poolListener = new PoolNotificationListener();

    private static final Pattern diagramNamePattern = Pattern.compile("(.*)-(.*)");

    public DiagramFileStore(final String fileName, final IRepositoryStore<? extends EMFFileStore> store) {
        super(fileName, store);
    }

    @Override
    public synchronized MainProcess getContent() {
        return (MainProcess) super.getContent();
    }

    @Override
    public String getDisplayName() {
        final String displayName = getResource().getLocation().removeFileExtension().lastSegment();
        final Matcher matcher = diagramNamePattern.matcher(displayName);
        if (matcher.matches()) {
            return String.format("%s (%s)", matcher.group(1), matcher.group(2));
        }
        return displayName;
    }

    @Override
    protected URI getResourceURI() {
        final IPath fullPath = getResource().getFullPath();
        return URI.createPlatformResourceURI(fullPath.toOSString(), true);
    }

    @Override
    public Image getIcon() {
        return Pics.getImage("ProcessDiagramFile.gif", Activator.getDefault());
    }

    @Override
    public Resource getEMFResource() {
        final DiagramEditor editor = getOpenedEditor();
        if (editor != null) {
            final DiagramEditPart diagramEditPart = editor.getDiagramEditPart();
            if (diagramEditPart != null) {
                final EObject resolveSemanticElement = diagramEditPart.resolveSemanticElement();
                if (resolveSemanticElement != null && resolveSemanticElement.eResource() != null) {
                    return resolveSemanticElement.eResource();
                }
            }
        }
        final URI uri = getResourceURI();
        final EditingDomain editingDomain = getParentStore().getEditingDomain(uri);
        final ResourceSet resourceSet = editingDomain.getResourceSet();
        if (getResource().exists()) {
            Resource resource = resourceSet.getResource(uri, false);
            if (resource == null) {
                resource = resourceSet.createResource(uri);
            }
            return resource;
        }
        return super.getEMFResource();
    }

    public void registerListeners(final EObject input, final TransactionalEditingDomain domain) {
        DiagramEventBroker.getInstance(domain).addNotificationListener(input, ProcessPackage.Literals.CONTAINER__ELEMENTS,
                poolListener);
        if (input instanceof MainProcess) {
            for (final EObject element : ((MainProcess) input).getElements()) {
                if (element instanceof Pool) {
                    DiagramEventBroker.getInstance(domain).addNotificationListener(element,
                            ProcessPackage.Literals.ELEMENT__NAME, poolListener);
                    DiagramEventBroker.getInstance(domain).addNotificationListener(element,
                            ProcessPackage.Literals.ABSTRACT_PROCESS__VERSION, poolListener);
                }
            }
        }
    }

    public void unregisterListeners(final EObject input, final TransactionalEditingDomain domain) {
        DiagramEventBroker.getInstance(domain).removeNotificationListener(input, ProcessPackage.Literals.CONTAINER__ELEMENTS,
                poolListener);
    }

    public DiagramEditor getOpenedEditor() {
        return findOpenedEditor()
                .map(DiagramEditor.class::cast)
                .orElse(null);
    }

    @Override
    protected boolean validateEditorInstance(IEditorPart editor) {
        return editor instanceof ProcessDiagramEditor;
    }

    public List<AbstractProcess> getProcesses() {
        MainProcess diagram = null;
        final DiagramEditor editor = getOpenedEditor();
        if (editor != null && editor.getDiagramEditPart() != null) {
            diagram = (MainProcess) editor.getDiagramEditPart().resolveSemanticElement();
        }
        if (diagram == null) {
            diagram = getContent();
        }
        final List<AbstractProcess> allProcesses = ModelHelper.getAllProcesses(diagram);
        final List<AbstractProcess> pools = new ArrayList<>();
        for (final AbstractProcess abstractProcess : allProcesses) {
            if (abstractProcess instanceof Pool) {
                pools.add(abstractProcess);
            }
        }
        return pools;
    }

    @Override
    protected void doSave(final Object content) {
        final Resource resource = getEMFResource();
        final TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(resource);
        try {
            OperationHistoryFactory.getOperationHistory().execute(
                    new SaveDiagramResourceCommand(content, editingDomain, resource),
                    Repository.NULL_PROGRESS_MONITOR,
                    null);
        } catch (final ExecutionException e1) {
            BonitaStudioLog.error(e1);
        }
        if (content instanceof DiagramDocumentEditor) {
            ((DiagramDocumentEditor) content).doSave(Repository.NULL_PROGRESS_MONITOR);
        }

        try {
            resource.save(ProcessDiagramEditorUtil.getSaveOptions());
        } catch (final IOException e) {
            BonitaStudioLog.error(e);
        }
    }

    @Override
    protected IWorkbenchPart doOpen() {
        IEditorPart part = null;
        final Resource emfResource = getEMFResource();
        final MainProcess content = getContent();
        Assert.isLegal(content != null);
        Assert.isLegal(emfResource != null && emfResource.isLoaded());
        final Diagram diagram = ModelHelper.getDiagramFor(content, emfResource);
        part = EditorService.getInstance().openEditor(new URIEditorInput(EcoreUtil.getURI(diagram).trimFragment()));
        if (part instanceof DiagramEditor) {
            final DiagramEditor editor = (DiagramEditor) part;
            final MainProcess mainProcess = (MainProcess) editor.getDiagramEditPart().resolveSemanticElement();
            mainProcess.eAdapters().add(new PoolNotificationListener());
            if (isReadOnly()) {
                setReadOnlyAndOpenWarningDialogAboutReadOnly(editor);
            }
            registerListeners(mainProcess, editor.getEditingDomain());

            setDefaultSelection(editor);

            return editor;
        }
        return part;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.filestore.EMFFileStore#doClose()
     */
    @Override
    protected void doClose() {
        DiagramEditor openedEditor = getOpenedEditor();
        if (openedEditor != null) {
            IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
            activePage.closeEditor(openedEditor, false);
        }
    }

    protected void setDefaultSelection(final DiagramEditor editor) {
        Display.getDefault().syncExec(() -> {
            //default selection
            editor.setFocus();
            final IGraphicalEditPart editPart = editor.getDiagramEditPart()
                    .getChildBySemanticHint(String.valueOf(PoolEditPart.VISUAL_ID));
            if (editPart != null && editPart.getFigure() != null) {
                editor.getDiagramEditPart().getViewer().select(editPart);
                //force viewer to flush selection event
                editor.getDiagramEditPart().getViewer().flush();
            }
        });
    }

    private void setReadOnlyAndOpenWarningDialogAboutReadOnly(final DiagramEditor editor) {
        setReadOnly(true);
        Display.getDefault()
                .asyncExec(() -> MessageDialog.openInformation(Display.getDefault().getActiveShell(),
                        Messages.readOnlyFileTitle,
                        Messages.readOnlyFileWarning));
    }

    @Override
    public void setReadOnly(final boolean readOnly) {
        super.setReadOnly(readOnly);
        final DiagramEditor openedEditor = getOpenedEditor();
        if (openedEditor != null) {
            if (openedEditor.getDiagramEditPart() != null) {
                if (readOnly) {
                    openedEditor.getDiagramEditPart().disableEditMode();
                } else {
                    openedEditor.getDiagramEditPart().enableEditMode();
                }
            }
            if (openedEditor instanceof ProcessDiagramEditor) {
                ((ProcessDiagramEditor) openedEditor).setReadOnly(readOnly);
            }
        }
    }

    @Override
    public IFile getResource() {
        return getParentStore().getResource().getFile(getName());
    }

    @Override
    public Set<IRepositoryFileStore> getRelatedFileStore() {
        final Set<IRepositoryFileStore> result = new HashSet<>();
        final ProcessConfigurationRepositoryStore processConfigurationRepositoryStore = getRepository().getRepositoryStore(
                ProcessConfigurationRepositoryStore.class);

        for (final AbstractProcess process : getProcesses()) {
            final String uuid = toUUID().apply(process);
            result.add(processConfigurationRepositoryStore
                    .getChild(String.format("%s.%s", uuid, ProcessConfigurationRepositoryStore.CONF_EXT), true));
        }
        return newHashSet(filter(result, Predicates.notNull()));
    }

    private Function<AbstractProcess, String> toUUID() {
        return ModelHelper::getEObjectID;
    }

    /**
     * @return the migration report if exists otherwise returns null
     */
    public Report getMigrationReport() {
        final Resource emfResource = getEMFResource();
        if (emfResource != null) {
            return (Report) find(emfResource.getContents(), instanceOf(Report.class), null);
        }
        return null;
    }

    /**
     * Remove the migration report from the proc file.
     *
     * @param save , Whether we save the resoruce after deletion or not
     * @throws IOException
     */
    public void clearMigrationReport(final boolean save) throws IOException {
        final EObject toRemove = null;
        final Resource emfResource = getEMFResource();
        final Report report = getMigrationReport();
        if (report != null) {
            final TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(toRemove);
            if (domain != null) {
                domain.getCommandStack().execute(new RecordingCommand(domain) {

                    @Override
                    protected void doExecute() {
                        emfResource.getContents().remove(report);
                    }
                });
                if (save) {
                    emfResource.save(Collections.emptyMap());
                }
            }
        }
    }

    public boolean hasMigrationReport() {
        final EMFResourceUtil emfResourceUtil = new EMFResourceUtil(getResource().getLocation().toFile());
        final Map<String, String[]> featureValueFromEObjectType = emfResourceUtil.getFeatureValueFromEObjectType(
                "report:Report",
                MigrationReportPackage.Literals.REPORT__NAME);
        if (featureValueFromEObjectType == null || featureValueFromEObjectType.values() == null
                || featureValueFromEObjectType.values().isEmpty()) {
            return false;
        }
        final Iterator<String[]> iterator = featureValueFromEObjectType.values().iterator();
        final String[] values = iterator.next();
        return values != null && values[0] != null;
    }

    public boolean isOpened() {
        return getOpenedEditor() != null;
    }

    public void stopResourceListening() {
        final TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(getEMFResource());
        final BonitaResourceSetInfoDelegate resourceSetInfoDelegate = BonitaResourceSetInfoDelegate.adapt(editingDomain);
        resourceSetInfoDelegate.stopResourceListening();
    }

    @Override
    public void deploy() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("diagram", getName());
        executeCommand(DEPLOY_DIAGRAM_COMMAND, parameters);
    }

    @Override
    public void rename(String newName) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("diagram", getName());
        executeCommand(RENAME_DIAGRAM_COMMAND, parameters);
    }

    @Override
    public Optional<String> retrieveNewName() {
        return Optional.of(""); // Specific behavior for diagrams, all is done in the rename method
    }

    @Override
    public void build(IPath buildPath, IProgressMonitor monitor) throws CoreException {
        IPath processFolderPath = buildPath.append("process");
        IFolder processFolder = getRepository().getProject()
                .getFolder(processFolderPath.makeRelativeTo(getRepository().getProject().getFullPath()));
        if (!processFolder.exists()) {
            processFolder.create(true, true, new NullProgressMonitor());
        }

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("fileName", getName());
        parameters.put("destinationPath", processFolder.getLocation().toOSString());
        monitor.subTask(String.format(Messages.buildingDiagram, getDisplayName()));
        IStatus buildStatus = (IStatus) executeCommand(BUILD_DIAGRAM_COMMAND, parameters);
        if (Objects.equals(buildStatus.getSeverity(), ValidationStatus.ERROR)) {
            throw new CoreException(parseStatus(buildStatus));
        }
    }

    // We only want to log error status
    private IStatus parseStatus(IStatus status) {
        if (status instanceof MultiStatus) {
            MultiStatus multiStatus = ((MultiStatus) status);
            IStatus[] errorChildren = Arrays.asList(multiStatus.getChildren()).stream()
                    .filter(aStatus -> Objects.equals(aStatus.getSeverity(), ValidationStatus.ERROR))
                    .collect(Collectors.toList()).toArray(new IStatus[0]);
            status = new MultiStatus(multiStatus.getPlugin(), multiStatus.getCode(), errorChildren,
                    multiStatus.getMessage(), multiStatus.getException());
        }
        return status;
    }

}
