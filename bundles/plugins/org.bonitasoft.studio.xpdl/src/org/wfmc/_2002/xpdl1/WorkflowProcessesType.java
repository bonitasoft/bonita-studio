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
 * A representation of the model object '<em><b>Workflow Processes Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.wfmc._2002.xpdl1.WorkflowProcessesType#getWorkflowProcess <em>Workflow Process</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getWorkflowProcessesType()
 * @model extendedMetaData="name='WorkflowProcesses_._type' kind='elementOnly'"
 * @generated
 */
public interface WorkflowProcessesType extends EObject {
	/**
	 * Returns the value of the '<em><b>Workflow Process</b></em>' containment reference list.
	 * The list contents are of type {@link org.wfmc._2002.xpdl1.WorkflowProcessType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Workflow Process</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Workflow Process</em>' containment reference list.
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getWorkflowProcessesType_WorkflowProcess()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='WorkflowProcess' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<WorkflowProcessType> getWorkflowProcess();

} // WorkflowProcessesType
