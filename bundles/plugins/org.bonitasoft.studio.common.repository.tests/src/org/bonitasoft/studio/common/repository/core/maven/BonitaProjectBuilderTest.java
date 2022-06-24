/**
 * Copyright (C) 2021 BonitaSoft S.A.
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
package org.bonitasoft.studio.common.repository.core.maven;

import static org.bonitasoft.studio.assertions.StatusAssert.assertThat;

import org.bonitasoft.studio.common.ProductVersion;
import org.junit.Test;

public class BonitaProjectBuilderTest {

    @Test
    public void should_report_warning_when_runtime_version_doesnt_match() throws Exception {
        var validator = new BonitaProjectBuilder.BonitaRuntimeVersionValidator();
        
        assertThat(validator.validate(ProductVersion.minorVersion() + ".5")).isWarning();
    }
    
    @Test
    public void should_report_error_when_runtime_version_is_another_major_version() throws Exception {
        var validator = new BonitaProjectBuilder.BonitaRuntimeVersionValidator();
        
        assertThat(validator.validate("7.7.0")).isError();
    }
    
    @Test
    public void should_be_ok_when_runtime_version_perfeclty_match() throws Exception {
        var validator = new BonitaProjectBuilder.BonitaRuntimeVersionValidator();
        
        assertThat(validator.validate(ProductVersion.BONITA_RUNTIME_VERSION)).isOK();
    }

}
