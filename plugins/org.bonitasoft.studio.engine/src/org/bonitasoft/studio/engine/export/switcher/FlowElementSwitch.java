/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.engine.export.switcher;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.bonitasoft.engine.bpm.model.ActivityDefinitionBuilder;
import org.bonitasoft.engine.bpm.model.AutomaticTaskDefinitionBuilder;
import org.bonitasoft.engine.bpm.model.BoundaryEventDefinitionBuilder;
import org.bonitasoft.engine.bpm.model.CallActivityBuilder;
import org.bonitasoft.engine.bpm.model.CatchMessageEventTriggerDefinitionBuilder;
import org.bonitasoft.engine.bpm.model.EndEventDefinitionBuilder;
import org.bonitasoft.engine.bpm.model.FlowElementBuilder;
import org.bonitasoft.engine.bpm.model.GatewayDefinitionBuilder;
import org.bonitasoft.engine.bpm.model.GatewayType;
import org.bonitasoft.engine.bpm.model.IntermediateCatchEventDefinitionBuilder;
import org.bonitasoft.engine.bpm.model.IntermediateCatchMessageEventTriggerDefinitionBuilder;
import org.bonitasoft.engine.bpm.model.IntermediateThrowEventDefinitionBuilder;
import org.bonitasoft.engine.bpm.model.MultiInstanceLoopCharacteristicsBuilder;
import org.bonitasoft.engine.bpm.model.StartEventDefinitionBuilder;
import org.bonitasoft.engine.bpm.model.SubProcessDefinitionBuilder;
import org.bonitasoft.engine.bpm.model.TaskPriority;
import org.bonitasoft.engine.bpm.model.ThrowMessageEventTriggerBuilder;
import org.bonitasoft.engine.bpm.model.UserFilterDefinitionBuilder;
import org.bonitasoft.engine.bpm.model.UserTaskDefinitionBuilder;
import org.bonitasoft.engine.bpm.model.event.trigger.TimerType;
import org.bonitasoft.engine.core.operation.LeftOperandBuilder;
import org.bonitasoft.engine.core.operation.OperationBuilder;
import org.bonitasoft.engine.core.operation.OperatorType;
import org.bonitasoft.engine.exception.InvalidExpressionException;
import org.bonitasoft.engine.expression.ExpressionBuilder;
import org.bonitasoft.engine.expression.ExpressionInterpreter;
import org.bonitasoft.engine.expression.ExpressionType;
import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.TimerUtil;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.engine.export.EngineExpressionUtil;
import org.bonitasoft.studio.engine.i18n.Messages;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorParameter;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ListExpression;
import org.bonitasoft.studio.model.expression.Operation;
import org.bonitasoft.studio.model.process.AbstractCatchMessageEvent;
import org.bonitasoft.studio.model.process.AbstractTimerEvent;
import org.bonitasoft.studio.model.process.Activity;
import org.bonitasoft.studio.model.process.ActorFilter;
import org.bonitasoft.studio.model.process.BoundaryEvent;
import org.bonitasoft.studio.model.process.BoundaryMessageEvent;
import org.bonitasoft.studio.model.process.BoundarySignalEvent;
import org.bonitasoft.studio.model.process.BoundaryTimerEvent;
import org.bonitasoft.studio.model.process.CallActivity;
import org.bonitasoft.studio.model.process.Connection;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.EndErrorEvent;
import org.bonitasoft.studio.model.process.EndEvent;
import org.bonitasoft.studio.model.process.EndMessageEvent;
import org.bonitasoft.studio.model.process.EndSignalEvent;
import org.bonitasoft.studio.model.process.EndTerminatedEvent;
import org.bonitasoft.studio.model.process.FlowElement;
import org.bonitasoft.studio.model.process.InputMapping;
import org.bonitasoft.studio.model.process.IntermediateCatchMessageEvent;
import org.bonitasoft.studio.model.process.IntermediateCatchSignalEvent;
import org.bonitasoft.studio.model.process.IntermediateCatchTimerEvent;
import org.bonitasoft.studio.model.process.IntermediateErrorCatchEvent;
import org.bonitasoft.studio.model.process.IntermediateThrowMessageEvent;
import org.bonitasoft.studio.model.process.IntermediateThrowSignalEvent;
import org.bonitasoft.studio.model.process.Lane;
import org.bonitasoft.studio.model.process.Message;
import org.bonitasoft.studio.model.process.MultiInstantiation;
import org.bonitasoft.studio.model.process.OperationContainer;
import org.bonitasoft.studio.model.process.OutputMapping;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.SearchIndex;
import org.bonitasoft.studio.model.process.SourceElement;
import org.bonitasoft.studio.model.process.StartEvent;
import org.bonitasoft.studio.model.process.StartMessageEvent;
import org.bonitasoft.studio.model.process.StartSignalEvent;
import org.bonitasoft.studio.model.process.StartTimerEvent;
import org.bonitasoft.studio.model.process.SubProcessEvent;
import org.bonitasoft.studio.model.process.Task;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;


