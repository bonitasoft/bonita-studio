/**
 * Copyright (C) 2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.common.jface.databinding.validator;

import static org.bonitasoft.studio.assertions.StatusAssert.assertThat;

import java.io.File;
import java.nio.file.InvalidPathException;

import org.eclipse.core.runtime.IStatus;
import org.junit.Assume;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TemporaryFolder;

public class PathValidatorTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Rule
    public TemporaryFolder tmpFolder = new TemporaryFolder();

    @Test
    public void should_fail_if_path_is_empty() throws Exception {
        final PathValidator pathValidator = new PathValidator("location");

        assertThat(pathValidator.validate("")).isNotOK();
    }

    @Test
    public void should_fail_if_path_is_null() throws Exception {
        final PathValidator pathValidator = new PathValidator("location");

        assertThat(pathValidator.validate(null)).isNotOK();
    }

    @Test
    public void should_throw_an_InvalidPathException_if_path_contains_illegal_character() throws Exception {
        assumeOSIsWindows();

        final PathValidator pathValidator = new PathValidator("location");

        expectedException.expect(InvalidPathException.class);

        pathValidator.validate("/my/path/*");
    }


    @Test
    public void should_fail_if_path_if_does_not_exists() throws Exception {
        final PathValidator pathValidator = new PathValidator("location");

        final IStatus status = pathValidator.validate(new File(tmpFolder.newFolder(), "notExists").getAbsolutePath());

        assertThat(status).isNotOK();
    }

    @Test
    public void should_not_fail_if_path_exists() throws Exception {
        final PathValidator pathValidator = new PathValidator("location");

        final IStatus status = pathValidator.validate(tmpFolder.newFolder().getAbsolutePath());

        assertThat(status).isOK();
    }

    private void assumeOSIsWindows() {
        final String osName = System.getProperty("os.name");
        Assume.assumeTrue(osName != null && osName.startsWith("Windows"));
    }

}
