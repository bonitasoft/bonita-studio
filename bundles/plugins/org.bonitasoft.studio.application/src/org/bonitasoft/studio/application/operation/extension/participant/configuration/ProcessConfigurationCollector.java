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
package org.bonitasoft.studio.application.operation.extension.participant.configuration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.bonitasoft.studio.common.FragmentTypes;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.dependencies.DependentArtifactCollector;
import org.bonitasoft.studio.dependencies.DependentArtifactCollectorRegistry;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.diagram.custom.repository.ProcessConfigurationFileStore;
import org.bonitasoft.studio.diagram.custom.repository.ProcessConfigurationRepositoryStore;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.eclipse.e4.core.di.annotations.Creatable;

@Creatable
public class ProcessConfigurationCollector implements DependentArtifactCollector<Configuration> {

    private RepositoryAccessor repositoryAccessor;

    @Inject
    public ProcessConfigurationCollector(RepositoryAccessor repositoryAccessor) {
        this.repositoryAccessor = repositoryAccessor;
    }
    
    @PostConstruct
    void init(DependentArtifactCollectorRegistry registry) {
        registry.register(Configuration.class, this);
    }

    @Override
    public Collection<Configuration> findArtifactDependingOn(String jarName) {
        ProcessConfigurationRepositoryStore processConfigurationRepositoryStore = repositoryAccessor
                .getRepositoryStore(ProcessConfigurationRepositoryStore.class);
        DiagramRepositoryStore diagramRepositoryStore = repositoryAccessor
                .getRepositoryStore(DiagramRepositoryStore.class);
        if (!diagramRepositoryStore.hasComputedProcesses()) {
            diagramRepositoryStore.computeProcesses(AbstractRepository.NULL_PROGRESS_MONITOR);
        }
        return diagramRepositoryStore.getComputedProcesses()
                .stream()
                .flatMap(process -> getProcessConfigurations(process, processConfigurationRepositoryStore).stream())
                .filter(c -> containsJar(c, jarName))
                .collect(Collectors.toList());

    }

    private boolean containsJar(Configuration configuration, String jarName) {
        return configuration.getProcessDependencies().stream()
                .filter(fc -> fc.getId().equals(FragmentTypes.OTHER))
                .flatMap(fc -> fc.getFragments().stream())
                .anyMatch(f -> Objects.equals(jarName, f.getValue()));
    }

    private List<Configuration> getProcessConfigurations(AbstractProcess process,
            ProcessConfigurationRepositoryStore processConfigurationRepositoryStore) {
        List<Configuration> result = new ArrayList<>();
        ProcessConfigurationFileStore fileStore = processConfigurationRepositoryStore
                .getChild(ModelHelper.getEObjectID(process) + ".conf", false);
        if (fileStore != null) {
            try {
                result.add(fileStore.getContent());
            } catch (ReadFileStoreException e) {
                BonitaStudioLog.error(e);
            }
        }
        result.addAll(process.getConfigurations());
        return result;
    }

}
