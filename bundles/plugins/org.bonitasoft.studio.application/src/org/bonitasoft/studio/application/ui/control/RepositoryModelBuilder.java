/**
 * Copyright (C) 2019 Bonitasoft S.A.
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
package org.bonitasoft.studio.application.ui.control;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.bonitasoft.studio.actors.repository.OrganizationFileStore;
import org.bonitasoft.studio.application.ui.control.model.Artifact;
import org.bonitasoft.studio.application.ui.control.model.BuildableArtifact;
import org.bonitasoft.studio.application.ui.control.model.BusinessObjectModelArtifact;
import org.bonitasoft.studio.application.ui.control.model.OrganizationArtifact;
import org.bonitasoft.studio.application.ui.control.model.ProcessArtifact;
import org.bonitasoft.studio.application.ui.control.model.RepositoryModel;
import org.bonitasoft.studio.application.ui.control.model.RepositoryStore;
import org.bonitasoft.studio.application.ui.control.model.TenantArtifact;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelFileStore;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.model.IDeployable;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.common.repository.model.ITenantResource;
import org.bonitasoft.studio.designer.core.repository.WebPageFileStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.Pool;
import org.eclipse.core.runtime.IStatus;

public class RepositoryModelBuilder {

    public RepositoryModel create(RepositoryAccessor repositoryAccessor) {
        AbstractRepository currentRepository = repositoryAccessor.getCurrentRepository();
        currentRepository.getAllStores();
        List<RepositoryStore> stores = createStore(currentRepository);
        return new RepositoryModel(currentRepository.getName(), stores);
    }

    private List<RepositoryStore> createStore(AbstractRepository repository) {
        return repository.getAllStores()
                .stream()
                .filter(repositoryStore -> repositoryStore.getChildren().stream()
                        .anyMatch(IDeployable.class::isInstance))
                .map(s -> fillStore(s))
                .collect(Collectors.toList());
    }

    private RepositoryStore fillStore(IRepositoryStore<? extends IRepositoryFileStore> repositoryStore) {
        RepositoryStore store = new RepositoryStore(repositoryStore);
        repositoryStore.getChildren()
                .stream()
                .map(fStore -> createArtifact(store, fStore))
                .filter(Objects::nonNull)
                .forEach(store::add);
        return store;
    }

    private Artifact createArtifact(RepositoryStore parent, IRepositoryFileStore fStore) {
        if( fStore.validate().getSeverity() == IStatus.ERROR) {
            //Do not display invalid artifacts
            return null;
        }
        if (fStore instanceof OrganizationFileStore) {
            return new OrganizationArtifact(parent, fStore);
        } else if (fStore instanceof BusinessObjectModelFileStore) {
            return new BusinessObjectModelArtifact(parent, fStore);
        } else if (fStore instanceof ITenantResource) {
            return new TenantArtifact(parent, fStore);
        } else if (fStore instanceof DiagramFileStore) {
            createProcessArtifacts(parent, (DiagramFileStore) fStore);
            return null;
        } else if (fStore instanceof WebPageFileStore) {
            if (!Objects.equals(WebPageFileStore.FORM_TYPE, ((WebPageFileStore) fStore).getType())) {
                return new BuildableArtifact(parent, fStore);
            }
            return null;
        } else {
            return new BuildableArtifact(parent, fStore);
        }
    }

    public void createProcessArtifacts(RepositoryStore parent, DiagramFileStore fStore) {
        final List<Artifact> processes = parent.getArtifacts();
        for (AbstractProcess procModel : fStore.getProcesses()) {
            ProcessArtifact defaultProcess = new ProcessArtifact(parent, procModel.getName());
            ProcessArtifact process = (ProcessArtifact) processes.stream().filter(defaultProcess::equals).findFirst()
                    .orElse(defaultProcess);
            if (!processes.contains(process)) {
                processes.add(process);
            }
            process.addVersion((Pool) procModel, fStore);
        }
    }
}
