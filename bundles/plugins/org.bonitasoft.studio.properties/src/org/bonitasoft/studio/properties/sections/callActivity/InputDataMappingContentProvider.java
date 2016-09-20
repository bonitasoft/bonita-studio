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
package org.bonitasoft.studio.properties.sections.callActivity;

import static com.google.common.collect.Iterables.filter;
import static com.google.common.collect.Iterables.toArray;

import java.util.Set;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.expression.editor.provider.IExpressionProvider;
import org.bonitasoft.studio.model.expression.Expression;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ArrayContentProvider;

import com.google.common.base.Predicate;

/**
 * @author Romain Bioteau
 *
 */
public class InputDataMappingContentProvider extends ArrayContentProvider {

    private final IObservableValue callActivityObservable;
    private final IExpressionProvider expressionProvider;

    public InputDataMappingContentProvider(final IObservableValue callActivityObservable, final IExpressionProvider expressionProvider) {
        this.callActivityObservable = callActivityObservable;
        this.expressionProvider = expressionProvider;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
     */
    @Override
    public Object[] getElements(final Object inputElement) {
        final Set<Expression> expressions = expressionProvider.getExpressions((EObject) callActivityObservable.getValue());
        final Iterable<Expression> dataExpressions = variableExpressions(expressions);
        return toArray(dataExpressions, Object.class);
    }


    protected Iterable<Expression> variableExpressions(final Iterable<Expression> expressions) {
        return filter(expressions, new Predicate<Expression>() {

            @Override
            public boolean apply(final Expression expression) {
                return ExpressionConstants.VARIABLE_TYPE.equals(expression.getType());
            }
        });
    }


}