/**
 * @author Romain Bioteau
 *
 */
public class FlowElementSwitch extends AbstractSwitch {

	private FlowElementBuilder builder;

	public FlowElementSwitch(FlowElementBuilder processBuilder,Set<EObject> eObjectNotExported){
		super(eObjectNotExported) ;
		this.builder = processBuilder;
	}

	@Override
	public Element caseSubProcessEvent(SubProcessEvent subProcessEvent) {
		final SubProcessDefinitionBuilder subProcessBuilder = builder.addSubProcess(subProcessEvent.getName(), true).getSubProcessBuilder();
		final FlowElementSwitch subProcessSwitch = new FlowElementSwitch(subProcessBuilder, eObjectNotExported);
		List<FlowElement> flowElements = ModelHelper.getAllItemsOfType(subProcessEvent, ProcessPackage.Literals.FLOW_ELEMENT);
        for (FlowElement flowElement : flowElements) {
            if(!eObjectNotExported.contains(flowElement)){
            	subProcessSwitch.doSwitch(flowElement);
            }
        }
        List<SourceElement> sourceElements = ModelHelper.getAllItemsOfType(subProcessEvent, ProcessPackage.Literals.SOURCE_ELEMENT);
        SequenceFlowSwitch sequenceFlowSwitch = new SequenceFlowSwitch(subProcessBuilder) ;
        for (SourceElement sourceElement : sourceElements) {
            for (Connection connection : sourceElement.getOutgoing()) {
            	sequenceFlowSwitch.doSwitch(connection);
            }
        }
		return subProcessEvent;
	}
	
	
	@Override
	public Activity caseActivity(final Activity activity) {
		AutomaticTaskDefinitionBuilder taskBuilder =  builder.addAutomaticTask(activity.getName());
		handleCommonActivity(activity, taskBuilder);
		return activity;
	}


	@Override
	public FlowElement caseStartMessageEvent(StartMessageEvent object) {
		StartEventDefinitionBuilder eventBuilder = builder.addStartEvent(object.getName()) ;
		String message =  object.getEvent() ;
		if(message != null){
			CatchMessageEventTriggerDefinitionBuilder triggerBuilder =  eventBuilder.addMessageEventTrigger(message) ;
			for(Operation operation : object.getMessageContent()){
				triggerBuilder.addOperation(EngineExpressionUtil.createOperationForMessageContent(operation)) ;
			}
		}
		addDescription(eventBuilder, object.getDocumentation()) ;
		return object;
	}

	@Override
	public FlowElement caseIntermediateCatchMessageEvent(IntermediateCatchMessageEvent object) {
		IntermediateCatchEventDefinitionBuilder eventBuilder = builder.addIntermediateCatchEvent(object.getName()) ;
		String message =  object.getEvent() ;
		if(message != null){
			IntermediateCatchMessageEventTriggerDefinitionBuilder triggerBuilder =  eventBuilder.addMessageEventTrigger(message) ;
			addMessageContent(object, triggerBuilder);
			addMessageCorrelation(object, triggerBuilder);
		}
		addDescription(eventBuilder, object.getDocumentation()) ;
		return object;
	}

