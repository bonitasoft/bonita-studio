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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.bonitasoft.studio.assertions.StatusAssert;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelFileStore;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.eclipse.core.runtime.IStatus;
import org.junit.Test;

public class ImportBdmConflictValidatorTest {

    @Test
    public void should_validate_bdm_conflicts() {
        BusinessObjectModelRepositoryStore<BusinessObjectModelFileStore> repositoryStore = mock(
                BusinessObjectModelRepositoryStore.class);
        BusinessObjectModelFileStore fileStore = mock(BusinessObjectModelFileStore.class);
        when(repositoryStore.getChild(BusinessObjectModelFileStore.BOM_FILENAME)).thenReturn(fileStore);

        IStatus status = new ImportBdmConflictValidator(repositoryStore).validate("myFile.zip");
        StatusAssert.assertThat(status).isWarning();
        assertThat(status.getMessage()).isEqualTo(Messages.bdmWillBeOverwritten);

        when(repositoryStore.getChild(BusinessObjectModelFileStore.BOM_FILENAME)).thenReturn(null);
        status = new ImportBdmConflictValidator(repositoryStore).validate("myFile.zip");
        StatusAssert.assertThat(status).isOK();
    }

}
