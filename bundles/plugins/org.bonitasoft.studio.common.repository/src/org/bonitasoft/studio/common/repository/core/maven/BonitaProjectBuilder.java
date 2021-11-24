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
package org.bonitasoft.studio.common.repository.core.maven;

import java.util.Map;
import java.util.Objects;

import org.apache.maven.model.Model;
import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.common.jface.databinding.StatusToMarkerSeverity;
import org.bonitasoft.studio.common.repository.BonitaProjectNature;
import org.bonitasoft.studio.common.repository.CommonRepositoryPlugin;
import org.bonitasoft.studio.common.repository.core.maven.model.ProjectDefaultConfiguration;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;

public class BonitaProjectBuilder extends IncrementalProjectBuilder {

    private static final String TARGET_RUNTIME_VERSION_MARKER_TYPE = CommonRepositoryPlugin.PLUGIN_ID
            + ".targetRuntimeVersionMarkerType";
    public static final String ID = CommonRepositoryPlugin.PLUGIN_ID + ".bonitaProjectValidationBuilder";

    private static final IValidator<String> RUNTIME_VERSION_VALIDATOR = new BonitaRuntimeVersionValidator();

    @Override
    protected IProject[] build(int kind, Map<String, String> args, IProgressMonitor monitor) throws CoreException {
        IProject project = getProject();
        if (project.hasNature(BonitaProjectNature.NATURE_ID)) {
            IResourceDelta delta = getDelta(project);
            IFile pomFile = project.getFile("pom.xml");
            if (delta != null) {
                delta.accept(new IResourceDeltaVisitor() {

                    @Override
                    public boolean visit(IResourceDelta delta) throws CoreException {
                        IResource resource = delta.getResource();
                        if (pomFile.equals(resource)) {
                            validateTargetRuntimeVersion(project);
                            return false;
                        }
                        return true;
                    }
                });
            } else if (kind == FULL_BUILD) {
                validateTargetRuntimeVersion(project);
            }
            return new IProject[] { project };
        }
        return new IProject[0];
    }

    private void validateTargetRuntimeVersion(IProject project) throws CoreException {
        project.deleteMarkers(TARGET_RUNTIME_VERSION_MARKER_TYPE, true, IResource.DEPTH_ONE);
        Model model = new MavenProjectHelper().getMavenModel(project);
        String runtimeVersion = model.getProperties().getProperty(ProjectDefaultConfiguration.BONITA_RUNTIME_VERSION);
        if (project.isAccessible() && !Objects.equals(runtimeVersion, ProductVersion.BONITA_RUNTIME_VERSION)) {
            IStatus status = RUNTIME_VERSION_VALIDATOR.validate(runtimeVersion);
            IMarker marker = project.createMarker(TARGET_RUNTIME_VERSION_MARKER_TYPE);
            marker.setAttribute(IMarker.SEVERITY, new StatusToMarkerSeverity(status).toMarkerSeverity());
            marker.setAttribute(IMarker.MESSAGE, status.getMessage());
        }
    }

    public static class BonitaRuntimeVersionValidator implements IValidator<String> {

        @Override
        public IStatus validate(String version) {
            if (!Objects.equals(version, ProductVersion.BONITA_RUNTIME_VERSION)) {
                if (!Objects.equals(ProductVersion.minorVersion(),
                        ProductVersion.toMinorVersionString(ProductVersion.minorVersion(version)))) {
                    return ValidationStatus.error(
                            String.format(org.bonitasoft.studio.common.repository.Messages.targetRuntimeVersionError,
                                    ProductVersion.BONITA_RUNTIME_VERSION));
                } else {
                    return ValidationStatus.warning(
                            String.format(org.bonitasoft.studio.common.repository.Messages.targetRuntimeVersionWarning,
                                    ProductVersion.BONITA_RUNTIME_VERSION));
                }

            }
            return ValidationStatus.ok();
        }

    }

}
