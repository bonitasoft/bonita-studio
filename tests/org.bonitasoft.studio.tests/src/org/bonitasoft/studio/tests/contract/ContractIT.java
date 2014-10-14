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
    public void create_expense_report_step_contract() {
        final BotProcessDiagramPerspective botProcessDiagramPerspective = new BotApplicationWorkbenchWindow(bot).createNewDiagram();
        final Task task = (Task) botProcessDiagramPerspective.activeProcessDiagramEditor().selectElement("Step1")
                .getSelectedSemanticElement();
        final BotContractPropertySection contractTabBot = botProcessDiagramPerspective
                .getDiagramPropertiesPart()
                .selectGeneralTab()
                .selectContractTab();
        final BotContractInputRow contractInputRow = contractTabBot.add();

        contractInputRow.setName("expenseReport").setDescription("An expense report").setType("COMPLEX");
        //        assertThat(task.getContract().getInputs()).hasSize(1).extracting("name", "type", "mandatory", "multiple", "description")
        //                .containsOnly(tuple("expenseReport", ContractInputType.COMPLEX, true, false, "An expense report"));

        //  contractTabBot.inputTable().select(0);

        BotContractInputRow childRow = contractTabBot.inputTable().selectActiveRow(bot);
        childRow.setName("expenseLines").clickMultiple().setType("COMPLEX");

        childRow = contractTabBot.inputTable().selectRow(bot, 2);
        childRow.setName("nature").setDescription("The nature of the expense (eg: Restaurant, transport, hotel...etc)");

        childRow = contractTabBot.add();
        childRow.setName("amount").setType("DECIMAL").setDescription("The amount of the expense VAT included in euros.");


        assertThat(task.getContract().getInputs()).hasSize(1);
    }

}