	private void addMessageContent(AbstractCatchMessageEvent messageEvent, CatchMessageEventTriggerDefinitionBuilder triggerBuilder) {
		for(Operation operation : messageEvent.getMessageContent()){
			triggerBuilder.addOperation(EngineExpressionUtil.createOperationForMessageContent(operation)) ;
		}	
	}

	private void addMessageCorrelation(AbstractCatchMessageEvent messageEvent, IntermediateCatchMessageEventTriggerDefinitionBuilder triggerBuilder){
		if(messageEvent.getCorrelation() != null){
			for(ListExpression row : messageEvent.getCorrelation().getExpressions()){
				List<org.bonitasoft.studio.model.expression.Expression> col =  row.getExpressions() ;
				if(col.size() == 2){
					org.bonitasoft.studio.model.expression.Expression correlationKeyExp = col.get(0);
					org.bonitasoft.studio.model.expression.Expression valueExpression = col.get(1);
					if(correlationKeyExp.getContent() != null
							&& !correlationKeyExp.getContent().isEmpty()
							&& valueExpression.getContent() != null
							&& !valueExpression.getContent().isEmpty()){
						triggerBuilder.addCorrelation(EngineExpressionUtil.createExpression(correlationKeyExp), EngineExpressionUtil.createExpression(valueExpression)) ;
					}
				}
			}
		}
	}


	@Override
	public FlowElement caseIntermediateThrowMessageEvent(IntermediateThrowMessageEvent object) {
		IntermediateThrowEventDefinitionBuilder eventBuilder = builder.addIntermediateThrowEvent(object.getName()) ;
		for(Message message  : object.getEvents()){
			ThrowMessageEventTriggerBuilder triggerBuilder =  eventBuilder.addMessageEventTrigger(message.getName(),EngineExpressionUtil.createExpression(message.getTargetProcessExpression()),EngineExpressionUtil.createExpression(message.getTargetElementExpression())) ;
			if(message.getMessageContent() != null){
				for(ListExpression row : message.getMessageContent().getExpressions()){
					List<org.bonitasoft.studio.model.expression.Expression> col =  row.getExpressions() ;
					org.bonitasoft.studio.model.expression.Expression idExp = col.get(0);
					org.bonitasoft.studio.model.expression.Expression messageContentExp = col.get(1) ;
					if(col.size()==2){
						if (idExp.getContent()!=null
								&& !idExp.getContent().isEmpty()
								&& messageContentExp.getContent()!=null
								&& !messageContentExp.getContent().isEmpty()){
							triggerBuilder.addMessageContentExpression(EngineExpressionUtil.createExpression(idExp), EngineExpressionUtil.createExpression(messageContentExp)) ;
						}
					}
				}
			}

			if(message.getCorrelation() != null){
				for(ListExpression row : message.getCorrelation().getCorrelationAssociation().getExpressions()){
					List<org.bonitasoft.studio.model.expression.Expression> col =  row.getExpressions() ;
					if(col.size() == 2){
						org.bonitasoft.studio.model.expression.Expression correlationKeyExp = col.get(0);
						org.bonitasoft.studio.model.expression.Expression valueExpression = col.get(1);
						if(correlationKeyExp.getContent() != null
								&& !correlationKeyExp.getContent().isEmpty()
								&& valueExpression.getContent() != null
								&& !valueExpression.getContent().isEmpty()){
							triggerBuilder.addCorrelation(EngineExpressionUtil.createExpression(correlationKeyExp), EngineExpressionUtil.createExpression(valueExpression)) ;
						}
					}
				}
			}
		}
		addDescription(eventBuilder, object.getDocumentation()) ;
		return object;
	}


