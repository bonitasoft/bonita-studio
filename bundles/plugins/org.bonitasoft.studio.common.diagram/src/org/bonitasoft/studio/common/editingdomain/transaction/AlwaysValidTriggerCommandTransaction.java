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

import java.util.Map;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.transaction.impl.InternalTransactionalEditingDomain;
import org.eclipse.emf.transaction.impl.TriggerCommandTransaction;
import org.eclipse.emf.transaction.util.TriggerCommand;

public class AlwaysValidTriggerCommandTransaction extends TriggerCommandTransaction {

    public AlwaysValidTriggerCommandTransaction(final TriggerCommand command, final InternalTransactionalEditingDomain domain, final Map<?, ?> options) {
        super(command, domain, options);
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.emf.transaction.impl.TransactionImpl#validate()
     */
    @Override
    protected IStatus validate() {
        super.validate();
        return Status.OK_STATUS;
    }
}
