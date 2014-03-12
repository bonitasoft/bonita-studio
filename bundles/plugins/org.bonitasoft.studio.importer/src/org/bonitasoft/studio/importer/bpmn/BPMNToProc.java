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
package org.bonitasoft.studio.importer.bpmn;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.bonitasoft.engine.bpm.connector.ConnectorEvent;
import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.ProjectUtil;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.importer.builder.IProcBuilder;
import org.bonitasoft.studio.importer.builder.IProcBuilder.DataType;
import org.bonitasoft.studio.importer.builder.IProcBuilder.EventType;
import org.bonitasoft.studio.importer.builder.IProcBuilder.GatewayType;
import org.bonitasoft.studio.importer.builder.IProcBuilder.TaskType;
import org.bonitasoft.studio.importer.builder.ProcBuilder;
import org.bonitasoft.studio.importer.builder.ProcBuilderException;
import org.bonitasoft.studio.importer.i18n.Messages;
import org.bonitasoft.studio.importer.processors.ToProcProcessor;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.process.Actor;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.diagram.edit.parts.MainProcessEditPart;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap.Entry;
import org.eclipse.emf.ecore.util.FeatureMapUtil;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.omg.spec.bpmn.di.BPMNDiagram;
import org.omg.spec.bpmn.di.BPMNEdge;
import org.omg.spec.bpmn.di.BPMNLabelStyle;
import org.omg.spec.bpmn.di.BPMNPlane;
import org.omg.spec.bpmn.di.BPMNShape;
import org.omg.spec.bpmn.di.util.DiResourceFactoryImpl;
import org.omg.spec.bpmn.model.DocumentRoot;
import org.omg.spec.bpmn.model.ModelPackage;
import org.omg.spec.bpmn.model.TActivity;
import org.omg.spec.bpmn.model.TArtifact;
import org.omg.spec.bpmn.model.TAssignment;
import org.omg.spec.bpmn.model.TAssociation;
import org.omg.spec.bpmn.model.TBaseElement;
import org.omg.spec.bpmn.model.TBoundaryEvent;
import org.omg.spec.bpmn.model.TCallActivity;
import org.omg.spec.bpmn.model.TCatchEvent;
import org.omg.spec.bpmn.model.TCollaboration;
import org.omg.spec.bpmn.model.TComplexGateway;
import org.omg.spec.bpmn.model.TDataInput;
import org.omg.spec.bpmn.model.TDataInputAssociation;
import org.omg.spec.bpmn.model.TDataObject;
import org.omg.spec.bpmn.model.TDataOutputAssociation;
import org.omg.spec.bpmn.model.TDefinitions;
import org.omg.spec.bpmn.model.TDocumentation;
import org.omg.spec.bpmn.model.TEndEvent;
import org.omg.spec.bpmn.model.TErrorEventDefinition;
import org.omg.spec.bpmn.model.TEvent;
import org.omg.spec.bpmn.model.TEventDefinition;
import org.omg.spec.bpmn.model.TExclusiveGateway;
import org.omg.spec.bpmn.model.TExpression;
import org.omg.spec.bpmn.model.TFlowElement;
import org.omg.spec.bpmn.model.TFlowNode;
import org.omg.spec.bpmn.model.TFormalExpression;
import org.omg.spec.bpmn.model.TGateway;
import org.omg.spec.bpmn.model.TInclusiveGateway;
import org.omg.spec.bpmn.model.TItemDefinition;
import org.omg.spec.bpmn.model.TLane;
import org.omg.spec.bpmn.model.TLaneSet;
import org.omg.spec.bpmn.model.TLinkEventDefinition;
import org.omg.spec.bpmn.model.TManualTask;
import org.omg.spec.bpmn.model.TMessageEventDefinition;
import org.omg.spec.bpmn.model.TMessageFlow;
import org.omg.spec.bpmn.model.TParallelGateway;
import org.omg.spec.bpmn.model.TParticipant;
import org.omg.spec.bpmn.model.TPerformer;
import org.omg.spec.bpmn.model.TProcess;
import org.omg.spec.bpmn.model.TProperty;
import org.omg.spec.bpmn.model.TReceiveTask;
import org.omg.spec.bpmn.model.TResourceRole;
import org.omg.spec.bpmn.model.TRootElement;
import org.omg.spec.bpmn.model.TScriptTask;
import org.omg.spec.bpmn.model.TSendTask;
import org.omg.spec.bpmn.model.TSequenceFlow;
import org.omg.spec.bpmn.model.TServiceTask;
import org.omg.spec.bpmn.model.TSignal;
import org.omg.spec.bpmn.model.TSignalEventDefinition;
import org.omg.spec.bpmn.model.TStartEvent;
import org.omg.spec.bpmn.model.TSubProcess;
import org.omg.spec.bpmn.model.TTask;
import org.omg.spec.bpmn.model.TTerminateEventDefinition;
import org.omg.spec.bpmn.model.TText;
import org.omg.spec.bpmn.model.TTextAnnotation;
import org.omg.spec.bpmn.model.TThrowEvent;
import org.omg.spec.bpmn.model.TTimerEventDefinition;
import org.omg.spec.bpmn.model.TTransaction;
import org.omg.spec.bpmn.model.TUserTask;
import org.omg.spec.dd.dc.Bounds;
import org.omg.spec.dd.dc.Font;
import org.omg.spec.dd.di.DiagramElement;
import org.omg.spec.dd.di.Shape;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class BPMNToProc extends ToProcProcessor {

	private boolean useBpmnDiagram = false;
	private EList<BPMNDiagram> bpmnDiagrams;
	private Stack<TSubProcess> subProcesses;
	private String resourceName;
	private List<BPMNPlane> bpmnProcessDiagrams;
	private BPMNShape subProc;
	private List<TProcess> bpmnProcess;
	private final List<Object> errorElements = new ArrayList<Object>();
	private TDefinitions definitions;
	private EList<TRootElement> rootElements;
	protected Diagram diagram;
	private IProcBuilder builder;
	private final List<String> subProcessesId = new ArrayList<String>();
	private File result;
	protected Map<String, Actor> participants;
	protected Map<String, String> dataNameByItemDefinition = new HashMap<String, String>();
	protected Map<String, TEventDefinition> idOfEventDefinitions = new HashMap<String, TEventDefinition>();
	protected Map<String, ShapeNodeEditPart> editParts;
	protected MainProcessEditPart diagramPart;
	private String JAVA_XMLNS = "java";// put java by default
	private String XMLNS_HTTP_BONITASOFT_COM_BONITA_CONNECTOR_DEFINITION = "http://www.bonitasoft.org/studio/connector/definition/6.0";// default
	// value
	private TSubProcess subprocEvent;
	private Point posMax;// used in case, (no pool shape defined and no
	// participant defined and no laneset defined) or no
	// diagram defined
	// private Point posMin;//used in case, no pool shape defined and no
	// participant defined and no laneset defined

	private final static String[] tagNameWithQNames = new String[] { "source", "target",
		"sourceRef", "targetRef", "attachedToRef",
		"supportedInterfaceRef", "calledElement",
		"calledChoreographyRef", "calledCollaborationRef",
		"eventDefinitionRef", "participantRef",
		"initiatingParticipantRef",
		"messageFlowRef",
		"choreographyRef",
		"activityRef",
		"innerConversationNodeRef",
		"outerConversationNodeRef",
		"participantRef",
		"messageFlowRef",
		"correlationPropertyRef",
		"type",
		"messageRef",
		"correlationKeyRef",
		"itemSubjectRef",
		"dataStoreRef",
		"structureRef",
		"errorRef",
		"escalationRef",
		"definition",// check that it collapsed not with other
		"categoryValueRef", "incoming", "outgoing",
		"evaluatesToTypeRef", "initiatingParticipantRef",
		"implementationRef", "operationRef", "partitionElementRef",
		"itemRef", "innerMessageFlowRef", "outerMessageFlowRef",
		"loopDataInputRef", "loopDataOutputRef",
		"oneBehaviorEventRef", "noneBehaviorEventRef",
		"inMessageRef", "outMessageRef", "endPointRef",
		"processRef", "innerParticipantRef", "outerParticipantRef",
		"participantRef", "supports",
		"definitionalCollaborationRef", "itemSubjectRef",
		"parameterRef", "resourceRef", "signalRef",
		"eventDefinitionRef", "attachedToRef" };
	/**
	 * @param resourceName
	 */
	public BPMNToProc(String resourceName) {
		File f = new File(resourceName);
		if (f.exists()) {
			this.resourceName = f.getName();
		} else {
			this.resourceName = resourceName;
		}

	}

	@Override
	public File createDiagram(URL sourceBPMNUrl, IProgressMonitor progressMonitor) {
		progressMonitor.beginTask(Messages.importFromBPMN,
				IProgressMonitor.UNKNOWN);
		builder = new ProcBuilder(progressMonitor);

		InputStream stream = null;
		try {
			boolean hadBeenPreProcessed = false;
			stream = sourceBPMNUrl.openStream();
			Document document = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder().parse(stream);
			if (!ModelPackage.eNS_URI.equals(document.getDocumentElement()
					.getAttribute("xmlns"))) {
				document.getDocumentElement().setAttribute("xmlns",
						ModelPackage.eNS_URI);
				hadBeenPreProcessed = true;
			}

			/* Search for bad Qname */
			hadBeenPreProcessed = replaceBadQNames(document) || hadBeenPreProcessed;

			if (hadBeenPreProcessed) {
				File file = File.createTempFile("importBPMN", ".bpmn",
						ProjectUtil.getBonitaStudioWorkFolder());
				file.deleteOnExit();
				TransformerFactory
				.newInstance()
				.newTransformer()
				.transform(new DOMSource(document),
						new StreamResult(file));
				sourceBPMNUrl = file.toURI().toURL();
			}

		} catch (Exception ex) {
			BonitaStudioLog.error(ex);
		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e) {
					BonitaStudioLog.error(e);
				}
			}
		}

		ResourceSet resourceSet = new ResourceSetImpl();
		final Map<String, Object> extensionToFactoryMap = resourceSet
				.getResourceFactoryRegistry().getExtensionToFactoryMap();
		extensionToFactoryMap.put(getExtension(), new DiResourceFactoryImpl());

		try {
			File f = new File(URLDecoder.decode(sourceBPMNUrl.getFile(),
					"UTF-8"));
			Resource resource = resourceSet.getResource(
					URI.createURI(f.toURI().toString()), true);

			final EObject rootContent = resource.getContents().get(0);
			if (rootContent == null || !(rootContent instanceof DocumentRoot)) {
				throw new Exception("Document type not supported");
			}

			final DocumentRoot docRoot = (DocumentRoot) rootContent;

			final TDefinitions docRootDefinitions = docRoot.getDefinitions();
			if (docRootDefinitions == null) {
				throw new Exception("Document type not supported");
			}
			String id = calculateBonitaDiagramId(docRootDefinitions);
			String name = calculateBonitaDiagramName(docRootDefinitions);
			result = File.createTempFile(id, ".proc");
			builder.createDiagram(id, name, "1.0", result);
			// create model and diagram

			updateXMLNamespaceIfNeeded(docRoot);
			importFromBPMN(docRootDefinitions);

			builder.done();
			return result;
		} catch (Throwable e) {
			BonitaStudioLog.error(e);
		}
		return null;
	}

	private void updateXMLNamespaceIfNeeded(final DocumentRoot docRoot) {
		for (java.util.Map.Entry<String, String> entry : docRoot
				.getXMLNSPrefixMap().entrySet()) {
			if ("http://jcp.org/en/jsr/detail?id=270".equals(entry
					.getValue())) {
				JAVA_XMLNS = entry.getKey();
			} else if ("http://www.bonitasoft.org/studio/connector/definition/6.0"
					.equals(entry.getValue())) {
				XMLNS_HTTP_BONITASOFT_COM_BONITA_CONNECTOR_DEFINITION = entry
						.getKey();
			}
		}
	}

	private String calculateBonitaDiagramName(final TDefinitions docRootDefinitions) {
		String name = null;
		if (docRootDefinitions.getName() == null
				|| docRootDefinitions.getName().isEmpty()) {
			name = resourceName.substring(0, resourceName.lastIndexOf("."));
		} else {
			name = docRootDefinitions.getName();
		}
		return name;
	}

	private String calculateBonitaDiagramId(final TDefinitions docRootDefinitions) {
		String id = null;
		if (docRootDefinitions.getId() == null
				|| docRootDefinitions.getId().length() == 0) {
			id = resourceName.substring(0, resourceName.lastIndexOf("."));
		} else {
			id = docRootDefinitions.getId();
		}
		return id;
	}

	private boolean replaceBadQNames(Document document) {
		boolean hadBeenPreProcessed = false;
		for (String tagName : tagNameWithQNames) {
			NodeList nodeList = document.getDocumentElement()
					.getElementsByTagName(tagName);
			for (int i = 0; i < nodeList.getLength(); i++) {
				org.w3c.dom.Node node = nodeList.item(i);
				NodeList sourceNodeList = node.getChildNodes();
				for (int j = 0; j < sourceNodeList.getLength(); j++) {
					org.w3c.dom.Node sourceNode = sourceNodeList.item(j);
					final String nodeValue = sourceNode.getNodeValue();
					try {
						new org.eclipse.emf.ecore.xml.type.internal.QName(
								nodeValue);
					} catch (Exception e) {
						/*
						 * this is not a valid QName, replace it and avert
						 * user
						 */
						final String convertedQName = nodeValue.replace(
								'/', '_').replace(':', '_');
						errorElements.add("invalid QName of tagName "
								+ tagName + ": " + nodeValue
								+ " converted to " + convertedQName);
						sourceNode.setNodeValue(convertedQName);
						hadBeenPreProcessed = true;
					}
				}
			}
		}
		return hadBeenPreProcessed;
	}

	/**
	 * @param definitions
	 * @param model
	 * @param diagram
	 * @throws Exception
	 */
	protected void importFromBPMN(TDefinitions definitions)
			throws ProcBuilderException {

		bpmnDiagrams = definitions.getBPMNDiagram();
		this.definitions = definitions;
		subProcesses = new Stack<TSubProcess>();
		bpmnProcess = new ArrayList<TProcess>();

		Point location = new Point(5, 5);
		rootElements = definitions.getRootElement();
		initEventDefinitions();
		for (TRootElement el : rootElements) {
			if (el instanceof TProcess) {
				bpmnProcess.add((TProcess) el);
				location = createProcess((TProcess) el, location);
			}
		}
		while (!subProcesses.isEmpty()) {
			location = createSubProcess(subProcesses.pop(), location);
		}

		for (TRootElement el : rootElements) {
			if (el instanceof TProcess) {
				processTransitions(((TProcess) el).getFlowElement());
				processAnnotations((TProcess) el);// must be after transition
			}
		}

		for (TRootElement el : rootElements) {
			if (el instanceof TCollaboration) {
				processMessageFlow(diagramPart, (TCollaboration) el);
			}
		}

	}

	private void initEventDefinitions() {
		for (TRootElement el : rootElements) {
			TreeIterator<EObject> eAllContents = el.eAllContents();
			while (eAllContents.hasNext()) {
				EObject eObject = (EObject) eAllContents.next();
				if(eObject instanceof TEventDefinition){
					idOfEventDefinitions.put(((TEventDefinition) eObject).getId(), (TEventDefinition) eObject);
				}				
			}
		}		
	}

	/**
	 * @param el
	 * @throws Exception
	 */
	private Point createProcess(TProcess el, Point location) throws ProcBuilderException {
		posMax = new Point();
		// posMin = new Point();
		TProcess process = el;
		initDiagramFor(el.getId());
		String name = process.getName();
		if (process.getName() == null || process.getName().isEmpty()) {
			name = process.getId();
		}
		createPool(process.getId(), name, location, false);

		builder.addDescription(retrieveDocumentation(process));

		EList<TResourceRole> resourceRole = el.getResourceRole();
		for (TResourceRole tResourceRole : resourceRole) {
			if(tResourceRole instanceof TPerformer){
				builder.addActor(tResourceRole.getName(), retrieveDocumentation(tResourceRole));
			}
		}


		processDataObjects(process);

		final EList<TFlowElement> flowElements = process.getFlowElement();
		if (process.getLaneSet().isEmpty()) {
			createProcessWithoutLanes(process, flowElements);
		} else {
			List<TLane> lanes = getAllTLanes(process.getLaneSet());
			if(!lanes.isEmpty()){
				createProcessWithLanes(flowElements, lanes);
			} else {
				//Some tools are exporting empty laneset (IBM BlueWorks Live for instance)
				createProcessWithoutLanes(process, flowElements);
			}			
		}
		processBoundaries(flowElements);

		return location;
	}

	private void createProcessWithLanes(final EList<TFlowElement> flowElements, List<TLane> lanes) throws ProcBuilderException {
		int nbActivities = 0;
		for (TLane tLane : lanes) {
			final Dimension sizeFor = getSizeFor(tLane.getId());
			if (sizeFor != null) {
				sizeFor.width = -1;
			}
			String laneName = tLane.getName();
			if (tLane.getName() == null || tLane.getName().isEmpty()) {
				laneName = tLane.getId();
			}
			builder.addLane(tLane.getId(), laneName, sizeFor);
			addFontStyle(tLane.getId());
			builder.addDescription(retrieveDocumentation(tLane));

			List<TFlowElement> inLane = new ArrayList<TFlowElement>();
			/* Retrieve flowElement that are in the Tlane */
			for (String flownNodeRef : tLane.getFlowNodeRef()) {
				for (TFlowElement flowElement : flowElements) {
					if (flownNodeRef.equals(flowElement.getId())) {
						inLane.add(flowElement);
						break;
					}
				}
			}
			nbActivities = processActivities(inLane, false);
			if (!useBpmnDiagram) {
				Dimension newSize = new Dimension();
				newSize.width = 100 + 150 * (nbActivities % 7) + 300;
				newSize.height = 100 * (1 + (nbActivities / 7)) + 100;
				builder.updateSize(tLane.getId(), newSize);
			}

			builder.switchToParentContainer();
		}
	}

	private void createProcessWithoutLanes(TProcess process,
			final EList<TFlowElement> flowElements) throws ProcBuilderException {
		int nbActivities = processActivities(flowElements, false);
		if (!useBpmnDiagram) {
			Dimension newSize = new Dimension();
			newSize.width = 100 + Math.max(150 * (nbActivities % 7),
					posMax.x) + 300;
			newSize.height = Math.max(100 * (1 + (nbActivities / 7)),
					posMax.y) + 100;
			builder.updateSize(process.getId(), newSize);
		}
	}

	protected void addFontStyle(String bpmnId) throws ProcBuilderException {
		BPMNShape bpmnShape = getBPMNShapeForBpmnID(bpmnId);
		if(bpmnShape != null 
				&& bpmnShape.getBPMNLabel() != null
				&& bpmnShape.getBPMNLabel().getLabelStyle() != null){
			BPMNLabelStyle style = getLabelStyle(bpmnShape.getBPMNLabel().getLabelStyle());
			if(style != null && style.getFont() != null){
				Font font = style.getFont();
				builder.setFontStyle(font.getName(),(int)font.getSize(),font.isIsBold(),font.isIsItalic());
			}
		}
	}

	private BPMNLabelStyle getLabelStyle(QName labelStyle) {
		for(BPMNDiagram diagram : bpmnDiagrams){
			for(BPMNLabelStyle style : diagram.getBPMNLabelStyle()){
				if(style.getId().equals(labelStyle.getLocalPart())){
					return style;
				}
			}
		}
		return null;
	}

	private List<TLane> getAllTLanes(List<TLaneSet> laneSets) {
		List<TLane> res = new ArrayList<TLane>();
		for (TLaneSet laneSet : laneSets) {
			for (TLane lane : laneSet.getLane()) {
				res.add(lane);
				res.addAll(getAllTLanes(lane.getChildLaneSet()));
			}
		}
		return res;
	}

	private Collection<? extends TLane> getAllTLanes(TLaneSet childLaneSet) {
		List<TLane> res = new ArrayList<TLane>();
		if (childLaneSet != null) {
			for (TLane lane : childLaneSet.getLane()) {
				res.add(lane);
				res.addAll(getAllTLanes(lane.getChildLaneSet()));
			}
		}
		return res;
	}

	/**
	 * @param flowElement
	 * @throws ProcBuilderException
	 */
	private void processBoundaries(EList<TFlowElement> flowElements)
			throws ProcBuilderException {
		if (flowElements != null) {
			for (TFlowElement flowElement : flowElements) {
				if (flowElement instanceof TEvent) {
					TEvent flowNode = (TEvent) flowElement;
					EventType eventType = getEventType(flowNode);
					if (eventType != null && flowNode instanceof TBoundaryEvent) {// do
						// boundaries
						// after
						String idOfParent = ((TBoundaryEvent) flowNode)
								.getAttachedToRef().getLocalPart();
						//						if (subProcessesId.contains(idOfParent)) {
						//							idOfParent = "subProc_" + idOfParent;
						//						}
						builder.setCurrentStep(idOfParent);

						String name = computeBoundaryName(flowNode);
						builder.addEvent(flowNode.getId(), name, null, null,
								eventType);

						builder.addDescription(retrieveDocumentation(flowNode));
						populateEvent(flowNode, eventType);

					} else if (flowNode instanceof TBoundaryEvent) {
						errorElements.add(flowNode.eClass().getName() + ": "
								+ flowNode.getName());
					}
				}
			}
		}
	}

	private String computeBoundaryName(TEvent flowNode) {
		String name;
		if (flowNode.getName() != null
				&& flowNode.getName().length() > 0) {
			name = flowNode.getName();
		} else {
			name = flowNode.getId();
		}
		return name;
	}

	/**
	 * @param poolEditPart
	 * @param process
	 * @throws ProcBuilderException
	 */
	private void processAnnotations(TProcess process)
			throws ProcBuilderException {
		EList<TArtifact> artifacts = process.getArtifact();
		List<String> sourceRefs = new ArrayList<String>();
		for (TArtifact tArtifact : artifacts) {
			if (tArtifact instanceof TTextAnnotation) {
				TTextAnnotation textAnnotation = (TTextAnnotation) tArtifact;
				TText text = textAnnotation.getText();
				String theText = "";
				if (text != null) {
					theText = (String) ((List<?>) text
							.getMixed()
							.get(XMLTypePackage.Literals.XML_TYPE_DOCUMENT_ROOT__TEXT,
									false)).get(0);
				} else {
					continue;
				}

				final Point location = getLocationFor(textAnnotation.getId());
				final Dimension size = getSizeFor(textAnnotation.getId());
				if(size != null){
					size.expand(0, 10);
				}
				if(location != null){
					location.translate(0, -10);
				}
				QName source = retrieveQNameOfSource(artifacts, textAnnotation);
				sourceRefs.add(source.getLocalPart());
				builder.addAnnotation(theText, location, size,
						source.getLocalPart());

			} 
		}
		for(TArtifact tArtifact : artifacts){
			if(!(tArtifact instanceof TTextAnnotation)){
				if(!(tArtifact instanceof TAssociation && sourceRefs.contains(((TAssociation) tArtifact).getSourceRef().getLocalPart()))){
					errorElements.add(tArtifact.eClass().getName() + ": "
							+ tArtifact.getId());
					BonitaStudioLog.log("can't create element for " + tArtifact);
				}
			}else if(!(tArtifact instanceof TTextAnnotation)){
				errorElements.add(tArtifact.eClass().getName() + ": "
						+ tArtifact.getId());
				BonitaStudioLog.log("can't create element for " + tArtifact);
			}
		}


	}

	protected QName retrieveQNameOfSource(EList<TArtifact> artifacts,
			TArtifact textAnnotation) {
		QName source = null;
		for (TArtifact tArtifact2 : artifacts) {
			if (tArtifact2 instanceof TAssociation) {
				TAssociation assosciation = ((TAssociation) tArtifact2);
				if (assosciation.getSourceRef().getLocalPart()
						.equals(textAnnotation.getId())) {
					source = assosciation.getTargetRef();
					break;
				}
				if (assosciation.getTargetRef().getLocalPart()
						.equals(textAnnotation.getId())) {
					source = assosciation.getSourceRef();
					break;
				}
			}
		}
		return source;
	}

	/**
	 * @param poolEditPart
	 * @param flowElement
	 * @throws ProcBuilderException
	 */
	private void processMessageFlow(IGraphicalEditPart diagramPart,
			TCollaboration collab) throws ProcBuilderException {
		for (TMessageFlow messageFLow : collab.getMessageFlow()) {
			String name = messageFLow.getName() != null ? messageFLow.getName()
					: messageFLow.getId();
			String eventName = messageFLow.getSourceRef().getLocalPart() + "_"
					+ messageFLow.getTargetRef().getLocalPart();
			builder.addMessageFlow(name, eventName, messageFLow.getSourceRef()
					.getLocalPart(), messageFLow.getTargetRef().getLocalPart(),
					null, null, new PointList());
		}
	}

	/**
	 * @param el
	 * @throws Exception
	 */
	private Point createSubProcess(TSubProcess subProcess, Point location)
			throws ProcBuilderException {
		subProc = null;
		// boolean oldUseBpmnDiagram = useBpmnDiagram;
		subProc = getSubProcessShapeType(subProcess.getId());
		/*
		 * if the subproc is collapsed and that we can find a BPMN diagram just
		 * for this subprocess use it
		 */
		if (subProc != null) {
			if (!subProc.isIsExpanded()) {
				/* search another diagram */
				for (BPMNDiagram dt : bpmnDiagrams) {
					final BPMNPlane bpmnPlane = dt.getBPMNPlane();
					final QName bpmnElementOfBpmnPlane = bpmnPlane
							.getBpmnElement();
					if (bpmnElementOfBpmnPlane != null) {
						final String localPart = bpmnElementOfBpmnPlane
								.getLocalPart();
						if (localPart.startsWith(subProcess.getId())) {
							/* we find it, so reinit the diagram to use it. */
							initDiagramFor(subProcess.getId());
							break;
						}
					} else {
						// bpmn element not specified and we are in case of a
						// subprocess
					}
				}
			}
		} else {
			// TODO: improve the search taking care of possible expanded
			// subprocess that caontains this subprocess
			/* search another diagram that contains the subproc */
			TBaseElement baseElement = getBPMNElementParentOfID(subProcess
					.getId());
			if (baseElement != null) {
				initDiagramFor(baseElement.getId());
			} else {
				// this should not happened
			}
		}

		// final String subProcessName = subProcess.getName();
		// String name = NamingUtils.convertToId((subProcessName != null &&
		// subProcessName.length()>0)?
		// subProcessName:NamingUtils.getPaletteText(false,
		// ProcessPackage.Literals.CALL_ACTIVITY));
		// FIXME : we are loosing the name of the subprocess :'(
		if (subProc != null) {
			createPool("subProc_"+NamingUtils.convertToId(subProcess.getId()),
					"subProc_"+NamingUtils.convertToId(subProcess.getId()), location, true);
		} else {
			createPool("subProc_"+NamingUtils.convertToId(subProcess.getId()),
					"subProc_"+NamingUtils.convertToId(subProcess.getId()), location,
					false);
		}
		location.x += 220; // next

		final EList<TFlowElement> flowElements = subProcess.getFlowElement();
		if (subProc != null) {
			processActivities(flowElements, true);
		} else {
			processActivities(flowElements, false);
		}
		processBoundaries(flowElements);
		processTransitions(flowElements);
		// if(triggeredByEvent){
		// //link the sub process to it's pool
		// TreeIterator<EObject> eAllContents =
		// poolEditPart.resolveSemanticElement().eContainer().eAllContents();
		// while(eAllContents.hasNext()){
		// EObject next = eAllContents.next();
		// if(next instanceof SubProcessEvent){
		// SubProcessEvent subProcEvent = (SubProcessEvent) next;
		// if(subProcEvent.getName().equals(convertID(subProcess.getId()))){
		// subProcEvent.setSubProcessEvent((EventSubProcessPool)
		// poolEditPart.resolveSemanticElement());
		// break;
		// }
		// }
		// }
		// }

		return location;
	}

	private TBaseElement getBPMNElementParentOfID(String id) {
		if (bpmnProcess.size() == 1) {
			return bpmnProcess.get(0);
		}
		for (TProcess tProcess : bpmnProcess) {
			for (TFlowElement tFlowElement : tProcess.getFlowElement()) {
				if (tFlowElement.getId().equals(id)) {
					return tProcess;
				} else if (tFlowElement instanceof TSubProcess) {
					if (isIdIn((TSubProcess) tFlowElement, id)) {
						return tProcess;
					}
				}
			}
		}
		return null;
	}

	private boolean isIdIn(TSubProcess tFlowElement, String id) {
		for (TFlowElement childFlowElement : tFlowElement.getFlowElement()) {
			if (childFlowElement.getId().equals(id)) {
				return true;
			} else if (childFlowElement instanceof TSubProcess) {
				return isIdIn((TSubProcess) childFlowElement, id);
			}
		}
		return false;
	}

	private BPMNShape getSubProcessShapeType(String id) {
		for (BPMNPlane processDiagram : bpmnProcessDiagrams) {
			for (DiagramElement diagramElement : processDiagram
					.getDiagramElement()) {
				if (diagramElement instanceof BPMNShape) {
					if (((BPMNShape) diagramElement).getBpmnElement()
							.getLocalPart().equals(id)) {
						return (BPMNShape) diagramElement;
					}
				}
			}
		}
		return subProc;
	}

	/**
	 * @param isEventSubProcess
	 * @param eList
	 * @param xpdlPackage
	 * @return
	 * @throws Exception
	 */
	private void createPool(String id, String name, Point location,
			boolean fromSubprocess) throws ProcBuilderException {
		Dimension poolSize = null;
		if (useBpmnDiagram) {
			if (!fromSubprocess) {
				poolSize = getSizeForPool(id);
			} else {
				int maxY = 0;
				int maxX = 0;
				final String subProcBpmnIdlocalPart = subProc.getBpmnElement()
						.getLocalPart();
				for (TProcess proc : bpmnProcess) {
					Point max = findMax(subProcBpmnIdlocalPart,
							proc.getFlowElement());
					maxY = Math.max(maxY, max.y);
					maxX = Math.max(maxX, max.x);
				}
				poolSize = new Dimension(maxX
						- (int) subProc.getBounds().getX() + 200, maxY + 100);
			}
		}
		if (poolSize == null) {
			//			location.translate(0, poolSize.height);
			//		} else {
			poolSize =  new Dimension(1200,250);
			//		location.translate(0, 250);// default size
		}

		builder.addPool(id, name, "1.0", location, poolSize);
		addFontStyle(id);
	}

	private String getParticipantWithProcessRef(String processId) {
		Iterator<TRootElement> rootElementsIterator = rootElements.iterator();
		while (rootElementsIterator.hasNext()) {
			TRootElement tRootElement = (TRootElement) rootElementsIterator.next();
			if(tRootElement instanceof TCollaboration){
				for(TParticipant participant : ((TCollaboration) tRootElement).getParticipant()){
					final QName processRef = participant.getProcessRef();
					if(processRef != null  && processId.equals(processRef.getLocalPart())){
						return participant.getId();
					}
				}
			}
		}
		return null;
	}


	private Point findMax(String subProcBpmnIdlocalPart,
			EList<TFlowElement> flowElement) {
		Point result = new Point();
		for (TFlowElement o : flowElement) {
			if (o instanceof TSubProcess) {
				TSubProcess sub = (TSubProcess) o;
				// Point loc = findMaxInsideSubProc();
				if (sub.getId().equals(subProcBpmnIdlocalPart)) {
					for (TFlowElement child : sub.getFlowElement()) {
						final Point locationForChild = getLocationFor(child
								.getId());
						result.y = Math.max(result.y,
								locationForChild != null ? locationForChild.y
										: result.y);
						result.x = Math.max(result.x,
								locationForChild != null ? locationForChild.x
										: result.x);
					}
					break;// we find it so go out of this for
				}
				/* If this is not the subprocess sub, search inside it */
				result = findMax(subProcBpmnIdlocalPart, sub.getFlowElement());
			}

		}
		return result;
	}

	private void initDiagramFor(String id) {
		bpmnProcessDiagrams = new ArrayList<BPMNPlane>();
		TCollaboration collaboration = null;
		Iterator<TRootElement> rootElementIterator = rootElements.iterator();
		while (collaboration == null && rootElementIterator.hasNext()) {
			TRootElement rootElement = rootElementIterator.next();
			if (rootElement instanceof TCollaboration) {
				for (TParticipant participant : ((TCollaboration) rootElement)
						.getParticipant()) {
					final QName participantProcessRef = participant
							.getProcessRef();
					if (participantProcessRef != null
							&& participantProcessRef.getLocalPart().equals(id)) {
						collaboration = (TCollaboration) rootElement;
						break;
					}
				}
			}
		}

		String idToSearch;
		if (collaboration != null) {
			idToSearch = collaboration.getId();
		} else {
			idToSearch = id;
		}

		for (BPMNDiagram dt : bpmnDiagrams) {
			final BPMNPlane bpmnPlane = dt.getBPMNPlane();
			// check both because some are related to ther collaborationId and
			// other only to the process even if there is collaboration
			final QName bpmnElementForBpmnPlane = bpmnPlane.getBpmnElement();
			if (bpmnElementForBpmnPlane != null) {
				final String localPart = bpmnElementForBpmnPlane.getLocalPart();
				if (idToSearch.startsWith(localPart)
						|| id.startsWith(localPart)) {
					bpmnProcessDiagrams.add(bpmnPlane);
				}
				// if(dt instanceof TProcess){
				// if(((TProcess)dt).getProcessType().getName().startsWith(id)){
				// bpmnProcessDiagrams.add((TProcess)dt);
				// }
				// }
			}/*
			 * else { //noob guys don't specify to which bpmn element the bpmn
			 * plane is related, // in this case put them all in the stack and
			 * we will hope that there was not several bpmnplanes
			 * bpmnProcessDiagrams.add(bpmnPlane); }
			 */
		}

		if (bpmnProcessDiagrams.isEmpty()) {// add all bpmn plane that have no
			// bpmn element related to
			for (BPMNDiagram dt : bpmnDiagrams) {
				final BPMNPlane bpmnPlane = dt.getBPMNPlane();
				// check both because some are related to ther collaborationId
				// and other only to the process even if there is collaboration
				final QName bpmnElementForBpmnPlane = bpmnPlane
						.getBpmnElement();
				if (bpmnElementForBpmnPlane == null) {
					// noob guys don't specify to which bpmn element the bpmn
					// plane is related,
					// in this case put them all in the stack and we will hope
					// that there was not several bpmnplanes
					bpmnProcessDiagrams.add(bpmnPlane);
				}
			}
		}

		useBpmnDiagram = !bpmnProcessDiagrams.isEmpty();
	}

	/**
	 * @param poolPart
	 * @param process
	 * @throws ProcBuilderException
	 */
	private void processTransitions(List<TFlowElement> flowElements)
			throws ProcBuilderException {

		for (TFlowElement flowElement : flowElements) {
			if (flowElement instanceof TSequenceFlow) {
				TSequenceFlow sequenceFlow = (TSequenceFlow) flowElement;

				/* Search the graphical BPMNEdge */
				final String sequenceFlowID = sequenceFlow.getId();
				final PointList bendpoints = computeBendpoints(sequenceFlowID);

				/*
				 * Search the incoming element and look if we are a defaultFlow
				 * for him
				 */
				boolean isDefault = isSequenceFlowDefault(sequenceFlow,	sequenceFlowID);

				String sourceId = sequenceFlow.getSourceRef();
				String targetId = sequenceFlow.getTargetRef();
				builder.addSequenceFlow(sequenceFlow.getName(), sourceId,
						targetId, isDefault, null, null, bendpoints);
				if (sequenceFlow.getConditionExpression() != null) {
					Expression basedExpression = getBonitaExpressionFromBPMNExpression(sequenceFlow
							.getConditionExpression());
					builder.addSequenceFlowCondition(
							basedExpression.getContent(),
							basedExpression.getInterpreter(),
							basedExpression.getType());
				}
			}
		}
	}

	private boolean isSequenceFlowDefault(TSequenceFlow sequenceFlow,
			final String sequenceFlowID) {
		boolean isDefault = false;
		final String sourceRef = sequenceFlow.getSourceRef();
		if (sourceRef != null && sourceRef.length() != 0) {
			for (TProcess tProcess : bpmnProcess) {
				for (TFlowElement tFlowElement : tProcess
						.getFlowElement()) {
					if (sourceRef.equals(tFlowElement.getId())) {
						if (tFlowElement instanceof TInclusiveGateway) {
							if (sequenceFlowID.equals(
									((TInclusiveGateway) tFlowElement)
									.getDefault())) {
								isDefault = true;
							}
						} else if (tFlowElement instanceof TExclusiveGateway) {
							if (sequenceFlowID.equals(
									((TExclusiveGateway) tFlowElement)
									.getDefault())) {
								isDefault = true;
							}
						} else if (tFlowElement instanceof TComplexGateway) {
							if (sequenceFlowID.equals(
									((TComplexGateway) tFlowElement)
									.getDefault())) {
								isDefault = true;
							}
						}
						break;
					}
				}
			}
		}
		return isDefault;
	}

	private PointList computeBendpoints(final String sequenceFlowID) {
		BPMNEdge edge = getBPMNEdgeFor(sequenceFlowID);
		PointList bendpoints = new PointList();
		if (edge != null) {
			/* Convert WayPoints to PointList */
			EList<org.omg.spec.dd.dc.Point> wayPoints = edge
					.getWaypoint();
			Point containerLocation = getContainerLocationFor(
					sequenceFlowID).getNegated();
			for (int i = 0; i < wayPoints.size(); i++) {
				org.omg.spec.dd.dc.Point ddp = wayPoints.get(i);
				bendpoints.insertPoint(new Point((int) ddp.getX(),
						(int) ddp.getY()).translate(containerLocation),
						i);
			}
		}
		return bendpoints;
	}

	/**
	 * @param containerPart
	 * @param process
	 * @throws ProcBuilderException
	 */
	private int processActivities(List<TFlowElement> flowElements, boolean fromSubProcess) throws ProcBuilderException {
		int activityNumber = 0;
		if (flowElements != null) {
			for (TFlowElement flowElement : flowElements) {
				if (flowElement instanceof TFlowNode) {
					TFlowNode flowNode = (TFlowNode) flowElement;
					Point location = toLocation(flowNode, activityNumber,
							fromSubProcess);
					posMax = new Point(Math.max(posMax.x, location.x + 50),
							Math.max(posMax.y, location.y + 50));

					TaskType taskType = TaskType.ABSTRACT;
					EventType eventType = EventType.START;
					GatewayType gateType = GatewayType.AND;
					boolean isEvent = false;
					boolean isGateway = false;
					boolean isSubprocessEvent = false;
					if (flowNode instanceof TGateway) {
						gateType = getGatewayType((TGateway) flowNode);
						isGateway = true;
					} else if (flowNode instanceof TActivity) {
						if (flowNode instanceof TSubProcess
								&& ((TSubProcess) flowNode)
								.isTriggeredByEvent()) {
							isSubprocessEvent = true;
						} else {
							taskType = getActivityType((TActivity) flowNode);
						}
					} else if (flowNode instanceof TEvent) {
						if (getEventType((TEvent) flowNode) == null) {
							/* transform it in an automatic task!! */
							taskType = TaskType.ABSTRACT;

						} else {
							isEvent = true;
							eventType = getEventType((TEvent) flowNode);
						}
					}

					if (!(flowNode instanceof TBoundaryEvent)) {// do boundaries
						// after
						activityNumber++;

						String name = computeBonitaNameOf(flowNode);

						// Documentation
						final String documentation = retrieveDocumentation(flowNode);

						if (isEvent) {
							builder.addEvent(flowNode.getId(), name, location,
									null, eventType);
							builder.addDescription(documentation);

							populateEvent(flowNode, eventType);

						} else if (isGateway) {
							String gatewayName = flowNode.getName();
							builder.addGateway(flowNode.getId(), name,
									location, null, gateType,gatewayName != null && !gatewayName.isEmpty());
							builder.addDescription(documentation);
						} else if (isSubprocessEvent) {
							processSubProcessEvent(fromSubProcess, flowNode, location, name, documentation);
						} else {
							String id = flowNode.getId();
							if (flowNode instanceof TSubProcess) {
								subProcessesId.add(id);
								//								id = NamingUtils.convertToId(/*"subProc_" +*/ id);
								//								name = id;
							}

							builder.addTask(id, name, location, null, taskType);
							builder.addDescription(documentation);

							if (flowNode instanceof TActivity) {
								/* Add Property as Transient data */
								for (TProperty tProperty : ((TActivity) flowNode)
										.getProperty()) {
									createBonitaData(flowElement, false,
											tProperty.getItemSubjectRef(), true);
								}
							}

							if (flowNode instanceof TServiceTask) {
								final TServiceTask tServiceTask = (TServiceTask) flowNode;
								handleConnector(name, id, tServiceTask);
							}

							if(flowNode instanceof TUserTask){
								EList<TResourceRole> resourceRoles = ((TUserTask) flowNode).getResourceRole();
								for (TResourceRole tResourceRole : resourceRoles) {
									//TODO: add actor to tasks
								}
							}

							if (taskType == TaskType.CALL_ACTIVITY) {
								if (flowNode instanceof TSubProcess) {
									processSubProcess((TSubProcess) flowNode);
								} else if (flowNode instanceof TCallActivity) {
									TCallActivity callActivity = (TCallActivity) flowNode;
									fillCalledActivity(callActivity);
									mapDataForCallActivity(callActivity);
								}
							}

						}
					} else if (!(flowNode instanceof TBoundaryEvent)) {
						errorElements.add(flowNode.eClass().getName() + ": "
								+ flowNode.getName());
					}
					addFontStyle(flowNode.getId());
				}
			}
		}
		return activityNumber;
	}

	private void processSubProcessEvent(boolean fromSubProcess,
			TFlowNode flowNode, Point location, String name,
			final String documentation) throws ProcBuilderException {
		TSubProcess bpmnSubProcess = ((TSubProcess) flowNode);
		Dimension sizeOFEventSubProc = getSizeFor(bpmnSubProcess
				.getId());
		BPMNShape bpmnShape = getBPMNShapeForBpmnID(flowNode
				.getId());
		builder.addEventSubprocess(flowNode.getId(), name,
				location, sizeOFEventSubProc,
				!bpmnShape.isIsExpanded());
		builder.addDescription(documentation);
		subprocEvent = bpmnSubProcess;
		processActivities(bpmnSubProcess.getFlowElement(),
				fromSubProcess);
		processTransitions(bpmnSubProcess.getFlowElement());
		builder.switchToParentContainer();
		subprocEvent = null;
	}

	private String computeBonitaNameOf(TFlowNode flowNode) {
		String name;
		if (flowNode.getName() != null
				&& !flowNode.getName().isEmpty()) {
			name = flowNode.getName();
		} else {
			name = flowNode.getId();
		}
		return name;
	}

	private void processSubProcess(TSubProcess bpmnSubProcess) throws ProcBuilderException {
		builder.addCallActivityTargetProcess(
				"subProc_"+NamingUtils.convertToId(bpmnSubProcess
						.getId()), "1.0");

		for (TDataInputAssociation input : bpmnSubProcess
				.getDataInputAssociation()) {
			for (TAssignment assignment : input
					.getAssignment()) {
				// assignment.getFrom()
			}
			builder.addCallActivityInParameter(
					input.getTargetRef(), null);
		}

		for (TDataOutputAssociation output : bpmnSubProcess
				.getDataOutputAssociation()) {
			builder.addCallActivityOutParameter(
					null, output.getTargetRef());
		}
		// the sub process event will be set with
		// the correct pool when we create it
		// create a pool for the subprocess
		subProcesses.add((TSubProcess) bpmnSubProcess);
	}

	private void fillCalledActivity(TCallActivity callActivity)	throws ProcBuilderException {
		final QName calledElement = callActivity.getCalledElement();
		if (calledElement != null) {
			final String calledElementID = calledElement.getLocalPart();
			String calledElementName = null;
			for (TRootElement rootElement : rootElements) {
				if (rootElement instanceof TProcess) {
					if(calledElementID.equals(rootElement.getId())){
						calledElementName = ((TProcess) rootElement).getName();
						break;
					}
				}
			}
			builder.addCallActivityTargetProcess(calledElementName != null?calledElementName : calledElementID,	""/*"1.0"*/);
		}
	}

	private void mapDataForCallActivity(TCallActivity callActivity)	throws ProcBuilderException {
		mapInputDataForCallActivity(callActivity);
		mapOutputDataForCallActivity(callActivity);
	}

	private void mapOutputDataForCallActivity(TCallActivity callActivity)
			throws ProcBuilderException {
		for (TDataOutputAssociation output : callActivity
				.getDataOutputAssociation()) {
			for (TAssignment assignment : output
					.getAssignment()) {
				final String assignmentFromValue = getAssignmentFromValue(assignment);
				final String fromDataName = dataNameByItemDefinition
						.get(assignmentFromValue) != null ? dataNameByItemDefinition
								.get(assignmentFromValue)
								: assignmentFromValue;

								final String assignmentToValue = getAssignmentToValue(assignment);
								final String toDataName = dataNameByItemDefinition
										.get(assignmentToValue) != null ? dataNameByItemDefinition
												.get(assignmentToValue)
												: assignmentToValue;

												builder.addCallActivityOutParameter(
														fromDataName, toDataName);
			}
		}
	}

	private void mapInputDataForCallActivity(TCallActivity callActivity)
			throws ProcBuilderException {
		for (TDataInputAssociation input : callActivity
				.getDataInputAssociation()) {
			for (TAssignment assignment : input
					.getAssignment()) {

				final String assignmentFromValue = getAssignmentFromValue(assignment);
				final String fromDataName = dataNameByItemDefinition
						.get(assignmentFromValue) != null ? dataNameByItemDefinition
								.get(assignmentFromValue)
								: assignmentFromValue;

								final String assignmentToValue = getAssignmentToValue(assignment);
								final String toDataName = dataNameByItemDefinition
										.get(assignmentToValue) != null ? dataNameByItemDefinition
												.get(assignmentToValue)
												: assignmentToValue;
												builder.addCallActivityInParameter(
														fromDataName, toDataName);
			}
		}
	}

	protected String retrieveDocumentation(TBaseElement baseElement) {
		StringBuilder sb = new StringBuilder();
		for (TDocumentation doc : baseElement.getDocumentation()) {
			final Iterator<org.eclipse.emf.ecore.util.FeatureMap.Entry> iterator = doc
					.getMixed().iterator();
			while (iterator.hasNext()) {
				FeatureMap.Entry entry = iterator.next();
				if (FeatureMapUtil.isText(entry)) {
					sb.append(entry.getValue()).append("\n");
				}
			}
		}
		final String documentation = sb.toString();
		return documentation;
	}

	protected void handleConnector(String name, String id,
			final TServiceTask tServiceTask) throws ProcBuilderException {
		QName operationRef = tServiceTask.getOperationRef();
		if (operationRef != null) {
			final String operationRefName = operationRef.getLocalPart();
			// FIXME: how to know that it is a Bonita Connector
			if (operationRefName.startsWith("Exec")) {
				processBonitaConnector(name, id, tServiceTask, operationRefName);
			} else if("##WebService".equals(tServiceTask.getImplementation())){
				// TODO: handle default implem for web services as Service tasks
				builder.addConnector(id, name, "webservices", "1.0", ConnectorEvent.ON_FINISH, true);
			}
		}
	}

	protected void processBonitaConnector(String name, String id,
			final TServiceTask tServiceTask, final String operationRefName)
					throws ProcBuilderException {
		String connectorId = operationRefName.replaceFirst("Exec", "");
		String version = "1.0.0";// FIXME where is stored version now??
		// Need to parse the filename of xsd
		String datainput = ".+";// FIXME: improve in order to avoid
		// confusion between several dtat
		// entries
		Pattern inputAttributesMatcher = Pattern
				.compile("getDataInput\\(\'"
						+ datainput
						+ "\'\\)/"
						+ XMLNS_HTTP_BONITASOFT_COM_BONITA_CONNECTOR_DEFINITION
						+ ":(.+)");
		Map<String, TExpression> connectorParameter = new HashMap<String, TExpression>();
		for (TDataInputAssociation dia : tServiceTask
				.getDataInputAssociation()) {
			for (TAssignment inputAssignment : dia.getAssignment()) {
				String toString = getAssignmentToValue(inputAssignment);
				// pattern for name and get it
				Matcher inputMatcher = inputAttributesMatcher
						.matcher(toString);
				if (inputMatcher.find()) {
					String inputKey = inputMatcher.group(1);
					TExpression inputValue = getAssignmentFromExpression(inputAssignment);
					connectorParameter.put(inputKey, inputValue);
				}
			}
		}

		Pattern connectorOuputMatcher = Pattern
				.compile("getDataOutput\\(\'"
						+ datainput
						+ "\'\\)/"
						+ XMLNS_HTTP_BONITASOFT_COM_BONITA_CONNECTOR_DEFINITION
						+ ":(.+)");
		Map<TExpression, String> connectorOutputs = new HashMap<TExpression, String>();
		for (TDataOutputAssociation doa : tServiceTask
				.getDataOutputAssociation()) {
			for (TAssignment outputAssignment : doa.getAssignment()) {
				String fromString = getAssignmentFromValue(outputAssignment);
				// pattern for name and get it
				Matcher outputConnectorMatcher = connectorOuputMatcher
						.matcher(fromString);
				if (outputConnectorMatcher.find()) {
					String outputKey = outputConnectorMatcher.group(1);
					TExpression inputValue = getAssignmentFromExpression(outputAssignment);
					connectorOutputs.put(inputValue, outputKey);
				}
			}
		}

		Pattern outputTargetDataObject = Pattern
				.compile("getDataObject\\(\'(.+)\'\\)");
		Map<String, TExpression> connectorOuputs = new HashMap<String, TExpression>();

		for (TDataOutputAssociation doa : tServiceTask
				.getDataOutputAssociation()) {
			for (TAssignment outputAssignment : doa.getAssignment()) {
				String dataObjectId = "";
				String toString = getAssignmentToValue(outputAssignment);
				Matcher matcher = outputTargetDataObject
						.matcher(toString);
				if (matcher.find()) {
					dataObjectId = matcher.group(1);
				}
				connectorOuputs.put(dataObjectId,
						outputAssignment.getFrom());
			}
		}
		// FIXME: connectorID deducted by operationRef local part... is
		// it true? Is there a best way?
		builder.addConnector(id, name, connectorId, version,
				ConnectorEvent.ON_FINISH, true);
		for (java.util.Map.Entry<String, TExpression> entry : connectorParameter
				.entrySet()) {

			final TExpression expression = entry.getValue();
			Expression bExpression = getBonitaExpressionFromBPMNExpression(expression);
			builder.addConnectorParameter(entry.getKey(),
					bExpression.getContent(),
					bExpression.getReturnType(),
					bExpression.getInterpreter(),
					bExpression.getType());// TODO how to define the real expression type
		}
		for (java.util.Map.Entry<String, TExpression> entry : connectorOuputs
				.entrySet()) {
			final String connectorOuput = connectorOutputs.get(entry
					.getValue());
			if (connectorOuput != null) {// it is a connector output
				// type
				builder.addConnectorOutput(entry.getKey(),
						connectorOuput, null,// Need to get return type
						// from connector xsd?
						null, ExpressionConstants.CONNECTOR_OUTPUT_TYPE);
			} else {
				Expression outputExpression = getBonitaExpressionFromBPMNExpression(entry
						.getValue());
				builder.addConnectorOutput(entry.getKey(),
						outputExpression.getContent(),
						outputExpression.getReturnType(),
						outputExpression.getInterpreter(),
						outputExpression.getType());
			}
		}
	}

	private Expression getBonitaExpressionFromBPMNExpression(
			TExpression expression) {
		Expression res = ExpressionFactory.eINSTANCE.createExpression();

		if (expression instanceof TFormalExpression) {
			final TFormalExpression tFormalExpression = (TFormalExpression) expression;

			String language = tFormalExpression.getLanguage();
			if (language == null) {
				// Use the one specified at the root
				language = definitions.getExpressionLanguage();
			}
			if (language != null) {
				if (ExpressionConstants.GROOVY.equals(language)
						|| "http://groovy.codehaus.org/".equals(language)) {
					res.setType(ExpressionConstants.SCRIPT_TYPE);
					res.setInterpreter(ExpressionConstants.GROOVY);
					res.setContent(getAssignmentValue(expression));
				} else if ("http://www.w3.org/1999/XPath".equals(language)) {
					Pattern datBObjectPattern = Pattern
							.compile("getDataObject\\(\'(.+)\'\\)");
					Pattern propertyPattern = Pattern
							.compile("getActivityProperty\\(\'.+\',\'(.+)\'\\)");
					Matcher mDataObject = datBObjectPattern
							.matcher(getAssignmentValue(expression));
					Matcher mProperty = propertyPattern
							.matcher(getAssignmentValue(expression));
					if (mDataObject.find()) {
						TItemDefinition tid = getItemDefinitionForDataObjectName(mDataObject
								.group(1));
						if (tid != null) {
							// FIXME : dataNameByItemDefinition can't be
							// calculated for now, fnd a way in order to
							// final Data data =
							// dataNameByItemDefinition.get(tid.getId());
							// if(data != null){
							// res.getReferencedElements().add(data);
							// }
							Data d = ProcessFactory.eINSTANCE.createData();
							d.setName(mDataObject.group(1));
							res.getReferencedElements().add(d);
							res.setType(ExpressionConstants.VARIABLE_TYPE);
							res.setContent(mDataObject.group(1));
							res.setInterpreter(null);
						} else {
							res.setType(ExpressionConstants.VARIABLE_TYPE);
							res.setContent(getAssignmentValue(expression));
							res.setInterpreter(null);
						}
					} else if (mProperty.find()) {
						TItemDefinition tid = getItemDefinitionForPropertyId(mProperty
								.group(1));
						if (tid != null) {
							// final Data data =
							// dataNameByItemDefinition.get(tid.getId());
							// if(data != null){
							// res.getReferencedElements().add(EcoreUtil.copy(data));
							// }
							res.setType(ExpressionConstants.VARIABLE_TYPE);
							res.setContent(mProperty.group(1));
							res.setInterpreter(null);
						} else {
							res.setType(ExpressionConstants.VARIABLE_TYPE);
							res.setContent(getAssignmentValue(expression));
							res.setInterpreter(null);
						}

					} else {
						res.setType(ExpressionConstants.CONSTANT_TYPE);
						res.setInterpreter(null);
						res.setContent(getAssignmentValue(expression));
					}

					// } else
					// if(ExpressionConstants.CONSTANT_TYPE.equals(language)){
					// res.setType(ExpressionConstants.CONSTANT_TYPE);
					// } else
					// if(ExpressionConstants.VARIABLE_TYPE.equals(language)){
					// res.setType(ExpressionConstants.VARIABLE_TYPE);
					// TItemDefinition tid =
					// getItemDefinition(QName.valueOf(getAssignmentValue(expression)));
					// res.setContent(tid.getId());
				} else {
					// Put Script with Groovy by default to avoid lose of data
					// TODO: add a warning at import
					res.setType(ExpressionConstants.SCRIPT_TYPE);
					res.setInterpreter(ExpressionConstants.GROOVY);
					res.setContent(getAssignmentValue(expression));
				}
			}

			final QName evaluatesToTypeRef = tFormalExpression
					.getEvaluatesToTypeRef();
			if (evaluatesToTypeRef != null) {
				res.setReturnType(evaluatesToTypeRef.getLocalPart());
			} else {
				// Put String by default
				res.setReturnType("java.lang.String");
			}
			// if(!ExpressionConstants.VARIABLE_TYPE.equals(language)){
			// res.setContent(getAssignmentValue(expression));
			// }
		} else {
			res.setType(ExpressionConstants.SCRIPT_TYPE);
			res.setInterpreter(ExpressionConstants.GROOVY);
			res.setContent(getAssignmentValue(expression));
		}

		return res;
	}

	private TExpression getAssignmentFromExpression(TAssignment inputAssignment) {
		return inputAssignment.getFrom();
	}

	protected String getAssignmentFromValue(TAssignment inputAssignment) {// TODO
		// doit
		// retourner
		// une
		// Expression
		// ou
		// au
		// moins
		// les
		// arguments
		// n�cessaire
		// sporu
		// reconstruire
		// l'expression
		// del'autre
		// c�t�
		final TExpression from = inputAssignment.getFrom();
		return getAssignmentValue(from);
	}

	protected String getAssignmentToValue(TAssignment inputAssignment) {
		final TExpression to = inputAssignment.getTo();
		return getAssignmentValue(to);
	}

	protected String getAssignmentValue(final TExpression to) {
		if (to != null) {
			final FeatureMap mixed = to.getMixed();
			if (mixed != null && !mixed.isEmpty()) {
				final Entry entry = mixed.get(0);
				if (entry != null) {
					return (String) entry.getValue();
				}
			}
		}
		return "";
	}

	protected void populateEvent(TFlowNode flowNode, EventType eventType)
			throws ProcBuilderException {
		if (eventType == EventType.ERROR_BOUNDARY
				|| eventType == EventType.END_ERROR
				|| eventType == EventType.START_ERROR) {
			populateErrorEvent(flowNode);
		} else if (eventType == EventType.SIGNAL_BOUNDARY
				|| eventType == EventType.INTERMEDIATE_CATCH_SIGNAL
				|| eventType == EventType.INTERMEDIATE_THROW_SIGNAL
				|| eventType == EventType.END_SIGNAL) {
			populateSignalEvent(flowNode);
		} else if (eventType == EventType.TIMER_BOUNDARY
				|| eventType == EventType.INTERMEDIATE_CATCH_TIMER
				|| eventType == EventType.START_TIMER) {
			populateTimerEvent(flowNode);
		} else if (eventType == EventType.INTERMEDIATE_THROW_LINK) {
			populateThrowLinkEvent(flowNode);
		}
	}

	private void populateErrorEvent(TFlowNode flowNode)
			throws ProcBuilderException {
		String errorCode = null;
		if (flowNode instanceof TCatchEvent) {
			final QName errorRef = ((TErrorEventDefinition) ((TCatchEvent) flowNode)
					.getEventDefinition().get(0)).getErrorRef();
			if (errorRef != null) {
				errorCode = errorRef.getLocalPart();
			}
		} else {
			final QName errorRef = ((TErrorEventDefinition) ((TThrowEvent) flowNode)
					.getEventDefinition().get(0)).getErrorRef();
			if (errorRef != null) {
				errorCode = errorRef.getLocalPart();
			}
		}
		if (errorCode != null) {
			builder.addErrorCode(errorCode);
		}
	}

	private void populateThrowLinkEvent(TFlowNode flowNode)
			throws ProcBuilderException {
		if (flowNode instanceof TThrowEvent) {
			for (TEventDefinition eventDef : ((TThrowEvent) flowNode)
					.getEventDefinition()) {
				if (eventDef instanceof TLinkEventDefinition) {
					QName sourceQname = ((TLinkEventDefinition) eventDef).getTarget();
					if(sourceQname != null){
						builder.addThrowLinkEventTarget(sourceQname.getLocalPart());
						break;
						// it should have only one TLinkEventDefinition
					}
				}
			}
		}
	}

	/**
	 * @param flowNode, flowNode can be a TCatchEvent or a TThrowEvent
	 */
	private void populateSignalEvent(TFlowNode flowNode) throws ProcBuilderException {
		final String signalRef = retrieveSignalReference(flowNode);
		if (signalRef != null) {
			addSignalCodeFor(signalRef);
		}
	}

	/**
	 * @param flowNode, flowNode can be a TCatchEvent or a TThrowEvent
	 * @return
	 */
	private String retrieveSignalReference(TFlowNode flowNode) {
		String signalRef = null;
		QName signalRefAsQName = retrieveTSignalEventDefinition(flowNode).getSignalRef();			
		if (signalRefAsQName != null) {
			signalRef = signalRefAsQName.getLocalPart();
		}
		return signalRef;
	}

	/**
	 * @param flowNode, flowNode can be a TCatchEvent or a TThrowEvent
	 * @return
	 */
	private TSignalEventDefinition retrieveTSignalEventDefinition(TFlowNode flowNode){
		if (flowNode instanceof TCatchEvent) {
			return (TSignalEventDefinition) ((TCatchEvent) flowNode).getEventDefinition().get(0);
		} else {
			return (TSignalEventDefinition) ((TThrowEvent) flowNode).getEventDefinition().get(0);
		}
	}

	private void addSignalCodeFor(String signalRef) throws ProcBuilderException {
		for (TRootElement rootElem : definitions.getRootElement()) {
			if (rootElem instanceof TSignal) {
				TSignal signal = (TSignal) rootElem;
				if (signal.getId().equals(signalRef)) {
					final String signalName = signal.getName();
					if (signalName != null && signalName.length() != 0) {
						builder.addSignalCode(signalName);
					} else {
						builder.addSignalCode(signal.getId());
					}
					break;
				}
			}
		}
	}

	private void populateTimerEvent(TFlowNode flowNode) throws ProcBuilderException {
		TTimerEventDefinition timerEventDefinition = getTimerEventDefinition(flowNode);
		final TExpression timeDate = timerEventDefinition.getTimeDate();
		if (timeDate != null) {
			addTimerEventConditionIfProvided(timeDate);
		} else {
			final TExpression timeDuration = timerEventDefinition.getTimeDuration();
			if (timeDuration != null) {
				addTimerEventConditionIfProvided(timeDuration);
			} else {
				final TExpression timeCycle = timerEventDefinition.getTimeCycle();
				if (timeCycle != null) {
					addTimerEventConditionIfProvided(timeCycle);
				}
			}
		}
	}

	private TTimerEventDefinition getTimerEventDefinition(TFlowNode flowNode) {
		TTimerEventDefinition timerEventDefinition;
		if (flowNode instanceof TCatchEvent) {
			timerEventDefinition = ((TTimerEventDefinition) ((TCatchEvent) flowNode)
					.getEventDefinition().get(0));
		} else {
			timerEventDefinition = ((TTimerEventDefinition) ((TThrowEvent) flowNode)
					.getEventDefinition().get(0));
		}
		return timerEventDefinition;
	}

	private void addTimerEventConditionIfProvided(final TExpression timeDate)
			throws ProcBuilderException {
		FeatureMap mixedTimedate = timeDate.getMixed();
		if (mixedTimedate != null && !mixedTimedate.isEmpty()
				&& mixedTimedate.getValue(0) != null) {
			builder.addTimerEventCondition(mixedTimedate.getValue(0)
					.toString());
		}
	}

	private TBaseElement retrieveElementWithID(String id) {
		if(id != null && !id.isEmpty()){
			for(TRootElement rootElement : rootElements){
				TreeIterator<EObject> eAllContents = rootElement.eAllContents();
				while(eAllContents.hasNext()){
					EObject element = eAllContents.next();
					if(element instanceof TBaseElement){
						if(id.equals(((TBaseElement) element).getId())){
							return (TBaseElement) element;
						}
					}
				}
			}
		}
		return null;
	}

	/**
	 * @param model
	 * @param process
	 * @throws ProcBuilderException
	 */
	private void processDataObjects(TProcess process)
			throws ProcBuilderException {
		for (TFlowElement flowElement : process.getFlowElement()) {
			if (flowElement instanceof TDataObject) {
				boolean isMultiple = false;
				if (((TDataObject) flowElement).isIsCollection()) {
					isMultiple = ((TDataObject) flowElement).isIsCollection();
				}
				QName itemSubjectRefOfDataObject = ((TDataObject) flowElement)
						.getItemSubjectRef();
				createBonitaData(flowElement, isMultiple,
						itemSubjectRefOfDataObject, false);
			} else {
				// The dataobject should have an process scope
			}
		}
	}

	protected void createBonitaData(TFlowElement flowElement,
			boolean isMultiple, QName itemDef, boolean isTransient)
					throws ProcBuilderException {
		TItemDefinition itemDefinition = getItemDefinition(itemDef);
		String id = null;
		DataType dataType = null;
		if (itemDefinition != null) {
			if (itemDefinition.getId() != null) {
				id = flowElement.getId();
			} else {
				id = "data" + dataNameByItemDefinition.size();
			}
			dataType = getDataType(itemDefinition);
		} else {
			if (flowElement.getId() != null) {
				id = flowElement.getId();
			} else {
				id = "data" + dataNameByItemDefinition.size();
			}
			dataType = DataType.STRING;
		}

		String name = createBonitaDataName(flowElement, id);
		String defaultValueContent = "";
		String defaultValueReturnType = "";
		String defaultValueInterpreter = "";
		if (flowElement instanceof TActivity) {
			for (TDataInputAssociation dataInputAssociation : ((TActivity) flowElement).getDataInputAssociation()) {
				for (TAssignment assignment : dataInputAssociation.getAssignment()) {
					TExpression to = assignment.getTo();
					if (to != null) {
						FeatureMap toMixed = to.getMixed();
						if (toMixed != null && !toMixed.isEmpty()) {
							Entry entry = toMixed.get(0);
							if (entry != null) {
								String entryValue = ((String) entry.getValue());// .replaceFirst(Matcher.quoteReplacement("getDataInput('"),
								// "").replace(Matcher.quoteReplacement("\')"),
								// "");
								TDataInput dataInput = getDataInputById((TActivity) flowElement, entryValue);
								if (dataInput != null) {
									TProperty property = getPropertyByItemSubjectRef(((TActivity) flowElement),	dataInput.getItemSubjectRef());
									if (property != null) {
										TExpression fromExpression = assignment.getFrom();
										defaultValueContent = retrieveDefaultValueContent(fromExpression);

										if (fromExpression instanceof TFormalExpression) {
											TFormalExpression fromFormalExpression = (TFormalExpression) fromExpression;
											defaultValueInterpreter = retrieveDefaultValueInterpreter(fromFormalExpression);
											QName evaluatesToTypeRef = fromFormalExpression.getEvaluatesToTypeRef();
											if (evaluatesToTypeRef != null) {
												defaultValueReturnType = evaluatesToTypeRef.getLocalPart();
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

		if (IProcBuilder.DataType.XML.equals(dataType)) {
			builder.addXMLData(id, name, defaultValueContent,
					defaultValueReturnType, defaultValueInterpreter,
					itemDefinition.getStructureRef().getLocalPart(),
					itemDefinition.getStructureRef().getNamespaceURI(),
					isMultiple, isTransient, ExpressionConstants.XPATH_TYPE);
		} else if (IProcBuilder.DataType.JAVA.equals(dataType)) {
			builder.addJavaData(id, name, defaultValueContent,
					defaultValueReturnType, defaultValueInterpreter,
					itemDefinition.getStructureRef().getLocalPart(),
					isMultiple, isTransient, ExpressionConstants.JAVA_TYPE);
		} else {
			String defaultValueExpressionType = null;
			if ("http://groovy.codehaus.org/".equals(defaultValueInterpreter)) {
				defaultValueExpressionType = ExpressionConstants.SCRIPT_TYPE;
			} else if ("http://www.w3.org/1999/XPath".equals(defaultValueInterpreter)) {
				defaultValueExpressionType = ExpressionConstants.CONSTANT_TYPE;
			}
			builder.addData(id, name, defaultValueContent,
					defaultValueReturnType, defaultValueInterpreter,
					isMultiple, isTransient, dataType,
					defaultValueExpressionType);// TODO how to define the real
			// type of the expression??
		}
		if (itemDefinition != null) {
			dataNameByItemDefinition.put(itemDefinition.getId(), name);
		}
	}

	private String createBonitaDataName(TFlowElement flowElement, String id) {
		String name = id;
		if (flowElement.getName() == null) {
			if (flowElement.getId() == null) {
				name = "data" + dataNameByItemDefinition.size();
			} else {
				name = flowElement.getId();
			}
		} else {
			name = flowElement.getName();
		}
		return name;
	}

	private String retrieveDefaultValueInterpreter(
			TFormalExpression fromFormalExpression) {
		String defaultValueInterpreter;
		String language = fromFormalExpression.getLanguage();
		if (language == null) {
			language = definitions.getExpressionLanguage();
		}
		defaultValueInterpreter = language;
		return defaultValueInterpreter;
	}

	private String retrieveDefaultValueContent(TExpression fromExpression) {
		String defaultValueContent = "";
		FeatureMap fromMixed = fromExpression
				.getMixed();
		if (fromMixed != null && !fromMixed.isEmpty()) {
			Entry fromEntry = fromMixed.get(0);
			if (fromEntry != null) {
				defaultValueContent = (String) fromEntry
						.getValue();
			}
		}
		return defaultValueContent;
	}

	private TProperty getPropertyByItemSubjectRef(TActivity tActivity,
			QName qName) {
		for (TProperty tProperty : tActivity.getProperty()) {
			if (tProperty.getItemSubjectRef().equals(qName)) {
				return tProperty;
			}
		}
		return null;
	}

	private TDataInput getDataInputById(TActivity tActivity, String entryValue) {
		for (TDataInput tdi : tActivity.getIoSpecification().getDataInput()) {
			if (entryValue.equals(tdi.getId())) {
				return tdi;
			}
		}
		return null;
	}

	private final static String[] toStringTable = { "string", "Name", "NCName", "QName", "normalizedString" };
	private final static Set<String> toString = new HashSet<String>(Arrays.asList(toStringTable));
	private final static String[] toIntegerTable = { "byte", "int", "integer",
		"nonPositiveInteger", "nonNegativeInteger", "positiveInteger",
		"short", "unsignedByte", "unsignedInt", "unsignedShort" };
	private final static Set<String> toInteger = new HashSet<String>(Arrays.asList(toIntegerTable));
	private final static String[] toDecimalTable = { "decimal", "double", "float", "unsignedLong" };
	private final static Set<String> toDecimal = new HashSet<String>(Arrays.asList(toDecimalTable));
	private final static String[] toDateTable = { "date", "dateTime" };
	private final static Set<String> toDate = new HashSet<String>(Arrays.asList(toDateTable));
	private boolean isInSubProcEventContainerSearch;

	/**
	 * 
	 * @param itemDefinition
	 * @return
	 */
	private DataType getDataType(TItemDefinition itemDefinition) {
		// TODO: in order to support more import, we should be able to
		// understand also when there is simple xsd type
		String prefix = itemDefinition.getStructureRef().getPrefix();
		String dataTypeToRetrieve = itemDefinition.getStructureRef().getLocalPart();
		return getDataType(prefix, dataTypeToRetrieve);
	}

	protected DataType getDataType(String prefix, String dataTypeToRetrieve) {
		if (JAVA_XMLNS.equals(prefix)) {
			if (dataTypeToRetrieve != null) {
				return getDataTypeForJavaSpace(dataTypeToRetrieve);
			}
		} else if ("xsd".equals(prefix)) {
			return getDataTypeForXmlSpace(dataTypeToRetrieve);
		} else {
			// another xml types? how to check?
			return DataType.XML;
		}
		return null;
	}

	private DataType getDataTypeForXmlSpace(String dataTypeToRetrieve) {
		if (toString.contains(dataTypeToRetrieve)) {
			return DataType.STRING;
		} else if (toInteger.contains(dataTypeToRetrieve)) {
			return DataType.INTEGER;
		} else if (toDecimal.contains(dataTypeToRetrieve)) {
			return DataType.DECIMAL;
		} else if (toDate.contains(dataTypeToRetrieve)) {
			return DataType.DATE;
		} else if ("boolean".equals(dataTypeToRetrieve)) {
			return DataType.BOOLEAN;
		} else {
			return null;
		}
	}

	private DataType getDataTypeForJavaSpace(String dataTypeToRetrieve) {
		if ("java.lang.Long".equals(dataTypeToRetrieve)) {
			return DataType.LONG;
		} else if ("java.lang.Integer".equals(dataTypeToRetrieve)) {
			return DataType.INTEGER;
		} else if ("java.lang.Double".equals(dataTypeToRetrieve)) {
			return DataType.DOUBLE;
		} else if ("java.util.Date".equals(dataTypeToRetrieve)) {
			return DataType.DATE;
		} else if ("java.lang.Boolean".equals(dataTypeToRetrieve)) {
			return DataType.BOOLEAN;
		} else if ("org.ow2.bonita.facade.runtime.AttachmentInstance"
				.equals(dataTypeToRetrieve)) {
			return DataType.ATTACHMENT;
		} else if ("java.lang.String".equals(dataTypeToRetrieve)) {
			return DataType.STRING;
		} else if ("java.lang.Float".equals(dataTypeToRetrieve)) {
			return DataType.DECIMAL;
		} else {// java class
			return DataType.JAVA;
		}
	}

	private TItemDefinition getItemDefinition(QName itemSubjectRef) {
		if (itemSubjectRef == null || itemSubjectRef.getLocalPart() == null
				|| itemSubjectRef.getLocalPart().isEmpty()) {
			return null;
		}
		for (TRootElement rootElement : definitions.getRootElement()) {
			if (rootElement instanceof TItemDefinition) {
				if (itemSubjectRef.getLocalPart().equals(rootElement.getId())) {
					return (TItemDefinition) rootElement;
				}
			}
		}
		return null;
	}

	private TItemDefinition getItemDefinitionForPropertyId(String propertyName) {
		if (propertyName == null) {
			return null;
		}

		for (TRootElement rootElement : definitions.getRootElement()) {
			if (rootElement instanceof TDataObject) {
				if (propertyName.equals(((TDataObject) rootElement).getName())) {
					return getItemDefinition(((TDataObject) rootElement)
							.getItemSubjectRef());
				}
			} else if (rootElement instanceof TProcess) {
				for (TFlowElement tfe : ((TProcess) rootElement)
						.getFlowElement()) {
					if (tfe instanceof TProperty) {
						if (propertyName.equals(((TProperty) rootElement)
								.getId())) {
							return getItemDefinition(((TProperty) rootElement)
									.getItemSubjectRef());
						}
					} else if (tfe instanceof TSubProcess) {// FIXME handle
						// recursivity
						for (TFlowElement tfe2 : ((TSubProcess) rootElement)
								.getFlowElement()) {
							if (tfe2 instanceof TTask) {
								for (TProperty prop : ((TTask) tfe2)
										.getProperty()) {
									if (propertyName.equals(prop.getId())) {
										return getItemDefinition(prop
												.getItemSubjectRef());
									}
								}
							}
						}
					} else if (tfe instanceof TActivity) {
						for (TProperty prop : ((TActivity) tfe).getProperty()) {
							if (propertyName.equals(prop.getName())) {
								return getItemDefinition(prop
										.getItemSubjectRef());
							}
						}
					}
				}
			}
		}
		return null;
	}

	private TItemDefinition getItemDefinitionForDataObjectName(
			String dataObjectName) {
		if (dataObjectName == null) {
			return null;
		}

		for (TRootElement rootElement : definitions.getRootElement()) {
			if (rootElement instanceof TDataObject) {
				if (dataObjectName
						.equals(((TDataObject) rootElement).getName())) {
					return getItemDefinition(((TDataObject) rootElement)
							.getItemSubjectRef());
				}
			} else if (rootElement instanceof TProcess) {
				for (TFlowElement tfe : ((TProcess) rootElement)
						.getFlowElement()) {
					if (tfe instanceof TDataObject) {
						if (dataObjectName
								.equals(((TDataObject) tfe).getName())
								|| dataObjectName.equals(((TDataObject) tfe)
										.getId())) {
							return getItemDefinition(((TDataObject) tfe)
									.getItemSubjectRef());
						}
					} else if (rootElement instanceof TSubProcess) {// FIXME
						// handle
						// recursivity
						for (TFlowElement tfe2 : ((TProcess) rootElement)
								.getFlowElement()) {
							if (tfe2 instanceof TDataObject) {
								if (dataObjectName.equals(((TDataObject) tfe2)
										.getName())) {
									return getItemDefinition(((TDataObject) tfe2)
											.getItemSubjectRef());
								}
							}
						}
					}
				}
			}
		}
		return null;
	}

	private GatewayType getGatewayType(TGateway flowNode) {

		if (flowNode instanceof TParallelGateway) {
			return GatewayType.AND;
		} else if (flowNode instanceof TInclusiveGateway) {
			return GatewayType.INCLUSIVE;
		} else if (flowNode instanceof TExclusiveGateway) {
			return GatewayType.XOR;
		} else {
			return GatewayType.XOR;
		}
	}

	private TaskType getActivityType(TActivity flowNode) {

		if (flowNode instanceof TSubProcess) {
			if (!((TSubProcess) flowNode).isTriggeredByEvent()) {
				return TaskType.CALL_ACTIVITY;
			}
		} else if (flowNode instanceof TUserTask
				|| flowNode instanceof TManualTask) {
			// we are using the same type task for both
			return TaskType.HUMAN;
		} else if (flowNode instanceof TReceiveTask) {
			return TaskType.RECIEVE_TASK;
		} else if (flowNode instanceof TSendTask) {
			return TaskType.SEND_TASK;
		} else if (flowNode instanceof TScriptTask) {
			return TaskType.SCRIPT;
		} else if (flowNode instanceof TServiceTask) {
			return TaskType.SERVICE;
		} else if (flowNode instanceof TCallActivity) {
			return TaskType.CALL_ACTIVITY;
		} else if (flowNode instanceof TTask) {
			// abstract task
			return TaskType.ABSTRACT;
		} else if (!(flowNode instanceof TTransaction)) {
			// default case
			return TaskType.ABSTRACT;
		}
		return TaskType.SERVICE;
	}

	private EventType getEventType(TEvent flowNode) {

		if (flowNode instanceof TCatchEvent) {
			if (flowNode instanceof TStartEvent) {
				return getStartEventTypeFor(flowNode);
			}
			if (flowNode instanceof TBoundaryEvent) {
				return getBoundaryEventType(flowNode);
			} else {
				if (!((TCatchEvent) flowNode).getEventDefinition().isEmpty()) {
					for (TEventDefinition e : ((TCatchEvent) flowNode)
							.getEventDefinition()) {
						EventType res = getCorrespondingIntermediateEventType(e);
						if(res != null){
							return res;
						}
					}
				} else {
					EList<QName> eventDefinitionRef = ((TCatchEvent) flowNode).getEventDefinitionRef();
					if(!eventDefinitionRef.isEmpty()){
						for (QName qNameEventDefRef : eventDefinitionRef) {
							TEventDefinition eventDefinition = findCorrespondingEventDefinition(qNameEventDefRef);
							EventType res = getCorrespondingIntermediateEventType(eventDefinition);
							if(res != null){
								return res;
							}
						}
					}
				}
			}
		}
		if (flowNode instanceof TThrowEvent) {
			if (flowNode instanceof TEndEvent) {
				return getEndEventTypeFor(flowNode);
			}
			if (flowNode instanceof TThrowEvent) {
				if (!((TThrowEvent) flowNode).getEventDefinition().isEmpty()) {
					for (TEventDefinition e : ((TThrowEvent) flowNode)
							.getEventDefinition()) {
						if (e instanceof TMessageEventDefinition) {
							return EventType.INTERMEDIATE_THROW_MESSAGE;
						} else if (e instanceof TLinkEventDefinition) {
							return EventType.INTERMEDIATE_THROW_LINK;
						} else if (e instanceof TSignalEventDefinition) {
							return EventType.INTERMEDIATE_THROW_SIGNAL;
						}
					}
				}
			}
		}
		return null;
	}

	private EventType getBoundaryEventType(TEvent flowNode) {
		if (!((TBoundaryEvent) flowNode).getEventDefinition().isEmpty()) {
			for (TEventDefinition e : ((TBoundaryEvent) flowNode)
					.getEventDefinition()) {
				if (e instanceof TMessageEventDefinition) {
					return EventType.MESSAGE_BOUNDARY;
				} else if (e instanceof TTimerEventDefinition && ((TBoundaryEvent) flowNode).isCancelActivity()) {
					return EventType.TIMER_BOUNDARY;
				}else if (e instanceof TTimerEventDefinition && !((TBoundaryEvent) flowNode).isCancelActivity()) {
					return EventType.NON_INTERRUPTING_TIMER_BOUNDARY;
				} else if (e instanceof TSignalEventDefinition) {
					return EventType.SIGNAL_BOUNDARY;
				} else if (e instanceof TErrorEventDefinition) {
					return EventType.ERROR_BOUNDARY;
				}
			}
		}
		return null;
	}

	private EventType getEndEventTypeFor(TEvent flowNode) {
		if (!((TEndEvent) flowNode).getEventDefinition().isEmpty()) {
			for (TEventDefinition e : ((TEndEvent) flowNode)
					.getEventDefinition()) {
				if (e instanceof TMessageEventDefinition) {
					return EventType.END_MESSAGE;
				} else if (e instanceof TSignalEventDefinition) {
					return EventType.END_SIGNAL;
				} else if (e instanceof TErrorEventDefinition) {
					return EventType.END_ERROR;
				} else if (e instanceof TTerminateEventDefinition) {
					return EventType.END_TERMINATED;
				}
			}
		}
		return EventType.END;
	}

	private EventType getStartEventTypeFor(TEvent flowNode) {
		if (!((TStartEvent) flowNode).getEventDefinition().isEmpty()) {
			for (TEventDefinition e : ((TStartEvent) flowNode)
					.getEventDefinition()) {
				EventType res = getStartEventTypeFor(e);
				if(res !=null){
					return res;
				}
			}
		}
		return EventType.START;
	}

	private EventType getStartEventTypeFor(TEventDefinition e) {
		if (e instanceof TMessageEventDefinition) {
			return EventType.START_MESSAGE;
		} else if (e instanceof TTimerEventDefinition) {
			return EventType.START_TIMER;
		} else if (e instanceof TSignalEventDefinition) {
			return EventType.START_SIGNAL;
		} else if (e instanceof TErrorEventDefinition) {
			return EventType.START_ERROR;
		} else {
			return null;
		}
	}

	private TEventDefinition findCorrespondingEventDefinition(QName qNameEventDefRef) {
		return idOfEventDefinitions.get(qNameEventDefRef.getLocalPart());
	}

	private EventType getCorrespondingIntermediateEventType(TEventDefinition e) {
		if (e instanceof TMessageEventDefinition) {
			return EventType.INTERMEDIATE_CATCH_MESSAGE;
		} else if (e instanceof TTimerEventDefinition) {
			return EventType.INTERMEDIATE_CATCH_TIMER;
		} else if (e instanceof TLinkEventDefinition) {
			return EventType.INTERMEDIATE_CATCH_LINK;
		} else if (e instanceof TSignalEventDefinition) {
			return EventType.INTERMEDIATE_CATCH_SIGNAL;
		} else {
			return null;
		}
	}

	/**
	 * @param flowNode
	 * @param containerPart
	 * @param activityNumber
	 * @param fromSubProcess
	 * @return
	 */
	private Point toLocation(TFlowNode flowNode, int activityNumber,
			boolean fromSubProcess) {
		Point location = new Point();

		if (useBpmnDiagram) {
			Point loc = getLocationFor(flowNode.getId());

			if (fromSubProcess) {
				if (subProc != null && subProc.isIsExpanded()) {
					for (BPMNPlane bpmnPlane : bpmnProcessDiagrams) {
						for (DiagramElement de : bpmnPlane.getDiagramElement()) {
							final String subProcId = subProc.getId();
							if (subProcId.equals(de.getId())) {
								/*
								 * do it only if the subproc is expanded and the
								 * bpmnshape defined in the same bpmnplane than
								 * the current element
								 */
								final Bounds bounds = subProc.getBounds();
								Point parentLoc = new Point((int)bounds.getX(),	(int)bounds.getY());
								loc.translate(parentLoc.getNegated())
								.translate(30, 0);
								break;
							}
						}
					}
				}
			}
			if (loc != null) {
				location.x = Math.max(25, loc.x + 25);
				// if(loc.x < 25) {
				// location.x = Math.max(25, loc.x) + 25;
				// } else {
				// location.x = Math.max(25, loc.x + 25) ;
				// }

				location.y = Math.max(0, loc.y);
				// if(loc.y < 25 ) {
				// location.y = Math.max(0, loc.y) + 25;
				// } else {
				// location.y = Math.max(0, loc.y) ;
				// }
			}
		} else {
			//perhaps, there is no Pool defined and elements are defined directly on the canvas
			Point loc = getLocationFor(flowNode.getId());
			if(loc == null){
				location.x = 100 + 150 * (activityNumber % 7);
				location.y = 100 * (1 + (activityNumber / 7));
			} else {
				location.x = Math.max(25, loc.x + 25);
				location.y = Math.max(0, loc.y);
			}
		}

		return location;
	}

	/**
	 * @param id
	 *            : the id of the BPMN Element (not the graphical one)
	 * @return
	 */
	private Point getLocationFor(String id) {
		if(!bpmnProcessDiagrams.isEmpty()){
			/* need to have location relatively to the container */
			for (BPMNPlane processDiagram : bpmnProcessDiagrams) {
				Point res = null;
				res = getLocationForInPlane(id, processDiagram);
				if(res != null){
					return res;
				}
			}
		} else {
			//in case we don't know in which diagram we are
			for (BPMNDiagram processDiagram : definitions.getBPMNDiagram()){
				Point res = null;
				res = getLocationForInPlane(id, processDiagram.getBPMNPlane());
				if(res != null){
					return res;
				}
			}
		}
		return null;
	}

	protected Point getLocationForInPlane(String id, BPMNPlane processDiagram) {
		for (DiagramElement diagramElement : processDiagram
				.getDiagramElement()) {
			if (diagramElement instanceof BPMNShape) {
				final BPMNShape bpmnShape = (BPMNShape) diagramElement;
				if (bpmnShape.getBpmnElement().getLocalPart().equals(id)) {
					Point containerLocation = getContainerLocationFor(id);
					final Bounds bounds = ((Shape) diagramElement)
							.getBounds();
					// 25 is for the margin
					final double x = bounds.getX() - containerLocation.x
							+ 35;
					final double y = bounds.getY() - containerLocation.y
							+ 35;
					return new Point((int)Math.max(x, 0), (int)Math.max(0, y));
				}
			}
		}
		return null;
	}

	protected Point getContainerLocationFor(String id) {
		/* Retrieve container */
		String containerId = findContainerId(id);
		/* if we don't find a container, perhaps it is because it is a TArtifact */
		if (containerId == null) {
			/* so search with a different algo... thanks BPMN2!!! */
			for (TRootElement rootElement : rootElements) {
				/* Search in all Processes ... */
				if (rootElement instanceof TProcess) {
					for (TArtifact artifact : ((TProcess) rootElement)
							.getArtifact()) {
						if (id.equals(artifact.getId())) {
							// need to find the top container that has a
							// BMPNShape associated with, again thanks BPMN2!!!
							Point containerLocationOfTArtifact = computeContainerLocationOfTArtifact(rootElement);
							if (containerLocationOfTArtifact != null) {
								return containerLocationOfTArtifact.getCopy()
										.translate(-25, -25);
							}
							// break;
						}
					}
				}
			}
		}

		// it wasn't a TArtifact nor a flownode referenced in a Lane,
		// perhaps it is a flownode that is inside a subprocess that is inside a
		// lane and this lane doesn't reference correctly the flowNodeRefs
		if (containerId == null && subprocEvent != null
				&& !isInSubProcEventContainerSearch) {
			isInSubProcEventContainerSearch = true;
			final Point containerLocationFor = getContainerLocationFor(subprocEvent
					.getId());
			isInSubProcEventContainerSearch = false;
			return containerLocationFor;
		}

		Point containerLocation = new Point();
		if (containerId != null) {
			final Point initialContainerLocation = getLocationFor(containerId);
			if(initialContainerLocation != null){
				containerLocation = initialContainerLocation.getCopy()
						.translate(-25, -25);
			}else {
				//Maybe, the BPMNShape is the one relative to the Participant
				String participantContainerID = getParticipantWithProcessRef(containerId);
				if(participantContainerID != null){
					final Point initialParticipantContainerLocation = getLocationFor(participantContainerID);
					if(initialParticipantContainerLocation != null){
						containerLocation = initialParticipantContainerLocation.getCopy()
								.translate(-25, -25);
					}
				}
			}
		}
		return containerLocation;
	}

	private Point computeContainerLocationOfTArtifact(TRootElement rootElement) {
		Point containerLocationOfTArtifact = null;
		/* ... in all LaneSets... */
		for (TLaneSet laneSet : ((TProcess) rootElement)
				.getLaneSet()) {
			/* ... in all Lanes ... */
			for (TLane lane : laneSet.getLane()) {
				Point tempContainerLocationOfTArtifact = getLocationFor(lane
						.getId());
				if (containerLocationOfTArtifact != null) {
					containerLocationOfTArtifact.x = Math
							.min(containerLocationOfTArtifact.x,
									tempContainerLocationOfTArtifact.x);
					containerLocationOfTArtifact.y = Math
							.min(containerLocationOfTArtifact.y,
									tempContainerLocationOfTArtifact.y);
				} else {
					containerLocationOfTArtifact = new Point();
					containerLocationOfTArtifact.x = tempContainerLocationOfTArtifact.x;
					containerLocationOfTArtifact.y = tempContainerLocationOfTArtifact.y;
				}
			}
		}
		return containerLocationOfTArtifact;
	}

	private String findContainerId(String id) {
		String containerId = null;
		Iterator<TRootElement> rootElementsIterator = rootElements.iterator();
		while (containerId == null && rootElementsIterator.hasNext()) {
			TRootElement rootElement = rootElementsIterator.next();
			/* Search in all Processes ... */
			if (rootElement instanceof TProcess) {
				/* ... in all LaneSets... */
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
		return containerId;
	}

	protected String findContainerIdOf(String id, TLaneSet laneSet) {
		/* ... in all Lanes ... */
		for (TLane lane : laneSet.getLane()) {
			/* ... to find if it is contained in it, ie if it is in FlowNodeRef */
			for (String flowNodeRef : lane.getFlowNodeRef()) {
				if (flowNodeRef.equals(id)) {
					return lane.getId();
				}
			}
			if (lane.getChildLaneSet() != null) {
				String res = findContainerIdOf(id, lane.getChildLaneSet());
				if (res != null) {
					return res;
				}
			}
		}
		return null;
	}

	/**
	 * Return size of pool : if there is a correspondent BPMNShape related to
	 * the collaboration element use it //for now this case doesn't work
	 * otherwise if there are lanesets, make sum of them for height and take the
	 * max of them for width otherwise take the max of x and y of element inside
	 * 
	 * @param id
	 * @return
	 */
	private Dimension getSizeForPool(String id) {
		int height = 0;
		int width = 0;
		int maxX = 0;
		int maxY = 0;

		Set<String> laneIds = new HashSet<String>();
		Set<String> flowElementIds = new HashSet<String>();
		Set<String> artifactIds = new HashSet<String>();
		String bpmnShapeId = null;

		/* Find the process definition */
		Iterator<TRootElement> rootElementsIterator = rootElements.iterator();
		while (bpmnShapeId == null && rootElementsIterator.hasNext()) {
			TRootElement rootElement = rootElementsIterator.next();
			if (rootElement instanceof TCollaboration) {
				for (TParticipant participant : ((TCollaboration) rootElement)
						.getParticipant()) {
					final QName processRef = participant.getProcessRef();
					if (processRef != null
							&& processRef.getLocalPart().equals(id)) {
						bpmnShapeId = participant.getId();
						break;
					}
				}
			}
			if (rootElement.getId() != null && rootElement.getId().equals(id)) {
				if (rootElement instanceof TProcess) {
					/* Register all lanes id contained in this process */
					for (TLaneSet laneSet : ((TProcess) rootElement)
							.getLaneSet()) {
						laneIds.addAll(findAllLanesIdInside(laneSet));
					}
					/*
					 * Register also the max x and y in case there are no
					 * laneSet
					 */
					if (laneIds.isEmpty()) {
						for (TFlowElement flowElement : ((TProcess) rootElement)
								.getFlowElement()) {
							flowElementIds.add(flowElement.getId());
						}
						for (TArtifact tArtifact : ((TProcess) rootElement)
								.getArtifact()) {
							artifactIds.add(tArtifact.getId());
						}
					}
					break;
				}
			}
		}
		
		

		bpmnShapeId = getParticipantWithProcessRef(id);
		if(getLocationFor(bpmnShapeId)== null){
			bpmnShapeId = null;
		}
		for (BPMNPlane processDiagram : bpmnProcessDiagrams) {
			for (DiagramElement diagramElement : processDiagram
					.getDiagramElement()) {
				if (diagramElement instanceof BPMNShape) {
					final String localPartId = ((BPMNShape) diagramElement)
							.getBpmnElement().getLocalPart();
					if (id.equals(localPartId)) {
						final Bounds bounds = ((BPMNShape) diagramElement)
								.getBounds();
						height = (int) bounds.getHeight();
						width = (int) bounds.getWidth();
						break;
					} else if (bpmnShapeId != null) {
						if (bpmnShapeId.equals(localPartId)) {
							final Bounds bounds = ((BPMNShape) diagramElement)
									.getBounds();
							height = (int) bounds.getHeight();
							width = (int) bounds.getWidth();
							break;
						}
					} else if (laneIds.contains(localPartId)) {
						final Bounds bounds = ((BPMNShape) diagramElement)
								.getBounds();
						height += bounds.getHeight();
						width = Math.max(width, (int) bounds.getWidth());
					} else if (flowElementIds.contains(localPartId)
							|| artifactIds.contains(localPartId)) {
						final Bounds bounds = ((BPMNShape) diagramElement)
								.getBounds();
						maxX = Math.max(maxX,
								(int) (bounds.getX() + bounds.getWidth()));
						maxY = Math.max(maxY,
								(int) (bounds.getY() + bounds.getHeight()));
					}
				}
			}
		}
		/*
		 * If we don't find size of pool by additioning lanes (because there is
		 * no lane, we use search the extremum to be sure that we don't hide a
		 * part of the process
		 */
		if (height != 0 && width != 0) {
			return new Dimension(width + 65, height + 65);
		}
		if (maxX != 0 && maxY != 0) {
			return new Dimension(maxX + 65, maxY + 65);
		}
		return null;
	}

	private Collection<? extends String> findAllLanesIdInside(TLaneSet laneSet) {
		List<String> laneIds = new ArrayList<String>();
		for (TLane lane : laneSet.getLane()) {
			laneIds.add(lane.getId());
			final TLaneSet childLaneSet = lane.getChildLaneSet();
			if (childLaneSet != null) {
				laneIds.addAll(findAllLanesIdInside(childLaneSet));
			}
		}
		return laneIds;
	}

	/**
	 * @param id
	 * @return the BPMNEdge corresponding to the bpmn element with id
	 */
	private BPMNEdge getBPMNEdgeFor(String id) {
		for (BPMNPlane processDiagram : bpmnProcessDiagrams) {
			for (DiagramElement diagramElement : processDiagram
					.getDiagramElement()) {
				if (diagramElement instanceof BPMNEdge) {
					if (((BPMNEdge) diagramElement).getBpmnElement()
							.getLocalPart().equals(id)) {
						return (BPMNEdge) diagramElement;
					}
				}
			}
		}
		return null;
	}

	/**
	 * @param id
	 *            : the id of the BPMN element
	 * @return
	 */
	private Dimension getSizeFor(String id) {
		int height = 0;
		int width = 0;
		BPMNShape bpmnShape = getBPMNShapeForBpmnID(id);

		if (bpmnShape != null) {
			final Bounds bounds = bpmnShape.getBounds();
			return new Dimension((int) bounds.getWidth(),
					(int) bounds.getHeight());
		}
		if (height == 0 && width == 0) {
			return new Dimension(-1, -1);
		}

		return new Dimension(width, height);
	}

	protected BPMNShape getBPMNShapeForBpmnID(String id) {
		for (BPMNPlane processDiagram : bpmnProcessDiagrams) {
			for (DiagramElement diagramElement : processDiagram
					.getDiagramElement()) {
				if (diagramElement instanceof BPMNShape) {
					if (((BPMNShape) diagramElement).getBpmnElement()
							.getLocalPart().equals(id)) {
						return (BPMNShape) diagramElement;
					}
				}
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bonitasoft.studio.importer.ToProcProcessor#getExtension()
	 */
	@Override
	public String getExtension() {
		return "bpmn"; //$NON-NLS-1$
	}

	/**
	 * @return the errorElements
	 */
	@Override
	public List<Object> getErrors() {
		return errorElements;
	}

	@Override
	public List<File> getResources() {
		return Collections.singletonList(result);
	}
}
