/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.bonitasoft.studio.connector.model.implementation.impl;

import java.util.Collection;

import org.bonitasoft.studio.connector.model.implementation.ConnectorImplementationPackage;
import org.bonitasoft.studio.connector.model.implementation.JarDependencies;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Jar Dependencies</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.bonitasoft.studio.connector.model.implementation.impl.JarDependenciesImpl#getJarDependency <em>Jar Dependency</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class JarDependenciesImpl extends EObjectImpl implements JarDependencies {
    /**
	 * The cached value of the '{@link #getJarDependency() <em>Jar Dependency</em>}' attribute list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getJarDependency()
	 * @generated
	 * @ordered
	 */
    protected EList<String> jarDependency;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected JarDependenciesImpl() {
		super();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    protected EClass eStaticClass() {
		return ConnectorImplementationPackage.Literals.JAR_DEPENDENCIES;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList<String> getJarDependency() {
		if (jarDependency == null) {
			jarDependency = new EDataTypeEList<String>(String.class, this, ConnectorImplementationPackage.JAR_DEPENDENCIES__JAR_DEPENDENCY);
		}
		return jarDependency;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ConnectorImplementationPackage.JAR_DEPENDENCIES__JAR_DEPENDENCY:
				return getJarDependency();
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
			case ConnectorImplementationPackage.JAR_DEPENDENCIES__JAR_DEPENDENCY:
				getJarDependency().clear();
				getJarDependency().addAll((Collection<? extends String>)newValue);
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
			case ConnectorImplementationPackage.JAR_DEPENDENCIES__JAR_DEPENDENCY:
				getJarDependency().clear();
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
			case ConnectorImplementationPackage.JAR_DEPENDENCIES__JAR_DEPENDENCY:
				return jarDependency != null && !jarDependency.isEmpty();
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
		result.append(" (jarDependency: ");
		result.append(jarDependency);
		result.append(')');
		return result.toString();
	}

} //JarDependenciesImpl
