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
import org.bonitasoft.studio.model.expression.Operation;

import org.bonitasoft.studio.model.form.Column;
import org.bonitasoft.studio.model.form.Form;
import org.bonitasoft.studio.model.form.FormPackage;
import org.bonitasoft.studio.model.form.Line;
import org.bonitasoft.studio.model.form.Validable;
import org.bonitasoft.studio.model.form.Validator;
import org.bonitasoft.studio.model.form.Widget;

import org.bonitasoft.studio.model.process.impl.ConnectableElementImpl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EStringToStringMapEntryImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Form</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.FormImpl#getValidators <em>Validators</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.FormImpl#getUseDefaultValidator <em>Use Default Validator</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.FormImpl#isBelow <em>Below</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.FormImpl#getNColumn <em>NColumn</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.FormImpl#getStringAttributes <em>String Attributes</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.FormImpl#getNLine <em>NLine</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.FormImpl#getShowPageLabel <em>Show Page Label</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.FormImpl#isAllowHTMLInPageLabel <em>Allow HTML In Page Label</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.FormImpl#getVersion <em>Version</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.FormImpl#getColumns <em>Columns</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.FormImpl#getLines <em>Lines</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.FormImpl#getWidgets <em>Widgets</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.FormImpl#getPageLabel <em>Page Label</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.FormImpl#getActions <em>Actions</em>}</li>
 * </ul>
 *
 * @generated
 */
public class FormImpl extends ConnectableElementImpl implements Form {
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
     * The default value of the '{@link #getNColumn() <em>NColumn</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getNColumn()
     * @generated
     * @ordered
     */
	protected static final int NCOLUMN_EDEFAULT = 1;

	/**
     * The cached value of the '{@link #getNColumn() <em>NColumn</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getNColumn()
     * @generated
     * @ordered
     */
	protected int nColumn = NCOLUMN_EDEFAULT;

	/**
     * The cached value of the '{@link #getStringAttributes() <em>String Attributes</em>}' map.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getStringAttributes()
     * @generated
     * @ordered
     */
	protected EMap<String, String> stringAttributes;

	/**
     * The default value of the '{@link #getNLine() <em>NLine</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getNLine()
     * @generated
     * @ordered
     */
	protected static final int NLINE_EDEFAULT = 4;

	/**
     * The cached value of the '{@link #getNLine() <em>NLine</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getNLine()
     * @generated
     * @ordered
     */
	protected int nLine = NLINE_EDEFAULT;

	/**
     * The default value of the '{@link #getShowPageLabel() <em>Show Page Label</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getShowPageLabel()
     * @generated
     * @ordered
     */
	protected static final Boolean SHOW_PAGE_LABEL_EDEFAULT = Boolean.TRUE;

	/**
     * The cached value of the '{@link #getShowPageLabel() <em>Show Page Label</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getShowPageLabel()
     * @generated
     * @ordered
     */
	protected Boolean showPageLabel = SHOW_PAGE_LABEL_EDEFAULT;

	/**
     * The default value of the '{@link #isAllowHTMLInPageLabel() <em>Allow HTML In Page Label</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isAllowHTMLInPageLabel()
     * @generated
     * @ordered
     */
	protected static final boolean ALLOW_HTML_IN_PAGE_LABEL_EDEFAULT = false;

	/**
     * The cached value of the '{@link #isAllowHTMLInPageLabel() <em>Allow HTML In Page Label</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isAllowHTMLInPageLabel()
     * @generated
     * @ordered
     */
	protected boolean allowHTMLInPageLabel = ALLOW_HTML_IN_PAGE_LABEL_EDEFAULT;

	/**
     * The default value of the '{@link #getVersion() <em>Version</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getVersion()
     * @generated
     * @ordered
     */
	protected static final String VERSION_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getVersion() <em>Version</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getVersion()
     * @generated
     * @ordered
     */
	protected String version = VERSION_EDEFAULT;

	/**
     * The cached value of the '{@link #getColumns() <em>Columns</em>}' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getColumns()
     * @generated
     * @ordered
     */
	protected EList<Column> columns;

	/**
     * The cached value of the '{@link #getLines() <em>Lines</em>}' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getLines()
     * @generated
     * @ordered
     */
	protected EList<Line> lines;

	/**
     * The cached value of the '{@link #getWidgets() <em>Widgets</em>}' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getWidgets()
     * @generated
     * @ordered
     */
	protected EList<Widget> widgets;

