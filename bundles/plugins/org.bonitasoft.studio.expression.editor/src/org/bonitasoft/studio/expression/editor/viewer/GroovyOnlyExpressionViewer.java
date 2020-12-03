/**
 * Copyright (C) 2012 BonitaSoft S.A.
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
package org.bonitasoft.studio.expression.editor.viewer;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.widgets.Composite;

/**
 * @author Romain Bioteau
 *
 */
public class GroovyOnlyExpressionViewer extends ExpressionViewer {

    public GroovyOnlyExpressionViewer(final Composite composite, final int style) {
        super(composite, style);
        getContentAssistText().getToolbar().getItem(0).setEnabled(false);

    }

    @Override
    protected EditExpressionDialog createEditDialog(final EObject editInput) {
        return new GroovyOnlyEditExpressionDialog(control.getShell(), isPassword,
                getSelectedExpression(), editInput, getEditingDomain(),
                filters.toArray(new ViewerFilter[filters.size()]), this, expressionNameResolver);
    }

    @Override
    protected String getContentTypeFromInput(final String input) {
        return ExpressionConstants.SCRIPT_TYPE;
    }

    @Override
    protected String defaultExpressionType() {
        return ExpressionConstants.SCRIPT_TYPE;
    }

}
