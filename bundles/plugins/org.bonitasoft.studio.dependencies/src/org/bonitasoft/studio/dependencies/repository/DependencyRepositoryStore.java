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
package org.bonitasoft.studio.dependencies.repository;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.ImportArchiveData;
import org.bonitasoft.studio.common.repository.core.IFileInputStreamSupplier;
import org.bonitasoft.studio.common.repository.core.InputStreamSupplier;
import org.bonitasoft.studio.common.repository.core.ProjectDependenciesStore;
import org.bonitasoft.studio.common.repository.core.maven.DefinitionUsageOperation;
import org.bonitasoft.studio.common.repository.core.maven.DependencyUsageOperation;
import org.bonitasoft.studio.common.repository.core.maven.MavenProjectHelper;
import org.bonitasoft.studio.common.repository.core.maven.MavenRepositoryRegistry;
import org.bonitasoft.studio.common.repository.core.maven.ProjectDependenciesResolver;
import org.bonitasoft.studio.common.repository.core.maven.migration.ProjectDependenciesMigrationOperation;
import org.bonitasoft.studio.common.repository.core.maven.migration.model.DependencyLookup;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.bonitasoft.studio.common.repository.store.AbstractRepositoryStore;
import org.bonitasoft.studio.common.repository.store.LocalDependenciesStore;
import org.bonitasoft.studio.dependencies.DependenciesPlugin;
import org.bonitasoft.studio.dependencies.i18n.Messages;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.swt.graphics.Image;

public class DependencyRepositoryStore extends AbstractRepositoryStore<DependencyFileStore> {

    public static final String STORE_NAME = "lib";
    public static final String JAR_EXT = "jar";
    private static final Set<String> extensions = new HashSet<>();
    static {
        extensions.add("jar");
    }

    private Map<String, String> runtimeDependencies;
    private ProjectDependenciesResolver projectDependenciesResolver = new ProjectDependenciesResolver();
    private MavenRepositoryRegistry mavenRepositoryRegistry = new MavenRepositoryRegistry();

    @Override
    public void createRepositoryStore(IRepository repository) {
        this.repository = repository;
    }

    @Override
    public IFolder getResource() {
        final IProject project = repository.getProject();
        return project.getFolder(getName());
    }

    @Override
    protected DependencyFileStore doImportInputStream(String fileName, InputStream inputStream) {
        return DependencyFileStore.NULL;
    }

    @Override
    protected DependencyFileStore doImportArchiveData(ImportArchiveData importArchiveData, IProgressMonitor monitor)
            throws CoreException {
        return DependencyFileStore.NULL;
    }

    @Override
    public boolean canBeExported() {
        return false;
    }

    @Override
    public boolean canBeShared() {
        return false;
    }

    @Override
    protected List<IResource> listChildren() throws CoreException {
        return Collections.emptyList();
    }

    @Override
    public DependencyFileStore createRepositoryFileStore(final String fileName) {
        return DependencyFileStore.NULL;
    }

    @Override
    public List<DependencyFileStore> getChildren() {
        try {
            return projectDependenciesResolver.getCompileDependencies(getRepository().getProject(),
                    AbstractRepository.NULL_PROGRESS_MONITOR)
                    .stream()
                    .map(artifact -> new MavenDependencyFileStore(artifact, DependencyRepositoryStore.this))
                    .collect(Collectors.toList());
        } catch (CoreException e) {
            BonitaStudioLog.error(e);
            return Collections.emptyList();
        }
    }

    @Override
    public DependencyFileStore getChild(String fileName, boolean force) {
        DependencyFileStore fileStore = super.getChild(fileName, force);
        if (fileStore == null) {
            try {
                return projectDependenciesResolver.findCompileDependency(fileName, getRepository().getProject(),
                        AbstractRepository.NULL_PROGRESS_MONITOR)
                        .map(artifact -> new MavenDependencyFileStore(artifact, DependencyRepositoryStore.this))
                        .orElse(null);
            } catch (CoreException e) {
                BonitaStudioLog.error(e);
                return null;
            }
        }
        return fileStore;
    }

