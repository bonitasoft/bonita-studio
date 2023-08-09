/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.identity.actors.repository;

import java.util.Optional;

import org.bonitasoft.studio.common.repository.model.IRenamable;
import org.bonitasoft.studio.common.repository.store.AbstractEMFRepositoryStore;
import org.bonitasoft.studio.connector.model.definition.AbstractDefFileStore;
import org.bonitasoft.studio.identity.IdentityPlugin;
import org.eclipse.ui.IWorkbenchPart;
import org.osgi.framework.Bundle;

/**
 * @author Romain Bioteau
 * @author Baptiste Mesta
 */
public class ActorFilterDefFileStore extends AbstractDefFileStore implements IRenamable {

    public ActorFilterDefFileStore(String fileName, AbstractEMFRepositoryStore<ActorFilterDefFileStore> store) {
        super(fileName, store);
    }

    @Override
    protected Bundle getBundle() {
        return IdentityPlugin.getDefault().getBundle();
    }

    @Override
    protected IWorkbenchPart doOpen() {
        return null;
    }

    @Override
    public void rename(String newName) {
        doOpen();
    }

    @Override
    public Optional<String> retrieveNewName() {
        return Optional.of("");
    }
}
