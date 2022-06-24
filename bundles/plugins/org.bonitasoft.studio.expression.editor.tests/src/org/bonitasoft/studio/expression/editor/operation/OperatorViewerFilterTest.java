/**
 * Copyright (C) 2014 Bonitasoft S.A.
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
package org.bonitasoft.studio.expression.editor.operation;

import static org.assertj.core.api.Assertions.assertThat;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.expression.Operation;
import org.bonitasoft.studio.model.process.Document;
import org.bonitasoft.studio.model.process.JavaObjectData;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.XMLData;
import org.junit.Test;

/**
 * @author Aurelien Pupier
 */
public class OperatorViewerFilterTest {

    @Test
    public void testFilterOperatorOnSimpleDocument() {
        final Operation operation = ExpressionFactory.eINSTANCE.createOperation();
        final Expression leftOperand = ExpressionFactory.eINSTANCE.createExpression();
        final Document documentReferenced = ProcessFactory.eINSTANCE.createDocument();
        leftOperand.setType(ExpressionConstants.DOCUMENT_REF_TYPE);
        leftOperand.getReferencedElements().add(documentReferenced);
        operation.setLeftOperand(leftOperand);

        final OperatorViewerFilter operatorViewerFilter = new OperatorViewerFilter(operation);

        assertThat(operatorViewerFilter.select(null, null, ExpressionConstants.SET_DOCUMENT_OPERATOR)).isTrue();
        assertThat(operatorViewerFilter.select(null, null, ExpressionConstants.SET_LIST_DOCUMENT_OPERATOR)).isFalse();
        assertThat(operatorViewerFilter.select(null, null, ExpressionConstants.ASSIGNMENT_OPERATOR)).isFalse();
        assertThat(operatorViewerFilter.select(null, null, ExpressionConstants.BUSINESS_DATA_JAVA_SETTER_OPERATOR)).isFalse();
        assertThat(operatorViewerFilter.select(null, null, ExpressionConstants.XPATH_UPDATE_OPERATOR)).isFalse();
    }

    @Test
    public void testFilterOperatorOnXMLData() {
        final Operation operation = ExpressionFactory.eINSTANCE.createOperation();
        final Expression leftOperand = ExpressionFactory.eINSTANCE.createExpression();
        final XMLData xmlData = ProcessFactory.eINSTANCE.createXMLData();
        leftOperand.setType(ExpressionConstants.VARIABLE_TYPE);
        leftOperand.getReferencedElements().add(xmlData);
        operation.setLeftOperand(leftOperand);

        final OperatorViewerFilter operatorViewerFilter = new OperatorViewerFilter(operation);

        assertThat(operatorViewerFilter.select(null, null, ExpressionConstants.SET_DOCUMENT_OPERATOR)).isFalse();
        assertThat(operatorViewerFilter.select(null, null, ExpressionConstants.SET_LIST_DOCUMENT_OPERATOR)).isFalse();
        assertThat(operatorViewerFilter.select(null, null, ExpressionConstants.ASSIGNMENT_OPERATOR)).isTrue();
        assertThat(operatorViewerFilter.select(null, null, ExpressionConstants.BUSINESS_DATA_JAVA_SETTER_OPERATOR)).isFalse();
        assertThat(operatorViewerFilter.select(null, null, ExpressionConstants.XPATH_UPDATE_OPERATOR)).isTrue();
    }

    @Test
    public void testFilterOperatorOnJavaData() {
        final Operation operation = ExpressionFactory.eINSTANCE.createOperation();
        final Expression leftOperand = ExpressionFactory.eINSTANCE.createExpression();
        final JavaObjectData xmlData = ProcessFactory.eINSTANCE.createJavaObjectData();
        leftOperand.setType(ExpressionConstants.VARIABLE_TYPE);
        leftOperand.getReferencedElements().add(xmlData);
        operation.setLeftOperand(leftOperand);

        final OperatorViewerFilter operatorViewerFilter = new OperatorViewerFilter(operation);

        assertThat(operatorViewerFilter.select(null, null, ExpressionConstants.SET_DOCUMENT_OPERATOR)).isFalse();
        assertThat(operatorViewerFilter.select(null, null, ExpressionConstants.SET_LIST_DOCUMENT_OPERATOR)).isFalse();
        assertThat(operatorViewerFilter.select(null, null, ExpressionConstants.ASSIGNMENT_OPERATOR)).isTrue();
        assertThat(operatorViewerFilter.select(null, null, ExpressionConstants.BUSINESS_DATA_JAVA_SETTER_OPERATOR)).isFalse();
        assertThat(operatorViewerFilter.select(null, null, ExpressionConstants.XPATH_UPDATE_OPERATOR)).isFalse();
        assertThat(operatorViewerFilter.select(null, null, ExpressionConstants.JAVA_METHOD_OPERATOR)).isTrue();
    }

    @Test
    public void testOperatorOnListDocument() {
        final Pool pool = ProcessFactory.eINSTANCE.createPool();
        final Document document = ProcessFactory.eINSTANCE.createDocument();
        document.setMultiple(true);
        document.setName("testDocument");
        pool.getDocuments().add(document);
        final Operation operation = ExpressionFactory.eINSTANCE.createOperation();
        final Expression leftOperand = ExpressionFactory.eINSTANCE.createExpression();
        final Document documentReferenced = (Document) ExpressionHelper.createDependencyFromEObject(document);
        leftOperand.setType(ExpressionConstants.DOCUMENT_REF_TYPE);
        leftOperand.getReferencedElements().add(documentReferenced);
        operation.setLeftOperand(leftOperand);

        final OperatorViewerFilter operatorViewerFilter = new OperatorViewerFilter(operation);

        assertThat(operatorViewerFilter.select(null, null, ExpressionConstants.SET_DOCUMENT_OPERATOR)).isFalse();
        assertThat(operatorViewerFilter.select(null, null, ExpressionConstants.SET_LIST_DOCUMENT_OPERATOR)).isTrue();
        assertThat(operatorViewerFilter.select(null, null, ExpressionConstants.ASSIGNMENT_OPERATOR)).isFalse();
        assertThat(operatorViewerFilter.select(null, null, ExpressionConstants.BUSINESS_DATA_JAVA_SETTER_OPERATOR)).isFalse();
        assertThat(operatorViewerFilter.select(null, null, ExpressionConstants.XPATH_UPDATE_OPERATOR)).isFalse();
    }
}
