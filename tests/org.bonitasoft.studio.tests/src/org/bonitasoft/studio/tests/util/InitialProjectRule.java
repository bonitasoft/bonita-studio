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
package org.bonitasoft.studio.tests.util;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.bonitasoft.studio.application.maven.handler.TestMavenRepositoriesConnectionHandler;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.core.maven.model.ProjectMetadata;
import org.bonitasoft.studio.common.ui.jface.FileActionDialog;
import org.bonitasoft.studio.maven.model.migration.ArchetypesRegistration;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.ui.PlatformUI;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.osgi.service.event.Event;

public class InitialProjectRule implements TestRule {

    public static final InitialProjectRule INSTANCE = new InitialProjectRule();

    @Override
    public Statement apply(Statement base, Description description) {
        return new Statement() {

            @Override
            public void evaluate() throws Throwable {
                initPreferences();
                ensureArchetypesRegistration();
                ensureDefaultProjectExists();
                ProjectUtil.cleanProject();
                try {
                    base.evaluate();
                } finally {
                    // Ensure project cleaned up after the test
                    ProjectUtil.cleanProject();
                }
            }
        };
    }

    /**
     * Ensure that the archetypes registration is done, even during test and not an actual product startup.
     */
    protected void ensureArchetypesRegistration() {
        Event e = new Event("org/eclipse/e4/ui/LifeCycle/appStartupComplete", Map.of());
        new ArchetypesRegistration().applicationStarted(e);
    }

    public static void ensureDefaultProjectExists() throws InvocationTargetException, InterruptedException {
        var repositoryManager = RepositoryManager.getInstance();
        var defaultProjectMetadta = ProjectMetadata.defaultMetadata();
        if (repositoryManager.getRepository(defaultProjectMetadta.getArtifactId()) == null
                || !repositoryManager.getRepository(defaultProjectMetadta.getArtifactId()).exists()) {
            PlatformUI.getWorkbench().getProgressService().run(false, false, monitor -> {
                Job.getJobManager().join(TestMavenRepositoriesConnectionHandler.TEST_CONNECTION_FAMILY, monitor);
                repositoryManager.getAccessor().createNewRepository(defaultProjectMetadta, monitor);
            });
        }
    }

    protected void initPreferences() {
        FileActionDialog.setDisablePopup(true);
    }

}
