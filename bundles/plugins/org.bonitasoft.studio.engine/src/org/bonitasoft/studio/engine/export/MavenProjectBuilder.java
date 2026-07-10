/**
 * Copyright (C) 2024 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 */
package org.bonitasoft.studio.engine.export;

import java.util.List;

import org.apache.maven.execution.BuildSuccess;
import org.apache.maven.execution.MavenExecutionResult;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.BuildScheduler;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.core.BonitaProject;
import org.bonitasoft.studio.engine.EnginePlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.m2e.core.MavenPlugin;
import org.eclipse.m2e.core.embedder.ICallable;
import org.eclipse.m2e.core.embedder.IMavenExecutionContext;
import org.eclipse.m2e.core.project.IMavenProjectFacade;

/**
 * Utility class for building Maven projects in Bonita Studio.
 * <p>
 * This class provides methods to execute Maven builds (clean, install, etc.)
 * on Bonita projects within the Eclipse/OSGi environment using the M2E API.
 * </p>
 * <p>
 * Each instance is stateless and can be safely created multiple times.
 * Maven builds are executed with proper Eclipse workspace scheduling rules
 * to avoid conflicts with other build operations.
 * </p>
 * <p>
 * <b>Usage example:</b>
 * <pre>
 * MavenProjectBuilder builder = new MavenProjectBuilder();
 * IStatus status = builder.cleanInstall();
 * if (!status.isOK()) {
 *     // Handle build failure
 * }
 * </pre>
 * </p>
 *
 * @since 10.3.0
 * @see BarExporter
 * @see BuildScheduler
 */
public class MavenProjectBuilder {

    private static final String PLUGIN_ID = EnginePlugin.PLUGIN_ID;

    /**
     * Builds a Maven project with the specified goals.
     * <p>
     * This method executes Maven goals on the given Bonita project within
     * the Eclipse workspace build rules to ensure proper synchronization.
     * The build output is logged using {@link BonitaStudioLog}.
     * </p>
     *
     * @param project the Bonita project to build, must not be null
     * @param goals the Maven goals to execute (e.g., "clean", "install"), must not be null or empty
     * @return {@link IStatus#OK_STATUS} if build succeeds, {@link Status#CANCEL_STATUS} if the build
     *         is canceled or interrupted, error status otherwise (including when project is null)
     */
    public IStatus buildProject(BonitaProject project, List<String> goals) {
        return buildProject(project, goals, false);
    }

    private IStatus buildProject(BonitaProject project, List<String> goals, boolean failureTriggersRetry) {
        if (!isProjectValid(project)) {
            return createErrorStatus("Cannot build Maven project: No active Bonita project found");
        }

        logBuildStart(project, goals);

        try {
            IStatus buildResult = executeMavenBuild(project, goals, failureTriggersRetry);
            if (!buildResult.isOK()) {
                return buildResult;
            }

            return waitForBuildCompletion(project);
        } catch (OperationCanceledException e) {
            return canceled(project);
        } catch (Exception e) {
            return handleUnexpectedError(project, e, failureTriggersRetry);
        }
    }

    /**
     * Convenience method to execute Maven clean install on the current active project.
     * <p>
     * Automatically retrieves the current project from {@link RepositoryManager}.
     * Equivalent to running {@code mvn clean install} from command line.
     * </p>
     *
     * @return {@link IStatus#OK_STATUS} if build succeeds, error status if no active project or build fails
     */
    public IStatus cleanInstall() {
        BonitaProject project = RepositoryManager.getInstance().getCurrentProject().orElse(null);
        return cleanInstall(project);
    }

    /**
     * Executes an incremental Maven install ({@code mvn install}, without the {@code clean} goal),
     * optionally demoting a build failure log to a warning when a clean install retry is known to
     * follow.
     * <p>
     * The {@code clean} goal must be avoided in recurring flows (deploy, run, export): on Windows,
     * the Studio process can hold a file lock on a previously built jar (e.g. the BDM model jar).
     * {@code mvn clean} then deletes the module compiled output before failing on the locked jar,
     * leaving the workspace build output in a broken state.
     * </p>
     *
     * @param project the Bonita project to build, must not be null
     * @param failureTriggersRetry true when the caller retries with a full clean install on failure
     * @return {@link IStatus#OK_STATUS} if build succeeds, {@link Status#CANCEL_STATUS} if the build
     *         is canceled or interrupted, error status otherwise
     */
    IStatus install(BonitaProject project, boolean failureTriggersRetry) {
        return buildProject(project, List.of("install"), failureTriggersRetry);
    }