    protected static Set<String> retriveAllJarFilesFrom(final File root) {
        final Set<String> allJarFiles = new HashSet<>();
        final File[] listFiles = root.listFiles();
        if (listFiles != null) {
            for (final File f : listFiles) {
                if (f.isDirectory()) {
                    allJarFiles.addAll(retriveAllJarFilesFrom(f));
                } else if (f.getName().endsWith(".jar")) {
                    allJarFiles.add(f.getName());
                }
            }
        }
        return allJarFiles;
    }

    @Override
    public String getName() {
        return STORE_NAME;
    }

    @Override
    public String getDisplayName() {
        return Messages.dependenciesRepository;
    }

    @Override
    public Image getIcon() {
        return Pics.getImage("dependencies.png", DependenciesPlugin.getDefault());
    }

    @Override
    public Set<String> getCompatibleExtensions() {
        return extensions;
    }

    public Map<String, String> getRuntimeDependencies() {
        if (runtimeDependencies == null) {
            runtimeDependencies = new HashMap<>();
            final File tomcatRoot = getTomcatRootFile();
            final Set<String> allJarFiles = retriveAllJarFilesFrom(new File(tomcatRoot, "lib"));
            allJarFiles.addAll(retriveAllJarFilesFrom(new File(tomcatRoot, "webapps")));
            for (final String jarName : allJarFiles) {
                runtimeDependencies.put(getLibName(jarName), getLibVersion(jarName));
            }
        }
        return runtimeDependencies;
    }

    protected String getLibVersion(String jarName) {
        if (jarName.endsWith(".jar")) {
            jarName = jarName.replace(".jar", "");
        }
        String libVersion = "";
        boolean appendSnapshot = false;
        if (jarName.indexOf("-SNAPSHOT") != -1) {
            jarName = jarName.substring(0, jarName.lastIndexOf("-SNAPSHOT"));
            appendSnapshot = true;
        }
        if (jarName.indexOf("-") != -1 && Character.isDigit(jarName.charAt(jarName.lastIndexOf("-") + 1))) {
            libVersion = jarName.substring(jarName.lastIndexOf("-") + 1, jarName.length());
        }
        if (appendSnapshot) {
            libVersion = libVersion + "-SNAPSHOT";
        }
        return libVersion;
    }

    protected String getLibName(String jarName) {
        if (jarName.endsWith(".jar")) {
            jarName = jarName.replace(".jar", "");
        }
        String libName = "";
        if (jarName.indexOf("-SNAPSHOT") != -1) {
            jarName = jarName.substring(0, jarName.lastIndexOf("-SNAPSHOT"));
        }
        if (jarName.indexOf("-") != -1 && Character.isDigit(jarName.charAt(jarName.lastIndexOf("-") + 1))) {
            libName = jarName.substring(0, jarName.lastIndexOf("-"));
        } else {
            libName = jarName;
        }
        return libName;
    }

    protected File getTomcatRootFile() {
        return new File(ResourcesPlugin.getWorkspace().getRoot().getLocation().toFile(),
                "tomcat" + File.separatorChar + "server");
    }

    @Override
    public void migrate(final IProgressMonitor monitor) throws CoreException, MigrationException {
        // TODO list existing jars from lib folder if it exists
        // Check if pom file exists 
        IProject project = getRepository().getProject();
        if (getResource().exists()) {
            try {
                Set<DependencyLookup> dependencyLookups = doMigrateToMavenDependencies(monitor);
                dependencyLookups.stream()
                        .forEach(dl -> dl.setSelected(dl.isUsed()));
                LocalDependenciesStore localDependencyStore = getRepository().getLocalDependencyStore();
                MavenProjectHelper mavenProjectHelper = new MavenProjectHelper();
                Model mavenModel = mavenProjectHelper.getMavenModel(project);
                dependencyLookups.stream()
                        .filter(DependencyLookup::isSelected)
                        .map(dl -> {
                            try {
                                return localDependencyStore.install(dl);
                            } catch (CoreException e) {
                                BonitaStudioLog.error(e);
                                return null;
                            }
                        })
                        .filter(Objects::nonNull)
                        .map(DependencyLookup::toMavenDependency)
                        .forEach(newDependeny -> {
                            Optional<Dependency> dependency = mavenProjectHelper.findDependency(mavenModel,
                                    newDependeny.getGroupId(),
                                    newDependeny.getArtifactId());
                            if (dependency.isPresent()) {
                                mavenModel.removeDependency(dependency.get());
                                mavenModel.addDependency(newDependeny);
                            } else {
                                mavenModel.addDependency(newDependeny);
                            }
                        });
                mavenProjectHelper.saveModel(project, mavenModel);
                ProjectDependenciesStore projectDependenciesStore = getRepository().getProjectDependenciesStore();
                if (projectDependenciesStore != null) {
                    projectDependenciesStore.analyze(monitor);
                }
                getResource().delete(true, monitor);
            } catch (InvocationTargetException e) {
                throw new MigrationException(e.getTargetException());
            } catch (InterruptedException e) {
                BonitaStudioLog.error(e);
            }
        }
    }

