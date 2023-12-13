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

import org.apache.maven.project.MavenProject;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.maven.ExtensionProjectDescriptor;
import org.bonitasoft.studio.maven.i18n.Messages;
import org.bonitasoft.studio.rest.api.extension.RestAPIExtensionActivator;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.debug.core.model.IProcess;
import org.eclipse.debug.internal.ui.DebugUIPlugin;
import org.eclipse.debug.internal.ui.IInternalDebugUIConstants;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.launching.IJavaLaunchConfigurationConstants;
import org.eclipse.jdt.launching.JavaRuntime;
import org.eclipse.jface.dialogs.MessageDialogWithToggle;
import org.eclipse.m2e.actions.MavenLaunchConstants;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.actions.WorkspaceModifyOperation;

import com.google.common.base.Predicate;
public class BuildCustomPageOperation implements IWorkspaceRunnable {

    private IStatus status = Status.OK_STATUS;
    private final ExtensionProjectDescriptor fileStoreDescriptor;

    public BuildCustomPageOperation(final ExtensionProjectDescriptor fileStoreDescriptor) {
        this.fileStoreDescriptor = fileStoreDescriptor;
    }

    @Override
    public void run(final IProgressMonitor monitor) throws CoreException {
        final String taskName = NLS.bind(Messages.building,
                String.format("%s (%s)", fileStoreDescriptor.getArtifactId(), fileStoreDescriptor.getVersion()));
        monitor.subTask(taskName);
        var pref = DebugUIPlugin.getDefault().getPreferenceStore();
        var waitFor = pref.getString(IInternalDebugUIConstants.PREF_WAIT_FOR_BUILD);
        pref.setValue(IInternalDebugUIConstants.PREF_WAIT_FOR_BUILD, MessageDialogWithToggle.NEVER);
        ILaunchConfigurationWorkingCopy workingCopy = null;
        try {
            final ILaunchConfigurationType launchConfigurationType = launchManager()
                    .getLaunchConfigurationType(MavenLaunchConstants.LAUNCH_CONFIGURATION_TYPE_ID);
            workingCopy = launchConfigurationType.newInstance(null, taskName);
            configureLaunchConfiguration(workingCopy);
            final ILaunch launch = workingCopy.launch(ILaunchManager.RUN_MODE, new NullProgressMonitor(),
                    true);
            final IProcess process = launch.getProcesses()[0];
            final StringBuilder output = new StringBuilder();
            process.getStreamsProxy().getOutputStreamMonitor().addListener((text, monitor1) -> output.append(text));
            waitForBuildProcessTermination(launch);
            String statusMessage = process.getExitValue() == 0
                    ? String.format(Messages.customPageBuildSuccess, fileStoreDescriptor.getCustomPageName())
                    : String.format(Messages.customPageBuildFailure, fileStoreDescriptor.getCustomPageName());
            status = new Status(process.getExitValue() == 0 ? IStatus.OK : IStatus.ERROR,
                    RestAPIExtensionActivator.PLUGIN_ID, statusMessage);
        } finally {
            pref.setValue(IInternalDebugUIConstants.PREF_WAIT_FOR_BUILD, waitFor);
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

    ILaunchManager launchManager() {
        return  DebugPlugin.getDefault().getLaunchManager();
    }


    protected void waitForBuildProcessTermination(final ILaunch launch) {
        while (!launch.isTerminated()) {
            try {
                Thread.sleep(500);
            } catch (final InterruptedException e) {
            }
        }
    }

    protected void configureLaunchConfiguration(final ILaunchConfigurationWorkingCopy workingCopy)
            throws CoreException {
        var baseDir = fileStoreDescriptor.getMavenProject()
                .map(MavenProject::getBasedir)
                .orElseThrow(() -> new CoreException(new Status(IStatus.ERROR,
                        RestAPIExtensionActivator.PLUGIN_ID,
                        String.format("No maven project found for %s", fileStoreDescriptor.getName()))));
        workingCopy.setAttribute(MavenLaunchConstants.ATTR_POM_DIR, baseDir.getParentFile().getParentFile().getAbsolutePath());
        workingCopy.setAttribute(MavenLaunchConstants.ATTR_GOALS,  String.format("-am -pl extensions/%s clean verify", baseDir.getName()));
        workingCopy.setAttribute(ILaunchManager.ATTR_PRIVATE, true);
        workingCopy.setAttribute(MavenLaunchConstants.ATTR_BATCH, true);
        workingCopy.setAttribute(DebugPlugin.ATTR_CONSOLE_ENCODING, "UTF-8");
        final IPath path = getJREContainerPath(fileStoreDescriptor.getProject());
        if (path != null) {
            workingCopy.setAttribute(IJavaLaunchConfigurationConstants.ATTR_JRE_CONTAINER_PATH,
                    path.toPortableString());
        }
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

}
