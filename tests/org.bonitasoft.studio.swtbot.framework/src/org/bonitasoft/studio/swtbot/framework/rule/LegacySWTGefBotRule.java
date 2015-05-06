/**
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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

import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.services.IEvaluationService;

/**
 * Enable legacy mode on @Before
 * Disable legacy mode on @After
 */
public class LegacySWTGefBotRule extends SWTGefBotRule {

    public LegacySWTGefBotRule(final SWTGefBot bot) {
        super(bot);
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.swtbot.framework.rule.SWTGefBotRule#initPreferences()
     */
    @Override
    protected void initPreferences() {
        super.initPreferences();
        BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore().setValue(BonitaPreferenceConstants.SHOW_LEGACY_6X_MODE, true);
        notifyActiveWorkbenchWindow();
    }

    protected void notifyActiveWorkbenchWindow() {
        Display.getDefault().asyncExec(new Runnable() {

            @Override
            public void run() {
                final IEvaluationService service = (IEvaluationService) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getWorkbench()
                        .getService(IEvaluationService.class);
                service.requestEvaluation("activeWorkbenchWindow");
            }
        });
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.swtbot.framework.rule.SWTGefBotRule#afterStatement()
     */
    @Override
    protected void afterStatement() {
        BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore().setValue(BonitaPreferenceConstants.SHOW_LEGACY_6X_MODE, false);
        notifyActiveWorkbenchWindow();
        super.afterStatement();
    }

}
