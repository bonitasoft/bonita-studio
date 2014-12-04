/*******************************************************************************
 * Copyright (c) 2007, 2010 BMW Car IT, Technische Universitaet Muenchen, and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     BMW Car IT - Initial API and implementation
 *     Technische Universitaet Muenchen - Major refactoring and extension
 *******************************************************************************/
package org.eclipse.emf.edapt.spi.migration.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EClassImpl;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.util.EContentsEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.emf.edapt.internal.migration.impl.ModelValidator;
import org.eclipse.emf.edapt.internal.migration.impl.UpdatingList;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edapt.spi.migration.AttributeSlot;
import org.eclipse.emf.edapt.spi.migration.Instance;
import org.eclipse.emf.edapt.spi.migration.Metamodel;
import org.eclipse.emf.edapt.spi.migration.MigrationFactory;
import org.eclipse.emf.edapt.spi.migration.MigrationPackage;
import org.eclipse.emf.edapt.spi.migration.Model;
import org.eclipse.emf.edapt.spi.migration.ModelResource;
import org.eclipse.emf.edapt.spi.migration.ReferenceSlot;
import org.eclipse.emf.edapt.spi.migration.Slot;
import org.eclipse.emf.edapt.spi.migration.Type;
import org.eclipse.ocl.OCL;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.Query;
import org.eclipse.ocl.ecore.Constraint;
import org.eclipse.ocl.ecore.EcoreEnvironmentFactory;
import org.eclipse.ocl.expressions.OCLExpression;
import org.eclipse.ocl.helper.OCLHelper;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Instance</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.edapt.spi.migration.impl.InstanceImpl#getSlots <em>Slots</em>}</li>
 *   <li>{@link org.eclipse.emf.edapt.spi.migration.impl.InstanceImpl#getType <em>Type</em>}</li>
 *   <li>{@link org.eclipse.emf.edapt.spi.migration.impl.InstanceImpl#getReferences <em>References</em>}</li>
 *   <li>{@link org.eclipse.emf.edapt.spi.migration.impl.InstanceImpl#getUri <em>Uri</em>}</li>
 *   <li>{@link org.eclipse.emf.edapt.spi.migration.impl.InstanceImpl#getUuid <em>Uuid</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class InstanceImpl extends EObjectImpl implements Instance {
	/**
	 * The cached value of the '{@link #getSlots() <em>Slots</em>}' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getSlots()
	 * @generated
	 * @ordered
	 */
	protected EList<Slot> slots;

	/**
	 * The cached value of the '{@link #getReferences() <em>References</em>}' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getReferences()
	 * @generated
	 * @ordered
	 */
	protected EList<ReferenceSlot> references;

	/**
	 * The default value of the '{@link #getUri() <em>Uri</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getUri()
	 * @generated
	 * @ordered
	 */
	protected static final URI URI_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getUri() <em>Uri</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getUri()
	 * @generated
	 * @ordered
	 */
	protected URI uri = URI_EDEFAULT;

	/**
	 * The default value of the '{@link #getUuid() <em>Uuid</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getUuid()
	 * @generated
	 * @ordered
	 */
	protected static final String UUID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getUuid() <em>Uuid</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getUuid()
	 * @generated
	 * @ordered
	 */
	protected String uuid = UUID_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected InstanceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return MigrationPackage.Literals.INSTANCE;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Slot> getSlots() {
		if (slots == null) {
			slots = new EObjectContainmentWithInverseEList<Slot>(Slot.class, this, MigrationPackage.INSTANCE__SLOTS, MigrationPackage.SLOT__INSTANCE);
		}
		return slots;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Type getType() {
		if (eContainerFeatureID() != MigrationPackage.INSTANCE__TYPE) return null;
		return (Type)eContainer();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetType(Type newType, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newType, MigrationPackage.INSTANCE__TYPE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setType(Type newType) {
		if (newType != eInternalContainer() || (eContainerFeatureID() != MigrationPackage.INSTANCE__TYPE && newType != null)) {
			if (EcoreUtil.isAncestor(this, (EObject)newType))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newType != null)
				msgs = ((InternalEObject)newType).eInverseAdd(this, MigrationPackage.TYPE__INSTANCES, Type.class, msgs);
			msgs = basicSetType(newType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MigrationPackage.INSTANCE__TYPE, newType, newType));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ReferenceSlot> getReferences() {
		if (references == null) {
			references = new EObjectWithInverseResolvingEList.ManyInverse<ReferenceSlot>(ReferenceSlot.class, this, MigrationPackage.INSTANCE__REFERENCES, MigrationPackage.REFERENCE_SLOT__VALUES);
		}
		return references;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public URI getUri() {
		return uri;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setUri(URI newUri) {
		URI oldUri = uri;
		uri = newUri;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MigrationPackage.INSTANCE__URI, oldUri, uri));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String getUuid() {
		return uuid;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setUuid(String newUuid) {
		String oldUuid = uuid;
		uuid = newUuid;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MigrationPackage.INSTANCE__UUID, oldUuid, uuid));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public <V> V get(EStructuralFeature feature) {
		if (feature instanceof EAttribute) {
			return this.getAttributeValue((EAttribute) feature);
		} else if (feature instanceof EReference) {
			return this.getReferenceValue((EReference) feature);
		}
		return null;
	}

	/**
	 * Get the value of an instance's attribute
	 * 
	 * @param <V>
	 * @param attribute
	 * @return Value
	 */
	@SuppressWarnings("unchecked")
	private <V> V getAttributeValue(EAttribute attribute) {
		Slot slot = this.getSlot(attribute);
		if (slot == null) {
			if (attribute.isMany()) {
				return (V) new UpdatingList(this, attribute);
			} else if (attribute.getDefaultValue() != null) {
				return (V) attribute.getDefaultValue();
			}
			return null;
		}
		EList<Object> values = new UpdatingList(this, attribute,
				((AttributeSlot) slot).getValues());
		if (attribute.isMany()) {
			return (V) values;
		} else if (!values.isEmpty()) {
			return (V) values.get(0);
		}
		return null;
	}

	/**
	 * Get the value of an instance's reference
	 * 
	 * @param <V>
	 * @param reference
	 * @return Value
	 */
	@SuppressWarnings("unchecked")
	<V> V getReferenceValue(EReference reference) {
		Slot slot = this.getSlot(reference);
		if (slot == null) {
			if (reference.isMany()) {
				return (V) new UpdatingList(this, reference);
			} else if (reference.getDefaultValue() != null) {
				return (V) reference.getDefaultValue();
			}
			return null;
		}
		EList<Instance> values = new UpdatingList(this, reference,
				((ReferenceSlot) slot).getValues());
		if (reference.isMany()) {
			return (V) values;
		} else if (!values.isEmpty()) {
			return (V) values.get(0);
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void set(EStructuralFeature feature, Object newValue) {
		if (feature.isMany()) {
			Collection<?> oldValues = (Collection<?>) this.get(feature);
			for (Object value : oldValues) {
				this.remove(feature, value);
			}
			Collection<?> newValues = (Collection<?>) newValue;
			for (Object value : newValues) {
				this.add(feature, value);
			}
		} else {
			if (newValue instanceof List<?>) {
				throw new IllegalArgumentException(
						"Single value expected, but list found");
			}
			Object oldValue = this.get(feature);
			if (oldValue != newValue) {
				if (this.isSet(feature) && oldValue != null) {
					this.remove(feature, oldValue);
				}
				if (newValue != null) {
					this.add(feature, newValue);
				}
			}
		}

	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Slot getSlot(EStructuralFeature feature) {
		for (Slot slot : this.getSlots()) {
			if (feature == slot.getEFeature()) {
				return slot;
			}
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@SuppressWarnings("unchecked")
	public <V> V evaluate(String expression) throws MigrationException {
		Model model = this.getType().getModel();
		this.enableReflection();

		OCL<?, EClassifier, ?, ?, ?, ?, ?, ?, ?, Constraint, EClass, EObject> ocl;
		ocl = OCL.newInstance(EcoreEnvironmentFactory.INSTANCE);
		OCLHelper<EClassifier, ?, ?, Constraint> helper = ocl.createOCLHelper();

		helper.setContext(this.eClass());
		OCLExpression<EClassifier> query;
		try {
			query = helper.createQuery(expression);
		} catch (ParserException e) {
			throw new MigrationException("OCL expression '" + expression
					+ "' could not be parsed", e);
		}
		ocl.setExtentMap((Map) model.createExtentMap());

		// create a Query to evaluate our query expression
		Query<EClassifier, EClass, EObject> queryEval = ocl.createQuery(query);
		Object result = queryEval.evaluate(this);

		this.disableReflection();
		return (V) result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public EReference getContainerReference() {
		for (ReferenceSlot slot : this.getReferences()) {
			if (slot.getEReference().isContainment()) {
				return slot.getEReference();
			}
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public ModelResource getResource() {
		Model model = this.getType().getModel();
		Instance instance = this;
		while (instance.getContainer() != null) {
			for (ModelResource resource : model.getResources()) {
				if (resource.getRootInstances().contains(instance)) {
					return resource;
				}
			}
			instance = instance.getContainer();
		}
		for (ModelResource resource : model.getResources()) {
			if (resource.getRootInstances().contains(instance)) {
				return resource;
			}
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public boolean isProxy() {
		return this.getUri() != null;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void migrate(String className) {
		EClass eClass = checkAndGetClass(className);
		migrate(eClass);
	}

	private EClass checkAndGetClass(String className) {
		Metamodel metamodel = getType().getModel().getMetamodel();
		EClass eClass = metamodel.getEClass(className);
		if (eClass == null) {
			throw new IllegalArgumentException("Class " + className + " not found.");
		}
		return eClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public EList<Instance> getInverse(String referenceName) {
		EReference reference = checkAndGetReference(referenceName);
		return getInverse(reference);
	}

	private EReference checkAndGetReference(String referenceName) {
		EReference reference = getType().getModel().getMetamodel()
				.getEReference(referenceName);
		if (reference == null) {
			throw new IllegalArgumentException("Reference " + referenceName + " not found.");
		}
		return reference;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Instance getLink(String referenceName) {
		return (Instance) get(referenceName);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public EList<Instance> getLinks(String referenceName) {
		return (EList<Instance>) get(referenceName);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public boolean instanceOf(String className) {
		EClass eClass = checkAndGetClass(className);
		return instanceOf(eClass);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void add(String featureName, Object value) {
		EStructuralFeature feature = checkAndGetFeature(featureName);
		add(feature, value);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void remove(String featureName, Object value) {
		EStructuralFeature feature = checkAndGetFeature(featureName);
		remove(feature, value);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void add(String featureName, int index, Object value) {
		EStructuralFeature feature = checkAndGetFeature(featureName);
		add(feature, index, value);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Instance getLink(EReference reference) {
		return (Instance) get(reference);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public EList<Instance> getLinks(EReference reference) {
		return (EList<Instance>) get(reference);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Instance copy() {

		// mapping of originals to copies
		Map<Instance, Instance> map = new HashMap<Instance, Instance>();

		// copy tree structure
		Instance copy = copyTree(this, map);
		// copy cross references
		copyReferences(this, map);

		return copy;
	}

	/** Copy the tree structure with an instance as root. */
	private Instance copyTree(Instance original, Map<Instance, Instance> map) {
		EClass eClass = original.getEClass();
		Instance copi = getType().getModel().newInstance(eClass);
		for (EReference reference : eClass.getEAllReferences()) {
			if (reference.isContainment()) {
				if (reference.isMany()) {
					for (Instance child : original.getLinks(reference)) {
						copi.add(reference, copyTree(child, map));
					}
				} else {
					Instance child = original.get(reference);
					if (child != null) {
						copi.set(reference, copyTree(child, map));
					}
				}
			}
		}
		for (EAttribute attribute : eClass.getEAllAttributes()) {
			copi.set(attribute, original.get(attribute));
		}
		map.put(original, copi);
		return copi;
	}

	/** Copy cross references of an instance. */
	private void copyReferences(Instance original, Map<Instance, Instance> map) {
		EClass eClass = original.getEClass();
		Instance copi = map.get(original);
		for (EReference reference : eClass.getEAllReferences()) {
			if (!reference.isContainment()) {
				if (reference.isMany()) {
					if (reference.getEOpposite() == null
							|| reference.getEOpposite().isMany()) {
						for (Instance ref : original.getLinks(reference)) {
							if (map.get(ref) != null) {
								ref = map.get(ref);
							}
							copi.add(reference, ref);
						}
					}
				} else {
					if (reference.getEOpposite() == null
							|| !reference.getEOpposite().isContainment()) {
						Instance ref = original.get(reference);
						if (ref != null) {
							if (map.get(ref) != null) {
								ref = map.get(ref);
							}
							copi.set(reference, ref);
						}
					}
				}
			}
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@SuppressWarnings("unchecked")
	public EList<Instance> getInverse(EReference reference) {
		List<ReferenceSlot> slots = this.getReferences();
		EList<Instance> instances = new BasicEList<Instance>();
		for (ReferenceSlot slot : slots) {
			if (reference == slot.getEReference()) {
				instances.add(slot.getInstance());
			}
		}
		return instances;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void migrate(EClass eClass) {
		Type oldType = this.getType();
		if (eClass != oldType.getEClass()) {
			ModelImpl model = (ModelImpl) oldType.getModel();
			model.removeDeleteType(oldType, this);
			Type type = model.getCreateType(eClass);
			type.getInstances().add(this);
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public <V> V unset(EStructuralFeature feature) {
		V value = this.get(feature);
		if (this.isSet(feature)) {
			if (feature.isMany()) {
				this.set(feature, Collections.EMPTY_LIST);
			} else {
				this.set(feature, null);
			}
		}
		return value;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void add(EStructuralFeature feature, int index, Object value) {
		if (feature instanceof EAttribute) {
			EAttribute attribute = (EAttribute) feature;
			AttributeSlot attributeSlot = this
					.getCreateAttributeSlot(attribute);
			if (!attribute.isUnique()
					|| !attributeSlot.getValues().contains(value)) {
				attributeSlot.getValues().add(index, value);
			}
		} else {
			EReference reference = (EReference) feature;
			Instance target = (Instance) value;
			if (reference.isUnique() && contains(reference, target)) {
				return;
			}
			EReference opposite = reference.getEOpposite();
			if (opposite != null && reference.eContainer() != null) {
				// if opposite is single-valued, unset it before
				if (!opposite.isMany()) {
					target.unset(opposite);
				}
				ReferenceSlot oppositeSlot = ((InstanceImpl) target)
						.getCreateReferenceSlot(opposite);
				oppositeSlot.getValues().add(this);
			}
			ReferenceSlot referenceSlot = this
					.getCreateReferenceSlot(reference);
			referenceSlot.getValues().add(index, target);
		}
	}

	/**
	 * Get the slot for an instance's attribute (create one if there is none)
	 * 
	 * @param attribute
	 * @return Slot
	 */
	private AttributeSlot getCreateAttributeSlot(EAttribute attribute) {

		AttributeSlot attributeSlot = (AttributeSlot) this.getSlot(attribute);
		if (attributeSlot == null) {
			attributeSlot = MigrationFactory.eINSTANCE.createAttributeSlot();
			attributeSlot.setEAttribute(attribute);
			this.getSlots().add(attributeSlot);
		}
		return attributeSlot;
	}

	/**
	 * Get the slot for an instance's reference (create one if there is none)
	 * 
	 * @param reference
	 * @return Slot
	 */
	ReferenceSlot getCreateReferenceSlot(EReference reference) {

		ReferenceSlot referenceSlot = (ReferenceSlot) this.getSlot(reference);
		if (referenceSlot == null) {
			referenceSlot = MigrationFactory.eINSTANCE.createReferenceSlot();
			referenceSlot.setEReference(reference);
			this.getSlots().add(referenceSlot);
		}
		return referenceSlot;
	}

	/**
	 * Determine whether a value is contained in an instance's reference
	 * 
	 * @param reference
	 * @param value
	 * @return true if it is contained, false otherwise
	 */
	private boolean contains(EReference reference, Instance value) {
		ReferenceSlot referenceSlot = (ReferenceSlot) this.getSlot(reference);
		if (referenceSlot != null) {
			return referenceSlot.getValues().contains(value);
		}
		return false;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void remove(EStructuralFeature feature, Object value) {
		SlotImpl slot = (SlotImpl) this.getSlot(feature);
		int index = slot != null ? slot.getValues().indexOf(value) : 0;
		if (index >= 0) {
			this.remove(feature, index);
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public EClass getEClass() {
		return ((Instance) this).getType().getEClass();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void validate() {
		BasicDiagnostic chain = new BasicDiagnostic();
		this.validate(chain);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public boolean validate(DiagnosticChain chain) {
		final ModelValidator validator = new ModelValidator(this.getType()
				.getModel());
		Diagnostician diagnostician = new Diagnostician() {
			@Override
			public String getObjectLabel(EObject eObject) {
				EClass eClass = eObject.eClass();
				StringBuffer result = new StringBuffer(eClass.getName());
				if (eClass.getInstanceClassName() == null) {
					result.append('/');
					result.append(eClass.getEPackage().getNsURI());
					result.append('#');
					result.append(eClass.getName());
				}
				result.append('@');
				result.append(Integer.toHexString(eObject.hashCode()));

				return result.toString();
			}

			@Override
			public boolean validate(EClass eClass, EObject eObject,
					DiagnosticChain diagnostics, Map<Object, Object> context) {
				boolean result = validator.validate(eClass, eObject,
						diagnostics, context);
				if (result || diagnostics != null) {
					result &= doValidateContents(eObject, diagnostics, context);
				}
				return result;
			}
		};
		this.enableReflection();
		boolean result = diagnostician.validate(this, chain);
		this.disableReflection();
		return result;
	}

	/**
	 * Disable reflection
	 */
	private void disableReflection() {
		this.getType().getModel().setReflection(false);
	}

	/**
	 * Enable reflection
	 */
	private void enableReflection() {
		this.getType().getModel().setReflection(true);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public <V> V get(String featureName) {
		EStructuralFeature feature = checkAndGetFeature(featureName);
		return this.get(feature);
	}

	private EStructuralFeature checkAndGetFeature(String featureName) {
		EStructuralFeature feature = this.getEClass().getEStructuralFeature(
				featureName);
		if (feature == null) {
			throw new IllegalArgumentException("Feature " + featureName + " not found.");
		}
		return feature;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void set(String featureName, Object value) {
		EStructuralFeature feature = checkAndGetFeature(featureName);
		this.set(feature, value);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public boolean isSet(EStructuralFeature feature) {
		return this.getSlot(feature) != null;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public boolean instanceOf(EClass eClass) {
		return this.getEClass() == eClass
				|| this.getEClass().getEAllSuperTypes().contains(eClass);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Instance getContainer() {
		for (ReferenceSlot slot : this.getReferences()) {
			if (slot.getEReference().isContainment()) {
				return slot.getInstance();
			}
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public EList<Instance> getContents() {
		EList<Instance> contents = new BasicEList<Instance>();
		for (Slot slot : this.getSlots()) {
			if (slot instanceof ReferenceSlot) {
				ReferenceSlot referenceSlot = (ReferenceSlot) slot;
				if (referenceSlot.getEReference().isContainment()) {
					contents.addAll(referenceSlot.getValues());
				}
			}
		}
		return contents;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void add(EStructuralFeature feature, Object value) {
		SlotImpl slot = (SlotImpl) this.getSlot(feature);
		int index = slot != null ? slot.getValues().size() : 0;
		this.add(feature, index, value);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void remove(EStructuralFeature feature, int index) {
		if (feature instanceof EAttribute) {
			EAttribute attribute = (EAttribute) feature;
			this.removeDeleteAttribute(attribute, index);
		} else {
			EReference reference = (EReference) feature;
			EReference opposite = reference.getEOpposite();
			if (opposite != null && reference.eContainer() != null) {
				ReferenceSlot referenceSlot = (ReferenceSlot) this
						.getSlot(reference);
				Instance target = referenceSlot.getValues().get(index);
				int oppositeIndex = ((ReferenceSlot) target.getSlot(opposite))
						.getValues().indexOf(this);
				((InstanceImpl) target).removeDeleteReference(opposite,
						oppositeIndex);
			}
			this.removeDeleteReference(reference, index);
		}
	}

	/**
	 * Remove a value from an instance's attribute (delete slot if it is empty)
	 * 
	 * @param attribute
	 * @param index
	 */
	private void removeDeleteAttribute(EAttribute attribute, int index) {
		AttributeSlot attributeSlot = (AttributeSlot) this.getSlot(attribute);
		attributeSlot.getValues().remove(index);
		if (attributeSlot.getValues().isEmpty()) {
			this.getSlots().remove(attributeSlot);
		}
	}

	/**
	 * Remove a value from an instance's reference (delete slot if it is empty)
	 * 
	 * @param reference
	 * @param index
	 */
	void removeDeleteReference(EReference reference, int index) {

		ReferenceSlot referenceSlot = (ReferenceSlot) this.getSlot(reference);
		referenceSlot.getValues().remove(index);
		if (referenceSlot.getValues().isEmpty()) {
			this.getSlots().remove(referenceSlot);
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
		switch (featureID) {
			case MigrationPackage.INSTANCE__SLOTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getSlots()).basicAdd(otherEnd, msgs);
			case MigrationPackage.INSTANCE__TYPE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetType((Type)otherEnd, msgs);
			case MigrationPackage.INSTANCE__REFERENCES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getReferences()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
		switch (featureID) {
			case MigrationPackage.INSTANCE__SLOTS:
				return ((InternalEList<?>)getSlots()).basicRemove(otherEnd, msgs);
			case MigrationPackage.INSTANCE__TYPE:
				return basicSetType(null, msgs);
			case MigrationPackage.INSTANCE__REFERENCES:
				return ((InternalEList<?>)getReferences()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(
			NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case MigrationPackage.INSTANCE__TYPE:
				return eInternalContainer().eInverseRemove(this, MigrationPackage.TYPE__INSTANCES, Type.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case MigrationPackage.INSTANCE__SLOTS:
				return getSlots();
			case MigrationPackage.INSTANCE__TYPE:
				return getType();
			case MigrationPackage.INSTANCE__REFERENCES:
				return getReferences();
			case MigrationPackage.INSTANCE__URI:
				return getUri();
			case MigrationPackage.INSTANCE__UUID:
				return getUuid();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case MigrationPackage.INSTANCE__SLOTS:
				getSlots().clear();
				getSlots().addAll((Collection<? extends Slot>)newValue);
				return;
			case MigrationPackage.INSTANCE__TYPE:
				setType((Type)newValue);
				return;
			case MigrationPackage.INSTANCE__REFERENCES:
				getReferences().clear();
				getReferences().addAll((Collection<? extends ReferenceSlot>)newValue);
				return;
			case MigrationPackage.INSTANCE__URI:
				setUri((URI)newValue);
				return;
			case MigrationPackage.INSTANCE__UUID:
				setUuid((String)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case MigrationPackage.INSTANCE__SLOTS:
				getSlots().clear();
				return;
			case MigrationPackage.INSTANCE__TYPE:
				setType((Type)null);
				return;
			case MigrationPackage.INSTANCE__REFERENCES:
				getReferences().clear();
				return;
			case MigrationPackage.INSTANCE__URI:
				setUri(URI_EDEFAULT);
				return;
			case MigrationPackage.INSTANCE__UUID:
				setUuid(UUID_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case MigrationPackage.INSTANCE__SLOTS:
				return slots != null && !slots.isEmpty();
			case MigrationPackage.INSTANCE__TYPE:
				return getType() != null;
			case MigrationPackage.INSTANCE__REFERENCES:
				return references != null && !references.isEmpty();
			case MigrationPackage.INSTANCE__URI:
				return URI_EDEFAULT == null ? uri != null : !URI_EDEFAULT.equals(uri);
			case MigrationPackage.INSTANCE__UUID:
				return UUID_EDEFAULT == null ? uuid != null : !UUID_EDEFAULT.equals(uuid);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer();
		result.append("Instance of ");
		result.append(getType().getEClass().getName());
		if(getUri() != null) {
			result.append(" (proxy: " + getUri() + ")");
		} else if(getUuid() != null) {
			result.append(" (uuid: " + getUuid() + ")");
		}
		return result.toString();
	}

	// overwritten

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EClass eClass() {
		if (getType().getModel().isReflection()) {
			return getEClass();
		}
		return super.eClass();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object eGet(EStructuralFeature feature, boolean resolve,
			boolean coreType) {
		if (getType().getModel().isReflection()) {
			return get(feature);
		}
		return super.eGet(feature, resolve, coreType);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean eIsSet(EStructuralFeature feature) {
		if (getType().getModel().isReflection()) {
			return getSlot(feature) != null;
		}
		return super.eIsSet(feature);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EList<EObject> eContents() {
		if (getType().getModel().isReflection()) {
			EStructuralFeature[] features = ((EClassImpl.FeatureSubsetSupplier) getEClass()
					.getEAllStructuralFeatures()).containments();
			return new EContentsEList<EObject>(this, features);
		}
		return super.eContents();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EList<EObject> eCrossReferences() {
		if (getType().getModel().isReflection()) {
			EStructuralFeature[] features = ((EClassImpl.FeatureSubsetSupplier) getEClass()
					.getEAllStructuralFeatures()).crossReferences();
			return new EContentsEList<EObject>(this, features);
		}
		return super.eCrossReferences();
	}
} // InstanceImpl
