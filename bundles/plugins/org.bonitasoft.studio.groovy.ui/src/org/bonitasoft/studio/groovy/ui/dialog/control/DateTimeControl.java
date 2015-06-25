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
package org.bonitasoft.studio.groovy.ui.dialog.control;

import java.util.Date;

import org.bonitasoft.studio.common.jface.databinding.DateTimeObservable;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.IValueChangeListener;
import org.eclipse.core.databinding.observable.value.ValueChangeEvent;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Event;

public class DateTimeControl extends Composite {

    private final DateTime dateControl;
    private final DateTime timeControl;

    public DateTimeControl(final Composite parent) {
        super(parent, SWT.NONE);
        setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());
        dateControl = new DateTime(this, SWT.BORDER | SWT.DATE | SWT.DROP_DOWN);
        timeControl = new DateTime(this, SWT.BORDER | SWT.TIME);
    }

    public void bindControl(final DataBindingContext dbc, final IObservableValue modelObservable) {
        final DateTimeObservable dateObservable = new DateTimeObservable(dateControl);
        final DateTimeObservable timeObservable = new DateTimeObservable(timeControl);
        dbc.bindValue(dateObservable, modelObservable);
        dateControl.notifyListeners(SWT.Selection, new Event());
        timeObservable.addValueChangeListener(new IValueChangeListener() {

            @Override
            public void handleValueChange(final ValueChangeEvent event) {
                final Date newValue = (Date) event.diff.getNewValue();
                dateControl.setTime(newValue.getHours(), newValue.getMinutes(), newValue.getMinutes());
                dateControl.notifyListeners(SWT.Selection, new Event());
            }
        });
    }

}
