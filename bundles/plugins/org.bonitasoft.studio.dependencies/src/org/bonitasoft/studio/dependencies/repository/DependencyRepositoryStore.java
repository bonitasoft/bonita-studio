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
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.ImportArchiveData;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.core.maven.ProjectDependenciesResolver;
import org.bonitasoft.studio.common.repository.core.migration.report.MigrationReport;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.bonitasoft.studio.common.repository.store.AbstractRepositoryStore;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.edapt.migration.MigrationException;

public class DependencyRepositoryStore extends AbstractRepositoryStore<DependencyFileStore> {

    private static final String SNAPSHOT_SUFFIX = "-SNAPSHOT";
    public static final String STORE_NAME = "lib";
    public static final String JAR_EXT = "jar";
    private static final Set<String> extensions = new HashSet<>();
    static {
        extensions.add("jar");
    }

    private Map<String, String> runtimeDependencies;
    private ProjectDependenciesResolver projectDependenciesResolver = new ProjectDependenciesResolver(
            RepositoryManager.getInstance().getAccessor());

    @Override
    public void createRepositoryStore(IRepository repository) {
        this.repository = repository;
    }

    @Override
    public IFolder getResource() {
        if (repository != null && repository.getProject() != null) {
            return repository.getProject().getFolder(getName());
        }
        return null;
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
        return new DependencyFileStore(fileName, this);
    }

    @Override
    public List<DependencyFileStore> getChildren() {
        try {
            return projectDependenciesResolver.getCompileDependencies(AbstractRepository.NULL_PROGRESS_MONITOR)
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
                return projectDependenciesResolver
                        .findCompileDependency(fileName, AbstractRepository.NULL_PROGRESS_MONITOR)
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
        if (jarName.indexOf(SNAPSHOT_SUFFIX) != -1) {
            jarName = jarName.substring(0, jarName.lastIndexOf(SNAPSHOT_SUFFIX));
            appendSnapshot = true;
        }
        if (jarName.indexOf("-") != -1 && Character.isDigit(jarName.charAt(jarName.lastIndexOf("-") + 1))) {
            libVersion = jarName.substring(jarName.lastIndexOf("-") + 1, jarName.length());
        }
        if (appendSnapshot) {
            libVersion = libVersion + SNAPSHOT_SUFFIX;
        }
        return libVersion;
    }

    protected String getLibName(String jarName) {
        if (jarName.endsWith(".jar")) {
            jarName = jarName.replace(".jar", "");
        }
        String libName = "";
        if (jarName.indexOf(SNAPSHOT_SUFFIX) != -1) {
            jarName = jarName.substring(0, jarName.lastIndexOf(SNAPSHOT_SUFFIX));
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
    public MigrationReport migrate(IProgressMonitor monitor) throws CoreException, MigrationException {
        return MigrationReport.emptyReport();
    }

    public Optional<DependencyFileStore> findDependencyByName(String jarName) {
        return getChildren().stream()
                .filter(dep -> Objects.equals(jarName, dep.getName()))
                .findFirst();
    }
    
    public List<DependencyFileStore> findJarDependencies() {
        return getChildren().stream()
                .filter(dep -> dep instanceof MavenDependencyFileStore
                        && "jar".equals(((MavenDependencyFileStore) dep).getArtifact().getType()))
                .collect(Collectors.toList());
    }

    /**
     * This store must be migrated first
     */
    @Override
    public int getImportOrder() {
        return 0;
    }



}
