/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.maven.operation;

import static com.google.common.collect.Iterables.find;
import static com.google.common.collect.Lists.newArrayList;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.maven.project.MavenProject;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.maven.CustomPageMavenProjectDescriptor;
import org.bonitasoft.studio.maven.i18n.Messages;
import org.bonitasoft.studio.rest.api.extension.RestAPIExtensionActivator;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.debug.core.IStreamListener;
import org.eclipse.debug.core.model.IProcess;
import org.eclipse.debug.core.model.IStreamMonitor;
import org.eclipse.debug.ui.IDebugUIConstants;
import org.eclipse.debug.ui.RefreshTab;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.launching.IJavaLaunchConfigurationConstants;
import org.eclipse.jdt.launching.JavaRuntime;
import org.eclipse.m2e.actions.MavenLaunchConstants;
import org.eclipse.m2e.core.MavenPlugin;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.actions.WorkspaceModifyOperation;

import com.google.common.base.Predicate;

public class BuildCustomPageOperation implements IWorkspaceRunnable {

    private final ILaunchManager launchManager;
    private IStatus status = Status.OK_STATUS;
    private final CustomPageMavenProjectDescriptor fileStoreDescriptor;
    private String mavenGoals;
    private boolean logToSysout = false;
    private final Set<String> activatedProfiles = new HashSet<>();

    public BuildCustomPageOperation(final CustomPageMavenProjectDescriptor fileStoreDescriptor,
            final ILaunchManager launchManager) {
        this(fileStoreDescriptor, launchManager, "clean verify");
    }

    public BuildCustomPageOperation(final CustomPageMavenProjectDescriptor fileStoreDescriptor,
            final ILaunchManager launchManager, final String mavenGoals) {
        this.fileStoreDescriptor = fileStoreDescriptor;
        this.launchManager = launchManager;
        this.mavenGoals = mavenGoals;
    }

    @Override
    public void run(final IProgressMonitor monitor) throws CoreException {
        final String taskName = NLS.bind(Messages.building,
                String.format("%s (%s)", fileStoreDescriptor.getArtifactId(), fileStoreDescriptor.getVersion()));
        monitor.subTask(taskName);
        final ILaunchConfigurationType launchConfigurationType = launchManager
                .getLaunchConfigurationType(MavenLaunchConstants.LAUNCH_CONFIGURATION_TYPE_ID);
        ILaunchConfigurationWorkingCopy workingCopy = null;
        try {
            workingCopy = launchConfigurationType.newInstance(null, taskName);
            configureLaunchConfiguration(workingCopy);
            final ILaunch launch = workingCopy.launch(ILaunchManager.RUN_MODE, AbstractRepository.NULL_PROGRESS_MONITOR,
                    true);
            final IProcess process = launch.getProcesses()[0];
            final StringBuilder output = new StringBuilder();
            process.getStreamsProxy().getOutputStreamMonitor().addListener(new IStreamListener() {

                @Override
                public void streamAppended(final String text, final IStreamMonitor monitor) {
                    if (logToSysout) {
                        System.out.print(text);
                    }
                    output.append(text);
                }
            });
            waitForBuildProcessTermination(launch);
            String statusMessage = process.getExitValue() == 0
                    ? String.format(Messages.customPageBuildSuccess, fileStoreDescriptor.getCustomPageName())
                    : String.format(Messages.customPageBuildFailure, fileStoreDescriptor.getCustomPageName());
            status = new Status(process.getExitValue() == 0 ? IStatus.OK : IStatus.ERROR,
                    RestAPIExtensionActivator.PLUGIN_ID, statusMessage);
        } finally {
            if (workingCopy != null) {
                try {
                    workingCopy.delete();
                } catch (final CoreException e) {
                    status = new Status(IStatus.ERROR, RestAPIExtensionActivator.PLUGIN_ID,
                            "Failed to delete temporary launch configuration", e);
                    throw new CoreException(status);
                }
            }
        }
    }

    protected void waitForBuildProcessTermination(final ILaunch launch) {
        while (!launch.isTerminated()) {
            try {
                Thread.sleep(1000);
            } catch (final InterruptedException e) {
            }
        }
    }

