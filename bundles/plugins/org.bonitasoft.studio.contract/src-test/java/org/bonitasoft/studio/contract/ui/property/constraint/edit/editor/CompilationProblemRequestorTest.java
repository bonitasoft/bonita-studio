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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import org.bonitasoft.studio.contract.i18n.Messages;
import org.eclipse.jdt.core.compiler.IProblem;
import org.junit.Test;

public class CompilationProblemRequestorTest {

    @Test
    public void should_be_active() throws Exception {
        final CompilationProblemRequestor mvelProblemRequestor = new CompilationProblemRequestor();

        assertThat(mvelProblemRequestor.isActive()).isTrue();
    }

    @Test
    public void should_insert_problem_when_accepting() throws Exception {
        final CompilationProblemRequestor mvelProblemRequestor = new CompilationProblemRequestor();

        mvelProblemRequestor.acceptProblem(aProblem("An error message"));

        assertThat(mvelProblemRequestor.isEmpty()).isFalse();
    }

    @Test
    public void should_reset_problems_when_beginning_report() throws Exception {
        final CompilationProblemRequestor mvelProblemRequestor = new CompilationProblemRequestor();

        mvelProblemRequestor.acceptProblem(aProblem("An error message"));
        mvelProblemRequestor.beginReporting();

        assertThat(mvelProblemRequestor.isEmpty()).isTrue();
    }

    @Test
    public void should_print_a_human_readable_with_message_when_a_single_problem_has_been_found() throws Exception {
        final CompilationProblemRequestor mvelProblemRequestor = new CompilationProblemRequestor();

        mvelProblemRequestor.acceptProblem(aProblem("An error message"));

        assertThat(mvelProblemRequestor.toString()).isEqualTo("An error message");
    }

    @Test
    public void should_print_a_human_readable_message_when_several_problem_has_been_found() throws Exception {
        final CompilationProblemRequestor mvelProblemRequestor = new CompilationProblemRequestor();

        mvelProblemRequestor.acceptProblem(aProblem("An error message"));
        mvelProblemRequestor.acceptProblem(aProblem("Another error message"));

        assertThat(mvelProblemRequestor.toString()).isEqualTo(Messages.bind(Messages.severalCompilationErrors, 2));
    }

    private IProblem aProblem(final String message) {
        final IProblem problem = mock(IProblem.class);
        doReturn(message).when(problem).getMessage();
        return problem;
    }

}
