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
package org.eclipse.emf.edapt.spi.migration.util;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.util.EObjectValidator;
import org.eclipse.emf.edapt.internal.migration.DiagnosticException;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edapt.spi.migration.AbstractResource;
import org.eclipse.emf.edapt.spi.migration.AttributeSlot;
import org.eclipse.emf.edapt.spi.migration.Instance;
import org.eclipse.emf.edapt.spi.migration.Metamodel;
import org.eclipse.emf.edapt.spi.migration.MetamodelResource;
import org.eclipse.emf.edapt.spi.migration.MigrationPackage;
import org.eclipse.emf.edapt.spi.migration.MigrationPlugin;
import org.eclipse.emf.edapt.spi.migration.Model;
import org.eclipse.emf.edapt.spi.migration.ModelResource;
import org.eclipse.emf.edapt.spi.migration.ReferenceSlot;
import org.eclipse.emf.edapt.spi.migration.Repository;
import org.eclipse.emf.edapt.spi.migration.Slot;
import org.eclipse.emf.edapt.spi.migration.Type;
import org.eclipse.emf.edapt.spi.migration.impl.SlotImpl;

/**
 * <!-- begin-user-doc -->
 * The <b>Validator</b> for the model.
 * <!-- end-user-doc -->
 *
 * @see org.eclipse.emf.edapt.spi.migration.MigrationPackage
 * @generated
 */
public class MigrationValidator extends EObjectValidator {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	public static final MigrationValidator INSTANCE = new MigrationValidator();

	/**
	 * A constant for the {@link org.eclipse.emf.common.util.Diagnostic#getSource() source} of diagnostic
	 * {@link org.eclipse.emf.common.util.Diagnostic#getCode() codes} from this package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @see org.eclipse.emf.common.util.Diagnostic#getSource()
	 * @see org.eclipse.emf.common.util.Diagnostic#getCode()
	 * @generated
	 */
	public static final String DIAGNOSTIC_SOURCE = "org.eclipse.emf.edapt.migration"; //$NON-NLS-1$

