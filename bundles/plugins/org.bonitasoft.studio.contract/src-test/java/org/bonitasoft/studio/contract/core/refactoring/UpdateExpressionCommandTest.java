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
package org.bonitasoft.studio.contract.core.refactoring;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.common.emf.tools.ExpressionHelper.createContractInputExpression;
import static org.bonitasoft.studio.model.expression.builders.ExpressionBuilder.anExpression;
import static org.bonitasoft.studio.model.process.builders.ContractInputBuilder.aContractInput;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.assertions.ExpressionAssert;
import org.bonitasoft.studio.model.expression.provider.ExpressionItemProviderAdapterFactory;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.ContractInputType;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.impl.TransactionalCommandStackImpl;
import org.eclipse.emf.transaction.impl.TransactionalEditingDomainImpl;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Romain Bioteau
 */
public class UpdateExpressionCommandTest {

    private EditingDomain editingDomain;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        editingDomain = new TransactionalEditingDomainImpl(new ExpressionItemProviderAdapterFactory(), new TransactionalCommandStackImpl());
    }

    @Test
    public void update_a_contract_input_expression_from_anpther_contract_input_expression() throws Exception {
        final Expression owner = anExpression().withName("name").withContent("name").withExpressionType(ExpressionConstants.CONTRACT_INPUT_TYPE)
                .withReturnType(String.class.getName()).build();
        final ContractInput contractInput = aContractInput().withType(ContractInputType.BOOLEAN)
                .withName("isValid").build();
        final UpdateExpressionCommand command = new UpdateExpressionCommand(editingDomain, owner,
                createContractInputExpression(contractInput));

        command.execute();

        ExpressionAssert.assertThat(owner).hasName("isValid").hasReturnType(Boolean.class.getName()).hasContent("isValid")
                .hasType(ExpressionConstants.CONTRACT_INPUT_TYPE);
        assertThat(owner.getReferencedElements()).hasSize(1);
        assertThat(EcoreUtil.equals(contractInput, owner.getReferencedElements().get(0))).isTrue();
    }
}
