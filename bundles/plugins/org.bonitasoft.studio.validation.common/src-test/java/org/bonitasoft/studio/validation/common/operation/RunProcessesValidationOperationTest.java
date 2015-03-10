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
package org.bonitasoft.studio.validation.common.operation;

import static org.bonitasoft.studio.assertions.StatusAssert.assertThat;
import static org.bonitasoft.studio.model.process.builders.PoolBuilder.aPool;
import static org.mockito.Matchers.notNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author Romain Bioteau
 */
@RunWith(MockitoJUnitRunner.class)
public class RunProcessesValidationOperationTest {

    private RunProcessesValidationOperation runProcessesValidationOperation;
    @Mock
    private IProgressMonitor monitor;
    @Mock
    private BatchValidationOperation validationOperation;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        runProcessesValidationOperation = spy(new RunProcessesValidationOperation(validationOperation));
    }

    @Test
    public void should_run_Batch_validation_operation() throws Exception {
        doReturn(ValidationStatus.ok()).when(validationOperation).getResult();

        final Resource eResource = new ResourceImpl();
        final Pool pool = aPool().build();
        final MainProcess mainProcess = ProcessFactory.eINSTANCE.createMainProcess();
        mainProcess.getElements().add(pool);
        eResource.getContents().add(mainProcess);

        final Diagram diagram = aDiagram(mainProcess);
        eResource.getContents().add(diagram);
        doReturn(null).when(runProcessesValidationOperation).editingDomain(notNull(Resource.class));

        runProcessesValidationOperation.addProcess(pool).run(monitor);

        assertThat(runProcessesValidationOperation.getStatus()).isOK();
        verify(validationOperation).addDiagram(diagram);
        verify(validationOperation).run(monitor);
    }

    private Diagram aDiagram(final MainProcess mainProcess) {
        final Diagram diagram = NotationFactory.eINSTANCE.createDiagram();
        diagram.setElement(mainProcess);
        return diagram;
    }
}
