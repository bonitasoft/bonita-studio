/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.engine.export.builder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.bonitasoft.engine.bpm.flownode.GatewayType;
import org.bonitasoft.engine.bpm.flownode.TaskPriority;
import org.bonitasoft.engine.bpm.flownode.TimerType;
import org.bonitasoft.engine.bpm.process.impl.ActivityDefinitionBuilder;
import org.bonitasoft.engine.bpm.process.impl.AutomaticTaskDefinitionBuilder;
import org.bonitasoft.engine.bpm.process.impl.BoundaryEventDefinitionBuilder;
import org.bonitasoft.engine.bpm.process.impl.CallActivityBuilder;
import org.bonitasoft.engine.bpm.process.impl.CatchMessageEventTriggerDefinitionBuilder;
import org.bonitasoft.engine.bpm.process.impl.EndEventDefinitionBuilder;
import org.bonitasoft.engine.bpm.process.impl.FlowElementBuilder;
import org.bonitasoft.engine.bpm.process.impl.GatewayDefinitionBuilder;
import org.bonitasoft.engine.bpm.process.impl.IntermediateCatchEventDefinitionBuilder;
import org.bonitasoft.engine.bpm.process.impl.IntermediateThrowEventDefinitionBuilder;
import org.bonitasoft.engine.bpm.process.impl.MultiInstanceLoopCharacteristicsBuilder;
import org.bonitasoft.engine.bpm.process.impl.ProcessDefinitionBuilder;
import org.bonitasoft.engine.bpm.process.impl.ReceiveTaskDefinitionBuilder;
import org.bonitasoft.engine.bpm.process.impl.SendTaskDefinitionBuilder;
import org.bonitasoft.engine.bpm.process.impl.StartEventDefinitionBuilder;
import org.bonitasoft.engine.bpm.process.impl.SubProcessDefinitionBuilder;
import org.bonitasoft.engine.bpm.process.impl.ThrowMessageEventTriggerBuilder;
import org.bonitasoft.engine.bpm.process.impl.UserFilterDefinitionBuilder;
import org.bonitasoft.engine.bpm.process.impl.UserTaskDefinitionBuilder;
import org.bonitasoft.engine.operation.LeftOperand;
import org.bonitasoft.engine.operation.LeftOperandBuilder;
import org.bonitasoft.engine.operation.OperationBuilder;
import org.bonitasoft.engine.operation.OperatorType;
import org.bonitasoft.studio.common.DataUtil;
import org.bonitasoft.studio.common.DateUtil;
import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.model.IModelSearch;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.engine.export.EngineExpressionUtil;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorParameter;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ListExpression;
import org.bonitasoft.studio.model.expression.Operation;
import org.bonitasoft.studio.model.process.AbstractCatchMessageEvent;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.AbstractTimerEvent;
import org.bonitasoft.studio.model.process.Activity;
import org.bonitasoft.studio.model.process.ActorFilter;
import org.bonitasoft.studio.model.process.BoundaryEvent;
import org.bonitasoft.studio.model.process.BoundaryMessageEvent;
import org.bonitasoft.studio.model.process.BoundarySignalEvent;
import org.bonitasoft.studio.model.process.BoundaryTimerEvent;
import org.bonitasoft.studio.model.process.BusinessObjectData;
import org.bonitasoft.studio.model.process.CallActivity;
import org.bonitasoft.studio.model.process.Connection;
import org.bonitasoft.studio.model.process.Correlation;
import org.bonitasoft.studio.model.process.CorrelationTypeActive;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.DataAware;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.EndErrorEvent;
import org.bonitasoft.studio.model.process.EndEvent;
import org.bonitasoft.studio.model.process.EndMessageEvent;
import org.bonitasoft.studio.model.process.EndSignalEvent;
import org.bonitasoft.studio.model.process.EndTerminatedEvent;
import org.bonitasoft.studio.model.process.FlowElement;
import org.bonitasoft.studio.model.process.InputMapping;
import org.bonitasoft.studio.model.process.InputMappingAssignationType;
import org.bonitasoft.studio.model.process.IntermediateCatchMessageEvent;
import org.bonitasoft.studio.model.process.IntermediateCatchSignalEvent;
import org.bonitasoft.studio.model.process.IntermediateCatchTimerEvent;
import org.bonitasoft.studio.model.process.IntermediateErrorCatchEvent;
import org.bonitasoft.studio.model.process.IntermediateThrowMessageEvent;
import org.bonitasoft.studio.model.process.IntermediateThrowSignalEvent;
import org.bonitasoft.studio.model.process.Lane;
import org.bonitasoft.studio.model.process.Message;
import org.bonitasoft.studio.model.process.MultiInstanceType;
import org.bonitasoft.studio.model.process.MultiInstantiable;
import org.bonitasoft.studio.model.process.NonInterruptingBoundaryTimerEvent;
import org.bonitasoft.studio.model.process.OperationContainer;
import org.bonitasoft.studio.model.process.OutputMapping;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ReceiveTask;
import org.bonitasoft.studio.model.process.SearchIndex;
import org.bonitasoft.studio.model.process.SendTask;
import org.bonitasoft.studio.model.process.SourceElement;
import org.bonitasoft.studio.model.process.StartErrorEvent;
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
 */
public class EngineFlowElementBuilder extends AbstractProcessBuilder {

    private final FlowElementBuilder builder;

    public EngineFlowElementBuilder(final FlowElementBuilder processBuilder,
            IEngineDefinitionBuilderProvider engineDefinitionBuilderProvider,
            IModelSearch modelSearch) {
        super(engineDefinitionBuilderProvider, modelSearch);
        builder = processBuilder;
    }