    /**
     * Executes an incremental Maven install on the current active project, falling back to a full
     * {@code clean install} if the incremental build fails.
     * <p>
     * Automatically retrieves the current project from {@link RepositoryManager}.
     * </p>
     *
     * @return {@link IStatus#OK_STATUS} if build succeeds, {@link Status#CANCEL_STATUS} if the build
     *         is canceled or interrupted, error status if no active project or build fails
     * @see #installWithCleanFallback(BonitaProject)
     */
    public IStatus installWithCleanFallback() {
        BonitaProject project = RepositoryManager.getInstance().getCurrentProject().orElse(null);
        return installWithCleanFallback(project);
    }

    /**
     * Executes an incremental Maven install on a specific project, falling back to a full
     * {@code clean install} if the incremental build fails.
     * <p>
     * This is the preferred build entry point for recurring flows (deploy, run, export, test):
     * the incremental build avoids the destructive {@code clean} goal that can wipe the BDM build
     * output when a jar file is locked by the Studio process on Windows, while the
     * fallback still recovers from stale build states that require a full rebuild.
     * </p>
     * <p>
     * Note that the fallback itself runs the destructive {@code clean} goal: if a jar is locked
     * while the incremental build fails for another reason, the clean can still wipe the module
     * build output. This method narrows the failure window but does not eliminate it on its own.
     * </p>
     *
     * @param project the Bonita project to build, must not be null
     * @return {@link IStatus#OK_STATUS} if build succeeds, {@link Status#CANCEL_STATUS} if the build
     *         is canceled or interrupted, error status otherwise
     */
    public IStatus installWithCleanFallback(BonitaProject project) {
        if (!isProjectValid(project)) {
            return createErrorStatus("Cannot build Maven project: No active Bonita project found");
        }
        IStatus status = install(project, true);
        if (status.isOK() || status.getSeverity() == IStatus.CANCEL) {
            return status;
        }
        return cleanInstall(project);
    }

    /**
     * Convenience method to execute Maven clean install on a specific project.
     * <p>
     * Equivalent to running {@code mvn clean install} from command line.
     * This will:
     * <ul>
     *   <li>Clean the target directory</li>
     *   <li>Compile sources</li>
     *   <li>Run tests</li>
     *   <li>Package artifacts</li>
     *   <li>Install artifacts in local Maven repository</li>
     * </ul>
     * </p>
     *
     * @param project the Bonita project to build, must not be null
     * @return {@link IStatus#OK_STATUS} if build succeeds, error status otherwise
     */
    public IStatus cleanInstall(BonitaProject project) {
        return buildProject(project, List.of("clean", "install"));
    }

    private boolean isProjectValid(BonitaProject project) {
        return project != null;
    }

    private void logBuildStart(BonitaProject project, List<String> goals) {
        String goalsStr = String.join(" ", goals);
        BonitaStudioLog.info(
                String.format("Building project %s (mvn %s)...", project.getDisplayName(), goalsStr),
                PLUGIN_ID);
    }

    private IStatus executeMavenBuild(BonitaProject project, List<String> goals, boolean failureTriggersRetry)
            throws CoreException {
        return BuildScheduler.callWithBuildRule(() -> {
            IMavenProjectFacade mavenProject = resolveMavenProject(project);
            if (mavenProject == null) {
                String errorMsg = "Cannot resolve Maven project for " + project.getDisplayName();
                if (failureTriggersRetry) {
                    // A clean install retry follows: do not report the recoverable failure as an error
                    BonitaStudioLog.warning(errorMsg + ". A clean install retry will follow.", PLUGIN_ID);
                    return new Status(IStatus.ERROR, PLUGIN_ID, errorMsg);
                }
                return createErrorStatus(errorMsg);
            }

            return runMavenGoals(mavenProject, goals, project.getDisplayName(), failureTriggersRetry);
        }, new NullProgressMonitor());
    }

    private IMavenProjectFacade resolveMavenProject(BonitaProject project) {
        return MavenPlugin.getMavenProjectRegistry().getProject(project.getParentProject());
    }

