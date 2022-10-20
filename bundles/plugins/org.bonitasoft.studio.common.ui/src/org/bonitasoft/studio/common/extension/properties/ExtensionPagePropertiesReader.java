/**
 * Copyright (C) 2021 Bonitasoft S.A.
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
package org.bonitasoft.studio.common.extension.properties;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;
import java.util.function.Predicate;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.bonitasoft.studio.common.log.BonitaStudioLog;

public class ExtensionPagePropertiesReader {

    private ExtensionPagePropertiesReader() {

    }

    public static Optional<Properties> getPageProperties(File zipFile) throws IOException {
        return findZipEntry(zipFile, entry -> entry.getName().equals("page.properties"))
                .map(entry -> {
                    try (ZipFile zip = new ZipFile(zipFile);
                            Reader reader = new InputStreamReader(zip.getInputStream(entry),
                                    StandardCharsets.UTF_8)) {
                        Properties prop = new Properties();
                        prop.load(reader);
                        return prop;
                    } catch (IOException e) {
                        BonitaStudioLog.error(e);
                        return null;
                    }
                })
                .filter(Objects::nonNull);
    }

    public static Optional<String> getProperty(Properties properties, String property) {
        return Optional.ofNullable(properties.getProperty(property))
                .filter(Objects::nonNull)
                .filter(value -> !(value.trim().startsWith("${") && value.trim().endsWith("}")))
                .filter(value -> !(value.trim().startsWith("custompage_${") && value.trim().endsWith("}")));
    }

    private static Optional<? extends ZipEntry> findZipEntry(File file, Predicate<? super ZipEntry> entryPredicate)
            throws IOException {
        try (ZipFile zipFile = new ZipFile(file)) {
            return zipFile.stream()
                    .filter(entryPredicate)
                    .findFirst();
        }
    }

}
