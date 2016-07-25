/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.wfmc._2002.xpdl1.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.wfmc._2002.xpdl1.ArrayTypeType;
import org.wfmc._2002.xpdl1.BasicTypeType;
import org.wfmc._2002.xpdl1.DeclaredTypeType;
import org.wfmc._2002.xpdl1.EnumerationTypeType;
import org.wfmc._2002.xpdl1.ExternalReferenceType;
import org.wfmc._2002.xpdl1.ListTypeType;
import org.wfmc._2002.xpdl1.MemberType;
import org.wfmc._2002.xpdl1.RecordTypeType;
import org.wfmc._2002.xpdl1.SchemaTypeType;
import org.wfmc._2002.xpdl1.UnionTypeType;
import org.wfmc._2002.xpdl1.Xpdl1Package;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Member Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.MemberTypeImpl#getBasicType <em>Basic Type</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.MemberTypeImpl#getDeclaredType <em>Declared Type</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.MemberTypeImpl#getSchemaType <em>Schema Type</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.MemberTypeImpl#getExternalReference <em>External Reference</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.MemberTypeImpl#getRecordType <em>Record Type</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.MemberTypeImpl#getUnionType <em>Union Type</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.MemberTypeImpl#getEnumerationType <em>Enumeration Type</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.MemberTypeImpl#getArrayType <em>Array Type</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.MemberTypeImpl#getListType <em>List Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MemberTypeImpl extends EObjectImpl implements MemberType {
	/**
	 * The cached value of the '{@link #getBasicType() <em>Basic Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBasicType()
	 * @generated
	 * @ordered
	 */
	protected BasicTypeType basicType;

	/**
	 * The cached value of the '{@link #getDeclaredType() <em>Declared Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDeclaredType()
	 * @generated
	 * @ordered
	 */
	protected DeclaredTypeType declaredType;

	/**
	 * The cached value of the '{@link #getSchemaType() <em>Schema Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSchemaType()
	 * @generated
	 * @ordered
	 */
	protected SchemaTypeType schemaType;

	/**
	 * The cached value of the '{@link #getExternalReference() <em>External Reference</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExternalReference()
	 * @generated
	 * @ordered
	 */
	protected ExternalReferenceType externalReference;

	/**
	 * The cached value of the '{@link #getRecordType() <em>Record Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRecordType()
	 * @generated
	 * @ordered
	 */
	protected RecordTypeType recordType;

	/**
	 * The cached value of the '{@link #getUnionType() <em>Union Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUnionType()
	 * @generated
	 * @ordered
	 */
	protected UnionTypeType unionType;

	/**
	 * The cached value of the '{@link #getEnumerationType() <em>Enumeration Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEnumerationType()
	 * @generated
	 * @ordered
	 */
	protected EnumerationTypeType enumerationType;

	/**
	 * The cached value of the '{@link #getArrayType() <em>Array Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getArrayType()
	 * @generated
	 * @ordered
	 */
	protected ArrayTypeType arrayType;

	/**
	 * The cached value of the '{@link #getListType() <em>List Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getListType()
	 * @generated
	 * @ordered
	 */
	protected ListTypeType listType;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MemberTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Xpdl1Package.Literals.MEMBER_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BasicTypeType getBasicType() {
		return basicType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetBasicType(BasicTypeType newBasicType, NotificationChain msgs) {
		BasicTypeType oldBasicType = basicType;
		basicType = newBasicType;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, Xpdl1Package.MEMBER_TYPE__BASIC_TYPE, oldBasicType, newBasicType);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBasicType(BasicTypeType newBasicType) {
		if (newBasicType != basicType) {
			NotificationChain msgs = null;
			if (basicType != null)
				msgs = ((InternalEObject)basicType).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - Xpdl1Package.MEMBER_TYPE__BASIC_TYPE, null, msgs);
			if (newBasicType != null)
				msgs = ((InternalEObject)newBasicType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - Xpdl1Package.MEMBER_TYPE__BASIC_TYPE, null, msgs);
			msgs = basicSetBasicType(newBasicType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Xpdl1Package.MEMBER_TYPE__BASIC_TYPE, newBasicType, newBasicType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DeclaredTypeType getDeclaredType() {
		return declaredType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDeclaredType(DeclaredTypeType newDeclaredType, NotificationChain msgs) {
		DeclaredTypeType oldDeclaredType = declaredType;
		declaredType = newDeclaredType;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, Xpdl1Package.MEMBER_TYPE__DECLARED_TYPE, oldDeclaredType, newDeclaredType);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDeclaredType(DeclaredTypeType newDeclaredType) {
		if (newDeclaredType != declaredType) {
			NotificationChain msgs = null;
			if (declaredType != null)
				msgs = ((InternalEObject)declaredType).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - Xpdl1Package.MEMBER_TYPE__DECLARED_TYPE, null, msgs);
			if (newDeclaredType != null)
				msgs = ((InternalEObject)newDeclaredType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - Xpdl1Package.MEMBER_TYPE__DECLARED_TYPE, null, msgs);
			msgs = basicSetDeclaredType(newDeclaredType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Xpdl1Package.MEMBER_TYPE__DECLARED_TYPE, newDeclaredType, newDeclaredType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SchemaTypeType getSchemaType() {
		return schemaType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSchemaType(SchemaTypeType newSchemaType, NotificationChain msgs) {
		SchemaTypeType oldSchemaType = schemaType;
		schemaType = newSchemaType;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, Xpdl1Package.MEMBER_TYPE__SCHEMA_TYPE, oldSchemaType, newSchemaType);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSchemaType(SchemaTypeType newSchemaType) {
		if (newSchemaType != schemaType) {
			NotificationChain msgs = null;
			if (schemaType != null)
				msgs = ((InternalEObject)schemaType).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - Xpdl1Package.MEMBER_TYPE__SCHEMA_TYPE, null, msgs);
			if (newSchemaType != null)
				msgs = ((InternalEObject)newSchemaType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - Xpdl1Package.MEMBER_TYPE__SCHEMA_TYPE, null, msgs);
			msgs = basicSetSchemaType(newSchemaType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Xpdl1Package.MEMBER_TYPE__SCHEMA_TYPE, newSchemaType, newSchemaType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExternalReferenceType getExternalReference() {
		return externalReference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetExternalReference(ExternalReferenceType newExternalReference, NotificationChain msgs) {
		ExternalReferenceType oldExternalReference = externalReference;
		externalReference = newExternalReference;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, Xpdl1Package.MEMBER_TYPE__EXTERNAL_REFERENCE, oldExternalReference, newExternalReference);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExternalReference(ExternalReferenceType newExternalReference) {
		if (newExternalReference != externalReference) {
			NotificationChain msgs = null;
			if (externalReference != null)
				msgs = ((InternalEObject)externalReference).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - Xpdl1Package.MEMBER_TYPE__EXTERNAL_REFERENCE, null, msgs);
			if (newExternalReference != null)
				msgs = ((InternalEObject)newExternalReference).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - Xpdl1Package.MEMBER_TYPE__EXTERNAL_REFERENCE, null, msgs);
			msgs = basicSetExternalReference(newExternalReference, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Xpdl1Package.MEMBER_TYPE__EXTERNAL_REFERENCE, newExternalReference, newExternalReference));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RecordTypeType getRecordType() {
		return recordType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRecordType(RecordTypeType newRecordType, NotificationChain msgs) {
		RecordTypeType oldRecordType = recordType;
		recordType = newRecordType;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, Xpdl1Package.MEMBER_TYPE__RECORD_TYPE, oldRecordType, newRecordType);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRecordType(RecordTypeType newRecordType) {
		if (newRecordType != recordType) {
			NotificationChain msgs = null;
			if (recordType != null)
				msgs = ((InternalEObject)recordType).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - Xpdl1Package.MEMBER_TYPE__RECORD_TYPE, null, msgs);
			if (newRecordType != null)
				msgs = ((InternalEObject)newRecordType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - Xpdl1Package.MEMBER_TYPE__RECORD_TYPE, null, msgs);
			msgs = basicSetRecordType(newRecordType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Xpdl1Package.MEMBER_TYPE__RECORD_TYPE, newRecordType, newRecordType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UnionTypeType getUnionType() {
		return unionType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetUnionType(UnionTypeType newUnionType, NotificationChain msgs) {
		UnionTypeType oldUnionType = unionType;
		unionType = newUnionType;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, Xpdl1Package.MEMBER_TYPE__UNION_TYPE, oldUnionType, newUnionType);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUnionType(UnionTypeType newUnionType) {
		if (newUnionType != unionType) {
			NotificationChain msgs = null;
			if (unionType != null)
				msgs = ((InternalEObject)unionType).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - Xpdl1Package.MEMBER_TYPE__UNION_TYPE, null, msgs);
			if (newUnionType != null)
				msgs = ((InternalEObject)newUnionType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - Xpdl1Package.MEMBER_TYPE__UNION_TYPE, null, msgs);
			msgs = basicSetUnionType(newUnionType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Xpdl1Package.MEMBER_TYPE__UNION_TYPE, newUnionType, newUnionType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EnumerationTypeType getEnumerationType() {
		return enumerationType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetEnumerationType(EnumerationTypeType newEnumerationType, NotificationChain msgs) {
		EnumerationTypeType oldEnumerationType = enumerationType;
		enumerationType = newEnumerationType;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, Xpdl1Package.MEMBER_TYPE__ENUMERATION_TYPE, oldEnumerationType, newEnumerationType);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEnumerationType(EnumerationTypeType newEnumerationType) {
		if (newEnumerationType != enumerationType) {
			NotificationChain msgs = null;
			if (enumerationType != null)
				msgs = ((InternalEObject)enumerationType).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - Xpdl1Package.MEMBER_TYPE__ENUMERATION_TYPE, null, msgs);
			if (newEnumerationType != null)
				msgs = ((InternalEObject)newEnumerationType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - Xpdl1Package.MEMBER_TYPE__ENUMERATION_TYPE, null, msgs);
			msgs = basicSetEnumerationType(newEnumerationType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Xpdl1Package.MEMBER_TYPE__ENUMERATION_TYPE, newEnumerationType, newEnumerationType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ArrayTypeType getArrayType() {
		return arrayType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetArrayType(ArrayTypeType newArrayType, NotificationChain msgs) {
		ArrayTypeType oldArrayType = arrayType;
		arrayType = newArrayType;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, Xpdl1Package.MEMBER_TYPE__ARRAY_TYPE, oldArrayType, newArrayType);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setArrayType(ArrayTypeType newArrayType) {
		if (newArrayType != arrayType) {
			NotificationChain msgs = null;
			if (arrayType != null)
				msgs = ((InternalEObject)arrayType).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - Xpdl1Package.MEMBER_TYPE__ARRAY_TYPE, null, msgs);
			if (newArrayType != null)
				msgs = ((InternalEObject)newArrayType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - Xpdl1Package.MEMBER_TYPE__ARRAY_TYPE, null, msgs);
			msgs = basicSetArrayType(newArrayType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Xpdl1Package.MEMBER_TYPE__ARRAY_TYPE, newArrayType, newArrayType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ListTypeType getListType() {
		return listType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetListType(ListTypeType newListType, NotificationChain msgs) {
		ListTypeType oldListType = listType;
		listType = newListType;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, Xpdl1Package.MEMBER_TYPE__LIST_TYPE, oldListType, newListType);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setListType(ListTypeType newListType) {
		if (newListType != listType) {
			NotificationChain msgs = null;
			if (listType != null)
				msgs = ((InternalEObject)listType).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - Xpdl1Package.MEMBER_TYPE__LIST_TYPE, null, msgs);
			if (newListType != null)
				msgs = ((InternalEObject)newListType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - Xpdl1Package.MEMBER_TYPE__LIST_TYPE, null, msgs);
			msgs = basicSetListType(newListType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Xpdl1Package.MEMBER_TYPE__LIST_TYPE, newListType, newListType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case Xpdl1Package.MEMBER_TYPE__BASIC_TYPE:
				return basicSetBasicType(null, msgs);
			case Xpdl1Package.MEMBER_TYPE__DECLARED_TYPE:
				return basicSetDeclaredType(null, msgs);
			case Xpdl1Package.MEMBER_TYPE__SCHEMA_TYPE:
				return basicSetSchemaType(null, msgs);
			case Xpdl1Package.MEMBER_TYPE__EXTERNAL_REFERENCE:
				return basicSetExternalReference(null, msgs);
			case Xpdl1Package.MEMBER_TYPE__RECORD_TYPE:
				return basicSetRecordType(null, msgs);
			case Xpdl1Package.MEMBER_TYPE__UNION_TYPE:
				return basicSetUnionType(null, msgs);
			case Xpdl1Package.MEMBER_TYPE__ENUMERATION_TYPE:
				return basicSetEnumerationType(null, msgs);
			case Xpdl1Package.MEMBER_TYPE__ARRAY_TYPE:
				return basicSetArrayType(null, msgs);
			case Xpdl1Package.MEMBER_TYPE__LIST_TYPE:
				return basicSetListType(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case Xpdl1Package.MEMBER_TYPE__BASIC_TYPE:
				return getBasicType();
			case Xpdl1Package.MEMBER_TYPE__DECLARED_TYPE:
				return getDeclaredType();
			case Xpdl1Package.MEMBER_TYPE__SCHEMA_TYPE:
				return getSchemaType();
			case Xpdl1Package.MEMBER_TYPE__EXTERNAL_REFERENCE:
				return getExternalReference();
			case Xpdl1Package.MEMBER_TYPE__RECORD_TYPE:
				return getRecordType();
			case Xpdl1Package.MEMBER_TYPE__UNION_TYPE:
				return getUnionType();
			case Xpdl1Package.MEMBER_TYPE__ENUMERATION_TYPE:
				return getEnumerationType();
			case Xpdl1Package.MEMBER_TYPE__ARRAY_TYPE:
				return getArrayType();
			case Xpdl1Package.MEMBER_TYPE__LIST_TYPE:
				return getListType();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case Xpdl1Package.MEMBER_TYPE__BASIC_TYPE:
				setBasicType((BasicTypeType)newValue);
				return;
			case Xpdl1Package.MEMBER_TYPE__DECLARED_TYPE:
				setDeclaredType((DeclaredTypeType)newValue);
				return;
			case Xpdl1Package.MEMBER_TYPE__SCHEMA_TYPE:
				setSchemaType((SchemaTypeType)newValue);
				return;
			case Xpdl1Package.MEMBER_TYPE__EXTERNAL_REFERENCE:
				setExternalReference((ExternalReferenceType)newValue);
				return;
			case Xpdl1Package.MEMBER_TYPE__RECORD_TYPE:
				setRecordType((RecordTypeType)newValue);
				return;
			case Xpdl1Package.MEMBER_TYPE__UNION_TYPE:
				setUnionType((UnionTypeType)newValue);
				return;
			case Xpdl1Package.MEMBER_TYPE__ENUMERATION_TYPE:
				setEnumerationType((EnumerationTypeType)newValue);
				return;
			case Xpdl1Package.MEMBER_TYPE__ARRAY_TYPE:
				setArrayType((ArrayTypeType)newValue);
				return;
			case Xpdl1Package.MEMBER_TYPE__LIST_TYPE:
				setListType((ListTypeType)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case Xpdl1Package.MEMBER_TYPE__BASIC_TYPE:
				setBasicType((BasicTypeType)null);
				return;
			case Xpdl1Package.MEMBER_TYPE__DECLARED_TYPE:
				setDeclaredType((DeclaredTypeType)null);
				return;
			case Xpdl1Package.MEMBER_TYPE__SCHEMA_TYPE:
				setSchemaType((SchemaTypeType)null);
				return;
			case Xpdl1Package.MEMBER_TYPE__EXTERNAL_REFERENCE:
				setExternalReference((ExternalReferenceType)null);
				return;
			case Xpdl1Package.MEMBER_TYPE__RECORD_TYPE:
				setRecordType((RecordTypeType)null);
				return;
			case Xpdl1Package.MEMBER_TYPE__UNION_TYPE:
				setUnionType((UnionTypeType)null);
				return;
			case Xpdl1Package.MEMBER_TYPE__ENUMERATION_TYPE:
				setEnumerationType((EnumerationTypeType)null);
				return;
			case Xpdl1Package.MEMBER_TYPE__ARRAY_TYPE:
				setArrayType((ArrayTypeType)null);
				return;
			case Xpdl1Package.MEMBER_TYPE__LIST_TYPE:
				setListType((ListTypeType)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case Xpdl1Package.MEMBER_TYPE__BASIC_TYPE:
				return basicType != null;
			case Xpdl1Package.MEMBER_TYPE__DECLARED_TYPE:
				return declaredType != null;
			case Xpdl1Package.MEMBER_TYPE__SCHEMA_TYPE:
				return schemaType != null;
			case Xpdl1Package.MEMBER_TYPE__EXTERNAL_REFERENCE:
				return externalReference != null;
			case Xpdl1Package.MEMBER_TYPE__RECORD_TYPE:
				return recordType != null;
			case Xpdl1Package.MEMBER_TYPE__UNION_TYPE:
				return unionType != null;
			case Xpdl1Package.MEMBER_TYPE__ENUMERATION_TYPE:
				return enumerationType != null;
			case Xpdl1Package.MEMBER_TYPE__ARRAY_TYPE:
				return arrayType != null;
			case Xpdl1Package.MEMBER_TYPE__LIST_TYPE:
				return listType != null;
		}
		return super.eIsSet(featureID);
	}

} //MemberTypeImpl
