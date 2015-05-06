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
package org.bonitasoft.studio.contract.ui.property.constraint.edit.editor;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.bonitasoft.studio.assertions.StatusAssert;
import org.bonitasoft.studio.contract.i18n.Messages;
import org.bonitasoft.studio.swt.rules.RealmWithDisplay;
import org.codehaus.jdt.groovy.model.GroovyCompilationUnit;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.core.runtime.IStatus;
import org.junit.Rule;
import org.junit.Test;

import com.google.common.collect.Lists;

public class ConstraintExpressionEditorValidatorTest {

    @Rule
    public RealmWithDisplay realmWithDisplay = new RealmWithDisplay();

    @Test
    public void should_fails_with_empty_content_error() throws Exception {
        final ConstraintExpressionEditorValidator validator = new ConstraintExpressionEditorValidator(anExpressionContentObservable(""), null, null, null);

        final IStatus status = validator.validate();

        StatusAssert.assertThat(status).isNotOK().hasMessage(Messages.emptyExpressionContent);
    }

    @Test
    public void should_fails_with_compilation_error() throws Exception {
        final CompilationProblemRequestor requestor = mock(CompilationProblemRequestor.class);
        final ConstraintExpressionEditorValidator validator = new ConstraintExpressionEditorValidator(anExpressionContentObservable("return true &&"), null,
                mock(GroovyCompilationUnit.class), requestor);
        when(requestor.isEmpty()).thenReturn(false);
        when(requestor.toString()).thenReturn("requestor error");

        final IStatus status = validator.validate();

        StatusAssert.assertThat(status).isNotOK().hasMessage("requestor error");
    }

    @Test
    public void should_warn_with_no_input_referenced() throws Exception {
        final CompilationProblemRequestor requestor = mock(CompilationProblemRequestor.class);
        when(requestor.isEmpty()).thenReturn(true);
        final ConstraintExpressionEditorValidator validator = new ConstraintExpressionEditorValidator(anExpressionContentObservable("return true;"),
                anInputNamesListObservable(),
                mock(GroovyCompilationUnit.class), requestor);

        final IStatus status = validator.validate();

        StatusAssert.assertThat(status).isNotOK().hasSeverity(IStatus.WARNING).hasMessage(Messages.noContractInputReferencedInExpression);
    }

    @Test
    public void should_pass_without_errors() throws Exception {
        final CompilationProblemRequestor requestor = mock(CompilationProblemRequestor.class);
        when(requestor.isEmpty()).thenReturn(true);
        final ConstraintExpressionEditorValidator validator = new ConstraintExpressionEditorValidator(anExpressionContentObservable("return true;"),
                anInputNamesListObservable("input1"),
                mock(GroovyCompilationUnit.class), requestor);

        final IStatus status = validator.validate();

        StatusAssert.assertThat(status).isOK();
    }

    private IObservableList anInputNamesListObservable(final String... inputNames) {
        final WritableList list = new WritableList(Lists.newArrayList(), String.class);
        for (final String inputName : inputNames) {
            list.add(inputName);
        }
        return list;
    }

    private IObservableValue anExpressionContentObservable(final String expression) {
        return new WritableValue(expression,
                String.class);
    }
}
