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
package org.bonitasoft.studio.contract.ui.property.input.edit;

import java.util.ArrayList;

import org.bonitasoft.studio.contract.ui.property.input.CustomAdapterFactoryContentProvider;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.ui.celleditor.ExtendedComboBoxCellEditor;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseWheelListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public class ContractInputTypePropertyDescriptor extends CustomAdapterFactoryContentProvider {

    public ContractInputTypePropertyDescriptor(AdapterFactory adapterFactory) {
        super(adapterFactory);
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.contract.ui.property.input.CustomAdapterFactoryContentProvider#createCustomPropertyEditor(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected CellEditor createCustomPropertyEditor(Composite composite,
            Object object,
            IItemPropertyDescriptor itemPropertyDescriptor,
            ILabelProvider labelProvider) {
        final ExtendedComboBoxCellEditor extendedComboBoxCellEditor = new ExtendedComboBoxCellEditor(
                composite,
                new ArrayList<Object>(itemPropertyDescriptor.getChoiceOfValues(object)),
                labelProvider,
                itemPropertyDescriptor.isSortChoices(object)) {

            /*
             * (non-Javadoc)
             * @see org.eclipse.jface.viewers.ComboBoxCellEditor#createControl(org.eclipse.swt.widgets.Composite)
             */
            @Override
            protected Control createControl(Composite parent) {
                final CCombo cCombo = (CCombo) super.createControl(parent);
                enableApplyEditorValueOnListSelection(cCombo);
                return cCombo;
            }

            private long timeOfNonFinalSelectionEvent = 0;

            /**
             * Solves the problem that selecting an element in the combo viewer does not
             * directly apply the value selected from it's list, see <a
             * href="https://bugs.eclipse.org/bugs/show_bug.cgi?id=230398">this bug
             * report</a>. We must not apply the value if the user is still busy
             * selecting a value with the keyboard up/down arrows or mousewheel. See <a
             * href="https://bugs.eclipse.org/bugs/show_bug.cgi?id=54989">this bug
             * report</a>.
             */
            private void enableApplyEditorValueOnListSelection(final CCombo cCombo) {
                cCombo.addSelectionListener(new SelectionAdapter() {

                    @Override
                    public void widgetSelected(SelectionEvent e) {
                        if (isFinalSelection(e)) {
                            fireApplyEditorValue();
                        }
                    }

                    private boolean isFinalSelection(SelectionEvent e) {
                        return !cCombo.getListVisible()
                                && e.time != timeOfNonFinalSelectionEvent;
                    }
                });
                cCombo.addMouseWheelListener(new MouseWheelListener() {

                    @Override
                    public void mouseScrolled(MouseEvent e) {
                        timeOfNonFinalSelectionEvent = e.time;
                    }
                });
                cCombo.addKeyListener(new KeyAdapter() {

                    @Override
                    public void keyPressed(KeyEvent e) {
                        if (e.keyCode == SWT.ARROW_UP || e.keyCode == SWT.ARROW_DOWN) {
                            timeOfNonFinalSelectionEvent = e.time;
                        }
                    }
                });
            }

        };
        extendedComboBoxCellEditor.setActivationStyle(ExtendedComboBoxCellEditor.DROP_DOWN_ON_MOUSE_ACTIVATION);
        extendedComboBoxCellEditor.getControl().addListener(SWT.Selection,
                e -> extendedComboBoxCellEditor.getControl().getParent().setFocus());
        return extendedComboBoxCellEditor;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.contract.ui.property.input.CustomAdapterFactoryContentProvider#getCustomFeature()
     */
    @Override
    protected Object getCustomFeature() {
        return ProcessPackage.Literals.CONTRACT_INPUT__TYPE;
    }

}
