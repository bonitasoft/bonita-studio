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
package org.bonitasoft.studio.properties.sections.iteration;

import static com.google.common.collect.Iterables.tryFind;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.process.Activity;
import org.bonitasoft.studio.model.process.Data;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import com.google.common.base.Optional;
import com.google.common.base.Predicate;

/**
 * @author Romain Bioteau
 * @author Aurelien
 *         Display only Process data, it filters out step data. The step requires to be the context of the expressionviewer
 */
public class OnlyProcessDataViewerFilter extends ViewerFilter {

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
     */
    @Override
    public boolean select(final Viewer viewer, final Object parentElement, final Object element) {
        if (parentElement instanceof Activity
                && isAVariableExpression(element)) {
            final Optional<Data> foundData = tryFind(((Activity) parentElement).getData(), new Predicate<Data>() {

                @Override
                public boolean apply(final Data data) {
                    return ((Expression) element).getName().equals(data.getName());
                }
            });
            return !foundData.isPresent();
        }
        return true;
    }

    private boolean isAVariableExpression(final Object element) {
        return element instanceof Expression
                && ((Expression) element).hasName()
                && ExpressionConstants.VARIABLE_TYPE.equals(((Expression) element).getType());
    }

}
