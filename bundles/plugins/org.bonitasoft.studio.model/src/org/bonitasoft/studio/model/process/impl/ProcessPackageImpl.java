/**
 * Copyright (C) 2009-2015 BonitaSoft S.A.
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
package org.bonitasoft.studio.model.process.impl;

import org.bonitasoft.studio.model.actormapping.ActorMappingPackage;

import org.bonitasoft.studio.model.actormapping.impl.ActorMappingPackageImpl;

import org.bonitasoft.studio.model.configuration.ConfigurationPackage;

import org.bonitasoft.studio.model.configuration.impl.ConfigurationPackageImpl;

import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfigurationPackage;

import org.bonitasoft.studio.model.connectorconfiguration.impl.ConnectorConfigurationPackageImpl;

import org.bonitasoft.studio.model.expression.ExpressionPackage;

import org.bonitasoft.studio.model.expression.impl.ExpressionPackageImpl;

import org.bonitasoft.studio.model.form.FormPackage;

import org.bonitasoft.studio.model.form.impl.FormPackageImpl;

import org.bonitasoft.studio.model.kpi.KpiPackage;

import org.bonitasoft.studio.model.kpi.impl.KpiPackageImpl;

import org.bonitasoft.studio.model.parameter.ParameterPackage;

import org.bonitasoft.studio.model.parameter.impl.ParameterPackageImpl;

import org.bonitasoft.studio.model.process.ANDGateway;
import org.bonitasoft.studio.model.process.AbstractCatchMessageEvent;
import org.bonitasoft.studio.model.process.AbstractPageFlow;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.AbstractTimerEvent;
import org.bonitasoft.studio.model.process.Activity;
import org.bonitasoft.studio.model.process.Actor;
import org.bonitasoft.studio.model.process.ActorFilter;
import org.bonitasoft.studio.model.process.AdditionalResource;
import org.bonitasoft.studio.model.process.Assignable;
import org.bonitasoft.studio.model.process.Association;
import org.bonitasoft.studio.model.process.BooleanType;
import org.bonitasoft.studio.model.process.BoundaryEvent;
import org.bonitasoft.studio.model.process.BoundaryMessageEvent;
import org.bonitasoft.studio.model.process.BoundarySignalEvent;
import org.bonitasoft.studio.model.process.BoundaryTimerEvent;
import org.bonitasoft.studio.model.process.BusinessObjectData;
import org.bonitasoft.studio.model.process.BusinessObjectType;
import org.bonitasoft.studio.model.process.CallActivity;
import org.bonitasoft.studio.model.process.CatchLinkEvent;
import org.bonitasoft.studio.model.process.CatchMessageEvent;
import org.bonitasoft.studio.model.process.CatchSignalEvent;
import org.bonitasoft.studio.model.process.ConnectableElement;
import org.bonitasoft.studio.model.process.Connection;
import org.bonitasoft.studio.model.process.Connector;
import org.bonitasoft.studio.model.process.Contract;
import org.bonitasoft.studio.model.process.ContractConstraint;
import org.bonitasoft.studio.model.process.ContractContainer;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.ContractInputMapping;
import org.bonitasoft.studio.model.process.ContractInputType;
import org.bonitasoft.studio.model.process.Correlation;
import org.bonitasoft.studio.model.process.CorrelationAssociation;
import org.bonitasoft.studio.model.process.CorrelationTypeActive;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.DataAware;
import org.bonitasoft.studio.model.process.DataType;
import org.bonitasoft.studio.model.process.DateType;
import org.bonitasoft.studio.model.process.Document;
import org.bonitasoft.studio.model.process.DocumentType;
import org.bonitasoft.studio.model.process.DoubleType;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.EndErrorEvent;
import org.bonitasoft.studio.model.process.EndEvent;
import org.bonitasoft.studio.model.process.EndMessageEvent;
import org.bonitasoft.studio.model.process.EndSignalEvent;
import org.bonitasoft.studio.model.process.EndTerminatedEvent;
import org.bonitasoft.studio.model.process.EnumType;
import org.bonitasoft.studio.model.process.ErrorEvent;
import org.bonitasoft.studio.model.process.Event;
import org.bonitasoft.studio.model.process.FloatType;
import org.bonitasoft.studio.model.process.FlowElement;
import org.bonitasoft.studio.model.process.FormMapping;
import org.bonitasoft.studio.model.process.FormMappingType;
import org.bonitasoft.studio.model.process.Gateway;
import org.bonitasoft.studio.model.process.GatewayType;
import org.bonitasoft.studio.model.process.InclusiveGateway;
import org.bonitasoft.studio.model.process.InputMapping;
import org.bonitasoft.studio.model.process.InputMappingAssignationType;
import org.bonitasoft.studio.model.process.IntegerType;
import org.bonitasoft.studio.model.process.IntermediateCatchMessageEvent;
import org.bonitasoft.studio.model.process.IntermediateCatchSignalEvent;
import org.bonitasoft.studio.model.process.IntermediateCatchTimerEvent;
import org.bonitasoft.studio.model.process.IntermediateErrorCatchEvent;
import org.bonitasoft.studio.model.process.IntermediateThrowMessageEvent;
import org.bonitasoft.studio.model.process.IntermediateThrowSignalEvent;
import org.bonitasoft.studio.model.process.JavaObjectData;
import org.bonitasoft.studio.model.process.JavaType;
import org.bonitasoft.studio.model.process.Lane;
import org.bonitasoft.studio.model.process.LinkEvent;
import org.bonitasoft.studio.model.process.LongType;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.Message;
import org.bonitasoft.studio.model.process.MessageEvent;
import org.bonitasoft.studio.model.process.MessageFlow;
import org.bonitasoft.studio.model.process.MultiInstanceType;
import org.bonitasoft.studio.model.process.MultiInstantiable;
import org.bonitasoft.studio.model.process.NonInterruptingBoundaryTimerEvent;
import org.bonitasoft.studio.model.process.OperationContainer;
import org.bonitasoft.studio.model.process.OutputMapping;
import org.bonitasoft.studio.model.process.PageFlow;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.RecapFlow;
import org.bonitasoft.studio.model.process.ReceiveTask;
import org.bonitasoft.studio.model.process.ScriptTask;
import org.bonitasoft.studio.model.process.SearchIndex;
import org.bonitasoft.studio.model.process.SendTask;
import org.bonitasoft.studio.model.process.SequenceFlow;
import org.bonitasoft.studio.model.process.SequenceFlowConditionType;
import org.bonitasoft.studio.model.process.ServiceTask;
import org.bonitasoft.studio.model.process.SignalEvent;
import org.bonitasoft.studio.model.process.SourceElement;
import org.bonitasoft.studio.model.process.StartErrorEvent;
import org.bonitasoft.studio.model.process.StartEvent;
import org.bonitasoft.studio.model.process.StartMessageEvent;
import org.bonitasoft.studio.model.process.StartSignalEvent;
import org.bonitasoft.studio.model.process.StartTimerEvent;
import org.bonitasoft.studio.model.process.StartTimerScriptType;
import org.bonitasoft.studio.model.process.StringType;
import org.bonitasoft.studio.model.process.SubProcessEvent;
import org.bonitasoft.studio.model.process.TargetElement;
import org.bonitasoft.studio.model.process.Task;
import org.bonitasoft.studio.model.process.TextAnnotation;
import org.bonitasoft.studio.model.process.TextAnnotationAttachment;
import org.bonitasoft.studio.model.process.ThrowLinkEvent;
import org.bonitasoft.studio.model.process.ThrowMessageEvent;
import org.bonitasoft.studio.model.process.ThrowSignalEvent;
import org.bonitasoft.studio.model.process.TimerEvent;
import org.bonitasoft.studio.model.process.XMLData;
import org.bonitasoft.studio.model.process.XMLType;
import org.bonitasoft.studio.model.process.XORGateway;

import org.bonitasoft.studio.model.process.decision.DecisionPackage;

import org.bonitasoft.studio.model.process.decision.impl.DecisionPackageImpl;

import org.bonitasoft.studio.model.process.decision.transitions.TransitionsPackage;

import org.bonitasoft.studio.model.process.decision.transitions.impl.TransitionsPackageImpl;

import org.bonitasoft.studio.model.simulation.SimulationPackage;

import org.bonitasoft.studio.model.simulation.impl.SimulationPackageImpl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ProcessPackageImpl extends EPackageImpl implements ProcessPackage {
	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass abstractCatchMessageEventEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass abstractPageFlowEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass abstractProcessEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass abstractTimerEventEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass activityEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass actorEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass actorFilterEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass andGatewayEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass assignableEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass associationEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass booleanTypeEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass boundaryEventEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass boundaryMessageEventEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass boundarySignalEventEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass boundaryTimerEventEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass businessObjectDataEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass businessObjectTypeEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass callActivityEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass catchLinkEventEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass catchMessageEventEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass catchSignalEventEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass connectableElementEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass connectorEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass containerEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass contractEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass contractInputMappingEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass contractInputEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass contractConstraintEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass contractContainerEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass connectionEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass correlationEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass correlationAssociationEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass dataEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass dataAwareEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass dateTypeEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass dataTypeEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass documentEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass doubleTypeEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass elementEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass eventEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass endErrorEventEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass endEventEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass endMessageEventEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass endSignalEventEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass endTerminatedEventEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass errorEventEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass enumTypeEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass floatTypeEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass flowElementEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass formMappingEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass gatewayEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass inclusiveGatewayEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass inputMappingEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass integerTypeEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass intermediateErrorCatchEventEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass intermediateCatchSignalEventEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass intermediateThrowSignalEventEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass intermediateCatchMessageEventEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass intermediateThrowMessageEventEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass intermediateCatchTimerEventEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass javaObjectDataEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass javaTypeEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass laneEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass linkEventEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass longTypeEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass mainProcessEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass messageEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass messageFlowEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass messageEventEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass multiInstantiableEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass nonInterruptingBoundaryTimerEventEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass operationContainerEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass outputMappingEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass pageFlowEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass poolEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass recapFlowEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass receiveTaskEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass sequenceFlowEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass signalEventEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass sourceElementEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass stringTypeEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass scriptTaskEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass searchIndexEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass sendTaskEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass serviceTaskEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass startErrorEventEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass startEventEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass startMessageEventEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass startSignalEventEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass startTimerEventEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass subProcessEventEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass taskEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass targetElementEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass textAnnotationEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass textAnnotationAttachmentEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass throwSignalEventEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass throwLinkEventEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass throwMessageEventEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass timerEventEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass xmlDataEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass xmlTypeEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass xorGatewayEClass = null;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass additionalResourceEClass = null;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EEnum correlationTypeActiveEEnum = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EEnum contractInputTypeEEnum = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EEnum documentTypeEEnum = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EEnum formMappingTypeEEnum = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EEnum gatewayTypeEEnum = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EEnum inputMappingAssignationTypeEEnum = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EEnum multiInstanceTypeEEnum = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EEnum sequenceFlowConditionTypeEEnum = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EEnum startTimerScriptTypeEEnum = null;

	/**
     * Creates an instance of the model <b>Package</b>, registered with
     * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
     * package URI value.
     * <p>Note: the correct way to create the package is via the static
     * factory method {@link #init init()}, which also performs
     * initialization of the package, or returns the registered package,
     * if one already exists.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.emf.ecore.EPackage.Registry
     * @see org.bonitasoft.studio.model.process.ProcessPackage#eNS_URI
     * @see #init()
     * @generated
     */
	private ProcessPackageImpl() {
        super(eNS_URI, ProcessFactory.eINSTANCE);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private static boolean isInited = false;

	/**
     * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
     *
     * <p>This method is used to initialize {@link ProcessPackage#eINSTANCE} when that field is accessed.
     * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
	public static ProcessPackage init() {
        if (isInited) return (ProcessPackage)EPackage.Registry.INSTANCE.getEPackage(ProcessPackage.eNS_URI);

        // Obtain or create and register package
        Object registeredProcessPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
        ProcessPackageImpl theProcessPackage = registeredProcessPackage instanceof ProcessPackageImpl ? (ProcessPackageImpl)registeredProcessPackage : new ProcessPackageImpl();

        isInited = true;

        // Obtain or create and register interdependencies
        Object registeredPackage = EPackage.Registry.INSTANCE.getEPackage(ActorMappingPackage.eNS_URI);
        ActorMappingPackageImpl theActorMappingPackage = (ActorMappingPackageImpl)(registeredPackage instanceof ActorMappingPackageImpl ? registeredPackage : ActorMappingPackage.eINSTANCE);
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(ConfigurationPackage.eNS_URI);
        ConfigurationPackageImpl theConfigurationPackage = (ConfigurationPackageImpl)(registeredPackage instanceof ConfigurationPackageImpl ? registeredPackage : ConfigurationPackage.eINSTANCE);
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(ConnectorConfigurationPackage.eNS_URI);
        ConnectorConfigurationPackageImpl theConnectorConfigurationPackage = (ConnectorConfigurationPackageImpl)(registeredPackage instanceof ConnectorConfigurationPackageImpl ? registeredPackage : ConnectorConfigurationPackage.eINSTANCE);
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(ExpressionPackage.eNS_URI);
        ExpressionPackageImpl theExpressionPackage = (ExpressionPackageImpl)(registeredPackage instanceof ExpressionPackageImpl ? registeredPackage : ExpressionPackage.eINSTANCE);
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(KpiPackage.eNS_URI);
        KpiPackageImpl theKpiPackage = (KpiPackageImpl)(registeredPackage instanceof KpiPackageImpl ? registeredPackage : KpiPackage.eINSTANCE);
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(ParameterPackage.eNS_URI);
        ParameterPackageImpl theParameterPackage = (ParameterPackageImpl)(registeredPackage instanceof ParameterPackageImpl ? registeredPackage : ParameterPackage.eINSTANCE);
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(DecisionPackage.eNS_URI);
        DecisionPackageImpl theDecisionPackage = (DecisionPackageImpl)(registeredPackage instanceof DecisionPackageImpl ? registeredPackage : DecisionPackage.eINSTANCE);
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(TransitionsPackage.eNS_URI);
        TransitionsPackageImpl theTransitionsPackage = (TransitionsPackageImpl)(registeredPackage instanceof TransitionsPackageImpl ? registeredPackage : TransitionsPackage.eINSTANCE);
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(FormPackage.eNS_URI);
        FormPackageImpl theFormPackage = (FormPackageImpl)(registeredPackage instanceof FormPackageImpl ? registeredPackage : FormPackage.eINSTANCE);
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(SimulationPackage.eNS_URI);
        SimulationPackageImpl theSimulationPackage = (SimulationPackageImpl)(registeredPackage instanceof SimulationPackageImpl ? registeredPackage : SimulationPackage.eINSTANCE);

        // Create package meta-data objects
        theProcessPackage.createPackageContents();
        theActorMappingPackage.createPackageContents();
        theConfigurationPackage.createPackageContents();
        theConnectorConfigurationPackage.createPackageContents();
        theExpressionPackage.createPackageContents();
        theKpiPackage.createPackageContents();
        theParameterPackage.createPackageContents();
        theDecisionPackage.createPackageContents();
        theTransitionsPackage.createPackageContents();
        theFormPackage.createPackageContents();
        theSimulationPackage.createPackageContents();

        // Initialize created meta-data
        theProcessPackage.initializePackageContents();
        theActorMappingPackage.initializePackageContents();
        theConfigurationPackage.initializePackageContents();
        theConnectorConfigurationPackage.initializePackageContents();
        theExpressionPackage.initializePackageContents();
        theKpiPackage.initializePackageContents();
        theParameterPackage.initializePackageContents();
        theDecisionPackage.initializePackageContents();
        theTransitionsPackage.initializePackageContents();
        theFormPackage.initializePackageContents();
        theSimulationPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theProcessPackage.freeze();

        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(ProcessPackage.eNS_URI, theProcessPackage);
        return theProcessPackage;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getAbstractCatchMessageEvent() {
        return abstractCatchMessageEventEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getAbstractCatchMessageEvent_Event() {
        return (EAttribute)abstractCatchMessageEventEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EReference getAbstractCatchMessageEvent_IncomingMessag() {
        return (EReference)abstractCatchMessageEventEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EReference getAbstractCatchMessageEvent_Correlation() {
        return (EReference)abstractCatchMessageEventEClass.getEStructuralFeatures().get(2);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EReference getAbstractCatchMessageEvent_MessageContent() {
        return (EReference)abstractCatchMessageEventEClass.getEStructuralFeatures().get(3);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getAbstractPageFlow() {
        return abstractPageFlowEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getAbstractProcess() {
        return abstractProcessEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getAbstractProcess_Version() {
        return (EAttribute)abstractProcessEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getAbstractProcess_Author() {
        return (EAttribute)abstractProcessEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getAbstractProcess_CreationDate() {
        return (EAttribute)abstractProcessEClass.getEStructuralFeatures().get(2);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getAbstractProcess_ModificationDate() {
        return (EAttribute)abstractProcessEClass.getEStructuralFeatures().get(3);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EReference getAbstractProcess_Datatypes() {
        return (EReference)abstractProcessEClass.getEStructuralFeatures().get(4);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EReference getAbstractProcess_Connections() {
        return (EReference)abstractProcessEClass.getEStructuralFeatures().get(5);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getAbstractProcess_Categories() {
        return (EAttribute)abstractProcessEClass.getEStructuralFeatures().get(6);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EReference getAbstractProcess_Actors() {
        return (EReference)abstractProcessEClass.getEStructuralFeatures().get(7);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EReference getAbstractProcess_Configurations() {
        return (EReference)abstractProcessEClass.getEStructuralFeatures().get(8);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EReference getAbstractProcess_Parameters() {
        return (EReference)abstractProcessEClass.getEStructuralFeatures().get(9);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getAbstractTimerEvent() {
        return abstractTimerEventEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EReference getAbstractTimerEvent_Condition() {
        return (EReference)abstractTimerEventEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getActivity() {
        return activityEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EReference getActivity_BoundaryIntermediateEvents() {
        return (EReference)activityEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getActor() {
        return actorEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getActor_Initiator() {
        return (EAttribute)actorEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getActorFilter() {
        return actorFilterEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getANDGateway() {
        return andGatewayEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getAssignable() {
        return assignableEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EReference getAssignable_Actor() {
        return (EReference)assignableEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EReference getAssignable_Filters() {
        return (EReference)assignableEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getAssociation() {
        return associationEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getAssociation_IsDirected() {
        return (EAttribute)associationEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getBooleanType() {
        return booleanTypeEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getBoundaryEvent() {
        return boundaryEventEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getBoundaryMessageEvent() {
        return boundaryMessageEventEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getBoundarySignalEvent() {
        return boundarySignalEventEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getBoundaryTimerEvent() {
        return boundaryTimerEventEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getBusinessObjectData() {
        return businessObjectDataEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getBusinessObjectData_BusinessDataRepositoryId() {
        return (EAttribute)businessObjectDataEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getBusinessObjectData_CreateNewInstance() {
        return (EAttribute)businessObjectDataEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getBusinessObjectData_EClassName() {
        return (EAttribute)businessObjectDataEClass.getEStructuralFeatures().get(2);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getBusinessObjectType() {
        return businessObjectTypeEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getCallActivity() {
        return callActivityEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EReference getCallActivity_InputMappings() {
        return (EReference)callActivityEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EReference getCallActivity_OutputMappings() {
        return (EReference)callActivityEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EReference getCallActivity_CalledActivityName() {
        return (EReference)callActivityEClass.getEStructuralFeatures().get(2);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EReference getCallActivity_CalledActivityVersion() {
        return (EReference)callActivityEClass.getEStructuralFeatures().get(3);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getCatchLinkEvent() {
        return catchLinkEventEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EReference getCatchLinkEvent_From() {
        return (EReference)catchLinkEventEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getCatchMessageEvent() {
        return catchMessageEventEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getCatchSignalEvent() {
        return catchSignalEventEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getConnectableElement() {
        return connectableElementEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EReference getConnectableElement_Connectors() {
        return (EReference)connectableElementEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EReference getConnectableElement_Kpis() {
        return (EReference)connectableElementEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getConnector() {
        return connectorEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getConnector_DefinitionId() {
        return (EAttribute)connectorEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getConnector_Event() {
        return (EAttribute)connectorEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getConnector_IgnoreErrors() {
        return (EAttribute)connectorEClass.getEStructuralFeatures().get(2);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getConnector_ThrowErrorEvent() {
        return (EAttribute)connectorEClass.getEStructuralFeatures().get(3);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getConnector_NamedError() {
        return (EAttribute)connectorEClass.getEStructuralFeatures().get(4);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getConnector_DefinitionVersion() {
        return (EAttribute)connectorEClass.getEStructuralFeatures().get(5);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EReference getConnector_Configuration() {
        return (EReference)connectorEClass.getEStructuralFeatures().get(6);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EReference getConnector_Outputs() {
        return (EReference)connectorEClass.getEStructuralFeatures().get(7);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getContainer() {
        return containerEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EReference getContainer_Elements() {
        return (EReference)containerEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getContract() {
        return contractEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EReference getContract_Inputs() {
        return (EReference)contractEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EReference getContract_Constraints() {
        return (EReference)contractEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getContractInputMapping() {
        return contractInputMappingEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EReference getContractInputMapping_Data() {
        return (EReference)contractInputMappingEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getContractInputMapping_SetterName() {
        return (EAttribute)contractInputMappingEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getContractInputMapping_SetterParamType() {
        return (EAttribute)contractInputMappingEClass.getEStructuralFeatures().get(2);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getContractInput() {
        return contractInputEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getContractInput_Name() {
        return (EAttribute)contractInputEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getContractInput_Type() {
        return (EAttribute)contractInputEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getContractInput_Description() {
        return (EAttribute)contractInputEClass.getEStructuralFeatures().get(2);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getContractInput_Multiple() {
        return (EAttribute)contractInputEClass.getEStructuralFeatures().get(3);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EReference getContractInput_Mapping() {
        return (EReference)contractInputEClass.getEStructuralFeatures().get(4);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EReference getContractInput_Inputs() {
        return (EReference)contractInputEClass.getEStructuralFeatures().get(5);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getContractInput_DataReference() {
        return (EAttribute)contractInputEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getContractInput_CreateMode() {
        return (EAttribute)contractInputEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getContractConstraint() {
        return contractConstraintEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getContractConstraint_Expression() {
        return (EAttribute)contractConstraintEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getContractConstraint_ErrorMessage() {
        return (EAttribute)contractConstraintEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getContractConstraint_Name() {
        return (EAttribute)contractConstraintEClass.getEStructuralFeatures().get(2);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getContractConstraint_InputNames() {
        return (EAttribute)contractConstraintEClass.getEStructuralFeatures().get(3);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getContractConstraint_Description() {
        return (EAttribute)contractConstraintEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getContractContainer() {
        return contractContainerEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EReference getContractContainer_Contract() {
        return (EReference)contractContainerEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getConnection() {
        return connectionEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EReference getConnection_Target() {
        return (EReference)connectionEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EReference getConnection_Source() {
        return (EReference)connectionEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getCorrelation() {
        return correlationEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getCorrelation_CorrelationType() {
        return (EAttribute)correlationEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EReference getCorrelation_CorrelationAssociation() {
        return (EReference)correlationEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getCorrelationAssociation() {
        return correlationAssociationEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EReference getCorrelationAssociation_CorrelationExpression() {
        return (EReference)correlationAssociationEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EReference getCorrelationAssociation_CorrelationKey() {
        return (EReference)correlationAssociationEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getData() {
        return dataEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getData_Generated() {
        return (EAttribute)dataEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getData_Multiple() {
        return (EAttribute)dataEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getData_Transient() {
        return (EAttribute)dataEClass.getEStructuralFeatures().get(2);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getData_DatasourceId() {
        return (EAttribute)dataEClass.getEStructuralFeatures().get(3);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EReference getData_DataType() {
        return (EReference)dataEClass.getEStructuralFeatures().get(4);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EReference getData_DefaultValue() {
        return (EReference)dataEClass.getEStructuralFeatures().get(5);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getDataAware() {
        return dataAwareEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EReference getDataAware_Data() {
        return (EReference)dataAwareEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getDateType() {
        return dateTypeEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getDataType() {
        return dataTypeEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getDocument() {
        return documentEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getDocument_DefaultValueIdOfDocumentStore() {
        return (EAttribute)documentEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EReference getDocument_MimeType() {
        return (EReference)documentEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EReference getDocument_Url() {
        return (EReference)documentEClass.getEStructuralFeatures().get(2);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getDocument_DocumentType() {
        return (EAttribute)documentEClass.getEStructuralFeatures().get(3);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EReference getDocument_InitialMultipleContent() {
        return (EReference)documentEClass.getEStructuralFeatures().get(4);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getDocument_Multiple() {
        return (EAttribute)documentEClass.getEStructuralFeatures().get(5);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EReference getDocument_ContractInput() {
        return (EReference)documentEClass.getEStructuralFeatures().get(6);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getDoubleType() {
        return doubleTypeEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getElement() {
        return elementEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getElement_Documentation() {
        return (EAttribute)elementEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getElement_Name() {
        return (EAttribute)elementEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EReference getElement_TextAnnotationAttachment() {
        return (EReference)elementEClass.getEStructuralFeatures().get(2);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getEvent() {
        return eventEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getEndErrorEvent() {
        return endErrorEventEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getEndEvent() {
        return endEventEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getEndMessageEvent() {
        return endMessageEventEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getEndSignalEvent() {
        return endSignalEventEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getEndTerminatedEvent() {
        return endTerminatedEventEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getErrorEvent() {
        return errorEventEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getErrorEvent_ErrorCode() {
        return (EAttribute)errorEventEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getEnumType() {
        return enumTypeEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getEnumType_Literals() {
        return (EAttribute)enumTypeEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getFloatType() {
        return floatTypeEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getFlowElement() {
        return flowElementEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EReference getFlowElement_DynamicLabel() {
        return (EReference)flowElementEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EReference getFlowElement_DynamicDescription() {
        return (EReference)flowElementEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EReference getFlowElement_StepSummary() {
        return (EReference)flowElementEClass.getEStructuralFeatures().get(2);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getFormMapping() {
        return formMappingEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EReference getFormMapping_TargetForm() {
        return (EReference)formMappingEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getFormMapping_Type() {
        return (EAttribute)formMappingEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getFormMapping_Url() {
        return (EAttribute)formMappingEClass.getEStructuralFeatures().get(2);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getGateway() {
        return gatewayEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getGateway_GatewayType() {
        return (EAttribute)gatewayEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getInclusiveGateway() {
        return inclusiveGatewayEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getInputMapping() {
        return inputMappingEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EReference getInputMapping_ProcessSource() {
        return (EReference)inputMappingEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getInputMapping_SubprocessTarget() {
        return (EAttribute)inputMappingEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getInputMapping_AssignationType() {
        return (EAttribute)inputMappingEClass.getEStructuralFeatures().get(2);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getIntegerType() {
        return integerTypeEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getIntermediateErrorCatchEvent() {
        return intermediateErrorCatchEventEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getIntermediateCatchSignalEvent() {
        return intermediateCatchSignalEventEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getIntermediateThrowSignalEvent() {
        return intermediateThrowSignalEventEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getIntermediateCatchMessageEvent() {
        return intermediateCatchMessageEventEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getIntermediateThrowMessageEvent() {
        return intermediateThrowMessageEventEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getIntermediateCatchTimerEvent() {
        return intermediateCatchTimerEventEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getJavaObjectData() {
        return javaObjectDataEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getJavaObjectData_ClassName() {
        return (EAttribute)javaObjectDataEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getJavaType() {
        return javaTypeEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getLane() {
        return laneEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getLinkEvent() {
        return linkEventEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getLongType() {
        return longTypeEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getMainProcess() {
        return mainProcessEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getMainProcess_BonitaVersion() {
        return (EAttribute)mainProcessEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getMainProcess_BonitaModelVersion() {
        return (EAttribute)mainProcessEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getMainProcess_IncludedEntries() {
        return (EAttribute)mainProcessEClass.getEStructuralFeatures().get(2);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EReference getMainProcess_MessageConnections() {
        return (EReference)mainProcessEClass.getEStructuralFeatures().get(3);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getMainProcess_GeneratedLibs() {
        return (EAttribute)mainProcessEClass.getEStructuralFeatures().get(4);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getMainProcess_EnableValidation() {
        return (EAttribute)mainProcessEClass.getEStructuralFeatures().get(5);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getMainProcess_ConfigId() {
        return (EAttribute)mainProcessEClass.getEStructuralFeatures().get(6);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getMessage() {
        return messageEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getMessage_ThrowEvent() {
        return (EAttribute)messageEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getMessage_Ttl() {
        return (EAttribute)messageEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EReference getMessage_Correlation() {
        return (EReference)messageEClass.getEStructuralFeatures().get(2);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EReference getMessage_Source() {
        return (EReference)messageEClass.getEStructuralFeatures().get(3);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EReference getMessage_TargetProcessExpression() {
        return (EReference)messageEClass.getEStructuralFeatures().get(4);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EReference getMessage_TargetElementExpression() {
        return (EReference)messageEClass.getEStructuralFeatures().get(5);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EReference getMessage_MessageContent() {
        return (EReference)messageEClass.getEStructuralFeatures().get(6);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getMessageFlow() {
        return messageFlowEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EReference getMessageFlow_Target() {
        return (EReference)messageFlowEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EReference getMessageFlow_Source() {
        return (EReference)messageFlowEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getMessageEvent() {
        return messageEventEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getMultiInstantiable() {
        return multiInstantiableEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getMultiInstantiable_Type() {
        return (EAttribute)multiInstantiableEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getMultiInstantiable_TestBefore() {
        return (EAttribute)multiInstantiableEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EReference getMultiInstantiable_LoopCondition() {
        return (EReference)multiInstantiableEClass.getEStructuralFeatures().get(2);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EReference getMultiInstantiable_LoopMaximum() {
        return (EReference)multiInstantiableEClass.getEStructuralFeatures().get(3);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getMultiInstantiable_UseCardinality() {
        return (EAttribute)multiInstantiableEClass.getEStructuralFeatures().get(4);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EReference getMultiInstantiable_CardinalityExpression() {
        return (EReference)multiInstantiableEClass.getEStructuralFeatures().get(5);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EReference getMultiInstantiable_CollectionDataToMultiInstantiate() {
        return (EReference)multiInstantiableEClass.getEStructuralFeatures().get(6);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EReference getMultiInstantiable_IteratorExpression() {
        return (EReference)multiInstantiableEClass.getEStructuralFeatures().get(7);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EReference getMultiInstantiable_OutputData() {
        return (EReference)multiInstantiableEClass.getEStructuralFeatures().get(8);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EReference getMultiInstantiable_ListDataContainingOutputResults() {
        return (EReference)multiInstantiableEClass.getEStructuralFeatures().get(9);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EReference getMultiInstantiable_CompletionCondition() {
        return (EReference)multiInstantiableEClass.getEStructuralFeatures().get(10);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getMultiInstantiable_StoreOutput() {
        return (EAttribute)multiInstantiableEClass.getEStructuralFeatures().get(11);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getNonInterruptingBoundaryTimerEvent() {
        return nonInterruptingBoundaryTimerEventEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getOperationContainer() {
        return operationContainerEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EReference getOperationContainer_Operations() {
        return (EReference)operationContainerEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getOutputMapping() {
        return outputMappingEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getOutputMapping_SubprocessSource() {
        return (EAttribute)outputMappingEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EReference getOutputMapping_ProcessTarget() {
        return (EReference)outputMappingEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getPageFlow() {
        return pageFlowEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EReference getPageFlow_FormMapping() {
        return (EReference)pageFlowEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getPool() {
        return poolEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EReference getPool_Documents() {
        return (EReference)poolEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EReference getPool_SearchIndexes() {
        return (EReference)poolEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getPool_DisplayName() {
        return (EAttribute)poolEClass.getEStructuralFeatures().get(2);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getPool_AdditionalResources() {
        return (EReference)poolEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getRecapFlow() {
        return recapFlowEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EReference getRecapFlow_OverviewFormMapping() {
        return (EReference)recapFlowEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getReceiveTask() {
        return receiveTaskEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getSequenceFlow() {
        return sequenceFlowEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getSequenceFlow_IsDefault() {
        return (EAttribute)sequenceFlowEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getSequenceFlow_ConditionType() {
        return (EAttribute)sequenceFlowEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EReference getSequenceFlow_DecisionTable() {
        return (EReference)sequenceFlowEClass.getEStructuralFeatures().get(2);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EReference getSequenceFlow_Condition() {
        return (EReference)sequenceFlowEClass.getEStructuralFeatures().get(3);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getSequenceFlow_PathToken() {
        return (EAttribute)sequenceFlowEClass.getEStructuralFeatures().get(4);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getSignalEvent() {
        return signalEventEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getSignalEvent_SignalCode() {
        return (EAttribute)signalEventEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getSourceElement() {
        return sourceElementEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EReference getSourceElement_Outgoing() {
        return (EReference)sourceElementEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getStringType() {
        return stringTypeEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getScriptTask() {
        return scriptTaskEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getSearchIndex() {
        return searchIndexEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EReference getSearchIndex_Name() {
        return (EReference)searchIndexEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EReference getSearchIndex_Value() {
        return (EReference)searchIndexEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getSendTask() {
        return sendTaskEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getServiceTask() {
        return serviceTaskEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getStartErrorEvent() {
        return startErrorEventEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getStartEvent() {
        return startEventEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getStartMessageEvent() {
        return startMessageEventEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getStartSignalEvent() {
        return startSignalEventEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getStartTimerEvent() {
        return startTimerEventEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getStartTimerEvent_From() {
        return (EAttribute)startTimerEventEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getStartTimerEvent_At() {
        return (EAttribute)startTimerEventEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getStartTimerEvent_Month() {
        return (EAttribute)startTimerEventEClass.getEStructuralFeatures().get(2);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getStartTimerEvent_Day() {
        return (EAttribute)startTimerEventEClass.getEStructuralFeatures().get(3);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getStartTimerEvent_Hours() {
        return (EAttribute)startTimerEventEClass.getEStructuralFeatures().get(4);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getStartTimerEvent_DayNumber() {
        return (EAttribute)startTimerEventEClass.getEStructuralFeatures().get(5);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getStartTimerEvent_Minutes() {
        return (EAttribute)startTimerEventEClass.getEStructuralFeatures().get(6);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getStartTimerEvent_Seconds() {
        return (EAttribute)startTimerEventEClass.getEStructuralFeatures().get(7);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getStartTimerEvent_ScriptType() {
        return (EAttribute)startTimerEventEClass.getEStructuralFeatures().get(8);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getSubProcessEvent() {
        return subProcessEventEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getTask() {
        return taskEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getTask_OverrideActorsOfTheLane() {
        return (EAttribute)taskEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getTask_Priority() {
        return (EAttribute)taskEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EReference getTask_ExpectedDuration() {
        return (EReference)taskEClass.getEStructuralFeatures().get(2);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getTargetElement() {
        return targetElementEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EReference getTargetElement_Incoming() {
        return (EReference)targetElementEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getTextAnnotation() {
        return textAnnotationEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getTextAnnotation_Text() {
        return (EAttribute)textAnnotationEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getTextAnnotationAttachment() {
        return textAnnotationAttachmentEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EReference getTextAnnotationAttachment_Source() {
        return (EReference)textAnnotationAttachmentEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EReference getTextAnnotationAttachment_Target() {
        return (EReference)textAnnotationAttachmentEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getThrowSignalEvent() {
        return throwSignalEventEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getThrowLinkEvent() {
        return throwLinkEventEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EReference getThrowLinkEvent_To() {
        return (EReference)throwLinkEventEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getThrowMessageEvent() {
        return throwMessageEventEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EReference getThrowMessageEvent_Events() {
        return (EReference)throwMessageEventEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EReference getThrowMessageEvent_OutgoingMessages() {
        return (EReference)throwMessageEventEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getTimerEvent() {
        return timerEventEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getXMLData() {
        return xmlDataEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getXMLData_Namespace() {
        return (EAttribute)xmlDataEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getXMLData_Type() {
        return (EAttribute)xmlDataEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getXMLType() {
        return xmlTypeEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getXORGateway() {
        return xorGatewayEClass;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getAdditionalResource() {
        return additionalResourceEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getAdditionalResource_Name() {
        return (EAttribute)additionalResourceEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getAdditionalResource_Description() {
        return (EAttribute)additionalResourceEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EEnum getCorrelationTypeActive() {
        return correlationTypeActiveEEnum;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EEnum getContractInputType() {
        return contractInputTypeEEnum;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EEnum getDocumentType() {
        return documentTypeEEnum;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EEnum getFormMappingType() {
        return formMappingTypeEEnum;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EEnum getGatewayType() {
        return gatewayTypeEEnum;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EEnum getInputMappingAssignationType() {
        return inputMappingAssignationTypeEEnum;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EEnum getMultiInstanceType() {
        return multiInstanceTypeEEnum;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EEnum getSequenceFlowConditionType() {
        return sequenceFlowConditionTypeEEnum;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EEnum getStartTimerScriptType() {
        return startTimerScriptTypeEEnum;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public ProcessFactory getProcessFactory() {
        return (ProcessFactory)getEFactoryInstance();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private boolean isCreated = false;

	/**
     * Creates the meta-model objects for the package.  This method is
     * guarded to have no affect on any invocation but its first.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void createPackageContents() {
        if (isCreated) return;
        isCreated = true;

        // Create classes and their features
        abstractCatchMessageEventEClass = createEClass(ABSTRACT_CATCH_MESSAGE_EVENT);
        createEAttribute(abstractCatchMessageEventEClass, ABSTRACT_CATCH_MESSAGE_EVENT__EVENT);
        createEReference(abstractCatchMessageEventEClass, ABSTRACT_CATCH_MESSAGE_EVENT__INCOMING_MESSAG);
        createEReference(abstractCatchMessageEventEClass, ABSTRACT_CATCH_MESSAGE_EVENT__CORRELATION);
        createEReference(abstractCatchMessageEventEClass, ABSTRACT_CATCH_MESSAGE_EVENT__MESSAGE_CONTENT);

        abstractPageFlowEClass = createEClass(ABSTRACT_PAGE_FLOW);

        abstractProcessEClass = createEClass(ABSTRACT_PROCESS);
        createEAttribute(abstractProcessEClass, ABSTRACT_PROCESS__VERSION);
        createEAttribute(abstractProcessEClass, ABSTRACT_PROCESS__AUTHOR);
        createEAttribute(abstractProcessEClass, ABSTRACT_PROCESS__CREATION_DATE);
        createEAttribute(abstractProcessEClass, ABSTRACT_PROCESS__MODIFICATION_DATE);
        createEReference(abstractProcessEClass, ABSTRACT_PROCESS__DATATYPES);
        createEReference(abstractProcessEClass, ABSTRACT_PROCESS__CONNECTIONS);
        createEAttribute(abstractProcessEClass, ABSTRACT_PROCESS__CATEGORIES);
        createEReference(abstractProcessEClass, ABSTRACT_PROCESS__ACTORS);
        createEReference(abstractProcessEClass, ABSTRACT_PROCESS__CONFIGURATIONS);
        createEReference(abstractProcessEClass, ABSTRACT_PROCESS__PARAMETERS);

        abstractTimerEventEClass = createEClass(ABSTRACT_TIMER_EVENT);
        createEReference(abstractTimerEventEClass, ABSTRACT_TIMER_EVENT__CONDITION);

        activityEClass = createEClass(ACTIVITY);
        createEReference(activityEClass, ACTIVITY__BOUNDARY_INTERMEDIATE_EVENTS);

        actorEClass = createEClass(ACTOR);
        createEAttribute(actorEClass, ACTOR__INITIATOR);

        actorFilterEClass = createEClass(ACTOR_FILTER);

        andGatewayEClass = createEClass(AND_GATEWAY);

        assignableEClass = createEClass(ASSIGNABLE);
        createEReference(assignableEClass, ASSIGNABLE__ACTOR);
        createEReference(assignableEClass, ASSIGNABLE__FILTERS);

        associationEClass = createEClass(ASSOCIATION);
        createEAttribute(associationEClass, ASSOCIATION__IS_DIRECTED);

        booleanTypeEClass = createEClass(BOOLEAN_TYPE);

        boundaryEventEClass = createEClass(BOUNDARY_EVENT);

        boundaryMessageEventEClass = createEClass(BOUNDARY_MESSAGE_EVENT);

        boundarySignalEventEClass = createEClass(BOUNDARY_SIGNAL_EVENT);

        boundaryTimerEventEClass = createEClass(BOUNDARY_TIMER_EVENT);

        businessObjectDataEClass = createEClass(BUSINESS_OBJECT_DATA);
        createEAttribute(businessObjectDataEClass, BUSINESS_OBJECT_DATA__BUSINESS_DATA_REPOSITORY_ID);
        createEAttribute(businessObjectDataEClass, BUSINESS_OBJECT_DATA__CREATE_NEW_INSTANCE);
        createEAttribute(businessObjectDataEClass, BUSINESS_OBJECT_DATA__ECLASS_NAME);

        businessObjectTypeEClass = createEClass(BUSINESS_OBJECT_TYPE);

        callActivityEClass = createEClass(CALL_ACTIVITY);
        createEReference(callActivityEClass, CALL_ACTIVITY__INPUT_MAPPINGS);
        createEReference(callActivityEClass, CALL_ACTIVITY__OUTPUT_MAPPINGS);
        createEReference(callActivityEClass, CALL_ACTIVITY__CALLED_ACTIVITY_NAME);
        createEReference(callActivityEClass, CALL_ACTIVITY__CALLED_ACTIVITY_VERSION);

        catchLinkEventEClass = createEClass(CATCH_LINK_EVENT);
        createEReference(catchLinkEventEClass, CATCH_LINK_EVENT__FROM);

        catchMessageEventEClass = createEClass(CATCH_MESSAGE_EVENT);

        catchSignalEventEClass = createEClass(CATCH_SIGNAL_EVENT);

        connectableElementEClass = createEClass(CONNECTABLE_ELEMENT);
        createEReference(connectableElementEClass, CONNECTABLE_ELEMENT__CONNECTORS);
        createEReference(connectableElementEClass, CONNECTABLE_ELEMENT__KPIS);

        connectorEClass = createEClass(CONNECTOR);
        createEAttribute(connectorEClass, CONNECTOR__DEFINITION_ID);
        createEAttribute(connectorEClass, CONNECTOR__EVENT);
        createEAttribute(connectorEClass, CONNECTOR__IGNORE_ERRORS);
        createEAttribute(connectorEClass, CONNECTOR__THROW_ERROR_EVENT);
        createEAttribute(connectorEClass, CONNECTOR__NAMED_ERROR);
        createEAttribute(connectorEClass, CONNECTOR__DEFINITION_VERSION);
        createEReference(connectorEClass, CONNECTOR__CONFIGURATION);
        createEReference(connectorEClass, CONNECTOR__OUTPUTS);

        containerEClass = createEClass(CONTAINER);
        createEReference(containerEClass, CONTAINER__ELEMENTS);

        contractEClass = createEClass(CONTRACT);
        createEReference(contractEClass, CONTRACT__INPUTS);
        createEReference(contractEClass, CONTRACT__CONSTRAINTS);

        contractInputMappingEClass = createEClass(CONTRACT_INPUT_MAPPING);
        createEReference(contractInputMappingEClass, CONTRACT_INPUT_MAPPING__DATA);
        createEAttribute(contractInputMappingEClass, CONTRACT_INPUT_MAPPING__SETTER_NAME);
        createEAttribute(contractInputMappingEClass, CONTRACT_INPUT_MAPPING__SETTER_PARAM_TYPE);

        contractInputEClass = createEClass(CONTRACT_INPUT);
        createEAttribute(contractInputEClass, CONTRACT_INPUT__NAME);
        createEAttribute(contractInputEClass, CONTRACT_INPUT__TYPE);
        createEAttribute(contractInputEClass, CONTRACT_INPUT__DESCRIPTION);
        createEAttribute(contractInputEClass, CONTRACT_INPUT__MULTIPLE);
        createEReference(contractInputEClass, CONTRACT_INPUT__MAPPING);
        createEReference(contractInputEClass, CONTRACT_INPUT__INPUTS);
        createEAttribute(contractInputEClass, CONTRACT_INPUT__DATA_REFERENCE);
        createEAttribute(contractInputEClass, CONTRACT_INPUT__CREATE_MODE);

        contractConstraintEClass = createEClass(CONTRACT_CONSTRAINT);
        createEAttribute(contractConstraintEClass, CONTRACT_CONSTRAINT__EXPRESSION);
        createEAttribute(contractConstraintEClass, CONTRACT_CONSTRAINT__ERROR_MESSAGE);
        createEAttribute(contractConstraintEClass, CONTRACT_CONSTRAINT__NAME);
        createEAttribute(contractConstraintEClass, CONTRACT_CONSTRAINT__INPUT_NAMES);
        createEAttribute(contractConstraintEClass, CONTRACT_CONSTRAINT__DESCRIPTION);

        contractContainerEClass = createEClass(CONTRACT_CONTAINER);
        createEReference(contractContainerEClass, CONTRACT_CONTAINER__CONTRACT);

        connectionEClass = createEClass(CONNECTION);
        createEReference(connectionEClass, CONNECTION__TARGET);
        createEReference(connectionEClass, CONNECTION__SOURCE);

        correlationEClass = createEClass(CORRELATION);
        createEAttribute(correlationEClass, CORRELATION__CORRELATION_TYPE);
        createEReference(correlationEClass, CORRELATION__CORRELATION_ASSOCIATION);

        correlationAssociationEClass = createEClass(CORRELATION_ASSOCIATION);
        createEReference(correlationAssociationEClass, CORRELATION_ASSOCIATION__CORRELATION_EXPRESSION);
        createEReference(correlationAssociationEClass, CORRELATION_ASSOCIATION__CORRELATION_KEY);

        dataEClass = createEClass(DATA);
        createEAttribute(dataEClass, DATA__GENERATED);
        createEAttribute(dataEClass, DATA__MULTIPLE);
        createEAttribute(dataEClass, DATA__TRANSIENT);
        createEAttribute(dataEClass, DATA__DATASOURCE_ID);
        createEReference(dataEClass, DATA__DATA_TYPE);
        createEReference(dataEClass, DATA__DEFAULT_VALUE);

        dataAwareEClass = createEClass(DATA_AWARE);
        createEReference(dataAwareEClass, DATA_AWARE__DATA);

        dateTypeEClass = createEClass(DATE_TYPE);

        dataTypeEClass = createEClass(DATA_TYPE);

        documentEClass = createEClass(DOCUMENT);
        createEAttribute(documentEClass, DOCUMENT__DEFAULT_VALUE_ID_OF_DOCUMENT_STORE);
        createEReference(documentEClass, DOCUMENT__MIME_TYPE);
        createEReference(documentEClass, DOCUMENT__URL);
        createEAttribute(documentEClass, DOCUMENT__DOCUMENT_TYPE);
        createEReference(documentEClass, DOCUMENT__INITIAL_MULTIPLE_CONTENT);
        createEAttribute(documentEClass, DOCUMENT__MULTIPLE);
        createEReference(documentEClass, DOCUMENT__CONTRACT_INPUT);

        doubleTypeEClass = createEClass(DOUBLE_TYPE);

        elementEClass = createEClass(ELEMENT);
        createEAttribute(elementEClass, ELEMENT__DOCUMENTATION);
        createEAttribute(elementEClass, ELEMENT__NAME);
        createEReference(elementEClass, ELEMENT__TEXT_ANNOTATION_ATTACHMENT);

        eventEClass = createEClass(EVENT);

        endErrorEventEClass = createEClass(END_ERROR_EVENT);

        endEventEClass = createEClass(END_EVENT);

        endMessageEventEClass = createEClass(END_MESSAGE_EVENT);

        endSignalEventEClass = createEClass(END_SIGNAL_EVENT);

        endTerminatedEventEClass = createEClass(END_TERMINATED_EVENT);

        errorEventEClass = createEClass(ERROR_EVENT);
        createEAttribute(errorEventEClass, ERROR_EVENT__ERROR_CODE);

        enumTypeEClass = createEClass(ENUM_TYPE);
        createEAttribute(enumTypeEClass, ENUM_TYPE__LITERALS);

        floatTypeEClass = createEClass(FLOAT_TYPE);

        flowElementEClass = createEClass(FLOW_ELEMENT);
        createEReference(flowElementEClass, FLOW_ELEMENT__DYNAMIC_LABEL);
        createEReference(flowElementEClass, FLOW_ELEMENT__DYNAMIC_DESCRIPTION);
        createEReference(flowElementEClass, FLOW_ELEMENT__STEP_SUMMARY);

        formMappingEClass = createEClass(FORM_MAPPING);
        createEReference(formMappingEClass, FORM_MAPPING__TARGET_FORM);
        createEAttribute(formMappingEClass, FORM_MAPPING__TYPE);
        createEAttribute(formMappingEClass, FORM_MAPPING__URL);

        gatewayEClass = createEClass(GATEWAY);
        createEAttribute(gatewayEClass, GATEWAY__GATEWAY_TYPE);

        inclusiveGatewayEClass = createEClass(INCLUSIVE_GATEWAY);

        inputMappingEClass = createEClass(INPUT_MAPPING);
        createEReference(inputMappingEClass, INPUT_MAPPING__PROCESS_SOURCE);
        createEAttribute(inputMappingEClass, INPUT_MAPPING__SUBPROCESS_TARGET);
        createEAttribute(inputMappingEClass, INPUT_MAPPING__ASSIGNATION_TYPE);

        integerTypeEClass = createEClass(INTEGER_TYPE);

        intermediateErrorCatchEventEClass = createEClass(INTERMEDIATE_ERROR_CATCH_EVENT);

        intermediateCatchSignalEventEClass = createEClass(INTERMEDIATE_CATCH_SIGNAL_EVENT);

        intermediateThrowSignalEventEClass = createEClass(INTERMEDIATE_THROW_SIGNAL_EVENT);

        intermediateCatchMessageEventEClass = createEClass(INTERMEDIATE_CATCH_MESSAGE_EVENT);

        intermediateThrowMessageEventEClass = createEClass(INTERMEDIATE_THROW_MESSAGE_EVENT);

        intermediateCatchTimerEventEClass = createEClass(INTERMEDIATE_CATCH_TIMER_EVENT);

        javaObjectDataEClass = createEClass(JAVA_OBJECT_DATA);
        createEAttribute(javaObjectDataEClass, JAVA_OBJECT_DATA__CLASS_NAME);

        javaTypeEClass = createEClass(JAVA_TYPE);

        laneEClass = createEClass(LANE);

        linkEventEClass = createEClass(LINK_EVENT);

        longTypeEClass = createEClass(LONG_TYPE);

        mainProcessEClass = createEClass(MAIN_PROCESS);
        createEAttribute(mainProcessEClass, MAIN_PROCESS__BONITA_VERSION);
        createEAttribute(mainProcessEClass, MAIN_PROCESS__BONITA_MODEL_VERSION);
        createEAttribute(mainProcessEClass, MAIN_PROCESS__INCLUDED_ENTRIES);
        createEReference(mainProcessEClass, MAIN_PROCESS__MESSAGE_CONNECTIONS);
        createEAttribute(mainProcessEClass, MAIN_PROCESS__GENERATED_LIBS);
        createEAttribute(mainProcessEClass, MAIN_PROCESS__ENABLE_VALIDATION);
        createEAttribute(mainProcessEClass, MAIN_PROCESS__CONFIG_ID);

        messageEClass = createEClass(MESSAGE);
        createEAttribute(messageEClass, MESSAGE__THROW_EVENT);
        createEAttribute(messageEClass, MESSAGE__TTL);
        createEReference(messageEClass, MESSAGE__CORRELATION);
        createEReference(messageEClass, MESSAGE__SOURCE);
        createEReference(messageEClass, MESSAGE__TARGET_PROCESS_EXPRESSION);
        createEReference(messageEClass, MESSAGE__TARGET_ELEMENT_EXPRESSION);
        createEReference(messageEClass, MESSAGE__MESSAGE_CONTENT);

        messageFlowEClass = createEClass(MESSAGE_FLOW);
        createEReference(messageFlowEClass, MESSAGE_FLOW__TARGET);
        createEReference(messageFlowEClass, MESSAGE_FLOW__SOURCE);

        messageEventEClass = createEClass(MESSAGE_EVENT);

        multiInstantiableEClass = createEClass(MULTI_INSTANTIABLE);
        createEAttribute(multiInstantiableEClass, MULTI_INSTANTIABLE__TYPE);
        createEAttribute(multiInstantiableEClass, MULTI_INSTANTIABLE__TEST_BEFORE);
        createEReference(multiInstantiableEClass, MULTI_INSTANTIABLE__LOOP_CONDITION);
        createEReference(multiInstantiableEClass, MULTI_INSTANTIABLE__LOOP_MAXIMUM);
        createEAttribute(multiInstantiableEClass, MULTI_INSTANTIABLE__USE_CARDINALITY);
        createEReference(multiInstantiableEClass, MULTI_INSTANTIABLE__CARDINALITY_EXPRESSION);
        createEReference(multiInstantiableEClass, MULTI_INSTANTIABLE__COLLECTION_DATA_TO_MULTI_INSTANTIATE);
        createEReference(multiInstantiableEClass, MULTI_INSTANTIABLE__ITERATOR_EXPRESSION);
        createEReference(multiInstantiableEClass, MULTI_INSTANTIABLE__OUTPUT_DATA);
        createEReference(multiInstantiableEClass, MULTI_INSTANTIABLE__LIST_DATA_CONTAINING_OUTPUT_RESULTS);
        createEReference(multiInstantiableEClass, MULTI_INSTANTIABLE__COMPLETION_CONDITION);
        createEAttribute(multiInstantiableEClass, MULTI_INSTANTIABLE__STORE_OUTPUT);

        nonInterruptingBoundaryTimerEventEClass = createEClass(NON_INTERRUPTING_BOUNDARY_TIMER_EVENT);

        operationContainerEClass = createEClass(OPERATION_CONTAINER);
        createEReference(operationContainerEClass, OPERATION_CONTAINER__OPERATIONS);

        outputMappingEClass = createEClass(OUTPUT_MAPPING);
        createEAttribute(outputMappingEClass, OUTPUT_MAPPING__SUBPROCESS_SOURCE);
        createEReference(outputMappingEClass, OUTPUT_MAPPING__PROCESS_TARGET);

        pageFlowEClass = createEClass(PAGE_FLOW);
        createEReference(pageFlowEClass, PAGE_FLOW__FORM_MAPPING);

        poolEClass = createEClass(POOL);
        createEReference(poolEClass, POOL__DOCUMENTS);
        createEReference(poolEClass, POOL__SEARCH_INDEXES);
        createEAttribute(poolEClass, POOL__DISPLAY_NAME);
        createEReference(poolEClass, POOL__ADDITIONAL_RESOURCES);

        recapFlowEClass = createEClass(RECAP_FLOW);
        createEReference(recapFlowEClass, RECAP_FLOW__OVERVIEW_FORM_MAPPING);

        receiveTaskEClass = createEClass(RECEIVE_TASK);

        sequenceFlowEClass = createEClass(SEQUENCE_FLOW);
        createEAttribute(sequenceFlowEClass, SEQUENCE_FLOW__IS_DEFAULT);
        createEAttribute(sequenceFlowEClass, SEQUENCE_FLOW__CONDITION_TYPE);
        createEReference(sequenceFlowEClass, SEQUENCE_FLOW__DECISION_TABLE);
        createEReference(sequenceFlowEClass, SEQUENCE_FLOW__CONDITION);
        createEAttribute(sequenceFlowEClass, SEQUENCE_FLOW__PATH_TOKEN);

        signalEventEClass = createEClass(SIGNAL_EVENT);
        createEAttribute(signalEventEClass, SIGNAL_EVENT__SIGNAL_CODE);

        sourceElementEClass = createEClass(SOURCE_ELEMENT);
        createEReference(sourceElementEClass, SOURCE_ELEMENT__OUTGOING);

        stringTypeEClass = createEClass(STRING_TYPE);

        scriptTaskEClass = createEClass(SCRIPT_TASK);

        searchIndexEClass = createEClass(SEARCH_INDEX);
        createEReference(searchIndexEClass, SEARCH_INDEX__NAME);
        createEReference(searchIndexEClass, SEARCH_INDEX__VALUE);

        sendTaskEClass = createEClass(SEND_TASK);

        serviceTaskEClass = createEClass(SERVICE_TASK);

        startErrorEventEClass = createEClass(START_ERROR_EVENT);

        startEventEClass = createEClass(START_EVENT);

        startMessageEventEClass = createEClass(START_MESSAGE_EVENT);

        startSignalEventEClass = createEClass(START_SIGNAL_EVENT);

        startTimerEventEClass = createEClass(START_TIMER_EVENT);
        createEAttribute(startTimerEventEClass, START_TIMER_EVENT__FROM);
        createEAttribute(startTimerEventEClass, START_TIMER_EVENT__AT);
        createEAttribute(startTimerEventEClass, START_TIMER_EVENT__MONTH);
        createEAttribute(startTimerEventEClass, START_TIMER_EVENT__DAY);
        createEAttribute(startTimerEventEClass, START_TIMER_EVENT__HOURS);
        createEAttribute(startTimerEventEClass, START_TIMER_EVENT__DAY_NUMBER);
        createEAttribute(startTimerEventEClass, START_TIMER_EVENT__MINUTES);
        createEAttribute(startTimerEventEClass, START_TIMER_EVENT__SECONDS);
        createEAttribute(startTimerEventEClass, START_TIMER_EVENT__SCRIPT_TYPE);

        subProcessEventEClass = createEClass(SUB_PROCESS_EVENT);

        taskEClass = createEClass(TASK);
        createEAttribute(taskEClass, TASK__OVERRIDE_ACTORS_OF_THE_LANE);
        createEAttribute(taskEClass, TASK__PRIORITY);
        createEReference(taskEClass, TASK__EXPECTED_DURATION);

        targetElementEClass = createEClass(TARGET_ELEMENT);
        createEReference(targetElementEClass, TARGET_ELEMENT__INCOMING);

        textAnnotationEClass = createEClass(TEXT_ANNOTATION);
        createEAttribute(textAnnotationEClass, TEXT_ANNOTATION__TEXT);

        textAnnotationAttachmentEClass = createEClass(TEXT_ANNOTATION_ATTACHMENT);
        createEReference(textAnnotationAttachmentEClass, TEXT_ANNOTATION_ATTACHMENT__SOURCE);
        createEReference(textAnnotationAttachmentEClass, TEXT_ANNOTATION_ATTACHMENT__TARGET);

        throwSignalEventEClass = createEClass(THROW_SIGNAL_EVENT);

        throwLinkEventEClass = createEClass(THROW_LINK_EVENT);
        createEReference(throwLinkEventEClass, THROW_LINK_EVENT__TO);

        throwMessageEventEClass = createEClass(THROW_MESSAGE_EVENT);
        createEReference(throwMessageEventEClass, THROW_MESSAGE_EVENT__EVENTS);
        createEReference(throwMessageEventEClass, THROW_MESSAGE_EVENT__OUTGOING_MESSAGES);

        timerEventEClass = createEClass(TIMER_EVENT);

        xmlDataEClass = createEClass(XML_DATA);
        createEAttribute(xmlDataEClass, XML_DATA__NAMESPACE);
        createEAttribute(xmlDataEClass, XML_DATA__TYPE);

        xmlTypeEClass = createEClass(XML_TYPE);

        xorGatewayEClass = createEClass(XOR_GATEWAY);

        additionalResourceEClass = createEClass(ADDITIONAL_RESOURCE);
        createEAttribute(additionalResourceEClass, ADDITIONAL_RESOURCE__NAME);
        createEAttribute(additionalResourceEClass, ADDITIONAL_RESOURCE__DESCRIPTION);

        // Create enums
        correlationTypeActiveEEnum = createEEnum(CORRELATION_TYPE_ACTIVE);
        contractInputTypeEEnum = createEEnum(CONTRACT_INPUT_TYPE);
        documentTypeEEnum = createEEnum(DOCUMENT_TYPE);
        formMappingTypeEEnum = createEEnum(FORM_MAPPING_TYPE);
        gatewayTypeEEnum = createEEnum(GATEWAY_TYPE);
        inputMappingAssignationTypeEEnum = createEEnum(INPUT_MAPPING_ASSIGNATION_TYPE);
        multiInstanceTypeEEnum = createEEnum(MULTI_INSTANCE_TYPE);
        sequenceFlowConditionTypeEEnum = createEEnum(SEQUENCE_FLOW_CONDITION_TYPE);
        startTimerScriptTypeEEnum = createEEnum(START_TIMER_SCRIPT_TYPE);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private boolean isInitialized = false;

	/**
     * Complete the initialization of the package and its meta-model.  This
     * method is guarded to have no affect on any invocation but its first.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void initializePackageContents() {
        if (isInitialized) return;
        isInitialized = true;

        // Initialize package
        setName(eNAME);
        setNsPrefix(eNS_PREFIX);
        setNsURI(eNS_URI);

        // Obtain other dependent packages
        DecisionPackage theDecisionPackage = (DecisionPackage)EPackage.Registry.INSTANCE.getEPackage(DecisionPackage.eNS_URI);
        ExpressionPackage theExpressionPackage = (ExpressionPackage)EPackage.Registry.INSTANCE.getEPackage(ExpressionPackage.eNS_URI);
        ConfigurationPackage theConfigurationPackage = (ConfigurationPackage)EPackage.Registry.INSTANCE.getEPackage(ConfigurationPackage.eNS_URI);
        ParameterPackage theParameterPackage = (ParameterPackage)EPackage.Registry.INSTANCE.getEPackage(ParameterPackage.eNS_URI);
        KpiPackage theKpiPackage = (KpiPackage)EPackage.Registry.INSTANCE.getEPackage(KpiPackage.eNS_URI);
        ConnectorConfigurationPackage theConnectorConfigurationPackage = (ConnectorConfigurationPackage)EPackage.Registry.INSTANCE.getEPackage(ConnectorConfigurationPackage.eNS_URI);

        // Add subpackages
        getESubpackages().add(theDecisionPackage);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        abstractCatchMessageEventEClass.getESuperTypes().add(this.getElement());
        abstractPageFlowEClass.getESuperTypes().add(this.getElement());
        abstractProcessEClass.getESuperTypes().add(this.getContainer());
        abstractProcessEClass.getESuperTypes().add(this.getPageFlow());
        abstractProcessEClass.getESuperTypes().add(this.getRecapFlow());
        abstractTimerEventEClass.getESuperTypes().add(this.getElement());
        activityEClass.getESuperTypes().add(this.getFlowElement());
        activityEClass.getESuperTypes().add(this.getConnectableElement());
        activityEClass.getESuperTypes().add(this.getOperationContainer());
        activityEClass.getESuperTypes().add(this.getMultiInstantiable());
        actorEClass.getESuperTypes().add(this.getElement());
        actorFilterEClass.getESuperTypes().add(this.getConnector());
        andGatewayEClass.getESuperTypes().add(this.getGateway());
        associationEClass.getESuperTypes().add(this.getConnection());
        booleanTypeEClass.getESuperTypes().add(this.getDataType());
        boundaryEventEClass.getESuperTypes().add(this.getElement());
        boundaryEventEClass.getESuperTypes().add(this.getSourceElement());
        boundaryMessageEventEClass.getESuperTypes().add(this.getBoundaryEvent());
        boundaryMessageEventEClass.getESuperTypes().add(this.getAbstractCatchMessageEvent());
        boundarySignalEventEClass.getESuperTypes().add(this.getBoundaryEvent());
        boundarySignalEventEClass.getESuperTypes().add(this.getSignalEvent());
        boundaryTimerEventEClass.getESuperTypes().add(this.getBoundaryEvent());
        boundaryTimerEventEClass.getESuperTypes().add(this.getAbstractTimerEvent());
        businessObjectDataEClass.getESuperTypes().add(this.getJavaObjectData());
        businessObjectTypeEClass.getESuperTypes().add(this.getDataType());
        callActivityEClass.getESuperTypes().add(this.getActivity());
        catchLinkEventEClass.getESuperTypes().add(this.getLinkEvent());
        catchMessageEventEClass.getESuperTypes().add(this.getMessageEvent());
        catchMessageEventEClass.getESuperTypes().add(this.getAbstractCatchMessageEvent());
        catchMessageEventEClass.getESuperTypes().add(this.getDataAware());
        catchSignalEventEClass.getESuperTypes().add(this.getSignalEvent());
        connectableElementEClass.getESuperTypes().add(this.getElement());
        connectableElementEClass.getESuperTypes().add(this.getDataAware());
        connectorEClass.getESuperTypes().add(this.getElement());
        containerEClass.getESuperTypes().add(this.getElement());
        connectionEClass.getESuperTypes().add(this.getElement());
        dataEClass.getESuperTypes().add(this.getElement());
        dateTypeEClass.getESuperTypes().add(this.getStringType());
        dataTypeEClass.getESuperTypes().add(this.getElement());
        documentEClass.getESuperTypes().add(this.getElement());
        doubleTypeEClass.getESuperTypes().add(this.getDataType());
        eventEClass.getESuperTypes().add(this.getFlowElement());
        endErrorEventEClass.getESuperTypes().add(this.getEvent());
        endErrorEventEClass.getESuperTypes().add(this.getErrorEvent());
        endEventEClass.getESuperTypes().add(this.getEvent());
        endMessageEventEClass.getESuperTypes().add(this.getThrowMessageEvent());
        endSignalEventEClass.getESuperTypes().add(this.getThrowSignalEvent());
        endSignalEventEClass.getESuperTypes().add(this.getEvent());
        endTerminatedEventEClass.getESuperTypes().add(this.getEvent());
        enumTypeEClass.getESuperTypes().add(this.getDataType());
        floatTypeEClass.getESuperTypes().add(this.getDataType());
        flowElementEClass.getESuperTypes().add(this.getElement());
        flowElementEClass.getESuperTypes().add(this.getSourceElement());
        flowElementEClass.getESuperTypes().add(this.getTargetElement());
        gatewayEClass.getESuperTypes().add(this.getFlowElement());
        inclusiveGatewayEClass.getESuperTypes().add(this.getGateway());
        integerTypeEClass.getESuperTypes().add(this.getDataType());
        intermediateErrorCatchEventEClass.getESuperTypes().add(this.getBoundaryEvent());
        intermediateErrorCatchEventEClass.getESuperTypes().add(this.getErrorEvent());
        intermediateCatchSignalEventEClass.getESuperTypes().add(this.getCatchSignalEvent());
        intermediateCatchSignalEventEClass.getESuperTypes().add(this.getEvent());
        intermediateCatchSignalEventEClass.getESuperTypes().add(this.getDataAware());
        intermediateThrowSignalEventEClass.getESuperTypes().add(this.getThrowSignalEvent());
        intermediateThrowSignalEventEClass.getESuperTypes().add(this.getEvent());
        intermediateCatchMessageEventEClass.getESuperTypes().add(this.getCatchMessageEvent());
        intermediateThrowMessageEventEClass.getESuperTypes().add(this.getThrowMessageEvent());
        intermediateCatchTimerEventEClass.getESuperTypes().add(this.getTimerEvent());
        javaObjectDataEClass.getESuperTypes().add(this.getData());
        javaTypeEClass.getESuperTypes().add(this.getDataType());
        laneEClass.getESuperTypes().add(this.getContainer());
        laneEClass.getESuperTypes().add(this.getAssignable());
        linkEventEClass.getESuperTypes().add(this.getEvent());
        longTypeEClass.getESuperTypes().add(this.getDataType());
        mainProcessEClass.getESuperTypes().add(this.getAbstractProcess());
        messageEClass.getESuperTypes().add(this.getElement());
        messageFlowEClass.getESuperTypes().add(this.getElement());
        messageEventEClass.getESuperTypes().add(this.getEvent());
        nonInterruptingBoundaryTimerEventEClass.getESuperTypes().add(this.getBoundaryTimerEvent());
        pageFlowEClass.getESuperTypes().add(this.getConnectableElement());
        pageFlowEClass.getESuperTypes().add(this.getAbstractPageFlow());
        poolEClass.getESuperTypes().add(this.getAbstractProcess());
        poolEClass.getESuperTypes().add(this.getContractContainer());
        recapFlowEClass.getESuperTypes().add(this.getAbstractPageFlow());
        receiveTaskEClass.getESuperTypes().add(this.getActivity());
        receiveTaskEClass.getESuperTypes().add(this.getCatchMessageEvent());
        sequenceFlowEClass.getESuperTypes().add(this.getConnection());
        signalEventEClass.getESuperTypes().add(this.getElement());
        sourceElementEClass.getESuperTypes().add(this.getElement());
        stringTypeEClass.getESuperTypes().add(this.getDataType());
        scriptTaskEClass.getESuperTypes().add(this.getActivity());
        sendTaskEClass.getESuperTypes().add(this.getActivity());
        sendTaskEClass.getESuperTypes().add(this.getThrowMessageEvent());
        serviceTaskEClass.getESuperTypes().add(this.getActivity());
        startErrorEventEClass.getESuperTypes().add(this.getErrorEvent());
        startErrorEventEClass.getESuperTypes().add(this.getEvent());
        startEventEClass.getESuperTypes().add(this.getEvent());
        startMessageEventEClass.getESuperTypes().add(this.getCatchMessageEvent());
        startSignalEventEClass.getESuperTypes().add(this.getCatchSignalEvent());
        startSignalEventEClass.getESuperTypes().add(this.getEvent());
        startSignalEventEClass.getESuperTypes().add(this.getDataAware());
        startTimerEventEClass.getESuperTypes().add(this.getTimerEvent());
        subProcessEventEClass.getESuperTypes().add(this.getContainer());
        taskEClass.getESuperTypes().add(this.getActivity());
        taskEClass.getESuperTypes().add(this.getPageFlow());
        taskEClass.getESuperTypes().add(this.getAssignable());
        taskEClass.getESuperTypes().add(this.getContractContainer());
        targetElementEClass.getESuperTypes().add(this.getElement());
        textAnnotationEClass.getESuperTypes().add(this.getElement());
        throwSignalEventEClass.getESuperTypes().add(this.getSignalEvent());
        throwLinkEventEClass.getESuperTypes().add(this.getLinkEvent());
        throwMessageEventEClass.getESuperTypes().add(this.getMessageEvent());
        timerEventEClass.getESuperTypes().add(this.getEvent());
        timerEventEClass.getESuperTypes().add(this.getAbstractTimerEvent());
        timerEventEClass.getESuperTypes().add(this.getDataAware());
        xmlDataEClass.getESuperTypes().add(this.getData());
        xmlTypeEClass.getESuperTypes().add(this.getDataType());
        xorGatewayEClass.getESuperTypes().add(this.getGateway());

        // Initialize classes and features; add operations and parameters
        initEClass(abstractCatchMessageEventEClass, AbstractCatchMessageEvent.class, "AbstractCatchMessageEvent", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getAbstractCatchMessageEvent_Event(), ecorePackage.getEString(), "event", null, 0, 1, AbstractCatchMessageEvent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getAbstractCatchMessageEvent_IncomingMessag(), this.getMessageFlow(), this.getMessageFlow_Target(), "incomingMessag", null, 0, 1, AbstractCatchMessageEvent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getAbstractCatchMessageEvent_Correlation(), theExpressionPackage.getTableExpression(), null, "correlation", null, 0, 1, AbstractCatchMessageEvent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getAbstractCatchMessageEvent_MessageContent(), theExpressionPackage.getOperation(), null, "messageContent", null, 0, -1, AbstractCatchMessageEvent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(abstractPageFlowEClass, AbstractPageFlow.class, "AbstractPageFlow", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(abstractProcessEClass, AbstractProcess.class, "AbstractProcess", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getAbstractProcess_Version(), ecorePackage.getEString(), "version", "1.0", 0, 1, AbstractProcess.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
        initEAttribute(getAbstractProcess_Author(), ecorePackage.getEString(), "author", null, 0, 1, AbstractProcess.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getAbstractProcess_CreationDate(), ecorePackage.getEDate(), "creationDate", null, 0, 1, AbstractProcess.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getAbstractProcess_ModificationDate(), ecorePackage.getEDate(), "modificationDate", null, 0, 1, AbstractProcess.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getAbstractProcess_Datatypes(), this.getDataType(), null, "datatypes", null, 0, -1, AbstractProcess.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getAbstractProcess_Connections(), this.getConnection(), null, "connections", null, 0, -1, AbstractProcess.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getAbstractProcess_Categories(), ecorePackage.getEString(), "categories", null, 0, -1, AbstractProcess.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getAbstractProcess_Actors(), this.getActor(), null, "actors", null, 0, -1, AbstractProcess.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getAbstractProcess_Configurations(), theConfigurationPackage.getConfiguration(), null, "configurations", null, 0, -1, AbstractProcess.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getAbstractProcess_Parameters(), theParameterPackage.getParameter(), null, "parameters", null, 0, -1, AbstractProcess.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(abstractTimerEventEClass, AbstractTimerEvent.class, "AbstractTimerEvent", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getAbstractTimerEvent_Condition(), theExpressionPackage.getExpression(), null, "condition", null, 0, 1, AbstractTimerEvent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(activityEClass, Activity.class, "Activity", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getActivity_BoundaryIntermediateEvents(), this.getBoundaryEvent(), null, "BoundaryIntermediateEvents", null, 0, -1, Activity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(actorEClass, Actor.class, "Actor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getActor_Initiator(), ecorePackage.getEBoolean(), "initiator", "false", 0, 1, Actor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

        initEClass(actorFilterEClass, ActorFilter.class, "ActorFilter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(andGatewayEClass, ANDGateway.class, "ANDGateway", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(assignableEClass, Assignable.class, "Assignable", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getAssignable_Actor(), this.getActor(), null, "actor", null, 0, 1, Assignable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getAssignable_Filters(), this.getActorFilter(), null, "filters", null, 0, -1, Assignable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(associationEClass, Association.class, "Association", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getAssociation_IsDirected(), ecorePackage.getEBoolean(), "isDirected", "false", 0, 1, Association.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

        initEClass(booleanTypeEClass, BooleanType.class, "BooleanType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(boundaryEventEClass, BoundaryEvent.class, "BoundaryEvent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(boundaryMessageEventEClass, BoundaryMessageEvent.class, "BoundaryMessageEvent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(boundarySignalEventEClass, BoundarySignalEvent.class, "BoundarySignalEvent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(boundaryTimerEventEClass, BoundaryTimerEvent.class, "BoundaryTimerEvent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(businessObjectDataEClass, BusinessObjectData.class, "BusinessObjectData", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getBusinessObjectData_BusinessDataRepositoryId(), ecorePackage.getEString(), "businessDataRepositoryId", null, 0, 1, BusinessObjectData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getBusinessObjectData_CreateNewInstance(), ecorePackage.getEBoolean(), "createNewInstance", "true", 0, 1, BusinessObjectData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
        initEAttribute(getBusinessObjectData_EClassName(), ecorePackage.getEString(), "eClassName", null, 0, 1, BusinessObjectData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(businessObjectTypeEClass, BusinessObjectType.class, "BusinessObjectType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(callActivityEClass, CallActivity.class, "CallActivity", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getCallActivity_InputMappings(), this.getInputMapping(), null, "inputMappings", null, 0, -1, CallActivity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getCallActivity_OutputMappings(), this.getOutputMapping(), null, "outputMappings", null, 0, -1, CallActivity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getCallActivity_CalledActivityName(), theExpressionPackage.getExpression(), null, "calledActivityName", null, 0, 1, CallActivity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getCallActivity_CalledActivityVersion(), theExpressionPackage.getExpression(), null, "calledActivityVersion", null, 0, 1, CallActivity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(catchLinkEventEClass, CatchLinkEvent.class, "CatchLinkEvent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getCatchLinkEvent_From(), this.getThrowLinkEvent(), this.getThrowLinkEvent_To(), "from", null, 0, -1, CatchLinkEvent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(catchMessageEventEClass, CatchMessageEvent.class, "CatchMessageEvent", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(catchSignalEventEClass, CatchSignalEvent.class, "CatchSignalEvent", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(connectableElementEClass, ConnectableElement.class, "ConnectableElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getConnectableElement_Connectors(), this.getConnector(), null, "connectors", null, 0, -1, ConnectableElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getConnectableElement_Kpis(), theKpiPackage.getAbstractKPIBinding(), null, "kpis", null, 0, -1, ConnectableElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(connectorEClass, Connector.class, "Connector", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getConnector_DefinitionId(), ecorePackage.getEString(), "definitionId", "", 1, 1, Connector.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
        initEAttribute(getConnector_Event(), ecorePackage.getEString(), "event", null, 0, 1, Connector.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getConnector_IgnoreErrors(), ecorePackage.getEBoolean(), "ignoreErrors", "false", 0, 1, Connector.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
        initEAttribute(getConnector_ThrowErrorEvent(), ecorePackage.getEBoolean(), "throwErrorEvent", "false", 1, 1, Connector.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
        initEAttribute(getConnector_NamedError(), ecorePackage.getEString(), "namedError", "", 0, 1, Connector.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
        initEAttribute(getConnector_DefinitionVersion(), ecorePackage.getEString(), "definitionVersion", null, 1, 1, Connector.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getConnector_Configuration(), theConnectorConfigurationPackage.getConnectorConfiguration(), null, "configuration", null, 0, 1, Connector.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getConnector_Outputs(), theExpressionPackage.getOperation(), null, "outputs", null, 0, -1, Connector.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(containerEClass, org.bonitasoft.studio.model.process.Container.class, "Container", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getContainer_Elements(), this.getElement(), null, "elements", null, 0, -1, org.bonitasoft.studio.model.process.Container.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(contractEClass, Contract.class, "Contract", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getContract_Inputs(), this.getContractInput(), null, "inputs", null, 0, -1, Contract.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getContract_Constraints(), this.getContractConstraint(), null, "constraints", null, 0, -1, Contract.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(contractInputMappingEClass, ContractInputMapping.class, "ContractInputMapping", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getContractInputMapping_Data(), this.getData(), null, "data", null, 0, 1, ContractInputMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getContractInputMapping_SetterName(), ecorePackage.getEString(), "setterName", null, 0, 1, ContractInputMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getContractInputMapping_SetterParamType(), ecorePackage.getEString(), "setterParamType", null, 0, 1, ContractInputMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(contractInputEClass, ContractInput.class, "ContractInput", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getContractInput_Name(), ecorePackage.getEString(), "name", null, 0, 1, ContractInput.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getContractInput_Type(), this.getContractInputType(), "type", null, 0, 1, ContractInput.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getContractInput_Description(), ecorePackage.getEString(), "description", null, 0, 1, ContractInput.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getContractInput_Multiple(), ecorePackage.getEBoolean(), "multiple", null, 0, 1, ContractInput.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getContractInput_Mapping(), this.getContractInputMapping(), null, "mapping", null, 0, 1, ContractInput.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getContractInput_Inputs(), this.getContractInput(), null, "inputs", null, 0, -1, ContractInput.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getContractInput_DataReference(), ecorePackage.getEString(), "dataReference", null, 0, 1, ContractInput.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getContractInput_CreateMode(), ecorePackage.getEBoolean(), "createMode", "true", 0, 1, ContractInput.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

        addEOperation(contractInputEClass, ecorePackage.getEString(), "getJavaType", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

        initEClass(contractConstraintEClass, ContractConstraint.class, "ContractConstraint", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getContractConstraint_Expression(), ecorePackage.getEString(), "expression", null, 0, 1, ContractConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getContractConstraint_ErrorMessage(), ecorePackage.getEString(), "errorMessage", null, 0, 1, ContractConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getContractConstraint_Name(), ecorePackage.getEString(), "name", null, 0, 1, ContractConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getContractConstraint_InputNames(), ecorePackage.getEString(), "inputNames", null, 0, -1, ContractConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getContractConstraint_Description(), ecorePackage.getEString(), "description", null, 0, 1, ContractConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(contractContainerEClass, ContractContainer.class, "ContractContainer", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getContractContainer_Contract(), this.getContract(), null, "contract", null, 0, 1, ContractContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(connectionEClass, Connection.class, "Connection", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getConnection_Target(), this.getTargetElement(), this.getTargetElement_Incoming(), "target", null, 0, 1, Connection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getConnection_Source(), this.getSourceElement(), this.getSourceElement_Outgoing(), "source", null, 0, 1, Connection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(correlationEClass, Correlation.class, "Correlation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getCorrelation_CorrelationType(), this.getCorrelationTypeActive(), "correlationType", "INACTIVE", 1, 1, Correlation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
        initEReference(getCorrelation_CorrelationAssociation(), theExpressionPackage.getTableExpression(), null, "correlationAssociation", null, 1, 1, Correlation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(correlationAssociationEClass, CorrelationAssociation.class, "CorrelationAssociation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getCorrelationAssociation_CorrelationExpression(), theExpressionPackage.getAbstractExpression(), null, "CorrelationExpression", null, 0, 1, CorrelationAssociation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getCorrelationAssociation_CorrelationKey(), theExpressionPackage.getAbstractExpression(), null, "correlationKey", null, 0, 1, CorrelationAssociation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(dataEClass, Data.class, "Data", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getData_Generated(), ecorePackage.getEBoolean(), "generated", "true", 0, 1, Data.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
        initEAttribute(getData_Multiple(), ecorePackage.getEBoolean(), "multiple", null, 0, 1, Data.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getData_Transient(), ecorePackage.getEBoolean(), "transient", null, 0, 1, Data.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getData_DatasourceId(), ecorePackage.getEString(), "datasourceId", "BOS", 1, 1, Data.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
        initEReference(getData_DataType(), this.getDataType(), null, "dataType", null, 1, 1, Data.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getData_DefaultValue(), theExpressionPackage.getExpression(), null, "defaultValue", null, 0, 1, Data.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(dataAwareEClass, DataAware.class, "DataAware", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getDataAware_Data(), this.getData(), null, "data", null, 0, -1, DataAware.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(dateTypeEClass, DateType.class, "DateType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(dataTypeEClass, DataType.class, "DataType", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(documentEClass, Document.class, "Document", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getDocument_DefaultValueIdOfDocumentStore(), ecorePackage.getEString(), "defaultValueIdOfDocumentStore", null, 0, 1, Document.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getDocument_MimeType(), theExpressionPackage.getExpression(), null, "mimeType", null, 0, 1, Document.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getDocument_Url(), theExpressionPackage.getExpression(), null, "url", null, 0, 1, Document.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getDocument_DocumentType(), this.getDocumentType(), "documentType", "NONE", 1, 1, Document.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
        initEReference(getDocument_InitialMultipleContent(), theExpressionPackage.getExpression(), null, "initialMultipleContent", null, 0, 1, Document.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getDocument_Multiple(), ecorePackage.getEBoolean(), "multiple", null, 0, 1, Document.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getDocument_ContractInput(), this.getContractInput(), null, "contractInput", null, 0, 1, Document.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(doubleTypeEClass, DoubleType.class, "DoubleType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(elementEClass, Element.class, "Element", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getElement_Documentation(), ecorePackage.getEString(), "documentation", "", 0, 1, Element.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
        initEAttribute(getElement_Name(), ecorePackage.getEString(), "name", "", 1, 1, Element.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
        initEReference(getElement_TextAnnotationAttachment(), this.getTextAnnotationAttachment(), this.getTextAnnotationAttachment_Target(), "textAnnotationAttachment", null, 0, -1, Element.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(eventEClass, Event.class, "Event", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(endErrorEventEClass, EndErrorEvent.class, "EndErrorEvent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(endEventEClass, EndEvent.class, "EndEvent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(endMessageEventEClass, EndMessageEvent.class, "EndMessageEvent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(endSignalEventEClass, EndSignalEvent.class, "EndSignalEvent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(endTerminatedEventEClass, EndTerminatedEvent.class, "EndTerminatedEvent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(errorEventEClass, ErrorEvent.class, "ErrorEvent", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getErrorEvent_ErrorCode(), ecorePackage.getEString(), "errorCode", null, 0, 1, ErrorEvent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(enumTypeEClass, EnumType.class, "EnumType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getEnumType_Literals(), ecorePackage.getEString(), "literals", null, 0, -1, EnumType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(floatTypeEClass, FloatType.class, "FloatType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(flowElementEClass, FlowElement.class, "FlowElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getFlowElement_DynamicLabel(), theExpressionPackage.getExpression(), null, "dynamicLabel", null, 0, 1, FlowElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getFlowElement_DynamicDescription(), theExpressionPackage.getExpression(), null, "dynamicDescription", null, 0, 1, FlowElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getFlowElement_StepSummary(), theExpressionPackage.getExpression(), null, "stepSummary", null, 0, 1, FlowElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(formMappingEClass, FormMapping.class, "FormMapping", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getFormMapping_TargetForm(), theExpressionPackage.getExpression(), null, "targetForm", null, 0, 1, FormMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getFormMapping_Type(), this.getFormMappingType(), "type", "INTERNAL", 0, 1, FormMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
        initEAttribute(getFormMapping_Url(), ecorePackage.getEString(), "url", null, 0, 1, FormMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(gatewayEClass, Gateway.class, "Gateway", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getGateway_GatewayType(), this.getGatewayType(), "gatewayType", null, 0, 1, Gateway.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(inclusiveGatewayEClass, InclusiveGateway.class, "InclusiveGateway", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(inputMappingEClass, InputMapping.class, "InputMapping", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getInputMapping_ProcessSource(), theExpressionPackage.getExpression(), null, "processSource", null, 0, 1, InputMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getInputMapping_SubprocessTarget(), ecorePackage.getEString(), "subprocessTarget", null, 0, 1, InputMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getInputMapping_AssignationType(), this.getInputMappingAssignationType(), "assignationType", "ContractInput", 1, 1, InputMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

        initEClass(integerTypeEClass, IntegerType.class, "IntegerType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(intermediateErrorCatchEventEClass, IntermediateErrorCatchEvent.class, "IntermediateErrorCatchEvent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(intermediateCatchSignalEventEClass, IntermediateCatchSignalEvent.class, "IntermediateCatchSignalEvent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(intermediateThrowSignalEventEClass, IntermediateThrowSignalEvent.class, "IntermediateThrowSignalEvent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(intermediateCatchMessageEventEClass, IntermediateCatchMessageEvent.class, "IntermediateCatchMessageEvent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(intermediateThrowMessageEventEClass, IntermediateThrowMessageEvent.class, "IntermediateThrowMessageEvent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(intermediateCatchTimerEventEClass, IntermediateCatchTimerEvent.class, "IntermediateCatchTimerEvent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(javaObjectDataEClass, JavaObjectData.class, "JavaObjectData", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getJavaObjectData_ClassName(), ecorePackage.getEString(), "className", null, 0, 1, JavaObjectData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(javaTypeEClass, JavaType.class, "JavaType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(laneEClass, Lane.class, "Lane", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(linkEventEClass, LinkEvent.class, "LinkEvent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(longTypeEClass, LongType.class, "LongType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(mainProcessEClass, MainProcess.class, "MainProcess", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getMainProcess_BonitaVersion(), ecorePackage.getEString(), "bonitaVersion", "", 1, 1, MainProcess.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
        initEAttribute(getMainProcess_BonitaModelVersion(), ecorePackage.getEString(), "bonitaModelVersion", "5.0", 0, 1, MainProcess.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
        initEAttribute(getMainProcess_IncludedEntries(), ecorePackage.getEString(), "includedEntries", null, 0, -1, MainProcess.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getMainProcess_MessageConnections(), this.getMessageFlow(), null, "messageConnections", null, 0, -1, MainProcess.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getMainProcess_GeneratedLibs(), ecorePackage.getEString(), "generatedLibs", null, 0, -1, MainProcess.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getMainProcess_EnableValidation(), ecorePackage.getEBoolean(), "enableValidation", "true", 0, 1, MainProcess.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
        initEAttribute(getMainProcess_ConfigId(), ecorePackage.getEJavaObject(), "configId", null, 0, 1, MainProcess.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(messageEClass, Message.class, "Message", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getMessage_ThrowEvent(), ecorePackage.getEString(), "throwEvent", "", 1, 1, Message.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
        initEAttribute(getMessage_Ttl(), ecorePackage.getEString(), "ttl", "31104000000", 0, 1, Message.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
        initEReference(getMessage_Correlation(), this.getCorrelation(), null, "correlation", null, 1, 1, Message.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getMessage_Source(), this.getThrowMessageEvent(), this.getThrowMessageEvent_Events(), "source", null, 0, 1, Message.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getMessage_TargetProcessExpression(), theExpressionPackage.getExpression(), null, "targetProcessExpression", null, 0, 1, Message.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getMessage_TargetElementExpression(), theExpressionPackage.getExpression(), null, "targetElementExpression", null, 0, 1, Message.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getMessage_MessageContent(), theExpressionPackage.getTableExpression(), null, "messageContent", null, 1, 1, Message.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(messageFlowEClass, MessageFlow.class, "MessageFlow", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getMessageFlow_Target(), this.getAbstractCatchMessageEvent(), this.getAbstractCatchMessageEvent_IncomingMessag(), "target", null, 1, 1, MessageFlow.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getMessageFlow_Source(), this.getThrowMessageEvent(), this.getThrowMessageEvent_OutgoingMessages(), "source", null, 1, 1, MessageFlow.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(messageEventEClass, MessageEvent.class, "MessageEvent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(multiInstantiableEClass, MultiInstantiable.class, "MultiInstantiable", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getMultiInstantiable_Type(), this.getMultiInstanceType(), "type", "NONE", 1, 1, MultiInstantiable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
        initEAttribute(getMultiInstantiable_TestBefore(), ecorePackage.getEBooleanObject(), "testBefore", "false", 0, 1, MultiInstantiable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
        initEReference(getMultiInstantiable_LoopCondition(), theExpressionPackage.getExpression(), null, "loopCondition", null, 0, 1, MultiInstantiable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getMultiInstantiable_LoopMaximum(), theExpressionPackage.getExpression(), null, "loopMaximum", null, 0, 1, MultiInstantiable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getMultiInstantiable_UseCardinality(), ecorePackage.getEBoolean(), "useCardinality", "false", 0, 1, MultiInstantiable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
        initEReference(getMultiInstantiable_CardinalityExpression(), theExpressionPackage.getExpression(), null, "cardinalityExpression", null, 0, 1, MultiInstantiable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getMultiInstantiable_CollectionDataToMultiInstantiate(), this.getData(), null, "collectionDataToMultiInstantiate", null, 0, 1, MultiInstantiable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getMultiInstantiable_IteratorExpression(), theExpressionPackage.getExpression(), null, "iteratorExpression", null, 0, 1, MultiInstantiable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getMultiInstantiable_OutputData(), this.getData(), null, "outputData", null, 0, 1, MultiInstantiable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getMultiInstantiable_ListDataContainingOutputResults(), this.getData(), null, "listDataContainingOutputResults", null, 0, 1, MultiInstantiable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getMultiInstantiable_CompletionCondition(), theExpressionPackage.getExpression(), null, "completionCondition", null, 0, 1, MultiInstantiable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getMultiInstantiable_StoreOutput(), ecorePackage.getEBoolean(), "storeOutput", "false", 0, 1, MultiInstantiable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

        initEClass(nonInterruptingBoundaryTimerEventEClass, NonInterruptingBoundaryTimerEvent.class, "NonInterruptingBoundaryTimerEvent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(operationContainerEClass, OperationContainer.class, "OperationContainer", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getOperationContainer_Operations(), theExpressionPackage.getOperation(), null, "operations", null, 0, -1, OperationContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(outputMappingEClass, OutputMapping.class, "OutputMapping", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getOutputMapping_SubprocessSource(), ecorePackage.getEString(), "subprocessSource", null, 0, 1, OutputMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getOutputMapping_ProcessTarget(), this.getData(), null, "processTarget", null, 0, 1, OutputMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(pageFlowEClass, PageFlow.class, "PageFlow", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getPageFlow_FormMapping(), this.getFormMapping(), null, "formMapping", null, 0, 1, PageFlow.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(poolEClass, Pool.class, "Pool", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getPool_Documents(), this.getDocument(), null, "documents", null, 0, -1, Pool.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getPool_SearchIndexes(), this.getSearchIndex(), null, "searchIndexes", null, 0, -1, Pool.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getPool_DisplayName(), ecorePackage.getEString(), "displayName", null, 0, 1, Pool.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getPool_AdditionalResources(), this.getAdditionalResource(), null, "additionalResources", null, 0, -1, Pool.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(recapFlowEClass, RecapFlow.class, "RecapFlow", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getRecapFlow_OverviewFormMapping(), this.getFormMapping(), null, "overviewFormMapping", null, 0, 1, RecapFlow.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(receiveTaskEClass, ReceiveTask.class, "ReceiveTask", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(sequenceFlowEClass, SequenceFlow.class, "SequenceFlow", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getSequenceFlow_IsDefault(), ecorePackage.getEBoolean(), "isDefault", "false", 0, 1, SequenceFlow.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
        initEAttribute(getSequenceFlow_ConditionType(), this.getSequenceFlowConditionType(), "conditionType", "EXPRESSION", 0, 1, SequenceFlow.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
        initEReference(getSequenceFlow_DecisionTable(), theDecisionPackage.getDecisionTable(), null, "decisionTable", null, 1, 1, SequenceFlow.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getSequenceFlow_Condition(), theExpressionPackage.getExpression(), null, "condition", null, 0, 1, SequenceFlow.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getSequenceFlow_PathToken(), ecorePackage.getEString(), "pathToken", null, 0, 1, SequenceFlow.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(signalEventEClass, SignalEvent.class, "SignalEvent", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getSignalEvent_SignalCode(), ecorePackage.getEString(), "signalCode", null, 0, 1, SignalEvent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(sourceElementEClass, SourceElement.class, "SourceElement", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getSourceElement_Outgoing(), this.getConnection(), this.getConnection_Source(), "outgoing", null, 0, -1, SourceElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(stringTypeEClass, StringType.class, "StringType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(scriptTaskEClass, ScriptTask.class, "ScriptTask", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(searchIndexEClass, SearchIndex.class, "SearchIndex", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getSearchIndex_Name(), theExpressionPackage.getExpression(), null, "name", null, 0, 1, SearchIndex.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getSearchIndex_Value(), theExpressionPackage.getExpression(), null, "value", null, 0, 1, SearchIndex.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(sendTaskEClass, SendTask.class, "SendTask", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(serviceTaskEClass, ServiceTask.class, "ServiceTask", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(startErrorEventEClass, StartErrorEvent.class, "StartErrorEvent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(startEventEClass, StartEvent.class, "StartEvent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(startMessageEventEClass, StartMessageEvent.class, "StartMessageEvent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(startSignalEventEClass, StartSignalEvent.class, "StartSignalEvent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(startTimerEventEClass, StartTimerEvent.class, "StartTimerEvent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getStartTimerEvent_From(), ecorePackage.getEDate(), "from", null, 0, 1, StartTimerEvent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getStartTimerEvent_At(), ecorePackage.getEDate(), "at", null, 0, 1, StartTimerEvent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getStartTimerEvent_Month(), ecorePackage.getEInt(), "month", "-1", 0, 1, StartTimerEvent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
        initEAttribute(getStartTimerEvent_Day(), ecorePackage.getEInt(), "day", "-1", 0, 1, StartTimerEvent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
        initEAttribute(getStartTimerEvent_Hours(), ecorePackage.getEInt(), "hours", "-1", 0, 1, StartTimerEvent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
        initEAttribute(getStartTimerEvent_DayNumber(), ecorePackage.getEInt(), "dayNumber", "-1", 0, 1, StartTimerEvent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
        initEAttribute(getStartTimerEvent_Minutes(), ecorePackage.getEInt(), "minutes", null, 0, 1, StartTimerEvent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getStartTimerEvent_Seconds(), ecorePackage.getEInt(), "seconds", null, 0, 1, StartTimerEvent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getStartTimerEvent_ScriptType(), this.getStartTimerScriptType(), "scriptType", "GROOVY", 0, 1, StartTimerEvent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

        initEClass(subProcessEventEClass, SubProcessEvent.class, "SubProcessEvent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(taskEClass, Task.class, "Task", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getTask_OverrideActorsOfTheLane(), ecorePackage.getEBoolean(), "overrideActorsOfTheLane", "true", 0, 1, Task.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
        initEAttribute(getTask_Priority(), ecorePackage.getEInt(), "priority", "2", 0, 1, Task.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
        initEReference(getTask_ExpectedDuration(), theExpressionPackage.getExpression(), null, "expectedDuration", null, 0, 1, Task.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(targetElementEClass, TargetElement.class, "TargetElement", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getTargetElement_Incoming(), this.getConnection(), this.getConnection_Target(), "incoming", null, 0, -1, TargetElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(textAnnotationEClass, TextAnnotation.class, "TextAnnotation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getTextAnnotation_Text(), ecorePackage.getEString(), "text", null, 1, 1, TextAnnotation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(textAnnotationAttachmentEClass, TextAnnotationAttachment.class, "TextAnnotationAttachment", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getTextAnnotationAttachment_Source(), this.getTextAnnotation(), null, "source", null, 0, 1, TextAnnotationAttachment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getTextAnnotationAttachment_Target(), this.getElement(), this.getElement_TextAnnotationAttachment(), "target", null, 0, 1, TextAnnotationAttachment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(throwSignalEventEClass, ThrowSignalEvent.class, "ThrowSignalEvent", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(throwLinkEventEClass, ThrowLinkEvent.class, "ThrowLinkEvent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getThrowLinkEvent_To(), this.getCatchLinkEvent(), this.getCatchLinkEvent_From(), "to", null, 0, 1, ThrowLinkEvent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(throwMessageEventEClass, ThrowMessageEvent.class, "ThrowMessageEvent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getThrowMessageEvent_Events(), this.getMessage(), this.getMessage_Source(), "events", null, 0, -1, ThrowMessageEvent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getThrowMessageEvent_OutgoingMessages(), this.getMessageFlow(), this.getMessageFlow_Source(), "outgoingMessages", null, 0, -1, ThrowMessageEvent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(timerEventEClass, TimerEvent.class, "TimerEvent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(xmlDataEClass, XMLData.class, "XMLData", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getXMLData_Namespace(), ecorePackage.getEString(), "namespace", null, 0, 1, XMLData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getXMLData_Type(), ecorePackage.getEString(), "type", null, 0, 1, XMLData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(xmlTypeEClass, XMLType.class, "XMLType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(xorGatewayEClass, XORGateway.class, "XORGateway", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(additionalResourceEClass, AdditionalResource.class, "AdditionalResource", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getAdditionalResource_Name(), ecorePackage.getEString(), "name", null, 0, 1, AdditionalResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getAdditionalResource_Description(), ecorePackage.getEString(), "description", null, 0, 1, AdditionalResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        // Initialize enums and add enum literals
        initEEnum(correlationTypeActiveEEnum, CorrelationTypeActive.class, "CorrelationTypeActive"); //$NON-NLS-1$
        addEEnumLiteral(correlationTypeActiveEEnum, CorrelationTypeActive.INACTIVE);
        addEEnumLiteral(correlationTypeActiveEEnum, CorrelationTypeActive.KEYS);
        addEEnumLiteral(correlationTypeActiveEEnum, CorrelationTypeActive.PREDICATES);

        initEEnum(contractInputTypeEEnum, ContractInputType.class, "ContractInputType"); //$NON-NLS-1$
        addEEnumLiteral(contractInputTypeEEnum, ContractInputType.TEXT);
        addEEnumLiteral(contractInputTypeEEnum, ContractInputType.INTEGER);
        addEEnumLiteral(contractInputTypeEEnum, ContractInputType.DECIMAL);
        addEEnumLiteral(contractInputTypeEEnum, ContractInputType.BOOLEAN);
        addEEnumLiteral(contractInputTypeEEnum, ContractInputType.DATE);
        addEEnumLiteral(contractInputTypeEEnum, ContractInputType.COMPLEX);
        addEEnumLiteral(contractInputTypeEEnum, ContractInputType.FILE);
        addEEnumLiteral(contractInputTypeEEnum, ContractInputType.LONG);
        addEEnumLiteral(contractInputTypeEEnum, ContractInputType.LOCALDATE);
        addEEnumLiteral(contractInputTypeEEnum, ContractInputType.LOCALDATETIME);
        addEEnumLiteral(contractInputTypeEEnum, ContractInputType.OFFSETDATETIME);

        initEEnum(documentTypeEEnum, DocumentType.class, "DocumentType"); //$NON-NLS-1$
        addEEnumLiteral(documentTypeEEnum, DocumentType.NONE);
        addEEnumLiteral(documentTypeEEnum, DocumentType.INTERNAL);
        addEEnumLiteral(documentTypeEEnum, DocumentType.EXTERNAL);
        addEEnumLiteral(documentTypeEEnum, DocumentType.CONTRACT);

        initEEnum(formMappingTypeEEnum, FormMappingType.class, "FormMappingType"); //$NON-NLS-1$
        addEEnumLiteral(formMappingTypeEEnum, FormMappingType.INTERNAL);
        addEEnumLiteral(formMappingTypeEEnum, FormMappingType.URL);
        addEEnumLiteral(formMappingTypeEEnum, FormMappingType.NONE);

        initEEnum(gatewayTypeEEnum, GatewayType.class, "GatewayType"); //$NON-NLS-1$
        addEEnumLiteral(gatewayTypeEEnum, GatewayType.XOR);
        addEEnumLiteral(gatewayTypeEEnum, GatewayType.OR);
        addEEnumLiteral(gatewayTypeEEnum, GatewayType.COMPLEX);
        addEEnumLiteral(gatewayTypeEEnum, GatewayType.AND);

        initEEnum(inputMappingAssignationTypeEEnum, InputMappingAssignationType.class, "InputMappingAssignationType"); //$NON-NLS-1$
        addEEnumLiteral(inputMappingAssignationTypeEEnum, InputMappingAssignationType.CONTRACT_INPUT);
        addEEnumLiteral(inputMappingAssignationTypeEEnum, InputMappingAssignationType.DATA);

        initEEnum(multiInstanceTypeEEnum, MultiInstanceType.class, "MultiInstanceType"); //$NON-NLS-1$
        addEEnumLiteral(multiInstanceTypeEEnum, MultiInstanceType.NONE);
        addEEnumLiteral(multiInstanceTypeEEnum, MultiInstanceType.STANDARD);
        addEEnumLiteral(multiInstanceTypeEEnum, MultiInstanceType.PARALLEL);
        addEEnumLiteral(multiInstanceTypeEEnum, MultiInstanceType.SEQUENTIAL);

        initEEnum(sequenceFlowConditionTypeEEnum, SequenceFlowConditionType.class, "SequenceFlowConditionType"); //$NON-NLS-1$
        addEEnumLiteral(sequenceFlowConditionTypeEEnum, SequenceFlowConditionType.EXPRESSION);
        addEEnumLiteral(sequenceFlowConditionTypeEEnum, SequenceFlowConditionType.DECISION_TABLE);

        initEEnum(startTimerScriptTypeEEnum, StartTimerScriptType.class, "StartTimerScriptType"); //$NON-NLS-1$
        addEEnumLiteral(startTimerScriptTypeEEnum, StartTimerScriptType.GROOVY);
        addEEnumLiteral(startTimerScriptTypeEEnum, StartTimerScriptType.CUSTOM);
        addEEnumLiteral(startTimerScriptTypeEEnum, StartTimerScriptType.YEARLY);
        addEEnumLiteral(startTimerScriptTypeEEnum, StartTimerScriptType.YEARLY_DAY_OF_MONTH);
        addEEnumLiteral(startTimerScriptTypeEEnum, StartTimerScriptType.YEARLY_DAY_OF_YEAR);
        addEEnumLiteral(startTimerScriptTypeEEnum, StartTimerScriptType.MONTHLY);
        addEEnumLiteral(startTimerScriptTypeEEnum, StartTimerScriptType.MONTHLY_DAY_NUMBER);
        addEEnumLiteral(startTimerScriptTypeEEnum, StartTimerScriptType.MONTHLY_DAY_OF_WEEK);
        addEEnumLiteral(startTimerScriptTypeEEnum, StartTimerScriptType.WEEKLY);
        addEEnumLiteral(startTimerScriptTypeEEnum, StartTimerScriptType.DAILY);
        addEEnumLiteral(startTimerScriptTypeEEnum, StartTimerScriptType.HOURLY);
        addEEnumLiteral(startTimerScriptTypeEEnum, StartTimerScriptType.MINUTELY);
        addEEnumLiteral(startTimerScriptTypeEEnum, StartTimerScriptType.CONSTANT);

        // Create resource
        createResource(eNS_URI);
    }

} //ProcessPackageImpl
