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
package org.bonitasoft.studio.engine;

import java.io.File;
import java.util.ArrayList;

import org.bonitasoft.bonita2bar.ConnectorImplementationRegistry;
import org.bonitasoft.bonita2bar.ConnectorImplementationRegistry.ArtifactInfo;
import org.bonitasoft.bonita2bar.ConnectorImplementationRegistry.ConnectorImplementationJar;
import org.bonitasoft.plugin.analyze.report.model.Implementation;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.eclipse.core.runtime.NullProgressMonitor;

public class ConnectorImplementationRegistryHelper {

    public static ConnectorImplementationRegistry getConnectorImplementationRegistry() {
        var reportStore = RepositoryManager.getInstance().getCurrentRepository().orElseThrow()
                .getProjectDependenciesStore();
        var report = reportStore.getReport()
                .orElseGet(() -> reportStore.analyze(new NullProgressMonitor()).orElseThrow());

        var implementations = new ArrayList<ConnectorImplementationJar>();
        report.getConnectorImplementations().stream()
                .map(ConnectorImplementationRegistryHelper::toConnectorImplementationJar)
                .forEach(implementations::add);
        report.getFilterImplementations().stream()
                .map(ConnectorImplementationRegistryHelper::toConnectorImplementationJar)
                .forEach(implementations::add);
        return ConnectorImplementationRegistry.of(implementations);
    }

    private static ConnectorImplementationJar toConnectorImplementationJar(Implementation implementation) {
        var artifact = implementation.getArtifact();
        if (artifact != null) {
            var artifactInfo = new ArtifactInfo(artifact.getGroupId(), artifact.getArtifactId(),
                    artifact.getVersion(), artifact.getClassifier(), artifact.getFile());
            return ConnectorImplementationJar.of(implementation.getImplementationId(),
                    implementation.getImplementationVersion(), artifactInfo, implementation.getJarEntry());
        } else {
            return ConnectorImplementationJar.of(implementation.getImplementationId(),
                    implementation.getImplementationVersion(), new File(implementation.getArtifact().getFile()),
                    implementation.getJarEntry());
        }
    }

}
