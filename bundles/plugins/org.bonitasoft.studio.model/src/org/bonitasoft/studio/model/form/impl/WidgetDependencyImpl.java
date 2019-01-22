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

import org.bonitasoft.studio.model.form.EventDependencyType;
import org.bonitasoft.studio.model.form.FormPackage;
import org.bonitasoft.studio.model.form.Widget;
import org.bonitasoft.studio.model.form.WidgetDependency;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Widget Dependency</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.WidgetDependencyImpl#isTriggerRefreshOnModification <em>Trigger Refresh On Modification</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.WidgetDependencyImpl#getEventTypes <em>Event Types</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.WidgetDependencyImpl#getWidget <em>Widget</em>}</li>
 * </ul>
 *
 * @generated
 */
public class WidgetDependencyImpl extends EObjectImpl implements WidgetDependency {
	/**
	 * The default value of the '{@link #isTriggerRefreshOnModification() <em>Trigger Refresh On Modification</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isTriggerRefreshOnModification()
	 * @generated
	 * @ordered
	 */
	protected static final boolean TRIGGER_REFRESH_ON_MODIFICATION_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isTriggerRefreshOnModification() <em>Trigger Refresh On Modification</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isTriggerRefreshOnModification()
	 * @generated
	 * @ordered
	 */
	protected boolean triggerRefreshOnModification = TRIGGER_REFRESH_ON_MODIFICATION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getEventTypes() <em>Event Types</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEventTypes()
	 * @generated
	 * @ordered
	 */
	protected EList<EventDependencyType> eventTypes;

	/**
	 * The cached value of the '{@link #getWidget() <em>Widget</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWidget()
	 * @generated
	 * @ordered
	 */
	protected Widget widget;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected WidgetDependencyImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FormPackage.Literals.WIDGET_DEPENDENCY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isTriggerRefreshOnModification() {
		return triggerRefreshOnModification;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTriggerRefreshOnModification(boolean newTriggerRefreshOnModification) {
		boolean oldTriggerRefreshOnModification = triggerRefreshOnModification;
		triggerRefreshOnModification = newTriggerRefreshOnModification;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.WIDGET_DEPENDENCY__TRIGGER_REFRESH_ON_MODIFICATION, oldTriggerRefreshOnModification, triggerRefreshOnModification));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<EventDependencyType> getEventTypes() {
		if (eventTypes == null) {
			eventTypes = new EDataTypeUniqueEList<EventDependencyType>(EventDependencyType.class, this, FormPackage.WIDGET_DEPENDENCY__EVENT_TYPES);
		}
		return eventTypes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Widget getWidget() {
		if (widget != null && widget.eIsProxy()) {
			InternalEObject oldWidget = (InternalEObject)widget;
			widget = (Widget)eResolveProxy(oldWidget);
			if (widget != oldWidget) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, FormPackage.WIDGET_DEPENDENCY__WIDGET, oldWidget, widget));
			}
		}
		return widget;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Widget basicGetWidget() {
		return widget;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setWidget(Widget newWidget) {
		Widget oldWidget = widget;
		widget = newWidget;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.WIDGET_DEPENDENCY__WIDGET, oldWidget, widget));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case FormPackage.WIDGET_DEPENDENCY__TRIGGER_REFRESH_ON_MODIFICATION:
				return isTriggerRefreshOnModification();
			case FormPackage.WIDGET_DEPENDENCY__EVENT_TYPES:
				return getEventTypes();
			case FormPackage.WIDGET_DEPENDENCY__WIDGET:
				if (resolve) return getWidget();
				return basicGetWidget();
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
			case FormPackage.WIDGET_DEPENDENCY__TRIGGER_REFRESH_ON_MODIFICATION:
				setTriggerRefreshOnModification((Boolean)newValue);
				return;
			case FormPackage.WIDGET_DEPENDENCY__EVENT_TYPES:
				getEventTypes().clear();
				getEventTypes().addAll((Collection<? extends EventDependencyType>)newValue);
				return;
			case FormPackage.WIDGET_DEPENDENCY__WIDGET:
				setWidget((Widget)newValue);
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
			case FormPackage.WIDGET_DEPENDENCY__TRIGGER_REFRESH_ON_MODIFICATION:
				setTriggerRefreshOnModification(TRIGGER_REFRESH_ON_MODIFICATION_EDEFAULT);
				return;
			case FormPackage.WIDGET_DEPENDENCY__EVENT_TYPES:
				getEventTypes().clear();
				return;
			case FormPackage.WIDGET_DEPENDENCY__WIDGET:
				setWidget((Widget)null);
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
			case FormPackage.WIDGET_DEPENDENCY__TRIGGER_REFRESH_ON_MODIFICATION:
				return triggerRefreshOnModification != TRIGGER_REFRESH_ON_MODIFICATION_EDEFAULT;
			case FormPackage.WIDGET_DEPENDENCY__EVENT_TYPES:
				return eventTypes != null && !eventTypes.isEmpty();
			case FormPackage.WIDGET_DEPENDENCY__WIDGET:
				return widget != null;
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
		result.append(" (triggerRefreshOnModification: "); //$NON-NLS-1$
		result.append(triggerRefreshOnModification);
		result.append(", eventTypes: "); //$NON-NLS-1$
		result.append(eventTypes);
		result.append(')');
		return result.toString();
	}

} //WidgetDependencyImpl
