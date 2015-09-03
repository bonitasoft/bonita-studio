/**
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.validators.provider;

import static org.assertj.core.api.Assertions.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;

import org.assertj.core.api.Assertions;
import org.bonitasoft.engine.bpm.bar.BarResource;
import org.bonitasoft.engine.bpm.bar.BusinessArchiveBuilder;
import org.bonitasoft.studio.common.FragmentTypes;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.filestore.PackageFileStore;
import org.bonitasoft.studio.common.repository.filestore.SourceFileStore;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.configuration.builders.ConfigurationBuilder;
import org.bonitasoft.studio.model.configuration.builders.FragmentContainerBuilder;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.validators.descriptor.validator.ValidatorDescriptor;
import org.bonitasoft.studio.validators.repository.ValidatorDescriptorFileStore;
import org.bonitasoft.studio.validators.repository.ValidatorDescriptorRepositoryStore;
import org.bonitasoft.studio.validators.repository.ValidatorSourceRepositorySotre;
import org.eclipse.emf.ecore.EObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ValidatorBarResourceProviderTest {

    private ValidatorBarResourceProvider provider;
    @Mock
    private ValidatorDescriptorRepositoryStore validatorDescStore;
    @Mock
    private ValidatorSourceRepositorySotre validatorSourceStore;
    @Mock
    private RepositoryAccessor repoAccessor;
    @Mock
    private BusinessArchiveBuilder builder;
    @Mock
    private ValidatorDescriptorFileStore defFile;
    @Mock
    private ValidatorDescriptor descriptor;
    @Mock
    private PackageFileStore packageFileStore;
    @Mock
    private SourceFileStore sourceFileStore;

    @Before
    public void setUp() throws Exception {
        provider = spy(new ValidatorBarResourceProvider());
        doReturn(repoAccessor).when(provider).getRepositoryAccessor();
        doReturn(validatorDescStore).when(repoAccessor).getRepositoryStore(ValidatorDescriptorRepositoryStore.class);
        doReturn(validatorSourceStore).when(repoAccessor).getRepositoryStore(ValidatorSourceRepositorySotre.class);
    }

    @Test
    public void testInvalidValidatorDefinitionThrowExplicitException() {
        final String validatorId = "org.bonitasoft.invalid.";
        final String className = "org.bonitasoft.invalid.";
        doReturn(defFile).when(validatorDescStore).getChild(validatorId + "." + ValidatorDescriptorRepositoryStore.VALIDATOR_EXT);
        doReturn(true).when(defFile).canBeShared();
        doReturn(descriptor).when(defFile).getContent();
        doReturn(className).when(descriptor).getClassName();
        doReturn(packageFileStore).when(validatorSourceStore).getChild(className);
        final FragmentContainerBuilder dependencies = FragmentContainerBuilder.aFragmentContainer(FragmentTypes.VALIDATOR).
                havingChildren(FragmentContainerBuilder.aFragmentContainer(validatorId));
        final Configuration configuration = ConfigurationBuilder.aConfiguration().havingApplicationDependencies(dependencies).build();
        try {
            provider.addResourcesForConfiguration(builder, (AbstractProcess) null, configuration, Collections.<EObject> emptySet());
        } catch (final RuntimeException e) {
            Assertions.assertThat(e.getMessage()).isEqualTo("Invalid validator definition " + validatorId + "!");
            return;
        } catch (final FileNotFoundException e) {
            e.printStackTrace();
        }
        fail("A clean RuntimeException should have been thrown.");
    }

    @Test
    public void testNotExistingValidatorDefinitionThrowExplicitException() {
        final String validatorId = "org.bonitasoft.invalid.";
        final String className = "org.bonitasoft.invalid.";
        doReturn(defFile).when(validatorDescStore).getChild(validatorId + "." + ValidatorDescriptorRepositoryStore.VALIDATOR_EXT);
        doReturn(true).when(defFile).canBeShared();
        doReturn(descriptor).when(defFile).getContent();
        doReturn(className).when(descriptor).getClassName();
        doReturn(null).when(validatorSourceStore).getChild(className);
        final FragmentContainerBuilder dependencies = FragmentContainerBuilder.aFragmentContainer(FragmentTypes.VALIDATOR).
                havingChildren(FragmentContainerBuilder.aFragmentContainer(validatorId));
        final Configuration configuration = ConfigurationBuilder.aConfiguration().havingApplicationDependencies(dependencies).build();
        try {
            provider.addResourcesForConfiguration(builder, (AbstractProcess) null, configuration, Collections.<EObject> emptySet());
        } catch (final RuntimeException e) {
            e.printStackTrace();
        } catch (final FileNotFoundException e) {
            Assertions.assertThat(e.getMessage()).isEqualTo(
                    "Validator class " + className + " not found for validator definition " + validatorId + "!");
            return;
        }
        fail("A clean RuntimeException should have been thrown.");
    }

    @Test
    public void testValidatorDefinitionAdded() throws InvocationTargetException, FileNotFoundException, IOException, InterruptedException {
        final String validatorId = "org.bonitasoft.valid.Test";
        final String className = "org.bonitasoft.valid.Test";
        doReturn(defFile).when(validatorDescStore).getChild(validatorId + "." + ValidatorDescriptorRepositoryStore.VALIDATOR_EXT);
        doReturn(true).when(defFile).canBeShared();
        doReturn(descriptor).when(defFile).getContent();
        doReturn(className).when(descriptor).getClassName();
        doReturn(sourceFileStore).when(validatorSourceStore).getChild(className);
        doReturn("test".getBytes()).when(provider).createJarContentAsByteArray(sourceFileStore);
        final FragmentContainerBuilder dependencies = FragmentContainerBuilder.aFragmentContainer(FragmentTypes.VALIDATOR).
                havingChildren(FragmentContainerBuilder.aFragmentContainer(validatorId));
        final Configuration configuration = ConfigurationBuilder.aConfiguration().havingApplicationDependencies(dependencies).build();
        provider.addResourcesForConfiguration(builder, (AbstractProcess) null, configuration, Collections.<EObject> emptySet());

        verify(builder).addExternalResource(any(BarResource.class));
    }

}
