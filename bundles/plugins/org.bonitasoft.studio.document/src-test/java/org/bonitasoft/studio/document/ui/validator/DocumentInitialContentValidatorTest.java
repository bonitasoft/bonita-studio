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
package org.bonitasoft.studio.document.ui.validator;

import static org.bonitasoft.studio.model.expression.builders.ExpressionBuilder.aGroovyScriptExpression;
import static org.bonitasoft.studio.model.expression.builders.ExpressionBuilder.anExpression;
import static org.bonitasoft.studio.model.process.builders.DocumentBuilder.aDocument;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.bonitasoft.studio.document.ui.validator.DocumentInitialContentValidator;
import org.bonitasoft.studio.model.process.Document;
import org.bonitasoft.studio.model.process.DocumentType;
import org.eclipse.core.runtime.IStatus;
import org.junit.Test;

/**
 * @author aurelie Zara
 */
public class DocumentInitialContentValidatorTest {

    private final static int MAX_LENGTH = 1023;

    @Test
    public void test_Document_URL_length_OK() {
        final DocumentInitialContentValidator validator = new DocumentInitialContentValidator(aDocumentWithURL("http://www.bonitasoft.com"), MAX_LENGTH);

        final IStatus status = validator.validate(null);

        assertTrue("the status should be ok but is " + status.getMessage(), status.isOK());

    }

    @Test
    public void test_Document_URL_Length_NOK() {
        final DocumentInitialContentValidator validator = new DocumentInitialContentValidator(
                aDocumentWithURL("http://www.bonitasoft.com"), 5);

        final IStatus status = validator.validate(null);

        assertFalse("the status should  not be ok", status.isOK());
    }

    @Test
    public void test_Document_URL_Content_EMPTY_NOK() {
        final DocumentInitialContentValidator validator = new DocumentInitialContentValidator(aDocumentWithURL(""), MAX_LENGTH);

        final IStatus status = validator.validate(null);

        assertFalse("the status should be an error", status.isOK());
    }

    @Test
    public void test_Document_URL_Content_Null_NOK() {
        final DocumentInitialContentValidator validator = new DocumentInitialContentValidator(aDocumentWithURL(null), MAX_LENGTH);

        final IStatus status = validator.validate(null);

        assertFalse("the status should be an error " + status.getMessage(), status.isOK());
    }

    @Test
    public void test_Document_URL_NULL_NOK() {
        final DocumentInitialContentValidator validator = new DocumentInitialContentValidator(aDocument().withDocumentType(DocumentType.EXTERNAL).build(),
                MAX_LENGTH);

        final IStatus status = validator.validate(null);

        assertFalse("the status should be an error " + status.getMessage(), status.isOK());
    }

    @Test
    public void test_Multiple_Document_InitialContent_Null_OK() {
        final DocumentInitialContentValidator validator = new DocumentInitialContentValidator(aDocument().multiple().build(),
                MAX_LENGTH);

        final IStatus status = validator.validate(null);

        assertTrue("the status should not be ok " + status.getMessage(), status.isOK());
    }

    @Test
    public void test_Multiple_Document_InitialContent_OK() {
        final DocumentInitialContentValidator validator = new DocumentInitialContentValidator(aDocument().multiple()
                .havingInitialMultipleContent(aGroovyScriptExpression().withContent("[]").withReturnType(List.class.getName())).build(),
                MAX_LENGTH);

        final IStatus status = validator.validate(null);

        assertTrue("the status should not be ok " + status.getMessage(), status.isOK());
    }

    private Document aDocumentWithURL(final String url) {
        return aDocument().withDocumentType(DocumentType.EXTERNAL).havingURL(anExpression().withContent(url)).build();
    }

}
