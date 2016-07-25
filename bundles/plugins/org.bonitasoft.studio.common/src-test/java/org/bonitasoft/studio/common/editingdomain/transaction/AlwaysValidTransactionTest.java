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
package org.bonitasoft.studio.common.editingdomain.transaction;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;

import org.bonitasoft.studio.assertions.StatusAssert;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.transaction.Transaction;
import org.eclipse.emf.transaction.impl.TransactionalEditingDomainImpl;
import org.junit.Test;

public class AlwaysValidTransactionTest {

    @Test
    public void should_validate_always_return_OK_status() throws Exception {
        final AlwaysValidTransaction alwaysValidTransaction = new AlwaysValidTransaction(new TransactionalEditingDomainImpl(new AdapterFactoryImpl()), false,
                Collections.emptyMap());

        StatusAssert.assertThat(alwaysValidTransaction.validate()).isOK();
    }

    @Test
    public void should_undo_redo_options_trigger_validation() throws Exception {
        assertThat(AlwaysValidTransaction.DEFAULT_UNDO_REDO_OPTIONS).containsEntry(Transaction.OPTION_NO_VALIDATION, Boolean.FALSE);
    }

}
