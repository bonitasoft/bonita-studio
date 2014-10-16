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
package org.bonitasoft.studio.contract.core.constraint;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bonitasoft.studio.contract.core.exception.ConstraintEvaluationException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * @author Romain Bioteau
 *
 */
public class ContractConstraintEvaluatorTest {

    private ContractConstraintEvaluator constraintEvaluation;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        constraintEvaluation = new ContractConstraintEvaluator();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void should_run_returns_true() throws Exception {
        final Map<String, Object> context = new HashMap<String, Object>();
        context.put("age", 20);
        assertThat((Boolean) constraintEvaluation.run("age > 18", context)).isTrue();
    }

    @Test
    public void should_run_returns_false() throws Exception {
        final Map<String, Object> context = new HashMap<String, Object>();
        context.put("age", 15);
        assertThat((Boolean) constraintEvaluation.run("age > 18", context)).isFalse();
    }

    @Test(expected = ConstraintEvaluationException.class)
    public void should_run_throw_a_ConstraintEvaluationException_for_non_boolean_value() throws Exception {
        final Map<String, Object> context = new HashMap<String, Object>();
        context.put("age", 15);
        constraintEvaluation.run("age", context);
    }

    @Test(expected = ConstraintEvaluationException.class)
    public void should_run_throw_a_ConstraintEvaluationException_for_null_value() throws Exception {
        final Map<String, Object> context = new HashMap<String, Object>();
        context.put("age", 15);
        constraintEvaluation.run("null", null);
    }

    @Test
    public void should_run_supports_expression_with_map_accessors() throws Exception {
        final Map<String, Object> context = new HashMap<String, Object>();
        final Map<String, Object> employeeMap = new HashMap<String, Object>();
        final List<Map<String, Object>> skills = new ArrayList<Map<String, Object>>();
        final Map<String, Object> s1 = new HashMap<String, Object>();
        s1.put("name", "Java");
        s1.put("level", 5);
        final Map<String, Object> s2 = new HashMap<String, Object>();
        s2.put("name", "English");
        s2.put("level", 4);
        skills.add(s1);
        skills.add(s2);
        employeeMap.put("firstName", "Romain");
        employeeMap.put("lastName", "Bioteau");
        employeeMap.put("skills", skills);
        context.put("employee", employeeMap);
        assertThat((Boolean) constraintEvaluation.run("employee.firstName == \"Romain\"", context)).isTrue();
        assertThat((Boolean) constraintEvaluation.run("employee.skills.size() == 2", context)).isTrue();
        assertThat((Boolean) constraintEvaluation.run("employee.skills[0].name == \"Java\"", context)).isTrue();
        assertThat((Boolean) constraintEvaluation.run("employee.skills[0].level < 4", context)).isFalse();
    }
}