    private IStatus runMavenGoals(IMavenProjectFacade mavenProject, List<String> goals, String projectName,
            boolean failureTriggersRetry) throws CoreException {
        var ctx = mavenProject.createExecutionContext();
        var request = ctx.getExecutionRequest();
        request.setGoals(goals);
        request.setPom(mavenProject.getPomFile());

        MavenExecutionResult result = MavenPlugin.getMavenProjectRegistry().execute(
                mavenProject,
                new MavenExecutor(request),
                new NullProgressMonitor());

        return evaluateResult(result, projectName, failureTriggersRetry);
    }

    private IStatus evaluateResult(MavenExecutionResult result, String projectName, boolean failureTriggersRetry) {
        if (result.getBuildSummary(result.getProject()) instanceof BuildSuccess) {
            BonitaStudioLog.info("Maven build successful for " + projectName, PLUGIN_ID);
            return Status.OK_STATUS;
        } else {
            String errorMsg = "Maven build failed for project " + projectName;
            Throwable cause = result.hasExceptions() ? result.getExceptions().get(0) : null;
            if (failureTriggersRetry) {
                // A clean install retry follows: do not report the recoverable failure as an error
                BonitaStudioLog.warning(String.format("%s (%s). A clean install retry will follow.",
                        errorMsg, describeCause(cause)), PLUGIN_ID);
            } else {
                BonitaStudioLog.error(errorMsg, cause);
            }
            return new Status(IStatus.ERROR, PLUGIN_ID, errorMsg, cause);
        }
    }

    private IStatus waitForBuildCompletion(BonitaProject project) {
        try {
            BuildScheduler.joinOnBuildRule();
            return Status.OK_STATUS;
        } catch (InterruptedException e) {
            // Catching InterruptedException clears the thread interrupt flag: restore it so
            // callers up the stack (e.g. the Jobs framework) can still observe the interruption
            Thread.currentThread().interrupt();
            return canceled(project);
        } catch (OperationCanceledException e) {
            return canceled(project);
        } catch (IllegalStateException e) {
            String errorMsg = "Maven build interrupted for " + project.getDisplayName();
            BonitaStudioLog.error(errorMsg, e);
            return new Status(IStatus.ERROR, PLUGIN_ID, errorMsg, e);
        }
    }

    private IStatus handleUnexpectedError(BonitaProject project, Exception e, boolean failureTriggersRetry) {
        String errorMsg = "Unexpected error during Maven build for " + project.getDisplayName();
        if (failureTriggersRetry) {
            // A clean install retry follows: do not report the recoverable failure as an error
            BonitaStudioLog.warning(String.format("%s (%s). A clean install retry will follow.",
                    errorMsg, describeCause(e)), PLUGIN_ID);
        } else {
            BonitaStudioLog.error(errorMsg, e);
        }
        return new Status(IStatus.ERROR, PLUGIN_ID, errorMsg, e);
    }

    private IStatus canceled(BonitaProject project) {
        BonitaStudioLog.info("Maven build canceled for " + project.getDisplayName(), PLUGIN_ID);
        return Status.CANCEL_STATUS;
    }

    private static String describeCause(Throwable cause) {
        if (cause == null) {
            return "no cause";
        }
        return cause.getMessage() != null ? cause.getMessage() : cause.toString();
    }

    private IStatus createErrorStatus(String message) {
        BonitaStudioLog.error(message, PLUGIN_ID);
        return new Status(IStatus.ERROR, PLUGIN_ID, message);
    }

    /**
     * Internal class to encapsulate Maven execution callback.
     * <p>
     * This class adapts a Maven execution request to the M2E {@link ICallable}
     * interface, allowing Maven builds to be executed within the Eclipse
     * Maven execution context with proper classloader and session management.
     * </p>
     */
    private static class MavenExecutor implements ICallable<MavenExecutionResult> {

        private final org.apache.maven.execution.MavenExecutionRequest request;

        /**
         * Creates a new Maven executor for the given request.
         *
         * @param request the Maven execution request containing goals, POM file, and settings
         */
        MavenExecutor(org.apache.maven.execution.MavenExecutionRequest request) {
            this.request = request;
        }

        /**
         * Executes the Maven request within the given execution context.
         *
         * @param context the Maven execution context providing classloader and session
         * @param monitor progress monitor (currently unused but required by interface)
         * @return the Maven execution result containing build status and any exceptions
         * @throws CoreException if Maven execution fails with an unrecoverable error
         */
        @Override
        public MavenExecutionResult call(IMavenExecutionContext context, IProgressMonitor monitor)
                throws CoreException {
            return context.execute(request);
        }
    }
}
