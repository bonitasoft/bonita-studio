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
package org.bonitasoft.studio.parameters.wizard.page;

import org.bonitasoft.studio.model.parameter.Parameter;
import org.bonitasoft.studio.parameters.i18n.Messages;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.ICellEditorValidator;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.widgets.Composite;

/**
 * @author Romain Bioteau
 */
public class ParameterValueEditingSupport extends EditingSupport {

    private final WizardPage page;

    public ParameterValueEditingSupport(final ColumnViewer viewer, final WizardPage page) {
        super(viewer);
        this.page = page;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.viewers.EditingSupport#canEdit(java.lang.Object)
     */
    @Override
    protected boolean canEdit(final Object element) {
        return true;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.viewers.EditingSupport#getCellEditor(java.lang.Object)
     */
    @Override
    protected CellEditor getCellEditor(final Object element) {
        final TextCellEditor editor = new TextCellEditor((Composite) getViewer().getControl());
        editor.getControl().setBackground(getViewer().getControl().getBackground());
        editor.setValidator(new ICellEditorValidator() {

            @Override
            public String isValid(final Object value) {
                final String input = (String) value;
                final Parameter param = (Parameter) element;
                final String typeName = param.getTypeClassname();
                if (!input.isEmpty()) {
                    if (typeName.equals(Integer.class.getName())) {
                        try {
                            Integer.parseInt(input);
                        } catch (final NumberFormatException e) {
                            return Messages.invalidInteger;
                        }
                    } else if (typeName.equals(Double.class.getName())) {
                        try {
                            Double.parseDouble(input);
                        } catch (final NumberFormatException e) {
                            return Messages.invalidDouble;
                        }
                    }
                }
                return null;
            }
        });

        return editor;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.viewers.EditingSupport#getValue(java.lang.Object)
     */
    @Override
    protected Object getValue(final Object element) {
        if (element instanceof Parameter) {
            final String paramValue = ((Parameter) element).getValue();
            if (paramValue == null) {
                return "";
            } else {
                return paramValue;
            }
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.viewers.EditingSupport#setValue(java.lang.Object, java.lang.Object)
     */
    @Override
    protected void setValue(final Object element, final Object value) {
        if (element != null && value != null) {
            ((Parameter) element).setValue(value.toString());
            getViewer().refresh();
            page.getWizard().getContainer().updateMessage();
        }
    }

}
