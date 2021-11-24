/**
 * Copyright (C) 2016 Bonitasoft S.A.
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
package org.bonitasoft.studio.validation.constraints.process;

import static org.bonitasoft.studio.model.process.builders.ContractInputBuilder.aContractInput;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.bonitasoft.studio.contract.i18n.Messages;
import org.bonitasoft.studio.model.process.ContractInputType;
import org.eclipse.emf.validation.IValidationContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author Romain Bioteau
 */
@RunWith(MockitoJUnitRunner.class)
public class ContractLongTypeConstraintTest {

    @Mock
    private IValidationContext ctx;

    @Test
    public void should_create_a_failure_if_input_type_is_LONG() throws Exception {
        final ContractLongTypeConstraint contractConstraint = new ContractLongTypeConstraint();
        when(ctx.getTarget()).thenReturn(aContractInput().withType(ContractInputType.LONG).build());

        contractConstraint.performBatchValidation(ctx);

        verify(ctx).createFailureStatus(Messages.longConversionWarning);
    }

    @Test
    public void should_create_a_success_if_input_type_is_not_LONG() throws Exception {
        final ContractLongTypeConstraint contractConstraint = new ContractLongTypeConstraint();
        when(ctx.getTarget()).thenReturn(aContractInput().withType(ContractInputType.TEXT).build());

        contractConstraint.performBatchValidation(ctx);

        verify(ctx).createSuccessStatus();
    }

}
