/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.jbpm.jpdl32;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Mail Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.jbpm.jpdl32.MailType#getGroup <em>Group</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.MailType#getSubject <em>Subject</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.MailType#getText <em>Text</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.MailType#getActors <em>Actors</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.MailType#getAsync <em>Async</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.MailType#getName <em>Name</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.MailType#getSubject1 <em>Subject1</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.MailType#getTemplate <em>Template</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.MailType#getText1 <em>Text1</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.MailType#getTo <em>To</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.jbpm.jpdl32.jpdl32Package#getMailType()
 * @model extendedMetaData="name='mail_._type' kind='elementOnly'"
 * @generated
 */
public interface MailType extends EObject {
	/**
	 * Returns the value of the '<em><b>Group</b></em>' attribute list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Group</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Group</em>' attribute list.
	 * @see org.jbpm.jpdl32.jpdl32Package#getMailType_Group()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
	 *        extendedMetaData="kind='group' name='group:0'"
	 * @generated
	 */
	FeatureMap getGroup();

	/**
	 * Returns the value of the '<em><b>Subject</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Subject</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Subject</em>' attribute list.
	 * @see org.jbpm.jpdl32.jpdl32Package#getMailType_Subject()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='subject' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	EList<String> getSubject();

	/**
	 * Returns the value of the '<em><b>Text</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Text</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Text</em>' attribute list.
	 * @see org.jbpm.jpdl32.jpdl32Package#getMailType_Text()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='text' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	EList<String> getText();

	/**
	 * Returns the value of the '<em><b>Actors</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Actors</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Actors</em>' attribute.
	 * @see #setActors(String)
	 * @see org.jbpm.jpdl32.jpdl32Package#getMailType_Actors()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='actors'"
	 * @generated
	 */
	String getActors();

	/**
	 * Sets the value of the '{@link org.jbpm.jpdl32.MailType#getActors <em>Actors</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Actors</em>' attribute.
	 * @see #getActors()
	 * @generated
	 */
	void setActors(String value);

	/**
	 * Returns the value of the '<em><b>Async</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Async</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Async</em>' attribute.
	 * @see #isSetAsync()
	 * @see #unsetAsync()
	 * @see #setAsync(String)
	 * @see org.jbpm.jpdl32.jpdl32Package#getMailType_Async()
	 * @model default="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='async'"
	 * @generated
	 */
	String getAsync();

	/**
	 * Sets the value of the '{@link org.jbpm.jpdl32.MailType#getAsync <em>Async</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Async</em>' attribute.
	 * @see #isSetAsync()
	 * @see #unsetAsync()
	 * @see #getAsync()
	 * @generated
	 */
	void setAsync(String value);

	/**
	 * Unsets the value of the '{@link org.jbpm.jpdl32.MailType#getAsync <em>Async</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetAsync()
	 * @see #getAsync()
	 * @see #setAsync(String)
	 * @generated
	 */
	void unsetAsync();

	/**
	 * Returns whether the value of the '{@link org.jbpm.jpdl32.MailType#getAsync <em>Async</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Async</em>' attribute is set.
	 * @see #unsetAsync()
	 * @see #getAsync()
	 * @see #setAsync(String)
	 * @generated
	 */
	boolean isSetAsync();

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.jbpm.jpdl32.jpdl32Package#getMailType_Name()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='attribute' name='name'"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.jbpm.jpdl32.MailType#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Subject1</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Subject1</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Subject1</em>' attribute.
	 * @see #setSubject1(String)
	 * @see org.jbpm.jpdl32.jpdl32Package#getMailType_Subject1()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='subject'"
	 * @generated
	 */
	String getSubject1();

	/**
	 * Sets the value of the '{@link org.jbpm.jpdl32.MailType#getSubject1 <em>Subject1</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Subject1</em>' attribute.
	 * @see #getSubject1()
	 * @generated
	 */
	void setSubject1(String value);

	/**
	 * Returns the value of the '<em><b>Template</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Template</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Template</em>' attribute.
	 * @see #setTemplate(String)
	 * @see org.jbpm.jpdl32.jpdl32Package#getMailType_Template()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='template'"
	 * @generated
	 */
	String getTemplate();

	/**
	 * Sets the value of the '{@link org.jbpm.jpdl32.MailType#getTemplate <em>Template</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Template</em>' attribute.
	 * @see #getTemplate()
	 * @generated
	 */
	void setTemplate(String value);

	/**
	 * Returns the value of the '<em><b>Text1</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Text1</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Text1</em>' attribute.
	 * @see #setText1(String)
	 * @see org.jbpm.jpdl32.jpdl32Package#getMailType_Text1()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='text'"
	 * @generated
	 */
	String getText1();

	/**
	 * Sets the value of the '{@link org.jbpm.jpdl32.MailType#getText1 <em>Text1</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Text1</em>' attribute.
	 * @see #getText1()
	 * @generated
	 */
	void setText1(String value);

	/**
	 * Returns the value of the '<em><b>To</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>To</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>To</em>' attribute.
	 * @see #setTo(String)
	 * @see org.jbpm.jpdl32.jpdl32Package#getMailType_To()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='to'"
	 * @generated
	 */
	String getTo();

	/**
	 * Sets the value of the '{@link org.jbpm.jpdl32.MailType#getTo <em>To</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>To</em>' attribute.
	 * @see #getTo()
	 * @generated
	 */
	void setTo(String value);

} // MailType
