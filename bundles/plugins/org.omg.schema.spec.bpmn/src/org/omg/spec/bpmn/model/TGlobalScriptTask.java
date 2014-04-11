/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.omg.spec.bpmn.model;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>TGlobal Script Task</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.TGlobalScriptTask#getScript <em>Script</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TGlobalScriptTask#getScriptLanguage <em>Script Language</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.omg.spec.bpmn.model.ModelPackage#getTGlobalScriptTask()
 * @model extendedMetaData="name='tGlobalScriptTask' kind='elementOnly'"
 * @generated
 */
public interface TGlobalScriptTask extends TGlobalTask {
	/**
	 * Returns the value of the '<em><b>Script</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Script</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Script</em>' containment reference.
	 * @see #setScript(TScript)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTGlobalScriptTask_Script()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='script' namespace='##targetNamespace'"
	 * @generated
	 */
	TScript getScript();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.TGlobalScriptTask#getScript <em>Script</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Script</em>' containment reference.
	 * @see #getScript()
	 * @generated
	 */
	void setScript(TScript value);

	/**
	 * Returns the value of the '<em><b>Script Language</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Script Language</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Script Language</em>' attribute.
	 * @see #setScriptLanguage(String)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTGlobalScriptTask_ScriptLanguage()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.AnyURI"
	 *        extendedMetaData="kind='attribute' name='scriptLanguage'"
	 * @generated
	 */
	String getScriptLanguage();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.TGlobalScriptTask#getScriptLanguage <em>Script Language</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Script Language</em>' attribute.
	 * @see #getScriptLanguage()
	 * @generated
	 */
	void setScriptLanguage(String value);

} // TGlobalScriptTask
