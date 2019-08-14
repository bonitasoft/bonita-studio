package org.bonitasoft.studio.validation.common.operation;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.collect.Iterables.filter;
import static com.google.common.collect.Lists.newArrayList;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import com.google.common.base.Predicates;

public class RunProcessesValidationOperation implements IRunnableWithProgress {

    private final List<AbstractProcess> listOfProcessesToValidate = new ArrayList<>();
    private IStatus status;
    private final BatchValidationOperation validationOperation;

    public RunProcessesValidationOperation(final BatchValidationOperation validationOperation) {
        checkNotNull(validationOperation);
        this.validationOperation = validationOperation;
    }

    public RunProcessesValidationOperation addProcess(final AbstractProcess process) {
        listOfProcessesToValidate.add(process);
        return this;
    }

    public RunProcessesValidationOperation addProcesses(final List<AbstractProcess> processes) {
        listOfProcessesToValidate.addAll(newArrayList(processes));
        return this;
    }

    private boolean statusContainsError() {
        if (status != null) {
            for (final IStatus s : status.getChildren()) {
                if (s.getSeverity() == IStatus.ERROR) {
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
                        + getActiveEditorTitle()
                        + Messages.errorValidationContinueAnywayMessage;
                final int result = new ValidationDialog(Display.getDefault().getActiveShell(),
                        Messages.validationFailedTitle, errorMessage,
                        ValidationDialog.YES_NO_SEEDETAILS).open();
                if (result == ValidationDialog.NO) {
                    return false;
                }
                if (result == ValidationDialog.SEE_DETAILS) {
                    showValidationPart();
                    return false;
                }
            }
        }
        return true;
    }

    protected String getActiveEditorTitle() {
        final IWorkbenchWindow activeWorkbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
        if (activeWorkbenchWindow != null) {
            final IWorkbenchPage activePage = activeWorkbenchWindow.getActivePage();
            if (activePage != null) {
                final IEditorPart activeEditor = activePage.getActiveEditor();
                if (activeEditor != null) {
                    return activeEditor.getTitle();
                }
            }
        }
        return "";
    }

    public boolean displayOkSeeMoreDetailsDialog() {
        if (statusContainsError()) {
            final String errorMessage = Messages.errorValidationMessage
                    + getActiveEditorTitle();
            final int result = new ValidationDialog(Display.getDefault().getActiveShell(), Messages.validationFailedTitle,
                    errorMessage,
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
            final MainProcess proc = ModelHelper
                    .getMainProcess(((DiagramEditor) part).getDiagramEditPart().resolveSemanticElement());
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
        for (final AbstractProcess p : listOfProcessesToValidate) {
            final Resource eResource = p.eResource();
            if (eResource != null) {
                for (final EObject diagram : filter(eResource.getContents(), Predicates.instanceOf(Diagram.class))) {
                    validationOperation.addDiagram((Diagram) diagram);
                }
            }
        }
        try {
            validationOperation.run(monitor);
            status = validationOperation.getResult();
        } catch (InterruptedException e) {
            if (!monitor.isCanceled()) {
                throw e;
            }
        }
    }

    protected TransactionalEditingDomain editingDomain(final Resource eResource) {
        return TransactionUtil.getEditingDomain(eResource);
    }

    protected DiagramFileStore asDiagramFileStore(final AbstractProcess process) {
        final DiagramRepositoryStore store = RepositoryManager.getInstance()
                .getRepositoryStore(DiagramRepositoryStore.class);
        final Resource eResource = process.eResource();
        if (eResource != null) {
            return store.getChild(URI.decode(eResource.getURI().lastSegment()), true);
        }
        return null;
    }

}