	/**
	 * A constant with a fixed name that can be used as the base value for additional hand written constants.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	private static final int GENERATED_DIAGNOSTIC_CODE_COUNT = 0;

	/**
	 * A constant with a fixed name that can be used as the base value for additional hand written constants in a
	 * derived class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	protected static final int DIAGNOSTIC_CODE_COUNT = GENERATED_DIAGNOSTIC_CODE_COUNT;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	public MigrationValidator() {
		super();
	}

	/**
	 * Returns the package of this validator switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	protected EPackage getEPackage() {
		return MigrationPackage.eINSTANCE;
	}

	/**
	 * Calls <code>validateXXX</code> for the corresponding classifier of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	protected boolean validate(int classifierID, Object value, DiagnosticChain diagnostics, Map<Object, Object> context) {
		switch (classifierID) {
		case MigrationPackage.REPOSITORY:
			return validateRepository((Repository) value, diagnostics, context);
		case MigrationPackage.MODEL:
			return validateModel((Model) value, diagnostics, context);
		case MigrationPackage.MODEL_RESOURCE:
			return validateModelResource((ModelResource) value, diagnostics, context);
		case MigrationPackage.TYPE:
			return validateType((Type) value, diagnostics, context);
		case MigrationPackage.INSTANCE:
			return validateInstance((Instance) value, diagnostics, context);
		case MigrationPackage.SLOT:
			return validateSlot((Slot) value, diagnostics, context);
		case MigrationPackage.ATTRIBUTE_SLOT:
			return validateAttributeSlot((AttributeSlot) value, diagnostics, context);
		case MigrationPackage.REFERENCE_SLOT:
			return validateReferenceSlot((ReferenceSlot) value, diagnostics, context);
		case MigrationPackage.METAMODEL:
			return validateMetamodel((Metamodel) value, diagnostics, context);
		case MigrationPackage.METAMODEL_RESOURCE:
			return validateMetamodelResource((MetamodelResource) value, diagnostics, context);
		case MigrationPackage.ABSTRACT_RESOURCE:
			return validateAbstractResource((AbstractResource) value, diagnostics, context);
		case MigrationPackage.SET:
			return validateSet((Set<?>) value, diagnostics, context);
		case MigrationPackage.DIAGNOSTIC_CHAIN:
			return validateDiagnosticChain((DiagnosticChain) value, diagnostics, context);
		case MigrationPackage.URI:
			return validateURI((URI) value, diagnostics, context);
		case MigrationPackage.DIAGNOSTIC_EXCEPTION:
			return validateDiagnosticException((DiagnosticException) value, diagnostics, context);
		case MigrationPackage.MIGRATION_EXCEPTION:
			return validateMigrationException((MigrationException) value, diagnostics, context);
		default:
			return true;
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	public boolean validateModel(Model model, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject) model, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	public boolean validateMetamodel(Metamodel metamodel, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject) metamodel, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	public boolean validateType(Type type, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject) type, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	public boolean validateInstance(Instance instance, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment((EObject) instance, diagnostics, context)) {
			return false;
		}
		boolean result = validate_EveryMultiplicityConforms((EObject) instance, diagnostics, context);
		if (result || diagnostics != null) {
			result &= validate_EveryDataValueConforms((EObject) instance, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_EveryReferenceIsContained((EObject) instance, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_EveryBidirectionalReferenceIsPaired((EObject) instance, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_EveryProxyResolves((EObject) instance, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_UniqueID((EObject) instance, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_EveryKeyUnique((EObject) instance, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_EveryMapEntryUnique((EObject) instance, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validateInstance_validContainment(instance, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validateInstance_validType(instance, diagnostics, context);
		}
		return result;
	}

	/**
	 * Validates the validContainment constraint of '<em>Instance</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated NOT
	 */
	public boolean validateInstance_validContainment(Instance instance, DiagnosticChain diagnostics,
		Map<Object, Object> context) {
		if (!validate_validContainment(instance)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
					(Diagnostic.ERROR,
						DIAGNOSTIC_SOURCE,
						0,
						EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { //$NON-NLS-1$
							"validContainment", getObjectLabel((EObject) instance, context) }), //$NON-NLS-1$
						new Object[] { instance }));
			}
			return false;
		}
		return true;
	}

	/**
	 * Determine whether an instance is correctly contained
	 *
	 * @param instance
	 * @return true if constraint holds, false otherwise
	 */
	private boolean validate_validContainment(Instance instance) {

		if (instance.isProxy()) {
			return true;
		}

		// number of instances in which the instance is contained
		int container = 0;
		for (final ReferenceSlot referenceSlot : instance.getReferences()) {
			if (referenceSlot.getEReference().isContainment()) {
				container++;
			}
		}
		// number of resources of which the instance is a root element
		int resources = 0;
		final Model model = instance.getType().getModel();
		for (final ModelResource modelResource : model.getResources()) {
			if (modelResource.getRootInstances().contains(instance)) {
				resources++;
			}
		}

		final boolean valid = container == 1 && resources <= 1 || resources == 1 && container <= 1;
		return valid;
	}

	/**
	 * Validates the validType constraint of '<em>Instance</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated NOT
	 */
	public boolean validateInstance_validType(Instance instance, DiagnosticChain diagnostics,
		Map<Object, Object> context) {
		if (!validate_validType(instance)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
					(Diagnostic.ERROR,
						DIAGNOSTIC_SOURCE,
						0,
						EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "validType", //$NON-NLS-1$ //$NON-NLS-2$
							getObjectLabel((EObject) instance, context) }),
						new Object[] { instance }));
			}
			return false;
		}
		return true;
	}

	/**
	 * Determine whether an instance is of a valid type
	 *
	 * @param instance
	 * @return true if constraint holds, false otherwise
	 */
	private boolean validate_validType(Instance instance) {
		final EClass eClass = instance.getEClass();
		final boolean result = eClass != null && eClass.getEPackage() != null
			&& !eClass.isAbstract();
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	public boolean validateSlot(Slot slot, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment((EObject) slot, diagnostics, context)) {
			return false;
		}
		boolean result = validate_EveryMultiplicityConforms((EObject) slot, diagnostics, context);
		if (result || diagnostics != null) {
			result &= validate_EveryDataValueConforms((EObject) slot, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_EveryReferenceIsContained((EObject) slot, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_EveryBidirectionalReferenceIsPaired((EObject) slot, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_EveryProxyResolves((EObject) slot, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_UniqueID((EObject) slot, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_EveryKeyUnique((EObject) slot, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_EveryMapEntryUnique((EObject) slot, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validateSlot_validFeature(slot, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validateSlot_validMultiplicity(slot, diagnostics, context);
		}
		return result;
	}

	/**
	 * Validates the validFeature constraint of '<em>Slot</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated NOT
	 */
	public boolean validateSlot_validFeature(Slot slot, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_validFeature(slot)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
					(Diagnostic.ERROR,
						DIAGNOSTIC_SOURCE,
						0,
						EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { //$NON-NLS-1$
							"validFeature", getObjectLabel((EObject) slot, context) }), //$NON-NLS-1$
						new Object[] { slot }));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validate whether a slot is allowed with respect to the metamodel
	 *
	 * @param slot
	 * @return true if it is allowed, false otherwise
	 */
	private boolean validate_validFeature(Slot slot) {
		final EClass contextClass = slot.getInstance().getEClass();
		final EList<EStructuralFeature> allowedFeatures = contextClass.getEAllStructuralFeatures();
		final boolean isAllowed = allowedFeatures.contains(slot.getEFeature());
		return isAllowed;
	}

	/**
	 * Validates the validMultiplicity constraint of '<em>Slot</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated NOT
	 */
	public boolean validateSlot_validMultiplicity(Slot slot, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_validMultiplicity(slot)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
					(Diagnostic.ERROR,
						DIAGNOSTIC_SOURCE,
						0,
						EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { //$NON-NLS-1$
							"validMultiplicity", getObjectLabel((EObject) slot, context) }), //$NON-NLS-1$
						new Object[] { slot }));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validate whether multiplicity holds
	 *
	 * @param slot
	 * @return true if it holds, false otherwise
	 */
	private boolean validate_validMultiplicity(Slot slot) {
		final int n = ((SlotImpl) slot).getValues().size();
		final EStructuralFeature feature = slot.getEFeature();
		// return feature.getLowerBound() <= n && (n <= feature.getUpperBound() || feature.getUpperBound() == -1);
		return feature.isMany() || n <= 1;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	public boolean validateAttributeSlot(AttributeSlot attributeSlot, DiagnosticChain diagnostics,
		Map<Object, Object> context) {
		if (!validate_NoCircularContainment((EObject) attributeSlot, diagnostics, context)) {
			return false;
		}
		boolean result = validate_EveryMultiplicityConforms((EObject) attributeSlot, diagnostics, context);
		if (result || diagnostics != null) {
			result &= validate_EveryDataValueConforms((EObject) attributeSlot, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_EveryReferenceIsContained((EObject) attributeSlot, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_EveryBidirectionalReferenceIsPaired((EObject) attributeSlot, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_EveryProxyResolves((EObject) attributeSlot, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_UniqueID((EObject) attributeSlot, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_EveryKeyUnique((EObject) attributeSlot, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_EveryMapEntryUnique((EObject) attributeSlot, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validateSlot_validFeature(attributeSlot, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validateSlot_validMultiplicity(attributeSlot, diagnostics, context);
		}
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	public boolean validateReferenceSlot(ReferenceSlot referenceSlot, DiagnosticChain diagnostics,
		Map<Object, Object> context) {
		if (!validate_NoCircularContainment((EObject) referenceSlot, diagnostics, context)) {
			return false;
		}
		boolean result = validate_EveryMultiplicityConforms((EObject) referenceSlot, diagnostics, context);
		if (result || diagnostics != null) {
			result &= validate_EveryDataValueConforms((EObject) referenceSlot, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_EveryReferenceIsContained((EObject) referenceSlot, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_EveryBidirectionalReferenceIsPaired((EObject) referenceSlot, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_EveryProxyResolves((EObject) referenceSlot, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_UniqueID((EObject) referenceSlot, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_EveryKeyUnique((EObject) referenceSlot, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_EveryMapEntryUnique((EObject) referenceSlot, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validateSlot_validFeature(referenceSlot, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validateSlot_validMultiplicity(referenceSlot, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validateReferenceSlot_validType(referenceSlot, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validateReferenceSlot_noDanglingReference(referenceSlot, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validateReferenceSlot_validOpposite(referenceSlot, diagnostics, context);
		}
		return result;
	}

	/**
	 * Validates the validType constraint of '<em>Reference Slot</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated NOT
	 */
	public boolean validateReferenceSlot_validType(ReferenceSlot referenceSlot, DiagnosticChain diagnostics,
		Map<Object, Object> context) {
		if (!validate_validType(referenceSlot)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
					(Diagnostic.ERROR,
						DIAGNOSTIC_SOURCE,
						0,
						EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "validType", //$NON-NLS-1$ //$NON-NLS-2$
							getObjectLabel((EObject) referenceSlot, context) }),
						new Object[] { referenceSlot }));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validate whether the values of a reference slot have valid type
	 *
	 * @param referenceSlot
	 * @return true if values have valid type, false otherwise
	 */
	private boolean validate_validType(ReferenceSlot referenceSlot) {

		final EClass referenceType = referenceSlot.getEReference().getEReferenceType();
		for (final Instance instance : referenceSlot.getValues()) {
			final EClass instanceType = instance.getEClass();
			final boolean validType = instanceType == referenceType
				|| instanceType.getEAllSuperTypes().contains(referenceType)
				|| referenceType == EcorePackage.eINSTANCE.getEObject();
			if (!validType) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Validates the noDanglingReference constraint of '<em>Reference Slot</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated NOT
	 */
	public boolean validateReferenceSlot_noDanglingReference(ReferenceSlot referenceSlot, DiagnosticChain diagnostics,
		Map<Object, Object> context) {
		if (!validate_noDanglingReference(referenceSlot)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
					(Diagnostic.ERROR,
						DIAGNOSTIC_SOURCE,
						0,
						EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { //$NON-NLS-1$
							"noDanglingReference", getObjectLabel((EObject) referenceSlot, context) }), //$NON-NLS-1$
						new Object[] { referenceSlot }));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validate whether reference slot contains a dangling reference
	 *
	 * @param referenceSlot
	 * @return true if there is not dangling reference, false otherwise
	 */
	private boolean validate_noDanglingReference(ReferenceSlot referenceSlot) {

		for (final Instance instance : referenceSlot.getValues()) {
			if (instance.getType() == null || instance.getType().getModel() == null) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Validates the validOpposite constraint of '<em>Reference Slot</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated NOT
	 */
	public boolean validateReferenceSlot_validOpposite(ReferenceSlot referenceSlot, DiagnosticChain diagnostics,
		Map<Object, Object> context) {
		if (!validate_validOpposite(referenceSlot)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
					(Diagnostic.ERROR,
						DIAGNOSTIC_SOURCE,
						0,
						EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { //$NON-NLS-1$
							"validOpposite", getObjectLabel((EObject) referenceSlot, context) }), //$NON-NLS-1$
						new Object[] { referenceSlot }));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validate whether opposite references are consistent with each other
	 *
	 * @param referenceSlot
	 * @return true if they are consistent, false otherwise
	 */
	private boolean validate_validOpposite(ReferenceSlot referenceSlot) {

		final Instance from = referenceSlot.getInstance();
		final EReference reference = referenceSlot.getEReference();
		final EReference opposite = reference.getEOpposite();
		if (opposite != null) {
			for (final Instance to : referenceSlot.getValues()) {
				final Object value = to.get(opposite);
				if (opposite.isMany()) {
					if (!((Collection) value).contains(from)) {
						return false;
					}
				}
				else {
					if (value != from) {
						return false;
					}
				}
			}
		}

		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	public boolean validateRepository(Repository repository, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject) repository, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	public boolean validateModelResource(ModelResource modelResource, DiagnosticChain diagnostics,
		Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject) modelResource, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	public boolean validateAbstractResource(AbstractResource abstractResource, DiagnosticChain diagnostics,
		Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject) abstractResource, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	public boolean validateMetamodelResource(MetamodelResource metamodelResource, DiagnosticChain diagnostics,
		Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject) metamodelResource, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	public boolean validateSet(Set<?> set, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	public boolean validateDiagnosticChain(DiagnosticChain diagnosticChain, DiagnosticChain diagnostics,
		Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	public boolean validateURI(URI uri, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	public boolean validateDiagnosticException(DiagnosticException diagnosticException, DiagnosticChain diagnostics,
		Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	public boolean validateMigrationException(MigrationException migrationException, DiagnosticChain diagnostics,
		Map<Object, Object> context) {
		return true;
	}

	/**
	 * Returns the resource locator that will be used to fetch messages for this validator's diagnostics.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return MigrationPlugin.INSTANCE;
	}

} // MigrationValidator
