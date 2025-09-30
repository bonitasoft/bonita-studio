/**
 * Copyright (C) 2025 BonitaSoft S.A.
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
package org.bonitasoft.studio.common.platform.tools;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.stream.IntStream;

import org.junit.Test;

/**
 * Test class for {@link CopyInputStream}.
 */
public class CopyInputStreamTest {

    @Test
    public void shouldCopyAndClose_getContent() throws IOException {
        var str = IntStream.range('A', 'z' + 1).mapToObj(c -> (char) c).collect(StringBuilder::new,
                StringBuilder::appendCodePoint,
                StringBuilder::append).toString();
        try (ByteArrayInputStream stream = new ByteArrayInputStream(str.getBytes())) {
            InputStream resultingCopy;
            File file;
            try (var copyIs = new CopyInputStream(stream)) {
                resultingCopy = copyIs.getCopy();
                file = copyIs.getFile();
            }
            assertThat(file).doesNotExist();
            assertThat(resultingCopy.readAllBytes()).isEqualTo(str.getBytes());
        }
    }

    @Test
    public void shouldFileInputStream_readAfterDelete() throws IOException {
        var str = IntStream.range('A', 'z' + 1).mapToObj(c -> (char) c).collect(StringBuilder::new,
                StringBuilder::appendCodePoint,
                StringBuilder::append).toString();
        File file = File.createTempFile("test", ".txt");
        Files.write(file.toPath(), str.getBytes());
        try (InputStream inputStream = new FileInputStream(file)) {
            file.delete();
            assertThat(inputStream.readAllBytes()).isEqualTo(str.getBytes());
        }
    }
}
