/*******************************************************************************
 * Copyright (C) 2019 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.rest.api.extension.core.builder.resolution;

import org.bonitasoft.studio.maven.builder.validator.PomFileValidator;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.m2e.core.internal.IMavenConstants;
import org.eclipse.ui.IMarkerResolution;
import org.eclipse.ui.IMarkerResolutionGenerator;

public class PomDependencyResolutionGenerator implements IMarkerResolutionGenerator {

    @Override
    public IMarkerResolution[] getResolutions(IMarker marker) {
        if (marker.getResource().getProjectRelativePath().equals(Path.fromOSString(IMavenConstants.POM_FILE_NAME))) {
            try {
                String version = (String) marker.getAttribute(PomFileValidator.VERSION_MIN_PROPERTY);
                return new IMarkerResolution[] { new RestAPIDependencyToUpdateMarkerResolution(version) };
            } catch (CoreException e) {
                throw new RuntimeException("An error occured while attempting to create quick fix", e);
            }
        }
        return new IMarkerResolution[] {};
    }

}
