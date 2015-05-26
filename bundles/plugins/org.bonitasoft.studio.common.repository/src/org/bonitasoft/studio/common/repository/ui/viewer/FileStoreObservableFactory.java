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
package org.bonitasoft.studio.common.repository.ui.viewer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.eclipse.core.databinding.observable.IObservable;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.core.databinding.observable.masterdetail.IObservableFactory;

public class FileStoreObservableFactory implements IObservableFactory {

    /*
     * (non-Javadoc)
     * @see org.eclipse.core.databinding.observable.masterdetail.IObservableFactory#createObservable(java.lang.Object)
     */
    @Override
    public IObservable createObservable(final Object target) {
        if (target instanceof List<?>) {
            if (((List<?>) target).size() == 1) {
                return createObservable(((List<?>) target).get(0));
            }
            return new WritableList((Collection<?>) target, IRepositoryStore.class);
        } else if (target instanceof IRepositoryStore<?>) {
            final List<IRepositoryFileStore> result = new ArrayList<IRepositoryFileStore>();
            for (final Object child : ((IRepositoryStore<?>) target).getChildren()) {
                if (child instanceof IRepositoryFileStore && ((IRepositoryFileStore) child).canBeExported()) {
                    result.add((IRepositoryFileStore) child);
                }
            }
            return new WritableList(result, IRepositoryFileStore.class);
        }
        return new WritableList();
    }
}
