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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edapt.migration.MigrationException;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Metamodel</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * The metamodel in the repository consisting of several resources
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.emf.edapt.spi.migration.Metamodel#getResources <em>Resources</em>}</li>
 * <li>{@link org.eclipse.emf.edapt.spi.migration.Metamodel#getRepository <em>Repository</em>}</li>
 * <li>{@link org.eclipse.emf.edapt.spi.migration.Metamodel#getDefaultPackage <em>Default Package</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.edapt.spi.migration.MigrationPackage#getMetamodel()
 * @model
 * @generated
 */
public interface Metamodel {
	/**
	 * Returns the value of the '<em><b>Resources</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.emf.edapt.spi.migration.MetamodelResource}.
	 * It is bidirectional and its opposite is '
	 * {@link org.eclipse.emf.edapt.spi.migration.MetamodelResource#getMetamodel <em>Metamodel</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Resources</em>' containment reference list isn't clear, there really should be more of
	 * a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The resources of which this metamodel consists
	 * <!-- end-model-doc -->
	 *
	 * @return the value of the '<em>Resources</em>' containment reference list.
	 * @see org.eclipse.emf.edapt.spi.migration.MigrationPackage#getMetamodel_Resources()
	 * @see org.eclipse.emf.edapt.spi.migration.MetamodelResource#getMetamodel
	 * @model opposite="metamodel" containment="true"
	 * @generated
	 */
	EList<MetamodelResource> getResources();

	/**
	 * Returns the value of the '<em><b>Repository</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.emf.edapt.spi.migration.Repository#getMetamodel
	 * <em>Metamodel</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Repository</em>' container reference isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The repository to which the metamodel belongs
	 * <!-- end-model-doc -->
	 *
	 * @return the value of the '<em>Repository</em>' container reference.
	 * @see #setRepository(Repository)
	 * @see org.eclipse.emf.edapt.spi.migration.MigrationPackage#getMetamodel_Repository()
	 * @see org.eclipse.emf.edapt.spi.migration.Repository#getMetamodel
	 * @model opposite="metamodel" transient="false"
	 * @generated
	 */
	Repository getRepository();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.edapt.spi.migration.Metamodel#getRepository <em>Repository</em>}'
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
	 * Returns the value of the '<em><b>Default Package</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Packages that constitute the root of a metamodel resource
	 * <!-- end-model-doc -->
	 *
	 * @return the value of the '<em>Default Package</em>' reference.
	 * @see #setDefaultPackage(EPackage)
	 * @see org.eclipse.emf.edapt.spi.migration.MigrationPackage#getMetamodel_DefaultPackage()
	 * @model
	 * @generated
	 */
	EPackage getDefaultPackage();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.edapt.spi.migration.Metamodel#getDefaultPackage
	 * <em>Default Package</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @param value the new value of the '<em>Default Package</em>' reference.
	 * @see #getDefaultPackage()
	 * @generated
	 */
	void setDefaultPackage(EPackage value);

	/**
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>EPackages</em>' reference list isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The root packages of all resources of which the metamodel consists
	 * <!-- end-model-doc -->
	 *
	 * @model kind="operation"
	 * @generated
	 */
	EList<EPackage> getEPackages();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Resolve an attribute of the metamodel by its fully qualified name
	 * <!-- end-model-doc -->
	 *
	 * @model packageNameRequired="true"
	 * @generated
	 */
	void setDefaultPackage(String packageName);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Resolve a data type of the metamodel by its fully qualified name
	 * <!-- end-model-doc -->
	 *
	 * @model nameRequired="true"
	 * @generated
	 */
	EEnum getEEnum(String name);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Resolve a data type of the metamodel by its fully qualified name
	 * <!-- end-model-doc -->
	 *
	 * @model nameRequired="true"
	 * @generated
	 */
	EEnumLiteral getEEnumLiteral(String name);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Delete an instance from the model, including all the contained instances
	 * <!-- end-model-doc -->
	 *
	 * @model metamodelElementRequired="true"
	 * @generated
	 */
	void delete(EModelElement metamodelElement);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Get the inverse value of the reference
	 * <!-- end-model-doc -->
	 *
	 * @model metamodelElementRequired="true" referenceRequired="true"
	 * @generated
	 */
	<V> EList<V> getInverse(EModelElement metamodelElement, EReference reference);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Get the inverse value of the reference
	 * <!-- end-model-doc -->
	 *
	 * @model eClassRequired="true"
	 * @generated
	 */
	EList<EClass> getESubTypes(EClass eClass);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Get the inverse value of the reference
	 * <!-- end-model-doc -->
	 *
	 * @model eClassRequired="true"
	 * @generated
	 */
	EList<EClass> getEAllSubTypes(EClass eClass);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Set the opposite of a reference, adapting opposite values in the model
	 * <!-- end-model-doc -->
	 *
	 * @model referenceRequired="true" oppositeRequired="true"
	 * @generated
	 */
	void setEOpposite(EReference reference, EReference opposite);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Resolve a package of the metamodel by its fully qualified name
	 * <!-- end-model-doc -->
	 *
	 * @model nameRequired="true"
	 * @generated
	 */
	EPackage getEPackage(String name);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Resolve a classifier of the metamodel by its fully qualified name
	 * <!-- end-model-doc -->
	 *
	 * @model nameRequired="true"
	 * @generated
	 */
	EClassifier getEClassifier(String name);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Resolve a feature of the metamodel by its fully qualified name
	 * <!-- end-model-doc -->
	 *
	 * @model nameRequired="true"
	 * @generated
	 */
	EStructuralFeature getEFeature(String name);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Resolve a class of the metamodel by its fully qualified name
	 * <!-- end-model-doc -->
	 *
	 * @model nameRequired="true"
	 * @generated
	 */
	EClass getEClass(String name);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Resolve a reference of the metamodel by its fully qualified name
	 * <!-- end-model-doc -->
	 *
	 * @model nameRequired="true"
	 * @generated
	 */
	EReference getEReference(String name);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Resolve an attribute of the metamodel by its fully qualified name
	 * <!-- end-model-doc -->
	 *
	 * @model nameRequired="true"
	 * @generated
	 */
	EAttribute getEAttribute(String name);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Resolve a data type of the metamodel by its fully qualified name
	 * <!-- end-model-doc -->
	 *
	 * @model nameRequired="true"
	 * @generated
	 */
	EDataType getEDataType(String name);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Resolve a metamodel element by its fully qualified name
	 * <!-- end-model-doc -->
	 *
	 * @model nameRequired="true"
	 * @generated
	 */
	EModelElement getElement(String name);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Validate the metamodel
	 * <!-- end-model-doc -->
	 *
	 * @model exceptions="org.eclipse.emf.edapt.migration.MigrationException"
	 * @generated
	 */
	void validate() throws MigrationException;

	/** Clear the internal caches within the metamodel elements. */
	void refreshCaches();

} // Metamodel
