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
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.engine.export.expression.converter.comparison;

import static org.assertj.core.api.Assertions.assertThat;

import org.bonitasoft.studio.condition.conditionModel.ConditionModelFactory;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * @author Romain Bioteau
 *
 */
public class OperatorSwitchTest {

    private OperatorSwitch operatorSwitch;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        operatorSwitch = new OperatorSwitch();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void should_doSwitch_return_operator_symbols() throws Exception {
        assertThat(operatorSwitch.doSwitch(ConditionModelFactory.eINSTANCE.createOperation_Equals())).isEqualTo("==");
        assertThat(operatorSwitch.doSwitch(ConditionModelFactory.eINSTANCE.createOperation_Greater())).isEqualTo(">");
        assertThat(operatorSwitch.doSwitch(ConditionModelFactory.eINSTANCE.createOperation_Greater_Equals())).isEqualTo(">=");
        assertThat(operatorSwitch.doSwitch(ConditionModelFactory.eINSTANCE.createOperation_Less())).isEqualTo("<");
        assertThat(operatorSwitch.doSwitch(ConditionModelFactory.eINSTANCE.createOperation_Less_Equals())).isEqualTo("<=");
        assertThat(operatorSwitch.doSwitch(ConditionModelFactory.eINSTANCE.createOperation_Not_Equals())).isEqualTo("!=");
    }

    @Test
    public void should_doSwitch_return_null_if_not_an_operator() throws Exception {
        assertThat(operatorSwitch.doSwitch(ProcessFactory.eINSTANCE.createPool())).isNull();
    }

}
