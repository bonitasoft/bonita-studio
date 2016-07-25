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

import org.bonitasoft.studio.swtbot.framework.BotBase;
import org.bonitasoft.studio.swtbot.framework.BotQAUtil;
import org.bonitasoft.studio.swtbot.framework.diagram.general.connectors.BotConnectorsPropertySection;
import org.bonitasoft.studio.swtbot.framework.diagram.general.contract.BotContractPropertySection;
import org.bonitasoft.studio.swtbot.framework.diagram.general.operations.BotOperationsPropertySection;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;

/**
 * @author aurelie
 */
public class BotExecutionDiagramPropertiesView extends BotBase {

    public BotExecutionDiagramPropertiesView(final SWTGefBot bot) {
        super(bot);
    }

    public BotConnectorsPropertySection selectConnectorsInTab() {
        BotQAUtil.selectTabbedPropertyView(bot, "Connectors in");
        return new BotConnectorsPropertySection(bot);
    }

    public BotOperationsPropertySection selectOperationTab() {
        BotQAUtil.selectTabbedPropertyView(bot, "Operations");
        return new BotOperationsPropertySection(bot);
    }

    public BotContractPropertySection selectContractTab() {
        BotQAUtil.selectTabbedPropertyView(bot, "Contract");
        return new BotContractPropertySection(bot);
    }

    public BotFormMappingPropertySection selectInstantiationFormTab() {
        BotQAUtil.selectTabbedPropertyView(bot, "Instantiation form");
        return new BotFormMappingPropertySection(bot);
    }

    public BotFormMappingPropertySection selectOverviewFormTab() {
        BotQAUtil.selectTabbedPropertyView(bot, "Overview form");
        return new BotFormMappingPropertySection(bot);
    }

    public BotFormMappingPropertySection selectFormTab() {
        BotQAUtil.selectTabbedPropertyView(bot, "Form");
        return new BotFormMappingPropertySection(bot);
    }
}
