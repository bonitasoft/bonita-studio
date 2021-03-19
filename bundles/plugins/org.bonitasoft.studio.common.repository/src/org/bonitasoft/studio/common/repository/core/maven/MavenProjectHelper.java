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

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.maven.artifact.repository.ArtifactRepository;
import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.apache.maven.model.io.xpp3.MavenXpp3Writer;
import org.apache.maven.project.MavenProject;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.e4.core.di.annotations.Creatable;
import org.eclipse.m2e.core.MavenPlugin;
import org.eclipse.m2e.core.internal.IMavenConstants;
import org.eclipse.m2e.core.project.IMavenProjectFacade;

@Creatable
public class MavenProjectHelper {

    private MavenXpp3Reader pomReader = new MavenXpp3Reader();
    private MavenXpp3Writer pomWriter = new MavenXpp3Writer();

    public Model getMavenModel(IProject project) throws CoreException {
        var pomFile = project.getFile(IMavenConstants.POM_FILE_NAME);
        if (!pomFile.exists()) {
            return null;
        }
        try(InputStream is = pomFile.getContents()) {
            return pomReader.read(is);
        } catch (IOException | XmlPullParserException e) {
            throw new CoreException(new Status(IStatus.ERROR, MavenModelOperation.class, null, e));
        }
    }

    public void saveModel(IProject project, Model model) throws CoreException {
        var pomFile = project.getFile(IMavenConstants.POM_FILE_NAME);
        try (OutputStream stream = new FileOutputStream(pomFile.getLocation().toFile())) {
            pomWriter.write(stream, model);
        } catch (IOException e) {
            throw new CoreException(
                    new Status(IStatus.ERROR, getClass(), "Failed to write maven model in pom.xml file.", e));
        }
        pomFile.refreshLocal(IResource.DEPTH_ONE, AbstractRepository.NULL_PROGRESS_MONITOR);
    }

    public List<ArtifactRepository> getProjectMavenRepositories(IProject project) throws CoreException {
        IMavenProjectFacade mavenFacade = getMavenProject(project);
        if (mavenFacade != null) {
            MavenProject mavenProject = mavenFacade.getMavenProject(AbstractRepository.NULL_PROGRESS_MONITOR);
            if (mavenProject != null) {
                return mavenProject.getRemoteArtifactRepositories();
            }
        }
        return Collections.emptyList();
    }

    private IMavenProjectFacade getMavenProject(IProject project) throws CoreException {
        return MavenPlugin.getMavenProjectRegistry().getProject(project);
    }

    public Optional<Dependency> findDependency(Model model, String groupId, String artifactId) {
        return model.getDependencies()
                .stream()
                .filter(dep -> Objects.equals(dep.getGroupId(), groupId))
                .filter(dep -> Objects.equals(dep.getArtifactId(), artifactId))
                .findFirst();
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

}
