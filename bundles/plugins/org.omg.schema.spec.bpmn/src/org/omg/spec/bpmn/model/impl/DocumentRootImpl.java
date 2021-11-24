/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.omg.spec.bpmn.model.impl;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.impl.EStringToStringMapEntryImpl;

import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;

import org.omg.spec.bpmn.model.DocumentRoot;
import org.omg.spec.bpmn.model.ModelPackage;
import org.omg.spec.bpmn.model.TActivity;
import org.omg.spec.bpmn.model.TAdHocSubProcess;
import org.omg.spec.bpmn.model.TArtifact;
import org.omg.spec.bpmn.model.TAssignment;
import org.omg.spec.bpmn.model.TAssociation;
import org.omg.spec.bpmn.model.TAuditing;
import org.omg.spec.bpmn.model.TBaseElement;
import org.omg.spec.bpmn.model.TBaseElementWithMixedContent;
import org.omg.spec.bpmn.model.TBoundaryEvent;
import org.omg.spec.bpmn.model.TBusinessRuleTask;
import org.omg.spec.bpmn.model.TCallActivity;
import org.omg.spec.bpmn.model.TCallChoreography;
import org.omg.spec.bpmn.model.TCallConversation;
import org.omg.spec.bpmn.model.TCallableElement;
import org.omg.spec.bpmn.model.TCancelEventDefinition;
import org.omg.spec.bpmn.model.TCatchEvent;
import org.omg.spec.bpmn.model.TCategory;
import org.omg.spec.bpmn.model.TCategoryValue;
import org.omg.spec.bpmn.model.TChoreography;
import org.omg.spec.bpmn.model.TChoreographyActivity;
import org.omg.spec.bpmn.model.TChoreographyTask;
import org.omg.spec.bpmn.model.TCollaboration;
import org.omg.spec.bpmn.model.TCompensateEventDefinition;
import org.omg.spec.bpmn.model.TComplexBehaviorDefinition;
import org.omg.spec.bpmn.model.TComplexGateway;
import org.omg.spec.bpmn.model.TConditionalEventDefinition;
import org.omg.spec.bpmn.model.TConversation;
import org.omg.spec.bpmn.model.TConversationAssociation;
import org.omg.spec.bpmn.model.TConversationLink;
import org.omg.spec.bpmn.model.TConversationNode;
import org.omg.spec.bpmn.model.TCorrelationKey;
import org.omg.spec.bpmn.model.TCorrelationProperty;
import org.omg.spec.bpmn.model.TCorrelationPropertyBinding;
import org.omg.spec.bpmn.model.TCorrelationPropertyRetrievalExpression;
import org.omg.spec.bpmn.model.TCorrelationSubscription;
import org.omg.spec.bpmn.model.TDataAssociation;
import org.omg.spec.bpmn.model.TDataInput;
import org.omg.spec.bpmn.model.TDataInputAssociation;
import org.omg.spec.bpmn.model.TDataObject;
import org.omg.spec.bpmn.model.TDataObjectReference;
import org.omg.spec.bpmn.model.TDataOutput;
import org.omg.spec.bpmn.model.TDataOutputAssociation;
import org.omg.spec.bpmn.model.TDataState;
import org.omg.spec.bpmn.model.TDataStore;
import org.omg.spec.bpmn.model.TDataStoreReference;
import org.omg.spec.bpmn.model.TDefinitions;
import org.omg.spec.bpmn.model.TDocumentation;
import org.omg.spec.bpmn.model.TEndEvent;
import org.omg.spec.bpmn.model.TEndPoint;
import org.omg.spec.bpmn.model.TError;
import org.omg.spec.bpmn.model.TErrorEventDefinition;
import org.omg.spec.bpmn.model.TEscalation;
import org.omg.spec.bpmn.model.TEscalationEventDefinition;
import org.omg.spec.bpmn.model.TEvent;
import org.omg.spec.bpmn.model.TEventBasedGateway;
import org.omg.spec.bpmn.model.TEventDefinition;
import org.omg.spec.bpmn.model.TExclusiveGateway;
import org.omg.spec.bpmn.model.TExpression;
import org.omg.spec.bpmn.model.TExtension;
import org.omg.spec.bpmn.model.TExtensionElements;
import org.omg.spec.bpmn.model.TFlowElement;
import org.omg.spec.bpmn.model.TFlowNode;
import org.omg.spec.bpmn.model.TFormalExpression;
import org.omg.spec.bpmn.model.TGateway;
import org.omg.spec.bpmn.model.TGlobalBusinessRuleTask;
import org.omg.spec.bpmn.model.TGlobalChoreographyTask;
import org.omg.spec.bpmn.model.TGlobalConversation;
import org.omg.spec.bpmn.model.TGlobalManualTask;
import org.omg.spec.bpmn.model.TGlobalScriptTask;
import org.omg.spec.bpmn.model.TGlobalTask;
import org.omg.spec.bpmn.model.TGlobalUserTask;
import org.omg.spec.bpmn.model.TGroup;
import org.omg.spec.bpmn.model.THumanPerformer;
import org.omg.spec.bpmn.model.TImplicitThrowEvent;
import org.omg.spec.bpmn.model.TImport;
import org.omg.spec.bpmn.model.TInclusiveGateway;
import org.omg.spec.bpmn.model.TInputOutputBinding;
import org.omg.spec.bpmn.model.TInputOutputSpecification;
import org.omg.spec.bpmn.model.TInputSet;
import org.omg.spec.bpmn.model.TInterface;
import org.omg.spec.bpmn.model.TIntermediateCatchEvent;
import org.omg.spec.bpmn.model.TIntermediateThrowEvent;
import org.omg.spec.bpmn.model.TItemDefinition;
import org.omg.spec.bpmn.model.TLane;
import org.omg.spec.bpmn.model.TLaneSet;
import org.omg.spec.bpmn.model.TLinkEventDefinition;
import org.omg.spec.bpmn.model.TLoopCharacteristics;
import org.omg.spec.bpmn.model.TManualTask;
import org.omg.spec.bpmn.model.TMessage;
import org.omg.spec.bpmn.model.TMessageEventDefinition;
import org.omg.spec.bpmn.model.TMessageFlow;
import org.omg.spec.bpmn.model.TMessageFlowAssociation;
import org.omg.spec.bpmn.model.TMonitoring;
import org.omg.spec.bpmn.model.TMultiInstanceLoopCharacteristics;
import org.omg.spec.bpmn.model.TOperation;
import org.omg.spec.bpmn.model.TOutputSet;
import org.omg.spec.bpmn.model.TParallelGateway;
import org.omg.spec.bpmn.model.TParticipant;
import org.omg.spec.bpmn.model.TParticipantAssociation;
import org.omg.spec.bpmn.model.TParticipantMultiplicity;
import org.omg.spec.bpmn.model.TPartnerEntity;
import org.omg.spec.bpmn.model.TPartnerRole;
import org.omg.spec.bpmn.model.TPerformer;
import org.omg.spec.bpmn.model.TPotentialOwner;
import org.omg.spec.bpmn.model.TProcess;
import org.omg.spec.bpmn.model.TProperty;
import org.omg.spec.bpmn.model.TReceiveTask;
import org.omg.spec.bpmn.model.TRelationship;
import org.omg.spec.bpmn.model.TRendering;
import org.omg.spec.bpmn.model.TResource;
import org.omg.spec.bpmn.model.TResourceAssignmentExpression;
import org.omg.spec.bpmn.model.TResourceParameter;
import org.omg.spec.bpmn.model.TResourceParameterBinding;
import org.omg.spec.bpmn.model.TResourceRole;
import org.omg.spec.bpmn.model.TRootElement;
import org.omg.spec.bpmn.model.TScript;
import org.omg.spec.bpmn.model.TScriptTask;
import org.omg.spec.bpmn.model.TSendTask;
import org.omg.spec.bpmn.model.TSequenceFlow;
import org.omg.spec.bpmn.model.TServiceTask;
import org.omg.spec.bpmn.model.TSignal;
import org.omg.spec.bpmn.model.TSignalEventDefinition;
import org.omg.spec.bpmn.model.TStandardLoopCharacteristics;
import org.omg.spec.bpmn.model.TStartEvent;
import org.omg.spec.bpmn.model.TSubChoreography;
import org.omg.spec.bpmn.model.TSubConversation;
import org.omg.spec.bpmn.model.TSubProcess;
import org.omg.spec.bpmn.model.TTask;
import org.omg.spec.bpmn.model.TTerminateEventDefinition;
import org.omg.spec.bpmn.model.TText;
import org.omg.spec.bpmn.model.TTextAnnotation;
import org.omg.spec.bpmn.model.TThrowEvent;
import org.omg.spec.bpmn.model.TTimerEventDefinition;
import org.omg.spec.bpmn.model.TTransaction;
import org.omg.spec.bpmn.model.TUserTask;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Document Root</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getMixed <em>Mixed</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getXSISchemaLocation <em>XSI Schema Location</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getActivity <em>Activity</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getAdHocSubProcess <em>Ad Hoc Sub Process</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getFlowElement <em>Flow Element</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getArtifact <em>Artifact</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getAssignment <em>Assignment</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getAssociation <em>Association</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getAuditing <em>Auditing</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getBaseElement <em>Base Element</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getBaseElementWithMixedContent <em>Base Element With Mixed Content</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getBoundaryEvent <em>Boundary Event</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getBusinessRuleTask <em>Business Rule Task</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getCallableElement <em>Callable Element</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getCallActivity <em>Call Activity</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getCallChoreography <em>Call Choreography</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getCallConversation <em>Call Conversation</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getConversationNode <em>Conversation Node</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getCancelEventDefinition <em>Cancel Event Definition</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getEventDefinition <em>Event Definition</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getRootElement <em>Root Element</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getCatchEvent <em>Catch Event</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getCategory <em>Category</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getCategoryValue <em>Category Value</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getChoreography <em>Choreography</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getCollaboration <em>Collaboration</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getChoreographyActivity <em>Choreography Activity</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getChoreographyTask <em>Choreography Task</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getCompensateEventDefinition <em>Compensate Event Definition</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getComplexBehaviorDefinition <em>Complex Behavior Definition</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getComplexGateway <em>Complex Gateway</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getConditionalEventDefinition <em>Conditional Event Definition</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getConversation <em>Conversation</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getConversationAssociation <em>Conversation Association</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getConversationLink <em>Conversation Link</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getCorrelationKey <em>Correlation Key</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getCorrelationProperty <em>Correlation Property</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getCorrelationPropertyBinding <em>Correlation Property Binding</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getCorrelationPropertyRetrievalExpression <em>Correlation Property Retrieval Expression</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getCorrelationSubscription <em>Correlation Subscription</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getDataAssociation <em>Data Association</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getDataInput <em>Data Input</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getDataInputAssociation <em>Data Input Association</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getDataObject <em>Data Object</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getDataObjectReference <em>Data Object Reference</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getDataOutput <em>Data Output</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getDataOutputAssociation <em>Data Output Association</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getDataState <em>Data State</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getDataStore <em>Data Store</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getDataStoreReference <em>Data Store Reference</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getDefinitions <em>Definitions</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getDocumentation <em>Documentation</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getEndEvent <em>End Event</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getEndPoint <em>End Point</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getError <em>Error</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getErrorEventDefinition <em>Error Event Definition</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getEscalation <em>Escalation</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getEscalationEventDefinition <em>Escalation Event Definition</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getEvent <em>Event</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getEventBasedGateway <em>Event Based Gateway</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getExclusiveGateway <em>Exclusive Gateway</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getExpression <em>Expression</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getExtension <em>Extension</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getExtensionElements <em>Extension Elements</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getFlowNode <em>Flow Node</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getFormalExpression <em>Formal Expression</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getGateway <em>Gateway</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getGlobalBusinessRuleTask <em>Global Business Rule Task</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getGlobalChoreographyTask <em>Global Choreography Task</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getGlobalConversation <em>Global Conversation</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getGlobalManualTask <em>Global Manual Task</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getGlobalScriptTask <em>Global Script Task</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getGlobalTask <em>Global Task</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getGlobalUserTask <em>Global User Task</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getGroup <em>Group</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getHumanPerformer <em>Human Performer</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getPerformer <em>Performer</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getResourceRole <em>Resource Role</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getImplicitThrowEvent <em>Implicit Throw Event</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getImport <em>Import</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getInclusiveGateway <em>Inclusive Gateway</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getInputSet <em>Input Set</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getInterface <em>Interface</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getIntermediateCatchEvent <em>Intermediate Catch Event</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getIntermediateThrowEvent <em>Intermediate Throw Event</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getIoBinding <em>Io Binding</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getIoSpecification <em>Io Specification</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getItemDefinition <em>Item Definition</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getLane <em>Lane</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getLaneSet <em>Lane Set</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getLinkEventDefinition <em>Link Event Definition</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getLoopCharacteristics <em>Loop Characteristics</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getManualTask <em>Manual Task</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getMessage <em>Message</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getMessageEventDefinition <em>Message Event Definition</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getMessageFlow <em>Message Flow</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getMessageFlowAssociation <em>Message Flow Association</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getMonitoring <em>Monitoring</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getMultiInstanceLoopCharacteristics <em>Multi Instance Loop Characteristics</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getOperation <em>Operation</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getOutputSet <em>Output Set</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getParallelGateway <em>Parallel Gateway</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getParticipant <em>Participant</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getParticipantAssociation <em>Participant Association</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getParticipantMultiplicity <em>Participant Multiplicity</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getPartnerEntity <em>Partner Entity</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getPartnerRole <em>Partner Role</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getPotentialOwner <em>Potential Owner</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getProcess <em>Process</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getProperty <em>Property</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getReceiveTask <em>Receive Task</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getRelationship <em>Relationship</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getRendering <em>Rendering</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getResource <em>Resource</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getResourceAssignmentExpression <em>Resource Assignment Expression</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getResourceParameter <em>Resource Parameter</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getResourceParameterBinding <em>Resource Parameter Binding</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getScript <em>Script</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getScriptTask <em>Script Task</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getSendTask <em>Send Task</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getSequenceFlow <em>Sequence Flow</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getServiceTask <em>Service Task</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getSignal <em>Signal</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getSignalEventDefinition <em>Signal Event Definition</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getStandardLoopCharacteristics <em>Standard Loop Characteristics</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getStartEvent <em>Start Event</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getSubChoreography <em>Sub Choreography</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getSubConversation <em>Sub Conversation</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getSubProcess <em>Sub Process</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getTask <em>Task</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getTerminateEventDefinition <em>Terminate Event Definition</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getText <em>Text</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getTextAnnotation <em>Text Annotation</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getThrowEvent <em>Throw Event</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getTimerEventDefinition <em>Timer Event Definition</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getTransaction <em>Transaction</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl#getUserTask <em>User Task</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DocumentRootImpl extends EObjectImpl implements DocumentRoot {
	/**
	 * The cached value of the '{@link #getMixed() <em>Mixed</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMixed()
	 * @generated
	 * @ordered
	 */
	protected FeatureMap mixed;

	/**
	 * The cached value of the '{@link #getXMLNSPrefixMap() <em>XMLNS Prefix Map</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getXMLNSPrefixMap()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, String> xMLNSPrefixMap;

	/**
	 * The cached value of the '{@link #getXSISchemaLocation() <em>XSI Schema Location</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getXSISchemaLocation()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, String> xSISchemaLocation;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DocumentRootImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.DOCUMENT_ROOT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getMixed() {
		if (mixed == null) {
			mixed = new BasicFeatureMap(this, ModelPackage.DOCUMENT_ROOT__MIXED);
		}
		return mixed;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EMap<String, String> getXMLNSPrefixMap() {
		if (xMLNSPrefixMap == null) {
			xMLNSPrefixMap = new EcoreEMap<String,String>(EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY, EStringToStringMapEntryImpl.class, this, ModelPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP);
		}
		return xMLNSPrefixMap;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EMap<String, String> getXSISchemaLocation() {
		if (xSISchemaLocation == null) {
			xSISchemaLocation = new EcoreEMap<String,String>(EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY, EStringToStringMapEntryImpl.class, this, ModelPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION);
		}
		return xSISchemaLocation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TActivity getActivity() {
		return (TActivity)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__ACTIVITY, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetActivity(TActivity newActivity, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__ACTIVITY, newActivity, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setActivity(TActivity newActivity) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__ACTIVITY, newActivity);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TAdHocSubProcess getAdHocSubProcess() {
		return (TAdHocSubProcess)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__AD_HOC_SUB_PROCESS, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAdHocSubProcess(TAdHocSubProcess newAdHocSubProcess, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__AD_HOC_SUB_PROCESS, newAdHocSubProcess, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAdHocSubProcess(TAdHocSubProcess newAdHocSubProcess) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__AD_HOC_SUB_PROCESS, newAdHocSubProcess);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TFlowElement getFlowElement() {
		return (TFlowElement)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__FLOW_ELEMENT, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetFlowElement(TFlowElement newFlowElement, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__FLOW_ELEMENT, newFlowElement, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFlowElement(TFlowElement newFlowElement) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__FLOW_ELEMENT, newFlowElement);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TArtifact getArtifact() {
		return (TArtifact)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__ARTIFACT, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetArtifact(TArtifact newArtifact, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__ARTIFACT, newArtifact, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setArtifact(TArtifact newArtifact) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__ARTIFACT, newArtifact);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TAssignment getAssignment() {
		return (TAssignment)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__ASSIGNMENT, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAssignment(TAssignment newAssignment, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__ASSIGNMENT, newAssignment, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAssignment(TAssignment newAssignment) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__ASSIGNMENT, newAssignment);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TAssociation getAssociation() {
		return (TAssociation)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__ASSOCIATION, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAssociation(TAssociation newAssociation, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__ASSOCIATION, newAssociation, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAssociation(TAssociation newAssociation) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__ASSOCIATION, newAssociation);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TAuditing getAuditing() {
		return (TAuditing)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__AUDITING, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAuditing(TAuditing newAuditing, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__AUDITING, newAuditing, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAuditing(TAuditing newAuditing) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__AUDITING, newAuditing);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TBaseElement getBaseElement() {
		return (TBaseElement)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__BASE_ELEMENT, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetBaseElement(TBaseElement newBaseElement, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__BASE_ELEMENT, newBaseElement, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBaseElement(TBaseElement newBaseElement) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__BASE_ELEMENT, newBaseElement);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TBaseElementWithMixedContent getBaseElementWithMixedContent() {
		return (TBaseElementWithMixedContent)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetBaseElementWithMixedContent(TBaseElementWithMixedContent newBaseElementWithMixedContent, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT, newBaseElementWithMixedContent, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBaseElementWithMixedContent(TBaseElementWithMixedContent newBaseElementWithMixedContent) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT, newBaseElementWithMixedContent);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TBoundaryEvent getBoundaryEvent() {
		return (TBoundaryEvent)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__BOUNDARY_EVENT, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetBoundaryEvent(TBoundaryEvent newBoundaryEvent, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__BOUNDARY_EVENT, newBoundaryEvent, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBoundaryEvent(TBoundaryEvent newBoundaryEvent) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__BOUNDARY_EVENT, newBoundaryEvent);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TBusinessRuleTask getBusinessRuleTask() {
		return (TBusinessRuleTask)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__BUSINESS_RULE_TASK, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetBusinessRuleTask(TBusinessRuleTask newBusinessRuleTask, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__BUSINESS_RULE_TASK, newBusinessRuleTask, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBusinessRuleTask(TBusinessRuleTask newBusinessRuleTask) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__BUSINESS_RULE_TASK, newBusinessRuleTask);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TCallableElement getCallableElement() {
		return (TCallableElement)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__CALLABLE_ELEMENT, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCallableElement(TCallableElement newCallableElement, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__CALLABLE_ELEMENT, newCallableElement, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCallableElement(TCallableElement newCallableElement) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__CALLABLE_ELEMENT, newCallableElement);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TCallActivity getCallActivity() {
		return (TCallActivity)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__CALL_ACTIVITY, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCallActivity(TCallActivity newCallActivity, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__CALL_ACTIVITY, newCallActivity, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCallActivity(TCallActivity newCallActivity) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__CALL_ACTIVITY, newCallActivity);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TCallChoreography getCallChoreography() {
		return (TCallChoreography)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__CALL_CHOREOGRAPHY, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCallChoreography(TCallChoreography newCallChoreography, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__CALL_CHOREOGRAPHY, newCallChoreography, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCallChoreography(TCallChoreography newCallChoreography) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__CALL_CHOREOGRAPHY, newCallChoreography);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TCallConversation getCallConversation() {
		return (TCallConversation)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__CALL_CONVERSATION, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCallConversation(TCallConversation newCallConversation, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__CALL_CONVERSATION, newCallConversation, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCallConversation(TCallConversation newCallConversation) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__CALL_CONVERSATION, newCallConversation);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TConversationNode getConversationNode() {
		return (TConversationNode)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__CONVERSATION_NODE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetConversationNode(TConversationNode newConversationNode, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__CONVERSATION_NODE, newConversationNode, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConversationNode(TConversationNode newConversationNode) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__CONVERSATION_NODE, newConversationNode);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TCancelEventDefinition getCancelEventDefinition() {
		return (TCancelEventDefinition)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__CANCEL_EVENT_DEFINITION, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCancelEventDefinition(TCancelEventDefinition newCancelEventDefinition, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__CANCEL_EVENT_DEFINITION, newCancelEventDefinition, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCancelEventDefinition(TCancelEventDefinition newCancelEventDefinition) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__CANCEL_EVENT_DEFINITION, newCancelEventDefinition);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TEventDefinition getEventDefinition() {
		return (TEventDefinition)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__EVENT_DEFINITION, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetEventDefinition(TEventDefinition newEventDefinition, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__EVENT_DEFINITION, newEventDefinition, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEventDefinition(TEventDefinition newEventDefinition) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__EVENT_DEFINITION, newEventDefinition);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TRootElement getRootElement() {
		return (TRootElement)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__ROOT_ELEMENT, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRootElement(TRootElement newRootElement, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__ROOT_ELEMENT, newRootElement, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRootElement(TRootElement newRootElement) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__ROOT_ELEMENT, newRootElement);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TCatchEvent getCatchEvent() {
		return (TCatchEvent)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__CATCH_EVENT, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCatchEvent(TCatchEvent newCatchEvent, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__CATCH_EVENT, newCatchEvent, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCatchEvent(TCatchEvent newCatchEvent) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__CATCH_EVENT, newCatchEvent);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TCategory getCategory() {
		return (TCategory)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__CATEGORY, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCategory(TCategory newCategory, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__CATEGORY, newCategory, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCategory(TCategory newCategory) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__CATEGORY, newCategory);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TCategoryValue getCategoryValue() {
		return (TCategoryValue)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__CATEGORY_VALUE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCategoryValue(TCategoryValue newCategoryValue, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__CATEGORY_VALUE, newCategoryValue, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCategoryValue(TCategoryValue newCategoryValue) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__CATEGORY_VALUE, newCategoryValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TChoreography getChoreography() {
		return (TChoreography)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__CHOREOGRAPHY, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetChoreography(TChoreography newChoreography, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__CHOREOGRAPHY, newChoreography, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setChoreography(TChoreography newChoreography) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__CHOREOGRAPHY, newChoreography);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TCollaboration getCollaboration() {
		return (TCollaboration)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__COLLABORATION, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCollaboration(TCollaboration newCollaboration, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__COLLABORATION, newCollaboration, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCollaboration(TCollaboration newCollaboration) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__COLLABORATION, newCollaboration);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TChoreographyActivity getChoreographyActivity() {
		return (TChoreographyActivity)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__CHOREOGRAPHY_ACTIVITY, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetChoreographyActivity(TChoreographyActivity newChoreographyActivity, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__CHOREOGRAPHY_ACTIVITY, newChoreographyActivity, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setChoreographyActivity(TChoreographyActivity newChoreographyActivity) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__CHOREOGRAPHY_ACTIVITY, newChoreographyActivity);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TChoreographyTask getChoreographyTask() {
		return (TChoreographyTask)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__CHOREOGRAPHY_TASK, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetChoreographyTask(TChoreographyTask newChoreographyTask, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__CHOREOGRAPHY_TASK, newChoreographyTask, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setChoreographyTask(TChoreographyTask newChoreographyTask) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__CHOREOGRAPHY_TASK, newChoreographyTask);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TCompensateEventDefinition getCompensateEventDefinition() {
		return (TCompensateEventDefinition)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__COMPENSATE_EVENT_DEFINITION, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCompensateEventDefinition(TCompensateEventDefinition newCompensateEventDefinition, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__COMPENSATE_EVENT_DEFINITION, newCompensateEventDefinition, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCompensateEventDefinition(TCompensateEventDefinition newCompensateEventDefinition) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__COMPENSATE_EVENT_DEFINITION, newCompensateEventDefinition);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TComplexBehaviorDefinition getComplexBehaviorDefinition() {
		return (TComplexBehaviorDefinition)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__COMPLEX_BEHAVIOR_DEFINITION, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetComplexBehaviorDefinition(TComplexBehaviorDefinition newComplexBehaviorDefinition, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__COMPLEX_BEHAVIOR_DEFINITION, newComplexBehaviorDefinition, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setComplexBehaviorDefinition(TComplexBehaviorDefinition newComplexBehaviorDefinition) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__COMPLEX_BEHAVIOR_DEFINITION, newComplexBehaviorDefinition);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TComplexGateway getComplexGateway() {
		return (TComplexGateway)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__COMPLEX_GATEWAY, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetComplexGateway(TComplexGateway newComplexGateway, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__COMPLEX_GATEWAY, newComplexGateway, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setComplexGateway(TComplexGateway newComplexGateway) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__COMPLEX_GATEWAY, newComplexGateway);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TConditionalEventDefinition getConditionalEventDefinition() {
		return (TConditionalEventDefinition)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__CONDITIONAL_EVENT_DEFINITION, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetConditionalEventDefinition(TConditionalEventDefinition newConditionalEventDefinition, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__CONDITIONAL_EVENT_DEFINITION, newConditionalEventDefinition, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConditionalEventDefinition(TConditionalEventDefinition newConditionalEventDefinition) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__CONDITIONAL_EVENT_DEFINITION, newConditionalEventDefinition);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TConversation getConversation() {
		return (TConversation)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__CONVERSATION, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetConversation(TConversation newConversation, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__CONVERSATION, newConversation, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConversation(TConversation newConversation) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__CONVERSATION, newConversation);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TConversationAssociation getConversationAssociation() {
		return (TConversationAssociation)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__CONVERSATION_ASSOCIATION, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetConversationAssociation(TConversationAssociation newConversationAssociation, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__CONVERSATION_ASSOCIATION, newConversationAssociation, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConversationAssociation(TConversationAssociation newConversationAssociation) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__CONVERSATION_ASSOCIATION, newConversationAssociation);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TConversationLink getConversationLink() {
		return (TConversationLink)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__CONVERSATION_LINK, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetConversationLink(TConversationLink newConversationLink, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__CONVERSATION_LINK, newConversationLink, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConversationLink(TConversationLink newConversationLink) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__CONVERSATION_LINK, newConversationLink);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TCorrelationKey getCorrelationKey() {
		return (TCorrelationKey)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__CORRELATION_KEY, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCorrelationKey(TCorrelationKey newCorrelationKey, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__CORRELATION_KEY, newCorrelationKey, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCorrelationKey(TCorrelationKey newCorrelationKey) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__CORRELATION_KEY, newCorrelationKey);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TCorrelationProperty getCorrelationProperty() {
		return (TCorrelationProperty)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__CORRELATION_PROPERTY, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCorrelationProperty(TCorrelationProperty newCorrelationProperty, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__CORRELATION_PROPERTY, newCorrelationProperty, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCorrelationProperty(TCorrelationProperty newCorrelationProperty) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__CORRELATION_PROPERTY, newCorrelationProperty);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TCorrelationPropertyBinding getCorrelationPropertyBinding() {
		return (TCorrelationPropertyBinding)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__CORRELATION_PROPERTY_BINDING, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCorrelationPropertyBinding(TCorrelationPropertyBinding newCorrelationPropertyBinding, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__CORRELATION_PROPERTY_BINDING, newCorrelationPropertyBinding, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCorrelationPropertyBinding(TCorrelationPropertyBinding newCorrelationPropertyBinding) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__CORRELATION_PROPERTY_BINDING, newCorrelationPropertyBinding);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TCorrelationPropertyRetrievalExpression getCorrelationPropertyRetrievalExpression() {
		return (TCorrelationPropertyRetrievalExpression)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__CORRELATION_PROPERTY_RETRIEVAL_EXPRESSION, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCorrelationPropertyRetrievalExpression(TCorrelationPropertyRetrievalExpression newCorrelationPropertyRetrievalExpression, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__CORRELATION_PROPERTY_RETRIEVAL_EXPRESSION, newCorrelationPropertyRetrievalExpression, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCorrelationPropertyRetrievalExpression(TCorrelationPropertyRetrievalExpression newCorrelationPropertyRetrievalExpression) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__CORRELATION_PROPERTY_RETRIEVAL_EXPRESSION, newCorrelationPropertyRetrievalExpression);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TCorrelationSubscription getCorrelationSubscription() {
		return (TCorrelationSubscription)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__CORRELATION_SUBSCRIPTION, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCorrelationSubscription(TCorrelationSubscription newCorrelationSubscription, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__CORRELATION_SUBSCRIPTION, newCorrelationSubscription, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCorrelationSubscription(TCorrelationSubscription newCorrelationSubscription) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__CORRELATION_SUBSCRIPTION, newCorrelationSubscription);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TDataAssociation getDataAssociation() {
		return (TDataAssociation)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__DATA_ASSOCIATION, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDataAssociation(TDataAssociation newDataAssociation, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__DATA_ASSOCIATION, newDataAssociation, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDataAssociation(TDataAssociation newDataAssociation) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__DATA_ASSOCIATION, newDataAssociation);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TDataInput getDataInput() {
		return (TDataInput)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__DATA_INPUT, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDataInput(TDataInput newDataInput, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__DATA_INPUT, newDataInput, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDataInput(TDataInput newDataInput) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__DATA_INPUT, newDataInput);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TDataInputAssociation getDataInputAssociation() {
		return (TDataInputAssociation)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__DATA_INPUT_ASSOCIATION, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDataInputAssociation(TDataInputAssociation newDataInputAssociation, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__DATA_INPUT_ASSOCIATION, newDataInputAssociation, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDataInputAssociation(TDataInputAssociation newDataInputAssociation) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__DATA_INPUT_ASSOCIATION, newDataInputAssociation);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TDataObject getDataObject() {
		return (TDataObject)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__DATA_OBJECT, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDataObject(TDataObject newDataObject, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__DATA_OBJECT, newDataObject, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDataObject(TDataObject newDataObject) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__DATA_OBJECT, newDataObject);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TDataObjectReference getDataObjectReference() {
		return (TDataObjectReference)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__DATA_OBJECT_REFERENCE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDataObjectReference(TDataObjectReference newDataObjectReference, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__DATA_OBJECT_REFERENCE, newDataObjectReference, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDataObjectReference(TDataObjectReference newDataObjectReference) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__DATA_OBJECT_REFERENCE, newDataObjectReference);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TDataOutput getDataOutput() {
		return (TDataOutput)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__DATA_OUTPUT, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDataOutput(TDataOutput newDataOutput, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__DATA_OUTPUT, newDataOutput, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDataOutput(TDataOutput newDataOutput) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__DATA_OUTPUT, newDataOutput);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TDataOutputAssociation getDataOutputAssociation() {
		return (TDataOutputAssociation)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__DATA_OUTPUT_ASSOCIATION, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDataOutputAssociation(TDataOutputAssociation newDataOutputAssociation, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__DATA_OUTPUT_ASSOCIATION, newDataOutputAssociation, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDataOutputAssociation(TDataOutputAssociation newDataOutputAssociation) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__DATA_OUTPUT_ASSOCIATION, newDataOutputAssociation);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TDataState getDataState() {
		return (TDataState)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__DATA_STATE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDataState(TDataState newDataState, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__DATA_STATE, newDataState, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDataState(TDataState newDataState) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__DATA_STATE, newDataState);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TDataStore getDataStore() {
		return (TDataStore)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__DATA_STORE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDataStore(TDataStore newDataStore, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__DATA_STORE, newDataStore, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDataStore(TDataStore newDataStore) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__DATA_STORE, newDataStore);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TDataStoreReference getDataStoreReference() {
		return (TDataStoreReference)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__DATA_STORE_REFERENCE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDataStoreReference(TDataStoreReference newDataStoreReference, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__DATA_STORE_REFERENCE, newDataStoreReference, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDataStoreReference(TDataStoreReference newDataStoreReference) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__DATA_STORE_REFERENCE, newDataStoreReference);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TDefinitions getDefinitions() {
		return (TDefinitions)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__DEFINITIONS, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDefinitions(TDefinitions newDefinitions, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__DEFINITIONS, newDefinitions, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDefinitions(TDefinitions newDefinitions) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__DEFINITIONS, newDefinitions);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TDocumentation getDocumentation() {
		return (TDocumentation)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__DOCUMENTATION, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDocumentation(TDocumentation newDocumentation, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__DOCUMENTATION, newDocumentation, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDocumentation(TDocumentation newDocumentation) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__DOCUMENTATION, newDocumentation);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TEndEvent getEndEvent() {
		return (TEndEvent)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__END_EVENT, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetEndEvent(TEndEvent newEndEvent, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__END_EVENT, newEndEvent, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEndEvent(TEndEvent newEndEvent) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__END_EVENT, newEndEvent);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TEndPoint getEndPoint() {
		return (TEndPoint)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__END_POINT, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetEndPoint(TEndPoint newEndPoint, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__END_POINT, newEndPoint, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEndPoint(TEndPoint newEndPoint) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__END_POINT, newEndPoint);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TError getError() {
		return (TError)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__ERROR, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetError(TError newError, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__ERROR, newError, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setError(TError newError) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__ERROR, newError);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TErrorEventDefinition getErrorEventDefinition() {
		return (TErrorEventDefinition)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__ERROR_EVENT_DEFINITION, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetErrorEventDefinition(TErrorEventDefinition newErrorEventDefinition, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__ERROR_EVENT_DEFINITION, newErrorEventDefinition, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setErrorEventDefinition(TErrorEventDefinition newErrorEventDefinition) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__ERROR_EVENT_DEFINITION, newErrorEventDefinition);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TEscalation getEscalation() {
		return (TEscalation)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__ESCALATION, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetEscalation(TEscalation newEscalation, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__ESCALATION, newEscalation, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEscalation(TEscalation newEscalation) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__ESCALATION, newEscalation);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TEscalationEventDefinition getEscalationEventDefinition() {
		return (TEscalationEventDefinition)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__ESCALATION_EVENT_DEFINITION, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetEscalationEventDefinition(TEscalationEventDefinition newEscalationEventDefinition, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__ESCALATION_EVENT_DEFINITION, newEscalationEventDefinition, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEscalationEventDefinition(TEscalationEventDefinition newEscalationEventDefinition) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__ESCALATION_EVENT_DEFINITION, newEscalationEventDefinition);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TEvent getEvent() {
		return (TEvent)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__EVENT, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetEvent(TEvent newEvent, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__EVENT, newEvent, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEvent(TEvent newEvent) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__EVENT, newEvent);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TEventBasedGateway getEventBasedGateway() {
		return (TEventBasedGateway)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__EVENT_BASED_GATEWAY, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetEventBasedGateway(TEventBasedGateway newEventBasedGateway, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__EVENT_BASED_GATEWAY, newEventBasedGateway, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEventBasedGateway(TEventBasedGateway newEventBasedGateway) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__EVENT_BASED_GATEWAY, newEventBasedGateway);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TExclusiveGateway getExclusiveGateway() {
		return (TExclusiveGateway)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__EXCLUSIVE_GATEWAY, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetExclusiveGateway(TExclusiveGateway newExclusiveGateway, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__EXCLUSIVE_GATEWAY, newExclusiveGateway, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExclusiveGateway(TExclusiveGateway newExclusiveGateway) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__EXCLUSIVE_GATEWAY, newExclusiveGateway);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TExpression getExpression() {
		return (TExpression)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__EXPRESSION, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetExpression(TExpression newExpression, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__EXPRESSION, newExpression, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExpression(TExpression newExpression) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__EXPRESSION, newExpression);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TExtension getExtension() {
		return (TExtension)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__EXTENSION, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetExtension(TExtension newExtension, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__EXTENSION, newExtension, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExtension(TExtension newExtension) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__EXTENSION, newExtension);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TExtensionElements getExtensionElements() {
		return (TExtensionElements)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetExtensionElements(TExtensionElements newExtensionElements, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS, newExtensionElements, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExtensionElements(TExtensionElements newExtensionElements) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS, newExtensionElements);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TFlowNode getFlowNode() {
		return (TFlowNode)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__FLOW_NODE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetFlowNode(TFlowNode newFlowNode, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__FLOW_NODE, newFlowNode, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFlowNode(TFlowNode newFlowNode) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__FLOW_NODE, newFlowNode);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TFormalExpression getFormalExpression() {
		return (TFormalExpression)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__FORMAL_EXPRESSION, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetFormalExpression(TFormalExpression newFormalExpression, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__FORMAL_EXPRESSION, newFormalExpression, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFormalExpression(TFormalExpression newFormalExpression) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__FORMAL_EXPRESSION, newFormalExpression);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TGateway getGateway() {
		return (TGateway)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__GATEWAY, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetGateway(TGateway newGateway, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__GATEWAY, newGateway, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TGlobalBusinessRuleTask getGlobalBusinessRuleTask() {
		return (TGlobalBusinessRuleTask)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__GLOBAL_BUSINESS_RULE_TASK, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetGlobalBusinessRuleTask(TGlobalBusinessRuleTask newGlobalBusinessRuleTask, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__GLOBAL_BUSINESS_RULE_TASK, newGlobalBusinessRuleTask, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGlobalBusinessRuleTask(TGlobalBusinessRuleTask newGlobalBusinessRuleTask) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__GLOBAL_BUSINESS_RULE_TASK, newGlobalBusinessRuleTask);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TGlobalChoreographyTask getGlobalChoreographyTask() {
		return (TGlobalChoreographyTask)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__GLOBAL_CHOREOGRAPHY_TASK, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetGlobalChoreographyTask(TGlobalChoreographyTask newGlobalChoreographyTask, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__GLOBAL_CHOREOGRAPHY_TASK, newGlobalChoreographyTask, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGlobalChoreographyTask(TGlobalChoreographyTask newGlobalChoreographyTask) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__GLOBAL_CHOREOGRAPHY_TASK, newGlobalChoreographyTask);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TGlobalConversation getGlobalConversation() {
		return (TGlobalConversation)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__GLOBAL_CONVERSATION, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetGlobalConversation(TGlobalConversation newGlobalConversation, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__GLOBAL_CONVERSATION, newGlobalConversation, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGlobalConversation(TGlobalConversation newGlobalConversation) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__GLOBAL_CONVERSATION, newGlobalConversation);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TGlobalManualTask getGlobalManualTask() {
		return (TGlobalManualTask)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__GLOBAL_MANUAL_TASK, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetGlobalManualTask(TGlobalManualTask newGlobalManualTask, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__GLOBAL_MANUAL_TASK, newGlobalManualTask, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGlobalManualTask(TGlobalManualTask newGlobalManualTask) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__GLOBAL_MANUAL_TASK, newGlobalManualTask);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TGlobalScriptTask getGlobalScriptTask() {
		return (TGlobalScriptTask)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__GLOBAL_SCRIPT_TASK, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetGlobalScriptTask(TGlobalScriptTask newGlobalScriptTask, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__GLOBAL_SCRIPT_TASK, newGlobalScriptTask, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGlobalScriptTask(TGlobalScriptTask newGlobalScriptTask) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__GLOBAL_SCRIPT_TASK, newGlobalScriptTask);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TGlobalTask getGlobalTask() {
		return (TGlobalTask)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__GLOBAL_TASK, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetGlobalTask(TGlobalTask newGlobalTask, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__GLOBAL_TASK, newGlobalTask, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGlobalTask(TGlobalTask newGlobalTask) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__GLOBAL_TASK, newGlobalTask);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TGlobalUserTask getGlobalUserTask() {
		return (TGlobalUserTask)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__GLOBAL_USER_TASK, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetGlobalUserTask(TGlobalUserTask newGlobalUserTask, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__GLOBAL_USER_TASK, newGlobalUserTask, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGlobalUserTask(TGlobalUserTask newGlobalUserTask) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__GLOBAL_USER_TASK, newGlobalUserTask);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TGroup getGroup() {
		return (TGroup)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__GROUP, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetGroup(TGroup newGroup, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__GROUP, newGroup, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGroup(TGroup newGroup) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__GROUP, newGroup);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public THumanPerformer getHumanPerformer() {
		return (THumanPerformer)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__HUMAN_PERFORMER, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetHumanPerformer(THumanPerformer newHumanPerformer, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__HUMAN_PERFORMER, newHumanPerformer, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHumanPerformer(THumanPerformer newHumanPerformer) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__HUMAN_PERFORMER, newHumanPerformer);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TPerformer getPerformer() {
		return (TPerformer)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__PERFORMER, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPerformer(TPerformer newPerformer, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__PERFORMER, newPerformer, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPerformer(TPerformer newPerformer) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__PERFORMER, newPerformer);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TResourceRole getResourceRole() {
		return (TResourceRole)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__RESOURCE_ROLE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetResourceRole(TResourceRole newResourceRole, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__RESOURCE_ROLE, newResourceRole, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setResourceRole(TResourceRole newResourceRole) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__RESOURCE_ROLE, newResourceRole);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TImplicitThrowEvent getImplicitThrowEvent() {
		return (TImplicitThrowEvent)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__IMPLICIT_THROW_EVENT, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetImplicitThrowEvent(TImplicitThrowEvent newImplicitThrowEvent, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__IMPLICIT_THROW_EVENT, newImplicitThrowEvent, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setImplicitThrowEvent(TImplicitThrowEvent newImplicitThrowEvent) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__IMPLICIT_THROW_EVENT, newImplicitThrowEvent);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TImport getImport() {
		return (TImport)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__IMPORT, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetImport(TImport newImport, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__IMPORT, newImport, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setImport(TImport newImport) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__IMPORT, newImport);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TInclusiveGateway getInclusiveGateway() {
		return (TInclusiveGateway)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__INCLUSIVE_GATEWAY, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetInclusiveGateway(TInclusiveGateway newInclusiveGateway, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__INCLUSIVE_GATEWAY, newInclusiveGateway, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInclusiveGateway(TInclusiveGateway newInclusiveGateway) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__INCLUSIVE_GATEWAY, newInclusiveGateway);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TInputSet getInputSet() {
		return (TInputSet)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__INPUT_SET, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetInputSet(TInputSet newInputSet, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__INPUT_SET, newInputSet, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInputSet(TInputSet newInputSet) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__INPUT_SET, newInputSet);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TInterface getInterface() {
		return (TInterface)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__INTERFACE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetInterface(TInterface newInterface, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__INTERFACE, newInterface, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInterface(TInterface newInterface) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__INTERFACE, newInterface);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TIntermediateCatchEvent getIntermediateCatchEvent() {
		return (TIntermediateCatchEvent)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__INTERMEDIATE_CATCH_EVENT, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetIntermediateCatchEvent(TIntermediateCatchEvent newIntermediateCatchEvent, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__INTERMEDIATE_CATCH_EVENT, newIntermediateCatchEvent, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIntermediateCatchEvent(TIntermediateCatchEvent newIntermediateCatchEvent) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__INTERMEDIATE_CATCH_EVENT, newIntermediateCatchEvent);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TIntermediateThrowEvent getIntermediateThrowEvent() {
		return (TIntermediateThrowEvent)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__INTERMEDIATE_THROW_EVENT, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetIntermediateThrowEvent(TIntermediateThrowEvent newIntermediateThrowEvent, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__INTERMEDIATE_THROW_EVENT, newIntermediateThrowEvent, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIntermediateThrowEvent(TIntermediateThrowEvent newIntermediateThrowEvent) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__INTERMEDIATE_THROW_EVENT, newIntermediateThrowEvent);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TInputOutputBinding getIoBinding() {
		return (TInputOutputBinding)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__IO_BINDING, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetIoBinding(TInputOutputBinding newIoBinding, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__IO_BINDING, newIoBinding, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIoBinding(TInputOutputBinding newIoBinding) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__IO_BINDING, newIoBinding);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TInputOutputSpecification getIoSpecification() {
		return (TInputOutputSpecification)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__IO_SPECIFICATION, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetIoSpecification(TInputOutputSpecification newIoSpecification, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__IO_SPECIFICATION, newIoSpecification, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIoSpecification(TInputOutputSpecification newIoSpecification) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__IO_SPECIFICATION, newIoSpecification);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TItemDefinition getItemDefinition() {
		return (TItemDefinition)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__ITEM_DEFINITION, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetItemDefinition(TItemDefinition newItemDefinition, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__ITEM_DEFINITION, newItemDefinition, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setItemDefinition(TItemDefinition newItemDefinition) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__ITEM_DEFINITION, newItemDefinition);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TLane getLane() {
		return (TLane)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__LANE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLane(TLane newLane, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__LANE, newLane, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLane(TLane newLane) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__LANE, newLane);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TLaneSet getLaneSet() {
		return (TLaneSet)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__LANE_SET, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLaneSet(TLaneSet newLaneSet, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__LANE_SET, newLaneSet, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLaneSet(TLaneSet newLaneSet) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__LANE_SET, newLaneSet);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TLinkEventDefinition getLinkEventDefinition() {
		return (TLinkEventDefinition)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__LINK_EVENT_DEFINITION, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLinkEventDefinition(TLinkEventDefinition newLinkEventDefinition, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__LINK_EVENT_DEFINITION, newLinkEventDefinition, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLinkEventDefinition(TLinkEventDefinition newLinkEventDefinition) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__LINK_EVENT_DEFINITION, newLinkEventDefinition);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TLoopCharacteristics getLoopCharacteristics() {
		return (TLoopCharacteristics)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__LOOP_CHARACTERISTICS, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLoopCharacteristics(TLoopCharacteristics newLoopCharacteristics, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__LOOP_CHARACTERISTICS, newLoopCharacteristics, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLoopCharacteristics(TLoopCharacteristics newLoopCharacteristics) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__LOOP_CHARACTERISTICS, newLoopCharacteristics);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TManualTask getManualTask() {
		return (TManualTask)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__MANUAL_TASK, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetManualTask(TManualTask newManualTask, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__MANUAL_TASK, newManualTask, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setManualTask(TManualTask newManualTask) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__MANUAL_TASK, newManualTask);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TMessage getMessage() {
		return (TMessage)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__MESSAGE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetMessage(TMessage newMessage, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__MESSAGE, newMessage, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMessage(TMessage newMessage) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__MESSAGE, newMessage);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TMessageEventDefinition getMessageEventDefinition() {
		return (TMessageEventDefinition)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__MESSAGE_EVENT_DEFINITION, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetMessageEventDefinition(TMessageEventDefinition newMessageEventDefinition, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__MESSAGE_EVENT_DEFINITION, newMessageEventDefinition, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMessageEventDefinition(TMessageEventDefinition newMessageEventDefinition) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__MESSAGE_EVENT_DEFINITION, newMessageEventDefinition);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TMessageFlow getMessageFlow() {
		return (TMessageFlow)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__MESSAGE_FLOW, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetMessageFlow(TMessageFlow newMessageFlow, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__MESSAGE_FLOW, newMessageFlow, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMessageFlow(TMessageFlow newMessageFlow) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__MESSAGE_FLOW, newMessageFlow);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TMessageFlowAssociation getMessageFlowAssociation() {
		return (TMessageFlowAssociation)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__MESSAGE_FLOW_ASSOCIATION, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetMessageFlowAssociation(TMessageFlowAssociation newMessageFlowAssociation, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__MESSAGE_FLOW_ASSOCIATION, newMessageFlowAssociation, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMessageFlowAssociation(TMessageFlowAssociation newMessageFlowAssociation) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__MESSAGE_FLOW_ASSOCIATION, newMessageFlowAssociation);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TMonitoring getMonitoring() {
		return (TMonitoring)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__MONITORING, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetMonitoring(TMonitoring newMonitoring, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__MONITORING, newMonitoring, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMonitoring(TMonitoring newMonitoring) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__MONITORING, newMonitoring);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TMultiInstanceLoopCharacteristics getMultiInstanceLoopCharacteristics() {
		return (TMultiInstanceLoopCharacteristics)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__MULTI_INSTANCE_LOOP_CHARACTERISTICS, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetMultiInstanceLoopCharacteristics(TMultiInstanceLoopCharacteristics newMultiInstanceLoopCharacteristics, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__MULTI_INSTANCE_LOOP_CHARACTERISTICS, newMultiInstanceLoopCharacteristics, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMultiInstanceLoopCharacteristics(TMultiInstanceLoopCharacteristics newMultiInstanceLoopCharacteristics) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__MULTI_INSTANCE_LOOP_CHARACTERISTICS, newMultiInstanceLoopCharacteristics);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TOperation getOperation() {
		return (TOperation)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__OPERATION, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOperation(TOperation newOperation, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__OPERATION, newOperation, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOperation(TOperation newOperation) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__OPERATION, newOperation);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TOutputSet getOutputSet() {
		return (TOutputSet)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__OUTPUT_SET, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOutputSet(TOutputSet newOutputSet, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__OUTPUT_SET, newOutputSet, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOutputSet(TOutputSet newOutputSet) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__OUTPUT_SET, newOutputSet);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TParallelGateway getParallelGateway() {
		return (TParallelGateway)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__PARALLEL_GATEWAY, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetParallelGateway(TParallelGateway newParallelGateway, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__PARALLEL_GATEWAY, newParallelGateway, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParallelGateway(TParallelGateway newParallelGateway) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__PARALLEL_GATEWAY, newParallelGateway);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TParticipant getParticipant() {
		return (TParticipant)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__PARTICIPANT, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetParticipant(TParticipant newParticipant, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__PARTICIPANT, newParticipant, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParticipant(TParticipant newParticipant) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__PARTICIPANT, newParticipant);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TParticipantAssociation getParticipantAssociation() {
		return (TParticipantAssociation)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__PARTICIPANT_ASSOCIATION, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetParticipantAssociation(TParticipantAssociation newParticipantAssociation, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__PARTICIPANT_ASSOCIATION, newParticipantAssociation, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParticipantAssociation(TParticipantAssociation newParticipantAssociation) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__PARTICIPANT_ASSOCIATION, newParticipantAssociation);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TParticipantMultiplicity getParticipantMultiplicity() {
		return (TParticipantMultiplicity)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__PARTICIPANT_MULTIPLICITY, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetParticipantMultiplicity(TParticipantMultiplicity newParticipantMultiplicity, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__PARTICIPANT_MULTIPLICITY, newParticipantMultiplicity, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParticipantMultiplicity(TParticipantMultiplicity newParticipantMultiplicity) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__PARTICIPANT_MULTIPLICITY, newParticipantMultiplicity);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TPartnerEntity getPartnerEntity() {
		return (TPartnerEntity)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__PARTNER_ENTITY, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPartnerEntity(TPartnerEntity newPartnerEntity, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__PARTNER_ENTITY, newPartnerEntity, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPartnerEntity(TPartnerEntity newPartnerEntity) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__PARTNER_ENTITY, newPartnerEntity);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TPartnerRole getPartnerRole() {
		return (TPartnerRole)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__PARTNER_ROLE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPartnerRole(TPartnerRole newPartnerRole, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__PARTNER_ROLE, newPartnerRole, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPartnerRole(TPartnerRole newPartnerRole) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__PARTNER_ROLE, newPartnerRole);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TPotentialOwner getPotentialOwner() {
		return (TPotentialOwner)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__POTENTIAL_OWNER, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPotentialOwner(TPotentialOwner newPotentialOwner, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__POTENTIAL_OWNER, newPotentialOwner, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPotentialOwner(TPotentialOwner newPotentialOwner) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__POTENTIAL_OWNER, newPotentialOwner);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TProcess getProcess() {
		return (TProcess)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__PROCESS, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetProcess(TProcess newProcess, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__PROCESS, newProcess, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setProcess(TProcess newProcess) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__PROCESS, newProcess);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TProperty getProperty() {
		return (TProperty)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__PROPERTY, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetProperty(TProperty newProperty, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__PROPERTY, newProperty, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setProperty(TProperty newProperty) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__PROPERTY, newProperty);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TReceiveTask getReceiveTask() {
		return (TReceiveTask)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__RECEIVE_TASK, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetReceiveTask(TReceiveTask newReceiveTask, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__RECEIVE_TASK, newReceiveTask, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReceiveTask(TReceiveTask newReceiveTask) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__RECEIVE_TASK, newReceiveTask);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TRelationship getRelationship() {
		return (TRelationship)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__RELATIONSHIP, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRelationship(TRelationship newRelationship, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__RELATIONSHIP, newRelationship, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRelationship(TRelationship newRelationship) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__RELATIONSHIP, newRelationship);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TRendering getRendering() {
		return (TRendering)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__RENDERING, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRendering(TRendering newRendering, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__RENDERING, newRendering, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRendering(TRendering newRendering) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__RENDERING, newRendering);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TResource getResource() {
		return (TResource)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__RESOURCE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetResource(TResource newResource, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__RESOURCE, newResource, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setResource(TResource newResource) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__RESOURCE, newResource);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TResourceAssignmentExpression getResourceAssignmentExpression() {
		return (TResourceAssignmentExpression)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__RESOURCE_ASSIGNMENT_EXPRESSION, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetResourceAssignmentExpression(TResourceAssignmentExpression newResourceAssignmentExpression, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__RESOURCE_ASSIGNMENT_EXPRESSION, newResourceAssignmentExpression, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setResourceAssignmentExpression(TResourceAssignmentExpression newResourceAssignmentExpression) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__RESOURCE_ASSIGNMENT_EXPRESSION, newResourceAssignmentExpression);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TResourceParameter getResourceParameter() {
		return (TResourceParameter)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__RESOURCE_PARAMETER, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetResourceParameter(TResourceParameter newResourceParameter, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__RESOURCE_PARAMETER, newResourceParameter, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setResourceParameter(TResourceParameter newResourceParameter) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__RESOURCE_PARAMETER, newResourceParameter);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TResourceParameterBinding getResourceParameterBinding() {
		return (TResourceParameterBinding)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__RESOURCE_PARAMETER_BINDING, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetResourceParameterBinding(TResourceParameterBinding newResourceParameterBinding, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__RESOURCE_PARAMETER_BINDING, newResourceParameterBinding, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setResourceParameterBinding(TResourceParameterBinding newResourceParameterBinding) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__RESOURCE_PARAMETER_BINDING, newResourceParameterBinding);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TScript getScript() {
		return (TScript)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__SCRIPT, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetScript(TScript newScript, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__SCRIPT, newScript, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setScript(TScript newScript) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__SCRIPT, newScript);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TScriptTask getScriptTask() {
		return (TScriptTask)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__SCRIPT_TASK, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetScriptTask(TScriptTask newScriptTask, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__SCRIPT_TASK, newScriptTask, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setScriptTask(TScriptTask newScriptTask) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__SCRIPT_TASK, newScriptTask);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TSendTask getSendTask() {
		return (TSendTask)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__SEND_TASK, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSendTask(TSendTask newSendTask, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__SEND_TASK, newSendTask, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSendTask(TSendTask newSendTask) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__SEND_TASK, newSendTask);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TSequenceFlow getSequenceFlow() {
		return (TSequenceFlow)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__SEQUENCE_FLOW, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSequenceFlow(TSequenceFlow newSequenceFlow, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__SEQUENCE_FLOW, newSequenceFlow, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSequenceFlow(TSequenceFlow newSequenceFlow) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__SEQUENCE_FLOW, newSequenceFlow);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TServiceTask getServiceTask() {
		return (TServiceTask)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__SERVICE_TASK, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetServiceTask(TServiceTask newServiceTask, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__SERVICE_TASK, newServiceTask, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setServiceTask(TServiceTask newServiceTask) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__SERVICE_TASK, newServiceTask);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TSignal getSignal() {
		return (TSignal)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__SIGNAL, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSignal(TSignal newSignal, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__SIGNAL, newSignal, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSignal(TSignal newSignal) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__SIGNAL, newSignal);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TSignalEventDefinition getSignalEventDefinition() {
		return (TSignalEventDefinition)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__SIGNAL_EVENT_DEFINITION, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSignalEventDefinition(TSignalEventDefinition newSignalEventDefinition, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__SIGNAL_EVENT_DEFINITION, newSignalEventDefinition, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSignalEventDefinition(TSignalEventDefinition newSignalEventDefinition) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__SIGNAL_EVENT_DEFINITION, newSignalEventDefinition);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TStandardLoopCharacteristics getStandardLoopCharacteristics() {
		return (TStandardLoopCharacteristics)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__STANDARD_LOOP_CHARACTERISTICS, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetStandardLoopCharacteristics(TStandardLoopCharacteristics newStandardLoopCharacteristics, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__STANDARD_LOOP_CHARACTERISTICS, newStandardLoopCharacteristics, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStandardLoopCharacteristics(TStandardLoopCharacteristics newStandardLoopCharacteristics) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__STANDARD_LOOP_CHARACTERISTICS, newStandardLoopCharacteristics);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TStartEvent getStartEvent() {
		return (TStartEvent)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__START_EVENT, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetStartEvent(TStartEvent newStartEvent, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__START_EVENT, newStartEvent, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStartEvent(TStartEvent newStartEvent) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__START_EVENT, newStartEvent);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TSubChoreography getSubChoreography() {
		return (TSubChoreography)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__SUB_CHOREOGRAPHY, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSubChoreography(TSubChoreography newSubChoreography, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__SUB_CHOREOGRAPHY, newSubChoreography, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSubChoreography(TSubChoreography newSubChoreography) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__SUB_CHOREOGRAPHY, newSubChoreography);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TSubConversation getSubConversation() {
		return (TSubConversation)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__SUB_CONVERSATION, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSubConversation(TSubConversation newSubConversation, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__SUB_CONVERSATION, newSubConversation, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSubConversation(TSubConversation newSubConversation) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__SUB_CONVERSATION, newSubConversation);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TSubProcess getSubProcess() {
		return (TSubProcess)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__SUB_PROCESS, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSubProcess(TSubProcess newSubProcess, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__SUB_PROCESS, newSubProcess, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSubProcess(TSubProcess newSubProcess) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__SUB_PROCESS, newSubProcess);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TTask getTask() {
		return (TTask)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__TASK, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTask(TTask newTask, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__TASK, newTask, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTask(TTask newTask) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__TASK, newTask);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TTerminateEventDefinition getTerminateEventDefinition() {
		return (TTerminateEventDefinition)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__TERMINATE_EVENT_DEFINITION, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTerminateEventDefinition(TTerminateEventDefinition newTerminateEventDefinition, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__TERMINATE_EVENT_DEFINITION, newTerminateEventDefinition, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTerminateEventDefinition(TTerminateEventDefinition newTerminateEventDefinition) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__TERMINATE_EVENT_DEFINITION, newTerminateEventDefinition);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TText getText() {
		return (TText)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__TEXT, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetText(TText newText, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__TEXT, newText, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setText(TText newText) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__TEXT, newText);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TTextAnnotation getTextAnnotation() {
		return (TTextAnnotation)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__TEXT_ANNOTATION, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTextAnnotation(TTextAnnotation newTextAnnotation, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__TEXT_ANNOTATION, newTextAnnotation, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTextAnnotation(TTextAnnotation newTextAnnotation) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__TEXT_ANNOTATION, newTextAnnotation);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TThrowEvent getThrowEvent() {
		return (TThrowEvent)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__THROW_EVENT, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetThrowEvent(TThrowEvent newThrowEvent, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__THROW_EVENT, newThrowEvent, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setThrowEvent(TThrowEvent newThrowEvent) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__THROW_EVENT, newThrowEvent);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TTimerEventDefinition getTimerEventDefinition() {
		return (TTimerEventDefinition)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__TIMER_EVENT_DEFINITION, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTimerEventDefinition(TTimerEventDefinition newTimerEventDefinition, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__TIMER_EVENT_DEFINITION, newTimerEventDefinition, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTimerEventDefinition(TTimerEventDefinition newTimerEventDefinition) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__TIMER_EVENT_DEFINITION, newTimerEventDefinition);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TTransaction getTransaction() {
		return (TTransaction)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__TRANSACTION, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTransaction(TTransaction newTransaction, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__TRANSACTION, newTransaction, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTransaction(TTransaction newTransaction) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__TRANSACTION, newTransaction);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TUserTask getUserTask() {
		return (TUserTask)getMixed().get(ModelPackage.Literals.DOCUMENT_ROOT__USER_TASK, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetUserTask(TUserTask newUserTask, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.DOCUMENT_ROOT__USER_TASK, newUserTask, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUserTask(TUserTask newUserTask) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.DOCUMENT_ROOT__USER_TASK, newUserTask);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ModelPackage.DOCUMENT_ROOT__MIXED:
				return ((InternalEList<?>)getMixed()).basicRemove(otherEnd, msgs);
			case ModelPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
				return ((InternalEList<?>)getXMLNSPrefixMap()).basicRemove(otherEnd, msgs);
			case ModelPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
				return ((InternalEList<?>)getXSISchemaLocation()).basicRemove(otherEnd, msgs);
			case ModelPackage.DOCUMENT_ROOT__ACTIVITY:
				return basicSetActivity(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__AD_HOC_SUB_PROCESS:
				return basicSetAdHocSubProcess(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__FLOW_ELEMENT:
				return basicSetFlowElement(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__ARTIFACT:
				return basicSetArtifact(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__ASSIGNMENT:
				return basicSetAssignment(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__ASSOCIATION:
				return basicSetAssociation(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__AUDITING:
				return basicSetAuditing(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__BASE_ELEMENT:
				return basicSetBaseElement(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT:
				return basicSetBaseElementWithMixedContent(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__BOUNDARY_EVENT:
				return basicSetBoundaryEvent(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__BUSINESS_RULE_TASK:
				return basicSetBusinessRuleTask(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__CALLABLE_ELEMENT:
				return basicSetCallableElement(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__CALL_ACTIVITY:
				return basicSetCallActivity(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__CALL_CHOREOGRAPHY:
				return basicSetCallChoreography(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__CALL_CONVERSATION:
				return basicSetCallConversation(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__CONVERSATION_NODE:
				return basicSetConversationNode(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__CANCEL_EVENT_DEFINITION:
				return basicSetCancelEventDefinition(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__EVENT_DEFINITION:
				return basicSetEventDefinition(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__ROOT_ELEMENT:
				return basicSetRootElement(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__CATCH_EVENT:
				return basicSetCatchEvent(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__CATEGORY:
				return basicSetCategory(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__CATEGORY_VALUE:
				return basicSetCategoryValue(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__CHOREOGRAPHY:
				return basicSetChoreography(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__COLLABORATION:
				return basicSetCollaboration(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__CHOREOGRAPHY_ACTIVITY:
				return basicSetChoreographyActivity(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__CHOREOGRAPHY_TASK:
				return basicSetChoreographyTask(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__COMPENSATE_EVENT_DEFINITION:
				return basicSetCompensateEventDefinition(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__COMPLEX_BEHAVIOR_DEFINITION:
				return basicSetComplexBehaviorDefinition(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__COMPLEX_GATEWAY:
				return basicSetComplexGateway(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__CONDITIONAL_EVENT_DEFINITION:
				return basicSetConditionalEventDefinition(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__CONVERSATION:
				return basicSetConversation(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__CONVERSATION_ASSOCIATION:
				return basicSetConversationAssociation(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__CONVERSATION_LINK:
				return basicSetConversationLink(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__CORRELATION_KEY:
				return basicSetCorrelationKey(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__CORRELATION_PROPERTY:
				return basicSetCorrelationProperty(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__CORRELATION_PROPERTY_BINDING:
				return basicSetCorrelationPropertyBinding(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__CORRELATION_PROPERTY_RETRIEVAL_EXPRESSION:
				return basicSetCorrelationPropertyRetrievalExpression(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__CORRELATION_SUBSCRIPTION:
				return basicSetCorrelationSubscription(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__DATA_ASSOCIATION:
				return basicSetDataAssociation(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__DATA_INPUT:
				return basicSetDataInput(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__DATA_INPUT_ASSOCIATION:
				return basicSetDataInputAssociation(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__DATA_OBJECT:
				return basicSetDataObject(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__DATA_OBJECT_REFERENCE:
				return basicSetDataObjectReference(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__DATA_OUTPUT:
				return basicSetDataOutput(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__DATA_OUTPUT_ASSOCIATION:
				return basicSetDataOutputAssociation(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__DATA_STATE:
				return basicSetDataState(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__DATA_STORE:
				return basicSetDataStore(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__DATA_STORE_REFERENCE:
				return basicSetDataStoreReference(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__DEFINITIONS:
				return basicSetDefinitions(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__DOCUMENTATION:
				return basicSetDocumentation(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__END_EVENT:
				return basicSetEndEvent(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__END_POINT:
				return basicSetEndPoint(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__ERROR:
				return basicSetError(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__ERROR_EVENT_DEFINITION:
				return basicSetErrorEventDefinition(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__ESCALATION:
				return basicSetEscalation(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__ESCALATION_EVENT_DEFINITION:
				return basicSetEscalationEventDefinition(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__EVENT:
				return basicSetEvent(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__EVENT_BASED_GATEWAY:
				return basicSetEventBasedGateway(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__EXCLUSIVE_GATEWAY:
				return basicSetExclusiveGateway(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__EXPRESSION:
				return basicSetExpression(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__EXTENSION:
				return basicSetExtension(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__EXTENSION_ELEMENTS:
				return basicSetExtensionElements(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__FLOW_NODE:
				return basicSetFlowNode(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__FORMAL_EXPRESSION:
				return basicSetFormalExpression(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__GATEWAY:
				return basicSetGateway(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__GLOBAL_BUSINESS_RULE_TASK:
				return basicSetGlobalBusinessRuleTask(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__GLOBAL_CHOREOGRAPHY_TASK:
				return basicSetGlobalChoreographyTask(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__GLOBAL_CONVERSATION:
				return basicSetGlobalConversation(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__GLOBAL_MANUAL_TASK:
				return basicSetGlobalManualTask(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__GLOBAL_SCRIPT_TASK:
				return basicSetGlobalScriptTask(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__GLOBAL_TASK:
				return basicSetGlobalTask(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__GLOBAL_USER_TASK:
				return basicSetGlobalUserTask(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__GROUP:
				return basicSetGroup(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__HUMAN_PERFORMER:
				return basicSetHumanPerformer(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__PERFORMER:
				return basicSetPerformer(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__RESOURCE_ROLE:
				return basicSetResourceRole(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__IMPLICIT_THROW_EVENT:
				return basicSetImplicitThrowEvent(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__IMPORT:
				return basicSetImport(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__INCLUSIVE_GATEWAY:
				return basicSetInclusiveGateway(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__INPUT_SET:
				return basicSetInputSet(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__INTERFACE:
				return basicSetInterface(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__INTERMEDIATE_CATCH_EVENT:
				return basicSetIntermediateCatchEvent(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__INTERMEDIATE_THROW_EVENT:
				return basicSetIntermediateThrowEvent(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__IO_BINDING:
				return basicSetIoBinding(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__IO_SPECIFICATION:
				return basicSetIoSpecification(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__ITEM_DEFINITION:
				return basicSetItemDefinition(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__LANE:
				return basicSetLane(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__LANE_SET:
				return basicSetLaneSet(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__LINK_EVENT_DEFINITION:
				return basicSetLinkEventDefinition(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__LOOP_CHARACTERISTICS:
				return basicSetLoopCharacteristics(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__MANUAL_TASK:
				return basicSetManualTask(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__MESSAGE:
				return basicSetMessage(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__MESSAGE_EVENT_DEFINITION:
				return basicSetMessageEventDefinition(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__MESSAGE_FLOW:
				return basicSetMessageFlow(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__MESSAGE_FLOW_ASSOCIATION:
				return basicSetMessageFlowAssociation(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__MONITORING:
				return basicSetMonitoring(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__MULTI_INSTANCE_LOOP_CHARACTERISTICS:
				return basicSetMultiInstanceLoopCharacteristics(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__OPERATION:
				return basicSetOperation(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__OUTPUT_SET:
				return basicSetOutputSet(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__PARALLEL_GATEWAY:
				return basicSetParallelGateway(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__PARTICIPANT:
				return basicSetParticipant(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__PARTICIPANT_ASSOCIATION:
				return basicSetParticipantAssociation(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__PARTICIPANT_MULTIPLICITY:
				return basicSetParticipantMultiplicity(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__PARTNER_ENTITY:
				return basicSetPartnerEntity(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__PARTNER_ROLE:
				return basicSetPartnerRole(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__POTENTIAL_OWNER:
				return basicSetPotentialOwner(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__PROCESS:
				return basicSetProcess(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__PROPERTY:
				return basicSetProperty(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__RECEIVE_TASK:
				return basicSetReceiveTask(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__RELATIONSHIP:
				return basicSetRelationship(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__RENDERING:
				return basicSetRendering(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__RESOURCE:
				return basicSetResource(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__RESOURCE_ASSIGNMENT_EXPRESSION:
				return basicSetResourceAssignmentExpression(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__RESOURCE_PARAMETER:
				return basicSetResourceParameter(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__RESOURCE_PARAMETER_BINDING:
				return basicSetResourceParameterBinding(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__SCRIPT:
				return basicSetScript(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__SCRIPT_TASK:
				return basicSetScriptTask(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__SEND_TASK:
				return basicSetSendTask(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__SEQUENCE_FLOW:
				return basicSetSequenceFlow(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__SERVICE_TASK:
				return basicSetServiceTask(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__SIGNAL:
				return basicSetSignal(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__SIGNAL_EVENT_DEFINITION:
				return basicSetSignalEventDefinition(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__STANDARD_LOOP_CHARACTERISTICS:
				return basicSetStandardLoopCharacteristics(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__START_EVENT:
				return basicSetStartEvent(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__SUB_CHOREOGRAPHY:
				return basicSetSubChoreography(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__SUB_CONVERSATION:
				return basicSetSubConversation(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__SUB_PROCESS:
				return basicSetSubProcess(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__TASK:
				return basicSetTask(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__TERMINATE_EVENT_DEFINITION:
				return basicSetTerminateEventDefinition(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__TEXT:
				return basicSetText(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__TEXT_ANNOTATION:
				return basicSetTextAnnotation(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__THROW_EVENT:
				return basicSetThrowEvent(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__TIMER_EVENT_DEFINITION:
				return basicSetTimerEventDefinition(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__TRANSACTION:
				return basicSetTransaction(null, msgs);
			case ModelPackage.DOCUMENT_ROOT__USER_TASK:
				return basicSetUserTask(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ModelPackage.DOCUMENT_ROOT__MIXED:
				if (coreType) return getMixed();
				return ((FeatureMap.Internal)getMixed()).getWrapper();
			case ModelPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
				if (coreType) return getXMLNSPrefixMap();
				else return getXMLNSPrefixMap().map();
			case ModelPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
				if (coreType) return getXSISchemaLocation();
				else return getXSISchemaLocation().map();
			case ModelPackage.DOCUMENT_ROOT__ACTIVITY:
				return getActivity();
			case ModelPackage.DOCUMENT_ROOT__AD_HOC_SUB_PROCESS:
				return getAdHocSubProcess();
			case ModelPackage.DOCUMENT_ROOT__FLOW_ELEMENT:
				return getFlowElement();
			case ModelPackage.DOCUMENT_ROOT__ARTIFACT:
				return getArtifact();
			case ModelPackage.DOCUMENT_ROOT__ASSIGNMENT:
				return getAssignment();
			case ModelPackage.DOCUMENT_ROOT__ASSOCIATION:
				return getAssociation();
			case ModelPackage.DOCUMENT_ROOT__AUDITING:
				return getAuditing();
			case ModelPackage.DOCUMENT_ROOT__BASE_ELEMENT:
				return getBaseElement();
			case ModelPackage.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT:
				return getBaseElementWithMixedContent();
			case ModelPackage.DOCUMENT_ROOT__BOUNDARY_EVENT:
				return getBoundaryEvent();
			case ModelPackage.DOCUMENT_ROOT__BUSINESS_RULE_TASK:
				return getBusinessRuleTask();
			case ModelPackage.DOCUMENT_ROOT__CALLABLE_ELEMENT:
				return getCallableElement();
			case ModelPackage.DOCUMENT_ROOT__CALL_ACTIVITY:
				return getCallActivity();
			case ModelPackage.DOCUMENT_ROOT__CALL_CHOREOGRAPHY:
				return getCallChoreography();
			case ModelPackage.DOCUMENT_ROOT__CALL_CONVERSATION:
				return getCallConversation();
			case ModelPackage.DOCUMENT_ROOT__CONVERSATION_NODE:
				return getConversationNode();
			case ModelPackage.DOCUMENT_ROOT__CANCEL_EVENT_DEFINITION:
				return getCancelEventDefinition();
			case ModelPackage.DOCUMENT_ROOT__EVENT_DEFINITION:
				return getEventDefinition();
			case ModelPackage.DOCUMENT_ROOT__ROOT_ELEMENT:
				return getRootElement();
			case ModelPackage.DOCUMENT_ROOT__CATCH_EVENT:
				return getCatchEvent();
			case ModelPackage.DOCUMENT_ROOT__CATEGORY:
				return getCategory();
			case ModelPackage.DOCUMENT_ROOT__CATEGORY_VALUE:
				return getCategoryValue();
			case ModelPackage.DOCUMENT_ROOT__CHOREOGRAPHY:
				return getChoreography();
			case ModelPackage.DOCUMENT_ROOT__COLLABORATION:
				return getCollaboration();
			case ModelPackage.DOCUMENT_ROOT__CHOREOGRAPHY_ACTIVITY:
				return getChoreographyActivity();
			case ModelPackage.DOCUMENT_ROOT__CHOREOGRAPHY_TASK:
				return getChoreographyTask();
			case ModelPackage.DOCUMENT_ROOT__COMPENSATE_EVENT_DEFINITION:
				return getCompensateEventDefinition();
			case ModelPackage.DOCUMENT_ROOT__COMPLEX_BEHAVIOR_DEFINITION:
				return getComplexBehaviorDefinition();
			case ModelPackage.DOCUMENT_ROOT__COMPLEX_GATEWAY:
				return getComplexGateway();
			case ModelPackage.DOCUMENT_ROOT__CONDITIONAL_EVENT_DEFINITION:
				return getConditionalEventDefinition();
			case ModelPackage.DOCUMENT_ROOT__CONVERSATION:
				return getConversation();
			case ModelPackage.DOCUMENT_ROOT__CONVERSATION_ASSOCIATION:
				return getConversationAssociation();
			case ModelPackage.DOCUMENT_ROOT__CONVERSATION_LINK:
				return getConversationLink();
			case ModelPackage.DOCUMENT_ROOT__CORRELATION_KEY:
				return getCorrelationKey();
			case ModelPackage.DOCUMENT_ROOT__CORRELATION_PROPERTY:
				return getCorrelationProperty();
			case ModelPackage.DOCUMENT_ROOT__CORRELATION_PROPERTY_BINDING:
				return getCorrelationPropertyBinding();
			case ModelPackage.DOCUMENT_ROOT__CORRELATION_PROPERTY_RETRIEVAL_EXPRESSION:
				return getCorrelationPropertyRetrievalExpression();
			case ModelPackage.DOCUMENT_ROOT__CORRELATION_SUBSCRIPTION:
				return getCorrelationSubscription();
			case ModelPackage.DOCUMENT_ROOT__DATA_ASSOCIATION:
				return getDataAssociation();
			case ModelPackage.DOCUMENT_ROOT__DATA_INPUT:
				return getDataInput();
			case ModelPackage.DOCUMENT_ROOT__DATA_INPUT_ASSOCIATION:
				return getDataInputAssociation();
			case ModelPackage.DOCUMENT_ROOT__DATA_OBJECT:
				return getDataObject();
			case ModelPackage.DOCUMENT_ROOT__DATA_OBJECT_REFERENCE:
				return getDataObjectReference();
			case ModelPackage.DOCUMENT_ROOT__DATA_OUTPUT:
				return getDataOutput();
			case ModelPackage.DOCUMENT_ROOT__DATA_OUTPUT_ASSOCIATION:
				return getDataOutputAssociation();
			case ModelPackage.DOCUMENT_ROOT__DATA_STATE:
				return getDataState();
			case ModelPackage.DOCUMENT_ROOT__DATA_STORE:
				return getDataStore();
			case ModelPackage.DOCUMENT_ROOT__DATA_STORE_REFERENCE:
				return getDataStoreReference();
			case ModelPackage.DOCUMENT_ROOT__DEFINITIONS:
				return getDefinitions();
			case ModelPackage.DOCUMENT_ROOT__DOCUMENTATION:
				return getDocumentation();
			case ModelPackage.DOCUMENT_ROOT__END_EVENT:
				return getEndEvent();
			case ModelPackage.DOCUMENT_ROOT__END_POINT:
				return getEndPoint();
			case ModelPackage.DOCUMENT_ROOT__ERROR:
				return getError();
			case ModelPackage.DOCUMENT_ROOT__ERROR_EVENT_DEFINITION:
				return getErrorEventDefinition();
			case ModelPackage.DOCUMENT_ROOT__ESCALATION:
				return getEscalation();
			case ModelPackage.DOCUMENT_ROOT__ESCALATION_EVENT_DEFINITION:
				return getEscalationEventDefinition();
			case ModelPackage.DOCUMENT_ROOT__EVENT:
				return getEvent();
			case ModelPackage.DOCUMENT_ROOT__EVENT_BASED_GATEWAY:
				return getEventBasedGateway();
			case ModelPackage.DOCUMENT_ROOT__EXCLUSIVE_GATEWAY:
				return getExclusiveGateway();
			case ModelPackage.DOCUMENT_ROOT__EXPRESSION:
				return getExpression();
			case ModelPackage.DOCUMENT_ROOT__EXTENSION:
				return getExtension();
			case ModelPackage.DOCUMENT_ROOT__EXTENSION_ELEMENTS:
				return getExtensionElements();
			case ModelPackage.DOCUMENT_ROOT__FLOW_NODE:
				return getFlowNode();
			case ModelPackage.DOCUMENT_ROOT__FORMAL_EXPRESSION:
				return getFormalExpression();
			case ModelPackage.DOCUMENT_ROOT__GATEWAY:
				return getGateway();
			case ModelPackage.DOCUMENT_ROOT__GLOBAL_BUSINESS_RULE_TASK:
				return getGlobalBusinessRuleTask();
			case ModelPackage.DOCUMENT_ROOT__GLOBAL_CHOREOGRAPHY_TASK:
				return getGlobalChoreographyTask();
			case ModelPackage.DOCUMENT_ROOT__GLOBAL_CONVERSATION:
				return getGlobalConversation();
			case ModelPackage.DOCUMENT_ROOT__GLOBAL_MANUAL_TASK:
				return getGlobalManualTask();
			case ModelPackage.DOCUMENT_ROOT__GLOBAL_SCRIPT_TASK:
				return getGlobalScriptTask();
			case ModelPackage.DOCUMENT_ROOT__GLOBAL_TASK:
				return getGlobalTask();
			case ModelPackage.DOCUMENT_ROOT__GLOBAL_USER_TASK:
				return getGlobalUserTask();
			case ModelPackage.DOCUMENT_ROOT__GROUP:
				return getGroup();
			case ModelPackage.DOCUMENT_ROOT__HUMAN_PERFORMER:
				return getHumanPerformer();
			case ModelPackage.DOCUMENT_ROOT__PERFORMER:
				return getPerformer();
			case ModelPackage.DOCUMENT_ROOT__RESOURCE_ROLE:
				return getResourceRole();
			case ModelPackage.DOCUMENT_ROOT__IMPLICIT_THROW_EVENT:
				return getImplicitThrowEvent();
			case ModelPackage.DOCUMENT_ROOT__IMPORT:
				return getImport();
			case ModelPackage.DOCUMENT_ROOT__INCLUSIVE_GATEWAY:
				return getInclusiveGateway();
			case ModelPackage.DOCUMENT_ROOT__INPUT_SET:
				return getInputSet();
			case ModelPackage.DOCUMENT_ROOT__INTERFACE:
				return getInterface();
			case ModelPackage.DOCUMENT_ROOT__INTERMEDIATE_CATCH_EVENT:
				return getIntermediateCatchEvent();
			case ModelPackage.DOCUMENT_ROOT__INTERMEDIATE_THROW_EVENT:
				return getIntermediateThrowEvent();
			case ModelPackage.DOCUMENT_ROOT__IO_BINDING:
				return getIoBinding();
			case ModelPackage.DOCUMENT_ROOT__IO_SPECIFICATION:
				return getIoSpecification();
			case ModelPackage.DOCUMENT_ROOT__ITEM_DEFINITION:
				return getItemDefinition();
			case ModelPackage.DOCUMENT_ROOT__LANE:
				return getLane();
			case ModelPackage.DOCUMENT_ROOT__LANE_SET:
				return getLaneSet();
			case ModelPackage.DOCUMENT_ROOT__LINK_EVENT_DEFINITION:
				return getLinkEventDefinition();
			case ModelPackage.DOCUMENT_ROOT__LOOP_CHARACTERISTICS:
				return getLoopCharacteristics();
			case ModelPackage.DOCUMENT_ROOT__MANUAL_TASK:
				return getManualTask();
			case ModelPackage.DOCUMENT_ROOT__MESSAGE:
				return getMessage();
			case ModelPackage.DOCUMENT_ROOT__MESSAGE_EVENT_DEFINITION:
				return getMessageEventDefinition();
			case ModelPackage.DOCUMENT_ROOT__MESSAGE_FLOW:
				return getMessageFlow();
			case ModelPackage.DOCUMENT_ROOT__MESSAGE_FLOW_ASSOCIATION:
				return getMessageFlowAssociation();
			case ModelPackage.DOCUMENT_ROOT__MONITORING:
				return getMonitoring();
			case ModelPackage.DOCUMENT_ROOT__MULTI_INSTANCE_LOOP_CHARACTERISTICS:
				return getMultiInstanceLoopCharacteristics();
			case ModelPackage.DOCUMENT_ROOT__OPERATION:
				return getOperation();
			case ModelPackage.DOCUMENT_ROOT__OUTPUT_SET:
				return getOutputSet();
			case ModelPackage.DOCUMENT_ROOT__PARALLEL_GATEWAY:
				return getParallelGateway();
			case ModelPackage.DOCUMENT_ROOT__PARTICIPANT:
				return getParticipant();
			case ModelPackage.DOCUMENT_ROOT__PARTICIPANT_ASSOCIATION:
				return getParticipantAssociation();
			case ModelPackage.DOCUMENT_ROOT__PARTICIPANT_MULTIPLICITY:
				return getParticipantMultiplicity();
			case ModelPackage.DOCUMENT_ROOT__PARTNER_ENTITY:
				return getPartnerEntity();
			case ModelPackage.DOCUMENT_ROOT__PARTNER_ROLE:
				return getPartnerRole();
			case ModelPackage.DOCUMENT_ROOT__POTENTIAL_OWNER:
				return getPotentialOwner();
			case ModelPackage.DOCUMENT_ROOT__PROCESS:
				return getProcess();
			case ModelPackage.DOCUMENT_ROOT__PROPERTY:
				return getProperty();
			case ModelPackage.DOCUMENT_ROOT__RECEIVE_TASK:
				return getReceiveTask();
			case ModelPackage.DOCUMENT_ROOT__RELATIONSHIP:
				return getRelationship();
			case ModelPackage.DOCUMENT_ROOT__RENDERING:
				return getRendering();
			case ModelPackage.DOCUMENT_ROOT__RESOURCE:
				return getResource();
			case ModelPackage.DOCUMENT_ROOT__RESOURCE_ASSIGNMENT_EXPRESSION:
				return getResourceAssignmentExpression();
			case ModelPackage.DOCUMENT_ROOT__RESOURCE_PARAMETER:
				return getResourceParameter();
			case ModelPackage.DOCUMENT_ROOT__RESOURCE_PARAMETER_BINDING:
				return getResourceParameterBinding();
			case ModelPackage.DOCUMENT_ROOT__SCRIPT:
				return getScript();
			case ModelPackage.DOCUMENT_ROOT__SCRIPT_TASK:
				return getScriptTask();
			case ModelPackage.DOCUMENT_ROOT__SEND_TASK:
				return getSendTask();
			case ModelPackage.DOCUMENT_ROOT__SEQUENCE_FLOW:
				return getSequenceFlow();
			case ModelPackage.DOCUMENT_ROOT__SERVICE_TASK:
				return getServiceTask();
			case ModelPackage.DOCUMENT_ROOT__SIGNAL:
				return getSignal();
			case ModelPackage.DOCUMENT_ROOT__SIGNAL_EVENT_DEFINITION:
				return getSignalEventDefinition();
			case ModelPackage.DOCUMENT_ROOT__STANDARD_LOOP_CHARACTERISTICS:
				return getStandardLoopCharacteristics();
			case ModelPackage.DOCUMENT_ROOT__START_EVENT:
				return getStartEvent();
			case ModelPackage.DOCUMENT_ROOT__SUB_CHOREOGRAPHY:
				return getSubChoreography();
			case ModelPackage.DOCUMENT_ROOT__SUB_CONVERSATION:
				return getSubConversation();
			case ModelPackage.DOCUMENT_ROOT__SUB_PROCESS:
				return getSubProcess();
			case ModelPackage.DOCUMENT_ROOT__TASK:
				return getTask();
			case ModelPackage.DOCUMENT_ROOT__TERMINATE_EVENT_DEFINITION:
				return getTerminateEventDefinition();
			case ModelPackage.DOCUMENT_ROOT__TEXT:
				return getText();
			case ModelPackage.DOCUMENT_ROOT__TEXT_ANNOTATION:
				return getTextAnnotation();
			case ModelPackage.DOCUMENT_ROOT__THROW_EVENT:
				return getThrowEvent();
			case ModelPackage.DOCUMENT_ROOT__TIMER_EVENT_DEFINITION:
				return getTimerEventDefinition();
			case ModelPackage.DOCUMENT_ROOT__TRANSACTION:
				return getTransaction();
			case ModelPackage.DOCUMENT_ROOT__USER_TASK:
				return getUserTask();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ModelPackage.DOCUMENT_ROOT__MIXED:
				((FeatureMap.Internal)getMixed()).set(newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
				((EStructuralFeature.Setting)getXMLNSPrefixMap()).set(newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
				((EStructuralFeature.Setting)getXSISchemaLocation()).set(newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__ACTIVITY:
				setActivity((TActivity)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__AD_HOC_SUB_PROCESS:
				setAdHocSubProcess((TAdHocSubProcess)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__FLOW_ELEMENT:
				setFlowElement((TFlowElement)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__ARTIFACT:
				setArtifact((TArtifact)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__ASSIGNMENT:
				setAssignment((TAssignment)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__ASSOCIATION:
				setAssociation((TAssociation)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__AUDITING:
				setAuditing((TAuditing)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__BASE_ELEMENT:
				setBaseElement((TBaseElement)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT:
				setBaseElementWithMixedContent((TBaseElementWithMixedContent)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__BOUNDARY_EVENT:
				setBoundaryEvent((TBoundaryEvent)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__BUSINESS_RULE_TASK:
				setBusinessRuleTask((TBusinessRuleTask)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__CALLABLE_ELEMENT:
				setCallableElement((TCallableElement)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__CALL_ACTIVITY:
				setCallActivity((TCallActivity)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__CALL_CHOREOGRAPHY:
				setCallChoreography((TCallChoreography)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__CALL_CONVERSATION:
				setCallConversation((TCallConversation)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__CONVERSATION_NODE:
				setConversationNode((TConversationNode)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__CANCEL_EVENT_DEFINITION:
				setCancelEventDefinition((TCancelEventDefinition)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__EVENT_DEFINITION:
				setEventDefinition((TEventDefinition)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__ROOT_ELEMENT:
				setRootElement((TRootElement)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__CATCH_EVENT:
				setCatchEvent((TCatchEvent)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__CATEGORY:
				setCategory((TCategory)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__CATEGORY_VALUE:
				setCategoryValue((TCategoryValue)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__CHOREOGRAPHY:
				setChoreography((TChoreography)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__COLLABORATION:
				setCollaboration((TCollaboration)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__CHOREOGRAPHY_ACTIVITY:
				setChoreographyActivity((TChoreographyActivity)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__CHOREOGRAPHY_TASK:
				setChoreographyTask((TChoreographyTask)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__COMPENSATE_EVENT_DEFINITION:
				setCompensateEventDefinition((TCompensateEventDefinition)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__COMPLEX_BEHAVIOR_DEFINITION:
				setComplexBehaviorDefinition((TComplexBehaviorDefinition)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__COMPLEX_GATEWAY:
				setComplexGateway((TComplexGateway)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__CONDITIONAL_EVENT_DEFINITION:
				setConditionalEventDefinition((TConditionalEventDefinition)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__CONVERSATION:
				setConversation((TConversation)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__CONVERSATION_ASSOCIATION:
				setConversationAssociation((TConversationAssociation)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__CONVERSATION_LINK:
				setConversationLink((TConversationLink)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__CORRELATION_KEY:
				setCorrelationKey((TCorrelationKey)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__CORRELATION_PROPERTY:
				setCorrelationProperty((TCorrelationProperty)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__CORRELATION_PROPERTY_BINDING:
				setCorrelationPropertyBinding((TCorrelationPropertyBinding)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__CORRELATION_PROPERTY_RETRIEVAL_EXPRESSION:
				setCorrelationPropertyRetrievalExpression((TCorrelationPropertyRetrievalExpression)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__CORRELATION_SUBSCRIPTION:
				setCorrelationSubscription((TCorrelationSubscription)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__DATA_ASSOCIATION:
				setDataAssociation((TDataAssociation)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__DATA_INPUT:
				setDataInput((TDataInput)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__DATA_INPUT_ASSOCIATION:
				setDataInputAssociation((TDataInputAssociation)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__DATA_OBJECT:
				setDataObject((TDataObject)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__DATA_OBJECT_REFERENCE:
				setDataObjectReference((TDataObjectReference)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__DATA_OUTPUT:
				setDataOutput((TDataOutput)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__DATA_OUTPUT_ASSOCIATION:
				setDataOutputAssociation((TDataOutputAssociation)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__DATA_STATE:
				setDataState((TDataState)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__DATA_STORE:
				setDataStore((TDataStore)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__DATA_STORE_REFERENCE:
				setDataStoreReference((TDataStoreReference)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__DEFINITIONS:
				setDefinitions((TDefinitions)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__DOCUMENTATION:
				setDocumentation((TDocumentation)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__END_EVENT:
				setEndEvent((TEndEvent)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__END_POINT:
				setEndPoint((TEndPoint)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__ERROR:
				setError((TError)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__ERROR_EVENT_DEFINITION:
				setErrorEventDefinition((TErrorEventDefinition)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__ESCALATION:
				setEscalation((TEscalation)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__ESCALATION_EVENT_DEFINITION:
				setEscalationEventDefinition((TEscalationEventDefinition)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__EVENT:
				setEvent((TEvent)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__EVENT_BASED_GATEWAY:
				setEventBasedGateway((TEventBasedGateway)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__EXCLUSIVE_GATEWAY:
				setExclusiveGateway((TExclusiveGateway)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__EXPRESSION:
				setExpression((TExpression)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__EXTENSION:
				setExtension((TExtension)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__EXTENSION_ELEMENTS:
				setExtensionElements((TExtensionElements)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__FLOW_NODE:
				setFlowNode((TFlowNode)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__FORMAL_EXPRESSION:
				setFormalExpression((TFormalExpression)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__GLOBAL_BUSINESS_RULE_TASK:
				setGlobalBusinessRuleTask((TGlobalBusinessRuleTask)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__GLOBAL_CHOREOGRAPHY_TASK:
				setGlobalChoreographyTask((TGlobalChoreographyTask)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__GLOBAL_CONVERSATION:
				setGlobalConversation((TGlobalConversation)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__GLOBAL_MANUAL_TASK:
				setGlobalManualTask((TGlobalManualTask)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__GLOBAL_SCRIPT_TASK:
				setGlobalScriptTask((TGlobalScriptTask)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__GLOBAL_TASK:
				setGlobalTask((TGlobalTask)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__GLOBAL_USER_TASK:
				setGlobalUserTask((TGlobalUserTask)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__GROUP:
				setGroup((TGroup)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__HUMAN_PERFORMER:
				setHumanPerformer((THumanPerformer)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__PERFORMER:
				setPerformer((TPerformer)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__RESOURCE_ROLE:
				setResourceRole((TResourceRole)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__IMPLICIT_THROW_EVENT:
				setImplicitThrowEvent((TImplicitThrowEvent)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__IMPORT:
				setImport((TImport)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__INCLUSIVE_GATEWAY:
				setInclusiveGateway((TInclusiveGateway)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__INPUT_SET:
				setInputSet((TInputSet)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__INTERFACE:
				setInterface((TInterface)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__INTERMEDIATE_CATCH_EVENT:
				setIntermediateCatchEvent((TIntermediateCatchEvent)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__INTERMEDIATE_THROW_EVENT:
				setIntermediateThrowEvent((TIntermediateThrowEvent)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__IO_BINDING:
				setIoBinding((TInputOutputBinding)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__IO_SPECIFICATION:
				setIoSpecification((TInputOutputSpecification)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__ITEM_DEFINITION:
				setItemDefinition((TItemDefinition)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__LANE:
				setLane((TLane)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__LANE_SET:
				setLaneSet((TLaneSet)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__LINK_EVENT_DEFINITION:
				setLinkEventDefinition((TLinkEventDefinition)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__LOOP_CHARACTERISTICS:
				setLoopCharacteristics((TLoopCharacteristics)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__MANUAL_TASK:
				setManualTask((TManualTask)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__MESSAGE:
				setMessage((TMessage)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__MESSAGE_EVENT_DEFINITION:
				setMessageEventDefinition((TMessageEventDefinition)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__MESSAGE_FLOW:
				setMessageFlow((TMessageFlow)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__MESSAGE_FLOW_ASSOCIATION:
				setMessageFlowAssociation((TMessageFlowAssociation)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__MONITORING:
				setMonitoring((TMonitoring)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__MULTI_INSTANCE_LOOP_CHARACTERISTICS:
				setMultiInstanceLoopCharacteristics((TMultiInstanceLoopCharacteristics)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__OPERATION:
				setOperation((TOperation)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__OUTPUT_SET:
				setOutputSet((TOutputSet)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__PARALLEL_GATEWAY:
				setParallelGateway((TParallelGateway)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__PARTICIPANT:
				setParticipant((TParticipant)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__PARTICIPANT_ASSOCIATION:
				setParticipantAssociation((TParticipantAssociation)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__PARTICIPANT_MULTIPLICITY:
				setParticipantMultiplicity((TParticipantMultiplicity)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__PARTNER_ENTITY:
				setPartnerEntity((TPartnerEntity)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__PARTNER_ROLE:
				setPartnerRole((TPartnerRole)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__POTENTIAL_OWNER:
				setPotentialOwner((TPotentialOwner)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__PROCESS:
				setProcess((TProcess)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__PROPERTY:
				setProperty((TProperty)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__RECEIVE_TASK:
				setReceiveTask((TReceiveTask)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__RELATIONSHIP:
				setRelationship((TRelationship)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__RENDERING:
				setRendering((TRendering)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__RESOURCE:
				setResource((TResource)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__RESOURCE_ASSIGNMENT_EXPRESSION:
				setResourceAssignmentExpression((TResourceAssignmentExpression)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__RESOURCE_PARAMETER:
				setResourceParameter((TResourceParameter)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__RESOURCE_PARAMETER_BINDING:
				setResourceParameterBinding((TResourceParameterBinding)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__SCRIPT:
				setScript((TScript)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__SCRIPT_TASK:
				setScriptTask((TScriptTask)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__SEND_TASK:
				setSendTask((TSendTask)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__SEQUENCE_FLOW:
				setSequenceFlow((TSequenceFlow)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__SERVICE_TASK:
				setServiceTask((TServiceTask)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__SIGNAL:
				setSignal((TSignal)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__SIGNAL_EVENT_DEFINITION:
				setSignalEventDefinition((TSignalEventDefinition)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__STANDARD_LOOP_CHARACTERISTICS:
				setStandardLoopCharacteristics((TStandardLoopCharacteristics)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__START_EVENT:
				setStartEvent((TStartEvent)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__SUB_CHOREOGRAPHY:
				setSubChoreography((TSubChoreography)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__SUB_CONVERSATION:
				setSubConversation((TSubConversation)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__SUB_PROCESS:
				setSubProcess((TSubProcess)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__TASK:
				setTask((TTask)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__TERMINATE_EVENT_DEFINITION:
				setTerminateEventDefinition((TTerminateEventDefinition)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__TEXT:
				setText((TText)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__TEXT_ANNOTATION:
				setTextAnnotation((TTextAnnotation)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__THROW_EVENT:
				setThrowEvent((TThrowEvent)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__TIMER_EVENT_DEFINITION:
				setTimerEventDefinition((TTimerEventDefinition)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__TRANSACTION:
				setTransaction((TTransaction)newValue);
				return;
			case ModelPackage.DOCUMENT_ROOT__USER_TASK:
				setUserTask((TUserTask)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case ModelPackage.DOCUMENT_ROOT__MIXED:
				getMixed().clear();
				return;
			case ModelPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
				getXMLNSPrefixMap().clear();
				return;
			case ModelPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
				getXSISchemaLocation().clear();
				return;
			case ModelPackage.DOCUMENT_ROOT__ACTIVITY:
				setActivity((TActivity)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__AD_HOC_SUB_PROCESS:
				setAdHocSubProcess((TAdHocSubProcess)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__FLOW_ELEMENT:
				setFlowElement((TFlowElement)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__ARTIFACT:
				setArtifact((TArtifact)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__ASSIGNMENT:
				setAssignment((TAssignment)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__ASSOCIATION:
				setAssociation((TAssociation)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__AUDITING:
				setAuditing((TAuditing)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__BASE_ELEMENT:
				setBaseElement((TBaseElement)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT:
				setBaseElementWithMixedContent((TBaseElementWithMixedContent)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__BOUNDARY_EVENT:
				setBoundaryEvent((TBoundaryEvent)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__BUSINESS_RULE_TASK:
				setBusinessRuleTask((TBusinessRuleTask)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__CALLABLE_ELEMENT:
				setCallableElement((TCallableElement)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__CALL_ACTIVITY:
				setCallActivity((TCallActivity)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__CALL_CHOREOGRAPHY:
				setCallChoreography((TCallChoreography)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__CALL_CONVERSATION:
				setCallConversation((TCallConversation)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__CONVERSATION_NODE:
				setConversationNode((TConversationNode)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__CANCEL_EVENT_DEFINITION:
				setCancelEventDefinition((TCancelEventDefinition)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__EVENT_DEFINITION:
				setEventDefinition((TEventDefinition)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__ROOT_ELEMENT:
				setRootElement((TRootElement)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__CATCH_EVENT:
				setCatchEvent((TCatchEvent)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__CATEGORY:
				setCategory((TCategory)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__CATEGORY_VALUE:
				setCategoryValue((TCategoryValue)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__CHOREOGRAPHY:
				setChoreography((TChoreography)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__COLLABORATION:
				setCollaboration((TCollaboration)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__CHOREOGRAPHY_ACTIVITY:
				setChoreographyActivity((TChoreographyActivity)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__CHOREOGRAPHY_TASK:
				setChoreographyTask((TChoreographyTask)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__COMPENSATE_EVENT_DEFINITION:
				setCompensateEventDefinition((TCompensateEventDefinition)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__COMPLEX_BEHAVIOR_DEFINITION:
				setComplexBehaviorDefinition((TComplexBehaviorDefinition)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__COMPLEX_GATEWAY:
				setComplexGateway((TComplexGateway)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__CONDITIONAL_EVENT_DEFINITION:
				setConditionalEventDefinition((TConditionalEventDefinition)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__CONVERSATION:
				setConversation((TConversation)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__CONVERSATION_ASSOCIATION:
				setConversationAssociation((TConversationAssociation)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__CONVERSATION_LINK:
				setConversationLink((TConversationLink)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__CORRELATION_KEY:
				setCorrelationKey((TCorrelationKey)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__CORRELATION_PROPERTY:
				setCorrelationProperty((TCorrelationProperty)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__CORRELATION_PROPERTY_BINDING:
				setCorrelationPropertyBinding((TCorrelationPropertyBinding)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__CORRELATION_PROPERTY_RETRIEVAL_EXPRESSION:
				setCorrelationPropertyRetrievalExpression((TCorrelationPropertyRetrievalExpression)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__CORRELATION_SUBSCRIPTION:
				setCorrelationSubscription((TCorrelationSubscription)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__DATA_ASSOCIATION:
				setDataAssociation((TDataAssociation)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__DATA_INPUT:
				setDataInput((TDataInput)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__DATA_INPUT_ASSOCIATION:
				setDataInputAssociation((TDataInputAssociation)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__DATA_OBJECT:
				setDataObject((TDataObject)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__DATA_OBJECT_REFERENCE:
				setDataObjectReference((TDataObjectReference)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__DATA_OUTPUT:
				setDataOutput((TDataOutput)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__DATA_OUTPUT_ASSOCIATION:
				setDataOutputAssociation((TDataOutputAssociation)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__DATA_STATE:
				setDataState((TDataState)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__DATA_STORE:
				setDataStore((TDataStore)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__DATA_STORE_REFERENCE:
				setDataStoreReference((TDataStoreReference)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__DEFINITIONS:
				setDefinitions((TDefinitions)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__DOCUMENTATION:
				setDocumentation((TDocumentation)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__END_EVENT:
				setEndEvent((TEndEvent)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__END_POINT:
				setEndPoint((TEndPoint)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__ERROR:
				setError((TError)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__ERROR_EVENT_DEFINITION:
				setErrorEventDefinition((TErrorEventDefinition)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__ESCALATION:
				setEscalation((TEscalation)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__ESCALATION_EVENT_DEFINITION:
				setEscalationEventDefinition((TEscalationEventDefinition)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__EVENT:
				setEvent((TEvent)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__EVENT_BASED_GATEWAY:
				setEventBasedGateway((TEventBasedGateway)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__EXCLUSIVE_GATEWAY:
				setExclusiveGateway((TExclusiveGateway)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__EXPRESSION:
				setExpression((TExpression)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__EXTENSION:
				setExtension((TExtension)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__EXTENSION_ELEMENTS:
				setExtensionElements((TExtensionElements)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__FLOW_NODE:
				setFlowNode((TFlowNode)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__FORMAL_EXPRESSION:
				setFormalExpression((TFormalExpression)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__GLOBAL_BUSINESS_RULE_TASK:
				setGlobalBusinessRuleTask((TGlobalBusinessRuleTask)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__GLOBAL_CHOREOGRAPHY_TASK:
				setGlobalChoreographyTask((TGlobalChoreographyTask)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__GLOBAL_CONVERSATION:
				setGlobalConversation((TGlobalConversation)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__GLOBAL_MANUAL_TASK:
				setGlobalManualTask((TGlobalManualTask)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__GLOBAL_SCRIPT_TASK:
				setGlobalScriptTask((TGlobalScriptTask)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__GLOBAL_TASK:
				setGlobalTask((TGlobalTask)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__GLOBAL_USER_TASK:
				setGlobalUserTask((TGlobalUserTask)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__GROUP:
				setGroup((TGroup)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__HUMAN_PERFORMER:
				setHumanPerformer((THumanPerformer)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__PERFORMER:
				setPerformer((TPerformer)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__RESOURCE_ROLE:
				setResourceRole((TResourceRole)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__IMPLICIT_THROW_EVENT:
				setImplicitThrowEvent((TImplicitThrowEvent)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__IMPORT:
				setImport((TImport)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__INCLUSIVE_GATEWAY:
				setInclusiveGateway((TInclusiveGateway)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__INPUT_SET:
				setInputSet((TInputSet)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__INTERFACE:
				setInterface((TInterface)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__INTERMEDIATE_CATCH_EVENT:
				setIntermediateCatchEvent((TIntermediateCatchEvent)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__INTERMEDIATE_THROW_EVENT:
				setIntermediateThrowEvent((TIntermediateThrowEvent)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__IO_BINDING:
				setIoBinding((TInputOutputBinding)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__IO_SPECIFICATION:
				setIoSpecification((TInputOutputSpecification)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__ITEM_DEFINITION:
				setItemDefinition((TItemDefinition)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__LANE:
				setLane((TLane)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__LANE_SET:
				setLaneSet((TLaneSet)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__LINK_EVENT_DEFINITION:
				setLinkEventDefinition((TLinkEventDefinition)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__LOOP_CHARACTERISTICS:
				setLoopCharacteristics((TLoopCharacteristics)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__MANUAL_TASK:
				setManualTask((TManualTask)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__MESSAGE:
				setMessage((TMessage)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__MESSAGE_EVENT_DEFINITION:
				setMessageEventDefinition((TMessageEventDefinition)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__MESSAGE_FLOW:
				setMessageFlow((TMessageFlow)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__MESSAGE_FLOW_ASSOCIATION:
				setMessageFlowAssociation((TMessageFlowAssociation)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__MONITORING:
				setMonitoring((TMonitoring)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__MULTI_INSTANCE_LOOP_CHARACTERISTICS:
				setMultiInstanceLoopCharacteristics((TMultiInstanceLoopCharacteristics)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__OPERATION:
				setOperation((TOperation)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__OUTPUT_SET:
				setOutputSet((TOutputSet)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__PARALLEL_GATEWAY:
				setParallelGateway((TParallelGateway)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__PARTICIPANT:
				setParticipant((TParticipant)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__PARTICIPANT_ASSOCIATION:
				setParticipantAssociation((TParticipantAssociation)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__PARTICIPANT_MULTIPLICITY:
				setParticipantMultiplicity((TParticipantMultiplicity)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__PARTNER_ENTITY:
				setPartnerEntity((TPartnerEntity)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__PARTNER_ROLE:
				setPartnerRole((TPartnerRole)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__POTENTIAL_OWNER:
				setPotentialOwner((TPotentialOwner)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__PROCESS:
				setProcess((TProcess)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__PROPERTY:
				setProperty((TProperty)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__RECEIVE_TASK:
				setReceiveTask((TReceiveTask)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__RELATIONSHIP:
				setRelationship((TRelationship)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__RENDERING:
				setRendering((TRendering)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__RESOURCE:
				setResource((TResource)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__RESOURCE_ASSIGNMENT_EXPRESSION:
				setResourceAssignmentExpression((TResourceAssignmentExpression)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__RESOURCE_PARAMETER:
				setResourceParameter((TResourceParameter)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__RESOURCE_PARAMETER_BINDING:
				setResourceParameterBinding((TResourceParameterBinding)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__SCRIPT:
				setScript((TScript)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__SCRIPT_TASK:
				setScriptTask((TScriptTask)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__SEND_TASK:
				setSendTask((TSendTask)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__SEQUENCE_FLOW:
				setSequenceFlow((TSequenceFlow)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__SERVICE_TASK:
				setServiceTask((TServiceTask)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__SIGNAL:
				setSignal((TSignal)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__SIGNAL_EVENT_DEFINITION:
				setSignalEventDefinition((TSignalEventDefinition)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__STANDARD_LOOP_CHARACTERISTICS:
				setStandardLoopCharacteristics((TStandardLoopCharacteristics)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__START_EVENT:
				setStartEvent((TStartEvent)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__SUB_CHOREOGRAPHY:
				setSubChoreography((TSubChoreography)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__SUB_CONVERSATION:
				setSubConversation((TSubConversation)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__SUB_PROCESS:
				setSubProcess((TSubProcess)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__TASK:
				setTask((TTask)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__TERMINATE_EVENT_DEFINITION:
				setTerminateEventDefinition((TTerminateEventDefinition)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__TEXT:
				setText((TText)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__TEXT_ANNOTATION:
				setTextAnnotation((TTextAnnotation)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__THROW_EVENT:
				setThrowEvent((TThrowEvent)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__TIMER_EVENT_DEFINITION:
				setTimerEventDefinition((TTimerEventDefinition)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__TRANSACTION:
				setTransaction((TTransaction)null);
				return;
			case ModelPackage.DOCUMENT_ROOT__USER_TASK:
				setUserTask((TUserTask)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case ModelPackage.DOCUMENT_ROOT__MIXED:
				return mixed != null && !mixed.isEmpty();
			case ModelPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
				return xMLNSPrefixMap != null && !xMLNSPrefixMap.isEmpty();
			case ModelPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
				return xSISchemaLocation != null && !xSISchemaLocation.isEmpty();
			case ModelPackage.DOCUMENT_ROOT__ACTIVITY:
				return getActivity() != null;
			case ModelPackage.DOCUMENT_ROOT__AD_HOC_SUB_PROCESS:
				return getAdHocSubProcess() != null;
			case ModelPackage.DOCUMENT_ROOT__FLOW_ELEMENT:
				return getFlowElement() != null;
			case ModelPackage.DOCUMENT_ROOT__ARTIFACT:
				return getArtifact() != null;
			case ModelPackage.DOCUMENT_ROOT__ASSIGNMENT:
				return getAssignment() != null;
			case ModelPackage.DOCUMENT_ROOT__ASSOCIATION:
				return getAssociation() != null;
			case ModelPackage.DOCUMENT_ROOT__AUDITING:
				return getAuditing() != null;
			case ModelPackage.DOCUMENT_ROOT__BASE_ELEMENT:
				return getBaseElement() != null;
			case ModelPackage.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT:
				return getBaseElementWithMixedContent() != null;
			case ModelPackage.DOCUMENT_ROOT__BOUNDARY_EVENT:
				return getBoundaryEvent() != null;
			case ModelPackage.DOCUMENT_ROOT__BUSINESS_RULE_TASK:
				return getBusinessRuleTask() != null;
			case ModelPackage.DOCUMENT_ROOT__CALLABLE_ELEMENT:
				return getCallableElement() != null;
			case ModelPackage.DOCUMENT_ROOT__CALL_ACTIVITY:
				return getCallActivity() != null;
			case ModelPackage.DOCUMENT_ROOT__CALL_CHOREOGRAPHY:
				return getCallChoreography() != null;
			case ModelPackage.DOCUMENT_ROOT__CALL_CONVERSATION:
				return getCallConversation() != null;
			case ModelPackage.DOCUMENT_ROOT__CONVERSATION_NODE:
				return getConversationNode() != null;
			case ModelPackage.DOCUMENT_ROOT__CANCEL_EVENT_DEFINITION:
				return getCancelEventDefinition() != null;
			case ModelPackage.DOCUMENT_ROOT__EVENT_DEFINITION:
				return getEventDefinition() != null;
			case ModelPackage.DOCUMENT_ROOT__ROOT_ELEMENT:
				return getRootElement() != null;
			case ModelPackage.DOCUMENT_ROOT__CATCH_EVENT:
				return getCatchEvent() != null;
			case ModelPackage.DOCUMENT_ROOT__CATEGORY:
				return getCategory() != null;
			case ModelPackage.DOCUMENT_ROOT__CATEGORY_VALUE:
				return getCategoryValue() != null;
			case ModelPackage.DOCUMENT_ROOT__CHOREOGRAPHY:
				return getChoreography() != null;
			case ModelPackage.DOCUMENT_ROOT__COLLABORATION:
				return getCollaboration() != null;
			case ModelPackage.DOCUMENT_ROOT__CHOREOGRAPHY_ACTIVITY:
				return getChoreographyActivity() != null;
			case ModelPackage.DOCUMENT_ROOT__CHOREOGRAPHY_TASK:
				return getChoreographyTask() != null;
			case ModelPackage.DOCUMENT_ROOT__COMPENSATE_EVENT_DEFINITION:
				return getCompensateEventDefinition() != null;
			case ModelPackage.DOCUMENT_ROOT__COMPLEX_BEHAVIOR_DEFINITION:
				return getComplexBehaviorDefinition() != null;
			case ModelPackage.DOCUMENT_ROOT__COMPLEX_GATEWAY:
				return getComplexGateway() != null;
			case ModelPackage.DOCUMENT_ROOT__CONDITIONAL_EVENT_DEFINITION:
				return getConditionalEventDefinition() != null;
			case ModelPackage.DOCUMENT_ROOT__CONVERSATION:
				return getConversation() != null;
			case ModelPackage.DOCUMENT_ROOT__CONVERSATION_ASSOCIATION:
				return getConversationAssociation() != null;
			case ModelPackage.DOCUMENT_ROOT__CONVERSATION_LINK:
				return getConversationLink() != null;
			case ModelPackage.DOCUMENT_ROOT__CORRELATION_KEY:
				return getCorrelationKey() != null;
			case ModelPackage.DOCUMENT_ROOT__CORRELATION_PROPERTY:
				return getCorrelationProperty() != null;
			case ModelPackage.DOCUMENT_ROOT__CORRELATION_PROPERTY_BINDING:
				return getCorrelationPropertyBinding() != null;
			case ModelPackage.DOCUMENT_ROOT__CORRELATION_PROPERTY_RETRIEVAL_EXPRESSION:
				return getCorrelationPropertyRetrievalExpression() != null;
			case ModelPackage.DOCUMENT_ROOT__CORRELATION_SUBSCRIPTION:
				return getCorrelationSubscription() != null;
			case ModelPackage.DOCUMENT_ROOT__DATA_ASSOCIATION:
				return getDataAssociation() != null;
			case ModelPackage.DOCUMENT_ROOT__DATA_INPUT:
				return getDataInput() != null;
			case ModelPackage.DOCUMENT_ROOT__DATA_INPUT_ASSOCIATION:
				return getDataInputAssociation() != null;
			case ModelPackage.DOCUMENT_ROOT__DATA_OBJECT:
				return getDataObject() != null;
			case ModelPackage.DOCUMENT_ROOT__DATA_OBJECT_REFERENCE:
				return getDataObjectReference() != null;
			case ModelPackage.DOCUMENT_ROOT__DATA_OUTPUT:
				return getDataOutput() != null;
			case ModelPackage.DOCUMENT_ROOT__DATA_OUTPUT_ASSOCIATION:
				return getDataOutputAssociation() != null;
			case ModelPackage.DOCUMENT_ROOT__DATA_STATE:
				return getDataState() != null;
			case ModelPackage.DOCUMENT_ROOT__DATA_STORE:
				return getDataStore() != null;
			case ModelPackage.DOCUMENT_ROOT__DATA_STORE_REFERENCE:
				return getDataStoreReference() != null;
			case ModelPackage.DOCUMENT_ROOT__DEFINITIONS:
				return getDefinitions() != null;
			case ModelPackage.DOCUMENT_ROOT__DOCUMENTATION:
				return getDocumentation() != null;
			case ModelPackage.DOCUMENT_ROOT__END_EVENT:
				return getEndEvent() != null;
			case ModelPackage.DOCUMENT_ROOT__END_POINT:
				return getEndPoint() != null;
			case ModelPackage.DOCUMENT_ROOT__ERROR:
				return getError() != null;
			case ModelPackage.DOCUMENT_ROOT__ERROR_EVENT_DEFINITION:
				return getErrorEventDefinition() != null;
			case ModelPackage.DOCUMENT_ROOT__ESCALATION:
				return getEscalation() != null;
			case ModelPackage.DOCUMENT_ROOT__ESCALATION_EVENT_DEFINITION:
				return getEscalationEventDefinition() != null;
			case ModelPackage.DOCUMENT_ROOT__EVENT:
				return getEvent() != null;
			case ModelPackage.DOCUMENT_ROOT__EVENT_BASED_GATEWAY:
				return getEventBasedGateway() != null;
			case ModelPackage.DOCUMENT_ROOT__EXCLUSIVE_GATEWAY:
				return getExclusiveGateway() != null;
			case ModelPackage.DOCUMENT_ROOT__EXPRESSION:
				return getExpression() != null;
			case ModelPackage.DOCUMENT_ROOT__EXTENSION:
				return getExtension() != null;
			case ModelPackage.DOCUMENT_ROOT__EXTENSION_ELEMENTS:
				return getExtensionElements() != null;
			case ModelPackage.DOCUMENT_ROOT__FLOW_NODE:
				return getFlowNode() != null;
			case ModelPackage.DOCUMENT_ROOT__FORMAL_EXPRESSION:
				return getFormalExpression() != null;
			case ModelPackage.DOCUMENT_ROOT__GATEWAY:
				return getGateway() != null;
			case ModelPackage.DOCUMENT_ROOT__GLOBAL_BUSINESS_RULE_TASK:
				return getGlobalBusinessRuleTask() != null;
			case ModelPackage.DOCUMENT_ROOT__GLOBAL_CHOREOGRAPHY_TASK:
				return getGlobalChoreographyTask() != null;
			case ModelPackage.DOCUMENT_ROOT__GLOBAL_CONVERSATION:
				return getGlobalConversation() != null;
			case ModelPackage.DOCUMENT_ROOT__GLOBAL_MANUAL_TASK:
				return getGlobalManualTask() != null;
			case ModelPackage.DOCUMENT_ROOT__GLOBAL_SCRIPT_TASK:
				return getGlobalScriptTask() != null;
			case ModelPackage.DOCUMENT_ROOT__GLOBAL_TASK:
				return getGlobalTask() != null;
			case ModelPackage.DOCUMENT_ROOT__GLOBAL_USER_TASK:
				return getGlobalUserTask() != null;
			case ModelPackage.DOCUMENT_ROOT__GROUP:
				return getGroup() != null;
			case ModelPackage.DOCUMENT_ROOT__HUMAN_PERFORMER:
				return getHumanPerformer() != null;
			case ModelPackage.DOCUMENT_ROOT__PERFORMER:
				return getPerformer() != null;
			case ModelPackage.DOCUMENT_ROOT__RESOURCE_ROLE:
				return getResourceRole() != null;
			case ModelPackage.DOCUMENT_ROOT__IMPLICIT_THROW_EVENT:
				return getImplicitThrowEvent() != null;
			case ModelPackage.DOCUMENT_ROOT__IMPORT:
				return getImport() != null;
			case ModelPackage.DOCUMENT_ROOT__INCLUSIVE_GATEWAY:
				return getInclusiveGateway() != null;
			case ModelPackage.DOCUMENT_ROOT__INPUT_SET:
				return getInputSet() != null;
			case ModelPackage.DOCUMENT_ROOT__INTERFACE:
				return getInterface() != null;
			case ModelPackage.DOCUMENT_ROOT__INTERMEDIATE_CATCH_EVENT:
				return getIntermediateCatchEvent() != null;
			case ModelPackage.DOCUMENT_ROOT__INTERMEDIATE_THROW_EVENT:
				return getIntermediateThrowEvent() != null;
			case ModelPackage.DOCUMENT_ROOT__IO_BINDING:
				return getIoBinding() != null;
			case ModelPackage.DOCUMENT_ROOT__IO_SPECIFICATION:
				return getIoSpecification() != null;
			case ModelPackage.DOCUMENT_ROOT__ITEM_DEFINITION:
				return getItemDefinition() != null;
			case ModelPackage.DOCUMENT_ROOT__LANE:
				return getLane() != null;
			case ModelPackage.DOCUMENT_ROOT__LANE_SET:
				return getLaneSet() != null;
			case ModelPackage.DOCUMENT_ROOT__LINK_EVENT_DEFINITION:
				return getLinkEventDefinition() != null;
			case ModelPackage.DOCUMENT_ROOT__LOOP_CHARACTERISTICS:
				return getLoopCharacteristics() != null;
			case ModelPackage.DOCUMENT_ROOT__MANUAL_TASK:
				return getManualTask() != null;
			case ModelPackage.DOCUMENT_ROOT__MESSAGE:
				return getMessage() != null;
			case ModelPackage.DOCUMENT_ROOT__MESSAGE_EVENT_DEFINITION:
				return getMessageEventDefinition() != null;
			case ModelPackage.DOCUMENT_ROOT__MESSAGE_FLOW:
				return getMessageFlow() != null;
			case ModelPackage.DOCUMENT_ROOT__MESSAGE_FLOW_ASSOCIATION:
				return getMessageFlowAssociation() != null;
			case ModelPackage.DOCUMENT_ROOT__MONITORING:
				return getMonitoring() != null;
			case ModelPackage.DOCUMENT_ROOT__MULTI_INSTANCE_LOOP_CHARACTERISTICS:
				return getMultiInstanceLoopCharacteristics() != null;
			case ModelPackage.DOCUMENT_ROOT__OPERATION:
				return getOperation() != null;
			case ModelPackage.DOCUMENT_ROOT__OUTPUT_SET:
				return getOutputSet() != null;
			case ModelPackage.DOCUMENT_ROOT__PARALLEL_GATEWAY:
				return getParallelGateway() != null;
			case ModelPackage.DOCUMENT_ROOT__PARTICIPANT:
				return getParticipant() != null;
			case ModelPackage.DOCUMENT_ROOT__PARTICIPANT_ASSOCIATION:
				return getParticipantAssociation() != null;
			case ModelPackage.DOCUMENT_ROOT__PARTICIPANT_MULTIPLICITY:
				return getParticipantMultiplicity() != null;
			case ModelPackage.DOCUMENT_ROOT__PARTNER_ENTITY:
				return getPartnerEntity() != null;
			case ModelPackage.DOCUMENT_ROOT__PARTNER_ROLE:
				return getPartnerRole() != null;
			case ModelPackage.DOCUMENT_ROOT__POTENTIAL_OWNER:
				return getPotentialOwner() != null;
			case ModelPackage.DOCUMENT_ROOT__PROCESS:
				return getProcess() != null;
			case ModelPackage.DOCUMENT_ROOT__PROPERTY:
				return getProperty() != null;
			case ModelPackage.DOCUMENT_ROOT__RECEIVE_TASK:
				return getReceiveTask() != null;
			case ModelPackage.DOCUMENT_ROOT__RELATIONSHIP:
				return getRelationship() != null;
			case ModelPackage.DOCUMENT_ROOT__RENDERING:
				return getRendering() != null;
			case ModelPackage.DOCUMENT_ROOT__RESOURCE:
				return getResource() != null;
			case ModelPackage.DOCUMENT_ROOT__RESOURCE_ASSIGNMENT_EXPRESSION:
				return getResourceAssignmentExpression() != null;
			case ModelPackage.DOCUMENT_ROOT__RESOURCE_PARAMETER:
				return getResourceParameter() != null;
			case ModelPackage.DOCUMENT_ROOT__RESOURCE_PARAMETER_BINDING:
				return getResourceParameterBinding() != null;
			case ModelPackage.DOCUMENT_ROOT__SCRIPT:
				return getScript() != null;
			case ModelPackage.DOCUMENT_ROOT__SCRIPT_TASK:
				return getScriptTask() != null;
			case ModelPackage.DOCUMENT_ROOT__SEND_TASK:
				return getSendTask() != null;
			case ModelPackage.DOCUMENT_ROOT__SEQUENCE_FLOW:
				return getSequenceFlow() != null;
			case ModelPackage.DOCUMENT_ROOT__SERVICE_TASK:
				return getServiceTask() != null;
			case ModelPackage.DOCUMENT_ROOT__SIGNAL:
				return getSignal() != null;
			case ModelPackage.DOCUMENT_ROOT__SIGNAL_EVENT_DEFINITION:
				return getSignalEventDefinition() != null;
			case ModelPackage.DOCUMENT_ROOT__STANDARD_LOOP_CHARACTERISTICS:
				return getStandardLoopCharacteristics() != null;
			case ModelPackage.DOCUMENT_ROOT__START_EVENT:
				return getStartEvent() != null;
			case ModelPackage.DOCUMENT_ROOT__SUB_CHOREOGRAPHY:
				return getSubChoreography() != null;
			case ModelPackage.DOCUMENT_ROOT__SUB_CONVERSATION:
				return getSubConversation() != null;
			case ModelPackage.DOCUMENT_ROOT__SUB_PROCESS:
				return getSubProcess() != null;
			case ModelPackage.DOCUMENT_ROOT__TASK:
				return getTask() != null;
			case ModelPackage.DOCUMENT_ROOT__TERMINATE_EVENT_DEFINITION:
				return getTerminateEventDefinition() != null;
			case ModelPackage.DOCUMENT_ROOT__TEXT:
				return getText() != null;
			case ModelPackage.DOCUMENT_ROOT__TEXT_ANNOTATION:
				return getTextAnnotation() != null;
			case ModelPackage.DOCUMENT_ROOT__THROW_EVENT:
				return getThrowEvent() != null;
			case ModelPackage.DOCUMENT_ROOT__TIMER_EVENT_DEFINITION:
				return getTimerEventDefinition() != null;
			case ModelPackage.DOCUMENT_ROOT__TRANSACTION:
				return getTransaction() != null;
			case ModelPackage.DOCUMENT_ROOT__USER_TASK:
				return getUserTask() != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (mixed: ");
		result.append(mixed);
		result.append(')');
		return result.toString();
	}

} //DocumentRootImpl
