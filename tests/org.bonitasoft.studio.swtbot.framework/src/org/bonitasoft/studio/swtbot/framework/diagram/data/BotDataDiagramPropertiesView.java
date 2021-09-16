/**
 * Copyright (C) 2014 BonitaSoft S.A.
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
package org.bonitasoft.studio.swtbot.framework.diagram.data;

import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.swtbot.framework.BotPropertiesView;
import org.bonitasoft.studio.swtbot.framework.diagram.general.data.BotDataPropertySection;
import org.bonitasoft.studio.swtbot.framework.diagram.general.documents.BotDocumentsPropertySection;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;

public class BotDataDiagramPropertiesView extends BotPropertiesView {

    public BotDataDiagramPropertiesView(final SWTGefBot bot, SWTBotView botView) {
        super(bot, botView);
    }

    public BotDataPropertySection selectLocalDataTab() {
        selectTab(SWTBotConstants.VIEWS_PROPERTIES_TASK_DATA_VARIABLES);
        return new BotDataPropertySection(bot);
    }

    public BotDataPropertySection selectPoolDataTab() {
        selectTab(SWTBotConstants.VIEWS_PROPERTIES_POOL_DATA_VARIABLES);
        return new BotDataPropertySection(bot);
    }

    public BotDocumentsPropertySection selectDocumentsTab() {
        selectTab("Documents");
        return new BotDocumentsPropertySection(bot);
    }

}