	@Override
	public FlowElement caseEndMessageEvent(EndMessageEvent object) {
		EndEventDefinitionBuilder eventBuilder = builder.addEndEvent(object.getName()) ;
		for(Message message  : object.getEvents()){
			ThrowMessageEventTriggerBuilder triggerBuilder =  eventBuilder.addMessageEventTrigger(message.getName(),EngineExpressionUtil.createExpression(message.getTargetProcessExpression()),EngineExpressionUtil.createExpression(message.getTargetElementExpression())) ;
			if(message.getMessageContent() != null){
				for(ListExpression row : message.getMessageContent().getExpressions()){
					List<org.bonitasoft.studio.model.expression.Expression> col =  row.getExpressions() ;
					if(col.size()==2){
						org.bonitasoft.studio.model.expression.Expression idExp=col.get(0);
						org.bonitasoft.studio.model.expression.Expression messageContentExp =col.get(1);
						if (idExp.getContent()!=null
								&& !idExp.getContent().isEmpty()
								&& messageContentExp.getContent()!=null
								&& !messageContentExp.getContent().isEmpty()){
							triggerBuilder.addMessageContentExpression(EngineExpressionUtil.createExpression(idExp), EngineExpressionUtil.createExpression(messageContentExp)) ;
						}
					}
				}
			}

			if(message.getCorrelation() != null){
				for(ListExpression row : message.getCorrelation().getCorrelationAssociation().getExpressions()){
					List<org.bonitasoft.studio.model.expression.Expression> col =  row.getExpressions() ;
					if(col.size() == 2){
						org.bonitasoft.studio.model.expression.Expression correlationKeyExp = col.get(0);
						org.bonitasoft.studio.model.expression.Expression valueExpression = col.get(1);
						if(correlationKeyExp.getContent() != null
								&& !correlationKeyExp.getContent().isEmpty()
								&& valueExpression.getContent() != null
								&& !valueExpression.getContent().isEmpty()){
							triggerBuilder.addCorrelation(EngineExpressionUtil.createExpression(correlationKeyExp), EngineExpressionUtil.createExpression(valueExpression)) ;
						}
					}
				}
			}
		}
		addDescription(eventBuilder, object.getDocumentation()) ;
		return object;
	}

	@Override
	public Element caseStartSignalEvent(StartSignalEvent object) {
		StartEventDefinitionBuilder eventBuilder = builder.addStartEvent(object.getName()) ;
		String signal =  object.getSignalCode() ;
		if(signal != null){
			eventBuilder.addSignalEventTrigger(signal) ;
		}
		addDescription(eventBuilder, object.getDocumentation()) ;
		return object;
	}

	@Override
	public EndSignalEvent caseEndSignalEvent(EndSignalEvent object) {
		EndEventDefinitionBuilder eventBuilder = builder.addEndEvent(object.getName()) ;
		String signalCode = object.getSignalCode() ;
		if(signalCode != null){
			eventBuilder.addSignalEventTrigger(signalCode) ;
		}
		addDescription(eventBuilder, object.getDocumentation()) ;
		return object;
	}

	@Override
	public IntermediateCatchSignalEvent caseIntermediateCatchSignalEvent(IntermediateCatchSignalEvent object) {
		IntermediateCatchEventDefinitionBuilder eventBuilder = builder.addIntermediateCatchEvent(object.getName()) ;
		String signal =  object.getSignalCode() ;
		if(signal != null){
			eventBuilder.addSignalEventTrigger(signal) ;
		}
		addDescription(eventBuilder, object.getDocumentation()) ;
		return object;
	}

	@Override
	public IntermediateThrowSignalEvent caseIntermediateThrowSignalEvent(IntermediateThrowSignalEvent object) {
		IntermediateThrowEventDefinitionBuilder eventBuilder = builder.addIntermediateThrowEvent(object.getName()) ;
		String signal =  object.getSignalCode() ;
		if(signal != null){
			eventBuilder.addSignalEventTrigger(signal) ;
		}
		addDescription(eventBuilder, object.getDocumentation()) ;
		return object;
	}

