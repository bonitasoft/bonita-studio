/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.omg.spec.bpmn.model;

import javax.xml.namespace.QName;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>TAssociation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.TAssociation#getAssociationDirection <em>Association Direction</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TAssociation#getSourceRef <em>Source Ref</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TAssociation#getTargetRef <em>Target Ref</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.omg.spec.bpmn.model.ModelPackage#getTAssociation()
 * @model extendedMetaData="name='tAssociation' kind='elementOnly'"
 * @generated
 */
public interface TAssociation extends TArtifact {
	/**
	 * Returns the value of the '<em><b>Association Direction</b></em>' attribute.
	 * The default value is <code>"None"</code>.
	 * The literals are from the enumeration {@link org.omg.spec.bpmn.model.TAssociationDirection}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Association Direction</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Association Direction</em>' attribute.
	 * @see org.omg.spec.bpmn.model.TAssociationDirection
	 * @see #isSetAssociationDirection()
	 * @see #unsetAssociationDirection()
	 * @see #setAssociationDirection(TAssociationDirection)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTAssociation_AssociationDirection()
	 * @model default="None" unsettable="true"
	 *        extendedMetaData="kind='attribute' name='associationDirection'"
	 * @generated
	 */
	TAssociationDirection getAssociationDirection();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.TAssociation#getAssociationDirection <em>Association Direction</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Association Direction</em>' attribute.
	 * @see org.omg.spec.bpmn.model.TAssociationDirection
	 * @see #isSetAssociationDirection()
	 * @see #unsetAssociationDirection()
	 * @see #getAssociationDirection()
	 * @generated
	 */
	void setAssociationDirection(TAssociationDirection value);

	/**
	 * Unsets the value of the '{@link org.omg.spec.bpmn.model.TAssociation#getAssociationDirection <em>Association Direction</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetAssociationDirection()
	 * @see #getAssociationDirection()
	 * @see #setAssociationDirection(TAssociationDirection)
	 * @generated
	 */
	void unsetAssociationDirection();

	/**
	 * Returns whether the value of the '{@link org.omg.spec.bpmn.model.TAssociation#getAssociationDirection <em>Association Direction</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Association Direction</em>' attribute is set.
	 * @see #unsetAssociationDirection()
	 * @see #getAssociationDirection()
	 * @see #setAssociationDirection(TAssociationDirection)
	 * @generated
	 */
	boolean isSetAssociationDirection();

	/**
	 * Returns the value of the '<em><b>Source Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source Ref</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source Ref</em>' attribute.
	 * @see #setSourceRef(QName)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTAssociation_SourceRef()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.QName" required="true"
	 *        extendedMetaData="kind='attribute' name='sourceRef'"
	 * @generated
	 */
	QName getSourceRef();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.TAssociation#getSourceRef <em>Source Ref</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source Ref</em>' attribute.
	 * @see #getSourceRef()
	 * @generated
	 */
	void setSourceRef(QName value);

	/**
	 * Returns the value of the '<em><b>Target Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target Ref</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target Ref</em>' attribute.
	 * @see #setTargetRef(QName)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTAssociation_TargetRef()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.QName" required="true"
	 *        extendedMetaData="kind='attribute' name='targetRef'"
	 * @generated
	 */
	QName getTargetRef();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.TAssociation#getTargetRef <em>Target Ref</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target Ref</em>' attribute.
	 * @see #getTargetRef()
	 * @generated
	 */
	void setTargetRef(QName value);

} // TAssociation
