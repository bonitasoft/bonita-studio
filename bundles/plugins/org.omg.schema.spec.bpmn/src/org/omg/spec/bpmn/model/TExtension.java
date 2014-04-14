/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.omg.spec.bpmn.model;

import javax.xml.namespace.QName;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>TExtension</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.TExtension#getDocumentation <em>Documentation</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TExtension#getDefinition <em>Definition</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TExtension#isMustUnderstand <em>Must Understand</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.omg.spec.bpmn.model.ModelPackage#getTExtension()
 * @model extendedMetaData="name='tExtension' kind='elementOnly'"
 * @generated
 */
public interface TExtension extends EObject {
	/**
	 * Returns the value of the '<em><b>Documentation</b></em>' containment reference list.
	 * The list contents are of type {@link org.omg.spec.bpmn.model.TDocumentation}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Documentation</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Documentation</em>' containment reference list.
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTExtension_Documentation()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='documentation' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<TDocumentation> getDocumentation();

	/**
	 * Returns the value of the '<em><b>Definition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Definition</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Definition</em>' attribute.
	 * @see #setDefinition(QName)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTExtension_Definition()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.QName"
	 *        extendedMetaData="kind='attribute' name='definition'"
	 * @generated
	 */
	QName getDefinition();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.TExtension#getDefinition <em>Definition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Definition</em>' attribute.
	 * @see #getDefinition()
	 * @generated
	 */
	void setDefinition(QName value);

	/**
	 * Returns the value of the '<em><b>Must Understand</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Must Understand</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Must Understand</em>' attribute.
	 * @see #isSetMustUnderstand()
	 * @see #unsetMustUnderstand()
	 * @see #setMustUnderstand(boolean)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTExtension_MustUnderstand()
	 * @model default="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='attribute' name='mustUnderstand'"
	 * @generated
	 */
	boolean isMustUnderstand();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.TExtension#isMustUnderstand <em>Must Understand</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Must Understand</em>' attribute.
	 * @see #isSetMustUnderstand()
	 * @see #unsetMustUnderstand()
	 * @see #isMustUnderstand()
	 * @generated
	 */
	void setMustUnderstand(boolean value);

	/**
	 * Unsets the value of the '{@link org.omg.spec.bpmn.model.TExtension#isMustUnderstand <em>Must Understand</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetMustUnderstand()
	 * @see #isMustUnderstand()
	 * @see #setMustUnderstand(boolean)
	 * @generated
	 */
	void unsetMustUnderstand();

	/**
	 * Returns whether the value of the '{@link org.omg.spec.bpmn.model.TExtension#isMustUnderstand <em>Must Understand</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Must Understand</em>' attribute is set.
	 * @see #unsetMustUnderstand()
	 * @see #isMustUnderstand()
	 * @see #setMustUnderstand(boolean)
	 * @generated
	 */
	boolean isSetMustUnderstand();

} // TExtension
