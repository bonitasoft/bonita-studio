/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.bonitasoft.studio.connector.model.definition.impl;

import java.math.BigInteger;

import java.util.Collection;

import org.bonitasoft.studio.connector.model.definition.Array;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinitionPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EDataTypeEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Array</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.bonitasoft.studio.connector.model.definition.impl.ArrayImpl#getColsCaption <em>Cols Caption</em>}</li>
 *   <li>{@link org.bonitasoft.studio.connector.model.definition.impl.ArrayImpl#getCols <em>Cols</em>}</li>
 *   <li>{@link org.bonitasoft.studio.connector.model.definition.impl.ArrayImpl#isFixedCols <em>Fixed Cols</em>}</li>
 *   <li>{@link org.bonitasoft.studio.connector.model.definition.impl.ArrayImpl#isFixedRows <em>Fixed Rows</em>}</li>
 *   <li>{@link org.bonitasoft.studio.connector.model.definition.impl.ArrayImpl#getRows <em>Rows</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ArrayImpl extends WidgetImpl implements Array {
    /**
	 * The cached value of the '{@link #getColsCaption() <em>Cols Caption</em>}' attribute list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getColsCaption()
	 * @generated
	 * @ordered
	 */
    protected EList<String> colsCaption;

    /**
	 * The default value of the '{@link #getCols() <em>Cols</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getCols()
	 * @generated
	 * @ordered
	 */
    protected static final BigInteger COLS_EDEFAULT = null;

    /**
	 * The cached value of the '{@link #getCols() <em>Cols</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getCols()
	 * @generated
	 * @ordered
	 */
    protected BigInteger cols = COLS_EDEFAULT;

    /**
	 * The default value of the '{@link #isFixedCols() <em>Fixed Cols</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isFixedCols()
	 * @generated
	 * @ordered
	 */
    protected static final boolean FIXED_COLS_EDEFAULT = true;

    /**
	 * The cached value of the '{@link #isFixedCols() <em>Fixed Cols</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isFixedCols()
	 * @generated
	 * @ordered
	 */
    protected boolean fixedCols = FIXED_COLS_EDEFAULT;

    /**
	 * This is true if the Fixed Cols attribute has been set.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    protected boolean fixedColsESet;

    /**
	 * The default value of the '{@link #isFixedRows() <em>Fixed Rows</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isFixedRows()
	 * @generated
	 * @ordered
	 */
    protected static final boolean FIXED_ROWS_EDEFAULT = false;

    /**
	 * The cached value of the '{@link #isFixedRows() <em>Fixed Rows</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isFixedRows()
	 * @generated
	 * @ordered
	 */
    protected boolean fixedRows = FIXED_ROWS_EDEFAULT;

    /**
	 * This is true if the Fixed Rows attribute has been set.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    protected boolean fixedRowsESet;

    /**
	 * The default value of the '{@link #getRows() <em>Rows</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getRows()
	 * @generated
	 * @ordered
	 */
    protected static final BigInteger ROWS_EDEFAULT = null;

    /**
	 * The cached value of the '{@link #getRows() <em>Rows</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getRows()
	 * @generated
	 * @ordered
	 */
    protected BigInteger rows = ROWS_EDEFAULT;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected ArrayImpl() {
		super();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    protected EClass eStaticClass() {
		return ConnectorDefinitionPackage.Literals.ARRAY;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList<String> getColsCaption() {
		if (colsCaption == null) {
			colsCaption = new EDataTypeEList<String>(String.class, this, ConnectorDefinitionPackage.ARRAY__COLS_CAPTION);
		}
		return colsCaption;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public BigInteger getCols() {
		return cols;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setCols(BigInteger newCols) {
		BigInteger oldCols = cols;
		cols = newCols;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConnectorDefinitionPackage.ARRAY__COLS, oldCols, cols));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public boolean isFixedCols() {
		return fixedCols;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setFixedCols(boolean newFixedCols) {
		boolean oldFixedCols = fixedCols;
		fixedCols = newFixedCols;
		boolean oldFixedColsESet = fixedColsESet;
		fixedColsESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConnectorDefinitionPackage.ARRAY__FIXED_COLS, oldFixedCols, fixedCols, !oldFixedColsESet));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void unsetFixedCols() {
		boolean oldFixedCols = fixedCols;
		boolean oldFixedColsESet = fixedColsESet;
		fixedCols = FIXED_COLS_EDEFAULT;
		fixedColsESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, ConnectorDefinitionPackage.ARRAY__FIXED_COLS, oldFixedCols, FIXED_COLS_EDEFAULT, oldFixedColsESet));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public boolean isSetFixedCols() {
		return fixedColsESet;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public boolean isFixedRows() {
		return fixedRows;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setFixedRows(boolean newFixedRows) {
		boolean oldFixedRows = fixedRows;
		fixedRows = newFixedRows;
		boolean oldFixedRowsESet = fixedRowsESet;
		fixedRowsESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConnectorDefinitionPackage.ARRAY__FIXED_ROWS, oldFixedRows, fixedRows, !oldFixedRowsESet));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void unsetFixedRows() {
		boolean oldFixedRows = fixedRows;
		boolean oldFixedRowsESet = fixedRowsESet;
		fixedRows = FIXED_ROWS_EDEFAULT;
		fixedRowsESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, ConnectorDefinitionPackage.ARRAY__FIXED_ROWS, oldFixedRows, FIXED_ROWS_EDEFAULT, oldFixedRowsESet));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public boolean isSetFixedRows() {
		return fixedRowsESet;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public BigInteger getRows() {
		return rows;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setRows(BigInteger newRows) {
		BigInteger oldRows = rows;
		rows = newRows;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConnectorDefinitionPackage.ARRAY__ROWS, oldRows, rows));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ConnectorDefinitionPackage.ARRAY__COLS_CAPTION:
				return getColsCaption();
			case ConnectorDefinitionPackage.ARRAY__COLS:
				return getCols();
			case ConnectorDefinitionPackage.ARRAY__FIXED_COLS:
				return isFixedCols();
			case ConnectorDefinitionPackage.ARRAY__FIXED_ROWS:
				return isFixedRows();
			case ConnectorDefinitionPackage.ARRAY__ROWS:
				return getRows();
		}
		return super.eGet(featureID, resolve, coreType);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ConnectorDefinitionPackage.ARRAY__COLS_CAPTION:
				getColsCaption().clear();
				getColsCaption().addAll((Collection<? extends String>)newValue);
				return;
			case ConnectorDefinitionPackage.ARRAY__COLS:
				setCols((BigInteger)newValue);
				return;
			case ConnectorDefinitionPackage.ARRAY__FIXED_COLS:
				setFixedCols((Boolean)newValue);
				return;
			case ConnectorDefinitionPackage.ARRAY__FIXED_ROWS:
				setFixedRows((Boolean)newValue);
				return;
			case ConnectorDefinitionPackage.ARRAY__ROWS:
				setRows((BigInteger)newValue);
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
			case ConnectorDefinitionPackage.ARRAY__COLS_CAPTION:
				getColsCaption().clear();
				return;
			case ConnectorDefinitionPackage.ARRAY__COLS:
				setCols(COLS_EDEFAULT);
				return;
			case ConnectorDefinitionPackage.ARRAY__FIXED_COLS:
				unsetFixedCols();
				return;
			case ConnectorDefinitionPackage.ARRAY__FIXED_ROWS:
				unsetFixedRows();
				return;
			case ConnectorDefinitionPackage.ARRAY__ROWS:
				setRows(ROWS_EDEFAULT);
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
			case ConnectorDefinitionPackage.ARRAY__COLS_CAPTION:
				return colsCaption != null && !colsCaption.isEmpty();
			case ConnectorDefinitionPackage.ARRAY__COLS:
				return COLS_EDEFAULT == null ? cols != null : !COLS_EDEFAULT.equals(cols);
			case ConnectorDefinitionPackage.ARRAY__FIXED_COLS:
				return isSetFixedCols();
			case ConnectorDefinitionPackage.ARRAY__FIXED_ROWS:
				return isSetFixedRows();
			case ConnectorDefinitionPackage.ARRAY__ROWS:
				return ROWS_EDEFAULT == null ? rows != null : !ROWS_EDEFAULT.equals(rows);
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
		result.append(" (colsCaption: ");
		result.append(colsCaption);
		result.append(", cols: ");
		result.append(cols);
		result.append(", fixedCols: ");
		if (fixedColsESet) result.append(fixedCols); else result.append("<unset>");
		result.append(", fixedRows: ");
		if (fixedRowsESet) result.append(fixedRows); else result.append("<unset>");
		result.append(", rows: ");
		result.append(rows);
		result.append(')');
		return result.toString();
	}

} //ArrayImpl
