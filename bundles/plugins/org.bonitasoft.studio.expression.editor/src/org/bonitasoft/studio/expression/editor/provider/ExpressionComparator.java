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
package org.bonitasoft.studio.expression.editor.provider;

import java.util.Comparator;

import org.bonitasoft.studio.model.expression.Expression;

/**
 * @author Romain Bioteau
 */
public class ExpressionComparator implements Comparator<Expression> {

    @Override
    public int compare(final Expression exp0, final Expression exp1) {
        if (exp0.getType().equals(exp1.getType())) {
            if (exp0.hasName() && exp1.hasName() && !exp0.getName().equals(exp1.getName())) {
                return exp0.getName().compareTo(exp1.getName());
            } else if (exp0.hasContent() && exp1.hasContent() && !exp0.getContent().equals(exp1.getContent())) {
                return exp0.getContent().compareTo(exp1.getContent());
            }
        }
        return exp0.getType().compareTo(exp1.getType());
    }
}
