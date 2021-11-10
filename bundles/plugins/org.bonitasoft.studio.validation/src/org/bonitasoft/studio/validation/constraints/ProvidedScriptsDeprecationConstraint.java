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
package org.bonitasoft.studio.validation.constraints;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorParameter;
import org.bonitasoft.studio.model.expression.AbstractExpression;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.process.Connector;
import org.bonitasoft.studio.validation.i18n.Messages;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;


public class ProvidedScriptsDeprecationConstraint extends AbstractLiveValidationMarkerConstraint {

    private static final String CONSTRAINT_ID = "org.bonitasoft.studio.validation.constraint.providedScriptDeprecationConstraint";
    private static final String GROOVY_DEF_ID = "scripting-groovy";
    private static final Object SCRIPT_PARAMETER = "script";

    @Override
    protected IStatus performBatchValidation(final IValidationContext context) {
        final EObject eObj = context.getTarget();
        if (eObj instanceof Expression
                && !ModelHelper.isAnExpressionCopy((Expression) eObj)
                && ExpressionConstants.SCRIPT_TYPE.equals(((Expression) eObj).getType())
                && ExpressionConstants.GROOVY.equals(((Expression) eObj).getInterpreter())) {
            return evaluateExpression(context, eObj);
        } else if (eObj instanceof Connector) {
            final Connector connector = (Connector) eObj;
            final String defId = connector.getDefinitionId();
            if (GROOVY_DEF_ID.equals(defId)) {
                for (final ConnectorParameter parameter : connector.getConfiguration().getParameters()) {
                    if (SCRIPT_PARAMETER.equals(parameter.getKey())) {
                        final AbstractExpression exp = parameter.getExpression();
                        if (exp instanceof Expression && !ModelHelper.isAnExpressionCopy((Expression) exp)) {
                            return evaluateExpression(context, exp);
                        }
                    }
                }
            }
        }
        return context.createSuccessStatus();
    }

    private IStatus evaluateExpression(final IValidationContext context, final EObject eObj) {
        final Expression expression = (Expression) eObj;
        final String scriptText = expression.getContent();
        if (scriptText == null || scriptText.isEmpty()) {
            return context.createSuccessStatus();
        }
        if(scriptText.contains("BonitaTypes.")){
            return context.createFailureStatus(Messages.bonitaTypesProvidedScriptDeprecationMessage);
        }
        if(scriptText.contains("BonitaXML.")){
            return context.createFailureStatus(Messages.bonitaXMLProvidedScriptDeprecationMessage);
        }
        if(scriptText.contains("BonitaUsers.")){
            return context.createFailureStatus(Messages.bonitaUsersProvidedScriptDeprecationMessage);
        }
        if(scriptText.contains("BonitaSql.")){
            return context.createFailureStatus(Messages.bonitaSqlProvidedScriptDeprecationMessage);
        }
        return context.createSuccessStatus();
    }

    @Override
    protected String getConstraintId() {
        return CONSTRAINT_ID;
    }

}