	@Override
	public CallActivity caseCallActivity(CallActivity object) {
		Expression version = object.getCalledActivityVersion() ;
		if(version == null || version.getContent() == null || version.getContent().trim().isEmpty()){
			version = null ; //latest version will be used by the engine
		}
		final CallActivityBuilder activityBuilder = builder.addCallActivity(object.getName(),
				EngineExpressionUtil.createExpression(object.getCalledActivityName()),
				EngineExpressionUtil.createExpression(version)) ;
		for(InputMapping mapping : object.getInputMappings()){
			final OperationBuilder opBuilder = new OperationBuilder();
			opBuilder.createNewInstance();
			opBuilder.setRightOperand(EngineExpressionUtil.createVariableExpression(mapping.getProcessSource()));
			final LeftOperandBuilder builder = new LeftOperandBuilder() ;
			builder.createNewInstance() ;
			builder.setName(mapping.getSubprocessTarget()) ;
			opBuilder.setLeftOperand(builder.done());
			opBuilder.setType(OperatorType.ASSIGNMENT);
			activityBuilder.addDataInputOperation(opBuilder.done());
		}

		for(OutputMapping mapping : object.getOutputMappings()){
			final OperationBuilder opBuilder = new OperationBuilder();
			opBuilder.createNewInstance();
			final Data d = EcoreUtil.copy(mapping.getProcessTarget());
			d.setName(mapping.getSubprocessSource());
			opBuilder.setRightOperand(EngineExpressionUtil.createVariableExpression(d));
			final LeftOperandBuilder builder = new LeftOperandBuilder() ;
			builder.createNewInstance() ;
			builder.setName(mapping.getProcessTarget().getName()) ;
			opBuilder.setLeftOperand(builder.done());
			opBuilder.setType(OperatorType.ASSIGNMENT);
			activityBuilder.addDataOutputOperation(opBuilder.done());
		}


		handleCommonActivity(object, activityBuilder) ;
		return object ;
	}

	@Override
	public Task caseTask(final Task task) {
		String actor = null;
		ActorFilter filter = null ;
		if(!task.getFilters().isEmpty()){
			filter = task.getFilters().get(0) ;
		}
		if(task.isOverrideActorsOfTheLane()){
			if (task.getActor() != null) {
				actor = task.getActor().getName();
			}
		}else{
			final Lane lane =  ModelHelper.getParentLane(task) ;
			if(lane != null && lane.getActor() != null){
				actor = lane.getActor().getName();
			}
			if(task.getFilters().isEmpty() && !lane.getFilters().isEmpty()){
				filter = lane.getFilters().get(0) ;
			}
		}

		final UserTaskDefinitionBuilder taskBuilder = builder.addUserTask(task.getName(), actor);
		handleCommonActivity(task, taskBuilder) ;
		taskBuilder.addPriority(TaskPriority.values()[task.getPriority()].name()) ;
		addExpectedDuration(taskBuilder,task) ;
		if(filter != null){
			final UserFilterDefinitionBuilder filterBuilder = taskBuilder.addUserFilter(filter.getName(), filter.getDefinitionId(),filter.getDefinitionVersion()) ;
			for(ConnectorParameter parameter : filter.getConfiguration().getParameters()){
				filterBuilder.addInput(parameter.getKey(), EngineExpressionUtil.createExpression(parameter.getExpression())) ;
			}
		}
		return task;
	}


	@Override
	public StartEvent caseStartEvent(final StartEvent startEvent) {
		StartEventDefinitionBuilder eventBuilder = builder.addStartEvent(startEvent.getName());
		addDescription(eventBuilder, startEvent.getDocumentation()) ;
		return startEvent;
	}
	@Override
	public FlowElement caseStartTimerEvent(final StartTimerEvent startTimer) {
		StartEventDefinitionBuilder startTimerBuilder = builder.addStartEvent(startTimer.getName());
		if(ModelHelper.isInEvenementialSubProcessPool(startTimer)){
			TimerType timerType = getTimerType(startTimer);
			if(timerType != null){
				startTimerBuilder.addTimerEventTriggerDefinition(timerType, EngineExpressionUtil.createExpression(startTimer.getCondition()));
			}
		}else{
			ExpressionBuilder expressionBuilder = new ExpressionBuilder().createNewInstance(startTimer.getName()+"_startCondition");
			expressionBuilder.setContent(TimerUtil.getTimerExpressionContent(startTimer));
			if(TimerUtil.isCycle(startTimer)){
				if(TimerUtil.isScript(startTimer)){
					expressionBuilder.setExpressionType(ExpressionType.TYPE_READ_ONLY_SCRIPT.name());
					expressionBuilder.setInterpreter(ExpressionInterpreter.GROOVY.name());
					expressionBuilder.setReturnType(Date.class.getName());
				}else{
					expressionBuilder.setExpressionType(ExpressionType.TYPE_CONSTANT.name());
					expressionBuilder.setReturnType(String.class.getName());
				}
				try {
					startTimerBuilder.addTimerEventTriggerDefinition(TimerType.CYCLE, expressionBuilder.done());
				} catch (InvalidExpressionException e) {
					throw new RuntimeException(e);
				}
			}else{
				expressionBuilder.setExpressionType(ExpressionType.TYPE_READ_ONLY_SCRIPT.name());
				expressionBuilder.setInterpreter(ExpressionInterpreter.GROOVY.name());
				expressionBuilder.setReturnType(Date.class.getName());
				try {
					startTimerBuilder.addTimerEventTriggerDefinition(TimerType.DATE, expressionBuilder.done());
				} catch (InvalidExpressionException e) {
					throw new RuntimeException(e);
				}
			}
		}
		addDescription(startTimerBuilder, startTimer.getDocumentation()) ;
		return startTimer;
	}

