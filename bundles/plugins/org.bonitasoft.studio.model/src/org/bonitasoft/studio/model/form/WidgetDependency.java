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
package org.bonitasoft.studio.model.form;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Widget Dependency</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.form.WidgetDependency#isTriggerRefreshOnModification <em>Trigger Refresh On Modification</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.WidgetDependency#getEventTypes <em>Event Types</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.WidgetDependency#getWidget <em>Widget</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.studio.model.form.FormPackage#getWidgetDependency()
 * @model
 * @generated
 */
public interface WidgetDependency extends EObject {
	/**
     * Returns the value of the '<em><b>Trigger Refresh On Modification</b></em>' attribute.
     * The default value is <code>"true"</code>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Trigger Refresh On Modification</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Trigger Refresh On Modification</em>' attribute.
     * @see #setTriggerRefreshOnModification(boolean)
     * @see org.bonitasoft.studio.model.form.FormPackage#getWidgetDependency_TriggerRefreshOnModification()
     * @model default="true"
     * @generated
     */
	boolean isTriggerRefreshOnModification();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.form.WidgetDependency#isTriggerRefreshOnModification <em>Trigger Refresh On Modification</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Trigger Refresh On Modification</em>' attribute.
     * @see #isTriggerRefreshOnModification()
     * @generated
     */
	void setTriggerRefreshOnModification(boolean value);

	/**
     * Returns the value of the '<em><b>Event Types</b></em>' attribute list.
     * The list contents are of type {@link org.bonitasoft.studio.model.form.EventDependencyType}.
     * The literals are from the enumeration {@link org.bonitasoft.studio.model.form.EventDependencyType}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Event Types</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Event Types</em>' attribute list.
     * @see org.bonitasoft.studio.model.form.EventDependencyType
     * @see org.bonitasoft.studio.model.form.FormPackage#getWidgetDependency_EventTypes()
     * @model default="onChange" required="true"
     * @generated
     */
	EList<EventDependencyType> getEventTypes();

	/**
     * Returns the value of the '<em><b>Widget</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Widget</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Widget</em>' reference.
     * @see #setWidget(Widget)
     * @see org.bonitasoft.studio.model.form.FormPackage#getWidgetDependency_Widget()
     * @model required="true"
     * @generated
     */
	Widget getWidget();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.form.WidgetDependency#getWidget <em>Widget</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Widget</em>' reference.
     * @see #getWidget()
     * @generated
     */
	void setWidget(Widget value);

} // WidgetDependency
