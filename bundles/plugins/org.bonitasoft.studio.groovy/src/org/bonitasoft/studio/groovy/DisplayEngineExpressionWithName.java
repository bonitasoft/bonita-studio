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
package org.bonitasoft.studio.groovy;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.model.expression.Expression;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

/**
 * @author Romain Bioteau
 */
public class DisplayEngineExpressionWithName extends ViewerFilter {

    private Set<String> namesToAllow;

    public DisplayEngineExpressionWithName(String[] namesToAllow) {
        this.namesToAllow = new HashSet<String>(Arrays.asList(namesToAllow));
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
     */
    @Override
    public boolean select(Viewer viewer, Object parentElement, Object element) {
        if (element instanceof Expression) {
            if (((Expression) element).getType().equals(ExpressionConstants.ENGINE_CONSTANT_TYPE)) {
                return namesToAllow.contains(((Expression) element).getName());
            }
        } else if (element instanceof String) {
            return namesToAllow.contains(element);
        }
        return true;
    }

}
