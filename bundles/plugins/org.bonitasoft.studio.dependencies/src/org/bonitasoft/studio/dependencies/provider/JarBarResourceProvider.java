/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.dependencies.provider;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Objects;

import org.bonitasoft.engine.bpm.bar.BarResource;
import org.bonitasoft.engine.bpm.bar.BusinessArchiveBuilder;
import org.bonitasoft.studio.common.FragmentTypes;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.extension.BARResourcesProvider;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.dependencies.repository.DependencyFileStore;
import org.bonitasoft.studio.dependencies.repository.DependencyRepositoryStore;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.configuration.Fragment;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

public class JarBarResourceProvider implements BARResourcesProvider {

    @Override
    public IStatus addResourcesForConfiguration(final BusinessArchiveBuilder builder, final AbstractProcess process,
            final Configuration configuration) {
        if (configuration == null) {
            return ValidationStatus.error("No configuration selected.");
        }
        final DependencyRepositoryStore store = RepositoryManager.getInstance()
                .getRepositoryStore(DependencyRepositoryStore.class);
        configuration.getProcessDependencies().stream()
                .flatMap(container -> ModelHelper.getAllElementOfTypeIn(container, Fragment.class).stream())
                .filter(fragment -> fragment.getType().equals(FragmentTypes.JAR) && fragment.isExported())
                .map(fragment -> store.getChild(fragment.getValue(), true))
                .filter(Objects::nonNull)
                .map(DependencyFileStore::getFile)
                .forEach(file -> {
                    try {
                        builder.addClasspathResource(
                                new BarResource(file.getName(), Files.readAllBytes(file.toPath())));
                    } catch (IOException e) {
                        BonitaStudioLog.error(
                                String.format("Failed to add file '%s' to business archive", file.getAbsolutePath()),
                                e);
                    }
                });
        return Status.OK_STATUS;
    }

}
