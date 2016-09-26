/**
 * Copyright (C) 2011 BonitaSoft S.A.
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
package org.bonitasoft.studio.importer.builder;

import java.io.File;
import java.util.Set;

import org.bonitasoft.engine.bpm.connector.ConnectorEvent;
import org.bonitasoft.studio.model.expression.Expression;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.emf.ecore.EAttribute;

/**
 * @author Romain Bioteau
 * @version BOS 5.6
 */
/**
 * @author Romain
 */
public interface IProcBuilder {

    /**
     * Gather all Bonita datatypes
     */
    public enum DataType {
        STRING, INTEGER, DECIMAL, BOOLEAN, DATE, XML, JAVA, ENUM, ATTACHMENT, LONG, DOUBLE
    }

    /**
     * Gather all Bonita task types
     */
    public enum TaskType {
        HUMAN, ABSTRACT, SCRIPT, SERVICE, CALL_ACTIVITY, SEND_TASK, RECIEVE_TASK
    }

    /**
     * Gather all Test time type
     */
    public enum TestTimeType {
        BEFORE, AFTER
    }

    /**
     * Gather all Bonita gateway types
     */
    public enum GatewayType {
        XOR, AND, INCLUSIVE
    }

    /**
     * Gather all Bonita event types, including boundary events
     */
    public enum EventType {
        START_MESSAGE, START_ERROR, START_TIMER, START_SIGNAL, START,
        MESSAGE_BOUNDARY, ERROR_BOUNDARY, TIMER_BOUNDARY, SIGNAL_BOUNDARY,
        INTERMEDIATE_CATCH_MESSAGE, INTERMEDIATE_CATCH_SIGNAL, INTERMEDIATE_CATCH_TIMER, INTERMEDIATE_CATCH_LINK,
        INTERMEDIATE_THROW_MESSAGE, INTERMEDIATE_THROW_SIGNAL, INTERMEDIATE_THROW_LINK,
        END, END_SIGNAL, END_MESSAGE, END_ERROR, END_TERMINATED, NON_INTERRUPTING_TIMER_BOUNDARY
    }

    /**
     * Entry point of the builder to create a Diagram.
     *
     * @param id
     * @param name
     * @param version
     * @param targetFile
     * @throws ProcBuilderException
     */
    public void createDiagram(String id, String name, String version, File targetFile) throws ProcBuilderException;

    /**
     * Add a Pool, you must have call createDiagram first
     *
     * @param name
     * @param version
     * @param location
     * @param size
     * @throws ProcBuilderException
     */
    public void addPool(String id, String name, String version, Point location, Dimension size) throws ProcBuilderException;

    /**
     * Add a Lane, the current container must be a pool
     *
     * @param id
     * @param name
     * @param size
     * @throws ProcBuilderException
     */
    public void addLane(String id, String name, Dimension size) throws ProcBuilderException;

    /**
     * Add a data
     *
     * @param id
     * @param name
     * @param defaultValue
     * @param isMultiple
     * @param datatype
     * @throws ProcBuilderException
     */
    @Deprecated
    public void addData(String id, String name, String defaultValue, boolean isMultiple, DataType datatype) throws ProcBuilderException;

    /**
     * Add a data
     *
     * @param id
     * @param name
     * @param defaultValue
     * @param isMultiple
     * @param datatype
     * @throws ProcBuilderException
     */
    public void addData(String id, String name, String defaultValueContent, String defaultValueReturnType, String defaultValueInterpreter, boolean isMultiple,
            boolean isTransient, DataType datatype, String expressionType) throws ProcBuilderException;

    /**
     * @param description
     * @throws ProcBuilderException
     */
    public void addDescription(String description) throws ProcBuilderException;

    /**
     * @param id
     * @param sourceId
     * @param targetId
     * @param isDefault
     * @param bendpoints
     * @throws ProcBuilderException
     */
    public void addSequenceFlow(String id, String sourceId, String targetId, boolean isDefault, Point sourceAnchor, Point targetAnchor, PointList bendpoints)
            throws ProcBuilderException;

