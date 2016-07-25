/**
 * Copyright (C) 2014-2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.validators.ui.property.section;

import java.util.List;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.expression.editor.filter.AvailableExpressionTypeFilter;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.form.Form;
import org.bonitasoft.studio.model.form.Widget;
import org.bonitasoft.studio.model.form.WidgetDependency;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.Viewer;

/**
 * @author Romain Bioteau
 */
public class AvailableExpressionTypeFilterWitoutContingentWidgets extends AvailableExpressionTypeFilter {

    public AvailableExpressionTypeFilterWitoutContingentWidgets() {
        super(new String[] { ExpressionConstants.CONSTANT_TYPE, ExpressionConstants.VARIABLE_TYPE, ExpressionConstants.SCRIPT_TYPE,
                ExpressionConstants.PARAMETER_TYPE, ExpressionConstants.FORM_FIELD_TYPE, ExpressionConstants.DOCUMENT_TYPE });
    }

    @Override
    public boolean select(final Viewer viewer, final Object context, final Object element) {
        if (element instanceof Expression){
            final Expression expression = (Expression)element;
            Widget parentWidget = null;
            if (context instanceof EObject) {
                parentWidget = ModelHelper.getParentWidget((EObject) context);
            } else if (viewer instanceof ExpressionViewer) {
                parentWidget = ModelHelper.getParentWidget(((ExpressionViewer) viewer).getContext());
            }
            if(ExpressionConstants.FORM_FIELD_TYPE.equals(expression.getType())
                    && !(expression.getReferencedElements().isEmpty())
                    && isContingentField(parentWidget, expression)) {
                return false;
            }
        }
        return super.select(viewer, context, element);
    }

    private boolean isContingentField(final Widget parentWidget, final Expression formFieldExpression) {
        final Widget widget = (Widget) formFieldExpression.getReferencedElements().get(0);
        if (parentWidget == null) {
            return false;
        }
        final Widget originalWidget = getOriginalWidget(widget, ModelHelper.getForm(parentWidget));
        for (final WidgetDependency dep : parentWidget.getDependOn()) {
            if (dep.getWidget().equals(originalWidget)) {
                return true;
            }
        }
        return false;
    }

    private Widget getOriginalWidget(final Widget widget, final Form form) {
        final List<Widget> widgetInsideForm = ModelHelper.getAllWidgetInsideForm(form);
        for (final Widget w : widgetInsideForm) {
            if (w.getName().equals(widget.getName())) {
                return w;
            }
        }
        return null;
    }

}
