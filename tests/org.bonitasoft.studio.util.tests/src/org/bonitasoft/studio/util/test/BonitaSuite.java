/**
 * Copyright (C) 2011 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.util.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.Suite;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.RunnerBuilder;

public final class BonitaSuite extends Suite {

    private RunListener runListener;

    private final SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd-HH:mm:ss");

    public BonitaSuite(Class<?> klass, Class<?>[] suiteClasses) throws InitializationError {
        super(klass, suiteClasses);
    }

    public BonitaSuite(Class<?> klass, List<Runner> runners) throws InitializationError {
        super(klass, runners);
    }

    public BonitaSuite(Class<?> klass, RunnerBuilder builder) throws InitializationError {
        super(klass, builder);
    }

    public BonitaSuite(RunnerBuilder builder, Class<?> klass, Class<?>[] suiteClasses) throws InitializationError {
        super(builder, klass, suiteClasses);
    }

    public BonitaSuite(RunnerBuilder builder, Class<?>[] classes) throws InitializationError {
        super(builder, classes);
    }

    /*
     * (non-Javadoc)
     * @see org.junit.runners.ParentRunner#run(org.junit.runner.notification.RunNotifier)
     */
    @Override
    public void run(RunNotifier notifier) {
        final RunListener logNotifier = getRunListener();
        notifier.removeListener(logNotifier); // remove existing listeners that could be added by suite or class runners
        notifier.addListener(logNotifier);
        try {
            super.run(notifier);
        } finally {
            notifier.removeListener(logNotifier);
        }
    }

    protected RunListener getRunListener() {
        if (runListener == null) {
            runListener = new RunListener() {

                private long startTime;
                private boolean success = true;

                /*
                 * (non-Javadoc)
                 * @see org.junit.runner.notification.RunListener#testStarted(org.junit.runner.Description)
                 */
                @Override
                public void testStarted(Description description) throws Exception {
                    startTime = System.currentTimeMillis();
                    success = true;
                    System.out.print(
                            String.format("| %s: Running %s", format.format(new Date()), description.getDisplayName()));
                }

                /*
                 * (non-Javadoc)
                 * @see org.junit.runner.notification.RunListener#testIgnored(org.junit.runner.Description)
                 */
                @Override
                public void testIgnored(Description description) throws Exception {
                    System.out.println(String.format(" --------> Skipped"));
                    success = false;
                }

                /*
                 * (non-Javadoc)
                 * @see org.junit.runner.notification.RunListener#testFailure(org.junit.runner.notification.Failure)
                 */
                @Override
                public void testFailure(Failure failure) throws Exception {
                    System.out.println(String.format(" --------> Failure: %s", failure.toString()));
                    success = false;
                }

                /*
                 * (non-Javadoc)
                 * @see org.junit.runner.notification.RunListener#testAssumptionFailure(org.junit.runner.notification.Failure)
                 */
                @Override
                public void testAssumptionFailure(Failure failure) {
                    System.out.println(String.format(" --------> Error: %s", failure.toString()));
                    success = false;
                }

                /*
                 * (non-Javadoc)
                 * @see org.junit.runner.notification.RunListener#testFinished(org.junit.runner.Description)
                 */
                @Override
                public void testFinished(Description description) throws Exception {
                    if (success) {
                        System.out.println(String.format(" --------> Success (%s)", time()));
                    }
                }

                protected String time() {
                    return new SimpleDateFormat("s,SSS 's'").format(new Date(System.currentTimeMillis() - startTime));
                }
            };

        }

        return runListener;
    }

}
