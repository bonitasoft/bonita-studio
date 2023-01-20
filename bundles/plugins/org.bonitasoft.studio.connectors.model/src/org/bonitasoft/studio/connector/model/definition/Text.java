/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.bonitasoft.studio.connector.model.definition;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Text</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.bonitasoft.studio.connector.model.definition.Text#isShowDocuments <em>Show Documents</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.bonitasoft.studio.connector.model.definition.ConnectorDefinitionPackage#getText()
 * @model extendedMetaData="name='Text' kind='empty'"
 * @generated
 */
public interface Text extends Widget {

	/**
	 * Returns the value of the '<em><b>Show Documents</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Show Documents</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Show Documents</em>' attribute.
	 * @see #setShowDocuments(boolean)
	 * @see org.bonitasoft.studio.connector.model.definition.ConnectorDefinitionPackage#getText_ShowDocuments()
	 * @model default="false"
	 *        extendedMetaData="kind='attribute' name='showDocuments'"
	 * @generated
	 */
	boolean isShowDocuments();

	/**
	 * Sets the value of the '{@link org.bonitasoft.studio.connector.model.definition.Text#isShowDocuments <em>Show Documents</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Show Documents</em>' attribute.
	 * @see #isShowDocuments()
	 * @generated
	 */
	void setShowDocuments(boolean value);
} // Text
