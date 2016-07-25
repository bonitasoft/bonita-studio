/**
 * Copyright (C) 2014 Bonitasoft S.A.
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

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class JDTMethodHelperTest {

    @Test
    public void testHandlePrimitiveTypes() {
        test("int", Integer.class.getName());
        test("boolean", Boolean.class.getName());
        test("long", Long.class.getName());
        test("float", Float.class.getName());
        test("double", Double.class.getName());
        test("short", Short.class.getName());
        test("byte", Byte.class.getName());
        test("E", Object.class.getName());
        test("V", Object.class.getName());
    }

    protected void test(final String input, final String output) {
        Assertions.assertThat(JDTMethodHelper.handlePrimitiveTypes(input)).isEqualTo(output);
    }

}
