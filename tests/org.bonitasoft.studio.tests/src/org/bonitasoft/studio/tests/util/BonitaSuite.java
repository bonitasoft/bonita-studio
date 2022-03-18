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
package org.bonitasoft.studio.tests.util;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;

import org.bonitasoft.studio.application.actions.coolbar.NormalCoolBarHandler;
import org.bonitasoft.studio.common.ConsoleColors;
import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.engine.BOSWebServerManager;
import org.bonitasoft.studio.engine.EnginePlugin;
import org.bonitasoft.studio.engine.preferences.EnginePreferenceConstants;
import org.bonitasoft.studio.preferences.BonitaCoolBarPreferenceConstant;
import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.bonitasoft.studio.preferences.pages.BonitaAdvancedPreferencePage;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.browser.WebBrowserUIPlugin;
import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.Suite;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.RunnerBuilder;

public class BonitaSuite extends Suite {

    private static final int SERVER_STARTUP_TIMEOUT = 90000;
    private RunListener runListener;

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

    @Override
    public void run(RunNotifier notifier) {
        Display.getDefault().syncExec(() -> 
        {
            configurePreferencesForTests();
            try {
                PlatformUI.getWorkbench().getProgressService().run(true, false, monitor -> {
                    Job.getJobManager().join(RepositoryManager.class, monitor);
                });
            } catch (InvocationTargetException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        if (shouldWaitForServerStartup()) {
            var serverReady = new AtomicBoolean(false);
            Display.getDefault().syncExec(() -> {
                try {
                    PlatformUI.getWorkbench().getProgressService().run(true, false, monitor -> {
                        monitor.beginTask("Preparing test suite env...", IProgressMonitor.UNKNOWN);
                        try {
                            Await.waitUntil(() -> BOSWebServerManager.getInstance().serverIsStarted(),
                                    SERVER_STARTUP_TIMEOUT, 200);
                            serverReady.set(true);
                        } catch (TimeoutException e) {
                            throw new InvocationTargetException(e);
                        }
                    });
                } catch (InvocationTargetException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
            if (!serverReady.get()) {
                throw new RuntimeException(
                        String.format("Server startup timed out after %sms", SERVER_STARTUP_TIMEOUT));
            }
        }

        final RunListener logNotifier = getRunListener();
        notifier.removeListener(logNotifier); // remove existing listeners that could be added by suite or class runners
        notifier.addListener(logNotifier);
        try {
            super.run(notifier);
        } finally {
            notifier.removeListener(logNotifier);
        }
    }

    private boolean shouldWaitForServerStartup() {
        IPreferenceStore preferenceStore = EnginePlugin.getDefault().getPreferenceStore();
        return !preferenceStore.getBoolean(EnginePreferenceConstants.LAZYLOAD_ENGINE)
                && System.getProperty(EnginePreferenceConstants.LAZYLOAD_ENGINE) == null;
    }

    protected RunListener getRunListener() {
        if (runListener == null) {
            runListener = new RunListener() {

                private long startTime;
                private boolean success = true;

                @Override
                public void testStarted(Description description) throws Exception {
                    Platform.getLog(BonitaSuite.class).info(String.format("Start: %s", description.getDisplayName()));
                    startTime = System.currentTimeMillis();
                    success = true;
                    System.out.print(
                            String.format("%s%s%s",
                                    ConsoleColors.BLUE_BRIGHT,
                                    description.getDisplayName(),
                                    ConsoleColors.RESET));
                }

                @Override
                public void testIgnored(Description description) throws Exception {
                    System.out.println(
                            String.format("%s%s%s",
                                    ConsoleColors.BLUE_BRIGHT_STRIKETHROUGH,
                                    description.getDisplayName(),
                                    ConsoleColors.RESET));
                    success = false;
                }

                @Override
                public void testFailure(Failure failure) throws Exception {
                    System.out.println(
                            String.format(" %s\u2718 %s%s", ConsoleColors.RED, failure.toString(),
                                    ConsoleColors.RESET));
                    success = false;
                }

                /*
                 * (non-Javadoc)
                 * @see
                 * org.junit.runner.notification.RunListener#testAssumptionFailure(org.junit.runner.notification.Failure)
                 */
                @Override
                public void testAssumptionFailure(Failure failure) {
                    System.out.println(
                            String.format(" %s\u274E %s%s", ConsoleColors.RED, failure.toString(),
                                    ConsoleColors.RESET));
                    success = false;
                }

                @Override
                public void testFinished(Description description) throws Exception {
                    if (success) {
                        System.out
                                .println(
                                        String.format(" %s\u2713%s %s(%s)%s",
                                                ConsoleColors.GREEN,
                                                ConsoleColors.RESET,
                                                ConsoleColors.BLACK_BRIGHT,
                                                time(),
                                                ConsoleColors.RESET));
                    }
                    Platform.getLog(BonitaSuite.class).info(String.format("End: %s", description.getDisplayName()));
                }

                protected String time() {
                    return new SimpleDateFormat("s,SSS 's'").format(new Date(System.currentTimeMillis() - startTime));
                }
            };

        }

        return runListener;
    }

    private void configurePreferencesForTests() {
        BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore()
                .setValue(BonitaAdvancedPreferencePage.HIDE_CONNECTOR_DEFINITION_CHANGE_WARNING, true);
        IPreferenceStore preferenceStore = BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore();
        preferenceStore.setValue(BonitaCoolBarPreferenceConstant.COOLBAR_DEFAULT_SIZE,
                BonitaCoolBarPreferenceConstant.NORMAL);
        Display.getDefault().asyncExec(() -> {
            try {
                new NormalCoolBarHandler().execute(null);
            } catch (ExecutionException e) {
                BonitaStudioLog.error(e);
            }
        });

        preferenceStore
                .setValue(BonitaPreferenceConstants.ASK_RENAME_ON_FIRST_SAVE, false);
        preferenceStore
                .setValue(BonitaPreferenceConstants.CONSOLE_BROWSER_CHOICE, BonitaPreferenceConstants.INTERNAL_BROWSER);
        preferenceStore.setValue(BonitaPreferenceConstants.HIDE_BDM_REFACTOR_PREVIEW, true);
        WebBrowserUIPlugin.getInstance().getPreferenceStore()
                .setValue(BonitaPreferenceConstants.CONSOLE_BROWSER_CHOICE, BonitaPreferenceConstants.INTERNAL_BROWSER);
        FileActionDialog.setDisablePopup(true);
    }

}
