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
package org.bonitasoft.studio.tests;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.bonitasoft.studio.common.ProductVersion;
import org.eclipse.core.runtime.Platform;
import org.junit.Ignore;
import org.junit.Test;
import org.osgi.framework.Version;

public class ProductVersionIT {

    @Test
    public void shouldCurrentProductVersionEquals_POMVersionIgnoringQualifier() throws Exception {
        final Version osgiVersion = loadBundleVersion();
        final Version current = new Version(ProductVersion.CURRENT_VERSION);
        assertThat(current.getMajor()).isEqualTo(osgiVersion.getMajor());
        assertThat(current.getMinor()).isEqualTo(osgiVersion.getMinor());
        assertThat(current.getMicro()).isEqualTo(osgiVersion.getMicro());
    }

    private Version loadBundleVersion() {
        return Platform.getBundle("org.bonitasoft.studio.tests").getVersion();
    }

    @Test
    @Ignore
    public void shouldCurrentYearEquals_EffectiveCurrentYear() throws Exception {
        final String currentYear = new SimpleDateFormat("yyyy").format(new Date());
        assertThat(ProductVersion.CURRENT_YEAR).isEqualTo(currentYear);
    }

    @Test
    public void should_not_import_version_below_6_0_0() throws Exception {
        assertThat(ProductVersion.canBeImported("5.10.0")).isFalse();
        assertThat(ProductVersion.canBeImported("6.0.0")).isTrue();
    }
}
