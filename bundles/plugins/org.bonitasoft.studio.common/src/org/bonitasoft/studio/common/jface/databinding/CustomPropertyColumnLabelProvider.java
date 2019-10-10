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

import org.eclipse.core.databinding.observable.map.IMapChangeListener;
import org.eclipse.core.databinding.observable.map.MapChangeEvent;
import org.eclipse.core.databinding.observable.set.IObservableSet;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.LabelProviderChangedEvent;
import org.eclipse.jface.viewers.ViewerColumn;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.IPropertySourceProvider;
import org.eclipse.ui.views.properties.PropertyColumnLabelProvider;

/**
 * Adds a listener on EMF Feature to triggered a label update when property value is changed
 */
public class CustomPropertyColumnLabelProvider extends PropertyColumnLabelProvider implements IMapChangeListener {

    private final IObservableSet knowElements;
    private final EStructuralFeature feature;

    public CustomPropertyColumnLabelProvider(final IPropertySourceProvider propertySourceProvider, final EStructuralFeature feature,
            final IObservableSet knowElements) {
        super(propertySourceProvider, feature.getName());
        this.knowElements = knowElements;
        this.feature = feature;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.viewers.CellLabelProvider#initialize(org.eclipse.jface.viewers.ColumnViewer, org.eclipse.jface.viewers.ViewerColumn)
     */
    @Override
    protected void initialize(final ColumnViewer viewer, final ViewerColumn column) {
        super.initialize(viewer, column);
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

    /*
     * (non-Javadoc)
     * @see org.eclipse.ui.views.properties.PropertyColumnLabelProvider#getText(java.lang.Object)
     */
    @Override
    public String getText(Object object) {
        final IPropertySource propertySource = propertySourceProvider
                .getPropertySource(object);
        if (propertySource != null) {
            return super.getText(object);
        }
        return null;
    }

}
