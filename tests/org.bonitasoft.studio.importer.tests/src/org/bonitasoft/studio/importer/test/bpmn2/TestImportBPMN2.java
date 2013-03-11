/**
 * Copyright (C) 2009 BonitaSoft S.A.
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
package org.bonitasoft.studio.importer.test.bpmn2;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import junit.framework.TestCase;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.importer.bpmn.BPMNToProc;
import org.bonitasoft.studio.model.process.BoundaryEvent;
import org.bonitasoft.studio.model.process.CallActivity;
import org.bonitasoft.studio.model.process.Event;
import org.bonitasoft.studio.model.process.EventSubProcessPool;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.TextAnnotation;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

/**
 * @author Romain Bioteau
 *
 */
public class TestImportBPMN2 extends TestCase {

    protected org.eclipse.emf.common.util.URI toEMFURI(File file) throws MalformedURLException {
        org.eclipse.emf.common.util.URI res = URI.createFileURI(file.getAbsolutePath());
        return res;
    }

    public void testImportBPMN2() throws Exception {
        URL bpmnResource = FileLocator.toFileURL(getClass().getResource("standardProcess.bpmn")); //$NON-NLS-1$
        BPMNToProc bpmnToProc = new BPMNToProc(bpmnResource.getFile());
        File destFile = bpmnToProc.createDiagram(bpmnResource, new NullProgressMonitor());

        ResourceSet resourceSet = new ResourceSetImpl();
        Resource resource = resourceSet.getResource(toEMFURI(destFile), true);
        MainProcess mainProcess = (MainProcess)resource.getContents().get(0);

        checkContent(mainProcess, 4, 14, 3, 0, 1, null);
        destFile.delete();
    }


    public void testImportBPMN2WithUnknownDiagramNS() throws Exception {
        URL bpmnResource = FileLocator.toFileURL(getClass().getResource("standardProcess_badNameSpace.bpmn")); //$NON-NLS-1$
        BPMNToProc bpmnToProc = new BPMNToProc(bpmnResource.getFile());
        File destFile = bpmnToProc.createDiagram(bpmnResource, new NullProgressMonitor());

        ResourceSet resourceSet = new ResourceSetImpl();
        Resource resource = resourceSet.getResource(toEMFURI(destFile), true);
        MainProcess mainProcess = (MainProcess)resource.getContents().get(0);

        checkContent(mainProcess, 4, 14, 3, 0, 1, null);
        destFile.delete();
    }

    public void testImportBPMN2WithOMG_ns() throws Exception {
        URL bpmnResource = FileLocator.toFileURL(getClass().getResource("testimport.bpmn")); //$NON-NLS-1$
        BPMNToProc bpmnToProc = new BPMNToProc(bpmnResource.getFile());
        File destFile = bpmnToProc.createDiagram(bpmnResource, new NullProgressMonitor());

        ResourceSet resourceSet = new ResourceSetImpl();
        Resource resource = resourceSet.getResource(toEMFURI(destFile), true);
        MainProcess mainProcess = (MainProcess)resource.getContents().get(0);

        checkContent(mainProcess, 2, 4, 0, 0, 0, null);
        destFile.delete();
    }

    public void testBug1908a() throws Exception {
        URL bpmnResource = FileLocator.toFileURL(getClass().getResource("definitionsTest2.bpmn")); //$NON-NLS-1$
        BPMNToProc bpmnToProc = new BPMNToProc(bpmnResource.getFile());
        File destFile = bpmnToProc.createDiagram(bpmnResource, new NullProgressMonitor());

        ResourceSet resourceSet = new ResourceSetImpl();
        Resource resource = resourceSet.getResource(toEMFURI(destFile), true);
        MainProcess mainProcess = (MainProcess)resource.getContents().get(0);

        checkContent(mainProcess, 1, 2, 0, 0, 0, null);
        destFile.delete();
    }


    public void testBug1908b() throws Exception {
        File destFile = new File("destBug1908b" + System.currentTimeMillis() + ".proc");  //$NON-NLS-1$//$NON-NLS-2$
        destFile.createNewFile();

        URL bpmnResource = FileLocator.toFileURL(getClass().getResource("definitionsTest3.bpmn")); //$NON-NLS-1$
        BPMNToProc bpmnToProc = new BPMNToProc(bpmnResource.getFile());
        destFile = bpmnToProc.createDiagram(bpmnResource, new NullProgressMonitor());

        ResourceSet resourceSet = new ResourceSetImpl();
        Resource resource = resourceSet.getResource(toEMFURI(destFile), true);
        MainProcess mainProcess = (MainProcess)resource.getContents().get(0);

        checkContent(mainProcess, 1, 2, 0, 0, 0, null);
        destFile.delete();
    }

    public void testImportActivitiSamples() throws Exception {
        String[] fileNames = new String[]{
                "EasyBugFilingProcess.bpmn",
                "VacationRequest.bpmn",
                "TaskAssigneeTest.testTaskAssignee.bpmn",
        "FinancialReportProcess.bpmn"};
        for (String bpmnFileName : fileNames) {
            File destFile = importFileWithName(bpmnFileName);
            //			ResourceSet resourceSet = new ResourceSetImpl();
            //			Resource resource = resourceSet.getResource(toEMFURI(destFile), true);
            //			MainProcess mainProcess = (MainProcess)resource.getContents().get(0);
            //
            //			int pools = 0 ;
            //			for(Element e : mainProcess.getElements()){
            //				if(e instanceof Pool){
            //					pools++ ;
            //				}
            //			}
            //			assertEquals("Missing Pools", 1, pools) ; //$NON-NLS-1$
            destFile.delete();
        }


    }

