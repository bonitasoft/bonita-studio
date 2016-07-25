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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;

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
import org.eclipse.core.commands.NotEnabledException;
import org.eclipse.core.commands.NotHandledException;
import org.eclipse.core.commands.common.NotDefinedException;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.handlers.IHandlerService;

import junit.framework.TestCase;

/**
 * @author Mickael Istria
 */
public class TestFullScenario extends TestCase {

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

    public void testNewProcess() throws Exception {
        final CountProcessesResourceVisitor visitor = new CountProcessesResourceVisitor();
        RepositoryManager.getInstance().getCurrentRepository().getProject().accept(visitor);
        nbProcBefore = visitor.getNbProc();

        createProcess();
        editAndSave();
        reopenEditedProcess();
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
    public void editAndSave() throws ExecutionException {
        processEditor = getTheOnlyOneEditor();
        processEditor.getEditingDomain().getCommandStack().execute(
                new SetCommand(processEditor.getEditingDomain(),
                        task,
                        ProcessPackage.Literals.ELEMENT__NAME,
                        TESTNAME));

        final IHandlerService handlerService = (IHandlerService) PlatformUI.getWorkbench().getService(IHandlerService.class);
        final ICommandService commandService = (ICommandService) PlatformUI.getWorkbench().getService(ICommandService.class);
        final Command saveCommand = commandService.getCommand("org.eclipse.ui.file.save");
        final ExecutionEvent executionEvent = new ExecutionEvent(saveCommand, Collections.EMPTY_MAP, null, handlerService.getClass());
        try {
            saveCommand.executeWithChecks(executionEvent);
        } catch (final NotDefinedException e) {
            e.printStackTrace();
        } catch (final NotEnabledException e) {
            e.printStackTrace();
        } catch (final NotHandledException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     */
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
    public void renameProcess() throws CoreException, ExecutionException {
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
        final ExecutionEvent executionEvent = new ExecutionEvent(saveCommand, Collections.EMPTY_MAP, null, handlerService.getClass());
        try {
            saveCommand.executeWithChecks(executionEvent);
        } catch (final NotDefinedException e) {
            e.printStackTrace();
        } catch (final NotEnabledException e) {
            e.printStackTrace();
        } catch (final NotHandledException e) {
            e.printStackTrace();
        }

        assertFalse("Old input should not exist any more after rename", input.exists());
    }

    /**
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws FileNotFoundException
     * @throws CoreException
     */
    //    public void importAndOverride() throws Exception {
    //        //check that the previous test still get opened the same process with same version/name
    //        MainProcess diagram = (MainProcess) getTheOnlyOneEditor().getDiagramEditPart().resolveSemanticElement();
    //        ExportBosArchiveOperation op = new ExportBosArchiveOperation();
    //        String destPath = ProjectUtil.getBonitaStudioWorkFolder().getAbsolutePath()+File.separatorChar+"testExport.bos";
    //        op.setDestinationPath(destPath);
    //
    //        ExportBosArchiveHandler handler = new ExportBosArchiveHandler();
    //        Set<IResource> resourcesToExport = new HashSet<IResource>();
    //        for(Object file :handler.getAllDiagramRelatedFiles(diagram)){
    //            if(((IRepositoryFileStore) file).getResource() != null && ((IRepositoryFileStore) file).getResource().exists()){
    //                resourcesToExport.add(((IRepositoryFileStore) file).getResource()) ;
    //            }
    //            if(!((IRepositoryFileStore) file).getRelatedResources().isEmpty()){
    //                resourcesToExport.addAll(((IRepositoryFileStore) file).getRelatedResources()) ;
    //            }
    //        }
    //        op.setResources(resourcesToExport);
    //        op.run(new NullProgressMonitor());
    //
    //        TransactionalEditingDomain editingDomain = getTheOnlyOneEditor().getDiagramEditPart().getEditingDomain();
    //        editingDomain.getCommandStack().execute(AddCommand.create(editingDomain, diagram.getElements().get(0), ProcessPackage.Literals.CONTAINER__ELEMENTS, ProcessFactory.eINSTANCE.createActivity()));
    //
    //        ImportBosArchiveOperation importOp = new ImportBosArchiveOperation();
    //        importOp.setArchiveFile(destPath);
    //        importOp.run(new NullProgressMonitor());
    //
    //        processEditor = getTheOnlyOneEditor();
    //
    //        process = (MainProcess) processEditor.getDiagramEditPart().resolveSemanticElement();
    //        Pool pool = (Pool)process.getElements().get(0);
    //        int nbActivity = 0;
    //        for (Element item : pool.getElements()) {
    //            if (item instanceof Activity) {
    //                nbActivity++;
    //            }
    //        }
    //        assertTrue("The new Web Purchase process should be more complicated", nbActivity == 1);
    //        RepositoryManager.getInstance().getCurrentRepository().getProject().refreshLocal(IResource.DEPTH_INFINITE, new NullProgressMonitor());
    //
    //
    //        assertProjectContainsNFile(nbProcBefore + 1);
    //        processEditor = getTheOnlyOneEditor();
    //        new SaveCommandHandler().execute(null);
    //        assertProjectContainsNFile(nbProcBefore + 1);
    //
    //    }

    /**
     * @throws ExecutionException
     * @throws Exception
     * @throws InterruptedException
     * @throws IOException
     */
    public void execute() throws ExecutionException, Exception, InterruptedException, IOException {
        BOSEngineManager.getInstance().start();
        final RunProcessCommand deployProcessCommand = new RunProcessCommand(true);

        deployProcessCommand.execute(new ExecutionEvent());
        final BufferedReader reader = new BufferedReader(new InputStreamReader((InputStream) deployProcessCommand.getUrl().getContent()));
        String line = null;
        while ((line = reader.readLine()) != null) {
            if (line.toLowerCase().contains("bonita")) {
                return; // OK
            }
        }
        fail("A line in application should contain \"Bonita\"...");
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

        final IEditorReference[] editorReferences = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getEditorReferences();
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
