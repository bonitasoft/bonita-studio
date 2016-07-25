/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.tests.migration;

import static org.assertj.core.api.Assertions.assertThat;

import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.bonitasoft.studio.swtbot.framework.diagram.BotProcessDiagramMigrationPerspective;
import org.bonitasoft.studio.test.swtbot.util.SWTBotTestUtil;
import org.eclipse.swtbot.eclipse.gef.finder.SWTBotGefTestCase;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;


/**
 * @author Romain Bioteau
 *
 */
@RunWith(SWTBotJunit4ClassRunner.class)
public class BarImportReportIT extends SWTBotGefTestCase {

    private boolean disablePopup;

    @Override
    @Before
    public void setUp() {
        disablePopup = FileActionDialog.getDisablePopup();
        FileActionDialog.setDisablePopup(true);
        BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore().setValue(BonitaPreferenceConstants.ASK_RENAME_ON_FIRST_SAVE, false);
    }

    @Override
    @After
    public void tearDown() {
        bot.saveAllEditors();
        bot.closeAllEditors();
        FileActionDialog.setDisablePopup(disablePopup);
    }

    @Test
    public void should_import_bar_from_5x_generate_a_migration_report() throws Exception {
        SWTBotTestUtil.importProcessWIthPathFromClass(bot, "Buy_a_MINI--3.2.bar", "Bonita BAR 5.9/5.10", "Buy_a_MINI", this.getClass(), false);
        final int numberOfRows = new BotProcessDiagramMigrationPerspective(bot).migrationReport().numberOfRows();

        assertThat(numberOfRows).isGreaterThan(10);
    }

}