    @Override
    public Element caseSubProcessEvent(final SubProcessEvent subProcessEvent) {
        final SubProcessDefinitionBuilder subProcessBuilder = builder.addSubProcess(subProcessEvent.getName(), true)
                .getSubProcessBuilder();
        final AbstractProcessBuilder subProcessSwitch = new EngineFlowElementBuilder(subProcessBuilder,
                engineDefinitionBuilderProvider, modelSearch);
        final List<FlowElement> flowElements = modelSearch.getAllItemsOfType(subProcessEvent, FlowElement.class);
        for (final FlowElement flowElement : flowElements) {
            subProcessSwitch.doSwitch(flowElement);
        }
        final List<SourceElement> sourceElements = modelSearch.getAllItemsOfType(subProcessEvent,
                SourceElement.class);
        final EngineSequenceFlowBuilder sequenceFlowSwitch = new EngineSequenceFlowBuilder(subProcessBuilder);
        for (final SourceElement sourceElement : sourceElements) {
            for (final Connection connection : sourceElement.getOutgoing()) {
                sequenceFlowSwitch.doSwitch(connection);
            }
        }
        return subProcessEvent;
    }

    @Override
    public Activity caseActivity(final Activity activity) {
        final AutomaticTaskDefinitionBuilder taskBuilder = builder.addAutomaticTask(activity.getName());
        handleCommonActivity(activity, taskBuilder);
        return activity;
    }

    @Override
    public Element caseSendTask(final SendTask senTask) {
        org.bonitasoft.engine.expression.Expression targetProcess = null;
        Message message = null;
        if (!senTask.getEvents().isEmpty()) {
            message = senTask.getEvents().get(0);
            targetProcess = EngineExpressionUtil.createExpression(message.getTargetProcessExpression());
        }
        final SendTaskDefinitionBuilder taskBuilder = ((ProcessDefinitionBuilder) builder).addSendTask(senTask.getName(),
                message.getName(), targetProcess);
        if (message != null) {
            taskBuilder.setTargetFlowNode(EngineExpressionUtil.createExpression(message.getTargetElementExpression()));
            if (message.getMessageContent() != null) {
                for (final ListExpression row : message.getMessageContent().getExpressions()) {
                    final List<org.bonitasoft.studio.model.expression.Expression> col = row.getExpressions();
                    final org.bonitasoft.studio.model.expression.Expression idExp = col.get(0);
                    final org.bonitasoft.studio.model.expression.Expression messageContentExp = col.get(1);
                    if (col.size() == 2) {
                        if (idExp.getContent() != null
                                && !idExp.getContent().isEmpty()
                                && messageContentExp.getContent() != null
                                && !messageContentExp.getContent().isEmpty()) {
                            taskBuilder.addMessageContentExpression(EngineExpressionUtil.createExpression(idExp),
                                    EngineExpressionUtil.createExpression(messageContentExp));
                        }
                    }
                }
            }
            if (message.getCorrelation() != null) {
                for (final ListExpression row : message.getCorrelation().getCorrelationAssociation().getExpressions()) {
                    final List<org.bonitasoft.studio.model.expression.Expression> col = row.getExpressions();
                    if (col.size() == 2) {
                        final org.bonitasoft.studio.model.expression.Expression correlationKeyExp = col.get(0);
                        final org.bonitasoft.studio.model.expression.Expression valueExpression = col.get(1);
                        if (correlationKeyExp.getContent() != null
                                && !correlationKeyExp.getContent().isEmpty()
                                && valueExpression.getContent() != null
                                && !valueExpression.getContent().isEmpty()) {
                            taskBuilder.addCorrelation(EngineExpressionUtil.createExpression(correlationKeyExp),
                                    EngineExpressionUtil.createExpression(valueExpression));
                        }
                    }
                }
            }
        }
        handleCommonActivity(senTask, taskBuilder);
        return senTask;
    }

    @Override
    public Element caseReceiveTask(final ReceiveTask receiveTask) {
        final String messageName = receiveTask.getEvent();
        final ReceiveTaskDefinitionBuilder taskBuilder = builder.addReceiveTask(receiveTask.getName(), messageName);
        if (messageName != null) {
            for (final Operation operation : receiveTask.getMessageContent()) {
                taskBuilder.addMessageOperation(EngineExpressionUtil.createOperationForMessageContent(operation));
            }
            if (receiveTask.getCorrelation() != null) {
                for (final ListExpression row : receiveTask.getCorrelation().getExpressions()) {
                    final List<org.bonitasoft.studio.model.expression.Expression> col = row.getExpressions();
                    if (col.size() == 2) {
                        final org.bonitasoft.studio.model.expression.Expression correlationKeyExp = col.get(0);
                        final org.bonitasoft.studio.model.expression.Expression valueExpression = col.get(1);
                        if (correlationKeyExp.getContent() != null
                                && !correlationKeyExp.getContent().isEmpty()
                                && valueExpression.getContent() != null
                                && !valueExpression.getContent().isEmpty()) {
                            taskBuilder.addCorrelation(EngineExpressionUtil.createExpression(correlationKeyExp),
                                    EngineExpressionUtil.createExpression(valueExpression));
                        }
                    }
                }
            }
        }
        handleCommonActivity(receiveTask, taskBuilder);
        return receiveTask;
    }