	@Override
	public FlowElement caseIntermediateCatchTimerEvent(IntermediateCatchTimerEvent timer) {
		IntermediateCatchEventDefinitionBuilder timerBuilder = builder.addIntermediateCatchEvent(timer.getName());

		TimerType timerType = getTimerType(timer);
		if(timerType != null){
			timerBuilder.addTimerEventTriggerDefinition(timerType, EngineExpressionUtil.createExpression(timer.getCondition()));
		}
		addDescription(timerBuilder, timer.getDocumentation()) ;
		return timer;
	}


	private TimerType getTimerType(AbstractTimerEvent timer) {
		if(TimerUtil.isDuration(timer)){
			return TimerType.DURATION;
		}else{
			if(Long.class.getName().equals(timer.getCondition().getReturnType())){
				return TimerType.DURATION;
			}else if(Date.class.getName().equals(timer.getCondition().getReturnType())){
				return TimerType.DATE;
			}
		}
		return null;
	}

	@Override
	public FlowElement caseEndEvent(final EndEvent endEvent) {
		final EndEventDefinitionBuilder eventBuilder = builder.addEndEvent(endEvent.getName());
		addDescription(eventBuilder, endEvent.getDocumentation()) ;
		return endEvent;
	}

	@Override
	public Element caseEndErrorEvent(EndErrorEvent endErrorEvent) {
		EndEventDefinitionBuilder eventBuilder = builder.addEndEvent(endErrorEvent.getName());
		eventBuilder.addErrorEventTrigger(endErrorEvent.getErrorCode());
		addDescription(eventBuilder, endErrorEvent.getDocumentation()) ;
		return endErrorEvent;
	}

	@Override
	public FlowElement caseEndTerminatedEvent(final EndTerminatedEvent endTerminatedEvent) {
		EndEventDefinitionBuilder eventBuilder = builder.addEndEvent(endTerminatedEvent.getName());
		eventBuilder.addTerminateEventTrigger();
		addDescription(eventBuilder, endTerminatedEvent.getDocumentation()) ;
		return endTerminatedEvent;
	}


	@Override
	public FlowElement caseANDGateway(final org.bonitasoft.studio.model.process.ANDGateway gateway) {
		builder.addGateway(gateway.getName(), GatewayType.PARALLEL);
		//   addDescription(gatewayBuilder, gateway.getDocumentation()) ;
		return gateway;
	}

	@Override
	public FlowElement caseInclusiveGateway(final org.bonitasoft.studio.model.process.InclusiveGateway gateway) {
		if(gateway.getIncoming().size() > 1) {
			throw new RuntimeException(Messages.errorTooManyIncomingInInclusiveGateway);
		}else{
			builder.addGateway(gateway.getName(), GatewayType.INCLUSIVE);
			//   addDescription(gatewayBuilder, gateway.getDocumentation()) ;
			return gateway;
		}
	}

	@Override
	public FlowElement caseXORGateway(final org.bonitasoft.studio.model.process.XORGateway gateway) {
		final GatewayDefinitionBuilder gatewayBuilder =  builder.addGateway(gateway.getName(), GatewayType.EXCLUSIVE);
		//           addDescription(gatewayBuilder, gateway.getDocumentation()) ;
		return gateway;
	}

