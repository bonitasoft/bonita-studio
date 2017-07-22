/**
 * Copyright (C) 2010 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.tests.repository;

import java.io.File;

import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.JavaModelException;

import junit.framework.TestCase;

/**
 * @author Mickael Istria
 */
public class TestExtensionProject extends TestCase {

    public void testExtensionProjectClasspathDoesNotContainAbsoluteFile() throws JavaModelException {
        for (IClasspathEntry entry : RepositoryManager.getInstance().getCurrentRepository().getJavaProject()
                .getRawClasspath()) {
            if (entry.getEntryKind() == IClasspathEntry.CPE_LIBRARY) {
                File file = new File(entry.getPath().toString());
                if (file.exists() && file.isAbsolute() && !Platform.inDevelopmentMode()) {
                    fail("Classpath should not contain absolute entry: " + entry.getPath().toString());
                }
            }
        }
    }
}
