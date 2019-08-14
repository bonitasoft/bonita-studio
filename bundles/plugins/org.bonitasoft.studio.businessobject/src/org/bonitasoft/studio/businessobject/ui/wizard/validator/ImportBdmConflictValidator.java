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
package org.bonitasoft.studio.businessobject.ui.wizard.validator;

import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelFileStore;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;

public class ImportBdmConflictValidator implements IValidator {

    private BusinessObjectModelRepositoryStore<BusinessObjectModelFileStore> repositoryStore;

    public ImportBdmConflictValidator(BusinessObjectModelRepositoryStore<BusinessObjectModelFileStore> repositoryStore) {
        this.repositoryStore = repositoryStore;
    }

    @Override
    public IStatus validate(Object value) {
        if (repositoryStore.getChild(BusinessObjectModelFileStore.BOM_FILENAME, true) != null) {
            return ValidationStatus.warning(Messages.bdmWillBeOverwritten);
        }
        return ValidationStatus.ok();
    }

}
