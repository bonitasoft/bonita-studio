/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.omg.spec.bpmn.model;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>TGlobal Task</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.TGlobalTask#getResourceRoleGroup <em>Resource Role Group</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TGlobalTask#getResourceRole <em>Resource Role</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.omg.spec.bpmn.model.ModelPackage#getTGlobalTask()
 * @model extendedMetaData="name='tGlobalTask' kind='elementOnly'"
 * @generated
 */
public interface TGlobalTask extends TCallableElement {
	/**
	 * Returns the value of the '<em><b>Resource Role Group</b></em>' attribute list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Resource Role Group</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Resource Role Group</em>' attribute list.
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTGlobalTask_ResourceRoleGroup()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
	 *        extendedMetaData="kind='group' name='resourceRole:group' namespace='##targetNamespace'"
	 * @generated
	 */
	FeatureMap getResourceRoleGroup();

	/**
	 * Returns the value of the '<em><b>Resource Role</b></em>' containment reference list.
	 * The list contents are of type {@link org.omg.spec.bpmn.model.TResourceRole}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Resource Role</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Resource Role</em>' containment reference list.
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTGlobalTask_ResourceRole()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='resourceRole' namespace='##targetNamespace' group='resourceRole:group'"
	 * @generated
	 */
	EList<TResourceRole> getResourceRole();

} // TGlobalTask
