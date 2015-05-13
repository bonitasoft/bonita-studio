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
package org.bonitasoft.studio.properties.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.model.process.InputMappingAssignationType;
import org.bonitasoft.studio.swtbot.framework.diagram.BotProcessDiagramPerspective;
import org.bonitasoft.studio.swtbot.framework.diagram.general.BotCallActivityMappingPropertySection;
import org.bonitasoft.studio.swtbot.framework.rule.SWTGefBotRule;
import org.bonitasoft.studio.test.swtbot.util.SWTBotTestUtil;
import org.eclipse.swtbot.eclipse.gef.finder.SWTBotGefTestCase;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SWTBotJunit4ClassRunner.class)
public class CallActivityMappingIT extends SWTBotGefTestCase {

    @Rule
    public SWTGefBotRule botRule = new SWTGefBotRule(bot);

    @Test
    public void testInputMappings() throws IOException {
        SWTBotTestUtil.importProcessWIthPathFromClass(
                bot,
                "ITTest-CallActivityMapping-1.0.bos",
                SWTBotTestUtil.IMPORTER_TITLE_BONITA,
                "TTest-CallActivityMapping",
                this.getClass(),
                false);
        final BotProcessDiagramPerspective botProcessDiagramPerspective = new BotProcessDiagramPerspective(bot);
        botProcessDiagramPerspective.activeProcessDiagramEditor().selectElement("Step With Known Called Process");
        final BotCallActivityMappingPropertySection botCallActivityMappingPropertySection = botProcessDiagramPerspective.getDiagramPropertiesPart()
                .selectGeneralTab().selectMappingTab();
        botCallActivityMappingPropertySection.addInputMapping();
        final String[] availableProcessData = bot.ccomboBoxWithId(SWTBotConstants.SWTBOT_ID_CALLACTIVITY_MAPPING_INPUT_SOURCEDATA, 0).items();
        assertThat(availableProcessData).containsOnly("processData1", "processData2", "stepData1");

        final String[] availableCalledProcessContractInput = bot.ccomboBoxWithId(SWTBotConstants.SWTBOT_ID_CALLACTIVITY_MAPPING_INPUT_CALLEDTARGET, 0).items();
        assertThat(availableCalledProcessContractInput).containsOnly("contractInput1", "contractInput2");

        botCallActivityMappingPropertySection.updateInputMapping(0, null, InputMappingAssignationType.DATA, "processData1");
        final String[] availableCalledProcessData = bot.ccomboBoxWithId(SWTBotConstants.SWTBOT_ID_CALLACTIVITY_MAPPING_INPUT_CALLEDTARGET, 0).items();
        assertThat(availableCalledProcessData).containsOnly("processData1", "processData2");

        botCallActivityMappingPropertySection.deleteInputMapping(0);
    }

}
