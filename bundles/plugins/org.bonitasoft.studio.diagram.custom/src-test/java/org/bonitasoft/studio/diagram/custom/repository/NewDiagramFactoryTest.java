/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.diagram.custom.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.util.Map;

import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.bonitasoft.studio.model.process.Lane;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.SequenceFlow;
import org.bonitasoft.studio.model.process.StartEvent;
import org.bonitasoft.studio.model.process.Task;
import org.bonitasoft.studio.model.process.diagram.providers.ElementInitializers;
import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.preference.IPreferenceStore;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author Romain Bioteau
 */
@RunWith(MockitoJUnitRunner.class)
public class NewDiagramFactoryTest {

    @Mock
    private IRepository repository;

    private NewDiagramFactory newDiagramFactory;

    @Mock
    private DiagramRepositoryStore diagramRepositoryStore;

    @Mock
    private IPreferenceStore preferenceStore;

    @Mock
    private ElementInitializers elementInitalizers;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        newDiagramFactory = spy(new NewDiagramFactory(repository, preferenceStore));

        doReturn(null).when(newDiagramFactory).getConfigurationId(any(MainProcess.class));
        when(repository.getRepositoryStore(DiagramRepositoryStore.class)).thenReturn(diagramRepositoryStore);
        when(diagramRepositoryStore.getChild(anyString())).thenReturn(null);
        when(preferenceStore.getBoolean(BonitaPreferenceConstants.PREF_ENABLE_VALIDATION)).thenReturn(true);
    }

    /**
     * Test method for {@link org.bonitasoft.studio.diagram.custom.repository.NewDiagramFactory#create(org.eclipse.core.runtime.IProgressMonitor)}.
     */
    @Test
    public void should_createModel_create_a_basic_initial_model() throws Exception {
        final Map<Class<?>, EObject> domainElements = newDiagramFactory.createlModel(ProcessFactory.eINSTANCE, "",
                elementInitalizers,
                "7.5.0-001",
                Repository.NULL_PROGRESS_MONITOR);
        assertThat(domainElements).containsKey(MainProcess.class);
        assertThat(domainElements).containsKey(Pool.class);
        assertThat(domainElements).containsKey(Lane.class);
        assertThat(domainElements).containsKey(StartEvent.class);
        assertThat(domainElements).containsKey(Task.class);
        assertThat(domainElements).containsKey(SequenceFlow.class);
    }

}
