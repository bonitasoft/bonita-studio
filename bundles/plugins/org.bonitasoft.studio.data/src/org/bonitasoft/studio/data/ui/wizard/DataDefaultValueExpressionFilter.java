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
package org.bonitasoft.studio.data.ui.wizard;

import java.util.Set;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.expression.editor.filter.AvailableExpressionTypeFilter;
import org.bonitasoft.studio.expression.editor.provider.IExpressionProvider;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.Pool;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.Viewer;

/**
 * @author Romain Bioteau
 */
public class DataDefaultValueExpressionFilter extends AvailableExpressionTypeFilter {

    private final DataWizardPage wizardPage;
    private final EObject container;
    private final boolean isOverviewContext;

    public DataDefaultValueExpressionFilter(final DataWizardPage wizardPage,
            final EObject container,
            final boolean isOverviewContext,
            final boolean isPageflowContext) {
        super(new String[] {
                ExpressionConstants.VARIABLE_TYPE,
                ExpressionConstants.CONSTANT_TYPE,
                ExpressionConstants.SCRIPT_TYPE,
                ExpressionConstants.PARAMETER_TYPE,
                ExpressionConstants.CONTRACT_INPUT_TYPE });
        this.wizardPage = wizardPage;
        this.container = container;
        this.isOverviewContext = isOverviewContext;
        if (isPageflowContext) {
            getContentTypes().add(ExpressionConstants.QUERY_TYPE);
        }

    }

    @Override
    public boolean select(final Viewer viewer, final Object context, final Object element) {
        final boolean selected = super.select(viewer, context, element);
        final Set<String> availableDataNames = wizardPage.refreshDataNames();
        final String expressionType = getExpressionType(element);
        if (ExpressionConstants.CONTRACT_INPUT_TYPE.equals(expressionType)) {
            return container instanceof Pool;
        }
        if (isExpressionOfVariableType(element)) {
            return availableDataNames.contains(((Expression) element).getName());
        } else if (isExpressionProviderForVariableType(element, expressionType)) {
            return !(container instanceof AbstractProcess) || container instanceof Pool && isOverviewContext;
        }
        return selected;
    }

    protected boolean isExpressionProviderForVariableType(final Object element, final String expressionType) {
        return element instanceof IExpressionProvider
                && ExpressionConstants.VARIABLE_TYPE.equals(expressionType);
    }

    protected boolean isExpressionOfVariableType(final Object element) {
        return element instanceof Expression && ExpressionConstants.VARIABLE_TYPE.equals(((Expression) element).getType());
    }

    protected String getExpressionType(final Object element) {
        if (element instanceof IExpressionProvider) {
            return ((IExpressionProvider) element).getExpressionType();
        } else if (element instanceof Expression) {
            return ((Expression) element).getType();
        }
        return null;
    }

}
