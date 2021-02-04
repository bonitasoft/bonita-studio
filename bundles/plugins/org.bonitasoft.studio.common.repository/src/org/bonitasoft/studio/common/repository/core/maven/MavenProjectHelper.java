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
import java.io.OutputStream;

import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.apache.maven.model.io.xpp3.MavenXpp3Writer;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

public class MavenProjectHelper {

    private MavenXpp3Reader pomReader = new MavenXpp3Reader();
    private MavenXpp3Writer pomWriter = new MavenXpp3Writer();

    public Model getMavenModel(IProject project) throws CoreException {
        var pomFile = project.getFile("pom.xml");
        if (!pomFile.exists()) {
            return null;
        }
        try {
            return pomReader.read(pomFile.getContents());
        } catch (IOException | XmlPullParserException e) {
            throw new CoreException(new Status(IStatus.ERROR, MavenModelOperation.class, null, e));
        }
    }

    public void saveModel(IProject project, Model model, IProgressMonitor monitor) throws CoreException {
        var pomFile = project.getFile("pom.xml");
        try (OutputStream stream = new FileOutputStream(pomFile.getLocation().toFile())) {
            pomWriter.write(stream, model);
        } catch (IOException e) {
            throw new CoreException(
                    new Status(IStatus.ERROR, getClass(), "Failed to write maven model in pom.xml file.", e));
        }
        pomFile.refreshLocal(IResource.DEPTH_ONE, monitor);
    }

}
