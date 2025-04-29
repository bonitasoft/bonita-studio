/**
 * Copyright (C) 2019 BonitaSoft S.A.
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
package org.bonitasoft.studio.maven;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;

import org.apache.maven.model.Dependency;
import org.apache.maven.project.MavenProject;
import org.bonitasoft.engine.session.APISession;
import org.bonitasoft.studio.application.views.BonitaProjectExplorer;
import org.bonitasoft.studio.common.extension.properties.ExtensionPagePropertiesReader;
import org.bonitasoft.studio.common.extension.properties.PagePropertyConstants;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.BuildScheduler;
import org.bonitasoft.studio.common.repository.core.BonitaProject;
import org.bonitasoft.studio.common.repository.core.maven.MavenModelOperation;
import org.bonitasoft.studio.common.repository.core.maven.MavenProjectHelper;
import org.bonitasoft.studio.common.repository.core.maven.RemoveDependencyOperation;
import org.bonitasoft.studio.common.repository.core.maven.model.GAV;
import org.bonitasoft.studio.common.repository.filestore.AbstractFileStore;
import org.bonitasoft.studio.common.repository.model.IBuildable;
import org.bonitasoft.studio.common.repository.model.IDeployable;
import org.bonitasoft.studio.common.repository.model.IRenamable;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.common.repository.ui.validator.MavenIdValidator;
import org.bonitasoft.studio.common.ui.PlatformUtil;
import org.bonitasoft.studio.common.ui.jface.FileActionDialog;
import org.bonitasoft.studio.common.ui.perspectives.BonitaPerspectivesUtils;
import org.bonitasoft.studio.maven.operation.BuildCustomPageOperation;
import org.bonitasoft.studio.maven.operation.ImportCustomPageProjectOperation;
import org.bonitasoft.studio.rest.api.extension.RestAPIExtensionActivator;
import org.bonitasoft.studio.rest.api.extension.ui.perspective.RestAPIExtensionPerspectiveFactory;
import org.bonitasoft.studio.ui.i18n.Messages;
import org.bonitasoft.studio.ui.validator.InputValidatorWrapper;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.m2e.core.MavenPlugin;
import org.eclipse.m2e.core.project.MavenProjectInfo;
import org.eclipse.m2e.core.project.ProjectImportConfiguration;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.internal.wizards.datatransfer.ArchiveFileExportOperation;

public class ExtensionProjectFileStore<T extends ExtensionProjectDescriptor> extends AbstractFileStore<T>
        implements IDeployable, IBuildable, IRenamable {

    public static final String QUICK_DEPLOY_COMMAND = "org.bonitasoft.studio.maven.extension.quickDeployCommand";

    public ExtensionProjectFileStore(String fileName, ExtensionRepositoryStore parentStore) {
        super(fileName, (IRepositoryStore<? extends IRepositoryFileStore<T>>) parentStore);
    }

    @Override
    public T getContent() throws ReadFileStoreException {
        return doGetContent();
    }

    public IProject getProject() {
        return getParentStore().getResource().getWorkspace().getRoot().getProject(getName());
    }

    public String getPageDisplayName() {
        try {
            final ExtensionProjectDescriptor content = getContent();
            return content.getDisplayName();
        } catch (final ReadFileStoreException e) {
            return "";
        }
    }

    public String getDescription() {
        try {
            final ExtensionProjectDescriptor content = getContent();
            return content.getDescription();
        } catch (final ReadFileStoreException e) {
            return "";
        }
    }

    public String getContentType() {
        var descriptor = getResource().getFile("src/main/resources/page.properties");
        if (!descriptor.exists()) {
            descriptor = getResource().getFile("page.properties");
        }
        if (descriptor.exists()) {
            try (var is = descriptor.getContents()) {
                var properties = new Properties();
                properties.load(is);
                return ExtensionPagePropertiesReader.getProperty(properties, PagePropertyConstants.CONTENT_TYPE)
                        .orElse(null);
            } catch (IOException | CoreException e1) {
                return null;
            }
        }
        return null;
    }

    public boolean canBeImported() {
        return getResource().getFile("pom.xml").exists();
    }

    @Override
    protected void doSave(final Object content) {

    }

    @Override
    public IFolder getResource() {
        return getParentStore().getResource().getFolder(getName());
    }

    @Override
    protected IWorkbenchPart doOpen() {
        final IWorkbenchPage page = getActivePage();
        try {
            final ExtensionProjectDescriptor raed = getContent();
            AbstractFileStore.refreshExplorerView();
            return openEditors(page, raed);
        } catch (final PartInitException | ReadFileStoreException e) {
            BonitaStudioLog.error(String.format("Failed to open editors of project %s", getName()), e,
                    RestAPIExtensionActivator.PLUGIN_ID);
        }
        return null;
    }

    protected IWorkbenchPart openEditors(final IWorkbenchPage page, final ExtensionProjectDescriptor descriptor)
            throws PartInitException {
        BonitaProjectExplorer explorerView = (BonitaProjectExplorer) getActivePage().findView(BonitaProjectExplorer.ID);
        if (explorerView != null) {
            explorerView.getCommonViewer().expandToLevel(getResource(), 1);
        }
        boolean editorOpened = false;
        for (final IFile file : descriptor.getFilesToOpen()) {
            if (file.exists()) {
                IDE.openEditor(page, file);
            }
            editorOpened = true;
        }
        if (descriptor.getPropertyFile().exists()) {
            return IDE.openEditor(page, descriptor.getPropertyFile());
        }
        if (!editorOpened) {
            BonitaPerspectivesUtils
                    .switchToPerspective(RestAPIExtensionPerspectiveFactory.REST_API_EXTENSION_PERSPECTIVE_ID);
            PlatformUtil.closeIntro();
        }
        return null;
    }

    protected IWorkbenchPage getActivePage() {
        return PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
    }

    @Override
    protected void doClose() {

    }

    @Override
    public IStatus export(final String targetFolderAbsoluteFilePath) throws IOException {
        checkWritePermission(new File(targetFolderAbsoluteFilePath));
        IResource file = getResource();
        if (file != null) {
            File to = new File(targetFolderAbsoluteFilePath);
            if (!to.exists()) {
                to.mkdirs();
            }
            File target = new File(to, file.getName() + ".zip");
            if (target.exists()) {
                if (FileActionDialog.overwriteQuestion(file.getName())) {
                    PlatformUtil.delete(target, AbstractRepository.NULL_PROGRESS_MONITOR);
                } else {
                    return ValidationStatus.cancel("");
                }
            }
            try {
                final ExtensionProjectDescriptor raed = getContent();
                final List<IResource> resourcesToInclude = findResourcesToExport(raed);
                final ArchiveFileExportOperation op = new ArchiveFileExportOperation(raed.getProject(),
                        resourcesToInclude, target.getAbsolutePath());
                op.run(AbstractRepository.NULL_PROGRESS_MONITOR);
                return ValidationStatus.ok();
            } catch (InvocationTargetException | InterruptedException | ReadFileStoreException e) {
                BonitaStudioLog.error("Cannot export REST API Extension project", e);
            }
        }
        return ValidationStatus.error(String
                .format(org.bonitasoft.studio.common.repository.Messages.failedToRetrieveResourceToExport, getName()));
    }

    protected List<IResource> findResourcesToExport(final ExtensionProjectDescriptor raed) {
        final List<IResource> resourcesToInclude = new ArrayList<>();
        try {
            final IResource[] members = raed.getProject().members();
            for (final IResource member : members) {
                final IPath projectRelativePath = member.getProjectRelativePath();
                final String firstSegment = projectRelativePath.segments()[0];
                if (!"target".equals(firstSegment) && !"bin".equals(firstSegment) && !"node".equals(firstSegment)
                        && !"node_modules".equals(firstSegment)) {
                    resourcesToInclude.add(member);
                }
            }
        } catch (final CoreException e) {
            BonitaStudioLog.error("Error while calculating resource to export for " + raed.getName(), e);
        }
        return resourcesToInclude;
    }

    @Override
    protected void doDelete() {
        try {
            IProject project = getContent().getProject();
            // Close opened resources from this project
            IWorkbenchWindow activeWorkbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
            if (activeWorkbenchWindow != null) {
                IWorkbenchPage activePage = activeWorkbenchWindow.getActivePage();
                for (IEditorReference ref : activePage.getEditorReferences()) {
                    IResource resource = ref.getEditorInput().getAdapter(IResource.class);
                    if (resource != null && Objects.equals(resource.getProject(), project)) {
                        activePage.closeEditor(ref.getEditor(false), false);
                    }
                }

                IProgressMonitor monitor = new NullProgressMonitor();
                getRepositoryAccessor().getCurrentProject().ifPresent(p -> {
                    try {
                        var gav = getGAV();
                        var dependency = toDependency(gav);
                        new RemoveDependencyOperation(dependency).run(monitor);
                        p.removeModule(p.getExtensionsParentProject(), project.getName(), monitor);
                    } catch (CoreException e) {
                        BonitaStudioLog.error(e);
                    }

                });

                project.close(monitor);
                project.delete(true, true, monitor);

            }
        } catch (CoreException | ReadFileStoreException e) {
            super.doDelete();
        }
    }

    private Dependency toDependency(GAV gav) {
        var dependency = new Dependency();
        dependency.setGroupId(gav.getGroupId());
        dependency.setArtifactId(gav.getArtifactId());
        dependency.setVersion(gav.getVersion());
        dependency.setClassifier(gav.getClassifier());
        dependency.setType(gav.getType());
        return dependency;
    }

    public BuildCustomPageOperation newBuildOperation() throws ReadFileStoreException {
        return new BuildCustomPageOperation(getContent());
    }

    public String getPageId() {
        try {
            return getContent().getCustomPageName();
        } catch (final ReadFileStoreException e) {
            BonitaStudioLog.error(e);
            return null;
        }
    }

    public File getArchiveFile() throws IOException {
        MavenProject mavenProject;
        try {
            mavenProject = getContent().getMavenProject().orElseThrow(
                    () -> new ReadFileStoreException(String.format("Maven project not found for %s", getName())));
        } catch (ReadFileStoreException e) {
            throw new IOException("Failed to retrieve maven project", e);
        }
        final File archive = new File(mavenProject.getBasedir(), "target" + File.separatorChar
                + mavenProject.getArtifactId() + "-" + mavenProject.getVersion() + ".zip");
        if (!archive.exists()) {
            throw new FileNotFoundException(archive.getAbsolutePath());
        }
        return archive;
    }

    public MavenProjectInfo getMavenProjectInfo() {
        return new MavenProjectInfo(null, getResource().getFile("pom.xml").getLocation().toFile(), null, null);
    }

    public void importProject() throws ImportProjectException {
        final IProject project = getProject();
        if (project.exists()) {
            throw new ImportProjectException(String.format("Project %s already exists", project.getName()));
        }
        final ProjectImportConfiguration projectImportConfiguration = new ProjectImportConfiguration();
        projectImportConfiguration.setProjectNameTemplate("[artifactId]");
        final ImportCustomPageProjectOperation importRestAPIExtensionProjectOperation = new ImportCustomPageProjectOperation(
                this, MavenPlugin.getProjectConfigurationManager(), projectImportConfiguration);
        var job = new WorkspaceJob(String.format("Import %s project", project.getName())) {

            @Override
            public IStatus runInWorkspace(IProgressMonitor monitor) throws CoreException {
                importRestAPIExtensionProjectOperation.run(monitor);
                return importRestAPIExtensionProjectOperation.getStatus();
            }
        };
        // schedule the 2 jobs immediately with the same rule to ensure sequencing
        job.setPriority(Job.INTERACTIVE);
        BuildScheduler.scheduleJobWithBuildRule(job);
        BuildScheduler.scheduleJobWithBuildRule(
                new WorkspaceJob(String.format("Add %s module to extensions", project.getName())) {

                    @Override
                    public IStatus runInWorkspace(IProgressMonitor monitor) throws CoreException {
                        var bonitaProject = getRepositoryAccessor().getCurrentProject().orElseThrow();
                        var extensionsParentProject = bonitaProject.getExtensionsParentProject();
                        bonitaProject.addModule(extensionsParentProject, ExtensionProjectFileStore.this.getName(),
                                new NullProgressMonitor());
                        return Status.OK_STATUS;
                    }
                });
    }

    public void removeProject() {
        try {
            final IProject project = getProject();
            if (project.exists()) {
                // Only delete project, not content
                project.delete(false, true, AbstractRepository.NULL_PROGRESS_MONITOR);
            }
        } catch (final CoreException e) {
            BonitaStudioLog.error(e);
        }
    }

    @Override
    public void deployInUI() {
        Map<String, Object> options = new HashMap<>();
        options.put("disablePopup", Boolean.FALSE.toString());
        options.put("projectPath", getResource().getLocation().toFile().toPath().toString());
        executeCommand(QUICK_DEPLOY_COMMAND, options);
    }

    @Override
    public IStatus deploy(APISession session, Map<String, Object> options, IProgressMonitor monitor) {
        options.put("disablePopup", Boolean.TRUE.toString());
        options.put("projectPath", getResource().getLocation().toFile().toPath().toString());
        Object result = executeCommand(QUICK_DEPLOY_COMMAND, options);
        return result instanceof IStatus ? (IStatus) result : ValidationStatus.ok();
    }

    /**
     * Always return false. A Rest API is composed of many files, all of them could
     * possibly be opened and dirty, we do not want to check all of them.
     */
    @Override
    public boolean isDirty() {
        return false;
    }

    @Override
    public IStatus build(IPath buildPath, IProgressMonitor monitor) {
        try {
            IPath restApiFolderPath = buildPath.append(getBuildFolder());
            IFolder restApiFolder = getRepository().getProject()
                    .getFolder(restApiFolderPath.makeRelativeTo(getRepository().getProject().getLocation()));
            if (!restApiFolder.exists()) {
                restApiFolder.create(true, true, new NullProgressMonitor());
            }
            BuildCustomPageOperation buildOperation = newBuildOperation();
            buildOperation.run(monitor);

            if (!Objects.equals(buildOperation.getStatus().getSeverity(), IStatus.ERROR)) {
                String archiveName = buildOperation.getArchiveName();
                IFile file = restApiFolder.getFile(archiveName);
                file.create(buildOperation.getArchiveContent(), true, new NullProgressMonitor());
            } else {
                return buildOperation.getStatus();
            }
        } catch (ReadFileStoreException | FileNotFoundException e) {
            return ValidationStatus
                    .error(String.format("An error occured while building REST API extension %s", getName()), e);
        } catch (CoreException e) {
            return e.getStatus();
        }
        return Status.OK_STATUS;
    }

    protected String getBuildFolder() {
        return getParentStore().getName();
    }

    @Override
    protected T doGetContent() throws ReadFileStoreException {
        final IProject project = getProject();
        if (!project.exists()) {
            throw new ReadFileStoreException(String.format("Project with name %s does not exist", project.getName()));
        }
        return (T) new ExtensionProjectDescriptor(project);
    }

    public GAV getGAV() {
        try {
            ExtensionProjectDescriptor descriptor = getContent();
            return new GAV(descriptor.getGroupId(), descriptor.getArtifactId(), descriptor.getVersion(),
                    descriptor.getClassifier(), "zip", null);
        } catch (ReadFileStoreException e) {
            BonitaStudioLog.error(e);
            return null;
        }
    }

    @Override
    public void rename(String newName) {
        IProject project = getProject();
        var projectLocation = project.getLocation();
        var pomFile = project.getFile("pom.xml");
        if (pomFile.exists()) {
            try (var is = pomFile.getContents()) {
                var model = MavenPlugin.getMaven().readModel(is);
                var oldArtifactId = model.getArtifactId();
                model.setArtifactId(newName);
                MavenProjectHelper.saveModel(pomFile.getLocation().toFile().toPath(), model);

                var bonitaProject = BonitaProject.create(getRepository().getProjectId());
                var appProject = bonitaProject.getAppProject();
                var appModel = MavenProjectHelper.getMavenModel(appProject);
                appModel.getDependencies().stream()
                        .filter(dep -> Objects.equals("${project.groupId}", dep.getGroupId()))
                        .filter(dep -> Objects.equals("${project.version}", dep.getVersion()))
                        .filter(dep -> Objects.equals(oldArtifactId, dep.getArtifactId()))
                        .findFirst()
                        .ifPresent(dependencyToUpdate -> {
                            try {
                                new MavenModelOperation() {

                                    @Override
                                    public void run(IProgressMonitor monitor) throws CoreException {
                                        dependencyToUpdate.setArtifactId(newName);
                                        modelUpdated = true;
                                        saveModel(appProject, appModel, monitor);
                                    }
                                }.run(new NullProgressMonitor());
                            } catch (CoreException e) {
                                BonitaStudioLog.error(e);
                            }
                        });

            } catch (IOException | CoreException e) {
                BonitaStudioLog.error(e);
            }
        }
        try {
            File projectFolder = projectLocation.toFile();
            projectFolder.renameTo(projectFolder.getParentFile().toPath().resolve(newName).toFile());
            project.delete(false, false, new NullProgressMonitor());
            var bonitaProject = getRepositoryAccessor().getCurrentProject().orElseThrow();
            bonitaProject.removeModule(bonitaProject.getExtensionsParentProject(), project.getName(),
                    new NullProgressMonitor());
            setName(newName);
            importProject();
        } catch (CoreException | ImportProjectException e) {
            BonitaStudioLog.error(e);
        }
    }

    @Override
    public Optional<String> retrieveNewName() {
        var validator = new MavenIdValidator("Artifact ID", true);
        var bonitaProject = getRepositoryAccessor().getCurrentProject().orElseThrow();
        bonitaProject.getRelatedProjects().stream().map(IProject::getName).forEach(validator::addReservedId);
        InputDialog dialog = new InputDialog(Display.getDefault().getActiveShell(), Messages.rename, "Artifact ID",
                getName(), new InputValidatorWrapper(validator));
        if (dialog.open() == Window.OK) {
            return Optional.of(dialog.getValue());
        }
        return Optional.empty();
    }

    @Override
    public boolean isInClasspath() {
        var dependency = toDependency(getGAV());
        dependency.setGroupId("${project.groupId}");
        dependency.setVersion("${project.version}");
        var bonitaProject = BonitaProject.create(getRepository().getProjectId());
        try {
            return new MavenProjectHelper()
                    .findDependency(MavenProjectHelper.getMavenModel(bonitaProject.getAppProject()), dependency)
                    .isPresent();
        } catch (CoreException e) {
            BonitaStudioLog.error(e);
            return false;
        }
    }

    /**
     * Fix the store's name and location to align with the artifact id
     * 
     * @param actualProject the project holding the content, which name's reflects the artifact id
     * @param monitor progress monitor
     * @throws CoreException Eclipse workspace exception
     */
    public void fixStoreName(IProject actualProject, IProgressMonitor monitor) throws CoreException {
        String correctName = actualProject.getName();
        if (!correctName.equals(getName())) {
            // we need to relocate the project to a location matching the artifact id
            var description = actualProject.getDescription();
            var targetUri = description.getLocationURI().resolve(correctName);
            description.setLocationURI(targetUri);
            actualProject.move(description, IResource.FORCE | IResource.SHALLOW, monitor);
            postMoveRename(correctName);
        }
    }

}
