/**
 * Copyright (C) 2009-2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.tests.form;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.bonitasoft.studio.application.actions.RedoCommandHandler;
import org.bonitasoft.studio.application.actions.UndoCommandHandler;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.console.test.util.ProcessRegistry;
import org.bonitasoft.studio.diagram.form.custom.commands.AddColumnCommand;
import org.bonitasoft.studio.diagram.form.custom.commands.AddRowCommand;
import org.bonitasoft.studio.diagram.form.custom.commands.RemoveColumnCommand;
import org.bonitasoft.studio.diagram.form.custom.commands.RemoveRowCommand;
import org.bonitasoft.studio.model.form.Form;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.PageFlow;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.FormEditPart;
import org.bonitasoft.studio.model.process.diagram.form.part.FormDiagramEditor;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gmf.runtime.common.ui.services.editor.EditorService;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.notation.Diagram;


/**
 * @author Aurelien Pupier
 * 
 */
public class TestCommands extends TestCase {


    @Override
    protected void setUp() throws Exception {
        super.setUp();
        FileActionDialog.setDisablePopup(true);
    }

    /**
     * Test that the deleteRowCommand was executed
     * @throws Exception
     */
    public void testDeleteRowCommand() throws Exception {
        FormDiagramEditor formEditor = openFormEditorWithBaseTestForForm();

        RemoveRowCommand rRow = new RemoveRowCommand(formEditor.getDiagramEditPart(), 1);
        IStatus status = rRow.execute(null, formEditor);
        assertTrue("The remove column command wasn't execute succesfully.", status.isOK()); //$NON-NLS-1$
        status = rRow.undo(null, formEditor);
        assertTrue("first undo", status.isOK()); //$NON-NLS-1$
        status = rRow.redo(null, formEditor);
        assertTrue("first redo", status.isOK()); //$NON-NLS-1$
        status = rRow.undo(null, formEditor);
        assertTrue("second undo", status.isOK()); //$NON-NLS-1$
        status = rRow.redo(null, formEditor);
        assertTrue("second redo", status.isOK()); //$NON-NLS-1$
        status = rRow.undo(null, formEditor);
        assertTrue("third undo", status.isOK()); //$NON-NLS-1$
        status = rRow.redo(null, formEditor);
        assertTrue("third redo", status.isOK()); //$NON-NLS-1$
        formEditor.doSave(Repository.NULL_PROGRESS_MONITOR);
        formEditor.close(false);
        //assertTrue("Can't save the editor.", page.saveEditor(formEditor, false)); //$NON-NLS-1$

    }

    /**
     * Test that the DeleteColumnCOmmand was succesfully executed and that there is one column less.
     * @throws Exception
     */
    public void testDeleteColumnCommand() throws Exception {

        FormDiagramEditor formEditor = openFormEditorWithBaseTestForForm();

        int oldNcolumn = ((Form) formEditor.getDiagram().getElement()).getNColumn();
        assertTrue("The example need to have more than 2 columns.", oldNcolumn >= 2); //$NON-NLS-1$
        RemoveColumnCommand rCol = new RemoveColumnCommand(formEditor.getDiagramEditPart(), 1);
        IStatus status = rCol.execute(null, formEditor);
        assertTrue("The remove column command wasn't execute succesfully.", status.isOK()); //$NON-NLS-1$
        status = rCol.undo(null, formEditor);
        assertTrue("first undo", status.isOK()); //$NON-NLS-1$
        status = rCol.redo(null, formEditor);
        assertTrue("first redo", status.isOK()); //$NON-NLS-1$
        status = rCol.undo(null, formEditor);
        assertTrue("second undo", status.isOK()); //$NON-NLS-1$
        status = rCol.redo(null, formEditor);
        assertTrue("second redo", status.isOK()); //$NON-NLS-1$
        status = rCol.undo(null, formEditor);
        assertTrue("third undo", status.isOK()); //$NON-NLS-1$
        status = rCol.redo(null, formEditor);
        assertTrue("third redo", status.isOK()); //$NON-NLS-1$
        formEditor.doSave(Repository.NULL_PROGRESS_MONITOR);
        formEditor.close(false);
        //assertTrue("Can't save the editor.", page.saveEditor(formEditor, false)); //$NON-NLS-1$
        assertEquals(oldNcolumn - 1, ((Form) formEditor.getDiagram().getElement()).getNColumn());

        // TODO : check that it is removed
    }


