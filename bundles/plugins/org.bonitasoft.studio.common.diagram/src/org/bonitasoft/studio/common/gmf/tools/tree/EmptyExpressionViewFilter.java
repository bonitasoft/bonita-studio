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

package org.bonitasoft.studio.common.gmf.tools.tree;

import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.process.MultiInstanceType;
import org.bonitasoft.studio.model.process.MultiInstantiable;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

/**
 * @author Aurelie Zara
 */
public class EmptyExpressionViewFilter extends ViewerFilter {

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object,
     * java.lang.Object)
     */
    @Override
    public boolean select(final Viewer viewer, final Object parentElement, final Object element) {
        if (element instanceof Expression) {
            final Expression expr = (Expression) element;
            if (!expr.hasName() && !expr.hasContent()) {
                return false;
            }
            if (ProcessPackage.Literals.MULTI_INSTANTIABLE__ITERATOR_EXPRESSION.equals(expr.eContainingFeature())) {
                final MultiInstantiable eContainer = (MultiInstantiable) expr.eContainer();
                return (eContainer.getType() == MultiInstanceType.PARALLEL
                        || eContainer.getType() == MultiInstanceType.SEQUENTIAL)
                        && !eContainer.isUseCardinality();
            }
        }
        return true;
    }

}
