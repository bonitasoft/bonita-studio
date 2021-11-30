package org.bonitasoft.studio.document.configuration;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.configuration.ConfigurationFactory;
import org.bonitasoft.studio.model.configuration.Resource;
import org.bonitasoft.studio.model.process.AdditionalResource;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.junit.Before;
import org.junit.Test;

public class AdditionalResourcesConfigurationSyncronizerTest {

    private static final String EXISTING_RESOURCE_BAR_PATH = "toto.txt";
    private static final String NEW_RESOURCE_BAR_PATH = "titi.txt";

    AdditionalResourcesConfigurationSyncronizer syncronizer;
    Configuration configuration;
    EditingDomain editingDomain;
    CompoundCommand cc;

    @Before
    public void init() {
        syncronizer = spy(new AdditionalResourcesConfigurationSyncronizer());
        configuration = ConfigurationFactory.eINSTANCE.createConfiguration();

        Resource existingResource = ConfigurationFactory.eINSTANCE.createResource();
        existingResource.setBarPath(EXISTING_RESOURCE_BAR_PATH);
        configuration.getAdditionalResources().add(existingResource);

        editingDomain = mock(EditingDomain.class);
        cc = new CompoundCommand();
    }

    @Test
    public void should_synchronize_additional_resources() {
        AdditionalResource newResource = ProcessFactory.eINSTANCE.createAdditionalResource();
        newResource.setName(NEW_RESOURCE_BAR_PATH);

        Pool pool = ProcessFactory.eINSTANCE.createPool();
        pool.getAdditionalResources().add(newResource);

        syncronizer.synchronize(configuration, pool, cc, editingDomain);
        verify(syncronizer, times(1)).createAddCommand(any(), any(), any());
        verify(syncronizer, times(1)).createRemoveCommand(any(), any(), any());
    }

}
