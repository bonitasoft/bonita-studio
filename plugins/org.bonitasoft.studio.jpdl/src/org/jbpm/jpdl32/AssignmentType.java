/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.jbpm.jpdl32;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Assignment Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.jbpm.jpdl32.AssignmentType#getActorId <em>Actor Id</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.AssignmentType#getExpression <em>Expression</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.AssignmentType#getPooledActors <em>Pooled Actors</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.jbpm.jpdl32.jpdl32Package#getAssignmentType()
 * @model extendedMetaData="name='assignment_._type' kind='mixed'"
 * @generated
 */
public interface AssignmentType extends Delegation {
	/**
	 * Returns the value of the '<em><b>Actor Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Actor Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Actor Id</em>' attribute.
	 * @see #setActorId(String)
	 * @see org.jbpm.jpdl32.jpdl32Package#getAssignmentType_ActorId()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='actor-id'"
	 * @generated
	 */
	String getActorId();

	/**
	 * Sets the value of the '{@link org.jbpm.jpdl32.AssignmentType#getActorId <em>Actor Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Actor Id</em>' attribute.
	 * @see #getActorId()
	 * @generated
	 */
	void setActorId(String value);

	/**
	 * Returns the value of the '<em><b>Expression</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Expression</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Expression</em>' attribute.
	 * @see #setExpression(String)
	 * @see org.jbpm.jpdl32.jpdl32Package#getAssignmentType_Expression()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='expression'"
	 * @generated
	 */
	String getExpression();

	/**
	 * Sets the value of the '{@link org.jbpm.jpdl32.AssignmentType#getExpression <em>Expression</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Expression</em>' attribute.
	 * @see #getExpression()
	 * @generated
	 */
	void setExpression(String value);

	/**
	 * Returns the value of the '<em><b>Pooled Actors</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Pooled Actors</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Pooled Actors</em>' attribute.
	 * @see #setPooledActors(String)
	 * @see org.jbpm.jpdl32.jpdl32Package#getAssignmentType_PooledActors()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='pooled-actors'"
	 * @generated
	 */
	String getPooledActors();

	/**
	 * Sets the value of the '{@link org.jbpm.jpdl32.AssignmentType#getPooledActors <em>Pooled Actors</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pooled Actors</em>' attribute.
	 * @see #getPooledActors()
	 * @generated
	 */
	void setPooledActors(String value);

} // AssignmentType
