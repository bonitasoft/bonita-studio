/**
 * Copyright (C) 2013 BonitaSoft S.A.
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
package org.bonitasoft.studio.tests.api;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.xml.api.XSDImport;
import org.bonitasoft.studio.xml.repository.XSDRepositoryStore;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class TestBonitaAPI {

    @Rule
    public TemporaryFolder tmpFolder = new TemporaryFolder();

    @Test
    public void testXSDImport() throws IOException {
        final XSDRepositoryStore xsdStore = RepositoryManager.getInstance()
                .getRepositoryStore(XSDRepositoryStore.class);
        final int countBefore = xsdStore.getChildren().size();
        XSDImport.importXSD(createXSDFileToImport());
        final int countAfter = xsdStore.getChildren().size();
        Assert.assertEquals("XSD import doesn't work", countBefore + 1, countAfter);
    }

    private String createXSDFileToImport() throws IOException, FileNotFoundException {
        try (final InputStream stream = TestBonitaAPI.class.getResourceAsStream("RealTimeMarketData.xsd")) {
            final File outputXSD = tmpFolder.newFile("RealTimeMarketData.xsd");
            Files.copy(stream, outputXSD.toPath(), StandardCopyOption.REPLACE_EXISTING);
            return outputXSD.getAbsolutePath();
        }
    }

}
