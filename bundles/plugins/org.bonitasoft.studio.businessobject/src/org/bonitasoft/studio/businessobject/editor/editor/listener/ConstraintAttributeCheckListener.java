/**
 * Copyright (C) 2019 Bonitasoft S.A.
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
package org.bonitasoft.studio.businessobject.editor.editor.listener;

import org.bonitasoft.studio.businessobject.editor.model.Field;
import org.bonitasoft.studio.businessobject.editor.model.UniqueConstraint;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.TableViewer;

public class ConstraintAttributeCheckListener implements ICheckStateListener {

    private IObservableValue<UniqueConstraint> selectedConstraintObservable;
    private IObservableValue<Field> selectedAttributeObservable;
    private TableViewer constraintViewer;

    public ConstraintAttributeCheckListener(IObservableValue<UniqueConstraint> selectedConstraintObservable,
            IObservableValue<Field> selectedAttributeObservable, TableViewer constraintViewer) {
        this.selectedConstraintObservable = selectedConstraintObservable;
        this.selectedAttributeObservable = selectedAttributeObservable;
        this.constraintViewer = constraintViewer;
    }

    @Override
    public void checkStateChanged(CheckStateChangedEvent e) {
        Field element = (Field) e.getElement();
        if (e.getChecked()) {
            selectedConstraintObservable.getValue().getFieldNames().add(element.getName());
        } else {
            selectedConstraintObservable.getValue().getFieldNames().remove(element.getName());
        }
        selectedAttributeObservable.setValue(element);
        constraintViewer.refresh(selectedConstraintObservable.getValue());
    }
}
