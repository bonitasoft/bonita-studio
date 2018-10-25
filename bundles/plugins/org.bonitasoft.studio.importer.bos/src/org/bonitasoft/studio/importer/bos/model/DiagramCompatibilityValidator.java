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
package org.bonitasoft.studio.importer.bos.model;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.Optional;

import org.bonitasoft.studio.common.ConfigurationIdProvider;
import org.bonitasoft.studio.common.emf.tools.EMFResourceUtil;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.importer.bos.i18n.Messages;
import org.bonitasoft.studio.ui.validator.TypedValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.FeatureNotFoundException;


public class DiagramCompatibilityValidator extends TypedValidator<InputStream, IStatus> {

    @Override
    protected IStatus doValidate(Optional<InputStream> value) {
        if (value.isPresent()) {
            Path tmpFile = null;
            try {
                tmpFile = Files.createTempFile("tmpProc", "");
                Files.copy(value.get(), tmpFile, StandardCopyOption.REPLACE_EXISTING);
                EMFResourceUtil emfResourceUtil = new EMFResourceUtil(tmpFile.toFile());
                for (String uuid : emfResourceUtil.getEObectIfFromEObjectType("process:MainProcess")) {
                    String configId = emfResourceUtil.getFeatureValueFromEObjectId(uuid, "process:MainProcess", "configId");
                    String name = emfResourceUtil.getFeatureValueFromEObjectId(uuid, "process:MainProcess", "name");
                    String bonitaModelVersion = emfResourceUtil.getFeatureValueFromEObjectId(uuid, "process:MainProcess",
                            "bonitaModelVersion");
                    String bonitaVersion = emfResourceUtil.getFeatureValueFromEObjectId(uuid, "process:MainProcess",
                            "bonitaVersion");
                    if (!ConfigurationIdProvider.getBosConfigurationIdProvider().isConfigurationIdValid(
                            EcoreUtil.createFromString(EcorePackage.Literals.EJAVA_OBJECT, configId), name,
                            bonitaModelVersion, bonitaVersion)) {
                        return ValidationStatus.error(Messages.spDiagramCannotBeImportedError);
                    }
                }
                for (String uuid : emfResourceUtil.getEObectIfFromEObjectType("process:FormMapping")) {
                    String formMappingType = emfResourceUtil.getFeatureValueFromEObjectId(uuid, "process:FormMapping",
                            "type");
                    if (Objects.equals(formMappingType, "LEGACY")) {
                        return ValidationStatus.warning(Messages.containsLegacyFormsWarning);
                    }
                }
            } catch (IOException | FeatureNotFoundException e) {
                BonitaStudioLog.error(e);
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
        return ValidationStatus.ok();
    }

}
