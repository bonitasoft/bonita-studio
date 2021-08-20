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
                .flatMap(artifact -> artifact instanceof VersionedArtifact ? Stream.concat(Stream.of(artifact),((VersionedArtifact) artifact).getVersions().stream()) : Stream.of(artifact))
                .collect(Collectors.toList());
    }

}
