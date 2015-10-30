/**
 * Copyright (C) 2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.groovy.provider;

import static org.assertj.core.api.Assertions.assertThat;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.junit.Test;

public class JarExportFailedExceptionTest {

    @Test
    public void testMessageWithSimpleStatus() throws Exception {
        final JarExportFailedException jarExportFailedException = new JarExportFailedException("global", new Status(IStatus.ERROR, "none", "message status"));

        assertThat(jarExportFailedException.getMessage()).startsWith("global").contains("message status");
    }

    @Test
    public void testMessageWithMultiStatus() throws Exception {
        final MultiStatus multi = new MultiStatus("none", IStatus.ERROR, "multi message", null);
        multi.add(new Status(IStatus.ERROR, "none", "message status 1"));
        multi.add(new Status(IStatus.ERROR, "none", "message status 2"));
        final JarExportFailedException jarExportFailedException = new JarExportFailedException("global", multi);

        assertThat(jarExportFailedException.getMessage())
                .startsWith("global")
                .doesNotContain("multi message")
                .contains("message status 1")
                .contains("message status 2");
    }
}
