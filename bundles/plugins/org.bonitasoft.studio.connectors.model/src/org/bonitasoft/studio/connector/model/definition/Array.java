/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.bonitasoft.studio.connector.model.definition;

import java.math.BigInteger;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Array</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.bonitasoft.studio.connector.model.definition.Array#getColsCaption <em>Cols Caption</em>}</li>
 *   <li>{@link org.bonitasoft.studio.connector.model.definition.Array#getCols <em>Cols</em>}</li>
 *   <li>{@link org.bonitasoft.studio.connector.model.definition.Array#isFixedCols <em>Fixed Cols</em>}</li>
 *   <li>{@link org.bonitasoft.studio.connector.model.definition.Array#isFixedRows <em>Fixed Rows</em>}</li>
 *   <li>{@link org.bonitasoft.studio.connector.model.definition.Array#getRows <em>Rows</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.bonitasoft.studio.connector.model.definition.ConnectorDefinitionPackage#getArray()
 * @model extendedMetaData="name='Array' kind='elementOnly'"
 * @generated
 */
public interface Array extends Widget {
    /**
	 * Returns the value of the '<em><b>Cols Caption</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Cols Caption</em>' attribute list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Cols Caption</em>' attribute list.
	 * @see org.bonitasoft.studio.connector.model.definition.ConnectorDefinitionPackage#getArray_ColsCaption()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='colsCaption'"
	 * @generated
	 */
    EList<String> getColsCaption();

    /**
	 * Returns the value of the '<em><b>Cols</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Cols</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Cols</em>' attribute.
	 * @see #setCols(BigInteger)
	 * @see org.bonitasoft.studio.connector.model.definition.ConnectorDefinitionPackage#getArray_Cols()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.Integer"
	 *        extendedMetaData="kind='attribute' name='cols'"
	 * @generated
	 */
    BigInteger getCols();

    /**
	 * Sets the value of the '{@link org.bonitasoft.studio.connector.model.definition.Array#getCols <em>Cols</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cols</em>' attribute.
	 * @see #getCols()
	 * @generated
	 */
    void setCols(BigInteger value);

    /**
	 * Returns the value of the '<em><b>Fixed Cols</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Fixed Cols</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Fixed Cols</em>' attribute.
	 * @see #isSetFixedCols()
	 * @see #unsetFixedCols()
	 * @see #setFixedCols(boolean)
	 * @see org.bonitasoft.studio.connector.model.definition.ConnectorDefinitionPackage#getArray_FixedCols()
	 * @model default="true" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='attribute' name='fixedCols'"
	 * @generated
	 */
    boolean isFixedCols();

    /**
	 * Sets the value of the '{@link org.bonitasoft.studio.connector.model.definition.Array#isFixedCols <em>Fixed Cols</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Fixed Cols</em>' attribute.
	 * @see #isSetFixedCols()
	 * @see #unsetFixedCols()
	 * @see #isFixedCols()
	 * @generated
	 */
    void setFixedCols(boolean value);

    /**
	 * Unsets the value of the '{@link org.bonitasoft.studio.connector.model.definition.Array#isFixedCols <em>Fixed Cols</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isSetFixedCols()
	 * @see #isFixedCols()
	 * @see #setFixedCols(boolean)
	 * @generated
	 */
    void unsetFixedCols();

    /**
	 * Returns whether the value of the '{@link org.bonitasoft.studio.connector.model.definition.Array#isFixedCols <em>Fixed Cols</em>}' attribute is set.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Fixed Cols</em>' attribute is set.
	 * @see #unsetFixedCols()
	 * @see #isFixedCols()
	 * @see #setFixedCols(boolean)
	 * @generated
	 */
    boolean isSetFixedCols();

    /**
	 * Returns the value of the '<em><b>Fixed Rows</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Fixed Rows</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Fixed Rows</em>' attribute.
	 * @see #isSetFixedRows()
	 * @see #unsetFixedRows()
	 * @see #setFixedRows(boolean)
	 * @see org.bonitasoft.studio.connector.model.definition.ConnectorDefinitionPackage#getArray_FixedRows()
	 * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='attribute' name='fixedRows'"
	 * @generated
	 */
    boolean isFixedRows();

    /**
	 * Sets the value of the '{@link org.bonitasoft.studio.connector.model.definition.Array#isFixedRows <em>Fixed Rows</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Fixed Rows</em>' attribute.
	 * @see #isSetFixedRows()
	 * @see #unsetFixedRows()
	 * @see #isFixedRows()
	 * @generated
	 */
    void setFixedRows(boolean value);

    /**
	 * Unsets the value of the '{@link org.bonitasoft.studio.connector.model.definition.Array#isFixedRows <em>Fixed Rows</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isSetFixedRows()
	 * @see #isFixedRows()
	 * @see #setFixedRows(boolean)
	 * @generated
	 */
    void unsetFixedRows();

    /**
	 * Returns whether the value of the '{@link org.bonitasoft.studio.connector.model.definition.Array#isFixedRows <em>Fixed Rows</em>}' attribute is set.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Fixed Rows</em>' attribute is set.
	 * @see #unsetFixedRows()
	 * @see #isFixedRows()
	 * @see #setFixedRows(boolean)
	 * @generated
	 */
    boolean isSetFixedRows();

    /**
	 * Returns the value of the '<em><b>Rows</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Rows</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Rows</em>' attribute.
	 * @see #setRows(BigInteger)
	 * @see org.bonitasoft.studio.connector.model.definition.ConnectorDefinitionPackage#getArray_Rows()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.Integer"
	 *        extendedMetaData="kind='attribute' name='rows'"
	 * @generated
	 */
    BigInteger getRows();

    /**
	 * Sets the value of the '{@link org.bonitasoft.studio.connector.model.definition.Array#getRows <em>Rows</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Rows</em>' attribute.
	 * @see #getRows()
	 * @generated
	 */
    void setRows(BigInteger value);

} // Array
