/**
 * Copyright (C) 2021 Bonitasoft S.A.
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
package org.bonitasoft.studio.application.operation.extension.participant.configuration;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.core.maven.ProjectDependenciesResolver;
import org.bonitasoft.studio.common.repository.extension.update.DependencyUpdate;
import org.bonitasoft.studio.common.repository.extension.update.participant.ExtensionUpdateParticipant;
import org.bonitasoft.studio.common.repository.extension.update.participant.ExtensionUpdateParticipantFactory;
import org.bonitasoft.studio.common.repository.extension.update.participant.ExtensionUpdateParticipantFactoryRegistry;
import org.bonitasoft.studio.dependencies.configuration.ProcessConfigurationUpdater;

public class ProcessConfigurationUpdateParticipantFactory implements ExtensionUpdateParticipantFactory {

    private RepositoryAccessor repositoryAccessor;
    private ProjectDependenciesResolver projectDependencyResolver;
    private ProcessConfigurationCollector processConfigurationCollector;
    private ProcessConfigurationUpdater processConfigurationUpdater;

    @Inject
    public ProcessConfigurationUpdateParticipantFactory(RepositoryAccessor repositoryAccessor,
            ProjectDependenciesResolver projectDependencyResolver,
            ProcessConfigurationCollector processConfigurationCollector,
            ProcessConfigurationUpdater processConfigurationUpdater) {
        this.repositoryAccessor = repositoryAccessor;
        this.projectDependencyResolver = projectDependencyResolver;
        this.processConfigurationCollector = processConfigurationCollector;
        this.processConfigurationUpdater = processConfigurationUpdater;
    }

    @PostConstruct
    public void register() {
        ExtensionUpdateParticipantFactoryRegistry.getInstance().register(this);
    }

    @Override
    public ExtensionUpdateParticipant create(List<DependencyUpdate> dependenciesUpdate) {
        return new ProcessConfigurationUpdateParticipant(dependenciesUpdate,
                processConfigurationCollector,
                projectDependencyResolver,
                processConfigurationUpdater,
                repositoryAccessor.getCurrentRepository());
    }
}
