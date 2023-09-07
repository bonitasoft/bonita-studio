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
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.common.repository.filestore;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * @author Romain Bioteau
 *
 */
@ExtendWith(MockitoExtension.class)
class AbstractFileStoreTest {

	@Mock
	private AbstractFileStore fileStore;
	
	@Mock
	private File exportFile;
	
	@Mock
	private File parentExportFile;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	public void setUp() throws Exception {
		when(fileStore.checkWritePermission(any(File.class))).thenCallRealMethod();
		when(exportFile.getAbsolutePath()).thenReturn("testPath");
		when(exportFile.getParentFile()).thenReturn(parentExportFile);
		lenient().when(parentExportFile.getAbsolutePath()).thenReturn("parentTestPath");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link org.bonitasoft.studio.common.repository.filestore.AbstractFileStore#checkWritePermission(java.lang.String)}.
	 */
	@Test
	public void shouldHaveWritePermission_ReturnsTrue() throws Exception {
		when(parentExportFile.canWrite()).thenReturn(true);
		assertThat(fileStore.checkWritePermission(exportFile)).isTrue();
	}
	
	@Test
	public void shouldHaveWritePermission_ThrowsAnIOException() throws Exception {
		assertThrows(IOException.class, () -> fileStore.checkWritePermission(exportFile));
	}

}
