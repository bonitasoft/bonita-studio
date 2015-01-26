package org.eclipse.emf.edapt.common;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EStringToStringMapEntryImpl;

/**
 * Helper methods to create metamodel elements.
 * 
 * @author herrmama
 * @author $Author$
 * @version $Rev$
 * @levd.rating RED Rev:
 */
public final class MetamodelFactory {

	/** Create a new package in a package. */
	public static EPackage newEPackage(EPackage ePackage, String name,
			String nsPrefix, String nsURI) {
		EPackage subPackage = EcoreFactory.eINSTANCE.createEPackage();
		subPackage.setName(name);
		subPackage.setNsPrefix(nsPrefix);
		subPackage.setNsURI(nsURI);
		ePackage.getESubpackages().add(subPackage);
		return subPackage;
	}

	/** Create a new class in a package. */
	public static EClass newEClass(EPackage ePackage, String name,
			Collection<EClass> superClasses, boolean abstr) {
		EClass eClass = EcoreFactory.eINSTANCE.createEClass();
		eClass.setName(name);
		eClass.getESuperTypes().addAll(superClasses);
		eClass.setAbstract(abstr);
		ePackage.getEClassifiers().add(eClass);
		return eClass;
	}

	/** Create a new class in a package. */
	public static EClass newEClass(EPackage ePackage, String name,
			Collection<EClass> superClasses) {
		return newEClass(ePackage, name, superClasses, false);
	}

	/** Create a new class in a package. */
	public static EClass newEClass(EPackage ePackage, String name,
			EClass superClass) {
		return newEClass(ePackage, name, Collections.singletonList(superClass));
	}

	/** Create a new class in a package. */
	public static EClass newEClass(EPackage ePackage, String name) {
		return newEClass(ePackage, name, Collections.<EClass> emptyList());
	}

	/** Create a new enumeration in a package. */
	public static EEnum newEEnum(EPackage ePackage, String name) {
		EEnum eEnum = EcoreFactory.eINSTANCE.createEEnum();
		eEnum.setName(name);
		ePackage.getEClassifiers().add(eEnum);
		return eEnum;
	}

	/** Create a new data type in a package. */
	public static EDataType newEDataType(EPackage ePackage, String name,
			String className) {
		EDataType eDataType = EcoreFactory.eINSTANCE.createEDataType();
		eDataType.setName(name);
		eDataType.setInstanceTypeName(className);
		ePackage.getEClassifiers().add(eDataType);
		return eDataType;
	}

	/** Create a new attribute in a class. */
	public static EAttribute newEAttribute(EClass eClass, String name,
			EDataType type, int lowerBound, int upperBound, String defaultValue) {
		EAttribute eAttribute = EcoreFactory.eINSTANCE.createEAttribute();
		initTypedElement(eAttribute, name, type, lowerBound, upperBound);
		eAttribute.setDefaultValueLiteral(defaultValue);
		eClass.getEStructuralFeatures().add(eAttribute);
		return eAttribute;
	}

	/** Create a new attribute in a class. */
	public static EAttribute newEAttribute(EClass eClass, String name,
			EDataType type) {
		return newEAttribute(eClass, name, type, 0, 1, null);
	}

	/** Create a new attribute in a class. */
	public static EAttribute newEAttribute(EClass eClass, String name,
			EDataType type, int lowerBound, int upperBound) {
		return newEAttribute(eClass, name, type, lowerBound, upperBound, null);
	}

	/** Create a new reference in a class. */
	public static EReference newEReference(EClass eClass, String name,
			EClass type, int lowerBound, int upperBound, boolean containment) {
		EReference eReference = EcoreFactory.eINSTANCE.createEReference();
		initTypedElement(eReference, name, type, lowerBound, upperBound);
		eReference.setContainment(containment);
		eClass.getEStructuralFeatures().add(eReference);
		return eReference;
	}

	/** Create a new reference in a class. */
	public static EReference newEReference(EClass eClass, String name,
			EClass type, int lowerBound, int upperBound) {
		return newEReference(eClass, name, type, lowerBound, upperBound, false);
	}

	/** Create a new reference in a class. */
	public static EReference newEReference(EClass eClass, String name,
			EClass type) {
		return newEReference(eClass, name, type, 0, 1);
	}

	/** Create a new literal in an enumeration. */
	public static EEnumLiteral newEEnumLiteral(EEnum eEnum, String name) {
		EEnumLiteral eEnumLiteral = EcoreFactory.eINSTANCE.createEEnumLiteral();
		eEnumLiteral.setName(name);
		eEnum.getELiterals().add(eEnumLiteral);
		return eEnumLiteral;
	}

	/** Create a new operation in a class. */
	public static EOperation newEOperation(EClass eClass, String name,
			EClassifier type, int lowerBound, int upperBound) {
		EOperation eOperation = EcoreFactory.eINSTANCE.createEOperation();
		initTypedElement(eOperation, name, type, lowerBound, upperBound);
		eClass.getEOperations().add(eOperation);
		return eOperation;
	
	}

	/** Create a new operation in a class. */
	public static EOperation newEOperation(EClass eClass, String name,
			EClassifier type) {
		return newEOperation(eClass, name, type, 0, 1);
	}

	/** Create a new parameter in an operation. */
	public static EParameter newEParameter(EOperation eOperation, String name,
			EClassifier type, int lowerBound, int upperBound) {
		EParameter eParameter = EcoreFactory.eINSTANCE.createEParameter();
		initTypedElement(eParameter, name, type, lowerBound, upperBound);
		eOperation.getEParameters().add(eParameter);
		return eParameter;
	}

	/** Create a new parameter in an operation. */
	public static EParameter newEParameter(EOperation eOperation, String name,
			EClassifier type) {
		return newEParameter(eOperation, name, type, 0, 1);
	}

	/** Create a new annotation in an element. */
	public static EAnnotation newEAnnotation(EModelElement eModelElement,
			String source) {
		EAnnotation eAnnotation = EcoreFactory.eINSTANCE.createEAnnotation();
		eAnnotation.setSource(source);
		eModelElement.getEAnnotations().add(eAnnotation);
		return eAnnotation;
	}

	/** Create a new entry in an annotation. */
	public static EStringToStringMapEntryImpl newEStringToStringMapEntry(
			EAnnotation eAnnotation, String key, String value) {
		EStringToStringMapEntryImpl entry = (EStringToStringMapEntryImpl) EcoreFactory.eINSTANCE
				.create(EcorePackage.eINSTANCE.getEStringToStringMapEntry());
		entry.setKey(key);
		entry.setValue(value);
		eAnnotation.getDetails().add(entry);
		return entry;
	}

	/** Initialize a feature. */
	private static void initTypedElement(ETypedElement eTypedElement,
			String name, EClassifier type, int lowerBound, int upperBound) {
		eTypedElement.setName(name);
		eTypedElement.setEType(type);
		eTypedElement.setLowerBound(lowerBound);
		eTypedElement.setUpperBound(upperBound);
	}
}