    @Override
    public FlowElement caseStartMessageEvent(final StartMessageEvent object) {
        final StartEventDefinitionBuilder eventBuilder = builder.addStartEvent(object.getName());
        final String message = object.getEvent();
        if (message != null) {
            final CatchMessageEventTriggerDefinitionBuilder triggerBuilder = eventBuilder.addMessageEventTrigger(message);
            addMessageContent(object, triggerBuilder);
            if (modelSearch.isInEvenementialSubProcessPool(object)) {
                addMessageCorrelation(object, triggerBuilder);
            }
        }
        addDescription(eventBuilder, object.getDocumentation());
        return object;
    }

    @Override
    public FlowElement caseIntermediateCatchMessageEvent(final IntermediateCatchMessageEvent object) {
        final IntermediateCatchEventDefinitionBuilder eventBuilder = builder.addIntermediateCatchEvent(object.getName());
        final String message = object.getEvent();
        if (message != null) {
            final CatchMessageEventTriggerDefinitionBuilder triggerBuilder = eventBuilder.addMessageEventTrigger(message);
            addMessageContent(object, triggerBuilder);
            addMessageCorrelation(object, triggerBuilder);
        }
        addDescription(eventBuilder, object.getDocumentation());
        return object;
    }

    private void addMessageContent(final AbstractCatchMessageEvent messageEvent,
            final CatchMessageEventTriggerDefinitionBuilder triggerBuilder) {
        for (final Operation operation : messageEvent.getMessageContent()) {
            triggerBuilder.addOperation(EngineExpressionUtil.createOperationForMessageContent(operation));
        }
    }

    protected void addMessageCorrelation(final AbstractCatchMessageEvent messageEvent,
            final CatchMessageEventTriggerDefinitionBuilder triggerBuilder) {
        if (messageEvent.getCorrelation() != null) {
            for (final ListExpression row : messageEvent.getCorrelation().getExpressions()) {
                final List<org.bonitasoft.studio.model.expression.Expression> col = row.getExpressions();
                if (col.size() == 2) {
                    final org.bonitasoft.studio.model.expression.Expression correlationKeyExp = col.get(0);
                    final org.bonitasoft.studio.model.expression.Expression valueExpression = col.get(1);
                    if (correlationKeyExp.getContent() != null
                            && !correlationKeyExp.getContent().isEmpty()
                            && valueExpression.getContent() != null
                            && !valueExpression.getContent().isEmpty()) {
                        triggerBuilder.addCorrelation(EngineExpressionUtil.createExpression(correlationKeyExp),
                                EngineExpressionUtil.createExpression(valueExpression));
                    }
                }
            }
        }
    }

    @Override
    public FlowElement caseIntermediateThrowMessageEvent(final IntermediateThrowMessageEvent object) {
        final IntermediateThrowEventDefinitionBuilder eventBuilder = builder.addIntermediateThrowEvent(object.getName());
        for (final Message message : object.getEvents()) {
            final ThrowMessageEventTriggerBuilder triggerBuilder = eventBuilder.addMessageEventTrigger(message.getName(),
                    EngineExpressionUtil.createExpression(message.getTargetProcessExpression()),
                    EngineExpressionUtil.createExpression(message.getTargetElementExpression()));
            if (message.getMessageContent() != null) {
                addThrowMessageContent(message, triggerBuilder);
            }
            final Correlation correlation = message.getCorrelation();
            if (correlation != null && correlation.getCorrelationType() != CorrelationTypeActive.INACTIVE) {
                addThrowMessageCorrelation(message, triggerBuilder);
            }
        }
        addDescription(eventBuilder, object.getDocumentation());
        return object;
    }

    protected void addThrowMessageCorrelation(final Message message,
            final ThrowMessageEventTriggerBuilder triggerBuilder) {
        for (final ListExpression row : message.getCorrelation().getCorrelationAssociation().getExpressions()) {
            final List<org.bonitasoft.studio.model.expression.Expression> col = row.getExpressions();
            if (col.size() == 2) {
                final org.bonitasoft.studio.model.expression.Expression correlationKeyExp = col.get(0);
                final org.bonitasoft.studio.model.expression.Expression valueExpression = col.get(1);
                if (correlationKeyExp.getContent() != null
                        && !correlationKeyExp.getContent().isEmpty()
                        && valueExpression.getContent() != null
                        && !valueExpression.getContent().isEmpty()) {
                    triggerBuilder.addCorrelation(EngineExpressionUtil.createExpression(correlationKeyExp),
                            EngineExpressionUtil.createExpression(valueExpression));
                }
            }
        }
    }

    protected void addThrowMessageContent(final Message message,
            final ThrowMessageEventTriggerBuilder triggerBuilder) {
        for (final ListExpression row : message.getMessageContent().getExpressions()) {
            final List<org.bonitasoft.studio.model.expression.Expression> col = row.getExpressions();
            final org.bonitasoft.studio.model.expression.Expression idExp = col.get(0);
            final org.bonitasoft.studio.model.expression.Expression messageContentExp = col.get(1);
            if (col.size() == 2) {
                if (idExp.getContent() != null
                        && !idExp.getContent().isEmpty()
                        && messageContentExp.getContent() != null
                        && !messageContentExp.getContent().isEmpty()) {
                    triggerBuilder.addMessageContentExpression(EngineExpressionUtil.createExpression(idExp),
                            EngineExpressionUtil.createExpression(messageContentExp));
                }
            }
        }
    }

