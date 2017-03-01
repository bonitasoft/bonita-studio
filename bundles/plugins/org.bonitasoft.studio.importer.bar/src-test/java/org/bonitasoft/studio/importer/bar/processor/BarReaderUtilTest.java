/**
 * Copyright (C) 2014 Bonitasoft S.A.
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
package org.bonitasoft.studio.importer.bar.processor;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.zip.ZipException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

/**
 * @author Aurelien Pupier
 */
public class BarReaderUtilTest {

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    @Test
    public void findCustomConnectorClassNameTestWithEmptyFileDontThrowException() throws ZipException, IOException {
        final File emptyFile = tempFolder.newFile("testEmptyFile");
        BarReaderUtil.findCustomConnectorClassName(emptyFile);
    }

    @Test
    public void findSeveralConnectorClassName() throws IOException {
        final File zipFile = getZipFileToTest();

        final List<String> foundCustomConnectorClassName = BarReaderUtil.findCustomConnectorClassName(zipFile);
        assertThat(foundCustomConnectorClassName).containsExactly("name1", "name2");
    }

    private File getZipFileToTest() throws IOException, FileNotFoundException {
        final InputStream inputStream = BarReaderUtilTest.class.getClassLoader()
                .getResourceAsStream("zipWithSeveralPropertyFile.zip");
        final File zipFile = tempFolder.newFile("zipWithSeveralPropertyFile.zip");
        final OutputStream outputStream = new FileOutputStream(zipFile);

        int read = 0;
        final byte[] bytes = new byte[1024];

        while ((read = inputStream.read(bytes)) != -1) {
            outputStream.write(bytes, 0, read);
        }
        outputStream.close();
        return zipFile;
    }
}
