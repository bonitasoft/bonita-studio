/*******************************************************************************
 * Copyright (c) 2007, 2010 BMW Car IT, Technische Universitaet Muenchen, and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * BMW Car IT - Initial API and implementation
 * Technische Universitaet Muenchen - Major refactoring and extension
 *******************************************************************************/
package org.eclipse.emf.edapt.spi.migration;

import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edapt.migration.MigrationException;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Instance</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * An instance of a class
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.emf.edapt.spi.migration.Instance#getSlots <em>Slots</em>}</li>
 * <li>{@link org.eclipse.emf.edapt.spi.migration.Instance#getType <em>Type</em>}</li>
 * <li>{@link org.eclipse.emf.edapt.spi.migration.Instance#getReferences <em>References</em>}</li>
 * <li>{@link org.eclipse.emf.edapt.spi.migration.Instance#getUri <em>Uri</em>}</li>
 * <li>{@link org.eclipse.emf.edapt.spi.migration.Instance#getUuid <em>Uuid</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.edapt.spi.migration.MigrationPackage#getInstance()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='validContainment validType'"
 * @generated
 */
public interface Instance {
	/**
	 * Returns the value of the '<em><b>Slots</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.emf.edapt.spi.migration.Slot}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.emf.edapt.spi.migration.Slot#getInstance
	 * <em>Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Slots</em>' containment reference list isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The slots defined by the instance
	 * <!-- end-model-doc -->
	 *
	 * @return the value of the '<em>Slots</em>' containment reference list.
	 * @see org.eclipse.emf.edapt.spi.migration.MigrationPackage#getInstance_Slots()
	 * @see org.eclipse.emf.edapt.spi.migration.Slot#getInstance
	 * @model opposite="instance" containment="true"
	 * @generated
	 */
	EList<Slot> getSlots();

	/**
	 * Returns the value of the '<em><b>Type</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.emf.edapt.spi.migration.Type#getInstances
	 * <em>Instances</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' container reference isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The type container to which the instance belongs
	 * <!-- end-model-doc -->
	 *
	 * @return the value of the '<em>Type</em>' container reference.
	 * @see #setType(Type)
	 * @see org.eclipse.emf.edapt.spi.migration.MigrationPackage#getInstance_Type()
	 * @see org.eclipse.emf.edapt.spi.migration.Type#getInstances
	 * @model opposite="instances" required="true" transient="false"
	 * @generated
	 */
	Type getType();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.edapt.spi.migration.Instance#getType <em>Type</em>}' container
	 * reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @param value the new value of the '<em>Type</em>' container reference.
	 * @see #getType()
	 * @generated
	 */
	void setType(Type value);

	/**
	 * Returns the value of the '<em><b>References</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.emf.edapt.spi.migration.ReferenceSlot}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.emf.edapt.spi.migration.ReferenceSlot#getValues
	 * <em>Values</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>References</em>' reference list isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The slots from which the instance is referenced
	 * <!-- end-model-doc -->
	 *
	 * @return the value of the '<em>References</em>' reference list.
	 * @see org.eclipse.emf.edapt.spi.migration.MigrationPackage#getInstance_References()
	 * @see org.eclipse.emf.edapt.spi.migration.ReferenceSlot#getValues
	 * @model opposite="values"
	 * @generated
	 */
	EList<ReferenceSlot> getReferences();

	/**
	 * Returns the value of the '<em><b>Uri</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Uri</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The Uniform Resource Identifier (URI) of the instance in case the instance is a proxy
	 * <!-- end-model-doc -->
	 *
	 * @return the value of the '<em>Uri</em>' attribute.
	 * @see #setUri(URI)
	 * @see org.eclipse.emf.edapt.spi.migration.MigrationPackage#getInstance_Uri()
	 * @model dataType="org.eclipse.emf.edapt.migration.URI"
	 * @generated
	 */
	URI getUri();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.edapt.spi.migration.Instance#getUri <em>Uri</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @param value the new value of the '<em>Uri</em>' attribute.
	 * @see #getUri()
	 * @generated
	 */
	void setUri(URI value);

	/**
	 * Returns the value of the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Uuid</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The unique and unchangeable identifier of the instance
	 * <!-- end-model-doc -->
	 *
	 * @return the value of the '<em>Uuid</em>' attribute.
	 * @see #setUuid(String)
	 * @see org.eclipse.emf.edapt.spi.migration.MigrationPackage#getInstance_Uuid()
	 * @model
	 * @generated
	 */
	String getUuid();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.edapt.spi.migration.Instance#getUuid <em>Uuid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @param value the new value of the '<em>Uuid</em>' attribute.
	 * @see #getUuid()
	 * @generated
	 */
	void setUuid(String value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Get the value of the feature
	 *
	 * @param feature Get the value of the feature
	 *            <!-- end-model-doc -->
	 * @model featureRequired="true"
	 * @generated
	 */
	<V> V get(EStructuralFeature feature);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Get the value of the feature which is given by its name
	 * <!-- end-model-doc -->
	 *
	 * @model featureNameRequired="true"
	 * @generated
	 */
	<V> V get(String featureName);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Get the inverse value of the reference
	 * <!-- end-model-doc -->
	 *
	 * @model referenceRequired="true"
	 * @generated
	 */
	EList<Instance> getInverse(EReference reference);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Get the slot that conforms to the feature
	 * <!-- end-model-doc -->
	 *
	 * @model featureRequired="true"
	 * @generated
	 */
	Slot getSlot(EStructuralFeature feature);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Get the class to which the instance conforms
	 * <!-- end-model-doc -->
	 *
	 * @model kind="operation" required="true"
	 * @generated
	 */
	EClass getEClass();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Set the value of the feature
	 * <!-- end-model-doc -->
	 *
	 * @model featureRequired="true"
	 * @generated
	 */
	void set(EStructuralFeature feature, Object value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Set the value of a feature which is given by its name
	 * <!-- end-model-doc -->
	 *
	 * @model featureNameRequired="true"
	 * @generated
	 */
	void set(String featureName, Object value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Check whether the value of a feature is set
	 * <!-- end-model-doc -->
	 *
	 * @model required="true" featureRequired="true"
	 * @generated
	 */
	boolean isSet(EStructuralFeature feature);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Unset the value of the feature
	 * <!-- end-model-doc -->
	 *
	 * @model featureRequired="true"
	 * @generated
	 */
	<V> V unset(EStructuralFeature feature);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Add an element at a certain position to the value of the feature
	 * <!-- end-model-doc -->
	 *
	 * @model featureRequired="true" indexRequired="true" valueRequired="true"
	 * @generated
	 */
	void add(EStructuralFeature feature, int index, Object value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Add an element to the value of the feature
	 * <!-- end-model-doc -->
	 *
	 * @model featureRequired="true" valueRequired="true"
	 * @generated
	 */
	void add(EStructuralFeature feature, Object value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Remove an element from the value of the feature
	 * <!-- end-model-doc -->
	 *
	 * @model featureRequired="true" valueRequired="true"
	 * @generated
	 */
	void remove(EStructuralFeature feature, Object value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Remove an element at a certain position from the value of the feature
	 * <!-- end-model-doc -->
	 *
	 * @model featureRequired="true" indexRequired="true"
	 * @generated
	 */
	void remove(EStructuralFeature feature, int index);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Change the type of instance to a different class
	 * <!-- end-model-doc -->
	 *
	 * @model eClassRequired="true"
	 * @generated
	 */
	void migrate(EClass eClass);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Check whether the instance is of a certain type (or its sub types)
	 * <!-- end-model-doc -->
	 *
	 * @model
	 * @generated
	 */
	boolean instanceOf(EClass eClass);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Get the elements by which the instance is contained
	 * <!-- end-model-doc -->
	 *
	 * @model kind="operation"
	 * @generated
	 */
	Instance getContainer();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Get the elements which are contained by the instance
	 * <!-- end-model-doc -->
	 *
	 * @model kind="operation"
	 * @generated
	 */
	EList<Instance> getContents();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Validate the elements rooted at the instance
	 * <!-- end-model-doc -->
	 *
	 * @model
	 * @generated
	 */
	void validate();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Validate the elements rooted at the instance (added all the violations to a diagnostic)
	 * <!-- end-model-doc -->
	 *
	 * @model required="true" chainDataType="org.eclipse.emf.edapt.migration.DiagnosticChain"
	 * @generated
	 */
	boolean validate(DiagnosticChain chain);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Evaluate an OCL constraint with the instance as context
	 * <!-- end-model-doc -->
	 *
	 * @model exceptions="org.eclipse.emf.edapt.migration.MigrationException" expressionRequired="true"
	 * @generated
	 */
	<V> V evaluate(String expression) throws MigrationException;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Get the reference by which the instance is contained
	 * <!-- end-model-doc -->
	 *
	 * @model kind="operation"
	 * @generated
	 */
	EReference getContainerReference();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Get the resource in which this instance is contained
	 * <!-- end-model-doc -->
	 *
	 * @model kind="operation"
	 * @generated
	 */
	ModelResource getResource();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Find out whether the instance is a proxy, i.e. has its URI set
	 * <!-- end-model-doc -->
	 *
	 * @model kind="operation" required="true"
	 * @generated
	 */
	boolean isProxy();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Change the type of instance to a different class
	 * <!-- end-model-doc -->
	 *
	 * @model classNameRequired="true"
	 * @generated
	 */
	void migrate(String className);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Get the inverse value of the reference
	 * <!-- end-model-doc -->
	 *
	 * @model referenceNameRequired="true"
	 * @generated
	 */
	EList<Instance> getInverse(String referenceName);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Get the value of the feature which is given by its name
	 * <!-- end-model-doc -->
	 *
	 * @model referenceNameRequired="true"
	 * @generated
	 */
	Instance getLink(String referenceName);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Get the value of the feature which is given by its name
	 * <!-- end-model-doc -->
	 *
	 * @model referenceNameRequired="true"
	 * @generated
	 */
	EList<Instance> getLinks(String referenceName);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Check whether the instance is of a certain type (or its sub types)
	 * <!-- end-model-doc -->
	 *
	 * @model
	 * @generated
	 */
	boolean instanceOf(String className);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Add an element to the value of the feature
	 * <!-- end-model-doc -->
	 *
	 * @model featureNameRequired="true" valueRequired="true"
	 * @generated
	 */
	void add(String featureName, Object value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Remove an element from the value of the feature
	 * <!-- end-model-doc -->
	 *
	 * @model featureNameRequired="true" valueRequired="true"
	 * @generated
	 */
	void remove(String featureName, Object value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Add an element at a certain position to the value of the feature
	 * <!-- end-model-doc -->
	 *
	 * @model featureNameRequired="true" indexRequired="true" valueRequired="true"
	 * @generated
	 */
	void add(String featureName, int index, Object value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Get the value of the feature which is given by its name
	 * <!-- end-model-doc -->
	 *
	 * @model referenceRequired="true"
	 * @generated
	 */
	Instance getLink(EReference reference);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Get the value of the feature which is given by its name
	 * <!-- end-model-doc -->
	 *
	 * @model referenceRequired="true"
	 * @generated
	 */
	EList<Instance> getLinks(EReference reference);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Get the value of the feature which is given by its name
	 * <!-- end-model-doc -->
	 *
	 * @model
	 * @generated
	 */
	Instance copy();

} // Instance
