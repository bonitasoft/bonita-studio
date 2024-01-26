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
package org.bonitasoft.studio.application.maven.handler;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

import jakarta.inject.Named;

import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.application.maven.BonitaMavenConfigurationManager;
import org.bonitasoft.studio.application.maven.BonitaMavenConfigurationManager.Result;
import org.bonitasoft.studio.common.repository.core.maven.contribution.InstallBonitaMavenArtifactsOperation;
import org.bonitasoft.studio.common.repository.core.maven.repository.MavenRepositories;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;

public class TestMavenRepositoriesConnectionHandler {

    public static final String SHOW_RESULT_PARAM = "org.bonitasoft.studio.application.command.checkMavenConfiguration.showResult";
    public static final Object TEST_CONNECTION_FAMILY = new Object();
    @Execute
    public void execute(@Optional @Named(SHOW_RESULT_PARAM) String showResult) {
        var job = new CheckRemoteAcessJob();
        job.addJobChangeListener(new JobChangeAdapter() {

            @Override
            public void done(IJobChangeEvent event) {
                var result = event.getResult();
                if (result.isOK()) {
                    if (Boolean.parseBoolean(showResult)) {
                        Display.getDefault()
                                .asyncExec(() -> MessageDialog.openInformation(
                                        PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
                                        Messages.testConnectionSuccessTitle,
                                        Messages.testConnectionSuccessMsg));
                    }
                } else {
                    new Job(Messages.testingConnection) {

                        @Override
                        protected IStatus run(IProgressMonitor monitor) {
                            CheckRemoteAcessJob checkJob = (CheckRemoteAcessJob) event.getJob();
                            BonitaMavenConfigurationManager
                                    .openMavenConfigurationStatusDialog(checkJob.getCheckResult(), monitor);
                            return Status.OK_STATUS;
                        }
                    }.schedule();
                }
            }

        });
        job.schedule();
    }

    class CheckRemoteAcessJob extends Job {

        private Result result;

        public CheckRemoteAcessJob() {
            super(Messages.testingConnection);
        }

        @Override
        protected IStatus run(IProgressMonitor monitor) {
            new InstallBonitaMavenArtifactsOperation(MavenRepositories.internalRepository()).execute(monitor);
            try {
                result = BonitaMavenConfigurationManager.checkRemoteAccess(monitor);
                if (result.hasRemoteAccess()) {
                    return Status.OK_STATUS;
                }
                return Status.warning("Maven configuration check failed");
            } catch (InvocationTargetException | InterruptedException e) {
                return Status.error("Failed to check Maven configuration", e);
            }
        }
        
        @Override
        public boolean belongsTo(Object family) {
            return Objects.equals(family, TEST_CONNECTION_FAMILY);
        }

        public Result getCheckResult() {
            return result;
        }

    }
}