    @Override
    public FlowElement caseEndMessageEvent(final EndMessageEvent object) {
        final EndEventDefinitionBuilder eventBuilder = builder.addEndEvent(object.getName());
        for (final Message message : object.getEvents()) {
            final ThrowMessageEventTriggerBuilder triggerBuilder = eventBuilder.addMessageEventTrigger(message.getName(),
                    EngineExpressionUtil.createExpression(message.getTargetProcessExpression()),
                    EngineExpressionUtil.createExpression(message.getTargetElementExpression()));
            if (message.getMessageContent() != null) {
                for (final ListExpression row : message.getMessageContent().getExpressions()) {
                    final List<org.bonitasoft.studio.model.expression.Expression> col = row.getExpressions();
                    if (col.size() == 2) {
                        final org.bonitasoft.studio.model.expression.Expression idExp = col.get(0);
                        final org.bonitasoft.studio.model.expression.Expression messageContentExp = col.get(1);
                        if (idExp.getContent() != null
                                && !idExp.getContent().isEmpty()
                                && messageContentExp.getContent() != null
                                && !messageContentExp.getContent().isEmpty()) {
                            triggerBuilder.addMessageContentExpression(EngineExpressionUtil.createExpression(idExp),
                                    EngineExpressionUtil.createExpression(messageContentExp));
                        }
                    }
                }
            }
            final Correlation correlation = message.getCorrelation();
            if (correlation != null && correlation.getCorrelationType() != CorrelationTypeActive.INACTIVE) {
                addThrowMessageCorrelation(message, triggerBuilder);
            }
        }
        addDescription(eventBuilder, object.getDocumentation());
        return object;
    }

    @Override
    public Element caseStartSignalEvent(final StartSignalEvent object) {
        final StartEventDefinitionBuilder eventBuilder = builder.addStartEvent(object.getName());
        final String signal = object.getSignalCode();
        if (signal != null) {
            eventBuilder.addSignalEventTrigger(signal);
        }
        addDescription(eventBuilder, object.getDocumentation());
        return object;
    }

    @Override
    public EndSignalEvent caseEndSignalEvent(final EndSignalEvent object) {
        final EndEventDefinitionBuilder eventBuilder = builder.addEndEvent(object.getName());
        final String signalCode = object.getSignalCode();
        if (signalCode != null) {
            eventBuilder.addSignalEventTrigger(signalCode);
        }
        addDescription(eventBuilder, object.getDocumentation());
        return object;
    }

    @Override
    public IntermediateCatchSignalEvent caseIntermediateCatchSignalEvent(final IntermediateCatchSignalEvent object) {
        final IntermediateCatchEventDefinitionBuilder eventBuilder = builder.addIntermediateCatchEvent(object.getName());
        final String signal = object.getSignalCode();
        if (signal != null) {
            eventBuilder.addSignalEventTrigger(signal);
        }
        addDescription(eventBuilder, object.getDocumentation());
        return object;
    }

    @Override
    public IntermediateThrowSignalEvent caseIntermediateThrowSignalEvent(final IntermediateThrowSignalEvent object) {
        final IntermediateThrowEventDefinitionBuilder eventBuilder = builder.addIntermediateThrowEvent(object.getName());
        final String signal = object.getSignalCode();
        if (signal != null) {
            eventBuilder.addSignalEventTrigger(signal);
        }
        addDescription(eventBuilder, object.getDocumentation());
        return object;
    }

    @Override
    public CallActivity caseCallActivity(final CallActivity object) {
        Expression version = object.getCalledActivityVersion();
        if (version == null || version.getContent() == null || version.getContent().trim().isEmpty()) {
            version = null; // latest version will be used by the engine
        }
        final CallActivityBuilder activityBuilder = builder.addCallActivity(object.getName(),
                EngineExpressionUtil.createExpression(object.getCalledActivityName()),
                EngineExpressionUtil.createExpression(version));
        exportInputMappingsForCallActivity(object, activityBuilder);
        exportOutputMappingForCallActivities(object, activityBuilder);

        handleCommonActivity(object, activityBuilder);
        return object;
    }

    protected void exportInputMappingsForCallActivity(final CallActivity callActivity,
            final CallActivityBuilder activityBuilder) {
        for (final InputMapping mapping : callActivity.getInputMappings()) {
            if (InputMappingAssignationType.DATA == mapping.getAssignationType()) {
                exportInputMappingAssignedToDataForCallActivity(activityBuilder, callActivity, mapping);
            } else {
                exportInputMappingAssignedToContractInputForCallActivity(activityBuilder, mapping);
            }
        }
    }

    private void exportInputMappingAssignedToContractInputForCallActivity(final CallActivityBuilder activityBuilder,
            final InputMapping mapping) {
        activityBuilder.addProcessStartContractInput(mapping.getSubprocessTarget(),
                EngineExpressionUtil.createExpression(mapping.getProcessSource()));
    }