    public void testAddColumnCommand() throws Exception{

        FormDiagramEditor formEditor = openFormEditorWithBaseTestForForm();

        int oldNcolumn = ((Form) formEditor.getDiagram().getElement()).getNColumn();
        //assertTrue("The example need to have more than 2 columns.", oldNcolumn >= 2);
        AddColumnCommand aCol = new AddColumnCommand((FormEditPart) formEditor.getDiagramEditPart(), 1);
        IStatus status = aCol.execute(null, formEditor);
        assertTrue("The remove column command wasn't execute succesfully.", status.isOK());
        formEditor.doSave(Repository.NULL_PROGRESS_MONITOR);
        formEditor.close(false);
        //assertTrue("Can't save the editor.", page.saveEditor(formEditor, false));
        assertEquals(oldNcolumn + 1, ((Form) formEditor.getDiagram().getElement()).getNColumn());


    }

    public void testAddRowCommand() throws Exception{

        FormDiagramEditor formEditor = openFormEditorWithBaseTestForForm();

        AddRowCommand aCol = new AddRowCommand((FormEditPart) formEditor.getDiagramEditPart(), 1);
        IStatus status = aCol.execute(null, formEditor);
        assertTrue("The remove column command wasn't execute succesfully.", status.isOK());
        formEditor.doSave(Repository.NULL_PROGRESS_MONITOR);
        formEditor.close(false);
        //assertTrue("Can't save the editor.", page.saveEditor(formEditor, false));

    }

    public void testCompositionOfCommand() throws Exception{
        FormDiagramEditor formEditor = openFormEditorWithBaseTestForForm();

        FormEditPart formEditPart = (FormEditPart) formEditor.getDiagramEditPart();
        List<AbstractTransactionalCommand> cs = new ArrayList<AbstractTransactionalCommand>();
        cs.add(new RemoveColumnCommand(formEditPart,1));
        cs.add(new AddColumnCommand(formEditPart, 0));
        cs.add(new RemoveRowCommand(formEditPart, 1));
        cs.add(new AddRowCommand(formEditPart, 1));
        for(AbstractTransactionalCommand c : cs){
            OperationHistoryFactory.getOperationHistory().execute(c, null, formEditPart);
        }

        /*Do all the undo then redo all*/
        new UndoCommandHandler().execute(null);
        new UndoCommandHandler().execute(null);
        new UndoCommandHandler().execute(null);
        new UndoCommandHandler().execute(null);
        new RedoCommandHandler().execute(null);
        new RedoCommandHandler().execute(null);
        new RedoCommandHandler().execute(null);
        new RedoCommandHandler().execute(null);
        formEditor.doSave(Repository.NULL_PROGRESS_MONITOR);
        formEditor.close(false);
    }

    public static FormDiagramEditor openFormEditorWithBaseTestForForm() throws Exception {
        MainProcess proc = ProcessRegistry.getTestExampleProcess("BaseTestForForm_1_1.proc");

        Form form = ((PageFlow) proc.getElements().get(0)).getForm().get(0);
        /* get the Diagram element related to the form in the resource */
        Diagram diag = ModelHelper.getDiagramFor(form, null);
        URI uri = EcoreUtil.getURI(diag);

        /* open the form editor */
        return (FormDiagramEditor) EditorService.getInstance().openEditor(new URIEditorInput(uri, form.getName()));
    }

}
