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
 * A representation of the model object '<em><b>TCorrelation Property Binding</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.TCorrelationPropertyBinding#getDataPath <em>Data Path</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TCorrelationPropertyBinding#getCorrelationPropertyRef <em>Correlation Property Ref</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.omg.spec.bpmn.model.ModelPackage#getTCorrelationPropertyBinding()
 * @model extendedMetaData="name='tCorrelationPropertyBinding' kind='elementOnly'"
 * @generated
 */
public interface TCorrelationPropertyBinding extends TBaseElement {
	/**
	 * Returns the value of the '<em><b>Data Path</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Data Path</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Data Path</em>' containment reference.
	 * @see #setDataPath(TFormalExpression)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTCorrelationPropertyBinding_DataPath()
	 * @model containment="true" required="true"
	 *        extendedMetaData="kind='element' name='dataPath' namespace='##targetNamespace'"
	 * @generated
	 */
	TFormalExpression getDataPath();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.TCorrelationPropertyBinding#getDataPath <em>Data Path</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Data Path</em>' containment reference.
	 * @see #getDataPath()
	 * @generated
	 */
	void setDataPath(TFormalExpression value);

	/**
	 * Returns the value of the '<em><b>Correlation Property Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Correlation Property Ref</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Correlation Property Ref</em>' attribute.
	 * @see #setCorrelationPropertyRef(QName)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTCorrelationPropertyBinding_CorrelationPropertyRef()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.QName" required="true"
	 *        extendedMetaData="kind='attribute' name='correlationPropertyRef'"
	 * @generated
	 */
	QName getCorrelationPropertyRef();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.TCorrelationPropertyBinding#getCorrelationPropertyRef <em>Correlation Property Ref</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Correlation Property Ref</em>' attribute.
	 * @see #getCorrelationPropertyRef()
	 * @generated
	 */
	void setCorrelationPropertyRef(QName value);

} // TCorrelationPropertyBinding
