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
package org.bonitasoft.studio.common.jface;

import org.eclipse.core.databinding.observable.value.IValueChangeListener;
import org.eclipse.core.databinding.observable.value.ValueChangeEvent;
import org.eclipse.jface.viewers.ColumnViewer;

/**
 * @author Romain Bioteau
 */
public class ColumnViewerUpdateListener implements IValueChangeListener {

    private final ColumnViewer viewer;

    private final Object element;

    public ColumnViewerUpdateListener(ColumnViewer viewer, Object element) {
        this.viewer = viewer;
        this.element = element;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.core.databinding.observable.value.IValueChangeListener#handleValueChange(org.eclipse.core.databinding.observable.value.ValueChangeEvent)
     */
    @Override
    public void handleValueChange(ValueChangeEvent event) {
        viewer.getControl().getDisplay().asyncExec(new Runnable() {

            @Override
            public void run() {
                viewer.update(element, null);
            }
        });

    }

}
