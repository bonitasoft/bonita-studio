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

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.SelectObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

public class BooleanRadioGroup extends Composite {

    private final Button trueButton;
    private final Button falseButton;

    public BooleanRadioGroup(final Composite parent) {
        super(parent, SWT.NONE);
        setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());

        trueButton = new Button(this, SWT.RADIO);
        trueButton.setText("true");

        falseButton = new Button(this, SWT.RADIO);
        falseButton.setText("false");
    }

    public void bindControl(final DataBindingContext dbc, final IObservableValue modelObservable) {
        final SelectObservableValue selectObservableValue = new SelectObservableValue(Boolean.class);
        selectObservableValue.addOption(Boolean.TRUE, SWTObservables.observeSelection(trueButton));
        selectObservableValue.addOption(Boolean.FALSE, SWTObservables.observeSelection(falseButton));
        selectObservableValue.setValue(Boolean.TRUE);
        dbc.bindValue(selectObservableValue, modelObservable);
    }

}
