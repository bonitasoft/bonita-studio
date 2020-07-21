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
package org.bonitasoft.studio.team.repository;

import org.bonitasoft.studio.common.extension.ExtensionContextInjectionFactory;
import org.bonitasoft.studio.common.repository.IRepositoryFactory;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.core.ProjectClasspathFactory;
import org.bonitasoft.studio.common.repository.core.ProjectManifestFactory;
import org.bonitasoft.studio.common.repository.jdt.JDTTypeHierarchyManager;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;

public class RepositoryFactory implements IRepositoryFactory {

    @Override
    public AbstractRepository newRepository(final String name, final boolean migrationEnabled) {
        final IWorkspace workspace = ResourcesPlugin.getWorkspace();
        return new Repository(workspace, workspace.getRoot().getProject(name),
                new ExtensionContextInjectionFactory(), new JDTTypeHierarchyManager(), new ProjectManifestFactory(), new ProjectClasspathFactory(),
                migrationEnabled);
    }

}
