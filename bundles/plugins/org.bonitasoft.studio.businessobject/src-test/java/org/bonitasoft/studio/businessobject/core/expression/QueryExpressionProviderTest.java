/*******************************************************************************
 * Copyright (C) 2009, 2014 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.businessobject.core.expression;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import java.util.List;

import org.bonitasoft.studio.businessobject.core.expression.model.BusinessObjectExpressionQuery;
import org.bonitasoft.studio.businessobject.core.expression.model.QueryExpressionModel;
import org.bonitasoft.studio.businessobject.ui.expression.QueryExpressionEditor;
import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.assertions.ExpressionAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.engine.bdm.model.BusinessObjectModel;
import org.bonitasoft.engine.bdm.model.Query;
import org.bonitasoft.engine.bdm.model.field.FieldType;
import org.bonitasoft.engine.bdm.model.field.SimpleField;

/**
 * @author Romain Bioteau
 */
public class QueryExpressionProviderTest {

    private QueryExpressionProvider queryExpressionProvider;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        queryExpressionProvider = spy(new QueryExpressionProvider());
        final BusinessObjectModel bom = new BusinessObjectModel();
        final BusinessObject businessObject = new BusinessObject();
        businessObject.setQualifiedName("org.bonita.Employee");
        final SimpleField id = new SimpleField();
        id.setName("employeeId");
        id.setType(FieldType.STRING);
        businessObject.addField(id);
        bom.addBusinessObject(businessObject);
        businessObject.addUniqueConstraint("UC_EMPLOYEE_ID", "employeeId");
        final Query query = businessObject.addQuery("findByEmployeeId", "SELECT e FROM Employee e WHERE e.employeeId = :employeeId", List.class.getName());
        query.addQueryParameter("employeeId", String.class.getName());
        doReturn(bom).when(queryExpressionProvider).getBusinessObjectModel();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void should_buildQueryExpressionModel_return_QueryExpressionModel_from_bom() throws Exception {
        final QueryExpressionModel model = queryExpressionProvider.buildQueryExpressionModel();
        assertThat(model).isNotNull();
        assertThat(model.getBusinessObjects()).hasSize(1);
        final BusinessObjectExpressionQuery businessObjectExpressionQuery = model.getBusinessObjects().get(0);
        assertThat(businessObjectExpressionQuery.getQualifiedName()).isEqualTo("org.bonita.Employee");
        assertThat(businessObjectExpressionQuery.getQueryExpressions()).hasSize(3);
        for (final Expression exp : businessObjectExpressionQuery.getQueryExpressions()) {
            ExpressionAssert.assertThat(exp).hasType(queryExpressionProvider.getExpressionType());
            assertThat(exp.getName()).isNotEmpty();
            assertThat(exp.getContent()).isNotEmpty();
            assertThat(exp.getType()).isNotEmpty();
            assertThat(exp.getReferencedElements()).isNotEmpty();
        }
    }

    @Test
    public void should_be_relevant_for_any_context() throws Exception {
        assertThat(queryExpressionProvider.isRelevantFor(null)).isTrue();
    }

    @Test
    public void should_getExpressionEditor_return_a_QueryExpressionEditor() throws Exception {
        assertThat(queryExpressionProvider.getExpressionEditor(null, null)).isInstanceOf(QueryExpressionEditor.class);
    }

    @Test
    public void should_have_valid_expression_type() throws Exception {
        assertThat(queryExpressionProvider.getExpressionType()).isEqualTo(ExpressionConstants.QUERY_TYPE);
    }
}
