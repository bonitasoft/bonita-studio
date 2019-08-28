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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

import org.bonitasoft.studio.actors.i18n.Messages;
import org.bonitasoft.studio.actors.repository.OrganizationFileStore;
import org.bonitasoft.studio.actors.repository.OrganizationRepositoryStore;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class OrganizationNameCellEditorValidatorTest {

    @Mock
    private OrganizationRepositoryStore orgaStore;
    @Mock
    private OrganizationFileStore orgaFileStore;
    @Spy
    private OrganizationNameCellEditorValidator validator;

    @Before
    public void setup() {
        doReturn(orgaStore).when(validator).getOrganizationStore();
    }

    @Test
    public void testValidOrganizationName() throws Exception {
        assertThat(validator.isValid("myorganization")).isNull();
    }

    @Test
    public void testValidOrganizationNameWithSpace() throws Exception {
        assertThat(validator.isValid("My organization with space")).isNull();
    }

    @Test
    public void testInvalidEmpty() throws Exception {
        assertThat(validator.isValid("")).isEqualTo(Messages.nameIsEmpty);
        assertThat(validator.isValid(null)).isEqualTo(Messages.nameIsEmpty);
    }

    @Test
    public void testInvalidFilenameCharacter() throws Exception {
        assertThat(validator.isValid("Myorga/")).isNotNull();
    }

    @Test
    public void testInvalidWhenorganiezationAlreadyExists() throws Exception {
        final String OrgaToTest = "Myorga";
        doReturn(orgaFileStore).when(orgaStore).getChild(OrgaToTest + "." + OrganizationRepositoryStore.ORGANIZATION_EXT);
        assertThat(validator.isValid(OrgaToTest)).isEqualTo(Messages.nameAlreadyExists);
    }

}
