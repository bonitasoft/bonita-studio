/**
 * Copyright (C) 2013 BonitaSoft S.A.
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
package org.bonitasoft.studio.businessobject.ui.wizard;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.spy;

import java.io.File;

import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.eclipse.swt.widgets.Composite;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author Romain Bioteau
 * 
 */
@RunWith(MockitoJUnitRunner.class)
public class ExportBusinessDataModelWizardPageTest {

    @Mock
    private BusinessObjectModelRepositoryStore businessObjectDefinitionStore;

    private ExportBusinessDataModelWizardPage wizardPageUnderTest;

    private File tmpFile;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        wizardPageUnderTest = spy(new ExportBusinessDataModelWizardPage(businessObjectDefinitionStore));
        doNothing().when(wizardPageUnderTest).createControl(any(Composite.class));
        tmpFile = File.createTempFile("testExport", ".zip", new File(System.getProperty("java.io.tmpdir")));
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        tmpFile.delete();
    }

    @Test
    public void shouldValidateFilePath_ReturnValidStatus_IfFilePathIsAValidFolder() throws Exception {
        assertThat(wizardPageUnderTest.validateFilePath(System.getProperty("java.io.tmpdir")).isOK()).isTrue();
    }

    @Test
    public void shouldValidateFilePath_ReturnFailStatus_IfFilePathIsNotAValidFolder() throws Exception {
        assertThat(wizardPageUnderTest.validateFilePath(System.getProperty("java.io.tmpdir") + File.separatorChar + "aFileName.zip").isOK()).isFalse();
        assertThat(wizardPageUnderTest.validateFilePath("toto").isOK()).isFalse();
        assertThat(wizardPageUnderTest.validateFilePath(tmpFile.getAbsolutePath()).isOK()).isFalse();
        assertThat(wizardPageUnderTest.validateFilePath(null).isOK()).isFalse();
        assertThat(wizardPageUnderTest.validateFilePath(tmpFile).isOK()).isFalse();
    }

}
