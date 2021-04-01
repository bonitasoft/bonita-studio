/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.bonitasoft.studio.connector.model.implementation;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Jar Dependencies</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.bonitasoft.studio.connector.model.implementation.JarDependencies#getJarDependency <em>Jar Dependency</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.bonitasoft.studio.connector.model.implementation.ConnectorImplementationPackage#getJarDependencies()
 * @model extendedMetaData="name='jarDependencies' kind='elementOnly'"
 * @generated
 */
public interface JarDependencies extends EObject {
    /**
	 * Returns the value of the '<em><b>Jar Dependency</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Jar Dependency</em>' attribute list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Jar Dependency</em>' attribute list.
	 * @see org.bonitasoft.studio.connector.model.implementation.ConnectorImplementationPackage#getJarDependencies_JarDependency()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='jarDependency'"
	 * @generated
	 */
    EList<String> getJarDependency();

} // JarDependencies
