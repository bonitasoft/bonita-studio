/**
 * Copyright (C) 2012 BonitaSoft S.A.
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
package org.bonitasoft.studio.tests.validation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.List;

import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.importer.bos.operation.ImportBosArchiveOperation;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.validation.constraints.connector.ConnectorExistenceConstraint;
import org.bonitasoft.studio.validation.constraints.process.AssignableConstraint;
import org.bonitasoft.studio.validation.constraints.process.XORGatewayConstraint;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.validation.internal.EMFModelValidationStatusCodes;
import org.eclipse.emf.validation.model.EvaluationMode;
import org.eclipse.emf.validation.model.IConstraintStatus;
import org.eclipse.emf.validation.model.IModelConstraint;
import org.eclipse.emf.validation.service.IBatchValidator;
import org.eclipse.emf.validation.service.ModelValidationService;
import org.eclipse.ui.PlatformUI;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Romain Bioteau
 */
public class TestValidationConstraints {
    
    private IBatchValidator batchValidator;
    private RepositoryAccessor repositoryAccessor;

    @Before
    public void setUp() {
        batchValidator = ModelValidationService.getInstance().newValidator(
                EvaluationMode.BATCH);
        batchValidator.setIncludeLiveConstraints(true);
        batchValidator.setReportSuccesses(true);

        repositoryAccessor = RepositoryManager.getInstance().getAccessor();
    }
    
    @AfterClass
    public static void close() {
        PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().saveAllEditors(false);
        PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().closeAllEditors(false);
    }

    @Test
    public void testAssignableConstraint() throws Exception {
        final MainProcess diagram = getDiagramFromArchive("TestAssignableConstraint-1.0.bos", "TestAssignableConstraint",
                "1.0");
        final Pool processWithErrors = getProcess(diagram, "ConstraintFailure", "1.0");
        IStatus[] status = getStatuses(batchValidator.validate(processWithErrors));
        List<IConstraintStatus> assignableStatus = getStatusForConstraint(status, AssignableConstraint.ID);
        assertFalse(assignableStatus.isEmpty());
        assertEquals(4, assignableStatus.size());
        for (final IConstraintStatus st : assignableStatus) {
            assertFalse(st.isOK());
        }

        final Pool processWithoutErrors = getProcess(diagram, "NoConstraintFailure", "1.0");
        status = getStatuses(batchValidator.validate(processWithoutErrors));
        assignableStatus = getStatusForConstraint(status, AssignableConstraint.ID);
        assertFalse(assignableStatus.isEmpty());
        assertEquals(2, assignableStatus.size());
        for (final IConstraintStatus st : assignableStatus) {
            assertTrue(st.isOK());
        }
    }

    @Test
    public void testXORGatewayConstraint() throws Exception {
        final MainProcess diagram = getDiagramFromArchive("XORGatewayConstraint-1.0.bos", "XORGatewayConstraint", "1.0");
        final Pool processWithErrors = getProcess(diagram, "Error", "1.0");
        IStatus[] status = getStatuses(batchValidator.validate(processWithErrors));
        List<IConstraintStatus> assignableStatus = getStatusForConstraint(status, XORGatewayConstraint.ID);
        assertFalse(assignableStatus.isEmpty());
        assertEquals(1, assignableStatus.size());
        for (final IConstraintStatus st : assignableStatus) {
            assertFalse(st.isOK());
        }

        final Pool processWithoutErrors = getProcess(diagram, "OK", "1.0");
        status = getStatuses(batchValidator.validate(processWithoutErrors));
        assignableStatus = getStatusForConstraint(status, XORGatewayConstraint.ID);
        assertFalse(assignableStatus.isEmpty());
        assertEquals(2, assignableStatus.size());
        for (final IConstraintStatus st : assignableStatus) {
            assertTrue(st.isOK());
        }

        final Pool processWithWarning = getProcess(diagram, "Warning", "1.0");
        status = getStatuses(batchValidator.validate(processWithWarning));
        assignableStatus = getStatusForConstraint(status, XORGatewayConstraint.ID);
        assertFalse(assignableStatus.isEmpty());
        assertEquals(1, assignableStatus.size());
        for (final IConstraintStatus st : assignableStatus) {
            assertTrue(st.getSeverity() == IMarker.SEVERITY_INFO);
        }
    }

