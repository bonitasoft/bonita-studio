/**
 * Copyright (C) 2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.engine;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

import org.bonitasoft.studio.common.repository.model.IRepository;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.junit.Test;

public class BOSEngineManagerTest {

    @Test
    public void should_not_flag_engine_running_state_to_true_if_post_start_fails() throws Exception {
        final BOSEngineManager engineManager = spy(new BOSEngineManager(new NullProgressMonitor()));
        doReturn(false).when(engineManager).tomcatServerIsRunning();
        doThrow(Exception.class).when(engineManager).getLoginAPI();
        IRepository repository = mock(IRepository.class);
        
        final boolean isRunning = engineManager.postEngineStart(repository);

        assertThat(isRunning).isFalse();
    }

}
