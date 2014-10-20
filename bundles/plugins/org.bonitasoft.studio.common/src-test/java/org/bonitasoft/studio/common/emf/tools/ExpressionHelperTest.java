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
package org.bonitasoft.studio.common.emf.tools;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.model.expression.assertions.ExpressionAssert.assertThat;
import static org.mockito.Matchers.any;

import java.util.Collection;

import org.bonitasoft.engine.bpm.document.DocumentValue;
import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.Messages;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.form.DateFormField;
import org.bonitasoft.studio.model.form.Duplicable;
import org.bonitasoft.studio.model.form.FormFactory;
import org.bonitasoft.studio.model.form.Widget;
import org.bonitasoft.studio.model.process.DataType;
import org.bonitasoft.studio.model.process.Document;
import org.bonitasoft.studio.model.process.JavaObjectData;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.SearchIndex;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Romain Bioteau
 */
public class ExpressionHelperTest {

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test method for {@link org.bonitasoft.studio.common.emf.tools.ExpressionHelper#createDependencyFromEObject(org.eclipse.emf.ecore.EObject)}.
     */
    @Test
    public void shouldCreateDependencyFromEObject_CopyDataAndRemoveDefaultValue() throws Exception {
        final JavaObjectData myData = ProcessFactory.eINSTANCE.createJavaObjectData();
        myData.setName("dataName");
        myData.setDatasourceId("datasourceId");
        myData.setClassName("org.bonita.test.MyClass");
        myData.setTransient(true);
        myData.setGenerated(false);
        myData.setMultiple(true);
        myData.setDocumentation("some doc");
        myData.setDefaultValue(ExpressionFactory.eINSTANCE.createExpression());
        final DataType dataType = ProcessFactory.eINSTANCE.createJavaType();
        myData.setDataType(dataType);

        final EObject dependencyFromEObject = ExpressionHelper.createDependencyFromEObject(myData);

        assertThat(dependencyFromEObject).isNotNull().isNotSameAs(myData).isInstanceOf(JavaObjectData.class);
        assertThat(((JavaObjectData) dependencyFromEObject).getName()).isEqualTo(myData.getName());
        assertThat(((JavaObjectData) dependencyFromEObject).getDatasourceId()).isEqualTo(myData.getDatasourceId());
        assertThat(((JavaObjectData) dependencyFromEObject).getDocumentation()).isEqualTo(myData.getDocumentation());
        assertThat(((JavaObjectData) dependencyFromEObject).isGenerated()).isEqualTo(myData.isGenerated());
        assertThat(((JavaObjectData) dependencyFromEObject).isMultiple()).isEqualTo(myData.isMultiple());
        assertThat(((JavaObjectData) dependencyFromEObject).isTransient()).isEqualTo(myData.isTransient());
        assertThat(((JavaObjectData) dependencyFromEObject).getDataType()).isEqualTo(myData.getDataType());
        assertThat(((JavaObjectData) dependencyFromEObject).getDefaultValue()).isNull();
    }

    @Test
    public void shouldCreateDependencyFromEObject_CopyWidgetWithName() throws Exception {
        final Widget widget = FormFactory.eINSTANCE.createDateFormField();
        widget.setName("myWidget");
        widget.setDisplayLabel(ExpressionFactory.eINSTANCE.createExpression());
        widget.setInputExpression(ExpressionFactory.eINSTANCE.createExpression());

        final EObject dependencyFromEObject = ExpressionHelper.createDependencyFromEObject(widget);
        assertThat(dependencyFromEObject).isNotNull().isNotSameAs(widget).isInstanceOf(DateFormField.class);
        assertThat(((Widget) dependencyFromEObject).getName()).isEqualTo(widget.getName());
        assertThat(((Widget) dependencyFromEObject).getDisplayLabel()).isNull();
        assertThat(((Widget) dependencyFromEObject).getInputExpression()).isNull();
    }

    @Test
    public void shouldCreateDependencyFromEObject_CopyDocumentWithMultiplicty() throws Exception {
        final Document document = ProcessFactory.eINSTANCE.createDocument();
        document.setName("document");
        document.setMultiple(true);

        final EObject dependencyFromEObject = ExpressionHelper.createDependencyFromEObject(document);
        assertThat(dependencyFromEObject).isNotNull().isNotSameAs(document).isInstanceOf(Document.class);
        assertThat(((Document) dependencyFromEObject).getName()).isEqualTo(document.getName());
        assertThat(((Document) dependencyFromEObject).isMultiple()).isTrue();
    }

    @Test
    public void shouldCreateDependencyFromEObject_CopySearchIndexWithoutName() throws Exception {
        final SearchIndex searchIndex = ProcessFactory.eINSTANCE.createSearchIndex();

        final EObject dependencyFromEObject = ExpressionHelper.createDependencyFromEObject(searchIndex);
        assertThat(dependencyFromEObject).isNotNull().isNotSameAs(searchIndex).isInstanceOf(SearchIndex.class);
    }

    @Test
    public void shouldCreateDependencyFromEObject_CopySearchIndexWithName() throws Exception {
        final SearchIndex searchIndex = ProcessFactory.eINSTANCE.createSearchIndex();
        searchIndex.setName(ExpressionHelper.createConstantExpression("searchIndex", String.class.getName()));

        final EObject dependencyFromEObject = ExpressionHelper.createDependencyFromEObject(searchIndex);
        assertThat(dependencyFromEObject).isNotNull().isNotSameAs(searchIndex).isInstanceOf(SearchIndex.class);
        assertThat(((SearchIndex) dependencyFromEObject).getName().getName()).isEqualTo(searchIndex.getName().getName());
    }

    @Test
    public void shouldCreateDependencyFromEObject_CopyWidgetReturnTypeModifier() throws Exception {
        final Widget widget = FormFactory.eINSTANCE.createTextFormField();
        widget.setReturnTypeModifier(Integer.class.getName());

        final EObject dependencyFromEObject = ExpressionHelper.createDependencyFromEObject(widget);
        assertThat(((Widget) dependencyFromEObject).getReturnTypeModifier()).isEqualTo(Integer.class.getName());
    }

    @Test
    public void shouldCreateDependencyFromEObject_CopyWidgetDuplicateValue() throws Exception {
        final Widget widget = FormFactory.eINSTANCE.createTextFormField();
        ((Duplicable) widget).setDuplicate(true);

        final EObject dependencyFromEObject = ExpressionHelper.createDependencyFromEObject(widget);
        assertThat(((Duplicable) dependencyFromEObject).isDuplicate()).isTrue();
    }

    @Test
    public void shouldClearExpression_SetEmptyExpression() throws Exception {
        final Expression expression = ExpressionFactory.eINSTANCE.createExpression();
        expression.setName("Toto");
        expression.setContent("Titi2014");
        expression.setType(ExpressionConstants.SCRIPT_TYPE);
        expression.setReturnType(DocumentValue.class.getName());
        expression.getReferencedElements().add(ProcessFactory.eINSTANCE.createData());

        ExpressionHelper.clearExpression(expression);
        assertThat(expression.getName()).isEmpty();
        assertThat(expression.getContent()).isEmpty();
        assertThat(expression.getType()).isEqualTo(ExpressionConstants.CONSTANT_TYPE);
        assertThat(expression.getReferencedElements()).isEmpty();
        assertThat(expression.getReturnType()).isEqualTo(String.class.getName());
    }

    @Test
    public void shouldClearExpressionWithEditingDomain_SetEmptyExpression() throws Exception {
        final Expression expression = ExpressionFactory.eINSTANCE.createExpression();
        expression.setName("Toto");
        expression.setContent("Titi2014");
        expression.setType(ExpressionConstants.SCRIPT_TYPE);
        expression.setReturnType(DocumentValue.class.getName());
        expression.getReferencedElements().add(ProcessFactory.eINSTANCE.createData());

        ExpressionHelper.clearExpression(expression, any(String.class), any(EditingDomain.class));
        assertThat(expression.getName()).isEmpty();
        assertThat(expression.getContent()).isEmpty();
        assertThat(expression.getType()).isEqualTo(ExpressionConstants.CONSTANT_TYPE);
        assertThat(expression.getReferencedElements()).isEmpty();
        assertThat(expression.getReturnType()).isEqualTo(String.class.getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldClearExpression_ThrowIllegalArgumentException() throws Exception {
        ExpressionHelper.clearExpression(null);
    }

    @Test
    public void shouldCreateEmptyListGroovyScriptExpression_ReturnAValidEmptyListExpression() throws Exception {
        final Expression expression = ExpressionHelper.createEmptyListGroovyScriptExpression();
        assertThat(expression).hasContent("[]").
        hasInterpreter(ExpressionConstants.GROOVY).
        hasType(ExpressionConstants.SCRIPT_TYPE).
        hasName(Messages.emptyListExpressionName).
        hasReturnType(Collection.class.getName());
    }
}
