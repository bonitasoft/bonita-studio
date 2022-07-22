/**
 * Copyright (C) 2020 BonitaSoft S.A.
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
package org.bonitasoft.studio.connector.wizard.office.templating;

import static org.bonitasoft.studio.model.expression.builders.ExpressionBuilder.aConstantExpression;
import static org.bonitasoft.studio.model.expression.builders.ExpressionBuilder.aVariableExpression;
import static org.bonitasoft.studio.model.expression.builders.ListExpressionBuilder.aListExpression;
import static org.bonitasoft.studio.model.expression.builders.TableExpressionBuilder.aTableExpression;
import static org.bonitasoft.studio.model.process.builders.BusinessObjectDataBuilder.aBusinessData;
import static org.bonitasoft.studio.model.process.builders.DataBuilder.aData;

import org.bonitasoft.studio.assertions.StatusAssert;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelFileStore;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.eclipse.core.runtime.IStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TableExpressionWithoutLazyLoadedRefsTest {

    @Mock
    private BusinessObjectModelRepositoryStore<BusinessObjectModelFileStore> store;

    @Test
    public void should_fail_if_table_expression_contains_a_business_object_data() throws Exception {
        final TableExpressionWithoutLazyLoadedRefs validator = newValidator();

        final IStatus status = validator
                .validate(aTableExpression().havingRows(aListExpression().havingExpressions(
                        aConstantExpression().withName("key"),
                        aVariableExpression().havingReferencedElements(aBusinessData().withClassname("Employee")))).build());

        StatusAssert.assertThat(status).isNotOK();
    }

    @Test
    public void should_not_fail_if_table_expression_does_not_contains_a_business_object_data() throws Exception {
        final TableExpressionWithoutLazyLoadedRefs validator = newValidator();

        final IStatus status = validator
                .validate(aTableExpression().havingRows(aListExpression().havingExpressions(
                        aConstantExpression().withName("key"),
                        aVariableExpression())).build());

        StatusAssert.assertThat(status).isOK();
    }

    @Test
    public void should_not_fail_if_table_expression_does_not_contains_a_variable_expression() throws Exception {
        final TableExpressionWithoutLazyLoadedRefs validator = newValidator();

        final IStatus status = validator
                .validate(aTableExpression().havingRows(aListExpression().havingExpressions(
                        aConstantExpression().withName("key"),
                        aConstantExpression())).build());

        StatusAssert.assertThat(status).isOK();
    }

    @Test
    public void should_not_fail_if_table_expression_contains_a_process_variable_expression() throws Exception {
        final TableExpressionWithoutLazyLoadedRefs validator = newValidator();

        final IStatus status = validator
                .validate(aTableExpression().havingRows(aListExpression().havingExpressions(
                        aConstantExpression().withName("key"),
                        aVariableExpression().havingReferencedElements(aData()))).build());

        StatusAssert.assertThat(status).isOK();
    }

    private TableExpressionWithoutLazyLoadedRefs newValidator() {
        return new TableExpressionWithoutLazyLoadedRefs(store);
    }

}
