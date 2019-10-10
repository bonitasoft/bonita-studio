/**
 * Copyright (C) 2018 BonitaSoft S.A.
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
package org.bonitasoft.studio.actors.propertyTester;

import java.util.Objects;

import org.bonitasoft.studio.actors.repository.ActorFilterDefFileStore;
import org.bonitasoft.studio.actors.repository.ActorFilterDefRepositoryStore;
import org.bonitasoft.studio.actors.repository.ActorFilterImplFileStore;
import org.bonitasoft.studio.actors.repository.ActorFilterImplRepositoryStore;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;

public class ActorPropertyTester extends PropertyTester {

    public static final String ACTOR_DEF_FOLDER_PROPERTY = "isActorFilterDefFolder";
    public static final String ACTOR_DEF_FILE_PROPERTY = "isActorFilterDefFile";
    public static final String ACTOR_IMPL_FOLDER_PROPERTY = "isActorFilterImplFolder";
    public static final String ACTOR_IMPL_FILE_PROPERTY = "isActorFilterImplFile";

    @Override
    public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {

        RepositoryManager manager = RepositoryManager.getInstance();
        ActorFilterDefRepositoryStore defRepositoryStore = manager.getRepositoryStore(ActorFilterDefRepositoryStore.class);
        ActorFilterImplRepositoryStore implRepositoryStore = manager
                .getRepositoryStore(ActorFilterImplRepositoryStore.class);

        switch (property) {
            case ACTOR_DEF_FOLDER_PROPERTY:
                return isActorDefFolder((IAdaptable) receiver, defRepositoryStore);
            case ACTOR_DEF_FILE_PROPERTY:
                return isActorDefFile((IAdaptable) receiver, defRepositoryStore);
            case ACTOR_IMPL_FOLDER_PROPERTY:
                return isActorImplFolder((IAdaptable) receiver, implRepositoryStore);
            case ACTOR_IMPL_FILE_PROPERTY:
                return isActorImplFile((IAdaptable) receiver, implRepositoryStore);
            default:
                return false;
        }
    }

    private boolean isActorDefFolder(IAdaptable receiver, ActorFilterDefRepositoryStore store) {
        return Objects.equals(store.getResource(), receiver.getAdapter(IResource.class));
    }

    private boolean isActorImplFolder(IAdaptable receiver, ActorFilterImplRepositoryStore store) {
        return Objects.equals(store.getResource(), receiver.getAdapter(IResource.class));
    }

    private boolean isActorDefFile(IAdaptable receiver, ActorFilterDefRepositoryStore store) {
        IFile file = receiver.getAdapter(IFile.class);
        if (file != null) {
            return store.getChildren().stream()
                    .map(ActorFilterDefFileStore::getResource)
                    .anyMatch(file::equals);
        }
        return false;
    }

    private boolean isActorImplFile(IAdaptable receiver, ActorFilterImplRepositoryStore store) {
        IFile file = receiver.getAdapter(IFile.class);
        if (file != null) {
            return store.getChildren().stream()
                    .map(ActorFilterImplFileStore::getResource)
                    .anyMatch(file::equals);
        }
        return false;
    }

}
