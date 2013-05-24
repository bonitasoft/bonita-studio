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
package org.bonitasoft.studio.application.test.deploycommand;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import org.bonitasoft.engine.api.ProcessManagementAPI;
import org.bonitasoft.engine.bpm.process.ProcessDefinitionCriterion;
import org.bonitasoft.engine.bpm.process.ProcessDeploymentInfo;
import org.bonitasoft.engine.session.APISession;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.operation.ImportBosArchiveOperation;
import org.bonitasoft.studio.engine.BOSEngineManager;
import org.bonitasoft.studio.engine.command.RunProcessCommand;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.diagram.part.ProcessDiagramEditor;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gmf.runtime.emf.core.GMFEditingDomainFactory;
import org.eclipse.ui.PlatformUI;

/**
 * @author Aurelien Pupier
 */
public class TestDeployCommand extends TestCase {

    /**
     * Used to check bug 1965
     * @throws Exception
     */
    public void testDeployCommandWithSubProcessLoop() throws Exception{
        /*import the two process for the test*/
        ImportBosArchiveOperation op = new ImportBosArchiveOperation();
        URL fileURL = FileLocator.toFileURL(TestDeployCommand.class.getResource("ProcessForSubProcessLoopTest-1-1.0.bos")); //$NON-NLS-1$
        op.setArchiveFile(FileLocator.toFileURL(fileURL).getFile());
        op.run(new NullProgressMonitor());
        ProcessDiagramEditor processEditor = (ProcessDiagramEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
        MainProcess mainProcess=(MainProcess)processEditor.getDiagramEditPart().resolveSemanticElement();

        URL fileURL2 = FileLocator.toFileURL(TestDeployCommand.class.getResource("ProcessForSubProcessLoopTest-2-1.0.bos")); //$NON-NLS-1$
        op.setArchiveFile(FileLocator.toFileURL(fileURL2).getFile());
        op.run(new NullProgressMonitor());

        /*And deploy it twice*/
        final RunProcessCommand runProcessCommand = new RunProcessCommand(true);
        Map<String,Object> param = new HashMap<String, Object>();
        param.put(RunProcessCommand.PROCESS, mainProcess.getElements().get(0));
        ExecutionEvent ee = new ExecutionEvent(null,param,null,null);
        runProcessCommand.execute(ee);
        runProcessCommand.execute(ee);

        //		/*import the two process for the test*/

        //		URL fileURL1 = FileLocator.toFileURL(getClass().getResource("ProcessForSubProcessLoopTest_1--1.0.bar")); //$NON-NLS-1$
        //		ProcessArtifact artifact1 = ProcessRepository.ImportBARModule.importProcOrBar(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
        //				new File(fileURL1.getFile()));
        //
        //		URL fileURL2 = FileLocator.toFileURL(getClass().getResource("ProcessForSubProcessLoopTest_2--1.0.bar")); //$NON-NLS-1$
        //		ProcessRepository.ImportBARModule.importProcOrBar(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
        //				new File(fileURL2.getFile()));
        //		/*And deploy it twice*/
        //		new RunProcessCommand(artifact1.getProcess(),true).execute(null);
        //
        //		//		new RunProcessCommand(artifact1.getProcess(),true).execute(null);
        //		//		/*Just check that there is no infinite loop*/
    }

    /**
     * used to check fix 2204
     * @throws Exception
     * 
     */
    public void testDeployAfterRenamedOfParentProcess() throws Exception {
        /*Import a base process for the test*/
        ImportBosArchiveOperation op = new ImportBosArchiveOperation();
        URL fileURL = FileLocator.toFileURL(TestDeployCommand.class.getResource("ProcessForTestBug2204.bos")); //$NON-NLS-1$
        op.setArchiveFile(FileLocator.toFileURL(fileURL).getFile());
        op.run(new NullProgressMonitor());
        ProcessDiagramEditor processEditor = (ProcessDiagramEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
        MainProcess mainProcess=(MainProcess)processEditor.getDiagramEditPart().resolveSemanticElement();

        /*Run a first time*/
        final RunProcessCommand runProcessCommand = new RunProcessCommand(true);
        Map<String,Object> param = new HashMap<String, Object>();
        param.put(RunProcessCommand.PROCESS, mainProcess.getElements().get(0));
        ExecutionEvent ee = new ExecutionEvent(null,param,null,null);
        runProcessCommand.execute(ee);

        /*Rename the parent*/
        final Pool parentProcess = (Pool) mainProcess.getElements().get(0);
        TransactionalEditingDomain domain = GMFEditingDomainFactory.getInstance().getEditingDomain(parentProcess.eResource().getResourceSet());
        if(domain == null){
            domain = TransactionUtil.getEditingDomain(parentProcess);
        }
        if(domain == null){
            domain = GMFEditingDomainFactory.getInstance().createEditingDomain();
        }
        CompoundCommand cc = new CompoundCommand() ;
        cc.append(SetCommand.create(domain, parentProcess, ProcessPackage.eINSTANCE.getElement_Name(), "ParentRenamed")) ;
        // cc.append(SetCommand.create(domain,parentProcess, ProcessPackage.eINSTANCE.getAbstractProcess_Version(), version)) ;
        domain.getCommandStack().execute(cc) ;
        //		domain.getCommandStack().execute(new RecordingCommand(domain) {
        //
        //			@Override
        //			protected void doExecute() {
        //				parentProcess.setName("ParentRenamed");
        //			}
        //		});
        //		parentProcess.eResource().save(Collections.EMPTY_MAP) ;
        //
        //
        processEditor.doSave(Repository.NULL_PROGRESS_MONITOR);
        /*Retry to deploy*/
        runProcessCommand.execute(ee);

        APISession session = null ;
        try{
            session = BOSEngineManager.getInstance().loginDefaultTenant(Repository.NULL_PROGRESS_MONITOR) ;
            ProcessManagementAPI processAPI = BOSEngineManager.getInstance().getProcessAPI(session) ;
            int nbProcess = (int) processAPI.getNumberOfProcesses() ;
            List<ProcessDeploymentInfo> infos = processAPI.getProcesses(0, nbProcess, ProcessDefinitionCriterion.DEFAULT) ;
            boolean found = false;
            for(ProcessDeploymentInfo info : infos){
                if(info.getName().equals(parentProcess.getName()) && info.getVersion().equals(parentProcess.getVersion())){
                    found = true ;
                }
            }
            assertTrue("Process not found", found) ;
        }catch (Exception e) {
            fail() ;
        }finally{
            if(session != null){
                BOSEngineManager.getInstance().logoutDefaultTenant(session) ;
            }
        }
        //		URL fileURLOfParent = FileLocator.toFileURL(getClass().getResource("ParentProcess--1.0.bar")); //$NON-NLS-1$
        //		ProcessArtifact artifact1 = ProcessRepository.ImportBARModule.importProcOrBar(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
        //				new File(fileURLOfParent.getFile()));
        //		//		URL fileURLOfChild = FileLocator.toFileURL(getClass().getResource("ChildProcess--1.0.bar")); //$NON-NLS-1$
        //		//		ProcessRepository.ImportBARModule.importProcOrBar(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
        //		//				new File(fileURLOfChild.getFile()));
        //
        //		/*Run a first time*/
        //		new RunProcessCommand(artifact1.getProcess(),true).execute(null);
        //
        //		/*Rename the parent*/
        //		final Pool parentProcess = (Pool) artifact1.getProcess().getElements().get(0);
        //		TransactionalEditingDomain domain = GMFEditingDomainFactory.getInstance().getEditingDomain(parentProcess.eResource().getResourceSet());
        //		if(domain == null){
        //			domain = TransactionUtil.getEditingDomain(parentProcess);
        //		}
        //		if(domain == null){
        //			domain = GMFEditingDomainFactory.getInstance().createEditingDomain();
        //		}
        //
        //		domain.getCommandStack().execute(new RecordingCommand(domain) {
        //
        //			@Override
        //			protected void doExecute() {
        //				parentProcess.setName("ParentRenamed");
        //				parentProcess.setLabel("ParentRenamed");
        //			}
        //		});
        //		parentProcess.eResource().save(Collections.EMPTY_MAP) ;
        //
        //		/*Retry to deploy*/
        //		new RunProcessCommand((MainProcess)parentProcess.eContainer(),true).execute(null);
        //
        //
        //		APISession session = null ;
        //		try{
        //			session = BOSEngineManager.getInstance().loginDefaultTenant() ;
        //			ProcessManagementAPI processAPI = BOSEngineManager.getInstance().getProcessAPI(session) ;
        //			int nbProcess = (int) processAPI.getNumberOfProcesses() ;
        //			List<ProcessDeploymentInfo> infos = processAPI.getProcesses(0, nbProcess, ProcessDefinitionCriterion.DEFAULT) ;
        //			boolean found = false;
        //			for(ProcessDeploymentInfo info : infos){
        //				if(info.getName().equals(parentProcess.getName()) && info.getVersion().equals(parentProcess.getVersion())){
        //					found = true ;
        //				}
        //			}
        //			assertTrue("Process not found", found) ;
        //		}catch (Exception e) {
        //			fail() ;
        //		}finally{
        //			if(session != null){
        //				BOSEngineManager.getInstance().loginDefaultTenant(session) ;
        //			}
        //		}


    }


}
