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
package org.bonitasoft.studio.engine.operation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bonitasoft.engine.bpm.bar.BusinessArchive;
import org.eclipse.core.databinding.validation.IValidator;

public class BusinessArchiveValidatorProvider {

    private static BusinessArchiveValidatorProvider INSTANCE;
    private List<IValidator<BusinessArchive>> validators = new ArrayList<>();

    public static BusinessArchiveValidatorProvider getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new BusinessArchiveValidatorProvider();
        }
        return INSTANCE;
    }

    private BusinessArchiveValidatorProvider() {

    }
    
    public void addValidator(IValidator<BusinessArchive> validator) {
        if(validator != null && !validators.contains(validator)) {
            validators.add(validator);
        }
    }

    public List<IValidator<BusinessArchive>> getValidators() {
        return Collections.unmodifiableList(validators);
    }
}
