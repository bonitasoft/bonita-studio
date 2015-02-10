/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.validation.test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.validation.constraints.connector.ConnectorExistenceConstraint;
import org.bonitasoft.studio.validation.constraints.process.AssignableConstraint;
import org.bonitasoft.studio.validation.constraints.process.InclusiveMergeGatewayConstraint;
import org.bonitasoft.studio.validation.constraints.process.XORGatewayConstraint;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.validation.model.IConstraintStatus;
import org.eclipse.ui.PlatformUI;
import org.junit.AfterClass;
import org.junit.Ignore;
import org.junit.Test;
/**
 * @author Romain Bioteau
 *
 */
public class TestValidationConstraints extends ValidationTestBase {

	@AfterClass
	public static void close(){
		PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().saveAllEditors(false);
		PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().closeAllEditors(false);
	}

	@Test
	public void testAssignableConstraint() throws Exception{
		final MainProcess diagram = getDiagramFromArchive("TestAssignableConstraint-1.0.bos","TestAssignableConstraint","1.0");
		final Pool processWithErrors = getProcess(diagram,"ConstraintFailure","1.0");
		IStatus[] status = getStatuses(batchValidator.validate(processWithErrors));
		List<IConstraintStatus> assignableStatus = getStatusForConstraint(status,AssignableConstraint.ID);
		assertFalse(assignableStatus.isEmpty());
		assertEquals(4, assignableStatus.size());
		for(final IConstraintStatus st : assignableStatus ){
			assertFalse(st.isOK());
		}

		final Pool processWithoutErrors = getProcess(diagram,"NoConstraintFailure","1.0");
		status = getStatuses(batchValidator.validate(processWithoutErrors));
		assignableStatus = getStatusForConstraint(status,AssignableConstraint.ID);
		assertFalse(assignableStatus.isEmpty());
		assertEquals(2, assignableStatus.size());
		for(final IConstraintStatus st : assignableStatus ){
			assertTrue(st.isOK());
		}
	}

	@Test
	@Ignore
	public void testInclusiveGatewayConstraint() throws Exception{
		final MainProcess diagram = getDiagramFromArchive("GatewayInclusiveMergeConstraint-1.0.bos","GatewayInclusiveMergeConstraint","1.0");
		final List<Pool> poolsWithoutErrors = new ArrayList<Pool>();
		poolsWithoutErrors.add(getProcess(diagram,"Valid","1.0"));
		poolsWithoutErrors.add(getProcess(diagram,"Valid","2.0"));
		poolsWithoutErrors.add(getProcess(diagram,"Valid","3.0"));
		poolsWithoutErrors.add(getProcess(diagram,"Cycle","1.0"));

		for(final Pool process : poolsWithoutErrors){
			final IStatus[] status = getStatuses(batchValidator.validate(process));
			final List<IConstraintStatus> inclusiveMergeStatus = getStatusForConstraint(status,InclusiveMergeGatewayConstraint.ID);
			assertFalse(inclusiveMergeStatus.isEmpty());
			int nbError =  0;
			for(final IConstraintStatus st : inclusiveMergeStatus ){
				if(!st.isOK()){
					nbError++;
				}
			}
			assertEquals(0, nbError);
		}

		final List<Pool> poolsWithErrors = new ArrayList<Pool>();
		poolsWithErrors.add(getProcess(diagram,"Invalid","1.0"));
		poolsWithErrors.add(getProcess(diagram,"Invalid","2.0"));
		for(final Pool process : poolsWithErrors){
			final IStatus[] status = getStatuses(batchValidator.validate(process));
			final List<IConstraintStatus> inclusiveMergeStatus = getStatusForConstraint(status,InclusiveMergeGatewayConstraint.ID);
			assertFalse(inclusiveMergeStatus.isEmpty());
			int nbError =  0;
			for(final IConstraintStatus st : inclusiveMergeStatus){
				if(!st.isOK()){
					nbError++;
				}
			}
			assertTrue(nbError>0);
		}
	}

	@Test
	public void testXORGatewayConstraint() throws Exception{
		final MainProcess diagram = getDiagramFromArchive("XORGatewayConstraint-1.0.bos","XORGatewayConstraint","1.0");
		final Pool processWithErrors = getProcess(diagram,"Error","1.0");
		IStatus[] status = getStatuses(batchValidator.validate(processWithErrors));
		List<IConstraintStatus> assignableStatus = getStatusForConstraint(status,XORGatewayConstraint.ID);
		assertFalse(assignableStatus.isEmpty());
		assertEquals(1, assignableStatus.size());
		for(final IConstraintStatus st : assignableStatus ){
			assertFalse(st.isOK());
		}

		final Pool processWithoutErrors = getProcess(diagram,"OK","1.0");
		status = getStatuses(batchValidator.validate(processWithoutErrors));
		assignableStatus = getStatusForConstraint(status,XORGatewayConstraint.ID);
		assertFalse(assignableStatus.isEmpty());
		assertEquals(2, assignableStatus.size());
		for(final IConstraintStatus st : assignableStatus ){
			assertTrue(st.isOK());
		}

		final Pool processWithWarning = getProcess(diagram,"Warning","1.0");
		status = getStatuses(batchValidator.validate(processWithWarning));
		assignableStatus = getStatusForConstraint(status,XORGatewayConstraint.ID);
		assertFalse(assignableStatus.isEmpty());
		assertEquals(1, assignableStatus.size());
		for(final IConstraintStatus st : assignableStatus ){
			assertTrue(st.getSeverity() == IMarker.SEVERITY_INFO);
		}
	}

	@Test
	public void testConnectorExistenceConstraint() throws Exception{
		final MainProcess diagram = getDiagramFromArchive("testConnectorExistence.bos","MyDiagram19","1.0");

		final Pool processWithoutError = getProcess(diagram,"Pool19","1.0");
		final Pool processWithErrors = getProcess(diagram,"Pool20","1.0");

		IStatus[] status = getStatuses(batchValidator.validate(processWithErrors));
		List<IConstraintStatus> assignableStatus = getStatusForConstraint(status,ConnectorExistenceConstraint.ID);
		assertFalse(assignableStatus.isEmpty());
		assertEquals(1, assignableStatus.size());
		for(final IConstraintStatus st : assignableStatus ){
			assertFalse(st.isOK());
		}

		status = getStatuses(batchValidator.validate(processWithoutError));
		assignableStatus = getStatusForConstraint(status,ConnectorExistenceConstraint.ID);
		assertFalse(assignableStatus.isEmpty());
		assertEquals(1, assignableStatus.size());
		for(final IConstraintStatus st : assignableStatus ){
			assertTrue(st.isOK());
		}
	}

}
