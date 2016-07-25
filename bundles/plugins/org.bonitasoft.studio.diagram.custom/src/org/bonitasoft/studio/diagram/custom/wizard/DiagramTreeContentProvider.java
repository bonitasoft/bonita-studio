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
package org.bonitasoft.studio.diagram.custom.wizard;

import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * @author Romain Bioteau
 */
public class DiagramTreeContentProvider implements ITreeContentProvider {

    private final DiagramRepositoryStore diagramSotre;

    public DiagramTreeContentProvider() {
        diagramSotre = RepositoryManager.getInstance().getCurrentRepository().getRepositoryStore(DiagramRepositoryStore.class);
    }

    @Override
    public void dispose() {

    }

    @Override
    public void inputChanged(final Viewer arg0, final Object arg1, final Object arg2) {

    }

    @Override
    public Object[] getChildren(final Object element) {
        return null;
    }

    @Override
    public Object[] getElements(final Object element) {
        return diagramSotre.getChildren().toArray();
    }

    @Override
    public Object getParent(final Object element) {
        return null;
    }

    @Override
    public boolean hasChildren(final Object element) {
        return false;
    }

}
