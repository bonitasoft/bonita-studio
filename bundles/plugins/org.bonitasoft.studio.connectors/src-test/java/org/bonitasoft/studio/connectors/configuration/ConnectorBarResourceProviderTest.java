/**
 * Copyright (C) 2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.connectors.configuration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.model.configuration.builders.ConfigurationBuilder.aConfiguration;
import static org.bonitasoft.studio.model.configuration.builders.DefinitionMappingBuilder.aDefinitionMapping;
import static org.bonitasoft.studio.model.configuration.builders.FragmentBuilder.aFragment;
import static org.bonitasoft.studio.model.configuration.builders.FragmentContainerBuilder.aFragmentContainer;
import static org.bonitasoft.studio.model.process.builders.PoolBuilder.aPool;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.bonitasoft.engine.bpm.bar.BarResource;
import org.bonitasoft.engine.bpm.bar.BusinessArchiveBuilder;
import org.bonitasoft.studio.common.FragmentTypes;
import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.filestore.PackageFileStore;
import org.bonitasoft.studio.connector.model.implementation.ConnectorImplementation;
import org.bonitasoft.studio.connector.model.implementation.ConnectorImplementationFactory;
import org.bonitasoft.studio.connectors.repository.ConnectorImplFileStore;
import org.bonitasoft.studio.connectors.repository.ConnectorImplRepositoryStore;
import org.bonitasoft.studio.connectors.repository.ConnectorSourceRepositoryStore;
import org.bonitasoft.studio.dependencies.repository.DependencyFileStore;
import org.bonitasoft.studio.dependencies.repository.DependencyRepositoryStore;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author aurelie
 */
@RunWith(MockitoJUnitRunner.class)
public class ConnectorBarResourceProviderTest {

    @Mock
    private ConnectorImplRepositoryStore connectorImplStore;

    @Mock
    private DependencyRepositoryStore depStore;

    @Mock
    private ConnectorSourceRepositoryStore connectorSourceStore;

    @Mock
    private RepositoryAccessor repositoryAccessor;

    @Mock
    private ConnectorImplFileStore myConnectorImplFileStore;

    @Mock
    private PackageFileStore connectorSourceFileStore;

    @Rule
    public TemporaryFolder fileRule = new TemporaryFolder();

    @Mock
    private DependencyFileStore libFileStore;

    @Before
    public void initRepositoryAccessor() throws Exception {
        when(repositoryAccessor.getRepositoryStore(ConnectorImplRepositoryStore.class)).thenReturn(connectorImplStore);
        when(repositoryAccessor.getRepositoryStore(DependencyRepositoryStore.class)).thenReturn(depStore);
        when(repositoryAccessor.getRepositoryStore(ConnectorSourceRepositoryStore.class)).thenReturn(connectorSourceStore);
    }

    @Test
    public void should_add_implementation_jar_in_bar_resource_from_source() throws Exception {
        final ConnectorBarResourceProvider provider = spy(new ConnectorBarResourceProvider(repositoryAccessor));
        doReturn(fileRule.newFile("myConnectorImpl-1.0.0.jar")).when(provider).exportJar(anyString(), any(PackageFileStore.class));
        when(myConnectorImplFileStore.getContent()).thenReturn(aConnectorImplementation("myConnectorDef", "1.0.0", "myConnectorImpl", "1.0.0",
                "org.test.MyConnector"));
        when(myConnectorImplFileStore.canBeShared()).thenReturn(true);
        when(connectorSourceStore.getChild("org.test")).thenReturn(connectorSourceFileStore);
        when(connectorImplStore.getImplementationFileStore("myConnectorImpl", "1.0.0")).thenReturn(myConnectorImplFileStore);
        when(connectorImplStore.getImplementation("myConnectorImpl", "1.0.0")).thenReturn(
                aConnectorImplementation("myConnectorDef", "1.0.0", "myConnectorImpl", "1.0.0",
                        "org.test.MyConnector"));
        final BusinessArchiveBuilder builder = mock(BusinessArchiveBuilder.class);
        provider.addResourcesForConfiguration(builder, aPool().build(),
                connectorConfiguration("myConnectorDef", "1.0.0", "myConnectorImpl", "1.0.0"));

        final ArgumentCaptor<BarResource> barResourceCaptor = ArgumentCaptor.forClass(BarResource.class);
        verify(builder).addClasspathResource(barResourceCaptor.capture());

        assertThat(barResourceCaptor.getValue().getName()).isEqualTo("myConnectorImpl-1.0.0.jar");
    }

