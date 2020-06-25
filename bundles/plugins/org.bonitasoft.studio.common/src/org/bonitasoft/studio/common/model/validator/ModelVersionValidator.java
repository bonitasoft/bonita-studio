/**
 * Copyright (C) 2019 Bonitasoft S.A.
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
package org.bonitasoft.studio.common.model.validator;

import org.apache.maven.artifact.versioning.ArtifactVersion;
import org.apache.maven.artifact.versioning.DefaultArtifactVersion;
import org.bonitasoft.studio.common.Messages;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;

public class ModelVersionValidator implements IValidator<String> {

    private String currentModelVersion;
    private String incompatibilityErrorMessage;

    public ModelVersionValidator(String currentModelVersion, String incompatibilityErrorMessage) {
        this.incompatibilityErrorMessage = incompatibilityErrorMessage;
        this.currentModelVersion = currentModelVersion;
    }

    @Override
    public IStatus validate(String modelVersion) {
        ArtifactVersion currentVersion = new DefaultArtifactVersion(currentModelVersion);
        if(modelVersion == null) {
            return ValidationStatus.error(incompatibilityErrorMessage);
        }
        ArtifactVersion version = new DefaultArtifactVersion(modelVersion);
        switch (currentVersion.compareTo(version)) {
            case 0:
                return ValidationStatus.ok();
            case 1:
                return ValidationStatus.warning(Messages.migrationWillBreakRetroCompatibility);
            case -1:
            default:
                return ValidationStatus.error(incompatibilityErrorMessage);
        }
    }

}
