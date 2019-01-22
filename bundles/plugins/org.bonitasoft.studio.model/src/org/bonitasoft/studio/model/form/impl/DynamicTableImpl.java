/**
 * Copyright (C) 2009-2015 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.model.form.impl;

import java.util.Collection;

import org.bonitasoft.studio.model.expression.Expression;

import org.bonitasoft.studio.model.form.DynamicTable;
import org.bonitasoft.studio.model.form.FormField;
import org.bonitasoft.studio.model.form.FormPackage;
import org.bonitasoft.studio.model.form.LabelPosition;
import org.bonitasoft.studio.model.form.SingleValuatedFormField;
import org.bonitasoft.studio.model.form.Validable;
import org.bonitasoft.studio.model.form.Validator;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Dynamic Table</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.DynamicTableImpl#getValidators <em>Validators</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.DynamicTableImpl#getUseDefaultValidator <em>Use Default Validator</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.DynamicTableImpl#isBelow <em>Below</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.DynamicTableImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.DynamicTableImpl#getExampleMessagePosition <em>Example Message Position</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.DynamicTableImpl#getExampleMessage <em>Example Message</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.DynamicTableImpl#isLimitMinNumberOfColumn <em>Limit Min Number Of Column</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.DynamicTableImpl#isLimitMinNumberOfRow <em>Limit Min Number Of Row</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.DynamicTableImpl#isAllowAddRemoveRow <em>Allow Add Remove Row</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.DynamicTableImpl#isAllowAddRemoveColumn <em>Allow Add Remove Column</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.DynamicTableImpl#isLimitMaxNumberOfColumn <em>Limit Max Number Of Column</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.DynamicTableImpl#isLimitMaxNumberOfRow <em>Limit Max Number Of Row</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.DynamicTableImpl#getMinNumberOfColumn <em>Min Number Of Column</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.DynamicTableImpl#getMinNumberOfRow <em>Min Number Of Row</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.DynamicTableImpl#getMaxNumberOfColumn <em>Max Number Of Column</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.DynamicTableImpl#getMaxNumberOfRow <em>Max Number Of Row</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DynamicTableImpl extends AbstractTableImpl implements DynamicTable {
	/**
	 * The cached value of the '{@link #getValidators() <em>Validators</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValidators()
	 * @generated
	 * @ordered
	 */
	protected EList<Validator> validators;

	/**
	 * The default value of the '{@link #getUseDefaultValidator() <em>Use Default Validator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUseDefaultValidator()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean USE_DEFAULT_VALIDATOR_EDEFAULT = Boolean.TRUE;

	/**
	 * The cached value of the '{@link #getUseDefaultValidator() <em>Use Default Validator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUseDefaultValidator()
	 * @generated
	 * @ordered
	 */
	protected Boolean useDefaultValidator = USE_DEFAULT_VALIDATOR_EDEFAULT;

	/**
	 * The default value of the '{@link #isBelow() <em>Below</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isBelow()
	 * @generated
	 * @ordered
	 */
	protected static final boolean BELOW_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isBelow() <em>Below</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isBelow()
	 * @generated
	 * @ordered
	 */
	protected boolean below = BELOW_EDEFAULT;

	/**
	 * The default value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected static final String DESCRIPTION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected String description = DESCRIPTION_EDEFAULT;

	/**
	 * The default value of the '{@link #getExampleMessagePosition() <em>Example Message Position</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExampleMessagePosition()
	 * @generated
	 * @ordered
	 */
	protected static final LabelPosition EXAMPLE_MESSAGE_POSITION_EDEFAULT = LabelPosition.DOWN;

	/**
	 * The cached value of the '{@link #getExampleMessagePosition() <em>Example Message Position</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExampleMessagePosition()
	 * @generated
	 * @ordered
	 */
	protected LabelPosition exampleMessagePosition = EXAMPLE_MESSAGE_POSITION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getExampleMessage() <em>Example Message</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExampleMessage()
	 * @generated
	 * @ordered
	 */
	protected Expression exampleMessage;

	/**
	 * The default value of the '{@link #isLimitMinNumberOfColumn() <em>Limit Min Number Of Column</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isLimitMinNumberOfColumn()
	 * @generated
	 * @ordered
	 */
	protected static final boolean LIMIT_MIN_NUMBER_OF_COLUMN_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isLimitMinNumberOfColumn() <em>Limit Min Number Of Column</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isLimitMinNumberOfColumn()
	 * @generated
	 * @ordered
	 */
	protected boolean limitMinNumberOfColumn = LIMIT_MIN_NUMBER_OF_COLUMN_EDEFAULT;

	/**
	 * The default value of the '{@link #isLimitMinNumberOfRow() <em>Limit Min Number Of Row</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isLimitMinNumberOfRow()
	 * @generated
	 * @ordered
	 */
	protected static final boolean LIMIT_MIN_NUMBER_OF_ROW_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isLimitMinNumberOfRow() <em>Limit Min Number Of Row</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isLimitMinNumberOfRow()
	 * @generated
	 * @ordered
	 */
	protected boolean limitMinNumberOfRow = LIMIT_MIN_NUMBER_OF_ROW_EDEFAULT;

	/**
	 * The default value of the '{@link #isAllowAddRemoveRow() <em>Allow Add Remove Row</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAllowAddRemoveRow()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ALLOW_ADD_REMOVE_ROW_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isAllowAddRemoveRow() <em>Allow Add Remove Row</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAllowAddRemoveRow()
	 * @generated
	 * @ordered
	 */
	protected boolean allowAddRemoveRow = ALLOW_ADD_REMOVE_ROW_EDEFAULT;

	/**
	 * The default value of the '{@link #isAllowAddRemoveColumn() <em>Allow Add Remove Column</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAllowAddRemoveColumn()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ALLOW_ADD_REMOVE_COLUMN_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isAllowAddRemoveColumn() <em>Allow Add Remove Column</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAllowAddRemoveColumn()
	 * @generated
	 * @ordered
	 */
	protected boolean allowAddRemoveColumn = ALLOW_ADD_REMOVE_COLUMN_EDEFAULT;

	/**
	 * The default value of the '{@link #isLimitMaxNumberOfColumn() <em>Limit Max Number Of Column</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isLimitMaxNumberOfColumn()
	 * @generated
	 * @ordered
	 */
	protected static final boolean LIMIT_MAX_NUMBER_OF_COLUMN_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isLimitMaxNumberOfColumn() <em>Limit Max Number Of Column</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isLimitMaxNumberOfColumn()
	 * @generated
	 * @ordered
	 */
	protected boolean limitMaxNumberOfColumn = LIMIT_MAX_NUMBER_OF_COLUMN_EDEFAULT;

	/**
	 * The default value of the '{@link #isLimitMaxNumberOfRow() <em>Limit Max Number Of Row</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isLimitMaxNumberOfRow()
	 * @generated
	 * @ordered
	 */
	protected static final boolean LIMIT_MAX_NUMBER_OF_ROW_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isLimitMaxNumberOfRow() <em>Limit Max Number Of Row</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isLimitMaxNumberOfRow()
	 * @generated
	 * @ordered
	 */
	protected boolean limitMaxNumberOfRow = LIMIT_MAX_NUMBER_OF_ROW_EDEFAULT;

	/**
	 * The cached value of the '{@link #getMinNumberOfColumn() <em>Min Number Of Column</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMinNumberOfColumn()
	 * @generated
	 * @ordered
	 */
	protected Expression minNumberOfColumn;

	/**
	 * The cached value of the '{@link #getMinNumberOfRow() <em>Min Number Of Row</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMinNumberOfRow()
	 * @generated
	 * @ordered
	 */
	protected Expression minNumberOfRow;

	/**
	 * The cached value of the '{@link #getMaxNumberOfColumn() <em>Max Number Of Column</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxNumberOfColumn()
	 * @generated
	 * @ordered
	 */
	protected Expression maxNumberOfColumn;

	/**
	 * The cached value of the '{@link #getMaxNumberOfRow() <em>Max Number Of Row</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxNumberOfRow()
	 * @generated
	 * @ordered
	 */
	protected Expression maxNumberOfRow;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DynamicTableImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FormPackage.Literals.DYNAMIC_TABLE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Validator> getValidators() {
		if (validators == null) {
			validators = new EObjectContainmentEList<Validator>(Validator.class, this, FormPackage.DYNAMIC_TABLE__VALIDATORS);
		}
		return validators;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Boolean getUseDefaultValidator() {
		return useDefaultValidator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setUseDefaultValidator(Boolean newUseDefaultValidator) {
		Boolean oldUseDefaultValidator = useDefaultValidator;
		useDefaultValidator = newUseDefaultValidator;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.DYNAMIC_TABLE__USE_DEFAULT_VALIDATOR, oldUseDefaultValidator, useDefaultValidator));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isBelow() {
		return below;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setBelow(boolean newBelow) {
		boolean oldBelow = below;
		below = newBelow;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.DYNAMIC_TABLE__BELOW, oldBelow, below));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getDescription() {
		return description;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDescription(String newDescription) {
		String oldDescription = description;
		description = newDescription;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.DYNAMIC_TABLE__DESCRIPTION, oldDescription, description));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public LabelPosition getExampleMessagePosition() {
		return exampleMessagePosition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setExampleMessagePosition(LabelPosition newExampleMessagePosition) {
		LabelPosition oldExampleMessagePosition = exampleMessagePosition;
		exampleMessagePosition = newExampleMessagePosition == null ? EXAMPLE_MESSAGE_POSITION_EDEFAULT : newExampleMessagePosition;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.DYNAMIC_TABLE__EXAMPLE_MESSAGE_POSITION, oldExampleMessagePosition, exampleMessagePosition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Expression getExampleMessage() {
		return exampleMessage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetExampleMessage(Expression newExampleMessage, NotificationChain msgs) {
		Expression oldExampleMessage = exampleMessage;
		exampleMessage = newExampleMessage;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FormPackage.DYNAMIC_TABLE__EXAMPLE_MESSAGE, oldExampleMessage, newExampleMessage);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setExampleMessage(Expression newExampleMessage) {
		if (newExampleMessage != exampleMessage) {
			NotificationChain msgs = null;
			if (exampleMessage != null)
				msgs = ((InternalEObject)exampleMessage).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FormPackage.DYNAMIC_TABLE__EXAMPLE_MESSAGE, null, msgs);
			if (newExampleMessage != null)
				msgs = ((InternalEObject)newExampleMessage).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FormPackage.DYNAMIC_TABLE__EXAMPLE_MESSAGE, null, msgs);
			msgs = basicSetExampleMessage(newExampleMessage, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.DYNAMIC_TABLE__EXAMPLE_MESSAGE, newExampleMessage, newExampleMessage));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isLimitMinNumberOfColumn() {
		return limitMinNumberOfColumn;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setLimitMinNumberOfColumn(boolean newLimitMinNumberOfColumn) {
		boolean oldLimitMinNumberOfColumn = limitMinNumberOfColumn;
		limitMinNumberOfColumn = newLimitMinNumberOfColumn;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.DYNAMIC_TABLE__LIMIT_MIN_NUMBER_OF_COLUMN, oldLimitMinNumberOfColumn, limitMinNumberOfColumn));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isLimitMinNumberOfRow() {
		return limitMinNumberOfRow;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setLimitMinNumberOfRow(boolean newLimitMinNumberOfRow) {
		boolean oldLimitMinNumberOfRow = limitMinNumberOfRow;
		limitMinNumberOfRow = newLimitMinNumberOfRow;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.DYNAMIC_TABLE__LIMIT_MIN_NUMBER_OF_ROW, oldLimitMinNumberOfRow, limitMinNumberOfRow));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isAllowAddRemoveRow() {
		return allowAddRemoveRow;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setAllowAddRemoveRow(boolean newAllowAddRemoveRow) {
		boolean oldAllowAddRemoveRow = allowAddRemoveRow;
		allowAddRemoveRow = newAllowAddRemoveRow;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.DYNAMIC_TABLE__ALLOW_ADD_REMOVE_ROW, oldAllowAddRemoveRow, allowAddRemoveRow));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isAllowAddRemoveColumn() {
		return allowAddRemoveColumn;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setAllowAddRemoveColumn(boolean newAllowAddRemoveColumn) {
		boolean oldAllowAddRemoveColumn = allowAddRemoveColumn;
		allowAddRemoveColumn = newAllowAddRemoveColumn;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.DYNAMIC_TABLE__ALLOW_ADD_REMOVE_COLUMN, oldAllowAddRemoveColumn, allowAddRemoveColumn));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isLimitMaxNumberOfColumn() {
		return limitMaxNumberOfColumn;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setLimitMaxNumberOfColumn(boolean newLimitMaxNumberOfColumn) {
		boolean oldLimitMaxNumberOfColumn = limitMaxNumberOfColumn;
		limitMaxNumberOfColumn = newLimitMaxNumberOfColumn;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.DYNAMIC_TABLE__LIMIT_MAX_NUMBER_OF_COLUMN, oldLimitMaxNumberOfColumn, limitMaxNumberOfColumn));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isLimitMaxNumberOfRow() {
		return limitMaxNumberOfRow;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setLimitMaxNumberOfRow(boolean newLimitMaxNumberOfRow) {
		boolean oldLimitMaxNumberOfRow = limitMaxNumberOfRow;
		limitMaxNumberOfRow = newLimitMaxNumberOfRow;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.DYNAMIC_TABLE__LIMIT_MAX_NUMBER_OF_ROW, oldLimitMaxNumberOfRow, limitMaxNumberOfRow));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Expression getMinNumberOfColumn() {
		return minNumberOfColumn;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetMinNumberOfColumn(Expression newMinNumberOfColumn, NotificationChain msgs) {
		Expression oldMinNumberOfColumn = minNumberOfColumn;
		minNumberOfColumn = newMinNumberOfColumn;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FormPackage.DYNAMIC_TABLE__MIN_NUMBER_OF_COLUMN, oldMinNumberOfColumn, newMinNumberOfColumn);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setMinNumberOfColumn(Expression newMinNumberOfColumn) {
		if (newMinNumberOfColumn != minNumberOfColumn) {
			NotificationChain msgs = null;
			if (minNumberOfColumn != null)
				msgs = ((InternalEObject)minNumberOfColumn).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FormPackage.DYNAMIC_TABLE__MIN_NUMBER_OF_COLUMN, null, msgs);
			if (newMinNumberOfColumn != null)
				msgs = ((InternalEObject)newMinNumberOfColumn).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FormPackage.DYNAMIC_TABLE__MIN_NUMBER_OF_COLUMN, null, msgs);
			msgs = basicSetMinNumberOfColumn(newMinNumberOfColumn, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.DYNAMIC_TABLE__MIN_NUMBER_OF_COLUMN, newMinNumberOfColumn, newMinNumberOfColumn));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Expression getMinNumberOfRow() {
		return minNumberOfRow;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetMinNumberOfRow(Expression newMinNumberOfRow, NotificationChain msgs) {
		Expression oldMinNumberOfRow = minNumberOfRow;
		minNumberOfRow = newMinNumberOfRow;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FormPackage.DYNAMIC_TABLE__MIN_NUMBER_OF_ROW, oldMinNumberOfRow, newMinNumberOfRow);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setMinNumberOfRow(Expression newMinNumberOfRow) {
		if (newMinNumberOfRow != minNumberOfRow) {
			NotificationChain msgs = null;
			if (minNumberOfRow != null)
				msgs = ((InternalEObject)minNumberOfRow).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FormPackage.DYNAMIC_TABLE__MIN_NUMBER_OF_ROW, null, msgs);
			if (newMinNumberOfRow != null)
				msgs = ((InternalEObject)newMinNumberOfRow).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FormPackage.DYNAMIC_TABLE__MIN_NUMBER_OF_ROW, null, msgs);
			msgs = basicSetMinNumberOfRow(newMinNumberOfRow, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.DYNAMIC_TABLE__MIN_NUMBER_OF_ROW, newMinNumberOfRow, newMinNumberOfRow));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Expression getMaxNumberOfColumn() {
		return maxNumberOfColumn;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetMaxNumberOfColumn(Expression newMaxNumberOfColumn, NotificationChain msgs) {
		Expression oldMaxNumberOfColumn = maxNumberOfColumn;
		maxNumberOfColumn = newMaxNumberOfColumn;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FormPackage.DYNAMIC_TABLE__MAX_NUMBER_OF_COLUMN, oldMaxNumberOfColumn, newMaxNumberOfColumn);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setMaxNumberOfColumn(Expression newMaxNumberOfColumn) {
		if (newMaxNumberOfColumn != maxNumberOfColumn) {
			NotificationChain msgs = null;
			if (maxNumberOfColumn != null)
				msgs = ((InternalEObject)maxNumberOfColumn).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FormPackage.DYNAMIC_TABLE__MAX_NUMBER_OF_COLUMN, null, msgs);
			if (newMaxNumberOfColumn != null)
				msgs = ((InternalEObject)newMaxNumberOfColumn).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FormPackage.DYNAMIC_TABLE__MAX_NUMBER_OF_COLUMN, null, msgs);
			msgs = basicSetMaxNumberOfColumn(newMaxNumberOfColumn, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.DYNAMIC_TABLE__MAX_NUMBER_OF_COLUMN, newMaxNumberOfColumn, newMaxNumberOfColumn));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Expression getMaxNumberOfRow() {
		return maxNumberOfRow;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetMaxNumberOfRow(Expression newMaxNumberOfRow, NotificationChain msgs) {
		Expression oldMaxNumberOfRow = maxNumberOfRow;
		maxNumberOfRow = newMaxNumberOfRow;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FormPackage.DYNAMIC_TABLE__MAX_NUMBER_OF_ROW, oldMaxNumberOfRow, newMaxNumberOfRow);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setMaxNumberOfRow(Expression newMaxNumberOfRow) {
		if (newMaxNumberOfRow != maxNumberOfRow) {
			NotificationChain msgs = null;
			if (maxNumberOfRow != null)
				msgs = ((InternalEObject)maxNumberOfRow).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FormPackage.DYNAMIC_TABLE__MAX_NUMBER_OF_ROW, null, msgs);
			if (newMaxNumberOfRow != null)
				msgs = ((InternalEObject)newMaxNumberOfRow).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FormPackage.DYNAMIC_TABLE__MAX_NUMBER_OF_ROW, null, msgs);
			msgs = basicSetMaxNumberOfRow(newMaxNumberOfRow, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.DYNAMIC_TABLE__MAX_NUMBER_OF_ROW, newMaxNumberOfRow, newMaxNumberOfRow));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case FormPackage.DYNAMIC_TABLE__VALIDATORS:
				return ((InternalEList<?>)getValidators()).basicRemove(otherEnd, msgs);
			case FormPackage.DYNAMIC_TABLE__EXAMPLE_MESSAGE:
				return basicSetExampleMessage(null, msgs);
			case FormPackage.DYNAMIC_TABLE__MIN_NUMBER_OF_COLUMN:
				return basicSetMinNumberOfColumn(null, msgs);
			case FormPackage.DYNAMIC_TABLE__MIN_NUMBER_OF_ROW:
				return basicSetMinNumberOfRow(null, msgs);
			case FormPackage.DYNAMIC_TABLE__MAX_NUMBER_OF_COLUMN:
				return basicSetMaxNumberOfColumn(null, msgs);
			case FormPackage.DYNAMIC_TABLE__MAX_NUMBER_OF_ROW:
				return basicSetMaxNumberOfRow(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case FormPackage.DYNAMIC_TABLE__VALIDATORS:
				return getValidators();
			case FormPackage.DYNAMIC_TABLE__USE_DEFAULT_VALIDATOR:
				return getUseDefaultValidator();
			case FormPackage.DYNAMIC_TABLE__BELOW:
				return isBelow();
			case FormPackage.DYNAMIC_TABLE__DESCRIPTION:
				return getDescription();
			case FormPackage.DYNAMIC_TABLE__EXAMPLE_MESSAGE_POSITION:
				return getExampleMessagePosition();
			case FormPackage.DYNAMIC_TABLE__EXAMPLE_MESSAGE:
				return getExampleMessage();
			case FormPackage.DYNAMIC_TABLE__LIMIT_MIN_NUMBER_OF_COLUMN:
				return isLimitMinNumberOfColumn();
			case FormPackage.DYNAMIC_TABLE__LIMIT_MIN_NUMBER_OF_ROW:
				return isLimitMinNumberOfRow();
			case FormPackage.DYNAMIC_TABLE__ALLOW_ADD_REMOVE_ROW:
				return isAllowAddRemoveRow();
			case FormPackage.DYNAMIC_TABLE__ALLOW_ADD_REMOVE_COLUMN:
				return isAllowAddRemoveColumn();
			case FormPackage.DYNAMIC_TABLE__LIMIT_MAX_NUMBER_OF_COLUMN:
				return isLimitMaxNumberOfColumn();
			case FormPackage.DYNAMIC_TABLE__LIMIT_MAX_NUMBER_OF_ROW:
				return isLimitMaxNumberOfRow();
			case FormPackage.DYNAMIC_TABLE__MIN_NUMBER_OF_COLUMN:
				return getMinNumberOfColumn();
			case FormPackage.DYNAMIC_TABLE__MIN_NUMBER_OF_ROW:
				return getMinNumberOfRow();
			case FormPackage.DYNAMIC_TABLE__MAX_NUMBER_OF_COLUMN:
				return getMaxNumberOfColumn();
			case FormPackage.DYNAMIC_TABLE__MAX_NUMBER_OF_ROW:
				return getMaxNumberOfRow();
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
			case FormPackage.DYNAMIC_TABLE__VALIDATORS:
				getValidators().clear();
				getValidators().addAll((Collection<? extends Validator>)newValue);
				return;
			case FormPackage.DYNAMIC_TABLE__USE_DEFAULT_VALIDATOR:
				setUseDefaultValidator((Boolean)newValue);
				return;
			case FormPackage.DYNAMIC_TABLE__BELOW:
				setBelow((Boolean)newValue);
				return;
			case FormPackage.DYNAMIC_TABLE__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case FormPackage.DYNAMIC_TABLE__EXAMPLE_MESSAGE_POSITION:
				setExampleMessagePosition((LabelPosition)newValue);
				return;
			case FormPackage.DYNAMIC_TABLE__EXAMPLE_MESSAGE:
				setExampleMessage((Expression)newValue);
				return;
			case FormPackage.DYNAMIC_TABLE__LIMIT_MIN_NUMBER_OF_COLUMN:
				setLimitMinNumberOfColumn((Boolean)newValue);
				return;
			case FormPackage.DYNAMIC_TABLE__LIMIT_MIN_NUMBER_OF_ROW:
				setLimitMinNumberOfRow((Boolean)newValue);
				return;
			case FormPackage.DYNAMIC_TABLE__ALLOW_ADD_REMOVE_ROW:
				setAllowAddRemoveRow((Boolean)newValue);
				return;
			case FormPackage.DYNAMIC_TABLE__ALLOW_ADD_REMOVE_COLUMN:
				setAllowAddRemoveColumn((Boolean)newValue);
				return;
			case FormPackage.DYNAMIC_TABLE__LIMIT_MAX_NUMBER_OF_COLUMN:
				setLimitMaxNumberOfColumn((Boolean)newValue);
				return;
			case FormPackage.DYNAMIC_TABLE__LIMIT_MAX_NUMBER_OF_ROW:
				setLimitMaxNumberOfRow((Boolean)newValue);
				return;
			case FormPackage.DYNAMIC_TABLE__MIN_NUMBER_OF_COLUMN:
				setMinNumberOfColumn((Expression)newValue);
				return;
			case FormPackage.DYNAMIC_TABLE__MIN_NUMBER_OF_ROW:
				setMinNumberOfRow((Expression)newValue);
				return;
			case FormPackage.DYNAMIC_TABLE__MAX_NUMBER_OF_COLUMN:
				setMaxNumberOfColumn((Expression)newValue);
				return;
			case FormPackage.DYNAMIC_TABLE__MAX_NUMBER_OF_ROW:
				setMaxNumberOfRow((Expression)newValue);
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
			case FormPackage.DYNAMIC_TABLE__VALIDATORS:
				getValidators().clear();
				return;
			case FormPackage.DYNAMIC_TABLE__USE_DEFAULT_VALIDATOR:
				setUseDefaultValidator(USE_DEFAULT_VALIDATOR_EDEFAULT);
				return;
			case FormPackage.DYNAMIC_TABLE__BELOW:
				setBelow(BELOW_EDEFAULT);
				return;
			case FormPackage.DYNAMIC_TABLE__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case FormPackage.DYNAMIC_TABLE__EXAMPLE_MESSAGE_POSITION:
				setExampleMessagePosition(EXAMPLE_MESSAGE_POSITION_EDEFAULT);
				return;
			case FormPackage.DYNAMIC_TABLE__EXAMPLE_MESSAGE:
				setExampleMessage((Expression)null);
				return;
			case FormPackage.DYNAMIC_TABLE__LIMIT_MIN_NUMBER_OF_COLUMN:
				setLimitMinNumberOfColumn(LIMIT_MIN_NUMBER_OF_COLUMN_EDEFAULT);
				return;
			case FormPackage.DYNAMIC_TABLE__LIMIT_MIN_NUMBER_OF_ROW:
				setLimitMinNumberOfRow(LIMIT_MIN_NUMBER_OF_ROW_EDEFAULT);
				return;
			case FormPackage.DYNAMIC_TABLE__ALLOW_ADD_REMOVE_ROW:
				setAllowAddRemoveRow(ALLOW_ADD_REMOVE_ROW_EDEFAULT);
				return;
			case FormPackage.DYNAMIC_TABLE__ALLOW_ADD_REMOVE_COLUMN:
				setAllowAddRemoveColumn(ALLOW_ADD_REMOVE_COLUMN_EDEFAULT);
				return;
			case FormPackage.DYNAMIC_TABLE__LIMIT_MAX_NUMBER_OF_COLUMN:
				setLimitMaxNumberOfColumn(LIMIT_MAX_NUMBER_OF_COLUMN_EDEFAULT);
				return;
			case FormPackage.DYNAMIC_TABLE__LIMIT_MAX_NUMBER_OF_ROW:
				setLimitMaxNumberOfRow(LIMIT_MAX_NUMBER_OF_ROW_EDEFAULT);
				return;
			case FormPackage.DYNAMIC_TABLE__MIN_NUMBER_OF_COLUMN:
				setMinNumberOfColumn((Expression)null);
				return;
			case FormPackage.DYNAMIC_TABLE__MIN_NUMBER_OF_ROW:
				setMinNumberOfRow((Expression)null);
				return;
			case FormPackage.DYNAMIC_TABLE__MAX_NUMBER_OF_COLUMN:
				setMaxNumberOfColumn((Expression)null);
				return;
			case FormPackage.DYNAMIC_TABLE__MAX_NUMBER_OF_ROW:
				setMaxNumberOfRow((Expression)null);
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
			case FormPackage.DYNAMIC_TABLE__VALIDATORS:
				return validators != null && !validators.isEmpty();
			case FormPackage.DYNAMIC_TABLE__USE_DEFAULT_VALIDATOR:
				return USE_DEFAULT_VALIDATOR_EDEFAULT == null ? useDefaultValidator != null : !USE_DEFAULT_VALIDATOR_EDEFAULT.equals(useDefaultValidator);
			case FormPackage.DYNAMIC_TABLE__BELOW:
				return below != BELOW_EDEFAULT;
			case FormPackage.DYNAMIC_TABLE__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case FormPackage.DYNAMIC_TABLE__EXAMPLE_MESSAGE_POSITION:
				return exampleMessagePosition != EXAMPLE_MESSAGE_POSITION_EDEFAULT;
			case FormPackage.DYNAMIC_TABLE__EXAMPLE_MESSAGE:
				return exampleMessage != null;
			case FormPackage.DYNAMIC_TABLE__LIMIT_MIN_NUMBER_OF_COLUMN:
				return limitMinNumberOfColumn != LIMIT_MIN_NUMBER_OF_COLUMN_EDEFAULT;
			case FormPackage.DYNAMIC_TABLE__LIMIT_MIN_NUMBER_OF_ROW:
				return limitMinNumberOfRow != LIMIT_MIN_NUMBER_OF_ROW_EDEFAULT;
			case FormPackage.DYNAMIC_TABLE__ALLOW_ADD_REMOVE_ROW:
				return allowAddRemoveRow != ALLOW_ADD_REMOVE_ROW_EDEFAULT;
			case FormPackage.DYNAMIC_TABLE__ALLOW_ADD_REMOVE_COLUMN:
				return allowAddRemoveColumn != ALLOW_ADD_REMOVE_COLUMN_EDEFAULT;
			case FormPackage.DYNAMIC_TABLE__LIMIT_MAX_NUMBER_OF_COLUMN:
				return limitMaxNumberOfColumn != LIMIT_MAX_NUMBER_OF_COLUMN_EDEFAULT;
			case FormPackage.DYNAMIC_TABLE__LIMIT_MAX_NUMBER_OF_ROW:
				return limitMaxNumberOfRow != LIMIT_MAX_NUMBER_OF_ROW_EDEFAULT;
			case FormPackage.DYNAMIC_TABLE__MIN_NUMBER_OF_COLUMN:
				return minNumberOfColumn != null;
			case FormPackage.DYNAMIC_TABLE__MIN_NUMBER_OF_ROW:
				return minNumberOfRow != null;
			case FormPackage.DYNAMIC_TABLE__MAX_NUMBER_OF_COLUMN:
				return maxNumberOfColumn != null;
			case FormPackage.DYNAMIC_TABLE__MAX_NUMBER_OF_ROW:
				return maxNumberOfRow != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == Validable.class) {
			switch (derivedFeatureID) {
				case FormPackage.DYNAMIC_TABLE__VALIDATORS: return FormPackage.VALIDABLE__VALIDATORS;
				case FormPackage.DYNAMIC_TABLE__USE_DEFAULT_VALIDATOR: return FormPackage.VALIDABLE__USE_DEFAULT_VALIDATOR;
				case FormPackage.DYNAMIC_TABLE__BELOW: return FormPackage.VALIDABLE__BELOW;
				default: return -1;
			}
		}
		if (baseClass == FormField.class) {
			switch (derivedFeatureID) {
				case FormPackage.DYNAMIC_TABLE__DESCRIPTION: return FormPackage.FORM_FIELD__DESCRIPTION;
				case FormPackage.DYNAMIC_TABLE__EXAMPLE_MESSAGE_POSITION: return FormPackage.FORM_FIELD__EXAMPLE_MESSAGE_POSITION;
				case FormPackage.DYNAMIC_TABLE__EXAMPLE_MESSAGE: return FormPackage.FORM_FIELD__EXAMPLE_MESSAGE;
				default: return -1;
			}
		}
		if (baseClass == SingleValuatedFormField.class) {
			switch (derivedFeatureID) {
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == Validable.class) {
			switch (baseFeatureID) {
				case FormPackage.VALIDABLE__VALIDATORS: return FormPackage.DYNAMIC_TABLE__VALIDATORS;
				case FormPackage.VALIDABLE__USE_DEFAULT_VALIDATOR: return FormPackage.DYNAMIC_TABLE__USE_DEFAULT_VALIDATOR;
				case FormPackage.VALIDABLE__BELOW: return FormPackage.DYNAMIC_TABLE__BELOW;
				default: return -1;
			}
		}
		if (baseClass == FormField.class) {
			switch (baseFeatureID) {
				case FormPackage.FORM_FIELD__DESCRIPTION: return FormPackage.DYNAMIC_TABLE__DESCRIPTION;
				case FormPackage.FORM_FIELD__EXAMPLE_MESSAGE_POSITION: return FormPackage.DYNAMIC_TABLE__EXAMPLE_MESSAGE_POSITION;
				case FormPackage.FORM_FIELD__EXAMPLE_MESSAGE: return FormPackage.DYNAMIC_TABLE__EXAMPLE_MESSAGE;
				default: return -1;
			}
		}
		if (baseClass == SingleValuatedFormField.class) {
			switch (baseFeatureID) {
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (useDefaultValidator: "); //$NON-NLS-1$
		result.append(useDefaultValidator);
		result.append(", below: "); //$NON-NLS-1$
		result.append(below);
		result.append(", description: "); //$NON-NLS-1$
		result.append(description);
		result.append(", exampleMessagePosition: "); //$NON-NLS-1$
		result.append(exampleMessagePosition);
		result.append(", limitMinNumberOfColumn: "); //$NON-NLS-1$
		result.append(limitMinNumberOfColumn);
		result.append(", limitMinNumberOfRow: "); //$NON-NLS-1$
		result.append(limitMinNumberOfRow);
		result.append(", allowAddRemoveRow: "); //$NON-NLS-1$
		result.append(allowAddRemoveRow);
		result.append(", allowAddRemoveColumn: "); //$NON-NLS-1$
		result.append(allowAddRemoveColumn);
		result.append(", limitMaxNumberOfColumn: "); //$NON-NLS-1$
		result.append(limitMaxNumberOfColumn);
		result.append(", limitMaxNumberOfRow: "); //$NON-NLS-1$
		result.append(limitMaxNumberOfRow);
		result.append(')');
		return result.toString();
	}

} //DynamicTableImpl
