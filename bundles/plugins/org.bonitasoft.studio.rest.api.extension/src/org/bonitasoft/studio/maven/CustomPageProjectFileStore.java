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

import org.apache.maven.project.MavenProject;
import org.bonitasoft.engine.session.APISession;
import org.bonitasoft.studio.application.views.BonitaProjectExplorer;
import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.perspectives.BonitaPerspectivesUtils;
import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.filestore.AbstractFileStore;
import org.bonitasoft.studio.common.repository.model.IBuildable;
import org.bonitasoft.studio.common.repository.model.IDeployable;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.maven.i18n.Messages;
import org.bonitasoft.studio.maven.operation.BuildCustomPageOperation;
import org.bonitasoft.studio.maven.operation.ImportCustomPageProjectOperation;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.rest.api.extension.RestAPIExtensionActivator;
import org.bonitasoft.studio.rest.api.extension.ui.perspective.RestAPIExtensionPerspectiveFactory;
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
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.m2e.core.MavenPlugin;
import org.eclipse.m2e.core.project.MavenProjectInfo;
import org.eclipse.m2e.core.project.ProjectImportConfiguration;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.internal.wizards.datatransfer.ArchiveFileExportOperation;

public abstract class CustomPageProjectFileStore<T extends CustomPageMavenProjectDescriptor> extends AbstractFileStore<T> implements IDeployable, IBuildable {

    public static final String QUICK_DEPLOY_COMMAND = "org.bonitasoft.studio.rest.api.extension.quickDeployCommand";

    public CustomPageProjectFileStore(String fileName, CustomPageProjectRepositoryStore parentStore) {
        super(fileName, parentStore);
    }
    
    @Override
    public T getContent() throws ReadFileStoreException {
        return doGetContent();
    }


    public IProject getProject() {
        return getParentStore().getResource().getWorkspace().getRoot().getProject(getName());
    }

    @Override
    public String getDisplayName() {
        try {
            final CustomPageMavenProjectDescriptor content = getContent();
            return String.format("%s (%s)", content.getArtifactId(), content.getVersion());
        } catch (final ReadFileStoreException e) {
            return super.getDisplayName();
        }
    }

    public String getPageDisplayName() {
        try {
            final CustomPageMavenProjectDescriptor content = getContent();
            return content.getDisplayName();
        } catch (final ReadFileStoreException e) {
            return null;
        }
    }

    public String getDescription() {
        try {
            final CustomPageMavenProjectDescriptor content = getContent();
            return content.getDescription();
        } catch (final ReadFileStoreException e) {
            return null;
        }
    }

    @Override
    public StyledString getStyledString() {
        StyledString styledString = new StyledString(getName());

        if ((getProject() == null || !getProject().exists()) && canBeImported()) {
            styledString.append("  ");
            styledString.append(Messages.rightClickToConvert, StyledString.DECORATIONS_STYLER);
        }
        return styledString;
    }

    public boolean canBeImported() {
        return getResource().getFile("pom.xml").exists();
    }

