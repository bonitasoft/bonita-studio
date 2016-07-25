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

import static org.bonitasoft.studio.model.process.builders.ContractInputBuilder.aContractInput;
import static org.bonitasoft.studio.model.process.builders.DocumentBuilder.aDocument;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.bonitasoft.studio.model.process.ContractInputType;
import org.bonitasoft.studio.model.process.DocumentType;
import org.bonitasoft.studio.validation.i18n.Messages;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;
import org.junit.Test;

public class DocumentWithContractInputContentConstraintTest {

    @Test
    public void should_fail_if_input_is_missing() throws Exception {
        final DocumentWithContractInputContentConstraint constraint = new DocumentWithContractInputContentConstraint();
        final IValidationContext aValidationContext = aValidationContext(aDocument().withName("myDoc").withDocumentType(DocumentType.CONTRACT).build());

        constraint.performBatchValidation(aValidationContext);

        verify(aValidationContext).createFailureStatus(Messages.bind(Messages.missingFileContractInput, "myDoc"));
    }

    @Test
    public void should_fail_if_input_is_not_a_FILE() throws Exception {
        final DocumentWithContractInputContentConstraint constraint = new DocumentWithContractInputContentConstraint();
        final IValidationContext aValidationContext = aValidationContext(aDocument().withName("myDoc").withDocumentType(DocumentType.CONTRACT)
                .havingContractInput(aContractInput().withType(ContractInputType.TEXT)).build());

        constraint.performBatchValidation(aValidationContext);

        verify(aValidationContext).createFailureStatus(Messages.bind(Messages.invalidFileContractInputType, "myDoc", ContractInputType.TEXT.name()));
    }

    @Test
    public void should_fail_if_input_is_multiple_and_document_is_not() throws Exception {
        final DocumentWithContractInputContentConstraint constraint = new DocumentWithContractInputContentConstraint();
        final IValidationContext aValidationContext = aValidationContext(aDocument().withName("myDoc").withDocumentType(DocumentType.CONTRACT)
                .havingContractInput(aContractInput().withType(ContractInputType.FILE).multiple()).build());

        constraint.performBatchValidation(aValidationContext);

        verify(aValidationContext).createFailureStatus(Messages.bind(Messages.invalidMultipleFileContractInput, "myDoc"));
    }

    @Test
    public void should_fail_if_input_is_single_and_document_is_multiple() throws Exception {
        final DocumentWithContractInputContentConstraint constraint = new DocumentWithContractInputContentConstraint();
        final IValidationContext aValidationContext = aValidationContext(aDocument().withName("myDoc").withDocumentType(DocumentType.CONTRACT)
                .havingContractInput(aContractInput().withType(ContractInputType.FILE)).multiple().build());

        constraint.performBatchValidation(aValidationContext);

        verify(aValidationContext).createFailureStatus(Messages.bind(Messages.invalidSingleFileContractInput, "myDoc"));
    }

    @Test
    public void should_not_fail_if_input_is_single_and_document_is_single() throws Exception {
        final DocumentWithContractInputContentConstraint constraint = new DocumentWithContractInputContentConstraint();
        final IValidationContext aValidationContext = aValidationContext(aDocument().withName("myDoc").withDocumentType(DocumentType.CONTRACT)
                .havingContractInput(aContractInput().withType(ContractInputType.FILE)).build());

        constraint.performBatchValidation(aValidationContext);

        verify(aValidationContext).createSuccessStatus();
    }

    @Test
    public void should_not_fail_if_input_is_multiple_and_document_is_multiple() throws Exception {
        final DocumentWithContractInputContentConstraint constraint = new DocumentWithContractInputContentConstraint();
        final IValidationContext aValidationContext = aValidationContext(aDocument().withName("myDoc").withDocumentType(DocumentType.CONTRACT)
                .havingContractInput(aContractInput().withType(ContractInputType.FILE).multiple()).multiple().build());

        constraint.performBatchValidation(aValidationContext);

        verify(aValidationContext).createSuccessStatus();
    }

    @Test
    public void should_not_fail_if_document_type_is_not_CONTRACT() throws Exception {
        final DocumentWithContractInputContentConstraint constraint = new DocumentWithContractInputContentConstraint();
        final IValidationContext aValidationContext = aValidationContext(aDocument().withName("myDoc").withDocumentType(DocumentType.NONE).build());

        constraint.performBatchValidation(aValidationContext);

        verify(aValidationContext).createSuccessStatus();
    }

    private IValidationContext aValidationContext(final EObject target) {
        final IValidationContext context = mock(IValidationContext.class);
        doReturn(target).when(context).getTarget();
        return context;
    }
}
