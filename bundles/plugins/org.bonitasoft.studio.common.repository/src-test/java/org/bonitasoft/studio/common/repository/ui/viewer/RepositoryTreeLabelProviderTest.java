/**
 * Copyright (C) 2014 Bonitasoft S.A.
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
package org.bonitasoft.studio.common.repository.ui.viewer;

import org.assertj.core.api.Assertions;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RepositoryTreeLabelProviderTest {

    @Mock
    IRepositoryStore<?> repoStore;

    @Mock
    IRepositoryFileStore<?> repoFileStore;

    @Test
    public void testGetTextForIRepositoryStore() {
        Mockito.doReturn("myName").when(repoStore).getDisplayName();
        Assertions.assertThat(new RepositoryTreeLabelProvider().getText(repoStore)).isEqualTo("myName");
    }

    @Test
    public void testGetTextForIRepositoryFileStore() {
        Mockito.doReturn("myName").when(repoFileStore).getDisplayName();
        Assertions.assertThat(new RepositoryTreeLabelProvider().getText(repoFileStore)).isEqualTo("myName");
    }

}
