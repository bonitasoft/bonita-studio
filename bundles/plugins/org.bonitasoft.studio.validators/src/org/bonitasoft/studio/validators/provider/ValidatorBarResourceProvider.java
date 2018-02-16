/**
 * Copyright (C) 2012-2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.validators.provider;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.bonitasoft.engine.bpm.bar.BarResource;
import org.bonitasoft.engine.bpm.bar.BusinessArchiveBuilder;
import org.bonitasoft.studio.common.FragmentTypes;
import org.bonitasoft.studio.common.ProjectUtil;
import org.bonitasoft.studio.common.extension.BARResourcesProvider;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.filestore.AbstractFileStore;
import org.bonitasoft.studio.common.repository.filestore.SourceFileStore;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.configuration.FragmentContainer;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.validators.descriptor.validator.ValidatorDescriptor;
import org.bonitasoft.studio.validators.repository.ValidatorDescriptorFileStore;
import org.bonitasoft.studio.validators.repository.ValidatorDescriptorRepositoryStore;
import org.bonitasoft.studio.validators.repository.ValidatorSourceRepositorySotre;
import org.eclipse.emf.ecore.EObject;

/**
 * @author Romain Bioteau
 */
public class ValidatorBarResourceProvider implements BARResourcesProvider {

    @Override
    public void addResourcesForConfiguration(final BusinessArchiveBuilder builder, final AbstractProcess process,
            final Configuration configuration,
            final Set<EObject> exludedObject) throws FileNotFoundException {
        if (configuration == null) {
            return;
        }
        final List<BarResource> resources = findAndCreateBarResourceForValidator(configuration);

        for (final BarResource barResource : resources) {
            builder.addExternalResource(barResource);
        }
    }

    protected List<BarResource> findAndCreateBarResourceForValidator(final Configuration configuration)
            throws FileNotFoundException {
        final List<BarResource> resources = new ArrayList<BarResource>();
        final ValidatorDescriptorRepositoryStore validatorDescStore = getRepositoryAccessor()
                .getRepositoryStore(ValidatorDescriptorRepositoryStore.class);
        final ValidatorSourceRepositorySotre validatorSourceStore = getRepositoryAccessor()
                .getRepositoryStore(ValidatorSourceRepositorySotre.class);
        final FragmentContainer validatorContainer = getContainer(configuration);
        if (validatorContainer != null) {
            for (final FragmentContainer validator : validatorContainer.getChildren()) {
                final String validatorId = validator.getId();
                final ValidatorDescriptorFileStore defFile = validatorDescStore.getChild(validatorId + "."
                        + ValidatorDescriptorRepositoryStore.VALIDATOR_EXT);
                if (defFile == null) {
                    throw new RuntimeException("Validator descriptor not found for id " + validatorId + "!");
                } else if (defFile.canBeShared()) {
                    final ValidatorDescriptor descriptor = defFile.getContent();
                    final SourceFileStore file = findSourceFileStoreForValidator(validatorSourceStore, validatorId,
                            descriptor);
                    try {
                        final byte[] content = createJarContentAsByteArray(file);
                        resources.add(new BarResource(
                                ValidatorSourceRepositorySotre.VALIDATOR_PATH_IN_BAR + descriptor.getClassName() + ".jar",
                                content));
                    } catch (final Exception e) {
                        BonitaStudioLog.error(e);
                    }
                }
            }
        }
        return resources;
    }

    protected SourceFileStore findSourceFileStoreForValidator(final ValidatorSourceRepositorySotre validatorSourceStore,
            final String validatorId,
            final ValidatorDescriptor descriptor) throws FileNotFoundException {
        final AbstractFileStore child = validatorSourceStore.getChild(descriptor.getClassName());
        if (child == null) {
            throw new FileNotFoundException("Validator class " + descriptor.getClassName()
                    + " not found for validator definition " + validatorId + "!");
        }
        if (!(child instanceof SourceFileStore)) {
            throw new RuntimeException("Invalid validator definition " + validatorId + "!");
        }
        return (SourceFileStore) child;
    }

    protected byte[] createJarContentAsByteArray(final SourceFileStore file)
            throws IOException, InvocationTargetException, InterruptedException,
            FileNotFoundException {
        final File tmpFile = File.createTempFile(file.getName(), ".jar", ProjectUtil.getBonitaStudioWorkFolder());
        tmpFile.deleteOnExit();
        file.exportAsJar(tmpFile.getAbsolutePath(), true);
        try (final FileInputStream fis = new FileInputStream(tmpFile);) {
            final byte[] content = new byte[fis.available()];
            fis.read(content);
            return content;
        } finally {
            tmpFile.delete();
        }
    }

    private FragmentContainer getContainer(final Configuration configuration) {
        for (final FragmentContainer container : configuration.getApplicationDependencies()) {
            if (FragmentTypes.VALIDATOR.equals(container.getId())) {
                return container;
            }
        }
        return null;
    }

    protected RepositoryAccessor getRepositoryAccessor() {
        final RepositoryAccessor repositoryAccessor = new RepositoryAccessor();
        repositoryAccessor.init();
        return repositoryAccessor;
    }

}
