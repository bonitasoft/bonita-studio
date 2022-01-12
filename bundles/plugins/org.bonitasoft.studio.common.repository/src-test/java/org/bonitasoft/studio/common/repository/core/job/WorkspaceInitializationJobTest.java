/**
 * Copyright (C) 2014 BonitaSoft S.A.
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
package org.bonitasoft.studio.common.repository.core.job;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.assertions.StatusAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;

/**
 * @author Romain Bioteau
 */
@RunWith(MockitoJUnitRunner.class)
public class WorkspaceInitializationJobTest {

    @Mock
    private WorkspaceInitializationJob workspaceInitializationJob;
    @Mock
    private BundleContext context;
    @Mock
    private Bundle bundle;
    @Mock
    private IProgressMonitor monitor;
    @Mock
    private RepositoryAccessor repositoryAccessor;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        when(workspaceInitializationJob.runInWorkspace(monitor)).thenCallRealMethod();
        when(workspaceInitializationJob.belongsTo(any(Object.class))).thenCallRealMethod();
        doCallRealMethod().when(workspaceInitializationJob).setRepositoryAccessor(any(RepositoryAccessor.class));
    }

    @Test
    public void should_belong_to_workspace_init_family() throws Exception {
        //Expect
        assertThat(workspaceInitializationJob.belongsTo(WorkspaceInitializationJob.WORKSPACE_INIT_FAMILY)).isTrue();
    }

    @Test
    public void should_trigger_repository_initialization() throws Exception {
        //Given
        workspaceInitializationJob.setRepositoryAccessor(repositoryAccessor);

        //When
        final IStatus status = workspaceInitializationJob.runInWorkspace(monitor);

        //Then
        verify(repositoryAccessor).start(monitor);
        assertThat(status).isOK();
    }
}
