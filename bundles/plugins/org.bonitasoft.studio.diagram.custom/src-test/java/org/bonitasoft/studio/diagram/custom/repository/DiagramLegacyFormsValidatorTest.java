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
package org.bonitasoft.studio.diagram.custom.repository;


import static org.bonitasoft.studio.assertions.StatusAssert.assertThat;

import java.io.InputStream;

import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.junit.Test;

public class DiagramLegacyFormsValidatorTest {
    
    @Test
    public void should_detect_legacy_form_mapping() throws Exception {
        DiagramLegacyFormsValidator validator = new DiagramLegacyFormsValidator();
        
        IStatus status = ValidationStatus.error("failed");
        try(InputStream is = DiagramLegacyFormsValidatorTest.class.getResourceAsStream("/ProcessWithLegacyForms-1.0.proc")){
            status = validator.validate(is);
        }
        
        assertThat(status).isWarning();
    }
    
    @Test
    public void should_not_detect_legacy_form_mapping() throws Exception {
        DiagramLegacyFormsValidator validator = new DiagramLegacyFormsValidator();
        
        IStatus status = ValidationStatus.error("failed");
        try(InputStream is = DiagramLegacyFormsValidatorTest.class.getResourceAsStream("/ProcessWithoutLegacyForms-1.0.proc")){
            status = validator.validate(is);
        }
        
        assertThat(status).isOK();
    }

}