    private void exportInputMappingAssignedToDataForCallActivity(final CallActivityBuilder activityBuilder,
            CallActivity callActivity, final InputMapping mapping) {
        final OperationBuilder opBuilder = new OperationBuilder();
        opBuilder.createNewInstance();
        opBuilder.setRightOperand(EngineExpressionUtil.createExpression(mapping.getProcessSource()));
        final LeftOperandBuilder builder = new LeftOperandBuilder();
        builder.createNewInstance();
        String subprocessTarget = mapping.getSubprocessTarget();
        builder.setName(subprocessTarget);

        Optional<Data> targetData = modelSearch.findProcess(
                callActivity.getCalledActivityName() != null ? callActivity.getCalledActivityName().getContent() : null,
                callActivity.getCalledActivityVersion() != null ? callActivity.getCalledActivityVersion().getContent()
                        : null)
                                .map(AbstractProcess::getData)
                                .map(Collection::stream)
                                .orElse(Stream.empty())
                                .filter(data -> subprocessTarget.equals(data.getName()))
                                .findFirst();
        if (targetData.isPresent()) {
            builder.setType(
                    targetData.get() instanceof BusinessObjectData ? LeftOperand.TYPE_BUSINESS_DATA : LeftOperand.TYPE_DATA);
        } else {
            final List<EObject> referencedElements = mapping.getProcessSource().getReferencedElements();
            String type = LeftOperand.TYPE_DATA;
            if (!referencedElements.isEmpty()) {
                type = getLeftOperandTypeForData(referencedElements.get(0));
            }
            builder.setType(type);
        }
        opBuilder.setLeftOperand(builder.done());
        opBuilder.setType(OperatorType.ASSIGNMENT);
        activityBuilder.addDataInputOperation(opBuilder.done());
    }

    protected DiagramRepositoryStore getDiagramRepositoryStore() {
        return RepositoryManager.getInstance().getCurrentRepository()
                .getRepositoryStore(DiagramRepositoryStore.class);
    }

    protected void exportOutputMappingForCallActivities(final CallActivity object,
            final CallActivityBuilder activityBuilder) {
        for (final OutputMapping mapping : object.getOutputMappings()) {
            final OperationBuilder opBuilder = new OperationBuilder();
            opBuilder.createNewInstance();
            final Data d = EcoreUtil.copy(mapping.getProcessTarget());
            d.setName(mapping.getSubprocessSource());
            opBuilder.setRightOperand(EngineExpressionUtil.createVariableExpression(d));
            final LeftOperandBuilder builder = new LeftOperandBuilder();
            builder.createNewInstance();
            builder.setName(mapping.getProcessTarget().getName());
            builder.setType(getLeftOperandTypeForData(mapping.getProcessTarget()));
            opBuilder.setLeftOperand(builder.done());
            opBuilder.setType(OperatorType.ASSIGNMENT);
            activityBuilder.addDataOutputOperation(opBuilder.done());
        }
    }

    private String getLeftOperandTypeForData(final EObject data) {
        if (data instanceof BusinessObjectData) {
            return LeftOperand.TYPE_BUSINESS_DATA;
        }
        return LeftOperand.TYPE_DATA;
    }

    @Override
    public Task caseTask(final Task task) {
        String actor = null;
        ActorFilter filter = null;
        if (!task.getFilters().isEmpty()) {
            filter = task.getFilters().get(0);
        }
        if (task.isOverrideActorsOfTheLane()) {
            if (task.getActor() != null) {
                actor = task.getActor().getName();
            }
        } else {
            final Lane lane = modelSearch.getDirectParentOfType(task, Lane.class);
            if (lane != null && lane.getActor() != null) {
                actor = lane.getActor().getName();
            }
            if (task.getFilters().isEmpty() && !lane.getFilters().isEmpty()) {
                filter = lane.getFilters().get(0);
            }
        }

        final UserTaskDefinitionBuilder taskBuilder = builder.addUserTask(task.getName(), actor);
        taskBuilder.addPriority(TaskPriority.values()[task.getPriority()].name());
        addExpectedDuration(taskBuilder, task);
        addUserFilterToTask(taskBuilder, actor, filter);
        addContract(taskBuilder, task);
        addContext(taskBuilder, task);

        handleCommonActivity(task, taskBuilder);
        return task;
    }

    protected void addUserFilterToTask(final UserTaskDefinitionBuilder taskBuilder, final String actor,
            final ActorFilter filter) {
        if (filter != null) {
            final UserFilterDefinitionBuilder filterBuilder = taskBuilder.addUserFilter(filter.getName(),
                    filter.getDefinitionId(),
                    filter.getDefinitionVersion());
            for (final ConnectorParameter parameter : filter.getConfiguration().getParameters()) {
                final org.bonitasoft.engine.expression.Expression inputExpression = EngineExpressionUtil
                        .createExpression(parameter.getExpression());
                if (inputExpression != null) {
                    filterBuilder.addInput(parameter.getKey(), inputExpression);
                }
            }
        }
    }

    @Override
    public StartEvent caseStartEvent(final StartEvent startEvent) {
        final StartEventDefinitionBuilder eventBuilder = builder.addStartEvent(startEvent.getName());
        addDescription(eventBuilder, startEvent.getDocumentation());
        return startEvent;
    }

    @Override
    public FlowElement caseStartTimerEvent(final StartTimerEvent startTimer) {
        final StartEventDefinitionBuilder startTimerBuilder = builder.addStartEvent(startTimer.getName());
        final org.bonitasoft.engine.expression.Expression startConditionExpression = EngineExpressionUtil
                .createExpression(startTimer.getCondition());
        if (modelSearch.isInEvenementialSubProcessPool(startTimer)) {
            final TimerType timerType = getTimerType(startTimer);
            if (timerType != null) {
                startTimerBuilder.addTimerEventTriggerDefinition(timerType, startConditionExpression);
            }
        } else {
            TimerType type = TimerType.CYCLE;
            if (startConditionExpression.getReturnType().equals(String.class.getName())) {
                type = TimerType.CYCLE;
            } else if (startConditionExpression.getReturnType().equals(Date.class.getName())) {
                type = TimerType.DATE;
            } else if (startConditionExpression.getReturnType().equals(Long.class.getName())) {
                type = TimerType.DURATION;
            } else {
                throw new RuntimeException(
                        "Unsupported return type " + startConditionExpression.getReturnType() + " for Start timer condition "
                                + startTimer.getName());
            }
            startTimerBuilder.addTimerEventTriggerDefinition(type, startConditionExpression);
        }
        addDescription(startTimerBuilder, startTimer.getDocumentation());
        return startTimer;
    }

