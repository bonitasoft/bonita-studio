package org.bonitasoft.studio.application.ui.control.model;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RepositoryModel {

    private List<RepositoryStore> stores;
    private String name;

    public RepositoryModel(String name, List<RepositoryStore> stores) {
        this.name = name;
        this.stores = stores;
    }

    public List<RepositoryStore> getStores() {
        return stores;
    }

    public String getName() {
        return name;
    }

    public Collection<Artifact> getArtifacts() {
        return stores.stream().map(RepositoryStore::getArtifacts)
                .flatMap(Collection::stream)
                .flatMap(artifact -> artifact instanceof ProcessArtifact ? Stream.concat(Stream.of(artifact),((ProcessArtifact) artifact).getVersions().stream()) : Stream.of(artifact))
                .collect(Collectors.toList());
    }

}
