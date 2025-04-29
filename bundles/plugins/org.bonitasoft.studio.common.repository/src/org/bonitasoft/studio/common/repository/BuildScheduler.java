/**
 * Copyright (C) 2023 BonitaSoft S.A.
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
package org.bonitasoft.studio.common.repository;

import java.util.concurrent.Callable;
import java.util.function.Supplier;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.core.runtime.jobs.Job;

/**
 * This class provides utility methods to help you schedule operations with the "build" job rule.
 * 
 * @author Vincent Hemery
 */
public class BuildScheduler {

    /**
     * An alternative to {@link Callable} with checked exception type.
     * 
     * @author Vincent Hemery
     * @param <V> the result type of method {@code call}
     * @param <E> the checked type of Exception
     */
    @FunctionalInterface
    public static interface CheckedCallable<V, E extends Exception> extends Callable<V> {

        /*
         * (non-Javadoc)
         * @see java.util.concurrent.Callable#call()
         */
        @Override
        V call() throws E;
    }

    /**
     * An exception which may not be thrown and can be safely ignored.
     */
    private static class NoException extends Exception {

        /**
         * Default serial version UID
         */
        private static final long serialVersionUID = 1L;
    }

    /**
     * Private utils Constructor.
     */
    private BuildScheduler() {
        // do nothing
    }

    /**
     * Execute a supplying operation, ensuring it is executed with an exclusive access to the "build" job rule.<br/>
     * When another build job is already active, this method will wait for an available schedule and block until it is executed.
     * <p><b>Note:</b> when you do not need a result, just <code>return (Void)null;</code></p>
     * 
     * @param <V> the result type for the operation
     * @param operation operation to execute
     * @return operation's supplied result.
     */
    public static <V> V supplyWithBuildRule(Supplier<V> operation) {
        return supplyWithBuildRule(operation, new NullProgressMonitor());
    }

    /**
     * Execute a supplying operation, ensuring it is executed with an exclusive access to the "build" job rule.<br/>
     * When another build job is already active, this method will wait for an available schedule and block until it is executed.
     * <p><b>Note:</b> when you do not need a result, just <code>return (Void)null;</code></p>
     * 
     * @param <V> the result type for the operation
     * @param operation operation to execute
     * @param monitor a progress monitor
     * @return operation's supplied result.
     */
    public static <V> V supplyWithBuildRule(Supplier<V> operation, IProgressMonitor monitor) {
        CheckedCallable<V, NoException> callable = operation::get;
        try {
            return callWithBuildRule(callable);
        } catch (NoException e) {
            // should never occur...
            BonitaStudioLog.error(e);
            return null;
        }
    }

    /**
     * Execute a callable operation, ensuring it is executed with an exclusive access to the "build" job rule.<br/>
     * When another build job is already active, this method will wait for an available schedule and block until it is executed.
     * <p><b>Note:</b> when you do not need a result, just <code>return (Void)null;</code></p>
     * 
     * @param <V> the result type for the operation
     * @param <E> the exception type the operation may throw
     * @param operation callable operation to execute
     * @return operation result.
     * @throws E if unable to compute the operation result
     */
    public static <V, E extends Exception> V callWithBuildRule(CheckedCallable<V, E> operation) throws E {
        return callWithBuildRule(operation, new NullProgressMonitor());
    }

    /**
     * Execute a callable operation, ensuring it is executed with an exclusive access to the "build" job rule.<br/>
     * When another build job is already active, this method will wait for an available schedule and block until it is executed.
     * <p><b>Note:</b> when you do not need a result, just <code>return (Void)null;</code></p>
     * 
     * @param <V> the result type for the operation
     * @param operation callable operation to execute
     * @param monitor a progress monitor
     * @return operation result.
     * @throws Exception if unable to compute the operation result
     */
    public static <V, E extends Exception> V callWithBuildRule(CheckedCallable<V, E> operation,
            IProgressMonitor monitor) throws E {
        ISchedulingRule schedulingRule = getBuildRule();
        boolean needRule = !schedulingRule.equals(Job.getJobManager().currentRule());
        if (!needRule) {
            return operation.call();
        }
        Job.getJobManager().beginRule(schedulingRule, monitor);
        try {
            return operation.call();
        } finally {
            Job.getJobManager().endRule(schedulingRule);
        }
    }

    /**
     * Get the build scheduling rule
     * 
     * @return the build rule for schedule
     */
    private static ISchedulingRule getBuildRule() {
        return ResourcesPlugin.getWorkspace().getRuleFactory().buildRule();
    }

    /**
     * Wait for all jobs with build rule to end.
     * 
     * @throws IllegalStateException if we are already in a build job (to prevent deadlock)
     * @throws InterruptedException if this thread is interrupted while waiting
     * @throws OperationCanceledException if the progress monitor is canceled while waiting
     */
    public static void joinOnBuildRule()
            throws IllegalStateException, OperationCanceledException, InterruptedException {
        if (getBuildRule().equals(Job.getJobManager().currentRule())) {
            throw new IllegalStateException("Already in a build operation.");
        }
        Job.getJobManager().join(ResourcesPlugin.getWorkspace().getRuleFactory().buildRule(),
                new NullProgressMonitor());
    }

    /**
     * Schedule job to run with build rule.
     * 
     * @param jobToSchedule the job which must be scheduled to execute later
     */
    public static void scheduleJobWithBuildRule(Job jobToSchedule) {
        jobToSchedule.setRule(getBuildRule());
        jobToSchedule.schedule();
    }

}