    @Override
    public FlowElement caseIntermediateCatchTimerEvent(final IntermediateCatchTimerEvent timer) {
        final IntermediateCatchEventDefinitionBuilder timerBuilder = builder.addIntermediateCatchEvent(timer.getName());

        final TimerType timerType = getTimerType(timer);
        if (timerType != null) {
            timerBuilder.addTimerEventTriggerDefinition(timerType,
                    EngineExpressionUtil.createExpression(timer.getCondition()));
        }
        addDescription(timerBuilder, timer.getDocumentation());
        return timer;
    }

    private TimerType getTimerType(final AbstractTimerEvent timer) {
        if (isDuration(timer)) {
            return TimerType.DURATION;
        } else {
            final String timerConditionReturnType = timer.getCondition().getReturnType();
            try {
                if (Number.class.isAssignableFrom(Class.forName(timerConditionReturnType))) {
                    return TimerType.DURATION;
                } else if (Date.class.getName().equals(timerConditionReturnType)) {
                    return TimerType.DATE;
                }
            } catch (final ClassNotFoundException e) {
                throw new IllegalArgumentException(
                        String.format("Timer condition return type '%s' is not supported.", timerConditionReturnType), e);
            }
        }
        return null;
    }

    private boolean isDuration(final AbstractTimerEvent timer) {
        final Expression exp = timer.getCondition();
        if (exp != null) {
            return DateUtil.isDuration(exp.getContent());
        }
        return false;
    }

    @Override
    public FlowElement caseEndEvent(final EndEvent endEvent) {
        final EndEventDefinitionBuilder eventBuilder = builder.addEndEvent(endEvent.getName());
        addDescription(eventBuilder, endEvent.getDocumentation());
        return endEvent;
    }

    @Override
    public Element caseStartErrorEvent(final StartErrorEvent startErrorEvent) {
        final StartEventDefinitionBuilder eventBuilder = builder.addStartEvent(startErrorEvent.getName());
        eventBuilder.addErrorEventTrigger(startErrorEvent.getErrorCode());
        addDescription(eventBuilder, startErrorEvent.getDocumentation());
        return startErrorEvent;
    }

    @Override
    public Element caseEndErrorEvent(final EndErrorEvent endErrorEvent) {
        final EndEventDefinitionBuilder eventBuilder = builder.addEndEvent(endErrorEvent.getName());
        eventBuilder.addErrorEventTrigger(endErrorEvent.getErrorCode());
        addDescription(eventBuilder, endErrorEvent.getDocumentation());
        return endErrorEvent;
    }

    @Override
    public FlowElement caseEndTerminatedEvent(final EndTerminatedEvent endTerminatedEvent) {
        final EndEventDefinitionBuilder eventBuilder = builder.addEndEvent(endTerminatedEvent.getName());
        eventBuilder.addTerminateEventTrigger();
        addDescription(eventBuilder, endTerminatedEvent.getDocumentation());
        return endTerminatedEvent;
    }

    @Override
    public FlowElement caseANDGateway(final org.bonitasoft.studio.model.process.ANDGateway gateway) {
        final GatewayDefinitionBuilder gatewayBuilder = builder.addGateway(gateway.getName(), GatewayType.PARALLEL);
        addDisplayTitle(gatewayBuilder, gateway);
        addDisplayDescription(gatewayBuilder, gateway);
        addDisplayDescriptionAfterCompletion(gatewayBuilder, gateway);
        return gateway;
    }

    @Override
    public FlowElement caseInclusiveGateway(final org.bonitasoft.studio.model.process.InclusiveGateway gateway) {
        final GatewayDefinitionBuilder gatewayBuilder = builder.addGateway(gateway.getName(), GatewayType.INCLUSIVE);
        addDisplayTitle(gatewayBuilder, gateway);
        addDisplayDescription(gatewayBuilder, gateway);
        addDisplayDescriptionAfterCompletion(gatewayBuilder, gateway);
        return gateway;
    }

    @Override
    public FlowElement caseXORGateway(final org.bonitasoft.studio.model.process.XORGateway gateway) {
        final GatewayDefinitionBuilder gatewayBuilder = builder.addGateway(gateway.getName(), GatewayType.EXCLUSIVE);
        addDisplayTitle(gatewayBuilder, gateway);
        addDisplayTitle(gatewayBuilder, gateway);
        addDisplayDescription(gatewayBuilder, gateway);
        addDisplayDescriptionAfterCompletion(gatewayBuilder, gateway);
        return gateway;
    }

    protected void handleCommonActivity(final Activity activity, final ActivityDefinitionBuilder taskBuilder) {
        addData(taskBuilder, activity);
        addOperation(taskBuilder, activity);
        addConnector(taskBuilder, activity);
        addKPIBinding(taskBuilder, activity);
        addDisplayTitle(taskBuilder, activity);
        addDescription(taskBuilder, activity.getDocumentation());
        addDisplayDescription(taskBuilder, activity);
        addDisplayDescriptionAfterCompletion(taskBuilder, activity);
        addMultiInstantiation(taskBuilder, activity);
        addBoundaryEvents(taskBuilder, activity);
        addDescription(taskBuilder, activity.getDocumentation());
    }

