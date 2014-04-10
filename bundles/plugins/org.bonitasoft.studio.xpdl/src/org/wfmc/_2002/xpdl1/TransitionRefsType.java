/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.wfmc._2002.xpdl1;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Transition Refs Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.wfmc._2002.xpdl1.TransitionRefsType#getTransitionRef <em>Transition Ref</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getTransitionRefsType()
 * @model extendedMetaData="name='TransitionRefs_._type' kind='elementOnly'"
 * @generated
 */
public interface TransitionRefsType extends EObject {
	/**
	 * Returns the value of the '<em><b>Transition Ref</b></em>' containment reference list.
	 * The list contents are of type {@link org.wfmc._2002.xpdl1.TransitionRefType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Transition Ref</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Transition Ref</em>' containment reference list.
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getTransitionRefsType_TransitionRef()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='TransitionRef' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<TransitionRefType> getTransitionRef();

} // TransitionRefsType
