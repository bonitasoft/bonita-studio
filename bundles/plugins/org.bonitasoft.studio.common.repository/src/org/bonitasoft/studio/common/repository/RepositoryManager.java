/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.common.repository;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.bonitasoft.studio.common.extension.BonitaStudioExtensionRegistryManager;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.core.BonitaProject;
import org.bonitasoft.studio.common.repository.core.IBonitaProjectListenerProvider;
import org.bonitasoft.studio.common.repository.core.maven.contribution.InstallBonitaMavenArtifactsOperation;
import org.bonitasoft.studio.common.repository.core.maven.model.ProjectMetadata;
import org.bonitasoft.studio.common.repository.filestore.AbstractFileStore;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.common.repository.preferences.RepositoryPreferenceConstant;
import org.bonitasoft.studio.common.ui.PlatformUtil;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Adapters;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.m2e.core.MavenPlugin;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * @author Romain Bioteau
 */
public class RepositoryManager {

    private static final String REPOSITORY_FACTORY_IMPLEMENTATION_ID = "org.bonitasoft.studio.repositoryFactory";
    private static final String PROJECT_LISTENER_PROVIDER_ID = "org.bonitasoft.studio.common.repository.projectListenerProvider";
    private static final String PRIORITY = "priority";
    private static final String CLASS = "class";

    private static RepositoryManager INSTANCE;

    private List<IBonitaProjectListener> projectListeners = new ArrayList<>();
    private AbstractRepository repository;
    private IPreferenceStore preferenceStore;
    private IConfigurationElement repositoryImplementationElement;
    private RepositoryAccessor repositoryAccessor;

    private RepositoryManager() {
        final IConfigurationElement[] repositoryFactories = BonitaStudioExtensionRegistryManager.getInstance()
                .getConfigurationElements(
                        REPOSITORY_FACTORY_IMPLEMENTATION_ID);
        final List<IConfigurationElement> sortedElems = sortByPriority(repositoryFactories);
        if (!sortedElems.isEmpty()) {
            repositoryImplementationElement = sortedElems.get(0); //Higher element
            preferenceStore = CommonRepositoryPlugin.getDefault().getPreferenceStore();
            IConfigurationElement[] listenerProviders = BonitaStudioExtensionRegistryManager.getInstance()
                    .getConfigurationElements(
                            PROJECT_LISTENER_PROVIDER_ID);
            for (IConfigurationElement elem : listenerProviders) {
                try {
                    IBonitaProjectListenerProvider provider = (IBonitaProjectListenerProvider) elem
                            .createExecutableExtension("class");
                    provider.getListeners().stream().forEach(this::addBonitaProjectListener);
                } catch (CoreException e) {
                    BonitaStudioLog.error(e);
                }
            }
            String currentRepository = preferenceStore.getString(RepositoryPreferenceConstant.CURRENT_REPOSITORY);
            if (currentRepository != null && !currentRepository.isEmpty()) {
                repository = newRepository(currentRepository);
            }

        }
    }

    private List<IConfigurationElement> sortByPriority(final IConfigurationElement[] elements) {
        final List<IConfigurationElement> sortedConfigElems = new ArrayList<>();
        for (final IConfigurationElement elem : elements) {
            sortedConfigElems.add(elem);
        }

        Collections.sort(sortedConfigElems, (e1, e2) -> {
            int p1 = 0;
            int p2 = 0;
            try {
                p1 = Integer.parseInt(e1.getAttribute(PRIORITY));
            } catch (final NumberFormatException e3) {
                p1 = 0;
            }
            try {
                p2 = Integer.parseInt(e2.getAttribute(PRIORITY));
            } catch (final NumberFormatException e4) {
                p2 = 0;
            }
            return p2 - p1; //Highest Priority first
        });
        return sortedConfigElems;

    }

    public AbstractRepository newRepository(final String name) {
        try {
            final IRepositoryFactory repositoryFactory = (IRepositoryFactory) repositoryImplementationElement
                    .createExecutableExtension(CLASS);
            AbstractRepository newRepository = repositoryFactory.newRepository(name);
            for (IBonitaProjectListener listener : projectListeners) {
                newRepository.addProjectListener(listener);
            }
            return newRepository;
        } catch (final CoreException e) {
            BonitaStudioLog.error(e);
        }
        return null;
    }

