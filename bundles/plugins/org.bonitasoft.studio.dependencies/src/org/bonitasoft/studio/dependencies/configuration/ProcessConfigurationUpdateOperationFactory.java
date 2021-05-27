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
package org.bonitasoft.studio.dependencies.configuration;

import javax.inject.Inject;

import org.bonitasoft.studio.common.repository.core.maven.ProjectDependenciesResolver;
import org.eclipse.e4.core.di.annotations.Creatable;

@Creatable
public class ProcessConfigurationUpdateOperationFactory {

    private ProcessConfigurationCollector processConfigurationCollector;
    private ProcessConfigurationUpdater processConfigurationUpdater;
    private ProjectDependenciesResolver dependencyResolver;

    @Inject
    public ProcessConfigurationUpdateOperationFactory(ProcessConfigurationCollector processConfigurationCollector,
            ProcessConfigurationUpdater processConfigurationUpdater,
            ProjectDependenciesResolver dependencyResolver) {
        this.processConfigurationCollector = processConfigurationCollector;
        this.processConfigurationUpdater = processConfigurationUpdater;
        this.dependencyResolver = dependencyResolver;
    }

    public ProcessConfigurationUpdateOperation create() {
        return new ProcessConfigurationUpdateOperation(processConfigurationCollector,
                processConfigurationUpdater,
                dependencyResolver);
    }

}
