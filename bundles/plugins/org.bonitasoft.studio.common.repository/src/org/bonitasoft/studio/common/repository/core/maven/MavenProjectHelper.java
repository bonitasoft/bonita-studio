/**
 * Copyright (C) 2021 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.common.repository.core.maven;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.maven.artifact.repository.ArtifactRepository;
import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.jdom.MavenJDOMWriter;
import org.apache.maven.project.MavenProject;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.codehaus.plexus.util.WriterFactory;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.e4.core.di.annotations.Creatable;
import org.eclipse.m2e.core.MavenPlugin;
import org.eclipse.m2e.core.project.IMavenProjectFacade;
import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.Format.TextMode;

@Creatable
public class MavenProjectHelper {

    public static final String POM_FILE_NAME = "pom.xml";

    public static Model getMavenModel(IProject project) throws CoreException {
        var pomFile = project.getFile(POM_FILE_NAME);
        if (!pomFile.exists()) {
            return null;
        }
        return readModel(pomFile.getLocation().toFile());
    }

    public static Model readModel(File pomFile) throws CoreException {
        try (InputStream is = Files.newInputStream(pomFile.toPath())) {
            var maven = MavenPlugin.getMaven();
            if (maven == null) {
                throw new CoreException(Status.error("IMaven singleton is null"));
            }
            return maven.readModel(is);
        } catch (IOException e) {
            throw new CoreException(new Status(IStatus.ERROR, MavenModelOperation.class,
                    String.format("Failed to read %s", pomFile), e));
        }
    }

    public static void saveModel(IProject project, Model model, IProgressMonitor monitor) throws CoreException {
        var pomFile = project.getFile(POM_FILE_NAME);
        saveModel(pomFile, model, monitor);
    }

    public static void saveModel(IFile pomFile, Model model, IProgressMonitor monitor) throws CoreException {
        saveModel(pomFile, model, true, monitor);
    }
    
    public static void saveModel(IFile pomFile, Model model, boolean forceRefresh, IProgressMonitor monitor) throws CoreException {
        saveModel(pomFile.getLocation().toFile().toPath(), model);
        if(forceRefresh) {
            pomFile.refreshLocal(IResource.DEPTH_ONE, monitor);
        }
    }

    public static Model saveModel(Path pomFile, Model model) throws CoreException {
        try {
            if (Files.exists(pomFile)) {
                update(pomFile.toFile(), model);
            } else {
                try (var os = Files.newOutputStream(pomFile)) {
                    MavenPlugin.getMaven().writeModel(model, os);
                }
            }
            return model;
        } catch (IOException e) {
            throw new CoreException(
                    new Status(IStatus.ERROR, MavenProjectHelper.class, "Failed to write maven model in pom.xml file.",
                            e));
        }
    }

    public List<ArtifactRepository> getProjectMavenRepositories(IProject project) throws CoreException {
        IMavenProjectFacade mavenFacade = getMavenProjectFacade(project);
        if (mavenFacade != null) {
            MavenProject mavenProject = mavenFacade.getMavenProject(AbstractRepository.NULL_PROGRESS_MONITOR);
            if (mavenProject != null) {
                return mavenProject.getRemoteArtifactRepositories();
            }
        }
        return Collections.emptyList();
    }

    public static MavenProject getMavenProject(IProject project) throws CoreException {
        var mavenProjectFacade = getMavenProjectFacade(project);
        if (mavenProjectFacade != null) {
            var mavenProject = mavenProjectFacade.getMavenProject();
            if (mavenProject == null) {
                return mavenProjectFacade.getMavenProject(new NullProgressMonitor());
            }
            return mavenProject;
        }
        return null;
    }

    private static IMavenProjectFacade getMavenProjectFacade(IProject project) throws CoreException {
        var facade = MavenPlugin.getMavenProjectRegistry().getProject(project);
        if (facade == null) {
            facade = MavenPlugin.getMavenProjectRegistry().create(project.getFile(POM_FILE_NAME), true,
                    new NullProgressMonitor());
        }
        return facade;
    }

    public static Optional<Dependency> findDependency(Model model, String groupId, String artifactId) {
        return model.getDependencies()
                .stream()
                .filter(dep -> Objects.equals(dep.getGroupId(), groupId))
                .filter(dep -> Objects.equals(dep.getArtifactId(), artifactId))
                .findFirst();
    }

    public static void update(File pom, Model model) throws IOException, CoreException {
        if (!pom.exists()) {
            throw new FileNotFoundException(pom.getAbsolutePath());
        }
        var mavenModelJDOMWriter = new MavenJDOMWriter();
        var doc = readDocument(pom);
        try (var outputStream = Files.newOutputStream(pom.toPath());
                var writer = WriterFactory.newWriter(outputStream, StandardCharsets.UTF_8.name());) {
            if (doc != null) {
                mavenModelJDOMWriter.write(model,
                        doc,
                        writer,
                        Format.getRawFormat()
                                .setTextMode(TextMode.PRESERVE));
            } else {
                try {
                    mavenModelJDOMWriter.write(model, pom);
                } catch (IOException | JDOMException e) {
                    throw new CoreException(Status.error("Failed to update pom.xml", e));
                }
            }
        }
    }

    public static Document readDocument(File pomFile) throws IOException {
        SAXBuilder builder = new SAXBuilder();
        builder.setIgnoringBoundaryWhitespace(false);
        builder.setIgnoringElementContentWhitespace(false);
        try {
            return builder.build(pomFile);
        } catch (JDOMException e) {
            BonitaStudioLog.error(e);
            return null;
        }
    }

    public Optional<Dependency> findDependency(Model model, Dependency dependency) {
        return model.getDependencies()
                .stream()
                .filter(dep -> Objects.equals(dep.getGroupId(), dependency.getGroupId()))
                .filter(dep -> Objects.equals(dep.getArtifactId(), dependency.getArtifactId()))
                .filter(dep -> Objects.equals(dep.getVersion(), dependency.getVersion()))
                .filter(dep -> Objects.equals(dep.getType(), dependency.getType()))
                .filter(dep -> Objects.equals(dep.getClassifier(), dependency.getClassifier()))
                .findFirst();
    }

    /**
     * Look for a dependency in the maven model that has the same groupdId, artifactId, type and classifier than the
     * dependency in parameter.
     * Version can be different.
     */
    public Optional<Dependency> findDependencyInAnyVersion(Model mavenModel, Dependency dep) {
        return findDependencyInAnyVersion(mavenModel, dep.getGroupId(), dep.getArtifactId(), dep.getType(),
                dep.getClassifier());
    }

    /**
     * Look for a dependency in the maven model that has the same groupdId, artifactId, type and classifier than the
     * dependency in parameter.
     * Version can be different.
     */
    public Optional<Dependency> findDependencyInAnyVersion(Model mavenModel, String groupId, String artifactId,
            String type,
            String classifier) {
        return mavenModel.getDependencies().stream()
                .filter(aDep -> Objects.equals(aDep.getGroupId(), groupId))
                .filter(aDep -> Objects.equals(aDep.getArtifactId(), artifactId))
                .filter(aDep -> sameOptionalString(aDep.getType(), type))
                .filter(aDep -> sameOptionalString(aDep.getClassifier(), classifier))
                .findFirst();
    }

    private boolean sameOptionalString(String a, String b) {
        if (a != null && a.isBlank()) {
            a = null;
        }
        if (b != null && b.isBlank()) {
            b = null;
        }
        return Objects.equals(a, b);
    }

}
