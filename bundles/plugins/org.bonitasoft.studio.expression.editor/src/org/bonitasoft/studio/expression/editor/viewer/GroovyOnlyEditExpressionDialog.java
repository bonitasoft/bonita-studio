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

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.model.expression.Expression;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

/**
 * @author Romain Bioteau
 */
public class GroovyOnlyEditExpressionDialog extends EditExpressionDialog {

    protected GroovyOnlyEditExpressionDialog(final Shell parentShell, final boolean isPassword,
            final Expression inputExpression, final EObject context,
            final EditingDomain domain, final ViewerFilter[] viewerTypeFilters, final ExpressionViewer expressionViewer) {
        super(parentShell, isPassword, inputExpression, context, domain, viewerTypeFilters, expressionViewer);
        this.inputExpression.setType(ExpressionConstants.SCRIPT_TYPE);
    }

    @Override
    protected void createExpressionTypePanel(final Composite parentForm) {
        //DO NOT CREATE EXPRESSION TYPE PANEL
    }

    @Override
    public void create() {
        super.create();
        showContent(ExpressionConstants.SCRIPT_TYPE);
        getShell().layout(true, true);
    }

}
