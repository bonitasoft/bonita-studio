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
package org.bonitasoft.studio.exporter.tests.bpmn;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.exporter.bpmn.transfo.BonitaToBPMN;
import org.bonitasoft.studio.exporter.extension.BonitaModelExporterImpl;
import org.bonitasoft.studio.exporter.extension.IBonitaModelExporter;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.Lane;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.Task;
import org.bonitasoft.studio.model.process.diagram.edit.parts.LaneEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.MainProcessEditPart;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.gmf.runtime.diagram.ui.OffscreenEditPartFactory;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.core.GMFEditingDomainFactory;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.ui.PlatformUI;
import org.junit.Assert;
import org.junit.Test;
import org.omg.spec.bpmn.di.BPMNDiagram;
import org.omg.spec.bpmn.di.BPMNPlane;
import org.omg.spec.bpmn.di.BPMNShape;
import org.omg.spec.bpmn.di.util.DiResourceFactoryImpl;
import org.omg.spec.bpmn.model.DocumentRoot;
import org.omg.spec.bpmn.model.TActivity;
import org.omg.spec.bpmn.model.TArtifact;
import org.omg.spec.bpmn.model.TBaseElement;
import org.omg.spec.bpmn.model.TBoundaryEvent;
import org.omg.spec.bpmn.model.TCollaboration;
import org.omg.spec.bpmn.model.TDataObject;
import org.omg.spec.bpmn.model.TDefinitions;
import org.omg.spec.bpmn.model.TEvent;
import org.omg.spec.bpmn.model.TFlowElement;
import org.omg.spec.bpmn.model.TFlowNode;
import org.omg.spec.bpmn.model.TIntermediateThrowEvent;
import org.omg.spec.bpmn.model.TItemDefinition;
import org.omg.spec.bpmn.model.TLane;
import org.omg.spec.bpmn.model.TLaneSet;
import org.omg.spec.bpmn.model.TProcess;
import org.omg.spec.bpmn.model.TRootElement;
import org.omg.spec.bpmn.model.TSignal;
import org.omg.spec.bpmn.model.TSubProcess;
import org.omg.spec.bpmn.model.TTextAnnotation;
import org.omg.spec.bpmn.model.TThrowEvent;
import org.omg.spec.dd.dc.Bounds;
import org.omg.spec.dd.di.DiagramElement;
import org.omg.spec.dd.di.Shape;

/**
 * @author Aurelien Pupier
 */
public class BPMNImportExportTest {

	@Test
	public void testImportExportWithAll() throws MalformedURLException, IOException, InterruptedException{
		final String bpmnFileName = "withAll.bpmn";
		doTest(bpmnFileName);
	}

	@Test
	public void testImportExportTwoPools() throws MalformedURLException, IOException, InterruptedException{
		final String bpmnFileName = "two pools.bpmn";
		doTest(bpmnFileName);
	}

	@Test
	public void testImportExportEasyBugFillingProcess() throws MalformedURLException, IOException, InterruptedException{
		final String bpmnFileName = "EasyBugFilingProcess.bpmn";
		doTest(bpmnFileName);
	}

	@Test
	public void testImportExportWithMessageFlow() throws MalformedURLException, IOException, InterruptedException{
		final String bpmnFileName = "withMessageFlow.bpmn";
		doTest(bpmnFileName);
	}

	@Test
	public void testImportExportDeliverytoPayment() throws MalformedURLException, IOException, InterruptedException{
		final String bpmnFileName = "Delivery-to-Payment.bpmn";
		doTest(bpmnFileName);
	}

	@Test
	public void testImportExportBPMN2Correlation() throws MalformedURLException, IOException, InterruptedException{
		final String bpmnFileName = "bpmn2sample/Correlation/Buyer Seller/CorrelationExampleSeller.bpmn";
		doTest(bpmnFileName, true, true, false);
	}

	@Test
	public void testImportExportBPMN2SamplePizza() throws MalformedURLException, IOException, InterruptedException{
		final String bpmnFileName = "bpmn2sample/Pizza/triso - Order Process for Pizza V4.bpmn";
		doTest(bpmnFileName, true, true, false);
	}

	@Test
	public void testImportExportBPMN2SampleDiagramInterchangeChoreo() throws MalformedURLException, IOException, InterruptedException{
		final String bpmnFileName = "bpmn2sample/Diagram Interchange/Examples - DI - Choreography.bpmn";
		doTest(bpmnFileName, true, true, false);
	}

	@Test
	public void testImportExportBPMN2SampleDiagramInterchangeExpSubProc() throws MalformedURLException, IOException, InterruptedException{
		final String bpmnFileName = "bpmn2sample/Diagram Interchange/Examples - DI - Expanded Sub-Process.bpmn";
		doTest(bpmnFileName);
	}

	@Test
	public void testImportExportBPMN2SampleDiagramInterchangeLanesAndNestedLanes() throws MalformedURLException, IOException, InterruptedException{
		final String bpmnFileName = "bpmn2sample/Diagram Interchange/Examples - DI - Lanes and Nested Lanes.bpmn";
		doTest(bpmnFileName, true, true, false);
		//we don't support nested lanes
		//and so we don't have te same number of pool
	}