    public void testImportSignavioSamples() throws Exception {
        String[] fileNames = new String[]{
                "Purchase Order-to-Delivery.bpmn",
                "Purchase Requisition-to-Purchase Order.bpmn",
        "Delivery-to-Payment.bpmn"};
        for (String bpmnFileName : fileNames) {
            File destFile = importFileWithName("signaviosamples/"+bpmnFileName);
            destFile.delete();
        }
    }

    //	public void testWrongQName(){
    //		final String qname = "https://editor";
    //		new javax.xml.namespace.QName(qname);
    //		new org.eclipse.emf.ecore.xml.type.internal.QName(qname);
    //	}


    public void testImportMessageFlow() throws MalformedURLException, IOException{
        File destFile = importFileWithName("withMessageFlow.bpmn");
        ResourceSet resourceSet = new ResourceSetImpl();
        Resource resource = resourceSet.getResource(toEMFURI(destFile), true);
        MainProcess mainProcess = (MainProcess)resource.getContents().get(0);

        checkContent(mainProcess, 2, 3, 0, 0, 0, null);
        assertEquals("Missing Message Flow", 1, mainProcess.getMessageConnections().size()); //$NON-NLS-1$
        destFile.delete();
    }

    public void testImportWithSubProc() throws MalformedURLException, IOException{
        File destFile = importFileWithName("withSubProc.bpmn");
        ResourceSet resourceSet = new ResourceSetImpl();
        Resource resource = resourceSet.getResource(toEMFURI(destFile), true);
        MainProcess mainProcess = (MainProcess)resource.getContents().get(0);

        checkContent(mainProcess, 2, 0, 0, 0, 0, "proc");

        CallActivity subprocTask = (CallActivity)ModelHelper.findElement(mainProcess, "subProc_sid_8C74FAF1_AFEE_4C3A_B66E_1190133DB509", true);


        final Pool subProcPool = (Pool)ModelHelper.findElement(mainProcess,subprocTask.getCalledActivityName().getContent(), true);
        assertNotNull("Sub proc not found",	subProcPool);

        destFile.delete();
    }

    public void testImportWithAll() throws MalformedURLException, IOException{
        File destFile = importFileWithName("withAll.bpmn");
        ResourceSet resourceSet = new ResourceSetImpl();
        Resource resource = resourceSet.getResource(toEMFURI(destFile), true);
        MainProcess mainProcess = (MainProcess)resource.getContents().get(0);

        final int expectedPools = 4;
        final int expectedEvents = 15;
        final int expectedBoundaryEvents = 3;
        final int expectedeventSubprocPool = 0;
        final int expectedTextAnnotations = 1;//TODO: it should be two but we can' timport text annotation attached to a sequenceflow
        final String poolName = "pool complex";

        checkContent(mainProcess, expectedPools, expectedEvents,
                expectedBoundaryEvents, expectedeventSubprocPool,
                expectedTextAnnotations, poolName);
        destFile.delete();
    }

    protected void checkContent(MainProcess mainProcess,
            final int expectedPools, final int expectedEvents,
            final int expectedBoundaryEvents,
            final int expectedeventSubprocPool,
            final int expectedTextAnnotations, final String poolName) {
        int pools = 0 ;
        boolean poolComplexNameFound = false;
        int boundaryEvent = 0;
        int textAnnotations = 0;
        int eventSubProcPool = 0;
        int events =0;
        TreeIterator<EObject> allElements = mainProcess.eAllContents();
        while(allElements.hasNext()){
            EObject current = allElements.next();
            if(current instanceof Pool){
                pools++ ;
                if(poolName!= null && poolName.equals(((Pool) current).getName())){
                    poolComplexNameFound = true;
                }
            } else if(current instanceof BoundaryEvent){
                boundaryEvent++;
            } else if(current instanceof TextAnnotation){
                textAnnotations++;
            } else if(current instanceof EventSubProcessPool){
                eventSubProcPool++;
            } else if(current instanceof Event){
                events++;
            }
        }


        assertEquals("Missing Pools", expectedPools, pools) ; //$NON-NLS-1$
        if(poolName != null){
            assertTrue("Issue with name of pool, not find pool with name "+poolName, poolComplexNameFound);
        }
        assertEquals("Missing BoundaryEvent", expectedBoundaryEvents, boundaryEvent) ; //$NON-NLS-1$
        assertEquals("Missing TextAnnotations", expectedTextAnnotations, textAnnotations) ; //$NON-NLS-1$
        assertEquals("Missing eventSubProcPool", /*expectedeventSubprocPool*/0, eventSubProcPool);  //$NON-NLS-1$
        assertEquals("Missing events", expectedEvents, events);  //$NON-NLS-1$
    }

    protected File importFileWithName(final String bpmnFileName)
            throws IOException, MalformedURLException {
        File destFile = new File("destbpmnFileName" + System.currentTimeMillis() + ".proc");  //$NON-NLS-1$//$NON-NLS-2$
        destFile.createNewFile();
        URL bpmnResource = FileLocator.toFileURL(getClass().getResource(bpmnFileName));
        BPMNToProc bpmnToProc = new BPMNToProc(bpmnResource.getFile());
        destFile = bpmnToProc.createDiagram(bpmnResource, new NullProgressMonitor());
        return destFile;
    }

}
