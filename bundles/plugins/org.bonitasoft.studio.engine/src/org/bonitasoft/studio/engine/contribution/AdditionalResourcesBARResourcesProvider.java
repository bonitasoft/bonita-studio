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

import org.bonitasoft.engine.bpm.bar.BarResource;
import org.bonitasoft.engine.bpm.bar.BusinessArchiveBuilder;
import org.bonitasoft.studio.common.extension.BARResourcesProvider;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.engine.EnginePlugin;
import org.bonitasoft.studio.engine.i18n.Messages;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.configuration.Resource;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;

import com.google.common.io.Files;


public class AdditionalResourcesBARResourcesProvider implements BARResourcesProvider {

    private static final String MISC_PREFIX = "misc/";

    @Override
    public IStatus addResourcesForConfiguration(BusinessArchiveBuilder builder, 
            AbstractProcess process,
            Configuration configuration) throws Exception {
       MultiStatus status = new MultiStatus(EnginePlugin.PLUGIN_ID, 0, null, null);
       for(Resource resource : configuration.getAdditionalResources()) {
           status.add(addResourceInBar(builder, resource, process));
       }
       return status;
    }

    private IStatus addResourceInBar(BusinessArchiveBuilder builder, Resource resource, AbstractProcess process) throws Exception {
        if(resource.getProjectPath() == null || resource.getProjectPath().isEmpty()) {
            return ValidationStatus.error(String.format(Messages.additionalResourceProjectPathNotSet,
                    process.getName(),
                    process.getVersion(),
                    resource.getBarPath()));
        }
        IProject project = RepositoryManager.getInstance().getCurrentRepository().getProject();
        File file = project.getFile(resource.getProjectPath()).getLocation().toFile();
        if(!file.exists()) {
            return ValidationStatus.error(String.format(Messages.additionalResourceFileNotFound,  
                    process.getName(),
                    process.getVersion(),
                    file.getAbsolutePath(), 
                    resource.getBarPath()));
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
        builder.addExternalResource(new BarResource(MISC_PREFIX + barPath, Files.toByteArray(file)));
        return Status.OK_STATUS;
    }

}
