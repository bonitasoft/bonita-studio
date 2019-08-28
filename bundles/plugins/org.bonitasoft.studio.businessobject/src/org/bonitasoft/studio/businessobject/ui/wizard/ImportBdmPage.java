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
package org.bonitasoft.studio.businessobject.ui.wizard;

import java.util.List;

import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.businessobject.ui.wizard.validator.ImportBdmConflictValidator;
import org.bonitasoft.studio.businessobject.ui.wizard.validator.ImportBdmContentValidator;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.ui.page.AbstractImportPage;
import org.eclipse.core.databinding.validation.IValidator;

public class ImportBdmPage extends AbstractImportPage {

    public static final String ZIP_EXTENSION = "*.zip";

    public ImportBdmPage(RepositoryAccessor repositoryAccessor) {
        super(repositoryAccessor);
    }

    @Override
    protected List<IValidator> getValidators() {
        List<IValidator> validators = super.getValidators();
        validators.add(new ImportBdmContentValidator());
        validators.add(new ImportBdmConflictValidator(
                repositoryAccessor.getRepositoryStore(BusinessObjectModelRepositoryStore.class)));
        return validators;
    }

    @Override
    protected String[] getExtensions() {
        return new String[] { ZIP_EXTENSION };
    }

}
