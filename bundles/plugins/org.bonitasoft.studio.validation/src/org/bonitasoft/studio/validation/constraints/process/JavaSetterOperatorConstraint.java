/**
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.validation.constraints.process;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.collect.Iterables.tryFind;

import java.util.Objects;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.groovy.ui.JDTMethodHelper;
import org.bonitasoft.studio.model.expression.Operation;
import org.bonitasoft.studio.model.expression.Operator;
import org.bonitasoft.studio.validation.constraints.AbstractLiveValidationMarkerConstraint;
import org.bonitasoft.studio.validation.i18n.Messages;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.Signature;
import org.eclipse.osgi.util.NLS;

import com.google.common.base.Optional;
import com.google.common.base.Predicate;

public class JavaSetterOperatorConstraint extends AbstractLiveValidationMarkerConstraint {

    public static final String ID = "org.bonitasoft.studio.validation.constraints.javaSetterOperator";

    @Override
    protected String getConstraintId() {
        return ID;
    }

    @Override
    protected IStatus performBatchValidation(final IValidationContext ctx) {
        final EObject eObj = ctx.getTarget();
        checkArgument(eObj instanceof Operator);
        final Operator operator = (Operator) eObj;
        if (operator.eContainer() instanceof Operation) {
            if (Objects.equals(operator.getType(), ExpressionConstants.JAVA_METHOD_OPERATOR)) {
                try {
                    return validateMethodExists(ctx, operator, leftOperandType(operator));
                } catch (final JavaModelException e) {
                    return ctx.createFailureStatus(e.getMessage());
                }
            }
        }
        return ctx.createSuccessStatus();
    }

    private IStatus validateMethodExists(final IValidationContext ctx, final Operator operator,
            final String leftOperandType) throws JavaModelException {
        final IJavaProject javaProject = javaProject();
        final IType type = javaProject.findType(leftOperandType);
        if (type == null) {
            return ctx.createFailureStatus(NLS.bind(Messages.failedToRetrieveLeftOperandType, leftOperandType));
        }
        final String parameterType = operator.getInputTypes().get(0);
        final String methodName = operator.getExpression();
        final Optional<IMethod> foundMethod = tryFind(JDTMethodHelper.allPublicMethodWithOneParameter(type), methodWith(methodName, parameterType));
        if (!foundMethod.isPresent()) {
            return ctx.createFailureStatus(NLS.bind(Messages.methodDoesnotExistInLeftOperandType,
                    new String[] { methodName, parameterType, leftOperandName(operator) }));
        }
        return ctx.createSuccessStatus();
    }

    private String leftOperandName(final Operator operator) {
        return ((Operation) operator.eContainer()).getLeftOperand().getName();
    }

    private Predicate<IMethod> methodWith(final String name, final String returnType) {
        return new Predicate<IMethod>() {

            @Override
            public boolean apply(final IMethod method) {
                return Objects.equals(name, method.getElementName()) && Objects.equals(returnType,
                        toQualfiedType(method.getParameterTypes()[0], method.getDeclaringType()));
            }

        };
    }

    protected String toQualfiedType(final String parameterType, final IType declaringType) {
        return JDTMethodHelper.retrieveQualifiedType(Signature.getTypeErasure(parameterType), declaringType);
    }

    protected IJavaProject javaProject() {
        return RepositoryManager.getInstance().getCurrentRepository().getJavaProject();
    }

    private String leftOperandType(final Operator operator) {
        checkArgument(operator.eContainer() instanceof Operation);
        final Operation operation = (Operation) operator.eContainer();
        return operation.getLeftOperand().getReturnType();
    }
}