	@Test
	public void testImportExportBPMN2SampleDiagramInterchangeVerticalCollaboration() throws MalformedURLException, IOException, InterruptedException{
		final String bpmnFileName = "bpmn2sample/Diagram Interchange/Examples - DI - Vertical Collaboration.bpmn";
		doTest(bpmnFileName, true, true, false);
	}

	@Test
	public void testImportExportBPMN2SampleDiagramInterchangecollapsedSubProc() throws MalformedURLException, IOException, InterruptedException{
		final String bpmnFileName = "bpmn2sample/Diagram Interchange/Examples - DI -Collapsed Sub-Process.bpmn";
		doTest(bpmnFileName);
	}

	@Test
	public void testImportExportBPMN2SampleHardwareretailer() throws MalformedURLException, IOException, InterruptedException{
		final String bpmnFileName = "bpmn2sample/Hardware Retailer/triso - Hardware Retailer v2.bpmn";
		doTest(bpmnFileName);
	}

	@Test
	public void testImportExportBPMN2SampleIncidentManagementCollChor() throws MalformedURLException, IOException, InterruptedException{
		final String bpmnFileName = "bpmn2sample/Incident Management/Incident Management - coll chor.bpmn";
		doTest(bpmnFileName, true, true, false);
	}

	@Test
	public void testImportExportBPMN2SampleIncidentManagementLevel1() throws MalformedURLException, IOException, InterruptedException{
		final String bpmnFileName = "bpmn2sample/Incident Management/Incident Management level 1.bpmn";
		doTest(bpmnFileName, true, true, false);
	}

	@Test
	public void testImportExportBPMN2SampleIncidentManagementAccounManager() throws MalformedURLException, IOException, InterruptedException{
		final String bpmnFileName = "bpmn2sample/Incident Management/Incident Management(Account Manager Only).bpmn";
		doTest(bpmnFileName, true, true, false);
	}

	@Test
	public void testImportExportBPMN2SampleIncidentManagementProcEngine() throws MalformedURLException, IOException, InterruptedException{
		final String bpmnFileName = "bpmn2sample/Incident Management/Incident Management(Process Engine Executable).bpmn";
		doTest(bpmnFileName);
	}

	@Test
	public void testImportExportBPMN2SampleIncidentManagementProcEngineOnly() throws MalformedURLException, IOException, InterruptedException{
		final String bpmnFileName = "bpmn2sample/Incident Management/Incident Management(Process Engine Only).bpmn";
		doTest(bpmnFileName);
	}

	@Test
	public void testImportExportBPMN2SampleIncidentManagementWholeCollab() throws MalformedURLException, IOException, InterruptedException{
		final String bpmnFileName = "bpmn2sample/Incident Management/Incident Management(Whole Collab).bpmn";
		doTest(bpmnFileName, true, true, false);
	}

	@Test
	public void testImportExportBPMN2SampleEmailVoting() throws MalformedURLException, IOException, InterruptedException{
		final String bpmnFileName = "bpmn2sample/eMail Voting/Email Voting 2.bpmn";
		doTest(bpmnFileName, true, true, false);
	}

	@Test
	public void testImportExportBPMN2SamplModelsDiagramCallActivity() throws MalformedURLException, IOException, InterruptedException{
		final String bpmnFileName = "bpmn2sample/Models & Diagrams/Call Activity.bpmn";
		doTest(bpmnFileName);
	}

	@Test
	public void testImportExportBPMN2SamplModelsDiagramCollapsedSubProc() throws MalformedURLException, IOException, InterruptedException{
		final String bpmnFileName = "bpmn2sample/Models & Diagrams/Collapsed SubProcess.bpmn";
		doTest(bpmnFileName);
	}

	@Test
	public void testImportExportBPMN2SamplModelsDiagramExpandedSubProcess() throws MalformedURLException, IOException, InterruptedException{
		final String bpmnFileName = "bpmn2sample/Models & Diagrams/Expanded SubProcess.bpmn";
		doTest(bpmnFileName);
	}

	@Test
	public void testImportExportBPMN2SamplModelsDiagramLaneset() throws MalformedURLException, IOException, InterruptedException{
		final String bpmnFileName = "bpmn2sample/Models & Diagrams/Laneset.bpmn";
		doTest(bpmnFileName);
	}

	@Test
	public void testImportExportBPMN2SamplModelsDiagramPool() throws MalformedURLException, IOException, InterruptedException{
		final String bpmnFileName = "bpmn2sample/Models & Diagrams/Pool.bpmn";
		doTest(bpmnFileName);
	}

	@Test
	public void testImportExportBPMN2SamplModelsDiagramProcess() throws MalformedURLException, IOException, InterruptedException{
		final String bpmnFileName = "bpmn2sample/Models & Diagrams/Process.bpmn";
		doTest(bpmnFileName);
	}

	@Test
	public void testImportExportBPMN2SampleNobelPrize() throws MalformedURLException, IOException, InterruptedException{
		final String bpmnFileName = "bpmn2sample/Nobel Prize/Nobel Prize Process.bpmn";
		doTest(bpmnFileName, true, true, false);
	}

