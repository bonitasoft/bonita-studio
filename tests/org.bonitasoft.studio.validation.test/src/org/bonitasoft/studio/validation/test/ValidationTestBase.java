package org.bonitasoft.studio.validation.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.operation.ImportBosArchiveOperation;
import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.Pool;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.validation.internal.EMFModelValidationStatusCodes;
import org.eclipse.emf.validation.model.EvaluationMode;
import org.eclipse.emf.validation.model.IConstraintStatus;
import org.eclipse.emf.validation.model.IModelConstraint;
import org.eclipse.emf.validation.service.IBatchValidator;
import org.eclipse.emf.validation.service.ModelValidationService;

@SuppressWarnings("restriction")
public class ValidationTestBase {

    protected final IBatchValidator batchValidator;

    public ValidationTestBase() {
        batchValidator = ModelValidationService.getInstance().newValidator(
                EvaluationMode.BATCH);
        batchValidator.setIncludeLiveConstraints(true);
        batchValidator.setReportSuccesses(true);
    }

    /**
     * Helper method to find the status matching a constraint ID.
     * 
     * @param statuses
     *            the statuses to search
     * @param id
     *            the constraint ID to look for
     * @return the matching status, or <code>null</code> if it's not found
     */
    protected IStatus getStatus(IStatus[] statuses, String id) {
        IStatus result = null;

        for (IStatus element : statuses) {
            IConstraintStatus next = (IConstraintStatus) element;

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
     *            the statuses to search
     * @param id
     *            the constraint ID to look for
     * @return the matching statuses, or empty array if none found
     * 
     * @since 1.1
     */
    protected IStatus[] getStatuses(IStatus[] statuses, String id) {
        List<IStatus> result = new java.util.ArrayList<IStatus>();

        for (IStatus element : statuses) {
            IConstraintStatus next = (IConstraintStatus) element;

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
     *            the status, which may be multi or not
     * @return all of the statuses represented by the incoming <code>status</code>
     */
    protected IStatus[] getStatuses(IStatus status) {
        if (status.getCode() == EMFModelValidationStatusCodes.NO_CONSTRAINTS_EVALUATED) {
            return new IStatus[0];
        }

        List<IStatus> result = new java.util.ArrayList<IStatus>();

        collectStatuses(status, result);

        return result.toArray(new IStatus[result.size()]);
    }

    private void collectStatuses(IStatus status, List<IStatus> statuses) {
        if (status.isMultiStatus()) {
            IStatus[] children = status.getChildren();

            for (IStatus element : children) {
                collectStatuses(element, statuses);
            }
        } else {
            statuses.add(status);
        }
    }

    protected List<IConstraintStatus> getStatusForConstraint(IStatus[] statuses, String constraintId) {
        List<IConstraintStatus> result = new java.util.ArrayList<IConstraintStatus>();
        for (IStatus element : statuses) {
            IModelConstraint next = ((IConstraintStatus) element).getConstraint();
            if (next.getDescriptor().getId().equals(constraintId)) {
                result.add((IConstraintStatus) element);
            }
        }
        return result;
    }

    protected MainProcess getDiagramFromArchive(String archiveName, String diagramName,
            String diagramVersion) throws IOException {
        final URL url = TestValidationConstraints.class.getResource(archiveName);
        ImportBosArchiveOperation op = new ImportBosArchiveOperation();
        op.setArchiveFile(FileLocator.toFileURL(url).getFile());
        op.setCurrentRepository(RepositoryManager.getInstance().getCurrentRepository());
        op.run(Repository.NULL_PROGRESS_MONITOR);
        final DiagramRepositoryStore store = (DiagramRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(DiagramRepositoryStore.class);
        final DiagramFileStore fStore = store.getDiagram(diagramName, diagramVersion);
        assertNotNull(fStore);
        return fStore.getContent();
    }

    protected Pool getProcess(MainProcess diagram, String processName, String version) {
        for (Element e : diagram.getElements()) {
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
