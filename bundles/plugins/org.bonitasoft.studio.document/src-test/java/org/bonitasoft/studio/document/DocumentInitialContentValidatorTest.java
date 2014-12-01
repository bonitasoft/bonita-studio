/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.document;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.process.Document;
import org.bonitasoft.studio.model.process.DocumentType;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.eclipse.core.runtime.IStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * @author aurelie Zara
 *
 */
public class DocumentInitialContentValidatorTest {

    private Document document;
    private Expression urlExpression;
    private DocumentInitialContentValidator validator;

    final int maxLength = 1023;
    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        document = ProcessFactory.eINSTANCE.createDocument();
        urlExpression = ExpressionFactory.eINSTANCE.createExpression();
        validator = new DocumentInitialContentValidator(maxLength);
    }


    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    /**
     *
     */
    @Test
    public void test_Document_URL_length_OK() {
        initializeDocumentWithURL("http://www.bonitasoft.com");
        final IStatus status = validator.validate(document);
        assertTrue("the status should be ok but is " + status.getMessage(), status.isOK());

    }

    @Test
    public void test_Document_URL_Length_NOK() {
        initializeDocumentWithURL("http://www.bonitasoft.com/ijfiosdjfoids/opsdkfopkdspofksd/sopdfkpodskfposdkpof/dpsokfopsdkfpoksdpf/odpskfposdkpofkopsdkf/opsdkfpodskpof/opdskfposdkpofksd/posdkfposkdpofkpsod/opdskfposkdopfkdposfkposd/odskfopsdkopfksdopfkopsd/qosidjioqsjdoiqjdoidojdojqojdoiqsqojd/osqpkdopskqodpkqsopkdopsqkdopksqpod/sqopkdopqskdopkqsopdkopsqkdopkqspodkqsopdkpoqsd/opsqkdopqskdopkqsopdkpoqskdopkqsopdkopqskdopqskd/oqpkdpokqpodkopsqkdpoqkdopkqsopdkopqskdopqskdopkqspodkqopskd/oqpskdpokqspodkqsopdkpoqkdpoqskdopksqopdksqpokdpoqskdpoqs/qsjdoiqsjdoiqsjdiojsqoidjqosijdioqsjdoq/oqpsdkpoqskdpoqskdopkqsopdkopsqkdpokqspokdpoqkdpoqsd/oqpkdopqkdopksqpokdopqkdpokqspod/qoskdpokspodkposkpdoksqpokdpoqskdpo/oqpskdopsqkopdksqpokdopqskopdkqspodkopqskdopkqsopdkopqskdopqskdopkqsopdkpoqs/psoqkdpokqsopkdopqskdopqskdopqskdpoksqpodkqpsod/spoqdkpoqksdpokqspodkpoqskdpoqsdkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkjoijsiodqjidoqisjdoiqsjdoijqsodjqoisjdoiqsjdoijqsiodjoiqjsdoiqsjdoijqsoidjqoisjdoi/qiojsdoijsoiqjdioqsjdoiqjsdoijsqoidjqsiojdoiqsjdioqsjdoiqsjdoijqsiodjqiosjdioqsjdoiqsjdioqsjdoiqsjdoijqsiodjioqsjd");
        final IStatus status = validator.validate(document);
        assertFalse("the status should  not be ok", status.isOK());
    }


    @Test
    public void test_Document_URL_Content_EMPTY_NOK() {
        initializeDocumentWithURL("");
        final IStatus status = validator.validate(document);
        assertFalse("the status should be an error", status.isOK());
    }

    @Test
    public void test_Document_URL_Content_Null_NOK() {
        document.setDocumentType(DocumentType.EXTERNAL);
        final Expression urlExpressionWithNullContent = ExpressionFactory.eINSTANCE.createExpression();
        document.setUrl(urlExpressionWithNullContent);
        final IStatus status = validator.validate(document);
        assertFalse("the status should be an error " + status.getMessage(), status.isOK());
    }

    @Test
    public void test_Document_URL_NULL_NOK() {
        document.setDocumentType(DocumentType.EXTERNAL);
        final IStatus status = validator.validate(document);
        assertFalse("the status should be an error " + status.getMessage(), status.isOK());
    }


    @Test
    public void test_Multiple_Document_InitialContent_Null_OK() {
        document.setMultiple(true);
        final IStatus status = validator.validate(document);
        assertTrue("the status should not be ok " + status.getMessage(), status.isOK());
    }

    @Test
    public void test_Multiple_Document_InitialContent_OK() {
        document.setMultiple(true);
        final Expression multipleInitialContent = ExpressionHelper.createGroovyScriptExpression("[]", List.class.getName());
        document.setInitialMultipleContent(multipleInitialContent);
        final IStatus status = validator.validate(document);
        assertTrue("the status should not be ok " + status.getMessage(), status.isOK());
    }

    private void initializeDocumentWithURL(final String url) {
        document.setDocumentType(DocumentType.EXTERNAL);
        urlExpression.setContent(url);
        document.setUrl(urlExpression);
    }



}
