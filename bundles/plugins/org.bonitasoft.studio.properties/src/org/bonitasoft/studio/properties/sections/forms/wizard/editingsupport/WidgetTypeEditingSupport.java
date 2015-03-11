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
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.properties.sections.forms.wizard.editingsupport;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.bonitasoft.studio.common.palette.FormPaletteLabelProvider;
import org.bonitasoft.studio.diagram.form.custom.model.WidgetMapping;
import org.bonitasoft.studio.model.form.FormFactory;
import org.bonitasoft.studio.model.form.Widget;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;

/**
 * @author Romain Bioteau
 *
 */
public class WidgetTypeEditingSupport extends EditingSupport {

    private final HashMap<String, EClass> widgetTypesByNames;
    private final FormPaletteLabelProvider formPaletteLabelProvider;

    /**
     * @param viewer
     */
    public WidgetTypeEditingSupport(final ColumnViewer viewer) {
        super(viewer);
        widgetTypesByNames = new HashMap<String,EClass>();
        formPaletteLabelProvider = new FormPaletteLabelProvider();
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.viewers.EditingSupport#getCellEditor(java.lang.Object)
     */
    @Override
    protected CellEditor getCellEditor(final Object element) {
        return new ComboBoxCellEditor((Composite) getViewer().getControl(), getItemsFor(element), SWT.READ_ONLY | SWT.NO_FOCUS) {

            @Override
            protected void doSetValue(final Object value) {
                super.doSetValue(value);
                final CCombo combo = (CCombo) getControl();
                combo.setSelection(new Point(0, 0));
            }
        };
    }

    public Control createControl(final Object element) {
        final Widget widgetType = ((WidgetMapping) element).getWidgetType();
        if (widgetType != null) {
        final CCombo combo = new CCombo((Composite) getViewer().getControl(), SWT.READ_ONLY);
        combo.setEditable(false);
        combo.setItems(getItemsFor(element));

            final String initialValue = getText(widgetType.eClass(), (WidgetMapping) element);
            combo.setText(initialValue);
            combo.addSelectionListener(new SelectionAdapter() {

                @Override
                public void widgetSelected(final SelectionEvent e) {
                    setValue(element, combo.getText());
                }

            });
            return combo;
        }
        return null;
    }

    protected String[] getItemsFor(final Object element) {
        if(element instanceof WidgetMapping){
            final WidgetMapping mapping = (WidgetMapping) element;
            final List<String> widgetNames = new ArrayList<String>();
            for(final EClass widgetType : mapping.getCompatibleWidgetTypes()){
                final String text = getText(widgetType,mapping);
                widgetTypesByNames.put(text,widgetType);
                widgetNames.add(text);
            }
            return widgetNames.toArray(new String[widgetNames.size()]);
        }
        return new String[0];
    }

    public String getText(final EClass widgetType, final WidgetMapping mapping) {
        return formPaletteLabelProvider.getFormPaletteText(widgetType);
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.viewers.EditingSupport#canEdit(java.lang.Object)
     */
    @Override
    protected boolean canEdit(final Object element) {
        if(element instanceof WidgetMapping){
            return getItemsFor(element).length > 1;
        }
        return false;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.viewers.EditingSupport#getValue(java.lang.Object)
     */
    @Override
    protected Object getValue(final Object element) {
        return Arrays.asList(getItemsFor(element)).indexOf(formPaletteLabelProvider.getFormPaletteText(((WidgetMapping) element).getWidgetType().eClass()));
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.viewers.EditingSupport#setValue(java.lang.Object, java.lang.Object)
     */
    @Override
    protected void setValue(final Object element, final Object value) {
        final EClass widgetEClass = getEClassByWidgetName(value.toString());
        ((WidgetMapping)element).setWidgetType(createWidgetFor(widgetEClass));
        Display.getDefault().asyncExec(new Runnable() {

            @Override
            public void run() {
                getViewer().refresh();
            }
        }) ;
    }


    protected EClass getEClassByWidgetName(final String widgetName) {
        return widgetTypesByNames.get(widgetName);
    }

    protected Widget createWidgetFor(final EClass widgetEClass) {
        return (Widget) FormFactory.eINSTANCE.create(widgetEClass);
    }

}