    public static synchronized RepositoryManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RepositoryManager();
        }
        return INSTANCE;
    }

    public IPreferenceStore getPreferenceStore() {
        return preferenceStore;
    }

    public Optional<AbstractRepository> getCurrentRepository() {
        return Optional.ofNullable(repository);
    }

    public <T> T getRepositoryStore(final Class<T> storeClass) {
        return getCurrentRepository()
                .map(repo -> repo.getRepositoryStore(storeClass))
                .map(storeClass::cast)
                .orElse(null);
    }

    public AbstractRepository getRepository(final String repositoryName) {
        var currentRepository = getCurrentRepository()
                .filter(repo -> Objects.equals(repo.getName(), repositoryName))
                .orElse(null);
        if (currentRepository != null) {
            return currentRepository;
        }

        final IWorkspace workspace = ResourcesPlugin.getWorkspace();
        final IProject project = workspace.getRoot().getProject(repositoryName);
        if (project == null || !project.exists()) {
            return null;
        }
        return Optional.of(project)
                .filter(p -> p.getLocation().toFile().toPath()
                        .resolve(IProjectDescription.DESCRIPTION_FILE_NAME).toFile().isFile())
                .map(p -> p.getLocation().toFile().toPath()
                        .resolve(IProjectDescription.DESCRIPTION_FILE_NAME).toFile())
                .map(descriptorFile -> {
                    if (hasNature(descriptorFile, BonitaProjectNature.NATURE_ID)) {
                        return newRepository(repositoryName);
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .orElse(null);
    }

    public List<IRepository> getAllRepositories() {
        final IWorkspace workspace = ResourcesPlugin.getWorkspace();
        return Stream.of(workspace.getRoot().getProjects())
                .filter(project -> project.getLocation().toFile().toPath()
                        .resolve(IProjectDescription.DESCRIPTION_FILE_NAME).toFile().isFile())
                .map(project -> {
                    File descriptorFile = project.getLocation()
                            .toFile()
                            .toPath()
                            .resolve(IProjectDescription.DESCRIPTION_FILE_NAME)
                            .toFile();
                    if (hasNature(descriptorFile, BonitaProjectNature.NATURE_ID)) {
                        String projectName = projectName(descriptorFile);
                        if (!projectName.equals(repository.getName())) {
                            return newRepository(projectName);
                        } else {
                            return repository;
                        }
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private String projectName(File descriptorFile) {
        XPath xPath = XPathFactory.newInstance().newXPath();
        try (InputStream is = java.nio.file.Files.newInputStream(descriptorFile.toPath())) {
            Document document = asXMLDocument(is);
            return (String) xPath.evaluate("//projectDescription/name/text()", document, XPathConstants.STRING);
        } catch (IOException | XPathExpressionException e) {
            BonitaStudioLog.error(e);
        }
        return null;
    }

    private boolean hasNature(File descriptorFile, String natureId) {
        XPath xPath = XPathFactory.newInstance().newXPath();
        try (InputStream is = java.nio.file.Files.newInputStream(descriptorFile.toPath())) {
            Document document = asXMLDocument(is);
            NodeList natures = (NodeList) xPath.evaluate("//projectDescription/natures/nature/text()", document,
                    XPathConstants.NODESET);
            for (int i = 0; i < natures.getLength(); ++i) {
                Node item = natures.item(i);
                if (Objects.equals(item.getTextContent(), natureId)) {
                    return true;
                }
            }
        } catch (IOException | XPathExpressionException e) {
            BonitaStudioLog.error(e);
        }
        return false;
    }

    private static Document asXMLDocument(InputStream source) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
        factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");
        factory.setNamespaceAware(true);
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            return builder.parse(new InputSource(source));
        } catch (SAXException | IOException | ParserConfigurationException e) {
            return null;
        }
    }

    public void setRepository(final String repositoryName, final IProgressMonitor monitor) {
        setRepository(repositoryName, false, monitor);
    }

    public void setRepository(final String repositoryName,
            final boolean migrationEnabled,
            final IProgressMonitor monitor) {
        var currentRepository = getCurrentRepository()
                .orElse(null);
        if (currentRepository != null && currentRepository.getName().equals(repositoryName)) {
            return;
        } else if (currentRepository != null && currentRepository.getProject().isOpen()) {
            try {
                getCurrentProject().orElseThrow().close(monitor);
            } catch (CoreException e) {
                BonitaStudioLog.error(e);
            }
        }
        currentRepository = getRepository(repositoryName);
        if (currentRepository == null) {
            // Ensure Bonita artifacts are properly installed in user local repository before creating a project
            try {
                monitor.subTask("");
                monitor.beginTask("Check local repository required artifacts...", IProgressMonitor.UNKNOWN);
                new InstallBonitaMavenArtifactsOperation(MavenPlugin.getMaven().getLocalRepository()).execute(monitor);
            } catch (CoreException e1) {
                BonitaStudioLog.error(e1);
            }
            currentRepository = newRepository(repositoryName);
        }
        ProjectMetadata metadata = ProjectMetadata.read(currentRepository.getProject(), monitor);
        metadata.setArtifactId(repositoryName);
        monitor.setTaskName(Messages.creatingNewProject);
        var project = Adapters.adapt(currentRepository.create(metadata, monitor), BonitaProject.class);
        try {
            project.open(monitor);
        } catch (CoreException e) {
            BonitaStudioLog.error(e);
        }
        setCurrentRepository(currentRepository);
    }

    public Optional<IRepositoryStore<? extends IRepositoryFileStore>> getRepositoryStore(Object element) {
        Object resource = element instanceof IJavaElement ? ((IJavaElement) element).getResource() : element;
        var currentRepository = getCurrentRepository().orElse(null);
        if (!hasActiveRepository() || !currentRepository.isLoaded()
                || !currentRepository.getProject().isOpen()) {
            return Optional.empty();
        }
        return currentRepository.getAllStores().stream()
                .filter(repo -> java.util.Objects.equals(resource, repo.getResource()))
                .findFirst();
    }

    public void addBonitaProjectListener(IBonitaProjectListener listener) {
        projectListeners.add(listener);
    }

    public List<IBonitaProjectListener> getBonitaProjectListeners() {
        return projectListeners;
    }

    public boolean hasActiveRepository() {
        return repository != null;
    }

    public void setCurrentRepository(AbstractRepository repository) {
        this.repository = repository;
    }

    public RepositoryAccessor getAccessor() {
        return repositoryAccessor;
    }

    public void setRepositoryAccessor(RepositoryAccessor repositoryAccessor) {
        this.repositoryAccessor = repositoryAccessor;
    }

    public synchronized IRepository switchToRepository(
            final String repositoryName,
            IProgressMonitor monitor) {
        if (monitor == null) {
            monitor = new NullProgressMonitor();
        }
        var currentRepository = RepositoryManager.getInstance()
                .getCurrentRepository();
        if (currentRepository.filter(repo -> Objects.equals(repo.getName(), repositoryName)).isPresent()) {
            return currentRepository.get();
        }
        //        getCurrentProject().ifPresent(project -> {
        //            try {
        //                project.close(new NullProgressMonitor());
        //            } catch (CoreException e) {
        //                BonitaStudioLog.error(e);
        //            }
        //        });
        try {
            WorkspaceModifyOperation workspaceModifyOperation = new WorkspaceModifyOperation() {

                @Override
                protected void execute(final IProgressMonitor monitor)
                        throws CoreException, InvocationTargetException, InterruptedException {
                    monitor.beginTask(Messages.team_switchingProject,
                            IProgressMonitor.UNKNOWN);
                    BonitaStudioLog.info("Switching project to "
                            + repositoryName, CommonRepositoryPlugin.PLUGIN_ID);
                    RepositoryManager.getInstance().setRepository(repositoryName, monitor);
                    BonitaStudioLog.info(
                            "Project switched to " + repositoryName,
                            CommonRepositoryPlugin.PLUGIN_ID);
                }
            };
            workspaceModifyOperation.run(monitor);
            Display.getDefault().asyncExec(
                    PlatformUtil::openDashboardIfNoOtherEditorOpen);
            Display.getDefault().asyncExec(
                    AbstractFileStore::refreshExplorerView);
        } catch (final InvocationTargetException | InterruptedException e) {
            BonitaStudioLog.error(e);
        }
        
        return RepositoryManager.getInstance()
                .getCurrentRepository().orElseThrow();

    }

    public Optional<BonitaProject> getCurrentProject() {
        return getCurrentRepository().map(repo -> Adapters.adapt(repo, BonitaProject.class));
    }
}
