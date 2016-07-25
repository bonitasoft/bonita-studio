/**
 * Copyright (C) 2014 BonitaSoft S.A.
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
package org.bonitasoft.studio.common.jface.selection;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;

/**
 * @author Romain Bioteau
 */
public abstract class EObjectAdaptableSelectionProvider implements ISelectionProvider, IAdaptable {

    protected ISelection selection;
    private final List<ISelectionChangedListener> listeners = new ArrayList<ISelectionChangedListener>();

    @Override
    public void addSelectionChangedListener(final ISelectionChangedListener listener) {
        if (!listeners.contains(listener)) {
            listeners.add(listener);
        }
    }

    @Override
    public void removeSelectionChangedListener(final ISelectionChangedListener listener) {
        if (listeners.contains(listener)) {
            listeners.remove(listener);
        }
    }

    @Override
    public ISelection getSelection() {
        return selection;
    }

    @Override
    public void setSelection(final ISelection selection) {
        if (!(selection instanceof IStructuredSelection)) {
            return;
        }
        this.selection = selection;
        this.selection = adaptSelection((IStructuredSelection) selection);
        fireSelectionChanged(this.selection);
    }

    private ISelection adaptSelection(final IStructuredSelection selection) {
        final Object element = unwrap(selection);
        if (element instanceof IAdaptable) {
            final Object adapter = getAdapter(EObject.class);
            if (adapter != null) {
                return new StructuredSelection(adapter);
            }
        }
        return new StructuredSelection();
    }

    protected Object unwrap(final ISelection selection) {
        if (selection instanceof IStructuredSelection) {
            return ((IStructuredSelection) selection).getFirstElement();
        }
        return null;
    }

    private void fireSelectionChanged(final ISelection selection) {
        for (final ISelectionChangedListener iSelectionChangedListener : listeners) {
            iSelectionChangedListener.selectionChanged(new SelectionChangedEvent(this, selection));
        }
    }

    @SuppressWarnings("rawtypes")
    @Override
    public abstract Object getAdapter(Class adapter);
}