	/**
     * The cached value of the '{@link #getPageLabel() <em>Page Label</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getPageLabel()
     * @generated
     * @ordered
     */
	protected Expression pageLabel;

	/**
     * The cached value of the '{@link #getActions() <em>Actions</em>}' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getActions()
     * @generated
     * @ordered
     */
	protected EList<Operation> actions;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected FormImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return FormPackage.Literals.FORM;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EList<Validator> getValidators() {
        if (validators == null) {
            validators = new EObjectContainmentEList<Validator>(Validator.class, this, FormPackage.FORM__VALIDATORS);
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
            eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.FORM__USE_DEFAULT_VALIDATOR, oldUseDefaultValidator, useDefaultValidator));
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
            eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.FORM__BELOW, oldBelow, below));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public int getNColumn() {
        return nColumn;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setNColumn(int newNColumn) {
        int oldNColumn = nColumn;
        nColumn = newNColumn;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.FORM__NCOLUMN, oldNColumn, nColumn));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EMap<String, String> getStringAttributes() {
        if (stringAttributes == null) {
            stringAttributes = new EcoreEMap<String,String>(EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY, EStringToStringMapEntryImpl.class, this, FormPackage.FORM__STRING_ATTRIBUTES);
        }
        return stringAttributes;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public int getNLine() {
        return nLine;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setNLine(int newNLine) {
        int oldNLine = nLine;
        nLine = newNLine;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.FORM__NLINE, oldNLine, nLine));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Boolean getShowPageLabel() {
        return showPageLabel;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setShowPageLabel(Boolean newShowPageLabel) {
        Boolean oldShowPageLabel = showPageLabel;
        showPageLabel = newShowPageLabel;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.FORM__SHOW_PAGE_LABEL, oldShowPageLabel, showPageLabel));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public boolean isAllowHTMLInPageLabel() {
        return allowHTMLInPageLabel;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setAllowHTMLInPageLabel(boolean newAllowHTMLInPageLabel) {
        boolean oldAllowHTMLInPageLabel = allowHTMLInPageLabel;
        allowHTMLInPageLabel = newAllowHTMLInPageLabel;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.FORM__ALLOW_HTML_IN_PAGE_LABEL, oldAllowHTMLInPageLabel, allowHTMLInPageLabel));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public String getVersion() {
        return version;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setVersion(String newVersion) {
        String oldVersion = version;
        version = newVersion;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.FORM__VERSION, oldVersion, version));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EList<Column> getColumns() {
        if (columns == null) {
            columns = new EObjectContainmentEList<Column>(Column.class, this, FormPackage.FORM__COLUMNS);
        }
        return columns;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EList<Line> getLines() {
        if (lines == null) {
            lines = new EObjectContainmentEList<Line>(Line.class, this, FormPackage.FORM__LINES);
        }
        return lines;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EList<Widget> getWidgets() {
        if (widgets == null) {
            widgets = new EObjectContainmentEList<Widget>(Widget.class, this, FormPackage.FORM__WIDGETS);
        }
        return widgets;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Expression getPageLabel() {
        return pageLabel;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetPageLabel(Expression newPageLabel, NotificationChain msgs) {
        Expression oldPageLabel = pageLabel;
        pageLabel = newPageLabel;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FormPackage.FORM__PAGE_LABEL, oldPageLabel, newPageLabel);
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
	public void setPageLabel(Expression newPageLabel) {
        if (newPageLabel != pageLabel) {
            NotificationChain msgs = null;
            if (pageLabel != null)
                msgs = ((InternalEObject)pageLabel).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FormPackage.FORM__PAGE_LABEL, null, msgs);
            if (newPageLabel != null)
                msgs = ((InternalEObject)newPageLabel).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FormPackage.FORM__PAGE_LABEL, null, msgs);
            msgs = basicSetPageLabel(newPageLabel, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.FORM__PAGE_LABEL, newPageLabel, newPageLabel));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EList<Operation> getActions() {
        if (actions == null) {
            actions = new EObjectContainmentEList<Operation>(Operation.class, this, FormPackage.FORM__ACTIONS);
        }
        return actions;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case FormPackage.FORM__VALIDATORS:
                return ((InternalEList<?>)getValidators()).basicRemove(otherEnd, msgs);
            case FormPackage.FORM__STRING_ATTRIBUTES:
                return ((InternalEList<?>)getStringAttributes()).basicRemove(otherEnd, msgs);
            case FormPackage.FORM__COLUMNS:
                return ((InternalEList<?>)getColumns()).basicRemove(otherEnd, msgs);
            case FormPackage.FORM__LINES:
                return ((InternalEList<?>)getLines()).basicRemove(otherEnd, msgs);
            case FormPackage.FORM__WIDGETS:
                return ((InternalEList<?>)getWidgets()).basicRemove(otherEnd, msgs);
            case FormPackage.FORM__PAGE_LABEL:
                return basicSetPageLabel(null, msgs);
            case FormPackage.FORM__ACTIONS:
                return ((InternalEList<?>)getActions()).basicRemove(otherEnd, msgs);
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
            case FormPackage.FORM__VALIDATORS:
                return getValidators();
            case FormPackage.FORM__USE_DEFAULT_VALIDATOR:
                return getUseDefaultValidator();
            case FormPackage.FORM__BELOW:
                return isBelow();
            case FormPackage.FORM__NCOLUMN:
                return getNColumn();
            case FormPackage.FORM__STRING_ATTRIBUTES:
                if (coreType) return getStringAttributes();
                else return getStringAttributes().map();
            case FormPackage.FORM__NLINE:
                return getNLine();
            case FormPackage.FORM__SHOW_PAGE_LABEL:
                return getShowPageLabel();
            case FormPackage.FORM__ALLOW_HTML_IN_PAGE_LABEL:
                return isAllowHTMLInPageLabel();
            case FormPackage.FORM__VERSION:
                return getVersion();
            case FormPackage.FORM__COLUMNS:
                return getColumns();
            case FormPackage.FORM__LINES:
                return getLines();
            case FormPackage.FORM__WIDGETS:
                return getWidgets();
            case FormPackage.FORM__PAGE_LABEL:
                return getPageLabel();
            case FormPackage.FORM__ACTIONS:
                return getActions();
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
            case FormPackage.FORM__VALIDATORS:
                getValidators().clear();
                getValidators().addAll((Collection<? extends Validator>)newValue);
                return;
            case FormPackage.FORM__USE_DEFAULT_VALIDATOR:
                setUseDefaultValidator((Boolean)newValue);
                return;
            case FormPackage.FORM__BELOW:
                setBelow((Boolean)newValue);
                return;
            case FormPackage.FORM__NCOLUMN:
                setNColumn((Integer)newValue);
                return;
            case FormPackage.FORM__STRING_ATTRIBUTES:
                ((EStructuralFeature.Setting)getStringAttributes()).set(newValue);
                return;
            case FormPackage.FORM__NLINE:
                setNLine((Integer)newValue);
                return;
            case FormPackage.FORM__SHOW_PAGE_LABEL:
                setShowPageLabel((Boolean)newValue);
                return;
            case FormPackage.FORM__ALLOW_HTML_IN_PAGE_LABEL:
                setAllowHTMLInPageLabel((Boolean)newValue);
                return;
            case FormPackage.FORM__VERSION:
                setVersion((String)newValue);
                return;
            case FormPackage.FORM__COLUMNS:
                getColumns().clear();
                getColumns().addAll((Collection<? extends Column>)newValue);
                return;
            case FormPackage.FORM__LINES:
                getLines().clear();
                getLines().addAll((Collection<? extends Line>)newValue);
                return;
            case FormPackage.FORM__WIDGETS:
                getWidgets().clear();
                getWidgets().addAll((Collection<? extends Widget>)newValue);
                return;
            case FormPackage.FORM__PAGE_LABEL:
                setPageLabel((Expression)newValue);
                return;
            case FormPackage.FORM__ACTIONS:
                getActions().clear();
                getActions().addAll((Collection<? extends Operation>)newValue);
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
            case FormPackage.FORM__VALIDATORS:
                getValidators().clear();
                return;
            case FormPackage.FORM__USE_DEFAULT_VALIDATOR:
                setUseDefaultValidator(USE_DEFAULT_VALIDATOR_EDEFAULT);
                return;
            case FormPackage.FORM__BELOW:
                setBelow(BELOW_EDEFAULT);
                return;
            case FormPackage.FORM__NCOLUMN:
                setNColumn(NCOLUMN_EDEFAULT);
                return;
            case FormPackage.FORM__STRING_ATTRIBUTES:
                getStringAttributes().clear();
                return;
            case FormPackage.FORM__NLINE:
                setNLine(NLINE_EDEFAULT);
                return;
            case FormPackage.FORM__SHOW_PAGE_LABEL:
                setShowPageLabel(SHOW_PAGE_LABEL_EDEFAULT);
                return;
            case FormPackage.FORM__ALLOW_HTML_IN_PAGE_LABEL:
                setAllowHTMLInPageLabel(ALLOW_HTML_IN_PAGE_LABEL_EDEFAULT);
                return;
            case FormPackage.FORM__VERSION:
                setVersion(VERSION_EDEFAULT);
                return;
            case FormPackage.FORM__COLUMNS:
                getColumns().clear();
                return;
            case FormPackage.FORM__LINES:
                getLines().clear();
                return;
            case FormPackage.FORM__WIDGETS:
                getWidgets().clear();
                return;
            case FormPackage.FORM__PAGE_LABEL:
                setPageLabel((Expression)null);
                return;
            case FormPackage.FORM__ACTIONS:
                getActions().clear();
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
            case FormPackage.FORM__VALIDATORS:
                return validators != null && !validators.isEmpty();
            case FormPackage.FORM__USE_DEFAULT_VALIDATOR:
                return USE_DEFAULT_VALIDATOR_EDEFAULT == null ? useDefaultValidator != null : !USE_DEFAULT_VALIDATOR_EDEFAULT.equals(useDefaultValidator);
            case FormPackage.FORM__BELOW:
                return below != BELOW_EDEFAULT;
            case FormPackage.FORM__NCOLUMN:
                return nColumn != NCOLUMN_EDEFAULT;
            case FormPackage.FORM__STRING_ATTRIBUTES:
                return stringAttributes != null && !stringAttributes.isEmpty();
            case FormPackage.FORM__NLINE:
                return nLine != NLINE_EDEFAULT;
            case FormPackage.FORM__SHOW_PAGE_LABEL:
                return SHOW_PAGE_LABEL_EDEFAULT == null ? showPageLabel != null : !SHOW_PAGE_LABEL_EDEFAULT.equals(showPageLabel);
            case FormPackage.FORM__ALLOW_HTML_IN_PAGE_LABEL:
                return allowHTMLInPageLabel != ALLOW_HTML_IN_PAGE_LABEL_EDEFAULT;
            case FormPackage.FORM__VERSION:
                return VERSION_EDEFAULT == null ? version != null : !VERSION_EDEFAULT.equals(version);
            case FormPackage.FORM__COLUMNS:
                return columns != null && !columns.isEmpty();
            case FormPackage.FORM__LINES:
                return lines != null && !lines.isEmpty();
            case FormPackage.FORM__WIDGETS:
                return widgets != null && !widgets.isEmpty();
            case FormPackage.FORM__PAGE_LABEL:
                return pageLabel != null;
            case FormPackage.FORM__ACTIONS:
                return actions != null && !actions.isEmpty();
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
                case FormPackage.FORM__VALIDATORS: return FormPackage.VALIDABLE__VALIDATORS;
                case FormPackage.FORM__USE_DEFAULT_VALIDATOR: return FormPackage.VALIDABLE__USE_DEFAULT_VALIDATOR;
                case FormPackage.FORM__BELOW: return FormPackage.VALIDABLE__BELOW;
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
                case FormPackage.VALIDABLE__VALIDATORS: return FormPackage.FORM__VALIDATORS;
                case FormPackage.VALIDABLE__USE_DEFAULT_VALIDATOR: return FormPackage.FORM__USE_DEFAULT_VALIDATOR;
                case FormPackage.VALIDABLE__BELOW: return FormPackage.FORM__BELOW;
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
        result.append(", nColumn: "); //$NON-NLS-1$
        result.append(nColumn);
        result.append(", nLine: "); //$NON-NLS-1$
        result.append(nLine);
        result.append(", showPageLabel: "); //$NON-NLS-1$
        result.append(showPageLabel);
        result.append(", allowHTMLInPageLabel: "); //$NON-NLS-1$
        result.append(allowHTMLInPageLabel);
        result.append(", version: "); //$NON-NLS-1$
        result.append(version);
        result.append(')');
        return result.toString();
    }

} //FormImpl