	protected void handleCommonActivity(final Activity activity, ActivityDefinitionBuilder taskBuilder) {
		addData(taskBuilder, activity);
		addOperation(taskBuilder, activity) ;
		addLoop(taskBuilder,activity) ;
		addConnector(taskBuilder,activity) ;
		addKPIBinding(taskBuilder, activity);
		addDisplayTitle(taskBuilder, activity) ;
		addDescription(taskBuilder, activity.getDocumentation()) ;
		addDisplayDescription(taskBuilder,activity) ;
		addDisplayDescriptionAfterCompletion(taskBuilder,activity) ;
		addMultiInstantiation(taskBuilder,activity);
		addBoundaryEvents(taskBuilder, activity);
	}

	private void addBoundaryEvents(ActivityDefinitionBuilder taskBuilder, Activity activity) {
		for(BoundaryEvent boundaryEvent :activity.getBoundaryIntermediateEvents()){
			BoundaryEventDefinitionBuilder boundaryEventBuilder = taskBuilder.addBoundaryEvent(boundaryEvent.getName());
			if(boundaryEvent instanceof IntermediateErrorCatchEvent){
				String errorCode = ((IntermediateErrorCatchEvent) boundaryEvent).getErrorCode();
				if(errorCode != null && errorCode.trim().isEmpty()){
					errorCode = null;
				}
				boundaryEventBuilder.addErrorEventTrigger(errorCode);
			} else if(boundaryEvent instanceof BoundaryMessageEvent){
				CatchMessageEventTriggerDefinitionBuilder catchMessageEventTriggerDefinitionBuilder = boundaryEventBuilder.addMessageEventTrigger(((BoundaryMessageEvent) boundaryEvent).getEvent());
				addMessageContent((BoundaryMessageEvent)boundaryEvent, catchMessageEventTriggerDefinitionBuilder);
			} else if(boundaryEvent instanceof BoundaryTimerEvent){
				TimerType timerType = getTimerType((BoundaryTimerEvent) boundaryEvent);
				if(timerType != null){
					boundaryEventBuilder.addTimerEventTriggerDefinition(timerType, EngineExpressionUtil.createExpression(((AbstractTimerEvent) boundaryEvent).getCondition()));
				}
			} else if(boundaryEvent instanceof BoundarySignalEvent){
				boundaryEventBuilder.addSignalEventTrigger(((BoundarySignalEvent) boundaryEvent).getSignalCode());
			}
		}		
	}

	protected void addMultiInstantiation( ActivityDefinitionBuilder taskBuilder , final Activity activity) {
		if(activity.isIsMultiInstance()){
			final MultiInstantiation multiInstantiation = activity.getMultiInstantiation();
			final Expression completionCondition = multiInstantiation.getCompletionCondition();
			if(multiInstantiation.isUseCardinality()){
				final Expression cardinality = multiInstantiation.getCardinality();
				if(cardinality != null && cardinality.getContent() != null && !cardinality.getContent().isEmpty()){
					MultiInstanceLoopCharacteristicsBuilder multiInstanceBuilder = taskBuilder.addMultiInstance(multiInstantiation.isSequential(), EngineExpressionUtil.createExpression(cardinality));
					if(completionCondition != null
							&& completionCondition.getContent() != null
							&& !completionCondition.getContent().isEmpty()){
						multiInstanceBuilder.addCompletionCondition(EngineExpressionUtil.createExpression(completionCondition));
					}
				}
			} else {
				final Data collectionDataToMultiInstantiate = multiInstantiation.getCollectionDataToMultiInstantiate();
				if(collectionDataToMultiInstantiate != null){
					MultiInstanceLoopCharacteristicsBuilder multiInstanceBuilder = taskBuilder.addMultiInstance(multiInstantiation.isSequential(), collectionDataToMultiInstantiate.getName());
					if(completionCondition != null
							&& completionCondition.getContent() != null
							&& !completionCondition.getContent().isEmpty()){
						multiInstanceBuilder.addCompletionCondition(EngineExpressionUtil.createExpression(completionCondition));
					}
					final Data inputData = multiInstantiation.getInputData();
					if(inputData != null){
						multiInstanceBuilder.addDataInputItemRef(inputData.getName());
					}
					final Data outputData = multiInstantiation.getOutputData();
					if(outputData != null) {
						multiInstanceBuilder.addDataOutputItemRef(outputData.getName());
					}
					Data listDataContainingOutputResults = multiInstantiation.getListDataContainingOutputResults();
					if(listDataContainingOutputResults != null){
						multiInstanceBuilder.addLoopDataOutputRef(listDataContainingOutputResults.getName());
					}
				}
			}

		}
	}

