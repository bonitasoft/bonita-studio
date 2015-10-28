/**
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.actors.ui.editingsupport;

import org.bonitasoft.studio.actors.i18n.Messages;
import org.bonitasoft.studio.actors.repository.OrganizationRepositoryStore;
import org.bonitasoft.studio.common.jface.databinding.validator.FileNameValidator;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.viewers.ICellEditorValidator;

class OrganizationNameCellEditorValidator implements ICellEditorValidator {

    @Override
    public String isValid(final Object input) {
        //Check Emptiness
        if (input == null || input.toString().isEmpty()) {
    		return Messages.nameIsEmpty ;
    	}

        final String toValidate = input.toString();
        //Check valid File name
        final FileNameValidator fileNameValidator = new FileNameValidator(Messages.organizationName);
        final IStatus fileNameValidationStatus = fileNameValidator.validate(toValidate);
        if (!fileNameValidationStatus.isOK()) {
            return fileNameValidationStatus.getMessage();
        }
        //Check unicity
        final OrganizationRepositoryStore store = getOrganizationStore();
        if (store.getChild(toValidate + "." + OrganizationRepositoryStore.ORGANIZATION_EXT) != null) {
    		return Messages.nameAlreadyExists ;
    	}
    	return null;
    }

    protected OrganizationRepositoryStore getOrganizationStore() {
        return RepositoryManager.getInstance().getRepositoryStore(OrganizationRepositoryStore.class);
    }
}