    protected void configureLaunchConfiguration(final ILaunchConfigurationWorkingCopy workingCopy)
            throws CoreException {
        workingCopy.setAttribute(MavenLaunchConstants.ATTR_POM_DIR,
                fileStoreDescriptor.getMavenProject()
                        .map(MavenProject::getBasedir)
                        .map(File::getAbsolutePath)
                        .orElseThrow(() -> new CoreException(new Status(IStatus.ERROR,
                                RestAPIExtensionActivator.PLUGIN_ID,
                                String.format("No maven project found for %s", fileStoreDescriptor.getName())))));
        workingCopy.setAttribute(MavenLaunchConstants.ATTR_GOALS, mavenGoals);
        workingCopy.setAttribute(MavenLaunchConstants.ATTR_PROFILES,
                activeProfiles());
        workingCopy.setAttribute(IDebugUIConstants.ATTR_PRIVATE, true);
        workingCopy.setAttribute(RefreshTab.ATTR_REFRESH_SCOPE, "${project}");
        workingCopy.setAttribute(RefreshTab.ATTR_REFRESH_RECURSIVE, true);
        workingCopy.setAttribute(DebugPlugin.ATTR_CAPTURE_OUTPUT, true);
        workingCopy.setAttribute(DebugPlugin.ATTR_CONSOLE_ENCODING, "UTF-8");
        final IPath path = getJREContainerPath(fileStoreDescriptor.getProject());
        if (path != null) {
            workingCopy.setAttribute(IJavaLaunchConfigurationConstants.ATTR_JRE_CONTAINER_PATH,
                    path.toPortableString());
        }
    }

    protected String activeProfiles() {
        final List<String> activeProfileList = MavenPlugin.getProjectConfigurationManager()
                .getResolverConfiguration(fileStoreDescriptor.getProject()).getActiveProfileList();
        return Stream.concat(activeProfileList.stream(), activatedProfiles.stream()).distinct()
                .collect(Collectors.joining(","));
    }

    private IPath getJREContainerPath(final IProject project) throws CoreException {
        if (project != null && project.hasNature(JavaCore.NATURE_ID)) {
            final IJavaProject javaProject = JavaCore.create(project);
            final IClasspathEntry entry = find(newArrayList(javaProject.getRawClasspath()),
                    new Predicate<IClasspathEntry>() {

                        @Override
                        public boolean apply(final IClasspathEntry entry) {
                            return JavaRuntime.JRE_CONTAINER.equals(entry.getPath().segment(0));
                        }
                    }, null);
            return entry != null ? entry.getPath() : null;
        }
        return null;
    }

    public IStatus getStatus() {
        return status;
    }

    public String getArchiveName() throws CoreException {
        final MavenProject mavenProject = fileStoreDescriptor.getMavenProject()
                .orElseThrow(() -> new CoreException(new Status(IStatus.ERROR,
                        RestAPIExtensionActivator.PLUGIN_ID,
                        String.format("No maven project found for %s", fileStoreDescriptor.getName()))));
        return mavenProject.getArtifactId() + "-" + mavenProject.getVersion() + ".zip";
    }

    public InputStream getArchiveContent() throws CoreException, FileNotFoundException {
        final MavenProject mavenProject = fileStoreDescriptor.getMavenProject()
                .orElseThrow(() -> new CoreException(new Status(IStatus.ERROR,
                        RestAPIExtensionActivator.PLUGIN_ID,
                        String.format("No maven project found for %s", fileStoreDescriptor.getName()))));
        final File targetDir = new File(mavenProject.getBasedir(), "target");
        return new FileInputStream(new File(targetDir, getArchiveName()));
    }

    public WorkspaceModifyOperation asWorkspaceModifyOperation() {
        return new WorkspaceModifyOperation() {

            @Override
            protected void execute(final IProgressMonitor monitor)
                    throws CoreException, InvocationTargetException, InterruptedException {
                BuildCustomPageOperation.this.run(monitor);
            }
        };
    }

    public void setLogToSysout(final boolean logToSysout) {
        this.logToSysout = logToSysout;
    }

    public void setGoals(String mavenGoals) {
        this.mavenGoals = mavenGoals;
    }

    public void activateProfile(String profileId) {
        this.activatedProfiles.add(profileId);
    }

}
