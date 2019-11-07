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
package org.bonitasoft.studio.groovy.ui.dialog;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.expression.editor.provider.IExpressionProvider;
import org.bonitasoft.studio.expression.editor.viewer.EditExpressionDialog;
import org.bonitasoft.studio.groovy.ui.providers.GroovyScriptFileEditor;
import org.bonitasoft.studio.model.expression.Expression;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.databinding.conversion.IConverter;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

/**
 * @author Romain Bioteau
 */
public class GroovyScriptFileDialog extends EditExpressionDialog {

    public GroovyScriptFileDialog(Shell parentShell, Expression inputExpression, EObject context, EditingDomain domain,
            ViewerFilter[] viewerTypeFilters) {
        super(parentShell, false, inputExpression, context, domain, viewerTypeFilters, null);
        if (isResizable()) {
            setShellStyle(SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL | SWT.MAX | SWT.RESIZE
                    | getDefaultOrientation());
        } else {
            setShellStyle(SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL
                    | getDefaultOrientation());
        }
    }

    @Override
    protected Control createDialogArea(Composite parent) {
        Control c = super.createDialogArea(parent);
        showContent(ExpressionConstants.SCRIPT_TYPE);
        return c;
    }

    @Override
    protected void createExpressionTypePanel(Composite parentForm) {

    }

    @Override
    protected void showContent(String type) {
        currentExpressionEditor = new GroovyScriptFileEditor();
        if (currentExpressionEditor != null) {
            if (dataBindingContext != null) {
                dataBindingContext.dispose();
            }
            dataBindingContext = new EMFDataBindingContext();
            currentExpressionEditor.createExpressionEditor(contentComposite, dataBindingContext);
            if (helpControl != null) {
                helpControl.setVisible(currentExpressionEditor.provideDialogTray());
                if (getTray() != null) {
                    closeTray();
                }
            }
            UpdateValueStrategy selectionToExpressionType = new UpdateValueStrategy();
            IConverter convert = new Converter(IExpressionProvider.class, String.class) {

                @Override
                public Object convert(Object from) {
                    return from != null ? ((IExpressionProvider) from).getExpressionType() : null;
                }
            };
            selectionToExpressionType.setConverter(convert);
            currentExpressionEditor.bindExpression(dataBindingContext, context, inputExpression, viewerTypeFilters, null);
        }
    }

}