    @Test
    public void should_add_implementation_jar_in_bar_resource_from_dependency_store() throws Exception {
        final ConnectorBarResourceProvider provider = spy(new ConnectorBarResourceProvider(repositoryAccessor));
        doReturn(fileRule.newFile("myConnectorImpl-1.0.0.jar")).when(provider).exportJar(anyString(), any(PackageFileStore.class));

        when(myConnectorImplFileStore.getContent()).thenReturn(aConnectorImplementation("myConnectorDef", "1.0.0", "myConnectorImpl", "1.0.0",
                "org.test.MyConnector"));
        when(myConnectorImplFileStore.canBeShared()).thenReturn(true);
        when(libFileStore.getName()).thenReturn("myConnectorImpl-1.0.0.jar");
        when(depStore.getChild("myConnectorImpl-1.0.0.jar")).thenReturn(libFileStore);
        when(connectorImplStore.getImplementationFileStore("myConnectorImpl", "1.0.0")).thenReturn(myConnectorImplFileStore);

        final BusinessArchiveBuilder builder = mock(BusinessArchiveBuilder.class);
        provider.addResourcesForConfiguration(builder, aPool().build(),
                connectorConfiguration("myConnectorDef", "1.0.0", "myConnectorImpl", "1.0.0"));

        final ArgumentCaptor<BarResource> barResourceCaptor = ArgumentCaptor.forClass(BarResource.class);
        verify(builder).addClasspathResource(barResourceCaptor.capture());

        assertThat(barResourceCaptor.getValue().getName()).isEqualTo("myConnectorImpl-1.0.0.jar");
    }

    @Test
    public void should_add_implementation_jar__name_in_dependency_of_implemetation__bar_resource() throws Exception {
        final ConnectorBarResourceProvider provider = new ConnectorBarResourceProvider(repositoryAccessor);

        when(myConnectorImplFileStore.getContent()).thenReturn(aConnectorImplementation("myConnectorDef", "1.0.0", "myConnectorImpl", "1.0.0",
                "org.test.MyConnector"));
        when(myConnectorImplFileStore.canBeShared()).thenReturn(true);
        when(libFileStore.getName()).thenReturn("myConnectorImpl-1.0.0.jar");
        when(depStore.getChild("myConnectorImpl-1.0.0.jar")).thenReturn(libFileStore);
        when(connectorImplStore.getImplementationFileStore("myConnectorImpl", "1.0.0")).thenReturn(myConnectorImplFileStore);

        final BusinessArchiveBuilder builder = mock(BusinessArchiveBuilder.class);
        provider.addResourcesForConfiguration(builder, aPool().build(),
                connectorConfiguration("myConnectorDef", "1.0.0", "myConnectorImpl", "1.0.0"));

        final ConnectorImplementation implemetationWithSelfDep = provider.implemetationWithSelfDep(myConnectorImplFileStore,
                connectorConfiguration("myConnectorDef", "1.0.0", "myConnectorImpl", "1.0.0"));

        final ArgumentCaptor<BarResource> barResourceCaptor = ArgumentCaptor.forClass(BarResource.class);
        verify(builder).addConnectorImplementation(barResourceCaptor.capture());

        assertThat(implemetationWithSelfDep.getJarDependencies().getJarDependency()).contains("myConnectorImpl-1.0.0.jar");
    }

    private ConnectorImplementation aConnectorImplementation(final String definitionId,
            final String definitionVersion,
            final String implementationId,
            final String implementationVersion,
            final String className) {
        final ConnectorImplementation connectorImplementation = ConnectorImplementationFactory.eINSTANCE.createConnectorImplementation();
        connectorImplementation.setDefinitionId(definitionId);
        connectorImplementation.setDefinitionId(definitionVersion);
        connectorImplementation.setImplementationId(implementationId);
        connectorImplementation.setImplementationVersion(implementationVersion);
        connectorImplementation.setImplementationClassname(className);
        return connectorImplementation;
    }

    private Configuration connectorConfiguration(final String defId,
            final String defVersion,
            final String implId,
            final String implVersion) {
        return aConfiguration()
                .havingDefinitionMappings(aDefinitionMapping()
                        .withDefinitionId(defId)
                        .withDefinitionVersion(defVersion)
                        .withImplementationId(implId)
                        .withImplementationVersion(implVersion)
                        .withType(FragmentTypes.CONNECTOR))
                .havingProcessDependencies(
                        aFragmentContainer(FragmentTypes.CONNECTOR)
                                .havingChildren(
                                        aFragmentContainer(NamingUtils.toConnectorImplementationFilename(implId, implVersion, false)).havingFragments(
                                                aFragment().withValue(NamingUtils.toConnectorImplementationFilename(implId, implVersion, false) + ".jar")
                                                        .exported()))).build();
    }
}
