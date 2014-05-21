/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.omg.spec.bpmn.model;

import javax.xml.namespace.QName;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>TCatch Event</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.TCatchEvent#getDataOutput <em>Data Output</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TCatchEvent#getDataOutputAssociation <em>Data Output Association</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TCatchEvent#getOutputSet <em>Output Set</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TCatchEvent#getEventDefinitionGroup <em>Event Definition Group</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TCatchEvent#getEventDefinition <em>Event Definition</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TCatchEvent#getEventDefinitionRef <em>Event Definition Ref</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TCatchEvent#isParallelMultiple <em>Parallel Multiple</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.omg.spec.bpmn.model.ModelPackage#getTCatchEvent()
 * @model abstract="true"
 *        extendedMetaData="name='tCatchEvent' kind='elementOnly'"
 * @generated
 */
public interface TCatchEvent extends TEvent {
	/**
	 * Returns the value of the '<em><b>Data Output</b></em>' containment reference list.
	 * The list contents are of type {@link org.omg.spec.bpmn.model.TDataOutput}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Data Output</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Data Output</em>' containment reference list.
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTCatchEvent_DataOutput()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='dataOutput' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<TDataOutput> getDataOutput();

	/**
	 * Returns the value of the '<em><b>Data Output Association</b></em>' containment reference list.
	 * The list contents are of type {@link org.omg.spec.bpmn.model.TDataOutputAssociation}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Data Output Association</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Data Output Association</em>' containment reference list.
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTCatchEvent_DataOutputAssociation()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='dataOutputAssociation' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<TDataOutputAssociation> getDataOutputAssociation();

	/**
	 * Returns the value of the '<em><b>Output Set</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Output Set</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Output Set</em>' containment reference.
	 * @see #setOutputSet(TOutputSet)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTCatchEvent_OutputSet()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='outputSet' namespace='##targetNamespace'"
	 * @generated
	 */
	TOutputSet getOutputSet();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.TCatchEvent#getOutputSet <em>Output Set</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Output Set</em>' containment reference.
	 * @see #getOutputSet()
	 * @generated
	 */
	void setOutputSet(TOutputSet value);

	/**
	 * Returns the value of the '<em><b>Event Definition Group</b></em>' attribute list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Event Definition Group</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Event Definition Group</em>' attribute list.
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTCatchEvent_EventDefinitionGroup()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
	 *        extendedMetaData="kind='group' name='eventDefinition:group' namespace='##targetNamespace'"
	 * @generated
	 */
	FeatureMap getEventDefinitionGroup();

	/**
	 * Returns the value of the '<em><b>Event Definition</b></em>' containment reference list.
	 * The list contents are of type {@link org.omg.spec.bpmn.model.TEventDefinition}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Event Definition</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Event Definition</em>' containment reference list.
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTCatchEvent_EventDefinition()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='eventDefinition' namespace='##targetNamespace' group='eventDefinition:group'"
	 * @generated
	 */
	EList<TEventDefinition> getEventDefinition();

	/**
	 * Returns the value of the '<em><b>Event Definition Ref</b></em>' attribute list.
	 * The list contents are of type {@link javax.xml.namespace.QName}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Event Definition Ref</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Event Definition Ref</em>' attribute list.
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTCatchEvent_EventDefinitionRef()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.QName"
	 *        extendedMetaData="kind='element' name='eventDefinitionRef' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<QName> getEventDefinitionRef();

	/**
	 * Returns the value of the '<em><b>Parallel Multiple</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parallel Multiple</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parallel Multiple</em>' attribute.
	 * @see #isSetParallelMultiple()
	 * @see #unsetParallelMultiple()
	 * @see #setParallelMultiple(boolean)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTCatchEvent_ParallelMultiple()
	 * @model default="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='attribute' name='parallelMultiple'"
	 * @generated
	 */
	boolean isParallelMultiple();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.TCatchEvent#isParallelMultiple <em>Parallel Multiple</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parallel Multiple</em>' attribute.
	 * @see #isSetParallelMultiple()
	 * @see #unsetParallelMultiple()
	 * @see #isParallelMultiple()
	 * @generated
	 */
	void setParallelMultiple(boolean value);

	/**
	 * Unsets the value of the '{@link org.omg.spec.bpmn.model.TCatchEvent#isParallelMultiple <em>Parallel Multiple</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetParallelMultiple()
	 * @see #isParallelMultiple()
	 * @see #setParallelMultiple(boolean)
	 * @generated
	 */
	void unsetParallelMultiple();

	/**
	 * Returns whether the value of the '{@link org.omg.spec.bpmn.model.TCatchEvent#isParallelMultiple <em>Parallel Multiple</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Parallel Multiple</em>' attribute is set.
	 * @see #unsetParallelMultiple()
	 * @see #isParallelMultiple()
	 * @see #setParallelMultiple(boolean)
	 * @generated
	 */
	boolean isSetParallelMultiple();

} // TCatchEvent
