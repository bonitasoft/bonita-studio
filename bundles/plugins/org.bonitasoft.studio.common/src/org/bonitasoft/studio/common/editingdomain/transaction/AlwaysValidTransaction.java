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

import java.util.Collections;
import java.util.Map;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.transaction.Transaction;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.impl.TransactionImpl;

public class AlwaysValidTransaction extends TransactionImpl {

    public static final Map<Object, Object> DEFAULT_UNDO_REDO_OPTIONS;
    static {
        final Map<Object, Object> map = new java.util.HashMap<Object, Object>();
        map.put(Transaction.OPTION_NO_TRIGGERS, Boolean.TRUE);
        map.put(Transaction.OPTION_NO_UNDO, Boolean.TRUE);
        map.put(Transaction.OPTION_NO_VALIDATION, Boolean.FALSE);
        map.put(Transaction.OPTION_IS_UNDO_REDO_TRANSACTION, Boolean.TRUE);
        DEFAULT_UNDO_REDO_OPTIONS = Collections.unmodifiableMap(map);
    }

    public AlwaysValidTransaction(final TransactionalEditingDomain domain, final boolean readOnly, final Map<?, ?> options) {
        super(domain, readOnly, options);
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
