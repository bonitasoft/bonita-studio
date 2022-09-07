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
package org.bonitasoft.studio.expression.editor.filter;

import static com.google.common.collect.Sets.newHashSet;

import java.util.Set;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.expression.editor.provider.IExpressionProvider;
import org.bonitasoft.studio.model.expression.Expression;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import com.google.common.collect.Sets;

/**
 * @author Romain Bioteau
 */
public class AvailableExpressionTypeFilter extends ViewerFilter {

    private final Set<String> contentTypes;

    public AvailableExpressionTypeFilter(final String... contentTypes) {
        this.contentTypes = newHashSet(contentTypes);
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
     */
    @Override
    public boolean select(final Viewer viewer, final Object context, final Object element) {
        return isExpressionAllowed(element);
    }

    protected boolean isExpressionAllowed(final Object element) {
        Set<String> allowedExpressionTypes = allowedExpressionTypes();
        if (element instanceof Expression) {
            return allowedExpressionTypes.contains(((Expression) element).getType());
        } else if (element instanceof IExpressionProvider) {
            return allowedExpressionTypes.contains(((IExpressionProvider) element).getExpressionType());
        }
        return true;
    }

    protected Set<String> allowedExpressionTypes() {
        Set<String> allowedExpressionTypes = Sets.newHashSet(getContentTypes());
        if (allowedExpressionTypes.contains(ExpressionConstants.VARIABLE_TYPE)) {
            allowedExpressionTypes.add(ExpressionConstants.JAVA_TYPE);
            allowedExpressionTypes.add(ExpressionConstants.XPATH_TYPE);
            allowedExpressionTypes.add(ExpressionConstants.MULTIINSTANCE_ITERATOR_TYPE);
        }
        return allowedExpressionTypes;
    }

    public Set<String> getContentTypes() {
        return contentTypes;
    }

    public void addType(String expressionType) {
        contentTypes.add(expressionType);
    }
}
