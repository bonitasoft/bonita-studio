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
package org.bonitasoft.studio.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Collections;

import org.eclipse.core.internal.jobs.JobListeners;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class BonitaStudioApplicationTest {

    private BonitaStudioApplication application;

    @BeforeEach
    void setUp() throws Exception {
        application = spy(new BonitaStudioApplication(Display.getDefault()));
        doReturn(null).when(application).initLocations(nullable(Shell.class), eq(Collections.EMPTY_MAP));
        doNothing().when(application).openErrorDialog(any(Display.class), anyString());
        doReturn(false).when(application).isWorkbenchRunning();
        doReturn(IApplication.EXIT_OK).when(application).createAndRunWorkbench(any());
        // do not dispose display for other tests
        doNothing().when(application).disposeDisplay(any(Display.class));
    }

    @AfterEach
    void tearDown() throws Exception {
        Job.getJobManager().removeJobChangeListener(application);
    }

    @Test
    void should_start_run_workbench_if_java_version_is_valid() throws Exception {
        doReturn("17").when(application).getJavaVersion();

        final Object result = application.start(null);

        verify(application).createAndRunWorkbench(Display.getDefault());
        assertThat(result).isEqualTo(IApplication.EXIT_OK);
    }

    @Test
    void exit_if_java_version_is_not_valid() throws Exception {
        doReturn("1.8").when(application).getJavaVersion();

        application.start(null);

        verify(application).openErrorDialog(Display.getDefault(), "1.8");
        verify(application, never()).createAndRunWorkbench(Display.getDefault());
    }

    @Test
    void add_auto_build_job_listener_that_cancel_autobuild_jobs_until_workbench_is_ready()
            throws Exception {
        doReturn("17").when(application).getJavaVersion();
        // increase job listener timeout to make sure the test executes correctly on low spec machines
        JobListeners.setJobListenerTimeout(10000);

        application.start(null);

        final Job job = new Job("auto build job") {

            @Override
            protected IStatus run(final IProgressMonitor monitor) {
                return Status.OK_STATUS;
            }

            @Override
            public boolean belongsTo(final Object family) {
                return ResourcesPlugin.FAMILY_AUTO_BUILD.equals(family);
            }
        };

        job.schedule();
        Job.getJobManager().join(ResourcesPlugin.FAMILY_AUTO_BUILD, null);

        assertThat(job.getResult()).isEqualTo(Status.CANCEL_STATUS);

        doReturn(true).when(application).isWorkbenchRunning();
        job.schedule();
        Job.getJobManager().join(ResourcesPlugin.FAMILY_AUTO_BUILD, null);

        assertThat(job.getResult()).isEqualTo(Status.OK_STATUS);

        verify(application, times(2)).cancelAutoBuildJobDuringStartup(any(IJobChangeEvent.class));
    }
}
