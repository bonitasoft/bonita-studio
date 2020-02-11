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
package org.bonitasoft.studio.engine.contribution;

import java.io.File;
import java.io.FileNotFoundException;

import org.bonitasoft.engine.bpm.bar.BarResource;
import org.bonitasoft.engine.bpm.bar.BusinessArchiveBuilder;
import org.bonitasoft.studio.common.extension.BARResourcesProvider;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.configuration.Resource;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.eclipse.core.resources.IProject;

import com.google.common.io.Files;


public class AdditionalResourcesBARResourcesProvider implements BARResourcesProvider {

    private static final String CONF_PREFIX = "conf/";

    @Override
    public void addResourcesForConfiguration(BusinessArchiveBuilder builder, 
            AbstractProcess process,
            Configuration configuration) throws Exception {
       for(Resource resource : configuration.getAdditionalResources()) {
           addResourceInBar(builder, resource);
       }
    }

    private void addResourceInBar(BusinessArchiveBuilder builder, Resource resource) throws Exception {
        IProject project = RepositoryManager.getInstance().getCurrentRepository().getProject();
        if(resource.getProjectPath() == null || resource.getProjectPath().isEmpty()) {
            throw new Exception("projectPath cannot be null for an additional resource.");
        }
        File file = project.getFile(resource.getProjectPath()).getLocation().toFile();
        if(!file.exists()) {
            throw new FileNotFoundException(file.getAbsolutePath());
        }
        if(!file.isFile()) {
            throw new Exception(file.getAbsolutePath() + "is not a file.");
        }
        String barPath = resource.getBarPath();
        if(barPath == null || barPath.isEmpty()) {
            throw new Exception("barPath cannot be null or empty for an additional resource.");
        }
        if(barPath.trim().startsWith("/")) {
            barPath = barPath.trim().substring(1);
        }
        //Avoid resource name conflict using conf/ folder in bar.
        builder.addExternalResource(new BarResource(CONF_PREFIX + barPath, Files.toByteArray(file)));
    }

}
