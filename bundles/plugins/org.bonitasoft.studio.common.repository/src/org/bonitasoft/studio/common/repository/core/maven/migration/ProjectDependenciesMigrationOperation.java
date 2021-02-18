/**
 * Copyright (C) 2021 BonitaSoft S.A.
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
package org.bonitasoft.studio.common.repository.core.maven.migration;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.maven.artifact.versioning.DefaultArtifactVersion;
import org.bonitasoft.studio.common.FileUtil;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.Messages;
import org.bonitasoft.studio.common.repository.core.maven.JarLookupOperation;
import org.bonitasoft.studio.common.repository.core.maven.migration.model.DependencyLookup;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;

public class ProjectDependenciesMigrationOperation implements IRunnableWithProgress {

    public static final String MAVEN_CENTRAL_REPOSITORY_URL = "https://repo.maven.apache.org/maven2/";
    private static final String BDM_CLIENT_POJO_JAR = "bdm-client-pojo.jar";

    private File libFolder;
    private List<DependencyLookup> result = new ArrayList<>();
    private Set<String> repositories = new HashSet<>();

    public ProjectDependenciesMigrationOperation(File libFolder) {
        this.libFolder = libFolder;
    }

    public ProjectDependenciesMigrationOperation addRemoteRespository(String repositoryUrl) {
        this.repositories.add(repositoryUrl);
        return this;
    }

    @Override
    public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        monitor.beginTask(Messages.convertingDependencies,
                libFolder.listFiles(f -> f.getName().endsWith(".jar")).length);
        List<BonitaJarDependencyReplacement> replacements = BonitaJarDependencyReplacement
                .getBonitaJarDependencyReplacements();

        List<String> jars = Stream.of(libFolder.listFiles())
                .filter(File::isFile)
                .map(File::getName)
                .filter(f -> f.toLowerCase().endsWith(".jar"))
                .collect(Collectors.toList());

        Set<String> dependenciesToRemove = jars.stream()
                .filter(jarName -> replacements.stream().anyMatch(r -> r.matches(jarName)))
                .collect(Collectors.toSet());

        // Search Bonita connectors/actor filters replacements
        jars.stream()
                .flatMap(jarName -> replacements.stream()
                        .filter(r -> r.matches(jarName))
                        .map(r -> newDependencyLookup(jarName, r)))
                .peek(jar -> monitor.worked(1))
                .forEach(result::add);

        // Remove all transitive jar from bonita artifacts
        Stream.of(libFolder.listFiles())
                .filter(jarFile -> dependenciesToRemove.contains(jarFile.getName()))
                .flatMap(jarFile -> BonitaJarDependencyReplacement.getTransitiveDependencies(jarFile).stream())
                .peek(jar -> monitor.worked(1))
                .forEach(dependenciesToRemove::add);

        dependenciesToRemove.add(BDM_CLIENT_POJO_JAR);

        Set<File> jarsToLookup = Stream.of(libFolder.listFiles())
                .filter(File::isFile)
                .filter(f -> f.getName().toLowerCase().endsWith(".jar"))
                .filter(f -> !dependenciesToRemove.contains(f.getName()))
                .collect(Collectors.toSet());

        Path localM2Repo;
        try {
            localM2Repo = Files.createTempDirectory("localM2repo");
        } catch (IOException e) {
            throw new InvocationTargetException(e);
        }
        for (File jarToLookup : jarsToLookup) {
            JarLookupOperation jarLookupOperation = new JarLookupOperation(jarToLookup);
            repositories.stream().forEach(jarLookupOperation::addRemoteRespository);
            jarLookupOperation.addLocalRespository(localM2Repo.toFile().getAbsolutePath());
            monitor.subTask(String.format(Messages.lookupDependencyFor, jarToLookup.getName()));
            jarLookupOperation.run(AbstractRepository.NULL_PROGRESS_MONITOR);
            monitor.worked(1);
            var status = jarLookupOperation.getStatus();
            if (status.isOK()) {
                DependencyLookup dep = jarLookupOperation.getResult();
                if (isLatestVersion(dep, result)) {
                    result.add(dep);
                }
            } else {
                //TODO add a migration issue in the report model
            }
        }
        if(localM2Repo != null) {
            FileUtil.deleteDir(localM2Repo.toFile());
        }
    }

    private DependencyLookup newDependencyLookup(String jarName, BonitaJarDependencyReplacement dep) {
        return new DependencyLookup(jarName,
                null, // no sha1
                DependencyLookup.Status.FOUND,
                dep.getMavenDependency().getGroupId(),
                dep.getMavenDependency().getArtifactId(),
                dep.getMavenDependency().getVersion(),
                dep.getMavenDependency().getClassifier(),
                MAVEN_CENTRAL_REPOSITORY_URL);
    }

    private boolean isLatestVersion(DependencyLookup depToAdd, List<DependencyLookup> dependencies) {
        List<DependencyLookup> conflictingDependencies = dependencies.stream()
                .filter(existingDep -> Objects.equals(depToAdd.getGroupId(), existingDep.getGroupId())
                        && Objects.equals(depToAdd.getArtifactId(), existingDep.getArtifactId()))
                .sorted((d1, d2) -> new DefaultArtifactVersion(d1.getVersion())
                        .compareTo(new DefaultArtifactVersion(d2.getVersion())))
                .collect(Collectors.toList());
        if (conflictingDependencies.isEmpty()) { // No conflicting deps
            return true;
        } else if (conflictingDependencies.get(0).equals(depToAdd)) { // Conflicting deps found and depToAdd is the latest version
            dependencies.removeAll(conflictingDependencies);
            return true;
        }
        return false; // Conflicting deps found and depToAdd is not the latest version
    }

    public List<DependencyLookup> getResult() {
        return result;
    }

}
