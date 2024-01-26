
package org.bonitasoft.studio.team.git;

import java.lang.reflect.Field;
import java.util.Objects;

import jakarta.inject.Inject;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.di.extensions.EventTopic;
import org.eclipse.e4.ui.workbench.UIEvents;
import org.eclipse.egit.ui.internal.push.PushJob;
import org.eclipse.jsch.internal.core.JSchCorePlugin;
import org.osgi.service.event.Event;

public class StartAddon {

    @Inject
    @Optional
    public void applicationStarted(
            @EventTopic(UIEvents.UILifeCycle.APP_STARTUP_COMPLETE) Event event) {
        // Force jsch.core plugin startup
        JSchCorePlugin.getPlugin();
        Job.getJobManager().addJobChangeListener(new JobChangeAdapter() {

            @Override
            public void scheduled(IJobChangeEvent event) {
                Job job = event.getJob();
                if (job instanceof PushJob) {
                    try {
                        Field declaredField = PushJob.class.getDeclaredField("showConfigureButton");
                        declaredField.setAccessible(true);
                        declaredField.set(job, false);
                    } catch (NoSuchFieldException | SecurityException | IllegalArgumentException
                            | IllegalAccessException e) {
                        BonitaStudioLog.error(e);
                    }
                }
                if (Objects.equals(job.getName(), "Git Clone DND Initialization")) {
                    job.cancel();
                }
            }

        });
    }

}
