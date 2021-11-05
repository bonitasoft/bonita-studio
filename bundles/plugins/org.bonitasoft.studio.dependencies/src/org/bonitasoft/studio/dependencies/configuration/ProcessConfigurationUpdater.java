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

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.bonitasoft.studio.common.emf.tools.EMFModelUpdater;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.eclipse.e4.core.di.annotations.Creatable;
import org.eclipse.emf.ecore.resource.Resource;

@Creatable
public class ProcessConfigurationUpdater {

    public Collection<Resource> update(ProcessConfigurationChange change) {
        Set<Resource> modifiedResources = new HashSet<>();
        for (var configuration : change.getConfigurations()) {
            var modelUpdater = new EMFModelUpdater<Configuration>().from(configuration);
            change.apply(modelUpdater.getWorkingCopy());
            Resource resource = configuration.eResource();
            modelUpdater.update();
            if (resource != null) {
                modifiedResources.add(resource);
            }
        }
        return modifiedResources;
    }

}
