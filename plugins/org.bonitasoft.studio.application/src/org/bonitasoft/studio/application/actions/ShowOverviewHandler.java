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
package org.bonitasoft.studio.application.actions;


import org.bonitasoft.studio.application.views.BonitaContentOutlineView;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

/**
 * @author Aurelien Pupier
 */
public class ShowOverviewHandler extends AbstractHandler {

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        try {
            IEditorPart part = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor() ;
            ISelection selection = null ;
            if(part != null){
                selection = part.getSite().getSelectionProvider().getSelection() ;
            }

            IViewPart view  = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView(BonitaContentOutlineView.VIEW_ID);
            if(view != null){
                view.setFocus();
                part.getSite().getSelectionProvider().setSelection(selection) ;
            }

            return Status.OK_STATUS ;
        } catch (PartInitException ex) {
            return new ExecutionException("Could not load overview view", ex);
        }
    }

}
