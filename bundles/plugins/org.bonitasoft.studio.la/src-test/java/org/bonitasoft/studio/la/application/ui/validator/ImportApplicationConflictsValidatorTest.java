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

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.bonitasoft.studio.assertions.StatusAssert;
import org.bonitasoft.studio.la.application.repository.ApplicationFileStore;
import org.bonitasoft.studio.la.application.repository.ApplicationRepositoryStore;
import org.bonitasoft.studio.la.application.ui.validator.ImportApplicationConflictsValidator;
import org.eclipse.core.runtime.IStatus;
import org.junit.Test;

public class ImportApplicationConflictsValidatorTest {

    @Test
    public void should_return_a_warning_status() throws Exception {
        final ImportApplicationConflictsValidator validator = new ImportApplicationConflictsValidator(
                appRepository());

        final IStatus status = validator.validate("myApp.xml");

        StatusAssert.assertThat(status).hasSeverity(IStatus.WARNING);
    }

    @Test
    public void should_return_a_valid_status() throws Exception {
        final ImportApplicationConflictsValidator validator = new ImportApplicationConflictsValidator(
                appRepository());

        final IStatus status = validator.validate("myApp2.xml");

        StatusAssert.assertThat(status).isOK();
    }

    private ApplicationRepositoryStore appRepository() {
        final ApplicationRepositoryStore repo = mock(ApplicationRepositoryStore.class);
        when(repo.getChild("myApp.xml")).thenReturn(mock(ApplicationFileStore.class));
        return repo;
    }

}
