/**
 * Copyright (C) 2013 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.common;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.osgi.framework.Version;

public class ProductVersionTest {

    @Test
    public void shouldCurrentProductVersionEquals_POMVersionIgnoringQualifier() throws Exception {
        final String pomVersion = loadPomVersion();
        org.junit.Assume.assumeFalse("Test must be run in maven to retrieve pom version", "UNDEFINED".equals(pomVersion));
        final Version current = new Version(ProductVersion.CURRENT_VERSION);
        final Version osgiPomVersion = Version.parseVersion(pomVersion);
        assertThat(current.getMajor()).isEqualTo(osgiPomVersion.getMajor());
        assertThat(current.getMinor()).isEqualTo(osgiPomVersion.getMinor());
        assertThat(current.getMicro()).isEqualTo(osgiPomVersion.getMicro());
    }

    private String loadPomVersion() {
        String pomVersion = System.getProperty("projectVersion", "UNDEFINED");
        if (pomVersion.indexOf("-SNAPSHOT") != -1) {
            pomVersion = pomVersion.substring(0, pomVersion.indexOf("-SNAPSHOT"));
        }
        return pomVersion;
    }

    @Test
    public void shouldCurrentYearEquals_EffectiveCurrentYear() throws Exception {
        final String currentYear = new SimpleDateFormat("yyyy").format(new Date());
        assertThat(ProductVersion.CURRENT_YEAR).isEqualTo(currentYear);
    }
}
