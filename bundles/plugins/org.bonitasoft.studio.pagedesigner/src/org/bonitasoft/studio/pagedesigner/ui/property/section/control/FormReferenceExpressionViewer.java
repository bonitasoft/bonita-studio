/**
 * Copyright (C) 2012 BonitaSoft S.A.
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
package org.bonitasoft.studio.pagedesigner.ui.property.section.control;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.expression.editor.viewer.EditExpressionDialog;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
import org.bonitasoft.studio.model.expression.Expression;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

/**
 * @author Romain Bioteau
 */
public class FormReferenceExpressionViewer extends ExpressionViewer {

    public FormReferenceExpressionViewer(final Composite composite, final int style, final TabbedPropertySheetWidgetFactory widgetFactory) {
        super(composite, style, widgetFactory);
    }

    @Override
    protected EditExpressionDialog createEditDialog(final EObject editInput) {
        return null;
    }

    @Override
    protected String getContentTypeFromInput(final String input) {
        return ExpressionConstants.FORM_REFERENCE_TYPE;
    }

    @Override
    protected String getDefaultExpressionType(final Expression selectedExpression) {
        return ExpressionConstants.FORM_REFERENCE_TYPE;
    }

}
