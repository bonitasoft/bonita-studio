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

import org.bonitasoft.bpm.model.process.AbstractProcess;
import org.bonitasoft.bpm.model.process.Pool;
import org.bonitasoft.studio.application.ui.control.model.Artifact;
import org.bonitasoft.studio.application.ui.control.model.BuildableArtifact;
import org.bonitasoft.studio.application.ui.control.model.BusinessObjectModelArtifact;
import org.bonitasoft.studio.application.ui.control.model.OrganizationArtifact;
import org.bonitasoft.studio.application.ui.control.model.ProcessArtifact;
import org.bonitasoft.studio.application.ui.control.model.RepositoryModel;
import org.bonitasoft.studio.application.ui.control.model.RepositoryStore;
import org.bonitasoft.studio.application.ui.control.model.TenantArtifact;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelFileStore;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.model.IDeployable;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.common.repository.model.ITenantResource;
import org.bonitasoft.studio.designer.core.repository.WebPageFileStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.identity.organization.repository.OrganizationFileStore;
import org.eclipse.core.runtime.IStatus;

public class RepositoryModelBuilder {

    public RepositoryModel create(RepositoryAccessor repositoryAccessor) {
        var currentRepository = repositoryAccessor.getCurrentRepository().orElse(null);
        if (currentRepository != null) {
            currentRepository.getAllStores();
            List<RepositoryStore> stores = createStore(currentRepository);
            return new RepositoryModel(currentRepository.getProjectId(), stores);
        }
        return null;
    }

    public RepositoryModel create(IRepository currentRepository,
            List<IRepositoryStore<? extends IRepositoryFileStore>> repositoryStores) {
        List<RepositoryStore> stores = repositoryStores.stream().map(this::fillStore).collect(Collectors.toList());
        return new RepositoryModel(currentRepository.getProjectId(), stores);
    }

    private List<RepositoryStore> createStore(IRepository repository) {
        return repository.getAllStores()
                .stream()
                .filter(repositoryStore -> repositoryStore.getChildren().stream()
                        .anyMatch(IDeployable.class::isInstance))
                .map(this::fillStore)
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

    protected Artifact createArtifact(RepositoryStore parent, IRepositoryFileStore fStore) {
        if (fStore.validate().getSeverity() == IStatus.ERROR) {
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
        for (AbstractProcess procModel : fStore.getProcesses(false)) {
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
