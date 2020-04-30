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
package org.bonitasoft.studio.contract.ui.property;

import org.bonitasoft.studio.contract.ui.property.input.CellEditorControlAdapter;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.fieldassist.FieldDecoration;
import org.eclipse.jface.fieldassist.FieldDecorationRegistry;
import org.eclipse.swt.widgets.Control;

/**
 * @author Romain Bioteau
 *
 */
public class FieldDecoratorProvider {

    public ControlDecoration createControlDecorator(final Control control, final String description, final String fieldDecorationType, final int style) {
        final FieldDecoration decorator = getDecorator(fieldDecorationType);
        final ControlDecoration controlDecoration = newControlDecoration(control, style);
        controlDecoration.setImage(decorator.getImage());
        controlDecoration.setDescriptionText(description);
        control.addControlListener(new CellEditorControlAdapter(control));
        return controlDecoration;
    }

    protected ControlDecoration newControlDecoration(final Control control, final int style) {
        return new ControlDecoration(control, style);
    }

    protected FieldDecoration getDecorator(final String fieldDecorationType) {
        final FieldDecoration decorator = FieldDecorationRegistry.getDefault().getFieldDecoration(fieldDecorationType);
        if(decorator == null){
            throw new IllegalArgumentException("Unknown fieldDecorationType: " + fieldDecorationType);
        }
        return decorator;
    }

}
