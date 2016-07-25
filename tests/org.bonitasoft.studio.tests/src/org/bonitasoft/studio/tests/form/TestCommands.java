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

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.application.actions.RedoCommandHandler;
import org.bonitasoft.studio.application.actions.UndoCommandHandler;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.jface.FileActionDialog;
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
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * @author Aurelien Pupier
 *
 */
public class TestCommands {


    private FormDiagramEditor formEditor;

    @Before
    public void setUp() throws Exception {
        FileActionDialog.setDisablePopup(true);
        formEditor = openFormEditorWithBaseTestForForm();
    }

    @After
    public void tearDown() throws Exception {
        if (formEditor != null) {
            formEditor.close(false);
        }
    }

    /**
     * Test that the deleteRowCommand was executed
     * @throws Exception
     */
    @Test
    public void testDeleteRowCommand() throws Exception {
        final RemoveRowCommand rRow = new RemoveRowCommand(formEditor.getDiagramEditPart(), 1);
        IStatus status = rRow.execute(null, formEditor);
        assertThat(status.isOK()).overridingErrorMessage("The remove column command wasn't execute succesfully.").isTrue();
        status = rRow.undo(null, formEditor);
        assertThat(status.isOK()).overridingErrorMessage("first undo").isTrue();

        status = rRow.redo(null, formEditor);
        assertThat(status.isOK()).overridingErrorMessage("first redo").isTrue();

        status = rRow.undo(null, formEditor);
        assertThat(status.isOK()).overridingErrorMessage("second undo").isTrue();

        status = rRow.redo(null, formEditor);
        assertThat(status.isOK()).overridingErrorMessage("second redo").isTrue();

        status = rRow.undo(null, formEditor);
        assertThat(status.isOK()).overridingErrorMessage("third undo").isTrue();

        status = rRow.redo(null, formEditor);
        assertThat(status.isOK()).overridingErrorMessage("third redo").isTrue();
    }

    /**
     * Test that the DeleteColumnCOmmand was succesfully executed and that there is one column less.
     * @throws Exception
     */
    @Test
    public void testDeleteColumnCommand() throws Exception {
        final int oldNcolumn = ((Form) formEditor.getDiagram().getElement()).getNColumn();
        assertThat(oldNcolumn).isGreaterThanOrEqualTo(2).overridingErrorMessage("The example need to have more than 2 columns.");

        final RemoveColumnCommand rCol = new RemoveColumnCommand(formEditor.getDiagramEditPart(), 1);
        IStatus status = rCol.execute(null, formEditor);
        assertThat(status.isOK()).overridingErrorMessage("The remove column command wasn't execute succesfully.").isTrue();

        status = rCol.undo(null, formEditor);
        assertThat(status.isOK()).isTrue();

        status = rCol.redo(null, formEditor);
        assertThat(status.isOK()).isTrue();

        status = rCol.undo(null, formEditor);
        assertThat(status.isOK()).isTrue();

        status = rCol.redo(null, formEditor);
        assertThat(status.isOK()).isTrue();

        status = rCol.undo(null, formEditor);
        assertThat(status.isOK()).isTrue();

        status = rCol.redo(null, formEditor);
        assertThat(status.isOK()).isTrue();

        assertThat(((Form) formEditor.getDiagram().getElement()).getNColumn()).isEqualTo(oldNcolumn - 1);
    }

    @Test
    public void testAddColumnCommand() throws Exception{
        final int oldNcolumn = ((Form) formEditor.getDiagram().getElement()).getNColumn();
        //assertTrue("The example need to have more than 2 columns.", oldNcolumn >= 2);
        final AddColumnCommand aCol = new AddColumnCommand((FormEditPart) formEditor.getDiagramEditPart(), 1);
        final IStatus status = aCol.execute(null, formEditor);
        assertThat(status.isOK()).isTrue();
        assertThat(((Form) formEditor.getDiagram().getElement()).getNColumn()).isEqualTo(oldNcolumn + 1);
    }

    @Test
    public void testAddRowCommand() throws Exception{
        final AddRowCommand aCol = new AddRowCommand((FormEditPart) formEditor.getDiagramEditPart(), 1);
        final IStatus status = aCol.execute(null, formEditor);
        assertThat(status.isOK()).isTrue();
    }

    @Test
    public void testCompositionOfCommand() throws Exception{
        final FormEditPart formEditPart = (FormEditPart) formEditor.getDiagramEditPart();
        final List<AbstractTransactionalCommand> cs = new ArrayList<AbstractTransactionalCommand>();
        cs.add(new RemoveColumnCommand(formEditPart,1));
        cs.add(new AddColumnCommand(formEditPart, 0));
        cs.add(new RemoveRowCommand(formEditPart, 1));
        cs.add(new AddRowCommand(formEditPart, 1));
        for(final AbstractTransactionalCommand c : cs){
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
    }

    public static FormDiagramEditor openFormEditorWithBaseTestForForm() throws Exception {
        final MainProcess proc = ProcessRegistry.getTestExampleProcess("BaseTestForForm_1_1.proc");

        final Form form = ((PageFlow) proc.getElements().get(0)).getForm().get(0);
        /* get the Diagram element related to the form in the resource */
        final Diagram diag = ModelHelper.getDiagramFor(form);
        final URI uri = EcoreUtil.getURI(diag);

        /* open the form editor */
        return (FormDiagramEditor) EditorService.getInstance().openEditor(new URIEditorInput(uri, form.getName()));
    }

}
