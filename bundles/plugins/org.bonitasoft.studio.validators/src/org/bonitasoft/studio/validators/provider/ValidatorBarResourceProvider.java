/**
 * Copyright (C) 2012-2014 Bonitasoft S.A.
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
import org.bonitasoft.studio.common.repository.RepositoryManager;
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
    public void addResourcesForConfiguration(final BusinessArchiveBuilder builder, final AbstractProcess process, final Configuration configuration,
            final Set<EObject> exludedObject) {
        if (configuration == null) {
            return;
        }
        final List<BarResource> resources = new ArrayList<BarResource>();
        final ValidatorDescriptorRepositoryStore validatorDescStore = RepositoryManager.getInstance().getRepositoryStore(
                ValidatorDescriptorRepositoryStore.class);
        final ValidatorSourceRepositorySotre validatorSourceStore = RepositoryManager.getInstance().getRepositoryStore(ValidatorSourceRepositorySotre.class);
        final FragmentContainer validatorContainer = getContainer(configuration);
        if (validatorContainer != null) {
            for (final FragmentContainer validator : validatorContainer.getChildren()) {

                final String validatorId = validator.getId();
                final ValidatorDescriptorFileStore defFile = validatorDescStore.getChild(validatorId + "."
                        + ValidatorDescriptorRepositoryStore.VALIDATOR_EXT);
                if (defFile == null) {
                    throw new RuntimeException("Validator descriptor not found for id " + validatorId + "!");
                }
                if (defFile != null && defFile.canBeShared()) {
                    final ValidatorDescriptor descriptor = defFile.getContent();
                    final SourceFileStore file = (SourceFileStore) validatorSourceStore.getChild(descriptor.getClassName());
                    if (file == null) {
                        throw new RuntimeException("Validator class " + descriptor.getClassName() + " not found for validator definition " + validatorId + "!");
                    }

                    try {
                        final byte[] content = createJarContentAsByteArray(file);
                        resources.add(new BarResource(ValidatorSourceRepositorySotre.VALIDATOR_PATH_IN_BAR + descriptor.getClassName() + ".jar", content));
                    } catch (final Exception e) {
                        BonitaStudioLog.error(e);
                    }
                }
            }
        }

        for (final BarResource barResource : resources) {
            builder.addExternalResource(barResource);
        }
    }

    private byte[] createJarContentAsByteArray(final SourceFileStore file) throws IOException, InvocationTargetException, InterruptedException,
            FileNotFoundException {
        final File tmpFile = File.createTempFile(file.getName(), ".jar", ProjectUtil.getBonitaStudioWorkFolder());
        tmpFile.deleteOnExit();
        file.exportAsJar(tmpFile.getAbsolutePath(), true);
        final FileInputStream fis = new FileInputStream(tmpFile);
        tmpFile.delete();
        final byte[] content = new byte[fis.available()];
        fis.read(content);
        fis.close();
        return content;
    }

    private FragmentContainer getContainer(final Configuration configuration) {
        for (final FragmentContainer container : configuration.getApplicationDependencies()) {
            if (container.getId().equals(FragmentTypes.VALIDATOR)) {
                return container;
            }
        }
        return null;
    }

}
