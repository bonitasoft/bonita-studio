/**
 * Copyright (C) 2021 BonitaSoft S.A.
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
package org.bonitasoft.studio.swtbot.framework.diagram.importer;

import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTable;

public class BotImportBOSDependenciesPreviewPage {

    private SWTGefBot bot;

    public BotImportBOSDependenciesPreviewPage(SWTGefBot bot) {
        this.bot = bot;
    }

    public SWTBotTable getDependenciesTable() {
        return bot.tableWithId(SWTBotConstants.SWTBOT_ID_DEPENDENCIES_PREVIEW_TABLE);
    }

}
