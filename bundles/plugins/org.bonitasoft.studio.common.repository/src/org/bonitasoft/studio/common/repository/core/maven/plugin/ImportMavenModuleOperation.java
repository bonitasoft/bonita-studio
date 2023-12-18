/**
 * Copyright (C) 2021 BonitaSoft S.A.
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
package org.bonitasoft.studio.common.repository.core.maven.plugin;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.BuildScheduler;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.m2e.core.MavenPlugin;
import org.eclipse.m2e.core.internal.preferences.MavenPreferenceConstants;
import org.eclipse.m2e.core.project.IProjectConfigurationManager;
import org.eclipse.m2e.core.project.LocalProjectScanner;
import org.eclipse.m2e.core.project.MavenProjectInfo;
import org.eclipse.m2e.core.project.ProjectImportConfiguration;
import org.eclipse.m2e.core.ui.internal.M2EUIPluginActivator;

public class ImportMavenModuleOperation implements IWorkspaceRunnable {

    private File projectRoot;
    private IProjectConfigurationManager projectConfigurationManager;

    public ImportMavenModuleOperation(File projectRoot) {
        this.projectRoot = projectRoot;
        this.projectConfigurationManager = MavenPlugin.getProjectConfigurationManager();
    }

    @Override
    public void run(final IProgressMonitor monitor) throws CoreException {
        BuildScheduler.callWithBuildRule(() -> {
            if (projectRoot == null || !projectRoot.exists()
                    || !projectRoot.toPath().resolve("pom.xml").toFile().exists()) {
                throw new CoreException(Status.error(String.format("No project found at %s", projectRoot)));
            }
            var scanner = new LocalProjectScanner(List.of(projectRoot.getAbsolutePath()), false,
                    MavenPlugin.getMavenModelManager());
            try {
                scanner.run(monitor);
            } catch (InterruptedException e) {
                BonitaStudioLog.error(e);
            }
            var store = M2EUIPluginActivator.getDefault().getPreferenceStore();
            store.setValue(MavenPreferenceConstants.P_AUTO_UPDATE_CONFIGURATION, false);
            try {
                var projectImportConfiguration = new ProjectImportConfiguration();
                projectImportConfiguration.setProjectNameTemplate("[artifactId]");
                projectConfigurationManager.importProjects(
                        flatten(scanner.getProjects()),
                        projectImportConfiguration, monitor);
            } finally {
                store.setValue(MavenPreferenceConstants.P_AUTO_UPDATE_CONFIGURATION, true);
            }
            return (Void) null;
        }, monitor);
    }

    private static Collection<MavenProjectInfo> flatten(Collection<MavenProjectInfo> projects) {
        var flatList = new ArrayList<MavenProjectInfo>();
        for (MavenProjectInfo t : projects) {
            flatList.add(t);
            if (t.getProjects() != null) {
                flatList.addAll(flatten(t.getProjects()));
            }
        }
        return flatList;
    }

}
