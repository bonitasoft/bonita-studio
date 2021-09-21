/*******************************************************************************
 * Copyright (C) 2018 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.team.ui.handler;

import java.io.File;
import java.io.IOException;

import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.common.NotDefinedException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.e4.core.services.statusreporter.StatusReporter;
import org.eclipse.jface.util.Util;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.internal.ide.IDEInternalPreferences;
import org.eclipse.ui.internal.ide.IDEWorkbenchMessages;
import org.eclipse.ui.internal.ide.IDEWorkbenchPlugin;

/**
 * duplicate of ShowInSystemExplorerHandler -> to complicated to ensure that the project will be opened in all the situation
 * using the command.
 */
@SuppressWarnings("restriction")
public class ShowRepositoryInSystemExplorerHandler extends AbstractHandler {

    private static final String VARIABLE_RESOURCE = "${selected_resource_loc}";
    private static final String VARIABLE_RESOURCE_URI = "${selected_resource_uri}";
    private static final String VARIABLE_FOLDER = "${selected_resource_parent_loc}";

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {

        IProject project = RepositoryManager.getInstance().getCurrentRepository().getProject();

        final StatusReporter statusReporter = HandlerUtil.getActiveWorkbenchWindow(event).getService(
                StatusReporter.class);

        Job job = Job.create(IDEWorkbenchMessages.ShowInSystemExplorerHandler_jobTitle, monitor -> {
            String logMsgPrefix;
            try {
                logMsgPrefix = event.getCommand().getName() + ": ";
            } catch (NotDefinedException e1) {
                logMsgPrefix = event.getCommand().getId() + ": ";
            }

            try {
                File canonicalPath = getSystemExplorerPath(project);
                if (canonicalPath == null) {
                    return statusReporter.newStatus(IStatus.ERROR, logMsgPrefix
                            + IDEWorkbenchMessages.ShowInSystemExplorerHandler_notDetermineLocation, null);
                }
                String launchCmd = formShowInSytemExplorerCommand(canonicalPath);

                if ("".equals(launchCmd)) { //$NON-NLS-1$
                    return statusReporter.newStatus(IStatus.ERROR, logMsgPrefix
                            + IDEWorkbenchMessages.ShowInSystemExplorerHandler_commandUnavailable, null);
                }

                File dir = project.getWorkspace().getRoot().getLocation().toFile();
                Process p;
                if (Util.isLinux() || Util.isMac()) {
                    p = Runtime.getRuntime().exec(new String[] { "/bin/sh", "-c", launchCmd }, null, dir);
                } else {
                    p = Runtime.getRuntime().exec(launchCmd, null, dir);
                }
                int retCode = p.waitFor();
                if (retCode != 0 && !Util.isWindows()) {
                    return statusReporter.newStatus(IStatus.ERROR, "Execution of '" + launchCmd
                            + "' failed with return code: " + retCode, null);
                }
            } catch (IOException | InterruptedException e2) {
                return statusReporter.newStatus(IStatus.ERROR, logMsgPrefix + "Unhandled failure.", e2);
            }
            return Status.OK_STATUS;
        });
        job.schedule();
        return null;
    }

    private File getSystemExplorerPath(IResource resource) throws IOException {
        IPath location = resource.getLocation();
        if (location == null)
            return null;
        return location.toFile();
    }

    private String formShowInSytemExplorerCommand(File path) throws IOException {
        String command = IDEWorkbenchPlugin.getDefault().getPreferenceStore()
                .getString(IDEInternalPreferences.WORKBENCH_SYSTEM_EXPLORER);

        command = Util.replaceAll(command, VARIABLE_RESOURCE, quotePath(path.getCanonicalPath()));
        command = Util.replaceAll(command, VARIABLE_RESOURCE_URI, path.getCanonicalFile().toURI().toString());
        File parent = path.getParentFile();
        if (parent != null) {
            command = Util.replaceAll(command, VARIABLE_FOLDER, quotePath(parent.getCanonicalPath()));
        }
        return command;
    }

    private String quotePath(String path) {
        if (Util.isLinux() || Util.isMac()) {
            // Quote for usage inside "", man sh, topic QUOTING:
            path = path.replaceAll("[\"$`]", "\\\\$0"); //$NON-NLS-1$ //$NON-NLS-2$
        }
        // Windows: Can't quote, since explorer.exe has a very special command line parsing strategy.
        return path;
    }

}