	@Test
	public void testImportExportBPMN2SampleOrderFullFillment() throws MalformedURLException, IOException, InterruptedException{
		final String bpmnFileName = "bpmn2sample/Order Fulfillment/Procurement Processes with Error Handling - Stencil Trisotech 3 pages.bpmn";
		doTest(bpmnFileName, false, false, true);
	}

	@Test
	public void testImportExportBPMN2SampleTravelBooking() throws MalformedURLException, IOException, InterruptedException{
		final String bpmnFileName = "bpmn2sample/Travel Booking/Tavel Booking.bpmn";
		doTest(bpmnFileName, false, false, true);
	}

	/*Bruce samples*/
	@Test
	public void testImportExportBruceSampleMyTask() throws MalformedURLException, IOException, InterruptedException{
		final String bpmnFileName = "brucesample/myTask.bpmn";
		doTest(bpmnFileName);
	}

	@Test
	public void testImportExportBruceSampleMyTaskMyPool() throws MalformedURLException, IOException, InterruptedException{
		final String bpmnFileName = "brucesample/myTaskMyPool.bpmn";
		doTest(bpmnFileName);
	}


	@Test
	public void testImportExportBruceSampleMyTaskMyPoolCorrected() throws MalformedURLException, IOException, InterruptedException{
		final String bpmnFileName = "brucesample/myTaskMyPool-corrected.bpmn";
		doTest(bpmnFileName);
	}

	@Test
	public void testImportExportBruceSampleOurProc() throws MalformedURLException, IOException, InterruptedException{
		final String bpmnFileName = "brucesample/ourProc.bpmn";
		doTest(bpmnFileName);
	}

	@Test
	public void testImportExportBruceSampleOurProcNoPool() throws MalformedURLException, IOException, InterruptedException{
		final String bpmnFileName = "brucesample/ourProcNoPool.bpmn";
		doTest(bpmnFileName);
	}

	@Test
	public void testImportExportBruceSampleOurProcNoPoolCorrected() throws MalformedURLException, IOException, InterruptedException{
		final String bpmnFileName = "brucesample/ourProcNoPool-corrected.bpmn";
		doTest(bpmnFileName);
	}

	@Test
	public void testImportExportBruceSampleOurReusable() throws MalformedURLException, IOException, InterruptedException{
		final String bpmnFileName = "brucesample/ourReusable.bpmn";
		doTest(bpmnFileName);
	}

	@Test
	public void testImportExportBruceSampleOurReusableCorrected() throws MalformedURLException, IOException, InterruptedException{
		final String bpmnFileName = "brucesample/ourReusable-corected.bpmn";
		doTest(bpmnFileName);
	}

	@Test
	public void testImportExportBruceSampleOurSubs() throws MalformedURLException, IOException, InterruptedException{
		final String bpmnFileName = "brucesample/ourSubs.bpmn";
		doTest(bpmnFileName);
	}

	@Test
	public void testImportExportBruceSampleOurSubsCorrected() throws MalformedURLException, IOException, InterruptedException{
		final String bpmnFileName = "brucesample/ourSubs-corrected.bpmn";
		doTest(bpmnFileName);
	}

	@Test
	public void testImportExportBruceSampleOurSubsNoPool() throws MalformedURLException, IOException, InterruptedException{
		final String bpmnFileName = "brucesample/ourSubsNoPool.bpmn";
		doTest(bpmnFileName);
	}

	@Test
	public void testImportExportBruceSampleOurSubsNoPoolCorrected() throws MalformedURLException, IOException, InterruptedException{
		final String bpmnFileName = "brucesample/ourSubsNoPool-corrected.bpmn";
		doTest(bpmnFileName);
	}
	
	@Test
	public void testImportExportPositionsForSeveralPoolWithoutLanes() throws MalformedURLException, IOException, InterruptedException{
		final String bpmnFileName = "Interchange Demo.bpmn";
		doTest(bpmnFileName);
	}
	
	@Test
	public void testImportExportWithEmptyLaneset() throws MalformedURLException, IOException, InterruptedException{
		final String bpmnFileName = "Front office.bpmn";
		doTest(bpmnFileName);
	}


	protected void doTest(final String bpmnFileName) throws MalformedURLException, IOException, InterruptedException{
		doTest(bpmnFileName, true, true, true);
	}

	protected void doTest(final String bpmnFileName, boolean checkActivities, boolean checkEvents, boolean checkMessageFlow) throws IOException,MalformedURLException, InterruptedException {
		File destFile = BPMNTestUtil.importFileWithName(BPMNImportExportTest.class, bpmnFileName);
		try{
			ResourceSet resourceSet = new ResourceSetImpl();
			GMFEditingDomainFactory.getInstance().createEditingDomain(resourceSet);
			Resource resource = resourceSet.getResource(BPMNTestUtil.toEMFURI(destFile), true);
			MainProcess mainProcess = (MainProcess)resource.getContents().get(0);

			checkSemantic(bpmnFileName,
					checkActivities,
					checkEvents,
					checkMessageFlow,
					mainProcess);

			/*Check graphic*/
			checkGraphic(bpmnFileName, mainProcess);
		}finally{
			if(destFile != null){
				destFile.delete();
			}
		}
	}

