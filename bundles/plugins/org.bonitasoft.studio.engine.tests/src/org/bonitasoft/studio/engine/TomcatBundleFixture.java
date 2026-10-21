/**
 * Copyright (C) 2026 Bonitasoft S.A.
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
package org.bonitasoft.studio.engine;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.jar.JarOutputStream;
import java.util.zip.ZipEntry;

import org.junit.rules.TemporaryFolder;

/**
 * An install tree on disk whose {@code catalina.jar} reports whatever version a test needs. Shared
 * because the version check is exercised from both sides: directly on {@link TomcatInstall}, and
 * through the launch setup that decides whether the shipped bundle is the wrong Tomcat line.
 */
final class TomcatBundleFixture {

    private TomcatBundleFixture() {
        // fixture factory
    }

    /** A bundle whose marker jar declares {@code serverInfoContent} as its ServerInfo.properties. */
    static File bundleWithCatalinaJar(TemporaryFolder temporaryFolder, String serverInfoContent) throws Exception {
        return bundleWithJarEntry(temporaryFolder, "org/apache/catalina/util/ServerInfo.properties",
                serverInfoContent);
    }

    /** A bundle whose marker jar holds one arbitrary entry: for the uninformative-jar cases. */
    static File bundleWithJarEntry(TemporaryFolder temporaryFolder, String entryName, String content)
            throws Exception {
        var bundleDir = temporaryFolder.newFolder("tomcat");
        var lib = Files.createDirectories(bundleDir.toPath().resolve("server").resolve("lib"));
        try (var jar = new JarOutputStream(Files.newOutputStream(lib.resolve("catalina.jar")))) {
            jar.putNextEntry(new ZipEntry(entryName));
            jar.write(content.getBytes(StandardCharsets.UTF_8));
            jar.closeEntry();
        }
        return bundleDir;
    }
}
