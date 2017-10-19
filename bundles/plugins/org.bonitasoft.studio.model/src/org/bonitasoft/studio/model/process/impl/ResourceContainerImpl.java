/**
 * Copyright (C) 2009-2015 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.model.process.impl;

import java.util.Collection;

import org.bonitasoft.studio.model.process.AssociatedFile;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.ResourceContainer;
import org.bonitasoft.studio.model.process.ResourceFile;
import org.bonitasoft.studio.model.process.ResourceFolder;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Resource Container</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.ResourceContainerImpl#getHtmlTemplate <em>Html Template</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.ResourceContainerImpl#getResourceJars <em>Resource Jars</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.ResourceContainerImpl#getResourceValidators <em>Resource Validators</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.ResourceContainerImpl#getResourceFiles <em>Resource Files</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.ResourceContainerImpl#getResourceFolders <em>Resource Folders</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ResourceContainerImpl extends EObjectImpl implements ResourceContainer {
	/**
	 * The cached value of the '{@link #getHtmlTemplate() <em>Html Template</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHtmlTemplate()
	 * @generated
	 * @ordered
	 */
	protected AssociatedFile htmlTemplate;

	/**
	 * The cached value of the '{@link #getResourceJars() <em>Resource Jars</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResourceJars()
	 * @generated
	 * @ordered
	 */
	protected EList<String> resourceJars;

	/**
	 * The cached value of the '{@link #getResourceValidators() <em>Resource Validators</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResourceValidators()
	 * @generated
	 * @ordered
	 */
	protected EList<String> resourceValidators;

	/**
	 * The cached value of the '{@link #getResourceFiles() <em>Resource Files</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResourceFiles()
	 * @generated
	 * @ordered
	 */
	protected EList<ResourceFile> resourceFiles;

	/**
	 * The cached value of the '{@link #getResourceFolders() <em>Resource Folders</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResourceFolders()
	 * @generated
	 * @ordered
	 */
	protected EList<ResourceFolder> resourceFolders;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ResourceContainerImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ProcessPackage.Literals.RESOURCE_CONTAINER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AssociatedFile getHtmlTemplate() {
		return htmlTemplate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetHtmlTemplate(AssociatedFile newHtmlTemplate, NotificationChain msgs) {
		AssociatedFile oldHtmlTemplate = htmlTemplate;
		htmlTemplate = newHtmlTemplate;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ProcessPackage.RESOURCE_CONTAINER__HTML_TEMPLATE, oldHtmlTemplate, newHtmlTemplate);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHtmlTemplate(AssociatedFile newHtmlTemplate) {
		if (newHtmlTemplate != htmlTemplate) {
			NotificationChain msgs = null;
			if (htmlTemplate != null)
				msgs = ((InternalEObject)htmlTemplate).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ProcessPackage.RESOURCE_CONTAINER__HTML_TEMPLATE, null, msgs);
			if (newHtmlTemplate != null)
				msgs = ((InternalEObject)newHtmlTemplate).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ProcessPackage.RESOURCE_CONTAINER__HTML_TEMPLATE, null, msgs);
			msgs = basicSetHtmlTemplate(newHtmlTemplate, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.RESOURCE_CONTAINER__HTML_TEMPLATE, newHtmlTemplate, newHtmlTemplate));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getResourceJars() {
		if (resourceJars == null) {
			resourceJars = new EDataTypeUniqueEList<String>(String.class, this, ProcessPackage.RESOURCE_CONTAINER__RESOURCE_JARS);
		}
		return resourceJars;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getResourceValidators() {
		if (resourceValidators == null) {
			resourceValidators = new EDataTypeUniqueEList<String>(String.class, this, ProcessPackage.RESOURCE_CONTAINER__RESOURCE_VALIDATORS);
		}
		return resourceValidators;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ResourceFile> getResourceFiles() {
		if (resourceFiles == null) {
			resourceFiles = new EObjectContainmentEList<ResourceFile>(ResourceFile.class, this, ProcessPackage.RESOURCE_CONTAINER__RESOURCE_FILES);
		}
		return resourceFiles;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ResourceFolder> getResourceFolders() {
		if (resourceFolders == null) {
			resourceFolders = new EObjectContainmentEList<ResourceFolder>(ResourceFolder.class, this, ProcessPackage.RESOURCE_CONTAINER__RESOURCE_FOLDERS);
		}
		return resourceFolders;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ProcessPackage.RESOURCE_CONTAINER__HTML_TEMPLATE:
				return basicSetHtmlTemplate(null, msgs);
			case ProcessPackage.RESOURCE_CONTAINER__RESOURCE_FILES:
				return ((InternalEList<?>)getResourceFiles()).basicRemove(otherEnd, msgs);
			case ProcessPackage.RESOURCE_CONTAINER__RESOURCE_FOLDERS:
				return ((InternalEList<?>)getResourceFolders()).basicRemove(otherEnd, msgs);
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
			case ProcessPackage.RESOURCE_CONTAINER__HTML_TEMPLATE:
				return getHtmlTemplate();
			case ProcessPackage.RESOURCE_CONTAINER__RESOURCE_JARS:
				return getResourceJars();
			case ProcessPackage.RESOURCE_CONTAINER__RESOURCE_VALIDATORS:
				return getResourceValidators();
			case ProcessPackage.RESOURCE_CONTAINER__RESOURCE_FILES:
				return getResourceFiles();
			case ProcessPackage.RESOURCE_CONTAINER__RESOURCE_FOLDERS:
				return getResourceFolders();
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
			case ProcessPackage.RESOURCE_CONTAINER__HTML_TEMPLATE:
				setHtmlTemplate((AssociatedFile)newValue);
				return;
			case ProcessPackage.RESOURCE_CONTAINER__RESOURCE_JARS:
				getResourceJars().clear();
				getResourceJars().addAll((Collection<? extends String>)newValue);
				return;
			case ProcessPackage.RESOURCE_CONTAINER__RESOURCE_VALIDATORS:
				getResourceValidators().clear();
				getResourceValidators().addAll((Collection<? extends String>)newValue);
				return;
			case ProcessPackage.RESOURCE_CONTAINER__RESOURCE_FILES:
				getResourceFiles().clear();
				getResourceFiles().addAll((Collection<? extends ResourceFile>)newValue);
				return;
			case ProcessPackage.RESOURCE_CONTAINER__RESOURCE_FOLDERS:
				getResourceFolders().clear();
				getResourceFolders().addAll((Collection<? extends ResourceFolder>)newValue);
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
			case ProcessPackage.RESOURCE_CONTAINER__HTML_TEMPLATE:
				setHtmlTemplate((AssociatedFile)null);
				return;
			case ProcessPackage.RESOURCE_CONTAINER__RESOURCE_JARS:
				getResourceJars().clear();
				return;
			case ProcessPackage.RESOURCE_CONTAINER__RESOURCE_VALIDATORS:
				getResourceValidators().clear();
				return;
			case ProcessPackage.RESOURCE_CONTAINER__RESOURCE_FILES:
				getResourceFiles().clear();
				return;
			case ProcessPackage.RESOURCE_CONTAINER__RESOURCE_FOLDERS:
				getResourceFolders().clear();
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
			case ProcessPackage.RESOURCE_CONTAINER__HTML_TEMPLATE:
				return htmlTemplate != null;
			case ProcessPackage.RESOURCE_CONTAINER__RESOURCE_JARS:
				return resourceJars != null && !resourceJars.isEmpty();
			case ProcessPackage.RESOURCE_CONTAINER__RESOURCE_VALIDATORS:
				return resourceValidators != null && !resourceValidators.isEmpty();
			case ProcessPackage.RESOURCE_CONTAINER__RESOURCE_FILES:
				return resourceFiles != null && !resourceFiles.isEmpty();
			case ProcessPackage.RESOURCE_CONTAINER__RESOURCE_FOLDERS:
				return resourceFolders != null && !resourceFolders.isEmpty();
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
		result.append(" (resourceJars: "); //$NON-NLS-1$
		result.append(resourceJars);
		result.append(", resourceValidators: "); //$NON-NLS-1$
		result.append(resourceValidators);
		result.append(')');
		return result.toString();
	}

} //ResourceContainerImpl
