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
package org.bonitasoft.studio.groovy.ui.dialog;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import java.io.Serializable;
import java.util.Collections;

import org.bonitasoft.studio.assertions.StatusAssert;
import org.bonitasoft.studio.groovy.ScriptVariable;
import org.bonitasoft.studio.swt.rules.RealmWithDisplay;
import org.codehaus.jdt.groovy.model.GroovyCompilationUnit;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.junit.Rule;
import org.junit.Test;

public class TestGroovyScriptDialogTest {

    @Rule
    public RealmWithDisplay realm = new RealmWithDisplay();

    @Test
    public void should_create_a_long_input_strategy() throws Exception {
        final TestGroovyScriptDialog dialog = newDialog();

        final UpdateValueStrategy strategy = dialog.variableInputStrategy("input", new ScriptVariable("input", Long.class.getName()));

        assertThat(strategy.convert("50")).isEqualTo(50L);
        StatusAssert.assertThat(strategy.validateAfterGet("")).isNotOK();
        StatusAssert.assertThat(strategy.validateAfterGet("Not a long parsable")).isNotOK();
    }

    @Test
    public void should_create_a_int_input_strategy() throws Exception {
        final TestGroovyScriptDialog dialog = newDialog();

        final UpdateValueStrategy strategy = dialog.variableInputStrategy("input", new ScriptVariable("input", Integer.class.getName()));

        assertThat(strategy.convert("5")).isEqualTo(5);
        StatusAssert.assertThat(strategy.validateAfterGet("")).isNotOK();
        StatusAssert.assertThat(strategy.validateAfterGet("Not a integer parsable")).isNotOK();
    }

    @Test
    public void should_create_a_double_input_strategy() throws Exception {
        final TestGroovyScriptDialog dialog = newDialog();

        final UpdateValueStrategy strategy = dialog.variableInputStrategy("input", new ScriptVariable("input", Double.class.getName()));

        assertThat(strategy.convert(String.format("%f", 5.6987d))).isEqualTo(5.6987d);
        StatusAssert.assertThat(strategy.validateAfterGet("")).isNotOK();
        StatusAssert.assertThat(strategy.validateAfterGet("Not a double parsable")).isNotOK();
    }

    @Test
    public void should_create_a_float_input_strategy() throws Exception {
        final TestGroovyScriptDialog dialog = newDialog();

        final UpdateValueStrategy strategy = dialog.variableInputStrategy("input", new ScriptVariable("input", Float.class.getName()));

        assertThat(strategy.convert(String.format("%f", 5.6987f))).isEqualTo(5.6987f);
        StatusAssert.assertThat(strategy.validateAfterGet("")).isNotOK();
        StatusAssert.assertThat(strategy.validateAfterGet("Not a float parsable")).isNotOK();
    }

    private TestGroovyScriptDialog newDialog() {
        return new TestGroovyScriptDialog(realm.getShell(), Collections.<ScriptVariable> emptyList(),
                mock(GroovyCompilationUnit.class), null,
                Collections.<String, Serializable> emptyMap());
    }

}
