/**
 * Copyright (C) 2010 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.common.editingdomain;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;

public class BonitaResourceSetInfoAdapter extends AdapterImpl {

	private BonitaResourceSetInfoDelegate sharedResourceSetInfoDelegate;

	@Override
	public void setTarget(Notifier newTarget) {
		super.setTarget(newTarget);
		TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(newTarget);
		if(editingDomain != null) {
			sharedResourceSetInfoDelegate = new BonitaResourceSetInfoDelegate(editingDomain);
		}
	}
	
	public BonitaResourceSetInfoDelegate getSharedResourceSetInfoDelegate() {
		return sharedResourceSetInfoDelegate;
	}

	@Override
	public boolean isAdapterForType(Object type) {
		return type == BonitaResourceSetInfoDelegate.class;
	}

	@Override
	public void unsetTarget(Notifier oldTarget) {
		super.unsetTarget(oldTarget);
		sharedResourceSetInfoDelegate = null;
	}
	
	public static class ResourceSetFactory extends AdapterFactoryImpl {
		@Override
		protected Adapter createAdapter(Notifier target) {
			return new BonitaResourceSetInfoAdapter();
		}

		@Override
		public boolean isFactoryForType(Object type) {
			return type == BonitaResourceSetInfoDelegate.class;
		}
	}
}
