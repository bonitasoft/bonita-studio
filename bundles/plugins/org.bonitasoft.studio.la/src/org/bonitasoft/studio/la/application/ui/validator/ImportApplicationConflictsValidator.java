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
package org.bonitasoft.studio.la.application.ui.validator;

import java.io.File;
import java.util.Optional;

import org.bonitasoft.studio.la.application.repository.ApplicationRepositoryStore;
import org.bonitasoft.studio.la.i18n.Messages;
import org.bonitasoft.studio.ui.validator.TypedValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;

public class ImportApplicationConflictsValidator extends TypedValidator<String, IStatus> {

    private final ApplicationRepositoryStore applicationRepositoryStore;

    public ImportApplicationConflictsValidator(ApplicationRepositoryStore applicationRepositoryStore) {
        this.applicationRepositoryStore = applicationRepositoryStore;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.ui.validator.TypedValidator#doValidate(java.util.Optional)
     */
    @Override
    protected IStatus doValidate(Optional<String> value) {
        if (value.isPresent()) {
            final File file = new File(value.get());
            return applicationRepositoryStore.getChild(file.getName()) != null
                    ? ValidationStatus.warning(Messages.importConflictWarning)
                    : ValidationStatus.ok();
        }
        return ValidationStatus.ok();
    }

}