    public void addSequenceFlowCondition(final String content, final String interpreter, final String expressionType);

    public void addSequenceFlowDecisionTable();

    /**
     * @param name
     * @param eventName
     * @param sourceId
     * @param targetId
     * @param sourceAnchor
     * @param targetAnchor
     * @param bendpoints
     * @throws ProcBuilderException
     */
    public void addMessageFlow(String name, String eventName, String sourceId, String targetId, Point sourceAnchor, Point targetAnchor, PointList bendpoints)
            throws ProcBuilderException;

    /**
     * @param id
     * @param name
     * @param location
     * @param size
     * @param taskType
     * @throws ProcBuilderException
     */
    public void addTask(String id, String name, Point location, Dimension size, TaskType taskType) throws ProcBuilderException;

    /**
     * @param id
     * @param name
     * @param location
     * @param size
     * @param eventType
     * @throws ProcBuilderException
     */
    public void addEvent(String id, String name, Point location, Dimension size, EventType eventType) throws ProcBuilderException;

    /**
     * @param id
     * @param name
     * @param location
     * @param size
     * @param gatewayType
     * @param displayLabel
     * @throws ProcBuilderException
     */
    public void addGateway(String id, String name, Point location, Dimension size, GatewayType gatewayType, boolean displayLabel) throws ProcBuilderException;

    public void addGateway(String id, String name, Point location, Dimension size, GatewayType gatewayType) throws ProcBuilderException;

    /**
     * @param id
     * @param name
     * @param location
     * @param size
     * @param isCollapsed
     * @throws ProcBuilderException
     */
    public void addEventSubprocess(String id, String name, Point location, Dimension size, boolean isCollapsed) throws ProcBuilderException;

    /**
     * @param text
     * @param location
     * @param size
     * @param sourceId
     * @throws ProcBuilderException
     */
    public void addAnnotation(String text, Point location, Dimension size, String sourceId) throws ProcBuilderException;

    /**
     * @param groupId
     * @throws ProcBuilderException
     */
    public void addAssignableActor(String groupId) throws ProcBuilderException;

    /**
     * @param targetProcessId
     * @param targetProcessVersion
     * @throws ProcBuilderException
     */
    public void addCallActivityTargetProcess(String targetProcessId, String targetProcessVersion) throws ProcBuilderException;

    /**
     * @param sourceData
     * @param targetData
     * @throws ProcBuilderException
     */
    public void addCallActivityInParameter(String sourceData, String targetData) throws ProcBuilderException;

    /**
     * @param sourceData
     * @param targetData
     * @throws ProcBuilderException
     */
    public void addCallActivityOutParameter(String sourceData, String targetData) throws ProcBuilderException;

    /**
     * build the proc file
     *
     * @throws ProcBuilderException
     */
    public void done() throws ProcBuilderException;

    /**
     * @throws ProcBuilderException
     */
    public void switchToParentContainer() throws ProcBuilderException;

    /**
     * Change the current to the one specified by the if it exists.Throw a ProcBuilderException otherwise
     *
     * @param stepId
     * @throws ProcBuilderException
     */
    public void setCurrentStep(String stepId) throws ProcBuilderException;

    /**
     * @param errorCode
     * @throws ProcBuilderException
     */
    public void addErrorCode(String errorCode) throws ProcBuilderException;

    /**
     * @param signalName
     * @throws ProcBuilderException
     */
    public void addSignalCode(String signalName) throws ProcBuilderException;

    /**
     * @param condition
     * @throws ProcBuilderException
     */
    public void addTimerEventCondition(String condition) throws ProcBuilderException;

    /**
     * @param targetId
     * @throws ProcBuilderException
     */
    public void addThrowLinkEventTarget(String targetId) throws ProcBuilderException;

    /**
     * @param id
     * @param name
     * @param connectorId
     * @param event
     * @param ignoreErrors
     * @throws ProcBuilderException
     */
    public void addConnector(String id, String name, String connectorId, String version, ConnectorEvent event, boolean ignoreErrors)
            throws ProcBuilderException;