	protected void checkSemantic(final String bpmnFileName,
			boolean checkActivities, boolean checkEvents,
			boolean checkMessageFlow, MainProcess mainProcess)
					throws IOException {
		final Diagram diagramFor = ModelHelper.getDiagramFor(mainProcess,null);
		DiagramEditPart dep;
		try{
			dep = OffscreenEditPartFactory.getInstance().createDiagramEditPart(diagramFor, PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell());
		} catch(Exception ex){
			dep = OffscreenEditPartFactory.getInstance().createDiagramEditPart(diagramFor, PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell());
		}
		MainProcessEditPart mped = (MainProcessEditPart) dep;
		IBonitaModelExporter exporter = new BonitaModelExporterImpl(mped) ;
		File bpmnFileExported = File.createTempFile("withAllExported", ".bpmn");
		final boolean transformed = new BonitaToBPMN().transform(exporter, bpmnFileExported, new NullProgressMonitor());
		Assert.assertTrue("Error during export", transformed);

		//compare bpmn before import and after import/export
		ResourceSet resourceSet1 = new ResourceSetImpl();
		final Map<String, Object> extensionToFactoryMap = resourceSet1.getResourceFactoryRegistry().getExtensionToFactoryMap();
		final DiResourceFactoryImpl diResourceFactoryImpl = new DiResourceFactoryImpl();
		extensionToFactoryMap.put("bpmn", diResourceFactoryImpl);
		URL bpmnResource = FileLocator.toFileURL(BPMNImportExportTest.class.getResource(bpmnFileName));

		System.out.println("bpmnFileName = "+bpmnFileName);
		System.out.println("bpmnResource = "+bpmnResource);
		URI emfuri = BPMNTestUtil.toEMFURI(new File(bpmnResource.getFile()));
		System.out.println("URI.createFileURI with : "+emfuri);
		Resource resource1 = null;
		Resource resource2 = null;
		try{
	
			resource1 = resourceSet1.createResource(emfuri);
			resource1.load(Collections.emptyMap());
			resource2 = resourceSet1.createResource(URI.createFileURI(bpmnFileExported.getAbsolutePath()));
			resource2.load(Collections.emptyMap());

			DocumentRoot model1 = (DocumentRoot) resource1.getContents().get(0);
			DocumentRoot model2 = (DocumentRoot) resource2.getContents().get(0);
			int nbProcess1 = 0;
			Set<String> processNames = new HashSet<String>();
			List<String> activityNames = new ArrayList<String>();
			int nbActivity1 = 0;
			int nbBoundaryEvent1 = 0;
			int nbTsignal1 = 0;
			int nbEvents1 = 0;
			int nbTextannotation1 = 0;
			int nbMessageFlow1 = 0;
			int nbProcessItemeDefinition1 = 0;
			int nbDataObject1 = 0;
			int nbProperty1 = 0;

			for(TRootElement rootElement : model1.getDefinitions().getRootElement()){
				if(rootElement instanceof TProcess){
					nbProcess1++;
					final String processName = ((TProcess) rootElement).getName() != null ? ((TProcess) rootElement).getName() : ((TProcess) rootElement).getId();
					processNames.add(processName);
					for(TArtifact tArtifact :((TProcess) rootElement).getArtifact()){
						if(tArtifact instanceof TTextAnnotation){
							nbTextannotation1++;
						}
					}
					for(TFlowElement tFlowElement : ((TProcess) rootElement).getFlowElement()){
						if(tFlowElement instanceof TActivity){                   	
							nbActivity1++;
							String name  = getFlowElementName((TActivity) tFlowElement);
							activityNames.add(name);
							if(tFlowElement instanceof TSubProcess){
								//for now we are creating new processses for each subprocess but we don't manage event
								if(((TSubProcess) tFlowElement).isTriggeredByEvent()){
									//event sub  process
									nbActivity1 += findNbActivitiesInSubProc((TSubProcess)tFlowElement) ;
									nbActivity1--;
								} else {
									//subprocess
									nbProcess1++;
									nbActivity1 += findNbActivitiesInSubProc((TSubProcess)tFlowElement);
								}
							}
							System.out.println("activity count for model 1 "+ nbActivity1 + tFlowElement.getId() +" ### "+ tFlowElement.getName());
							nbProperty1 += ((TActivity) tFlowElement).getProperty().size();
						} else if(tFlowElement instanceof TDataObject){
							nbDataObject1++;
						} else if(tFlowElement instanceof TIntermediateThrowEvent){
							if((((TThrowEvent) tFlowElement).getEventDefinition() == null
									|| ((TThrowEvent) tFlowElement).getEventDefinition().isEmpty())
									&& (((TThrowEvent) tFlowElement).getEventDefinitionRef() == null
									|| ((TThrowEvent) tFlowElement).getEventDefinitionRef().isEmpty())){
								//empty event definitions are imported as abstract tasks
								nbActivity1++;
							}
						}
					}
				} else if(rootElement instanceof TBoundaryEvent){
					nbBoundaryEvent1++;
				} else if(rootElement instanceof TSignal){
					nbTsignal1++;
				} else if(rootElement instanceof TEvent){
					nbEvents1++;
				} else if(rootElement instanceof TCollaboration){
					nbMessageFlow1+=((TCollaboration) rootElement).getMessageFlow().size();
				} else if(rootElement instanceof TItemDefinition){
					nbProcessItemeDefinition1++;
				}
			}

			int nbProcess2 = 0;
			int nbBoundaryEvent2 = 0;
			int nbActivity2 = 0;
			int nbTsignal2 = 0;
			int nbEvents2 = 0;
			int nbTextannotation2 = 0;
			int nbMessageFlow2 =0;
			int nbProcessItemeDefinition2 = 0;
			int nbDataObject2 = 0;
			int nbProperty2 = 0;

			for(TRootElement rootElement : model2.getDefinitions().getRootElement()){
				if(rootElement instanceof TProcess){
					nbProcess2++;
					final String processName = ((TProcess) rootElement).getName() != null ? ((TProcess) rootElement).getName() : ((TProcess) rootElement).getId();
					if(!processNames.remove(processName)){
						System.out.println(processName+ "  has appeared");
					}
					for(TArtifact tArtifact :((TProcess) rootElement).getArtifact()){
						if(tArtifact instanceof TTextAnnotation){
							nbTextannotation2++;
						}
					}
					for(TFlowElement tFlowElement : ((TProcess) rootElement).getFlowElement()){
						if(tFlowElement instanceof TActivity){
							nbActivity2++;
							String name  = getFlowElementName((TActivity) tFlowElement);
							if(!activityNames.remove(name)){
								System.out.println("The activity : "+name +" is missing ");
							}
							if(tFlowElement instanceof TSubProcess){
								nbActivity2 += findNbActivitiesInSubProc((TSubProcess)tFlowElement);
								if(((TSubProcess) tFlowElement).isTriggeredByEvent()){
									nbActivity2--;
								}
							}
							System.out.println("activity count for model 2 "+ nbActivity2 + tFlowElement.getId() +" ### "+ tFlowElement.getName());
							nbProperty2 += ((TActivity) tFlowElement).getProperty().size();
						} else if(tFlowElement instanceof TDataObject){
							nbDataObject2++;
						}
					}

				} else if(rootElement instanceof TBoundaryEvent){
					nbBoundaryEvent2++;
				} else if(rootElement instanceof TSignal){
					nbTsignal2++;
				} else if(rootElement instanceof TEvent){
					nbEvents2++;
				}  else if(rootElement instanceof TCollaboration){
					nbMessageFlow2 = ((TCollaboration) rootElement).getMessageFlow().size();
				} else if(rootElement instanceof TItemDefinition){
					nbProcessItemeDefinition2++;
				}
			}

			/*Check id unicity*/
			Collection<String> ids = new HashSet<String>();
			Collection<String> duplicatedIds = new HashSet<String>();
			Collection<EObject> noIds = new HashSet<EObject>();
			for (Iterator<EObject> iterator = model2.eAllContents(); iterator.hasNext();) {
				EObject object = iterator.next();
				if(object instanceof TBaseElement){
					String id = ((TBaseElement) object).getId();
					if(id == null){
						noIds.add(object);
					}
					if(ids.contains(id)){
						//error
						duplicatedIds.add(id);
					}
					ids.add(id);
				}
			}


			if(!activityNames.isEmpty()){
				System.out.println("Some activity have disappeared for diagram:" + bpmnFileName);
				for (String activityName : activityNames) {
					System.out.println(activityName+"\n");
				}
			}

			assertEquals("We don't find the same number of process", nbProcess1, nbProcess2);
			assertTrue("There are missing processes:"+processNames.toString(), processNames.isEmpty());
			assertEquals("We don't find the same number of boundaries event", nbBoundaryEvent1, nbBoundaryEvent2);
			if(checkActivities){
				assertEquals("We don't find the same number of activities", nbActivity1, nbActivity2);
			}
			assertEquals("We don't find the same number of TSignal defined", nbTsignal1, nbTsignal2);
			if(checkEvents){
				assertEquals("We don't find the same number of TEvent defined", nbEvents1, nbEvents2);
			}
			if(checkMessageFlow){
				assertEquals("We don't find the same number of TMessageFlow defined", nbMessageFlow1, nbMessageFlow2);
			}
			assertTrue("There are null ids: "+noIds, noIds.isEmpty());
			assertTrue("There are duplicated ids: "+duplicatedIds, duplicatedIds.isEmpty());
			assertEquals("Some data were lost", nbDataObject1, nbDataObject2);
			assertEquals("Some property (transient data) were lost", nbProperty1, nbProperty2);
		}finally{
			if(resource1 != null){
				resource1.unload();
			}
			if(resource2 != null){
				resource2.unload();
			}
		}
		//	assertEquals("We don't find the same number of TTextAnnotation defined", nbTextannotation1, nbTextannotation2);

		//TODO: check link between signal thrown and caught
	}

