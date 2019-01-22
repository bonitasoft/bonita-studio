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

import java.util.Collection;

import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.Operation;

import org.bonitasoft.studio.model.kpi.AbstractKPIBinding;

import org.bonitasoft.studio.model.process.Activity;
import org.bonitasoft.studio.model.process.BoundaryEvent;
import org.bonitasoft.studio.model.process.ConnectableElement;
import org.bonitasoft.studio.model.process.Connector;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.DataAware;
import org.bonitasoft.studio.model.process.MultiInstanceType;
import org.bonitasoft.studio.model.process.MultiInstantiable;
import org.bonitasoft.studio.model.process.OperationContainer;
import org.bonitasoft.studio.model.process.ProcessPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Activity</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.ActivityImpl#getData <em>Data</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.ActivityImpl#getConnectors <em>Connectors</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.ActivityImpl#getKpis <em>Kpis</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.ActivityImpl#getOperations <em>Operations</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.ActivityImpl#getType <em>Type</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.ActivityImpl#getTestBefore <em>Test Before</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.ActivityImpl#getLoopCondition <em>Loop Condition</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.ActivityImpl#getLoopMaximum <em>Loop Maximum</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.ActivityImpl#isUseCardinality <em>Use Cardinality</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.ActivityImpl#getCardinalityExpression <em>Cardinality Expression</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.ActivityImpl#getCollectionDataToMultiInstantiate <em>Collection Data To Multi Instantiate</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.ActivityImpl#getIteratorExpression <em>Iterator Expression</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.ActivityImpl#getOutputData <em>Output Data</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.ActivityImpl#getListDataContainingOutputResults <em>List Data Containing Output Results</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.ActivityImpl#getCompletionCondition <em>Completion Condition</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.ActivityImpl#isStoreOutput <em>Store Output</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.ActivityImpl#getBoundaryIntermediateEvents <em>Boundary Intermediate Events</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ActivityImpl extends FlowElementImpl implements Activity {
	/**
	 * The cached value of the '{@link #getData() <em>Data</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getData()
	 * @generated
	 * @ordered
	 */
	protected EList<Data> data;

	/**
	 * The cached value of the '{@link #getConnectors() <em>Connectors</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConnectors()
	 * @generated
	 * @ordered
	 */
	protected EList<Connector> connectors;

	/**
	 * The cached value of the '{@link #getKpis() <em>Kpis</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKpis()
	 * @generated
	 * @ordered
	 */
	protected EList<AbstractKPIBinding> kpis;

	/**
	 * The cached value of the '{@link #getOperations() <em>Operations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOperations()
	 * @generated
	 * @ordered
	 */
	protected EList<Operation> operations;

	/**
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final MultiInstanceType TYPE_EDEFAULT = MultiInstanceType.NONE;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected MultiInstanceType type = TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getTestBefore() <em>Test Before</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTestBefore()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean TEST_BEFORE_EDEFAULT = Boolean.FALSE;

	/**
	 * The cached value of the '{@link #getTestBefore() <em>Test Before</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTestBefore()
	 * @generated
	 * @ordered
	 */
	protected Boolean testBefore = TEST_BEFORE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getLoopCondition() <em>Loop Condition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLoopCondition()
	 * @generated
	 * @ordered
	 */
	protected Expression loopCondition;

	/**
	 * The cached value of the '{@link #getLoopMaximum() <em>Loop Maximum</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLoopMaximum()
	 * @generated
	 * @ordered
	 */
	protected Expression loopMaximum;

	/**
	 * The default value of the '{@link #isUseCardinality() <em>Use Cardinality</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isUseCardinality()
	 * @generated
	 * @ordered
	 */
	protected static final boolean USE_CARDINALITY_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isUseCardinality() <em>Use Cardinality</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isUseCardinality()
	 * @generated
	 * @ordered
	 */
	protected boolean useCardinality = USE_CARDINALITY_EDEFAULT;

	/**
	 * The cached value of the '{@link #getCardinalityExpression() <em>Cardinality Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCardinalityExpression()
	 * @generated
	 * @ordered
	 */
	protected Expression cardinalityExpression;

	/**
	 * The cached value of the '{@link #getCollectionDataToMultiInstantiate() <em>Collection Data To Multi Instantiate</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCollectionDataToMultiInstantiate()
	 * @generated
	 * @ordered
	 */
	protected Data collectionDataToMultiInstantiate;

	/**
	 * The cached value of the '{@link #getIteratorExpression() <em>Iterator Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIteratorExpression()
	 * @generated
	 * @ordered
	 */
	protected Expression iteratorExpression;

	/**
	 * The cached value of the '{@link #getOutputData() <em>Output Data</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutputData()
	 * @generated
	 * @ordered
	 */
	protected Data outputData;

	/**
	 * The cached value of the '{@link #getListDataContainingOutputResults() <em>List Data Containing Output Results</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getListDataContainingOutputResults()
	 * @generated
	 * @ordered
	 */
	protected Data listDataContainingOutputResults;

	/**
	 * The cached value of the '{@link #getCompletionCondition() <em>Completion Condition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCompletionCondition()
	 * @generated
	 * @ordered
	 */
	protected Expression completionCondition;

	/**
	 * The default value of the '{@link #isStoreOutput() <em>Store Output</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isStoreOutput()
	 * @generated
	 * @ordered
	 */
	protected static final boolean STORE_OUTPUT_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isStoreOutput() <em>Store Output</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isStoreOutput()
	 * @generated
	 * @ordered
	 */
	protected boolean storeOutput = STORE_OUTPUT_EDEFAULT;

	/**
	 * The cached value of the '{@link #getBoundaryIntermediateEvents() <em>Boundary Intermediate Events</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBoundaryIntermediateEvents()
	 * @generated
	 * @ordered
	 */
	protected EList<BoundaryEvent> boundaryIntermediateEvents;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ActivityImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ProcessPackage.Literals.ACTIVITY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Data> getData() {
		if (data == null) {
			data = new EObjectContainmentEList<Data>(Data.class, this, ProcessPackage.ACTIVITY__DATA);
		}
		return data;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Connector> getConnectors() {
		if (connectors == null) {
			connectors = new EObjectContainmentEList<Connector>(Connector.class, this, ProcessPackage.ACTIVITY__CONNECTORS);
		}
		return connectors;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<AbstractKPIBinding> getKpis() {
		if (kpis == null) {
			kpis = new EObjectContainmentEList<AbstractKPIBinding>(AbstractKPIBinding.class, this, ProcessPackage.ACTIVITY__KPIS);
		}
		return kpis;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Operation> getOperations() {
		if (operations == null) {
			operations = new EObjectContainmentEList<Operation>(Operation.class, this, ProcessPackage.ACTIVITY__OPERATIONS);
		}
		return operations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public MultiInstanceType getType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setType(MultiInstanceType newType) {
		MultiInstanceType oldType = type;
		type = newType == null ? TYPE_EDEFAULT : newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.ACTIVITY__TYPE, oldType, type));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Boolean getTestBefore() {
		return testBefore;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTestBefore(Boolean newTestBefore) {
		Boolean oldTestBefore = testBefore;
		testBefore = newTestBefore;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.ACTIVITY__TEST_BEFORE, oldTestBefore, testBefore));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Expression getLoopCondition() {
		return loopCondition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLoopCondition(Expression newLoopCondition, NotificationChain msgs) {
		Expression oldLoopCondition = loopCondition;
		loopCondition = newLoopCondition;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ProcessPackage.ACTIVITY__LOOP_CONDITION, oldLoopCondition, newLoopCondition);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setLoopCondition(Expression newLoopCondition) {
		if (newLoopCondition != loopCondition) {
			NotificationChain msgs = null;
			if (loopCondition != null)
				msgs = ((InternalEObject)loopCondition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ProcessPackage.ACTIVITY__LOOP_CONDITION, null, msgs);
			if (newLoopCondition != null)
				msgs = ((InternalEObject)newLoopCondition).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ProcessPackage.ACTIVITY__LOOP_CONDITION, null, msgs);
			msgs = basicSetLoopCondition(newLoopCondition, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.ACTIVITY__LOOP_CONDITION, newLoopCondition, newLoopCondition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Expression getLoopMaximum() {
		return loopMaximum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLoopMaximum(Expression newLoopMaximum, NotificationChain msgs) {
		Expression oldLoopMaximum = loopMaximum;
		loopMaximum = newLoopMaximum;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ProcessPackage.ACTIVITY__LOOP_MAXIMUM, oldLoopMaximum, newLoopMaximum);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setLoopMaximum(Expression newLoopMaximum) {
		if (newLoopMaximum != loopMaximum) {
			NotificationChain msgs = null;
			if (loopMaximum != null)
				msgs = ((InternalEObject)loopMaximum).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ProcessPackage.ACTIVITY__LOOP_MAXIMUM, null, msgs);
			if (newLoopMaximum != null)
				msgs = ((InternalEObject)newLoopMaximum).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ProcessPackage.ACTIVITY__LOOP_MAXIMUM, null, msgs);
			msgs = basicSetLoopMaximum(newLoopMaximum, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.ACTIVITY__LOOP_MAXIMUM, newLoopMaximum, newLoopMaximum));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isUseCardinality() {
		return useCardinality;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setUseCardinality(boolean newUseCardinality) {
		boolean oldUseCardinality = useCardinality;
		useCardinality = newUseCardinality;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.ACTIVITY__USE_CARDINALITY, oldUseCardinality, useCardinality));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Expression getCardinalityExpression() {
		return cardinalityExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCardinalityExpression(Expression newCardinalityExpression, NotificationChain msgs) {
		Expression oldCardinalityExpression = cardinalityExpression;
		cardinalityExpression = newCardinalityExpression;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ProcessPackage.ACTIVITY__CARDINALITY_EXPRESSION, oldCardinalityExpression, newCardinalityExpression);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setCardinalityExpression(Expression newCardinalityExpression) {
		if (newCardinalityExpression != cardinalityExpression) {
			NotificationChain msgs = null;
			if (cardinalityExpression != null)
				msgs = ((InternalEObject)cardinalityExpression).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ProcessPackage.ACTIVITY__CARDINALITY_EXPRESSION, null, msgs);
			if (newCardinalityExpression != null)
				msgs = ((InternalEObject)newCardinalityExpression).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ProcessPackage.ACTIVITY__CARDINALITY_EXPRESSION, null, msgs);
			msgs = basicSetCardinalityExpression(newCardinalityExpression, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.ACTIVITY__CARDINALITY_EXPRESSION, newCardinalityExpression, newCardinalityExpression));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Data getCollectionDataToMultiInstantiate() {
		if (collectionDataToMultiInstantiate != null && collectionDataToMultiInstantiate.eIsProxy()) {
			InternalEObject oldCollectionDataToMultiInstantiate = (InternalEObject)collectionDataToMultiInstantiate;
			collectionDataToMultiInstantiate = (Data)eResolveProxy(oldCollectionDataToMultiInstantiate);
			if (collectionDataToMultiInstantiate != oldCollectionDataToMultiInstantiate) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ProcessPackage.ACTIVITY__COLLECTION_DATA_TO_MULTI_INSTANTIATE, oldCollectionDataToMultiInstantiate, collectionDataToMultiInstantiate));
			}
		}
		return collectionDataToMultiInstantiate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Data basicGetCollectionDataToMultiInstantiate() {
		return collectionDataToMultiInstantiate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setCollectionDataToMultiInstantiate(Data newCollectionDataToMultiInstantiate) {
		Data oldCollectionDataToMultiInstantiate = collectionDataToMultiInstantiate;
		collectionDataToMultiInstantiate = newCollectionDataToMultiInstantiate;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.ACTIVITY__COLLECTION_DATA_TO_MULTI_INSTANTIATE, oldCollectionDataToMultiInstantiate, collectionDataToMultiInstantiate));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Expression getIteratorExpression() {
		return iteratorExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetIteratorExpression(Expression newIteratorExpression, NotificationChain msgs) {
		Expression oldIteratorExpression = iteratorExpression;
		iteratorExpression = newIteratorExpression;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ProcessPackage.ACTIVITY__ITERATOR_EXPRESSION, oldIteratorExpression, newIteratorExpression);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setIteratorExpression(Expression newIteratorExpression) {
		if (newIteratorExpression != iteratorExpression) {
			NotificationChain msgs = null;
			if (iteratorExpression != null)
				msgs = ((InternalEObject)iteratorExpression).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ProcessPackage.ACTIVITY__ITERATOR_EXPRESSION, null, msgs);
			if (newIteratorExpression != null)
				msgs = ((InternalEObject)newIteratorExpression).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ProcessPackage.ACTIVITY__ITERATOR_EXPRESSION, null, msgs);
			msgs = basicSetIteratorExpression(newIteratorExpression, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.ACTIVITY__ITERATOR_EXPRESSION, newIteratorExpression, newIteratorExpression));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Data getOutputData() {
		if (outputData != null && outputData.eIsProxy()) {
			InternalEObject oldOutputData = (InternalEObject)outputData;
			outputData = (Data)eResolveProxy(oldOutputData);
			if (outputData != oldOutputData) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ProcessPackage.ACTIVITY__OUTPUT_DATA, oldOutputData, outputData));
			}
		}
		return outputData;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Data basicGetOutputData() {
		return outputData;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOutputData(Data newOutputData) {
		Data oldOutputData = outputData;
		outputData = newOutputData;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.ACTIVITY__OUTPUT_DATA, oldOutputData, outputData));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Data getListDataContainingOutputResults() {
		if (listDataContainingOutputResults != null && listDataContainingOutputResults.eIsProxy()) {
			InternalEObject oldListDataContainingOutputResults = (InternalEObject)listDataContainingOutputResults;
			listDataContainingOutputResults = (Data)eResolveProxy(oldListDataContainingOutputResults);
			if (listDataContainingOutputResults != oldListDataContainingOutputResults) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ProcessPackage.ACTIVITY__LIST_DATA_CONTAINING_OUTPUT_RESULTS, oldListDataContainingOutputResults, listDataContainingOutputResults));
			}
		}
		return listDataContainingOutputResults;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Data basicGetListDataContainingOutputResults() {
		return listDataContainingOutputResults;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setListDataContainingOutputResults(Data newListDataContainingOutputResults) {
		Data oldListDataContainingOutputResults = listDataContainingOutputResults;
		listDataContainingOutputResults = newListDataContainingOutputResults;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.ACTIVITY__LIST_DATA_CONTAINING_OUTPUT_RESULTS, oldListDataContainingOutputResults, listDataContainingOutputResults));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Expression getCompletionCondition() {
		return completionCondition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCompletionCondition(Expression newCompletionCondition, NotificationChain msgs) {
		Expression oldCompletionCondition = completionCondition;
		completionCondition = newCompletionCondition;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ProcessPackage.ACTIVITY__COMPLETION_CONDITION, oldCompletionCondition, newCompletionCondition);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setCompletionCondition(Expression newCompletionCondition) {
		if (newCompletionCondition != completionCondition) {
			NotificationChain msgs = null;
			if (completionCondition != null)
				msgs = ((InternalEObject)completionCondition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ProcessPackage.ACTIVITY__COMPLETION_CONDITION, null, msgs);
			if (newCompletionCondition != null)
				msgs = ((InternalEObject)newCompletionCondition).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ProcessPackage.ACTIVITY__COMPLETION_CONDITION, null, msgs);
			msgs = basicSetCompletionCondition(newCompletionCondition, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.ACTIVITY__COMPLETION_CONDITION, newCompletionCondition, newCompletionCondition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isStoreOutput() {
		return storeOutput;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setStoreOutput(boolean newStoreOutput) {
		boolean oldStoreOutput = storeOutput;
		storeOutput = newStoreOutput;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.ACTIVITY__STORE_OUTPUT, oldStoreOutput, storeOutput));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<BoundaryEvent> getBoundaryIntermediateEvents() {
		if (boundaryIntermediateEvents == null) {
			boundaryIntermediateEvents = new EObjectContainmentEList<BoundaryEvent>(BoundaryEvent.class, this, ProcessPackage.ACTIVITY__BOUNDARY_INTERMEDIATE_EVENTS);
		}
		return boundaryIntermediateEvents;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ProcessPackage.ACTIVITY__DATA:
				return ((InternalEList<?>)getData()).basicRemove(otherEnd, msgs);
			case ProcessPackage.ACTIVITY__CONNECTORS:
				return ((InternalEList<?>)getConnectors()).basicRemove(otherEnd, msgs);
			case ProcessPackage.ACTIVITY__KPIS:
				return ((InternalEList<?>)getKpis()).basicRemove(otherEnd, msgs);
			case ProcessPackage.ACTIVITY__OPERATIONS:
				return ((InternalEList<?>)getOperations()).basicRemove(otherEnd, msgs);
			case ProcessPackage.ACTIVITY__LOOP_CONDITION:
				return basicSetLoopCondition(null, msgs);
			case ProcessPackage.ACTIVITY__LOOP_MAXIMUM:
				return basicSetLoopMaximum(null, msgs);
			case ProcessPackage.ACTIVITY__CARDINALITY_EXPRESSION:
				return basicSetCardinalityExpression(null, msgs);
			case ProcessPackage.ACTIVITY__ITERATOR_EXPRESSION:
				return basicSetIteratorExpression(null, msgs);
			case ProcessPackage.ACTIVITY__COMPLETION_CONDITION:
				return basicSetCompletionCondition(null, msgs);
			case ProcessPackage.ACTIVITY__BOUNDARY_INTERMEDIATE_EVENTS:
				return ((InternalEList<?>)getBoundaryIntermediateEvents()).basicRemove(otherEnd, msgs);
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
			case ProcessPackage.ACTIVITY__DATA:
				return getData();
			case ProcessPackage.ACTIVITY__CONNECTORS:
				return getConnectors();
			case ProcessPackage.ACTIVITY__KPIS:
				return getKpis();
			case ProcessPackage.ACTIVITY__OPERATIONS:
				return getOperations();
			case ProcessPackage.ACTIVITY__TYPE:
				return getType();
			case ProcessPackage.ACTIVITY__TEST_BEFORE:
				return getTestBefore();
			case ProcessPackage.ACTIVITY__LOOP_CONDITION:
				return getLoopCondition();
			case ProcessPackage.ACTIVITY__LOOP_MAXIMUM:
				return getLoopMaximum();
			case ProcessPackage.ACTIVITY__USE_CARDINALITY:
				return isUseCardinality();
			case ProcessPackage.ACTIVITY__CARDINALITY_EXPRESSION:
				return getCardinalityExpression();
			case ProcessPackage.ACTIVITY__COLLECTION_DATA_TO_MULTI_INSTANTIATE:
				if (resolve) return getCollectionDataToMultiInstantiate();
				return basicGetCollectionDataToMultiInstantiate();
			case ProcessPackage.ACTIVITY__ITERATOR_EXPRESSION:
				return getIteratorExpression();
			case ProcessPackage.ACTIVITY__OUTPUT_DATA:
				if (resolve) return getOutputData();
				return basicGetOutputData();
			case ProcessPackage.ACTIVITY__LIST_DATA_CONTAINING_OUTPUT_RESULTS:
				if (resolve) return getListDataContainingOutputResults();
				return basicGetListDataContainingOutputResults();
			case ProcessPackage.ACTIVITY__COMPLETION_CONDITION:
				return getCompletionCondition();
			case ProcessPackage.ACTIVITY__STORE_OUTPUT:
				return isStoreOutput();
			case ProcessPackage.ACTIVITY__BOUNDARY_INTERMEDIATE_EVENTS:
				return getBoundaryIntermediateEvents();
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
			case ProcessPackage.ACTIVITY__DATA:
				getData().clear();
				getData().addAll((Collection<? extends Data>)newValue);
				return;
			case ProcessPackage.ACTIVITY__CONNECTORS:
				getConnectors().clear();
				getConnectors().addAll((Collection<? extends Connector>)newValue);
				return;
			case ProcessPackage.ACTIVITY__KPIS:
				getKpis().clear();
				getKpis().addAll((Collection<? extends AbstractKPIBinding>)newValue);
				return;
			case ProcessPackage.ACTIVITY__OPERATIONS:
				getOperations().clear();
				getOperations().addAll((Collection<? extends Operation>)newValue);
				return;
			case ProcessPackage.ACTIVITY__TYPE:
				setType((MultiInstanceType)newValue);
				return;
			case ProcessPackage.ACTIVITY__TEST_BEFORE:
				setTestBefore((Boolean)newValue);
				return;
			case ProcessPackage.ACTIVITY__LOOP_CONDITION:
				setLoopCondition((Expression)newValue);
				return;
			case ProcessPackage.ACTIVITY__LOOP_MAXIMUM:
				setLoopMaximum((Expression)newValue);
				return;
			case ProcessPackage.ACTIVITY__USE_CARDINALITY:
				setUseCardinality((Boolean)newValue);
				return;
			case ProcessPackage.ACTIVITY__CARDINALITY_EXPRESSION:
				setCardinalityExpression((Expression)newValue);
				return;
			case ProcessPackage.ACTIVITY__COLLECTION_DATA_TO_MULTI_INSTANTIATE:
				setCollectionDataToMultiInstantiate((Data)newValue);
				return;
			case ProcessPackage.ACTIVITY__ITERATOR_EXPRESSION:
				setIteratorExpression((Expression)newValue);
				return;
			case ProcessPackage.ACTIVITY__OUTPUT_DATA:
				setOutputData((Data)newValue);
				return;
			case ProcessPackage.ACTIVITY__LIST_DATA_CONTAINING_OUTPUT_RESULTS:
				setListDataContainingOutputResults((Data)newValue);
				return;
			case ProcessPackage.ACTIVITY__COMPLETION_CONDITION:
				setCompletionCondition((Expression)newValue);
				return;
			case ProcessPackage.ACTIVITY__STORE_OUTPUT:
				setStoreOutput((Boolean)newValue);
				return;
			case ProcessPackage.ACTIVITY__BOUNDARY_INTERMEDIATE_EVENTS:
				getBoundaryIntermediateEvents().clear();
				getBoundaryIntermediateEvents().addAll((Collection<? extends BoundaryEvent>)newValue);
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
			case ProcessPackage.ACTIVITY__DATA:
				getData().clear();
				return;
			case ProcessPackage.ACTIVITY__CONNECTORS:
				getConnectors().clear();
				return;
			case ProcessPackage.ACTIVITY__KPIS:
				getKpis().clear();
				return;
			case ProcessPackage.ACTIVITY__OPERATIONS:
				getOperations().clear();
				return;
			case ProcessPackage.ACTIVITY__TYPE:
				setType(TYPE_EDEFAULT);
				return;
			case ProcessPackage.ACTIVITY__TEST_BEFORE:
				setTestBefore(TEST_BEFORE_EDEFAULT);
				return;
			case ProcessPackage.ACTIVITY__LOOP_CONDITION:
				setLoopCondition((Expression)null);
				return;
			case ProcessPackage.ACTIVITY__LOOP_MAXIMUM:
				setLoopMaximum((Expression)null);
				return;
			case ProcessPackage.ACTIVITY__USE_CARDINALITY:
				setUseCardinality(USE_CARDINALITY_EDEFAULT);
				return;
			case ProcessPackage.ACTIVITY__CARDINALITY_EXPRESSION:
				setCardinalityExpression((Expression)null);
				return;
			case ProcessPackage.ACTIVITY__COLLECTION_DATA_TO_MULTI_INSTANTIATE:
				setCollectionDataToMultiInstantiate((Data)null);
				return;
			case ProcessPackage.ACTIVITY__ITERATOR_EXPRESSION:
				setIteratorExpression((Expression)null);
				return;
			case ProcessPackage.ACTIVITY__OUTPUT_DATA:
				setOutputData((Data)null);
				return;
			case ProcessPackage.ACTIVITY__LIST_DATA_CONTAINING_OUTPUT_RESULTS:
				setListDataContainingOutputResults((Data)null);
				return;
			case ProcessPackage.ACTIVITY__COMPLETION_CONDITION:
				setCompletionCondition((Expression)null);
				return;
			case ProcessPackage.ACTIVITY__STORE_OUTPUT:
				setStoreOutput(STORE_OUTPUT_EDEFAULT);
				return;
			case ProcessPackage.ACTIVITY__BOUNDARY_INTERMEDIATE_EVENTS:
				getBoundaryIntermediateEvents().clear();
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
			case ProcessPackage.ACTIVITY__DATA:
				return data != null && !data.isEmpty();
			case ProcessPackage.ACTIVITY__CONNECTORS:
				return connectors != null && !connectors.isEmpty();
			case ProcessPackage.ACTIVITY__KPIS:
				return kpis != null && !kpis.isEmpty();
			case ProcessPackage.ACTIVITY__OPERATIONS:
				return operations != null && !operations.isEmpty();
			case ProcessPackage.ACTIVITY__TYPE:
				return type != TYPE_EDEFAULT;
			case ProcessPackage.ACTIVITY__TEST_BEFORE:
				return TEST_BEFORE_EDEFAULT == null ? testBefore != null : !TEST_BEFORE_EDEFAULT.equals(testBefore);
			case ProcessPackage.ACTIVITY__LOOP_CONDITION:
				return loopCondition != null;
			case ProcessPackage.ACTIVITY__LOOP_MAXIMUM:
				return loopMaximum != null;
			case ProcessPackage.ACTIVITY__USE_CARDINALITY:
				return useCardinality != USE_CARDINALITY_EDEFAULT;
			case ProcessPackage.ACTIVITY__CARDINALITY_EXPRESSION:
				return cardinalityExpression != null;
			case ProcessPackage.ACTIVITY__COLLECTION_DATA_TO_MULTI_INSTANTIATE:
				return collectionDataToMultiInstantiate != null;
			case ProcessPackage.ACTIVITY__ITERATOR_EXPRESSION:
				return iteratorExpression != null;
			case ProcessPackage.ACTIVITY__OUTPUT_DATA:
				return outputData != null;
			case ProcessPackage.ACTIVITY__LIST_DATA_CONTAINING_OUTPUT_RESULTS:
				return listDataContainingOutputResults != null;
			case ProcessPackage.ACTIVITY__COMPLETION_CONDITION:
				return completionCondition != null;
			case ProcessPackage.ACTIVITY__STORE_OUTPUT:
				return storeOutput != STORE_OUTPUT_EDEFAULT;
			case ProcessPackage.ACTIVITY__BOUNDARY_INTERMEDIATE_EVENTS:
				return boundaryIntermediateEvents != null && !boundaryIntermediateEvents.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == DataAware.class) {
			switch (derivedFeatureID) {
				case ProcessPackage.ACTIVITY__DATA: return ProcessPackage.DATA_AWARE__DATA;
				default: return -1;
			}
		}
		if (baseClass == ConnectableElement.class) {
			switch (derivedFeatureID) {
				case ProcessPackage.ACTIVITY__CONNECTORS: return ProcessPackage.CONNECTABLE_ELEMENT__CONNECTORS;
				case ProcessPackage.ACTIVITY__KPIS: return ProcessPackage.CONNECTABLE_ELEMENT__KPIS;
				default: return -1;
			}
		}
		if (baseClass == OperationContainer.class) {
			switch (derivedFeatureID) {
				case ProcessPackage.ACTIVITY__OPERATIONS: return ProcessPackage.OPERATION_CONTAINER__OPERATIONS;
				default: return -1;
			}
		}
		if (baseClass == MultiInstantiable.class) {
			switch (derivedFeatureID) {
				case ProcessPackage.ACTIVITY__TYPE: return ProcessPackage.MULTI_INSTANTIABLE__TYPE;
				case ProcessPackage.ACTIVITY__TEST_BEFORE: return ProcessPackage.MULTI_INSTANTIABLE__TEST_BEFORE;
				case ProcessPackage.ACTIVITY__LOOP_CONDITION: return ProcessPackage.MULTI_INSTANTIABLE__LOOP_CONDITION;
				case ProcessPackage.ACTIVITY__LOOP_MAXIMUM: return ProcessPackage.MULTI_INSTANTIABLE__LOOP_MAXIMUM;
				case ProcessPackage.ACTIVITY__USE_CARDINALITY: return ProcessPackage.MULTI_INSTANTIABLE__USE_CARDINALITY;
				case ProcessPackage.ACTIVITY__CARDINALITY_EXPRESSION: return ProcessPackage.MULTI_INSTANTIABLE__CARDINALITY_EXPRESSION;
				case ProcessPackage.ACTIVITY__COLLECTION_DATA_TO_MULTI_INSTANTIATE: return ProcessPackage.MULTI_INSTANTIABLE__COLLECTION_DATA_TO_MULTI_INSTANTIATE;
				case ProcessPackage.ACTIVITY__ITERATOR_EXPRESSION: return ProcessPackage.MULTI_INSTANTIABLE__ITERATOR_EXPRESSION;
				case ProcessPackage.ACTIVITY__OUTPUT_DATA: return ProcessPackage.MULTI_INSTANTIABLE__OUTPUT_DATA;
				case ProcessPackage.ACTIVITY__LIST_DATA_CONTAINING_OUTPUT_RESULTS: return ProcessPackage.MULTI_INSTANTIABLE__LIST_DATA_CONTAINING_OUTPUT_RESULTS;
				case ProcessPackage.ACTIVITY__COMPLETION_CONDITION: return ProcessPackage.MULTI_INSTANTIABLE__COMPLETION_CONDITION;
				case ProcessPackage.ACTIVITY__STORE_OUTPUT: return ProcessPackage.MULTI_INSTANTIABLE__STORE_OUTPUT;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == DataAware.class) {
			switch (baseFeatureID) {
				case ProcessPackage.DATA_AWARE__DATA: return ProcessPackage.ACTIVITY__DATA;
				default: return -1;
			}
		}
		if (baseClass == ConnectableElement.class) {
			switch (baseFeatureID) {
				case ProcessPackage.CONNECTABLE_ELEMENT__CONNECTORS: return ProcessPackage.ACTIVITY__CONNECTORS;
				case ProcessPackage.CONNECTABLE_ELEMENT__KPIS: return ProcessPackage.ACTIVITY__KPIS;
				default: return -1;
			}
		}
		if (baseClass == OperationContainer.class) {
			switch (baseFeatureID) {
				case ProcessPackage.OPERATION_CONTAINER__OPERATIONS: return ProcessPackage.ACTIVITY__OPERATIONS;
				default: return -1;
			}
		}
		if (baseClass == MultiInstantiable.class) {
			switch (baseFeatureID) {
				case ProcessPackage.MULTI_INSTANTIABLE__TYPE: return ProcessPackage.ACTIVITY__TYPE;
				case ProcessPackage.MULTI_INSTANTIABLE__TEST_BEFORE: return ProcessPackage.ACTIVITY__TEST_BEFORE;
				case ProcessPackage.MULTI_INSTANTIABLE__LOOP_CONDITION: return ProcessPackage.ACTIVITY__LOOP_CONDITION;
				case ProcessPackage.MULTI_INSTANTIABLE__LOOP_MAXIMUM: return ProcessPackage.ACTIVITY__LOOP_MAXIMUM;
				case ProcessPackage.MULTI_INSTANTIABLE__USE_CARDINALITY: return ProcessPackage.ACTIVITY__USE_CARDINALITY;
				case ProcessPackage.MULTI_INSTANTIABLE__CARDINALITY_EXPRESSION: return ProcessPackage.ACTIVITY__CARDINALITY_EXPRESSION;
				case ProcessPackage.MULTI_INSTANTIABLE__COLLECTION_DATA_TO_MULTI_INSTANTIATE: return ProcessPackage.ACTIVITY__COLLECTION_DATA_TO_MULTI_INSTANTIATE;
				case ProcessPackage.MULTI_INSTANTIABLE__ITERATOR_EXPRESSION: return ProcessPackage.ACTIVITY__ITERATOR_EXPRESSION;
				case ProcessPackage.MULTI_INSTANTIABLE__OUTPUT_DATA: return ProcessPackage.ACTIVITY__OUTPUT_DATA;
				case ProcessPackage.MULTI_INSTANTIABLE__LIST_DATA_CONTAINING_OUTPUT_RESULTS: return ProcessPackage.ACTIVITY__LIST_DATA_CONTAINING_OUTPUT_RESULTS;
				case ProcessPackage.MULTI_INSTANTIABLE__COMPLETION_CONDITION: return ProcessPackage.ACTIVITY__COMPLETION_CONDITION;
				case ProcessPackage.MULTI_INSTANTIABLE__STORE_OUTPUT: return ProcessPackage.ACTIVITY__STORE_OUTPUT;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (type: "); //$NON-NLS-1$
		result.append(type);
		result.append(", testBefore: "); //$NON-NLS-1$
		result.append(testBefore);
		result.append(", useCardinality: "); //$NON-NLS-1$
		result.append(useCardinality);
		result.append(", storeOutput: "); //$NON-NLS-1$
		result.append(storeOutput);
		result.append(')');
		return result.toString();
	}

} //ActivityImpl
