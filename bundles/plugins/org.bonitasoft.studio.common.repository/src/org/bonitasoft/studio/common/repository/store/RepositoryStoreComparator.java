/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.common.repository.store;

import java.util.Comparator;

import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;

/**
 * @author Romain Bioteau
 * 
 */
public class RepositoryStoreComparator implements Comparator<IRepositoryStore<? extends IRepositoryFileStore>> {

    @Override
    public int compare(IRepositoryStore<? extends IRepositoryFileStore> f1, IRepositoryStore<? extends IRepositoryFileStore> f2) {
        String f1Name = f1.getDisplayName() == null ? f1.getName() : f1.getDisplayName();
        String f2Name = f2.getDisplayName() == null ? f2.getName() : f2.getDisplayName();
        return f1Name.compareTo(f2Name);
    }

}
