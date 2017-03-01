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
package org.bonitasoft.studio.tests.preferences;

import java.util.Locale;

import org.assertj.core.api.Assertions;
import org.bonitasoft.studio.preferences.LocaleUtil;
import org.junit.Test;

public class LocaleUtilIT {

    @Test
    public void testGetPortalLocales() {
        Assertions
                .assertThat(LocaleUtil.getProtalLocales())
                .containsOnly(new Locale("fr"), new Locale("es"), new Locale("en"), new Locale("pt", "BR"), new Locale("it"),
                        new Locale("de"));
    }

    @Test
    public void testGetStudioLocales() {
        Assertions
                .assertThat(LocaleUtil.getStudioLocales())
                .containsOnly(new Locale("fr"), new Locale("es"), new Locale("en"), new Locale("pt", "BR"), new Locale("it"),
                        new Locale("de"), new Locale("ja"));
    }

}
