/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.expression.editor.viewer;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.model.expression.Expression;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

/**
 * @author Romain Bioteau
 */
public class SelectDependencyDialog extends Dialog {

    private final ComposedAdapterFactory adapterFactory;
    private final AdapterFactoryLabelProvider adapterLabelProvider;
    private TableViewer dependenciesViewer;
    private final List<EObject> deps;
    private final Set<Expression> filteredExpression;

    public SelectDependencyDialog(final Shell parentShell, final Set<Expression> filteredExpression, final List<EObject> currentDepList) {
        super(parentShell);
        this.filteredExpression = filteredExpression;
        adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
        adapterLabelProvider = new AdapterFactoryLabelProvider(adapterFactory);
        deps = currentDepList;
    }

    @Override
    protected void setShellStyle(final int newShellStyle) {
        super.setShellStyle(newShellStyle | SWT.SHEET);
    }

    @Override
    protected Control createDialogArea(final Composite parent) {
        final Composite composite = (Composite) super.createDialogArea(parent);

        dependenciesViewer = new TableViewer(composite, SWT.BORDER | SWT.V_SCROLL | SWT.READ_ONLY | SWT.MULTI);
        dependenciesViewer.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        dependenciesViewer.setContentProvider(new ArrayContentProvider());
        dependenciesViewer.setLabelProvider(adapterLabelProvider);

        final List<EObject> input = new ArrayList<EObject>();
        for (final Expression e : filteredExpression) {
            final EList<EObject> referencedElements = e.getReferencedElements();
            if (!referencedElements.isEmpty()) {
                final EObject element = referencedElements.get(0);
                input.add(ExpressionHelper.createDependencyFromEObject(element));
            }
        }

        dependenciesViewer.setInput(input);
        return composite;
    }

    @Override
    protected void okPressed() {
        for (final Object sel : ((IStructuredSelection) dependenciesViewer.getSelection()).toList()) {
            deps.add((EObject) sel);
        }

        super.okPressed();
    }

    public List<EObject> getDependencies() {
        return deps;
    }
}
