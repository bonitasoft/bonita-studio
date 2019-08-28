/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.connector.model.implementation;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.repository.filestore.EMFFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author Romain Bioteau
 * 
 */
@RunWith(MockitoJUnitRunner.class)
public class AbstractConnectorImplRepositoryStoreTest {

    private AbstractConnectorImplRepositoryStore<? extends EMFFileStore> implRepositoryStore;

    @Mock
    private IRepositoryFileStore fStore;

    @Mock
    private IRepositoryStore<? extends IRepositoryFileStore> dependencyRepositoryStore;

    @Mock
    private IRepositoryStore<? extends IRepositoryFileStore> sourceRepositoryStore;

    @Mock
    private IRepositoryFileStore sourceFileStore;

    @Mock
    private IRepositoryFileStore depFileStore;

    private ConnectorImplementation connectorImpl;

    /**
     * @throws java.lang.Exception
     */
    @SuppressWarnings("unchecked")
    @Before
    public void setUp() throws Exception {
        implRepositoryStore = mock(AbstractConnectorImplRepositoryStore.class);
        doAnswer(Answers.CALLS_REAL_METHODS.get()).when(implRepositoryStore)
                .cleanJarDependency(any(IRepositoryFileStore.class), any(IRepositoryStore.class), any(IRepositoryStore.class));

        connectorImpl = ConnectorImplementationFactory.eINSTANCE.createConnectorImplementation();
        connectorImpl.setDefinitionId("def");
        connectorImpl.setDefinitionVersion("1.0");
        connectorImpl.setImplementationClassname("org.bonita.MyImpl");
        connectorImpl.setImplementationId("impl");
        connectorImpl.setImplementationVersion("1.0");
        JarDependencies deps = ConnectorImplementationFactory.eINSTANCE.createJarDependencies();
        String jarName = NamingUtils.toConnectorImplementationFilename(connectorImpl.getImplementationId(), connectorImpl.getImplementationVersion(), false)
                + ".jar";
        deps.getJarDependency().add(
                jarName);
        connectorImpl.setJarDependencies(deps);

        doReturn(connectorImpl).when(fStore).getContent();

        doReturn(depFileStore).when(dependencyRepositoryStore).getChild(jarName);
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void should_cleanJarDependency_remove_jar_from_dependencies_if_impl_has_sources() throws Exception {
        doReturn(sourceFileStore).when(sourceRepositoryStore).getChild(connectorImpl.getImplementationClassname());
        implRepositoryStore.cleanJarDependency(fStore, dependencyRepositoryStore, sourceRepositoryStore);
        verify(depFileStore).delete();
    }

    @Test
    public void should_cleanJarDependency_not_remove_jar_from_dependencies_if_impl_has_no_sources() throws Exception {
        doReturn(null).when(sourceRepositoryStore).getChild(connectorImpl.getImplementationClassname());
        implRepositoryStore.cleanJarDependency(fStore, dependencyRepositoryStore, sourceRepositoryStore);
        verifyZeroInteractions(depFileStore);
    }

}
