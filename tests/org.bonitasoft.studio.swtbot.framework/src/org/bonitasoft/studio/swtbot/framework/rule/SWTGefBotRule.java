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

import static org.bonitasoft.studio.swtbot.framework.conditions.BonitaBPMConditions.activeWelcomePage;

import org.bonitasoft.studio.application.actions.coolbar.NormalCoolBarHandler;
import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.connector.model.definition.provider.ConnectorEditPlugin;
import org.bonitasoft.studio.connector.model.definition.wizard.AbstractDefinitionWizard;
import org.bonitasoft.studio.engine.EnginePlugin;
import org.bonitasoft.studio.engine.preferences.EnginePreferenceConstants;
import org.bonitasoft.studio.preferences.BonitaCoolBarPreferenceConstant;
import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.bonitasoft.studio.swtbot.framework.conditions.BonitaBPMConditions;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.preference.IPreferenceStore;
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

    /*
     * (non-Javadoc)
     * @see org.junit.rules.TestRule#apply(org.junit.runners.model.Statement, org.junit.runner.Description)
     */
    @Override
    public Statement apply(final Statement base, final Description description) {
        return statement(base);
    }

    private Statement statement(final Statement base) {
        return new Statement() {

            @Override
            public void evaluate() throws Throwable {
                beforeStatement();
                try {
                    base.evaluate();
                } finally {
                    afterStatement();
                }

            }

        };
    }

    protected void afterStatement() {
        try {
            bot.waitUntil(BonitaBPMConditions.noPopupActive());
        } catch (final TimeoutException e) {
            bot.captureScreenshot(String.format("screenshots/OpenedShellAfterTest%s.jpg", System.currentTimeMillis()));
            closeAllShells(bot,e);
        }
        try {
        	 closeAllAndReturnToWelcomePage();
        }catch (Throwable e) {
        	BonitaStudioLog.error("An error occured while trying to close all editors.",e);
		}
    }

    private void closeAllShells(SWTWorkbenchBot bot,Exception e) {
        final SWTBotShell[] shells = bot.shells();
        for (final SWTBotShell shell : shells) {
            if (shell.isOpen() && !isEclipseShell(shell)) {
				System.out.println(String.format("Trying to close shell '%s' after test failure %s",shell.getText(),e));
				try {
				    shell.close();
				}catch(TimeoutException e1) {
				    System.out.println(String.format("Failed to close shell %s: %s",shell.getText(),e1));
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
        initPreferences();
        bot.saveAllEditors();
        bot.closeAllEditors();
    }

    protected void closeAllAndReturnToWelcomePage() {
        bot.saveAllEditors();
        bot.closeAllEditors();
        FileActionDialog.setDisablePopup(disablePopup);
    }

    protected void initPreferences() {
        ConnectorEditPlugin.getPlugin().getPreferenceStore()
                .setValue(AbstractDefinitionWizard.HIDE_CONNECTOR_DEFINITION_CHANGE_WARNING, true);
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