    protected void addBoundaryEvents(final ActivityDefinitionBuilder taskBuilder, final Activity activity) {
        for (final BoundaryEvent boundaryEvent : activity.getBoundaryIntermediateEvents()) {
            final BoundaryEventDefinitionBuilder boundaryEventBuilder = taskBuilder.addBoundaryEvent(boundaryEvent.getName(),
                    !(boundaryEvent instanceof NonInterruptingBoundaryTimerEvent));
            if (boundaryEvent instanceof IntermediateErrorCatchEvent) {
                String errorCode = ((IntermediateErrorCatchEvent) boundaryEvent).getErrorCode();
                if (errorCode != null && errorCode.trim().isEmpty()) {
                    errorCode = null;
                }
                boundaryEventBuilder.addErrorEventTrigger(errorCode);
            } else if (boundaryEvent instanceof BoundaryMessageEvent) {
                final CatchMessageEventTriggerDefinitionBuilder catchMessageEventTriggerDefinitionBuilder = boundaryEventBuilder
                        .addMessageEventTrigger(((BoundaryMessageEvent) boundaryEvent).getEvent());
                addMessageContent((BoundaryMessageEvent) boundaryEvent, catchMessageEventTriggerDefinitionBuilder);
                addMessageCorrelation((BoundaryMessageEvent) boundaryEvent, catchMessageEventTriggerDefinitionBuilder);
            } else if (boundaryEvent instanceof BoundaryTimerEvent) {
                final TimerType timerType = getTimerType((BoundaryTimerEvent) boundaryEvent);
                if (timerType != null) {
                    boundaryEventBuilder.addTimerEventTriggerDefinition(timerType,
                            EngineExpressionUtil.createExpression(((AbstractTimerEvent) boundaryEvent).getCondition()));
                }
            } else if (boundaryEvent instanceof BoundarySignalEvent) {
                boundaryEventBuilder.addSignalEventTrigger(((BoundarySignalEvent) boundaryEvent).getSignalCode());
            }
        }
    }

    protected void addMultiInstantiation(final ActivityDefinitionBuilder taskBuilder, final Activity activity) {
        final MultiInstanceType multiInstanceType = activity.getType();
        switch (multiInstanceType) {
            case NONE:
                break;
            case STANDARD:
                addStandardLoop(taskBuilder, activity);
                break;
            case PARALLEL:
            case SEQUENTIAL:
                configureMultiInstantiation(taskBuilder, activity);
                break;
            default:
                break;
        }
    }

    protected void addExpectedDuration(final UserTaskDefinitionBuilder taskBuilder, final Task task) {
        final Expression duration = task.getExpectedDuration();
        if (duration != null && duration.hasContent()) {
            taskBuilder.addExpectedDuration(EngineExpressionUtil.createExpression(duration));
        }
    }

    protected void addDisplayDescription(final ActivityDefinitionBuilder builder, final FlowElement flowElement) {
        final org.bonitasoft.engine.expression.Expression exp = EngineExpressionUtil
                .createExpression(flowElement.getDynamicDescription());
        if (exp != null) {
            builder.addDisplayDescription(exp);
        }
    }

    protected void addDisplayDescriptionAfterCompletion(final ActivityDefinitionBuilder builder,
            final FlowElement flowElement) {
        final org.bonitasoft.engine.expression.Expression exp = EngineExpressionUtil
                .createExpression(flowElement.getStepSummary());
        if (exp != null) {
            builder.addDisplayDescriptionAfterCompletion(exp);
        }
    }

    protected void addDisplayTitle(final ActivityDefinitionBuilder builder, final FlowElement flowElement) {
        final org.bonitasoft.engine.expression.Expression exp = EngineExpressionUtil
                .createExpression(flowElement.getDynamicLabel());
        if (exp != null) {
            builder.addDisplayName(exp);
        }
    }

    protected void addDisplayDescription(final GatewayDefinitionBuilder builder, final FlowElement flowElement) {
        final org.bonitasoft.engine.expression.Expression exp = EngineExpressionUtil
                .createExpression(flowElement.getDynamicDescription());
        if (exp != null) {
            builder.addDisplayDescription(exp);
        }
    }

    protected void addDisplayTitle(final GatewayDefinitionBuilder builder, final FlowElement flowElement) {
        final org.bonitasoft.engine.expression.Expression exp = EngineExpressionUtil
                .createExpression(flowElement.getDynamicLabel());
        if (exp != null) {
            builder.addDisplayName(exp);
        }
    }

    protected void addDisplayDescriptionAfterCompletion(final GatewayDefinitionBuilder builder,
            final FlowElement flowElement) {
        final org.bonitasoft.engine.expression.Expression exp = EngineExpressionUtil
                .createExpression(flowElement.getStepSummary());
        if (exp != null) {
            builder.addDisplayDescriptionAfterCompletion(exp);
        }
    }

    protected void addOperation(final ActivityDefinitionBuilder builder, final OperationContainer activity) {
        for (final Operation operation : activity.getOperations()) {
            if (operation.getLeftOperand() != null
                    && operation.getLeftOperand().getContent() != null
                    && operation.getRightOperand() != null
                    && operation.getRightOperand().getContent() != null) {
                if (ExpressionConstants.SEARCH_INDEX_TYPE.equals(operation.getLeftOperand().getType())) {
                    // get the pool to get the list of searchIndex list
                    Pool pool = null;
                    if (activity.eContainer() instanceof Pool) {
                        pool = (Pool) activity.eContainer();
                    } else if (activity.eContainer().eContainer() instanceof Pool) {
                        pool = (Pool) activity.eContainer().eContainer();
                    }
                    // get the searchIndex list
                    List<SearchIndex> searchIndexList = new ArrayList<>();
                    if (pool != null) {
                        searchIndexList = pool.getSearchIndexes();
                    }
                    int idx = 1;
                    for (final SearchIndex searchIdx : searchIndexList) {
                        // get the related searchIndex to set the operation
                        if (searchIdx.getName().getContent().equals(operation.getLeftOperand().getName())) {
                            builder.addOperation(EngineExpressionUtil.createOperation(operation,
                                    EngineExpressionUtil.createLeftOperandIndex(idx)));
                            break;
                        }
                        idx++;
                    }
                } else {
                    builder.addOperation(EngineExpressionUtil.createOperation(operation));
                }
            }
        }
    }

