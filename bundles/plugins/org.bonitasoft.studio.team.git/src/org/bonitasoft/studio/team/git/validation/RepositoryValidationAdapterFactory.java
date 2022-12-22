/**
 * Copyright (C) 2022 BonitaSoft S.A.
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
package org.bonitasoft.studio.team.git.validation;

import org.bonitasoft.studio.common.core.IRunnableWithStatus;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.egit.core.GitProvider;

/**
 * Adapts a repository to a validation runnable
 * 
 * @author Vincent Hemery
 */
public class RepositoryValidationAdapterFactory implements IAdapterFactory {

    /*
     * (non-Javadoc)
     * @see org.eclipse.core.runtime.IAdapterFactory#getAdapter(java.lang.Object, java.lang.Class)
     */
    @Override
    public <T> T getAdapter(Object adaptableObject, Class<T> adapterType) {
        if (adaptableObject instanceof IRepository && adapterType.isAssignableFrom(RepositoryValidator.class)) {
            IRepository repository = (IRepository) adaptableObject;
            if (repository.isShared(GitProvider.ID)) {
                // adapt the repository to provide the validation process
                IRunnableWithStatus validator = new RepositoryValidator(repository);
                return (T) validator;
            }
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.core.runtime.IAdapterFactory#getAdapterList()
     */
    @Override
    public Class<?>[] getAdapterList() {
        return new Class[] { IRunnableWithStatus.class };
    }

}