    @Test
    public void testConnectorExistenceConstraint() throws Exception {
        final MainProcess diagram = getDiagramFromArchive("testConnectorExistence.bos", "MyDiagram19", "1.0");
        
        final Pool processWithoutError = getProcess(diagram, "Pool19", "1.0");
        final Pool processWithErrors = getProcess(diagram, "Pool20", "1.0");
        
        IStatus[] status = getStatuses(batchValidator.validate(processWithErrors));
        List<IConstraintStatus> assignableStatus = getStatusForConstraint(status, ConnectorExistenceConstraint.ID);
        assertFalse(assignableStatus.isEmpty());
        assertEquals(1, assignableStatus.size());
        for (final IConstraintStatus st : assignableStatus) {
            assertFalse(st.isOK());
        }

        status = getStatuses(batchValidator.validate(processWithoutError));
        assignableStatus = getStatusForConstraint(status, ConnectorExistenceConstraint.ID);
        assertFalse(assignableStatus.isEmpty());
        assertEquals(1, assignableStatus.size());
        for (final IConstraintStatus st : assignableStatus) {
            assertTrue(st.isOK());
        }
    }
    
    /**
     * Helper method to find the status matching a constraint ID.
     *
     * @param statuses
     *        the statuses to search
     * @param id
     *        the constraint ID to look for
     * @return the matching status, or <code>null</code> if it's not found
     */
    protected IStatus getStatus(final IStatus[] statuses, final String id) {
        IStatus result = null;

        for (final IStatus element : statuses) {
            final IConstraintStatus next = (IConstraintStatus) element;

            if (next.getConstraint().getDescriptor().getId().equals(id)) {
                result = next;
                break;
            }
        }
        return result;
    }

    /**
     * Helper method to find multiple statuses matching a constraint ID.
     *
     * @param statuses
     *        the statuses to search
     * @param id
     *        the constraint ID to look for
     * @return the matching statuses, or empty array if none found
     * @since 1.1
     */
    protected IStatus[] getStatuses(final IStatus[] statuses, final String id) {
        final List<IStatus> result = new java.util.ArrayList<>();

        for (final IStatus element : statuses) {
            final IConstraintStatus next = (IConstraintStatus) element;

            if (next.getConstraint().getDescriptor().getId().equals(id)) {
                result.add(next);
            }
        }

        return result.toArray(new IStatus[result.size()]);
    }

    /**
     * Helper method to convert the specified <code>status</code> to an array
     * of statuses for uniform treatment of scalar and multi-statuses. As a
     * special case, the scalar status indicating success because no constraints
     * were evaluated results in an empty array being returned.
     *
     * @param status
     *        the status, which may be multi or not
     * @return all of the statuses represented by the incoming <code>status</code>
     */
    public IStatus[] getStatuses(final IStatus status) {
        if (status.getCode() == EMFModelValidationStatusCodes.NO_CONSTRAINTS_EVALUATED) {
            return new IStatus[0];
        }

        final List<IStatus> result = new java.util.ArrayList<>();

        collectStatuses(status, result);

        return result.toArray(new IStatus[result.size()]);
    }

    private void collectStatuses(final IStatus status, final List<IStatus> statuses) {
        if (status.isMultiStatus()) {
            final IStatus[] children = status.getChildren();

            for (final IStatus element : children) {
                collectStatuses(element, statuses);
            }
        } else {
            statuses.add(status);
        }
    }

    protected List<IConstraintStatus> getStatusForConstraint(final IStatus[] statuses, final String constraintId) {
        final List<IConstraintStatus> result = new java.util.ArrayList<>();
        for (final IStatus element : statuses) {
            final IModelConstraint next = ((IConstraintStatus) element).getConstraint();
            if (next.getDescriptor().getId().equals(constraintId)) {
                result.add((IConstraintStatus) element);
            }
        }
        return result;
    }

    protected MainProcess getDiagramFromArchive(final String archiveName, final String diagramName,
            final String diagramVersion) throws IOException, InvocationTargetException, InterruptedException, ReadFileStoreException {
        final URL url = TestValidationConstraints.class.getResource(archiveName);
        final ImportBosArchiveOperation op = new ImportBosArchiveOperation(repositoryAccessor);
        op.disableValidation();
        op.setArchiveFile(FileLocator.toFileURL(url).getFile());
        op.setCurrentRepository(repositoryAccessor.getCurrentRepository());
        PlatformUI.getWorkbench().getProgressService().run(true, false, op);
        var store = repositoryAccessor.getRepositoryStore(DiagramRepositoryStore.class);
        PlatformUI.getWorkbench().getProgressService().run(true, false, store::computeProcesses);
        final DiagramFileStore fStore = store.getDiagram(diagramName, diagramVersion);
        assertNotNull(fStore);
        return fStore.getContent();
    }

    protected Pool getProcess(final MainProcess diagram, final String processName, final String version) {
        for (final Element e : diagram.getElements()) {
            if (e instanceof Pool) {
                if (e.getName().equals(processName) && ((AbstractProcess) e).getVersion().equals(version)) {
                    return (Pool) e;
                }
            }
        }
        fail("No pool found with name " + processName + " and version " + version);
        return null;
    }

}
