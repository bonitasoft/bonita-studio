/**
 * Copyright (C) 2009 BonitaSoft S.A.
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
package org.bonitasoft.studio.importer.bonita4;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bonitasoft.engine.bpm.connector.ConnectorEvent;
import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.NamingUtils;
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
import org.bonitasoft.studio.model.process.MainProcess;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap.Entry;
import org.eclipse.emf.ecore.xml.type.AnyType;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.wfmc._2002.xpdl1.ActivityType;
import org.wfmc._2002.xpdl1.ConditionType;
import org.wfmc._2002.xpdl1.DataFieldType;
import org.wfmc._2002.xpdl1.DocumentRoot;
import org.wfmc._2002.xpdl1.EnumerationValueType;
import org.wfmc._2002.xpdl1.ExtendedAttributeType;
import org.wfmc._2002.xpdl1.ExtendedAttributesType;
import org.wfmc._2002.xpdl1.PackageType;
import org.wfmc._2002.xpdl1.ParticipantType;
import org.wfmc._2002.xpdl1.TransitionType;
import org.wfmc._2002.xpdl1.TypeType;
import org.wfmc._2002.xpdl1.TypeType1;
import org.wfmc._2002.xpdl1.TypeType3;
import org.wfmc._2002.xpdl1.WorkflowProcessType;
import org.wfmc._2002.xpdl1.util.Xpdl1ResourceFactoryImpl;

/**
 * @author Romain Bioteau
 * @author Mickael Istria
 */
public class XPDLToProc extends ToProcProcessor {

    private static final String X_OFFSET = "XOffset"; //$NON-NLS-1$
    private static final String Y_OFFSET = "YOffset"; //$NON-NLS-1$
    private Dimension poolSize;
    private IProcBuilder builder;
    private File result;

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.importer.bonita4.ToProcProcessor#createDiagram(org.eclipse.emf.common.util.URI, org.eclipse.emf.common.util.URI,
     * org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    public File createDiagram(URL sourceURL, IProgressMonitor progressMonitor) throws IOException {

        try {
            progressMonitor.beginTask(Messages.importFromXPDL, 3);
            builder = new ProcBuilder(progressMonitor);
            final ResourceSet resourceSet = new ResourceSetImpl();
            resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xpdl", new Xpdl1ResourceFactoryImpl());
            final URI sourceURI = URI.createFileURI(new File(URI.decode(sourceURL.getFile())).getAbsolutePath());
            final Resource resource = resourceSet.getResource(sourceURI, true);
            final DocumentRoot docRoot = (DocumentRoot) resource.getContents().get(0);
            final PackageType xpdlPackage = docRoot.getPackage();
            final String diagramName = sourceURI.lastSegment();

            String version = "1.0";
            if (xpdlPackage.getRedefinableHeader() != null && xpdlPackage.getRedefinableHeader().getVersion() != null) {
                version = xpdlPackage.getRedefinableHeader().getVersion();
            }

            result = File.createTempFile(diagramName, ".proc");
            result.deleteOnExit();
            builder.createDiagram(diagramName, xpdlPackage.getName(), version, result);

            importFromXPDL(xpdlPackage);
            builder.done();
            return result;
        } catch (final ProcBuilderException e) {
            BonitaStudioLog.error(e);
        }
        return null;
    }

    /**
     * @param xpdlPackage
     * @param model
     * @param diagram
     * @param monitor
     * @throws ProcBuilderException
     */
    protected void importFromXPDL(PackageType xpdlPackage) throws ProcBuilderException {

        Point location = new Point(5, 5);
        if (xpdlPackage.getWorkflowProcesses() != null) {
            for (final WorkflowProcessType process : xpdlPackage.getWorkflowProcesses().getWorkflowProcess()) {

                createPool(process, location); //ADD POOL
                location = new Point(location.getCopy().translate(100, 0));

                processDataFields(process, null); //ADD PROCESS DATA

                processParticipants(process);//ADD PARTICIPANTS

                processActivities(process);

                processTransitions(process);

            }
        }

    }

