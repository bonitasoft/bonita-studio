package org.bonitasoft.studio.validation.common.operation;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.jface.ValidationDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.validation.common.i18n.Messages;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

public class RunProcessesValidationOperation implements IRunnableWithProgress {

    private final List<AbstractProcess> listOfProcessesToValidate;
    private IStatus status;

    public RunProcessesValidationOperation(final List<AbstractProcess> processes) {
        listOfProcessesToValidate = processes;
    }

    private boolean statusContainsError() {
        if (status != null) {
            for (final IStatus s : status.getChildren()) {
                if (s.getSeverity() == IStatus.WARNING || s.getSeverity() == IStatus.ERROR) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean displayConfirmationDialog() {
        if (statusContainsError()) {
            if (!FileActionDialog.getDisablePopup()) {
                final String errorMessage = Messages.errorValidationMessage
                        + PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor().getTitle()
                        + Messages.errorValidationContinueAnywayMessage;
                final int result = new ValidationDialog(Display.getDefault().getActiveShell(), Messages.validationFailedTitle, errorMessage,
                        ValidationDialog.YES_NO_SEEDETAILS).open();
                if (result == ValidationDialog.NO) {
                    return false;
                } else {
                    if (result == ValidationDialog.SEE_DETAILS) {
                        showValidationPart();
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean displayOkSeeMoreDetailsDialog() {
        if (statusContainsError()) {
            final String errorMessage = Messages.errorValidationMessage
                    + PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor().getTitle();
            final int result = new ValidationDialog(Display.getDefault().getActiveShell(), Messages.validationFailedTitle, errorMessage,
                    ValidationDialog.OK_SEEDETAILS).open();
            if (result == ValidationDialog.SEE_DETAILS) {
                showValidationPart();
            }
        }
        return true;
    }

    public IStatus getStatus() {
        return status;
    }

    public static void showValidationPart() {
        final IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
        final IEditorPart part = activePage.getActiveEditor();
        if (part != null && part instanceof DiagramEditor) {
            final MainProcess proc = ModelHelper.getMainProcess(((DiagramEditor) part).getDiagramEditPart().resolveSemanticElement());
            final String partName = proc.getName() + " (" + proc.getVersion() + ")";
            for (final IEditorReference ref : activePage.getEditorReferences()) {
                if (partName.equals(ref.getPartName())) {
                    activePage.activate(ref.getPart(true));
                    break;
                }
            }

        }
        Display.getDefault().asyncExec(new Runnable() {

            @Override
            public void run() {
                try {
                    activePage.showView("org.bonitasoft.studio.validation.view");
                } catch (final PartInitException e) {
                    BonitaStudioLog.error(e);
                }
            }
        });
    }

    @Override
    public void run(final IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        final BatchValidationOperation batchValidationOperation = new BatchValidationOperation(new OffscreenEditPartFactory());
        for (final AbstractProcess p : listOfProcessesToValidate) {
            final DiagramFileStore fileStore = asDiagramFileStore(p);
            if (fileStore == null) {
                final MainProcess mainProcess = ModelHelper.getMainProcess(p);
                if (mainProcess == null) {
                    throw new InvocationTargetException(new NullPointerException(String.format("Process %s (%s) is not contained in a MainProcess",
                            p.getName(), p.getVersion())));
                }
                throw new InvocationTargetException(new IOException(String.format("Failed to retrieve resource for diagram %s (%s)", mainProcess
                        .getName(), mainProcess.getVersion())));
            }
            final Resource eResource = fileStore.getEMFResource();
            final TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(eResource);
            final FindDiagramRunnable runnable = new FindDiagramRunnable(eResource, batchValidationOperation);
            if (editingDomain != null) {
                try {
                    editingDomain.runExclusive(runnable);
                } catch (final InterruptedException e) {
                    BonitaStudioLog.error(e);
                }
            } else {
                runnable.run();
            }
        }
        batchValidationOperation.run(monitor);
        status = batchValidationOperation.getResult();
    }

    protected DiagramFileStore asDiagramFileStore(final AbstractProcess process) {
        final DiagramRepositoryStore store = RepositoryManager.getInstance().getRepositoryStore(DiagramRepositoryStore.class);
        final Resource eResource = process.eResource();
        if (eResource != null) {
            return store.getChild(URI.decode(eResource.getURI().lastSegment()));
        }
        return null;
    }

}
