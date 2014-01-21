/**
 * Copyright (C) 2011 BonitaSoft S.A.
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
package org.bonitasoft.studio.application.actions;

import org.bonitasoft.studio.application.views.BonitaContentOutlineTreeView;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.model.process.diagram.form.part.FormDiagramEditor;
import org.bonitasoft.studio.model.process.diagram.part.ProcessDiagramEditor;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.action.IAction;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.FilteredTree;
import org.eclipse.ui.part.PageBook;

/**
 * @author Mickael Istria
 *
 */
public class FindHandler extends AbstractHandler {

    /* (non-Javadoc)
     * @see org.eclipse.core.commands.IHandler#execute(org.eclipse.core.commands.ExecutionEvent)
     */
    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
//        final IWorkbenchWindow workbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
//        IEditorPart editor = workbenchWindow.getActivePage().getActiveEditor() ;
//
//        BonitaContentOutlineTreeView view;
//        try {
//            view = (BonitaContentOutlineTreeView) workbenchWindow.getActivePage().showView(BonitaContentOutlineTreeView.VIEW_ID);
//            view.setFocus();
//            if (editor != null && editor instanceof ProcessDiagramEditor ) {
//                IAction action =((ProcessDiagramEditor)editor).getShowOutlineAction();
//                if(action != null){
//                    action.run();
//                }
//            }else if(editor != null && editor instanceof FormDiagramEditor){
//                IAction action =((FormDiagramEditor)editor).getShowOutlineAction();
//                if(action != null){
//                    action.run();
//                }
//            }
//            for (Control control : ((PageBook)view.getCurrentPage().getControl()).getTabList()) {
//                if (control instanceof FilteredTree) {
//                    final Text filterText = ((FilteredTree)control).getFilterControl();
//                    filterText.setFocus();
//                    if (filterText.getText() != null) {
//                        filterText.setSelection(0, filterText.getText().length());
//                    }
//                }
//            }
//        } catch (PartInitException e) {
//            BonitaStudioLog.error(e);
//        }


        return null;
    }

}