	protected String getFlowElementName(TActivity tFlowElement) {
		return tFlowElement.getName() != null && !tFlowElement.getName().isEmpty() ? tFlowElement.getName() : tFlowElement.getId();
	}



	private int findNbActivitiesInSubProc(TSubProcess tSubProc) {
		int result = 0;
		for(TFlowElement tFlowElement : tSubProc.getFlowElement()){
			if(tFlowElement instanceof TActivity){
				//for now we are creating new processses for each subprocess
				result++;
				if(tFlowElement instanceof TSubProcess){
					result += findNbActivitiesInSubProc((TSubProcess)tFlowElement);
					if(((TSubProcess) tFlowElement).isTriggeredByEvent()){
						result--;
					}
				}
			}
		}
		return result;
	}


	protected void checkGraphic(String fileName, MainProcess mainProcess) throws IOException{
		URL bpmnResource = FileLocator.toFileURL(BPMNImportExportTest.class.getResource(fileName));
		ResourceSet resourceSet1 = new ResourceSetImpl();
		Resource resource1 = resourceSet1.createResource(BPMNTestUtil.toEMFURI(new File(bpmnResource.getFile())));
		resource1.load(Collections.emptyMap());
		DocumentRoot model1 = (DocumentRoot) resource1.getContents().get(0);

		final Diagram diagramFor = ModelHelper.getDiagramFor(mainProcess,null);
		DiagramEditPart dep;
		try{
			dep = OffscreenEditPartFactory.getInstance().createDiagramEditPart(diagramFor, PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell());
		} catch(Exception ex){
			dep = OffscreenEditPartFactory.getInstance().createDiagramEditPart(diagramFor, PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell());
		}
		MainProcessEditPart mped = (MainProcessEditPart) dep;

		checkAllEditPartsAreVisible(mped);

		if(model1.getDefinitions().getBPMNDiagram().size() != 0){
			checkRelativeGraphicPosition(mainProcess, model1, dep, mped);
		}
	}

