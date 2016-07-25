/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.omg.spec.bpmn.model.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;

import org.omg.spec.bpmn.di.BPMNDiagram;

import org.omg.spec.bpmn.model.ModelPackage;
import org.omg.spec.bpmn.model.TDefinitions;
import org.omg.spec.bpmn.model.TExtension;
import org.omg.spec.bpmn.model.TImport;
import org.omg.spec.bpmn.model.TRelationship;
import org.omg.spec.bpmn.model.TRootElement;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>TDefinitions</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TDefinitionsImpl#getImport <em>Import</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TDefinitionsImpl#getExtension <em>Extension</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TDefinitionsImpl#getRootElementGroup <em>Root Element Group</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TDefinitionsImpl#getRootElement <em>Root Element</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TDefinitionsImpl#getBPMNDiagram <em>BPMN Diagram</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TDefinitionsImpl#getRelationship <em>Relationship</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TDefinitionsImpl#getExporter <em>Exporter</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TDefinitionsImpl#getExporterVersion <em>Exporter Version</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TDefinitionsImpl#getExpressionLanguage <em>Expression Language</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TDefinitionsImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TDefinitionsImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TDefinitionsImpl#getTargetNamespace <em>Target Namespace</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TDefinitionsImpl#getTypeLanguage <em>Type Language</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TDefinitionsImpl#getAnyAttribute <em>Any Attribute</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TDefinitionsImpl extends EObjectImpl implements TDefinitions {
	/**
	 * The cached value of the '{@link #getImport() <em>Import</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImport()
	 * @generated
	 * @ordered
	 */
	protected EList<TImport> import_;

	/**
	 * The cached value of the '{@link #getExtension() <em>Extension</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExtension()
	 * @generated
	 * @ordered
	 */
	protected EList<TExtension> extension;

	/**
	 * The cached value of the '{@link #getRootElementGroup() <em>Root Element Group</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRootElementGroup()
	 * @generated
	 * @ordered
	 */
	protected FeatureMap rootElementGroup;

	/**
	 * The cached value of the '{@link #getBPMNDiagram() <em>BPMN Diagram</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBPMNDiagram()
	 * @generated
	 * @ordered
	 */
	protected EList<BPMNDiagram> bPMNDiagram;

	/**
	 * The cached value of the '{@link #getRelationship() <em>Relationship</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRelationship()
	 * @generated
	 * @ordered
	 */
	protected EList<TRelationship> relationship;

	/**
	 * The default value of the '{@link #getExporter() <em>Exporter</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExporter()
	 * @generated
	 * @ordered
	 */
	protected static final String EXPORTER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getExporter() <em>Exporter</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExporter()
	 * @generated
	 * @ordered
	 */
	protected String exporter = EXPORTER_EDEFAULT;

	/**
	 * The default value of the '{@link #getExporterVersion() <em>Exporter Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExporterVersion()
	 * @generated
	 * @ordered
	 */
	protected static final String EXPORTER_VERSION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getExporterVersion() <em>Exporter Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExporterVersion()
	 * @generated
	 * @ordered
	 */
	protected String exporterVersion = EXPORTER_VERSION_EDEFAULT;

	/**
	 * The default value of the '{@link #getExpressionLanguage() <em>Expression Language</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExpressionLanguage()
	 * @generated
	 * @ordered
	 */
	protected static final String EXPRESSION_LANGUAGE_EDEFAULT = "http://www.w3.org/1999/XPath";

	/**
	 * The cached value of the '{@link #getExpressionLanguage() <em>Expression Language</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExpressionLanguage()
	 * @generated
	 * @ordered
	 */
	protected String expressionLanguage = EXPRESSION_LANGUAGE_EDEFAULT;

	/**
	 * This is true if the Expression Language attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean expressionLanguageESet;

	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected String id = ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getTargetNamespace() <em>Target Namespace</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTargetNamespace()
	 * @generated
	 * @ordered
	 */
	protected static final String TARGET_NAMESPACE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTargetNamespace() <em>Target Namespace</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTargetNamespace()
	 * @generated
	 * @ordered
	 */
	protected String targetNamespace = TARGET_NAMESPACE_EDEFAULT;

	/**
	 * The default value of the '{@link #getTypeLanguage() <em>Type Language</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTypeLanguage()
	 * @generated
	 * @ordered
	 */
	protected static final String TYPE_LANGUAGE_EDEFAULT = "http://www.w3.org/2001/XMLSchema";

	/**
	 * The cached value of the '{@link #getTypeLanguage() <em>Type Language</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTypeLanguage()
	 * @generated
	 * @ordered
	 */
	protected String typeLanguage = TYPE_LANGUAGE_EDEFAULT;

	/**
	 * This is true if the Type Language attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean typeLanguageESet;

	/**
	 * The cached value of the '{@link #getAnyAttribute() <em>Any Attribute</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAnyAttribute()
	 * @generated
	 * @ordered
	 */
	protected FeatureMap anyAttribute;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TDefinitionsImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.TDEFINITIONS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TImport> getImport() {
		if (import_ == null) {
			import_ = new EObjectContainmentEList<TImport>(TImport.class, this, ModelPackage.TDEFINITIONS__IMPORT);
		}
		return import_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TExtension> getExtension() {
		if (extension == null) {
			extension = new EObjectContainmentEList<TExtension>(TExtension.class, this, ModelPackage.TDEFINITIONS__EXTENSION);
		}
		return extension;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getRootElementGroup() {
		if (rootElementGroup == null) {
			rootElementGroup = new BasicFeatureMap(this, ModelPackage.TDEFINITIONS__ROOT_ELEMENT_GROUP);
		}
		return rootElementGroup;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TRootElement> getRootElement() {
		return getRootElementGroup().list(ModelPackage.Literals.TDEFINITIONS__ROOT_ELEMENT);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<BPMNDiagram> getBPMNDiagram() {
		if (bPMNDiagram == null) {
			bPMNDiagram = new EObjectContainmentEList<BPMNDiagram>(BPMNDiagram.class, this, ModelPackage.TDEFINITIONS__BPMN_DIAGRAM);
		}
		return bPMNDiagram;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TRelationship> getRelationship() {
		if (relationship == null) {
			relationship = new EObjectContainmentEList<TRelationship>(TRelationship.class, this, ModelPackage.TDEFINITIONS__RELATIONSHIP);
		}
		return relationship;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getExporter() {
		return exporter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExporter(String newExporter) {
		String oldExporter = exporter;
		exporter = newExporter;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TDEFINITIONS__EXPORTER, oldExporter, exporter));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getExporterVersion() {
		return exporterVersion;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExporterVersion(String newExporterVersion) {
		String oldExporterVersion = exporterVersion;
		exporterVersion = newExporterVersion;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TDEFINITIONS__EXPORTER_VERSION, oldExporterVersion, exporterVersion));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getExpressionLanguage() {
		return expressionLanguage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExpressionLanguage(String newExpressionLanguage) {
		String oldExpressionLanguage = expressionLanguage;
		expressionLanguage = newExpressionLanguage;
		boolean oldExpressionLanguageESet = expressionLanguageESet;
		expressionLanguageESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TDEFINITIONS__EXPRESSION_LANGUAGE, oldExpressionLanguage, expressionLanguage, !oldExpressionLanguageESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetExpressionLanguage() {
		String oldExpressionLanguage = expressionLanguage;
		boolean oldExpressionLanguageESet = expressionLanguageESet;
		expressionLanguage = EXPRESSION_LANGUAGE_EDEFAULT;
		expressionLanguageESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, ModelPackage.TDEFINITIONS__EXPRESSION_LANGUAGE, oldExpressionLanguage, EXPRESSION_LANGUAGE_EDEFAULT, oldExpressionLanguageESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetExpressionLanguage() {
		return expressionLanguageESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setId(String newId) {
		String oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TDEFINITIONS__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TDEFINITIONS__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTargetNamespace() {
		return targetNamespace;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTargetNamespace(String newTargetNamespace) {
		String oldTargetNamespace = targetNamespace;
		targetNamespace = newTargetNamespace;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TDEFINITIONS__TARGET_NAMESPACE, oldTargetNamespace, targetNamespace));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTypeLanguage() {
		return typeLanguage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTypeLanguage(String newTypeLanguage) {
		String oldTypeLanguage = typeLanguage;
		typeLanguage = newTypeLanguage;
		boolean oldTypeLanguageESet = typeLanguageESet;
		typeLanguageESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TDEFINITIONS__TYPE_LANGUAGE, oldTypeLanguage, typeLanguage, !oldTypeLanguageESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetTypeLanguage() {
		String oldTypeLanguage = typeLanguage;
		boolean oldTypeLanguageESet = typeLanguageESet;
		typeLanguage = TYPE_LANGUAGE_EDEFAULT;
		typeLanguageESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, ModelPackage.TDEFINITIONS__TYPE_LANGUAGE, oldTypeLanguage, TYPE_LANGUAGE_EDEFAULT, oldTypeLanguageESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetTypeLanguage() {
		return typeLanguageESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getAnyAttribute() {
		if (anyAttribute == null) {
			anyAttribute = new BasicFeatureMap(this, ModelPackage.TDEFINITIONS__ANY_ATTRIBUTE);
		}
		return anyAttribute;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ModelPackage.TDEFINITIONS__IMPORT:
				return ((InternalEList<?>)getImport()).basicRemove(otherEnd, msgs);
			case ModelPackage.TDEFINITIONS__EXTENSION:
				return ((InternalEList<?>)getExtension()).basicRemove(otherEnd, msgs);
			case ModelPackage.TDEFINITIONS__ROOT_ELEMENT_GROUP:
				return ((InternalEList<?>)getRootElementGroup()).basicRemove(otherEnd, msgs);
			case ModelPackage.TDEFINITIONS__ROOT_ELEMENT:
				return ((InternalEList<?>)getRootElement()).basicRemove(otherEnd, msgs);
			case ModelPackage.TDEFINITIONS__BPMN_DIAGRAM:
				return ((InternalEList<?>)getBPMNDiagram()).basicRemove(otherEnd, msgs);
			case ModelPackage.TDEFINITIONS__RELATIONSHIP:
				return ((InternalEList<?>)getRelationship()).basicRemove(otherEnd, msgs);
			case ModelPackage.TDEFINITIONS__ANY_ATTRIBUTE:
				return ((InternalEList<?>)getAnyAttribute()).basicRemove(otherEnd, msgs);
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
			case ModelPackage.TDEFINITIONS__IMPORT:
				return getImport();
			case ModelPackage.TDEFINITIONS__EXTENSION:
				return getExtension();
			case ModelPackage.TDEFINITIONS__ROOT_ELEMENT_GROUP:
				if (coreType) return getRootElementGroup();
				return ((FeatureMap.Internal)getRootElementGroup()).getWrapper();
			case ModelPackage.TDEFINITIONS__ROOT_ELEMENT:
				return getRootElement();
			case ModelPackage.TDEFINITIONS__BPMN_DIAGRAM:
				return getBPMNDiagram();
			case ModelPackage.TDEFINITIONS__RELATIONSHIP:
				return getRelationship();
			case ModelPackage.TDEFINITIONS__EXPORTER:
				return getExporter();
			case ModelPackage.TDEFINITIONS__EXPORTER_VERSION:
				return getExporterVersion();
			case ModelPackage.TDEFINITIONS__EXPRESSION_LANGUAGE:
				return getExpressionLanguage();
			case ModelPackage.TDEFINITIONS__ID:
				return getId();
			case ModelPackage.TDEFINITIONS__NAME:
				return getName();
			case ModelPackage.TDEFINITIONS__TARGET_NAMESPACE:
				return getTargetNamespace();
			case ModelPackage.TDEFINITIONS__TYPE_LANGUAGE:
				return getTypeLanguage();
			case ModelPackage.TDEFINITIONS__ANY_ATTRIBUTE:
				if (coreType) return getAnyAttribute();
				return ((FeatureMap.Internal)getAnyAttribute()).getWrapper();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ModelPackage.TDEFINITIONS__IMPORT:
				getImport().clear();
				getImport().addAll((Collection<? extends TImport>)newValue);
				return;
			case ModelPackage.TDEFINITIONS__EXTENSION:
				getExtension().clear();
				getExtension().addAll((Collection<? extends TExtension>)newValue);
				return;
			case ModelPackage.TDEFINITIONS__ROOT_ELEMENT_GROUP:
				((FeatureMap.Internal)getRootElementGroup()).set(newValue);
				return;
			case ModelPackage.TDEFINITIONS__ROOT_ELEMENT:
				getRootElement().clear();
				getRootElement().addAll((Collection<? extends TRootElement>)newValue);
				return;
			case ModelPackage.TDEFINITIONS__BPMN_DIAGRAM:
				getBPMNDiagram().clear();
				getBPMNDiagram().addAll((Collection<? extends BPMNDiagram>)newValue);
				return;
			case ModelPackage.TDEFINITIONS__RELATIONSHIP:
				getRelationship().clear();
				getRelationship().addAll((Collection<? extends TRelationship>)newValue);
				return;
			case ModelPackage.TDEFINITIONS__EXPORTER:
				setExporter((String)newValue);
				return;
			case ModelPackage.TDEFINITIONS__EXPORTER_VERSION:
				setExporterVersion((String)newValue);
				return;
			case ModelPackage.TDEFINITIONS__EXPRESSION_LANGUAGE:
				setExpressionLanguage((String)newValue);
				return;
			case ModelPackage.TDEFINITIONS__ID:
				setId((String)newValue);
				return;
			case ModelPackage.TDEFINITIONS__NAME:
				setName((String)newValue);
				return;
			case ModelPackage.TDEFINITIONS__TARGET_NAMESPACE:
				setTargetNamespace((String)newValue);
				return;
			case ModelPackage.TDEFINITIONS__TYPE_LANGUAGE:
				setTypeLanguage((String)newValue);
				return;
			case ModelPackage.TDEFINITIONS__ANY_ATTRIBUTE:
				((FeatureMap.Internal)getAnyAttribute()).set(newValue);
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
			case ModelPackage.TDEFINITIONS__IMPORT:
				getImport().clear();
				return;
			case ModelPackage.TDEFINITIONS__EXTENSION:
				getExtension().clear();
				return;
			case ModelPackage.TDEFINITIONS__ROOT_ELEMENT_GROUP:
				getRootElementGroup().clear();
				return;
			case ModelPackage.TDEFINITIONS__ROOT_ELEMENT:
				getRootElement().clear();
				return;
			case ModelPackage.TDEFINITIONS__BPMN_DIAGRAM:
				getBPMNDiagram().clear();
				return;
			case ModelPackage.TDEFINITIONS__RELATIONSHIP:
				getRelationship().clear();
				return;
			case ModelPackage.TDEFINITIONS__EXPORTER:
				setExporter(EXPORTER_EDEFAULT);
				return;
			case ModelPackage.TDEFINITIONS__EXPORTER_VERSION:
				setExporterVersion(EXPORTER_VERSION_EDEFAULT);
				return;
			case ModelPackage.TDEFINITIONS__EXPRESSION_LANGUAGE:
				unsetExpressionLanguage();
				return;
			case ModelPackage.TDEFINITIONS__ID:
				setId(ID_EDEFAULT);
				return;
			case ModelPackage.TDEFINITIONS__NAME:
				setName(NAME_EDEFAULT);
				return;
			case ModelPackage.TDEFINITIONS__TARGET_NAMESPACE:
				setTargetNamespace(TARGET_NAMESPACE_EDEFAULT);
				return;
			case ModelPackage.TDEFINITIONS__TYPE_LANGUAGE:
				unsetTypeLanguage();
				return;
			case ModelPackage.TDEFINITIONS__ANY_ATTRIBUTE:
				getAnyAttribute().clear();
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
			case ModelPackage.TDEFINITIONS__IMPORT:
				return import_ != null && !import_.isEmpty();
			case ModelPackage.TDEFINITIONS__EXTENSION:
				return extension != null && !extension.isEmpty();
			case ModelPackage.TDEFINITIONS__ROOT_ELEMENT_GROUP:
				return rootElementGroup != null && !rootElementGroup.isEmpty();
			case ModelPackage.TDEFINITIONS__ROOT_ELEMENT:
				return !getRootElement().isEmpty();
			case ModelPackage.TDEFINITIONS__BPMN_DIAGRAM:
				return bPMNDiagram != null && !bPMNDiagram.isEmpty();
			case ModelPackage.TDEFINITIONS__RELATIONSHIP:
				return relationship != null && !relationship.isEmpty();
			case ModelPackage.TDEFINITIONS__EXPORTER:
				return EXPORTER_EDEFAULT == null ? exporter != null : !EXPORTER_EDEFAULT.equals(exporter);
			case ModelPackage.TDEFINITIONS__EXPORTER_VERSION:
				return EXPORTER_VERSION_EDEFAULT == null ? exporterVersion != null : !EXPORTER_VERSION_EDEFAULT.equals(exporterVersion);
			case ModelPackage.TDEFINITIONS__EXPRESSION_LANGUAGE:
				return isSetExpressionLanguage();
			case ModelPackage.TDEFINITIONS__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case ModelPackage.TDEFINITIONS__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case ModelPackage.TDEFINITIONS__TARGET_NAMESPACE:
				return TARGET_NAMESPACE_EDEFAULT == null ? targetNamespace != null : !TARGET_NAMESPACE_EDEFAULT.equals(targetNamespace);
			case ModelPackage.TDEFINITIONS__TYPE_LANGUAGE:
				return isSetTypeLanguage();
			case ModelPackage.TDEFINITIONS__ANY_ATTRIBUTE:
				return anyAttribute != null && !anyAttribute.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (rootElementGroup: ");
		result.append(rootElementGroup);
		result.append(", exporter: ");
		result.append(exporter);
		result.append(", exporterVersion: ");
		result.append(exporterVersion);
		result.append(", expressionLanguage: ");
		if (expressionLanguageESet) result.append(expressionLanguage); else result.append("<unset>");
		result.append(", id: ");
		result.append(id);
		result.append(", name: ");
		result.append(name);
		result.append(", targetNamespace: ");
		result.append(targetNamespace);
		result.append(", typeLanguage: ");
		if (typeLanguageESet) result.append(typeLanguage); else result.append("<unset>");
		result.append(", anyAttribute: ");
		result.append(anyAttribute);
		result.append(')');
		return result.toString();
	}

} //TDefinitionsImpl
