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

import org.bonitasoft.studio.model.form.FormField;
import org.bonitasoft.studio.model.form.FormPackage;
import org.bonitasoft.studio.model.form.LabelPosition;
import org.bonitasoft.studio.model.form.MultipleValuatedFormField;
import org.bonitasoft.studio.model.form.Table;
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
 * An implementation of the model object '<em><b>Table</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.TableImpl#getValidators <em>Validators</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.TableImpl#getUseDefaultValidator <em>Use Default Validator</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.TableImpl#isBelow <em>Below</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.TableImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.TableImpl#getExampleMessagePosition <em>Example Message Position</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.TableImpl#getExampleMessage <em>Example Message</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.TableImpl#getDefaultExpression <em>Default Expression</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.TableImpl#getDefaultExpressionAfterEvent <em>Default Expression After Event</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.TableImpl#isUsePagination <em>Use Pagination</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.TableImpl#isAllowSelection <em>Allow Selection</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.TableImpl#isSelectionModeIsMultiple <em>Selection Mode Is Multiple</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.TableImpl#getMaxRowForPagination <em>Max Row For Pagination</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.TableImpl#getColumnForInitialSelectionIndex <em>Column For Initial Selection Index</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.TableImpl#getSelectedValues <em>Selected Values</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TableImpl extends AbstractTableImpl implements Table {
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
     * The cached value of the '{@link #getDefaultExpression() <em>Default Expression</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getDefaultExpression()
     * @generated
     * @ordered
     */
	protected Expression defaultExpression;

	/**
     * The cached value of the '{@link #getDefaultExpressionAfterEvent() <em>Default Expression After Event</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getDefaultExpressionAfterEvent()
     * @generated
     * @ordered
     */
	protected Expression defaultExpressionAfterEvent;

	/**
     * The default value of the '{@link #isUsePagination() <em>Use Pagination</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isUsePagination()
     * @generated
     * @ordered
     */
	protected static final boolean USE_PAGINATION_EDEFAULT = false;

	/**
     * The cached value of the '{@link #isUsePagination() <em>Use Pagination</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isUsePagination()
     * @generated
     * @ordered
     */
	protected boolean usePagination = USE_PAGINATION_EDEFAULT;

	/**
     * The default value of the '{@link #isAllowSelection() <em>Allow Selection</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isAllowSelection()
     * @generated
     * @ordered
     */
	protected static final boolean ALLOW_SELECTION_EDEFAULT = false;

	/**
     * The cached value of the '{@link #isAllowSelection() <em>Allow Selection</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isAllowSelection()
     * @generated
     * @ordered
     */
	protected boolean allowSelection = ALLOW_SELECTION_EDEFAULT;

	/**
     * The default value of the '{@link #isSelectionModeIsMultiple() <em>Selection Mode Is Multiple</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isSelectionModeIsMultiple()
     * @generated
     * @ordered
     */
	protected static final boolean SELECTION_MODE_IS_MULTIPLE_EDEFAULT = true;

	/**
     * The cached value of the '{@link #isSelectionModeIsMultiple() <em>Selection Mode Is Multiple</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isSelectionModeIsMultiple()
     * @generated
     * @ordered
     */
	protected boolean selectionModeIsMultiple = SELECTION_MODE_IS_MULTIPLE_EDEFAULT;

	/**
     * The cached value of the '{@link #getMaxRowForPagination() <em>Max Row For Pagination</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getMaxRowForPagination()
     * @generated
     * @ordered
     */
	protected Expression maxRowForPagination;

	/**
     * The cached value of the '{@link #getColumnForInitialSelectionIndex() <em>Column For Initial Selection Index</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getColumnForInitialSelectionIndex()
     * @generated
     * @ordered
     */
	protected Expression columnForInitialSelectionIndex;

	/**
     * The cached value of the '{@link #getSelectedValues() <em>Selected Values</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getSelectedValues()
     * @generated
     * @ordered
     */
	protected Expression selectedValues;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected TableImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return FormPackage.Literals.TABLE;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EList<Validator> getValidators() {
        if (validators == null) {
            validators = new EObjectContainmentEList<Validator>(Validator.class, this, FormPackage.TABLE__VALIDATORS);
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
            eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.TABLE__USE_DEFAULT_VALIDATOR, oldUseDefaultValidator, useDefaultValidator));
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
            eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.TABLE__BELOW, oldBelow, below));
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
            eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.TABLE__DESCRIPTION, oldDescription, description));
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
            eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.TABLE__EXAMPLE_MESSAGE_POSITION, oldExampleMessagePosition, exampleMessagePosition));
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FormPackage.TABLE__EXAMPLE_MESSAGE, oldExampleMessage, newExampleMessage);
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
                msgs = ((InternalEObject)exampleMessage).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FormPackage.TABLE__EXAMPLE_MESSAGE, null, msgs);
            if (newExampleMessage != null)
                msgs = ((InternalEObject)newExampleMessage).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FormPackage.TABLE__EXAMPLE_MESSAGE, null, msgs);
            msgs = basicSetExampleMessage(newExampleMessage, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.TABLE__EXAMPLE_MESSAGE, newExampleMessage, newExampleMessage));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Expression getDefaultExpression() {
        return defaultExpression;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetDefaultExpression(Expression newDefaultExpression, NotificationChain msgs) {
        Expression oldDefaultExpression = defaultExpression;
        defaultExpression = newDefaultExpression;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FormPackage.TABLE__DEFAULT_EXPRESSION, oldDefaultExpression, newDefaultExpression);
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
	public void setDefaultExpression(Expression newDefaultExpression) {
        if (newDefaultExpression != defaultExpression) {
            NotificationChain msgs = null;
            if (defaultExpression != null)
                msgs = ((InternalEObject)defaultExpression).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FormPackage.TABLE__DEFAULT_EXPRESSION, null, msgs);
            if (newDefaultExpression != null)
                msgs = ((InternalEObject)newDefaultExpression).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FormPackage.TABLE__DEFAULT_EXPRESSION, null, msgs);
            msgs = basicSetDefaultExpression(newDefaultExpression, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.TABLE__DEFAULT_EXPRESSION, newDefaultExpression, newDefaultExpression));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Expression getDefaultExpressionAfterEvent() {
        return defaultExpressionAfterEvent;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetDefaultExpressionAfterEvent(Expression newDefaultExpressionAfterEvent, NotificationChain msgs) {
        Expression oldDefaultExpressionAfterEvent = defaultExpressionAfterEvent;
        defaultExpressionAfterEvent = newDefaultExpressionAfterEvent;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FormPackage.TABLE__DEFAULT_EXPRESSION_AFTER_EVENT, oldDefaultExpressionAfterEvent, newDefaultExpressionAfterEvent);
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
	public void setDefaultExpressionAfterEvent(Expression newDefaultExpressionAfterEvent) {
        if (newDefaultExpressionAfterEvent != defaultExpressionAfterEvent) {
            NotificationChain msgs = null;
            if (defaultExpressionAfterEvent != null)
                msgs = ((InternalEObject)defaultExpressionAfterEvent).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FormPackage.TABLE__DEFAULT_EXPRESSION_AFTER_EVENT, null, msgs);
            if (newDefaultExpressionAfterEvent != null)
                msgs = ((InternalEObject)newDefaultExpressionAfterEvent).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FormPackage.TABLE__DEFAULT_EXPRESSION_AFTER_EVENT, null, msgs);
            msgs = basicSetDefaultExpressionAfterEvent(newDefaultExpressionAfterEvent, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.TABLE__DEFAULT_EXPRESSION_AFTER_EVENT, newDefaultExpressionAfterEvent, newDefaultExpressionAfterEvent));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public boolean isUsePagination() {
        return usePagination;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setUsePagination(boolean newUsePagination) {
        boolean oldUsePagination = usePagination;
        usePagination = newUsePagination;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.TABLE__USE_PAGINATION, oldUsePagination, usePagination));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public boolean isAllowSelection() {
        return allowSelection;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setAllowSelection(boolean newAllowSelection) {
        boolean oldAllowSelection = allowSelection;
        allowSelection = newAllowSelection;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.TABLE__ALLOW_SELECTION, oldAllowSelection, allowSelection));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public boolean isSelectionModeIsMultiple() {
        return selectionModeIsMultiple;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setSelectionModeIsMultiple(boolean newSelectionModeIsMultiple) {
        boolean oldSelectionModeIsMultiple = selectionModeIsMultiple;
        selectionModeIsMultiple = newSelectionModeIsMultiple;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.TABLE__SELECTION_MODE_IS_MULTIPLE, oldSelectionModeIsMultiple, selectionModeIsMultiple));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Expression getMaxRowForPagination() {
        return maxRowForPagination;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetMaxRowForPagination(Expression newMaxRowForPagination, NotificationChain msgs) {
        Expression oldMaxRowForPagination = maxRowForPagination;
        maxRowForPagination = newMaxRowForPagination;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FormPackage.TABLE__MAX_ROW_FOR_PAGINATION, oldMaxRowForPagination, newMaxRowForPagination);
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
	public void setMaxRowForPagination(Expression newMaxRowForPagination) {
        if (newMaxRowForPagination != maxRowForPagination) {
            NotificationChain msgs = null;
            if (maxRowForPagination != null)
                msgs = ((InternalEObject)maxRowForPagination).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FormPackage.TABLE__MAX_ROW_FOR_PAGINATION, null, msgs);
            if (newMaxRowForPagination != null)
                msgs = ((InternalEObject)newMaxRowForPagination).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FormPackage.TABLE__MAX_ROW_FOR_PAGINATION, null, msgs);
            msgs = basicSetMaxRowForPagination(newMaxRowForPagination, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.TABLE__MAX_ROW_FOR_PAGINATION, newMaxRowForPagination, newMaxRowForPagination));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Expression getColumnForInitialSelectionIndex() {
        return columnForInitialSelectionIndex;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetColumnForInitialSelectionIndex(Expression newColumnForInitialSelectionIndex, NotificationChain msgs) {
        Expression oldColumnForInitialSelectionIndex = columnForInitialSelectionIndex;
        columnForInitialSelectionIndex = newColumnForInitialSelectionIndex;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FormPackage.TABLE__COLUMN_FOR_INITIAL_SELECTION_INDEX, oldColumnForInitialSelectionIndex, newColumnForInitialSelectionIndex);
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
	public void setColumnForInitialSelectionIndex(Expression newColumnForInitialSelectionIndex) {
        if (newColumnForInitialSelectionIndex != columnForInitialSelectionIndex) {
            NotificationChain msgs = null;
            if (columnForInitialSelectionIndex != null)
                msgs = ((InternalEObject)columnForInitialSelectionIndex).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FormPackage.TABLE__COLUMN_FOR_INITIAL_SELECTION_INDEX, null, msgs);
            if (newColumnForInitialSelectionIndex != null)
                msgs = ((InternalEObject)newColumnForInitialSelectionIndex).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FormPackage.TABLE__COLUMN_FOR_INITIAL_SELECTION_INDEX, null, msgs);
            msgs = basicSetColumnForInitialSelectionIndex(newColumnForInitialSelectionIndex, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.TABLE__COLUMN_FOR_INITIAL_SELECTION_INDEX, newColumnForInitialSelectionIndex, newColumnForInitialSelectionIndex));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Expression getSelectedValues() {
        return selectedValues;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetSelectedValues(Expression newSelectedValues, NotificationChain msgs) {
        Expression oldSelectedValues = selectedValues;
        selectedValues = newSelectedValues;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FormPackage.TABLE__SELECTED_VALUES, oldSelectedValues, newSelectedValues);
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
	public void setSelectedValues(Expression newSelectedValues) {
        if (newSelectedValues != selectedValues) {
            NotificationChain msgs = null;
            if (selectedValues != null)
                msgs = ((InternalEObject)selectedValues).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FormPackage.TABLE__SELECTED_VALUES, null, msgs);
            if (newSelectedValues != null)
                msgs = ((InternalEObject)newSelectedValues).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FormPackage.TABLE__SELECTED_VALUES, null, msgs);
            msgs = basicSetSelectedValues(newSelectedValues, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.TABLE__SELECTED_VALUES, newSelectedValues, newSelectedValues));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case FormPackage.TABLE__VALIDATORS:
                return ((InternalEList<?>)getValidators()).basicRemove(otherEnd, msgs);
            case FormPackage.TABLE__EXAMPLE_MESSAGE:
                return basicSetExampleMessage(null, msgs);
            case FormPackage.TABLE__DEFAULT_EXPRESSION:
                return basicSetDefaultExpression(null, msgs);
            case FormPackage.TABLE__DEFAULT_EXPRESSION_AFTER_EVENT:
                return basicSetDefaultExpressionAfterEvent(null, msgs);
            case FormPackage.TABLE__MAX_ROW_FOR_PAGINATION:
                return basicSetMaxRowForPagination(null, msgs);
            case FormPackage.TABLE__COLUMN_FOR_INITIAL_SELECTION_INDEX:
                return basicSetColumnForInitialSelectionIndex(null, msgs);
            case FormPackage.TABLE__SELECTED_VALUES:
                return basicSetSelectedValues(null, msgs);
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
            case FormPackage.TABLE__VALIDATORS:
                return getValidators();
            case FormPackage.TABLE__USE_DEFAULT_VALIDATOR:
                return getUseDefaultValidator();
            case FormPackage.TABLE__BELOW:
                return isBelow();
            case FormPackage.TABLE__DESCRIPTION:
                return getDescription();
            case FormPackage.TABLE__EXAMPLE_MESSAGE_POSITION:
                return getExampleMessagePosition();
            case FormPackage.TABLE__EXAMPLE_MESSAGE:
                return getExampleMessage();
            case FormPackage.TABLE__DEFAULT_EXPRESSION:
                return getDefaultExpression();
            case FormPackage.TABLE__DEFAULT_EXPRESSION_AFTER_EVENT:
                return getDefaultExpressionAfterEvent();
            case FormPackage.TABLE__USE_PAGINATION:
                return isUsePagination();
            case FormPackage.TABLE__ALLOW_SELECTION:
                return isAllowSelection();
            case FormPackage.TABLE__SELECTION_MODE_IS_MULTIPLE:
                return isSelectionModeIsMultiple();
            case FormPackage.TABLE__MAX_ROW_FOR_PAGINATION:
                return getMaxRowForPagination();
            case FormPackage.TABLE__COLUMN_FOR_INITIAL_SELECTION_INDEX:
                return getColumnForInitialSelectionIndex();
            case FormPackage.TABLE__SELECTED_VALUES:
                return getSelectedValues();
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
            case FormPackage.TABLE__VALIDATORS:
                getValidators().clear();
                getValidators().addAll((Collection<? extends Validator>)newValue);
                return;
            case FormPackage.TABLE__USE_DEFAULT_VALIDATOR:
                setUseDefaultValidator((Boolean)newValue);
                return;
            case FormPackage.TABLE__BELOW:
                setBelow((Boolean)newValue);
                return;
            case FormPackage.TABLE__DESCRIPTION:
                setDescription((String)newValue);
                return;
            case FormPackage.TABLE__EXAMPLE_MESSAGE_POSITION:
                setExampleMessagePosition((LabelPosition)newValue);
                return;
            case FormPackage.TABLE__EXAMPLE_MESSAGE:
                setExampleMessage((Expression)newValue);
                return;
            case FormPackage.TABLE__DEFAULT_EXPRESSION:
                setDefaultExpression((Expression)newValue);
                return;
            case FormPackage.TABLE__DEFAULT_EXPRESSION_AFTER_EVENT:
                setDefaultExpressionAfterEvent((Expression)newValue);
                return;
            case FormPackage.TABLE__USE_PAGINATION:
                setUsePagination((Boolean)newValue);
                return;
            case FormPackage.TABLE__ALLOW_SELECTION:
                setAllowSelection((Boolean)newValue);
                return;
            case FormPackage.TABLE__SELECTION_MODE_IS_MULTIPLE:
                setSelectionModeIsMultiple((Boolean)newValue);
                return;
            case FormPackage.TABLE__MAX_ROW_FOR_PAGINATION:
                setMaxRowForPagination((Expression)newValue);
                return;
            case FormPackage.TABLE__COLUMN_FOR_INITIAL_SELECTION_INDEX:
                setColumnForInitialSelectionIndex((Expression)newValue);
                return;
            case FormPackage.TABLE__SELECTED_VALUES:
                setSelectedValues((Expression)newValue);
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
            case FormPackage.TABLE__VALIDATORS:
                getValidators().clear();
                return;
            case FormPackage.TABLE__USE_DEFAULT_VALIDATOR:
                setUseDefaultValidator(USE_DEFAULT_VALIDATOR_EDEFAULT);
                return;
            case FormPackage.TABLE__BELOW:
                setBelow(BELOW_EDEFAULT);
                return;
            case FormPackage.TABLE__DESCRIPTION:
                setDescription(DESCRIPTION_EDEFAULT);
                return;
            case FormPackage.TABLE__EXAMPLE_MESSAGE_POSITION:
                setExampleMessagePosition(EXAMPLE_MESSAGE_POSITION_EDEFAULT);
                return;
            case FormPackage.TABLE__EXAMPLE_MESSAGE:
                setExampleMessage((Expression)null);
                return;
            case FormPackage.TABLE__DEFAULT_EXPRESSION:
                setDefaultExpression((Expression)null);
                return;
            case FormPackage.TABLE__DEFAULT_EXPRESSION_AFTER_EVENT:
                setDefaultExpressionAfterEvent((Expression)null);
                return;
            case FormPackage.TABLE__USE_PAGINATION:
                setUsePagination(USE_PAGINATION_EDEFAULT);
                return;
            case FormPackage.TABLE__ALLOW_SELECTION:
                setAllowSelection(ALLOW_SELECTION_EDEFAULT);
                return;
            case FormPackage.TABLE__SELECTION_MODE_IS_MULTIPLE:
                setSelectionModeIsMultiple(SELECTION_MODE_IS_MULTIPLE_EDEFAULT);
                return;
            case FormPackage.TABLE__MAX_ROW_FOR_PAGINATION:
                setMaxRowForPagination((Expression)null);
                return;
            case FormPackage.TABLE__COLUMN_FOR_INITIAL_SELECTION_INDEX:
                setColumnForInitialSelectionIndex((Expression)null);
                return;
            case FormPackage.TABLE__SELECTED_VALUES:
                setSelectedValues((Expression)null);
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
            case FormPackage.TABLE__VALIDATORS:
                return validators != null && !validators.isEmpty();
            case FormPackage.TABLE__USE_DEFAULT_VALIDATOR:
                return USE_DEFAULT_VALIDATOR_EDEFAULT == null ? useDefaultValidator != null : !USE_DEFAULT_VALIDATOR_EDEFAULT.equals(useDefaultValidator);
            case FormPackage.TABLE__BELOW:
                return below != BELOW_EDEFAULT;
            case FormPackage.TABLE__DESCRIPTION:
                return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
            case FormPackage.TABLE__EXAMPLE_MESSAGE_POSITION:
                return exampleMessagePosition != EXAMPLE_MESSAGE_POSITION_EDEFAULT;
            case FormPackage.TABLE__EXAMPLE_MESSAGE:
                return exampleMessage != null;
            case FormPackage.TABLE__DEFAULT_EXPRESSION:
                return defaultExpression != null;
            case FormPackage.TABLE__DEFAULT_EXPRESSION_AFTER_EVENT:
                return defaultExpressionAfterEvent != null;
            case FormPackage.TABLE__USE_PAGINATION:
                return usePagination != USE_PAGINATION_EDEFAULT;
            case FormPackage.TABLE__ALLOW_SELECTION:
                return allowSelection != ALLOW_SELECTION_EDEFAULT;
            case FormPackage.TABLE__SELECTION_MODE_IS_MULTIPLE:
                return selectionModeIsMultiple != SELECTION_MODE_IS_MULTIPLE_EDEFAULT;
            case FormPackage.TABLE__MAX_ROW_FOR_PAGINATION:
                return maxRowForPagination != null;
            case FormPackage.TABLE__COLUMN_FOR_INITIAL_SELECTION_INDEX:
                return columnForInitialSelectionIndex != null;
            case FormPackage.TABLE__SELECTED_VALUES:
                return selectedValues != null;
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
                case FormPackage.TABLE__VALIDATORS: return FormPackage.VALIDABLE__VALIDATORS;
                case FormPackage.TABLE__USE_DEFAULT_VALIDATOR: return FormPackage.VALIDABLE__USE_DEFAULT_VALIDATOR;
                case FormPackage.TABLE__BELOW: return FormPackage.VALIDABLE__BELOW;
                default: return -1;
            }
        }
        if (baseClass == FormField.class) {
            switch (derivedFeatureID) {
                case FormPackage.TABLE__DESCRIPTION: return FormPackage.FORM_FIELD__DESCRIPTION;
                case FormPackage.TABLE__EXAMPLE_MESSAGE_POSITION: return FormPackage.FORM_FIELD__EXAMPLE_MESSAGE_POSITION;
                case FormPackage.TABLE__EXAMPLE_MESSAGE: return FormPackage.FORM_FIELD__EXAMPLE_MESSAGE;
                default: return -1;
            }
        }
        if (baseClass == MultipleValuatedFormField.class) {
            switch (derivedFeatureID) {
                case FormPackage.TABLE__DEFAULT_EXPRESSION: return FormPackage.MULTIPLE_VALUATED_FORM_FIELD__DEFAULT_EXPRESSION;
                case FormPackage.TABLE__DEFAULT_EXPRESSION_AFTER_EVENT: return FormPackage.MULTIPLE_VALUATED_FORM_FIELD__DEFAULT_EXPRESSION_AFTER_EVENT;
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
                case FormPackage.VALIDABLE__VALIDATORS: return FormPackage.TABLE__VALIDATORS;
                case FormPackage.VALIDABLE__USE_DEFAULT_VALIDATOR: return FormPackage.TABLE__USE_DEFAULT_VALIDATOR;
                case FormPackage.VALIDABLE__BELOW: return FormPackage.TABLE__BELOW;
                default: return -1;
            }
        }
        if (baseClass == FormField.class) {
            switch (baseFeatureID) {
                case FormPackage.FORM_FIELD__DESCRIPTION: return FormPackage.TABLE__DESCRIPTION;
                case FormPackage.FORM_FIELD__EXAMPLE_MESSAGE_POSITION: return FormPackage.TABLE__EXAMPLE_MESSAGE_POSITION;
                case FormPackage.FORM_FIELD__EXAMPLE_MESSAGE: return FormPackage.TABLE__EXAMPLE_MESSAGE;
                default: return -1;
            }
        }
        if (baseClass == MultipleValuatedFormField.class) {
            switch (baseFeatureID) {
                case FormPackage.MULTIPLE_VALUATED_FORM_FIELD__DEFAULT_EXPRESSION: return FormPackage.TABLE__DEFAULT_EXPRESSION;
                case FormPackage.MULTIPLE_VALUATED_FORM_FIELD__DEFAULT_EXPRESSION_AFTER_EVENT: return FormPackage.TABLE__DEFAULT_EXPRESSION_AFTER_EVENT;
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
        result.append(", usePagination: "); //$NON-NLS-1$
        result.append(usePagination);
        result.append(", allowSelection: "); //$NON-NLS-1$
        result.append(allowSelection);
        result.append(", selectionModeIsMultiple: "); //$NON-NLS-1$
        result.append(selectionModeIsMultiple);
        result.append(')');
        return result.toString();
    }

} //TableImpl
