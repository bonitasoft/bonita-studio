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
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.bonitasoft.studio.swt.rules.RealmWithDisplay;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.swt.widgets.Display;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author Romain Bioteau
 */
@RunWith(MockitoJUnitRunner.class)
public class BonitaStudioApplicationTest {

    private BonitaStudioApplication application;

    @Rule
    public RealmWithDisplay realm = new RealmWithDisplay();

    @Before
    public void setUp() throws Exception {
        application = spy(new BonitaStudioApplication(realm.getShell().getDisplay()));
        doNothing().when(application).initWorkspaceLocation();
        doNothing().when(application).openErrorDialog(eq(realm.getShell().getDisplay()), anyString());
        doReturn(false).when(application).isWorkbenchRunning();
        doReturn(IApplication.EXIT_OK).when(application).createAndRunWorkbench(any(Display.class));
    }

    @After
    public void tearDown() throws Exception {
        Job.getJobManager().removeJobChangeListener(application);
    }

    @Test
    public void shoul_start_run_workbench_if_java_version_is_valid() throws Exception {
        doReturn("11").when(application).getJavaVersion();

        final Object result = application.start(null);

        verify(application).createAndRunWorkbench(realm.getShell().getDisplay());
        assertThat(result).isEqualTo(IApplication.EXIT_OK);
    }

    @Test
    public void shoul_start_exit_if_java_version_is_not_valid() throws Exception {
        doReturn("1.8").when(application).getJavaVersion();

        application.start(null);

        verify(application).openErrorDialog(realm.getShell().getDisplay(), "1.8");
        verify(application, never()).createAndRunWorkbench(realm.getShell().getDisplay());
    }

    @Test
    public void should_start_add_auto_build_job_listener_that_cancel_autobuild_jobs_until_workbench_is_ready()
            throws Exception {
        doReturn("11").when(application).getJavaVersion();

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

        assertThat(job.getResult()).isNull();

        doReturn(true).when(application).isWorkbenchRunning();
        job.schedule();
        Job.getJobManager().join(ResourcesPlugin.FAMILY_AUTO_BUILD, null);

        assertThat(job.getResult()).isEqualTo(Status.OK_STATUS);

        verify(application, times(2)).cancelAutoBuildJobDuringStartup(any(IJobChangeEvent.class));
    }

}
