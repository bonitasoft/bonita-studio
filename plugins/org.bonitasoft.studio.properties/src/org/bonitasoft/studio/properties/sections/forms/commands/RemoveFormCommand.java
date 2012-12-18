/**
 * Copyright (C) 2009 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.properties.sections.forms.commands;

import java.util.ArrayList;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.model.form.Form;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.diagram.form.part.FormDiagramEditor;
import org.bonitasoft.studio.properties.i18n.Messages;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.Transaction;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.common.core.command.CompositeCommand;
import org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

/**
 * @author Baptiste Mesta : initial implementation
 * @author Aurelien Pupier : remove also the diagram from the file
 *
 */
public class RemoveFormCommand extends AbstractTransactionalCommand {

    protected final Element pageFlow;
    protected final ArrayList<Form> toRemoveForm;
    /**
     * The command used to perform the remove.
     */
    protected CompositeCommand cc;
    /**
     * The TreeViewer of the form list in order to refresh it on undo/redo.
     */
    protected TreeViewer treeViewer;

    public RemoveFormCommand(TransactionalEditingDomain editingDomain, Element pageFlow2, ArrayList<Form> toRemove, TreeViewer treeViewer) {
        super(editingDomain, Messages.formRemoveFormCommand, getWorkspaceFiles(pageFlow2));
        pageFlow = pageFlow2;
        toRemoveForm = toRemove;
        this.treeViewer = treeViewer;
    }

    @Override
    protected CommandResult doExecuteWithResult(IProgressMonitor monitor,
            IAdaptable info) throws ExecutionException {
        cc = new CompositeCommand(getLabel());
        IEditorReference[] editorReferences = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getEditorReferences();
        for (Form form : toRemoveForm) {
            for (int i = 0; i < editorReferences.length; i++) {
                IEditorReference iEditorReference = editorReferences[i];
                try {
                    IEditorInput editorInput = iEditorReference.getEditorInput();
                    if(editorInput instanceof URIEditorInput){
                        URI uri = ((URIEditorInput) editorInput).getURI();
                        Diagram diagramFor = ModelHelper.getDiagramFor(form,getEditingDomain());
                        if(diagramFor != null){
                            if(ModelHelper.getEObjectID(diagramFor).equals(uri.fragment())){
                                //close the editor
                                IWorkbenchPart part = iEditorReference.getPart(false);
                                if(part instanceof FormDiagramEditor){
                                    ((FormDiagramEditor) part).close(false);
                                }
                                break;
                            }
                        }
                    }
                } catch (PartInitException e) {
                    BonitaStudioLog.error(e);
                }
            }
            /*Destroy the form in the model*/
            DestroyElementRequest der = new DestroyElementRequest(getEditingDomain(), form, false);
            cc.add(new DestroyElementCommand(der));
            /*Destroy the form diagram*/
            for(EObject o : form.eResource().getContents()){
                if(o instanceof Diagram){
                    if(((Diagram) o).getElement() instanceof Form){
                        Form f = (Form) ((View) o).getElement();
                        if(f.getName().equals(form.getName())){
                            if(((Diagram) o).getElement().equals(form)){
                                cc.add(new DeleteCommand((View) o));
                            }
                        }
                    }
                }
            }
        }

        try {
            cc.execute(null, null);
        } catch (ExecutionException e) {
            BonitaStudioLog.error(e);
        }
        return CommandResult.newOKCommandResult();
    }

    @Override
    protected void didRedo(Transaction tx) {
        super.didRedo(tx);
        if(treeViewer!=null && !treeViewer.getTree().isDisposed()) {
            treeViewer.refresh();
        }
    }

    @Override
    protected void didUndo(Transaction tx) {
        super.didUndo(tx);
        if(treeViewer!=null && !treeViewer.getTree().isDisposed()) {
            treeViewer.refresh();
        }
    }
}
