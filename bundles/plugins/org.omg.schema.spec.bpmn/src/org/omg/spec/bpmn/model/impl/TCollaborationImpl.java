/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.omg.spec.bpmn.model.impl;

import java.util.Collection;

import javax.xml.namespace.QName;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.EDataTypeEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;

import org.omg.spec.bpmn.model.ModelPackage;
import org.omg.spec.bpmn.model.TArtifact;
import org.omg.spec.bpmn.model.TCollaboration;
import org.omg.spec.bpmn.model.TConversationAssociation;
import org.omg.spec.bpmn.model.TConversationLink;
import org.omg.spec.bpmn.model.TConversationNode;
import org.omg.spec.bpmn.model.TCorrelationKey;
import org.omg.spec.bpmn.model.TMessageFlow;
import org.omg.spec.bpmn.model.TMessageFlowAssociation;
import org.omg.spec.bpmn.model.TParticipant;
import org.omg.spec.bpmn.model.TParticipantAssociation;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>TCollaboration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TCollaborationImpl#getParticipant <em>Participant</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TCollaborationImpl#getMessageFlow <em>Message Flow</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TCollaborationImpl#getArtifactGroup <em>Artifact Group</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TCollaborationImpl#getArtifact <em>Artifact</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TCollaborationImpl#getConversationNodeGroup <em>Conversation Node Group</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TCollaborationImpl#getConversationNode <em>Conversation Node</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TCollaborationImpl#getConversationAssociation <em>Conversation Association</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TCollaborationImpl#getParticipantAssociation <em>Participant Association</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TCollaborationImpl#getMessageFlowAssociation <em>Message Flow Association</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TCollaborationImpl#getCorrelationKey <em>Correlation Key</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TCollaborationImpl#getChoreographyRef <em>Choreography Ref</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TCollaborationImpl#getConversationLink <em>Conversation Link</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TCollaborationImpl#isIsClosed <em>Is Closed</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TCollaborationImpl#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TCollaborationImpl extends TRootElementImpl implements TCollaboration {
	/**
	 * The cached value of the '{@link #getParticipant() <em>Participant</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParticipant()
	 * @generated
	 * @ordered
	 */
	protected EList<TParticipant> participant;

	/**
	 * The cached value of the '{@link #getMessageFlow() <em>Message Flow</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMessageFlow()
	 * @generated
	 * @ordered
	 */
	protected EList<TMessageFlow> messageFlow;

	/**
	 * The cached value of the '{@link #getArtifactGroup() <em>Artifact Group</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getArtifactGroup()
	 * @generated
	 * @ordered
	 */
	protected FeatureMap artifactGroup;

	/**
	 * The cached value of the '{@link #getConversationNodeGroup() <em>Conversation Node Group</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConversationNodeGroup()
	 * @generated
	 * @ordered
	 */
	protected FeatureMap conversationNodeGroup;

	/**
	 * The cached value of the '{@link #getConversationAssociation() <em>Conversation Association</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConversationAssociation()
	 * @generated
	 * @ordered
	 */
	protected EList<TConversationAssociation> conversationAssociation;

	/**
	 * The cached value of the '{@link #getParticipantAssociation() <em>Participant Association</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParticipantAssociation()
	 * @generated
	 * @ordered
	 */
	protected EList<TParticipantAssociation> participantAssociation;

	/**
	 * The cached value of the '{@link #getMessageFlowAssociation() <em>Message Flow Association</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMessageFlowAssociation()
	 * @generated
	 * @ordered
	 */
	protected EList<TMessageFlowAssociation> messageFlowAssociation;

	/**
	 * The cached value of the '{@link #getCorrelationKey() <em>Correlation Key</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCorrelationKey()
	 * @generated
	 * @ordered
	 */
	protected EList<TCorrelationKey> correlationKey;

	/**
	 * The cached value of the '{@link #getChoreographyRef() <em>Choreography Ref</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getChoreographyRef()
	 * @generated
	 * @ordered
	 */
	protected EList<QName> choreographyRef;

	/**
	 * The cached value of the '{@link #getConversationLink() <em>Conversation Link</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConversationLink()
	 * @generated
	 * @ordered
	 */
	protected EList<TConversationLink> conversationLink;

	/**
	 * The default value of the '{@link #isIsClosed() <em>Is Closed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsClosed()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_CLOSED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsClosed() <em>Is Closed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsClosed()
	 * @generated
	 * @ordered
	 */
	protected boolean isClosed = IS_CLOSED_EDEFAULT;

	/**
	 * This is true if the Is Closed attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean isClosedESet;

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TCollaborationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.TCOLLABORATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TParticipant> getParticipant() {
		if (participant == null) {
			participant = new EObjectContainmentEList<TParticipant>(TParticipant.class, this, ModelPackage.TCOLLABORATION__PARTICIPANT);
		}
		return participant;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TMessageFlow> getMessageFlow() {
		if (messageFlow == null) {
			messageFlow = new EObjectContainmentEList<TMessageFlow>(TMessageFlow.class, this, ModelPackage.TCOLLABORATION__MESSAGE_FLOW);
		}
		return messageFlow;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getArtifactGroup() {
		if (artifactGroup == null) {
			artifactGroup = new BasicFeatureMap(this, ModelPackage.TCOLLABORATION__ARTIFACT_GROUP);
		}
		return artifactGroup;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TArtifact> getArtifact() {
		return getArtifactGroup().list(ModelPackage.Literals.TCOLLABORATION__ARTIFACT);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getConversationNodeGroup() {
		if (conversationNodeGroup == null) {
			conversationNodeGroup = new BasicFeatureMap(this, ModelPackage.TCOLLABORATION__CONVERSATION_NODE_GROUP);
		}
		return conversationNodeGroup;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TConversationNode> getConversationNode() {
		return getConversationNodeGroup().list(ModelPackage.Literals.TCOLLABORATION__CONVERSATION_NODE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TConversationAssociation> getConversationAssociation() {
		if (conversationAssociation == null) {
			conversationAssociation = new EObjectContainmentEList<TConversationAssociation>(TConversationAssociation.class, this, ModelPackage.TCOLLABORATION__CONVERSATION_ASSOCIATION);
		}
		return conversationAssociation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TParticipantAssociation> getParticipantAssociation() {
		if (participantAssociation == null) {
			participantAssociation = new EObjectContainmentEList<TParticipantAssociation>(TParticipantAssociation.class, this, ModelPackage.TCOLLABORATION__PARTICIPANT_ASSOCIATION);
		}
		return participantAssociation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TMessageFlowAssociation> getMessageFlowAssociation() {
		if (messageFlowAssociation == null) {
			messageFlowAssociation = new EObjectContainmentEList<TMessageFlowAssociation>(TMessageFlowAssociation.class, this, ModelPackage.TCOLLABORATION__MESSAGE_FLOW_ASSOCIATION);
		}
		return messageFlowAssociation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TCorrelationKey> getCorrelationKey() {
		if (correlationKey == null) {
			correlationKey = new EObjectContainmentEList<TCorrelationKey>(TCorrelationKey.class, this, ModelPackage.TCOLLABORATION__CORRELATION_KEY);
		}
		return correlationKey;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<QName> getChoreographyRef() {
		if (choreographyRef == null) {
			choreographyRef = new EDataTypeEList<QName>(QName.class, this, ModelPackage.TCOLLABORATION__CHOREOGRAPHY_REF);
		}
		return choreographyRef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TConversationLink> getConversationLink() {
		if (conversationLink == null) {
			conversationLink = new EObjectContainmentEList<TConversationLink>(TConversationLink.class, this, ModelPackage.TCOLLABORATION__CONVERSATION_LINK);
		}
		return conversationLink;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIsClosed() {
		return isClosed;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsClosed(boolean newIsClosed) {
		boolean oldIsClosed = isClosed;
		isClosed = newIsClosed;
		boolean oldIsClosedESet = isClosedESet;
		isClosedESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TCOLLABORATION__IS_CLOSED, oldIsClosed, isClosed, !oldIsClosedESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetIsClosed() {
		boolean oldIsClosed = isClosed;
		boolean oldIsClosedESet = isClosedESet;
		isClosed = IS_CLOSED_EDEFAULT;
		isClosedESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, ModelPackage.TCOLLABORATION__IS_CLOSED, oldIsClosed, IS_CLOSED_EDEFAULT, oldIsClosedESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetIsClosed() {
		return isClosedESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TCOLLABORATION__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ModelPackage.TCOLLABORATION__PARTICIPANT:
				return ((InternalEList<?>)getParticipant()).basicRemove(otherEnd, msgs);
			case ModelPackage.TCOLLABORATION__MESSAGE_FLOW:
				return ((InternalEList<?>)getMessageFlow()).basicRemove(otherEnd, msgs);
			case ModelPackage.TCOLLABORATION__ARTIFACT_GROUP:
				return ((InternalEList<?>)getArtifactGroup()).basicRemove(otherEnd, msgs);
			case ModelPackage.TCOLLABORATION__ARTIFACT:
				return ((InternalEList<?>)getArtifact()).basicRemove(otherEnd, msgs);
			case ModelPackage.TCOLLABORATION__CONVERSATION_NODE_GROUP:
				return ((InternalEList<?>)getConversationNodeGroup()).basicRemove(otherEnd, msgs);
			case ModelPackage.TCOLLABORATION__CONVERSATION_NODE:
				return ((InternalEList<?>)getConversationNode()).basicRemove(otherEnd, msgs);
			case ModelPackage.TCOLLABORATION__CONVERSATION_ASSOCIATION:
				return ((InternalEList<?>)getConversationAssociation()).basicRemove(otherEnd, msgs);
			case ModelPackage.TCOLLABORATION__PARTICIPANT_ASSOCIATION:
				return ((InternalEList<?>)getParticipantAssociation()).basicRemove(otherEnd, msgs);
			case ModelPackage.TCOLLABORATION__MESSAGE_FLOW_ASSOCIATION:
				return ((InternalEList<?>)getMessageFlowAssociation()).basicRemove(otherEnd, msgs);
			case ModelPackage.TCOLLABORATION__CORRELATION_KEY:
				return ((InternalEList<?>)getCorrelationKey()).basicRemove(otherEnd, msgs);
			case ModelPackage.TCOLLABORATION__CONVERSATION_LINK:
				return ((InternalEList<?>)getConversationLink()).basicRemove(otherEnd, msgs);
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
			case ModelPackage.TCOLLABORATION__PARTICIPANT:
				return getParticipant();
			case ModelPackage.TCOLLABORATION__MESSAGE_FLOW:
				return getMessageFlow();
			case ModelPackage.TCOLLABORATION__ARTIFACT_GROUP:
				if (coreType) return getArtifactGroup();
				return ((FeatureMap.Internal)getArtifactGroup()).getWrapper();
			case ModelPackage.TCOLLABORATION__ARTIFACT:
				return getArtifact();
			case ModelPackage.TCOLLABORATION__CONVERSATION_NODE_GROUP:
				if (coreType) return getConversationNodeGroup();
				return ((FeatureMap.Internal)getConversationNodeGroup()).getWrapper();
			case ModelPackage.TCOLLABORATION__CONVERSATION_NODE:
				return getConversationNode();
			case ModelPackage.TCOLLABORATION__CONVERSATION_ASSOCIATION:
				return getConversationAssociation();
			case ModelPackage.TCOLLABORATION__PARTICIPANT_ASSOCIATION:
				return getParticipantAssociation();
			case ModelPackage.TCOLLABORATION__MESSAGE_FLOW_ASSOCIATION:
				return getMessageFlowAssociation();
			case ModelPackage.TCOLLABORATION__CORRELATION_KEY:
				return getCorrelationKey();
			case ModelPackage.TCOLLABORATION__CHOREOGRAPHY_REF:
				return getChoreographyRef();
			case ModelPackage.TCOLLABORATION__CONVERSATION_LINK:
				return getConversationLink();
			case ModelPackage.TCOLLABORATION__IS_CLOSED:
				return isIsClosed();
			case ModelPackage.TCOLLABORATION__NAME:
				return getName();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ModelPackage.TCOLLABORATION__PARTICIPANT:
				getParticipant().clear();
				getParticipant().addAll((Collection<? extends TParticipant>)newValue);
				return;
			case ModelPackage.TCOLLABORATION__MESSAGE_FLOW:
				getMessageFlow().clear();
				getMessageFlow().addAll((Collection<? extends TMessageFlow>)newValue);
				return;
			case ModelPackage.TCOLLABORATION__ARTIFACT_GROUP:
				((FeatureMap.Internal)getArtifactGroup()).set(newValue);
				return;
			case ModelPackage.TCOLLABORATION__ARTIFACT:
				getArtifact().clear();
				getArtifact().addAll((Collection<? extends TArtifact>)newValue);
				return;
			case ModelPackage.TCOLLABORATION__CONVERSATION_NODE_GROUP:
				((FeatureMap.Internal)getConversationNodeGroup()).set(newValue);
				return;
			case ModelPackage.TCOLLABORATION__CONVERSATION_NODE:
				getConversationNode().clear();
				getConversationNode().addAll((Collection<? extends TConversationNode>)newValue);
				return;
			case ModelPackage.TCOLLABORATION__CONVERSATION_ASSOCIATION:
				getConversationAssociation().clear();
				getConversationAssociation().addAll((Collection<? extends TConversationAssociation>)newValue);
				return;
			case ModelPackage.TCOLLABORATION__PARTICIPANT_ASSOCIATION:
				getParticipantAssociation().clear();
				getParticipantAssociation().addAll((Collection<? extends TParticipantAssociation>)newValue);
				return;
			case ModelPackage.TCOLLABORATION__MESSAGE_FLOW_ASSOCIATION:
				getMessageFlowAssociation().clear();
				getMessageFlowAssociation().addAll((Collection<? extends TMessageFlowAssociation>)newValue);
				return;
			case ModelPackage.TCOLLABORATION__CORRELATION_KEY:
				getCorrelationKey().clear();
				getCorrelationKey().addAll((Collection<? extends TCorrelationKey>)newValue);
				return;
			case ModelPackage.TCOLLABORATION__CHOREOGRAPHY_REF:
				getChoreographyRef().clear();
				getChoreographyRef().addAll((Collection<? extends QName>)newValue);
				return;
			case ModelPackage.TCOLLABORATION__CONVERSATION_LINK:
				getConversationLink().clear();
				getConversationLink().addAll((Collection<? extends TConversationLink>)newValue);
				return;
			case ModelPackage.TCOLLABORATION__IS_CLOSED:
				setIsClosed((Boolean)newValue);
				return;
			case ModelPackage.TCOLLABORATION__NAME:
				setName((String)newValue);
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
			case ModelPackage.TCOLLABORATION__PARTICIPANT:
				getParticipant().clear();
				return;
			case ModelPackage.TCOLLABORATION__MESSAGE_FLOW:
				getMessageFlow().clear();
				return;
			case ModelPackage.TCOLLABORATION__ARTIFACT_GROUP:
				getArtifactGroup().clear();
				return;
			case ModelPackage.TCOLLABORATION__ARTIFACT:
				getArtifact().clear();
				return;
			case ModelPackage.TCOLLABORATION__CONVERSATION_NODE_GROUP:
				getConversationNodeGroup().clear();
				return;
			case ModelPackage.TCOLLABORATION__CONVERSATION_NODE:
				getConversationNode().clear();
				return;
			case ModelPackage.TCOLLABORATION__CONVERSATION_ASSOCIATION:
				getConversationAssociation().clear();
				return;
			case ModelPackage.TCOLLABORATION__PARTICIPANT_ASSOCIATION:
				getParticipantAssociation().clear();
				return;
			case ModelPackage.TCOLLABORATION__MESSAGE_FLOW_ASSOCIATION:
				getMessageFlowAssociation().clear();
				return;
			case ModelPackage.TCOLLABORATION__CORRELATION_KEY:
				getCorrelationKey().clear();
				return;
			case ModelPackage.TCOLLABORATION__CHOREOGRAPHY_REF:
				getChoreographyRef().clear();
				return;
			case ModelPackage.TCOLLABORATION__CONVERSATION_LINK:
				getConversationLink().clear();
				return;
			case ModelPackage.TCOLLABORATION__IS_CLOSED:
				unsetIsClosed();
				return;
			case ModelPackage.TCOLLABORATION__NAME:
				setName(NAME_EDEFAULT);
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
			case ModelPackage.TCOLLABORATION__PARTICIPANT:
				return participant != null && !participant.isEmpty();
			case ModelPackage.TCOLLABORATION__MESSAGE_FLOW:
				return messageFlow != null && !messageFlow.isEmpty();
			case ModelPackage.TCOLLABORATION__ARTIFACT_GROUP:
				return artifactGroup != null && !artifactGroup.isEmpty();
			case ModelPackage.TCOLLABORATION__ARTIFACT:
				return !getArtifact().isEmpty();
			case ModelPackage.TCOLLABORATION__CONVERSATION_NODE_GROUP:
				return conversationNodeGroup != null && !conversationNodeGroup.isEmpty();
			case ModelPackage.TCOLLABORATION__CONVERSATION_NODE:
				return !getConversationNode().isEmpty();
			case ModelPackage.TCOLLABORATION__CONVERSATION_ASSOCIATION:
				return conversationAssociation != null && !conversationAssociation.isEmpty();
			case ModelPackage.TCOLLABORATION__PARTICIPANT_ASSOCIATION:
				return participantAssociation != null && !participantAssociation.isEmpty();
			case ModelPackage.TCOLLABORATION__MESSAGE_FLOW_ASSOCIATION:
				return messageFlowAssociation != null && !messageFlowAssociation.isEmpty();
			case ModelPackage.TCOLLABORATION__CORRELATION_KEY:
				return correlationKey != null && !correlationKey.isEmpty();
			case ModelPackage.TCOLLABORATION__CHOREOGRAPHY_REF:
				return choreographyRef != null && !choreographyRef.isEmpty();
			case ModelPackage.TCOLLABORATION__CONVERSATION_LINK:
				return conversationLink != null && !conversationLink.isEmpty();
			case ModelPackage.TCOLLABORATION__IS_CLOSED:
				return isSetIsClosed();
			case ModelPackage.TCOLLABORATION__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
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
		result.append(" (artifactGroup: ");
		result.append(artifactGroup);
		result.append(", conversationNodeGroup: ");
		result.append(conversationNodeGroup);
		result.append(", choreographyRef: ");
		result.append(choreographyRef);
		result.append(", isClosed: ");
		if (isClosedESet) result.append(isClosed); else result.append("<unset>");
		result.append(", name: ");
		result.append(name);
		result.append(')');
		return result.toString();
	}

} //TCollaborationImpl
