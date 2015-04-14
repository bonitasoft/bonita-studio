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
package org.bonitasoft.studio.tests.contract;

import static org.assertj.core.api.Assertions.assertThat;

import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.model.process.Contract;
import org.bonitasoft.studio.model.process.ContractConstraint;
import org.bonitasoft.studio.model.process.ContractContainer;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.ContractInputType;
import org.bonitasoft.studio.model.process.assertions.ContractConstraintAssert;
import org.bonitasoft.studio.model.process.assertions.ContractInputAssert;
import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.bonitasoft.studio.swtbot.framework.application.BotApplicationWorkbenchWindow;
import org.bonitasoft.studio.swtbot.framework.diagram.BotProcessDiagramPerspective;
import org.bonitasoft.studio.swtbot.framework.diagram.general.contract.BotContractConstraintRow;
import org.bonitasoft.studio.swtbot.framework.diagram.general.contract.BotContractConstraintTab;
import org.bonitasoft.studio.swtbot.framework.diagram.general.contract.BotContractInputRow;
import org.bonitasoft.studio.swtbot.framework.diagram.general.contract.BotContractInputTab;
import org.bonitasoft.studio.swtbot.framework.diagram.general.contract.BotContractPropertySection;
import org.bonitasoft.studio.swtbot.framework.draw.BotGefProcessDiagramEditor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.swtbot.eclipse.gef.finder.SWTBotGefTestCase;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Romain Bioteau
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
        final ContractContainer contractContainer = (ContractContainer) botProcessDiagramPerspective.activeProcessDiagramEditor().selectElement("Step1")
                .getSelectedSemanticElement();
        createExpenseReport(botProcessDiagramPerspective, contractContainer);
    }

    @Test
    public void create_expense_report_process_contract() {
        final BotProcessDiagramPerspective botProcessDiagramPerspective = new BotApplicationWorkbenchWindow(bot).createNewDiagram();
        final BotGefProcessDiagramEditor activeProcessDiagramEditor = botProcessDiagramPerspective.activeProcessDiagramEditor();
        final ContractContainer contractContainer = (ContractContainer) activeProcessDiagramEditor.getSelectedSemanticElement();
        createExpenseReport(botProcessDiagramPerspective, contractContainer);

    }

    protected void createExpenseReport(final BotProcessDiagramPerspective botProcessDiagramPerspective, final ContractContainer contractContainer) {
        final BotContractPropertySection contractTabBot = botProcessDiagramPerspective
                .getDiagramPropertiesPart()
                .selectExecutionTab()
                .selectContractTab();
        final BotContractInputTab inputTab = contractTabBot.selectInputTab();
        final BotContractInputRow contractInputRow = inputTab.add();

        contractInputRow.setName("expenseReport").setDescription("An expense report").setType("COMPLEX");
        //        assertThat(task.getContract().getInputs()).hasSize(1).extracting("name", "type", "mandatory", "multiple", "description")
        //                .containsOnly(tuple("expenseReport", ContractInputType.COMPLEX, true, false, "An expense report"));

        //  contractTabBot.inputTable().select(0);

        BotContractInputRow childRow = contractInputRow.getChildRow(0);
        childRow.setName("expenseLines").clickMultiple().setType("COMPLEX");

        childRow = childRow.getChildRow(0);
        childRow.setName("nature").setDescription("The nature of the expense");

        childRow = inputTab.add();
        childRow.setName("amount").setType("DECIMAL").setDescription("The amount of the expense VAT included in euros");

        childRow = inputTab.add();
        childRow.setName("expenseDate").setType("DATE").setDescription("When the expense was done").clickMandatory();

        Contract contract = contractContainer.getContract();
        final EList<ContractInput> rootInputs = contract.getInputs();
        assertThat(rootInputs).hasSize(1);
        final ContractInput expenseReportInput = rootInputs.get(0);
        ContractInputAssert.assertThat(expenseReportInput).hasName("expenseReport").hasDescription("An expense report").hasType(ContractInputType.COMPLEX)
                .isMandatory();
        assertThat(expenseReportInput.getInputs()).hasSize(1);
        final ContractInput expenseLineInput = expenseReportInput.getInputs().get(0);
        ContractInputAssert.assertThat(expenseLineInput).hasName("expenseLines").hasType(ContractInputType.COMPLEX).isMultiple().isMandatory();
        assertThat(expenseLineInput.getInputs()).hasSize(3);
        final ContractInput natureInput = expenseLineInput.getInputs().get(0);
        final ContractInput amountInput = expenseLineInput.getInputs().get(1);
        final ContractInput dateInput = expenseLineInput.getInputs().get(2);

        ContractInputAssert.assertThat(natureInput).hasName("nature").hasType(ContractInputType.TEXT).isNotMultiple().isMandatory()
                .hasDescription("The nature of the expense");
        ContractInputAssert.assertThat(amountInput).hasName("amount").hasType(ContractInputType.DECIMAL).isNotMultiple().isMandatory()
                .hasDescription("The amount of the expense VAT included in euros");
        ContractInputAssert.assertThat(dateInput).hasName("expenseDate").hasType(ContractInputType.DATE).isNotMultiple().isNotMandatory()
                .hasDescription("When the expense was done");

        final BotContractConstraintTab constraintTab = contractTabBot.selectConstraintTab();
        final BotContractConstraintRow constraintRow = constraintTab.add();
        constraintRow.setName("Check empty report");
        constraintRow.setExpression("expenseReport.expenseLines.size() > 0");
        constraintRow.setErrorMessages("An expense report must have at lease one expense line");

        contract = contractContainer.getContract();
        assertThat(contract.getConstraints()).hasSize(1);
        final ContractConstraint constraint = contract.getConstraints().get(0);
        ContractConstraintAssert.assertThat(constraint).hasName("Check empty report");
        ContractConstraintAssert.assertThat(constraint).hasExpression("expenseReport.expenseLines.size() > 0");
        ContractConstraintAssert.assertThat(constraint).hasInputNames("expenseReport");
        assertThat(constraint.getErrorMessage()).isNotNull();
        ContractConstraintAssert.assertThat(constraint).hasErrorMessage("An expense report must have at lease one expense line");
    }

}
