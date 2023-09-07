/**
 * Copyright (C) 2022 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.common.repository.ui.internal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.bonitasoft.studio.common.repository.CommonRepositoryPlugin;
import org.bonitasoft.studio.common.repository.core.BonitaProject;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.bonitasoft.studio.common.ui.IDisplayable;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.core.runtime.Adapters;
import org.junit.jupiter.api.Test;

class DisplayableAdapterFactoryTest {

    @Test
    void should_adapt_repository_as_displayable() throws Exception {
        var repository = mock(IRepository.class); 
        when(repository.getProjectId()).thenReturn("my-project");
        when(repository.isShared()).thenReturn(true);
        
        var displayable = Adapters.adapt(repository, IDisplayable.class);
        
        assertThat(displayable).isNotNull();
        assertThat(displayable.getDisplayName()).isEqualTo("my-project");
        assertThat(displayable.getIcon()).isEqualTo(Pics.getImage("git.png", CommonRepositoryPlugin.getDefault()));
    }
    
}
