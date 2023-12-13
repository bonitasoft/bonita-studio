/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.maven.operation;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.collect.Sets.newHashSet;

import java.io.File;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.apache.maven.model.Dependency;
import org.bonitasoft.studio.common.RestAPIExtensionNature;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.core.BonitaProject;
import org.bonitasoft.studio.common.repository.core.ProjectDescriptionBuilder;
import org.bonitasoft.studio.common.repository.core.maven.AddDependencyOperation;
import org.bonitasoft.studio.common.repository.core.maven.MavenProjectHelper;
import org.bonitasoft.studio.maven.ExtensionRepositoryStore;
import org.bonitasoft.studio.maven.i18n.Messages;
import org.bonitasoft.studio.maven.model.CustomPageArchetypeConfiguration;
import org.eclipse.core.resources.ICommand;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.egit.core.op.ConnectProviderOperation;
import org.eclipse.m2e.core.MavenPlugin;
import org.eclipse.m2e.core.internal.IMavenConstants;
import org.eclipse.m2e.core.project.IArchetype;
import org.eclipse.m2e.core.project.IMavenProjectImportResult;
import org.eclipse.m2e.core.project.ProjectImportConfiguration;
import org.eclipse.m2e.core.ui.internal.M2EUIPluginActivator;
import org.eclipse.m2e.core.ui.internal.archetype.ArchetypeGenerator;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.navigator.CommonViewer;

public abstract class CreateCustomPageProjectOperation extends AbstractMavenProjectUpdateOperation {

    private static final String UTF_8 = "UTF-8";
    private static final String PAGE_PROPERTY_PATH = "src/main/resources/page.properties";
    private final IStatus status = Status.OK_STATUS;
    private List<IProject> projects;
    private final CustomPageArchetypeConfiguration archetypeConfiguration;
    protected final ExtensionRepositoryStore repositoryStore;
    private final ProjectImportConfiguration projectImportConfiguration;

    protected CreateCustomPageProjectOperation(
            final ExtensionRepositoryStore repositoryStore,
            final ProjectImportConfiguration projectImportConfiguration,
            final CustomPageArchetypeConfiguration archetypeConfiguration) {
        this.repositoryStore = repositoryStore;
        this.archetypeConfiguration = archetypeConfiguration;
        this.projectImportConfiguration = projectImportConfiguration;
    }

    protected CustomPageArchetypeConfiguration getArchetypeConfiguration() {
        return archetypeConfiguration;
    }

    @Override
    protected IProject doRun(final IProgressMonitor monitor) throws CoreException {
        monitor.beginTask(Messages.creatingRestAPIExtensionProject, IProgressMonitor.UNKNOWN);
        var location = prepareProjectLocation(monitor);
        var mavenProjects  = archetypeGenerator().createArchetypeProjects(location,
                getArchetype(),
                archetypeConfiguration.getGroupId(),
                archetypeConfiguration.getPageName(),
                archetypeConfiguration.getVersion(),
                archetypeConfiguration.getJavaPackage(),
                archetypeConfiguration.toProperties(),
                false,
                monitor);
        projects = MavenPlugin.getProjectConfigurationManager()
                .importProjects(mavenProjects, projectImportConfiguration, project -> {
                    try {
                        CreateCustomPageProjectOperation.this.projectCreated(project);
                    } catch (CoreException e) {
                        BonitaStudioLog.error(e);
                    }
                }, monitor)
                .stream().filter(r -> r.getProject() != null && r.getProject().exists())
                .map(IMavenProjectImportResult::getProject).toList();
        var project = projects.get(0);
        configure(project, monitor);
        repositoryStore.getResource().getProject().refreshLocal(IResource.DEPTH_INFINITE, monitor);
        return project;
    }

    ArchetypeGenerator archetypeGenerator() {
        return M2EUIPluginActivator.getDefault().getArchetypePlugin().getGenerator();
    }

