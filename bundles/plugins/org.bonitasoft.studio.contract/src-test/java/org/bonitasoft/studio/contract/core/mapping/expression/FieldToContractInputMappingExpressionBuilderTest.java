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
package org.bonitasoft.studio.contract.core.mapping.expression;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.contract.core.mapping.operation.FieldToContractInputMappingBuilder.aRelationMapping;
import static org.bonitasoft.studio.contract.core.mapping.operation.FieldToContractInputMappingBuilder.aSimpleMapping;
import static org.bonitasoft.studio.model.businessObject.BusinessObjectBuilder.aBO;
import static org.bonitasoft.studio.model.businessObject.FieldBuilder.aCompositionField;
import static org.bonitasoft.studio.model.businessObject.FieldBuilder.aSimpleField;
import static org.bonitasoft.studio.model.process.builders.BusinessObjectDataBuilder.aBusinessData;
import static org.bonitasoft.studio.model.process.builders.ContractInputBuilder.aContractInput;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import org.bonitasoft.engine.bdm.model.field.FieldType;
import org.bonitasoft.engine.bdm.model.field.RelationField;
import org.bonitasoft.engine.bdm.model.field.RelationField.Type;
import org.bonitasoft.engine.bdm.model.field.SimpleField;
import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.contract.core.mapping.FieldToContractInputMapping;
import org.bonitasoft.studio.contract.core.mapping.RelationFieldToContractInputMapping;
import org.bonitasoft.studio.contract.core.mapping.SimpleFieldToContractInputMapping;
import org.bonitasoft.studio.contract.core.mapping.operation.BusinessObjectInstantiationException;
import org.bonitasoft.studio.expression.editor.ExpressionProviderService;
import org.bonitasoft.studio.model.businessObject.BusinessObjectBuilder;
import org.bonitasoft.studio.model.businessObject.FieldBuilder.RelationFieldBuilder;
import org.bonitasoft.studio.model.businessObject.FieldBuilder.SimpleFieldBuilder;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.assertions.ExpressionAssert;
import org.bonitasoft.studio.model.process.BusinessObjectData;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.ContractInputType;
import org.bonitasoft.studio.model.process.assertions.ContractInputAssert;
import org.codehaus.jdt.groovy.model.GroovyCompilationUnit;
import org.eclipse.jdt.core.JavaModelException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class FieldToContractInputMappingExpressionBuilderTest {

    @Mock
    private RepositoryAccessor repositoryAccessor;
    @Mock
    private ExpressionProviderService expressionEditorService;
    @Mock
    private GroovyCompilationUnit groovyCompilationUnit;

    @Test
    public void should_create_an_operation_for_a_given_complex_contact_input_and_a_composite_reference_business_data_field()
            throws Exception {
        final FieldToContractInputMappingExpressionBuilder expressionBuilder = newExpressionBuilder();

        final RelationField address = aCompositionField("address", aBO("Address").build());
        final FieldToContractInputMapping mapping = aRelationMapping(address).build();
        mapping.toContractInput(aContractInput().withName("employee").withType(ContractInputType.COMPLEX).build());
        final BusinessObjectData businessObjectData = aBusinessData().withName("myEmployee").build();
        final Expression expression = expressionBuilder.toExpression(businessObjectData,
                mapping, false);

        ExpressionAssert.assertThat(expression)
                .hasName("employee.address")
                .hasContent("def addressVar = myEmployee.address ?: new Address()" + System.lineSeparator()
                        + "return addressVar")
                .hasReturnType("Address")
                .hasType(ExpressionConstants.SCRIPT_TYPE);
        assertThat(expression.getReferencedElements()).hasSize(2);
    }

    private FieldToContractInputMappingExpressionBuilder newExpressionBuilder() throws JavaModelException {
        final FieldToContractInputMappingExpressionBuilder expressionBuilder = spy(
                new FieldToContractInputMappingExpressionBuilder(repositoryAccessor,
                        expressionEditorService));
        doReturn(groovyCompilationUnit).when(expressionBuilder).groovyCompilationUnit(any(Expression.class));
        return expressionBuilder;
    }

    @Test
    public void should_create_an_operation_for_a_given_simple_contact_input_and_a_primitive_business_data_field()
            throws Exception {
        final FieldToContractInputMappingExpressionBuilder expressionBuilder = newExpressionBuilder();

        final SimpleField lastNameField = aSimpleField().withName("lastName").ofType(FieldType.STRING).build();
        final FieldToContractInputMapping mapping = aSimpleMapping(lastNameField).build();
        final Expression expression = expressionBuilder.toExpression(aBusinessData().withName("myEmployee").build(),
                mapping, false);

        ExpressionAssert.assertThat(expression)
                .hasName("lastName")
                .hasContent("lastName")
                .hasType(ExpressionConstants.SCRIPT_TYPE);
        assertThat(expression.getReferencedElements()).hasSize(1);
    }

    @Test
    public void should_create_an_operation_for_a_given_complex_contact_input_and_a_primitive_business_data_field()
            throws Exception {
        final FieldToContractInputMappingExpressionBuilder expressionBuilder = newExpressionBuilder();

        final SimpleField lastNameField = aSimpleField().withName("lastName").ofType(FieldType.STRING).build();
        final FieldToContractInputMapping mapping = aSimpleMapping(lastNameField).build();
        mapping.toContractInput(aContractInput().withType(ContractInputType.COMPLEX)
                .withName("employee").build());

        final BusinessObjectData data = aBusinessData().withName("myEmployee").build();
        final Expression expression = expressionBuilder.toExpression(data,
                mapping, false);

        ExpressionAssert.assertThat(expression)
                .hasName("employee.lastName")
                .hasContent("employee.lastName")
                .hasReturnType(String.class.getName())
                .hasType(ExpressionConstants.SCRIPT_TYPE);
        assertThat(expression.getReferencedElements()).hasSize(1);
        ContractInputAssert.assertThat((ContractInput) expression.getReferencedElements().get(0)).hasName("employee");
    }

    @Test
    public void should_not_add_businessVariable_dependency_forInitializationScript()
            throws JavaModelException, BusinessObjectInstantiationException {
        final FieldToContractInputMappingExpressionBuilder expressionBuilder = newExpressionBuilder();

        final RelationField address = aCompositionField("address", aBO("Address").build());
        final FieldToContractInputMapping mapping = aRelationMapping(address).build();
        mapping.toContractInput(aContractInput().withName("employee").withType(ContractInputType.COMPLEX).build());
        final BusinessObjectData businessObjectData = aBusinessData().withName("myEmployee").build();
        final Expression expression = expressionBuilder.toExpression(businessObjectData,
                mapping, true);

        ExpressionAssert.assertThat(expression)
                .hasName("initMyEmployee()")
                .hasContent("def addressVar = myEmployee.address ?: new Address()" + System.lineSeparator()
                        + "return addressVar")
                .hasReturnType("Address")
                .hasType(ExpressionConstants.SCRIPT_TYPE);
        assertThat(expression.getReferencedElements()).hasSize(1);
    }

    @Test
    public void should_delete_compilation_unit_after_dependencies_resolution() throws Exception {
        final FieldToContractInputMappingExpressionBuilder expressionBuilder = newExpressionBuilder();

        final SimpleField lastNameField = aSimpleField().withName("lastName").ofType(FieldType.STRING).build();
        final FieldToContractInputMapping mapping = aSimpleMapping(lastNameField).build();
        expressionBuilder.toExpression(aBusinessData().withName("myEmployee").build(),
                mapping, false);

        verify(groovyCompilationUnit).delete(true, Repository.NULL_PROGRESS_MONITOR);
    }

    @Test
    public void should_create_expression_for_multiple_business_data_with_a_multiple_complex_field_in_aggregation()
            throws Exception {
        RelationFieldToContractInputMapping bookMapping = createBookMapping(true, false);
        BusinessObjectData businessData = aBusinessData().withName("myBooks").multiple().build();
        FieldToContractInputMappingExpressionBuilder expressionBuilder = newExpressionBuilder();

        // On a task
        Expression expression = expressionBuilder.toExpression(businessData, bookMapping, false);
        assertThat(expression.getContent()).isEqualToIgnoringWhitespace("def bookList = []\n"
                + "//For each item collected in multiple input\n"
                + "Book.each{\n"
                + "   //Add Book instance\n"
                + "    bookList.add({ currentBookInput ->\n"
                + "        def bookVar = myBooks.find { it.persistenceId.toString() == currentBookInput.persistenceId_string} ?: new com.company.Book()\n"
                + "        bookVar.page = {\n"
                + "            def pageList = []\n"
                + "            //For each item collected in multiple input\n"
                + "            currentBookInput.page.each{\n"
                + "                //Add Page instance\n"
                + "                pageList.add({ currentPageInput ->\n"
                + "                    def pageVar = pageDAO.findByPersistenceId(currentPageInput.persistenceId_string?.toLong())\n"
                + "                    if(!pageVar) {\n"
                + "                        throw new IllegalArgumentException(\"The aggregated reference of type `Page`  with the persistence id \" + currentPageInput.persistenceId_string?.toLong() + \" has not been found.\")\n"
                + "                    }\n"
                + "                    pageVar.pageContent = currentPageInput.pageContent\n"
                + "                    return pageVar\n"
                + "                }(it))\n"
                + "            }\n"
                + "            return pageList}()\n"
                + "        return bookVar\n"
                + "    }(it))\n"
                + "}\n"
                + "return bookList");

        //On a pool
        expression = expressionBuilder.toExpression(businessData, bookMapping, true);
        assertThat(expression.getContent()).isEqualToIgnoringWhitespace("def bookList = []\n"
                + "//For each item collected in multiple input\n"
                + "Book.each{\n"
                + "   //Add a new composed Book instance\n"
                + "    bookList.add({ currentBookInput ->\n"
                + "        def bookVar = new com.company.Book()\n"
                + "        bookVar.page = {\n"
                + "            def pageList = []\n"
                + "            //For each item collected in multiple input\n"
                + "            currentBookInput.page.each{\n"
                + "                //Add Page instance\n"
                + "                pageList.add({ currentPageInput ->\n"
                + "                    def pageVar = pageDAO.findByPersistenceId(currentPageInput.persistenceId_string?.toLong())\n"
                + "                    if(!pageVar) {\n"
                + "                        throw new IllegalArgumentException(\"The aggregated reference of type `Page`  with the persistence id \" + currentPageInput.persistenceId_string?.toLong() + \" has not been found.\")\n"
                + "                    }\n"
                + "                    pageVar.pageContent = currentPageInput.pageContent\n"
                + "                    return pageVar\n"
                + "                }(it))\n"
                + "            }\n"
                + "            return pageList}()\n"
                + "        return bookVar\n"
                + "    }(it))\n"
                + "}\n"
                + "return bookList");
    }

    @Test
    public void should_create_expression_for_multiple_business_data_with_a_simple_complex_field_in_aggregation()
            throws Exception {
        RelationFieldToContractInputMapping bookMapping = createBookMapping(false, false);
        BusinessObjectData businessData = aBusinessData().withName("myBooks").multiple().build();
        FieldToContractInputMappingExpressionBuilder expressionBuilder = newExpressionBuilder();

        // On a task
        Expression expression = expressionBuilder.toExpression(businessData, bookMapping, false);
        assertThat(expression.getContent()).isEqualToIgnoringWhitespace("def bookList = []\n"
                + "//For each item collected in multiple input\n"
                + "Book.each{\n"
                + "   //Add Book instance\n"
                + "    bookList.add({ currentBookInput ->\n"
                + "        def bookVar = myBooks.find { it.persistenceId.toString() == currentBookInput.persistenceId_string} ?: new com.company.Book()\n"
                + "        bookVar.page = {\n"
                + "            //Retrieve aggregated Page using its DAO and persistenceId\n"
                + "            def pageVar = pageDAO.findByPersistenceId(currentBookInput.page.persistenceId_string?.toLong())\n"
                + "            if(!pageVar) {\n"
                + "                throw new IllegalArgumentException(\"The aggregated reference of type `Page`  with the persistence id \" + currentBookInput.page.persistenceId_string?.toLong() + \" has not been found.\")\n"
                + "            }\n"
                + "            pageVar.pageContent = currentBookInput.page.pageContent\n"
                + "            return pageVar}()\n"
                + "        return bookVar\n"
                + "    }(it))\n"
                + "}\n"
                + "return bookList");

        //On a pool
        expression = expressionBuilder.toExpression(businessData, bookMapping, true);
        assertThat(expression.getContent()).isEqualToIgnoringWhitespace("def bookList = []\n"
                + "//For each item collected in multiple input\n"
                + "Book.each{\n"
                + "   //Add  a new composed Book instance\n"
                + "    bookList.add({ currentBookInput ->\n"
                + "        def bookVar = new com.company.Book()\n"
                + "        bookVar.page = {\n"
                + "            //Retrieve aggregated Page using its DAO and persistenceId\n"
                + "            def pageVar = pageDAO.findByPersistenceId(currentBookInput.page.persistenceId_string?.toLong())\n"
                + "            if(!pageVar) {\n"
                + "                throw new IllegalArgumentException(\"The aggregated reference of type `Page`  with the persistence id \" + currentBookInput.page.persistenceId_string?.toLong() + \" has not been found.\")\n"
                + "            }\n"
                + "            pageVar.pageContent = currentBookInput.page.pageContent\n"
                + "            return pageVar}()\n"
                + "        return bookVar\n"
                + "    }(it))\n"
                + "}\n"
                + "return bookList");
    }

    @Test
    public void should_create_expression_for_multiple_business_data_with_a_simple_complex_field_in_composition()
            throws Exception {
        RelationFieldToContractInputMapping bookMapping = createBookMapping(false, true);
        BusinessObjectData businessData = aBusinessData().withName("myBooks").multiple().build();
        FieldToContractInputMappingExpressionBuilder expressionBuilder = newExpressionBuilder();

        // On a task
        Expression expression = expressionBuilder.toExpression(businessData, bookMapping, false);
        assertThat(expression.getContent()).isEqualToIgnoringWhitespace("def bookList = []\n"
                + "//For each item collected in multiple input\n"
                + "Book.each{\n"
                + "   //Add Book instance\n"
                + "    bookList.add({ currentBookInput ->\n"
                + "        def bookVar = myBooks.find { it.persistenceId.toString() == currentBookInput.persistenceId_string} ?: new com.company.Book()\n"
                + "        bookVar.page = {\n"
                + "            def pageVar = bookVar.page ?: new com.company.Page()\n"
                + "            pageVar.pageContent = currentBookInput.page.pageContent\n"
                + "            return pageVar}()\n"
                + "        return bookVar\n"
                + "    }(it))\n"
                + "}\n"
                + "return bookList");

        //On a pool
        expression = expressionBuilder.toExpression(businessData, bookMapping, true);
        assertThat(expression.getContent()).isEqualToIgnoringWhitespace("def bookList = []\n"
                + "//For each item collected in multiple input\n"
                + "Book.each{\n"
                + "   //Add  a new composed Book instance\n"
                + "    bookList.add({ currentBookInput ->\n"
                + "        def bookVar = new com.company.Book()\n"
                + "        bookVar.page = {\n"
                + "            def pageVar = new com.company.Page()\n"
                + "            pageVar.pageContent = currentBookInput.page.pageContent\n"
                + "            return pageVar}()\n"
                + "        return bookVar\n"
                + "    }(it))\n"
                + "}\n"
                + "return bookList");
    }

    @Test
    public void should_create_expression_for_multiple_business_data_with_a_multiple_complex_field_in_composition()
            throws Exception {
        RelationFieldToContractInputMapping bookMapping = createBookMapping(true, true);
        BusinessObjectData businessData = aBusinessData().withName("myBooks").multiple().build();
        FieldToContractInputMappingExpressionBuilder expressionBuilder = newExpressionBuilder();

        // On a task
        Expression expression = expressionBuilder.toExpression(businessData, bookMapping, false);
        assertThat(expression.getContent()).isEqualToIgnoringWhitespace("def bookList = []\n"
                + "//For each item collected in multiple input\n"
                + "Book.each{\n"
                + "   //Add Book instance\n"
                + "    bookList.add({ currentBookInput ->\n"
                + "        def bookVar = myBooks.find { it.persistenceId.toString() == currentBookInput.persistenceId_string} ?: new com.company.Book()\n"
                + "        bookVar.page = {\n"
                + "            def pageList = []\n"
                + "            //For each item collected in multiple input\n"
                + "            currentBookInput.page.each{\n"
                + "                //Add a new composed Page instance\n"
                + "                pageList.add({ currentPageInput ->\n"
                + "                    def pageVar = bookVar.page?.find { it.persistenceId.toString() == currentPageInput.persistenceId_string } ?: new com.company.Page()\n"
                + "                    pageVar.pageContent = currentPageInput.pageContent\n"
                + "                    return pageVar\n"
                + "                }(it))\n"
                + "            }\n"
                + "            return pageList}()\n"
                + "        return bookVar\n"
                + "    }(it))\n"
                + "}\n"
                + "return bookList");

        //On a pool
        expression = expressionBuilder.toExpression(businessData, bookMapping, true);
        assertThat(expression.getContent()).isEqualToIgnoringWhitespace("def bookList = []\n"
                + "//For each item collected in multiple input\n"
                + "Book.each{\n"
                + "   //Add  a new composed Book instance\n"
                + "    bookList.add({ currentBookInput ->\n"
                + "        def bookVar = new com.company.Book()\n"
                + "        bookVar.page = {\n"
                + "            def pageList = []\n"
                + "            //For each item collected in multiple input\n"
                + "            currentBookInput.page.each{\n"
                + "                //Add a new composed Page instance\n"
                + "                pageList.add({ currentPageInput ->\n"
                + "                    def pageVar = new com.company.Page()\n"
                + "                    pageVar.pageContent = currentPageInput.pageContent\n"
                + "                    return pageVar\n"
                + "                }(it))\n"
                + "            }\n"
                + "            return pageList}()\n"
                + "        return bookVar\n"
                + "    }(it))\n"
                + "}\n"
                + "return bookList");
    }

    private RelationFieldToContractInputMapping createBookMapping(boolean pageMultiple, boolean pageInComposition) {
        RelationField bookField = RelationFieldBuilder.aRelationField()
                .composition()
                .withName("Book")
                .multiple()
                .referencing(new BusinessObjectBuilder("com.company.Book").build())
                .build();
        RelationField pageField = RelationFieldBuilder.aRelationField()
                .withName("page")
                .referencing(new BusinessObjectBuilder("com.company.Page").build())
                .build();
        pageField.setType(pageInComposition ? Type.COMPOSITION : Type.AGGREGATION);
        if (pageMultiple) {
            pageField.setCollection(true);
        }

        SimpleField pageContentField = SimpleFieldBuilder.aStringField("pageContent").build();
        SimpleField persistenceIdBookField = SimpleFieldBuilder.aStringField("persistenceId_string").build();
        SimpleField persistenceIdPageField = SimpleFieldBuilder.aStringField("persistenceId_string").build();

        RelationFieldToContractInputMapping bookMapping = new RelationFieldToContractInputMapping(bookField);
        RelationFieldToContractInputMapping pageMapping = new RelationFieldToContractInputMapping(pageField);
        SimpleFieldToContractInputMapping pageContentMapping = new SimpleFieldToContractInputMapping(pageContentField);
        SimpleFieldToContractInputMapping persistenceIdBookMapping = new SimpleFieldToContractInputMapping(
                persistenceIdBookField);
        SimpleFieldToContractInputMapping persistenceIdPageMapping = new SimpleFieldToContractInputMapping(
                persistenceIdPageField);

        bookMapping.addChild(pageMapping);
        bookMapping.addChild(persistenceIdBookMapping);
        pageMapping.addChild(pageContentMapping);
        pageMapping.addChild(persistenceIdPageMapping);

        return bookMapping;
    }
}
