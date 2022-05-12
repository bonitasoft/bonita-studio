/*******************************************************************************
 * Copyright (c) 2007, 2010 BMW Car IT, Technische Universitaet Muenchen, and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 * BMW Car IT - Initial API and implementation
 * Technische Universitaet Muenchen - Major refactoring and extension
 *******************************************************************************/
package org.eclipse.emf.edapt.spi.migration;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Reference Slot</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A slot for the values of a reference
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.emf.edapt.spi.migration.ReferenceSlot#getEReference <em>EReference</em>}</li>
 * <li>{@link org.eclipse.emf.edapt.spi.migration.ReferenceSlot#getValues <em>Values</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.edapt.spi.migration.MigrationPackage#getReferenceSlot()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='validType noDanglingReference validOpposite'"
 * @generated
 */
public interface ReferenceSlot extends Slot {
	/**
	 * Returns the value of the '<em><b>EReference</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>EReference</em>' reference isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The reference to which the slot conforms
	 * <!-- end-model-doc -->
	 *
	 * @return the value of the '<em>EReference</em>' reference.
	 * @see #setEReference(EReference)
	 * @see org.eclipse.emf.edapt.spi.migration.MigrationPackage#getReferenceSlot_EReference()
	 * @model required="true"
	 * @generated
	 */
	EReference getEReference();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.edapt.spi.migration.ReferenceSlot#getEReference
	 * <em>EReference</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @param value the new value of the '<em>EReference</em>' reference.
	 * @see #getEReference()
	 * @generated
	 */
	void setEReference(EReference value);

	/**
	 * Returns the value of the '<em><b>Values</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.emf.edapt.spi.migration.Instance}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.emf.edapt.spi.migration.Instance#getReferences
	 * <em>References</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Values</em>' reference list isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The values of the reference slot
	 * <!-- end-model-doc -->
	 *
	 * @return the value of the '<em>Values</em>' reference list.
	 * @see org.eclipse.emf.edapt.spi.migration.MigrationPackage#getReferenceSlot_Values()
	 * @see org.eclipse.emf.edapt.spi.migration.Instance#getReferences
	 * @model opposite="references"
	 * @generated
	 */
	EList<Instance> getValues();

} // ReferenceSlot
