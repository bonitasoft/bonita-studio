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
package org.bonitasoft.studio.model.form.builders;

import org.bonitasoft.studio.model.expression.builders.ExpressionBuilder;
import org.bonitasoft.studio.model.expression.builders.OperationBuilder;
import org.bonitasoft.studio.model.form.Duplicable;
import org.bonitasoft.studio.model.form.EventDependencyType;
import org.bonitasoft.studio.model.form.FormFactory;
import org.bonitasoft.studio.model.form.FormPackage;
import org.bonitasoft.studio.model.form.Widget;
import org.bonitasoft.studio.model.form.WidgetDependency;
import org.bonitasoft.studio.model.process.builders.ElementBuilder;

/**
 * @author Romain Bioteau
 */
public abstract class WidgetBuilder<T extends Widget, B extends WidgetBuilder<T, B>> extends ElementBuilder<T, B> {

    public B havingDisplayLabel(final ExpressionBuilder displayLabelExpression) {
        getBuiltInstance().setDisplayLabel(displayLabelExpression.build());
        return getThis();
    }

    public B showDisplayLabel() {
        getBuiltInstance().setShowDisplayLabel(true);
        return getThis();
    }

    public B readOnly() {
        getBuiltInstance().setReadOnly(true);
        return getThis();
    }

    public B hideDisplayLabel() {
        getBuiltInstance().setShowDisplayLabel(false);
        return getThis();
    }

    public B havingInputExpression(final ExpressionBuilder inputExpression) {
        getBuiltInstance().setInputExpression(inputExpression.build());
        return getThis();
    }

    public B havingOutputOperation(final OperationBuilder operationBuilder) {
        getBuiltInstance().setAction(operationBuilder.build());
        return getThis();
    }

    public B withReturnTypeModifier(final String returnTypeModifier) {
        getBuiltInstance().setReturnTypeModifier(returnTypeModifier);
        return getThis();
    }

    public B duplicated() {
        final T builtInstance = getBuiltInstance();
        if (builtInstance instanceof Duplicable) {
            ((Duplicable) builtInstance).setDuplicate(true);
        }
        return getThis();
    }

    public B notDuplicated() {
        final T builtInstance = getBuiltInstance();
        if (builtInstance instanceof Duplicable) {
            ((Duplicable) builtInstance).setDuplicate(false);
        }
        return getThis();
    }

    public B havingContingengy(final Widget... widgets) {
        final T builtInstance = getBuiltInstance();
        for (final Widget widget : widgets) {
            final WidgetDependency wd = FormFactory.eINSTANCE.createWidgetDependency();
            wd.setWidget(widget);
            // set default eventdependencytype
            wd.getEventTypes().add((EventDependencyType) FormPackage.Literals.EVENT_DEPENDENCY_TYPE.getDefaultValue());

            builtInstance.getDependOn().add(wd);
        }
        return getThis();
    }

}
