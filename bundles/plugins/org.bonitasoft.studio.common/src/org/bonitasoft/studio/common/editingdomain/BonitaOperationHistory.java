/**
 * Copyright (C) 2010 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.common.editingdomain;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.DefaultOperationHistory;
import org.eclipse.core.commands.operations.ICompositeOperation;
import org.eclipse.core.commands.operations.IOperationApprover;
import org.eclipse.core.commands.operations.IOperationHistory;
import org.eclipse.core.commands.operations.IOperationHistoryListener;
import org.eclipse.core.commands.operations.IUndoContext;
import org.eclipse.core.commands.operations.IUndoableOperation;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.emf.commands.core.command.EditingDomainUndoContext;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

/**
 * Wrap {@link DefaultOperationHistory} only the {@link DefaultOperationHistory.dispose()} is overriden
 *
 * @author Baptiste Mesta
 * @author Romain Bioteau
 */
public class BonitaOperationHistory implements IOperationHistory {

    private final DefaultOperationHistory defaultOperationHistory;

    public BonitaOperationHistory() {
        defaultOperationHistory = new DefaultOperationHistory();
    }

    @Override
    public void dispose(final IUndoContext context, final boolean flushUndo, final boolean flushRedo, final boolean flushContext) {
        // dispose of any limit that was set for the context if it is not to be
        // used again.
        if (context instanceof EditingDomainUndoContext) {
            final EditingDomainUndoContext editingDomainContext = (EditingDomainUndoContext) context;
            final EditingDomain editingDomain = editingDomainContext.getEditingDomain();
            if (PlatformUI.isWorkbenchRunning()
                    && PlatformUI.getWorkbench().getActiveWorkbenchWindow() != null
                    && PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage() != null
                    && PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor() != null) {

                final IEditorReference[] editorReferences = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getEditorReferences();
                for (final IEditorReference editorRef : editorReferences) {
                    try {
                        final IWorkbenchPart part = editorRef.getPart(false);
                        if (part instanceof DiagramEditor) {
                            final DiagramEditor editor = (DiagramEditor) part;
                            if (editor.getEditingDomain() != null && editor.getEditingDomain().equals(editingDomain)) {
                                return;// do not dispose if the editing domain
                                // is else
                            }
                        }
                    } catch (final Exception e) {
                        BonitaStudioLog.error(e);
                    }
                }
            }
        }
        defaultOperationHistory.dispose(context, flushUndo, flushRedo, flushContext);
    }

    @Override
    public void add(final IUndoableOperation operation) {
        defaultOperationHistory.add(operation);
    }

    @Override
    public void addOperationApprover(final IOperationApprover approver) {
        defaultOperationHistory.addOperationApprover(approver);
    }

    @Override
    public void addOperationHistoryListener(final IOperationHistoryListener listener) {
        defaultOperationHistory.addOperationHistoryListener(listener);
    }

    @Override
    public void closeOperation(final boolean operationOK, final boolean addToHistory,
            final int mode) {
        defaultOperationHistory.closeOperation(operationOK, addToHistory, mode);
    }

    @Override
    public boolean canRedo(final IUndoContext context) {
        return defaultOperationHistory.canRedo(context);
    }

    @Override
    public boolean canUndo(final IUndoContext context) {
        return defaultOperationHistory.canUndo(context);
    }

    @Override
    public IStatus execute(final IUndoableOperation operation,
            final IProgressMonitor monitor, final IAdaptable info)
            throws ExecutionException {
        return defaultOperationHistory.execute(operation, monitor, info);
    }

    @Override
    public int getLimit(final IUndoContext context) {
        return defaultOperationHistory.getLimit(context);
    }

    @Override
    public IUndoableOperation[] getRedoHistory(final IUndoContext context) {
        return defaultOperationHistory.getRedoHistory(context);
    }

    @Override
    public IUndoableOperation getRedoOperation(final IUndoContext context) {
        return defaultOperationHistory.getRedoOperation(context);
    }

    @Override
    public IUndoableOperation[] getUndoHistory(final IUndoContext context) {
        return defaultOperationHistory.getUndoHistory(context);
    }

    @Override
    public void openOperation(final ICompositeOperation operation, final int mode) {
        defaultOperationHistory.openOperation(operation, mode);
    }

    @Override
    public void operationChanged(final IUndoableOperation operation) {
        defaultOperationHistory.operationChanged(operation);
    }

    @Override
    public IUndoableOperation getUndoOperation(final IUndoContext context) {
        return defaultOperationHistory.getUndoOperation(context);
    }

    @Override
    public IStatus redo(final IUndoContext context, final IProgressMonitor monitor,
            final IAdaptable info) throws ExecutionException {
        return defaultOperationHistory.redo(context, monitor, info);
    }

    @Override
    public IStatus redoOperation(final IUndoableOperation operation,
            final IProgressMonitor monitor, final IAdaptable info)
            throws ExecutionException {
        return defaultOperationHistory.redoOperation(operation, monitor, info);
    }

    @Override
    public void removeOperationApprover(final IOperationApprover approver) {
        defaultOperationHistory.removeOperationApprover(approver);
    }

    @Override
    public void removeOperationHistoryListener(
            final IOperationHistoryListener listener) {
        defaultOperationHistory.removeOperationHistoryListener(listener);
    }

    @Override
    public void replaceOperation(final IUndoableOperation operation,
            final IUndoableOperation[] replacements) {
        defaultOperationHistory.replaceOperation(operation, replacements);
    }

    @Override
    public void setLimit(final IUndoContext context, final int limit) {
        defaultOperationHistory.setLimit(context, limit);
    }

    @Override
    public IStatus undo(final IUndoContext context, final IProgressMonitor monitor,
            final IAdaptable info) throws ExecutionException {
        return defaultOperationHistory.undo(context, monitor, info);
    }

    @Override
    public IStatus undoOperation(final IUndoableOperation operation,
            final IProgressMonitor monitor, final IAdaptable info)
            throws ExecutionException {
        return defaultOperationHistory.undoOperation(operation, monitor, info);
    }
}
