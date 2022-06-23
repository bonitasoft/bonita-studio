/**
 * Copyright (C) 2015 BonitaSoft S.A.
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
package org.bonitasoft.studio.swtbot.framework.rule;

import java.lang.reflect.InvocationTargetException;

import org.bonitasoft.studio.application.actions.coolbar.NormalCoolBarHandler;
import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.bonitasoft.studio.common.repository.Messages;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.core.maven.contribution.InstallBonitaMavenArtifactsOperation;
import org.bonitasoft.studio.engine.EnginePlugin;
import org.bonitasoft.studio.engine.preferences.EnginePreferenceConstants;
import org.bonitasoft.studio.preferences.BonitaCoolBarPreferenceConstant;
import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.bonitasoft.studio.preferences.pages.BonitaAdvancedPreferencePage;
import org.bonitasoft.studio.swtbot.framework.conditions.BonitaBPMConditions;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.m2e.core.MavenPlugin;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.BoolResult;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.TimeoutException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.browser.WebBrowserUIPlugin;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 * @author Romain Bioteau
 *         Initialize prefrences for tests (disable confirmation popup...etc)
 *         Save and close all opened editors after the test and wait for the welcome page
 */
public class SWTGefBotRule implements TestRule {

    private boolean disablePopup;
    protected final SWTGefBot bot;

    public SWTGefBotRule(final SWTGefBot bot) {
        this.bot = bot;
    }

    @Override
    public Statement apply(final Statement base, final Description description) {
        return statement(base, description);
    }

    private Statement statement(final Statement base, Description description) {
        return new Statement() {

            @Override
            public void evaluate() throws Throwable {
                beforeStatement();
                try {
                    base.evaluate();
                } catch (Throwable t) {
                    captureScreenshot(description, t);
                    throw t;
                } finally {
                    afterStatement(description);
                }

            }

            void captureScreenshot(Description description, Throwable t) {
                try {
                    String shellText = bot.activeShell().getText();
                    BonitaStudioLog.error(
                            String.format("%s failed ! (active shell = %s)", description.getDisplayName(), shellText),
                            t);
                    bot.captureScreenshot(String.format("screenshots/%s_%s.jpg", description.getClassName(),
                            description.getMethodName()));
                } catch (Throwable e) {
                   BonitaStudioLog.error("Failed to capture screenshot after test failure.", e);
                }
            }

        };
    }

    protected void afterStatement(Description description) {
        try {
            bot.waitUntil(BonitaBPMConditions.noPopupActive());
        } catch (final TimeoutException e) {
            bot.captureScreenshot(String.format("screenshots/OpenedShellAfter_%s_%s.jpg", description.getClassName(),
                    description.getMethodName()));
            closeAllShells(bot, e);
        }
        try {
            closeAllAndReturnToWelcomePage();
        } catch (Throwable e) {
            BonitaStudioLog.error("An error occured while trying to close all editors.", e);
        }
    }

    private void closeAllShells(SWTWorkbenchBot bot, Exception e) {
        System.out.println(
                String.format("Trying to close shell '%s' after test failure %s", bot.activeShell().getText(), e));
        //Force shell close
        final SWTBotShell[] shells = bot.shells();
        for (final SWTBotShell shell : shells) {
            if (shell.isOpen() && !isEclipseShell(shell)) {
                shell.activate();
                try {
                    bot.button(IDialogConstants.CANCEL_LABEL).click();
                    break;
                } catch (Throwable t) {
                    // not in a wizard
                }
                try {
                    bot.button(IDialogConstants.CLOSE_LABEL).click();
                    break;
                } catch (Throwable t) {
                    // not in a dialog
                }
                try {
                    shell.close();
                } catch (TimeoutException e1) {
                    System.out.println(String.format("Failed to close shell %s: %s", shell.getText(), e1));
                }
            }
        }
    }

    private boolean isEclipseShell(final SWTBotShell shell) {
        return UIThreadRunnable.syncExec(new BoolResult() {

            @Override
            public Boolean run() {
                return PlatformUI.getWorkbench().getActiveWorkbenchWindow()
                        .getShell() == shell.widget;
            }
        });
    }

    protected void beforeStatement() {
        Display.getDefault().syncExec(this::ensureDefaultProjectExists);
        initPreferences();
        bot.saveAllEditors();
        bot.closeAllEditors();
        bot.waitUntil(BonitaBPMConditions.noPopupActive(), 10000);
    }

    private void ensureDefaultProjectExists() {
        var repositoryManager = RepositoryManager.getInstance();
        if (repositoryManager.getRepository(Messages.defaultRepositoryName) == null
                || !repositoryManager.getRepository(Messages.defaultRepositoryName).exists()) {
            try {
                PlatformUI.getWorkbench().getProgressService().run(true, false, monitor -> {
                    try {
                        new InstallBonitaMavenArtifactsOperation(MavenPlugin.getMaven().getLocalRepository())
                                .execute(monitor);
                    } catch (CoreException e) {
                        throw new InvocationTargetException(e);
                    }
                    repositoryManager.setCurrentRepository(
                            repositoryManager.createRepository(Messages.defaultRepositoryName, false));
                    repositoryManager.getAccessor().start(monitor);
                    PlatformUtil.openDashboardIfNoOtherEditorOpen();
                });
            } catch (InvocationTargetException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    protected void closeAllAndReturnToWelcomePage() {
        bot.saveAllEditors();
        bot.closeAllEditors();
        FileActionDialog.setDisablePopup(disablePopup);
    }

    protected void initPreferences() {
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
        WebBrowserUIPlugin.getInstance().getPreferenceStore()
                .setValue(BonitaPreferenceConstants.CONSOLE_BROWSER_CHOICE, BonitaPreferenceConstants.INTERNAL_BROWSER);
        EnginePlugin.getDefault().getPreferenceStore().setValue(EnginePreferenceConstants.LAZYLOAD_ENGINE, true);
        FileActionDialog.setDisablePopup(true);
    }

}
