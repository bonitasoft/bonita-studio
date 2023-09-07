/**
 * Copyright (C) 2014 Bonitasoft S.A.
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
package org.bonitasoft.studio.common.repository.ui.viewer;

import org.assertj.core.api.Assertions;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.common.ui.IDisplayable;
import org.eclipse.core.runtime.IAdaptable;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RepositoryTreeLabelProviderTest {

    public static interface AdaptableRepositoryStore extends IRepositoryStore, IAdaptable {
    }

    public static interface AdaptableRepositoryFileStore extends IRepositoryFileStore, IAdaptable {
    }

    @Mock
    AdaptableRepositoryStore repoStore;

    @Mock
    AdaptableRepositoryFileStore repoFileStore;

    @Test
    void testGetTextForIRepositoryStore() {
        IDisplayable displayable = () -> "myName";
        Mockito.doReturn(displayable).when(repoStore).getAdapter(IDisplayable.class);
        Assertions.assertThat(new RepositoryTreeLabelProvider().getText(repoStore)).isEqualTo("myName");
    }

    @Test
    void testGetTextForIRepositoryFileStore() {
        IDisplayable displayable = () -> "myName";
        Mockito.doReturn(displayable).when(repoFileStore).getAdapter(IDisplayable.class);
        Assertions.assertThat(new RepositoryTreeLabelProvider().getText(repoFileStore)).isEqualTo("myName");
    }

}
