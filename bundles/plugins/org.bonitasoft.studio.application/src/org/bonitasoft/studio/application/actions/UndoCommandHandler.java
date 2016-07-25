/**
 * Copyright (C) 2009-2015 BonitaSoft S.A.
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

package org.bonitasoft.studio.application.actions;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.operations.IOperationHistory;
import org.eclipse.core.commands.operations.IUndoContext;
import org.eclipse.core.commands.operations.IUndoableOperation;
import org.eclipse.gmf.runtime.common.ui.action.actions.global.GlobalActionManager;
import org.eclipse.gmf.runtime.common.ui.action.global.GlobalAction;
import org.eclipse.gmf.runtime.common.ui.action.global.GlobalActionId;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;

/**
 * @author Romain Bioteau
 *
 */
public class UndoCommandHandler  extends AbstractHandler implements IHandler  {

	public UndoCommandHandler(){

	}

	@Override
	public Object execute(final ExecutionEvent event) throws ExecutionException {
		final IEditorPart part = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor() ;
		final GlobalAction action = GlobalActionManager.getInstance().getGlobalActionHandler(part,GlobalActionId.UNDO);
		if(action.isRunnable()){
			action.run();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.commands.AbstractHandler#isEnabled()
	 */
	@Override
	public boolean isEnabled() {
		final IEditorPart editor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor() ;
		if(editor != null){
			final IOperationHistory history = (IOperationHistory) editor.getAdapter(IOperationHistory.class);
			final IUndoContext context = (IUndoContext) editor.getAdapter(IUndoContext.class);
			if(history != null && context != null){
				final IUndoableOperation[] undoHistory = history.getUndoHistory(context);
                if (undoHistory != null && undoHistory.length != 0) {
                    final IUndoableOperation ctxt = undoHistory[undoHistory.length - 1];
					final String ctxtLabel = ctxt.getLabel();
                    if(ctxtLabel != null && ctxtLabel.contains("Lane")){//Avoid Exception on undo //$NON-NLS-1$
						return false ;
					} else {
						return ctxt.canUndo();
					}
				}
			} else {
				return false;
			}
		}
		return false;
	}

}
