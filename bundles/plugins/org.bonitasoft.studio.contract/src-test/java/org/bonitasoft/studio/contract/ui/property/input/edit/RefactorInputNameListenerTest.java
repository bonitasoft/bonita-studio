/**
 * Copyright (C) 2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.contract.ui.property.input.edit;

import static org.bonitasoft.studio.model.process.builders.ContractInputBuilder.aContractInput;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.notNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.lang.reflect.InvocationTargetException;

import org.bonitasoft.studio.contract.core.refactoring.RefactorContractInputOperation;
import org.bonitasoft.studio.model.process.ContractInput;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.progress.IProgressService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RefactorInputNameListenerTest {

    @Mock
    private Text textEditor;
    @Mock
    private RefactorContractInputOperation refactorOperation;
    @Mock
    private IProgressService progressService;

    @Test
    public void should_refactor_input_name_when_applying_editor_value_and_name_has_changed() throws Exception {
        final RefactorInputNameListener refactorInputNameListener = spy(new RefactorInputNameListener(progressService, aContractInput().withName("firstName")
                .build(),
                textEditor));
        when(textEditor.getText()).thenReturn("lastName");
        doReturn(refactorOperation).when(refactorInputNameListener).newRefactorOperation(eq("firstName"), notNull(ContractInput.class));

        refactorInputNameListener.applyEditorValue();

        verify(progressService).busyCursorWhile(refactorOperation);
    }

    @Test
    public void should_not_refactor_input_name_when_applying_editor_value_and_name_has_not_changed() throws Exception {
        final RefactorInputNameListener refactorInputNameListener = spy(new RefactorInputNameListener(progressService, aContractInput().withName("firstName")
                .build(),
                textEditor));
        when(textEditor.getText()).thenReturn("firstName");
        doReturn(refactorOperation).when(refactorInputNameListener).newRefactorOperation(eq("firstName"), notNull(ContractInput.class));

        refactorInputNameListener.applyEditorValue();

        verify(progressService, never()).busyCursorWhile(refactorOperation);
    }

    @Test
    public void should_open_error_dialog_if_an_exception_occurs_when_refactoring_input_name() throws Exception {
        final RefactorInputNameListener refactorInputNameListener = spy(new RefactorInputNameListener(progressService, aContractInput().withName("firstName")
                .build(),
                textEditor));
        when(textEditor.getText()).thenReturn("lastName");
        doReturn(refactorOperation).when(refactorInputNameListener).newRefactorOperation(eq("firstName"), notNull(ContractInput.class));
        final InvocationTargetException toBeThrown = new InvocationTargetException(new Throwable("an error message"));
        doThrow(toBeThrown).when(progressService).busyCursorWhile(refactorOperation);
        doNothing().when(refactorInputNameListener).openErrorDialog(notNull(String.class), notNull(String.class), eq(toBeThrown));

        refactorInputNameListener.applyEditorValue();

        verify(refactorInputNameListener).openErrorDialog("firstName", "lastName", toBeThrown);
    }
}
