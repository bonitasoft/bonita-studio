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
package org.bonitasoft.studio.data.provider;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.model.expression.builders.ExpressionBuilder.anExpression;
import static org.bonitasoft.studio.model.process.builders.MainProcessBuilder.aMainProcess;
import static org.bonitasoft.studio.model.process.builders.StringDataTypeBuilder.aStringDataType;
import static org.bonitasoft.studio.model.process.builders.TaskBuilder.aTask;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.StringType;
import org.junit.Test;

public class DataExpressionProviderTest {

    @Test
    public void should_create_a_data_with_name_from_an_iterator_expression() throws Exception {
        final Data data = DataExpressionProvider.dataFromIteratorExpression(aTask().build(), anExpression()
                .withExpressionType(ExpressionConstants.MULTIINSTANCE_ITERATOR_TYPE).withName("iterator").build(),
                aMainProcess().build());

        assertThat(data.getName()).isEqualTo("iterator");
    }

    @Test
    public void should_create_a_data_with_datatype_from_an_iterator_expression() throws Exception {
        final StringType stringDataType = aStringDataType().build();

        final Data data = DataExpressionProvider.dataFromIteratorExpression(aTask().build(),
                anExpression()
                        .withExpressionType(ExpressionConstants.MULTIINSTANCE_ITERATOR_TYPE).withReturnType(String.class.getName()).build(),
                aMainProcess().havingDatatypes(stringDataType).build());

        assertThat(data.getDataType()).isEqualTo(stringDataType);
    }
}
