/**
 * Copyright (C) 2015 BonitaSoft S.A.
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
package org.bonitasoft.studio.contract.core.refactoring;

import static com.google.common.collect.Lists.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;
import static org.bonitasoft.studio.model.expression.builders.ExpressionBuilder.anExpression;
import static org.bonitasoft.studio.model.process.builders.ContractInputBuilder.aContractInput;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.refactoring.core.RefactoringOperationType;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.impl.TransactionalEditingDomainImpl;
import org.junit.Before;
import org.junit.Test;

public class ContractInputScriptExpressionRefactoringActionTest {

    private EditingDomain editingDomain;

    @Before
    public void setUp() throws Exception {
        editingDomain = new TransactionalEditingDomainImpl(new AdapterFactoryImpl());
    }

    @Test
    public void find_contract_input_in_expression_referenced_elements() throws Exception {
        final List<ContractInputRefactorPair> pairsToRefactor = newArrayList(
                new ContractInputRefactorPair(aContractInput().withName("newName").build(), aContractInput().withName("oldName").build()),
                new ContractInputRefactorPair(aContractInput().withName("fName").build(), aContractInput().withName("name").build()));
        final ContractInputScriptExpressionRefactoringAction refactoringAction = new ContractInputScriptExpressionRefactoringAction(pairsToRefactor,
                Collections.<Expression> emptyList(),
                Collections.<Expression> emptyList(),
                new CompoundCommand(),
                editingDomain,
                RefactoringOperationType.UPDATE);

        final ContractInput referencedInput = aContractInput().withName("oldName").build();
        final Map<EObject, EObject> referencedObjectMap = refactoringAction.getReferencedObjectInScriptsOperation(anExpression()
                .havingReferencedElements(referencedInput).build());

        assertThat(referencedObjectMap)
                .containsOnly(entry(referencedInput, pairsToRefactor.get(0).getNewValue()));

    }
}
