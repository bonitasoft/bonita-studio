/**
 * Copyright (C) 2018 Bonitasoft S.A.
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
package org.bonitasoft.studio.diagram.custom.repository;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.stream.Stream;

import org.bonitasoft.studio.common.ModelVersion;
import org.bonitasoft.studio.common.emf.tools.EMFResourceUtil;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.model.validator.ModelVersionValidator;
import org.bonitasoft.studio.diagram.custom.Activator;
import org.bonitasoft.studio.diagram.custom.i18n.Messages;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.emf.ecore.xmi.FeatureNotFoundException;

public class DiagramCompatibilityValidator implements IValidator<InputStream> {
    
    
    private String incompatibleModelMessage;

    public DiagramCompatibilityValidator(String incompatibleModelMessage) {
        this.incompatibleModelMessage = incompatibleModelMessage;
    }

    @Override
    public IStatus validate(InputStream inputStream) {
        Path tmpFile = null;
        MultiStatus result = new MultiStatus(Activator.PLUGIN_ID, 0, null, null);
        try {
            tmpFile = Files.createTempFile("tmpProc", "");
            Files.copy(inputStream, tmpFile, StandardCopyOption.REPLACE_EXISTING);
            EMFResourceUtil emfResourceUtil = new EMFResourceUtil(tmpFile.toFile());
            String mainProcessUUID = Stream.of(emfResourceUtil.getEObectIfFromEObjectType("process:MainProcess"))
                    .findFirst().orElse(null);
            if (mainProcessUUID == null) {
                return ValidationStatus.ok();
            }
            String bonitaModelVersion = emfResourceUtil.getFeatureValueFromEObjectId(mainProcessUUID,
                    "process:MainProcess",
                    "bonitaModelVersion");
            IStatus modelVersionStatus = new ModelVersionValidator(ModelVersion.CURRENT_DIAGRAM_VERSION,incompatibleModelMessage).validate(bonitaModelVersion);
            if (modelVersionStatus.getSeverity() == IStatus.ERROR) {
                return modelVersionStatus;
            } else {
                result.add(modelVersionStatus);
            }
            for (String uuid : emfResourceUtil.getEObectIfFromEObjectType("process:FormMapping")) {
                String formMappingType = emfResourceUtil.getFeatureValueFromEObjectId(uuid, "process:FormMapping",
                        "type");
                if (Objects.equals(formMappingType, "LEGACY")) {
                    result.add(ValidationStatus.warning(Messages.containsLegacyFormsWarning));
                }
            }
            return result;
        } catch (IOException | FeatureNotFoundException e) {
            BonitaStudioLog.error(e);
            return ValidationStatus.error("Failed to validate process compatibility", e);
        } finally {
            if (tmpFile != null) {
                try {
                    Files.deleteIfExists(tmpFile);
                } catch (IOException e) {
                    BonitaStudioLog.error(e);
                }
            }
        }
    }

}
