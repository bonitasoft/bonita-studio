///**
// * Copyright (C) 2009 BonitaSoft S.A.
// * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
// *
// * This program is free software: you can redistribute it and/or modify
// * it under the terms of the GNU General Public License as published by
// * the Free Software Foundation, either version 2.0 of the License, or
// * (at your option) any later version.
// *
// * This program is distributed in the hope that it will be useful,
// * but WITHOUT ANY WARRANTY; without even the implied warranty of
// * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// * GNU General Public License for more details.
// *
// * You should have received a copy of the GNU General Public License
// * along with this program.  If not, see <http://www.gnu.org/licenses/>.
// */
//package org.bonitasoft.studio.engine.export;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
//import org.bonitasoft.engine.bpm.model.DesignProcessDefinition;
//import org.bonitasoft.engine.bpm.model.ProcessDefinition;
//import org.bonitasoft.engine.bpm.model.ProcessDefinitionBuilder;
//import org.bonitasoft.studio.common.NamingUtils;
//import org.bonitasoft.studio.common.TimerUtil;
//import org.bonitasoft.studio.common.WriteType;
//import org.bonitasoft.studio.common.emf.tools.ModelHelper;
//import org.bonitasoft.studio.common.extension.BonitaStudioExtensionRegistryManager;
//import org.bonitasoft.studio.common.log.BonitaStudioLog;
//import org.bonitasoft.studio.decision.core.DecisionTableUtil;
//import org.bonitasoft.studio.engine.Messages;
//import org.bonitasoft.studio.engine.contribution.IKPITypeResolverContribution;
//import org.bonitasoft.studio.model.kpi.AbstractKPIBinding;
//import org.bonitasoft.studio.model.kpi.DatabaseKPIBinding;
//import org.bonitasoft.studio.model.kpi.KPIField;
//import org.bonitasoft.studio.model.kpi.KPIParameterMapping;
//import org.bonitasoft.studio.model.process.ANDGateway;
//import org.bonitasoft.studio.model.process.AbstractCatchMessageEvent;
//import org.bonitasoft.studio.model.process.AbstractProcess;
//import org.bonitasoft.studio.model.process.Activity;
//import org.bonitasoft.studio.model.process.ActorType;
//import org.bonitasoft.studio.model.process.Assignable;
//import org.bonitasoft.studio.model.process.AttachmentData;
//import org.bonitasoft.studio.model.process.AttachmentType;
//import org.bonitasoft.studio.model.process.BooleanType;
//import org.bonitasoft.studio.model.process.BoundaryEvent;
//import org.bonitasoft.studio.model.process.BoundaryMessageEvent;
//import org.bonitasoft.studio.model.process.BoundarySignalEvent;
//import org.bonitasoft.studio.model.process.BoundaryTimerEvent;
//import org.bonitasoft.studio.model.process.CatchLinkEvent;
//import org.bonitasoft.studio.model.process.CatchMessageEvent;
//import org.bonitasoft.studio.model.process.CatchSignalEvent;
//import org.bonitasoft.studio.model.process.ConnectableElement;
//import org.bonitasoft.studio.model.process.Connection;
//import org.bonitasoft.studio.model.process.Connector;
//import org.bonitasoft.studio.model.process.Container;
//import org.bonitasoft.studio.model.process.Data;
//import org.bonitasoft.studio.model.process.DataType;
//import org.bonitasoft.studio.model.process.DateType;
//import org.bonitasoft.studio.model.process.Deadline;
//import org.bonitasoft.studio.model.process.Element;
//import org.bonitasoft.studio.model.process.EndErrorEvent;
//import org.bonitasoft.studio.model.process.EndTerminatedEvent;
//import org.bonitasoft.studio.model.process.EnumType;
//import org.bonitasoft.studio.model.process.EventObject;
//import org.bonitasoft.studio.model.process.FloatType;
//import org.bonitasoft.studio.model.process.FlowElement;
//import org.bonitasoft.studio.model.process.Gateway;
//import org.bonitasoft.studio.model.process.Group;
//import org.bonitasoft.studio.model.process.InclusiveGateway;
//import org.bonitasoft.studio.model.process.InputMapping;
//import org.bonitasoft.studio.model.process.IntegerType;
//import org.bonitasoft.studio.model.process.IntermediateErrorCatchEvent;
//import org.bonitasoft.studio.model.process.JavaObjectData;
//import org.bonitasoft.studio.model.process.JavaType;
//import org.bonitasoft.studio.model.process.Lane;
//import org.bonitasoft.studio.model.process.LinkEvent;
//import org.bonitasoft.studio.model.process.MainProcess;
//import org.bonitasoft.studio.model.process.MultiInstantiation;
//import org.bonitasoft.studio.model.process.OutputMapping;
//import org.bonitasoft.studio.model.process.OutputParameterMapping;
//import org.bonitasoft.studio.model.process.Parameter;
//import org.bonitasoft.studio.model.process.ReceiveTask;
//import org.bonitasoft.studio.model.process.SendTask;
//import org.bonitasoft.studio.model.process.SequenceFlow;
//import org.bonitasoft.studio.model.process.SequenceFlowConditionType;
//import org.bonitasoft.studio.model.process.SignalEvent;
//import org.bonitasoft.studio.model.process.SourceElement;
//import org.bonitasoft.studio.model.process.StartErrorEvent;
//import org.bonitasoft.studio.model.process.StartTimerEvent;
//import org.bonitasoft.studio.model.process.StringType;
//import org.bonitasoft.studio.model.process.SubProcess;
//import org.bonitasoft.studio.model.process.SubProcessEvent;
//import org.bonitasoft.studio.model.process.TargetElement;
//import org.bonitasoft.studio.model.process.Task;
//import org.bonitasoft.studio.model.process.ThrowLinkEvent;
//import org.bonitasoft.studio.model.process.ThrowMessageEvent;
//import org.bonitasoft.studio.model.process.TimerEvent;
//import org.bonitasoft.studio.model.process.XMLData;
//import org.bonitasoft.studio.model.process.XMLType;
//import org.bonitasoft.studio.model.process.XORGateway;
//import org.bonitasoft.studio.repository.connectors.configuration.ConfigurationRepository;
//import org.bonitasoft.studio.repository.connectors.connector.ConnectorRepository;
//import org.eclipse.core.runtime.CoreException;
//import org.eclipse.core.runtime.IConfigurationElement;
//import org.eclipse.emf.common.util.EList;
//import org.eclipse.emf.ecore.EObject;
//
//
///**
// * @author Mickael Istria
// * @author Aurelien Pupier
// * 				- add exception transition
// *
// */
//public class BonitaExporter {
//
//	private static final String DB_REQUEST_PARAMETER = "setQuery"; //$NON-NLS-1$
//	private static final String NOW_DATE_GROOVY_EXPRESSION = "${new java.util.Date()}"; //$NON-NLS-1$
//	private static final String GROOVY_PREFIX = "${"; //$NON-NLS-1$
//	private static final String GROOVY_SUFFIX = "}"; //$NON-NLS-1$
//
//	private Set<String> definedAuthorities;
//	private Set<Data> definedData;
//	private Set<LinkEvent> definedLink;
//	private IKPITypeResolverContribution kpiTypeResolverContrib;
//	private Set<EObject> eObjectNotExported; 
//
//	public BonitaExporter() {
//		definedAuthorities = new HashSet<String>();
//		definedData = new HashSet<Data>();
//		definedLink = new HashSet<LinkEvent>();
//		eObjectNotExported = new HashSet<EObject>() ;
//	}
//
//	public BonitaExporter(Set<EObject> eObjectNotExported) {
//		this() ;
//		this.eObjectNotExported = eObjectNotExported ;
//	}
//
//	/**
//	 * 	can be called on a MainProcess
//	 * @param studioProcess
//	 * @return
//	 * @throws BonitaExportException
//	 */
//	public List<DesignProcessDefinition> createProcessDefinition(AbstractProcess studioProcess) throws BonitaExportException {
//		final List<DesignProcessDefinition> res = new ArrayList<DesignProcessDefinition>();
//
//		final DesignProcessDefinition def = createDefinition(studioProcess);
//		if(null != def){
//			res.add(def);
//			reset();
//		}
//
//
//		if (studioProcess instanceof MainProcess) {
//			for (Element item : studioProcess.getElements()) {
//				if (item instanceof AbstractProcess) {
//					res.addAll(createProcessDefinition((AbstractProcess)item));	
//				}
//			}
//		}
//
//		return res;
//	}
//
//	private void reset() {
//		definedAuthorities.clear();
//		definedData.clear() ;
//		definedLink.clear() ;
//	}
//
//	/**
//	 *  Must be called on a single process
//	 * @param process
//	 * @return
//	 * @throws BonitaExportException
//	 */
//	public DesignProcessDefinition createDefinition(AbstractProcess process) throws BonitaExportException {
//		if (hasSteps(process)) {
//			final ProcessDefinitionBuilder builder = new ProcessDefinitionBuilder().createNewInstance(process.getName(), process.getVersion()) ;
////			if(process instanceof SubProcessEvent){
////				builder.setEventSubProcess();
////			}
//
//			addDesc(builder, process);
////			for (String category : process.getCategories()) {
////				builder.addCategory(category);
////			}
//			processConnectors(process, builder, false);
//			processKPIs(process, builder);
//			addData(process, builder);
//			addElementsOfContainer(process, builder);
//			processLanesSteps(process, builder);
//
//
//			addTransitions(process, builder);
//
//			return builder.done();
//		}
//		return null;
//	}
//
//
//	/**
//	 * @param container
//	 * @return
//	 */
//	private boolean hasSteps(Container container) {
//		boolean hasSteps = false;
//		final Iterator<Element> elementIt = container.getElements().iterator();
//		Element item;
//		while (!hasSteps && elementIt.hasNext()) {
//			item = elementIt.next();
//			if (item instanceof FlowElement) {
//				hasSteps = true;
//			} else if (item instanceof Lane) {
//				hasSteps = hasSteps((Lane)item);
//			}
//		}
//		return hasSteps;
//	}
//
//	/**
//	 * @param studioProcess
//	 * @param builder
//	 * @throws BonitaExportException 
//	 */
//	private void processLanesSteps(Container container, ProcessDefinitionBuilder builder) throws BonitaExportException {
//		for (Element item : container.getElements()) {
//			if (item instanceof Lane) {
//				addElementsOfContainer((Lane)item, builder);
//				processLanesSteps((Lane)item, builder);
//			}
//		}
//	}
//
//	/**
//	 * @param studioProcess
//	 * @param builder
//	 * @throws BonitaExportException 
//	 */
//	private void addTransitions(AbstractProcess process, ProcessDefinitionBuilder builder) throws BonitaExportException {
//		for (Element item : process.getConnections()) {
//			if (item instanceof SequenceFlow) {
//				SequenceFlow transition = (SequenceFlow)item;
//				SourceElement source = transition.getSource();
//				TargetElement target = transition.getTarget();
//				boolean invalidTransition = false ;
//				if(source == null || target == null){
//					invalidTransition = true ;
//				}
//				if(!invalidTransition){
//					if(target instanceof ThrowLinkEvent || source instanceof CatchLinkEvent){/*link with catch or throw link event*/
//						addLinkEvents(builder, transition);
//					} else {
//						if(ModelHelper.getParentProcessIncludedEmbedded(source).equals(process) && ModelHelper.getParentProcessIncludedEmbedded(target).equals(process)){
//							String sourceName = source.getName();
//							String targetName = target.getName();
//							String id = null ;
//							if(source.getLabel().trim().length() == 0 &&
//									target.getLabel().trim().length() == 0	){
//								id = NamingUtils.toJavaIdentifier(ModelHelper.getEObjectID(source) + "_" + ModelHelper.getEObjectID(target),false) ; ;
//							}
//							if(source instanceof BoundaryEvent){/*BoundaryEvent*/
//								if(transition.getSource() instanceof BoundaryMessageEvent){
//									builder.addExceptionTransition(((Element)transition.getSource().eContainer()).getName(),((BoundaryMessageEvent)transition.getSource()).getEvent(),targetName);
//								}else{
//									builder.addExceptionTransition(((Element) source.eContainer()).getName(), sourceName, targetName);			
//								}
//							} else {/*in all other cases*/
//								if(id == null){
//									builder.addTransition(sourceName, targetName);
//								}else{
//									builder.addTransition(id,sourceName,targetName);
//								}
//								if(transition.isIsDefault()){
//									builder.setDefault();
//								} else {
//									String condition = transition.getCondition();
//									if (transition.getConditionType() == SequenceFlowConditionType.GROOVY_EXPRESSION &&
//											condition != null && condition.length() > 0) {
//										builder.addCondition(condition);
//									} else if (transition.getConditionType() == SequenceFlowConditionType.DECISION_TABLE) {
//										builder.addCondition(DecisionTableUtil.toGroovyScript(transition.getDecisionTable()));
//									}
//								}
//								addDesc(builder, transition);
//							}
//						}
//					}
//				}
//			}
//		}
//	}
//
//	/**
//	 * @param builder
//	 * @param item
//	 */
//	private void addDesc(ProcessDefinitionBuilder builder, Element item) {
//		builder.addLabel(item.getLabel());
//		builder.addDescription(item.getDocumentation());
//		if(item instanceof FlowElement){
//			final FlowElement fe = (FlowElement) item;
//			final String dynamicLabel = fe.getDynamicLabel();
//			if(null != dynamicLabel && dynamicLabel.length() > 0){
//				builder.addDynamicLabel(dynamicLabel);
//			}
//			final String dynamicDescription = fe.getDynamicDescription();
//			if(null != dynamicDescription && dynamicDescription.length() > 0){
//				builder.addDynamicDescription(dynamicDescription);
//			}
//			final String stepSummary = fe.getStepSummary();
//			if(null != stepSummary && stepSummary.length() > 0){
//				builder.addDynamicExecutionSummary(stepSummary);
//			}
//		}
//	}
//
//	/**
//	 * Add Elements of the container except LinkEvent
//	 * @param studioProcess
//	 * @param builder
//	 */
//	private void addElementsOfContainer(Container container, ProcessDefinitionBuilder builder) throws BonitaExportException {
//		for (Element item : container.getElements()) {
//			if(!(item instanceof LinkEvent)){
//				if (item instanceof Activity
//						&& !(item instanceof ReceiveTask)//will be treat as CatchMessageEvent
//						&& !(item instanceof SendTask)) {//will be treat as ThrowMessageEvent
//					addActivity((Activity)item, builder);
//				}else if(item instanceof SubProcessEvent){
//					builder.addEventSubProcess(item.getName(), ((SubProcessEvent)item).getVersion());		
//				} else if(item instanceof org.bonitasoft.studio.model.process.Event){
//					org.bonitasoft.studio.model.process.Event event = (org.bonitasoft.studio.model.process.Event)item;
//					addEvent(builder, event);
//				}else if (item instanceof Gateway){
//					addGateway(builder, (Gateway)item);
//				}
//				if(item instanceof FlowElement){
//					if(!((FlowElement) item).getSynchronous())
//						builder.asynchronous();
//				}
//			}
//		}
//	}
//
//	/**
//	 * Add event
//	 * @param builder
//	 * @param event
//	 * @throws BonitaExportException
//	 */
//	protected void addEvent(ProcessDefinitionBuilder builder,
//			org.bonitasoft.studio.model.process.Event event)
//	throws BonitaExportException {
//		if(event instanceof EndTerminatedEvent){
//			builder.addTerminateEndEvent(event.getName());
//			addDesc(builder, event);
//			builder.addJoinType(JoinType.XOR);
//		} else if(event instanceof SignalEvent){
//			SignalEvent signalEvent = (SignalEvent) event;
//			builder.addSignalEventTask(event.getName(), signalEvent.getSignalCode(),event instanceof CatchSignalEvent);
//			addDesc(builder, event);
//			builder.addJoinType(JoinType.XOR) ;
//		} else if(event instanceof CatchMessageEvent){ 
//			addIncomingEvents(builder,event);
//		} else if(event instanceof ThrowMessageEvent){ 
//			addOutgoingEvents(builder,event);
//		} else if(event instanceof TimerEvent){
//			String condition;
//			TimerEvent timerEvent = (TimerEvent)event;
//			if(event instanceof StartTimerEvent){
//				condition = TimerUtil.getScript((StartTimerEvent)timerEvent);
//			}else{
//				condition = timerEvent.getCondition();
//			}
//			if(condition != null && condition.length()>=0){
//				builder.addTimerTask(event.getName(), condition);
//			}else{
//				throw new BonitaExportException(Messages.bind(Messages.errorMissingTimerCondition,event.getLabel())); 
//			}
//			addDesc(builder, event);
//			builder.addJoinType(JoinType.XOR);
//			//processConnectors(timerEvent, builder, Event.onTimer); NOT SUPPORTED
//		} else if(event instanceof EndErrorEvent){
//			EndErrorEvent endErrorEvent = (EndErrorEvent)event;
//			builder.addErrorEventTask(endErrorEvent.getName(), endErrorEvent.getErrorCode());
//			addDesc(builder, event);
//			builder.addJoinType(JoinType.XOR);
//		} else if(event instanceof StartErrorEvent){
//			StartErrorEvent startErrorEvent = (StartErrorEvent)event;
//			builder.addErrorEventTask(startErrorEvent.getName(), startErrorEvent.getErrorCode());
//			addDesc(builder, event);
//			builder.addJoinType(JoinType.XOR);
//		} else  {//All others events
//			builder.addSystemTask(event.getName());
//			addDesc(builder, event);
//			builder.addJoinType(JoinType.XOR);
//		}
//	}
//
//	protected void addGateway(ProcessDefinitionBuilder builder, Gateway gateway) {
//		builder.addDecisionNode(gateway.getName());
//		addDesc(builder, gateway);
//		if(gateway instanceof ANDGateway) {						
//			builder.addJoinType(JoinType.AND);
//			builder.addSplitType(SplitType.AND);
//		} else if (gateway instanceof XORGateway){
//			builder.addJoinType(JoinType.XOR);
//			builder.addSplitType(SplitType.XOR);
//		} else if (gateway instanceof InclusiveGateway){
//			builder.addJoinType(JoinType.XOR);
//			builder.addSplitType(SplitType.AND);
//		}
//	}
//
//	private void addLinkEvents(ProcessDefinitionBuilder builder, Element item) throws BonitaExportException {
//		if(item instanceof SequenceFlow){
//			final SequenceFlow transition = (SequenceFlow) item ;
//
//			if(transition.getTarget() instanceof ThrowLinkEvent){
//				final ThrowLinkEvent throwLink = (ThrowLinkEvent) transition.getTarget() ;
//				if(null != throwLink.getTo()){
//					final CatchLinkEvent target = throwLink.getTo() ; 
//					for(Connection c : target.getOutgoing()){
//						if(transition.getSource() instanceof BoundaryEvent){
//							builder.addExceptionTransition(((Element)transition.getSource().eContainer()).getName(),transition.getSource().getName(),c.getTarget().getName());
//						}else{
//							builder.addTransition(transition.getSource().getName(),c.getTarget().getName());
//						}
//
//						if(transition.isIsDefault()) {
//							builder.setDefault();
//						}else{
//							String transitionCondition = transition.getCondition();
//							if (null != transitionCondition && transitionCondition.length() > 0) {
//								builder.addCondition(transitionCondition);
//							}
//						}
//						addDesc(builder, item);
//					}
//				}else{
//					throw new BonitaExportException(Messages.bind(Messages.linkGoToIsNull,throwLink.getLabel()));
//				}
//			}
//		}
//	}
//
//	private void addOutgoingEvents(ProcessDefinitionBuilder builder, Element item) throws BonitaExportException {
//		final ThrowMessageEvent event = (ThrowMessageEvent)item;
//		builder.addSendEventTask(event.getName());
//		addDesc(builder, item);
//		builder.addJoinType(JoinType.XOR);
//		for(EventObject ev : event.getEvents()){
//			Map<String, Object> parameters = new HashMap<String, Object>();
//			for(Data d : ev.getData()){
//				parameters.put(d.getName(), d.getDefaultValue());
//			}
//			String targetStep = ev.getTargetElementName() ;
//			String targetProc = ev.getTargetProcessName() ;
//			if(null != targetStep){
//				if(0 == targetStep.trim().length()){
//					targetStep = null;
//				}
//				if(0 == targetProc.trim().length() || targetStep == null){
//					targetProc = null;
//				}
//			}
//
//			//Specific case of boundary message
//			if(targetStep != null && targetProc != null){
//				MainProcess p = ModelHelper.getMainProcess(event) ;
//				AbstractProcess process = null ;
//				for(AbstractProcess proc : ModelHelper.getAllProcesses(p)){
//					if(proc.getName().equals(targetProc)){
//						process = proc ;
//					}
//				}
//				if(process != null){
//					for(AbstractCatchMessageEvent messageEvent : ModelHelper.getAllCatchEvent(process)){
//						if(messageEvent.getName().equals(targetStep) && messageEvent instanceof BoundaryMessageEvent){
//							if( messageEvent.eContainer() instanceof Element){
//								targetStep = ((Element) messageEvent.eContainer()).getName() ;
//							}
//						}
//					}
//				}
//
//			}
//
//			if(null == ev.getTtl()){
//				builder.addOutgoingEvent(ev.getName(), targetProc, targetStep,parameters);
//			}else{
//				builder.addOutgoingEvent(ev.getName(), targetProc, targetStep,Long.parseLong(ev.getTtl()),parameters);
//			}	
//		}
//	}
//
//	private void addIncomingEvents(ProcessDefinitionBuilder builder, Element item) throws BonitaExportException {
//		final CatchMessageEvent event = (CatchMessageEvent)item;
//		if(null == event.getEvent() || event.getEvent().length() <=0 ){
//			throw new BonitaExportException(Messages.bind(Messages.errorMissingEvent,event.getLabel()));
//		}
//		if(null == event.getMatcher() || 0 == event.getMatcher().trim().length()){
//			builder.addReceiveEventTask(event.getName(),  NamingUtils.convertToId(event.getEvent()));
//		}else{
//			builder.addReceiveEventTask(event.getName(), NamingUtils.convertToId(event.getEvent()),event.getMatcher());
//		}
//		addDesc(builder, item);
//		/*This is a special case*/
//		if(item instanceof ReceiveTask){
//			ReceiveTask receiveTask = (ReceiveTask) item;
//			addBoundaries(receiveTask, builder);
//		}
//		builder.addJoinType(JoinType.XOR);
//		processReceiveEventConnectors(event,builder);
//	}
//
//	private void processReceiveEventConnectors(ConnectableElement event,ProcessDefinitionBuilder builder) {
//		for (Connector connector : event.getConnectors()) {
//			if(!eObjectNotExported.contains(connector)){
//				String className = ConnectorRepository.getInstance().getConnectorAPI().getConnector(connector.getConnectorId()).getConnectorClass().getName();
//				builder.addReceiveEventConnector(className, ! connector.isIgnoreErrors());
//				builder.addDescription(connector.getDocumentation());
//				processConnectorParams(builder, connector);
//			}
//		}
//
//	}
//
//	/**
//	 * @param item
//	 * @param builder
//	 */
//	private void addActivity(Activity item, ProcessDefinitionBuilder builder) throws BonitaExportException {
//		if (item instanceof Task) {
//			determineWhichActorsToAdd((Task)item, builder);
//			builder.addHumanTask(item.getName(), getActorsForTask((Task)item)); // Several groups are possibles
//			processFilters((Task) item, builder);
//			builder.addActivityPriority(((Task)item).getPriority());
//			builder.addJoinType(JoinType.XOR);
//		} else if (item instanceof SubProcess) {
//			final String subprocessName = ((SubProcess)item).getSubprocessName();
//			if(null != subprocessName){
//				if(null != ((SubProcess)item).getSubprocessVersion()){
//					builder.addSubProcess(item.getName(), subprocessName,((SubProcess)item).getSubprocessVersion());
//				}else{
//					builder.addSubProcess(item.getName(), subprocessName);
//				}
//
//				builder.addJoinType(JoinType.XOR);
//				processSubprocessParameterMapping((SubProcess)item, builder);
//			}else{
//				throw new BonitaExportException(Messages.bind(Messages.errorMissingSubprocess,item.getLabel()));
//			}
//		} else {
//			//include system, service and script task
//			builder.addSystemTask(item.getName()).addJoinType(JoinType.XOR);
//		}
//		addDesc(builder, item);
//		final String duration = item.getDuration();
//		if(null != duration){
//			if(Long.parseLong(duration)> 0){
//				builder.addActivityExecutingTime(Long.parseLong(duration));
//			}
//		}
//
//		processConnectors(item, builder, false);
//		processDeadlines(item,builder);
//		processKPIs(item, builder);
//		addData(item, builder);
//
//		final MultiInstantiation multiInstantiation = item.getMultiInstantiation();
//		if (null != multiInstantiation && multiInstantiation.isEnabled()) {
//			final ConnectorAPI connectorAPI = ConnectorRepository.getInstance().getConnectorAPI();
//			final Connector instantiator = multiInstantiation.getInstantiator();
//			builder.addMultipleActivitiesInstantiator(connectorAPI.getConnector(instantiator.getConnectorId()).getConnectorClass().getName());
//			processConnectorParams(builder, instantiator);
//			final Connector joinChecker = multiInstantiation.getJoinChecker();
//			builder.addMultipleActivitiesJoinChecker(connectorAPI.getConnector(joinChecker.getConnectorId()).getConnectorClass().getName());
//			processConnectorParams(builder, joinChecker);
//
//		}else if(item.getIsLoop()){
//			final String loopCondition = item.getLoopCondition();
//			if(null == loopCondition || loopCondition.trim().length() ==0)
//				throw new BonitaExportException(Messages.bind(Messages.errorMissingConditiononLoop,item.getLabel()));
//
//			final String loopMaximum = item.getLoopMaximum();
//			if(loopMaximum != null && loopMaximum.trim().length()>0){
//				if( isGroovyExpression(loopMaximum)){
//					builder.addLoop(loopCondition, item.getTestBefore(),loopMaximum);
//				}else{
//					try{
//						Integer.parseInt(loopMaximum);
//						builder.addLoop(loopCondition, item.getTestBefore(),loopMaximum);
//					}catch (Exception e) {
//						throw new BonitaExportException(Messages.bind(Messages.errorMaximumConditionLoopNotValid,item.getLabel()));
//					}
//				}
//			}else{
//				builder.addLoop(loopCondition, item.getTestBefore());
//			}			
//		}
//		addBoundaries(item, builder);
//
//	}
//
//
//	/**
//	 * @param activity : The activity that can have boundary event.
//	 * @param builder
//	 */
//	private void addBoundaries(Activity activity, ProcessDefinitionBuilder builder) {
//		for(BoundaryEvent boundaryEvent : activity.getBoundaryIntermediateEvents()){
//			String boundaryEventName = boundaryEvent.getName();
//			if(boundaryEvent instanceof BoundaryTimerEvent){
//				builder.addTimerBoundaryEvent(boundaryEventName, ((BoundaryTimerEvent) boundaryEvent).getCondition());
//			} else if(boundaryEvent instanceof BoundaryMessageEvent){
//				builder.addMessageBoundaryEvent(((BoundaryMessageEvent)boundaryEvent).getEvent(), ((BoundaryMessageEvent)boundaryEvent).getMatcher());
//			} else if(boundaryEvent instanceof IntermediateErrorCatchEvent){
//				/*Need to call different method because engine don't interpret empty string as null*/
//				String errorCode = ((IntermediateErrorCatchEvent) boundaryEvent).getErrorCode();
//				if(errorCode != null && errorCode.trim().length() != 0){
//					builder.addErrorBoundaryEvent(boundaryEventName, errorCode);
//				} else {
//					builder.addErrorBoundaryEvent(boundaryEventName);
//				}				
//			} else if(boundaryEvent instanceof BoundarySignalEvent){
//				builder.addSignalBoundaryEvent(boundaryEventName, ((BoundarySignalEvent) boundaryEvent).getSignalCode());
//			}
//		}
//	}
//
//	/**
//	 * @param filters
//	 * @param builder
//	 */
//	private void processFilters(Task item, ProcessDefinitionBuilder builder) {
//		if(!item.isOverrideGroupsOfTheLane() && item.eContainer() instanceof Lane){
//			final Lane lane = (Lane) item.eContainer();
//			for (Connector filter : lane.getFilters()) {
//				String className = ConnectorRepository.getInstance().getConnectorAPI().getConnector(filter.getConnectorId()).getConnectorClass().getName();
//				builder.addFilter(className);
//				builder.addDescription(filter.getDocumentation());
//				processConnectorParams(builder, filter);
//			}
//
//		} else {
//			for (Connector filter : item.getFilters()) {
//				String className = ConnectorRepository.getInstance().getConnectorAPI().getConnector(filter.getConnectorId()).getConnectorClass().getName();
//				builder.addFilter(className);
//				builder.addDescription(filter.getDocumentation());
//				processConnectorParams(builder, filter);
//			}
//		}	
//	}
//
//	/**
//	 * @param item
//	 * @param builder
//	 * @throws BonitaExportException 
//	 */
//	private void processKPIs(ConnectableElement item, ProcessDefinitionBuilder builder) throws BonitaExportException {
//		for (AbstractKPIBinding kpiBinding : item.getKpis()) {
//			if (kpiBinding instanceof DatabaseKPIBinding) {
//				processDatabaseKPIBinding((DatabaseKPIBinding)kpiBinding, builder);
//			}
//		}
//	}
//
//	/**
//	 * @param kpiBinding
//	 * @param builder
//	 * @throws BonitaExportException 
//	 */
//	private void processDatabaseKPIBinding(DatabaseKPIBinding kpiBinding, ProcessDefinitionBuilder builder) throws BonitaExportException {
//		if (kpiBinding.getParameters().size() == 0) {
//			return;
//		}
//
//		final Configuration config = (Configuration) ConfigurationRepository.getInstance().getXStream().fromXML(kpiBinding.getDatabaseConfiguration().getConfiguration());
//		final Event event = Event.valueOf(kpiBinding.getEvent());
//		builder.addConnector(event, config.getConnectorDescription().getConnectorClass().getName(), ! kpiBinding.isIgnoreError());
//		for (org.ow2.bonita.connector.core.configuration.Parameter param : config.getParameters()) {
//			builder.addInputParameter(param.getName(), param.getValue());
//		}
//		if(kpiBinding.isUseGraphicalEditor()){
//			builder.addInputParameter(DB_REQUEST_PARAMETER, createInsertKPIRequest(kpiBinding));
//		} else {
//			builder.addInputParameter(DB_REQUEST_PARAMETER, kpiBinding.getRequest());
//		}
//
//	}
//
//	private String createInsertKPIRequest(DatabaseKPIBinding kpiBinding) throws BonitaExportException {
//		final StringBuilder insert = new StringBuilder();
//		final StringBuilder values = new StringBuilder();
//		insert.append("INSERT INTO ");
//		insert.append(kpiBinding.getTableName());
//		insert.append(" (");
//		values.append("VALUES (");
//		for (org.bonitasoft.studio.model.kpi.KPIParameterMapping mapping : kpiBinding.getParameters()) {
//			if (mapping.getKpiFieldName() != null && mapping.getKpiFieldName().trim().length() > 0) {
//				insert.append(mapping.getKpiFieldName());
//				insert.append(", ");
//				if (isTypeNeedsQuotes(mapping)) {
//					values.append('\'');
//				}
//				values.append(mapping.getValue());
//				if (isTypeNeedsQuotes(mapping)) {
//					values.append('\'');
//				}
//				values.append(", ");
//			}
//		}
//		insert.delete(insert.length() - 2, insert.length() - 1);
//		insert.append(')');
//		values.delete(values.length() - 2, values.length() - 1);
//		values.append(')');
//
//		insert.append('\n');
//		insert.append(values);
//		return insert.toString();
//	}
//
//	/**
//	 * @param mapping
//	 * @return
//	 * @throws BonitaExportException 
//	 */
//	private boolean isTypeNeedsQuotes(KPIParameterMapping mapping) throws BonitaExportException {
//
//		if(kpiTypeResolverContrib == null){
//			final IConfigurationElement[] elements = BonitaStudioExtensionRegistryManager.getInstance().getConfigurationElements("org.bonitasoft.studio.engine.kpityperesolver"); //$NON-NLS-1$
//			for (IConfigurationElement elem : elements){
//				try {
//					kpiTypeResolverContrib = (IKPITypeResolverContribution) elem.createExecutableExtension("class"); //$NON-NLS-1$
//				} catch (CoreException e) {
//					BonitaStudioLog.log(e);
//				}
//			}
//		}
//		final KPIField field = kpiTypeResolverContrib.getKPIField(mapping);
//		return field.isUseQuotes();
//	}
//
//	/**
//	 * @param item
//	 * @param builder
//	 */
//	private void processSubprocessParameterMapping(SubProcess item, ProcessDefinitionBuilder builder) {
//		for (InputMapping inputMapping : item.getInputMappings()) {
//			builder.addSubProcessInParameter(inputMapping.getProcessSource().getName(), inputMapping.getSubprocessTarget());
//		}
//		for (OutputMapping outputMapping : item.getOutputMappings()) {
//			builder.addSubProcessOutParameter(outputMapping.getSubprocessSource(), outputMapping.getProcessTarget().getName());
//		}
//	}
//
//	private void processDeadlines(Activity item, ProcessDefinitionBuilder builder) throws BonitaExportException {
//		for(Deadline deadline : item.getDeadlines()){
//			if(!eObjectNotExported.contains(deadline)){
//				String className = ConnectorRepository.getInstance().getConnectorAPI().getConnector(deadline.getConnector().getConnectorId()).getConnectorClass().getName();
//				if(deadline.getCondition() == null || deadline.getCondition().trim().length() == 0){
//					throw new BonitaExportException(Messages.bind(Messages.errorMissingConditiononDeadline,deadline.getConnector().getLabel()));
//				}
//				builder.addDeadline(deadline.getCondition(),className);
//				builder.addDescription(deadline.getConnector().getDocumentation());
//				processConnectorParams(builder, deadline.getConnector());
//			}
//		}
//	}
//
//	/**
//	 * @param item
//	 * @return The actors of the task or its lane if it doesn't override it.
//	 * @throws BonitaExportException 
//	 */
//	private String[] getActorsForTask(Task item) throws BonitaExportException {
//		if(!item.isOverrideGroupsOfTheLane() && item.eContainer() instanceof Lane){
//			final Lane lane = (Lane) item.eContainer();
//			return getActors((Assignable)lane);
//		} else {
//			return getActors((Assignable)item);
//		}	
//	}
//
//	private String[] getActors(Assignable assignable) throws BonitaExportException{
//		if (assignable.getActorType().equals(ActorType.GROUP)) {
//			if (assignable.getGroups().isEmpty()) {
//				throw new BonitaExportException(Messages.errorMissingGroup);
//			}
//
//			final List<String> res = new ArrayList<String>();
//			for (Group group : assignable.getGroups()) {
//				res.add(group.getName());
//			}
//			return res.toArray(new String[res.size()]);
//
//		} else if (assignable.getActorType().equals(ActorType.USER)) {
//			final String[] res = new String[1];
//			res[0] = assignable.getUser();
//			return res;
//		} else {
//			throw new BonitaRuntimeException("This exception should never occur"); //$NON-NLS-1$
//		}
//	}
//
//
//	/**
//	 * @param item
//	 * @param builder
//	 * @param forceAutomatic
//	 */
//	private void processConnectors(ConnectableElement item, ProcessDefinitionBuilder builder, boolean forceAutomatic) {
//		for (Connector connector : item.getConnectors()) {
//			if(!eObjectNotExported.contains(connector)){
//				String className = ConnectorRepository.getInstance().getConnectorAPI().getConnector(connector.getConnectorId()).getConnectorClass().getName();
//				Event event;
//				if (forceAutomatic || connector.getEvent() == null) {
//					event = Event.automaticOnEnter;
//				} else {
//					event = Event.valueOf(connector.getEvent());
//				}
//				builder.addConnector(event, className, ! connector.isIgnoreErrors());
//				String namedError = connector.getNamedError();
//				if(!connector.isIgnoreErrors() && connector.isThrowErrorEvent()){
//					builder.throwCatchError(namedError);
//				}
//				builder.addDescription(connector.getDocumentation());
//				processConnectorParams(builder, connector);
//			}
//		}
//	}
//
//
//	/**
//	 * @param item
//	 * @param builder
//	 */
//	private void determineWhichActorsToAdd(Task item, ProcessDefinitionBuilder builder) throws BonitaExportException {
//		if(!item.isOverrideGroupsOfTheLane() && item.eContainer() instanceof Lane){
//			Lane lane = (Lane) item.eContainer();
//			addActors((Assignable)lane, builder);
//		} else {
//			addActors((Assignable)item, builder);
//		}
//	}
//
//	private void addActors(Assignable assignable, ProcessDefinitionBuilder builder) throws BonitaExportException{
//		if (assignable.getActorType().equals(ActorType.GROUP)) {
//			if (assignable.getGroups().size() != 0) {
//				addGroups(assignable.getGroups(), builder);
//			} else if (assignable.getGroups().isEmpty()) {
//				throw new BonitaExportException(Messages.bind(Messages.errorMissingGroup,((Element) assignable).getName()));
//			}
//		} else if (assignable.getActorType().equals(ActorType.USER)) {
//			if (assignable.getUser() == null || assignable.getUser().length() == 0) {
//				throw new BonitaExportException(Messages.bind(Messages.errorIndividualNotSet, ((Element) assignable).getLabel()));
//			}
//			if (! definedAuthorities.contains(assignable.getUser())) {
//				builder.addHuman(assignable.getUser()); // define user
//				definedAuthorities.add(assignable.getUser());
//			}
//		}
//	}
//
//
//
//	/**
//	 * @param builder
//	 * @param connector
//	 */
//	private void processConnectorParams(ProcessDefinitionBuilder builder, Connector connector) {
//		List<Setter> setters = ConnectorRepository.getInstance().getConnectorAPI().getConnector(connector.getConnectorId()).getInputs();
//		Set<String> setterNames = new HashSet<String>();
//		//addComponents(components, setterNames);
//		for(Setter set : setters){
//			setterNames.add(set.getSetterName());
//		}
//
//		for (Parameter param : connector.getParameters()) {
//			if (!setterNames.contains(param.getKey())) { // ignore unrelevant keys
//				continue;
//			}
//
//			Object value = param.getValue();
//			if (value != null && value instanceof String && ((String)value).length() == 0) {
//				builder.addInputParameter(param.getKey(), (Object)null);
//			} else {
//				builder.addInputParameter(param.getKey(), param.getValue());
//			}
//		}
//		StringBuilder targetBuilder;
//		String outputValueExpression;
//		Data targetData;
//		String additionalPath;
//		for (OutputParameterMapping output : connector.getOutputs()) {
//			outputValueExpression = output.getValueExpression();
//			targetData = output.getTargetData();
//			if (outputValueExpression != null && outputValueExpression.trim().length() > 0 && targetData != null) {
//				String targetExpression = toSetVariableExpression(output);
//				builder.addOutputParameter("${" + outputValueExpression + "}", targetExpression); //$NON-NLS-1$ //$NON-NLS-2$
//			}
//		}
//	}
//
//	/**
//	 * @param targetData
//	 * @param output
//	 * @return
//	 */
//	public static String toSetVariableExpression(OutputParameterMapping output) {
//		Data targetData = output.getTargetData();
//		StringBuilder targetBuilder;
//		String additionalPath;
//		targetBuilder = new StringBuilder(targetData.getName());
//		additionalPath = output.getAdditionalPath();
//		if (targetData.isMultiple() && WriteType.valueOf(additionalPath) == WriteType.APPEND) {
//			targetBuilder.append(BonitaConstants.JAVA_VAR_SEPARATOR).append(targetData.getName()).append(BonitaConstants.JAVA_VAR_SEPARATOR).append("add");
//		} else if (targetData.isMultiple() && WriteType.valueOf(additionalPath) == WriteType.REPLACE) {
//			//do nothing
//		} else if (targetData instanceof XMLData && additionalPath != null && additionalPath.trim().length() > 0) {
//			targetBuilder.append(BonitaConstants.XPATH_VAR_SEPARATOR).append(additionalPath);
//		} else if (targetData instanceof JavaObjectData && additionalPath != null && additionalPath.trim().length() > 0) {
//			targetBuilder.append(BonitaConstants.JAVA_VAR_SEPARATOR).append(additionalPath);
//		}
//		String targetExpression = targetBuilder.toString();
//		return targetExpression;
//	}
//
//	/**
//	 * @param groups
//	 */
//	private void addGroups(EList<? extends Group> groups, ProcessDefinitionBuilder builder) {
//		if(groups.size() != 0){
//			ConnectorAPI connectorAPI = ConnectorRepository.getInstance().getConnectorAPI();
//			for (Group group : groups) {
//				if (!definedAuthorities.contains(group.getName())) {				
//					String className = connectorAPI.getConnector(group.getConnectorId()).getConnectorClass().getName();
//					builder.addGroup(group.getName());
//					addDesc(builder, group);
//					builder.addGroupResolver(className);
//					processConnectorParams(builder, group);
//					definedAuthorities.add(group.getName());
//				}
//			}
//		}
//	}
//
//	/**
//	 * @param element
//	 * @param builder
//	 * @throws BonitaExportException 
//	 */
//	private void addData(DataAware element, ProcessDefinitionBuilder builder) throws BonitaExportException {
//		for (Data data : element.getData()) {
//			if (definedData.contains(data)) {
//				continue;
//			}
//
//			definedData.add(data);
//			DataType dataType = data.getDataType();
//			boolean hasDefaultValue = data.getDefaultValue() != null && data.getDefaultValue().trim().length() > 0;
//			if (data.isMultiple()) {
//				String defaultValue = data.getDefaultValue();
//				if (defaultValue == null || defaultValue.trim().length() == 0) {
//					defaultValue = "${[]}";
//				}
//				builder.addObjectData(data.getName(), List.class.getName(), defaultValue);
//			} else if (data.getDataType() instanceof EnumType) {
//				List<String> literals = ((EnumType)data.getDataType()).getLiterals();
//				builder.addEnumData(data.getName(), toSet(literals), data.getDefaultValue());
//			} else if (dataType instanceof DateType) { // To be tested before StringType
//				if (hasDefaultValue) {
//					if(org.bonitasoft.studio.common.DateUtil.isDate(data.getDefaultValue())){
//						builder.addDateData(data.getName(), DateUtil.parseDate(data.getDefaultValue())); // convert
//					}else if (data.getDefaultValue().toLowerCase().equals("now")){ //$NON-NLS-1$
//						builder.addDateData(data.getName(),NOW_DATE_GROOVY_EXPRESSION);
//					}else if(isGroovyExpression(data.getDefaultValue())){
//						builder.addDateData(data.getName(), data.getDefaultValue()); 
//					}else{
//						builder.addDateData(data.getName(), DateUtil.parseDate(data.getDefaultValue())); // convert
//					}
//				} else {
//					builder.addDateData(data.getName());
//				}					
//			} else if (dataType instanceof StringType) {
//				if (hasDefaultValue) {
//					if(isGroovyExpression(data.getDefaultValue())){
//						builder.addStringDataFromScript(data.getName(), data.getDefaultValue());
//					}else{
//						builder.addStringData(data.getName(), data.getDefaultValue());
//					}
//
//				} else {
//					builder.addStringData(data.getName());
//				}
//			} else if (dataType instanceof BooleanType) {
//				if (hasDefaultValue) {
//					if(isGroovyExpression(data.getDefaultValue())){
//						builder.addBooleanData(data.getName(),data.getDefaultValue());
//					}else{
//						builder.addBooleanData(data.getName(), Boolean.parseBoolean(data.getDefaultValue()));
//					}
//
//				} else {
//					builder.addBooleanData(data.getName());
//				}
//			} else if (dataType instanceof IntegerType) {
//				if (hasDefaultValue) {
//					if(isGroovyExpression(data.getDefaultValue())){
//						builder.addLongData(data.getName(),data.getDefaultValue());
//					}else{
//						builder.addLongData(data.getName(), Long.parseLong(data.getDefaultValue()));
//					}
//
//				} else {
//					builder.addLongData(data.getName());
//				}
//			} else if (dataType instanceof FloatType) {
//				if (hasDefaultValue) {
//					if(isGroovyExpression(data.getDefaultValue())){
//						builder.addDoubleData(data.getName(), data.getDefaultValue());
//					}else{
//						builder.addDoubleData(data.getName(), Double.parseDouble(data.getDefaultValue()));
//					}
//				} else {
//					builder.addDoubleData(data.getName());
//				}
//			} else if (dataType instanceof AttachmentType) {
//				if (hasDefaultValue) {
//					builder.addAttachment(data.getName(),((AttachmentData)data).getBarPath(),data.getDefaultValue());
//				} else {
//					builder.addAttachment(data.getName());
//				}
//			} else if (dataType instanceof JavaType) {
//				JavaObjectData javaObjectData = (JavaObjectData)data;
//				String defaultValue = javaObjectData.getDefaultValue();
//				if (defaultValue != null && !defaultValue.startsWith("${")) {
//					defaultValue = null;
//				}
//				if (javaObjectData.getClassName() != null) {
//					builder.addObjectData(data.getName(), javaObjectData.getClassName(), defaultValue);
//				} else {
//					builder.addObjectData(data.getName(), Object.class.getName(), defaultValue);
//				}
//			} else if (dataType instanceof XMLType) {
//				if (hasDefaultValue) {
//					if(isGroovyExpression(data.getDefaultValue())){
//						builder.addXMLDataFromScript(data.getName(), data.getDefaultValue());
//					}else{
//						builder.addXMLData(data.getName(), data.getDefaultValue());
//					}
//
//				} else {
//					builder.addXMLData(data.getName());
//				}
//			} else {
//				if (hasDefaultValue) {
//					builder.addStringData(data.getName(), data.getDefaultValue());
//				} else {
//					builder.addStringData(data.getName());
//				}
//			}
//			if(element instanceof Activity && data.isTransient()){
//				builder.setTransient();				
//			}
//			builder.addLabel(data.getLabel());
//			builder.addDescription(data.getDocumentation()) ;
//		}
//	}
//
//
//
//	private boolean isGroovyExpression(String text) {
//		return text.startsWith(GROOVY_PREFIX) && text.endsWith(GROOVY_SUFFIX);
//	}
//
//	private <T> Set<T> toSet(Collection<T> input) {
//		Set<T> res = new HashSet<T>();
//		for (T item : input) {
//			res.add(item);
//		}
//		return res;
//	}
//}
