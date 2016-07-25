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
package org.bonitasoft.studio.contract.core.mapping.operation;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class DefaultFormatterPreferencesTest {

    @Test
    public void should_provides_default_formatter_values() throws Exception {
        final DefaultFormatterPreferences defaultFormatterPreferences = new DefaultFormatterPreferences();
        assertThat(defaultFormatterPreferences.getBracesEnd()).isEqualTo(0);
        assertThat(defaultFormatterPreferences.getBracesStart()).isEqualTo(0);
        assertThat(defaultFormatterPreferences.getIndentationMultiline()).isEqualTo(0);
        assertThat(defaultFormatterPreferences.getIndentationSize()).isEqualTo(4);
        assertThat(defaultFormatterPreferences.getTabSize()).isEqualTo(4);
        assertThat(defaultFormatterPreferences.getLongListLength()).isEqualTo(0);
        assertThat(defaultFormatterPreferences.getMaxLineLength()).isEqualTo(0);
        assertThat(defaultFormatterPreferences.isIndentEmptyLines()).isFalse();
        assertThat(defaultFormatterPreferences.isRemoveUnnecessarySemicolons()).isFalse();
        assertThat(defaultFormatterPreferences.isSmartPaste()).isFalse();
        assertThat(defaultFormatterPreferences.useTabs()).isTrue();
    }

}
