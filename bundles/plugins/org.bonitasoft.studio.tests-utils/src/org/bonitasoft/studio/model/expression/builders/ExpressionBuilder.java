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
package org.bonitasoft.studio.model.expression.builders;

import org.bonitasoft.engine.expression.ExpressionInterpreter;
import org.bonitasoft.engine.expression.ExpressionType;
import org.bonitasoft.studio.model.Buildable;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.process.Connector;
import org.bonitasoft.studio.model.process.Element;
import org.eclipse.emf.ecore.EObject;

/**
 * @author Romain Bioteau
 */
public class ExpressionBuilder {

    private final Expression expression;

    private ExpressionBuilder(final Expression expression) {
        this.expression = expression;
    }

    public static ExpressionBuilder anExpression() {
        return new ExpressionBuilder(ExpressionFactory.eINSTANCE.createExpression());
    }

    public static ExpressionBuilder aConstantExpression() {
        return new ExpressionBuilder(ExpressionFactory.eINSTANCE.createExpression()).withExpressionType(ExpressionType.TYPE_CONSTANT.name());
    }

    public static ExpressionBuilder aVariableExpression() {
        return new ExpressionBuilder(ExpressionFactory.eINSTANCE.createExpression()).withExpressionType(ExpressionType.TYPE_VARIABLE.name());
    }

    public static ExpressionBuilder aGroovyScriptExpression() {
        return new ExpressionBuilder(ExpressionFactory.eINSTANCE.createExpression()).withExpressionType(ExpressionType.TYPE_READ_ONLY_SCRIPT.name())
                .withInterpreter(ExpressionInterpreter.GROOVY.name());
    }

    public ExpressionBuilder withName(final String name) {
        expression.setName(name);
        return this;
    }

    public ExpressionBuilder withContent(final String content) {
        expression.setContent(content);
        return this;
    }

    public ExpressionBuilder withExpressionType(final String type) {
        expression.setType(type);
        return this;
    }

    public ExpressionBuilder withInterpreter(final String interpreter) {
        expression.setInterpreter(interpreter);
        return this;
    }

    public ExpressionBuilder withReturnType(final String returnType) {
        expression.setReturnType(returnType);
        return this;
    }

    public ExpressionBuilder fixedReturnType() {
        expression.setReturnTypeFixed(true);
        return this;
    }

    public ExpressionBuilder anyReturnType() {
        expression.setReturnTypeFixed(false);
        return this;
    }

    public ExpressionBuilder automaticDependencies() {
        expression.setAutomaticDependencies(true);
        return this;
    }

    public ExpressionBuilder manualDependencies() {
        expression.setAutomaticDependencies(false);
        return this;
    }

    public ExpressionBuilder havingReferencedElements(final EObject... referencedElements) {
        if (referencedElements != null) {
            for (final EObject referencedElement : referencedElements) {
                expression.getReferencedElements().add(referencedElement);
            }
        }
        return this;
    }

    public ExpressionBuilder havingReferencedElements(final Buildable<? extends Element>... referencedElementBuildable) {
        for (final Buildable<? extends Element> referencedElement : referencedElementBuildable) {
            expression.getReferencedElements().add(referencedElement.build());
        }
        return this;
    }

    public ExpressionBuilder havingConnectors(final Connector... connectors) {
        if (connectors != null) {
            for (final Connector connector : connectors) {
                expression.getConnectors().add(connector);
            }
        }
        return this;
    }

    public ExpressionBuilder havingConnectors(final Buildable<Connector>... connectors) {
        if (connectors != null) {
            for (final Buildable<Connector> connector : connectors) {
                expression.getConnectors().add(connector.build());
            }
        }
        return this;
    }

    public Expression build() {
        return expression;
    }

}
