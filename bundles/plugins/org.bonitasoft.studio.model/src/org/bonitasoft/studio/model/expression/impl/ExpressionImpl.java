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
package org.bonitasoft.studio.model.expression.impl;

import java.util.Collection;

import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionPackage;

import org.bonitasoft.studio.model.process.Connector;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.expression.impl.ExpressionImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.expression.impl.ExpressionImpl#getContent <em>Content</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.expression.impl.ExpressionImpl#getInterpreter <em>Interpreter</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.expression.impl.ExpressionImpl#getType <em>Type</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.expression.impl.ExpressionImpl#getReturnType <em>Return Type</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.expression.impl.ExpressionImpl#getReferencedElements <em>Referenced Elements</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.expression.impl.ExpressionImpl#getConnectors <em>Connectors</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.expression.impl.ExpressionImpl#getPropagateVariableChange <em>Propagate Variable Change</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.expression.impl.ExpressionImpl#isReturnTypeFixed <em>Return Type Fixed</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.expression.impl.ExpressionImpl#isAutomaticDependencies <em>Automatic Dependencies</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.expression.impl.ExpressionImpl#isHtmlAllowed <em>Html Allowed</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ExpressionImpl extends AbstractExpressionImpl implements Expression {
	/**
     * The default value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getName()
     * @generated
     * @ordered
     */
	protected static final String NAME_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getName()
     * @generated
     * @ordered
     */
	protected String name = NAME_EDEFAULT;

	/**
     * The default value of the '{@link #getContent() <em>Content</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getContent()
     * @generated
     * @ordered
     */
	protected static final String CONTENT_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getContent() <em>Content</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getContent()
     * @generated
     * @ordered
     */
	protected String content = CONTENT_EDEFAULT;

	/**
     * The default value of the '{@link #getInterpreter() <em>Interpreter</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getInterpreter()
     * @generated
     * @ordered
     */
	protected static final String INTERPRETER_EDEFAULT = ""; //$NON-NLS-1$

	/**
     * The cached value of the '{@link #getInterpreter() <em>Interpreter</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getInterpreter()
     * @generated
     * @ordered
     */
	protected String interpreter = INTERPRETER_EDEFAULT;

	/**
     * The default value of the '{@link #getType() <em>Type</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getType()
     * @generated
     * @ordered
     */
	protected static final String TYPE_EDEFAULT = "TYPE_CONSTANT"; //$NON-NLS-1$

	/**
     * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getType()
     * @generated
     * @ordered
     */
	protected String type = TYPE_EDEFAULT;

	/**
     * The default value of the '{@link #getReturnType() <em>Return Type</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getReturnType()
     * @generated
     * @ordered
     */
	protected static final String RETURN_TYPE_EDEFAULT = "java.lang.String"; //$NON-NLS-1$

	/**
     * The cached value of the '{@link #getReturnType() <em>Return Type</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getReturnType()
     * @generated
     * @ordered
     */
	protected String returnType = RETURN_TYPE_EDEFAULT;

	/**
     * The cached value of the '{@link #getReferencedElements() <em>Referenced Elements</em>}' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getReferencedElements()
     * @generated
     * @ordered
     */
	protected EList<EObject> referencedElements;

	/**
     * The cached value of the '{@link #getConnectors() <em>Connectors</em>}' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getConnectors()
     * @generated
     * @ordered
     */
	protected EList<Connector> connectors;

	/**
     * The default value of the '{@link #getPropagateVariableChange() <em>Propagate Variable Change</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getPropagateVariableChange()
     * @generated
     * @ordered
     */
	protected static final Boolean PROPAGATE_VARIABLE_CHANGE_EDEFAULT = Boolean.FALSE;

	/**
     * The cached value of the '{@link #getPropagateVariableChange() <em>Propagate Variable Change</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getPropagateVariableChange()
     * @generated
     * @ordered
     */
	protected Boolean propagateVariableChange = PROPAGATE_VARIABLE_CHANGE_EDEFAULT;

	/**
     * The default value of the '{@link #isReturnTypeFixed() <em>Return Type Fixed</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isReturnTypeFixed()
     * @generated
     * @ordered
     */
	protected static final boolean RETURN_TYPE_FIXED_EDEFAULT = false;

	/**
     * The cached value of the '{@link #isReturnTypeFixed() <em>Return Type Fixed</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isReturnTypeFixed()
     * @generated
     * @ordered
     */
	protected boolean returnTypeFixed = RETURN_TYPE_FIXED_EDEFAULT;

	/**
     * The default value of the '{@link #isAutomaticDependencies() <em>Automatic Dependencies</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isAutomaticDependencies()
     * @generated
     * @ordered
     */
	protected static final boolean AUTOMATIC_DEPENDENCIES_EDEFAULT = true;

	/**
     * The cached value of the '{@link #isAutomaticDependencies() <em>Automatic Dependencies</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isAutomaticDependencies()
     * @generated
     * @ordered
     */
	protected boolean automaticDependencies = AUTOMATIC_DEPENDENCIES_EDEFAULT;

	/**
     * The default value of the '{@link #isHtmlAllowed() <em>Html Allowed</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isHtmlAllowed()
     * @generated
     * @ordered
     */
	protected static final boolean HTML_ALLOWED_EDEFAULT = false;

	/**
     * The cached value of the '{@link #isHtmlAllowed() <em>Html Allowed</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isHtmlAllowed()
     * @generated
     * @ordered
     */
	protected boolean htmlAllowed = HTML_ALLOWED_EDEFAULT;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected ExpressionImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return ExpressionPackage.Literals.EXPRESSION;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public String getName() {
        return name;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setName(String newName) {
        String oldName = name;
        name = newName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ExpressionPackage.EXPRESSION__NAME, oldName, name));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public String getContent() {
        return content;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setContent(String newContent) {
        String oldContent = content;
        content = newContent;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ExpressionPackage.EXPRESSION__CONTENT, oldContent, content));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public String getInterpreter() {
        return interpreter;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setInterpreter(String newInterpreter) {
        String oldInterpreter = interpreter;
        interpreter = newInterpreter;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ExpressionPackage.EXPRESSION__INTERPRETER, oldInterpreter, interpreter));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public String getType() {
        return type;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setType(String newType) {
        String oldType = type;
        type = newType;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ExpressionPackage.EXPRESSION__TYPE, oldType, type));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public String getReturnType() {
        return returnType;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setReturnType(String newReturnType) {
        String oldReturnType = returnType;
        returnType = newReturnType;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ExpressionPackage.EXPRESSION__RETURN_TYPE, oldReturnType, returnType));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EList<EObject> getReferencedElements() {
        if (referencedElements == null) {
            referencedElements = new EObjectContainmentEList<EObject>(EObject.class, this, ExpressionPackage.EXPRESSION__REFERENCED_ELEMENTS);
        }
        return referencedElements;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EList<Connector> getConnectors() {
        if (connectors == null) {
            connectors = new EObjectContainmentEList<Connector>(Connector.class, this, ExpressionPackage.EXPRESSION__CONNECTORS);
        }
        return connectors;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Boolean getPropagateVariableChange() {
        return propagateVariableChange;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setPropagateVariableChange(Boolean newPropagateVariableChange) {
        Boolean oldPropagateVariableChange = propagateVariableChange;
        propagateVariableChange = newPropagateVariableChange;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ExpressionPackage.EXPRESSION__PROPAGATE_VARIABLE_CHANGE, oldPropagateVariableChange, propagateVariableChange));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public boolean isReturnTypeFixed() {
        return returnTypeFixed;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setReturnTypeFixed(boolean newReturnTypeFixed) {
        boolean oldReturnTypeFixed = returnTypeFixed;
        returnTypeFixed = newReturnTypeFixed;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ExpressionPackage.EXPRESSION__RETURN_TYPE_FIXED, oldReturnTypeFixed, returnTypeFixed));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public boolean isAutomaticDependencies() {
        return automaticDependencies;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setAutomaticDependencies(boolean newAutomaticDependencies) {
        boolean oldAutomaticDependencies = automaticDependencies;
        automaticDependencies = newAutomaticDependencies;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ExpressionPackage.EXPRESSION__AUTOMATIC_DEPENDENCIES, oldAutomaticDependencies, automaticDependencies));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public boolean isHtmlAllowed() {
        return htmlAllowed;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setHtmlAllowed(boolean newHtmlAllowed) {
        boolean oldHtmlAllowed = htmlAllowed;
        htmlAllowed = newHtmlAllowed;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ExpressionPackage.EXPRESSION__HTML_ALLOWED, oldHtmlAllowed, htmlAllowed));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public boolean hasContent() {
        return content != null && !content.isEmpty();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public boolean hasName() {
        return name != null && !name.isEmpty();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case ExpressionPackage.EXPRESSION__REFERENCED_ELEMENTS:
                return ((InternalEList<?>)getReferencedElements()).basicRemove(otherEnd, msgs);
            case ExpressionPackage.EXPRESSION__CONNECTORS:
                return ((InternalEList<?>)getConnectors()).basicRemove(otherEnd, msgs);
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
            case ExpressionPackage.EXPRESSION__NAME:
                return getName();
            case ExpressionPackage.EXPRESSION__CONTENT:
                return getContent();
            case ExpressionPackage.EXPRESSION__INTERPRETER:
                return getInterpreter();
            case ExpressionPackage.EXPRESSION__TYPE:
                return getType();
            case ExpressionPackage.EXPRESSION__RETURN_TYPE:
                return getReturnType();
            case ExpressionPackage.EXPRESSION__REFERENCED_ELEMENTS:
                return getReferencedElements();
            case ExpressionPackage.EXPRESSION__CONNECTORS:
                return getConnectors();
            case ExpressionPackage.EXPRESSION__PROPAGATE_VARIABLE_CHANGE:
                return getPropagateVariableChange();
            case ExpressionPackage.EXPRESSION__RETURN_TYPE_FIXED:
                return isReturnTypeFixed();
            case ExpressionPackage.EXPRESSION__AUTOMATIC_DEPENDENCIES:
                return isAutomaticDependencies();
            case ExpressionPackage.EXPRESSION__HTML_ALLOWED:
                return isHtmlAllowed();
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
            case ExpressionPackage.EXPRESSION__NAME:
                setName((String)newValue);
                return;
            case ExpressionPackage.EXPRESSION__CONTENT:
                setContent((String)newValue);
                return;
            case ExpressionPackage.EXPRESSION__INTERPRETER:
                setInterpreter((String)newValue);
                return;
            case ExpressionPackage.EXPRESSION__TYPE:
                setType((String)newValue);
                return;
            case ExpressionPackage.EXPRESSION__RETURN_TYPE:
                setReturnType((String)newValue);
                return;
            case ExpressionPackage.EXPRESSION__REFERENCED_ELEMENTS:
                getReferencedElements().clear();
                getReferencedElements().addAll((Collection<? extends EObject>)newValue);
                return;
            case ExpressionPackage.EXPRESSION__CONNECTORS:
                getConnectors().clear();
                getConnectors().addAll((Collection<? extends Connector>)newValue);
                return;
            case ExpressionPackage.EXPRESSION__PROPAGATE_VARIABLE_CHANGE:
                setPropagateVariableChange((Boolean)newValue);
                return;
            case ExpressionPackage.EXPRESSION__RETURN_TYPE_FIXED:
                setReturnTypeFixed((Boolean)newValue);
                return;
            case ExpressionPackage.EXPRESSION__AUTOMATIC_DEPENDENCIES:
                setAutomaticDependencies((Boolean)newValue);
                return;
            case ExpressionPackage.EXPRESSION__HTML_ALLOWED:
                setHtmlAllowed((Boolean)newValue);
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
            case ExpressionPackage.EXPRESSION__NAME:
                setName(NAME_EDEFAULT);
                return;
            case ExpressionPackage.EXPRESSION__CONTENT:
                setContent(CONTENT_EDEFAULT);
                return;
            case ExpressionPackage.EXPRESSION__INTERPRETER:
                setInterpreter(INTERPRETER_EDEFAULT);
                return;
            case ExpressionPackage.EXPRESSION__TYPE:
                setType(TYPE_EDEFAULT);
                return;
            case ExpressionPackage.EXPRESSION__RETURN_TYPE:
                setReturnType(RETURN_TYPE_EDEFAULT);
                return;
            case ExpressionPackage.EXPRESSION__REFERENCED_ELEMENTS:
                getReferencedElements().clear();
                return;
            case ExpressionPackage.EXPRESSION__CONNECTORS:
                getConnectors().clear();
                return;
            case ExpressionPackage.EXPRESSION__PROPAGATE_VARIABLE_CHANGE:
                setPropagateVariableChange(PROPAGATE_VARIABLE_CHANGE_EDEFAULT);
                return;
            case ExpressionPackage.EXPRESSION__RETURN_TYPE_FIXED:
                setReturnTypeFixed(RETURN_TYPE_FIXED_EDEFAULT);
                return;
            case ExpressionPackage.EXPRESSION__AUTOMATIC_DEPENDENCIES:
                setAutomaticDependencies(AUTOMATIC_DEPENDENCIES_EDEFAULT);
                return;
            case ExpressionPackage.EXPRESSION__HTML_ALLOWED:
                setHtmlAllowed(HTML_ALLOWED_EDEFAULT);
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
            case ExpressionPackage.EXPRESSION__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case ExpressionPackage.EXPRESSION__CONTENT:
                return CONTENT_EDEFAULT == null ? content != null : !CONTENT_EDEFAULT.equals(content);
            case ExpressionPackage.EXPRESSION__INTERPRETER:
                return INTERPRETER_EDEFAULT == null ? interpreter != null : !INTERPRETER_EDEFAULT.equals(interpreter);
            case ExpressionPackage.EXPRESSION__TYPE:
                return TYPE_EDEFAULT == null ? type != null : !TYPE_EDEFAULT.equals(type);
            case ExpressionPackage.EXPRESSION__RETURN_TYPE:
                return RETURN_TYPE_EDEFAULT == null ? returnType != null : !RETURN_TYPE_EDEFAULT.equals(returnType);
            case ExpressionPackage.EXPRESSION__REFERENCED_ELEMENTS:
                return referencedElements != null && !referencedElements.isEmpty();
            case ExpressionPackage.EXPRESSION__CONNECTORS:
                return connectors != null && !connectors.isEmpty();
            case ExpressionPackage.EXPRESSION__PROPAGATE_VARIABLE_CHANGE:
                return PROPAGATE_VARIABLE_CHANGE_EDEFAULT == null ? propagateVariableChange != null : !PROPAGATE_VARIABLE_CHANGE_EDEFAULT.equals(propagateVariableChange);
            case ExpressionPackage.EXPRESSION__RETURN_TYPE_FIXED:
                return returnTypeFixed != RETURN_TYPE_FIXED_EDEFAULT;
            case ExpressionPackage.EXPRESSION__AUTOMATIC_DEPENDENCIES:
                return automaticDependencies != AUTOMATIC_DEPENDENCIES_EDEFAULT;
            case ExpressionPackage.EXPRESSION__HTML_ALLOWED:
                return htmlAllowed != HTML_ALLOWED_EDEFAULT;
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

        StringBuilder result = new StringBuilder(super.toString());
        result.append(" (name: "); //$NON-NLS-1$
        result.append(name);
        result.append(", content: "); //$NON-NLS-1$
        result.append(content);
        result.append(", interpreter: "); //$NON-NLS-1$
        result.append(interpreter);
        result.append(", type: "); //$NON-NLS-1$
        result.append(type);
        result.append(", returnType: "); //$NON-NLS-1$
        result.append(returnType);
        result.append(", propagateVariableChange: "); //$NON-NLS-1$
        result.append(propagateVariableChange);
        result.append(", returnTypeFixed: "); //$NON-NLS-1$
        result.append(returnTypeFixed);
        result.append(", automaticDependencies: "); //$NON-NLS-1$
        result.append(automaticDependencies);
        result.append(", htmlAllowed: "); //$NON-NLS-1$
        result.append(htmlAllowed);
        result.append(')');
        return result.toString();
    }

} //ExpressionImpl
