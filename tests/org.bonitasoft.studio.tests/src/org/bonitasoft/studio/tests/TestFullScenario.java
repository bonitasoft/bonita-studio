/**
 * Copyright (C) 2009-2012 BonitaSoft S.A.
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
package org.bonitasoft.studio.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;

import org.bonitasoft.studio.assertions.StatusAssert;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.diagram.custom.commands.NewDiagramCommandHandler;
import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.engine.BOSEngineManager;
import org.bonitasoft.studio.engine.command.RunProcessCommand;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.Lane;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.Task;
import org.bonitasoft.studio.model.process.diagram.part.ProcessDiagramEditor;
import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.handlers.IHandlerService;
import org.junit.Before;
import org.junit.Test;

public class TestFullScenario {

    private class CountProcessesResourceVisitor implements IResourceVisitor {

        private int nbProc;

        /*
         * (non-Javadoc)
         * @see org.eclipse.core.resources.IResourceVisitor#visit(org.eclipse.core.resources.IResource)
         */
        @Override
        public boolean visit(final IResource resource) throws CoreException {
            if (resource.getName().endsWith(DiagramFileStore.PROC_EXT)) {
                nbProc++;
            }
            return true;
        }

        public int getNbProc() {
            return nbProc;
        }

    }

    private static final String TESTNAME = "TESTNAME";
    private static ProcessDiagramEditor processEditor;
    private static IEditorInput input;
    private static MainProcess process;
    private static Task task;
    private static int nbProcBefore;
    private static String webPurchaseVersion = "1.5";

    @Before
    public void closeEditors() throws Exception {
        PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().closeAllEditors(false);
        BOSEngineManager.getInstance().start();
    }
    
    @Test
    public void testNewProcess() throws Exception {
        final CountProcessesResourceVisitor visitor = new CountProcessesResourceVisitor();
        RepositoryManager.getInstance().getCurrentRepository().getProject().accept(visitor);
        nbProcBefore = visitor.getNbProc();

        createProcess();
        editAndSave();
        reopenEditedProcess();
        execute();
    }

    protected void createProcess() throws ExecutionException {
        final DiagramFileStore newDiagram = new NewDiagramCommandHandler().newDiagram();
        newDiagram.open();
        processEditor = getTheOnlyOneEditor();
        input = processEditor.getEditorInput();

        task = getTheTask(processEditor.getDiagramEditPart());
        assertNotNull("Default process should contain one task", task);
    }

    /**
     * @throws ExecutionException
     */
    public void editAndSave() throws Exception {
        processEditor = getTheOnlyOneEditor();
        processEditor.getEditingDomain().getCommandStack().execute(
                new SetCommand(processEditor.getEditingDomain(),
                        task,
                        ProcessPackage.Literals.ELEMENT__NAME,
                        TESTNAME));

        final IHandlerService handlerService = (IHandlerService) PlatformUI.getWorkbench().getService(IHandlerService.class);
        final ICommandService commandService = (ICommandService) PlatformUI.getWorkbench().getService(ICommandService.class);
        final Command saveCommand = commandService.getCommand("org.eclipse.ui.file.save");
        final ExecutionEvent executionEvent = new ExecutionEvent(saveCommand, Collections.EMPTY_MAP, null,
                handlerService.getClass());
        saveCommand.executeWithChecks(executionEvent);
    }

    public void reopenEditedProcess() {
        processEditor = getTheOnlyOneEditor();
        task = getTheTask(processEditor.getDiagramEditPart());
        assertEquals("Task name does not match after process name changed",
                TESTNAME, task.getName());
    }

    /**
     * @throws CoreException
     * @throws ExecutionException
     */
    public void renameProcess() throws Exception {
        processEditor = getTheOnlyOneEditor();
        process = (MainProcess) processEditor.getDiagramEditPart().resolveSemanticElement();
        final MainProcess oldProc = EcoreUtil.copy(process);

        processEditor.getEditingDomain().getCommandStack().execute(new SetCommand(
                processEditor.getEditingDomain(),
                process,
                ProcessPackage.Literals.ELEMENT__NAME,
                "Web_Purchase_Diagram"));
        processEditor.getEditingDomain().getCommandStack().execute(new SetCommand(
                processEditor.getEditingDomain(),
                process,
                ProcessPackage.Literals.ABSTRACT_PROCESS__VERSION,
                webPurchaseVersion));

        final IHandlerService handlerService = (IHandlerService) PlatformUI.getWorkbench().getService(IHandlerService.class);
        final ICommandService commandService = (ICommandService) PlatformUI.getWorkbench().getService(ICommandService.class);
        final Command saveCommand = commandService.getCommand("org.eclipse.ui.file.save");
        final ExecutionEvent executionEvent = new ExecutionEvent(saveCommand, Collections.EMPTY_MAP, null,
                handlerService.getClass());

        saveCommand.executeWithChecks(executionEvent);

        assertFalse("Old input should not exist any more after rename", input.exists());
    }

    /**
     * @throws ExecutionException
     * @throws Exception
     * @throws InterruptedException
     * @throws IOException
     */
    public void execute() throws Exception {
        final RunProcessCommand deployProcessCommand = new RunProcessCommand(true);

        final IStatus status = (IStatus) deployProcessCommand.execute(new ExecutionEvent());
        assertNotNull(status);
        StatusAssert.assertThat(status).overridingErrorMessage(status.getMessage()).isOK();
        final URL url = deployProcessCommand.getUrl();
        assertNotNull(url);
        assertTrue(url.toString().contains("/bonita/"));
    }

    /**
     * @param diagramEditPart
     * @return
     */
    private Task getTheTask(final IGraphicalEditPart diagramEditPart) {
        final MainProcess process = (MainProcess) diagramEditPart.resolveSemanticElement();
        final Pool pool = (Pool) process.getElements().get(0);
        Task task = null;
        for (final Element item : pool.getElements()) {
            if (item instanceof Task) {
                task = (Task) item;
            } else if (item instanceof Lane) {
                for (final Element laneItem : ((Lane) item).getElements()) {
                    if (laneItem instanceof Task) {
                        task = (Task) laneItem;
                    }
                }
            }
        }
        return task;
    }

    /**
     * @return
     */
    private ProcessDiagramEditor getTheOnlyOneEditor() {
        IEditorPart editor;
        ProcessDiagramEditor processEditor;

        final IEditorReference[] editorReferences = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
                .getEditorReferences();
        final StringBuilder sb = new StringBuilder();
        for (final IEditorReference iEditorReference : editorReferences) {
            sb.append(iEditorReference);
        }
        assertEquals("There should be only be 1 editor after save. But there are:\n" + sb.toString(),
                1,
                editorReferences.length);
        editor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
        assertTrue(editor instanceof ProcessDiagramEditor);
        processEditor = (ProcessDiagramEditor) editor;
        return processEditor;
    }
}
