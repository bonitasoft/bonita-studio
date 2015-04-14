/**
 * Copyright (C) 2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.exporter.form;

import static com.google.common.base.Strings.isNullOrEmpty;

import java.util.List;

import org.bonitasoft.forms.server.builder.IFormBuilder;
import org.bonitasoft.forms.server.exception.InvalidFormDefinitionException;

public class ExpressionDependencyBuilder {

    public void buildExpressionDependency(final IFormBuilder builder, final org.bonitasoft.engine.expression.Expression expression)
            throws InvalidFormDefinitionException {
        boolean isSameLevelThanPrevious = true;
        final List<org.bonitasoft.engine.expression.Expression> dependencies = expression.getDependencies();
        if (!dependencies.isEmpty()) {
            for (final org.bonitasoft.engine.expression.Expression dependency : dependencies) {
                builder.addDependentExpression(
                        dependency.getName(),
                        dependency.getContent(),
                        dependency.getExpressionType(),
                        dependency.getReturnType(),
                        interpreter(dependency),
                        !isSameLevelThanPrevious);
                isSameLevelThanPrevious = false;
                if (!dependency.getDependencies().isEmpty()) {
                    buildExpressionDependency(builder, dependency);
                }
            }
            builder.endExpressionDependencies();
        }
    }

    private String interpreter(final org.bonitasoft.engine.expression.Expression dependency) {
        return !isNullOrEmpty(dependency.getInterpreter()) ? dependency.getInterpreter() : null;
    }
}
