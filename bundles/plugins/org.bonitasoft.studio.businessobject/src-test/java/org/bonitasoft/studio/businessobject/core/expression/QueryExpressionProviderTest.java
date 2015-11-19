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
package org.bonitasoft.studio.businessobject.core.expression;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import java.util.List;

import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.engine.bdm.model.BusinessObjectModel;
import org.bonitasoft.engine.bdm.model.Query;
import org.bonitasoft.engine.bdm.model.field.FieldType;
import org.bonitasoft.engine.bdm.model.field.SimpleField;
import org.bonitasoft.studio.businessobject.core.expression.model.BusinessObjectExpressionQuery;
import org.bonitasoft.studio.businessobject.core.expression.model.QueryExpressionModel;
import org.bonitasoft.studio.businessobject.ui.expression.QueryExpressionEditor;
import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.assertions.ExpressionAssert;
import org.junit.Before;
import org.junit.Test;

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

    @Test
    public void should_buildQueryExpressionModel_return_QueryExpressionModel_from_bom() throws Exception {
        final QueryExpressionModel model = queryExpressionProvider.buildQueryExpressionModel();
        assertThat(model).isNotNull();
        assertThat(model.getBusinessObjects()).hasSize(1);
        final BusinessObjectExpressionQuery businessObjectExpressionQuery = model.getBusinessObjects().get(0);
        assertThat(businessObjectExpressionQuery.getQualifiedName()).isEqualTo("org.bonita.Employee");
        String countExpressionName = "Employee.countForFind";
		assertThat(businessObjectExpressionQuery.getQueryExpressions()).extracting("name").containsOnly("Employee.find", countExpressionName, "Employee.findByEmployeeId",
                "Employee.findByPersistenceId");
        for (final Expression exp : businessObjectExpressionQuery.getQueryExpressions()) {
            ExpressionAssert.assertThat(exp).hasType(queryExpressionProvider.getExpressionType());
            assertThat(exp.getName()).isNotEmpty();
            assertThat(exp.getContent()).isNotEmpty();
            assertThat(exp.getType()).isNotEmpty();
            if(exp.getName().equals(countExpressionName)) {
            	assertThat(exp.getReferencedElements()).isEmpty();
            } else {
            	assertThat(exp.getReferencedElements()).isNotEmpty();
            }
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
