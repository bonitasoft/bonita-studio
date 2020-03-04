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
package org.bonitasoft.studio.businessobject.ui.dialog;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

/**
 * @author Romain Bioteau
 */
public class ElementCheckboxSelectionDialog extends Dialog {

    private int fWidth = 60;

    private int fHeight = 18;

    private ILabelProvider renderer;

    private CheckboxTableViewer checkList;

    private DataBindingContext context;

    private Set<Object> selectedElements = new HashSet<Object>();

    private Object[] elements;

    private Label messageLabel;

    private String message;

    private String title;

    public ElementCheckboxSelectionDialog(Shell parent, ILabelProvider renderer) {
        super(parent);
        this.renderer = renderer;
        this.context = new DataBindingContext();
    }

    protected void configureShell(Shell shell) {
        super.configureShell(shell);
        if (title != null) {
            shell.setText(title);
        }
    }

    @Override
    protected Control createDialogArea(Composite parent) {
        Composite composite = (Composite) super.createDialogArea(parent);
        createMessageArea(composite);
        checkList = CheckboxTableViewer.newCheckList(composite, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
        GridData data = new GridData();
        data.widthHint = convertWidthInCharsToPixels(fWidth);
        data.heightHint = convertHeightInCharsToPixels(fHeight);
        data.grabExcessVerticalSpace = true;
        data.grabExcessHorizontalSpace = true;
        data.horizontalAlignment = GridData.FILL;
        data.verticalAlignment = GridData.FILL;
        checkList.getTable().setLayoutData(data);
        checkList.setLabelProvider(renderer);
        checkList.setContentProvider(ArrayContentProvider.getInstance());
        checkList.setInput(elements);
        context.bindSet(ViewersObservables.observeCheckedElements(checkList, Object.class), PojoObservables.observeSet(this, "selectedElements"));
        return composite;
    }

    protected Label createMessageArea(Composite composite) {
        messageLabel = new Label(composite, SWT.NONE);
        if (message != null) {
            messageLabel.setText(message);
        }
        messageLabel.setFont(composite.getFont());

        GridData data = new GridData();
        data.grabExcessVerticalSpace = false;
        data.grabExcessHorizontalSpace = true;
        data.horizontalAlignment = GridData.FILL;
        data.verticalAlignment = GridData.BEGINNING;
        messageLabel.setLayoutData(data);

        return messageLabel;
    }

    public void setElements(Object[] elements) {
        this.elements = elements;
    }

    public Set<Object> getSelectedElements() {
        return selectedElements;
    }

    public void setSelectedElements(Set<Object> selectedElements) {
        this.selectedElements = selectedElements;
    }

    public Object getResult() {
        return selectedElements.toArray();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
