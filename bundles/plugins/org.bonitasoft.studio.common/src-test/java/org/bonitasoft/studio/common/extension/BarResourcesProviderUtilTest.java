/**
 * Copyright (C) 2014 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.common.extension;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.assertj.core.api.Assertions;
import org.bonitasoft.engine.bpm.bar.BarResource;
import org.junit.Test;


public class BarResourcesProviderUtilTest {

    @Test
    public void testAddFileContents() throws FileNotFoundException, IOException {
        final File tempFile = File.createTempFile("fileTestToAdd", "");
        final FileWriter fw = new FileWriter(tempFile, true); //the true will append the new data
        final String fileContent = "add a line\n";
        fw.write(fileContent);//appends the string to the file
        fw.close();

        final ArrayList<BarResource> resources = new ArrayList<BarResource>();
        BarResourcesProviderUtil.addFileContents(resources, tempFile);

        Assertions.assertThat(resources.get(0).getName()).isEqualTo(tempFile.getName());
        Assertions.assertThat(resources.get(0).getContent()).isEqualTo(fileContent.getBytes());
    }

}
