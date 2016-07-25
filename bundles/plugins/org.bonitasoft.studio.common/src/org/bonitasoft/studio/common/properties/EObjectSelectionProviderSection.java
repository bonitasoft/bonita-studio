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
package org.bonitasoft.studio.common.properties;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.model.process.Pool;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IWorkbenchPart;


/**
 * @author Romain Bioteau
 *
 */
public abstract class EObjectSelectionProviderSection extends AbstractBonitaDescriptionSection implements ISelectionProvider {

    private ISelection selection;
    private final List<ISelectionChangedListener> listeners = new ArrayList<ISelectionChangedListener>();
    private IObservableValue selectionObservable;
    private IObservableValue editingDomainObservable;
    private IObservableValue containerObservable;
    private Pool poolContainer;

    protected IObservableValue getEObjectObservable() {
        if (selectionObservable == null) {
            selectionObservable = ViewersObservables.observeSingleSelection(this);
        }
        return selectionObservable;
    }

    protected IObservableValue getPoolObservable() {
        if (containerObservable == null) {
            containerObservable = PojoObservables.observeValue(this, "poolContainer");
        }
        return containerObservable;
    }

    protected IObservableValue getEditingDomainObservable() {
        if (editingDomainObservable == null) {
            editingDomainObservable = PojoObservables.observeValue(this, "EditingDomain");
        }
        return editingDomainObservable;
    }

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
    public void setSelection(final ISelection selection) {
        this.selection = selection;
        fireSelectionChanged(this.selection);
    }

    @Override
    public ISelection getSelection() {
        return selection;
    }

    private void fireSelectionChanged(final ISelection newSelection) {
        for (final ISelectionChangedListener l : listeners) {
            l.selectionChanged(new SelectionChangedEvent(this, newSelection));
        }
    }

    @Override
    public void setInput(final IWorkbenchPart part, final ISelection selection) {
        super.setInput(part, selection);
        final EObject semanticElement = (EObject) ((IAdaptable) ((IStructuredSelection) selection).getFirstElement()).getAdapter(EObject.class);
        setSelection(new StructuredSelection(adaptEObject(semanticElement)));
        setPoolContainer(ModelHelper.getParentPool(semanticElement));
    }

    protected EObject adaptEObject(final EObject semanticElement) {
        return semanticElement;
    }

    public Pool getPoolContainer() {
        return poolContainer;
    }

    public void setPoolContainer(final Pool poolContainer) {
        this.poolContainer = poolContainer;
    }

}
