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

import java.util.List;
import java.util.Objects;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.data.provider.JDTMethodHelper;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.JavaObjectData;
import org.bonitasoft.studio.validation.constraints.AbstractLiveValidationMarkerConstraint;
import org.bonitasoft.studio.validation.i18n.Messages;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.osgi.util.NLS;

import com.google.common.base.Optional;
import com.google.common.base.Predicate;

public class JavaGetterExpressionConstraint extends AbstractLiveValidationMarkerConstraint {

    public static final String ID = "org.bonitasoft.studio.validation.constraints.javaGetterExpression";

    @Override
    protected String getConstraintId() {
        return ID;
    }

    @Override
    protected IStatus performBatchValidation(final IValidationContext ctx) {
        final EObject eObj = ctx.getTarget();
        checkArgument(eObj instanceof Expression);
        final Expression expression = (Expression) eObj;
        if (Objects.equals(expression.getType(), ExpressionConstants.JAVA_TYPE)) {
            try {
                return validateMethodExists(ctx, expression);
            } catch (final JavaModelException e) {
                return ctx.createFailureStatus(e.getMessage());
            }
        }
        return ctx.createSuccessStatus();
    }

    private IStatus validateMethodExists(final IValidationContext ctx, final Expression expression) throws JavaModelException {
        final IJavaProject javaProject = javaProject();
        final String dataClassname = toDataClassname(expression);
        final IType type = javaProject.findType(dataClassname);
        if (type == null) {
            return ctx.createFailureStatus(NLS.bind(Messages.failedToRetrieveExpressionType, dataClassname));
        }
        final String methodName = expression.getContent();
        final Optional<IMethod> foundMethod = tryFind(JDTMethodHelper.allPublicMethodWithoutParameterReturningNonVoid(type), methodWithName(methodName));
        if (!foundMethod.isPresent()) {
            return ctx.createFailureStatus(NLS.bind(Messages.methodDoesnotExist, methodName, dataName(expression)));
        }
        return ctx.createSuccessStatus();
    }

    private Object dataName(final Expression expression) {
        return ((Data) expression.getReferencedElements().get(0)).getName();
    }

    private String toDataClassname(final Expression expression) {
        checkArgument(!expression.getReferencedElements().isEmpty());
        final EObject ref = expression.getReferencedElements().get(0);
        checkArgument(ref instanceof Data);
        final Data data = (Data) ref;
        if (data.isMultiple()) {
            return List.class.getName();
        }
        if (data instanceof JavaObjectData) {
            return ((JavaObjectData) data).getClassName();
        }
        throw new IllegalStateException("Data should be multiple or a JavaObjectData, but was: " + data);
    }

    private Predicate<IMethod> methodWithName(final String name) {
        return new Predicate<IMethod>() {

            @Override
            public boolean apply(final IMethod method) {
                return Objects.equals(name, method.getElementName());
            }

        };
    }

    protected IJavaProject javaProject() {
        return RepositoryManager.getInstance().getCurrentRepository().getJavaProject();
    }

}
