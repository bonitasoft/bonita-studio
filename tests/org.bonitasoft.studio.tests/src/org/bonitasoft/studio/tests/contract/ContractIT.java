/**
 * Copyright (C) 2014-2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.tests.contract;

import static com.google.common.collect.Iterables.find;
import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.common.predicate.ContractInputPredicates.withContractInputName;

import java.util.List;

import org.bonitasoft.studio.model.process.Contract;
import org.bonitasoft.studio.model.process.ContractConstraint;
import org.bonitasoft.studio.model.process.ContractContainer;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.ContractInputType;
import org.bonitasoft.studio.model.process.assertions.ContractConstraintAssert;
import org.bonitasoft.studio.model.process.assertions.ContractInputAssert;
import org.bonitasoft.studio.swtbot.framework.application.BotApplicationWorkbenchWindow;
import org.bonitasoft.studio.swtbot.framework.conditions.AssertionCondition;
import org.bonitasoft.studio.swtbot.framework.diagram.BotProcessDiagramPerspective;
import org.bonitasoft.studio.swtbot.framework.diagram.general.contract.BotContractConstraintRow;
import org.bonitasoft.studio.swtbot.framework.diagram.general.contract.BotContractConstraintTab;
import org.bonitasoft.studio.swtbot.framework.diagram.general.contract.BotContractInputRow;
import org.bonitasoft.studio.swtbot.framework.diagram.general.contract.BotContractInputTab;
import org.bonitasoft.studio.swtbot.framework.diagram.general.contract.BotContractPropertySection;
import org.bonitasoft.studio.swtbot.framework.draw.BotGefProcessDiagramEditor;
import org.bonitasoft.studio.swtbot.framework.rule.SWTGefBotRule;
import org.eclipse.emf.common.util.EList;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SWTBotJunit4ClassRunner.class)
public class ContractIT {

    private final SWTGefBot bot = new SWTGefBot();

    @Rule
    public SWTGefBotRule botRule = new SWTGefBotRule(bot);

    @Test
    public void create_expense_report_step_contract() {
        final BotProcessDiagramPerspective botProcessDiagramPerspective = new BotApplicationWorkbenchWindow(bot)
                .createNewDiagram();
        final ContractContainer contractContainer = (ContractContainer) botProcessDiagramPerspective
                .activeProcessDiagramEditor().selectElement("Step1")
                .getSelectedSemanticElement();
        createExpenseReport(botProcessDiagramPerspective, contractContainer);
    }

    @Test
    public void create_expense_report_process_contract() {
        final BotProcessDiagramPerspective botProcessDiagramPerspective = new BotApplicationWorkbenchWindow(bot)
                .createNewDiagram();
        final BotGefProcessDiagramEditor activeProcessDiagramEditor = botProcessDiagramPerspective
                .activeProcessDiagramEditor();
        final ContractContainer contractContainer = (ContractContainer) activeProcessDiagramEditor
                .getSelectedSemanticElement();
        createExpenseReport(botProcessDiagramPerspective, contractContainer);
    }

    protected void createExpenseReport(final BotProcessDiagramPerspective botProcessDiagramPerspective,
            final ContractContainer contractContainer) {
        final BotContractPropertySection contractTabBot = botProcessDiagramPerspective
                .getDiagramPropertiesPart()
                .selectExecutionTab()
                .selectContractTab();
        final BotContractInputTab inputTab = contractTabBot.selectInputTab();
        final BotContractInputRow contractInputRow = inputTab.add();

        contractInputRow.setName("expenseReport").setType("COMPLEX - java.util.Map").setDescription("An expense report");

        BotContractInputRow childRow = contractInputRow.getChildRow(0);
        childRow.setName("expenseLines").setType("COMPLEX - java.util.Map").clickMultiple();

        childRow = childRow.getChildRow(0);
        childRow.setName("nature").setDescription("The nature of the expense");

        childRow = inputTab.add();
        childRow.setName("amount").setType("DECIMAL - java.lang.Double")
                .setDescription("The amount of the expense VAT included in euros");

        childRow = inputTab.add();
        childRow.setName("expenseDate").setType("DATE ONLY - java.time.LocalDate")
                .setDescription("When the expense was done");

        bot.waitUntil(new AssertionCondition() {

            @Override
            protected void makeAssert() throws Exception {
                checkContractInput(contractContainer);
            }
        });

        final BotContractConstraintTab constraintTab = contractTabBot.selectConstraintTab();
        final BotContractConstraintRow constraintRow = constraintTab.add();
        constraintRow.setName("Check empty report");
        constraintRow.setExpression("expenseReport.expenseLines.size() > 0");
        constraintRow.setErrorMessages("An expense report must have at lease one expense line");

        bot.waitUntil(new AssertionCondition() {

            @Override
            protected void makeAssert() throws Exception {
                checkContractConstraint(contractContainer);
            }
        });

    }

    protected void checkContractConstraint(final ContractContainer contractContainer) throws Exception {
        final Contract contract = contractContainer.getContract();
        assertThat(contract.getConstraints()).hasSize(1);
        final ContractConstraint constraint = contract.getConstraints().get(0);
        ContractConstraintAssert.assertThat(constraint)
                .hasName("Check empty report")
                .hasExpression("expenseReport.expenseLines.size() > 0")
                .hasInputNames("expenseReport")
                .hasErrorMessage("An expense report must have at lease one expense line");
    }

    protected void checkContractInput(final ContractContainer contractContainer) throws Exception {
        final Contract contract = contractContainer.getContract();
        final EList<ContractInput> rootInputs = contract.getInputs();
        assertThat(rootInputs).hasSize(1);
        final ContractInput expenseReportInput = rootInputs.get(0);
        ContractInputAssert.assertThat(expenseReportInput).hasName("expenseReport").hasDescription("An expense report")
                .hasType(ContractInputType.COMPLEX);
        assertThat(expenseReportInput.getInputs()).hasSize(1);
        final ContractInput expenseLineInput = find(expenseReportInput.getInputs(), withContractInputName("expenseLines"));
        ContractInputAssert.assertThat(find(expenseReportInput.getInputs(), withContractInputName("expenseLines")))
                .hasType(ContractInputType.COMPLEX)
                .isMultiple();
        final List<ContractInput> expenseLineInputs = expenseLineInput.getInputs();
        assertThat(expenseLineInputs).hasSize(3);
        ContractInputAssert.assertThat(find(expenseLineInputs, withContractInputName("nature")))
                .hasType(ContractInputType.TEXT)
                .isNotMultiple()
                .hasDescription("The nature of the expense");
        ContractInputAssert.assertThat(find(expenseLineInputs, withContractInputName("amount")))
                .hasType(ContractInputType.DECIMAL)
                .isNotMultiple()
                .hasDescription("The amount of the expense VAT included in euros");
        ContractInputAssert.assertThat(find(expenseLineInputs, withContractInputName("expenseDate")))
                .hasType(ContractInputType.LOCALDATE)
                .isNotMultiple()
                .hasDescription("When the expense was done");
    }
}
