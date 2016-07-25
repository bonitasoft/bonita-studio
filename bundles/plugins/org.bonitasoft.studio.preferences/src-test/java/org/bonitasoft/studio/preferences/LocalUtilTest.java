/**
 * Copyright (C) 2014 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.preferences;

import org.assertj.core.api.Assertions;
import org.junit.Test;


public class LocalUtilTest {

    @Test
    public void testReplacePartNotI18nRelatedForDefaultLanguage() {
        final String[] poFiles = new String[] { "portal-js.po", "portal-js-sp.po", "mobile.po", "mobile-sp.po", "portal.po", "portal-sp.po" };
        for (final String poFile : poFiles) {
            Assertions.assertThat(LocaleUtil.replacePartNotI18nRelated(poFile)).isEqualTo("");
        }
    }

    @Test
    public void testReplacePartNotI18nRelatedForSpecificLanague() {
        final String[] poFiles = new String[] { "portal-js_fr.po", "portal-js-sp_fr.po", "mobile_fr.po", "mobile-sp_fr.po", "portal_fr.po", "portal-sp_fr.po" };
        for (final String poFile : poFiles) {
            Assertions.assertThat(LocaleUtil.replacePartNotI18nRelated(poFile)).isEqualTo("fr");
        }
    }

    @Test
    public void testReplacePartNotI18nRelatedForSpecificCountryLanguage() {
        final String[] poFiles = new String[] { "portal-js_pt_BR.po", "portal-js-sp_pt_BR.po", "mobile_pt_BR.po", "mobile-sp_pt_BR.po", "portal_pt_BR.po",
                "portal-sp_pt_BR.po" };
        for (final String poFile : poFiles) {
            Assertions.assertThat(LocaleUtil.replacePartNotI18nRelated(poFile)).isEqualTo("pt_BR");
        }
    }

}
