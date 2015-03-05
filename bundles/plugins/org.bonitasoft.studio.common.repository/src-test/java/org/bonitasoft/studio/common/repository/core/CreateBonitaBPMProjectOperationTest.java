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
package org.bonitasoft.studio.common.repository.core;

import static org.assertj.core.api.Assertions.assertThat;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.runtime.IProgressMonitor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author Romain Bioteau
 */
@RunWith(MockitoJUnitRunner.class)
public class CreateBonitaBPMProjectOperationTest {

    private CreateBonitaBPMProjectOperation createBonitaBPMProjectOperation;
    @Mock
    private IProgressMonitor monitor;

    @Mock
    private IWorkspace workspace;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        createBonitaBPMProjectOperation = new CreateBonitaBPMProjectOperation(workspace, "my project");
    }

    @Test
    public void should_create_a_new_eclipse_project() throws Exception {
        createBonitaBPMProjectOperation.run(monitor);

        final IProject project = createBonitaBPMProjectOperation.getProject();
        assertThat(project).isNotNull();
        assertThat(project.exists()).isTrue();
    }
}
