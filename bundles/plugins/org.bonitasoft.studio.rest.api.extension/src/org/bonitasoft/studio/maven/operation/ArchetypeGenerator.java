/**
 * Copyright (C) 2025 BonitaSoft S.A.
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
package org.bonitasoft.studio.maven.operation;

import java.util.Collection;

import org.bonitasoft.studio.maven.model.ExtensionProjectArchetypeConfiguration;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.m2e.core.project.IArchetype;
import org.eclipse.m2e.core.project.MavenProjectInfo;
import org.eclipse.m2e.core.ui.internal.M2EUIPluginActivator;
import org.eclipse.swt.widgets.Display;

/**
 * This singleton hides the org.eclipse.m2e.core.ui.internal.archetype.ArchetypeGenerator class dependency and avoids the m2e UI plugin is loaded too early
 * while workbench is not ready yet.
 */
public class ArchetypeGenerator {

    private static final class InstanceHolder {

        private static final ArchetypeGenerator INSTANCE = new ArchetypeGenerator();
    }

    public static ArchetypeGenerator getInstance() {
        return InstanceHolder.INSTANCE;
    }

    /**
     * Create a new project from the given archetype.
     * 
     * @param location the location where the project should be created
     * @param archetype the archetype to use to create the project
     * @param config the configuration to use for project information
     * @param monitor the progress monitor to use for the operation
     * @return a collection of MavenProjectInfo representing the created projects
     * @throws CoreException if an error occurs while creating the project
     */
    public Collection<MavenProjectInfo> createArchetypeProjects(IPath location, IArchetype archetype,
            ExtensionProjectArchetypeConfiguration config, IProgressMonitor monitor) throws CoreException {
        // must be run in UI thread because it may trigger a progress dialog
        return Display.getDefault().syncCall(() -> {
            return archetypeGenerator().createArchetypeProjects(
                    location, archetype,
                    config.getGroupId(), config.getProjectName(), config.getVersion(), config.getJavaPackage(),
                    config.toProperties(), false, monitor);
        });
    }

    private org.eclipse.m2e.core.ui.internal.archetype.ArchetypeGenerator archetypeGenerator() {
        return M2EUIPluginActivator.getDefault().getArchetypePlugin().getGenerator();
    }

}
