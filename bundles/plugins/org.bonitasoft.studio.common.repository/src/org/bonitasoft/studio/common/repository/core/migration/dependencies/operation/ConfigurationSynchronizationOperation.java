/**
 * Copyright (C) 2023 BonitaSoft S.A.
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
package org.bonitasoft.studio.common.repository.core.migration.dependencies.operation;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.HashSet;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.core.migration.dependencies.ConfigurationCollector;
import org.bonitasoft.studio.common.repository.core.migration.dependencies.Synchronizer;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.operation.IRunnableWithProgress;

public class ConfigurationSynchronizationOperation implements IRunnableWithProgress {

    private ConfigurationCollector collector;
    private Synchronizer configurationSynchronizer;

    public ConfigurationSynchronizationOperation(
            ConfigurationCollector collector,
            Synchronizer configurationSynchronizer) {
        this.collector = collector;
        this.configurationSynchronizer = configurationSynchronizer;
    }

    @Override
    public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        collector.forEach((process, configurations) -> {
            var resources = new HashSet<Resource>();
            for (var conf : configurations) {
                configurationSynchronizer.synchronize(process, conf);
                var modifiedResources = conf.eResource();
                if (modifiedResources != null) {
                    resources.add(modifiedResources);
                }
            }
            resources.stream().forEach(r -> {
                try {
                    r.save(Collections.emptyMap());
                } catch (IOException e) {
                    BonitaStudioLog.error(e);
                }
            });
        });
    }

}
