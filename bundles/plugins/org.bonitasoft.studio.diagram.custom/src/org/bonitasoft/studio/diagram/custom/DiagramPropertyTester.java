/**
 * Copyright (C) 2018 Bonitasoft S.A.
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
package org.bonitasoft.studio.diagram.custom;

import java.util.Objects;

import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.IAdaptable;

public class DiagramPropertyTester extends PropertyTester {

    public static final String DIAGRAM_FOLDER_PROPERTY = "isDiagramFolder";

    @Override
    public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
        DiagramRepositoryStore store = RepositoryManager.getInstance().getRepositoryStore(DiagramRepositoryStore.class);
        if (Objects.equals(property, DIAGRAM_FOLDER_PROPERTY)) {
            return isDiagramFolder((IAdaptable) receiver, store);
        }
        return false;
    }

    private boolean isDiagramFolder(IAdaptable receiver, DiagramRepositoryStore repositoryStore) {
        return Objects.equals(receiver.getAdapter(IFolder.class), repositoryStore.getResource());
    }

}