    @Override
    public Image getIcon() {
        return Pics.getImage("prj_obj.gif", RestAPIExtensionActivator.getDefault());
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
            final CustomPageMavenProjectDescriptor raed = getContent();
            return openEditors(page, raed);
        } catch (final PartInitException | ReadFileStoreException e) {
            BonitaStudioLog.error(String.format("Failed to open editors of project %s", getName()), e,
                    RestAPIExtensionActivator.PLUGIN_ID);
        }
        return null;
    }

    protected IWorkbenchPart openEditors(final IWorkbenchPage page, final CustomPageMavenProjectDescriptor descriptor)
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
                final CustomPageMavenProjectDescriptor raed = getContent();
                final List<IResource> resourcesToInclude = findResourcesToExport(raed);
                final ArchiveFileExportOperation op = new ArchiveFileExportOperation(raed.getProject(),
                        resourcesToInclude,
                        target.getAbsolutePath());
                op.run(AbstractRepository.NULL_PROGRESS_MONITOR);
                return ValidationStatus.ok();
            } catch (InvocationTargetException | InterruptedException | ReadFileStoreException e) {
                BonitaStudioLog.error("Cannot export REST API Extension project", e);
            }
        }
        return ValidationStatus.error(
                String.format(org.bonitasoft.studio.common.repository.Messages.failedToRetrieveResourceToExport,
                        getName()));
    }

    protected List<IResource> findResourcesToExport(final CustomPageMavenProjectDescriptor raed) {
        final List<IResource> resourcesToInclude = new ArrayList<>();
        try {
            final IResource[] members = raed.getProject().members();
            for (final IResource member : members) {
                final IPath projectRelativePath = member.getProjectRelativePath();
                final String firstSegment = projectRelativePath.segments()[0];
                if (!"target".equals(firstSegment)
                        && !"bin".equals(firstSegment)
                        && !"node".equals(firstSegment)
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
            //Close opened resources from this project
            IWorkbenchWindow activeWorkbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
            if (activeWorkbenchWindow != null) {
                IWorkbenchPage activePage = activeWorkbenchWindow.getActivePage();
                for (IEditorReference ref : activePage.getEditorReferences()) {
                    IResource resource = ref.getEditorInput().getAdapter(IResource.class);
                    if (resource != null && Objects.equals(resource.getProject(), project)) {
                        activePage.closeEditor(ref.getEditor(false), false);
                    }
                }
                IProgressMonitor monitor = AbstractRepository.NULL_PROGRESS_MONITOR;
                project.close(monitor);
                project.delete(true, true, monitor);
                ((CustomPageProjectRepositoryStore<?>) getParentStore()).refreshMarkers();
            }
        } catch (CoreException | ReadFileStoreException e) {
            super.doDelete();
        }
    }

    public BuildCustomPageOperation newBuildOperation() throws ReadFileStoreException {
        return new BuildCustomPageOperation(getContent(),
                DebugPlugin.getDefault().getLaunchManager());
    }

    public BuildCustomPageOperation newBuildOperation(final String mavenGoals)
            throws ReadFileStoreException {
        return new BuildCustomPageOperation(getContent(),
                DebugPlugin.getDefault().getLaunchManager(), mavenGoals);
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
            mavenProject = getContent().getMavenProject()
                    .orElseThrow(() -> new ReadFileStoreException(String.format("Maven project not found for %s", getName())));
        } catch (ReadFileStoreException e) {
            throw new IOException("Failed to retrieve maven project", e);
        }
        final File archive = new File(mavenProject.getBasedir(),
                "target" + File.separatorChar + mavenProject.getArtifactId() + "-" + mavenProject.getVersion()
                        + ".zip");
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
        projectImportConfiguration.setProjectNameTemplate(project.getName());
        final ImportCustomPageProjectOperation importRestAPIExtensionProjectOperation = new ImportCustomPageProjectOperation(
                this,
                MavenPlugin.getProjectConfigurationManager(), projectImportConfiguration);
        Job job = new WorkspaceJob(String.format("Import %s Project", project.getName())) {

            @Override
            public IStatus runInWorkspace(IProgressMonitor monitor) throws CoreException {
                importRestAPIExtensionProjectOperation.run(monitor);
                return importRestAPIExtensionProjectOperation.getStatus();
            }
        };
        job.setRule(getResource().getWorkspace().getRoot());
        job.schedule();
    }

    public void removeProject() {
        try {
            final IProject project = getProject();
            if (project.exists()) {
                //Only delete project, not content
                project.delete(false, true, AbstractRepository.NULL_PROGRESS_MONITOR);
                ((CustomPageProjectRepositoryStore<?>) getParentStore()).refreshMarkers();
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
     * Always return false.
     * A Rest API is composed of many files, all of them could possibly be opened and dirty, we do not want to check all of
     * them.
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

            if (!Objects.equals(buildOperation.getStatus().getSeverity(), ValidationStatus.ERROR)) {
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

    protected abstract String getBuildFolder();

}
