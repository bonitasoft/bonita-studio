/**
 * Copyright (C) 2010 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 *
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
 * 
 * Wrap {@link DefaultOperationHistory}
 * only the {@link DefaultOperationHistory.dispose()} is overriden
 * 
 * @author Baptiste Mesta
 * @author Romain Bioteau
 */
public class BonitaOperationHistory implements IOperationHistory {

	private DefaultOperationHistory defaultOperationHistory;

	public BonitaOperationHistory(){
		defaultOperationHistory = new DefaultOperationHistory();
	}
	
	@Override
	public void dispose(IUndoContext context, boolean flushUndo, boolean flushRedo, boolean flushContext) {
		// dispose of any limit that was set for the context if it is not to be
		// used again.
		if (context instanceof EditingDomainUndoContext) {
			EditingDomainUndoContext editingDomainContext = (EditingDomainUndoContext) context;
			EditingDomain editingDomain = editingDomainContext.getEditingDomain();
			if (PlatformUI.isWorkbenchRunning() 
					&& PlatformUI.getWorkbench().getActiveWorkbenchWindow() != null 
					&& PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage() != null
					&& PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor() != null) {

				IEditorReference[] editorReferences = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getEditorReferences();
				for (IEditorReference editorRef : editorReferences) {
					try {
						IWorkbenchPart part = editorRef.getPart(false);
						if (part instanceof DiagramEditor) {
							DiagramEditor editor = (DiagramEditor) part;
							if (editor.getEditingDomain() != null && editor.getEditingDomain().equals(editingDomain)) {
								return;// do not dispose if the editing domain
								// is else
							}
						}
					} catch (Exception e) {
						BonitaStudioLog.error(e);
					}
				}
			}
		}
		defaultOperationHistory.dispose(context, flushUndo, flushRedo, flushContext);
	}

	@Override
	public void add(IUndoableOperation operation) {
		defaultOperationHistory.add(operation);
	}

	@Override
	public void addOperationApprover(IOperationApprover approver) {
		defaultOperationHistory.addOperationApprover(approver);
	}

	@Override
	public void addOperationHistoryListener(IOperationHistoryListener listener) {
		defaultOperationHistory.addOperationHistoryListener(listener);
	}

	@Override
	public void closeOperation(boolean operationOK, boolean addToHistory,
			int mode) {
		defaultOperationHistory.closeOperation(operationOK, addToHistory, mode);
	}

	@Override
	public boolean canRedo(IUndoContext context) {
		return defaultOperationHistory.canRedo(context);
	}

	@Override
	public boolean canUndo(IUndoContext context) {
		return defaultOperationHistory.canUndo(context);
	}

	@Override
	public IStatus execute(IUndoableOperation operation,
			IProgressMonitor monitor, IAdaptable info)
			throws ExecutionException {
		return defaultOperationHistory.execute(operation, monitor, info);
	}

	@Override
	public int getLimit(IUndoContext context) {
		return defaultOperationHistory.getLimit(context);
	}

	@Override
	public IUndoableOperation[] getRedoHistory(IUndoContext context) {
		return defaultOperationHistory.getRedoHistory(context);
	}

	@Override
	public IUndoableOperation getRedoOperation(IUndoContext context) {
		return defaultOperationHistory.getRedoOperation(context);
	}

	@Override
	public IUndoableOperation[] getUndoHistory(IUndoContext context) {
		return defaultOperationHistory.getUndoHistory(context);
	}

	@Override
	public void openOperation(ICompositeOperation operation, int mode) {
		defaultOperationHistory.openOperation(operation, mode);
	}

	@Override
	public void operationChanged(IUndoableOperation operation) {
		defaultOperationHistory.operationChanged(operation);
	}

	@Override
	public IUndoableOperation getUndoOperation(IUndoContext context) {
		return defaultOperationHistory.getUndoOperation(context);
	}

	@Override
	public IStatus redo(IUndoContext context, IProgressMonitor monitor,
			IAdaptable info) throws ExecutionException {
		return defaultOperationHistory.redo(context, monitor, info);
	}

	@Override
	public IStatus redoOperation(IUndoableOperation operation,
			IProgressMonitor monitor, IAdaptable info)
			throws ExecutionException {
		return defaultOperationHistory.redoOperation(operation, monitor, info);
	}

	@Override
	public void removeOperationApprover(IOperationApprover approver) {
		defaultOperationHistory.removeOperationApprover(approver);
	}

	@Override
	public void removeOperationHistoryListener(
			IOperationHistoryListener listener) {
		defaultOperationHistory.removeOperationHistoryListener(listener);
	}

	@Override
	public void replaceOperation(IUndoableOperation operation,
			IUndoableOperation[] replacements) {
		defaultOperationHistory.replaceOperation(operation, replacements);	
	}

	@Override
	public void setLimit(IUndoContext context, int limit) {
		defaultOperationHistory.setLimit(context, limit);
	}

	@Override
	public IStatus undo(IUndoContext context, IProgressMonitor monitor,
			IAdaptable info) throws ExecutionException {
		return defaultOperationHistory.undo(context, monitor, info);
	}

	@Override
	public IStatus undoOperation(IUndoableOperation operation,
			IProgressMonitor monitor, IAdaptable info)
			throws ExecutionException {
		return defaultOperationHistory.undoOperation(operation, monitor, info);
	}
}
