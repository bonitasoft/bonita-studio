/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.jbpm.jpdl32.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.jbpm.jpdl32.AssignmentType;
import org.jbpm.jpdl32.jpdl32Package;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Assignment Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.jbpm.jpdl32.impl.AssignmentTypeImpl#getActorId <em>Actor Id</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.AssignmentTypeImpl#getExpression <em>Expression</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.AssignmentTypeImpl#getPooledActors <em>Pooled Actors</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AssignmentTypeImpl extends DelegationImpl implements AssignmentType {
	/**
	 * The default value of the '{@link #getActorId() <em>Actor Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActorId()
	 * @generated
	 * @ordered
	 */
	protected static final String ACTOR_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getActorId() <em>Actor Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActorId()
	 * @generated
	 * @ordered
	 */
	protected String actorId = ACTOR_ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getExpression() <em>Expression</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExpression()
	 * @generated
	 * @ordered
	 */
	protected static final String EXPRESSION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getExpression() <em>Expression</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExpression()
	 * @generated
	 * @ordered
	 */
	protected String expression = EXPRESSION_EDEFAULT;

	/**
	 * The default value of the '{@link #getPooledActors() <em>Pooled Actors</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPooledActors()
	 * @generated
	 * @ordered
	 */
	protected static final String POOLED_ACTORS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPooledActors() <em>Pooled Actors</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPooledActors()
	 * @generated
	 * @ordered
	 */
	protected String pooledActors = POOLED_ACTORS_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AssignmentTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return jpdl32Package.Literals.ASSIGNMENT_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getActorId() {
		return actorId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setActorId(String newActorId) {
		String oldActorId = actorId;
		actorId = newActorId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, jpdl32Package.ASSIGNMENT_TYPE__ACTOR_ID, oldActorId, actorId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getExpression() {
		return expression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExpression(String newExpression) {
		String oldExpression = expression;
		expression = newExpression;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, jpdl32Package.ASSIGNMENT_TYPE__EXPRESSION, oldExpression, expression));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPooledActors() {
		return pooledActors;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPooledActors(String newPooledActors) {
		String oldPooledActors = pooledActors;
		pooledActors = newPooledActors;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, jpdl32Package.ASSIGNMENT_TYPE__POOLED_ACTORS, oldPooledActors, pooledActors));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case jpdl32Package.ASSIGNMENT_TYPE__ACTOR_ID:
				return getActorId();
			case jpdl32Package.ASSIGNMENT_TYPE__EXPRESSION:
				return getExpression();
			case jpdl32Package.ASSIGNMENT_TYPE__POOLED_ACTORS:
				return getPooledActors();
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
			case jpdl32Package.ASSIGNMENT_TYPE__ACTOR_ID:
				setActorId((String)newValue);
				return;
			case jpdl32Package.ASSIGNMENT_TYPE__EXPRESSION:
				setExpression((String)newValue);
				return;
			case jpdl32Package.ASSIGNMENT_TYPE__POOLED_ACTORS:
				setPooledActors((String)newValue);
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
			case jpdl32Package.ASSIGNMENT_TYPE__ACTOR_ID:
				setActorId(ACTOR_ID_EDEFAULT);
				return;
			case jpdl32Package.ASSIGNMENT_TYPE__EXPRESSION:
				setExpression(EXPRESSION_EDEFAULT);
				return;
			case jpdl32Package.ASSIGNMENT_TYPE__POOLED_ACTORS:
				setPooledActors(POOLED_ACTORS_EDEFAULT);
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
			case jpdl32Package.ASSIGNMENT_TYPE__ACTOR_ID:
				return ACTOR_ID_EDEFAULT == null ? actorId != null : !ACTOR_ID_EDEFAULT.equals(actorId);
			case jpdl32Package.ASSIGNMENT_TYPE__EXPRESSION:
				return EXPRESSION_EDEFAULT == null ? expression != null : !EXPRESSION_EDEFAULT.equals(expression);
			case jpdl32Package.ASSIGNMENT_TYPE__POOLED_ACTORS:
				return POOLED_ACTORS_EDEFAULT == null ? pooledActors != null : !POOLED_ACTORS_EDEFAULT.equals(pooledActors);
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
		result.append(" (actorId: ");
		result.append(actorId);
		result.append(", expression: ");
		result.append(expression);
		result.append(", pooledActors: ");
		result.append(pooledActors);
		result.append(')');
		return result.toString();
	}

} //AssignmentTypeImpl
