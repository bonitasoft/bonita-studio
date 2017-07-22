/**
 * Copyright (C) 2009-2012 BonitaSoft S.A.
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
package org.bonitasoft.studio.tests.repository;

import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;

import junit.framework.TestCase;

/**
 * @author Baptiste Mesta
 *         test that all bonita projects are in the local workspace
 */
public class TestRepositoryLocation extends TestCase {

    public void testMyExtensions() throws Exception {
        IProject project = RepositoryManager.getInstance().getCurrentRepository().getProject();
        assertTrue(ResourcesPlugin.getWorkspace().getRoot().findMember(project.getName()) != null
                && project.getName().startsWith(RepositoryManager.getInstance().getCurrentRepository().getName()));
    }

}
