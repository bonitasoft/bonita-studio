/**
 * Copyright (C) 2009-2015 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.model.process.impl;

import java.util.Collection;

import org.bonitasoft.studio.model.expression.Expression;

import org.bonitasoft.studio.model.form.ViewForm;

import org.bonitasoft.studio.model.process.AbstractPageFlow;
import org.bonitasoft.studio.model.process.Connection;
import org.bonitasoft.studio.model.process.Connector;
import org.bonitasoft.studio.model.process.ConsultationPageFlowType;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.DataAware;
import org.bonitasoft.studio.model.process.Event;
import org.bonitasoft.studio.model.process.FlowElement;
import org.bonitasoft.studio.model.process.IntermediateCatchSignalEvent;
import org.bonitasoft.studio.model.process.PageFlowTransition;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.SourceElement;
import org.bonitasoft.studio.model.process.TargetElement;
import org.bonitasoft.studio.model.process.TextAnnotationAttachment;
import org.bonitasoft.studio.model.process.ViewPageFlow;

import org.bonitasoft.studio.model.simulation.DataChange;
import org.bonitasoft.studio.model.simulation.ResourceUsage;
import org.bonitasoft.studio.model.simulation.SimulationActivity;
import org.bonitasoft.studio.model.simulation.SimulationData;
import org.bonitasoft.studio.model.simulation.SimulationDataContainer;
import org.bonitasoft.studio.model.simulation.SimulationPackage;
import org.bonitasoft.studio.model.simulation.SimulationTransition;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Intermediate Catch Signal Event</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.bonitasoft.studio.model.process.impl.IntermediateCatchSignalEventImpl#getDocumentation
 * <em>Documentation</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.IntermediateCatchSignalEventImpl#getName <em>Name</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.IntermediateCatchSignalEventImpl#getTextAnnotationAttachment <em>Text
 * Annotation Attachment</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.IntermediateCatchSignalEventImpl#getSignalCode <em>Signal
 * Code</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.IntermediateCatchSignalEventImpl#getOutgoing <em>Outgoing</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.IntermediateCatchSignalEventImpl#getIncoming <em>Incoming</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.IntermediateCatchSignalEventImpl#getRegExpToHideDefaultField <em>Reg
 * Exp To Hide Default Field</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.IntermediateCatchSignalEventImpl#isUseRegExpToHideDefaultField <em>Use
 * Reg Exp To Hide Default Field</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.IntermediateCatchSignalEventImpl#getViewPageFlowTransitions <em>View
 * Page Flow Transitions</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.IntermediateCatchSignalEventImpl#getViewTransientData <em>View
 * Transient Data</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.IntermediateCatchSignalEventImpl#getViewPageFlowConnectors <em>View
 * Page Flow Connectors</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.IntermediateCatchSignalEventImpl#getViewPageFlowType <em>View Page
 * Flow Type</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.IntermediateCatchSignalEventImpl#getViewForm <em>View Form</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.IntermediateCatchSignalEventImpl#getViewPageFlowRedirectionURL
 * <em>View Page Flow Redirection URL</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.IntermediateCatchSignalEventImpl#getSimulationData <em>Simulation
 * Data</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.IntermediateCatchSignalEventImpl#getResourcesUsages <em>Resources
 * Usages</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.IntermediateCatchSignalEventImpl#getExecutionTime <em>Execution
 * Time</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.IntermediateCatchSignalEventImpl#getEstimatedTime <em>Estimated
 * Time</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.IntermediateCatchSignalEventImpl#getMaximumTime <em>Maximum
 * Time</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.IntermediateCatchSignalEventImpl#isContigous <em>Contigous</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.IntermediateCatchSignalEventImpl#isExclusiveOutgoingTransition
 * <em>Exclusive Outgoing Transition</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.IntermediateCatchSignalEventImpl#getLoopTransition <em>Loop
 * Transition</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.IntermediateCatchSignalEventImpl#getDataChange <em>Data
 * Change</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.IntermediateCatchSignalEventImpl#getDynamicLabel <em>Dynamic
 * Label</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.IntermediateCatchSignalEventImpl#getDynamicDescription <em>Dynamic
 * Description</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.IntermediateCatchSignalEventImpl#getStepSummary <em>Step
 * Summary</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.IntermediateCatchSignalEventImpl#getData <em>Data</em>}</li>
 * </ul>
 *
 * @generated
 */
public class IntermediateCatchSignalEventImpl extends EObjectImpl implements IntermediateCatchSignalEvent {

    /**
     * The default value of the '{@link #getDocumentation() <em>Documentation</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getDocumentation()
     * @generated
     * @ordered
     */
    protected static final String DOCUMENTATION_EDEFAULT = ""; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getDocumentation() <em>Documentation</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getDocumentation()
     * @generated
     * @ordered
     */
    protected String documentation = DOCUMENTATION_EDEFAULT;

    /**
     * The default value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getName()
     * @generated
     * @ordered
     */
    protected static final String NAME_EDEFAULT = ""; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getName()
     * @generated
     * @ordered
     */
    protected String name = NAME_EDEFAULT;

    /**
     * The cached value of the '{@link #getTextAnnotationAttachment() <em>Text Annotation Attachment</em>}' containment
     * reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getTextAnnotationAttachment()
     * @generated
     * @ordered
     */
    protected EList<TextAnnotationAttachment> textAnnotationAttachment;

    /**
     * The default value of the '{@link #getSignalCode() <em>Signal Code</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getSignalCode()
     * @generated
     * @ordered
     */
    protected static final String SIGNAL_CODE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getSignalCode() <em>Signal Code</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getSignalCode()
     * @generated
     * @ordered
     */
    protected String signalCode = SIGNAL_CODE_EDEFAULT;

    /**
     * The cached value of the '{@link #getOutgoing() <em>Outgoing</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getOutgoing()
     * @generated
     * @ordered
     */
    protected EList<Connection> outgoing;

    /**
     * The cached value of the '{@link #getIncoming() <em>Incoming</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getIncoming()
     * @generated
     * @ordered
     */
    protected EList<Connection> incoming;

    /**
     * The default value of the '{@link #getRegExpToHideDefaultField() <em>Reg Exp To Hide Default Field</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getRegExpToHideDefaultField()
     * @generated
     * @ordered
     */
    protected static final String REG_EXP_TO_HIDE_DEFAULT_FIELD_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getRegExpToHideDefaultField() <em>Reg Exp To Hide Default Field</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getRegExpToHideDefaultField()
     * @generated
     * @ordered
     */
    protected String regExpToHideDefaultField = REG_EXP_TO_HIDE_DEFAULT_FIELD_EDEFAULT;