    /**
     * @param xpdlPackage
     * @return
     * @throws ProcBuilderException
     */
    private void createPool(WorkflowProcessType xpdlProcess, Point location) throws ProcBuilderException {
        String version = "1.0";
        if (xpdlProcess.getRedefinableHeader() != null) {
            version = xpdlProcess.getRedefinableHeader().getVersion();
        }

        if (xpdlProcess.getActivities() != null) {
            int maxRight = 1000;
            int maxBottom = 200;
            for (final ActivityType activity : xpdlProcess.getActivities().getActivity()) {
                final Point activityLocation = toLocation(activity.getExtendedAttributes());
                if (activityLocation.x > maxRight) {
                    maxRight = activityLocation.x;
                }
                if (activityLocation.y > maxBottom) {
                    maxBottom = activityLocation.y;
                }
            }
            poolSize = new Dimension(maxRight + 100, maxBottom + 100);
        } else {
            poolSize = new Dimension(1000, 200);
        }

        builder.addPool(NamingUtils.convertToId(xpdlProcess.getId()), xpdlProcess.getName(), version, location, poolSize);

    }

    /**
     * @param poolPart
     * @param process
     */
    private void processTransitions(WorkflowProcessType process) throws ProcBuilderException {
        if (process.getTransitions() != null) {
            for (final TransitionType transition : process.getTransitions().getTransition()) {
                final ConditionType transitionCondition = transition.getCondition();
                String condition = "";
                if (transitionCondition != null) {
                    final FeatureMap mixed = transitionCondition.getMixed();
                    if (mixed.size() != 0) {
                        condition = mixed.get(0).getValue().toString();
                    }
                }
                builder.addSequenceFlow(transition.getName(), transition.getFrom(), transition.getTo(), false, null, null,
                        new PointList());
                if (!condition.isEmpty()) {
                    builder.addSequenceFlowCondition(condition, ExpressionConstants.GROOVY, ExpressionConstants.SCRIPT_TYPE);
                }
                builder.addDescription(transition.getDescription());
            }
        }
    }

