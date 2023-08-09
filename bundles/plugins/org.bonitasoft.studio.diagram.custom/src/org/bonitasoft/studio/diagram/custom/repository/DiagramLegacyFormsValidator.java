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

import java.io.InputStream;
import java.nio.channels.Channels;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Scanner;

import org.bonitasoft.studio.common.emf.tools.EMFResourceUtil;
import org.bonitasoft.studio.diagram.custom.Activator;
import org.bonitasoft.studio.diagram.custom.i18n.Messages;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.emf.ecore.xmi.FeatureNotFoundException;

public class DiagramLegacyFormsValidator implements IValidator<InputStream> {

    @Override
    public IStatus validate(InputStream inputStream) {
        MultiStatus result = new MultiStatus(Activator.PLUGIN_ID, 0, null, null);
        try {
            if (hasLegacyFormMappingType(inputStream)) {
                return ValidationStatus.warning(Messages.containsLegacyFormsWarning);
            }
        } catch (FeatureNotFoundException e) {
            return result;
        }
        return result;
    }

    private static boolean hasLegacyFormMappingType(InputStream is) throws FeatureNotFoundException {
        String xmiType = "process:FormMapping";
        try (Scanner scanner = new Scanner(Channels.newChannel(is), StandardCharsets.UTF_8.name())) {
            String xmiTypePattern = EMFResourceUtil.toXMITypePattern(xmiType);
            String xsiTypePattern = EMFResourceUtil.toXSITypePattern(xmiType);
            String tagTypePattern = EMFResourceUtil.toTagTypePattern(xmiType);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.contains(xmiTypePattern) || line.contains(xsiTypePattern) || line.contains(tagTypePattern)) {
                    String formMappingType = EMFResourceUtil.getFeatureValue(line, xmiType, "type");
                    if (Objects.equals(formMappingType, "LEGACY")) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

}