	protected void checkRelativeGraphicPosition(MainProcess mainProcess,
			DocumentRoot model1, DiagramEditPart dep, MainProcessEditPart mped) {
		/*find correspondence between bonita model element and bpmn2 model element,
		 * they should have the same ID*/
		List<Element> activities = ModelHelper.getAllItemsOfType(mainProcess, ProcessPackage.Literals.ACTIVITY);

		for(Element activity : activities){
			if(!(activity instanceof Task)
					/*&& !(activity instanceof SubProcess)*/){
				/*Find the Bonita EditPart for the id*/
				String ID = findIdOfFlowNodeWithName(model1, activity.getName());/*argh now, the name is no more the ID of the BPMN element from which it is extracted, this id is lost...*/
				IGraphicalEditPart activityPart = (IGraphicalEditPart)dep.findEditPart(mped, activity);
				assertNotNull(activityPart);

				/*Find relative positions*/
				Point bpmnLocation = getLocationFor(model1.getDefinitions(), ID);
				assertNotNull("ID not found bpmn location:" + ID, bpmnLocation);


				/*Compare */
				final int offset = Math.abs(bpmnLocation.x - activityPart.getFigure().getBounds().x);
				final String message =
						"\nbpmn location: "+ bpmnLocation
						+"\n aprt location "+activityPart.getFigure().getBounds()
						+activityPart.resolveSemanticElement()
						+": "
						+"\ncurrent editpat : "+activityPart;
				assertTrue("Too much offset "+ offset+" for :\n"+message, offset < 35);

			}
		}
	}

