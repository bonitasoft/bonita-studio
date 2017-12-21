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
package org.bonitasoft.studio.common.jface.dialog;

import java.util.Collection;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

public abstract class ProblemsDialog<T> extends MessageDialog {

    public ProblemsDialog(Shell parentShell, String dialogTitle, String dialogMessage, int dialogImageType,
            String[] dialogButtonLabels) {
        super(parentShell, dialogTitle, null, dialogMessage, dialogImageType, dialogButtonLabels, 0);
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.dialogs.MessageDialog#createCustomArea(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected Control createCustomArea(Composite parent) {
        Collection<T> input = getInput();
        Assert.isNotNull(input);
        if (input.isEmpty()) {
            return super.createCustomArea(parent);
        }
        TableViewer problemsViewer = new TableViewer(parent, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
        problemsViewer.getControl()
                .setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(350, 100).indent(0, 10).create());
        problemsViewer.setContentProvider(ArrayContentProvider.getInstance());
        final TypedLabelProvider<T> typedLabelProvider = getTypedLabelProvider();
        Assert.isNotNull(typedLabelProvider);
        problemsViewer.setLabelProvider(new LabelProvider() {

            @SuppressWarnings("unchecked")
            @Override
            public String getText(Object element) {
                return typedLabelProvider.getText((T) element);
            }

            @SuppressWarnings("unchecked")
            @Override
            public Image getImage(Object element) {
                return typedLabelProvider.getImage((T) element);
            }
        });

        problemsViewer.setInput(input);
        return problemsViewer.getControl();
    }

    protected abstract TypedLabelProvider<T> getTypedLabelProvider();

    protected abstract Collection<T> getInput();

    @Override
    protected boolean isResizable() {
        return true;
    }

}