	protected void addExpectedDuration(UserTaskDefinitionBuilder taskBuilder, Task task) {
		final String duration = task.getDuration() ;
		if(duration != null && !duration.isEmpty()){
			try{
				taskBuilder.addExpectedDuration(Long.parseLong(duration)) ;
			}catch (NumberFormatException e) {
				BonitaStudioLog.error(e) ;
			}
		}
	}

	protected void addDisplayDescription(ActivityDefinitionBuilder builder, FlowElement flowElement) {
		org.bonitasoft.engine.expression.Expression exp =   EngineExpressionUtil.createExpression(flowElement.getDynamicDescription()) ;
		if(exp != null){
			builder.addDisplayDescription(exp) ;
		}
	}
	
	protected void addDisplayDescriptionAfterCompletion(ActivityDefinitionBuilder builder, FlowElement flowElement){
		org.bonitasoft.engine.expression.Expression exp =   EngineExpressionUtil.createExpression(flowElement.getStepSummary()) ;
		if(exp != null){
			builder.addDisplayDescriptionAfterCompletion(exp) ;
		}
	}

	protected void addDisplayTitle(ActivityDefinitionBuilder builder, FlowElement flowElement) {
		org.bonitasoft.engine.expression.Expression exp =   EngineExpressionUtil.createExpression(flowElement.getDynamicLabel()) ;
		if(exp != null){
			builder.addDisplayName(exp) ;
		}
	}

	protected void addOperation(ActivityDefinitionBuilder builder,OperationContainer activity) {
		for(Operation operation : activity.getOperations()){
			String inputType = null ;
			if(!operation.getOperator().getInputTypes().isEmpty()){
				inputType = operation.getOperator().getInputTypes().get(0) ;
			}
			if(operation.getLeftOperand() != null
					&& operation.getLeftOperand().getContent() != null
					&& operation.getRightOperand() != null
					&& operation.getRightOperand().getContent() != null){
				if (ExpressionConstants.SEARCH_INDEX_TYPE.equals(operation.getLeftOperand().getType())){
					// get the pool to get the list of searchIndex list
					Pool pool = null;
					if(activity.eContainer() instanceof Pool){
						pool = (Pool) activity.eContainer();
					}else if(activity.eContainer().eContainer() instanceof Pool){
						pool = (Pool) activity.eContainer().eContainer();
					}
					// get the searchIndex list
					List<SearchIndex> searchIndexList = new ArrayList<SearchIndex>();
					if(pool!=null){
						searchIndexList = pool.getSearchIndexes();
					}
					int idx=1;
					for(SearchIndex searchIdx : searchIndexList){
						// get the related searchIndex to set the operation
						if(searchIdx.getName().getContent().equals(operation.getLeftOperand().getName())){
							builder.addOperation(EngineExpressionUtil.createLeftOperandIndex(idx), OperatorType.STRING_INDEX, null, null, EngineExpressionUtil.createExpression(operation.getRightOperand()));
							break;
						}
						idx++;
					}
				} else {
					builder.addOperation(EngineExpressionUtil.createLeftOperand(operation.getLeftOperand()), OperatorType.valueOf(operation.getOperator().getType()), operation.getOperator().getExpression(),inputType, EngineExpressionUtil.createExpression(operation.getRightOperand())) ;
				}
			}
		}
	}

	protected void addLoop(ActivityDefinitionBuilder builder,Activity activity) {
		if(activity.getIsLoop()){
			if(activity.getLoopCondition() != null){
				builder.addLoop(activity.getTestBefore(), EngineExpressionUtil.createExpression(activity.getLoopCondition()), EngineExpressionUtil.createExpression(activity.getLoopMaximum())) ;
			}
		}
	}




}
