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
import java.util.Set;

import org.bonitasoft.studio.common.RestAPIExtensionNature;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.core.ProjectDescriptionBuilder;
import org.bonitasoft.studio.maven.CustomPageProjectRepositoryStore;
import org.bonitasoft.studio.maven.i18n.Messages;
import org.bonitasoft.studio.maven.model.CustomPageArchetypeConfiguration;
import org.bonitasoft.studio.rest.api.extension.core.repository.RestAPIExtensionRepositoryStore;
import org.eclipse.core.resources.ICommand;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.egit.core.op.ConnectProviderOperation;
import org.eclipse.m2e.core.internal.IMavenConstants;
import org.eclipse.m2e.core.project.IProjectConfigurationManager;
import org.eclipse.m2e.core.project.IProjectCreationListener;
import org.eclipse.m2e.core.project.ProjectImportConfiguration;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.navigator.CommonViewer;

public class CreateCustomPageProjectOperation extends AbstractMavenProjectUpdateOperation {

    private static final String UTF_8 = "UTF-8";
    private static final String PAGE_PROPERTY_PATH = "src/main/resources/page.properties";
    private final IStatus status = Status.OK_STATUS;
    private List<IProject> projects;
    private final IProjectConfigurationManager projectConfigurationManager;
    private final CustomPageArchetypeConfiguration archetypeConfiguration;
    private final CustomPageProjectRepositoryStore<?> repositoryStore;
    private final ProjectImportConfiguration projectImportConfiguration;

    public CreateCustomPageProjectOperation(
            final CustomPageProjectRepositoryStore<?> repositoryStore,
            final IProjectConfigurationManager projectConfigurationManager,
            final ProjectImportConfiguration projectImportConfiguration,
            final CustomPageArchetypeConfiguration archetypeConfiguration) {
        super(true);
        this.repositoryStore = repositoryStore;
        this.projectConfigurationManager = projectConfigurationManager;
        this.archetypeConfiguration = archetypeConfiguration;
        this.projectImportConfiguration = projectImportConfiguration;
    }

    protected CustomPageArchetypeConfiguration getArchetypeConfiguration() {
        return archetypeConfiguration;
    }

    @Override
    protected IProject doRun(final IProgressMonitor monitor) throws CoreException {
        monitor.beginTask(Messages.creatingRestAPIExtensionProject, IProgressMonitor.UNKNOWN);
        final IPath location = repositoryStore.getResource().getLocation();

        projects = projectConfigurationManager.createArchetypeProjects(location,
                repositoryStore.getArchetype(),
                archetypeConfiguration.getGroupId(),
                archetypeConfiguration.getPageName(),
                archetypeConfiguration.getVersion(),
                archetypeConfiguration.getGroupId(),
                archetypeConfiguration.toProperties(),
                projectImportConfiguration,
                new IProjectCreationListener() {

                    @Override
                    public void projectCreated(IProject project) {
                        try {
                            CreateCustomPageProjectOperation.this.projectCreated(project);
                        } catch (CoreException e) {
                            BonitaStudioLog.error(e);
                        }
                    }
                },
                monitor);
        final IProject project = projects.get(0);
        configure(project, monitor);
        repositoryStore.getResource().getProject().refreshLocal(IResource.DEPTH_INFINITE, monitor);
        return project;
    }

    protected void projectCreated(IProject project) throws CoreException {
        var display = PlatformUI.getWorkbench().getDisplay();
        display.asyncExec(() ->  display.timerExec(500, () -> refreshProjectExplorerView()));
    }

    private void refreshProjectExplorerView() {
        IViewPart viewPart = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
                .findView("org.bonitasoft.studio.application.project.explorer");
        if (viewPart != null) {
            CommonViewer viewer = viewPart.getAdapter(CommonViewer.class);
            var store = RepositoryManager.getInstance().getCurrentRepository().orElseThrow().getRepositoryStore(RestAPIExtensionRepositoryStore.class);
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
