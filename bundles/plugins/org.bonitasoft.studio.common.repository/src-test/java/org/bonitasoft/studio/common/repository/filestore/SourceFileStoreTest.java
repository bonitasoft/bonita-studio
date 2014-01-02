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

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.io.File;

import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.common.repository.store.SourceRepositoryStore;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.ui.jarpackager.JarPackageData;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Romain Bioteau
 *
 */
public class SourceFileStoreTest {

	private IRepositoryStore parentStore;
	
	private SourceFileStore fileStoreUnderTest;
	
	private File tmpJarFile;
	
	private JarPackageData packageData;
	

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		parentStore = mock(IRepositoryStore.class);
		fileStoreUnderTest = spy(new SourceFileStore("org.bonita.test.Test", parentStore));
		tmpJarFile = File.createTempFile("tmpJar",".jar",new File(System.getProperty("java.io.tmpdir")));
		packageData = mock(JarPackageData.class);
		doReturn(packageData).when(fileStoreUnderTest).createJarPackageData();
		doReturn(null).when(fileStoreUnderTest).getResource();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		tmpJarFile.delete();
	}
	
	@Test
	public void shoudExportAsJar_ConfigureJarPackageData() throws Exception {
		fileStoreUnderTest.exportAsJar(tmpJarFile.getAbsolutePath(),true);
		verify(packageData).setBuildIfNeeded(true);
		verify(packageData).setExportWarnings(true);
		verify(packageData).setComment(SourceRepositoryStore.SIGNATURE_FILE_NAME);
		verify(packageData).setExportClassFiles(true);
		verify(packageData).setExportJavaFiles(true);
		verify(packageData).setGenerateManifest(true);
		verify(packageData).setElements(any(Object[].class));
		verify(packageData).setUseSourceFolderHierarchy(true) ;
		verify(packageData).setOverwrite(true);
		verify(packageData).setJarLocation(Path.fromOSString(tmpJarFile.getAbsolutePath())) ;
		verify(packageData).createJarExportRunnable(null);
	}

}
