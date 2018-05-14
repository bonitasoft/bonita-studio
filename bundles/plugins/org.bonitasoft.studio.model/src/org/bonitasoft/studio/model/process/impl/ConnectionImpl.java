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

import org.bonitasoft.studio.model.process.Connection;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.SourceElement;
import org.bonitasoft.studio.model.process.TargetElement;
import org.bonitasoft.studio.model.process.TextAnnotationAttachment;

import org.bonitasoft.studio.model.simulation.SimulationPackage;
import org.bonitasoft.studio.model.simulation.SimulationTransition;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Connection</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.bonitasoft.studio.model.process.impl.ConnectionImpl#getDocumentation <em>Documentation</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.ConnectionImpl#getName <em>Name</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.ConnectionImpl#getTextAnnotationAttachment <em>Text Annotation
 * Attachment</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.ConnectionImpl#getProbability <em>Probability</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.ConnectionImpl#isDataBased <em>Data Based</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.ConnectionImpl#isUseExpression <em>Use Expression</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.ConnectionImpl#getExpression <em>Expression</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.ConnectionImpl#getTarget <em>Target</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.ConnectionImpl#getSource <em>Source</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ConnectionImpl extends EObjectImpl implements Connection {

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
     * The default value of the '{@link #getProbability() <em>Probability</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getProbability()
     * @generated
     * @ordered
     */
    protected static final double PROBABILITY_EDEFAULT = 1.0;

    /**
     * The cached value of the '{@link #getProbability() <em>Probability</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getProbability()
     * @generated
     * @ordered
     */
    protected double probability = PROBABILITY_EDEFAULT;

    /**
     * The default value of the '{@link #isDataBased() <em>Data Based</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #isDataBased()
     * @generated
     * @ordered
     */
    protected static final boolean DATA_BASED_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isDataBased() <em>Data Based</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #isDataBased()
     * @generated
     * @ordered
     */
    protected boolean dataBased = DATA_BASED_EDEFAULT;

    /**
     * The default value of the '{@link #isUseExpression() <em>Use Expression</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #isUseExpression()
     * @generated
     * @ordered
     */
    protected static final boolean USE_EXPRESSION_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isUseExpression() <em>Use Expression</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #isUseExpression()
     * @generated
     * @ordered
     */
    protected boolean useExpression = USE_EXPRESSION_EDEFAULT;

    /**
     * The cached value of the '{@link #getExpression() <em>Expression</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getExpression()
     * @generated
     * @ordered
     */
    protected Expression expression;

    /**
     * The cached value of the '{@link #getTarget() <em>Target</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getTarget()
     * @generated
     * @ordered
     */
    protected TargetElement target;

    /**
     * The cached value of the '{@link #getSource() <em>Source</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getSource()
     * @generated
     * @ordered
     */
    protected SourceElement source;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    protected ConnectionImpl() {
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
        return ProcessPackage.Literals.CONNECTION;
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
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.CONNECTION__DOCUMENTATION, oldDocumentation,
                    documentation));
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
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.CONNECTION__NAME, oldName, name));
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
                    TextAnnotationAttachment.class, this, ProcessPackage.CONNECTION__TEXT_ANNOTATION_ATTACHMENT,
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
    public double getProbability() {
        return probability;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setProbability(double newProbability) {
        double oldProbability = probability;
        probability = newProbability;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.CONNECTION__PROBABILITY, oldProbability,
                    probability));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public boolean isDataBased() {
        return dataBased;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setDataBased(boolean newDataBased) {
        boolean oldDataBased = dataBased;
        dataBased = newDataBased;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.CONNECTION__DATA_BASED, oldDataBased,
                    dataBased));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public boolean isUseExpression() {
        return useExpression;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setUseExpression(boolean newUseExpression) {
        boolean oldUseExpression = useExpression;
        useExpression = newUseExpression;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.CONNECTION__USE_EXPRESSION,
                    oldUseExpression, useExpression));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public Expression getExpression() {
        return expression;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetExpression(Expression newExpression, NotificationChain msgs) {
        Expression oldExpression = expression;
        expression = newExpression;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
                    ProcessPackage.CONNECTION__EXPRESSION, oldExpression, newExpression);
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
    public void setExpression(Expression newExpression) {
        if (newExpression != expression) {
            NotificationChain msgs = null;
            if (expression != null)
                msgs = ((InternalEObject) expression).eInverseRemove(this,
                        EOPPOSITE_FEATURE_BASE - ProcessPackage.CONNECTION__EXPRESSION, null, msgs);
            if (newExpression != null)
                msgs = ((InternalEObject) newExpression).eInverseAdd(this,
                        EOPPOSITE_FEATURE_BASE - ProcessPackage.CONNECTION__EXPRESSION, null, msgs);
            msgs = basicSetExpression(newExpression, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.CONNECTION__EXPRESSION, newExpression,
                    newExpression));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public TargetElement getTarget() {
        if (target != null && target.eIsProxy()) {
            InternalEObject oldTarget = (InternalEObject) target;
            target = (TargetElement) eResolveProxy(oldTarget);
            if (target != oldTarget) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ProcessPackage.CONNECTION__TARGET, oldTarget,
                            target));
            }
        }
        return target;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public TargetElement basicGetTarget() {
        return target;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetTarget(TargetElement newTarget, NotificationChain msgs) {
        TargetElement oldTarget = target;
        target = newTarget;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ProcessPackage.CONNECTION__TARGET,
                    oldTarget, newTarget);
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
    public void setTarget(TargetElement newTarget) {
        if (newTarget != target) {
            NotificationChain msgs = null;
            if (target != null)
                msgs = ((InternalEObject) target).eInverseRemove(this, ProcessPackage.TARGET_ELEMENT__INCOMING,
                        TargetElement.class, msgs);
            if (newTarget != null)
                msgs = ((InternalEObject) newTarget).eInverseAdd(this, ProcessPackage.TARGET_ELEMENT__INCOMING,
                        TargetElement.class, msgs);
            msgs = basicSetTarget(newTarget, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.CONNECTION__TARGET, newTarget, newTarget));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public SourceElement getSource() {
        if (source != null && source.eIsProxy()) {
            InternalEObject oldSource = (InternalEObject) source;
            source = (SourceElement) eResolveProxy(oldSource);
            if (source != oldSource) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ProcessPackage.CONNECTION__SOURCE, oldSource,
                            source));
            }
        }
        return source;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public SourceElement basicGetSource() {
        return source;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetSource(SourceElement newSource, NotificationChain msgs) {
        SourceElement oldSource = source;
        source = newSource;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ProcessPackage.CONNECTION__SOURCE,
                    oldSource, newSource);
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
    public void setSource(SourceElement newSource) {
        if (newSource != source) {
            NotificationChain msgs = null;
            if (source != null)
                msgs = ((InternalEObject) source).eInverseRemove(this, ProcessPackage.SOURCE_ELEMENT__OUTGOING,
                        SourceElement.class, msgs);
            if (newSource != null)
                msgs = ((InternalEObject) newSource).eInverseAdd(this, ProcessPackage.SOURCE_ELEMENT__OUTGOING,
                        SourceElement.class, msgs);
            msgs = basicSetSource(newSource, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.CONNECTION__SOURCE, newSource, newSource));
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
            case ProcessPackage.CONNECTION__TEXT_ANNOTATION_ATTACHMENT:
                return ((InternalEList<InternalEObject>) (InternalEList<?>) getTextAnnotationAttachment()).basicAdd(otherEnd,
                        msgs);
            case ProcessPackage.CONNECTION__TARGET:
                if (target != null)
                    msgs = ((InternalEObject) target).eInverseRemove(this, ProcessPackage.TARGET_ELEMENT__INCOMING,
                            TargetElement.class, msgs);
                return basicSetTarget((TargetElement) otherEnd, msgs);
            case ProcessPackage.CONNECTION__SOURCE:
                if (source != null)
                    msgs = ((InternalEObject) source).eInverseRemove(this, ProcessPackage.SOURCE_ELEMENT__OUTGOING,
                            SourceElement.class, msgs);
                return basicSetSource((SourceElement) otherEnd, msgs);
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
            case ProcessPackage.CONNECTION__TEXT_ANNOTATION_ATTACHMENT:
                return ((InternalEList<?>) getTextAnnotationAttachment()).basicRemove(otherEnd, msgs);
            case ProcessPackage.CONNECTION__EXPRESSION:
                return basicSetExpression(null, msgs);
            case ProcessPackage.CONNECTION__TARGET:
                return basicSetTarget(null, msgs);
            case ProcessPackage.CONNECTION__SOURCE:
                return basicSetSource(null, msgs);
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
            case ProcessPackage.CONNECTION__DOCUMENTATION:
                return getDocumentation();
            case ProcessPackage.CONNECTION__NAME:
                return getName();
            case ProcessPackage.CONNECTION__TEXT_ANNOTATION_ATTACHMENT:
                return getTextAnnotationAttachment();
            case ProcessPackage.CONNECTION__PROBABILITY:
                return getProbability();
            case ProcessPackage.CONNECTION__DATA_BASED:
                return isDataBased();
            case ProcessPackage.CONNECTION__USE_EXPRESSION:
                return isUseExpression();
            case ProcessPackage.CONNECTION__EXPRESSION:
                return getExpression();
            case ProcessPackage.CONNECTION__TARGET:
                if (resolve)
                    return getTarget();
                return basicGetTarget();
            case ProcessPackage.CONNECTION__SOURCE:
                if (resolve)
                    return getSource();
                return basicGetSource();
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
            case ProcessPackage.CONNECTION__DOCUMENTATION:
                setDocumentation((String) newValue);
                return;
            case ProcessPackage.CONNECTION__NAME:
                setName((String) newValue);
                return;
            case ProcessPackage.CONNECTION__TEXT_ANNOTATION_ATTACHMENT:
                getTextAnnotationAttachment().clear();
                getTextAnnotationAttachment().addAll((Collection<? extends TextAnnotationAttachment>) newValue);
                return;
            case ProcessPackage.CONNECTION__PROBABILITY:
                setProbability((Double) newValue);
                return;
            case ProcessPackage.CONNECTION__DATA_BASED:
                setDataBased((Boolean) newValue);
                return;
            case ProcessPackage.CONNECTION__USE_EXPRESSION:
                setUseExpression((Boolean) newValue);
                return;
            case ProcessPackage.CONNECTION__EXPRESSION:
                setExpression((Expression) newValue);
                return;
            case ProcessPackage.CONNECTION__TARGET:
                setTarget((TargetElement) newValue);
                return;
            case ProcessPackage.CONNECTION__SOURCE:
                setSource((SourceElement) newValue);
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
            case ProcessPackage.CONNECTION__DOCUMENTATION:
                setDocumentation(DOCUMENTATION_EDEFAULT);
                return;
            case ProcessPackage.CONNECTION__NAME:
                setName(NAME_EDEFAULT);
                return;
            case ProcessPackage.CONNECTION__TEXT_ANNOTATION_ATTACHMENT:
                getTextAnnotationAttachment().clear();
                return;
            case ProcessPackage.CONNECTION__PROBABILITY:
                setProbability(PROBABILITY_EDEFAULT);
                return;
            case ProcessPackage.CONNECTION__DATA_BASED:
                setDataBased(DATA_BASED_EDEFAULT);
                return;
            case ProcessPackage.CONNECTION__USE_EXPRESSION:
                setUseExpression(USE_EXPRESSION_EDEFAULT);
                return;
            case ProcessPackage.CONNECTION__EXPRESSION:
                setExpression((Expression) null);
                return;
            case ProcessPackage.CONNECTION__TARGET:
                setTarget((TargetElement) null);
                return;
            case ProcessPackage.CONNECTION__SOURCE:
                setSource((SourceElement) null);
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
            case ProcessPackage.CONNECTION__DOCUMENTATION:
                return DOCUMENTATION_EDEFAULT == null ? documentation != null
                        : !DOCUMENTATION_EDEFAULT.equals(documentation);
            case ProcessPackage.CONNECTION__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case ProcessPackage.CONNECTION__TEXT_ANNOTATION_ATTACHMENT:
                return textAnnotationAttachment != null && !textAnnotationAttachment.isEmpty();
            case ProcessPackage.CONNECTION__PROBABILITY:
                return probability != PROBABILITY_EDEFAULT;
            case ProcessPackage.CONNECTION__DATA_BASED:
                return dataBased != DATA_BASED_EDEFAULT;
            case ProcessPackage.CONNECTION__USE_EXPRESSION:
                return useExpression != USE_EXPRESSION_EDEFAULT;
            case ProcessPackage.CONNECTION__EXPRESSION:
                return expression != null;
            case ProcessPackage.CONNECTION__TARGET:
                return target != null;
            case ProcessPackage.CONNECTION__SOURCE:
                return source != null;
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
        if (baseClass == SimulationTransition.class) {
            switch (derivedFeatureID) {
                case ProcessPackage.CONNECTION__PROBABILITY:
                    return SimulationPackage.SIMULATION_TRANSITION__PROBABILITY;
                case ProcessPackage.CONNECTION__DATA_BASED:
                    return SimulationPackage.SIMULATION_TRANSITION__DATA_BASED;
                case ProcessPackage.CONNECTION__USE_EXPRESSION:
                    return SimulationPackage.SIMULATION_TRANSITION__USE_EXPRESSION;
                case ProcessPackage.CONNECTION__EXPRESSION:
                    return SimulationPackage.SIMULATION_TRANSITION__EXPRESSION;
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
        if (baseClass == SimulationTransition.class) {
            switch (baseFeatureID) {
                case SimulationPackage.SIMULATION_TRANSITION__PROBABILITY:
                    return ProcessPackage.CONNECTION__PROBABILITY;
                case SimulationPackage.SIMULATION_TRANSITION__DATA_BASED:
                    return ProcessPackage.CONNECTION__DATA_BASED;
                case SimulationPackage.SIMULATION_TRANSITION__USE_EXPRESSION:
                    return ProcessPackage.CONNECTION__USE_EXPRESSION;
                case SimulationPackage.SIMULATION_TRANSITION__EXPRESSION:
                    return ProcessPackage.CONNECTION__EXPRESSION;
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
        result.append(", probability: "); //$NON-NLS-1$
        result.append(probability);
        result.append(", dataBased: "); //$NON-NLS-1$
        result.append(dataBased);
        result.append(", useExpression: "); //$NON-NLS-1$
        result.append(useExpression);
        result.append(')');
        return result.toString();
    }

} //ConnectionImpl
