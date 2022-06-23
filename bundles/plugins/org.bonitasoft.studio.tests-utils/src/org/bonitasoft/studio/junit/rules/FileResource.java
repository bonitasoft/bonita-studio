/**
 * Copyright (C) 2017 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.junit.rules;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import org.junit.rules.ExternalResource;

public class FileResource extends ExternalResource {

    private final String res;
    private File file = null;
    private InputStream stream;

    public FileResource(String resourcePathInClassloader) {
        this.res = resourcePathInClassloader;
    }

    public File getFile() throws IOException {
        if (file == null) {
            createFile();
        }
        return file;
    }

    public InputStream getInputStream() {
        return stream;
    }

    private InputStream createInputStream() {
        return getClass().getResourceAsStream(res);
    }

    public String getContent() throws IOException {
        return getContent("utf-8");
    }

    public String getContent(String charSet) throws IOException {
        final char[] tmp = new char[4096];
        final StringBuilder b = new StringBuilder();
        try (InputStreamReader reader = new InputStreamReader(createInputStream(),
                Charset.forName(charSet))) {
            while (true) {
                final int len = reader.read(tmp);
                if (len < 0) {
                    break;
                }
                b.append(tmp, 0, len);
            }
        }
        return b.toString();
    }

    @Override
    protected void before() throws Throwable {
        super.before();
        stream = getClass().getResourceAsStream(res);
    }

    @Override
    protected void after() {
        try {
            stream.close();
        } catch (final IOException e) {
            // ignore
        }
        if (file != null) {
            file.delete();
        }
        super.after();
    }

    private void createFile() throws IOException {
        file = new File(".", res);
        try (InputStream stream = getClass().getResourceAsStream(res)) {
            file.createNewFile();
            try (FileOutputStream ostream = new FileOutputStream(file)) {
                final byte[] buffer = new byte[4096];
                while (true) {
                    final int len = stream.read(buffer);
                    if (len < 0) {
                        break;
                    }
                    ostream.write(buffer, 0, len);
                }
            }
        }
    }
}
