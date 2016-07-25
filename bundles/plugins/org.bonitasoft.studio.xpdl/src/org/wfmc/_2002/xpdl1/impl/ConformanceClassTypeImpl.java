/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.wfmc._2002.xpdl1.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.wfmc._2002.xpdl1.ConformanceClassType;
import org.wfmc._2002.xpdl1.GraphConformanceType;
import org.wfmc._2002.xpdl1.Xpdl1Package;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Conformance Class Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.ConformanceClassTypeImpl#getGraphConformance <em>Graph Conformance</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ConformanceClassTypeImpl extends EObjectImpl implements ConformanceClassType {
	/**
	 * The default value of the '{@link #getGraphConformance() <em>Graph Conformance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGraphConformance()
	 * @generated
	 * @ordered
	 */
	protected static final GraphConformanceType GRAPH_CONFORMANCE_EDEFAULT = GraphConformanceType.FULLBLOCKED;

	/**
	 * The cached value of the '{@link #getGraphConformance() <em>Graph Conformance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGraphConformance()
	 * @generated
	 * @ordered
	 */
	protected GraphConformanceType graphConformance = GRAPH_CONFORMANCE_EDEFAULT;

	/**
	 * This is true if the Graph Conformance attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean graphConformanceESet;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ConformanceClassTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Xpdl1Package.Literals.CONFORMANCE_CLASS_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GraphConformanceType getGraphConformance() {
		return graphConformance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGraphConformance(GraphConformanceType newGraphConformance) {
		GraphConformanceType oldGraphConformance = graphConformance;
		graphConformance = newGraphConformance == null ? GRAPH_CONFORMANCE_EDEFAULT : newGraphConformance;
		boolean oldGraphConformanceESet = graphConformanceESet;
		graphConformanceESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Xpdl1Package.CONFORMANCE_CLASS_TYPE__GRAPH_CONFORMANCE, oldGraphConformance, graphConformance, !oldGraphConformanceESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetGraphConformance() {
		GraphConformanceType oldGraphConformance = graphConformance;
		boolean oldGraphConformanceESet = graphConformanceESet;
		graphConformance = GRAPH_CONFORMANCE_EDEFAULT;
		graphConformanceESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, Xpdl1Package.CONFORMANCE_CLASS_TYPE__GRAPH_CONFORMANCE, oldGraphConformance, GRAPH_CONFORMANCE_EDEFAULT, oldGraphConformanceESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetGraphConformance() {
		return graphConformanceESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case Xpdl1Package.CONFORMANCE_CLASS_TYPE__GRAPH_CONFORMANCE:
				return getGraphConformance();
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
			case Xpdl1Package.CONFORMANCE_CLASS_TYPE__GRAPH_CONFORMANCE:
				setGraphConformance((GraphConformanceType)newValue);
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
			case Xpdl1Package.CONFORMANCE_CLASS_TYPE__GRAPH_CONFORMANCE:
				unsetGraphConformance();
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
			case Xpdl1Package.CONFORMANCE_CLASS_TYPE__GRAPH_CONFORMANCE:
				return isSetGraphConformance();
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
		result.append(" (graphConformance: ");
		if (graphConformanceESet) result.append(graphConformance); else result.append("<unset>");
		result.append(')');
		return result.toString();
	}

} //ConformanceClassTypeImpl