	/**
	 * if there are several activities with the sam enam ewe will have issues :'(
	 * @param model1
	 * @param name
	 * @return
	 */
	private String findIdOfFlowNodeWithName(DocumentRoot model1, String name) {
		for(TRootElement root : model1.getDefinitions().getRootElement()){
			if(root instanceof TProcess){
				for(TFlowElement flowElementDirectlyInProcess : ((TProcess) root).getFlowElement()){
					if(flowElementDirectlyInProcess instanceof TFlowNode){
						if(name.equals(flowElementDirectlyInProcess.getName())){
							return flowElementDirectlyInProcess.getId();
						}
						if(flowElementDirectlyInProcess instanceof TSubProcess){
							System.out.println("subprocess "+flowElementDirectlyInProcess.getName());
							for(TFlowElement flowElementInSubProcess : ((TSubProcess) flowElementDirectlyInProcess).getFlowElement()){
								System.out.println(flowElementInSubProcess.getName());
								if(flowElementInSubProcess instanceof TFlowNode){
									if(name.equals(flowElementInSubProcess.getName())){
										return flowElementInSubProcess.getId();
									}
									if(flowElementInSubProcess instanceof TSubProcess){
										for(TFlowElement flowElementInSubSubProcess : ((TSubProcess) flowElementInSubProcess).getFlowElement()){
											System.out.println(flowElementInSubSubProcess.getName());
											if(flowElementInSubSubProcess instanceof TFlowNode){
												if(name.equals(flowElementInSubSubProcess.getName())){
													return flowElementInSubSubProcess.getId();
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return name;//hope name and id were the same and that we didn't do a correct search
	}

	private void checkAllEditPartsAreVisible(IGraphicalEditPart mped) {
		Rectangle parentBounds = mped.getFigure().getBounds();
		//Rectangle childrenBounds = mped.getChildrenBounds();
		if(parentBounds != null){
			for(Object childEditPart :mped.getChildren()){
				if(childEditPart instanceof IGraphicalEditPart
						/*&& !(childEditPart instanceof ITextAwareEditPart)*/){
					final Rectangle childBounds = ((IGraphicalEditPart)childEditPart).getFigure().getBounds();
					if(childBounds != null){
						int marge = 50;//should be the max if the task is contained in a lane and that the pool has the position set to 0
						final String message = ((IGraphicalEditPart)childEditPart).resolveSemanticElement()
								+": "
								+"\ncurrent editpat : "+childEditPart
								+"\nparent editpart : "+mped
								+"\nparent semantic : "+mped.resolveSemanticElement();
						if(childEditPart instanceof LaneEditPart){
							//TODO: take of text that is too near from the right and left border
							marge += 45;
						}

						assertTrue("x not good for "+message
								+ "\n " + parentBounds.width
								+ "\n " + childBounds.right(),
								parentBounds.width+marge >= childBounds.right());
						assertTrue("x not good for "+message
								+"\n"+childBounds.x,
								childBounds.x >= 0);
						assertTrue("y not good for "+ message
								+"\n"+parentBounds.height
								+"\n"+childBounds.y,
								parentBounds.height >= childBounds.y);
						assertTrue("y not good for "
								+message
								+"\n"+childBounds.y,
								childBounds.y >= 0);
						EObject semanticElement = ((IGraphicalEditPart)childEditPart).resolveSemanticElement();
						if(semanticElement instanceof Pool
								|| semanticElement instanceof Lane){
							checkAllEditPartsAreVisible((IGraphicalEditPart) childEditPart);
						}
					}
				}
			}
		}
		//assertTrue(parentBounds.contains(childrenBounds));
	}

	/**
	 * @param id :  the id of the BPMN Element (not the graphical one)
	 * @return
	 */
	private Point getLocationFor(TDefinitions tDefinition, String id) {
		/*need to have location relatively to the container*/
		//for(TDefinitions tDefinition : tDefinitions){
		for(BPMNDiagram rootElement : tDefinition.getBPMNDiagram()){
			for(DiagramElement diagramElement : rootElement.getBPMNPlane().getDiagramElement()){
				if(diagramElement instanceof BPMNShape){
					final BPMNShape bpmnShape = (BPMNShape)diagramElement;
					final String localPart = bpmnShape.getBpmnElement().getLocalPart();
					if(NamingUtils.convertToId(localPart).equals(id)
							|| localPart.equals(id)
							|| NamingUtils.convertToId("subProc_"+localPart).equals(id)
							|| ("subProc_"+localPart).equals(id)){
						Point containerLocation = getContainerLocationFor(tDefinition, tDefinition.getRootElement(), id);
						final Bounds bounds = ((Shape)diagramElement).getBounds();
						return new Point(bounds.getX() - containerLocation.x,
								bounds.getY()- containerLocation.y);
					}
				}
			}
		}
		//}
		return null;
	}

	protected Point getContainerLocationFor(TDefinitions tDefinitions, List<TRootElement> rootElements,String id) {
		/*Retrieve container*/
		String containerId = null;
		Iterator<TRootElement> rootElementsIterator = rootElements.iterator();
		while(containerId == null && rootElementsIterator.hasNext()){
			TRootElement rootElement = rootElementsIterator.next();
			/*Search in all Processes ...*/
			if(rootElement instanceof TProcess){
				/*... in all LaneSets...*/
				final EList<TLaneSet> laneSet = ((TProcess) rootElement).getLaneSet();
				if(!laneSet.isEmpty()){
					for (TLaneSet childLaneSet : laneSet) {
						containerId = findContainerIdOf(id, childLaneSet);
						if (containerId != null) {
							break;
						}
					}
				} else {
					//Search directly in the process
					for(TFlowElement flowElement : ((TProcess)rootElement).getFlowElement()){
						if(id.equals(flowElement.getId())){
							containerId = rootElement.getId();
							break;
						}
					}
				}
			}
		}
		/*if we don't find a container, perhaps it is because it is a TArtifact*/
		if(containerId == null){
			/*so search with a different algo... thanks BPMN2!!!*/
			for(TRootElement rootElement : rootElements){
				/*Search in all Processes ...*/
				if(rootElement instanceof TProcess){
					for(TArtifact artifact : ((TProcess) rootElement).getArtifact()){
						if(id.equals(artifact.getId())){
							//need to find the top container that has a BMPNShape associated with, again thanks BPMN2!!!
							Point containerLocationOfTArtifact = null;
							/*... in all LaneSets...*/
							for(TLaneSet laneSet : ((TProcess)rootElement).getLaneSet()){
								/*... in all Lanes ...*/
								for(TLane lane : laneSet.getLane()){
									Point tempContainerLocationOfTArtifact = getLocationFor(tDefinitions, lane.getId());
									if(containerLocationOfTArtifact != null){
										containerLocationOfTArtifact.x = Math.min(containerLocationOfTArtifact.x, tempContainerLocationOfTArtifact.x);
										containerLocationOfTArtifact.y = Math.min(containerLocationOfTArtifact.y, tempContainerLocationOfTArtifact.y);
									} else {
										containerLocationOfTArtifact = new Point();
										containerLocationOfTArtifact.x = tempContainerLocationOfTArtifact.x;
										containerLocationOfTArtifact.y = tempContainerLocationOfTArtifact.y;
									}
								}
							}
							if(containerLocationOfTArtifact != null){
								return containerLocationOfTArtifact.getCopy().translate(-25, -25);
							}
							//break;
						}
					}
				}
			}
		}
		Point containerLocation = new Point();
		if(containerId != null){
			final Point initialLocationContainer = getLocationFor(tDefinitions, containerId);
			if(initialLocationContainer != null){
				containerLocation = initialLocationContainer.getCopy().translate(-25, -25);
			}
		} else {
			//TODO : support recursivity fort subprocesses, we are doing it only in tests because
			/*check we were not in a subprocess from BPMN that is not in a lane*/
			for(TRootElement rootElement : rootElements){
				/*Search in all Processes ...*/
				if(rootElement instanceof TProcess){
					for (TFlowElement tFlowElement : ((TProcess) rootElement).getFlowElement()) {
						if(tFlowElement instanceof TSubProcess){
							containerLocation = getContainerLocationFor((TSubProcess)tFlowElement, id, tDefinitions);
							if(containerLocation != null){
								return containerLocation;
							}
						}
					}
				}
			}
		}
		return containerLocation != null? containerLocation : new Point();
	}

	private Point getContainerLocationFor(TSubProcess subProcess, String id, TDefinitions tDefinitions) {
		final BPMNShape bpmnShapeForBpmnID = getBPMNShapeForBpmnID(tDefinitions, subProcess.getId());
		if(bpmnShapeForBpmnID != null && bpmnShapeForBpmnID.isIsExpanded()){
			for (TFlowElement subProcFlowElement : subProcess.getFlowElement()) {
				if(id.equals(subProcFlowElement.getId())){
					if(subProcess.isTriggeredByEvent()){
						return getContainerLocationFor(tDefinitions, tDefinitions.getRootElement(), subProcess.getId()).getCopy();
					} else {
						return getAbsoluteLocationFor(tDefinitions, subProcess.getId()).getCopy().translate(-25, -25);
					}
				} else if(subProcFlowElement instanceof TSubProcess){
					Point containerLocation = getContainerLocationFor((TSubProcess)subProcFlowElement, id, tDefinitions);
					if(containerLocation != null){
						return containerLocation.getCopy();
					}
				}
			}
		}
		return null;
	}

	private Point getAbsoluteLocationFor(TDefinitions tDefinitions, String id) {
		for(BPMNDiagram rootElement : tDefinitions.getBPMNDiagram()){
			for(DiagramElement diagramElement : rootElement.getBPMNPlane().getDiagramElement()){
				if(diagramElement instanceof BPMNShape){
					final BPMNShape bpmnShape = (BPMNShape)diagramElement;
					final String localPart = bpmnShape.getBpmnElement().getLocalPart();
					if(NamingUtils.convertToId(localPart).equals(id)
							|| localPart.equals(id)
							|| NamingUtils.convertToId("subProc_"+localPart).equals(id)
							|| ("subProc_"+localPart).equals(id)){
						final Bounds bounds = ((Shape)diagramElement).getBounds();
						return new Point(bounds.getX(), bounds.getY()).getCopy().translate(-25, -25);
					}
				}
			}
		}
		//}
		return null;
	}

	protected BPMNShape getBPMNShapeForBpmnID(TDefinitions tDefinitions, String id) {
		for (BPMNDiagram bpmnDiagram : tDefinitions.getBPMNDiagram()) {
			BPMNPlane bpmnPlane = bpmnDiagram.getBPMNPlane();
			for(DiagramElement diagramElement : bpmnPlane.getDiagramElement()){
				if(diagramElement instanceof BPMNShape){
					if(((BPMNShape)diagramElement).getBpmnElement().getLocalPart().equals(id)){
						return (BPMNShape)diagramElement;
					}
				}
			}
		}
		return null;
	}

	protected String findContainerIdOf(String id, TLaneSet laneSet) {
		/*... in all Lanes ...*/
		for(TLane lane : laneSet.getLane()){
			/*... to find if it is contained in it, ie if it is in FlowNodeRef*/
			for(String flowNodeRef :lane.getFlowNodeRef()){
				if(flowNodeRef.equals(id)
						|| NamingUtils.convertToId(flowNodeRef).equals(id)
						|| ("subProc_"+flowNodeRef).equals(id)
						|| NamingUtils.convertToId("subProc_"+flowNodeRef).equals(id)){
					return lane.getId();
				}
			}
			if(lane.getChildLaneSet() != null){
				String res = findContainerIdOf(id, lane.getChildLaneSet());
				if(res != null){
					return res;
				}
			}
		}
		return null;
	}




}
