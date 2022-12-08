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

import static com.google.common.collect.Iterables.toArray;
import static org.bonitasoft.studio.model.expression.builders.ExpressionBuilder.anExpression;
import static org.bonitasoft.studio.model.process.builders.PoolBuilder.aPool;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.model.process.builders.JavaObjectDataBuilder;
import org.bonitasoft.studio.validation.i18n.Messages;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.jdt.core.Flags;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.osgi.util.NLS;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class JavaGetterExpressionConstraintTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();
    @Mock
    private IJavaProject javaProject;

    @Test
    public void should_throw_an_IllegalArgumentException_if_context_eobject_is_not_an_operator() throws Exception {
        final JavaGetterExpressionConstraint constraint = createConstraint();

        thrown.expect(IllegalArgumentException.class);

        constraint.performBatchValidation(aValidationContext(aPool().build()));
    }

    @Test
    public void should_not_fail_if_operator_is_not_a_Java_expression_type() throws Exception {
        final JavaGetterExpressionConstraint constraint = createConstraint();

        final IValidationContext aValidationContext = aValidationContext(anExpression().withExpressionType(ExpressionConstants.CONSTANT_TYPE).build());
        constraint.performBatchValidation(aValidationContext);

        verify(aValidationContext).createSuccessStatus();
    }

    @Test
    public void should_fail_if_setter_method_doesnt_exist_in_left_variable_class() throws Exception {
        final JavaGetterExpressionConstraint constraint = createConstraint();
        final IType employeeType = typeWithMethods(aPublicMethod("getFirstName", String.class.getName()));
        doReturn(employeeType).when(javaProject).findType("com.test.Employee");

        final IValidationContext aValidationContext = aValidationContext(anExpression()
                .withExpressionType(ExpressionConstants.JAVA_TYPE)
                .withContent("getName")
                .havingReferencedElements(JavaObjectDataBuilder.aJavaObjectData().withName("myEmployee").withClassname("com.test.Employee"))
                .build());
        constraint.performBatchValidation(aValidationContext);

        verify(aValidationContext).createFailureStatus(
                NLS.bind(Messages.methodDoesnotExist, new String[] { "getName",
                        "myEmployee" }));
    }

    @Test
    public void should_not_fail_if_setter_method_exists_in_left_variable_class() throws Exception {
        final JavaGetterExpressionConstraint constraint = createConstraint();
        final IType employeeType = typeWithMethods(aPublicMethod("getName", String.class.getName()));
        doReturn(employeeType).when(javaProject).findType("com.test.Employee");

        final IValidationContext aValidationContext = aValidationContext(anExpression()
                .withExpressionType(ExpressionConstants.JAVA_TYPE)
                .withContent("getName")
                .havingReferencedElements(JavaObjectDataBuilder.aJavaObjectData().withName("myEmployee").withClassname("com.test.Employee"))
                .build());
        constraint.performBatchValidation(aValidationContext);

        verify(aValidationContext).createSuccessStatus();
    }

    private IType typeWithMethods(final MethodSignature... methods) throws JavaModelException {
        final IType type = mock(IType.class);
        doReturn(methodsFormSignature(methods)).when(type).getMethods();
        return type;
    }

    private IMethod[] methodsFormSignature(final MethodSignature[] methods)
            throws JavaModelException {
        final List<IMethod> result = new ArrayList<IMethod>();
        for (final MethodSignature s : methods) {
            result.add(iMethod(s));
        }
        return toArray(result, IMethod.class);
    }

    private IMethod iMethod(final MethodSignature signature) throws JavaModelException {
        final IMethod method = mock(IMethod.class);
        doReturn(signature.getName()).when(method).getElementName();
        doReturn(signature.getFlags()).when(method).getFlags();
        doReturn(signature.getReturnType()).when(method).getReturnType();
        doReturn(new String[0]).when(method).getParameterTypes();
        return method;
    }

    private MethodSignature aPublicMethod(final String name, final String returnType) {
        return new MethodSignature(Flags.AccPublic, name, returnType);
    }

    private IValidationContext aValidationContext(final EObject eObject) {
        final IValidationContext validationContext = mock(IValidationContext.class);
        when(validationContext.getTarget()).thenReturn(eObject);
        return validationContext;
    }

    private JavaGetterExpressionConstraint createConstraint() {
        final JavaGetterExpressionConstraint constraint = spy(new JavaGetterExpressionConstraint());
        doReturn(javaProject).when(constraint).javaProject();
        return constraint;
    }

    private class MethodSignature {

        private final String name;
        private final String returnType;
        private final int visibilityFlags;

        public MethodSignature(final int visibilityFlags, final String name, final String returnType) {
            this.name = name;
            this.visibilityFlags = visibilityFlags;
            this.returnType = returnType;
        }

        public int getFlags() {
            return visibilityFlags;
        }

        public String getName() {
            return name;
        }

        public String getReturnType() {
            return returnType;
        }

    }
}
