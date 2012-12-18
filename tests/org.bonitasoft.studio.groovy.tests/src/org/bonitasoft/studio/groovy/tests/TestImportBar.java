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
package org.bonitasoft.studio.groovy.tests;

import junit.framework.TestCase;

/**
 * @author Mickael Istria
 *
 */
public class TestImportBar extends TestCase {

    public void testImportBarBefore_5_2() throws Exception {
        fail("testImportBarBefore_5_2 not implemented");

        //		URL fileURL = FileLocator.toFileURL(getClass().getResource("Arrival_of_a_New_Employee_3.8.bar")); //$NON-NLS-1$
        //		barProcessor.createDiagram(fileURL, new NullProgressMonitor());
        //
        //		ProcessArtifact artifact = ProcessRepository.ImportBARModule.importProcOrBar(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
        //				new File(fileURL.getFile()));
        //		IFolder typeFolder = GroovyScriptRepository.getInstance().getProject().getFolder(GroovyScriptRepository.SRC_GROOVY_FOLDER).getFolder(NamingUtils.convertToPackage(artifact.getProcess().getName(), artifact.getProcess().getVersion()));
        //		assertTrue("Missing groovy types folder", typeFolder.exists()); //$NON-NLS-1$
        //		assertTrue("Missing groovy types in folder", typeFolder.members().length > 0); //$NON-NLS-1$
        //
        //		PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().closeAllEditors(false);

    }

    public void testImportBarAfter_5_2() throws Exception {
        fail();
        //		IFolder srcFolder = GroovyScriptRepository.getInstance().getProject().getFolder(GroovyScriptRepository.SRC_GROOVY_FOLDER);
        //		int artifactBefore = srcFolder.members().length ;
        //
        //		URL fileURL = FileLocator.toFileURL(getClass().getResource("Arrival_of_a_New_Employee_3.9.bar")); //$NON-NLS-1$
        //		ProcessArtifact artifact = ProcessRepository.ImportBARModule.importProcOrBar(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
        //				new File(fileURL.getFile()));
        //
        //		IFolder typeFolder = srcFolder.getFolder(NamingUtils.convertToPackage(artifact.getProcess().getName(), artifact.getProcess().getVersion()));
        //
        //		assertTrue("Missing groovy types folder", typeFolder.exists()); //$NON-NLS-1$
        //		assertTrue("Missing groovy types in folder", typeFolder.members().length > 0); //$NON-NLS-1$
        //		assertTrue("More Artifact than expected after Import", srcFolder.members().length == artifactBefore+1); //$NON-NLS-1$
        //
        //		artifact.open() ;
        //		ProcessDiagramEditor editor = (ProcessDiagramEditor)PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor() ;
        //		MainProcess proc = (MainProcess) editor.getDiagramEditPart().resolveSemanticElement() ;
        //		editor.getEditingDomain().getCommandStack().execute(new SetCommand(
        //				editor.getEditingDomain(),
        //				proc,
        //				ProcessPackage.Literals.ELEMENT__NAME,
        //		"New_Name")); //$NON-NLS-1$
        //
        //		System.out.println(editor.isDirty());
        //
        //		IHandlerService handlerService = (IHandlerService) PlatformUI.getWorkbench().getService(IHandlerService.class);
        //		ICommandService commandService = (ICommandService)  PlatformUI.getWorkbench().getService(ICommandService.class);
        //		Command saveCommand = commandService.getCommand("org.eclipse.ui.file.save");
        //		ExecutionEvent executionEvent = new ExecutionEvent(saveCommand, Collections.EMPTY_MAP, null, handlerService.getClass());
        //		try {
        //			saveCommand.executeWithChecks(executionEvent);
        //		} catch (NotDefinedException e) {
        //			e.printStackTrace();
        //		} catch (NotEnabledException e) {
        //			e.printStackTrace();
        //		} catch (NotHandledException e) {
        //			e.printStackTrace();
        //		}
        //
        //		IFolder newTypeFolder = srcFolder.getFolder(NamingUtils.convertToPackage("New_Name",proc.getVersion()));
        //
        //		assertTrue("Old folder type should have been deleted", !typeFolder.exists()); //$NON-NLS-1$
        //		assertTrue("New folder type should exists", newTypeFolder.exists()); //$NON-NLS-1$
        //
        //		List<String> datatypes = new ArrayList<String>();
        //		for(EnumType et : ModelHelper.getAllUserDatatype(proc)){
        //			datatypes.add(et.getName());
        //		}
        //		List<String> script = new ArrayList<String>();
        //		for(IResource res : newTypeFolder.members()){
        //			script.add(res.getName().replace(".groovy", ""));
        //		}
        //		boolean match = true ;
        //		assertTrue("Missing type in NewFolder",script.size() == datatypes.size());
        //
        //		for(int i = 0 ; i<datatypes.size() ;i++){
        //			if(!script.contains(datatypes.get(i))){
        //				match = false ;
        //			}
        //			if(match == false) break ;
        //		}
        //
        //		assertTrue("Types names doesn't match", match);

    }


}
