/**
 * Copyright (C) 2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.common.repository.core;

import static org.bonitasoft.studio.common.NamingUtils.toJavaIdentifier;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.bonitasoft.engine.bpm.bar.BusinessArchive;
import org.bonitasoft.studio.common.repository.CommonRepositoryPlugin;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.pde.internal.core.util.ManifestUtils;
import org.osgi.framework.BundleReference;

public class ProjectManifestFactory {

    private static final String MANIFEST_MF_FILE_NAME = "MANIFEST.MF";
    private static final String META_INF_FOLDER_NAME = "META-INF";
    private static final String DEFAULT_SYMBOLIC_NAME = "org.bonitasoft.studio.myextensions";

    public void createProjectManifest(final IProject project, final IProgressMonitor monitor) throws CoreException {
        final IFolder metaInf = project.getFolder(META_INF_FOLDER_NAME);
        if (!metaInf.exists()) {
            metaInf.create(true, true, monitor);
        }
        final IFile projectManifest = project.getFolder(META_INF_FOLDER_NAME).getFile(MANIFEST_MF_FILE_NAME);
        if (!projectManifest.exists()) {
            writeNewManifestFile(project, monitor, projectManifest);
        }
    }

    protected void writeNewManifestFile(final IProject project, final IProgressMonitor monitor, final IFile projectManifest)
            throws CoreException {
        try (PrintWriter writer = new PrintWriter(projectManifest.getLocation().toFile())) {
            ManifestUtils.writeManifest(createManifestHeaders(project.getName()), writer);
            projectManifest.refreshLocal(IResource.DEPTH_ONE, monitor);
        } catch (final IOException e) {
            throw new CoreException(
                    new Status(IStatus.ERROR, CommonRepositoryPlugin.PLUGIN_ID, "Failed to create MANIFEST.MF", e));
        }
    }

    protected Map<String, String> createManifestHeaders(final String projectName) {
        final Map<String, String> headers = new HashMap<String, String>();
        headers.put(ManifestUtils.MANIFEST_VERSION, "1.0");
        headers.put(org.osgi.framework.Constants.BUNDLE_MANIFESTVERSION, "2");
        headers.put(org.osgi.framework.Constants.BUNDLE_NAME, projectName);
        String symbolicName = toJavaIdentifier(projectName, false);
        if (symbolicName == null || symbolicName.isEmpty() || !isValidBundleName(symbolicName)) {
            symbolicName = DEFAULT_SYMBOLIC_NAME;
        }
        headers.put(org.osgi.framework.Constants.BUNDLE_SYMBOLICNAME, symbolicName);
        headers.put("Automatic-Module-Name", symbolicName);
        headers.put(org.osgi.framework.Constants.BUNDLE_VERSION, "1.0.0.qualifier");
        headers.put(org.osgi.framework.Constants.BUNDLE_VENDOR, "Bonitasoft S.A.");
        headers.put(org.osgi.framework.Constants.BUNDLE_REQUIREDEXECUTIONENVIRONMENT, "JavaSE-1.8");
        headers.put(org.osgi.framework.Constants.REQUIRE_BUNDLE, engineBundleSymbolicName());
        
        return headers;
    }

    protected boolean isValidBundleName(String symbolicName) {
        return Pattern.matches("[a-zA-Z0-9\\-\\._]+", symbolicName);
    }

    protected String engineBundleSymbolicName() {
        final BundleReference cl = (BundleReference) BusinessArchive.class.getClassLoader();
        return cl.getBundle().getSymbolicName();
    }

}