    /**
     * @param poolPart
     * @param process
     * @throws ProcBuilderException
     */
    private void processActivities(WorkflowProcessType process) throws ProcBuilderException {
        try {
            if (process.getActivities() != null) {
                for (final ActivityType activity : process.getActivities().getActivity()) {
                    final Point location = toLocation(activity.getExtendedAttributes());

                    final ExtendedAttributesType extendedAttributes = activity.getExtendedAttributes();
                    TaskType taskType = TaskType.ABSTRACT;
                    GatewayType gatewayType = GatewayType.AND;
                    EventType eventType = EventType.START;
                    boolean isEvent = false;
                    boolean isGateway = false;
                    final String id = NamingUtils.convertToId(activity.getId());
                    final String label = activity.getName() != null ? activity.getName() : activity.getId();
                    if (activity.getRoute() != null) {
                        TypeType joinType = TypeType.AND;
                        TypeType splitType = TypeType.AND;
                        try {
                            joinType = activity.getTransitionRestrictions().getTransitionRestriction().get(0).getJoin()
                                    .getType();
                        } catch (final Exception ex) {
                            // NOTHING
                        }
                        try {
                            splitType = activity.getTransitionRestrictions().getTransitionRestriction().get(0).getJoin()
                                    .getType();
                        } catch (final Exception ex) {
                            ex.printStackTrace();
                        }

                        if (joinType.equals(TypeType.XOR) || splitType.equals(TypeType.XOR)) {
                            gatewayType = GatewayType.XOR;
                        }

                        builder.addGateway(id, label, location, null, gatewayType);
                        builder.addDescription(activity.getDescription());
                        isGateway = true;
                    } else if (activity.getId().equals("BonitaEnd")) {
                        eventType = EventType.END_TERMINATED;
                        isEvent = true;
                    } else if (activity.getId().equals("BonitaStart")) {
                        eventType = EventType.START;
                        isEvent = true;
                    } else if (activity.getImplementation() != null && activity.getImplementation().getSubFlow() != null) {
                        taskType = TaskType.CALL_ACTIVITY;
                    } else if (activity.getStartMode() != null && activity.getStartMode().getManual() != null) {
                        taskType = TaskType.HUMAN;
                    }

                    if (isEvent) {
                        builder.addEvent(id, label, location, null, eventType);
                        builder.addDescription(activity.getDescription());
                    } else if (!isGateway) { //isTask
                        builder.addTask(id, label, location, null, taskType);
                        builder.addDescription(activity.getDescription());
                        if (extendedAttributes != null) {
                            //Add Data
                            for (final ExtendedAttributeType att : getExtendedAttribute(
                                    extendedAttributes.getExtendedAttribute(), "property")) {
                                processDataFields(process, att.getValue());
                            }
                        }

                        if (taskType == TaskType.HUMAN) {
                            builder.addAssignableActor(activity.getPerformer());
                        } else if (taskType == TaskType.CALL_ACTIVITY) {
                            builder.addCallActivityTargetProcess(activity.getImplementation().getSubFlow().getId(), "1.0");
                            if (activity.getImplementation().getSubFlow().getActualParameters() != null) {
                                for (final String param : activity.getImplementation().getSubFlow().getActualParameters()
                                        .getActualParameter()) {
                                    builder.addCallActivityOutParameter(null, param);
                                    builder.addCallActivityInParameter(param, null);
                                }
                            }
                        }
                    }

                    //Add hook
                    if (extendedAttributes != null) {
                        final List<ExtendedAttributeType> hooks = getExtendedAttribute(
                                extendedAttributes.getExtendedAttribute(), "hook");
                        for (final ExtendedAttributeType hook : hooks) {
                            final ConnectorEvent event = getEvent(hook);
                            final String hookClass = hook.getValue();
                            builder.addConnector(hookClass, hookClass, "HookProcessConnector", "1.0", event, false);
                            builder.addConnectorParameter("setClassName", hookClass);
                        }
                    }

                    // Performer assign (== filters)
                    if (extendedAttributes != null) {
                        final List<ExtendedAttributeType> performerAssigns = getExtendedAttribute(
                                extendedAttributes.getExtendedAttribute(), "PerformerAssign");
                        for (final ExtendedAttributeType performerAssign : performerAssigns) {
                            final String performerAssignValue = getPerformerAssignValue(performerAssign);
                            if (performerAssign.getValue().equals("variable")) {
                                builder.addFilter(performerAssignValue, performerAssignValue,
                                        "VariablePerformerAssignFilter", false);
                                builder.addConnectorParameter("setVariableName", performerAssignValue);
                            } else {
                                builder.addFilter(performerAssignValue, performerAssignValue, "PerformerAssignFilter",
                                        false);
                                builder.addConnectorParameter("setClassName", performerAssignValue);
                            }

                        }
                    }

                    // Multi Instantiation
                    final List<ExtendedAttributeType> multis = getExtendedAttribute(
                            extendedAttributes.getExtendedAttribute(), "MultiInstantiation");
                    if (multis != null && multis.size() > 0) {

                        builder.addMultiInstantiation(false);
                        String instantiatorName = null;
                        String instantiatorParamKey = null;
                        String instantiatorParamValue = null;

                        String joinCheckerName = null;
                        String joinCheckerParamKey = null;
                        String joinCheckerParamValue = null;

                        for (final Object item : multis.get(0).getMixed()) {
                            final Entry entry = (Entry) item;
                            final String name = entry.getEStructuralFeature().getName();
                            if (name.equals("MultiInstantiator")) {
                                {
                                    final String value = (String) ((AnyType) entry.getValue()).getMixed().getValue(0);
                                    instantiatorName = value;
                                    instantiatorParamKey = "setClassName";
                                    instantiatorParamValue = value;
                                }
                                {
                                    final String value = (String) ((AnyType) entry.getValue()).getMixed().getValue(0);
                                    joinCheckerName = value;
                                    joinCheckerParamKey = "setClassName";
                                    joinCheckerParamValue = value;
                                }
                            } else if (name.equals("Variable")) {
                                final String value = (String) ((AnyType) entry.getValue()).getMixed().getValue(0);
                                instantiatorName = value;
                                instantiatorParamKey = "setVariableName";
                                instantiatorParamValue = value;
                            }
                        }

                        //TODO: import multi-instance from BONITA 4

                    }

                }
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param model
     * @param process
     * @throws ProcBuilderException
     */
    private void processParticipants(WorkflowProcessType process) throws ProcBuilderException {
        if (process.getParticipants() != null) {
            for (final ParticipantType participant : process.getParticipants().getParticipant()) {

                if (participant.getParticipantType().getType().equals(TypeType1.HUMAN)) {
                    builder.addActor(participant.getName(), "");
                    builder.addDescription(participant.getDescription());
                } else if (participant.getExtendedAttributes() != null) {
                    final String mapper = getExtendedAttributeValue(
                            participant.getExtendedAttributes().getExtendedAttribute(), "Mapper");
                    if (mapper != null && mapper.equals("Instance Initiator")) {
                        builder.addActor(participant.getName(), "");
                        builder.addDescription(participant.getDescription());
                    } else if (mapper != null) {
                        builder.addActor(participant.getName(), "");
                        builder.addDescription(participant.getDescription());
                    }
                }
            }
        }
    }

    /**
     * @param model
     * @param process
     * @param dataId
     */
    private void processDataFields(WorkflowProcessType process, String dataId) {
        if (process.getDataFields() != null) {
            for (final DataFieldType dataField : process.getDataFields().getDataField()) {

                try {
                    org.bonitasoft.studio.importer.builder.IProcBuilder.DataType procDataType = null;
                    String dataTypeId = null;
                    if (dataField.getDataType().getBasicType() != null) {
                        final TypeType3 dataType = dataField.getDataType().getBasicType().getType();
                        switch (dataType) {
                            case STRING:
                                procDataType = org.bonitasoft.studio.importer.builder.IProcBuilder.DataType.STRING;
                                break;
                            case BOOLEAN:
                                procDataType = org.bonitasoft.studio.importer.builder.IProcBuilder.DataType.BOOLEAN;
                                break;
                            case INTEGER:
                                procDataType = org.bonitasoft.studio.importer.builder.IProcBuilder.DataType.INTEGER;
                                break;
                            case DATETIME:
                                procDataType = org.bonitasoft.studio.importer.builder.IProcBuilder.DataType.DATE;
                                break;
                            case FLOAT:
                                procDataType = org.bonitasoft.studio.importer.builder.IProcBuilder.DataType.DECIMAL;
                                break;
                            default:
                                procDataType = org.bonitasoft.studio.importer.builder.IProcBuilder.DataType.STRING;
                                break;
                        }

                    } else if (dataField.getDataType().getEnumerationType() != null) {
                        procDataType = org.bonitasoft.studio.importer.builder.IProcBuilder.DataType.ENUM;
                        final Set<String> literals = new HashSet<>();
                        for (final EnumerationValueType literal : dataField.getDataType().getEnumerationType()
                                .getEnumerationValue()) {
                            literals.add(literal.getName());
                        }
                        builder.addEnumType(NamingUtils.convertToId(dataField.getName()), dataField.getName(), literals);
                        procDataType = org.bonitasoft.studio.importer.builder.IProcBuilder.DataType.ENUM;
                        dataTypeId = NamingUtils.convertToId(dataField.getName());
                    }

                    if (dataId == null && (dataField.getExtendedAttributes() == null
                            || getExtendedAttribute(dataField.getExtendedAttributes().getExtendedAttribute(),
                                    "PropertyActivity").size() == 0)) {
                        if (procDataType == DataType.ENUM) {
                            builder.addEnumData(NamingUtils.convertToId(dataField.getId()), dataField.getName(),
                                    dataField.getInitialValue(), false, dataTypeId);
                        } else {
                            builder.addData(NamingUtils.convertToId(dataField.getId()), dataField.getName(),
                                    dataField.getInitialValue(), false, procDataType);
                        }

                    } else if (dataField.getId().equals(dataId)) {
                        if (procDataType == DataType.ENUM) {
                            builder.addEnumData(NamingUtils.convertToId(dataField.getId()), dataField.getName(),
                                    dataField.getInitialValue(), false, dataTypeId);
                        } else {
                            builder.addData(NamingUtils.convertToId(dataField.getId()), dataField.getName(),
                                    dataField.getInitialValue(), false, procDataType);
                        }
                    }
                } catch (final Exception ex) {
                    BonitaStudioLog.error(ex);
                }

            }
        }
    }

    /**
     * @param extendedAttribute
     * @param string
     * @return
     */
    private String getExtendedAttributeValue(EList<ExtendedAttributeType> extendedAttribute, String string) {
        final List<ExtendedAttributeType> ext = getExtendedAttribute(extendedAttribute, string);
        if (ext.size() == 0) {
            return null;
        }
        final ExtendedAttributeType att = ext.get(0);
        if (att != null) {
            return att.getValue();
        }
        return null;
    }

    /**
     * @param extendedAttribute
     * @param string
     * @return
     */
    private List<ExtendedAttributeType> getExtendedAttribute(EList<ExtendedAttributeType> extendedAttribute, String string) {
        final List<ExtendedAttributeType> res = new ArrayList<>();
        for (final ExtendedAttributeType attribute : extendedAttribute) {
            if (attribute.getName().equals(string)) {
                res.add(attribute);
            }
        }
        return res;
    }

    /**
     * @param performerAssign
     * @return
     */
    private String getPerformerAssignValue(ExtendedAttributeType performerAssign) {
        for (final FeatureMap.Entry entry : performerAssign.getMixed()) {
            if (entry.getValue() instanceof AnyType) {
                final AnyType any = (AnyType) entry.getValue();
                final String tag = entry.getEStructuralFeature().getName();
                if (tag.toLowerCase().equals("callback") || tag.toLowerCase().equals("custom")) {
                    return (String) any.getMixed().get(0).getValue();
                }
            }
        }
        return null;
    }

    /**
     * @param hook
     * @return
     */
    private ConnectorEvent getEvent(ExtendedAttributeType hook) {
        for (final FeatureMap.Entry entry : hook.getMixed()) {
            if (entry.getValue() instanceof AnyType) {
                final AnyType any = (AnyType) entry.getValue();
                final String tag = entry.getEStructuralFeature().getName();
                if (tag.equals("HookEventName")) {
                    final String hookEventName = (String) any.getMixed().get(0).getValue();
                    if (hookEventName.equals("task:onReady")) {
                        return ConnectorEvent.ON_ENTER;
                    } else if (hookEventName.equals("task:onStart")) {
                        return ConnectorEvent.ON_ENTER;
                    } else if (hookEventName.equals("task:onFinish")) {
                        return ConnectorEvent.ON_FINISH;
                    } else if (hookEventName.equals("task:onSuspend")) {
                        return ConnectorEvent.ON_ENTER;
                    } else if (hookEventName.equals("task:onResume")) {
                        return ConnectorEvent.ON_ENTER;
                    } else if (hookEventName.equals("task:onCancel")) {
                        return ConnectorEvent.ON_ENTER;
                    } else if (hookEventName.equals("automatic:onEnter")) {
                        return ConnectorEvent.ON_ENTER;
                    }
                }
            }
        }
        return null;
    }

    /**
     * @param extendedAttributes
     * @return
     */
    private Point toLocation(ExtendedAttributesType extendedAttributes) {
        final Point location = new Point();
        if (extendedAttributes != null) {
            for (final ExtendedAttributeType att : extendedAttributes.getExtendedAttribute()) {
                if (att.getName().equals(X_OFFSET)) {
                    location.x = Integer.parseInt(att.getValue()) + 60;
                }
                if (att.getName().equals(Y_OFFSET)) {
                    location.y = Integer.parseInt(att.getValue());
                }
            }
        }
        return location;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.importer.ToProcProcessor#arrangeGraph(org.bonitasoft.studio.model.process.MainProcess,
     * org.eclipse.gmf.runtime.notation.Diagram)
     */
    public void arrangeGraph(MainProcess modelProcess2, Diagram diagram) {
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.importer.ToProcProcessor#getExtension()
     */
    @Override
    public String getExtension() {
        return "xpdl";
    }

    @Override
    public List<File> getResources() {
        return Collections.singletonList(result);
    }
}
