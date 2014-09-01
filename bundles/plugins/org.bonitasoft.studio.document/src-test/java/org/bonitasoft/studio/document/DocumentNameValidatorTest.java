/**
 * Copyright (C) 2014 BonitaSoft S.A.
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
package org.bonitasoft.studio.document;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.bonitasoft.studio.common.jface.databinding.validator.EmptyInputValidator;
import org.bonitasoft.studio.common.jface.databinding.validator.GroovyReferenceValidator;
import org.bonitasoft.studio.document.i18n.Messages;
import org.bonitasoft.studio.model.process.Document;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.eclipse.core.runtime.IStatus;
import org.junit.Before;
import org.junit.Test;


public class DocumentNameValidatorTest {

    private Pool pool;
    private Document document;
    private static String EXISTING_DOCUMENT_NAME = "docName";

    @Test
    public void testDocumentNameDuplicatedWithCurrentName() {
        final DocumentNameValidator documentNameValidator = new DocumentNameValidator(pool, EXISTING_DOCUMENT_NAME);
        final IStatus status = documentNameValidator.validate(EXISTING_DOCUMENT_NAME);
        assertTrue("The validation status should be ok and consider the currentName parameter.\n" + status, status.isOK());
    }

    @Test
    public void testDocumentNameNotDuplicatedWithCurrentNameSpecified() {
        final DocumentNameValidator documentNameValidator = new DocumentNameValidator(pool, EXISTING_DOCUMENT_NAME);
        final IStatus status = documentNameValidator.validate("aNewName");
        assertTrue("The validation status should be ok and consider the currentName parameter.\n" + status, status.isOK());
    }

    @Test
    public void testDocumentNameWithoutCurrentName() {
        final DocumentNameValidator documentNameValidator = new DocumentNameValidator(pool, null);
        final IStatus status = documentNameValidator.validate(EXISTING_DOCUMENT_NAME);
        assertFalse("The validation status should not be ok.\n" + status, status.isOK());
        assertThat(status.getMessage()).isEqualTo(Messages.error_documentAllreadyexist);
    }

    @Test
    public void testInvalidDocumentName() {
        final DocumentNameValidator documentNameValidator = new DocumentNameValidator(pool, null);
        final IStatus status = documentNameValidator.validate("a name");
        assertFalse("The validation status should not be ok.\n" + status, status.isOK());
        assertThat(status.getMessage()).isEqualTo(new GroovyReferenceValidator("").validate("a name").getMessage());
    }

    @Test
    public void testEmptyDocumentName() {
        final DocumentNameValidator documentNameValidator = new DocumentNameValidator(pool, null);
        final IStatus status = documentNameValidator.validate("");
        assertFalse("The validation status should not be ok.\n" + status, status.isOK());
        assertThat(status.getMessage()).isEqualTo(new EmptyInputValidator("Name").validate("").getMessage());
    }

    @Before
    public void setup() {
        pool = ProcessFactory.eINSTANCE.createPool();
        document = ProcessFactory.eINSTANCE.createDocument();
        document.setName(EXISTING_DOCUMENT_NAME);
        pool.getDocuments().add(document);
    }

}