    @Deprecated
    public void addConnectorParameter(final String parameterKey, final String valueContent) throws ProcBuilderException;

    /**
     * @param key
     * @param value
     * @throws ProcBuilderException
     */
    public void addConnectorParameter(final String parameterKey, final String defaultValueContent, final String defaultValueReturnType,
            final String defaultValueInterpreter, String expressionType) throws ProcBuilderException;

    /**
     * @param targetDataId
     * @param valueContent
     * @param valueReturnType
     * @param valueInterpreter
     * @param expressionType
     * @throws ProcBuilderException
     */
    public void addConnectorOutput(final String targetDataId, final String valueContent, final String valueReturnType, final String valueInterpreter,
            String expressionType) throws ProcBuilderException;

    /**
     * @param id
     * @param name
     * @param connectorId
     * @param event
     * @param ignoreErrors
     * @throws ProcBuilderException
     */
    public void addFilter(String id, String name, String connectorId, boolean ignoreErrors) throws ProcBuilderException;

    /**
     * @param isSequential
     * @throws ProcBuilderException
     */
    public void addMultiInstantiation(boolean isSequential) throws ProcBuilderException;

    /**
     * @param id
     * @param name
     * @param literals
     * @throws ProcBuilderException
     */
    public void addEnumType(String id, String name, Set<String> literals) throws ProcBuilderException;

    /**
     * @param id
     * @param name
     * @param initialValue
     * @param isMultiple
     * @param dataTypeId
     * @throws ProcBuilderException
     */
    public void addEnumData(String id, String name, String initialValue, boolean isMultiple, String dataTypeId) throws ProcBuilderException;

    /**
     * @param id
     * @param newSize
     * @throws ProcBuilderException
     */
    public void updateSize(String id, Dimension newSize) throws ProcBuilderException;

    /**
     * @param name
     * @param documentation
     */
    public void addActor(String name, String documentation);


    /**
     * @param loopCondition
     * @param maxLoopExpression
     * @param testTime
     * @throws ProcBuilderException
     */
    public void addLoopCondition(Expression loopConditionExpression, String maxLoopExpression, TestTimeType testTime) throws ProcBuilderException;

    /**
     * Generic helper to set an EMF attribute on the current step
     * Hint : you should use the ProcessPackage.eInstance to retrieve the available EAttributes. To do so
     * you will need to add org.bontisaoft.studio.model in your dependencies
     *
     * @param emfModelAttribute
     * @param value
     */
    public void setAttributeOnCurrentStep(EAttribute emfModelAttribute, Object value);

    /**
     * Generic helper to set an EMF attribute on the current container
     * Hint : you should use the ProcessPackage.eInstance to retrieve the available EAttributes. To do so
     * you will need to add org.bontisaoft.studio.model in your dependencies
     *
     * @param emfModelAttribute
     * @param value
     */
    public void setAttributeOnCurrentContainer(EAttribute emfModelAttribute, Object value);

    public void addXMLData(String id, String name, String defaultValueContent, String defaultValueReturnType, String defaultValueInterpreter, String type,
            String nameSpace, boolean isMultiple, boolean isTransient, String expressionType) throws ProcBuilderException;

    public void addJavaData(String id, String name, String defaultValueContent, String defaultValueReturnType, String defaultValueInterpreter,
            String javaQualifiedName, boolean isMultiple, boolean isTransient, String expressionType) throws ProcBuilderException;

    public void setFontStyle(String name, int height, boolean isBold, boolean isItalic) throws ProcBuilderException;

    /**
     * @param location
     * @throws ProcBuilderException
     */
    void setLabelPositionOnSequenceFlowOrEvent(Point location) throws ProcBuilderException;

    public void addCompletionConditionExpression(Expression completionCondition) throws ProcBuilderException;

    public void addCardinalityExpression(Expression cardinalityExpression) throws ProcBuilderException;
}