    /**
     * The default value of the '{@link #isUseRegExpToHideDefaultField() <em>Use Reg Exp To Hide Default Field</em>}'
     * attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #isUseRegExpToHideDefaultField()
     * @generated
     * @ordered
     */
    protected static final boolean USE_REG_EXP_TO_HIDE_DEFAULT_FIELD_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isUseRegExpToHideDefaultField() <em>Use Reg Exp To Hide Default Field</em>}'
     * attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #isUseRegExpToHideDefaultField()
     * @generated
     * @ordered
     */
    protected boolean useRegExpToHideDefaultField = USE_REG_EXP_TO_HIDE_DEFAULT_FIELD_EDEFAULT;

    /**
     * The cached value of the '{@link #getViewPageFlowTransitions() <em>View Page Flow Transitions</em>}' containment
     * reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getViewPageFlowTransitions()
     * @generated
     * @ordered
     */
    protected EList<PageFlowTransition> viewPageFlowTransitions;

    /**
     * The cached value of the '{@link #getViewTransientData() <em>View Transient Data</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getViewTransientData()
     * @generated
     * @ordered
     */
    protected EList<Data> viewTransientData;

    /**
     * The cached value of the '{@link #getViewPageFlowConnectors() <em>View Page Flow Connectors</em>}' containment
     * reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getViewPageFlowConnectors()
     * @generated
     * @ordered
     */
    protected EList<Connector> viewPageFlowConnectors;

    /**
     * The default value of the '{@link #getViewPageFlowType() <em>View Page Flow Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getViewPageFlowType()
     * @generated
     * @ordered
     */
    protected static final ConsultationPageFlowType VIEW_PAGE_FLOW_TYPE_EDEFAULT = ConsultationPageFlowType.PAGEFLOW;

    /**
     * The cached value of the '{@link #getViewPageFlowType() <em>View Page Flow Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getViewPageFlowType()
     * @generated
     * @ordered
     */
    protected ConsultationPageFlowType viewPageFlowType = VIEW_PAGE_FLOW_TYPE_EDEFAULT;

    /**
     * The cached value of the '{@link #getViewForm() <em>View Form</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getViewForm()
     * @generated
     * @ordered
     */
    protected EList<ViewForm> viewForm;

    /**
     * The cached value of the '{@link #getViewPageFlowRedirectionURL() <em>View Page Flow Redirection URL</em>}' containment
     * reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getViewPageFlowRedirectionURL()
     * @generated
     * @ordered
     */
    protected Expression viewPageFlowRedirectionURL;

    /**
     * The cached value of the '{@link #getSimulationData() <em>Simulation Data</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getSimulationData()
     * @generated
     * @ordered
     */
    protected EList<SimulationData> simulationData;

    /**
     * The cached value of the '{@link #getResourcesUsages() <em>Resources Usages</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getResourcesUsages()
     * @generated
     * @ordered
     */
    protected EList<ResourceUsage> resourcesUsages;

    /**
     * The default value of the '{@link #getExecutionTime() <em>Execution Time</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getExecutionTime()
     * @generated
     * @ordered
     */
    protected static final long EXECUTION_TIME_EDEFAULT = 0L;

    /**
     * The cached value of the '{@link #getExecutionTime() <em>Execution Time</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getExecutionTime()
     * @generated
     * @ordered
     */
    protected long executionTime = EXECUTION_TIME_EDEFAULT;

    /**
     * The default value of the '{@link #getEstimatedTime() <em>Estimated Time</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getEstimatedTime()
     * @generated
     * @ordered
     */
    protected static final double ESTIMATED_TIME_EDEFAULT = 0.0;

    /**
     * The cached value of the '{@link #getEstimatedTime() <em>Estimated Time</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getEstimatedTime()
     * @generated
     * @ordered
     */
    protected double estimatedTime = ESTIMATED_TIME_EDEFAULT;

    /**
     * The default value of the '{@link #getMaximumTime() <em>Maximum Time</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getMaximumTime()
     * @generated
     * @ordered
     */
    protected static final double MAXIMUM_TIME_EDEFAULT = 0.0;

    /**
     * The cached value of the '{@link #getMaximumTime() <em>Maximum Time</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getMaximumTime()
     * @generated
     * @ordered
     */
    protected double maximumTime = MAXIMUM_TIME_EDEFAULT;

    /**
     * The default value of the '{@link #isContigous() <em>Contigous</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #isContigous()
     * @generated
     * @ordered
     */
    protected static final boolean CONTIGOUS_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isContigous() <em>Contigous</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #isContigous()
     * @generated
     * @ordered
     */
    protected boolean contigous = CONTIGOUS_EDEFAULT;

    /**
     * The default value of the '{@link #isExclusiveOutgoingTransition() <em>Exclusive Outgoing Transition</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #isExclusiveOutgoingTransition()
     * @generated
     * @ordered
     */
    protected static final boolean EXCLUSIVE_OUTGOING_TRANSITION_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isExclusiveOutgoingTransition() <em>Exclusive Outgoing Transition</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #isExclusiveOutgoingTransition()
     * @generated
     * @ordered
     */
    protected boolean exclusiveOutgoingTransition = EXCLUSIVE_OUTGOING_TRANSITION_EDEFAULT;

    /**
     * The cached value of the '{@link #getLoopTransition() <em>Loop Transition</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getLoopTransition()
     * @generated
     * @ordered
     */
    protected SimulationTransition loopTransition;

    /**
     * The cached value of the '{@link #getDataChange() <em>Data Change</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getDataChange()
     * @generated
     * @ordered
     */
    protected EList<DataChange> dataChange;

    /**
     * The cached value of the '{@link #getDynamicLabel() <em>Dynamic Label</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getDynamicLabel()
     * @generated
     * @ordered
     */
    protected Expression dynamicLabel;

    /**
     * The cached value of the '{@link #getDynamicDescription() <em>Dynamic Description</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getDynamicDescription()
     * @generated
     * @ordered
     */
    protected Expression dynamicDescription;

    /**
     * The cached value of the '{@link #getStepSummary() <em>Step Summary</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getStepSummary()
     * @generated
     * @ordered
     */
    protected Expression stepSummary;

    /**
     * The cached value of the '{@link #getData() <em>Data</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getData()
     * @generated
     * @ordered
     */
    protected EList<Data> data;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    protected IntermediateCatchSignalEventImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ProcessPackage.Literals.INTERMEDIATE_CATCH_SIGNAL_EVENT;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getDocumentation() {
        return documentation;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setDocumentation(String newDocumentation) {
        String oldDocumentation = documentation;
        documentation = newDocumentation;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__DOCUMENTATION, oldDocumentation, documentation));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getName() {
        return name;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setName(String newName) {
        String oldName = name;
        name = newName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__NAME,
                    oldName, name));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<TextAnnotationAttachment> getTextAnnotationAttachment() {
        if (textAnnotationAttachment == null) {
            textAnnotationAttachment = new EObjectContainmentWithInverseEList<TextAnnotationAttachment>(
                    TextAnnotationAttachment.class, this,
                    ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__TEXT_ANNOTATION_ATTACHMENT,
                    ProcessPackage.TEXT_ANNOTATION_ATTACHMENT__TARGET);
        }
        return textAnnotationAttachment;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getSignalCode() {
        return signalCode;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setSignalCode(String newSignalCode) {
        String oldSignalCode = signalCode;
        signalCode = newSignalCode;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__SIGNAL_CODE, oldSignalCode, signalCode));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<Connection> getOutgoing() {
        if (outgoing == null) {
            outgoing = new EObjectWithInverseResolvingEList<Connection>(Connection.class, this,
                    ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__OUTGOING, ProcessPackage.CONNECTION__SOURCE);
        }
        return outgoing;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<Connection> getIncoming() {
        if (incoming == null) {
            incoming = new EObjectWithInverseResolvingEList<Connection>(Connection.class, this,
                    ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__INCOMING, ProcessPackage.CONNECTION__TARGET);
        }
        return incoming;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getRegExpToHideDefaultField() {
        return regExpToHideDefaultField;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setRegExpToHideDefaultField(String newRegExpToHideDefaultField) {
        String oldRegExpToHideDefaultField = regExpToHideDefaultField;
        regExpToHideDefaultField = newRegExpToHideDefaultField;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__REG_EXP_TO_HIDE_DEFAULT_FIELD,
                    oldRegExpToHideDefaultField, regExpToHideDefaultField));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public boolean isUseRegExpToHideDefaultField() {
        return useRegExpToHideDefaultField;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setUseRegExpToHideDefaultField(boolean newUseRegExpToHideDefaultField) {
        boolean oldUseRegExpToHideDefaultField = useRegExpToHideDefaultField;
        useRegExpToHideDefaultField = newUseRegExpToHideDefaultField;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__USE_REG_EXP_TO_HIDE_DEFAULT_FIELD,
                    oldUseRegExpToHideDefaultField, useRegExpToHideDefaultField));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<PageFlowTransition> getViewPageFlowTransitions() {
        if (viewPageFlowTransitions == null) {
            viewPageFlowTransitions = new EObjectContainmentEList<PageFlowTransition>(PageFlowTransition.class, this,
                    ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__VIEW_PAGE_FLOW_TRANSITIONS);
        }
        return viewPageFlowTransitions;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<Data> getViewTransientData() {
        if (viewTransientData == null) {
            viewTransientData = new EObjectContainmentEList<Data>(Data.class, this,
                    ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__VIEW_TRANSIENT_DATA);
        }
        return viewTransientData;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<Connector> getViewPageFlowConnectors() {
        if (viewPageFlowConnectors == null) {
            viewPageFlowConnectors = new EObjectContainmentEList<Connector>(Connector.class, this,
                    ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__VIEW_PAGE_FLOW_CONNECTORS);
        }
        return viewPageFlowConnectors;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public ConsultationPageFlowType getViewPageFlowType() {
        return viewPageFlowType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setViewPageFlowType(ConsultationPageFlowType newViewPageFlowType) {
        ConsultationPageFlowType oldViewPageFlowType = viewPageFlowType;
        viewPageFlowType = newViewPageFlowType == null ? VIEW_PAGE_FLOW_TYPE_EDEFAULT : newViewPageFlowType;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__VIEW_PAGE_FLOW_TYPE, oldViewPageFlowType,
                    viewPageFlowType));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<ViewForm> getViewForm() {
        if (viewForm == null) {
            viewForm = new EObjectContainmentEList<ViewForm>(ViewForm.class, this,
                    ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__VIEW_FORM);
        }
        return viewForm;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public Expression getViewPageFlowRedirectionURL() {
        return viewPageFlowRedirectionURL;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetViewPageFlowRedirectionURL(Expression newViewPageFlowRedirectionURL,
            NotificationChain msgs) {
        Expression oldViewPageFlowRedirectionURL = viewPageFlowRedirectionURL;
        viewPageFlowRedirectionURL = newViewPageFlowRedirectionURL;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
                    ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__VIEW_PAGE_FLOW_REDIRECTION_URL,
                    oldViewPageFlowRedirectionURL, newViewPageFlowRedirectionURL);
            if (msgs == null)
                msgs = notification;
            else
                msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setViewPageFlowRedirectionURL(Expression newViewPageFlowRedirectionURL) {
        if (newViewPageFlowRedirectionURL != viewPageFlowRedirectionURL) {
            NotificationChain msgs = null;
            if (viewPageFlowRedirectionURL != null)
                msgs = ((InternalEObject) viewPageFlowRedirectionURL).eInverseRemove(this,
                        EOPPOSITE_FEATURE_BASE
                                - ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__VIEW_PAGE_FLOW_REDIRECTION_URL,
                        null, msgs);
            if (newViewPageFlowRedirectionURL != null)
                msgs = ((InternalEObject) newViewPageFlowRedirectionURL).eInverseAdd(this,
                        EOPPOSITE_FEATURE_BASE
                                - ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__VIEW_PAGE_FLOW_REDIRECTION_URL,
                        null, msgs);
            msgs = basicSetViewPageFlowRedirectionURL(newViewPageFlowRedirectionURL, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__VIEW_PAGE_FLOW_REDIRECTION_URL,
                    newViewPageFlowRedirectionURL, newViewPageFlowRedirectionURL));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<SimulationData> getSimulationData() {
        if (simulationData == null) {
            simulationData = new EObjectContainmentEList<SimulationData>(SimulationData.class, this,
                    ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__SIMULATION_DATA);
        }
        return simulationData;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<ResourceUsage> getResourcesUsages() {
        if (resourcesUsages == null) {
            resourcesUsages = new EObjectContainmentEList<ResourceUsage>(ResourceUsage.class, this,
                    ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__RESOURCES_USAGES);
        }
        return resourcesUsages;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public long getExecutionTime() {
        return executionTime;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setExecutionTime(long newExecutionTime) {
        long oldExecutionTime = executionTime;
        executionTime = newExecutionTime;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__EXECUTION_TIME, oldExecutionTime, executionTime));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public double getEstimatedTime() {
        return estimatedTime;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setEstimatedTime(double newEstimatedTime) {
        double oldEstimatedTime = estimatedTime;
        estimatedTime = newEstimatedTime;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__ESTIMATED_TIME, oldEstimatedTime, estimatedTime));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public double getMaximumTime() {
        return maximumTime;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setMaximumTime(double newMaximumTime) {
        double oldMaximumTime = maximumTime;
        maximumTime = newMaximumTime;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__MAXIMUM_TIME, oldMaximumTime, maximumTime));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public boolean isContigous() {
        return contigous;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setContigous(boolean newContigous) {
        boolean oldContigous = contigous;
        contigous = newContigous;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__CONTIGOUS,
                    oldContigous, contigous));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public boolean isExclusiveOutgoingTransition() {
        return exclusiveOutgoingTransition;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setExclusiveOutgoingTransition(boolean newExclusiveOutgoingTransition) {
        boolean oldExclusiveOutgoingTransition = exclusiveOutgoingTransition;
        exclusiveOutgoingTransition = newExclusiveOutgoingTransition;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__EXCLUSIVE_OUTGOING_TRANSITION,
                    oldExclusiveOutgoingTransition, exclusiveOutgoingTransition));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public SimulationTransition getLoopTransition() {
        return loopTransition;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetLoopTransition(SimulationTransition newLoopTransition, NotificationChain msgs) {
        SimulationTransition oldLoopTransition = loopTransition;
        loopTransition = newLoopTransition;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
                    ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__LOOP_TRANSITION, oldLoopTransition, newLoopTransition);
            if (msgs == null)
                msgs = notification;
            else
                msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setLoopTransition(SimulationTransition newLoopTransition) {
        if (newLoopTransition != loopTransition) {
            NotificationChain msgs = null;
            if (loopTransition != null)
                msgs = ((InternalEObject) loopTransition).eInverseRemove(this,
                        EOPPOSITE_FEATURE_BASE - ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__LOOP_TRANSITION, null,
                        msgs);
            if (newLoopTransition != null)
                msgs = ((InternalEObject) newLoopTransition).eInverseAdd(this,
                        EOPPOSITE_FEATURE_BASE - ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__LOOP_TRANSITION, null,
                        msgs);
            msgs = basicSetLoopTransition(newLoopTransition, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__LOOP_TRANSITION, newLoopTransition, newLoopTransition));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<DataChange> getDataChange() {
        if (dataChange == null) {
            dataChange = new EObjectContainmentEList<DataChange>(DataChange.class, this,
                    ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__DATA_CHANGE);
        }
        return dataChange;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public Expression getDynamicLabel() {
        return dynamicLabel;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetDynamicLabel(Expression newDynamicLabel, NotificationChain msgs) {
        Expression oldDynamicLabel = dynamicLabel;
        dynamicLabel = newDynamicLabel;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
                    ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__DYNAMIC_LABEL, oldDynamicLabel, newDynamicLabel);
            if (msgs == null)
                msgs = notification;
            else
                msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setDynamicLabel(Expression newDynamicLabel) {
        if (newDynamicLabel != dynamicLabel) {
            NotificationChain msgs = null;
            if (dynamicLabel != null)
                msgs = ((InternalEObject) dynamicLabel).eInverseRemove(this,
                        EOPPOSITE_FEATURE_BASE - ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__DYNAMIC_LABEL, null, msgs);
            if (newDynamicLabel != null)
                msgs = ((InternalEObject) newDynamicLabel).eInverseAdd(this,
                        EOPPOSITE_FEATURE_BASE - ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__DYNAMIC_LABEL, null, msgs);
            msgs = basicSetDynamicLabel(newDynamicLabel, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__DYNAMIC_LABEL, newDynamicLabel, newDynamicLabel));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public Expression getDynamicDescription() {
        return dynamicDescription;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetDynamicDescription(Expression newDynamicDescription, NotificationChain msgs) {
        Expression oldDynamicDescription = dynamicDescription;
        dynamicDescription = newDynamicDescription;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
                    ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__DYNAMIC_DESCRIPTION, oldDynamicDescription,
                    newDynamicDescription);
            if (msgs == null)
                msgs = notification;
            else
                msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setDynamicDescription(Expression newDynamicDescription) {
        if (newDynamicDescription != dynamicDescription) {
            NotificationChain msgs = null;
            if (dynamicDescription != null)
                msgs = ((InternalEObject) dynamicDescription).eInverseRemove(this,
                        EOPPOSITE_FEATURE_BASE - ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__DYNAMIC_DESCRIPTION, null,
                        msgs);
            if (newDynamicDescription != null)
                msgs = ((InternalEObject) newDynamicDescription).eInverseAdd(this,
                        EOPPOSITE_FEATURE_BASE - ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__DYNAMIC_DESCRIPTION, null,
                        msgs);
            msgs = basicSetDynamicDescription(newDynamicDescription, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__DYNAMIC_DESCRIPTION, newDynamicDescription,
                    newDynamicDescription));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public Expression getStepSummary() {
        return stepSummary;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetStepSummary(Expression newStepSummary, NotificationChain msgs) {
        Expression oldStepSummary = stepSummary;
        stepSummary = newStepSummary;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
                    ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__STEP_SUMMARY, oldStepSummary, newStepSummary);
            if (msgs == null)
                msgs = notification;
            else
                msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setStepSummary(Expression newStepSummary) {
        if (newStepSummary != stepSummary) {
            NotificationChain msgs = null;
            if (stepSummary != null)
                msgs = ((InternalEObject) stepSummary).eInverseRemove(this,
                        EOPPOSITE_FEATURE_BASE - ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__STEP_SUMMARY, null, msgs);
            if (newStepSummary != null)
                msgs = ((InternalEObject) newStepSummary).eInverseAdd(this,
                        EOPPOSITE_FEATURE_BASE - ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__STEP_SUMMARY, null, msgs);
            msgs = basicSetStepSummary(newStepSummary, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__STEP_SUMMARY, newStepSummary, newStepSummary));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<Data> getData() {
        if (data == null) {
            data = new EObjectContainmentEList<Data>(Data.class, this, ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__DATA);
        }
        return data;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__TEXT_ANNOTATION_ATTACHMENT:
                return ((InternalEList<InternalEObject>) (InternalEList<?>) getTextAnnotationAttachment()).basicAdd(otherEnd,
                        msgs);
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__OUTGOING:
                return ((InternalEList<InternalEObject>) (InternalEList<?>) getOutgoing()).basicAdd(otherEnd, msgs);
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__INCOMING:
                return ((InternalEList<InternalEObject>) (InternalEList<?>) getIncoming()).basicAdd(otherEnd, msgs);
        }
        return super.eInverseAdd(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__TEXT_ANNOTATION_ATTACHMENT:
                return ((InternalEList<?>) getTextAnnotationAttachment()).basicRemove(otherEnd, msgs);
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__OUTGOING:
                return ((InternalEList<?>) getOutgoing()).basicRemove(otherEnd, msgs);
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__INCOMING:
                return ((InternalEList<?>) getIncoming()).basicRemove(otherEnd, msgs);
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__VIEW_PAGE_FLOW_TRANSITIONS:
                return ((InternalEList<?>) getViewPageFlowTransitions()).basicRemove(otherEnd, msgs);
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__VIEW_TRANSIENT_DATA:
                return ((InternalEList<?>) getViewTransientData()).basicRemove(otherEnd, msgs);
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__VIEW_PAGE_FLOW_CONNECTORS:
                return ((InternalEList<?>) getViewPageFlowConnectors()).basicRemove(otherEnd, msgs);
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__VIEW_FORM:
                return ((InternalEList<?>) getViewForm()).basicRemove(otherEnd, msgs);
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__VIEW_PAGE_FLOW_REDIRECTION_URL:
                return basicSetViewPageFlowRedirectionURL(null, msgs);
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__SIMULATION_DATA:
                return ((InternalEList<?>) getSimulationData()).basicRemove(otherEnd, msgs);
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__RESOURCES_USAGES:
                return ((InternalEList<?>) getResourcesUsages()).basicRemove(otherEnd, msgs);
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__LOOP_TRANSITION:
                return basicSetLoopTransition(null, msgs);
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__DATA_CHANGE:
                return ((InternalEList<?>) getDataChange()).basicRemove(otherEnd, msgs);
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__DYNAMIC_LABEL:
                return basicSetDynamicLabel(null, msgs);
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__DYNAMIC_DESCRIPTION:
                return basicSetDynamicDescription(null, msgs);
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__STEP_SUMMARY:
                return basicSetStepSummary(null, msgs);
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__DATA:
                return ((InternalEList<?>) getData()).basicRemove(otherEnd, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__DOCUMENTATION:
                return getDocumentation();
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__NAME:
                return getName();
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__TEXT_ANNOTATION_ATTACHMENT:
                return getTextAnnotationAttachment();
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__SIGNAL_CODE:
                return getSignalCode();
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__OUTGOING:
                return getOutgoing();
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__INCOMING:
                return getIncoming();
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__REG_EXP_TO_HIDE_DEFAULT_FIELD:
                return getRegExpToHideDefaultField();
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__USE_REG_EXP_TO_HIDE_DEFAULT_FIELD:
                return isUseRegExpToHideDefaultField();
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__VIEW_PAGE_FLOW_TRANSITIONS:
                return getViewPageFlowTransitions();
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__VIEW_TRANSIENT_DATA:
                return getViewTransientData();
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__VIEW_PAGE_FLOW_CONNECTORS:
                return getViewPageFlowConnectors();
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__VIEW_PAGE_FLOW_TYPE:
                return getViewPageFlowType();
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__VIEW_FORM:
                return getViewForm();
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__VIEW_PAGE_FLOW_REDIRECTION_URL:
                return getViewPageFlowRedirectionURL();
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__SIMULATION_DATA:
                return getSimulationData();
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__RESOURCES_USAGES:
                return getResourcesUsages();
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__EXECUTION_TIME:
                return getExecutionTime();
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__ESTIMATED_TIME:
                return getEstimatedTime();
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__MAXIMUM_TIME:
                return getMaximumTime();
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__CONTIGOUS:
                return isContigous();
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__EXCLUSIVE_OUTGOING_TRANSITION:
                return isExclusiveOutgoingTransition();
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__LOOP_TRANSITION:
                return getLoopTransition();
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__DATA_CHANGE:
                return getDataChange();
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__DYNAMIC_LABEL:
                return getDynamicLabel();
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__DYNAMIC_DESCRIPTION:
                return getDynamicDescription();
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__STEP_SUMMARY:
                return getStepSummary();
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__DATA:
                return getData();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__DOCUMENTATION:
                setDocumentation((String) newValue);
                return;
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__NAME:
                setName((String) newValue);
                return;
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__TEXT_ANNOTATION_ATTACHMENT:
                getTextAnnotationAttachment().clear();
                getTextAnnotationAttachment().addAll((Collection<? extends TextAnnotationAttachment>) newValue);
                return;
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__SIGNAL_CODE:
                setSignalCode((String) newValue);
                return;
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__OUTGOING:
                getOutgoing().clear();
                getOutgoing().addAll((Collection<? extends Connection>) newValue);
                return;
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__INCOMING:
                getIncoming().clear();
                getIncoming().addAll((Collection<? extends Connection>) newValue);
                return;
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__REG_EXP_TO_HIDE_DEFAULT_FIELD:
                setRegExpToHideDefaultField((String) newValue);
                return;
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__USE_REG_EXP_TO_HIDE_DEFAULT_FIELD:
                setUseRegExpToHideDefaultField((Boolean) newValue);
                return;
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__VIEW_PAGE_FLOW_TRANSITIONS:
                getViewPageFlowTransitions().clear();
                getViewPageFlowTransitions().addAll((Collection<? extends PageFlowTransition>) newValue);
                return;
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__VIEW_TRANSIENT_DATA:
                getViewTransientData().clear();
                getViewTransientData().addAll((Collection<? extends Data>) newValue);
                return;
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__VIEW_PAGE_FLOW_CONNECTORS:
                getViewPageFlowConnectors().clear();
                getViewPageFlowConnectors().addAll((Collection<? extends Connector>) newValue);
                return;
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__VIEW_PAGE_FLOW_TYPE:
                setViewPageFlowType((ConsultationPageFlowType) newValue);
                return;
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__VIEW_FORM:
                getViewForm().clear();
                getViewForm().addAll((Collection<? extends ViewForm>) newValue);
                return;
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__VIEW_PAGE_FLOW_REDIRECTION_URL:
                setViewPageFlowRedirectionURL((Expression) newValue);
                return;
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__SIMULATION_DATA:
                getSimulationData().clear();
                getSimulationData().addAll((Collection<? extends SimulationData>) newValue);
                return;
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__RESOURCES_USAGES:
                getResourcesUsages().clear();
                getResourcesUsages().addAll((Collection<? extends ResourceUsage>) newValue);
                return;
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__EXECUTION_TIME:
                setExecutionTime((Long) newValue);
                return;
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__ESTIMATED_TIME:
                setEstimatedTime((Double) newValue);
                return;
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__MAXIMUM_TIME:
                setMaximumTime((Double) newValue);
                return;
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__CONTIGOUS:
                setContigous((Boolean) newValue);
                return;
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__EXCLUSIVE_OUTGOING_TRANSITION:
                setExclusiveOutgoingTransition((Boolean) newValue);
                return;
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__LOOP_TRANSITION:
                setLoopTransition((SimulationTransition) newValue);
                return;
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__DATA_CHANGE:
                getDataChange().clear();
                getDataChange().addAll((Collection<? extends DataChange>) newValue);
                return;
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__DYNAMIC_LABEL:
                setDynamicLabel((Expression) newValue);
                return;
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__DYNAMIC_DESCRIPTION:
                setDynamicDescription((Expression) newValue);
                return;
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__STEP_SUMMARY:
                setStepSummary((Expression) newValue);
                return;
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__DATA:
                getData().clear();
                getData().addAll((Collection<? extends Data>) newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__DOCUMENTATION:
                setDocumentation(DOCUMENTATION_EDEFAULT);
                return;
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__NAME:
                setName(NAME_EDEFAULT);
                return;
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__TEXT_ANNOTATION_ATTACHMENT:
                getTextAnnotationAttachment().clear();
                return;
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__SIGNAL_CODE:
                setSignalCode(SIGNAL_CODE_EDEFAULT);
                return;
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__OUTGOING:
                getOutgoing().clear();
                return;
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__INCOMING:
                getIncoming().clear();
                return;
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__REG_EXP_TO_HIDE_DEFAULT_FIELD:
                setRegExpToHideDefaultField(REG_EXP_TO_HIDE_DEFAULT_FIELD_EDEFAULT);
                return;
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__USE_REG_EXP_TO_HIDE_DEFAULT_FIELD:
                setUseRegExpToHideDefaultField(USE_REG_EXP_TO_HIDE_DEFAULT_FIELD_EDEFAULT);
                return;
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__VIEW_PAGE_FLOW_TRANSITIONS:
                getViewPageFlowTransitions().clear();
                return;
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__VIEW_TRANSIENT_DATA:
                getViewTransientData().clear();
                return;
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__VIEW_PAGE_FLOW_CONNECTORS:
                getViewPageFlowConnectors().clear();
                return;
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__VIEW_PAGE_FLOW_TYPE:
                setViewPageFlowType(VIEW_PAGE_FLOW_TYPE_EDEFAULT);
                return;
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__VIEW_FORM:
                getViewForm().clear();
                return;
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__VIEW_PAGE_FLOW_REDIRECTION_URL:
                setViewPageFlowRedirectionURL((Expression) null);
                return;
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__SIMULATION_DATA:
                getSimulationData().clear();
                return;
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__RESOURCES_USAGES:
                getResourcesUsages().clear();
                return;
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__EXECUTION_TIME:
                setExecutionTime(EXECUTION_TIME_EDEFAULT);
                return;
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__ESTIMATED_TIME:
                setEstimatedTime(ESTIMATED_TIME_EDEFAULT);
                return;
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__MAXIMUM_TIME:
                setMaximumTime(MAXIMUM_TIME_EDEFAULT);
                return;
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__CONTIGOUS:
                setContigous(CONTIGOUS_EDEFAULT);
                return;
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__EXCLUSIVE_OUTGOING_TRANSITION:
                setExclusiveOutgoingTransition(EXCLUSIVE_OUTGOING_TRANSITION_EDEFAULT);
                return;
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__LOOP_TRANSITION:
                setLoopTransition((SimulationTransition) null);
                return;
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__DATA_CHANGE:
                getDataChange().clear();
                return;
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__DYNAMIC_LABEL:
                setDynamicLabel((Expression) null);
                return;
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__DYNAMIC_DESCRIPTION:
                setDynamicDescription((Expression) null);
                return;
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__STEP_SUMMARY:
                setStepSummary((Expression) null);
                return;
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__DATA:
                getData().clear();
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__DOCUMENTATION:
                return DOCUMENTATION_EDEFAULT == null ? documentation != null
                        : !DOCUMENTATION_EDEFAULT.equals(documentation);
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__TEXT_ANNOTATION_ATTACHMENT:
                return textAnnotationAttachment != null && !textAnnotationAttachment.isEmpty();
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__SIGNAL_CODE:
                return SIGNAL_CODE_EDEFAULT == null ? signalCode != null : !SIGNAL_CODE_EDEFAULT.equals(signalCode);
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__OUTGOING:
                return outgoing != null && !outgoing.isEmpty();
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__INCOMING:
                return incoming != null && !incoming.isEmpty();
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__REG_EXP_TO_HIDE_DEFAULT_FIELD:
                return REG_EXP_TO_HIDE_DEFAULT_FIELD_EDEFAULT == null ? regExpToHideDefaultField != null
                        : !REG_EXP_TO_HIDE_DEFAULT_FIELD_EDEFAULT.equals(regExpToHideDefaultField);
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__USE_REG_EXP_TO_HIDE_DEFAULT_FIELD:
                return useRegExpToHideDefaultField != USE_REG_EXP_TO_HIDE_DEFAULT_FIELD_EDEFAULT;
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__VIEW_PAGE_FLOW_TRANSITIONS:
                return viewPageFlowTransitions != null && !viewPageFlowTransitions.isEmpty();
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__VIEW_TRANSIENT_DATA:
                return viewTransientData != null && !viewTransientData.isEmpty();
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__VIEW_PAGE_FLOW_CONNECTORS:
                return viewPageFlowConnectors != null && !viewPageFlowConnectors.isEmpty();
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__VIEW_PAGE_FLOW_TYPE:
                return viewPageFlowType != VIEW_PAGE_FLOW_TYPE_EDEFAULT;
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__VIEW_FORM:
                return viewForm != null && !viewForm.isEmpty();
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__VIEW_PAGE_FLOW_REDIRECTION_URL:
                return viewPageFlowRedirectionURL != null;
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__SIMULATION_DATA:
                return simulationData != null && !simulationData.isEmpty();
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__RESOURCES_USAGES:
                return resourcesUsages != null && !resourcesUsages.isEmpty();
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__EXECUTION_TIME:
                return executionTime != EXECUTION_TIME_EDEFAULT;
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__ESTIMATED_TIME:
                return estimatedTime != ESTIMATED_TIME_EDEFAULT;
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__MAXIMUM_TIME:
                return maximumTime != MAXIMUM_TIME_EDEFAULT;
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__CONTIGOUS:
                return contigous != CONTIGOUS_EDEFAULT;
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__EXCLUSIVE_OUTGOING_TRANSITION:
                return exclusiveOutgoingTransition != EXCLUSIVE_OUTGOING_TRANSITION_EDEFAULT;
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__LOOP_TRANSITION:
                return loopTransition != null;
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__DATA_CHANGE:
                return dataChange != null && !dataChange.isEmpty();
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__DYNAMIC_LABEL:
                return dynamicLabel != null;
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__DYNAMIC_DESCRIPTION:
                return dynamicDescription != null;
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__STEP_SUMMARY:
                return stepSummary != null;
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__DATA:
                return data != null && !data.isEmpty();
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
        if (baseClass == SourceElement.class) {
            switch (derivedFeatureID) {
                case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__OUTGOING:
                    return ProcessPackage.SOURCE_ELEMENT__OUTGOING;
                default:
                    return -1;
            }
        }
        if (baseClass == TargetElement.class) {
            switch (derivedFeatureID) {
                case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__INCOMING:
                    return ProcessPackage.TARGET_ELEMENT__INCOMING;
                default:
                    return -1;
            }
        }
        if (baseClass == AbstractPageFlow.class) {
            switch (derivedFeatureID) {
                case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__REG_EXP_TO_HIDE_DEFAULT_FIELD:
                    return ProcessPackage.ABSTRACT_PAGE_FLOW__REG_EXP_TO_HIDE_DEFAULT_FIELD;
                case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__USE_REG_EXP_TO_HIDE_DEFAULT_FIELD:
                    return ProcessPackage.ABSTRACT_PAGE_FLOW__USE_REG_EXP_TO_HIDE_DEFAULT_FIELD;
                default:
                    return -1;
            }
        }
        if (baseClass == ViewPageFlow.class) {
            switch (derivedFeatureID) {
                case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__VIEW_PAGE_FLOW_TRANSITIONS:
                    return ProcessPackage.VIEW_PAGE_FLOW__VIEW_PAGE_FLOW_TRANSITIONS;
                case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__VIEW_TRANSIENT_DATA:
                    return ProcessPackage.VIEW_PAGE_FLOW__VIEW_TRANSIENT_DATA;
                case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__VIEW_PAGE_FLOW_CONNECTORS:
                    return ProcessPackage.VIEW_PAGE_FLOW__VIEW_PAGE_FLOW_CONNECTORS;
                case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__VIEW_PAGE_FLOW_TYPE:
                    return ProcessPackage.VIEW_PAGE_FLOW__VIEW_PAGE_FLOW_TYPE;
                case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__VIEW_FORM:
                    return ProcessPackage.VIEW_PAGE_FLOW__VIEW_FORM;
                case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__VIEW_PAGE_FLOW_REDIRECTION_URL:
                    return ProcessPackage.VIEW_PAGE_FLOW__VIEW_PAGE_FLOW_REDIRECTION_URL;
                default:
                    return -1;
            }
        }
        if (baseClass == SimulationDataContainer.class) {
            switch (derivedFeatureID) {
                case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__SIMULATION_DATA:
                    return SimulationPackage.SIMULATION_DATA_CONTAINER__SIMULATION_DATA;
                default:
                    return -1;
            }
        }
        if (baseClass == SimulationActivity.class) {
            switch (derivedFeatureID) {
                case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__RESOURCES_USAGES:
                    return SimulationPackage.SIMULATION_ACTIVITY__RESOURCES_USAGES;
                case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__EXECUTION_TIME:
                    return SimulationPackage.SIMULATION_ACTIVITY__EXECUTION_TIME;
                case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__ESTIMATED_TIME:
                    return SimulationPackage.SIMULATION_ACTIVITY__ESTIMATED_TIME;
                case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__MAXIMUM_TIME:
                    return SimulationPackage.SIMULATION_ACTIVITY__MAXIMUM_TIME;
                case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__CONTIGOUS:
                    return SimulationPackage.SIMULATION_ACTIVITY__CONTIGOUS;
                case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__EXCLUSIVE_OUTGOING_TRANSITION:
                    return SimulationPackage.SIMULATION_ACTIVITY__EXCLUSIVE_OUTGOING_TRANSITION;
                case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__LOOP_TRANSITION:
                    return SimulationPackage.SIMULATION_ACTIVITY__LOOP_TRANSITION;
                case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__DATA_CHANGE:
                    return SimulationPackage.SIMULATION_ACTIVITY__DATA_CHANGE;
                default:
                    return -1;
            }
        }
        if (baseClass == FlowElement.class) {
            switch (derivedFeatureID) {
                case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__DYNAMIC_LABEL:
                    return ProcessPackage.FLOW_ELEMENT__DYNAMIC_LABEL;
                case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__DYNAMIC_DESCRIPTION:
                    return ProcessPackage.FLOW_ELEMENT__DYNAMIC_DESCRIPTION;
                case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__STEP_SUMMARY:
                    return ProcessPackage.FLOW_ELEMENT__STEP_SUMMARY;
                default:
                    return -1;
            }
        }
        if (baseClass == Event.class) {
            switch (derivedFeatureID) {
                default:
                    return -1;
            }
        }
        if (baseClass == DataAware.class) {
            switch (derivedFeatureID) {
                case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__DATA:
                    return ProcessPackage.DATA_AWARE__DATA;
                default:
                    return -1;
            }
        }
        return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
        if (baseClass == SourceElement.class) {
            switch (baseFeatureID) {
                case ProcessPackage.SOURCE_ELEMENT__OUTGOING:
                    return ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__OUTGOING;
                default:
                    return -1;
            }
        }
        if (baseClass == TargetElement.class) {
            switch (baseFeatureID) {
                case ProcessPackage.TARGET_ELEMENT__INCOMING:
                    return ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__INCOMING;
                default:
                    return -1;
            }
        }
        if (baseClass == AbstractPageFlow.class) {
            switch (baseFeatureID) {
                case ProcessPackage.ABSTRACT_PAGE_FLOW__REG_EXP_TO_HIDE_DEFAULT_FIELD:
                    return ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__REG_EXP_TO_HIDE_DEFAULT_FIELD;
                case ProcessPackage.ABSTRACT_PAGE_FLOW__USE_REG_EXP_TO_HIDE_DEFAULT_FIELD:
                    return ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__USE_REG_EXP_TO_HIDE_DEFAULT_FIELD;
                default:
                    return -1;
            }
        }
        if (baseClass == ViewPageFlow.class) {
            switch (baseFeatureID) {
                case ProcessPackage.VIEW_PAGE_FLOW__VIEW_PAGE_FLOW_TRANSITIONS:
                    return ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__VIEW_PAGE_FLOW_TRANSITIONS;
                case ProcessPackage.VIEW_PAGE_FLOW__VIEW_TRANSIENT_DATA:
                    return ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__VIEW_TRANSIENT_DATA;
                case ProcessPackage.VIEW_PAGE_FLOW__VIEW_PAGE_FLOW_CONNECTORS:
                    return ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__VIEW_PAGE_FLOW_CONNECTORS;
                case ProcessPackage.VIEW_PAGE_FLOW__VIEW_PAGE_FLOW_TYPE:
                    return ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__VIEW_PAGE_FLOW_TYPE;
                case ProcessPackage.VIEW_PAGE_FLOW__VIEW_FORM:
                    return ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__VIEW_FORM;
                case ProcessPackage.VIEW_PAGE_FLOW__VIEW_PAGE_FLOW_REDIRECTION_URL:
                    return ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__VIEW_PAGE_FLOW_REDIRECTION_URL;
                default:
                    return -1;
            }
        }
        if (baseClass == SimulationDataContainer.class) {
            switch (baseFeatureID) {
                case SimulationPackage.SIMULATION_DATA_CONTAINER__SIMULATION_DATA:
                    return ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__SIMULATION_DATA;
                default:
                    return -1;
            }
        }
        if (baseClass == SimulationActivity.class) {
            switch (baseFeatureID) {
                case SimulationPackage.SIMULATION_ACTIVITY__RESOURCES_USAGES:
                    return ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__RESOURCES_USAGES;
                case SimulationPackage.SIMULATION_ACTIVITY__EXECUTION_TIME:
                    return ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__EXECUTION_TIME;
                case SimulationPackage.SIMULATION_ACTIVITY__ESTIMATED_TIME:
                    return ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__ESTIMATED_TIME;
                case SimulationPackage.SIMULATION_ACTIVITY__MAXIMUM_TIME:
                    return ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__MAXIMUM_TIME;
                case SimulationPackage.SIMULATION_ACTIVITY__CONTIGOUS:
                    return ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__CONTIGOUS;
                case SimulationPackage.SIMULATION_ACTIVITY__EXCLUSIVE_OUTGOING_TRANSITION:
                    return ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__EXCLUSIVE_OUTGOING_TRANSITION;
                case SimulationPackage.SIMULATION_ACTIVITY__LOOP_TRANSITION:
                    return ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__LOOP_TRANSITION;
                case SimulationPackage.SIMULATION_ACTIVITY__DATA_CHANGE:
                    return ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__DATA_CHANGE;
                default:
                    return -1;
            }
        }
        if (baseClass == FlowElement.class) {
            switch (baseFeatureID) {
                case ProcessPackage.FLOW_ELEMENT__DYNAMIC_LABEL:
                    return ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__DYNAMIC_LABEL;
                case ProcessPackage.FLOW_ELEMENT__DYNAMIC_DESCRIPTION:
                    return ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__DYNAMIC_DESCRIPTION;
                case ProcessPackage.FLOW_ELEMENT__STEP_SUMMARY:
                    return ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__STEP_SUMMARY;
                default:
                    return -1;
            }
        }
        if (baseClass == Event.class) {
            switch (baseFeatureID) {
                default:
                    return -1;
            }
        }
        if (baseClass == DataAware.class) {
            switch (baseFeatureID) {
                case ProcessPackage.DATA_AWARE__DATA:
                    return ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT__DATA;
                default:
                    return -1;
            }
        }
        return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String toString() {
        if (eIsProxy())
            return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (documentation: "); //$NON-NLS-1$
        result.append(documentation);
        result.append(", name: "); //$NON-NLS-1$
        result.append(name);
        result.append(", signalCode: "); //$NON-NLS-1$
        result.append(signalCode);
        result.append(", regExpToHideDefaultField: "); //$NON-NLS-1$
        result.append(regExpToHideDefaultField);
        result.append(", useRegExpToHideDefaultField: "); //$NON-NLS-1$
        result.append(useRegExpToHideDefaultField);
        result.append(", viewPageFlowType: "); //$NON-NLS-1$
        result.append(viewPageFlowType);
        result.append(", executionTime: "); //$NON-NLS-1$
        result.append(executionTime);
        result.append(", estimatedTime: "); //$NON-NLS-1$
        result.append(estimatedTime);
        result.append(", maximumTime: "); //$NON-NLS-1$
        result.append(maximumTime);
        result.append(", contigous: "); //$NON-NLS-1$
        result.append(contigous);
        result.append(", exclusiveOutgoingTransition: "); //$NON-NLS-1$
        result.append(exclusiveOutgoingTransition);
        result.append(')');
        return result.toString();
    }

} //IntermediateCatchSignalEventImpl
