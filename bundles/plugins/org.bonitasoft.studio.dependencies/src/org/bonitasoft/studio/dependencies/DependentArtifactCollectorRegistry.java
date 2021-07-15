/**
 * Copyright (C) 2021 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.dependencies;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Singleton;

import org.eclipse.e4.core.di.annotations.Creatable;

@Creatable
@Singleton
public class DependentArtifactCollectorRegistry {
    
    private Map<Class<?>, DependentArtifactCollector<?>> registry = new HashMap<>();

    public <T> void register(Class<T> artifactType, DependentArtifactCollector<T> collector) {
        registry.put(artifactType, collector);
    }
    
    @SuppressWarnings("unchecked")
    public <T> DependentArtifactCollector<T> get(Class<T> artifactType){
        return (DependentArtifactCollector<T>) registry.get(artifactType);
    }

}