    protected void addStandardLoop(final ActivityDefinitionBuilder builder, final MultiInstantiable multiInstantiable) {
        builder.addLoop(multiInstantiable.getTestBefore(),
                EngineExpressionUtil.createExpression(multiInstantiable.getLoopCondition()),
                EngineExpressionUtil.createExpression(multiInstantiable.getLoopMaximum()));
    }

    protected void configureMultiInstantiation(final ActivityDefinitionBuilder taskBuilder,
            final MultiInstantiable activity) {
        final Expression completionCondition = activity.getCompletionCondition();
        if (activity.isUseCardinality()) {
            final Expression cardinality = activity.getCardinalityExpression();
            if (cardinality != null && cardinality.getContent() != null && !cardinality.getContent().isEmpty()) {
                final MultiInstanceLoopCharacteristicsBuilder multiInstanceBuilder = taskBuilder.addMultiInstance(
                        activity.getType() == MultiInstanceType.SEQUENTIAL,
                        EngineExpressionUtil.createExpression(cardinality));
                if (completionCondition != null
                        && completionCondition.getContent() != null
                        && !completionCondition.getContent().isEmpty()) {
                    multiInstanceBuilder.addCompletionCondition(EngineExpressionUtil.createExpression(completionCondition));
                }
                if (activity.isStoreOutput()) {
                    final Data outputData = activity.getOutputData();
                    if (outputData != null) {
                        multiInstanceBuilder.addDataOutputItemRef(outputData.getName());
                    }
                    final Data listDataContainingOutputResults = activity.getListDataContainingOutputResults();
                    if (listDataContainingOutputResults != null) {
                        multiInstanceBuilder.addLoopDataOutputRef(listDataContainingOutputResults.getName());
                    }
                }
            }
        } else {
            final Data collectionDataToMultiInstantiate = activity.getCollectionDataToMultiInstantiate();
            final Expression iteratorExpression = activity.getIteratorExpression();
            if (ExpressionConstants.MULTIINSTANCE_ITERATOR_TYPE.equals(iteratorExpression.getType())
                    && iteratorExpression.getName() != null
                    && !iteratorExpression.getName().isEmpty()
                    && activity instanceof DataAware) {
                addDataForMultiInstanceIterator(taskBuilder, iteratorExpression, collectionDataToMultiInstantiate);
            }
            if (collectionDataToMultiInstantiate != null) {
                final MultiInstanceLoopCharacteristicsBuilder multiInstanceBuilder = taskBuilder.addMultiInstance(
                        activity.getType() == MultiInstanceType.SEQUENTIAL,
                        collectionDataToMultiInstantiate.getName());
                if (completionCondition != null
                        && completionCondition.getContent() != null
                        && !completionCondition.getContent().isEmpty()) {
                    multiInstanceBuilder.addCompletionCondition(EngineExpressionUtil.createExpression(completionCondition));
                }

                if (iteratorExpression != null && iteratorExpression.getName() != null
                        && !iteratorExpression.getName().isEmpty()) {
                    multiInstanceBuilder.addDataInputItemRef(iteratorExpression.getName());
                }
                if (activity.isStoreOutput()) {
                    final Data outputData = activity.getOutputData();
                    if (outputData != null) {
                        multiInstanceBuilder.addDataOutputItemRef(outputData.getName());
                    }
                    final Data listDataContainingOutputResults = activity.getListDataContainingOutputResults();
                    if (listDataContainingOutputResults != null) {
                        multiInstanceBuilder.addLoopDataOutputRef(listDataContainingOutputResults.getName());
                    }
                }

            }
        }
    }

    protected void addDataForMultiInstanceIterator(final ActivityDefinitionBuilder taskBuilder,
            final Expression iteratorExpression,
            final Data collectionDataToMultiInstantiate) {
        if (collectionDataToMultiInstantiate instanceof BusinessObjectData) {
            taskBuilder.addBusinessData(iteratorExpression.getName(), iteratorExpression.getReturnType());
        } else {
            final FlowElement parentFlowElement = modelSearch.getDirectParentOfType(iteratorExpression, FlowElement.class);
            if (parentFlowElement instanceof DataAware
                    && !isDataAlreadyExists(iteratorExpression, (DataAware) parentFlowElement)) {
                taskBuilder.addData(iteratorExpression.getName(), iteratorExpression.getReturnType(), null);
            }
        }
    }

    protected boolean isDataAlreadyExists(final Expression iteratorExpression, final DataAware parentFlowElement) {
        boolean dataAlreadyExists = false;
        for (final Data d : parentFlowElement.getData()) {
            if (d.getName().equals(iteratorExpression.getName())
                    && DataUtil.getTechnicalTypeFor(d).equals(iteratorExpression.getReturnType())) {
                dataAlreadyExists = true;
            }
        }
        return dataAlreadyExists;
    }

}
