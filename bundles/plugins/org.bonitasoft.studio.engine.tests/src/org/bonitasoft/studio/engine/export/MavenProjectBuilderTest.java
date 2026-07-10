/**
 * Copyright (C) 2026 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.engine.export;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import org.bonitasoft.studio.common.repository.core.BonitaProject;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.junit.Before;
import org.junit.Test;

public class MavenProjectBuilderTest {

    private MavenProjectBuilder builder;
    private BonitaProject project;

    @Before
    public void setUp() {
        builder = spy(new MavenProjectBuilder());
        project = mock(BonitaProject.class);
    }

    @Test
    public void should_not_run_clean_install_when_incremental_install_succeeds() {
        doReturn(Status.OK_STATUS).when(builder).install(project, true);

        IStatus status = builder.installWithCleanFallback(project);

        assertThat(status.isOK()).isTrue();
        verify(builder, never()).cleanInstall(project);
    }

    @Test
    public void should_fall_back_to_clean_install_when_incremental_install_fails() {
        IStatus installFailure = Status.error("install failed");
        doReturn(installFailure).when(builder).install(project, true);
        doReturn(Status.OK_STATUS).when(builder).cleanInstall(project);

        IStatus status = builder.installWithCleanFallback(project);

        assertThat(status.isOK()).isTrue();
        verify(builder).cleanInstall(project);
    }

    @Test
    public void should_return_clean_install_failure_when_both_builds_fail() {
        IStatus cleanInstallFailure = Status.error("clean install failed");
        doReturn(Status.error("install failed")).when(builder).install(project, true);
        doReturn(cleanInstallFailure).when(builder).cleanInstall(project);

        IStatus status = builder.installWithCleanFallback(project);

        assertThat(status).isEqualTo(cleanInstallFailure);
    }

    @Test
    public void should_not_fall_back_to_clean_install_when_build_is_canceled() {
        doReturn(Status.CANCEL_STATUS).when(builder).install(project, true);

        IStatus status = builder.installWithCleanFallback(project);

        assertThat(status.getSeverity()).isEqualTo(IStatus.CANCEL);
        verify(builder, never()).cleanInstall(project);
    }

    @Test
    public void should_return_error_without_building_when_project_is_null() {
        IStatus status = builder.installWithCleanFallback((BonitaProject) null);

        assertThat(status.getSeverity()).isEqualTo(IStatus.ERROR);
        verify(builder, never()).install((BonitaProject) null, true);
        verify(builder, never()).cleanInstall((BonitaProject) null);
    }
}
