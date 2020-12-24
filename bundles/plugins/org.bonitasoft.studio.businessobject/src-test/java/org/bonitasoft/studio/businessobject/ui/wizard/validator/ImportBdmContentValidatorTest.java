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

import java.io.File;

import org.bonitasoft.studio.assertions.StatusAssert;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.eclipse.core.runtime.IStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ImportBdmContentValidatorTest {

    private File archive;
    @Mock
    private BusinessObjectModelRepositoryStore<?> store;

    @Before
    public void init() throws Exception {
        archive = new File(ImportBdmContentValidatorTest.class.getResource("/notAbdm.zip").toURI());
    }

    @Test
    public void should_validate_archive_content() {
        IStatus status = new ImportBdmContentValidator(store).validate(archive.getAbsolutePath());
        StatusAssert.assertThat(status).isError();
        assertThat(status.getMessage()).isEqualTo(String.format(Messages.bdmZipInvalid, archive.getName()));
    }

}
