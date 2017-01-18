/**
 * Copyright (C) 2016 Bonitasoft S.A.
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
package org.bonitasoft.studio.ui.databinding;

import org.eclipse.core.databinding.ValidationStatusProvider;
import org.eclipse.core.databinding.observable.DisposeEvent;
import org.eclipse.core.databinding.observable.IDisposeListener;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.IValueChangeListener;
import org.eclipse.core.databinding.observable.value.ValueChangeEvent;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IStatus;

public abstract class ControlMessageSupport {

    private IObservableValue validationStatus;
    private IObservableList targets;

    private IDisposeListener disposeListener = new IDisposeListener() {

        @Override
        public void handleDispose(DisposeEvent staleEvent) {
            dispose();
        }
    };

    private IValueChangeListener statusChangeListener = new IValueChangeListener() {

        @Override
        public void handleValueChange(ValueChangeEvent event) {
            if (statusHasChanged(event)) {
                statusChanged((IStatus) validationStatus.getValue());
            }
        }
    };

    public ControlMessageSupport(ValidationStatusProvider validationStatusProvider) {
        this.validationStatus = validationStatusProvider.getValidationStatus();
        Assert.isTrue(!this.validationStatus.isDisposed());

        this.targets = validationStatusProvider.getTargets();
        Assert.isTrue(!this.targets.isDisposed());

        validationStatus.addDisposeListener(disposeListener);
        validationStatus.addValueChangeListener(statusChangeListener);
    }

    protected boolean statusHasChanged(ValueChangeEvent event) {
        final IStatus newStatus = (IStatus) event.diff.getNewValue();
        final IStatus oldStatus = (IStatus) event.diff.getOldValue();
        return oldStatus != newStatus;
    }

    protected abstract void statusChanged(IStatus status);

    public void dispose() {
        if (validationStatus != null) {
            validationStatus.removeDisposeListener(disposeListener);
            validationStatus.removeValueChangeListener(statusChangeListener);
            validationStatus = null;
        }

        if (targets != null) {
            targets.removeDisposeListener(disposeListener);
            targets = null;
        }

        disposeListener = null;
        statusChangeListener = null;
    }
}
