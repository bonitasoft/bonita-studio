/**
 * Copyright (C) 2009 BonitaSoft S.A.
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
package org.bonitasoft.studio.validation.constraints.form;

import java.util.Collection;
import java.util.Map;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.Operation;
import org.bonitasoft.studio.model.form.MultipleValuatedFormField;
import org.bonitasoft.studio.model.process.Connector;
import org.bonitasoft.studio.validation.constraints.AbstractLiveValidationMarkerConstraint;
import org.bonitasoft.studio.validation.i18n.Messages;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;

/**
 * @author Baptiste Mesta
 */
public class AvailableValuesReturnTypeConstraint extends AbstractLiveValidationMarkerConstraint {

    private static final String ID = "org.bonitasoft.studio.validation.constraints.AvailableValuesReturnTypeConstraint";

    @Override
    protected IStatus performBatchValidation(final IValidationContext ctx) {
        final EObject eObj = ctx.getTarget();
        if (eObj instanceof MultipleValuatedFormField && !(eObj.eContainer() instanceof Expression)) {
            final MultipleValuatedFormField widget = (MultipleValuatedFormField) eObj;
            final Expression availableValues = widget.getInputExpression();
            if (availableValues == null) {
                return ctx.createSuccessStatus();
            }
            String returnType = availableValues.getReturnType();
            if (ExpressionConstants.CONNECTOR_TYPE.equals(availableValues.getType()) && !availableValues.getConnectors().isEmpty()) {
                final Connector connector = availableValues.getConnectors().get(0);
                if (!connector.getOutputs().isEmpty()) {
                    final Operation op = connector.getOutputs().get(0);
                    returnType = op.getRightOperand().getReturnType();
                }

            }
            Class<?> returnTypeClass;
            try {
                returnTypeClass = Class.forName(returnType);
            } catch (final ClassNotFoundException e) {
                return ctx.createFailureStatus(Messages.bind(Messages.unsupportedReturnTypeForAvailableValuesOf, widget.getName(), returnType));
            }
            if (Collection.class.isAssignableFrom(returnTypeClass) || Map.class.isAssignableFrom(returnTypeClass)) {
                return ctx.createSuccessStatus();
            }
            return ctx.createFailureStatus(Messages.bind(Messages.unsupportedReturnTypeForAvailableValuesOf, widget.getName(), returnType));
        }
        return ctx.createSuccessStatus();
    }

    @Override
    protected String getConstraintId() {
        return ID;
    }

}
