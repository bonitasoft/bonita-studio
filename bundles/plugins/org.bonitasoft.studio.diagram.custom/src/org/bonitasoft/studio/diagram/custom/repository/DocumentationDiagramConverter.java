/**
 * Copyright (C) 2019 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.diagram.custom.repository;

import java.util.Comparator;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Stream;

import org.apache.maven.artifact.versioning.DefaultArtifactVersion;
import org.bonitasoft.asciidoc.templating.model.process.Actor;
import org.bonitasoft.asciidoc.templating.model.process.ActorFilter;
import org.bonitasoft.asciidoc.templating.model.process.Connector;
import org.bonitasoft.asciidoc.templating.model.process.Data;
import org.bonitasoft.asciidoc.templating.model.process.DecisionTable;
import org.bonitasoft.asciidoc.templating.model.process.Diagram;
import org.bonitasoft.asciidoc.templating.model.process.Document;
import org.bonitasoft.asciidoc.templating.model.process.FlowElement;
import org.bonitasoft.asciidoc.templating.model.process.FlowElement.FlowElementBuilder;
import org.bonitasoft.asciidoc.templating.model.process.Lane;
import org.bonitasoft.asciidoc.templating.model.process.Parameter;
import org.bonitasoft.asciidoc.templating.model.process.Process;
import org.bonitasoft.asciidoc.templating.model.process.SequenceFlow;
import org.bonitasoft.engine.bpm.connector.ConnectorEvent;
import org.bonitasoft.studio.common.DataUtil;
import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.repository.model.ILocalizedResourceProvider;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.CallActivity;
import org.bonitasoft.studio.model.process.ConnectableElement;
import org.bonitasoft.studio.model.process.Connection;
import org.bonitasoft.studio.model.process.DocumentType;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.SequenceFlowConditionType;
import org.bonitasoft.studio.model.process.decision.DecisionTableLine;
import org.bonitasoft.studio.model.process.decision.transitions.TakeTransitionAction;
import org.eclipse.core.resources.IFolder;
import org.eclipse.emf.common.util.EList;
import org.eclipse.swt.widgets.Display;

public class DocumentationDiagramConverter implements Function<MainProcess, Diagram> {

    private IFolder targetFolder;
    private ILocalizedResourceProvider localizedResourceProvider;
    private DiagramRepositoryStore store;

    public DocumentationDiagramConverter(IFolder targetFolder, ILocalizedResourceProvider localizedResourceProvider, DiagramRepositoryStore store) {
        this.targetFolder = targetFolder;
        this.localizedResourceProvider = localizedResourceProvider;
        this.store = store;
    }

    @Override
    public Diagram apply(MainProcess mainProcess) {
        GenerateDiagramImagesJob generateDiagramImagesJob = new GenerateDiagramImagesJob(Display.getDefault(),
                mainProcess, targetFolder);
        generateDiagramImagesJob.setUser(false);
        generateDiagramImagesJob.schedule();
        return Diagram.builder()
                .name(mainProcess.getName())
                .version(mainProcess.getVersion())
                .description(thisOrEmpty(mainProcess.getDocumentation()))
                .processes(convertProcesses(mainProcess))
                .build();
    }

    private static String thisOrEmpty(String value) {
        return value == null ? "" : value;
    }

    private Process[] convertProcesses(MainProcess mainProcess) {
        return ModelHelper.getAllElementOfTypeIn(mainProcess, Pool.class).stream()
                .map(pool -> createProcess(pool))
                .toArray(Process[]::new);
    }

    private Process createProcess(Pool pool) {
        return Process.builder()
                .name(pool.getName())
                .displayName(pool.getDisplayName())
                .version(pool.getVersion())
                .description(thisOrEmpty(pool.getDocumentation()))
                .parameters(convertParameters(pool))
                .documents(convertDocuments(pool))
                .globalVariables(convertVariables(pool))
                .actors(convertActors(pool))
                .lanes(convertLanes(pool))
                .flowElements(convertFlowElements(pool))
                .connectorsIn(convertConnectors(pool.getConnectors().stream()
                        .filter(c -> Objects.equals(ConnectorEvent.ON_ENTER.name(), c.getEvent()))))
                .connectorsOut(convertConnectors(pool.getConnectors().stream()
                        .filter(c -> Objects.equals(ConnectorEvent.ON_FINISH.name(), c.getEvent()))))
                .build();
    }

    private FlowElement[] convertFlowElements(Pool pool) {
        return ModelHelper.getAllElementOfTypeIn(pool, org.bonitasoft.studio.model.process.FlowElement.class).stream()
                .map(this::createFlowElement)
                .toArray(FlowElement[]::new);
    }

    private Lane[] convertLanes(Pool pool) {
        return ModelHelper.getAllElementOfTypeIn(pool, org.bonitasoft.studio.model.process.Lane.class).stream()
                .map(this::createLane)
                .toArray(Lane[]::new);
    }

    private Actor[] convertActors(Pool pool) {
        return pool.getActors().stream()
                .map(this::createActor)
                .toArray(Actor[]::new);
    }

    private Data[] convertVariables(Pool pool) {
        return pool.getData().stream()
                .map(this::createData)
                .toArray(Data[]::new);
    }

    private Document[] convertDocuments(Pool pool) {
        return pool.getDocuments().stream()
                .map(this::createDocument)
                .toArray(Document[]::new);
    }

    private Parameter[] convertParameters(Pool pool) {
        return pool.getParameters().stream()
                .map(this::createParameter)
                .toArray(Parameter[]::new);
    }

    private FlowElement createFlowElement(org.bonitasoft.studio.model.process.FlowElement flowElement) {
        FlowElementBuilder builder = FlowElement.builder()
                .name(flowElement.getName())
                .description(thisOrEmpty(flowElement.getDocumentation()))
                .bpmnType(flowElement.eClass().getName())
                .incomings(convertSequenceFlows(flowElement.getIncoming()))
                .outgoings(convertSequenceFlows(flowElement.getOutgoing()))
                .process(ModelHelper.getParentPool(flowElement).getName())
                .lane(ModelHelper.getParentLane(flowElement) != null ? ModelHelper.getParentLane(flowElement).getName()
                        : null)
                .connectorsIn(flowElement instanceof ConnectableElement
                        ? convertConnectors(((ConnectableElement) flowElement).getConnectors().stream()
                                .filter(c -> Objects.equals(ConnectorEvent.ON_ENTER.name(), c.getEvent())))
                        : new Connector[0])
                .connectorsOut(flowElement instanceof ConnectableElement
                        ? convertConnectors(((ConnectableElement) flowElement).getConnectors().stream()
                                .filter(c -> Objects.equals(ConnectorEvent.ON_FINISH.name(), c.getEvent())))
                        : new Connector[0]);

        if (flowElement instanceof CallActivity) {
            builder
                .calledProcessName(createExpression(((CallActivity) flowElement).getCalledActivityName()))
                .calledProcessVersion(createCalledActivityVersionExpression(((CallActivity) flowElement)));
        }
        return builder.build();
    }

    private org.bonitasoft.asciidoc.templating.model.process.Expression createCalledActivityVersionExpression(CallActivity callActivity){
        if(callActivity.getCalledActivityName().hasContent()
                && Objects.equals(callActivity.getCalledActivityName().getType(),ExpressionConstants.CONSTANT_TYPE) 
                && (callActivity.getCalledActivityVersion() == null || !callActivity.getCalledActivityVersion().hasContent())) {
            String latestVersion = store.findProcesses(callActivity.getCalledActivityName().getContent()).stream()
                    .map(AbstractProcess::getVersion)
                    .sorted(latestVersionComparator())
                    .findFirst()
                    .orElse(null);
            if(latestVersion != null) {
                return org.bonitasoft.asciidoc.templating.model.process.Expression.builder()
                        .name(latestVersion)
                        .content(latestVersion)
                        .type(ExpressionConstants.CONSTANT_TYPE)
                        .build();
            }
        }
        return createExpression(callActivity.getCalledActivityVersion());
    }

    private Comparator<? super String> latestVersionComparator() {
        return (v1,v2)->  new DefaultArtifactVersion(v2).compareTo(new DefaultArtifactVersion(v1));
    }

    private Connector[] convertConnectors(Stream<org.bonitasoft.studio.model.process.Connector> connectors) {
        return connectors
                .map(this::createConnector)
                .toArray(Connector[]::new);
    }

    private Connector createConnector(org.bonitasoft.studio.model.process.Connector connector) {
        return Connector.builder()
                .name(connector.getName())
                .description(thisOrEmpty(connector.getDocumentation()))
                .definitionName(localizedResourceProvider.getConnectorDefinitionName(connector.getDefinitionId(),
                        connector.getDefinitionVersion()))
                .definitionId(connector.getDefinitionId())
                .definitionVersion(connector.getDefinitionVersion())
                .build();
    }

    private SequenceFlow[] convertSequenceFlows(EList<Connection> incomingsConnection) {
        return incomingsConnection.stream()
                .filter(org.bonitasoft.studio.model.process.SequenceFlow.class::isInstance)
                .map(org.bonitasoft.studio.model.process.SequenceFlow.class::cast)
                .map(this::createSequenceFlow)
                .toArray(SequenceFlow[]::new);
    }

    private SequenceFlow createSequenceFlow(org.bonitasoft.studio.model.process.SequenceFlow sequenceFlow) {
        return SequenceFlow.builder()
                .name(sequenceFlow.getName())
                .description(thisOrEmpty(sequenceFlow.getDocumentation()))
                .defaultFlow(sequenceFlow.isIsDefault())
                .source(sequenceFlow.getSource().getName())
                .target(sequenceFlow.getTarget().getName())
                .condition(createExpression(sequenceFlow.getCondition()))
                .useDecisionTable(sequenceFlow.getConditionType() == SequenceFlowConditionType.DECISION_TABLE)
                .decisionTable(convertDecisionTable(sequenceFlow.getDecisionTable()))
                .build();
    }

    private DecisionTable convertDecisionTable(
            org.bonitasoft.studio.model.process.decision.DecisionTable decisionTable) {
        return DecisionTable.builder()
                .lines(convertDecisionTableLines(decisionTable.getLines()))
                .defaultTakeTransition(decisionTable.getDefaultAction() instanceof TakeTransitionAction
                        && ((TakeTransitionAction) decisionTable.getDefaultAction()).isTakeTransition())
                .build();
    }

    private org.bonitasoft.asciidoc.templating.model.process.DecisionTableLine[] convertDecisionTableLines(
            EList<DecisionTableLine> lines) {
        return lines.stream()
                .map(this::createDecisionTableLine)
                .toArray(org.bonitasoft.asciidoc.templating.model.process.DecisionTableLine[]::new);
    }

    private org.bonitasoft.asciidoc.templating.model.process.DecisionTableLine createDecisionTableLine(
            DecisionTableLine line) {
        return org.bonitasoft.asciidoc.templating.model.process.DecisionTableLine.builder()
                .conditions(convertConditions(line.getConditions()))
                .takeTransition(line.getAction() instanceof TakeTransitionAction
                        && ((TakeTransitionAction) line.getAction()).isTakeTransition())
                .build();
    }

    private org.bonitasoft.asciidoc.templating.model.process.Expression[] convertConditions(
            EList<Expression> conditions) {
        return conditions.stream()
                .map(this::createExpression)
                .toArray(org.bonitasoft.asciidoc.templating.model.process.Expression[]::new);
    }

    private Lane createLane(org.bonitasoft.studio.model.process.Lane lane) {
        return Lane.builder()
                .name(lane.getName())
                .description(thisOrEmpty(lane.getDocumentation()))
                .actor(lane.getActor() != null ? lane.getActor().getName() : "")
                .actorFilter(!lane.getFilters().isEmpty() ? createActorFilter(lane.getFilters().get(0)) : null)
                .process(ModelHelper.getParentPool(lane).getName())
                .build();
    }

    private ActorFilter createActorFilter(org.bonitasoft.studio.model.process.ActorFilter actorFilter) {
        return ActorFilter.builder()
                .name(actorFilter.getName())
                .description(actorFilter.getDocumentation())
                .definitionName(localizedResourceProvider.getActorFilterDefinitionName(actorFilter.getDefinitionId(),
                        actorFilter.getDefinitionVersion()))
                .definitionId(actorFilter.getDefinitionId())
                .definitionVersion(actorFilter.getDefinitionVersion())
                .build();
    }

    private Actor createActor(org.bonitasoft.studio.model.process.Actor actor) {
        return Actor.builder()
                .name(actor.getName())
                .description(thisOrEmpty(actor.getDocumentation()))
                .initiator(actor.isInitiator())
                .build();
    }

    private Parameter createParameter(org.bonitasoft.studio.model.parameter.Parameter parameter) {
        return Parameter.builder()
                .name(parameter.getName())
                .type(NamingUtils.getSimpleName(parameter.getTypeClassname()))
                .description(thisOrEmpty(parameter.getDescription()))
                .build();
    }

    private Document createDocument(org.bonitasoft.studio.model.process.Document document) {
        return Document.builder()
                .name(document.getName())
                .multiple(document.isMultiple())
                .description(thisOrEmpty(document.getDocumentation()))
                .initialValue(getDocumentInitialValue(document))
                .mimeType(document.getMimeType() != null && document.getMimeType().hasContent()
                        ? document.getMimeType().getContent() : "")
                .build();
    }

    private Data createData(org.bonitasoft.studio.model.process.Data data) {
        return Data.builder()
                .name(data.getName())
                .type(DataUtil.getDisplayTypeName(data))
                .description(thisOrEmpty(data.getDocumentation()))
                .defaultValue(createExpression(data.getDefaultValue()))
                .build();
    }

    private org.bonitasoft.asciidoc.templating.model.process.Expression createExpression(Expression expression) {
        return org.bonitasoft.asciidoc.templating.model.process.Expression.builder()
                .name(expression.getName())
                .content(expression.getContent())
                .returnType(NamingUtils.getSimpleName(expression.getReturnType()))
                .type(expression.getType())
                .build();
    }

    private String getDocumentInitialValue(org.bonitasoft.studio.model.process.Document document) {
        if (document.isMultiple() && document.getInitialMultipleContent().hasContent()) {
            return document.getInitialMultipleContent().getContent();
        } else if (document.getDocumentType() == DocumentType.EXTERNAL && document.getUrl().hasContent()) {
            return document.getUrl().getContent();
        } else if (document.getDocumentType() == DocumentType.INTERNAL) {
            return document.getDefaultValueIdOfDocumentStore();
        } else if (document.getDocumentType() == DocumentType.CONTRACT && document.getContractInput() != null) {
            return document.getContractInput().getName();
        }
        return "";
    }

}
