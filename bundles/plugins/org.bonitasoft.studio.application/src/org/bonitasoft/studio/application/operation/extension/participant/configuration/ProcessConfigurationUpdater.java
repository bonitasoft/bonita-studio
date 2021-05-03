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

import java.io.IOException;
import java.util.Collections;

import org.bonitasoft.studio.common.emf.tools.EMFModelUpdater;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.eclipse.e4.core.di.annotations.Creatable;
import org.eclipse.emf.ecore.resource.Resource;

@Creatable
public class ProcessConfigurationUpdater {

    public void update(ProcessConfigurationChange change) {
        for (var configuration : change.getConfigurations()) {
            var modelUpdater = new EMFModelUpdater<Configuration>().from(configuration);
            change.apply(modelUpdater.getWorkingCopy());
            Resource resource = configuration.eResource();
            boolean saveResourceAfterUpdate = resource != null && !resource.isModified();
            modelUpdater.update();
            if (saveResourceAfterUpdate) {
                try {
                    resource.save(Collections.emptyMap());
                } catch (IOException e) {
                    BonitaStudioLog.error(e);
                }
            }
        }
    }

}
