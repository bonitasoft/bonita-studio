/**
 * Copyright (C) 2017 Bonitasoft S.A.
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
package org.bonitasoft.studio.la.ui.validator;

import java.util.List;
import java.util.stream.Collectors;

import org.bonitasoft.studio.common.jface.databinding.validator.UniqueValidator;
import org.bonitasoft.studio.common.jface.databinding.validator.UniqueValidatorFactory;
import org.bonitasoft.studio.la.repository.ApplicationFileStore;
import org.bonitasoft.studio.ui.validator.ValidatorBuilder;

public class ApplicationNameUnicityValidator implements ValidatorBuilder<UniqueValidator> {

    private List<String> applicationDescriptorNames;

    public ApplicationNameUnicityValidator withApplicationDescriptors(List<ApplicationFileStore> applicationsDescriptors) {
        applicationDescriptorNames = applicationsDescriptors.stream()
                .map(applicationDescriptorName -> applicationDescriptorName.getName().substring(0,
                        applicationDescriptorName.getName().length() - ".xml".length()))
                .collect(Collectors.toList());
        return this;
    }

    @Override
    public UniqueValidator create() {
        return UniqueValidatorFactory.uniqueValidator().in(applicationDescriptorNames).create();
    }

}
