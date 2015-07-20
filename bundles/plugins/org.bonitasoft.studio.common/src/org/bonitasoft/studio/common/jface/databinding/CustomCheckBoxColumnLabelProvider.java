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
package org.bonitasoft.studio.common.jface.databinding;

import java.util.Set;

import org.bonitasoft.studio.common.jface.AbstractCheckboxLabelProvider;
import org.eclipse.core.databinding.observable.map.IMapChangeListener;
import org.eclipse.core.databinding.observable.map.MapChangeEvent;
import org.eclipse.core.databinding.observable.set.IObservableSet;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.LabelProviderChangedEvent;

public abstract class CustomCheckBoxColumnLabelProvider extends AbstractCheckboxLabelProvider implements IMapChangeListener {

    public CustomCheckBoxColumnLabelProvider(final ColumnViewer viewer, final EStructuralFeature feature,
            final IObservableSet knowElements) {
        super(viewer);
        if (knowElements != null && feature != null) {
            EMFObservables.observeMap(knowElements, feature).addMapChangeListener(this);
        }
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.core.databinding.observable.map.IMapChangeListener#handleMapChange(org.eclipse.core.databinding.observable.map.MapChangeEvent)
     */
    @Override
    public void handleMapChange(final MapChangeEvent event) {
        final Set<?> affectedElements = event.diff.getChangedKeys();
        if (!affectedElements.isEmpty()) {
            fireLabelProviderChanged(new LabelProviderChangedEvent(this, affectedElements.toArray()));
        }
    }

}
