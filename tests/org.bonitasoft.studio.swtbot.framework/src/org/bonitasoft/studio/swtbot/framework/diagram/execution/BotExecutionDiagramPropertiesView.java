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
package org.bonitasoft.studio.swtbot.framework.diagram.execution;

import org.bonitasoft.studio.swtbot.framework.BotPropertiesView;
import org.bonitasoft.studio.swtbot.framework.diagram.general.BotCallActivityInputMappingPropertySection;
import org.bonitasoft.studio.swtbot.framework.diagram.general.connectors.BotConnectorsPropertySection;
import org.bonitasoft.studio.swtbot.framework.diagram.general.contract.BotContractPropertySection;
import org.bonitasoft.studio.swtbot.framework.diagram.general.operations.BotOperationsPropertySection;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;

public class BotExecutionDiagramPropertiesView extends BotPropertiesView {

    public BotExecutionDiagramPropertiesView(final SWTGefBot bot, SWTBotView botView) {
        super(bot, botView);
    }

    public BotConnectorsPropertySection selectConnectorsInTab() {
        selectTab("Connectors in");
        return new BotConnectorsPropertySection(bot);
    }

    public BotOperationsPropertySection selectOperationTab() {
        selectTab("Operations");
        return new BotOperationsPropertySection(bot);
    }

    public BotContractPropertySection selectContractTab() {
        selectTab("Contract");
        return new BotContractPropertySection(bot);
    }

    public BotFormMappingPropertySection selectInstantiationFormTab() {
        selectTab("Instantiation form");
        return new BotFormMappingPropertySection(bot);
    }

    public BotFormMappingPropertySection selectOverviewFormTab() {
        selectTab("Overview form");
        return new BotFormMappingPropertySection(bot);
    }

    public BotFormMappingPropertySection selectFormTab() {
        selectTab("Form");
        return new BotFormMappingPropertySection(bot);
    }

    public BotCallActivityInputMappingPropertySection selectDataToSendTab() {
        selectTab("Data to send");
        return new BotCallActivityInputMappingPropertySection(bot);
    }
}
