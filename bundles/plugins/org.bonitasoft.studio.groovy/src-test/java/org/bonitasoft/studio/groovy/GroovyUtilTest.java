/**
 * Copyright (C) 2017 Bonitasoft S.A.
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
package org.bonitasoft.studio.groovy;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.bonitasoft.studio.model.expression.builders.OperationBuilder.anOperation;
import static org.bonitasoft.studio.model.process.builders.TaskBuilder.aTask;

import java.util.List;

import org.bonitasoft.engine.expression.ExpressionConstants;
import org.bonitasoft.studio.model.process.MultiInstanceType;
import org.junit.Test;

public class GroovyUtilTest {

    @Test
    public void should_add_taskAssigneeId_for_operation_context() throws Exception {
        final List<ScriptVariable> bonitaVariables = GroovyUtil.getBonitaVariables(anOperation().in(aTask()).build(), null,
                false);

        assertThat(bonitaVariables).extracting("name", "type").contains(
                tuple(ExpressionConstants.TASK_ASSIGNEE_ID.getEngineConstantName(),
                        ExpressionConstants.TASK_ASSIGNEE_ID.getReturnType()));
    }

    @Test
    public void should_add_loopcounter_for_operation_context() throws Exception {
        final List<ScriptVariable> bonitaVariables = GroovyUtil.getBonitaVariables(
                anOperation().in(aTask().withMultiInstanceType(MultiInstanceType.STANDARD)).build(), null,
                false);

        assertThat(bonitaVariables).extracting("name", "type").contains(
                tuple(ExpressionConstants.LOOP_COUNTER.getEngineConstantName(),
                        ExpressionConstants.LOOP_COUNTER.getReturnType()));
    }

}