    IPath prepareProjectLocation(final IProgressMonitor monitor) throws CoreException {
        var bonitaProject = BonitaProject.create(repositoryStore.getRepository().getProjectId());
        if(!bonitaProject.getExtensionsParentProject().exists()) {
            repositoryStore.createExtensionModule(bonitaProject, monitor);
        }
        // Folder link is broken, recreate it.
        if(bonitaProject.getExtensionsParentProject().exists() && !repositoryStore.getResource().exists()) {
            repositoryStore.createExtensionFolderLinkInAppProject(bonitaProject);
        }
        return bonitaProject.getExtensionsParentProject().getLocation();
    }

    protected abstract IArchetype getArchetype() ;

    protected void projectCreated(IProject project) throws CoreException {
    	addDependencyToAppProject();
    	
        var display = PlatformUI.getWorkbench().getDisplay();
        display.asyncExec(() ->  display.timerExec(500, this::refreshProjectExplorerView));
    }

    protected void addDependencyToAppProject() throws CoreException {
    	var bonitaProject = BonitaProject.create(repositoryStore.getRepository().getProjectId());
    	var appModel = MavenProjectHelper.getMavenModel(bonitaProject.getAppProject());
    	var extensionDependency = new Dependency();
    	var existingDependency = appModel.getDependencies()
                  .stream()
                  .filter(dep -> Objects.equals(dep.getGroupId(), archetypeConfiguration.getGroupId()))
                  .filter(dep -> Objects.equals(dep.getArtifactId(), archetypeConfiguration.getPageName()))
                  .filter(dep -> Objects.equals(dep.getType(), "zip"))
                  .findFirst();
    	if(existingDependency.isEmpty()) {
    		extensionDependency.setGroupId("${project.groupId}");
        	extensionDependency.setArtifactId(archetypeConfiguration.getPageName());
        	extensionDependency.setVersion("${project.version}");
        	extensionDependency.setType("zip");
    		appModel.getDependencies().add(extensionDependency);
    		new AddDependencyOperation(extensionDependency).run(new NullProgressMonitor());
    	}
    }

	private void refreshProjectExplorerView() {
        IViewPart viewPart = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
                .findView("org.bonitasoft.studio.application.project.explorer");
        if (viewPart != null) {
            CommonViewer viewer = viewPart.getAdapter(CommonViewer.class);
            var store = RepositoryManager.getInstance().getCurrentRepository().orElseThrow().getRepositoryStore(ExtensionRepositoryStore.class);
            if (viewer != null && !viewer.getTree().isDisposed()) {
                viewer.expandToLevel(store.getResource(), 1);
                viewer.refresh(true);
            }
        }
    }

    protected void configure(final IProject project, final IProgressMonitor monitor) throws CoreException {
        IProjectDescription description = project.getDescription();
        checkArgument(description != null, "Project Description is null");
        description = new ProjectDescriptionBuilder().withProjectName(project.getName())
                .withComment(description.getComment())
                .havingNatures(projectNatures(description))
                .havingBuilders(projectBuilders(description)).build();
        project.setDescription(description, monitor);
        project.getFile(getPagePropertyPath()).setCharset(UTF_8, monitor);
        RepositoryManager.getInstance().getCurrentProject().ifPresent( bonitaProject -> {
            File gitDir = bonitaProject.getGitDir();
            if(gitDir.exists()) {
                try {
                    new ConnectProviderOperation(project, gitDir).execute(monitor);
                } catch (CoreException e) {
                   BonitaStudioLog.error(e);
                }
            }
        });
    }

    protected String getPagePropertyPath() {
        return PAGE_PROPERTY_PATH;
    }

    protected Set<String> projectBuilders(final IProjectDescription description) {
        final ICommand[] buildSpec = description.getBuildSpec();
        final Set<String> builders = newHashSet();
        for (final ICommand c : buildSpec) {
            builders.add(c.getBuilderName());
        }
        builders.add(IMavenConstants.BUILDER_ID);
        return builders;
    }

    protected Set<String> projectNatures(final IProjectDescription description) {
        final Set<String> natures = newHashSet(description.getNatureIds());
        natures.add(IMavenConstants.NATURE_ID);
        natures.add(RestAPIExtensionNature.NATURE_ID);
        return natures;
    }

    @Override
    public IStatus getStatus() {
        return status;
    }

    public List<IProject> getProjects() {
        return projects;
    }

}
