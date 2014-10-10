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
package org.bonitasoft.studio.tests.contract;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.model.process.Task;
import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.bonitasoft.studio.swtbot.framework.application.BotApplicationWorkbenchWindow;
import org.bonitasoft.studio.swtbot.framework.diagram.BotProcessDiagramPerspective;
import org.bonitasoft.studio.swtbot.framework.diagram.general.contract.BotContractInputRow;
import org.bonitasoft.studio.swtbot.framework.diagram.general.contract.BotContractPropertySection;
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
public class ContractIT extends SWTBotGefTestCase {

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
    public void add_remove_input_to_a_step_contract() {
        final BotProcessDiagramPerspective botProcessDiagramPerspective = new BotApplicationWorkbenchWindow(bot).createNewDiagram();
        final Task task = (Task) botProcessDiagramPerspective.activeProcessDiagramEditor().selectElement("Step1")
                .getSelectedSemanticElement();
        final BotContractPropertySection contractTabBot = botProcessDiagramPerspective
                .getDiagramPropertiesPart()
                .selectGeneralTab()
                .selectContractTab();
        final BotContractInputRow contractInputRow = contractTabBot.add();
        contractInputRow.setName("myInputName").setDescription("A short description");
        assertThat(task.getContract().getInputs()).hasSize(1).extracting("name", "mandatory", "multiple", "description")
        .containsOnly(tuple("myInputName", true, false, "A short description"));
        contractInputRow.clickMultiple();
        contractInputRow.clickMandatory();
        assertThat(task.getContract().getInputs()).hasSize(1).extracting("name", "mandatory", "multiple", "description")
        .containsOnly(tuple("myInputName", false, true, "A short description"));

        contractTabBot.inputTable().select(0);
        contractTabBot.remove();
        assertThat(task.getContract().getInputs()).hasSize(0);
    }

}
