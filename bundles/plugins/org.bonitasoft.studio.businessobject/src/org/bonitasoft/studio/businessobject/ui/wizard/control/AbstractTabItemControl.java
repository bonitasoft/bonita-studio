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
package org.bonitasoft.studio.businessobject.ui.wizard.control;

import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

/**
 * @author Romain Bioteau
 */
public abstract class AbstractTabItemControl extends Composite {

    public AbstractTabItemControl(Composite parent, int style) {
        super(parent, style);
    }

    protected Button createAddButton(final Composite buttonsComposite) {
        final Button addButton = new Button(buttonsComposite, SWT.FLAT);
        addButton.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        addButton.setText(Messages.add);
        addButton.setEnabled(false);
        return addButton;
    }

    protected Button createDeleteButton(final Composite buttonsComposite) {
        final Button deleteButton = new Button(buttonsComposite, SWT.FLAT);
        deleteButton.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        deleteButton.setText(Messages.delete);
        deleteButton.setEnabled(false);
        return deleteButton;
    }

    protected Button createDownButton(final IObservableValue<BusinessObject> viewerObservableValue,
            final Composite buttonsComposite) {
        final Button downButton = new Button(buttonsComposite, SWT.FLAT);
        downButton.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        downButton.setText(Messages.down);
        downButton.setEnabled(viewerObservableValue.getValue() != null);
        return downButton;
    }

    protected Button createUpButton(final IObservableValue<BusinessObject> viewerObservableValue,
            final Composite buttonsComposite) {
        final Button upButton = new Button(buttonsComposite, SWT.FLAT);
        upButton.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        upButton.setText(Messages.up);
        upButton.setEnabled(viewerObservableValue.getValue() != null);
        return upButton;
    }

}
