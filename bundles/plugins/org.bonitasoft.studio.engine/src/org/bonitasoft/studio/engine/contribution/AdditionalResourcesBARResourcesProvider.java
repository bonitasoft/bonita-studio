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
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.bonitasoft.engine.bpm.bar.BarResource;
import org.bonitasoft.engine.bpm.bar.BusinessArchiveBuilder;
import org.bonitasoft.studio.common.extension.BARResourcesProvider;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.engine.EnginePlugin;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;

public class AdditionalResourcesBARResourcesProvider implements BARResourcesProvider {

    private static final String RESOURCES_FOLDER = "src/main/resources";
    private static final String COMMON_FOLDER = "_common";

    private IProject getProject() {
        return RepositoryManager.getInstance().getCurrentRepository().getProject();
    }

    @Override
    public IStatus addResourcesForConfiguration(BusinessArchiveBuilder builder,
            AbstractProcess process,
            Configuration configuration) throws Exception {
        MultiStatus status = new MultiStatus(EnginePlugin.PLUGIN_ID, 0, null, null);

        var resourcesFolder = getProject().getFolder(RESOURCES_FOLDER);

        var commonFolder = resourcesFolder.getFolder(COMMON_FOLDER);
        if (commonFolder.exists()) {
            addFolderInBarResources(builder, commonFolder.getLocation().toFile(), process, status);
        }

        var processFolder = resourcesFolder.getFolder(process.getName());
        if (processFolder.exists()) {
            addFolderInBarResources(builder, processFolder.getLocation().toFile(), process, status);
        }
        processFolder = resourcesFolder.getFolder(String.format("%s-%s", process.getName(), process.getVersion()));
        if (processFolder.exists()) {
            addFolderInBarResources(builder, processFolder.getLocation().toFile(), process, status);
        }

        return status;
    }

    private void addFolderInBarResources(BusinessArchiveBuilder builder, File folder, AbstractProcess process,
            MultiStatus status) {
        Path folderPath = folder.toPath();
        try {
            Files.walk(folderPath).forEach(resourcePath -> {
                try {
                    if (resourcePath.toFile().isFile()) {
                        Path barPath = folderPath.relativize(resourcePath);
                        builder.addExternalResource(new BarResource(barPath.toString(), Files.readAllBytes(resourcePath)));
                        BonitaStudioLog.debug(String.format("Resource '%s' added to %s--%s.bar.", resourcePath.toString(),
                                process.getName(), process.getVersion()), EnginePlugin.PLUGIN_ID);
                    }
                } catch (IOException e) {
                    status.add(ValidationStatus
                            .error(String.format("Failed to add resource '%s' in bar.", resourcePath.toString()), e));
                }
            });
        } catch (IOException e) {
            status.add(ValidationStatus
                    .error(String.format("Failed to iterate over resources folder '%s'.", folderPath.toString()), e));
        }
    }

}
