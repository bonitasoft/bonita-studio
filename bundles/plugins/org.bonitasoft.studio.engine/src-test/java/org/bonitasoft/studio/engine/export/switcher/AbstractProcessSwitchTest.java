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
package org.bonitasoft.studio.engine.export.switcher;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.util.Collections;

import org.bonitasoft.engine.bpm.process.impl.BusinessDataDefinitionBuilder;
import org.bonitasoft.engine.bpm.process.impl.ProcessDefinitionBuilder;
import org.bonitasoft.engine.expression.Expression;
import org.bonitasoft.engine.expression.ExpressionType;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.builders.BusinessObjectDataBuilder;
import org.bonitasoft.studio.model.process.builders.PoolBuilder;
import org.eclipse.emf.ecore.EObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class AbstractProcessSwitchTest {

    private AbstractProcessSwitch processSwitch;
    @Mock
    ProcessDefinitionBuilder processDefBuilder;
    @Mock
    BusinessDataDefinitionBuilder bDataBuilder;

    @Before
    public void setup() {
        processSwitch = spy(new AbstractProcessSwitch(processDefBuilder, Collections.<EObject> emptySet()));
        doReturn(bDataBuilder).when(processDefBuilder).addBusinessData(anyString(), anyString(), any(Expression.class));
    }

    @Test
    public void testAddContext() {
        final Pool pool = PoolBuilder.aPool()
                .havingData(BusinessObjectDataBuilder.aBusinessData().withName("myBData").withClassname("my.classname"))
                .build();
        processSwitch.caseAbstractProcess(pool);

        final ArgumentCaptor<Expression> argument = ArgumentCaptor.forClass(Expression.class);
        verify(processDefBuilder).addContextEntry(eq("myBData_ref"), argument.capture());
        assertThat(argument.getValue().getName()).isEqualTo("myBData");
        assertThat(argument.getValue().getExpressionType()).isEqualTo(ExpressionType.TYPE_BUSINESS_DATA_REFERENCE.name());
    }

}
