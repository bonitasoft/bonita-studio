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
package org.bonitasoft.studio.tests.properties;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.model.process.InputMappingAssignationType;
import org.bonitasoft.studio.swtbot.framework.SWTBotTestUtil;
import org.bonitasoft.studio.swtbot.framework.application.BotApplicationWorkbenchWindow;
import org.bonitasoft.studio.swtbot.framework.diagram.BotProcessDiagramPerspective;
import org.bonitasoft.studio.swtbot.framework.diagram.general.BotCallActivityInputMappingPropertySection;
import org.bonitasoft.studio.swtbot.framework.rule.SWTGefBotRule;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SWTBotJunit4ClassRunner.class)
public class CallActivityMappingIT {

    private final SWTGefBot bot = new SWTGefBot();

    @Rule
    public SWTGefBotRule botRule = new SWTGefBotRule(bot);

    @Test
    public void testInputMappings() throws IOException {
        new BotApplicationWorkbenchWindow(bot).importBOSArchive()
                .setArchive(
                        CallActivityMappingIT.class.getResource("ITTest-CallActivityMapping-1.0.bos"))
                .existingRepository()
                .next()
                .next()
                .finish();

        final BotProcessDiagramPerspective botProcessDiagramPerspective = new BotProcessDiagramPerspective(bot);
        botProcessDiagramPerspective.activeProcessDiagramEditor().selectElement("Step With Known Called Process");
        final BotCallActivityInputMappingPropertySection botCallActivityMappingPropertySection = botProcessDiagramPerspective
                .getDiagramPropertiesPart()
                .selectExecutionTab().selectDataToSendTab();
        botCallActivityMappingPropertySection.addInputMapping();
        assertThat(SWTBotTestUtil.listExpressionProposal(bot, 0)).containsOnly("processData1 -- " + String.class.getName(),
                "processData2 -- " + String.class.getName(), "stepData1 -- " + String.class.getName());

        final String[] availableCalledProcessContractInput = bot
                .ccomboBoxWithId(SWTBotConstants.SWTBOT_ID_CALLACTIVITY_MAPPING_INPUT_CALLEDTARGET, 0).items();
        assertThat(availableCalledProcessContractInput).containsOnly("contractInput1", "contractInput2");

        botCallActivityMappingPropertySection.updateInputMapping(0, null, null, InputMappingAssignationType.DATA,
                "processData1");
        final String[] availableCalledProcessData = bot
                .ccomboBoxWithId(SWTBotConstants.SWTBOT_ID_CALLACTIVITY_MAPPING_INPUT_CALLEDTARGET, 0).items();
        assertThat(availableCalledProcessData).containsOnly("processData1", "processData2");

        botCallActivityMappingPropertySection.deleteInputMapping(0);
    }

}
