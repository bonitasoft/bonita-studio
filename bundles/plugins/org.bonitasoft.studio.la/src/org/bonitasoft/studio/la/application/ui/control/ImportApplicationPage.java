/**
 * Copyright (C) 2016 Bonitasoft S.A.
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
package org.bonitasoft.studio.la.application.ui.control;

import java.util.List;

import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.la.application.repository.ApplicationRepositoryStore;
import org.bonitasoft.studio.la.application.ui.validator.ApplicationXMLContentValidator;
import org.bonitasoft.studio.la.validator.ImportFileStoreConflictsValidator;
import org.bonitasoft.studio.ui.page.AbstractImportPage;
import org.eclipse.core.databinding.validation.IValidator;

public class ImportApplicationPage extends AbstractImportPage {

    public static final String XML_EXTENSION = "*.xml";

    public ImportApplicationPage(RepositoryAccessor repositoryAccessor) {
        super(repositoryAccessor);
    }

    @Override
    protected List<IValidator> getValidators() {
        List<IValidator> validators = super.getValidators();
        ApplicationRepositoryStore store = repositoryAccessor.getRepositoryStore(ApplicationRepositoryStore.class);
        validators.add(new ApplicationXMLContentValidator(store));
        validators.add(new ImportFileStoreConflictsValidator(store));
        return validators;
    }

    @Override
    protected String[] getExtensions() {
        return new String[] { XML_EXTENSION };
    }

}
