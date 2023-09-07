/**
 * Copyright (C) 2022 BonitaSoft S.A.
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
package org.bonitasoft.studio.common.repository;

import org.bonitasoft.studio.common.extension.ExtensionContextInjectionFactory;
import org.bonitasoft.studio.common.repository.core.BonitaProject;
import org.bonitasoft.studio.common.repository.jdt.JDTTypeHierarchyManager;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.e4.core.services.events.IEventBroker;

class TestRepository extends AbstractRepository{

    public TestRepository(IWorkspace workspace, BonitaProject project,
            ExtensionContextInjectionFactory extensionContextInjectionFactory,
            JDTTypeHierarchyManager jdtTypeHierarchyManager, IEventBroker eventBroker) {
        super(workspace, project, extensionContextInjectionFactory, jdtTypeHierarchyManager, eventBroker);
    }

}
