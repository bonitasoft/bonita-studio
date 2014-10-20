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
package org.bonitasoft.studio.contract.ui.property.constraint.edit.editor;

import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.HashSet;

import org.bonitasoft.studio.contract.AbstractSWTTestCase;
import org.bonitasoft.studio.contract.core.constraint.ConstraintInputIndexer;
import org.bonitasoft.studio.model.process.ContractConstraint;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.assertions.ContractConstraintAssert;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


/**
 * @author Romain Bioteau
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class UpdateInputReferenceListenerTest extends AbstractSWTTestCase {

    private UpdateInputReferenceListener updateInputReferenceListener;

    @Mock
    private ConstraintInputIndexer job;
    @Mock
    private IJobChangeEvent event;
    private ContractConstraint constraint;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        createDisplayAndRealm();
        when(job.getReferencedInputs()).thenReturn(new HashSet<String>(Arrays.asList("name")));
        when(event.getJob()).thenReturn(job);
        constraint = ProcessFactory.eINSTANCE.createContractConstraint();
        updateInputReferenceListener = new UpdateInputReferenceListener(constraint);
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        dispose();
    }

    @Test
    public void should_done_update_ObservableList_with_job_result() throws Exception {
        ContractConstraintAssert.assertThat(constraint).hasNoInputNames();
        updateInputReferenceListener.done(event);
        ContractConstraintAssert.assertThat(constraint).hasInputNames("name");
    }


}