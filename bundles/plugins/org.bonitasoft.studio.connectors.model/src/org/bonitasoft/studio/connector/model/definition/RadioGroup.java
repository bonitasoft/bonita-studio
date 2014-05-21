/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.bonitasoft.studio.connector.model.definition;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Radio Group</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.bonitasoft.studio.connector.model.definition.RadioGroup#getChoices <em>Choices</em>}</li>
 *   <li>{@link org.bonitasoft.studio.connector.model.definition.RadioGroup#getOrientation <em>Orientation</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.bonitasoft.studio.connector.model.definition.ConnectorDefinitionPackage#getRadioGroup()
 * @model extendedMetaData="name='RadioGroup' kind='elementOnly'"
 * @generated
 */
public interface RadioGroup extends Widget {
    /**
	 * Returns the value of the '<em><b>Choices</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Choices</em>' attribute list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Choices</em>' attribute list.
	 * @see org.bonitasoft.studio.connector.model.definition.ConnectorDefinitionPackage#getRadioGroup_Choices()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='choices'"
	 * @generated
	 */
    EList<String> getChoices();

    /**
	 * Returns the value of the '<em><b>Orientation</b></em>' attribute.
	 * The default value is <code>"HORIZONTAL"</code>.
	 * The literals are from the enumeration {@link org.bonitasoft.studio.connector.model.definition.Orientation}.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Orientation</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Orientation</em>' attribute.
	 * @see org.bonitasoft.studio.connector.model.definition.Orientation
	 * @see #isSetOrientation()
	 * @see #unsetOrientation()
	 * @see #setOrientation(Orientation)
	 * @see org.bonitasoft.studio.connector.model.definition.ConnectorDefinitionPackage#getRadioGroup_Orientation()
	 * @model default="HORIZONTAL" unsettable="true"
	 *        extendedMetaData="kind='attribute' name='orientation'"
	 * @generated
	 */
    Orientation getOrientation();

    /**
	 * Sets the value of the '{@link org.bonitasoft.studio.connector.model.definition.RadioGroup#getOrientation <em>Orientation</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Orientation</em>' attribute.
	 * @see org.bonitasoft.studio.connector.model.definition.Orientation
	 * @see #isSetOrientation()
	 * @see #unsetOrientation()
	 * @see #getOrientation()
	 * @generated
	 */
    void setOrientation(Orientation value);

    /**
	 * Unsets the value of the '{@link org.bonitasoft.studio.connector.model.definition.RadioGroup#getOrientation <em>Orientation</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isSetOrientation()
	 * @see #getOrientation()
	 * @see #setOrientation(Orientation)
	 * @generated
	 */
    void unsetOrientation();

    /**
	 * Returns whether the value of the '{@link org.bonitasoft.studio.connector.model.definition.RadioGroup#getOrientation <em>Orientation</em>}' attribute is set.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Orientation</em>' attribute is set.
	 * @see #unsetOrientation()
	 * @see #getOrientation()
	 * @see #setOrientation(Orientation)
	 * @generated
	 */
    boolean isSetOrientation();

} // RadioGroup
