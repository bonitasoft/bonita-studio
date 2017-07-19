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

import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.edapt.migration.MigrationException;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * The model in the repository consisting of several resources
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.emf.edapt.spi.migration.Model#getMetamodel <em>Metamodel</em>}</li>
 * <li>{@link org.eclipse.emf.edapt.spi.migration.Model#getTypes <em>Types</em>}</li>
 * <li>{@link org.eclipse.emf.edapt.spi.migration.Model#isReflection <em>Reflection</em>}</li>
 * <li>{@link org.eclipse.emf.edapt.spi.migration.Model#getResources <em>Resources</em>}</li>
 * <li>{@link org.eclipse.emf.edapt.spi.migration.Model#getRepository <em>Repository</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.edapt.spi.migration.MigrationPackage#getModel()
 * @model
 * @generated
 */
public interface Model {
	/**
	 * Returns the value of the '<em><b>Metamodel</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Metamodel</em>' reference isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Get the metamodel to which this model conforms
	 * <!-- end-model-doc -->
	 *
	 * @return the value of the '<em>Metamodel</em>' reference.
	 * @see #setMetamodel(Metamodel)
	 * @see org.eclipse.emf.edapt.spi.migration.MigrationPackage#getModel_Metamodel()
	 * @model
	 * @generated
	 */
	Metamodel getMetamodel();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.edapt.spi.migration.Model#getMetamodel <em>Metamodel</em>}'
	 * reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @param value the new value of the '<em>Metamodel</em>' reference.
	 * @see #getMetamodel()
	 * @generated
	 */
	void setMetamodel(Metamodel value);

	/**
	 * Returns the value of the '<em><b>Types</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.emf.edapt.spi.migration.Type}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.emf.edapt.spi.migration.Type#getModel <em>Model</em>}
	 * '.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Types</em>' containment reference list isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Containers for the classes from which instances exist
	 * <!-- end-model-doc -->
	 *
	 * @return the value of the '<em>Types</em>' containment reference list.
	 * @see org.eclipse.emf.edapt.spi.migration.MigrationPackage#getModel_Types()
	 * @see org.eclipse.emf.edapt.spi.migration.Type#getModel
	 * @model opposite="model" containment="true"
	 * @generated
	 */
	EList<Type> getTypes();

	/**
	 * Returns the value of the '<em><b>Reflection</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Reflection</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Whether the reflection interface allows to access the real model structure
	 * <!-- end-model-doc -->
	 *
	 * @return the value of the '<em>Reflection</em>' attribute.
	 * @see #setReflection(boolean)
	 * @see org.eclipse.emf.edapt.spi.migration.MigrationPackage#getModel_Reflection()
	 * @model required="true"
	 * @generated
	 */
	boolean isReflection();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.edapt.spi.migration.Model#isReflection <em>Reflection</em>}'
	 * attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @param value the new value of the '<em>Reflection</em>' attribute.
	 * @see #isReflection()
	 * @generated
	 */
	void setReflection(boolean value);

	/**
	 * Returns the value of the '<em><b>Resources</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.emf.edapt.spi.migration.ModelResource}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.emf.edapt.spi.migration.ModelResource#getModel
	 * <em>Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Resources</em>' containment reference list isn't clear, there really should be more of
	 * a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Resources of which this model consists
	 * <!-- end-model-doc -->
	 *
	 * @return the value of the '<em>Resources</em>' containment reference list.
	 * @see org.eclipse.emf.edapt.spi.migration.MigrationPackage#getModel_Resources()
	 * @see org.eclipse.emf.edapt.spi.migration.ModelResource#getModel
	 * @model opposite="model" containment="true"
	 * @generated
	 */
	EList<ModelResource> getResources();

	/**
	 * Returns the value of the '<em><b>Repository</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.emf.edapt.spi.migration.Repository#getModel
	 * <em>Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Repository</em>' container reference isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Repository to which this model belongs
	 * <!-- end-model-doc -->
	 *
	 * @return the value of the '<em>Repository</em>' container reference.
	 * @see #setRepository(Repository)
	 * @see org.eclipse.emf.edapt.spi.migration.MigrationPackage#getModel_Repository()
	 * @see org.eclipse.emf.edapt.spi.migration.Repository#getModel
	 * @model opposite="model" transient="false"
	 * @generated
	 */
	Repository getRepository();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.edapt.spi.migration.Model#getRepository <em>Repository</em>}'
	 * container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @param value the new value of the '<em>Repository</em>' container reference.
	 * @see #getRepository()
	 * @generated
	 */
	void setRepository(Repository value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Get all instances of a class, including all sub classes
	 * <!-- end-model-doc -->
	 *
	 * @model eClassRequired="true"
	 * @generated
	 */
	EList<Instance> getAllInstances(EClass eClass);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Get all instances of a class, excluding sub classes
	 * <!-- end-model-doc -->
	 *
	 * @model eClassRequired="true"
	 * @generated
	 */
	EList<Instance> getInstances(EClass eClass);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Get the container for instances that conform to a certain class
	 * <!-- end-model-doc -->
	 *
	 * @model eClassRequired="true"
	 * @generated
	 */
	Type getType(EClass eClass);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Create a map to access instances by their class
	 * <!-- end-model-doc -->
	 *
	 * @model required="true"
	 * @generated
	 */
	Map<EClass, Set<Instance>> createExtentMap();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Create a new instance of a certain class
	 * <!-- end-model-doc -->
	 *
	 * @model required="true" eClassRequired="true"
	 * @generated
	 */
	Instance newInstance(EClass eClass);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Delete an instance from the model, including all the contained instances
	 * <!-- end-model-doc -->
	 *
	 * @model instanceRequired="true"
	 * @generated
	 */
	void delete(Instance instance);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Validate the model
	 * <!-- end-model-doc -->
	 *
	 * @model exceptions="org.eclipse.emf.edapt.migration.MigrationException"
	 * @generated
	 */
	void validate() throws MigrationException;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Check whether the model conforms to the metamodel
	 * <!-- end-model-doc -->
	 *
	 * @model exceptions="org.eclipse.emf.edapt.migration.MigrationException"
	 * @generated
	 */
	void checkConformance() throws MigrationException;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Commit the changes to the repository, i.e. check conformance and consistency
	 * <!-- end-model-doc -->
	 *
	 * @model exceptions="org.eclipse.emf.edapt.migration.MigrationException"
	 * @generated
	 */
	void commit() throws MigrationException;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Get all instances of a class, including all sub classes
	 * <!-- end-model-doc -->
	 *
	 * @model classNameRequired="true"
	 * @generated
	 */
	EList<Instance> getAllInstances(String className);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Get all instances of a class, excluding sub classes
	 * <!-- end-model-doc -->
	 *
	 * @model classNameRequired="true"
	 * @generated
	 */
	EList<Instance> getInstances(String className);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Create a new instance of a certain class
	 * <!-- end-model-doc -->
	 *
	 * @model required="true" classNameRequired="true"
	 * @generated
	 */
	Instance newInstance(String className);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Create a new instance of a certain class
	 * <!-- end-model-doc -->
	 *
	 * @model required="true" uriDataType="org.eclipse.emf.edapt.migration.URI" uriRequired="true"
	 * @generated
	 */
	ModelResource newResource(URI uri);

} // Model
