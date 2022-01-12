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
import static org.bonitasoft.studio.model.expression.builders.OperationBuilder.anOperation;
import static org.bonitasoft.studio.model.expression.builders.OperatorBuilder.anOperator;
import static org.bonitasoft.studio.model.process.builders.PoolBuilder.aPool;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.ExpressionConstants;
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
public class JavaSetterOperatorConstraintTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();
    @Mock
    private IJavaProject javaProject;

    @Test
    public void should_throw_an_IllegalArgumentException_if_context_eobject_is_not_an_operator() throws Exception {
        final JavaSetterOperatorConstraint constraint = createConstraint();

        thrown.expect(IllegalArgumentException.class);

        constraint.performBatchValidation(aValidationContext(aPool().build()));
    }

    @Test
    public void should_not_fail_if_operator_is_not_a_JavaMethod_operator_type() throws Exception {
        final JavaSetterOperatorConstraint constraint = createConstraint();

        final IValidationContext aValidationContext = aValidationContext(anOperator().withType(ExpressionConstants.ASSIGNMENT_OPERATOR).build());
        constraint.performBatchValidation(aValidationContext);

        verify(aValidationContext).createSuccessStatus();
    }

    @Test
    public void should_fail_if_setter_method_doesnt_exist_in_left_variable_class() throws Exception {
        final JavaSetterOperatorConstraint constraint = createConstraint();
        final IType employeeType = typeWithMethods(constraint, aPublicMethod("setChecked", Boolean.class.getName()));
        doReturn(employeeType).when(javaProject).findType("com.test.Employee");

        final IValidationContext aValidationContext = aValidationContext(anOperator()
                .withType(ExpressionConstants.JAVA_METHOD_OPERATOR)
                .withExpression("setIsChecked")
                .havingInputTypes(Boolean.class.getName())
                .in(anOperation().havingLeftOperand(anExpression().withName("myEmployee").withReturnType("com.test.Employee")))
                .build());
        constraint.performBatchValidation(aValidationContext);

        verify(aValidationContext).createFailureStatus(
                NLS.bind(Messages.methodDoesnotExistInLeftOperandType, new String[] { "setIsChecked", Boolean.class.getName(),
                        "myEmployee" }));
    }

    @Test
    public void should_not_fail_if_setter_method_exists_in_left_variable_class() throws Exception {
        final JavaSetterOperatorConstraint constraint = createConstraint();
        final IType employeeType = typeWithMethods(constraint, aPublicMethod("setIsChecked", Boolean.class.getName()));
        doReturn(employeeType).when(javaProject).findType("com.test.Employee");

        final IValidationContext aValidationContext = aValidationContext(anOperator()
                .withType(ExpressionConstants.JAVA_METHOD_OPERATOR)
                .withExpression("setIsChecked")
                .havingInputTypes(Boolean.class.getName())
                .in(anOperation().havingLeftOperand(anExpression().withName("myEmployee").withReturnType("com.test.Employee")))
                .build());
        constraint.performBatchValidation(aValidationContext);

        verify(aValidationContext).createSuccessStatus();
    }

    private IType typeWithMethods(final JavaSetterOperatorConstraint constraint, final MethodSignature... methods) throws JavaModelException {
        final IType type = mock(IType.class);
        doReturn(methodsFormSignature(methods, type, constraint)).when(type).getMethods();
        return type;
    }

    private IMethod[] methodsFormSignature(final MethodSignature[] methods, final IType type, final JavaSetterOperatorConstraint constraint)
            throws JavaModelException {
        final List<IMethod> result = new ArrayList<IMethod>();
        for (final MethodSignature s : methods) {
            result.add(iMethod(s, type, constraint));
        }
        return toArray(result, IMethod.class);
    }

    private IMethod iMethod(final MethodSignature signature, final IType type, final JavaSetterOperatorConstraint constraint) throws JavaModelException {
        final IMethod method = mock(IMethod.class);
        doReturn(signature.getName()).when(method).getElementName();
        doReturn(signature.getFlags()).when(method).getFlags();
        doReturn(toArray(signature.getParameters(), String.class)).when(method).getParameterTypes();
        doReturn(type).when(method).getDeclaringType();
        for (final String returnTtype : signature.getParameters()) {
            doReturn(returnTtype).when(constraint).toQualfiedType(returnTtype, type);
        }
        return method;
    }

    private MethodSignature aPublicMethod(final String name, final String... parametersType) {
        return new MethodSignature(Flags.AccPublic, name, parametersType);
    }

    private IValidationContext aValidationContext(final EObject eObject) {
        final IValidationContext validationContext = mock(IValidationContext.class);
        when(validationContext.getTarget()).thenReturn(eObject);
        return validationContext;
    }

    private JavaSetterOperatorConstraint createConstraint() {
        final JavaSetterOperatorConstraint constraint = spy(new JavaSetterOperatorConstraint());
        doReturn(javaProject).when(constraint).javaProject();
        return constraint;
    }

    private class MethodSignature {

        private final String name;
        private final List<String> parameters = new ArrayList<String>();
        private final int visibilityFlags;

        public MethodSignature(final int visibilityFlags, final String name, final String... parametersType) {
            this.name = name;
            this.visibilityFlags = visibilityFlags;
            for (final String p : parametersType) {
                parameters.add(p);
            }
        }

        public int getFlags() {
            return visibilityFlags;
        }

        public String getName() {
            return name;
        }

        public List<String> getParameters() {
            return parameters;
        }

    }
}
