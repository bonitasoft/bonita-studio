/**
 * Copyright (C) 2015 BonitaSoft S.A.
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
package org.bonitasoft.studio.common.repository.jdt;

import static org.bonitasoft.studio.assertions.StatusAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.File;
import java.nio.file.Files;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.ui.jarpackager.IJarExportRunnable;
import org.eclipse.jdt.ui.jarpackager.JarPackageData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mock.Strictness;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * @author Romain Bioteau
 */
@ExtendWith(MockitoExtension.class)
class CreateJarOperationTest {

    @Mock
    private IPackageFragment packageFragment;

    @Mock
    private IProgressMonitor monitor;

    @Mock(strictness = Strictness.LENIENT)
    private JarPackageData jarPackageData;

    @Mock(strictness = Strictness.LENIENT)
    private IJarExportRunnable exportRunnable;

    private CreateJarOperation operation;

    private File toFile;

    @Mock
    private ICompilationUnit cu;

    @BeforeEach
    void setUp() throws Exception {
        when(packageFragment.getCompilationUnits()).thenReturn(new ICompilationUnit[] { cu });
        when(jarPackageData.createJarExportRunnable(null)).thenReturn(exportRunnable);
        when(exportRunnable.getStatus()).thenReturn(Status.OK_STATUS);
        toFile = Files.createTempFile("test", ".jar").toFile();
        operation = spy(new CreateJarOperation(toFile, packageFragment.getCompilationUnits()));
        lenient().doReturn(jarPackageData).when(operation).newJarPackageData();
    }
    
    @AfterEach
    void deleteTmpFile() throws Exception {
        Files.delete(toFile.toPath());
    }

    @Test
    void should_run_a_JarExportRunnable() throws Exception {
        //When
        operation.run(monitor);

        //Then
        verify(exportRunnable).run(monitor);
    }

    @Test
    void should_configure_JarPackageData_to_overwrite_existing_file() throws Exception {
        //When
        operation.run(monitor);

        //Then
        verify(jarPackageData).setOverwrite(true);
    }

    @Test
    void should_configure_JarPackageData_to_with_target_file_path() throws Exception {
        //When
        operation.run(monitor);

        //Then
        verify(jarPackageData).setJarLocation(Path.fromOSString(toFile.getAbsolutePath()));
    }

    @Test
    void should_configure_JarPackageData_enable_deprecation_awareness() throws Exception {
        //When
        operation.run(monitor);

        //Then
        verify(jarPackageData).setDeprecationAware(true);
    }

    @Test
    void should_configure_JarPackageData_set_compilation_units_to_export() throws Exception {
        //When
        operation.run(monitor);

        //Then
        verify(jarPackageData).setElements(packageFragment.getCompilationUnits());
    }

    @Test
    void should_configure_JarPackageData_include_java_source_file() throws Exception {
        //When
        operation.includeSources().run(monitor);

        //Then
        verify(jarPackageData).setExportJavaFiles(true);
    }

    @Test
    void should_configure_JarPackageData_not_include_java_source_file() throws Exception {
        //When
        operation.run(monitor);

        //Then
        verify(jarPackageData).setExportJavaFiles(false);
    }

    @Test
    void should_retrieve_status_from_export_runnable() throws Exception {
        //When
        operation.run(monitor);

        //Then
        assertThat(operation.getStatus()).isOK();
    }

    @Test
    void should_retrieve_an_error_status_if_operation_is_not_ran() throws Exception {
        //When
        //!operation.run(monitor);

        //Then
        assertThat(operation.getStatus()).isNotOK();
    }

    public void should_throw_an_IllegalArgumentException_if_file_is_null() throws Exception {
        //When
        assertThrows(IllegalArgumentException.class, () -> new CreateJarOperation(null, packageFragment.getCompilationUnits()));
    }

    public void should_throw_an_IllegalArgumentException_if_no_compilation_unit() throws Exception {
        //When
        assertThrows(IllegalArgumentException.class, () -> new CreateJarOperation(toFile, null));
    }
}