    protected Set<DependencyLookup> doMigrateToMavenDependencies(IProgressMonitor monitor)
            throws InvocationTargetException, InterruptedException, CoreException {
        Set<String> usedDependencies = dependencyUsageAnalysis(monitor);
        Set<String> usedDefinitions = definitionUsageAnalysis(monitor);
        // Automatic dependency lookup
        List<InputStreamSupplier> jars = new ArrayList<>();
        try {
            jars = createJarInputStreamSuppliers();
            var projectDependenciesMigrationOperation = new ProjectDependenciesMigrationOperation(jars)
                    .addUsedDependencies(usedDependencies)
                    .addUsedDefinitions(usedDefinitions);
            mavenRepositoryRegistry.updateRegistry().run(monitor);
            mavenRepositoryRegistry
                    .getGlobalRepositories()
                    .stream()
                    .map(org.eclipse.m2e.core.repository.IRepository::getUrl)
                    .forEach(projectDependenciesMigrationOperation::addRemoteRespository);
            projectDependenciesMigrationOperation.run(monitor);
            return projectDependenciesMigrationOperation.getResult();
        } finally {
            for (InputStreamSupplier jar : jars) {
                try {
                    jar.close();
                } catch (Exception e) {
                    BonitaStudioLog.error(e);
                }
            }
        }
    }

    private List<InputStreamSupplier> createJarInputStreamSuppliers() throws CoreException {
        return Stream.of(getResource().members())
                .filter(IFile.class::isInstance)
                .map(IFile.class::cast)
                .filter(file -> Objects.equals(file.getFileExtension(), "jar"))
                .map(file -> new IFileInputStreamSupplier(file))
                .collect(Collectors.toList());
    }

    private Set<String> definitionUsageAnalysis(IProgressMonitor monitor)
            throws CoreException, InvocationTargetException, InterruptedException {
        IProject project = getRepository().getProject();
        IFolder diagrams = project.getFolder("diagrams");
        List<InputStreamSupplier> files = Stream.of(diagrams.members())
                .filter(IFile.class::isInstance)
                .map(IFile.class::cast)
                .filter(file -> Objects.equals("proc", file.getFileExtension()))
                .map(f -> new IFileInputStreamSupplier(f))
                .collect(Collectors.toList());
        DefinitionUsageOperation dependencyUsageOperation = new DefinitionUsageOperation(files);
        dependencyUsageOperation.run(monitor);
        return dependencyUsageOperation.getUsedDefinitions();
    }

    private Set<String> dependencyUsageAnalysis(IProgressMonitor monitor)
            throws CoreException, InvocationTargetException, InterruptedException {
        IProject project = getRepository().getProject();
        IFolder diagrams = project.getFolder("diagrams");
        IFolder processConfiguration = project.getFolder("process_configurations");
        List<InputStreamSupplier> files = Stream.concat(
                Stream.of(diagrams.members())
                        .filter(IFile.class::isInstance)
                        .map(IFile.class::cast)
                        .filter(file -> Objects.equals("proc", file.getFileExtension())),
                Stream.of(processConfiguration.members())
                        .filter(IFile.class::isInstance)
                        .map(IFile.class::cast)
                        .filter(file -> Objects.equals("conf", file.getFileExtension())))
                .map(f -> new IFileInputStreamSupplier(f))
                .collect(Collectors.toList());
        DependencyUsageOperation dependencyUsageOperation = new DependencyUsageOperation(files);
        dependencyUsageOperation.run(monitor);
        return dependencyUsageOperation.getUsedDependencies();
    }

    public Optional<DependencyFileStore> findDependencyByName(String jarName) {
        return getChildren().stream().filter(dep-> Objects.equals(jarName, dep.getName())).findFirst();
    }
    
}
