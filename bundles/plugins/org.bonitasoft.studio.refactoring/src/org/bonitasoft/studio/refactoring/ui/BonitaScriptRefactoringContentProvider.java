/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.refactoring.ui;

import org.eclipse.compare.structuremergeviewer.DiffNode;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class BonitaScriptRefactoringContentProvider implements
        ITreeContentProvider {

    @Override
    public void dispose() {

    }

    @Override
    public void inputChanged(Viewer viewer, Object oldInput,
            Object newInput) {

    }

    @Override
    public Object[] getElements(Object inputElement) {
        if (inputElement instanceof DiffNode) {
            return ((DiffNode) inputElement).getChildren();
        }
        return null;
    }

    @Override
    public Object[] getChildren(Object parentElement) {
        if (((DiffNode) parentElement).hasChildren()) {
            return ((DiffNode) parentElement).getChildren();
        }
        return null;
    }

    @Override
    public Object getParent(Object element) {
        return null;
    }

    @Override
    public boolean hasChildren(Object element) {
        final int length = ((DiffNode) element).getChildren().length;
        return length != 0;
    }

}
