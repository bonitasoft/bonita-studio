/**
 * Copyright (C) 2019 Bonitasoft S.A.
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
package org.bonitasoft.studio.businessobject;

import javax.inject.Inject;

import org.bonitasoft.studio.businessobject.core.operation.DeployBDMJob;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelFileStore;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.common.jface.MessageDialogWithPrompt;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.di.extensions.EventTopic;
import org.eclipse.e4.ui.workbench.UIEvents;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.osgi.service.event.Event;

public class StartAddon {

    @Inject
    @Optional
    public void applicationStarted(
            @EventTopic(UIEvents.UILifeCycle.APP_STARTUP_COMPLETE) Event event) {

        Job.getJobManager().addJobChangeListener(new JobChangeAdapter() {

            @Override
            public void done(IJobChangeEvent event) {
                Job job = event.getJob();
                if (job instanceof DeployBDMJob) {
                    IStatus status = ((DeployBDMJob) job).getStatus();
                    if (status.isOK()) {
                        IPreferenceStore preferenceStore = BusinessObjectPlugin.getDefault().getPreferenceStore();
                        if (!preferenceStore.getBoolean(BusinessObjectModelFileStore.DO_NOT_SHOW_INSTALL_MESSAGE_DIALOG)) {
                            Display.getDefault().syncExec(() -> {
                                MessageDialogWithPrompt.openWithDetails(MessageDialog.INFORMATION,
                                        Display.getDefault().getActiveShell(),
                                        Messages.bdmDeployedTitle,
                                        Messages.bdmDeployedMessage,
                                        Messages.doNotShowMeAgain,
                                        Messages.bdmDeployDetails,
                                        false,
                                        preferenceStore,
                                        BusinessObjectModelFileStore.DO_NOT_SHOW_INSTALL_MESSAGE_DIALOG,
                                        SWT.NONE);
                            });
                        }
                    }
                }
            }
        });
    }
}
