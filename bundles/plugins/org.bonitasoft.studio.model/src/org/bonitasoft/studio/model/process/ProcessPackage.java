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
package org.bonitasoft.studio.model.process;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.bonitasoft.studio.model.process.ProcessFactory
 * @model kind="package"
 * @generated
 */
public interface ProcessPackage extends EPackage {
	/**
     * The package name.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	String eNAME = "process"; //$NON-NLS-1$

	/**
     * The package namespace URI.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	String eNS_URI = "http://www.bonitasoft.org/ns/studio/process"; //$NON-NLS-1$

	/**
     * The package namespace name.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	String eNS_PREFIX = "process"; //$NON-NLS-1$

	/**
     * The singleton instance of the package.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	ProcessPackage eINSTANCE = org.bonitasoft.studio.model.process.impl.ProcessPackageImpl.init();

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.Element <em>Element</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.Element
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getElement()
     * @generated
     */
	int ELEMENT = 38;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ELEMENT__DOCUMENTATION = 0;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ELEMENT__NAME = 1;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ELEMENT__TEXT_ANNOTATION_ATTACHMENT = 2;

	/**
     * The number of structural features of the '<em>Element</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ELEMENT_FEATURE_COUNT = 3;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.AbstractCatchMessageEvent <em>Abstract Catch Message Event</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.AbstractCatchMessageEvent
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getAbstractCatchMessageEvent()
     * @generated
     */
	int ABSTRACT_CATCH_MESSAGE_EVENT = 0;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ABSTRACT_CATCH_MESSAGE_EVENT__DOCUMENTATION = ELEMENT__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ABSTRACT_CATCH_MESSAGE_EVENT__NAME = ELEMENT__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ABSTRACT_CATCH_MESSAGE_EVENT__TEXT_ANNOTATION_ATTACHMENT = ELEMENT__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Event</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ABSTRACT_CATCH_MESSAGE_EVENT__EVENT = ELEMENT_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Incoming Messag</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ABSTRACT_CATCH_MESSAGE_EVENT__INCOMING_MESSAG = ELEMENT_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Correlation</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ABSTRACT_CATCH_MESSAGE_EVENT__CORRELATION = ELEMENT_FEATURE_COUNT + 2;

	/**
     * The feature id for the '<em><b>Message Content</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ABSTRACT_CATCH_MESSAGE_EVENT__MESSAGE_CONTENT = ELEMENT_FEATURE_COUNT + 3;

	/**
     * The number of structural features of the '<em>Abstract Catch Message Event</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ABSTRACT_CATCH_MESSAGE_EVENT_FEATURE_COUNT = ELEMENT_FEATURE_COUNT + 4;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.impl.AbstractPageFlowImpl <em>Abstract Page Flow</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.impl.AbstractPageFlowImpl
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getAbstractPageFlow()
     * @generated
     */
	int ABSTRACT_PAGE_FLOW = 1;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ABSTRACT_PAGE_FLOW__DOCUMENTATION = ELEMENT__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ABSTRACT_PAGE_FLOW__NAME = ELEMENT__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ABSTRACT_PAGE_FLOW__TEXT_ANNOTATION_ATTACHMENT = ELEMENT__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The number of structural features of the '<em>Abstract Page Flow</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ABSTRACT_PAGE_FLOW_FEATURE_COUNT = ELEMENT_FEATURE_COUNT + 0;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.impl.ContainerImpl <em>Container</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.impl.ContainerImpl
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getContainer()
     * @generated
     */
	int CONTAINER = 23;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONTAINER__DOCUMENTATION = ELEMENT__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONTAINER__NAME = ELEMENT__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONTAINER__TEXT_ANNOTATION_ATTACHMENT = ELEMENT__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Elements</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONTAINER__ELEMENTS = ELEMENT_FEATURE_COUNT + 0;

	/**
     * The number of structural features of the '<em>Container</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONTAINER_FEATURE_COUNT = ELEMENT_FEATURE_COUNT + 1;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.impl.AbstractProcessImpl <em>Abstract Process</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.impl.AbstractProcessImpl
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getAbstractProcess()
     * @generated
     */
	int ABSTRACT_PROCESS = 2;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ABSTRACT_PROCESS__DOCUMENTATION = CONTAINER__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ABSTRACT_PROCESS__NAME = CONTAINER__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ABSTRACT_PROCESS__TEXT_ANNOTATION_ATTACHMENT = CONTAINER__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Elements</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ABSTRACT_PROCESS__ELEMENTS = CONTAINER__ELEMENTS;

	/**
     * The feature id for the '<em><b>Data</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ABSTRACT_PROCESS__DATA = CONTAINER_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Connectors</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ABSTRACT_PROCESS__CONNECTORS = CONTAINER_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Kpis</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ABSTRACT_PROCESS__KPIS = CONTAINER_FEATURE_COUNT + 2;

	/**
     * The feature id for the '<em><b>Form Mapping</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ABSTRACT_PROCESS__FORM_MAPPING = CONTAINER_FEATURE_COUNT + 3;

	/**
     * The feature id for the '<em><b>Overview Form Mapping</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ABSTRACT_PROCESS__OVERVIEW_FORM_MAPPING = CONTAINER_FEATURE_COUNT + 4;

	/**
     * The feature id for the '<em><b>Version</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ABSTRACT_PROCESS__VERSION = CONTAINER_FEATURE_COUNT + 5;

	/**
     * The feature id for the '<em><b>Author</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ABSTRACT_PROCESS__AUTHOR = CONTAINER_FEATURE_COUNT + 6;

	/**
     * The feature id for the '<em><b>Creation Date</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ABSTRACT_PROCESS__CREATION_DATE = CONTAINER_FEATURE_COUNT + 7;

	/**
     * The feature id for the '<em><b>Modification Date</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ABSTRACT_PROCESS__MODIFICATION_DATE = CONTAINER_FEATURE_COUNT + 8;

	/**
     * The feature id for the '<em><b>Datatypes</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ABSTRACT_PROCESS__DATATYPES = CONTAINER_FEATURE_COUNT + 9;

	/**
     * The feature id for the '<em><b>Connections</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ABSTRACT_PROCESS__CONNECTIONS = CONTAINER_FEATURE_COUNT + 10;

	/**
     * The feature id for the '<em><b>Categories</b></em>' attribute list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ABSTRACT_PROCESS__CATEGORIES = CONTAINER_FEATURE_COUNT + 11;

	/**
     * The feature id for the '<em><b>Actors</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ABSTRACT_PROCESS__ACTORS = CONTAINER_FEATURE_COUNT + 12;

	/**
     * The feature id for the '<em><b>Configurations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ABSTRACT_PROCESS__CONFIGURATIONS = CONTAINER_FEATURE_COUNT + 13;

	/**
     * The feature id for the '<em><b>Parameters</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ABSTRACT_PROCESS__PARAMETERS = CONTAINER_FEATURE_COUNT + 14;

	/**
     * The number of structural features of the '<em>Abstract Process</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ABSTRACT_PROCESS_FEATURE_COUNT = CONTAINER_FEATURE_COUNT + 15;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.AbstractTimerEvent <em>Abstract Timer Event</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.AbstractTimerEvent
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getAbstractTimerEvent()
     * @generated
     */
	int ABSTRACT_TIMER_EVENT = 3;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ABSTRACT_TIMER_EVENT__DOCUMENTATION = ELEMENT__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ABSTRACT_TIMER_EVENT__NAME = ELEMENT__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ABSTRACT_TIMER_EVENT__TEXT_ANNOTATION_ATTACHMENT = ELEMENT__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Condition</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ABSTRACT_TIMER_EVENT__CONDITION = ELEMENT_FEATURE_COUNT + 0;

	/**
     * The number of structural features of the '<em>Abstract Timer Event</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ABSTRACT_TIMER_EVENT_FEATURE_COUNT = ELEMENT_FEATURE_COUNT + 1;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.impl.FlowElementImpl <em>Flow Element</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.impl.FlowElementImpl
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getFlowElement()
     * @generated
     */
	int FLOW_ELEMENT = 48;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FLOW_ELEMENT__DOCUMENTATION = ELEMENT__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FLOW_ELEMENT__NAME = ELEMENT__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FLOW_ELEMENT__TEXT_ANNOTATION_ATTACHMENT = ELEMENT__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Outgoing</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FLOW_ELEMENT__OUTGOING = ELEMENT_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Incoming</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FLOW_ELEMENT__INCOMING = ELEMENT_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Dynamic Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FLOW_ELEMENT__DYNAMIC_LABEL = ELEMENT_FEATURE_COUNT + 2;

	/**
     * The feature id for the '<em><b>Dynamic Description</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FLOW_ELEMENT__DYNAMIC_DESCRIPTION = ELEMENT_FEATURE_COUNT + 3;

	/**
     * The feature id for the '<em><b>Step Summary</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FLOW_ELEMENT__STEP_SUMMARY = ELEMENT_FEATURE_COUNT + 4;

	/**
     * The number of structural features of the '<em>Flow Element</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FLOW_ELEMENT_FEATURE_COUNT = ELEMENT_FEATURE_COUNT + 5;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.impl.ActivityImpl <em>Activity</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.impl.ActivityImpl
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getActivity()
     * @generated
     */
	int ACTIVITY = 4;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ACTIVITY__DOCUMENTATION = FLOW_ELEMENT__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ACTIVITY__NAME = FLOW_ELEMENT__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ACTIVITY__TEXT_ANNOTATION_ATTACHMENT = FLOW_ELEMENT__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Outgoing</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ACTIVITY__OUTGOING = FLOW_ELEMENT__OUTGOING;

	/**
     * The feature id for the '<em><b>Incoming</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ACTIVITY__INCOMING = FLOW_ELEMENT__INCOMING;

	/**
     * The feature id for the '<em><b>Dynamic Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ACTIVITY__DYNAMIC_LABEL = FLOW_ELEMENT__DYNAMIC_LABEL;

	/**
     * The feature id for the '<em><b>Dynamic Description</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ACTIVITY__DYNAMIC_DESCRIPTION = FLOW_ELEMENT__DYNAMIC_DESCRIPTION;

	/**
     * The feature id for the '<em><b>Step Summary</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ACTIVITY__STEP_SUMMARY = FLOW_ELEMENT__STEP_SUMMARY;

	/**
     * The feature id for the '<em><b>Data</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ACTIVITY__DATA = FLOW_ELEMENT_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Connectors</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ACTIVITY__CONNECTORS = FLOW_ELEMENT_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Kpis</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ACTIVITY__KPIS = FLOW_ELEMENT_FEATURE_COUNT + 2;

	/**
     * The feature id for the '<em><b>Operations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ACTIVITY__OPERATIONS = FLOW_ELEMENT_FEATURE_COUNT + 3;

	/**
     * The feature id for the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ACTIVITY__TYPE = FLOW_ELEMENT_FEATURE_COUNT + 4;

	/**
     * The feature id for the '<em><b>Test Before</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ACTIVITY__TEST_BEFORE = FLOW_ELEMENT_FEATURE_COUNT + 5;

	/**
     * The feature id for the '<em><b>Loop Condition</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ACTIVITY__LOOP_CONDITION = FLOW_ELEMENT_FEATURE_COUNT + 6;

	/**
     * The feature id for the '<em><b>Loop Maximum</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ACTIVITY__LOOP_MAXIMUM = FLOW_ELEMENT_FEATURE_COUNT + 7;

	/**
     * The feature id for the '<em><b>Use Cardinality</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ACTIVITY__USE_CARDINALITY = FLOW_ELEMENT_FEATURE_COUNT + 8;

	/**
     * The feature id for the '<em><b>Cardinality Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ACTIVITY__CARDINALITY_EXPRESSION = FLOW_ELEMENT_FEATURE_COUNT + 9;

	/**
     * The feature id for the '<em><b>Collection Data To Multi Instantiate</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ACTIVITY__COLLECTION_DATA_TO_MULTI_INSTANTIATE = FLOW_ELEMENT_FEATURE_COUNT + 10;

	/**
     * The feature id for the '<em><b>Iterator Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ACTIVITY__ITERATOR_EXPRESSION = FLOW_ELEMENT_FEATURE_COUNT + 11;

	/**
     * The feature id for the '<em><b>Output Data</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ACTIVITY__OUTPUT_DATA = FLOW_ELEMENT_FEATURE_COUNT + 12;

	/**
     * The feature id for the '<em><b>List Data Containing Output Results</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ACTIVITY__LIST_DATA_CONTAINING_OUTPUT_RESULTS = FLOW_ELEMENT_FEATURE_COUNT + 13;

	/**
     * The feature id for the '<em><b>Completion Condition</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ACTIVITY__COMPLETION_CONDITION = FLOW_ELEMENT_FEATURE_COUNT + 14;

	/**
     * The feature id for the '<em><b>Store Output</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ACTIVITY__STORE_OUTPUT = FLOW_ELEMENT_FEATURE_COUNT + 15;

	/**
     * The feature id for the '<em><b>Boundary Intermediate Events</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ACTIVITY__BOUNDARY_INTERMEDIATE_EVENTS = FLOW_ELEMENT_FEATURE_COUNT + 16;

	/**
     * The number of structural features of the '<em>Activity</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ACTIVITY_FEATURE_COUNT = FLOW_ELEMENT_FEATURE_COUNT + 17;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.impl.ActorImpl <em>Actor</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.impl.ActorImpl
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getActor()
     * @generated
     */
	int ACTOR = 5;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ACTOR__DOCUMENTATION = ELEMENT__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ACTOR__NAME = ELEMENT__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ACTOR__TEXT_ANNOTATION_ATTACHMENT = ELEMENT__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Initiator</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ACTOR__INITIATOR = ELEMENT_FEATURE_COUNT + 0;

	/**
     * The number of structural features of the '<em>Actor</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ACTOR_FEATURE_COUNT = ELEMENT_FEATURE_COUNT + 1;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.impl.ConnectorImpl <em>Connector</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.impl.ConnectorImpl
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getConnector()
     * @generated
     */
	int CONNECTOR = 22;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONNECTOR__DOCUMENTATION = ELEMENT__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONNECTOR__NAME = ELEMENT__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONNECTOR__TEXT_ANNOTATION_ATTACHMENT = ELEMENT__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Definition Id</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONNECTOR__DEFINITION_ID = ELEMENT_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Event</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONNECTOR__EVENT = ELEMENT_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Ignore Errors</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONNECTOR__IGNORE_ERRORS = ELEMENT_FEATURE_COUNT + 2;

	/**
     * The feature id for the '<em><b>Throw Error Event</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONNECTOR__THROW_ERROR_EVENT = ELEMENT_FEATURE_COUNT + 3;

	/**
     * The feature id for the '<em><b>Named Error</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONNECTOR__NAMED_ERROR = ELEMENT_FEATURE_COUNT + 4;

	/**
     * The feature id for the '<em><b>Definition Version</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONNECTOR__DEFINITION_VERSION = ELEMENT_FEATURE_COUNT + 5;

	/**
     * The feature id for the '<em><b>Configuration</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONNECTOR__CONFIGURATION = ELEMENT_FEATURE_COUNT + 6;

	/**
     * The feature id for the '<em><b>Outputs</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONNECTOR__OUTPUTS = ELEMENT_FEATURE_COUNT + 7;

	/**
     * The number of structural features of the '<em>Connector</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONNECTOR_FEATURE_COUNT = ELEMENT_FEATURE_COUNT + 8;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.impl.ActorFilterImpl <em>Actor Filter</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.impl.ActorFilterImpl
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getActorFilter()
     * @generated
     */
	int ACTOR_FILTER = 6;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ACTOR_FILTER__DOCUMENTATION = CONNECTOR__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ACTOR_FILTER__NAME = CONNECTOR__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ACTOR_FILTER__TEXT_ANNOTATION_ATTACHMENT = CONNECTOR__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Definition Id</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ACTOR_FILTER__DEFINITION_ID = CONNECTOR__DEFINITION_ID;

	/**
     * The feature id for the '<em><b>Event</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ACTOR_FILTER__EVENT = CONNECTOR__EVENT;

	/**
     * The feature id for the '<em><b>Ignore Errors</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ACTOR_FILTER__IGNORE_ERRORS = CONNECTOR__IGNORE_ERRORS;

	/**
     * The feature id for the '<em><b>Throw Error Event</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ACTOR_FILTER__THROW_ERROR_EVENT = CONNECTOR__THROW_ERROR_EVENT;

	/**
     * The feature id for the '<em><b>Named Error</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ACTOR_FILTER__NAMED_ERROR = CONNECTOR__NAMED_ERROR;

	/**
     * The feature id for the '<em><b>Definition Version</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ACTOR_FILTER__DEFINITION_VERSION = CONNECTOR__DEFINITION_VERSION;

	/**
     * The feature id for the '<em><b>Configuration</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ACTOR_FILTER__CONFIGURATION = CONNECTOR__CONFIGURATION;

	/**
     * The feature id for the '<em><b>Outputs</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ACTOR_FILTER__OUTPUTS = CONNECTOR__OUTPUTS;

	/**
     * The number of structural features of the '<em>Actor Filter</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ACTOR_FILTER_FEATURE_COUNT = CONNECTOR_FEATURE_COUNT + 0;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.impl.GatewayImpl <em>Gateway</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.impl.GatewayImpl
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getGateway()
     * @generated
     */
	int GATEWAY = 50;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GATEWAY__DOCUMENTATION = FLOW_ELEMENT__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GATEWAY__NAME = FLOW_ELEMENT__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GATEWAY__TEXT_ANNOTATION_ATTACHMENT = FLOW_ELEMENT__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Outgoing</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GATEWAY__OUTGOING = FLOW_ELEMENT__OUTGOING;

	/**
     * The feature id for the '<em><b>Incoming</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GATEWAY__INCOMING = FLOW_ELEMENT__INCOMING;

	/**
     * The feature id for the '<em><b>Dynamic Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GATEWAY__DYNAMIC_LABEL = FLOW_ELEMENT__DYNAMIC_LABEL;

	/**
     * The feature id for the '<em><b>Dynamic Description</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GATEWAY__DYNAMIC_DESCRIPTION = FLOW_ELEMENT__DYNAMIC_DESCRIPTION;

	/**
     * The feature id for the '<em><b>Step Summary</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GATEWAY__STEP_SUMMARY = FLOW_ELEMENT__STEP_SUMMARY;

	/**
     * The feature id for the '<em><b>Gateway Type</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GATEWAY__GATEWAY_TYPE = FLOW_ELEMENT_FEATURE_COUNT + 0;

	/**
     * The number of structural features of the '<em>Gateway</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GATEWAY_FEATURE_COUNT = FLOW_ELEMENT_FEATURE_COUNT + 1;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.impl.ANDGatewayImpl <em>AND Gateway</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.impl.ANDGatewayImpl
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getANDGateway()
     * @generated
     */
	int AND_GATEWAY = 7;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int AND_GATEWAY__DOCUMENTATION = GATEWAY__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int AND_GATEWAY__NAME = GATEWAY__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int AND_GATEWAY__TEXT_ANNOTATION_ATTACHMENT = GATEWAY__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Outgoing</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int AND_GATEWAY__OUTGOING = GATEWAY__OUTGOING;

	/**
     * The feature id for the '<em><b>Incoming</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int AND_GATEWAY__INCOMING = GATEWAY__INCOMING;

	/**
     * The feature id for the '<em><b>Dynamic Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int AND_GATEWAY__DYNAMIC_LABEL = GATEWAY__DYNAMIC_LABEL;

	/**
     * The feature id for the '<em><b>Dynamic Description</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int AND_GATEWAY__DYNAMIC_DESCRIPTION = GATEWAY__DYNAMIC_DESCRIPTION;

	/**
     * The feature id for the '<em><b>Step Summary</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int AND_GATEWAY__STEP_SUMMARY = GATEWAY__STEP_SUMMARY;

	/**
     * The feature id for the '<em><b>Gateway Type</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int AND_GATEWAY__GATEWAY_TYPE = GATEWAY__GATEWAY_TYPE;

	/**
     * The number of structural features of the '<em>AND Gateway</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int AND_GATEWAY_FEATURE_COUNT = GATEWAY_FEATURE_COUNT + 0;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.Assignable <em>Assignable</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.Assignable
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getAssignable()
     * @generated
     */
	int ASSIGNABLE = 8;

	/**
     * The feature id for the '<em><b>Actor</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ASSIGNABLE__ACTOR = 0;

	/**
     * The feature id for the '<em><b>Filters</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ASSIGNABLE__FILTERS = 1;

	/**
     * The number of structural features of the '<em>Assignable</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ASSIGNABLE_FEATURE_COUNT = 2;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.impl.ConnectionImpl <em>Connection</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.impl.ConnectionImpl
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getConnection()
     * @generated
     */
	int CONNECTION = 29;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONNECTION__DOCUMENTATION = ELEMENT__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONNECTION__NAME = ELEMENT__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONNECTION__TEXT_ANNOTATION_ATTACHMENT = ELEMENT__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Target</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONNECTION__TARGET = ELEMENT_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Source</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONNECTION__SOURCE = ELEMENT_FEATURE_COUNT + 1;

	/**
     * The number of structural features of the '<em>Connection</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONNECTION_FEATURE_COUNT = ELEMENT_FEATURE_COUNT + 2;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.impl.AssociationImpl <em>Association</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.impl.AssociationImpl
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getAssociation()
     * @generated
     */
	int ASSOCIATION = 9;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ASSOCIATION__DOCUMENTATION = CONNECTION__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ASSOCIATION__NAME = CONNECTION__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ASSOCIATION__TEXT_ANNOTATION_ATTACHMENT = CONNECTION__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Target</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ASSOCIATION__TARGET = CONNECTION__TARGET;

	/**
     * The feature id for the '<em><b>Source</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ASSOCIATION__SOURCE = CONNECTION__SOURCE;

	/**
     * The feature id for the '<em><b>Is Directed</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ASSOCIATION__IS_DIRECTED = CONNECTION_FEATURE_COUNT + 0;

	/**
     * The number of structural features of the '<em>Association</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ASSOCIATION_FEATURE_COUNT = CONNECTION_FEATURE_COUNT + 1;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.impl.DataTypeImpl <em>Data Type</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.impl.DataTypeImpl
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getDataType()
     * @generated
     */
	int DATA_TYPE = 35;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DATA_TYPE__DOCUMENTATION = ELEMENT__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DATA_TYPE__NAME = ELEMENT__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DATA_TYPE__TEXT_ANNOTATION_ATTACHMENT = ELEMENT__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The number of structural features of the '<em>Data Type</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DATA_TYPE_FEATURE_COUNT = ELEMENT_FEATURE_COUNT + 0;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.impl.BooleanTypeImpl <em>Boolean Type</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.impl.BooleanTypeImpl
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getBooleanType()
     * @generated
     */
	int BOOLEAN_TYPE = 10;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int BOOLEAN_TYPE__DOCUMENTATION = DATA_TYPE__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int BOOLEAN_TYPE__NAME = DATA_TYPE__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int BOOLEAN_TYPE__TEXT_ANNOTATION_ATTACHMENT = DATA_TYPE__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The number of structural features of the '<em>Boolean Type</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int BOOLEAN_TYPE_FEATURE_COUNT = DATA_TYPE_FEATURE_COUNT + 0;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.impl.BoundaryEventImpl <em>Boundary Event</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.impl.BoundaryEventImpl
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getBoundaryEvent()
     * @generated
     */
	int BOUNDARY_EVENT = 11;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int BOUNDARY_EVENT__DOCUMENTATION = ELEMENT__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int BOUNDARY_EVENT__NAME = ELEMENT__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int BOUNDARY_EVENT__TEXT_ANNOTATION_ATTACHMENT = ELEMENT__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Outgoing</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int BOUNDARY_EVENT__OUTGOING = ELEMENT_FEATURE_COUNT + 0;

	/**
     * The number of structural features of the '<em>Boundary Event</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int BOUNDARY_EVENT_FEATURE_COUNT = ELEMENT_FEATURE_COUNT + 1;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.impl.BoundaryMessageEventImpl <em>Boundary Message Event</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.impl.BoundaryMessageEventImpl
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getBoundaryMessageEvent()
     * @generated
     */
	int BOUNDARY_MESSAGE_EVENT = 12;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int BOUNDARY_MESSAGE_EVENT__DOCUMENTATION = BOUNDARY_EVENT__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int BOUNDARY_MESSAGE_EVENT__NAME = BOUNDARY_EVENT__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int BOUNDARY_MESSAGE_EVENT__TEXT_ANNOTATION_ATTACHMENT = BOUNDARY_EVENT__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Outgoing</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int BOUNDARY_MESSAGE_EVENT__OUTGOING = BOUNDARY_EVENT__OUTGOING;

	/**
     * The feature id for the '<em><b>Event</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int BOUNDARY_MESSAGE_EVENT__EVENT = BOUNDARY_EVENT_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Incoming Messag</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int BOUNDARY_MESSAGE_EVENT__INCOMING_MESSAG = BOUNDARY_EVENT_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Correlation</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int BOUNDARY_MESSAGE_EVENT__CORRELATION = BOUNDARY_EVENT_FEATURE_COUNT + 2;

	/**
     * The feature id for the '<em><b>Message Content</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int BOUNDARY_MESSAGE_EVENT__MESSAGE_CONTENT = BOUNDARY_EVENT_FEATURE_COUNT + 3;

	/**
     * The number of structural features of the '<em>Boundary Message Event</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int BOUNDARY_MESSAGE_EVENT_FEATURE_COUNT = BOUNDARY_EVENT_FEATURE_COUNT + 4;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.impl.BoundarySignalEventImpl <em>Boundary Signal Event</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.impl.BoundarySignalEventImpl
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getBoundarySignalEvent()
     * @generated
     */
	int BOUNDARY_SIGNAL_EVENT = 13;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int BOUNDARY_SIGNAL_EVENT__DOCUMENTATION = BOUNDARY_EVENT__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int BOUNDARY_SIGNAL_EVENT__NAME = BOUNDARY_EVENT__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int BOUNDARY_SIGNAL_EVENT__TEXT_ANNOTATION_ATTACHMENT = BOUNDARY_EVENT__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Outgoing</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int BOUNDARY_SIGNAL_EVENT__OUTGOING = BOUNDARY_EVENT__OUTGOING;

	/**
     * The feature id for the '<em><b>Signal Code</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int BOUNDARY_SIGNAL_EVENT__SIGNAL_CODE = BOUNDARY_EVENT_FEATURE_COUNT + 0;

	/**
     * The number of structural features of the '<em>Boundary Signal Event</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int BOUNDARY_SIGNAL_EVENT_FEATURE_COUNT = BOUNDARY_EVENT_FEATURE_COUNT + 1;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.impl.BoundaryTimerEventImpl <em>Boundary Timer Event</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.impl.BoundaryTimerEventImpl
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getBoundaryTimerEvent()
     * @generated
     */
	int BOUNDARY_TIMER_EVENT = 14;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int BOUNDARY_TIMER_EVENT__DOCUMENTATION = BOUNDARY_EVENT__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int BOUNDARY_TIMER_EVENT__NAME = BOUNDARY_EVENT__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int BOUNDARY_TIMER_EVENT__TEXT_ANNOTATION_ATTACHMENT = BOUNDARY_EVENT__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Outgoing</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int BOUNDARY_TIMER_EVENT__OUTGOING = BOUNDARY_EVENT__OUTGOING;

	/**
     * The feature id for the '<em><b>Condition</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int BOUNDARY_TIMER_EVENT__CONDITION = BOUNDARY_EVENT_FEATURE_COUNT + 0;

	/**
     * The number of structural features of the '<em>Boundary Timer Event</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int BOUNDARY_TIMER_EVENT_FEATURE_COUNT = BOUNDARY_EVENT_FEATURE_COUNT + 1;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.impl.DataImpl <em>Data</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.impl.DataImpl
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getData()
     * @generated
     */
	int DATA = 32;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DATA__DOCUMENTATION = ELEMENT__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DATA__NAME = ELEMENT__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DATA__TEXT_ANNOTATION_ATTACHMENT = ELEMENT__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Generated</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DATA__GENERATED = ELEMENT_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Multiple</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DATA__MULTIPLE = ELEMENT_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Transient</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DATA__TRANSIENT = ELEMENT_FEATURE_COUNT + 2;

	/**
     * The feature id for the '<em><b>Datasource Id</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DATA__DATASOURCE_ID = ELEMENT_FEATURE_COUNT + 3;

	/**
     * The feature id for the '<em><b>Data Type</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DATA__DATA_TYPE = ELEMENT_FEATURE_COUNT + 4;

	/**
     * The feature id for the '<em><b>Default Value</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DATA__DEFAULT_VALUE = ELEMENT_FEATURE_COUNT + 5;

	/**
     * The number of structural features of the '<em>Data</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DATA_FEATURE_COUNT = ELEMENT_FEATURE_COUNT + 6;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.impl.JavaObjectDataImpl <em>Java Object Data</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.impl.JavaObjectDataImpl
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getJavaObjectData()
     * @generated
     */
	int JAVA_OBJECT_DATA = 60;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int JAVA_OBJECT_DATA__DOCUMENTATION = DATA__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int JAVA_OBJECT_DATA__NAME = DATA__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int JAVA_OBJECT_DATA__TEXT_ANNOTATION_ATTACHMENT = DATA__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Generated</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int JAVA_OBJECT_DATA__GENERATED = DATA__GENERATED;

	/**
     * The feature id for the '<em><b>Multiple</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int JAVA_OBJECT_DATA__MULTIPLE = DATA__MULTIPLE;

	/**
     * The feature id for the '<em><b>Transient</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int JAVA_OBJECT_DATA__TRANSIENT = DATA__TRANSIENT;

	/**
     * The feature id for the '<em><b>Datasource Id</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int JAVA_OBJECT_DATA__DATASOURCE_ID = DATA__DATASOURCE_ID;

	/**
     * The feature id for the '<em><b>Data Type</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int JAVA_OBJECT_DATA__DATA_TYPE = DATA__DATA_TYPE;

	/**
     * The feature id for the '<em><b>Default Value</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int JAVA_OBJECT_DATA__DEFAULT_VALUE = DATA__DEFAULT_VALUE;

	/**
     * The feature id for the '<em><b>Class Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int JAVA_OBJECT_DATA__CLASS_NAME = DATA_FEATURE_COUNT + 0;

	/**
     * The number of structural features of the '<em>Java Object Data</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int JAVA_OBJECT_DATA_FEATURE_COUNT = DATA_FEATURE_COUNT + 1;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.impl.BusinessObjectDataImpl <em>Business Object Data</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.impl.BusinessObjectDataImpl
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getBusinessObjectData()
     * @generated
     */
	int BUSINESS_OBJECT_DATA = 15;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int BUSINESS_OBJECT_DATA__DOCUMENTATION = JAVA_OBJECT_DATA__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int BUSINESS_OBJECT_DATA__NAME = JAVA_OBJECT_DATA__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int BUSINESS_OBJECT_DATA__TEXT_ANNOTATION_ATTACHMENT = JAVA_OBJECT_DATA__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Generated</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int BUSINESS_OBJECT_DATA__GENERATED = JAVA_OBJECT_DATA__GENERATED;

	/**
     * The feature id for the '<em><b>Multiple</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int BUSINESS_OBJECT_DATA__MULTIPLE = JAVA_OBJECT_DATA__MULTIPLE;

	/**
     * The feature id for the '<em><b>Transient</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int BUSINESS_OBJECT_DATA__TRANSIENT = JAVA_OBJECT_DATA__TRANSIENT;

	/**
     * The feature id for the '<em><b>Datasource Id</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int BUSINESS_OBJECT_DATA__DATASOURCE_ID = JAVA_OBJECT_DATA__DATASOURCE_ID;

	/**
     * The feature id for the '<em><b>Data Type</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int BUSINESS_OBJECT_DATA__DATA_TYPE = JAVA_OBJECT_DATA__DATA_TYPE;

	/**
     * The feature id for the '<em><b>Default Value</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int BUSINESS_OBJECT_DATA__DEFAULT_VALUE = JAVA_OBJECT_DATA__DEFAULT_VALUE;

	/**
     * The feature id for the '<em><b>Class Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int BUSINESS_OBJECT_DATA__CLASS_NAME = JAVA_OBJECT_DATA__CLASS_NAME;

	/**
     * The feature id for the '<em><b>Business Data Repository Id</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int BUSINESS_OBJECT_DATA__BUSINESS_DATA_REPOSITORY_ID = JAVA_OBJECT_DATA_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Create New Instance</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int BUSINESS_OBJECT_DATA__CREATE_NEW_INSTANCE = JAVA_OBJECT_DATA_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>EClass Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int BUSINESS_OBJECT_DATA__ECLASS_NAME = JAVA_OBJECT_DATA_FEATURE_COUNT + 2;

	/**
     * The number of structural features of the '<em>Business Object Data</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int BUSINESS_OBJECT_DATA_FEATURE_COUNT = JAVA_OBJECT_DATA_FEATURE_COUNT + 3;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.impl.BusinessObjectTypeImpl <em>Business Object Type</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.impl.BusinessObjectTypeImpl
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getBusinessObjectType()
     * @generated
     */
	int BUSINESS_OBJECT_TYPE = 16;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int BUSINESS_OBJECT_TYPE__DOCUMENTATION = DATA_TYPE__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int BUSINESS_OBJECT_TYPE__NAME = DATA_TYPE__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int BUSINESS_OBJECT_TYPE__TEXT_ANNOTATION_ATTACHMENT = DATA_TYPE__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The number of structural features of the '<em>Business Object Type</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int BUSINESS_OBJECT_TYPE_FEATURE_COUNT = DATA_TYPE_FEATURE_COUNT + 0;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.impl.CallActivityImpl <em>Call Activity</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.impl.CallActivityImpl
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getCallActivity()
     * @generated
     */
	int CALL_ACTIVITY = 17;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CALL_ACTIVITY__DOCUMENTATION = ACTIVITY__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CALL_ACTIVITY__NAME = ACTIVITY__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CALL_ACTIVITY__TEXT_ANNOTATION_ATTACHMENT = ACTIVITY__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Outgoing</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CALL_ACTIVITY__OUTGOING = ACTIVITY__OUTGOING;

	/**
     * The feature id for the '<em><b>Incoming</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CALL_ACTIVITY__INCOMING = ACTIVITY__INCOMING;

	/**
     * The feature id for the '<em><b>Dynamic Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CALL_ACTIVITY__DYNAMIC_LABEL = ACTIVITY__DYNAMIC_LABEL;

	/**
     * The feature id for the '<em><b>Dynamic Description</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CALL_ACTIVITY__DYNAMIC_DESCRIPTION = ACTIVITY__DYNAMIC_DESCRIPTION;

	/**
     * The feature id for the '<em><b>Step Summary</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CALL_ACTIVITY__STEP_SUMMARY = ACTIVITY__STEP_SUMMARY;

	/**
     * The feature id for the '<em><b>Data</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CALL_ACTIVITY__DATA = ACTIVITY__DATA;

	/**
     * The feature id for the '<em><b>Connectors</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CALL_ACTIVITY__CONNECTORS = ACTIVITY__CONNECTORS;

	/**
     * The feature id for the '<em><b>Kpis</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CALL_ACTIVITY__KPIS = ACTIVITY__KPIS;

	/**
     * The feature id for the '<em><b>Operations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CALL_ACTIVITY__OPERATIONS = ACTIVITY__OPERATIONS;

	/**
     * The feature id for the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CALL_ACTIVITY__TYPE = ACTIVITY__TYPE;

	/**
     * The feature id for the '<em><b>Test Before</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CALL_ACTIVITY__TEST_BEFORE = ACTIVITY__TEST_BEFORE;

	/**
     * The feature id for the '<em><b>Loop Condition</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CALL_ACTIVITY__LOOP_CONDITION = ACTIVITY__LOOP_CONDITION;

	/**
     * The feature id for the '<em><b>Loop Maximum</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CALL_ACTIVITY__LOOP_MAXIMUM = ACTIVITY__LOOP_MAXIMUM;

	/**
     * The feature id for the '<em><b>Use Cardinality</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CALL_ACTIVITY__USE_CARDINALITY = ACTIVITY__USE_CARDINALITY;

	/**
     * The feature id for the '<em><b>Cardinality Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CALL_ACTIVITY__CARDINALITY_EXPRESSION = ACTIVITY__CARDINALITY_EXPRESSION;

	/**
     * The feature id for the '<em><b>Collection Data To Multi Instantiate</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CALL_ACTIVITY__COLLECTION_DATA_TO_MULTI_INSTANTIATE = ACTIVITY__COLLECTION_DATA_TO_MULTI_INSTANTIATE;

	/**
     * The feature id for the '<em><b>Iterator Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CALL_ACTIVITY__ITERATOR_EXPRESSION = ACTIVITY__ITERATOR_EXPRESSION;

	/**
     * The feature id for the '<em><b>Output Data</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CALL_ACTIVITY__OUTPUT_DATA = ACTIVITY__OUTPUT_DATA;

	/**
     * The feature id for the '<em><b>List Data Containing Output Results</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CALL_ACTIVITY__LIST_DATA_CONTAINING_OUTPUT_RESULTS = ACTIVITY__LIST_DATA_CONTAINING_OUTPUT_RESULTS;

	/**
     * The feature id for the '<em><b>Completion Condition</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CALL_ACTIVITY__COMPLETION_CONDITION = ACTIVITY__COMPLETION_CONDITION;

	/**
     * The feature id for the '<em><b>Store Output</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CALL_ACTIVITY__STORE_OUTPUT = ACTIVITY__STORE_OUTPUT;

	/**
     * The feature id for the '<em><b>Boundary Intermediate Events</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CALL_ACTIVITY__BOUNDARY_INTERMEDIATE_EVENTS = ACTIVITY__BOUNDARY_INTERMEDIATE_EVENTS;

	/**
     * The feature id for the '<em><b>Input Mappings</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CALL_ACTIVITY__INPUT_MAPPINGS = ACTIVITY_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Output Mappings</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CALL_ACTIVITY__OUTPUT_MAPPINGS = ACTIVITY_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Called Activity Name</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CALL_ACTIVITY__CALLED_ACTIVITY_NAME = ACTIVITY_FEATURE_COUNT + 2;

	/**
     * The feature id for the '<em><b>Called Activity Version</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CALL_ACTIVITY__CALLED_ACTIVITY_VERSION = ACTIVITY_FEATURE_COUNT + 3;

	/**
     * The number of structural features of the '<em>Call Activity</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CALL_ACTIVITY_FEATURE_COUNT = ACTIVITY_FEATURE_COUNT + 4;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.impl.EventImpl <em>Event</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.impl.EventImpl
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getEvent()
     * @generated
     */
	int EVENT = 39;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int EVENT__DOCUMENTATION = FLOW_ELEMENT__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int EVENT__NAME = FLOW_ELEMENT__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int EVENT__TEXT_ANNOTATION_ATTACHMENT = FLOW_ELEMENT__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Outgoing</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int EVENT__OUTGOING = FLOW_ELEMENT__OUTGOING;

	/**
     * The feature id for the '<em><b>Incoming</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int EVENT__INCOMING = FLOW_ELEMENT__INCOMING;

	/**
     * The feature id for the '<em><b>Dynamic Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int EVENT__DYNAMIC_LABEL = FLOW_ELEMENT__DYNAMIC_LABEL;

	/**
     * The feature id for the '<em><b>Dynamic Description</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int EVENT__DYNAMIC_DESCRIPTION = FLOW_ELEMENT__DYNAMIC_DESCRIPTION;

	/**
     * The feature id for the '<em><b>Step Summary</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int EVENT__STEP_SUMMARY = FLOW_ELEMENT__STEP_SUMMARY;

	/**
     * The number of structural features of the '<em>Event</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int EVENT_FEATURE_COUNT = FLOW_ELEMENT_FEATURE_COUNT + 0;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.impl.LinkEventImpl <em>Link Event</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.impl.LinkEventImpl
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getLinkEvent()
     * @generated
     */
	int LINK_EVENT = 63;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int LINK_EVENT__DOCUMENTATION = EVENT__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int LINK_EVENT__NAME = EVENT__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int LINK_EVENT__TEXT_ANNOTATION_ATTACHMENT = EVENT__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Outgoing</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int LINK_EVENT__OUTGOING = EVENT__OUTGOING;

	/**
     * The feature id for the '<em><b>Incoming</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int LINK_EVENT__INCOMING = EVENT__INCOMING;

	/**
     * The feature id for the '<em><b>Dynamic Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int LINK_EVENT__DYNAMIC_LABEL = EVENT__DYNAMIC_LABEL;

	/**
     * The feature id for the '<em><b>Dynamic Description</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int LINK_EVENT__DYNAMIC_DESCRIPTION = EVENT__DYNAMIC_DESCRIPTION;

	/**
     * The feature id for the '<em><b>Step Summary</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int LINK_EVENT__STEP_SUMMARY = EVENT__STEP_SUMMARY;

	/**
     * The number of structural features of the '<em>Link Event</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int LINK_EVENT_FEATURE_COUNT = EVENT_FEATURE_COUNT + 0;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.impl.CatchLinkEventImpl <em>Catch Link Event</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.impl.CatchLinkEventImpl
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getCatchLinkEvent()
     * @generated
     */
	int CATCH_LINK_EVENT = 18;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CATCH_LINK_EVENT__DOCUMENTATION = LINK_EVENT__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CATCH_LINK_EVENT__NAME = LINK_EVENT__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CATCH_LINK_EVENT__TEXT_ANNOTATION_ATTACHMENT = LINK_EVENT__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Outgoing</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CATCH_LINK_EVENT__OUTGOING = LINK_EVENT__OUTGOING;

	/**
     * The feature id for the '<em><b>Incoming</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CATCH_LINK_EVENT__INCOMING = LINK_EVENT__INCOMING;

	/**
     * The feature id for the '<em><b>Dynamic Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CATCH_LINK_EVENT__DYNAMIC_LABEL = LINK_EVENT__DYNAMIC_LABEL;

	/**
     * The feature id for the '<em><b>Dynamic Description</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CATCH_LINK_EVENT__DYNAMIC_DESCRIPTION = LINK_EVENT__DYNAMIC_DESCRIPTION;

	/**
     * The feature id for the '<em><b>Step Summary</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CATCH_LINK_EVENT__STEP_SUMMARY = LINK_EVENT__STEP_SUMMARY;

	/**
     * The feature id for the '<em><b>From</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CATCH_LINK_EVENT__FROM = LINK_EVENT_FEATURE_COUNT + 0;

	/**
     * The number of structural features of the '<em>Catch Link Event</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CATCH_LINK_EVENT_FEATURE_COUNT = LINK_EVENT_FEATURE_COUNT + 1;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.impl.MessageEventImpl <em>Message Event</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.impl.MessageEventImpl
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getMessageEvent()
     * @generated
     */
	int MESSAGE_EVENT = 68;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MESSAGE_EVENT__DOCUMENTATION = EVENT__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MESSAGE_EVENT__NAME = EVENT__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MESSAGE_EVENT__TEXT_ANNOTATION_ATTACHMENT = EVENT__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Outgoing</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MESSAGE_EVENT__OUTGOING = EVENT__OUTGOING;

	/**
     * The feature id for the '<em><b>Incoming</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MESSAGE_EVENT__INCOMING = EVENT__INCOMING;

	/**
     * The feature id for the '<em><b>Dynamic Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MESSAGE_EVENT__DYNAMIC_LABEL = EVENT__DYNAMIC_LABEL;

	/**
     * The feature id for the '<em><b>Dynamic Description</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MESSAGE_EVENT__DYNAMIC_DESCRIPTION = EVENT__DYNAMIC_DESCRIPTION;

	/**
     * The feature id for the '<em><b>Step Summary</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MESSAGE_EVENT__STEP_SUMMARY = EVENT__STEP_SUMMARY;

	/**
     * The number of structural features of the '<em>Message Event</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MESSAGE_EVENT_FEATURE_COUNT = EVENT_FEATURE_COUNT + 0;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.CatchMessageEvent <em>Catch Message Event</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.CatchMessageEvent
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getCatchMessageEvent()
     * @generated
     */
	int CATCH_MESSAGE_EVENT = 19;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CATCH_MESSAGE_EVENT__DOCUMENTATION = MESSAGE_EVENT__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CATCH_MESSAGE_EVENT__NAME = MESSAGE_EVENT__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CATCH_MESSAGE_EVENT__TEXT_ANNOTATION_ATTACHMENT = MESSAGE_EVENT__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Outgoing</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CATCH_MESSAGE_EVENT__OUTGOING = MESSAGE_EVENT__OUTGOING;

	/**
     * The feature id for the '<em><b>Incoming</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CATCH_MESSAGE_EVENT__INCOMING = MESSAGE_EVENT__INCOMING;

	/**
     * The feature id for the '<em><b>Dynamic Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CATCH_MESSAGE_EVENT__DYNAMIC_LABEL = MESSAGE_EVENT__DYNAMIC_LABEL;

	/**
     * The feature id for the '<em><b>Dynamic Description</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CATCH_MESSAGE_EVENT__DYNAMIC_DESCRIPTION = MESSAGE_EVENT__DYNAMIC_DESCRIPTION;

	/**
     * The feature id for the '<em><b>Step Summary</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CATCH_MESSAGE_EVENT__STEP_SUMMARY = MESSAGE_EVENT__STEP_SUMMARY;

	/**
     * The feature id for the '<em><b>Event</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CATCH_MESSAGE_EVENT__EVENT = MESSAGE_EVENT_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Incoming Messag</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CATCH_MESSAGE_EVENT__INCOMING_MESSAG = MESSAGE_EVENT_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Correlation</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CATCH_MESSAGE_EVENT__CORRELATION = MESSAGE_EVENT_FEATURE_COUNT + 2;

	/**
     * The feature id for the '<em><b>Message Content</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CATCH_MESSAGE_EVENT__MESSAGE_CONTENT = MESSAGE_EVENT_FEATURE_COUNT + 3;

	/**
     * The feature id for the '<em><b>Data</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CATCH_MESSAGE_EVENT__DATA = MESSAGE_EVENT_FEATURE_COUNT + 4;

	/**
     * The number of structural features of the '<em>Catch Message Event</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CATCH_MESSAGE_EVENT_FEATURE_COUNT = MESSAGE_EVENT_FEATURE_COUNT + 5;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.SignalEvent <em>Signal Event</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.SignalEvent
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getSignalEvent()
     * @generated
     */
	int SIGNAL_EVENT = 78;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SIGNAL_EVENT__DOCUMENTATION = ELEMENT__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SIGNAL_EVENT__NAME = ELEMENT__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SIGNAL_EVENT__TEXT_ANNOTATION_ATTACHMENT = ELEMENT__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Signal Code</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SIGNAL_EVENT__SIGNAL_CODE = ELEMENT_FEATURE_COUNT + 0;

	/**
     * The number of structural features of the '<em>Signal Event</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SIGNAL_EVENT_FEATURE_COUNT = ELEMENT_FEATURE_COUNT + 1;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.CatchSignalEvent <em>Catch Signal Event</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.CatchSignalEvent
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getCatchSignalEvent()
     * @generated
     */
	int CATCH_SIGNAL_EVENT = 20;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CATCH_SIGNAL_EVENT__DOCUMENTATION = SIGNAL_EVENT__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CATCH_SIGNAL_EVENT__NAME = SIGNAL_EVENT__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CATCH_SIGNAL_EVENT__TEXT_ANNOTATION_ATTACHMENT = SIGNAL_EVENT__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Signal Code</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CATCH_SIGNAL_EVENT__SIGNAL_CODE = SIGNAL_EVENT__SIGNAL_CODE;

	/**
     * The number of structural features of the '<em>Catch Signal Event</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CATCH_SIGNAL_EVENT_FEATURE_COUNT = SIGNAL_EVENT_FEATURE_COUNT + 0;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.impl.ConnectableElementImpl <em>Connectable Element</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.impl.ConnectableElementImpl
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getConnectableElement()
     * @generated
     */
	int CONNECTABLE_ELEMENT = 21;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONNECTABLE_ELEMENT__DOCUMENTATION = ELEMENT__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONNECTABLE_ELEMENT__NAME = ELEMENT__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONNECTABLE_ELEMENT__TEXT_ANNOTATION_ATTACHMENT = ELEMENT__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Data</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONNECTABLE_ELEMENT__DATA = ELEMENT_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Connectors</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONNECTABLE_ELEMENT__CONNECTORS = ELEMENT_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Kpis</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONNECTABLE_ELEMENT__KPIS = ELEMENT_FEATURE_COUNT + 2;

	/**
     * The number of structural features of the '<em>Connectable Element</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONNECTABLE_ELEMENT_FEATURE_COUNT = ELEMENT_FEATURE_COUNT + 3;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.impl.ContractImpl <em>Contract</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.impl.ContractImpl
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getContract()
     * @generated
     */
	int CONTRACT = 24;

	/**
     * The feature id for the '<em><b>Inputs</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONTRACT__INPUTS = 0;

	/**
     * The feature id for the '<em><b>Constraints</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONTRACT__CONSTRAINTS = 1;

	/**
     * The number of structural features of the '<em>Contract</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONTRACT_FEATURE_COUNT = 2;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.impl.ContractInputMappingImpl <em>Contract Input Mapping</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.impl.ContractInputMappingImpl
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getContractInputMapping()
     * @generated
     */
	int CONTRACT_INPUT_MAPPING = 25;

	/**
     * The feature id for the '<em><b>Data</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONTRACT_INPUT_MAPPING__DATA = 0;

	/**
     * The feature id for the '<em><b>Setter Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONTRACT_INPUT_MAPPING__SETTER_NAME = 1;

	/**
     * The feature id for the '<em><b>Setter Param Type</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONTRACT_INPUT_MAPPING__SETTER_PARAM_TYPE = 2;

	/**
     * The number of structural features of the '<em>Contract Input Mapping</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONTRACT_INPUT_MAPPING_FEATURE_COUNT = 3;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.impl.ContractInputImpl <em>Contract Input</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.impl.ContractInputImpl
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getContractInput()
     * @generated
     */
	int CONTRACT_INPUT = 26;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONTRACT_INPUT__NAME = 0;

	/**
     * The feature id for the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONTRACT_INPUT__TYPE = 1;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONTRACT_INPUT__DESCRIPTION = 2;

	/**
     * The feature id for the '<em><b>Multiple</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONTRACT_INPUT__MULTIPLE = 3;

	/**
     * The feature id for the '<em><b>Mapping</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONTRACT_INPUT__MAPPING = 4;

	/**
     * The feature id for the '<em><b>Inputs</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONTRACT_INPUT__INPUTS = 5;

	/**
     * The feature id for the '<em><b>Data Reference</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONTRACT_INPUT__DATA_REFERENCE = 6;

    /**
     * The feature id for the '<em><b>Create Mode</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONTRACT_INPUT__CREATE_MODE = 7;

    /**
     * The number of structural features of the '<em>Contract Input</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONTRACT_INPUT_FEATURE_COUNT = 8;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.impl.ContractConstraintImpl <em>Contract Constraint</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.impl.ContractConstraintImpl
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getContractConstraint()
     * @generated
     */
	int CONTRACT_CONSTRAINT = 27;

	/**
     * The feature id for the '<em><b>Expression</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONTRACT_CONSTRAINT__EXPRESSION = 0;

	/**
     * The feature id for the '<em><b>Error Message</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONTRACT_CONSTRAINT__ERROR_MESSAGE = 1;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONTRACT_CONSTRAINT__NAME = 2;

	/**
     * The feature id for the '<em><b>Input Names</b></em>' attribute list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONTRACT_CONSTRAINT__INPUT_NAMES = 3;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONTRACT_CONSTRAINT__DESCRIPTION = 4;

    /**
     * The number of structural features of the '<em>Contract Constraint</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONTRACT_CONSTRAINT_FEATURE_COUNT = 5;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.impl.ContractContainerImpl <em>Contract Container</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.impl.ContractContainerImpl
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getContractContainer()
     * @generated
     */
	int CONTRACT_CONTAINER = 28;

	/**
     * The feature id for the '<em><b>Contract</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONTRACT_CONTAINER__CONTRACT = 0;

	/**
     * The number of structural features of the '<em>Contract Container</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONTRACT_CONTAINER_FEATURE_COUNT = 1;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.impl.CorrelationImpl <em>Correlation</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.impl.CorrelationImpl
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getCorrelation()
     * @generated
     */
	int CORRELATION = 30;

	/**
     * The feature id for the '<em><b>Correlation Type</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CORRELATION__CORRELATION_TYPE = 0;

	/**
     * The feature id for the '<em><b>Correlation Association</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CORRELATION__CORRELATION_ASSOCIATION = 1;

	/**
     * The number of structural features of the '<em>Correlation</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CORRELATION_FEATURE_COUNT = 2;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.impl.CorrelationAssociationImpl <em>Correlation Association</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.impl.CorrelationAssociationImpl
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getCorrelationAssociation()
     * @generated
     */
	int CORRELATION_ASSOCIATION = 31;

	/**
     * The feature id for the '<em><b>Correlation Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CORRELATION_ASSOCIATION__CORRELATION_EXPRESSION = 0;

	/**
     * The feature id for the '<em><b>Correlation Key</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CORRELATION_ASSOCIATION__CORRELATION_KEY = 1;

	/**
     * The number of structural features of the '<em>Correlation Association</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CORRELATION_ASSOCIATION_FEATURE_COUNT = 2;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.impl.DataAwareImpl <em>Data Aware</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.impl.DataAwareImpl
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getDataAware()
     * @generated
     */
	int DATA_AWARE = 33;

	/**
     * The feature id for the '<em><b>Data</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DATA_AWARE__DATA = 0;

	/**
     * The number of structural features of the '<em>Data Aware</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DATA_AWARE_FEATURE_COUNT = 1;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.impl.StringTypeImpl <em>String Type</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.impl.StringTypeImpl
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getStringType()
     * @generated
     */
	int STRING_TYPE = 80;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int STRING_TYPE__DOCUMENTATION = DATA_TYPE__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int STRING_TYPE__NAME = DATA_TYPE__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int STRING_TYPE__TEXT_ANNOTATION_ATTACHMENT = DATA_TYPE__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The number of structural features of the '<em>String Type</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int STRING_TYPE_FEATURE_COUNT = DATA_TYPE_FEATURE_COUNT + 0;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.impl.DateTypeImpl <em>Date Type</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.impl.DateTypeImpl
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getDateType()
     * @generated
     */
	int DATE_TYPE = 34;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DATE_TYPE__DOCUMENTATION = STRING_TYPE__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DATE_TYPE__NAME = STRING_TYPE__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DATE_TYPE__TEXT_ANNOTATION_ATTACHMENT = STRING_TYPE__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The number of structural features of the '<em>Date Type</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DATE_TYPE_FEATURE_COUNT = STRING_TYPE_FEATURE_COUNT + 0;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.impl.DocumentImpl <em>Document</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.impl.DocumentImpl
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getDocument()
     * @generated
     */
	int DOCUMENT = 36;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DOCUMENT__DOCUMENTATION = ELEMENT__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DOCUMENT__NAME = ELEMENT__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DOCUMENT__TEXT_ANNOTATION_ATTACHMENT = ELEMENT__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Default Value Id Of Document Store</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DOCUMENT__DEFAULT_VALUE_ID_OF_DOCUMENT_STORE = ELEMENT_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Mime Type</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DOCUMENT__MIME_TYPE = ELEMENT_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Url</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DOCUMENT__URL = ELEMENT_FEATURE_COUNT + 2;

	/**
     * The feature id for the '<em><b>Document Type</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DOCUMENT__DOCUMENT_TYPE = ELEMENT_FEATURE_COUNT + 3;

	/**
     * The feature id for the '<em><b>Initial Multiple Content</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DOCUMENT__INITIAL_MULTIPLE_CONTENT = ELEMENT_FEATURE_COUNT + 4;

	/**
     * The feature id for the '<em><b>Multiple</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DOCUMENT__MULTIPLE = ELEMENT_FEATURE_COUNT + 5;

	/**
     * The feature id for the '<em><b>Contract Input</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DOCUMENT__CONTRACT_INPUT = ELEMENT_FEATURE_COUNT + 6;

	/**
     * The number of structural features of the '<em>Document</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DOCUMENT_FEATURE_COUNT = ELEMENT_FEATURE_COUNT + 7;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.impl.DoubleTypeImpl <em>Double Type</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.impl.DoubleTypeImpl
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getDoubleType()
     * @generated
     */
	int DOUBLE_TYPE = 37;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DOUBLE_TYPE__DOCUMENTATION = DATA_TYPE__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DOUBLE_TYPE__NAME = DATA_TYPE__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DOUBLE_TYPE__TEXT_ANNOTATION_ATTACHMENT = DATA_TYPE__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The number of structural features of the '<em>Double Type</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DOUBLE_TYPE_FEATURE_COUNT = DATA_TYPE_FEATURE_COUNT + 0;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.impl.EndErrorEventImpl <em>End Error Event</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.impl.EndErrorEventImpl
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getEndErrorEvent()
     * @generated
     */
	int END_ERROR_EVENT = 40;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int END_ERROR_EVENT__DOCUMENTATION = EVENT__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int END_ERROR_EVENT__NAME = EVENT__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int END_ERROR_EVENT__TEXT_ANNOTATION_ATTACHMENT = EVENT__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Outgoing</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int END_ERROR_EVENT__OUTGOING = EVENT__OUTGOING;

	/**
     * The feature id for the '<em><b>Incoming</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int END_ERROR_EVENT__INCOMING = EVENT__INCOMING;

	/**
     * The feature id for the '<em><b>Dynamic Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int END_ERROR_EVENT__DYNAMIC_LABEL = EVENT__DYNAMIC_LABEL;

	/**
     * The feature id for the '<em><b>Dynamic Description</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int END_ERROR_EVENT__DYNAMIC_DESCRIPTION = EVENT__DYNAMIC_DESCRIPTION;

	/**
     * The feature id for the '<em><b>Step Summary</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int END_ERROR_EVENT__STEP_SUMMARY = EVENT__STEP_SUMMARY;

	/**
     * The feature id for the '<em><b>Error Code</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int END_ERROR_EVENT__ERROR_CODE = EVENT_FEATURE_COUNT + 0;

	/**
     * The number of structural features of the '<em>End Error Event</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int END_ERROR_EVENT_FEATURE_COUNT = EVENT_FEATURE_COUNT + 1;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.impl.EndEventImpl <em>End Event</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.impl.EndEventImpl
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getEndEvent()
     * @generated
     */
	int END_EVENT = 41;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int END_EVENT__DOCUMENTATION = EVENT__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int END_EVENT__NAME = EVENT__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int END_EVENT__TEXT_ANNOTATION_ATTACHMENT = EVENT__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Outgoing</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int END_EVENT__OUTGOING = EVENT__OUTGOING;

	/**
     * The feature id for the '<em><b>Incoming</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int END_EVENT__INCOMING = EVENT__INCOMING;

	/**
     * The feature id for the '<em><b>Dynamic Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int END_EVENT__DYNAMIC_LABEL = EVENT__DYNAMIC_LABEL;

	/**
     * The feature id for the '<em><b>Dynamic Description</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int END_EVENT__DYNAMIC_DESCRIPTION = EVENT__DYNAMIC_DESCRIPTION;

	/**
     * The feature id for the '<em><b>Step Summary</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int END_EVENT__STEP_SUMMARY = EVENT__STEP_SUMMARY;

	/**
     * The number of structural features of the '<em>End Event</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int END_EVENT_FEATURE_COUNT = EVENT_FEATURE_COUNT + 0;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.impl.ThrowMessageEventImpl <em>Throw Message Event</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.impl.ThrowMessageEventImpl
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getThrowMessageEvent()
     * @generated
     */
	int THROW_MESSAGE_EVENT = 97;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int THROW_MESSAGE_EVENT__DOCUMENTATION = MESSAGE_EVENT__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int THROW_MESSAGE_EVENT__NAME = MESSAGE_EVENT__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int THROW_MESSAGE_EVENT__TEXT_ANNOTATION_ATTACHMENT = MESSAGE_EVENT__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Outgoing</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int THROW_MESSAGE_EVENT__OUTGOING = MESSAGE_EVENT__OUTGOING;

	/**
     * The feature id for the '<em><b>Incoming</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int THROW_MESSAGE_EVENT__INCOMING = MESSAGE_EVENT__INCOMING;

	/**
     * The feature id for the '<em><b>Dynamic Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int THROW_MESSAGE_EVENT__DYNAMIC_LABEL = MESSAGE_EVENT__DYNAMIC_LABEL;

	/**
     * The feature id for the '<em><b>Dynamic Description</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int THROW_MESSAGE_EVENT__DYNAMIC_DESCRIPTION = MESSAGE_EVENT__DYNAMIC_DESCRIPTION;

	/**
     * The feature id for the '<em><b>Step Summary</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int THROW_MESSAGE_EVENT__STEP_SUMMARY = MESSAGE_EVENT__STEP_SUMMARY;

	/**
     * The feature id for the '<em><b>Events</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int THROW_MESSAGE_EVENT__EVENTS = MESSAGE_EVENT_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Outgoing Messages</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int THROW_MESSAGE_EVENT__OUTGOING_MESSAGES = MESSAGE_EVENT_FEATURE_COUNT + 1;

	/**
     * The number of structural features of the '<em>Throw Message Event</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int THROW_MESSAGE_EVENT_FEATURE_COUNT = MESSAGE_EVENT_FEATURE_COUNT + 2;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.impl.EndMessageEventImpl <em>End Message Event</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.impl.EndMessageEventImpl
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getEndMessageEvent()
     * @generated
     */
	int END_MESSAGE_EVENT = 42;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int END_MESSAGE_EVENT__DOCUMENTATION = THROW_MESSAGE_EVENT__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int END_MESSAGE_EVENT__NAME = THROW_MESSAGE_EVENT__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int END_MESSAGE_EVENT__TEXT_ANNOTATION_ATTACHMENT = THROW_MESSAGE_EVENT__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Outgoing</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int END_MESSAGE_EVENT__OUTGOING = THROW_MESSAGE_EVENT__OUTGOING;

	/**
     * The feature id for the '<em><b>Incoming</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int END_MESSAGE_EVENT__INCOMING = THROW_MESSAGE_EVENT__INCOMING;

	/**
     * The feature id for the '<em><b>Dynamic Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int END_MESSAGE_EVENT__DYNAMIC_LABEL = THROW_MESSAGE_EVENT__DYNAMIC_LABEL;

	/**
     * The feature id for the '<em><b>Dynamic Description</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int END_MESSAGE_EVENT__DYNAMIC_DESCRIPTION = THROW_MESSAGE_EVENT__DYNAMIC_DESCRIPTION;

	/**
     * The feature id for the '<em><b>Step Summary</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int END_MESSAGE_EVENT__STEP_SUMMARY = THROW_MESSAGE_EVENT__STEP_SUMMARY;

	/**
     * The feature id for the '<em><b>Events</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int END_MESSAGE_EVENT__EVENTS = THROW_MESSAGE_EVENT__EVENTS;

	/**
     * The feature id for the '<em><b>Outgoing Messages</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int END_MESSAGE_EVENT__OUTGOING_MESSAGES = THROW_MESSAGE_EVENT__OUTGOING_MESSAGES;

	/**
     * The number of structural features of the '<em>End Message Event</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int END_MESSAGE_EVENT_FEATURE_COUNT = THROW_MESSAGE_EVENT_FEATURE_COUNT + 0;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.ThrowSignalEvent <em>Throw Signal Event</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.ThrowSignalEvent
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getThrowSignalEvent()
     * @generated
     */
	int THROW_SIGNAL_EVENT = 95;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int THROW_SIGNAL_EVENT__DOCUMENTATION = SIGNAL_EVENT__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int THROW_SIGNAL_EVENT__NAME = SIGNAL_EVENT__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int THROW_SIGNAL_EVENT__TEXT_ANNOTATION_ATTACHMENT = SIGNAL_EVENT__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Signal Code</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int THROW_SIGNAL_EVENT__SIGNAL_CODE = SIGNAL_EVENT__SIGNAL_CODE;

	/**
     * The number of structural features of the '<em>Throw Signal Event</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int THROW_SIGNAL_EVENT_FEATURE_COUNT = SIGNAL_EVENT_FEATURE_COUNT + 0;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.impl.EndSignalEventImpl <em>End Signal Event</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.impl.EndSignalEventImpl
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getEndSignalEvent()
     * @generated
     */
	int END_SIGNAL_EVENT = 43;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int END_SIGNAL_EVENT__DOCUMENTATION = THROW_SIGNAL_EVENT__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int END_SIGNAL_EVENT__NAME = THROW_SIGNAL_EVENT__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int END_SIGNAL_EVENT__TEXT_ANNOTATION_ATTACHMENT = THROW_SIGNAL_EVENT__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Signal Code</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int END_SIGNAL_EVENT__SIGNAL_CODE = THROW_SIGNAL_EVENT__SIGNAL_CODE;

	/**
     * The feature id for the '<em><b>Outgoing</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int END_SIGNAL_EVENT__OUTGOING = THROW_SIGNAL_EVENT_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Incoming</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int END_SIGNAL_EVENT__INCOMING = THROW_SIGNAL_EVENT_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Dynamic Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int END_SIGNAL_EVENT__DYNAMIC_LABEL = THROW_SIGNAL_EVENT_FEATURE_COUNT + 2;

	/**
     * The feature id for the '<em><b>Dynamic Description</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int END_SIGNAL_EVENT__DYNAMIC_DESCRIPTION = THROW_SIGNAL_EVENT_FEATURE_COUNT + 3;

	/**
     * The feature id for the '<em><b>Step Summary</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int END_SIGNAL_EVENT__STEP_SUMMARY = THROW_SIGNAL_EVENT_FEATURE_COUNT + 4;

	/**
     * The number of structural features of the '<em>End Signal Event</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int END_SIGNAL_EVENT_FEATURE_COUNT = THROW_SIGNAL_EVENT_FEATURE_COUNT + 5;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.impl.EndTerminatedEventImpl <em>End Terminated Event</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.impl.EndTerminatedEventImpl
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getEndTerminatedEvent()
     * @generated
     */
	int END_TERMINATED_EVENT = 44;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int END_TERMINATED_EVENT__DOCUMENTATION = EVENT__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int END_TERMINATED_EVENT__NAME = EVENT__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int END_TERMINATED_EVENT__TEXT_ANNOTATION_ATTACHMENT = EVENT__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Outgoing</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int END_TERMINATED_EVENT__OUTGOING = EVENT__OUTGOING;

	/**
     * The feature id for the '<em><b>Incoming</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int END_TERMINATED_EVENT__INCOMING = EVENT__INCOMING;

	/**
     * The feature id for the '<em><b>Dynamic Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int END_TERMINATED_EVENT__DYNAMIC_LABEL = EVENT__DYNAMIC_LABEL;

	/**
     * The feature id for the '<em><b>Dynamic Description</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int END_TERMINATED_EVENT__DYNAMIC_DESCRIPTION = EVENT__DYNAMIC_DESCRIPTION;

	/**
     * The feature id for the '<em><b>Step Summary</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int END_TERMINATED_EVENT__STEP_SUMMARY = EVENT__STEP_SUMMARY;

	/**
     * The number of structural features of the '<em>End Terminated Event</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int END_TERMINATED_EVENT_FEATURE_COUNT = EVENT_FEATURE_COUNT + 0;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.ErrorEvent <em>Error Event</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.ErrorEvent
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getErrorEvent()
     * @generated
     */
	int ERROR_EVENT = 45;

	/**
     * The feature id for the '<em><b>Error Code</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ERROR_EVENT__ERROR_CODE = 0;

	/**
     * The number of structural features of the '<em>Error Event</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ERROR_EVENT_FEATURE_COUNT = 1;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.impl.EnumTypeImpl <em>Enum Type</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.impl.EnumTypeImpl
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getEnumType()
     * @generated
     */
	int ENUM_TYPE = 46;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ENUM_TYPE__DOCUMENTATION = DATA_TYPE__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ENUM_TYPE__NAME = DATA_TYPE__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ENUM_TYPE__TEXT_ANNOTATION_ATTACHMENT = DATA_TYPE__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Literals</b></em>' attribute list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ENUM_TYPE__LITERALS = DATA_TYPE_FEATURE_COUNT + 0;

	/**
     * The number of structural features of the '<em>Enum Type</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ENUM_TYPE_FEATURE_COUNT = DATA_TYPE_FEATURE_COUNT + 1;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.impl.FloatTypeImpl <em>Float Type</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.impl.FloatTypeImpl
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getFloatType()
     * @generated
     */
	int FLOAT_TYPE = 47;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FLOAT_TYPE__DOCUMENTATION = DATA_TYPE__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FLOAT_TYPE__NAME = DATA_TYPE__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FLOAT_TYPE__TEXT_ANNOTATION_ATTACHMENT = DATA_TYPE__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The number of structural features of the '<em>Float Type</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FLOAT_TYPE_FEATURE_COUNT = DATA_TYPE_FEATURE_COUNT + 0;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.impl.FormMappingImpl <em>Form Mapping</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.impl.FormMappingImpl
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getFormMapping()
     * @generated
     */
	int FORM_MAPPING = 49;

	/**
     * The feature id for the '<em><b>Target Form</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FORM_MAPPING__TARGET_FORM = 0;

	/**
     * The feature id for the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FORM_MAPPING__TYPE = 1;

	/**
     * The feature id for the '<em><b>Url</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FORM_MAPPING__URL = 2;

	/**
     * The number of structural features of the '<em>Form Mapping</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FORM_MAPPING_FEATURE_COUNT = 3;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.impl.InclusiveGatewayImpl <em>Inclusive Gateway</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.impl.InclusiveGatewayImpl
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getInclusiveGateway()
     * @generated
     */
	int INCLUSIVE_GATEWAY = 51;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INCLUSIVE_GATEWAY__DOCUMENTATION = GATEWAY__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INCLUSIVE_GATEWAY__NAME = GATEWAY__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INCLUSIVE_GATEWAY__TEXT_ANNOTATION_ATTACHMENT = GATEWAY__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Outgoing</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INCLUSIVE_GATEWAY__OUTGOING = GATEWAY__OUTGOING;

	/**
     * The feature id for the '<em><b>Incoming</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INCLUSIVE_GATEWAY__INCOMING = GATEWAY__INCOMING;

	/**
     * The feature id for the '<em><b>Dynamic Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INCLUSIVE_GATEWAY__DYNAMIC_LABEL = GATEWAY__DYNAMIC_LABEL;

	/**
     * The feature id for the '<em><b>Dynamic Description</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INCLUSIVE_GATEWAY__DYNAMIC_DESCRIPTION = GATEWAY__DYNAMIC_DESCRIPTION;

	/**
     * The feature id for the '<em><b>Step Summary</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INCLUSIVE_GATEWAY__STEP_SUMMARY = GATEWAY__STEP_SUMMARY;

	/**
     * The feature id for the '<em><b>Gateway Type</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INCLUSIVE_GATEWAY__GATEWAY_TYPE = GATEWAY__GATEWAY_TYPE;

	/**
     * The number of structural features of the '<em>Inclusive Gateway</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INCLUSIVE_GATEWAY_FEATURE_COUNT = GATEWAY_FEATURE_COUNT + 0;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.impl.InputMappingImpl <em>Input Mapping</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.impl.InputMappingImpl
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getInputMapping()
     * @generated
     */
	int INPUT_MAPPING = 52;

	/**
     * The feature id for the '<em><b>Process Source</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INPUT_MAPPING__PROCESS_SOURCE = 0;

	/**
     * The feature id for the '<em><b>Subprocess Target</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INPUT_MAPPING__SUBPROCESS_TARGET = 1;

	/**
     * The feature id for the '<em><b>Assignation Type</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INPUT_MAPPING__ASSIGNATION_TYPE = 2;

	/**
     * The number of structural features of the '<em>Input Mapping</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INPUT_MAPPING_FEATURE_COUNT = 3;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.impl.IntegerTypeImpl <em>Integer Type</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.impl.IntegerTypeImpl
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getIntegerType()
     * @generated
     */
	int INTEGER_TYPE = 53;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INTEGER_TYPE__DOCUMENTATION = DATA_TYPE__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INTEGER_TYPE__NAME = DATA_TYPE__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INTEGER_TYPE__TEXT_ANNOTATION_ATTACHMENT = DATA_TYPE__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The number of structural features of the '<em>Integer Type</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INTEGER_TYPE_FEATURE_COUNT = DATA_TYPE_FEATURE_COUNT + 0;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.impl.IntermediateErrorCatchEventImpl <em>Intermediate Error Catch Event</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.impl.IntermediateErrorCatchEventImpl
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getIntermediateErrorCatchEvent()
     * @generated
     */
	int INTERMEDIATE_ERROR_CATCH_EVENT = 54;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INTERMEDIATE_ERROR_CATCH_EVENT__DOCUMENTATION = BOUNDARY_EVENT__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INTERMEDIATE_ERROR_CATCH_EVENT__NAME = BOUNDARY_EVENT__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INTERMEDIATE_ERROR_CATCH_EVENT__TEXT_ANNOTATION_ATTACHMENT = BOUNDARY_EVENT__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Outgoing</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INTERMEDIATE_ERROR_CATCH_EVENT__OUTGOING = BOUNDARY_EVENT__OUTGOING;

	/**
     * The feature id for the '<em><b>Error Code</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INTERMEDIATE_ERROR_CATCH_EVENT__ERROR_CODE = BOUNDARY_EVENT_FEATURE_COUNT + 0;

	/**
     * The number of structural features of the '<em>Intermediate Error Catch Event</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INTERMEDIATE_ERROR_CATCH_EVENT_FEATURE_COUNT = BOUNDARY_EVENT_FEATURE_COUNT + 1;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.impl.IntermediateCatchSignalEventImpl <em>Intermediate Catch Signal Event</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.impl.IntermediateCatchSignalEventImpl
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getIntermediateCatchSignalEvent()
     * @generated
     */
	int INTERMEDIATE_CATCH_SIGNAL_EVENT = 55;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INTERMEDIATE_CATCH_SIGNAL_EVENT__DOCUMENTATION = CATCH_SIGNAL_EVENT__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INTERMEDIATE_CATCH_SIGNAL_EVENT__NAME = CATCH_SIGNAL_EVENT__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INTERMEDIATE_CATCH_SIGNAL_EVENT__TEXT_ANNOTATION_ATTACHMENT = CATCH_SIGNAL_EVENT__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Signal Code</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INTERMEDIATE_CATCH_SIGNAL_EVENT__SIGNAL_CODE = CATCH_SIGNAL_EVENT__SIGNAL_CODE;

	/**
     * The feature id for the '<em><b>Outgoing</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INTERMEDIATE_CATCH_SIGNAL_EVENT__OUTGOING = CATCH_SIGNAL_EVENT_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Incoming</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INTERMEDIATE_CATCH_SIGNAL_EVENT__INCOMING = CATCH_SIGNAL_EVENT_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Dynamic Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INTERMEDIATE_CATCH_SIGNAL_EVENT__DYNAMIC_LABEL = CATCH_SIGNAL_EVENT_FEATURE_COUNT + 2;

	/**
     * The feature id for the '<em><b>Dynamic Description</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INTERMEDIATE_CATCH_SIGNAL_EVENT__DYNAMIC_DESCRIPTION = CATCH_SIGNAL_EVENT_FEATURE_COUNT + 3;

	/**
     * The feature id for the '<em><b>Step Summary</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INTERMEDIATE_CATCH_SIGNAL_EVENT__STEP_SUMMARY = CATCH_SIGNAL_EVENT_FEATURE_COUNT + 4;

	/**
     * The feature id for the '<em><b>Data</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INTERMEDIATE_CATCH_SIGNAL_EVENT__DATA = CATCH_SIGNAL_EVENT_FEATURE_COUNT + 5;

	/**
     * The number of structural features of the '<em>Intermediate Catch Signal Event</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INTERMEDIATE_CATCH_SIGNAL_EVENT_FEATURE_COUNT = CATCH_SIGNAL_EVENT_FEATURE_COUNT + 6;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.impl.IntermediateThrowSignalEventImpl <em>Intermediate Throw Signal Event</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.impl.IntermediateThrowSignalEventImpl
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getIntermediateThrowSignalEvent()
     * @generated
     */
	int INTERMEDIATE_THROW_SIGNAL_EVENT = 56;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INTERMEDIATE_THROW_SIGNAL_EVENT__DOCUMENTATION = THROW_SIGNAL_EVENT__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INTERMEDIATE_THROW_SIGNAL_EVENT__NAME = THROW_SIGNAL_EVENT__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INTERMEDIATE_THROW_SIGNAL_EVENT__TEXT_ANNOTATION_ATTACHMENT = THROW_SIGNAL_EVENT__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Signal Code</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INTERMEDIATE_THROW_SIGNAL_EVENT__SIGNAL_CODE = THROW_SIGNAL_EVENT__SIGNAL_CODE;

	/**
     * The feature id for the '<em><b>Outgoing</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INTERMEDIATE_THROW_SIGNAL_EVENT__OUTGOING = THROW_SIGNAL_EVENT_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Incoming</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INTERMEDIATE_THROW_SIGNAL_EVENT__INCOMING = THROW_SIGNAL_EVENT_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Dynamic Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INTERMEDIATE_THROW_SIGNAL_EVENT__DYNAMIC_LABEL = THROW_SIGNAL_EVENT_FEATURE_COUNT + 2;

	/**
     * The feature id for the '<em><b>Dynamic Description</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INTERMEDIATE_THROW_SIGNAL_EVENT__DYNAMIC_DESCRIPTION = THROW_SIGNAL_EVENT_FEATURE_COUNT + 3;

	/**
     * The feature id for the '<em><b>Step Summary</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INTERMEDIATE_THROW_SIGNAL_EVENT__STEP_SUMMARY = THROW_SIGNAL_EVENT_FEATURE_COUNT + 4;

	/**
     * The number of structural features of the '<em>Intermediate Throw Signal Event</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INTERMEDIATE_THROW_SIGNAL_EVENT_FEATURE_COUNT = THROW_SIGNAL_EVENT_FEATURE_COUNT + 5;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.impl.IntermediateCatchMessageEventImpl <em>Intermediate Catch Message Event</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.impl.IntermediateCatchMessageEventImpl
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getIntermediateCatchMessageEvent()
     * @generated
     */
	int INTERMEDIATE_CATCH_MESSAGE_EVENT = 57;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INTERMEDIATE_CATCH_MESSAGE_EVENT__DOCUMENTATION = CATCH_MESSAGE_EVENT__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INTERMEDIATE_CATCH_MESSAGE_EVENT__NAME = CATCH_MESSAGE_EVENT__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INTERMEDIATE_CATCH_MESSAGE_EVENT__TEXT_ANNOTATION_ATTACHMENT = CATCH_MESSAGE_EVENT__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Outgoing</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INTERMEDIATE_CATCH_MESSAGE_EVENT__OUTGOING = CATCH_MESSAGE_EVENT__OUTGOING;

	/**
     * The feature id for the '<em><b>Incoming</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INTERMEDIATE_CATCH_MESSAGE_EVENT__INCOMING = CATCH_MESSAGE_EVENT__INCOMING;

	/**
     * The feature id for the '<em><b>Dynamic Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INTERMEDIATE_CATCH_MESSAGE_EVENT__DYNAMIC_LABEL = CATCH_MESSAGE_EVENT__DYNAMIC_LABEL;

	/**
     * The feature id for the '<em><b>Dynamic Description</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INTERMEDIATE_CATCH_MESSAGE_EVENT__DYNAMIC_DESCRIPTION = CATCH_MESSAGE_EVENT__DYNAMIC_DESCRIPTION;

	/**
     * The feature id for the '<em><b>Step Summary</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INTERMEDIATE_CATCH_MESSAGE_EVENT__STEP_SUMMARY = CATCH_MESSAGE_EVENT__STEP_SUMMARY;

	/**
     * The feature id for the '<em><b>Event</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INTERMEDIATE_CATCH_MESSAGE_EVENT__EVENT = CATCH_MESSAGE_EVENT__EVENT;

	/**
     * The feature id for the '<em><b>Incoming Messag</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INTERMEDIATE_CATCH_MESSAGE_EVENT__INCOMING_MESSAG = CATCH_MESSAGE_EVENT__INCOMING_MESSAG;

	/**
     * The feature id for the '<em><b>Correlation</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INTERMEDIATE_CATCH_MESSAGE_EVENT__CORRELATION = CATCH_MESSAGE_EVENT__CORRELATION;

	/**
     * The feature id for the '<em><b>Message Content</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INTERMEDIATE_CATCH_MESSAGE_EVENT__MESSAGE_CONTENT = CATCH_MESSAGE_EVENT__MESSAGE_CONTENT;

	/**
     * The feature id for the '<em><b>Data</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INTERMEDIATE_CATCH_MESSAGE_EVENT__DATA = CATCH_MESSAGE_EVENT__DATA;

	/**
     * The number of structural features of the '<em>Intermediate Catch Message Event</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INTERMEDIATE_CATCH_MESSAGE_EVENT_FEATURE_COUNT = CATCH_MESSAGE_EVENT_FEATURE_COUNT + 0;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.impl.IntermediateThrowMessageEventImpl <em>Intermediate Throw Message Event</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.impl.IntermediateThrowMessageEventImpl
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getIntermediateThrowMessageEvent()
     * @generated
     */
	int INTERMEDIATE_THROW_MESSAGE_EVENT = 58;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INTERMEDIATE_THROW_MESSAGE_EVENT__DOCUMENTATION = THROW_MESSAGE_EVENT__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INTERMEDIATE_THROW_MESSAGE_EVENT__NAME = THROW_MESSAGE_EVENT__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INTERMEDIATE_THROW_MESSAGE_EVENT__TEXT_ANNOTATION_ATTACHMENT = THROW_MESSAGE_EVENT__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Outgoing</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INTERMEDIATE_THROW_MESSAGE_EVENT__OUTGOING = THROW_MESSAGE_EVENT__OUTGOING;

	/**
     * The feature id for the '<em><b>Incoming</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INTERMEDIATE_THROW_MESSAGE_EVENT__INCOMING = THROW_MESSAGE_EVENT__INCOMING;

	/**
     * The feature id for the '<em><b>Dynamic Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INTERMEDIATE_THROW_MESSAGE_EVENT__DYNAMIC_LABEL = THROW_MESSAGE_EVENT__DYNAMIC_LABEL;

	/**
     * The feature id for the '<em><b>Dynamic Description</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INTERMEDIATE_THROW_MESSAGE_EVENT__DYNAMIC_DESCRIPTION = THROW_MESSAGE_EVENT__DYNAMIC_DESCRIPTION;

	/**
     * The feature id for the '<em><b>Step Summary</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INTERMEDIATE_THROW_MESSAGE_EVENT__STEP_SUMMARY = THROW_MESSAGE_EVENT__STEP_SUMMARY;

	/**
     * The feature id for the '<em><b>Events</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INTERMEDIATE_THROW_MESSAGE_EVENT__EVENTS = THROW_MESSAGE_EVENT__EVENTS;

	/**
     * The feature id for the '<em><b>Outgoing Messages</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INTERMEDIATE_THROW_MESSAGE_EVENT__OUTGOING_MESSAGES = THROW_MESSAGE_EVENT__OUTGOING_MESSAGES;

	/**
     * The number of structural features of the '<em>Intermediate Throw Message Event</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INTERMEDIATE_THROW_MESSAGE_EVENT_FEATURE_COUNT = THROW_MESSAGE_EVENT_FEATURE_COUNT + 0;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.impl.TimerEventImpl <em>Timer Event</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.impl.TimerEventImpl
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getTimerEvent()
     * @generated
     */
	int TIMER_EVENT = 98;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TIMER_EVENT__DOCUMENTATION = EVENT__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TIMER_EVENT__NAME = EVENT__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TIMER_EVENT__TEXT_ANNOTATION_ATTACHMENT = EVENT__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Outgoing</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TIMER_EVENT__OUTGOING = EVENT__OUTGOING;

	/**
     * The feature id for the '<em><b>Incoming</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TIMER_EVENT__INCOMING = EVENT__INCOMING;

	/**
     * The feature id for the '<em><b>Dynamic Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TIMER_EVENT__DYNAMIC_LABEL = EVENT__DYNAMIC_LABEL;

	/**
     * The feature id for the '<em><b>Dynamic Description</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TIMER_EVENT__DYNAMIC_DESCRIPTION = EVENT__DYNAMIC_DESCRIPTION;

	/**
     * The feature id for the '<em><b>Step Summary</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TIMER_EVENT__STEP_SUMMARY = EVENT__STEP_SUMMARY;

	/**
     * The feature id for the '<em><b>Condition</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TIMER_EVENT__CONDITION = EVENT_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Data</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TIMER_EVENT__DATA = EVENT_FEATURE_COUNT + 1;

	/**
     * The number of structural features of the '<em>Timer Event</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TIMER_EVENT_FEATURE_COUNT = EVENT_FEATURE_COUNT + 2;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.impl.IntermediateCatchTimerEventImpl <em>Intermediate Catch Timer Event</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.impl.IntermediateCatchTimerEventImpl
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getIntermediateCatchTimerEvent()
     * @generated
     */
	int INTERMEDIATE_CATCH_TIMER_EVENT = 59;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INTERMEDIATE_CATCH_TIMER_EVENT__DOCUMENTATION = TIMER_EVENT__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INTERMEDIATE_CATCH_TIMER_EVENT__NAME = TIMER_EVENT__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INTERMEDIATE_CATCH_TIMER_EVENT__TEXT_ANNOTATION_ATTACHMENT = TIMER_EVENT__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Outgoing</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INTERMEDIATE_CATCH_TIMER_EVENT__OUTGOING = TIMER_EVENT__OUTGOING;

	/**
     * The feature id for the '<em><b>Incoming</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INTERMEDIATE_CATCH_TIMER_EVENT__INCOMING = TIMER_EVENT__INCOMING;

	/**
     * The feature id for the '<em><b>Dynamic Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INTERMEDIATE_CATCH_TIMER_EVENT__DYNAMIC_LABEL = TIMER_EVENT__DYNAMIC_LABEL;

	/**
     * The feature id for the '<em><b>Dynamic Description</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INTERMEDIATE_CATCH_TIMER_EVENT__DYNAMIC_DESCRIPTION = TIMER_EVENT__DYNAMIC_DESCRIPTION;

	/**
     * The feature id for the '<em><b>Step Summary</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INTERMEDIATE_CATCH_TIMER_EVENT__STEP_SUMMARY = TIMER_EVENT__STEP_SUMMARY;

	/**
     * The feature id for the '<em><b>Condition</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INTERMEDIATE_CATCH_TIMER_EVENT__CONDITION = TIMER_EVENT__CONDITION;

	/**
     * The feature id for the '<em><b>Data</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INTERMEDIATE_CATCH_TIMER_EVENT__DATA = TIMER_EVENT__DATA;

	/**
     * The number of structural features of the '<em>Intermediate Catch Timer Event</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INTERMEDIATE_CATCH_TIMER_EVENT_FEATURE_COUNT = TIMER_EVENT_FEATURE_COUNT + 0;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.impl.JavaTypeImpl <em>Java Type</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.impl.JavaTypeImpl
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getJavaType()
     * @generated
     */
	int JAVA_TYPE = 61;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int JAVA_TYPE__DOCUMENTATION = DATA_TYPE__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int JAVA_TYPE__NAME = DATA_TYPE__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int JAVA_TYPE__TEXT_ANNOTATION_ATTACHMENT = DATA_TYPE__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The number of structural features of the '<em>Java Type</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int JAVA_TYPE_FEATURE_COUNT = DATA_TYPE_FEATURE_COUNT + 0;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.impl.LaneImpl <em>Lane</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.impl.LaneImpl
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getLane()
     * @generated
     */
	int LANE = 62;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int LANE__DOCUMENTATION = CONTAINER__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int LANE__NAME = CONTAINER__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int LANE__TEXT_ANNOTATION_ATTACHMENT = CONTAINER__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Elements</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int LANE__ELEMENTS = CONTAINER__ELEMENTS;

	/**
     * The feature id for the '<em><b>Actor</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int LANE__ACTOR = CONTAINER_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Filters</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int LANE__FILTERS = CONTAINER_FEATURE_COUNT + 1;

	/**
     * The number of structural features of the '<em>Lane</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int LANE_FEATURE_COUNT = CONTAINER_FEATURE_COUNT + 2;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.impl.LongTypeImpl <em>Long Type</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.impl.LongTypeImpl
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getLongType()
     * @generated
     */
	int LONG_TYPE = 64;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int LONG_TYPE__DOCUMENTATION = DATA_TYPE__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int LONG_TYPE__NAME = DATA_TYPE__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int LONG_TYPE__TEXT_ANNOTATION_ATTACHMENT = DATA_TYPE__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The number of structural features of the '<em>Long Type</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int LONG_TYPE_FEATURE_COUNT = DATA_TYPE_FEATURE_COUNT + 0;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.impl.MainProcessImpl <em>Main Process</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.impl.MainProcessImpl
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getMainProcess()
     * @generated
     */
	int MAIN_PROCESS = 65;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MAIN_PROCESS__DOCUMENTATION = ABSTRACT_PROCESS__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MAIN_PROCESS__NAME = ABSTRACT_PROCESS__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MAIN_PROCESS__TEXT_ANNOTATION_ATTACHMENT = ABSTRACT_PROCESS__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Elements</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MAIN_PROCESS__ELEMENTS = ABSTRACT_PROCESS__ELEMENTS;

	/**
     * The feature id for the '<em><b>Data</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MAIN_PROCESS__DATA = ABSTRACT_PROCESS__DATA;

	/**
     * The feature id for the '<em><b>Connectors</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MAIN_PROCESS__CONNECTORS = ABSTRACT_PROCESS__CONNECTORS;

	/**
     * The feature id for the '<em><b>Kpis</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MAIN_PROCESS__KPIS = ABSTRACT_PROCESS__KPIS;

	/**
     * The feature id for the '<em><b>Form Mapping</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MAIN_PROCESS__FORM_MAPPING = ABSTRACT_PROCESS__FORM_MAPPING;

	/**
     * The feature id for the '<em><b>Overview Form Mapping</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MAIN_PROCESS__OVERVIEW_FORM_MAPPING = ABSTRACT_PROCESS__OVERVIEW_FORM_MAPPING;

	/**
     * The feature id for the '<em><b>Version</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MAIN_PROCESS__VERSION = ABSTRACT_PROCESS__VERSION;

	/**
     * The feature id for the '<em><b>Author</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MAIN_PROCESS__AUTHOR = ABSTRACT_PROCESS__AUTHOR;

	/**
     * The feature id for the '<em><b>Creation Date</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MAIN_PROCESS__CREATION_DATE = ABSTRACT_PROCESS__CREATION_DATE;

	/**
     * The feature id for the '<em><b>Modification Date</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MAIN_PROCESS__MODIFICATION_DATE = ABSTRACT_PROCESS__MODIFICATION_DATE;

	/**
     * The feature id for the '<em><b>Datatypes</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MAIN_PROCESS__DATATYPES = ABSTRACT_PROCESS__DATATYPES;

	/**
     * The feature id for the '<em><b>Connections</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MAIN_PROCESS__CONNECTIONS = ABSTRACT_PROCESS__CONNECTIONS;

	/**
     * The feature id for the '<em><b>Categories</b></em>' attribute list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MAIN_PROCESS__CATEGORIES = ABSTRACT_PROCESS__CATEGORIES;

	/**
     * The feature id for the '<em><b>Actors</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MAIN_PROCESS__ACTORS = ABSTRACT_PROCESS__ACTORS;

	/**
     * The feature id for the '<em><b>Configurations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MAIN_PROCESS__CONFIGURATIONS = ABSTRACT_PROCESS__CONFIGURATIONS;

	/**
     * The feature id for the '<em><b>Parameters</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MAIN_PROCESS__PARAMETERS = ABSTRACT_PROCESS__PARAMETERS;

	/**
     * The feature id for the '<em><b>Bonita Version</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MAIN_PROCESS__BONITA_VERSION = ABSTRACT_PROCESS_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Bonita Model Version</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MAIN_PROCESS__BONITA_MODEL_VERSION = ABSTRACT_PROCESS_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Included Entries</b></em>' attribute list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MAIN_PROCESS__INCLUDED_ENTRIES = ABSTRACT_PROCESS_FEATURE_COUNT + 2;

	/**
     * The feature id for the '<em><b>Message Connections</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MAIN_PROCESS__MESSAGE_CONNECTIONS = ABSTRACT_PROCESS_FEATURE_COUNT + 3;

	/**
     * The feature id for the '<em><b>Generated Libs</b></em>' attribute list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MAIN_PROCESS__GENERATED_LIBS = ABSTRACT_PROCESS_FEATURE_COUNT + 4;

	/**
     * The feature id for the '<em><b>Enable Validation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MAIN_PROCESS__ENABLE_VALIDATION = ABSTRACT_PROCESS_FEATURE_COUNT + 5;

	/**
     * The feature id for the '<em><b>Config Id</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MAIN_PROCESS__CONFIG_ID = ABSTRACT_PROCESS_FEATURE_COUNT + 6;

	/**
     * The number of structural features of the '<em>Main Process</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MAIN_PROCESS_FEATURE_COUNT = ABSTRACT_PROCESS_FEATURE_COUNT + 7;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.impl.MessageImpl <em>Message</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.impl.MessageImpl
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getMessage()
     * @generated
     */
	int MESSAGE = 66;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MESSAGE__DOCUMENTATION = ELEMENT__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MESSAGE__NAME = ELEMENT__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MESSAGE__TEXT_ANNOTATION_ATTACHMENT = ELEMENT__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Throw Event</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MESSAGE__THROW_EVENT = ELEMENT_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Ttl</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MESSAGE__TTL = ELEMENT_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Correlation</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MESSAGE__CORRELATION = ELEMENT_FEATURE_COUNT + 2;

	/**
     * The feature id for the '<em><b>Source</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MESSAGE__SOURCE = ELEMENT_FEATURE_COUNT + 3;

	/**
     * The feature id for the '<em><b>Target Process Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MESSAGE__TARGET_PROCESS_EXPRESSION = ELEMENT_FEATURE_COUNT + 4;

	/**
     * The feature id for the '<em><b>Target Element Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MESSAGE__TARGET_ELEMENT_EXPRESSION = ELEMENT_FEATURE_COUNT + 5;

	/**
     * The feature id for the '<em><b>Message Content</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MESSAGE__MESSAGE_CONTENT = ELEMENT_FEATURE_COUNT + 6;

	/**
     * The number of structural features of the '<em>Message</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MESSAGE_FEATURE_COUNT = ELEMENT_FEATURE_COUNT + 7;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.impl.MessageFlowImpl <em>Message Flow</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.impl.MessageFlowImpl
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getMessageFlow()
     * @generated
     */
	int MESSAGE_FLOW = 67;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MESSAGE_FLOW__DOCUMENTATION = ELEMENT__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MESSAGE_FLOW__NAME = ELEMENT__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MESSAGE_FLOW__TEXT_ANNOTATION_ATTACHMENT = ELEMENT__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Target</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MESSAGE_FLOW__TARGET = ELEMENT_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Source</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MESSAGE_FLOW__SOURCE = ELEMENT_FEATURE_COUNT + 1;

	/**
     * The number of structural features of the '<em>Message Flow</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MESSAGE_FLOW_FEATURE_COUNT = ELEMENT_FEATURE_COUNT + 2;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.impl.MultiInstantiableImpl <em>Multi Instantiable</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.impl.MultiInstantiableImpl
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getMultiInstantiable()
     * @generated
     */
	int MULTI_INSTANTIABLE = 69;

	/**
     * The feature id for the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MULTI_INSTANTIABLE__TYPE = 0;

	/**
     * The feature id for the '<em><b>Test Before</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MULTI_INSTANTIABLE__TEST_BEFORE = 1;

	/**
     * The feature id for the '<em><b>Loop Condition</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MULTI_INSTANTIABLE__LOOP_CONDITION = 2;

	/**
     * The feature id for the '<em><b>Loop Maximum</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MULTI_INSTANTIABLE__LOOP_MAXIMUM = 3;

	/**
     * The feature id for the '<em><b>Use Cardinality</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MULTI_INSTANTIABLE__USE_CARDINALITY = 4;

	/**
     * The feature id for the '<em><b>Cardinality Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MULTI_INSTANTIABLE__CARDINALITY_EXPRESSION = 5;

	/**
     * The feature id for the '<em><b>Collection Data To Multi Instantiate</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MULTI_INSTANTIABLE__COLLECTION_DATA_TO_MULTI_INSTANTIATE = 6;

	/**
     * The feature id for the '<em><b>Iterator Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MULTI_INSTANTIABLE__ITERATOR_EXPRESSION = 7;

	/**
     * The feature id for the '<em><b>Output Data</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MULTI_INSTANTIABLE__OUTPUT_DATA = 8;

	/**
     * The feature id for the '<em><b>List Data Containing Output Results</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MULTI_INSTANTIABLE__LIST_DATA_CONTAINING_OUTPUT_RESULTS = 9;

	/**
     * The feature id for the '<em><b>Completion Condition</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MULTI_INSTANTIABLE__COMPLETION_CONDITION = 10;

	/**
     * The feature id for the '<em><b>Store Output</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MULTI_INSTANTIABLE__STORE_OUTPUT = 11;

	/**
     * The number of structural features of the '<em>Multi Instantiable</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MULTI_INSTANTIABLE_FEATURE_COUNT = 12;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.impl.NonInterruptingBoundaryTimerEventImpl <em>Non Interrupting Boundary Timer Event</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.impl.NonInterruptingBoundaryTimerEventImpl
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getNonInterruptingBoundaryTimerEvent()
     * @generated
     */
	int NON_INTERRUPTING_BOUNDARY_TIMER_EVENT = 70;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int NON_INTERRUPTING_BOUNDARY_TIMER_EVENT__DOCUMENTATION = BOUNDARY_TIMER_EVENT__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int NON_INTERRUPTING_BOUNDARY_TIMER_EVENT__NAME = BOUNDARY_TIMER_EVENT__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int NON_INTERRUPTING_BOUNDARY_TIMER_EVENT__TEXT_ANNOTATION_ATTACHMENT = BOUNDARY_TIMER_EVENT__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Outgoing</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int NON_INTERRUPTING_BOUNDARY_TIMER_EVENT__OUTGOING = BOUNDARY_TIMER_EVENT__OUTGOING;

	/**
     * The feature id for the '<em><b>Condition</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int NON_INTERRUPTING_BOUNDARY_TIMER_EVENT__CONDITION = BOUNDARY_TIMER_EVENT__CONDITION;

	/**
     * The number of structural features of the '<em>Non Interrupting Boundary Timer Event</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int NON_INTERRUPTING_BOUNDARY_TIMER_EVENT_FEATURE_COUNT = BOUNDARY_TIMER_EVENT_FEATURE_COUNT + 0;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.impl.OperationContainerImpl <em>Operation Container</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.impl.OperationContainerImpl
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getOperationContainer()
     * @generated
     */
	int OPERATION_CONTAINER = 71;

	/**
     * The feature id for the '<em><b>Operations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int OPERATION_CONTAINER__OPERATIONS = 0;

	/**
     * The number of structural features of the '<em>Operation Container</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int OPERATION_CONTAINER_FEATURE_COUNT = 1;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.impl.OutputMappingImpl <em>Output Mapping</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.impl.OutputMappingImpl
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getOutputMapping()
     * @generated
     */
	int OUTPUT_MAPPING = 72;

	/**
     * The feature id for the '<em><b>Subprocess Source</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int OUTPUT_MAPPING__SUBPROCESS_SOURCE = 0;

	/**
     * The feature id for the '<em><b>Process Target</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int OUTPUT_MAPPING__PROCESS_TARGET = 1;

	/**
     * The number of structural features of the '<em>Output Mapping</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int OUTPUT_MAPPING_FEATURE_COUNT = 2;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.impl.PageFlowImpl <em>Page Flow</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.impl.PageFlowImpl
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getPageFlow()
     * @generated
     */
	int PAGE_FLOW = 73;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PAGE_FLOW__DOCUMENTATION = CONNECTABLE_ELEMENT__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PAGE_FLOW__NAME = CONNECTABLE_ELEMENT__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PAGE_FLOW__TEXT_ANNOTATION_ATTACHMENT = CONNECTABLE_ELEMENT__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Data</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PAGE_FLOW__DATA = CONNECTABLE_ELEMENT__DATA;

	/**
     * The feature id for the '<em><b>Connectors</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PAGE_FLOW__CONNECTORS = CONNECTABLE_ELEMENT__CONNECTORS;

	/**
     * The feature id for the '<em><b>Kpis</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PAGE_FLOW__KPIS = CONNECTABLE_ELEMENT__KPIS;

	/**
     * The feature id for the '<em><b>Form Mapping</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PAGE_FLOW__FORM_MAPPING = CONNECTABLE_ELEMENT_FEATURE_COUNT + 0;

	/**
     * The number of structural features of the '<em>Page Flow</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PAGE_FLOW_FEATURE_COUNT = CONNECTABLE_ELEMENT_FEATURE_COUNT + 1;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.impl.PoolImpl <em>Pool</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.impl.PoolImpl
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getPool()
     * @generated
     */
	int POOL = 74;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int POOL__DOCUMENTATION = ABSTRACT_PROCESS__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int POOL__NAME = ABSTRACT_PROCESS__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int POOL__TEXT_ANNOTATION_ATTACHMENT = ABSTRACT_PROCESS__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Elements</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int POOL__ELEMENTS = ABSTRACT_PROCESS__ELEMENTS;

	/**
     * The feature id for the '<em><b>Data</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int POOL__DATA = ABSTRACT_PROCESS__DATA;

	/**
     * The feature id for the '<em><b>Connectors</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int POOL__CONNECTORS = ABSTRACT_PROCESS__CONNECTORS;

	/**
     * The feature id for the '<em><b>Kpis</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int POOL__KPIS = ABSTRACT_PROCESS__KPIS;

	/**
     * The feature id for the '<em><b>Form Mapping</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int POOL__FORM_MAPPING = ABSTRACT_PROCESS__FORM_MAPPING;

	/**
     * The feature id for the '<em><b>Overview Form Mapping</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int POOL__OVERVIEW_FORM_MAPPING = ABSTRACT_PROCESS__OVERVIEW_FORM_MAPPING;

	/**
     * The feature id for the '<em><b>Version</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int POOL__VERSION = ABSTRACT_PROCESS__VERSION;

	/**
     * The feature id for the '<em><b>Author</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int POOL__AUTHOR = ABSTRACT_PROCESS__AUTHOR;

	/**
     * The feature id for the '<em><b>Creation Date</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int POOL__CREATION_DATE = ABSTRACT_PROCESS__CREATION_DATE;

	/**
     * The feature id for the '<em><b>Modification Date</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int POOL__MODIFICATION_DATE = ABSTRACT_PROCESS__MODIFICATION_DATE;

	/**
     * The feature id for the '<em><b>Datatypes</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int POOL__DATATYPES = ABSTRACT_PROCESS__DATATYPES;

	/**
     * The feature id for the '<em><b>Connections</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int POOL__CONNECTIONS = ABSTRACT_PROCESS__CONNECTIONS;

	/**
     * The feature id for the '<em><b>Categories</b></em>' attribute list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int POOL__CATEGORIES = ABSTRACT_PROCESS__CATEGORIES;

	/**
     * The feature id for the '<em><b>Actors</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int POOL__ACTORS = ABSTRACT_PROCESS__ACTORS;

	/**
     * The feature id for the '<em><b>Configurations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int POOL__CONFIGURATIONS = ABSTRACT_PROCESS__CONFIGURATIONS;

	/**
     * The feature id for the '<em><b>Parameters</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int POOL__PARAMETERS = ABSTRACT_PROCESS__PARAMETERS;

	/**
     * The feature id for the '<em><b>Contract</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int POOL__CONTRACT = ABSTRACT_PROCESS_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Documents</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int POOL__DOCUMENTS = ABSTRACT_PROCESS_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Search Indexes</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int POOL__SEARCH_INDEXES = ABSTRACT_PROCESS_FEATURE_COUNT + 2;

	/**
     * The feature id for the '<em><b>Display Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int POOL__DISPLAY_NAME = ABSTRACT_PROCESS_FEATURE_COUNT + 3;

	/**
     * The feature id for the '<em><b>Additional Resources</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int POOL__ADDITIONAL_RESOURCES = ABSTRACT_PROCESS_FEATURE_COUNT + 4;

    /**
     * The number of structural features of the '<em>Pool</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int POOL_FEATURE_COUNT = ABSTRACT_PROCESS_FEATURE_COUNT + 5;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.RecapFlow <em>Recap Flow</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.RecapFlow
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getRecapFlow()
     * @generated
     */
	int RECAP_FLOW = 75;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RECAP_FLOW__DOCUMENTATION = ABSTRACT_PAGE_FLOW__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RECAP_FLOW__NAME = ABSTRACT_PAGE_FLOW__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RECAP_FLOW__TEXT_ANNOTATION_ATTACHMENT = ABSTRACT_PAGE_FLOW__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Overview Form Mapping</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RECAP_FLOW__OVERVIEW_FORM_MAPPING = ABSTRACT_PAGE_FLOW_FEATURE_COUNT + 0;

	/**
     * The number of structural features of the '<em>Recap Flow</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RECAP_FLOW_FEATURE_COUNT = ABSTRACT_PAGE_FLOW_FEATURE_COUNT + 1;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.impl.ReceiveTaskImpl <em>Receive Task</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.impl.ReceiveTaskImpl
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getReceiveTask()
     * @generated
     */
	int RECEIVE_TASK = 76;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RECEIVE_TASK__DOCUMENTATION = ACTIVITY__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RECEIVE_TASK__NAME = ACTIVITY__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RECEIVE_TASK__TEXT_ANNOTATION_ATTACHMENT = ACTIVITY__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Outgoing</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RECEIVE_TASK__OUTGOING = ACTIVITY__OUTGOING;

	/**
     * The feature id for the '<em><b>Incoming</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RECEIVE_TASK__INCOMING = ACTIVITY__INCOMING;

	/**
     * The feature id for the '<em><b>Dynamic Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RECEIVE_TASK__DYNAMIC_LABEL = ACTIVITY__DYNAMIC_LABEL;

	/**
     * The feature id for the '<em><b>Dynamic Description</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RECEIVE_TASK__DYNAMIC_DESCRIPTION = ACTIVITY__DYNAMIC_DESCRIPTION;

	/**
     * The feature id for the '<em><b>Step Summary</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RECEIVE_TASK__STEP_SUMMARY = ACTIVITY__STEP_SUMMARY;

	/**
     * The feature id for the '<em><b>Data</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RECEIVE_TASK__DATA = ACTIVITY__DATA;

	/**
     * The feature id for the '<em><b>Connectors</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RECEIVE_TASK__CONNECTORS = ACTIVITY__CONNECTORS;

	/**
     * The feature id for the '<em><b>Kpis</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RECEIVE_TASK__KPIS = ACTIVITY__KPIS;

	/**
     * The feature id for the '<em><b>Operations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RECEIVE_TASK__OPERATIONS = ACTIVITY__OPERATIONS;

	/**
     * The feature id for the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RECEIVE_TASK__TYPE = ACTIVITY__TYPE;

	/**
     * The feature id for the '<em><b>Test Before</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RECEIVE_TASK__TEST_BEFORE = ACTIVITY__TEST_BEFORE;

	/**
     * The feature id for the '<em><b>Loop Condition</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RECEIVE_TASK__LOOP_CONDITION = ACTIVITY__LOOP_CONDITION;

	/**
     * The feature id for the '<em><b>Loop Maximum</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RECEIVE_TASK__LOOP_MAXIMUM = ACTIVITY__LOOP_MAXIMUM;

	/**
     * The feature id for the '<em><b>Use Cardinality</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RECEIVE_TASK__USE_CARDINALITY = ACTIVITY__USE_CARDINALITY;

	/**
     * The feature id for the '<em><b>Cardinality Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RECEIVE_TASK__CARDINALITY_EXPRESSION = ACTIVITY__CARDINALITY_EXPRESSION;

	/**
     * The feature id for the '<em><b>Collection Data To Multi Instantiate</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RECEIVE_TASK__COLLECTION_DATA_TO_MULTI_INSTANTIATE = ACTIVITY__COLLECTION_DATA_TO_MULTI_INSTANTIATE;

	/**
     * The feature id for the '<em><b>Iterator Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RECEIVE_TASK__ITERATOR_EXPRESSION = ACTIVITY__ITERATOR_EXPRESSION;

	/**
     * The feature id for the '<em><b>Output Data</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RECEIVE_TASK__OUTPUT_DATA = ACTIVITY__OUTPUT_DATA;

	/**
     * The feature id for the '<em><b>List Data Containing Output Results</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RECEIVE_TASK__LIST_DATA_CONTAINING_OUTPUT_RESULTS = ACTIVITY__LIST_DATA_CONTAINING_OUTPUT_RESULTS;

	/**
     * The feature id for the '<em><b>Completion Condition</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RECEIVE_TASK__COMPLETION_CONDITION = ACTIVITY__COMPLETION_CONDITION;

	/**
     * The feature id for the '<em><b>Store Output</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RECEIVE_TASK__STORE_OUTPUT = ACTIVITY__STORE_OUTPUT;

	/**
     * The feature id for the '<em><b>Boundary Intermediate Events</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RECEIVE_TASK__BOUNDARY_INTERMEDIATE_EVENTS = ACTIVITY__BOUNDARY_INTERMEDIATE_EVENTS;

	/**
     * The feature id for the '<em><b>Event</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RECEIVE_TASK__EVENT = ACTIVITY_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Incoming Messag</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RECEIVE_TASK__INCOMING_MESSAG = ACTIVITY_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Correlation</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RECEIVE_TASK__CORRELATION = ACTIVITY_FEATURE_COUNT + 2;

	/**
     * The feature id for the '<em><b>Message Content</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RECEIVE_TASK__MESSAGE_CONTENT = ACTIVITY_FEATURE_COUNT + 3;

	/**
     * The number of structural features of the '<em>Receive Task</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RECEIVE_TASK_FEATURE_COUNT = ACTIVITY_FEATURE_COUNT + 4;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.impl.SequenceFlowImpl <em>Sequence Flow</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.impl.SequenceFlowImpl
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getSequenceFlow()
     * @generated
     */
	int SEQUENCE_FLOW = 77;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SEQUENCE_FLOW__DOCUMENTATION = CONNECTION__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SEQUENCE_FLOW__NAME = CONNECTION__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SEQUENCE_FLOW__TEXT_ANNOTATION_ATTACHMENT = CONNECTION__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Target</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SEQUENCE_FLOW__TARGET = CONNECTION__TARGET;

	/**
     * The feature id for the '<em><b>Source</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SEQUENCE_FLOW__SOURCE = CONNECTION__SOURCE;

	/**
     * The feature id for the '<em><b>Is Default</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SEQUENCE_FLOW__IS_DEFAULT = CONNECTION_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Condition Type</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SEQUENCE_FLOW__CONDITION_TYPE = CONNECTION_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Decision Table</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SEQUENCE_FLOW__DECISION_TABLE = CONNECTION_FEATURE_COUNT + 2;

	/**
     * The feature id for the '<em><b>Condition</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SEQUENCE_FLOW__CONDITION = CONNECTION_FEATURE_COUNT + 3;

	/**
     * The feature id for the '<em><b>Path Token</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SEQUENCE_FLOW__PATH_TOKEN = CONNECTION_FEATURE_COUNT + 4;

	/**
     * The number of structural features of the '<em>Sequence Flow</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SEQUENCE_FLOW_FEATURE_COUNT = CONNECTION_FEATURE_COUNT + 5;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.SourceElement <em>Source Element</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.SourceElement
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getSourceElement()
     * @generated
     */
	int SOURCE_ELEMENT = 79;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SOURCE_ELEMENT__DOCUMENTATION = ELEMENT__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SOURCE_ELEMENT__NAME = ELEMENT__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SOURCE_ELEMENT__TEXT_ANNOTATION_ATTACHMENT = ELEMENT__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Outgoing</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SOURCE_ELEMENT__OUTGOING = ELEMENT_FEATURE_COUNT + 0;

	/**
     * The number of structural features of the '<em>Source Element</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SOURCE_ELEMENT_FEATURE_COUNT = ELEMENT_FEATURE_COUNT + 1;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.impl.ScriptTaskImpl <em>Script Task</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.impl.ScriptTaskImpl
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getScriptTask()
     * @generated
     */
	int SCRIPT_TASK = 81;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SCRIPT_TASK__DOCUMENTATION = ACTIVITY__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SCRIPT_TASK__NAME = ACTIVITY__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SCRIPT_TASK__TEXT_ANNOTATION_ATTACHMENT = ACTIVITY__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Outgoing</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SCRIPT_TASK__OUTGOING = ACTIVITY__OUTGOING;

	/**
     * The feature id for the '<em><b>Incoming</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SCRIPT_TASK__INCOMING = ACTIVITY__INCOMING;

	/**
     * The feature id for the '<em><b>Dynamic Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SCRIPT_TASK__DYNAMIC_LABEL = ACTIVITY__DYNAMIC_LABEL;

	/**
     * The feature id for the '<em><b>Dynamic Description</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SCRIPT_TASK__DYNAMIC_DESCRIPTION = ACTIVITY__DYNAMIC_DESCRIPTION;

	/**
     * The feature id for the '<em><b>Step Summary</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SCRIPT_TASK__STEP_SUMMARY = ACTIVITY__STEP_SUMMARY;

	/**
     * The feature id for the '<em><b>Data</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SCRIPT_TASK__DATA = ACTIVITY__DATA;

	/**
     * The feature id for the '<em><b>Connectors</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SCRIPT_TASK__CONNECTORS = ACTIVITY__CONNECTORS;

	/**
     * The feature id for the '<em><b>Kpis</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SCRIPT_TASK__KPIS = ACTIVITY__KPIS;

	/**
     * The feature id for the '<em><b>Operations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SCRIPT_TASK__OPERATIONS = ACTIVITY__OPERATIONS;

	/**
     * The feature id for the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SCRIPT_TASK__TYPE = ACTIVITY__TYPE;

	/**
     * The feature id for the '<em><b>Test Before</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SCRIPT_TASK__TEST_BEFORE = ACTIVITY__TEST_BEFORE;

	/**
     * The feature id for the '<em><b>Loop Condition</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SCRIPT_TASK__LOOP_CONDITION = ACTIVITY__LOOP_CONDITION;

	/**
     * The feature id for the '<em><b>Loop Maximum</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SCRIPT_TASK__LOOP_MAXIMUM = ACTIVITY__LOOP_MAXIMUM;

	/**
     * The feature id for the '<em><b>Use Cardinality</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SCRIPT_TASK__USE_CARDINALITY = ACTIVITY__USE_CARDINALITY;

	/**
     * The feature id for the '<em><b>Cardinality Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SCRIPT_TASK__CARDINALITY_EXPRESSION = ACTIVITY__CARDINALITY_EXPRESSION;

	/**
     * The feature id for the '<em><b>Collection Data To Multi Instantiate</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SCRIPT_TASK__COLLECTION_DATA_TO_MULTI_INSTANTIATE = ACTIVITY__COLLECTION_DATA_TO_MULTI_INSTANTIATE;

	/**
     * The feature id for the '<em><b>Iterator Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SCRIPT_TASK__ITERATOR_EXPRESSION = ACTIVITY__ITERATOR_EXPRESSION;

	/**
     * The feature id for the '<em><b>Output Data</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SCRIPT_TASK__OUTPUT_DATA = ACTIVITY__OUTPUT_DATA;

	/**
     * The feature id for the '<em><b>List Data Containing Output Results</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SCRIPT_TASK__LIST_DATA_CONTAINING_OUTPUT_RESULTS = ACTIVITY__LIST_DATA_CONTAINING_OUTPUT_RESULTS;

	/**
     * The feature id for the '<em><b>Completion Condition</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SCRIPT_TASK__COMPLETION_CONDITION = ACTIVITY__COMPLETION_CONDITION;

	/**
     * The feature id for the '<em><b>Store Output</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SCRIPT_TASK__STORE_OUTPUT = ACTIVITY__STORE_OUTPUT;

	/**
     * The feature id for the '<em><b>Boundary Intermediate Events</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SCRIPT_TASK__BOUNDARY_INTERMEDIATE_EVENTS = ACTIVITY__BOUNDARY_INTERMEDIATE_EVENTS;

	/**
     * The number of structural features of the '<em>Script Task</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SCRIPT_TASK_FEATURE_COUNT = ACTIVITY_FEATURE_COUNT + 0;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.impl.SearchIndexImpl <em>Search Index</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.impl.SearchIndexImpl
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getSearchIndex()
     * @generated
     */
	int SEARCH_INDEX = 82;

	/**
     * The feature id for the '<em><b>Name</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SEARCH_INDEX__NAME = 0;

	/**
     * The feature id for the '<em><b>Value</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SEARCH_INDEX__VALUE = 1;

	/**
     * The number of structural features of the '<em>Search Index</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SEARCH_INDEX_FEATURE_COUNT = 2;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.impl.SendTaskImpl <em>Send Task</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.impl.SendTaskImpl
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getSendTask()
     * @generated
     */
	int SEND_TASK = 83;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SEND_TASK__DOCUMENTATION = ACTIVITY__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SEND_TASK__NAME = ACTIVITY__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SEND_TASK__TEXT_ANNOTATION_ATTACHMENT = ACTIVITY__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Outgoing</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SEND_TASK__OUTGOING = ACTIVITY__OUTGOING;

	/**
     * The feature id for the '<em><b>Incoming</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SEND_TASK__INCOMING = ACTIVITY__INCOMING;

	/**
     * The feature id for the '<em><b>Dynamic Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SEND_TASK__DYNAMIC_LABEL = ACTIVITY__DYNAMIC_LABEL;

	/**
     * The feature id for the '<em><b>Dynamic Description</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SEND_TASK__DYNAMIC_DESCRIPTION = ACTIVITY__DYNAMIC_DESCRIPTION;

	/**
     * The feature id for the '<em><b>Step Summary</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SEND_TASK__STEP_SUMMARY = ACTIVITY__STEP_SUMMARY;

	/**
     * The feature id for the '<em><b>Data</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SEND_TASK__DATA = ACTIVITY__DATA;

	/**
     * The feature id for the '<em><b>Connectors</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SEND_TASK__CONNECTORS = ACTIVITY__CONNECTORS;

	/**
     * The feature id for the '<em><b>Kpis</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SEND_TASK__KPIS = ACTIVITY__KPIS;

	/**
     * The feature id for the '<em><b>Operations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SEND_TASK__OPERATIONS = ACTIVITY__OPERATIONS;

	/**
     * The feature id for the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SEND_TASK__TYPE = ACTIVITY__TYPE;

	/**
     * The feature id for the '<em><b>Test Before</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SEND_TASK__TEST_BEFORE = ACTIVITY__TEST_BEFORE;

	/**
     * The feature id for the '<em><b>Loop Condition</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SEND_TASK__LOOP_CONDITION = ACTIVITY__LOOP_CONDITION;

	/**
     * The feature id for the '<em><b>Loop Maximum</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SEND_TASK__LOOP_MAXIMUM = ACTIVITY__LOOP_MAXIMUM;

	/**
     * The feature id for the '<em><b>Use Cardinality</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SEND_TASK__USE_CARDINALITY = ACTIVITY__USE_CARDINALITY;

	/**
     * The feature id for the '<em><b>Cardinality Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SEND_TASK__CARDINALITY_EXPRESSION = ACTIVITY__CARDINALITY_EXPRESSION;

	/**
     * The feature id for the '<em><b>Collection Data To Multi Instantiate</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SEND_TASK__COLLECTION_DATA_TO_MULTI_INSTANTIATE = ACTIVITY__COLLECTION_DATA_TO_MULTI_INSTANTIATE;

	/**
     * The feature id for the '<em><b>Iterator Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SEND_TASK__ITERATOR_EXPRESSION = ACTIVITY__ITERATOR_EXPRESSION;

	/**
     * The feature id for the '<em><b>Output Data</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SEND_TASK__OUTPUT_DATA = ACTIVITY__OUTPUT_DATA;

	/**
     * The feature id for the '<em><b>List Data Containing Output Results</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SEND_TASK__LIST_DATA_CONTAINING_OUTPUT_RESULTS = ACTIVITY__LIST_DATA_CONTAINING_OUTPUT_RESULTS;

	/**
     * The feature id for the '<em><b>Completion Condition</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SEND_TASK__COMPLETION_CONDITION = ACTIVITY__COMPLETION_CONDITION;

	/**
     * The feature id for the '<em><b>Store Output</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SEND_TASK__STORE_OUTPUT = ACTIVITY__STORE_OUTPUT;

	/**
     * The feature id for the '<em><b>Boundary Intermediate Events</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SEND_TASK__BOUNDARY_INTERMEDIATE_EVENTS = ACTIVITY__BOUNDARY_INTERMEDIATE_EVENTS;

	/**
     * The feature id for the '<em><b>Events</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SEND_TASK__EVENTS = ACTIVITY_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Outgoing Messages</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SEND_TASK__OUTGOING_MESSAGES = ACTIVITY_FEATURE_COUNT + 1;

	/**
     * The number of structural features of the '<em>Send Task</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SEND_TASK_FEATURE_COUNT = ACTIVITY_FEATURE_COUNT + 2;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.impl.ServiceTaskImpl <em>Service Task</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.impl.ServiceTaskImpl
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getServiceTask()
     * @generated
     */
	int SERVICE_TASK = 84;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SERVICE_TASK__DOCUMENTATION = ACTIVITY__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SERVICE_TASK__NAME = ACTIVITY__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SERVICE_TASK__TEXT_ANNOTATION_ATTACHMENT = ACTIVITY__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Outgoing</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SERVICE_TASK__OUTGOING = ACTIVITY__OUTGOING;

	/**
     * The feature id for the '<em><b>Incoming</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SERVICE_TASK__INCOMING = ACTIVITY__INCOMING;

	/**
     * The feature id for the '<em><b>Dynamic Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SERVICE_TASK__DYNAMIC_LABEL = ACTIVITY__DYNAMIC_LABEL;

	/**
     * The feature id for the '<em><b>Dynamic Description</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SERVICE_TASK__DYNAMIC_DESCRIPTION = ACTIVITY__DYNAMIC_DESCRIPTION;

	/**
     * The feature id for the '<em><b>Step Summary</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SERVICE_TASK__STEP_SUMMARY = ACTIVITY__STEP_SUMMARY;

	/**
     * The feature id for the '<em><b>Data</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SERVICE_TASK__DATA = ACTIVITY__DATA;

	/**
     * The feature id for the '<em><b>Connectors</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SERVICE_TASK__CONNECTORS = ACTIVITY__CONNECTORS;

	/**
     * The feature id for the '<em><b>Kpis</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SERVICE_TASK__KPIS = ACTIVITY__KPIS;

	/**
     * The feature id for the '<em><b>Operations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SERVICE_TASK__OPERATIONS = ACTIVITY__OPERATIONS;

	/**
     * The feature id for the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SERVICE_TASK__TYPE = ACTIVITY__TYPE;

	/**
     * The feature id for the '<em><b>Test Before</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SERVICE_TASK__TEST_BEFORE = ACTIVITY__TEST_BEFORE;

	/**
     * The feature id for the '<em><b>Loop Condition</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SERVICE_TASK__LOOP_CONDITION = ACTIVITY__LOOP_CONDITION;

	/**
     * The feature id for the '<em><b>Loop Maximum</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SERVICE_TASK__LOOP_MAXIMUM = ACTIVITY__LOOP_MAXIMUM;

	/**
     * The feature id for the '<em><b>Use Cardinality</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SERVICE_TASK__USE_CARDINALITY = ACTIVITY__USE_CARDINALITY;

	/**
     * The feature id for the '<em><b>Cardinality Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SERVICE_TASK__CARDINALITY_EXPRESSION = ACTIVITY__CARDINALITY_EXPRESSION;

	/**
     * The feature id for the '<em><b>Collection Data To Multi Instantiate</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SERVICE_TASK__COLLECTION_DATA_TO_MULTI_INSTANTIATE = ACTIVITY__COLLECTION_DATA_TO_MULTI_INSTANTIATE;

	/**
     * The feature id for the '<em><b>Iterator Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SERVICE_TASK__ITERATOR_EXPRESSION = ACTIVITY__ITERATOR_EXPRESSION;

	/**
     * The feature id for the '<em><b>Output Data</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SERVICE_TASK__OUTPUT_DATA = ACTIVITY__OUTPUT_DATA;

	/**
     * The feature id for the '<em><b>List Data Containing Output Results</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SERVICE_TASK__LIST_DATA_CONTAINING_OUTPUT_RESULTS = ACTIVITY__LIST_DATA_CONTAINING_OUTPUT_RESULTS;

	/**
     * The feature id for the '<em><b>Completion Condition</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SERVICE_TASK__COMPLETION_CONDITION = ACTIVITY__COMPLETION_CONDITION;

	/**
     * The feature id for the '<em><b>Store Output</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SERVICE_TASK__STORE_OUTPUT = ACTIVITY__STORE_OUTPUT;

	/**
     * The feature id for the '<em><b>Boundary Intermediate Events</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SERVICE_TASK__BOUNDARY_INTERMEDIATE_EVENTS = ACTIVITY__BOUNDARY_INTERMEDIATE_EVENTS;

	/**
     * The number of structural features of the '<em>Service Task</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SERVICE_TASK_FEATURE_COUNT = ACTIVITY_FEATURE_COUNT + 0;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.impl.StartErrorEventImpl <em>Start Error Event</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.impl.StartErrorEventImpl
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getStartErrorEvent()
     * @generated
     */
	int START_ERROR_EVENT = 85;

	/**
     * The feature id for the '<em><b>Error Code</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int START_ERROR_EVENT__ERROR_CODE = ERROR_EVENT__ERROR_CODE;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int START_ERROR_EVENT__DOCUMENTATION = ERROR_EVENT_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int START_ERROR_EVENT__NAME = ERROR_EVENT_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int START_ERROR_EVENT__TEXT_ANNOTATION_ATTACHMENT = ERROR_EVENT_FEATURE_COUNT + 2;

	/**
     * The feature id for the '<em><b>Outgoing</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int START_ERROR_EVENT__OUTGOING = ERROR_EVENT_FEATURE_COUNT + 3;

	/**
     * The feature id for the '<em><b>Incoming</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int START_ERROR_EVENT__INCOMING = ERROR_EVENT_FEATURE_COUNT + 4;

	/**
     * The feature id for the '<em><b>Dynamic Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int START_ERROR_EVENT__DYNAMIC_LABEL = ERROR_EVENT_FEATURE_COUNT + 5;

	/**
     * The feature id for the '<em><b>Dynamic Description</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int START_ERROR_EVENT__DYNAMIC_DESCRIPTION = ERROR_EVENT_FEATURE_COUNT + 6;

	/**
     * The feature id for the '<em><b>Step Summary</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int START_ERROR_EVENT__STEP_SUMMARY = ERROR_EVENT_FEATURE_COUNT + 7;

	/**
     * The number of structural features of the '<em>Start Error Event</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int START_ERROR_EVENT_FEATURE_COUNT = ERROR_EVENT_FEATURE_COUNT + 8;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.impl.StartEventImpl <em>Start Event</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.impl.StartEventImpl
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getStartEvent()
     * @generated
     */
	int START_EVENT = 86;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int START_EVENT__DOCUMENTATION = EVENT__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int START_EVENT__NAME = EVENT__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int START_EVENT__TEXT_ANNOTATION_ATTACHMENT = EVENT__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Outgoing</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int START_EVENT__OUTGOING = EVENT__OUTGOING;

	/**
     * The feature id for the '<em><b>Incoming</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int START_EVENT__INCOMING = EVENT__INCOMING;

	/**
     * The feature id for the '<em><b>Dynamic Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int START_EVENT__DYNAMIC_LABEL = EVENT__DYNAMIC_LABEL;

	/**
     * The feature id for the '<em><b>Dynamic Description</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int START_EVENT__DYNAMIC_DESCRIPTION = EVENT__DYNAMIC_DESCRIPTION;

	/**
     * The feature id for the '<em><b>Step Summary</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int START_EVENT__STEP_SUMMARY = EVENT__STEP_SUMMARY;

	/**
     * The number of structural features of the '<em>Start Event</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int START_EVENT_FEATURE_COUNT = EVENT_FEATURE_COUNT + 0;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.impl.StartMessageEventImpl <em>Start Message Event</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.impl.StartMessageEventImpl
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getStartMessageEvent()
     * @generated
     */
	int START_MESSAGE_EVENT = 87;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int START_MESSAGE_EVENT__DOCUMENTATION = CATCH_MESSAGE_EVENT__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int START_MESSAGE_EVENT__NAME = CATCH_MESSAGE_EVENT__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int START_MESSAGE_EVENT__TEXT_ANNOTATION_ATTACHMENT = CATCH_MESSAGE_EVENT__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Outgoing</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int START_MESSAGE_EVENT__OUTGOING = CATCH_MESSAGE_EVENT__OUTGOING;

	/**
     * The feature id for the '<em><b>Incoming</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int START_MESSAGE_EVENT__INCOMING = CATCH_MESSAGE_EVENT__INCOMING;

	/**
     * The feature id for the '<em><b>Dynamic Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int START_MESSAGE_EVENT__DYNAMIC_LABEL = CATCH_MESSAGE_EVENT__DYNAMIC_LABEL;

	/**
     * The feature id for the '<em><b>Dynamic Description</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int START_MESSAGE_EVENT__DYNAMIC_DESCRIPTION = CATCH_MESSAGE_EVENT__DYNAMIC_DESCRIPTION;

	/**
     * The feature id for the '<em><b>Step Summary</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int START_MESSAGE_EVENT__STEP_SUMMARY = CATCH_MESSAGE_EVENT__STEP_SUMMARY;

	/**
     * The feature id for the '<em><b>Event</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int START_MESSAGE_EVENT__EVENT = CATCH_MESSAGE_EVENT__EVENT;

	/**
     * The feature id for the '<em><b>Incoming Messag</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int START_MESSAGE_EVENT__INCOMING_MESSAG = CATCH_MESSAGE_EVENT__INCOMING_MESSAG;

	/**
     * The feature id for the '<em><b>Correlation</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int START_MESSAGE_EVENT__CORRELATION = CATCH_MESSAGE_EVENT__CORRELATION;

	/**
     * The feature id for the '<em><b>Message Content</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int START_MESSAGE_EVENT__MESSAGE_CONTENT = CATCH_MESSAGE_EVENT__MESSAGE_CONTENT;

	/**
     * The feature id for the '<em><b>Data</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int START_MESSAGE_EVENT__DATA = CATCH_MESSAGE_EVENT__DATA;

	/**
     * The number of structural features of the '<em>Start Message Event</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int START_MESSAGE_EVENT_FEATURE_COUNT = CATCH_MESSAGE_EVENT_FEATURE_COUNT + 0;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.impl.StartSignalEventImpl <em>Start Signal Event</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.impl.StartSignalEventImpl
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getStartSignalEvent()
     * @generated
     */
	int START_SIGNAL_EVENT = 88;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int START_SIGNAL_EVENT__DOCUMENTATION = CATCH_SIGNAL_EVENT__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int START_SIGNAL_EVENT__NAME = CATCH_SIGNAL_EVENT__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int START_SIGNAL_EVENT__TEXT_ANNOTATION_ATTACHMENT = CATCH_SIGNAL_EVENT__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Signal Code</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int START_SIGNAL_EVENT__SIGNAL_CODE = CATCH_SIGNAL_EVENT__SIGNAL_CODE;

	/**
     * The feature id for the '<em><b>Outgoing</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int START_SIGNAL_EVENT__OUTGOING = CATCH_SIGNAL_EVENT_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Incoming</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int START_SIGNAL_EVENT__INCOMING = CATCH_SIGNAL_EVENT_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Dynamic Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int START_SIGNAL_EVENT__DYNAMIC_LABEL = CATCH_SIGNAL_EVENT_FEATURE_COUNT + 2;

	/**
     * The feature id for the '<em><b>Dynamic Description</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int START_SIGNAL_EVENT__DYNAMIC_DESCRIPTION = CATCH_SIGNAL_EVENT_FEATURE_COUNT + 3;

	/**
     * The feature id for the '<em><b>Step Summary</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int START_SIGNAL_EVENT__STEP_SUMMARY = CATCH_SIGNAL_EVENT_FEATURE_COUNT + 4;

	/**
     * The feature id for the '<em><b>Data</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int START_SIGNAL_EVENT__DATA = CATCH_SIGNAL_EVENT_FEATURE_COUNT + 5;

	/**
     * The number of structural features of the '<em>Start Signal Event</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int START_SIGNAL_EVENT_FEATURE_COUNT = CATCH_SIGNAL_EVENT_FEATURE_COUNT + 6;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.impl.StartTimerEventImpl <em>Start Timer Event</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.impl.StartTimerEventImpl
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getStartTimerEvent()
     * @generated
     */
	int START_TIMER_EVENT = 89;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int START_TIMER_EVENT__DOCUMENTATION = TIMER_EVENT__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int START_TIMER_EVENT__NAME = TIMER_EVENT__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int START_TIMER_EVENT__TEXT_ANNOTATION_ATTACHMENT = TIMER_EVENT__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Outgoing</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int START_TIMER_EVENT__OUTGOING = TIMER_EVENT__OUTGOING;

	/**
     * The feature id for the '<em><b>Incoming</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int START_TIMER_EVENT__INCOMING = TIMER_EVENT__INCOMING;

	/**
     * The feature id for the '<em><b>Dynamic Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int START_TIMER_EVENT__DYNAMIC_LABEL = TIMER_EVENT__DYNAMIC_LABEL;

	/**
     * The feature id for the '<em><b>Dynamic Description</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int START_TIMER_EVENT__DYNAMIC_DESCRIPTION = TIMER_EVENT__DYNAMIC_DESCRIPTION;

	/**
     * The feature id for the '<em><b>Step Summary</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int START_TIMER_EVENT__STEP_SUMMARY = TIMER_EVENT__STEP_SUMMARY;

	/**
     * The feature id for the '<em><b>Condition</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int START_TIMER_EVENT__CONDITION = TIMER_EVENT__CONDITION;

	/**
     * The feature id for the '<em><b>Data</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int START_TIMER_EVENT__DATA = TIMER_EVENT__DATA;

	/**
     * The feature id for the '<em><b>From</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int START_TIMER_EVENT__FROM = TIMER_EVENT_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>At</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int START_TIMER_EVENT__AT = TIMER_EVENT_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Month</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int START_TIMER_EVENT__MONTH = TIMER_EVENT_FEATURE_COUNT + 2;

	/**
     * The feature id for the '<em><b>Day</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int START_TIMER_EVENT__DAY = TIMER_EVENT_FEATURE_COUNT + 3;

	/**
     * The feature id for the '<em><b>Hours</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int START_TIMER_EVENT__HOURS = TIMER_EVENT_FEATURE_COUNT + 4;

	/**
     * The feature id for the '<em><b>Day Number</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int START_TIMER_EVENT__DAY_NUMBER = TIMER_EVENT_FEATURE_COUNT + 5;

	/**
     * The feature id for the '<em><b>Minutes</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int START_TIMER_EVENT__MINUTES = TIMER_EVENT_FEATURE_COUNT + 6;

	/**
     * The feature id for the '<em><b>Seconds</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int START_TIMER_EVENT__SECONDS = TIMER_EVENT_FEATURE_COUNT + 7;

	/**
     * The feature id for the '<em><b>Script Type</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int START_TIMER_EVENT__SCRIPT_TYPE = TIMER_EVENT_FEATURE_COUNT + 8;

	/**
     * The number of structural features of the '<em>Start Timer Event</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int START_TIMER_EVENT_FEATURE_COUNT = TIMER_EVENT_FEATURE_COUNT + 9;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.impl.SubProcessEventImpl <em>Sub Process Event</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.impl.SubProcessEventImpl
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getSubProcessEvent()
     * @generated
     */
	int SUB_PROCESS_EVENT = 90;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUB_PROCESS_EVENT__DOCUMENTATION = CONTAINER__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUB_PROCESS_EVENT__NAME = CONTAINER__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUB_PROCESS_EVENT__TEXT_ANNOTATION_ATTACHMENT = CONTAINER__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Elements</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUB_PROCESS_EVENT__ELEMENTS = CONTAINER__ELEMENTS;

	/**
     * The number of structural features of the '<em>Sub Process Event</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUB_PROCESS_EVENT_FEATURE_COUNT = CONTAINER_FEATURE_COUNT + 0;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.impl.TaskImpl <em>Task</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.impl.TaskImpl
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getTask()
     * @generated
     */
	int TASK = 91;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TASK__DOCUMENTATION = ACTIVITY__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TASK__NAME = ACTIVITY__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TASK__TEXT_ANNOTATION_ATTACHMENT = ACTIVITY__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Outgoing</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TASK__OUTGOING = ACTIVITY__OUTGOING;

	/**
     * The feature id for the '<em><b>Incoming</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TASK__INCOMING = ACTIVITY__INCOMING;

	/**
     * The feature id for the '<em><b>Dynamic Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TASK__DYNAMIC_LABEL = ACTIVITY__DYNAMIC_LABEL;

	/**
     * The feature id for the '<em><b>Dynamic Description</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TASK__DYNAMIC_DESCRIPTION = ACTIVITY__DYNAMIC_DESCRIPTION;

	/**
     * The feature id for the '<em><b>Step Summary</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TASK__STEP_SUMMARY = ACTIVITY__STEP_SUMMARY;

	/**
     * The feature id for the '<em><b>Data</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TASK__DATA = ACTIVITY__DATA;

	/**
     * The feature id for the '<em><b>Connectors</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TASK__CONNECTORS = ACTIVITY__CONNECTORS;

	/**
     * The feature id for the '<em><b>Kpis</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TASK__KPIS = ACTIVITY__KPIS;

	/**
     * The feature id for the '<em><b>Operations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TASK__OPERATIONS = ACTIVITY__OPERATIONS;

	/**
     * The feature id for the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TASK__TYPE = ACTIVITY__TYPE;

	/**
     * The feature id for the '<em><b>Test Before</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TASK__TEST_BEFORE = ACTIVITY__TEST_BEFORE;

	/**
     * The feature id for the '<em><b>Loop Condition</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TASK__LOOP_CONDITION = ACTIVITY__LOOP_CONDITION;

	/**
     * The feature id for the '<em><b>Loop Maximum</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TASK__LOOP_MAXIMUM = ACTIVITY__LOOP_MAXIMUM;

	/**
     * The feature id for the '<em><b>Use Cardinality</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TASK__USE_CARDINALITY = ACTIVITY__USE_CARDINALITY;

	/**
     * The feature id for the '<em><b>Cardinality Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TASK__CARDINALITY_EXPRESSION = ACTIVITY__CARDINALITY_EXPRESSION;

	/**
     * The feature id for the '<em><b>Collection Data To Multi Instantiate</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TASK__COLLECTION_DATA_TO_MULTI_INSTANTIATE = ACTIVITY__COLLECTION_DATA_TO_MULTI_INSTANTIATE;

	/**
     * The feature id for the '<em><b>Iterator Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TASK__ITERATOR_EXPRESSION = ACTIVITY__ITERATOR_EXPRESSION;

	/**
     * The feature id for the '<em><b>Output Data</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TASK__OUTPUT_DATA = ACTIVITY__OUTPUT_DATA;

	/**
     * The feature id for the '<em><b>List Data Containing Output Results</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TASK__LIST_DATA_CONTAINING_OUTPUT_RESULTS = ACTIVITY__LIST_DATA_CONTAINING_OUTPUT_RESULTS;

	/**
     * The feature id for the '<em><b>Completion Condition</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TASK__COMPLETION_CONDITION = ACTIVITY__COMPLETION_CONDITION;

	/**
     * The feature id for the '<em><b>Store Output</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TASK__STORE_OUTPUT = ACTIVITY__STORE_OUTPUT;

	/**
     * The feature id for the '<em><b>Boundary Intermediate Events</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TASK__BOUNDARY_INTERMEDIATE_EVENTS = ACTIVITY__BOUNDARY_INTERMEDIATE_EVENTS;

	/**
     * The feature id for the '<em><b>Form Mapping</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TASK__FORM_MAPPING = ACTIVITY_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Actor</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TASK__ACTOR = ACTIVITY_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Filters</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TASK__FILTERS = ACTIVITY_FEATURE_COUNT + 2;

	/**
     * The feature id for the '<em><b>Contract</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TASK__CONTRACT = ACTIVITY_FEATURE_COUNT + 3;

	/**
     * The feature id for the '<em><b>Override Actors Of The Lane</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TASK__OVERRIDE_ACTORS_OF_THE_LANE = ACTIVITY_FEATURE_COUNT + 4;

	/**
     * The feature id for the '<em><b>Priority</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TASK__PRIORITY = ACTIVITY_FEATURE_COUNT + 5;

	/**
     * The feature id for the '<em><b>Expected Duration</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TASK__EXPECTED_DURATION = ACTIVITY_FEATURE_COUNT + 6;

	/**
     * The number of structural features of the '<em>Task</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TASK_FEATURE_COUNT = ACTIVITY_FEATURE_COUNT + 7;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.TargetElement <em>Target Element</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.TargetElement
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getTargetElement()
     * @generated
     */
	int TARGET_ELEMENT = 92;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TARGET_ELEMENT__DOCUMENTATION = ELEMENT__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TARGET_ELEMENT__NAME = ELEMENT__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TARGET_ELEMENT__TEXT_ANNOTATION_ATTACHMENT = ELEMENT__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Incoming</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TARGET_ELEMENT__INCOMING = ELEMENT_FEATURE_COUNT + 0;

	/**
     * The number of structural features of the '<em>Target Element</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TARGET_ELEMENT_FEATURE_COUNT = ELEMENT_FEATURE_COUNT + 1;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.impl.TextAnnotationImpl <em>Text Annotation</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.impl.TextAnnotationImpl
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getTextAnnotation()
     * @generated
     */
	int TEXT_ANNOTATION = 93;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_ANNOTATION__DOCUMENTATION = ELEMENT__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_ANNOTATION__NAME = ELEMENT__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_ANNOTATION__TEXT_ANNOTATION_ATTACHMENT = ELEMENT__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Text</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_ANNOTATION__TEXT = ELEMENT_FEATURE_COUNT + 0;

	/**
     * The number of structural features of the '<em>Text Annotation</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_ANNOTATION_FEATURE_COUNT = ELEMENT_FEATURE_COUNT + 1;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.impl.TextAnnotationAttachmentImpl <em>Text Annotation Attachment</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.impl.TextAnnotationAttachmentImpl
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getTextAnnotationAttachment()
     * @generated
     */
	int TEXT_ANNOTATION_ATTACHMENT = 94;

	/**
     * The feature id for the '<em><b>Source</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_ANNOTATION_ATTACHMENT__SOURCE = 0;

	/**
     * The feature id for the '<em><b>Target</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_ANNOTATION_ATTACHMENT__TARGET = 1;

	/**
     * The number of structural features of the '<em>Text Annotation Attachment</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_ANNOTATION_ATTACHMENT_FEATURE_COUNT = 2;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.impl.ThrowLinkEventImpl <em>Throw Link Event</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.impl.ThrowLinkEventImpl
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getThrowLinkEvent()
     * @generated
     */
	int THROW_LINK_EVENT = 96;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int THROW_LINK_EVENT__DOCUMENTATION = LINK_EVENT__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int THROW_LINK_EVENT__NAME = LINK_EVENT__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int THROW_LINK_EVENT__TEXT_ANNOTATION_ATTACHMENT = LINK_EVENT__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Outgoing</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int THROW_LINK_EVENT__OUTGOING = LINK_EVENT__OUTGOING;

	/**
     * The feature id for the '<em><b>Incoming</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int THROW_LINK_EVENT__INCOMING = LINK_EVENT__INCOMING;

	/**
     * The feature id for the '<em><b>Dynamic Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int THROW_LINK_EVENT__DYNAMIC_LABEL = LINK_EVENT__DYNAMIC_LABEL;

	/**
     * The feature id for the '<em><b>Dynamic Description</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int THROW_LINK_EVENT__DYNAMIC_DESCRIPTION = LINK_EVENT__DYNAMIC_DESCRIPTION;

	/**
     * The feature id for the '<em><b>Step Summary</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int THROW_LINK_EVENT__STEP_SUMMARY = LINK_EVENT__STEP_SUMMARY;

	/**
     * The feature id for the '<em><b>To</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int THROW_LINK_EVENT__TO = LINK_EVENT_FEATURE_COUNT + 0;

	/**
     * The number of structural features of the '<em>Throw Link Event</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int THROW_LINK_EVENT_FEATURE_COUNT = LINK_EVENT_FEATURE_COUNT + 1;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.impl.XMLDataImpl <em>XML Data</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.impl.XMLDataImpl
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getXMLData()
     * @generated
     */
	int XML_DATA = 99;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_DATA__DOCUMENTATION = DATA__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_DATA__NAME = DATA__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_DATA__TEXT_ANNOTATION_ATTACHMENT = DATA__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Generated</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_DATA__GENERATED = DATA__GENERATED;

	/**
     * The feature id for the '<em><b>Multiple</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_DATA__MULTIPLE = DATA__MULTIPLE;

	/**
     * The feature id for the '<em><b>Transient</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_DATA__TRANSIENT = DATA__TRANSIENT;

	/**
     * The feature id for the '<em><b>Datasource Id</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_DATA__DATASOURCE_ID = DATA__DATASOURCE_ID;

	/**
     * The feature id for the '<em><b>Data Type</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_DATA__DATA_TYPE = DATA__DATA_TYPE;

	/**
     * The feature id for the '<em><b>Default Value</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_DATA__DEFAULT_VALUE = DATA__DEFAULT_VALUE;

	/**
     * The feature id for the '<em><b>Namespace</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_DATA__NAMESPACE = DATA_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_DATA__TYPE = DATA_FEATURE_COUNT + 1;

	/**
     * The number of structural features of the '<em>XML Data</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_DATA_FEATURE_COUNT = DATA_FEATURE_COUNT + 2;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.impl.XMLTypeImpl <em>XML Type</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.impl.XMLTypeImpl
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getXMLType()
     * @generated
     */
	int XML_TYPE = 100;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_TYPE__DOCUMENTATION = DATA_TYPE__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_TYPE__NAME = DATA_TYPE__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_TYPE__TEXT_ANNOTATION_ATTACHMENT = DATA_TYPE__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The number of structural features of the '<em>XML Type</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_TYPE_FEATURE_COUNT = DATA_TYPE_FEATURE_COUNT + 0;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.impl.XORGatewayImpl <em>XOR Gateway</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.impl.XORGatewayImpl
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getXORGateway()
     * @generated
     */
	int XOR_GATEWAY = 101;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XOR_GATEWAY__DOCUMENTATION = GATEWAY__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XOR_GATEWAY__NAME = GATEWAY__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XOR_GATEWAY__TEXT_ANNOTATION_ATTACHMENT = GATEWAY__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Outgoing</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XOR_GATEWAY__OUTGOING = GATEWAY__OUTGOING;

	/**
     * The feature id for the '<em><b>Incoming</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XOR_GATEWAY__INCOMING = GATEWAY__INCOMING;

	/**
     * The feature id for the '<em><b>Dynamic Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XOR_GATEWAY__DYNAMIC_LABEL = GATEWAY__DYNAMIC_LABEL;

	/**
     * The feature id for the '<em><b>Dynamic Description</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XOR_GATEWAY__DYNAMIC_DESCRIPTION = GATEWAY__DYNAMIC_DESCRIPTION;

	/**
     * The feature id for the '<em><b>Step Summary</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XOR_GATEWAY__STEP_SUMMARY = GATEWAY__STEP_SUMMARY;

	/**
     * The feature id for the '<em><b>Gateway Type</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XOR_GATEWAY__GATEWAY_TYPE = GATEWAY__GATEWAY_TYPE;

	/**
     * The number of structural features of the '<em>XOR Gateway</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XOR_GATEWAY_FEATURE_COUNT = GATEWAY_FEATURE_COUNT + 0;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.impl.AdditionalResourceImpl <em>Additional Resource</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.impl.AdditionalResourceImpl
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getAdditionalResource()
     * @generated
     */
    int ADDITIONAL_RESOURCE = 102;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ADDITIONAL_RESOURCE__NAME = 0;

    /**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ADDITIONAL_RESOURCE__DESCRIPTION = 1;

    /**
     * The number of structural features of the '<em>Additional Resource</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ADDITIONAL_RESOURCE_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.CorrelationTypeActive <em>Correlation Type Active</em>}' enum.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.CorrelationTypeActive
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getCorrelationTypeActive()
     * @generated
     */
	int CORRELATION_TYPE_ACTIVE = 103;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.ContractInputType <em>Contract Input Type</em>}' enum.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.ContractInputType
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getContractInputType()
     * @generated
     */
	int CONTRACT_INPUT_TYPE = 104;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.DocumentType <em>Document Type</em>}' enum.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.DocumentType
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getDocumentType()
     * @generated
     */
	int DOCUMENT_TYPE = 105;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.FormMappingType <em>Form Mapping Type</em>}' enum.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.FormMappingType
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getFormMappingType()
     * @generated
     */
	int FORM_MAPPING_TYPE = 106;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.GatewayType <em>Gateway Type</em>}' enum.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.GatewayType
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getGatewayType()
     * @generated
     */
	int GATEWAY_TYPE = 107;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.InputMappingAssignationType <em>Input Mapping Assignation Type</em>}' enum.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.InputMappingAssignationType
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getInputMappingAssignationType()
     * @generated
     */
	int INPUT_MAPPING_ASSIGNATION_TYPE = 108;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.MultiInstanceType <em>Multi Instance Type</em>}' enum.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.MultiInstanceType
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getMultiInstanceType()
     * @generated
     */
	int MULTI_INSTANCE_TYPE = 109;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.SequenceFlowConditionType <em>Sequence Flow Condition Type</em>}' enum.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.SequenceFlowConditionType
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getSequenceFlowConditionType()
     * @generated
     */
	int SEQUENCE_FLOW_CONDITION_TYPE = 110;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.StartTimerScriptType <em>Start Timer Script Type</em>}' enum.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.StartTimerScriptType
     * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getStartTimerScriptType()
     * @generated
     */
	int START_TIMER_SCRIPT_TYPE = 111;


	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.process.AbstractCatchMessageEvent <em>Abstract Catch Message Event</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Abstract Catch Message Event</em>'.
     * @see org.bonitasoft.studio.model.process.AbstractCatchMessageEvent
     * @generated
     */
	EClass getAbstractCatchMessageEvent();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.process.AbstractCatchMessageEvent#getEvent <em>Event</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Event</em>'.
     * @see org.bonitasoft.studio.model.process.AbstractCatchMessageEvent#getEvent()
     * @see #getAbstractCatchMessageEvent()
     * @generated
     */
	EAttribute getAbstractCatchMessageEvent_Event();

	/**
     * Returns the meta object for the reference '{@link org.bonitasoft.studio.model.process.AbstractCatchMessageEvent#getIncomingMessag <em>Incoming Messag</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Incoming Messag</em>'.
     * @see org.bonitasoft.studio.model.process.AbstractCatchMessageEvent#getIncomingMessag()
     * @see #getAbstractCatchMessageEvent()
     * @generated
     */
	EReference getAbstractCatchMessageEvent_IncomingMessag();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.model.process.AbstractCatchMessageEvent#getCorrelation <em>Correlation</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Correlation</em>'.
     * @see org.bonitasoft.studio.model.process.AbstractCatchMessageEvent#getCorrelation()
     * @see #getAbstractCatchMessageEvent()
     * @generated
     */
	EReference getAbstractCatchMessageEvent_Correlation();

	/**
     * Returns the meta object for the containment reference list '{@link org.bonitasoft.studio.model.process.AbstractCatchMessageEvent#getMessageContent <em>Message Content</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Message Content</em>'.
     * @see org.bonitasoft.studio.model.process.AbstractCatchMessageEvent#getMessageContent()
     * @see #getAbstractCatchMessageEvent()
     * @generated
     */
	EReference getAbstractCatchMessageEvent_MessageContent();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.process.AbstractPageFlow <em>Abstract Page Flow</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Abstract Page Flow</em>'.
     * @see org.bonitasoft.studio.model.process.AbstractPageFlow
     * @generated
     */
	EClass getAbstractPageFlow();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.process.AbstractProcess <em>Abstract Process</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Abstract Process</em>'.
     * @see org.bonitasoft.studio.model.process.AbstractProcess
     * @generated
     */
	EClass getAbstractProcess();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.process.AbstractProcess#getVersion <em>Version</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Version</em>'.
     * @see org.bonitasoft.studio.model.process.AbstractProcess#getVersion()
     * @see #getAbstractProcess()
     * @generated
     */
	EAttribute getAbstractProcess_Version();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.process.AbstractProcess#getAuthor <em>Author</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Author</em>'.
     * @see org.bonitasoft.studio.model.process.AbstractProcess#getAuthor()
     * @see #getAbstractProcess()
     * @generated
     */
	EAttribute getAbstractProcess_Author();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.process.AbstractProcess#getCreationDate <em>Creation Date</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Creation Date</em>'.
     * @see org.bonitasoft.studio.model.process.AbstractProcess#getCreationDate()
     * @see #getAbstractProcess()
     * @generated
     */
	EAttribute getAbstractProcess_CreationDate();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.process.AbstractProcess#getModificationDate <em>Modification Date</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Modification Date</em>'.
     * @see org.bonitasoft.studio.model.process.AbstractProcess#getModificationDate()
     * @see #getAbstractProcess()
     * @generated
     */
	EAttribute getAbstractProcess_ModificationDate();

	/**
     * Returns the meta object for the containment reference list '{@link org.bonitasoft.studio.model.process.AbstractProcess#getDatatypes <em>Datatypes</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Datatypes</em>'.
     * @see org.bonitasoft.studio.model.process.AbstractProcess#getDatatypes()
     * @see #getAbstractProcess()
     * @generated
     */
	EReference getAbstractProcess_Datatypes();

	/**
     * Returns the meta object for the containment reference list '{@link org.bonitasoft.studio.model.process.AbstractProcess#getConnections <em>Connections</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Connections</em>'.
     * @see org.bonitasoft.studio.model.process.AbstractProcess#getConnections()
     * @see #getAbstractProcess()
     * @generated
     */
	EReference getAbstractProcess_Connections();

	/**
     * Returns the meta object for the attribute list '{@link org.bonitasoft.studio.model.process.AbstractProcess#getCategories <em>Categories</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute list '<em>Categories</em>'.
     * @see org.bonitasoft.studio.model.process.AbstractProcess#getCategories()
     * @see #getAbstractProcess()
     * @generated
     */
	EAttribute getAbstractProcess_Categories();

	/**
     * Returns the meta object for the containment reference list '{@link org.bonitasoft.studio.model.process.AbstractProcess#getActors <em>Actors</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Actors</em>'.
     * @see org.bonitasoft.studio.model.process.AbstractProcess#getActors()
     * @see #getAbstractProcess()
     * @generated
     */
	EReference getAbstractProcess_Actors();

	/**
     * Returns the meta object for the containment reference list '{@link org.bonitasoft.studio.model.process.AbstractProcess#getConfigurations <em>Configurations</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Configurations</em>'.
     * @see org.bonitasoft.studio.model.process.AbstractProcess#getConfigurations()
     * @see #getAbstractProcess()
     * @generated
     */
	EReference getAbstractProcess_Configurations();

	/**
     * Returns the meta object for the containment reference list '{@link org.bonitasoft.studio.model.process.AbstractProcess#getParameters <em>Parameters</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Parameters</em>'.
     * @see org.bonitasoft.studio.model.process.AbstractProcess#getParameters()
     * @see #getAbstractProcess()
     * @generated
     */
	EReference getAbstractProcess_Parameters();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.process.AbstractTimerEvent <em>Abstract Timer Event</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Abstract Timer Event</em>'.
     * @see org.bonitasoft.studio.model.process.AbstractTimerEvent
     * @generated
     */
	EClass getAbstractTimerEvent();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.model.process.AbstractTimerEvent#getCondition <em>Condition</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Condition</em>'.
     * @see org.bonitasoft.studio.model.process.AbstractTimerEvent#getCondition()
     * @see #getAbstractTimerEvent()
     * @generated
     */
	EReference getAbstractTimerEvent_Condition();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.process.Activity <em>Activity</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Activity</em>'.
     * @see org.bonitasoft.studio.model.process.Activity
     * @generated
     */
	EClass getActivity();

	/**
     * Returns the meta object for the containment reference list '{@link org.bonitasoft.studio.model.process.Activity#getBoundaryIntermediateEvents <em>Boundary Intermediate Events</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Boundary Intermediate Events</em>'.
     * @see org.bonitasoft.studio.model.process.Activity#getBoundaryIntermediateEvents()
     * @see #getActivity()
     * @generated
     */
	EReference getActivity_BoundaryIntermediateEvents();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.process.Actor <em>Actor</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Actor</em>'.
     * @see org.bonitasoft.studio.model.process.Actor
     * @generated
     */
	EClass getActor();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.process.Actor#isInitiator <em>Initiator</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Initiator</em>'.
     * @see org.bonitasoft.studio.model.process.Actor#isInitiator()
     * @see #getActor()
     * @generated
     */
	EAttribute getActor_Initiator();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.process.ActorFilter <em>Actor Filter</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Actor Filter</em>'.
     * @see org.bonitasoft.studio.model.process.ActorFilter
     * @generated
     */
	EClass getActorFilter();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.process.ANDGateway <em>AND Gateway</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>AND Gateway</em>'.
     * @see org.bonitasoft.studio.model.process.ANDGateway
     * @generated
     */
	EClass getANDGateway();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.process.Assignable <em>Assignable</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Assignable</em>'.
     * @see org.bonitasoft.studio.model.process.Assignable
     * @generated
     */
	EClass getAssignable();

	/**
     * Returns the meta object for the reference '{@link org.bonitasoft.studio.model.process.Assignable#getActor <em>Actor</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Actor</em>'.
     * @see org.bonitasoft.studio.model.process.Assignable#getActor()
     * @see #getAssignable()
     * @generated
     */
	EReference getAssignable_Actor();

	/**
     * Returns the meta object for the containment reference list '{@link org.bonitasoft.studio.model.process.Assignable#getFilters <em>Filters</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Filters</em>'.
     * @see org.bonitasoft.studio.model.process.Assignable#getFilters()
     * @see #getAssignable()
     * @generated
     */
	EReference getAssignable_Filters();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.process.Association <em>Association</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Association</em>'.
     * @see org.bonitasoft.studio.model.process.Association
     * @generated
     */
	EClass getAssociation();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.process.Association#isIsDirected <em>Is Directed</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Is Directed</em>'.
     * @see org.bonitasoft.studio.model.process.Association#isIsDirected()
     * @see #getAssociation()
     * @generated
     */
	EAttribute getAssociation_IsDirected();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.process.BooleanType <em>Boolean Type</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Boolean Type</em>'.
     * @see org.bonitasoft.studio.model.process.BooleanType
     * @generated
     */
	EClass getBooleanType();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.process.BoundaryEvent <em>Boundary Event</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Boundary Event</em>'.
     * @see org.bonitasoft.studio.model.process.BoundaryEvent
     * @generated
     */
	EClass getBoundaryEvent();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.process.BoundaryMessageEvent <em>Boundary Message Event</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Boundary Message Event</em>'.
     * @see org.bonitasoft.studio.model.process.BoundaryMessageEvent
     * @generated
     */
	EClass getBoundaryMessageEvent();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.process.BoundarySignalEvent <em>Boundary Signal Event</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Boundary Signal Event</em>'.
     * @see org.bonitasoft.studio.model.process.BoundarySignalEvent
     * @generated
     */
	EClass getBoundarySignalEvent();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.process.BoundaryTimerEvent <em>Boundary Timer Event</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Boundary Timer Event</em>'.
     * @see org.bonitasoft.studio.model.process.BoundaryTimerEvent
     * @generated
     */
	EClass getBoundaryTimerEvent();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.process.BusinessObjectData <em>Business Object Data</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Business Object Data</em>'.
     * @see org.bonitasoft.studio.model.process.BusinessObjectData
     * @generated
     */
	EClass getBusinessObjectData();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.process.BusinessObjectData#getBusinessDataRepositoryId <em>Business Data Repository Id</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Business Data Repository Id</em>'.
     * @see org.bonitasoft.studio.model.process.BusinessObjectData#getBusinessDataRepositoryId()
     * @see #getBusinessObjectData()
     * @generated
     */
	EAttribute getBusinessObjectData_BusinessDataRepositoryId();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.process.BusinessObjectData#isCreateNewInstance <em>Create New Instance</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Create New Instance</em>'.
     * @see org.bonitasoft.studio.model.process.BusinessObjectData#isCreateNewInstance()
     * @see #getBusinessObjectData()
     * @generated
     */
	EAttribute getBusinessObjectData_CreateNewInstance();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.process.BusinessObjectData#getEClassName <em>EClass Name</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>EClass Name</em>'.
     * @see org.bonitasoft.studio.model.process.BusinessObjectData#getEClassName()
     * @see #getBusinessObjectData()
     * @generated
     */
	EAttribute getBusinessObjectData_EClassName();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.process.BusinessObjectType <em>Business Object Type</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Business Object Type</em>'.
     * @see org.bonitasoft.studio.model.process.BusinessObjectType
     * @generated
     */
	EClass getBusinessObjectType();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.process.CallActivity <em>Call Activity</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Call Activity</em>'.
     * @see org.bonitasoft.studio.model.process.CallActivity
     * @generated
     */
	EClass getCallActivity();

	/**
     * Returns the meta object for the containment reference list '{@link org.bonitasoft.studio.model.process.CallActivity#getInputMappings <em>Input Mappings</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Input Mappings</em>'.
     * @see org.bonitasoft.studio.model.process.CallActivity#getInputMappings()
     * @see #getCallActivity()
     * @generated
     */
	EReference getCallActivity_InputMappings();

	/**
     * Returns the meta object for the containment reference list '{@link org.bonitasoft.studio.model.process.CallActivity#getOutputMappings <em>Output Mappings</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Output Mappings</em>'.
     * @see org.bonitasoft.studio.model.process.CallActivity#getOutputMappings()
     * @see #getCallActivity()
     * @generated
     */
	EReference getCallActivity_OutputMappings();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.model.process.CallActivity#getCalledActivityName <em>Called Activity Name</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Called Activity Name</em>'.
     * @see org.bonitasoft.studio.model.process.CallActivity#getCalledActivityName()
     * @see #getCallActivity()
     * @generated
     */
	EReference getCallActivity_CalledActivityName();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.model.process.CallActivity#getCalledActivityVersion <em>Called Activity Version</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Called Activity Version</em>'.
     * @see org.bonitasoft.studio.model.process.CallActivity#getCalledActivityVersion()
     * @see #getCallActivity()
     * @generated
     */
	EReference getCallActivity_CalledActivityVersion();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.process.CatchLinkEvent <em>Catch Link Event</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Catch Link Event</em>'.
     * @see org.bonitasoft.studio.model.process.CatchLinkEvent
     * @generated
     */
	EClass getCatchLinkEvent();

	/**
     * Returns the meta object for the reference list '{@link org.bonitasoft.studio.model.process.CatchLinkEvent#getFrom <em>From</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>From</em>'.
     * @see org.bonitasoft.studio.model.process.CatchLinkEvent#getFrom()
     * @see #getCatchLinkEvent()
     * @generated
     */
	EReference getCatchLinkEvent_From();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.process.CatchMessageEvent <em>Catch Message Event</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Catch Message Event</em>'.
     * @see org.bonitasoft.studio.model.process.CatchMessageEvent
     * @generated
     */
	EClass getCatchMessageEvent();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.process.CatchSignalEvent <em>Catch Signal Event</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Catch Signal Event</em>'.
     * @see org.bonitasoft.studio.model.process.CatchSignalEvent
     * @generated
     */
	EClass getCatchSignalEvent();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.process.ConnectableElement <em>Connectable Element</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Connectable Element</em>'.
     * @see org.bonitasoft.studio.model.process.ConnectableElement
     * @generated
     */
	EClass getConnectableElement();

	/**
     * Returns the meta object for the containment reference list '{@link org.bonitasoft.studio.model.process.ConnectableElement#getConnectors <em>Connectors</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Connectors</em>'.
     * @see org.bonitasoft.studio.model.process.ConnectableElement#getConnectors()
     * @see #getConnectableElement()
     * @generated
     */
	EReference getConnectableElement_Connectors();

	/**
     * Returns the meta object for the containment reference list '{@link org.bonitasoft.studio.model.process.ConnectableElement#getKpis <em>Kpis</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Kpis</em>'.
     * @see org.bonitasoft.studio.model.process.ConnectableElement#getKpis()
     * @see #getConnectableElement()
     * @generated
     */
	EReference getConnectableElement_Kpis();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.process.Connector <em>Connector</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Connector</em>'.
     * @see org.bonitasoft.studio.model.process.Connector
     * @generated
     */
	EClass getConnector();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.process.Connector#getDefinitionId <em>Definition Id</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Definition Id</em>'.
     * @see org.bonitasoft.studio.model.process.Connector#getDefinitionId()
     * @see #getConnector()
     * @generated
     */
	EAttribute getConnector_DefinitionId();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.process.Connector#getEvent <em>Event</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Event</em>'.
     * @see org.bonitasoft.studio.model.process.Connector#getEvent()
     * @see #getConnector()
     * @generated
     */
	EAttribute getConnector_Event();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.process.Connector#isIgnoreErrors <em>Ignore Errors</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Ignore Errors</em>'.
     * @see org.bonitasoft.studio.model.process.Connector#isIgnoreErrors()
     * @see #getConnector()
     * @generated
     */
	EAttribute getConnector_IgnoreErrors();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.process.Connector#isThrowErrorEvent <em>Throw Error Event</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Throw Error Event</em>'.
     * @see org.bonitasoft.studio.model.process.Connector#isThrowErrorEvent()
     * @see #getConnector()
     * @generated
     */
	EAttribute getConnector_ThrowErrorEvent();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.process.Connector#getNamedError <em>Named Error</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Named Error</em>'.
     * @see org.bonitasoft.studio.model.process.Connector#getNamedError()
     * @see #getConnector()
     * @generated
     */
	EAttribute getConnector_NamedError();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.process.Connector#getDefinitionVersion <em>Definition Version</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Definition Version</em>'.
     * @see org.bonitasoft.studio.model.process.Connector#getDefinitionVersion()
     * @see #getConnector()
     * @generated
     */
	EAttribute getConnector_DefinitionVersion();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.model.process.Connector#getConfiguration <em>Configuration</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Configuration</em>'.
     * @see org.bonitasoft.studio.model.process.Connector#getConfiguration()
     * @see #getConnector()
     * @generated
     */
	EReference getConnector_Configuration();

	/**
     * Returns the meta object for the containment reference list '{@link org.bonitasoft.studio.model.process.Connector#getOutputs <em>Outputs</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Outputs</em>'.
     * @see org.bonitasoft.studio.model.process.Connector#getOutputs()
     * @see #getConnector()
     * @generated
     */
	EReference getConnector_Outputs();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.process.Container <em>Container</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Container</em>'.
     * @see org.bonitasoft.studio.model.process.Container
     * @generated
     */
	EClass getContainer();

	/**
     * Returns the meta object for the containment reference list '{@link org.bonitasoft.studio.model.process.Container#getElements <em>Elements</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Elements</em>'.
     * @see org.bonitasoft.studio.model.process.Container#getElements()
     * @see #getContainer()
     * @generated
     */
	EReference getContainer_Elements();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.process.Contract <em>Contract</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Contract</em>'.
     * @see org.bonitasoft.studio.model.process.Contract
     * @generated
     */
	EClass getContract();

	/**
     * Returns the meta object for the containment reference list '{@link org.bonitasoft.studio.model.process.Contract#getInputs <em>Inputs</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Inputs</em>'.
     * @see org.bonitasoft.studio.model.process.Contract#getInputs()
     * @see #getContract()
     * @generated
     */
	EReference getContract_Inputs();

	/**
     * Returns the meta object for the containment reference list '{@link org.bonitasoft.studio.model.process.Contract#getConstraints <em>Constraints</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Constraints</em>'.
     * @see org.bonitasoft.studio.model.process.Contract#getConstraints()
     * @see #getContract()
     * @generated
     */
	EReference getContract_Constraints();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.process.ContractInputMapping <em>Contract Input Mapping</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Contract Input Mapping</em>'.
     * @see org.bonitasoft.studio.model.process.ContractInputMapping
     * @generated
     */
	EClass getContractInputMapping();

	/**
     * Returns the meta object for the reference '{@link org.bonitasoft.studio.model.process.ContractInputMapping#getData <em>Data</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Data</em>'.
     * @see org.bonitasoft.studio.model.process.ContractInputMapping#getData()
     * @see #getContractInputMapping()
     * @generated
     */
	EReference getContractInputMapping_Data();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.process.ContractInputMapping#getSetterName <em>Setter Name</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Setter Name</em>'.
     * @see org.bonitasoft.studio.model.process.ContractInputMapping#getSetterName()
     * @see #getContractInputMapping()
     * @generated
     */
	EAttribute getContractInputMapping_SetterName();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.process.ContractInputMapping#getSetterParamType <em>Setter Param Type</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Setter Param Type</em>'.
     * @see org.bonitasoft.studio.model.process.ContractInputMapping#getSetterParamType()
     * @see #getContractInputMapping()
     * @generated
     */
	EAttribute getContractInputMapping_SetterParamType();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.process.ContractInput <em>Contract Input</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Contract Input</em>'.
     * @see org.bonitasoft.studio.model.process.ContractInput
     * @generated
     */
	EClass getContractInput();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.process.ContractInput#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.bonitasoft.studio.model.process.ContractInput#getName()
     * @see #getContractInput()
     * @generated
     */
	EAttribute getContractInput_Name();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.process.ContractInput#getType <em>Type</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Type</em>'.
     * @see org.bonitasoft.studio.model.process.ContractInput#getType()
     * @see #getContractInput()
     * @generated
     */
	EAttribute getContractInput_Type();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.process.ContractInput#getDescription <em>Description</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Description</em>'.
     * @see org.bonitasoft.studio.model.process.ContractInput#getDescription()
     * @see #getContractInput()
     * @generated
     */
	EAttribute getContractInput_Description();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.process.ContractInput#isMultiple <em>Multiple</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Multiple</em>'.
     * @see org.bonitasoft.studio.model.process.ContractInput#isMultiple()
     * @see #getContractInput()
     * @generated
     */
	EAttribute getContractInput_Multiple();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.model.process.ContractInput#getMapping <em>Mapping</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Mapping</em>'.
     * @see org.bonitasoft.studio.model.process.ContractInput#getMapping()
     * @see #getContractInput()
     * @generated
     */
	EReference getContractInput_Mapping();

	/**
     * Returns the meta object for the containment reference list '{@link org.bonitasoft.studio.model.process.ContractInput#getInputs <em>Inputs</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Inputs</em>'.
     * @see org.bonitasoft.studio.model.process.ContractInput#getInputs()
     * @see #getContractInput()
     * @generated
     */
	EReference getContractInput_Inputs();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.process.ContractInput#getDataReference <em>Data Reference</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Data Reference</em>'.
     * @see org.bonitasoft.studio.model.process.ContractInput#getDataReference()
     * @see #getContractInput()
     * @generated
     */
    EAttribute getContractInput_DataReference();

    /**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.process.ContractInput#isCreateMode <em>Create Mode</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Create Mode</em>'.
     * @see org.bonitasoft.studio.model.process.ContractInput#isCreateMode()
     * @see #getContractInput()
     * @generated
     */
    EAttribute getContractInput_CreateMode();

    /**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.process.ContractConstraint <em>Contract Constraint</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Contract Constraint</em>'.
     * @see org.bonitasoft.studio.model.process.ContractConstraint
     * @generated
     */
	EClass getContractConstraint();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.process.ContractConstraint#getExpression <em>Expression</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Expression</em>'.
     * @see org.bonitasoft.studio.model.process.ContractConstraint#getExpression()
     * @see #getContractConstraint()
     * @generated
     */
	EAttribute getContractConstraint_Expression();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.process.ContractConstraint#getErrorMessage <em>Error Message</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Error Message</em>'.
     * @see org.bonitasoft.studio.model.process.ContractConstraint#getErrorMessage()
     * @see #getContractConstraint()
     * @generated
     */
	EAttribute getContractConstraint_ErrorMessage();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.process.ContractConstraint#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.bonitasoft.studio.model.process.ContractConstraint#getName()
     * @see #getContractConstraint()
     * @generated
     */
	EAttribute getContractConstraint_Name();

	/**
     * Returns the meta object for the attribute list '{@link org.bonitasoft.studio.model.process.ContractConstraint#getInputNames <em>Input Names</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute list '<em>Input Names</em>'.
     * @see org.bonitasoft.studio.model.process.ContractConstraint#getInputNames()
     * @see #getContractConstraint()
     * @generated
     */
	EAttribute getContractConstraint_InputNames();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.process.ContractConstraint#getDescription <em>Description</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Description</em>'.
     * @see org.bonitasoft.studio.model.process.ContractConstraint#getDescription()
     * @see #getContractConstraint()
     * @generated
     */
    EAttribute getContractConstraint_Description();

    /**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.process.ContractContainer <em>Contract Container</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Contract Container</em>'.
     * @see org.bonitasoft.studio.model.process.ContractContainer
     * @generated
     */
	EClass getContractContainer();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.model.process.ContractContainer#getContract <em>Contract</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Contract</em>'.
     * @see org.bonitasoft.studio.model.process.ContractContainer#getContract()
     * @see #getContractContainer()
     * @generated
     */
	EReference getContractContainer_Contract();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.process.Connection <em>Connection</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Connection</em>'.
     * @see org.bonitasoft.studio.model.process.Connection
     * @generated
     */
	EClass getConnection();

	/**
     * Returns the meta object for the reference '{@link org.bonitasoft.studio.model.process.Connection#getTarget <em>Target</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Target</em>'.
     * @see org.bonitasoft.studio.model.process.Connection#getTarget()
     * @see #getConnection()
     * @generated
     */
	EReference getConnection_Target();

	/**
     * Returns the meta object for the reference '{@link org.bonitasoft.studio.model.process.Connection#getSource <em>Source</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Source</em>'.
     * @see org.bonitasoft.studio.model.process.Connection#getSource()
     * @see #getConnection()
     * @generated
     */
	EReference getConnection_Source();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.process.Correlation <em>Correlation</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Correlation</em>'.
     * @see org.bonitasoft.studio.model.process.Correlation
     * @generated
     */
	EClass getCorrelation();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.process.Correlation#getCorrelationType <em>Correlation Type</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Correlation Type</em>'.
     * @see org.bonitasoft.studio.model.process.Correlation#getCorrelationType()
     * @see #getCorrelation()
     * @generated
     */
	EAttribute getCorrelation_CorrelationType();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.model.process.Correlation#getCorrelationAssociation <em>Correlation Association</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Correlation Association</em>'.
     * @see org.bonitasoft.studio.model.process.Correlation#getCorrelationAssociation()
     * @see #getCorrelation()
     * @generated
     */
	EReference getCorrelation_CorrelationAssociation();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.process.CorrelationAssociation <em>Correlation Association</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Correlation Association</em>'.
     * @see org.bonitasoft.studio.model.process.CorrelationAssociation
     * @generated
     */
	EClass getCorrelationAssociation();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.model.process.CorrelationAssociation#getCorrelationExpression <em>Correlation Expression</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Correlation Expression</em>'.
     * @see org.bonitasoft.studio.model.process.CorrelationAssociation#getCorrelationExpression()
     * @see #getCorrelationAssociation()
     * @generated
     */
	EReference getCorrelationAssociation_CorrelationExpression();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.model.process.CorrelationAssociation#getCorrelationKey <em>Correlation Key</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Correlation Key</em>'.
     * @see org.bonitasoft.studio.model.process.CorrelationAssociation#getCorrelationKey()
     * @see #getCorrelationAssociation()
     * @generated
     */
	EReference getCorrelationAssociation_CorrelationKey();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.process.Data <em>Data</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Data</em>'.
     * @see org.bonitasoft.studio.model.process.Data
     * @generated
     */
	EClass getData();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.process.Data#isGenerated <em>Generated</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Generated</em>'.
     * @see org.bonitasoft.studio.model.process.Data#isGenerated()
     * @see #getData()
     * @generated
     */
	EAttribute getData_Generated();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.process.Data#isMultiple <em>Multiple</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Multiple</em>'.
     * @see org.bonitasoft.studio.model.process.Data#isMultiple()
     * @see #getData()
     * @generated
     */
	EAttribute getData_Multiple();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.process.Data#isTransient <em>Transient</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Transient</em>'.
     * @see org.bonitasoft.studio.model.process.Data#isTransient()
     * @see #getData()
     * @generated
     */
	EAttribute getData_Transient();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.process.Data#getDatasourceId <em>Datasource Id</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Datasource Id</em>'.
     * @see org.bonitasoft.studio.model.process.Data#getDatasourceId()
     * @see #getData()
     * @generated
     */
	EAttribute getData_DatasourceId();

	/**
     * Returns the meta object for the reference '{@link org.bonitasoft.studio.model.process.Data#getDataType <em>Data Type</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Data Type</em>'.
     * @see org.bonitasoft.studio.model.process.Data#getDataType()
     * @see #getData()
     * @generated
     */
	EReference getData_DataType();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.model.process.Data#getDefaultValue <em>Default Value</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Default Value</em>'.
     * @see org.bonitasoft.studio.model.process.Data#getDefaultValue()
     * @see #getData()
     * @generated
     */
	EReference getData_DefaultValue();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.process.DataAware <em>Data Aware</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Data Aware</em>'.
     * @see org.bonitasoft.studio.model.process.DataAware
     * @generated
     */
	EClass getDataAware();

	/**
     * Returns the meta object for the containment reference list '{@link org.bonitasoft.studio.model.process.DataAware#getData <em>Data</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Data</em>'.
     * @see org.bonitasoft.studio.model.process.DataAware#getData()
     * @see #getDataAware()
     * @generated
     */
	EReference getDataAware_Data();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.process.DateType <em>Date Type</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Date Type</em>'.
     * @see org.bonitasoft.studio.model.process.DateType
     * @generated
     */
	EClass getDateType();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.process.DataType <em>Data Type</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Data Type</em>'.
     * @see org.bonitasoft.studio.model.process.DataType
     * @generated
     */
	EClass getDataType();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.process.Document <em>Document</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Document</em>'.
     * @see org.bonitasoft.studio.model.process.Document
     * @generated
     */
	EClass getDocument();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.process.Document#getDefaultValueIdOfDocumentStore <em>Default Value Id Of Document Store</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Default Value Id Of Document Store</em>'.
     * @see org.bonitasoft.studio.model.process.Document#getDefaultValueIdOfDocumentStore()
     * @see #getDocument()
     * @generated
     */
	EAttribute getDocument_DefaultValueIdOfDocumentStore();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.model.process.Document#getMimeType <em>Mime Type</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Mime Type</em>'.
     * @see org.bonitasoft.studio.model.process.Document#getMimeType()
     * @see #getDocument()
     * @generated
     */
	EReference getDocument_MimeType();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.model.process.Document#getUrl <em>Url</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Url</em>'.
     * @see org.bonitasoft.studio.model.process.Document#getUrl()
     * @see #getDocument()
     * @generated
     */
	EReference getDocument_Url();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.process.Document#getDocumentType <em>Document Type</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Document Type</em>'.
     * @see org.bonitasoft.studio.model.process.Document#getDocumentType()
     * @see #getDocument()
     * @generated
     */
	EAttribute getDocument_DocumentType();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.model.process.Document#getInitialMultipleContent <em>Initial Multiple Content</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Initial Multiple Content</em>'.
     * @see org.bonitasoft.studio.model.process.Document#getInitialMultipleContent()
     * @see #getDocument()
     * @generated
     */
	EReference getDocument_InitialMultipleContent();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.process.Document#isMultiple <em>Multiple</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Multiple</em>'.
     * @see org.bonitasoft.studio.model.process.Document#isMultiple()
     * @see #getDocument()
     * @generated
     */
	EAttribute getDocument_Multiple();

	/**
     * Returns the meta object for the reference '{@link org.bonitasoft.studio.model.process.Document#getContractInput <em>Contract Input</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Contract Input</em>'.
     * @see org.bonitasoft.studio.model.process.Document#getContractInput()
     * @see #getDocument()
     * @generated
     */
	EReference getDocument_ContractInput();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.process.DoubleType <em>Double Type</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Double Type</em>'.
     * @see org.bonitasoft.studio.model.process.DoubleType
     * @generated
     */
	EClass getDoubleType();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.process.Element <em>Element</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Element</em>'.
     * @see org.bonitasoft.studio.model.process.Element
     * @generated
     */
	EClass getElement();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.process.Element#getDocumentation <em>Documentation</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Documentation</em>'.
     * @see org.bonitasoft.studio.model.process.Element#getDocumentation()
     * @see #getElement()
     * @generated
     */
	EAttribute getElement_Documentation();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.process.Element#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.bonitasoft.studio.model.process.Element#getName()
     * @see #getElement()
     * @generated
     */
	EAttribute getElement_Name();

	/**
     * Returns the meta object for the containment reference list '{@link org.bonitasoft.studio.model.process.Element#getTextAnnotationAttachment <em>Text Annotation Attachment</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Text Annotation Attachment</em>'.
     * @see org.bonitasoft.studio.model.process.Element#getTextAnnotationAttachment()
     * @see #getElement()
     * @generated
     */
	EReference getElement_TextAnnotationAttachment();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.process.Event <em>Event</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Event</em>'.
     * @see org.bonitasoft.studio.model.process.Event
     * @generated
     */
	EClass getEvent();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.process.EndErrorEvent <em>End Error Event</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>End Error Event</em>'.
     * @see org.bonitasoft.studio.model.process.EndErrorEvent
     * @generated
     */
	EClass getEndErrorEvent();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.process.EndEvent <em>End Event</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>End Event</em>'.
     * @see org.bonitasoft.studio.model.process.EndEvent
     * @generated
     */
	EClass getEndEvent();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.process.EndMessageEvent <em>End Message Event</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>End Message Event</em>'.
     * @see org.bonitasoft.studio.model.process.EndMessageEvent
     * @generated
     */
	EClass getEndMessageEvent();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.process.EndSignalEvent <em>End Signal Event</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>End Signal Event</em>'.
     * @see org.bonitasoft.studio.model.process.EndSignalEvent
     * @generated
     */
	EClass getEndSignalEvent();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.process.EndTerminatedEvent <em>End Terminated Event</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>End Terminated Event</em>'.
     * @see org.bonitasoft.studio.model.process.EndTerminatedEvent
     * @generated
     */
	EClass getEndTerminatedEvent();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.process.ErrorEvent <em>Error Event</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Error Event</em>'.
     * @see org.bonitasoft.studio.model.process.ErrorEvent
     * @generated
     */
	EClass getErrorEvent();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.process.ErrorEvent#getErrorCode <em>Error Code</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Error Code</em>'.
     * @see org.bonitasoft.studio.model.process.ErrorEvent#getErrorCode()
     * @see #getErrorEvent()
     * @generated
     */
	EAttribute getErrorEvent_ErrorCode();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.process.EnumType <em>Enum Type</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Enum Type</em>'.
     * @see org.bonitasoft.studio.model.process.EnumType
     * @generated
     */
	EClass getEnumType();

	/**
     * Returns the meta object for the attribute list '{@link org.bonitasoft.studio.model.process.EnumType#getLiterals <em>Literals</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute list '<em>Literals</em>'.
     * @see org.bonitasoft.studio.model.process.EnumType#getLiterals()
     * @see #getEnumType()
     * @generated
     */
	EAttribute getEnumType_Literals();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.process.FloatType <em>Float Type</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Float Type</em>'.
     * @see org.bonitasoft.studio.model.process.FloatType
     * @generated
     */
	EClass getFloatType();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.process.FlowElement <em>Flow Element</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Flow Element</em>'.
     * @see org.bonitasoft.studio.model.process.FlowElement
     * @generated
     */
	EClass getFlowElement();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.model.process.FlowElement#getDynamicLabel <em>Dynamic Label</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Dynamic Label</em>'.
     * @see org.bonitasoft.studio.model.process.FlowElement#getDynamicLabel()
     * @see #getFlowElement()
     * @generated
     */
	EReference getFlowElement_DynamicLabel();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.model.process.FlowElement#getDynamicDescription <em>Dynamic Description</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Dynamic Description</em>'.
     * @see org.bonitasoft.studio.model.process.FlowElement#getDynamicDescription()
     * @see #getFlowElement()
     * @generated
     */
	EReference getFlowElement_DynamicDescription();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.model.process.FlowElement#getStepSummary <em>Step Summary</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Step Summary</em>'.
     * @see org.bonitasoft.studio.model.process.FlowElement#getStepSummary()
     * @see #getFlowElement()
     * @generated
     */
	EReference getFlowElement_StepSummary();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.process.FormMapping <em>Form Mapping</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Form Mapping</em>'.
     * @see org.bonitasoft.studio.model.process.FormMapping
     * @generated
     */
	EClass getFormMapping();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.model.process.FormMapping#getTargetForm <em>Target Form</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Target Form</em>'.
     * @see org.bonitasoft.studio.model.process.FormMapping#getTargetForm()
     * @see #getFormMapping()
     * @generated
     */
	EReference getFormMapping_TargetForm();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.process.FormMapping#getType <em>Type</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Type</em>'.
     * @see org.bonitasoft.studio.model.process.FormMapping#getType()
     * @see #getFormMapping()
     * @generated
     */
	EAttribute getFormMapping_Type();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.process.FormMapping#getUrl <em>Url</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Url</em>'.
     * @see org.bonitasoft.studio.model.process.FormMapping#getUrl()
     * @see #getFormMapping()
     * @generated
     */
	EAttribute getFormMapping_Url();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.process.Gateway <em>Gateway</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Gateway</em>'.
     * @see org.bonitasoft.studio.model.process.Gateway
     * @generated
     */
	EClass getGateway();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.process.Gateway#getGatewayType <em>Gateway Type</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Gateway Type</em>'.
     * @see org.bonitasoft.studio.model.process.Gateway#getGatewayType()
     * @see #getGateway()
     * @generated
     */
	EAttribute getGateway_GatewayType();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.process.InclusiveGateway <em>Inclusive Gateway</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Inclusive Gateway</em>'.
     * @see org.bonitasoft.studio.model.process.InclusiveGateway
     * @generated
     */
	EClass getInclusiveGateway();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.process.InputMapping <em>Input Mapping</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Input Mapping</em>'.
     * @see org.bonitasoft.studio.model.process.InputMapping
     * @generated
     */
	EClass getInputMapping();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.model.process.InputMapping#getProcessSource <em>Process Source</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Process Source</em>'.
     * @see org.bonitasoft.studio.model.process.InputMapping#getProcessSource()
     * @see #getInputMapping()
     * @generated
     */
	EReference getInputMapping_ProcessSource();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.process.InputMapping#getSubprocessTarget <em>Subprocess Target</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Subprocess Target</em>'.
     * @see org.bonitasoft.studio.model.process.InputMapping#getSubprocessTarget()
     * @see #getInputMapping()
     * @generated
     */
	EAttribute getInputMapping_SubprocessTarget();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.process.InputMapping#getAssignationType <em>Assignation Type</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Assignation Type</em>'.
     * @see org.bonitasoft.studio.model.process.InputMapping#getAssignationType()
     * @see #getInputMapping()
     * @generated
     */
	EAttribute getInputMapping_AssignationType();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.process.IntegerType <em>Integer Type</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Integer Type</em>'.
     * @see org.bonitasoft.studio.model.process.IntegerType
     * @generated
     */
	EClass getIntegerType();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.process.IntermediateErrorCatchEvent <em>Intermediate Error Catch Event</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Intermediate Error Catch Event</em>'.
     * @see org.bonitasoft.studio.model.process.IntermediateErrorCatchEvent
     * @generated
     */
	EClass getIntermediateErrorCatchEvent();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.process.IntermediateCatchSignalEvent <em>Intermediate Catch Signal Event</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Intermediate Catch Signal Event</em>'.
     * @see org.bonitasoft.studio.model.process.IntermediateCatchSignalEvent
     * @generated
     */
	EClass getIntermediateCatchSignalEvent();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.process.IntermediateThrowSignalEvent <em>Intermediate Throw Signal Event</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Intermediate Throw Signal Event</em>'.
     * @see org.bonitasoft.studio.model.process.IntermediateThrowSignalEvent
     * @generated
     */
	EClass getIntermediateThrowSignalEvent();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.process.IntermediateCatchMessageEvent <em>Intermediate Catch Message Event</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Intermediate Catch Message Event</em>'.
     * @see org.bonitasoft.studio.model.process.IntermediateCatchMessageEvent
     * @generated
     */
	EClass getIntermediateCatchMessageEvent();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.process.IntermediateThrowMessageEvent <em>Intermediate Throw Message Event</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Intermediate Throw Message Event</em>'.
     * @see org.bonitasoft.studio.model.process.IntermediateThrowMessageEvent
     * @generated
     */
	EClass getIntermediateThrowMessageEvent();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.process.IntermediateCatchTimerEvent <em>Intermediate Catch Timer Event</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Intermediate Catch Timer Event</em>'.
     * @see org.bonitasoft.studio.model.process.IntermediateCatchTimerEvent
     * @generated
     */
	EClass getIntermediateCatchTimerEvent();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.process.JavaObjectData <em>Java Object Data</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Java Object Data</em>'.
     * @see org.bonitasoft.studio.model.process.JavaObjectData
     * @generated
     */
	EClass getJavaObjectData();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.process.JavaObjectData#getClassName <em>Class Name</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Class Name</em>'.
     * @see org.bonitasoft.studio.model.process.JavaObjectData#getClassName()
     * @see #getJavaObjectData()
     * @generated
     */
	EAttribute getJavaObjectData_ClassName();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.process.JavaType <em>Java Type</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Java Type</em>'.
     * @see org.bonitasoft.studio.model.process.JavaType
     * @generated
     */
	EClass getJavaType();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.process.Lane <em>Lane</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Lane</em>'.
     * @see org.bonitasoft.studio.model.process.Lane
     * @generated
     */
	EClass getLane();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.process.LinkEvent <em>Link Event</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Link Event</em>'.
     * @see org.bonitasoft.studio.model.process.LinkEvent
     * @generated
     */
	EClass getLinkEvent();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.process.LongType <em>Long Type</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Long Type</em>'.
     * @see org.bonitasoft.studio.model.process.LongType
     * @generated
     */
	EClass getLongType();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.process.MainProcess <em>Main Process</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Main Process</em>'.
     * @see org.bonitasoft.studio.model.process.MainProcess
     * @generated
     */
	EClass getMainProcess();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.process.MainProcess#getBonitaVersion <em>Bonita Version</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Bonita Version</em>'.
     * @see org.bonitasoft.studio.model.process.MainProcess#getBonitaVersion()
     * @see #getMainProcess()
     * @generated
     */
	EAttribute getMainProcess_BonitaVersion();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.process.MainProcess#getBonitaModelVersion <em>Bonita Model Version</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Bonita Model Version</em>'.
     * @see org.bonitasoft.studio.model.process.MainProcess#getBonitaModelVersion()
     * @see #getMainProcess()
     * @generated
     */
	EAttribute getMainProcess_BonitaModelVersion();

	/**
     * Returns the meta object for the attribute list '{@link org.bonitasoft.studio.model.process.MainProcess#getIncludedEntries <em>Included Entries</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute list '<em>Included Entries</em>'.
     * @see org.bonitasoft.studio.model.process.MainProcess#getIncludedEntries()
     * @see #getMainProcess()
     * @generated
     */
	EAttribute getMainProcess_IncludedEntries();

	/**
     * Returns the meta object for the containment reference list '{@link org.bonitasoft.studio.model.process.MainProcess#getMessageConnections <em>Message Connections</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Message Connections</em>'.
     * @see org.bonitasoft.studio.model.process.MainProcess#getMessageConnections()
     * @see #getMainProcess()
     * @generated
     */
	EReference getMainProcess_MessageConnections();

	/**
     * Returns the meta object for the attribute list '{@link org.bonitasoft.studio.model.process.MainProcess#getGeneratedLibs <em>Generated Libs</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute list '<em>Generated Libs</em>'.
     * @see org.bonitasoft.studio.model.process.MainProcess#getGeneratedLibs()
     * @see #getMainProcess()
     * @generated
     */
	EAttribute getMainProcess_GeneratedLibs();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.process.MainProcess#isEnableValidation <em>Enable Validation</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Enable Validation</em>'.
     * @see org.bonitasoft.studio.model.process.MainProcess#isEnableValidation()
     * @see #getMainProcess()
     * @generated
     */
	EAttribute getMainProcess_EnableValidation();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.process.MainProcess#getConfigId <em>Config Id</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Config Id</em>'.
     * @see org.bonitasoft.studio.model.process.MainProcess#getConfigId()
     * @see #getMainProcess()
     * @generated
     */
	EAttribute getMainProcess_ConfigId();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.process.Message <em>Message</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Message</em>'.
     * @see org.bonitasoft.studio.model.process.Message
     * @generated
     */
	EClass getMessage();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.process.Message#getThrowEvent <em>Throw Event</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Throw Event</em>'.
     * @see org.bonitasoft.studio.model.process.Message#getThrowEvent()
     * @see #getMessage()
     * @generated
     */
	EAttribute getMessage_ThrowEvent();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.process.Message#getTtl <em>Ttl</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Ttl</em>'.
     * @see org.bonitasoft.studio.model.process.Message#getTtl()
     * @see #getMessage()
     * @generated
     */
	EAttribute getMessage_Ttl();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.model.process.Message#getCorrelation <em>Correlation</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Correlation</em>'.
     * @see org.bonitasoft.studio.model.process.Message#getCorrelation()
     * @see #getMessage()
     * @generated
     */
	EReference getMessage_Correlation();

	/**
     * Returns the meta object for the container reference '{@link org.bonitasoft.studio.model.process.Message#getSource <em>Source</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Source</em>'.
     * @see org.bonitasoft.studio.model.process.Message#getSource()
     * @see #getMessage()
     * @generated
     */
	EReference getMessage_Source();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.model.process.Message#getTargetProcessExpression <em>Target Process Expression</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Target Process Expression</em>'.
     * @see org.bonitasoft.studio.model.process.Message#getTargetProcessExpression()
     * @see #getMessage()
     * @generated
     */
	EReference getMessage_TargetProcessExpression();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.model.process.Message#getTargetElementExpression <em>Target Element Expression</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Target Element Expression</em>'.
     * @see org.bonitasoft.studio.model.process.Message#getTargetElementExpression()
     * @see #getMessage()
     * @generated
     */
	EReference getMessage_TargetElementExpression();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.model.process.Message#getMessageContent <em>Message Content</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Message Content</em>'.
     * @see org.bonitasoft.studio.model.process.Message#getMessageContent()
     * @see #getMessage()
     * @generated
     */
	EReference getMessage_MessageContent();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.process.MessageFlow <em>Message Flow</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Message Flow</em>'.
     * @see org.bonitasoft.studio.model.process.MessageFlow
     * @generated
     */
	EClass getMessageFlow();

	/**
     * Returns the meta object for the reference '{@link org.bonitasoft.studio.model.process.MessageFlow#getTarget <em>Target</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Target</em>'.
     * @see org.bonitasoft.studio.model.process.MessageFlow#getTarget()
     * @see #getMessageFlow()
     * @generated
     */
	EReference getMessageFlow_Target();

	/**
     * Returns the meta object for the reference '{@link org.bonitasoft.studio.model.process.MessageFlow#getSource <em>Source</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Source</em>'.
     * @see org.bonitasoft.studio.model.process.MessageFlow#getSource()
     * @see #getMessageFlow()
     * @generated
     */
	EReference getMessageFlow_Source();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.process.MessageEvent <em>Message Event</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Message Event</em>'.
     * @see org.bonitasoft.studio.model.process.MessageEvent
     * @generated
     */
	EClass getMessageEvent();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.process.MultiInstantiable <em>Multi Instantiable</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Multi Instantiable</em>'.
     * @see org.bonitasoft.studio.model.process.MultiInstantiable
     * @generated
     */
	EClass getMultiInstantiable();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.process.MultiInstantiable#getType <em>Type</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Type</em>'.
     * @see org.bonitasoft.studio.model.process.MultiInstantiable#getType()
     * @see #getMultiInstantiable()
     * @generated
     */
	EAttribute getMultiInstantiable_Type();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.process.MultiInstantiable#getTestBefore <em>Test Before</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Test Before</em>'.
     * @see org.bonitasoft.studio.model.process.MultiInstantiable#getTestBefore()
     * @see #getMultiInstantiable()
     * @generated
     */
	EAttribute getMultiInstantiable_TestBefore();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.model.process.MultiInstantiable#getLoopCondition <em>Loop Condition</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Loop Condition</em>'.
     * @see org.bonitasoft.studio.model.process.MultiInstantiable#getLoopCondition()
     * @see #getMultiInstantiable()
     * @generated
     */
	EReference getMultiInstantiable_LoopCondition();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.model.process.MultiInstantiable#getLoopMaximum <em>Loop Maximum</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Loop Maximum</em>'.
     * @see org.bonitasoft.studio.model.process.MultiInstantiable#getLoopMaximum()
     * @see #getMultiInstantiable()
     * @generated
     */
	EReference getMultiInstantiable_LoopMaximum();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.process.MultiInstantiable#isUseCardinality <em>Use Cardinality</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Use Cardinality</em>'.
     * @see org.bonitasoft.studio.model.process.MultiInstantiable#isUseCardinality()
     * @see #getMultiInstantiable()
     * @generated
     */
	EAttribute getMultiInstantiable_UseCardinality();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.model.process.MultiInstantiable#getCardinalityExpression <em>Cardinality Expression</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Cardinality Expression</em>'.
     * @see org.bonitasoft.studio.model.process.MultiInstantiable#getCardinalityExpression()
     * @see #getMultiInstantiable()
     * @generated
     */
	EReference getMultiInstantiable_CardinalityExpression();

	/**
     * Returns the meta object for the reference '{@link org.bonitasoft.studio.model.process.MultiInstantiable#getCollectionDataToMultiInstantiate <em>Collection Data To Multi Instantiate</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Collection Data To Multi Instantiate</em>'.
     * @see org.bonitasoft.studio.model.process.MultiInstantiable#getCollectionDataToMultiInstantiate()
     * @see #getMultiInstantiable()
     * @generated
     */
	EReference getMultiInstantiable_CollectionDataToMultiInstantiate();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.model.process.MultiInstantiable#getIteratorExpression <em>Iterator Expression</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Iterator Expression</em>'.
     * @see org.bonitasoft.studio.model.process.MultiInstantiable#getIteratorExpression()
     * @see #getMultiInstantiable()
     * @generated
     */
	EReference getMultiInstantiable_IteratorExpression();

	/**
     * Returns the meta object for the reference '{@link org.bonitasoft.studio.model.process.MultiInstantiable#getOutputData <em>Output Data</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Output Data</em>'.
     * @see org.bonitasoft.studio.model.process.MultiInstantiable#getOutputData()
     * @see #getMultiInstantiable()
     * @generated
     */
	EReference getMultiInstantiable_OutputData();

	/**
     * Returns the meta object for the reference '{@link org.bonitasoft.studio.model.process.MultiInstantiable#getListDataContainingOutputResults <em>List Data Containing Output Results</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>List Data Containing Output Results</em>'.
     * @see org.bonitasoft.studio.model.process.MultiInstantiable#getListDataContainingOutputResults()
     * @see #getMultiInstantiable()
     * @generated
     */
	EReference getMultiInstantiable_ListDataContainingOutputResults();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.model.process.MultiInstantiable#getCompletionCondition <em>Completion Condition</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Completion Condition</em>'.
     * @see org.bonitasoft.studio.model.process.MultiInstantiable#getCompletionCondition()
     * @see #getMultiInstantiable()
     * @generated
     */
	EReference getMultiInstantiable_CompletionCondition();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.process.MultiInstantiable#isStoreOutput <em>Store Output</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Store Output</em>'.
     * @see org.bonitasoft.studio.model.process.MultiInstantiable#isStoreOutput()
     * @see #getMultiInstantiable()
     * @generated
     */
	EAttribute getMultiInstantiable_StoreOutput();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.process.NonInterruptingBoundaryTimerEvent <em>Non Interrupting Boundary Timer Event</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Non Interrupting Boundary Timer Event</em>'.
     * @see org.bonitasoft.studio.model.process.NonInterruptingBoundaryTimerEvent
     * @generated
     */
	EClass getNonInterruptingBoundaryTimerEvent();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.process.OperationContainer <em>Operation Container</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Operation Container</em>'.
     * @see org.bonitasoft.studio.model.process.OperationContainer
     * @generated
     */
	EClass getOperationContainer();

	/**
     * Returns the meta object for the containment reference list '{@link org.bonitasoft.studio.model.process.OperationContainer#getOperations <em>Operations</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Operations</em>'.
     * @see org.bonitasoft.studio.model.process.OperationContainer#getOperations()
     * @see #getOperationContainer()
     * @generated
     */
	EReference getOperationContainer_Operations();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.process.OutputMapping <em>Output Mapping</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Output Mapping</em>'.
     * @see org.bonitasoft.studio.model.process.OutputMapping
     * @generated
     */
	EClass getOutputMapping();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.process.OutputMapping#getSubprocessSource <em>Subprocess Source</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Subprocess Source</em>'.
     * @see org.bonitasoft.studio.model.process.OutputMapping#getSubprocessSource()
     * @see #getOutputMapping()
     * @generated
     */
	EAttribute getOutputMapping_SubprocessSource();

	/**
     * Returns the meta object for the reference '{@link org.bonitasoft.studio.model.process.OutputMapping#getProcessTarget <em>Process Target</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Process Target</em>'.
     * @see org.bonitasoft.studio.model.process.OutputMapping#getProcessTarget()
     * @see #getOutputMapping()
     * @generated
     */
	EReference getOutputMapping_ProcessTarget();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.process.PageFlow <em>Page Flow</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Page Flow</em>'.
     * @see org.bonitasoft.studio.model.process.PageFlow
     * @generated
     */
	EClass getPageFlow();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.model.process.PageFlow#getFormMapping <em>Form Mapping</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Form Mapping</em>'.
     * @see org.bonitasoft.studio.model.process.PageFlow#getFormMapping()
     * @see #getPageFlow()
     * @generated
     */
	EReference getPageFlow_FormMapping();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.process.Pool <em>Pool</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Pool</em>'.
     * @see org.bonitasoft.studio.model.process.Pool
     * @generated
     */
	EClass getPool();

	/**
     * Returns the meta object for the containment reference list '{@link org.bonitasoft.studio.model.process.Pool#getDocuments <em>Documents</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Documents</em>'.
     * @see org.bonitasoft.studio.model.process.Pool#getDocuments()
     * @see #getPool()
     * @generated
     */
	EReference getPool_Documents();

	/**
     * Returns the meta object for the containment reference list '{@link org.bonitasoft.studio.model.process.Pool#getSearchIndexes <em>Search Indexes</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Search Indexes</em>'.
     * @see org.bonitasoft.studio.model.process.Pool#getSearchIndexes()
     * @see #getPool()
     * @generated
     */
	EReference getPool_SearchIndexes();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.process.Pool#getDisplayName <em>Display Name</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Display Name</em>'.
     * @see org.bonitasoft.studio.model.process.Pool#getDisplayName()
     * @see #getPool()
     * @generated
     */
	EAttribute getPool_DisplayName();

	/**
     * Returns the meta object for the containment reference list '{@link org.bonitasoft.studio.model.process.Pool#getAdditionalResources <em>Additional Resources</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Additional Resources</em>'.
     * @see org.bonitasoft.studio.model.process.Pool#getAdditionalResources()
     * @see #getPool()
     * @generated
     */
    EReference getPool_AdditionalResources();

    /**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.process.RecapFlow <em>Recap Flow</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Recap Flow</em>'.
     * @see org.bonitasoft.studio.model.process.RecapFlow
     * @generated
     */
	EClass getRecapFlow();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.model.process.RecapFlow#getOverviewFormMapping <em>Overview Form Mapping</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Overview Form Mapping</em>'.
     * @see org.bonitasoft.studio.model.process.RecapFlow#getOverviewFormMapping()
     * @see #getRecapFlow()
     * @generated
     */
	EReference getRecapFlow_OverviewFormMapping();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.process.ReceiveTask <em>Receive Task</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Receive Task</em>'.
     * @see org.bonitasoft.studio.model.process.ReceiveTask
     * @generated
     */
	EClass getReceiveTask();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.process.SequenceFlow <em>Sequence Flow</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Sequence Flow</em>'.
     * @see org.bonitasoft.studio.model.process.SequenceFlow
     * @generated
     */
	EClass getSequenceFlow();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.process.SequenceFlow#isIsDefault <em>Is Default</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Is Default</em>'.
     * @see org.bonitasoft.studio.model.process.SequenceFlow#isIsDefault()
     * @see #getSequenceFlow()
     * @generated
     */
	EAttribute getSequenceFlow_IsDefault();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.process.SequenceFlow#getConditionType <em>Condition Type</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Condition Type</em>'.
     * @see org.bonitasoft.studio.model.process.SequenceFlow#getConditionType()
     * @see #getSequenceFlow()
     * @generated
     */
	EAttribute getSequenceFlow_ConditionType();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.model.process.SequenceFlow#getDecisionTable <em>Decision Table</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Decision Table</em>'.
     * @see org.bonitasoft.studio.model.process.SequenceFlow#getDecisionTable()
     * @see #getSequenceFlow()
     * @generated
     */
	EReference getSequenceFlow_DecisionTable();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.model.process.SequenceFlow#getCondition <em>Condition</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Condition</em>'.
     * @see org.bonitasoft.studio.model.process.SequenceFlow#getCondition()
     * @see #getSequenceFlow()
     * @generated
     */
	EReference getSequenceFlow_Condition();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.process.SequenceFlow#getPathToken <em>Path Token</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Path Token</em>'.
     * @see org.bonitasoft.studio.model.process.SequenceFlow#getPathToken()
     * @see #getSequenceFlow()
     * @generated
     */
	EAttribute getSequenceFlow_PathToken();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.process.SignalEvent <em>Signal Event</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Signal Event</em>'.
     * @see org.bonitasoft.studio.model.process.SignalEvent
     * @generated
     */
	EClass getSignalEvent();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.process.SignalEvent#getSignalCode <em>Signal Code</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Signal Code</em>'.
     * @see org.bonitasoft.studio.model.process.SignalEvent#getSignalCode()
     * @see #getSignalEvent()
     * @generated
     */
	EAttribute getSignalEvent_SignalCode();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.process.SourceElement <em>Source Element</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Source Element</em>'.
     * @see org.bonitasoft.studio.model.process.SourceElement
     * @generated
     */
	EClass getSourceElement();

	/**
     * Returns the meta object for the reference list '{@link org.bonitasoft.studio.model.process.SourceElement#getOutgoing <em>Outgoing</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Outgoing</em>'.
     * @see org.bonitasoft.studio.model.process.SourceElement#getOutgoing()
     * @see #getSourceElement()
     * @generated
     */
	EReference getSourceElement_Outgoing();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.process.StringType <em>String Type</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>String Type</em>'.
     * @see org.bonitasoft.studio.model.process.StringType
     * @generated
     */
	EClass getStringType();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.process.ScriptTask <em>Script Task</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Script Task</em>'.
     * @see org.bonitasoft.studio.model.process.ScriptTask
     * @generated
     */
	EClass getScriptTask();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.process.SearchIndex <em>Search Index</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Search Index</em>'.
     * @see org.bonitasoft.studio.model.process.SearchIndex
     * @generated
     */
	EClass getSearchIndex();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.model.process.SearchIndex#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Name</em>'.
     * @see org.bonitasoft.studio.model.process.SearchIndex#getName()
     * @see #getSearchIndex()
     * @generated
     */
	EReference getSearchIndex_Name();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.model.process.SearchIndex#getValue <em>Value</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Value</em>'.
     * @see org.bonitasoft.studio.model.process.SearchIndex#getValue()
     * @see #getSearchIndex()
     * @generated
     */
	EReference getSearchIndex_Value();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.process.SendTask <em>Send Task</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Send Task</em>'.
     * @see org.bonitasoft.studio.model.process.SendTask
     * @generated
     */
	EClass getSendTask();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.process.ServiceTask <em>Service Task</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Service Task</em>'.
     * @see org.bonitasoft.studio.model.process.ServiceTask
     * @generated
     */
	EClass getServiceTask();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.process.StartErrorEvent <em>Start Error Event</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Start Error Event</em>'.
     * @see org.bonitasoft.studio.model.process.StartErrorEvent
     * @generated
     */
	EClass getStartErrorEvent();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.process.StartEvent <em>Start Event</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Start Event</em>'.
     * @see org.bonitasoft.studio.model.process.StartEvent
     * @generated
     */
	EClass getStartEvent();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.process.StartMessageEvent <em>Start Message Event</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Start Message Event</em>'.
     * @see org.bonitasoft.studio.model.process.StartMessageEvent
     * @generated
     */
	EClass getStartMessageEvent();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.process.StartSignalEvent <em>Start Signal Event</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Start Signal Event</em>'.
     * @see org.bonitasoft.studio.model.process.StartSignalEvent
     * @generated
     */
	EClass getStartSignalEvent();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.process.StartTimerEvent <em>Start Timer Event</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Start Timer Event</em>'.
     * @see org.bonitasoft.studio.model.process.StartTimerEvent
     * @generated
     */
	EClass getStartTimerEvent();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.process.StartTimerEvent#getFrom <em>From</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>From</em>'.
     * @see org.bonitasoft.studio.model.process.StartTimerEvent#getFrom()
     * @see #getStartTimerEvent()
     * @generated
     */
	EAttribute getStartTimerEvent_From();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.process.StartTimerEvent#getAt <em>At</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>At</em>'.
     * @see org.bonitasoft.studio.model.process.StartTimerEvent#getAt()
     * @see #getStartTimerEvent()
     * @generated
     */
	EAttribute getStartTimerEvent_At();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.process.StartTimerEvent#getMonth <em>Month</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Month</em>'.
     * @see org.bonitasoft.studio.model.process.StartTimerEvent#getMonth()
     * @see #getStartTimerEvent()
     * @generated
     */
	EAttribute getStartTimerEvent_Month();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.process.StartTimerEvent#getDay <em>Day</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Day</em>'.
     * @see org.bonitasoft.studio.model.process.StartTimerEvent#getDay()
     * @see #getStartTimerEvent()
     * @generated
     */
	EAttribute getStartTimerEvent_Day();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.process.StartTimerEvent#getHours <em>Hours</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Hours</em>'.
     * @see org.bonitasoft.studio.model.process.StartTimerEvent#getHours()
     * @see #getStartTimerEvent()
     * @generated
     */
	EAttribute getStartTimerEvent_Hours();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.process.StartTimerEvent#getDayNumber <em>Day Number</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Day Number</em>'.
     * @see org.bonitasoft.studio.model.process.StartTimerEvent#getDayNumber()
     * @see #getStartTimerEvent()
     * @generated
     */
	EAttribute getStartTimerEvent_DayNumber();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.process.StartTimerEvent#getMinutes <em>Minutes</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Minutes</em>'.
     * @see org.bonitasoft.studio.model.process.StartTimerEvent#getMinutes()
     * @see #getStartTimerEvent()
     * @generated
     */
	EAttribute getStartTimerEvent_Minutes();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.process.StartTimerEvent#getSeconds <em>Seconds</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Seconds</em>'.
     * @see org.bonitasoft.studio.model.process.StartTimerEvent#getSeconds()
     * @see #getStartTimerEvent()
     * @generated
     */
	EAttribute getStartTimerEvent_Seconds();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.process.StartTimerEvent#getScriptType <em>Script Type</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Script Type</em>'.
     * @see org.bonitasoft.studio.model.process.StartTimerEvent#getScriptType()
     * @see #getStartTimerEvent()
     * @generated
     */
	EAttribute getStartTimerEvent_ScriptType();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.process.SubProcessEvent <em>Sub Process Event</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Sub Process Event</em>'.
     * @see org.bonitasoft.studio.model.process.SubProcessEvent
     * @generated
     */
	EClass getSubProcessEvent();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.process.Task <em>Task</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Task</em>'.
     * @see org.bonitasoft.studio.model.process.Task
     * @generated
     */
	EClass getTask();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.process.Task#isOverrideActorsOfTheLane <em>Override Actors Of The Lane</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Override Actors Of The Lane</em>'.
     * @see org.bonitasoft.studio.model.process.Task#isOverrideActorsOfTheLane()
     * @see #getTask()
     * @generated
     */
	EAttribute getTask_OverrideActorsOfTheLane();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.process.Task#getPriority <em>Priority</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Priority</em>'.
     * @see org.bonitasoft.studio.model.process.Task#getPriority()
     * @see #getTask()
     * @generated
     */
	EAttribute getTask_Priority();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.model.process.Task#getExpectedDuration <em>Expected Duration</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Expected Duration</em>'.
     * @see org.bonitasoft.studio.model.process.Task#getExpectedDuration()
     * @see #getTask()
     * @generated
     */
	EReference getTask_ExpectedDuration();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.process.TargetElement <em>Target Element</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Target Element</em>'.
     * @see org.bonitasoft.studio.model.process.TargetElement
     * @generated
     */
	EClass getTargetElement();

	/**
     * Returns the meta object for the reference list '{@link org.bonitasoft.studio.model.process.TargetElement#getIncoming <em>Incoming</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Incoming</em>'.
     * @see org.bonitasoft.studio.model.process.TargetElement#getIncoming()
     * @see #getTargetElement()
     * @generated
     */
	EReference getTargetElement_Incoming();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.process.TextAnnotation <em>Text Annotation</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Text Annotation</em>'.
     * @see org.bonitasoft.studio.model.process.TextAnnotation
     * @generated
     */
	EClass getTextAnnotation();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.process.TextAnnotation#getText <em>Text</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Text</em>'.
     * @see org.bonitasoft.studio.model.process.TextAnnotation#getText()
     * @see #getTextAnnotation()
     * @generated
     */
	EAttribute getTextAnnotation_Text();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.process.TextAnnotationAttachment <em>Text Annotation Attachment</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Text Annotation Attachment</em>'.
     * @see org.bonitasoft.studio.model.process.TextAnnotationAttachment
     * @generated
     */
	EClass getTextAnnotationAttachment();

	/**
     * Returns the meta object for the reference '{@link org.bonitasoft.studio.model.process.TextAnnotationAttachment#getSource <em>Source</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Source</em>'.
     * @see org.bonitasoft.studio.model.process.TextAnnotationAttachment#getSource()
     * @see #getTextAnnotationAttachment()
     * @generated
     */
	EReference getTextAnnotationAttachment_Source();

	/**
     * Returns the meta object for the container reference '{@link org.bonitasoft.studio.model.process.TextAnnotationAttachment#getTarget <em>Target</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Target</em>'.
     * @see org.bonitasoft.studio.model.process.TextAnnotationAttachment#getTarget()
     * @see #getTextAnnotationAttachment()
     * @generated
     */
	EReference getTextAnnotationAttachment_Target();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.process.ThrowSignalEvent <em>Throw Signal Event</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Throw Signal Event</em>'.
     * @see org.bonitasoft.studio.model.process.ThrowSignalEvent
     * @generated
     */
	EClass getThrowSignalEvent();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.process.ThrowLinkEvent <em>Throw Link Event</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Throw Link Event</em>'.
     * @see org.bonitasoft.studio.model.process.ThrowLinkEvent
     * @generated
     */
	EClass getThrowLinkEvent();

	/**
     * Returns the meta object for the reference '{@link org.bonitasoft.studio.model.process.ThrowLinkEvent#getTo <em>To</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>To</em>'.
     * @see org.bonitasoft.studio.model.process.ThrowLinkEvent#getTo()
     * @see #getThrowLinkEvent()
     * @generated
     */
	EReference getThrowLinkEvent_To();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.process.ThrowMessageEvent <em>Throw Message Event</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Throw Message Event</em>'.
     * @see org.bonitasoft.studio.model.process.ThrowMessageEvent
     * @generated
     */
	EClass getThrowMessageEvent();

	/**
     * Returns the meta object for the containment reference list '{@link org.bonitasoft.studio.model.process.ThrowMessageEvent#getEvents <em>Events</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Events</em>'.
     * @see org.bonitasoft.studio.model.process.ThrowMessageEvent#getEvents()
     * @see #getThrowMessageEvent()
     * @generated
     */
	EReference getThrowMessageEvent_Events();

	/**
     * Returns the meta object for the reference list '{@link org.bonitasoft.studio.model.process.ThrowMessageEvent#getOutgoingMessages <em>Outgoing Messages</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Outgoing Messages</em>'.
     * @see org.bonitasoft.studio.model.process.ThrowMessageEvent#getOutgoingMessages()
     * @see #getThrowMessageEvent()
     * @generated
     */
	EReference getThrowMessageEvent_OutgoingMessages();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.process.TimerEvent <em>Timer Event</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Timer Event</em>'.
     * @see org.bonitasoft.studio.model.process.TimerEvent
     * @generated
     */
	EClass getTimerEvent();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.process.XMLData <em>XML Data</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>XML Data</em>'.
     * @see org.bonitasoft.studio.model.process.XMLData
     * @generated
     */
	EClass getXMLData();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.process.XMLData#getNamespace <em>Namespace</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Namespace</em>'.
     * @see org.bonitasoft.studio.model.process.XMLData#getNamespace()
     * @see #getXMLData()
     * @generated
     */
	EAttribute getXMLData_Namespace();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.process.XMLData#getType <em>Type</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Type</em>'.
     * @see org.bonitasoft.studio.model.process.XMLData#getType()
     * @see #getXMLData()
     * @generated
     */
	EAttribute getXMLData_Type();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.process.XMLType <em>XML Type</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>XML Type</em>'.
     * @see org.bonitasoft.studio.model.process.XMLType
     * @generated
     */
	EClass getXMLType();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.process.XORGateway <em>XOR Gateway</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>XOR Gateway</em>'.
     * @see org.bonitasoft.studio.model.process.XORGateway
     * @generated
     */
	EClass getXORGateway();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.process.AdditionalResource <em>Additional Resource</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Additional Resource</em>'.
     * @see org.bonitasoft.studio.model.process.AdditionalResource
     * @generated
     */
    EClass getAdditionalResource();

    /**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.process.AdditionalResource#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.bonitasoft.studio.model.process.AdditionalResource#getName()
     * @see #getAdditionalResource()
     * @generated
     */
    EAttribute getAdditionalResource_Name();

    /**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.process.AdditionalResource#getDescription <em>Description</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Description</em>'.
     * @see org.bonitasoft.studio.model.process.AdditionalResource#getDescription()
     * @see #getAdditionalResource()
     * @generated
     */
    EAttribute getAdditionalResource_Description();

    /**
     * Returns the meta object for enum '{@link org.bonitasoft.studio.model.process.CorrelationTypeActive <em>Correlation Type Active</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for enum '<em>Correlation Type Active</em>'.
     * @see org.bonitasoft.studio.model.process.CorrelationTypeActive
     * @generated
     */
	EEnum getCorrelationTypeActive();

	/**
     * Returns the meta object for enum '{@link org.bonitasoft.studio.model.process.ContractInputType <em>Contract Input Type</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for enum '<em>Contract Input Type</em>'.
     * @see org.bonitasoft.studio.model.process.ContractInputType
     * @generated
     */
	EEnum getContractInputType();

	/**
     * Returns the meta object for enum '{@link org.bonitasoft.studio.model.process.DocumentType <em>Document Type</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for enum '<em>Document Type</em>'.
     * @see org.bonitasoft.studio.model.process.DocumentType
     * @generated
     */
	EEnum getDocumentType();

	/**
     * Returns the meta object for enum '{@link org.bonitasoft.studio.model.process.FormMappingType <em>Form Mapping Type</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for enum '<em>Form Mapping Type</em>'.
     * @see org.bonitasoft.studio.model.process.FormMappingType
     * @generated
     */
	EEnum getFormMappingType();

	/**
     * Returns the meta object for enum '{@link org.bonitasoft.studio.model.process.GatewayType <em>Gateway Type</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for enum '<em>Gateway Type</em>'.
     * @see org.bonitasoft.studio.model.process.GatewayType
     * @generated
     */
	EEnum getGatewayType();

	/**
     * Returns the meta object for enum '{@link org.bonitasoft.studio.model.process.InputMappingAssignationType <em>Input Mapping Assignation Type</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for enum '<em>Input Mapping Assignation Type</em>'.
     * @see org.bonitasoft.studio.model.process.InputMappingAssignationType
     * @generated
     */
	EEnum getInputMappingAssignationType();

	/**
     * Returns the meta object for enum '{@link org.bonitasoft.studio.model.process.MultiInstanceType <em>Multi Instance Type</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for enum '<em>Multi Instance Type</em>'.
     * @see org.bonitasoft.studio.model.process.MultiInstanceType
     * @generated
     */
	EEnum getMultiInstanceType();

	/**
     * Returns the meta object for enum '{@link org.bonitasoft.studio.model.process.SequenceFlowConditionType <em>Sequence Flow Condition Type</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for enum '<em>Sequence Flow Condition Type</em>'.
     * @see org.bonitasoft.studio.model.process.SequenceFlowConditionType
     * @generated
     */
	EEnum getSequenceFlowConditionType();

	/**
     * Returns the meta object for enum '{@link org.bonitasoft.studio.model.process.StartTimerScriptType <em>Start Timer Script Type</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for enum '<em>Start Timer Script Type</em>'.
     * @see org.bonitasoft.studio.model.process.StartTimerScriptType
     * @generated
     */
	EEnum getStartTimerScriptType();

	/**
     * Returns the factory that creates the instances of the model.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
	ProcessFactory getProcessFactory();

	/**
     * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
     * @generated
     */
	interface Literals {
		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.AbstractCatchMessageEvent <em>Abstract Catch Message Event</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.AbstractCatchMessageEvent
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getAbstractCatchMessageEvent()
         * @generated
         */
		EClass ABSTRACT_CATCH_MESSAGE_EVENT = eINSTANCE.getAbstractCatchMessageEvent();

		/**
         * The meta object literal for the '<em><b>Event</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute ABSTRACT_CATCH_MESSAGE_EVENT__EVENT = eINSTANCE.getAbstractCatchMessageEvent_Event();

		/**
         * The meta object literal for the '<em><b>Incoming Messag</b></em>' reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference ABSTRACT_CATCH_MESSAGE_EVENT__INCOMING_MESSAG = eINSTANCE.getAbstractCatchMessageEvent_IncomingMessag();

		/**
         * The meta object literal for the '<em><b>Correlation</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference ABSTRACT_CATCH_MESSAGE_EVENT__CORRELATION = eINSTANCE.getAbstractCatchMessageEvent_Correlation();

		/**
         * The meta object literal for the '<em><b>Message Content</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference ABSTRACT_CATCH_MESSAGE_EVENT__MESSAGE_CONTENT = eINSTANCE.getAbstractCatchMessageEvent_MessageContent();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.impl.AbstractPageFlowImpl <em>Abstract Page Flow</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.impl.AbstractPageFlowImpl
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getAbstractPageFlow()
         * @generated
         */
		EClass ABSTRACT_PAGE_FLOW = eINSTANCE.getAbstractPageFlow();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.impl.AbstractProcessImpl <em>Abstract Process</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.impl.AbstractProcessImpl
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getAbstractProcess()
         * @generated
         */
		EClass ABSTRACT_PROCESS = eINSTANCE.getAbstractProcess();

		/**
         * The meta object literal for the '<em><b>Version</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute ABSTRACT_PROCESS__VERSION = eINSTANCE.getAbstractProcess_Version();

		/**
         * The meta object literal for the '<em><b>Author</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute ABSTRACT_PROCESS__AUTHOR = eINSTANCE.getAbstractProcess_Author();

		/**
         * The meta object literal for the '<em><b>Creation Date</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute ABSTRACT_PROCESS__CREATION_DATE = eINSTANCE.getAbstractProcess_CreationDate();

		/**
         * The meta object literal for the '<em><b>Modification Date</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute ABSTRACT_PROCESS__MODIFICATION_DATE = eINSTANCE.getAbstractProcess_ModificationDate();

		/**
         * The meta object literal for the '<em><b>Datatypes</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference ABSTRACT_PROCESS__DATATYPES = eINSTANCE.getAbstractProcess_Datatypes();

		/**
         * The meta object literal for the '<em><b>Connections</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference ABSTRACT_PROCESS__CONNECTIONS = eINSTANCE.getAbstractProcess_Connections();

		/**
         * The meta object literal for the '<em><b>Categories</b></em>' attribute list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute ABSTRACT_PROCESS__CATEGORIES = eINSTANCE.getAbstractProcess_Categories();

		/**
         * The meta object literal for the '<em><b>Actors</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference ABSTRACT_PROCESS__ACTORS = eINSTANCE.getAbstractProcess_Actors();

		/**
         * The meta object literal for the '<em><b>Configurations</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference ABSTRACT_PROCESS__CONFIGURATIONS = eINSTANCE.getAbstractProcess_Configurations();

		/**
         * The meta object literal for the '<em><b>Parameters</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference ABSTRACT_PROCESS__PARAMETERS = eINSTANCE.getAbstractProcess_Parameters();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.AbstractTimerEvent <em>Abstract Timer Event</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.AbstractTimerEvent
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getAbstractTimerEvent()
         * @generated
         */
		EClass ABSTRACT_TIMER_EVENT = eINSTANCE.getAbstractTimerEvent();

		/**
         * The meta object literal for the '<em><b>Condition</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference ABSTRACT_TIMER_EVENT__CONDITION = eINSTANCE.getAbstractTimerEvent_Condition();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.impl.ActivityImpl <em>Activity</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.impl.ActivityImpl
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getActivity()
         * @generated
         */
		EClass ACTIVITY = eINSTANCE.getActivity();

		/**
         * The meta object literal for the '<em><b>Boundary Intermediate Events</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference ACTIVITY__BOUNDARY_INTERMEDIATE_EVENTS = eINSTANCE.getActivity_BoundaryIntermediateEvents();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.impl.ActorImpl <em>Actor</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.impl.ActorImpl
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getActor()
         * @generated
         */
		EClass ACTOR = eINSTANCE.getActor();

		/**
         * The meta object literal for the '<em><b>Initiator</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute ACTOR__INITIATOR = eINSTANCE.getActor_Initiator();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.impl.ActorFilterImpl <em>Actor Filter</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.impl.ActorFilterImpl
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getActorFilter()
         * @generated
         */
		EClass ACTOR_FILTER = eINSTANCE.getActorFilter();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.impl.ANDGatewayImpl <em>AND Gateway</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.impl.ANDGatewayImpl
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getANDGateway()
         * @generated
         */
		EClass AND_GATEWAY = eINSTANCE.getANDGateway();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.Assignable <em>Assignable</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.Assignable
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getAssignable()
         * @generated
         */
		EClass ASSIGNABLE = eINSTANCE.getAssignable();

		/**
         * The meta object literal for the '<em><b>Actor</b></em>' reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference ASSIGNABLE__ACTOR = eINSTANCE.getAssignable_Actor();

		/**
         * The meta object literal for the '<em><b>Filters</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference ASSIGNABLE__FILTERS = eINSTANCE.getAssignable_Filters();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.impl.AssociationImpl <em>Association</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.impl.AssociationImpl
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getAssociation()
         * @generated
         */
		EClass ASSOCIATION = eINSTANCE.getAssociation();

		/**
         * The meta object literal for the '<em><b>Is Directed</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute ASSOCIATION__IS_DIRECTED = eINSTANCE.getAssociation_IsDirected();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.impl.BooleanTypeImpl <em>Boolean Type</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.impl.BooleanTypeImpl
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getBooleanType()
         * @generated
         */
		EClass BOOLEAN_TYPE = eINSTANCE.getBooleanType();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.impl.BoundaryEventImpl <em>Boundary Event</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.impl.BoundaryEventImpl
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getBoundaryEvent()
         * @generated
         */
		EClass BOUNDARY_EVENT = eINSTANCE.getBoundaryEvent();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.impl.BoundaryMessageEventImpl <em>Boundary Message Event</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.impl.BoundaryMessageEventImpl
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getBoundaryMessageEvent()
         * @generated
         */
		EClass BOUNDARY_MESSAGE_EVENT = eINSTANCE.getBoundaryMessageEvent();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.impl.BoundarySignalEventImpl <em>Boundary Signal Event</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.impl.BoundarySignalEventImpl
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getBoundarySignalEvent()
         * @generated
         */
		EClass BOUNDARY_SIGNAL_EVENT = eINSTANCE.getBoundarySignalEvent();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.impl.BoundaryTimerEventImpl <em>Boundary Timer Event</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.impl.BoundaryTimerEventImpl
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getBoundaryTimerEvent()
         * @generated
         */
		EClass BOUNDARY_TIMER_EVENT = eINSTANCE.getBoundaryTimerEvent();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.impl.BusinessObjectDataImpl <em>Business Object Data</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.impl.BusinessObjectDataImpl
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getBusinessObjectData()
         * @generated
         */
		EClass BUSINESS_OBJECT_DATA = eINSTANCE.getBusinessObjectData();

		/**
         * The meta object literal for the '<em><b>Business Data Repository Id</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute BUSINESS_OBJECT_DATA__BUSINESS_DATA_REPOSITORY_ID = eINSTANCE.getBusinessObjectData_BusinessDataRepositoryId();

		/**
         * The meta object literal for the '<em><b>Create New Instance</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute BUSINESS_OBJECT_DATA__CREATE_NEW_INSTANCE = eINSTANCE.getBusinessObjectData_CreateNewInstance();

		/**
         * The meta object literal for the '<em><b>EClass Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute BUSINESS_OBJECT_DATA__ECLASS_NAME = eINSTANCE.getBusinessObjectData_EClassName();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.impl.BusinessObjectTypeImpl <em>Business Object Type</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.impl.BusinessObjectTypeImpl
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getBusinessObjectType()
         * @generated
         */
		EClass BUSINESS_OBJECT_TYPE = eINSTANCE.getBusinessObjectType();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.impl.CallActivityImpl <em>Call Activity</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.impl.CallActivityImpl
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getCallActivity()
         * @generated
         */
		EClass CALL_ACTIVITY = eINSTANCE.getCallActivity();

		/**
         * The meta object literal for the '<em><b>Input Mappings</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference CALL_ACTIVITY__INPUT_MAPPINGS = eINSTANCE.getCallActivity_InputMappings();

		/**
         * The meta object literal for the '<em><b>Output Mappings</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference CALL_ACTIVITY__OUTPUT_MAPPINGS = eINSTANCE.getCallActivity_OutputMappings();

		/**
         * The meta object literal for the '<em><b>Called Activity Name</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference CALL_ACTIVITY__CALLED_ACTIVITY_NAME = eINSTANCE.getCallActivity_CalledActivityName();

		/**
         * The meta object literal for the '<em><b>Called Activity Version</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference CALL_ACTIVITY__CALLED_ACTIVITY_VERSION = eINSTANCE.getCallActivity_CalledActivityVersion();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.impl.CatchLinkEventImpl <em>Catch Link Event</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.impl.CatchLinkEventImpl
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getCatchLinkEvent()
         * @generated
         */
		EClass CATCH_LINK_EVENT = eINSTANCE.getCatchLinkEvent();

		/**
         * The meta object literal for the '<em><b>From</b></em>' reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference CATCH_LINK_EVENT__FROM = eINSTANCE.getCatchLinkEvent_From();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.CatchMessageEvent <em>Catch Message Event</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.CatchMessageEvent
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getCatchMessageEvent()
         * @generated
         */
		EClass CATCH_MESSAGE_EVENT = eINSTANCE.getCatchMessageEvent();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.CatchSignalEvent <em>Catch Signal Event</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.CatchSignalEvent
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getCatchSignalEvent()
         * @generated
         */
		EClass CATCH_SIGNAL_EVENT = eINSTANCE.getCatchSignalEvent();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.impl.ConnectableElementImpl <em>Connectable Element</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.impl.ConnectableElementImpl
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getConnectableElement()
         * @generated
         */
		EClass CONNECTABLE_ELEMENT = eINSTANCE.getConnectableElement();

		/**
         * The meta object literal for the '<em><b>Connectors</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference CONNECTABLE_ELEMENT__CONNECTORS = eINSTANCE.getConnectableElement_Connectors();

		/**
         * The meta object literal for the '<em><b>Kpis</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference CONNECTABLE_ELEMENT__KPIS = eINSTANCE.getConnectableElement_Kpis();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.impl.ConnectorImpl <em>Connector</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.impl.ConnectorImpl
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getConnector()
         * @generated
         */
		EClass CONNECTOR = eINSTANCE.getConnector();

		/**
         * The meta object literal for the '<em><b>Definition Id</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute CONNECTOR__DEFINITION_ID = eINSTANCE.getConnector_DefinitionId();

		/**
         * The meta object literal for the '<em><b>Event</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute CONNECTOR__EVENT = eINSTANCE.getConnector_Event();

		/**
         * The meta object literal for the '<em><b>Ignore Errors</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute CONNECTOR__IGNORE_ERRORS = eINSTANCE.getConnector_IgnoreErrors();

		/**
         * The meta object literal for the '<em><b>Throw Error Event</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute CONNECTOR__THROW_ERROR_EVENT = eINSTANCE.getConnector_ThrowErrorEvent();

		/**
         * The meta object literal for the '<em><b>Named Error</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute CONNECTOR__NAMED_ERROR = eINSTANCE.getConnector_NamedError();

		/**
         * The meta object literal for the '<em><b>Definition Version</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute CONNECTOR__DEFINITION_VERSION = eINSTANCE.getConnector_DefinitionVersion();

		/**
         * The meta object literal for the '<em><b>Configuration</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference CONNECTOR__CONFIGURATION = eINSTANCE.getConnector_Configuration();

		/**
         * The meta object literal for the '<em><b>Outputs</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference CONNECTOR__OUTPUTS = eINSTANCE.getConnector_Outputs();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.impl.ContainerImpl <em>Container</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.impl.ContainerImpl
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getContainer()
         * @generated
         */
		EClass CONTAINER = eINSTANCE.getContainer();

		/**
         * The meta object literal for the '<em><b>Elements</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference CONTAINER__ELEMENTS = eINSTANCE.getContainer_Elements();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.impl.ContractImpl <em>Contract</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.impl.ContractImpl
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getContract()
         * @generated
         */
		EClass CONTRACT = eINSTANCE.getContract();

		/**
         * The meta object literal for the '<em><b>Inputs</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference CONTRACT__INPUTS = eINSTANCE.getContract_Inputs();

		/**
         * The meta object literal for the '<em><b>Constraints</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference CONTRACT__CONSTRAINTS = eINSTANCE.getContract_Constraints();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.impl.ContractInputMappingImpl <em>Contract Input Mapping</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.impl.ContractInputMappingImpl
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getContractInputMapping()
         * @generated
         */
		EClass CONTRACT_INPUT_MAPPING = eINSTANCE.getContractInputMapping();

		/**
         * The meta object literal for the '<em><b>Data</b></em>' reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference CONTRACT_INPUT_MAPPING__DATA = eINSTANCE.getContractInputMapping_Data();

		/**
         * The meta object literal for the '<em><b>Setter Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute CONTRACT_INPUT_MAPPING__SETTER_NAME = eINSTANCE.getContractInputMapping_SetterName();

		/**
         * The meta object literal for the '<em><b>Setter Param Type</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute CONTRACT_INPUT_MAPPING__SETTER_PARAM_TYPE = eINSTANCE.getContractInputMapping_SetterParamType();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.impl.ContractInputImpl <em>Contract Input</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.impl.ContractInputImpl
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getContractInput()
         * @generated
         */
		EClass CONTRACT_INPUT = eINSTANCE.getContractInput();

		/**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute CONTRACT_INPUT__NAME = eINSTANCE.getContractInput_Name();

		/**
         * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute CONTRACT_INPUT__TYPE = eINSTANCE.getContractInput_Type();

		/**
         * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute CONTRACT_INPUT__DESCRIPTION = eINSTANCE.getContractInput_Description();

		/**
         * The meta object literal for the '<em><b>Multiple</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute CONTRACT_INPUT__MULTIPLE = eINSTANCE.getContractInput_Multiple();

		/**
         * The meta object literal for the '<em><b>Mapping</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference CONTRACT_INPUT__MAPPING = eINSTANCE.getContractInput_Mapping();

		/**
         * The meta object literal for the '<em><b>Inputs</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference CONTRACT_INPUT__INPUTS = eINSTANCE.getContractInput_Inputs();

		/**
         * The meta object literal for the '<em><b>Data Reference</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute CONTRACT_INPUT__DATA_REFERENCE = eINSTANCE.getContractInput_DataReference();

        /**
         * The meta object literal for the '<em><b>Create Mode</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute CONTRACT_INPUT__CREATE_MODE = eINSTANCE.getContractInput_CreateMode();

        /**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.impl.ContractConstraintImpl <em>Contract Constraint</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.impl.ContractConstraintImpl
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getContractConstraint()
         * @generated
         */
		EClass CONTRACT_CONSTRAINT = eINSTANCE.getContractConstraint();

		/**
         * The meta object literal for the '<em><b>Expression</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute CONTRACT_CONSTRAINT__EXPRESSION = eINSTANCE.getContractConstraint_Expression();

		/**
         * The meta object literal for the '<em><b>Error Message</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute CONTRACT_CONSTRAINT__ERROR_MESSAGE = eINSTANCE.getContractConstraint_ErrorMessage();

		/**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute CONTRACT_CONSTRAINT__NAME = eINSTANCE.getContractConstraint_Name();

		/**
         * The meta object literal for the '<em><b>Input Names</b></em>' attribute list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute CONTRACT_CONSTRAINT__INPUT_NAMES = eINSTANCE.getContractConstraint_InputNames();

		/**
         * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute CONTRACT_CONSTRAINT__DESCRIPTION = eINSTANCE.getContractConstraint_Description();

        /**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.impl.ContractContainerImpl <em>Contract Container</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.impl.ContractContainerImpl
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getContractContainer()
         * @generated
         */
		EClass CONTRACT_CONTAINER = eINSTANCE.getContractContainer();

		/**
         * The meta object literal for the '<em><b>Contract</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference CONTRACT_CONTAINER__CONTRACT = eINSTANCE.getContractContainer_Contract();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.impl.ConnectionImpl <em>Connection</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.impl.ConnectionImpl
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getConnection()
         * @generated
         */
		EClass CONNECTION = eINSTANCE.getConnection();

		/**
         * The meta object literal for the '<em><b>Target</b></em>' reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference CONNECTION__TARGET = eINSTANCE.getConnection_Target();

		/**
         * The meta object literal for the '<em><b>Source</b></em>' reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference CONNECTION__SOURCE = eINSTANCE.getConnection_Source();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.impl.CorrelationImpl <em>Correlation</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.impl.CorrelationImpl
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getCorrelation()
         * @generated
         */
		EClass CORRELATION = eINSTANCE.getCorrelation();

		/**
         * The meta object literal for the '<em><b>Correlation Type</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute CORRELATION__CORRELATION_TYPE = eINSTANCE.getCorrelation_CorrelationType();

		/**
         * The meta object literal for the '<em><b>Correlation Association</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference CORRELATION__CORRELATION_ASSOCIATION = eINSTANCE.getCorrelation_CorrelationAssociation();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.impl.CorrelationAssociationImpl <em>Correlation Association</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.impl.CorrelationAssociationImpl
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getCorrelationAssociation()
         * @generated
         */
		EClass CORRELATION_ASSOCIATION = eINSTANCE.getCorrelationAssociation();

		/**
         * The meta object literal for the '<em><b>Correlation Expression</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference CORRELATION_ASSOCIATION__CORRELATION_EXPRESSION = eINSTANCE.getCorrelationAssociation_CorrelationExpression();

		/**
         * The meta object literal for the '<em><b>Correlation Key</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference CORRELATION_ASSOCIATION__CORRELATION_KEY = eINSTANCE.getCorrelationAssociation_CorrelationKey();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.impl.DataImpl <em>Data</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.impl.DataImpl
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getData()
         * @generated
         */
		EClass DATA = eINSTANCE.getData();

		/**
         * The meta object literal for the '<em><b>Generated</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute DATA__GENERATED = eINSTANCE.getData_Generated();

		/**
         * The meta object literal for the '<em><b>Multiple</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute DATA__MULTIPLE = eINSTANCE.getData_Multiple();

		/**
         * The meta object literal for the '<em><b>Transient</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute DATA__TRANSIENT = eINSTANCE.getData_Transient();

		/**
         * The meta object literal for the '<em><b>Datasource Id</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute DATA__DATASOURCE_ID = eINSTANCE.getData_DatasourceId();

		/**
         * The meta object literal for the '<em><b>Data Type</b></em>' reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference DATA__DATA_TYPE = eINSTANCE.getData_DataType();

		/**
         * The meta object literal for the '<em><b>Default Value</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference DATA__DEFAULT_VALUE = eINSTANCE.getData_DefaultValue();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.impl.DataAwareImpl <em>Data Aware</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.impl.DataAwareImpl
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getDataAware()
         * @generated
         */
		EClass DATA_AWARE = eINSTANCE.getDataAware();

		/**
         * The meta object literal for the '<em><b>Data</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference DATA_AWARE__DATA = eINSTANCE.getDataAware_Data();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.impl.DateTypeImpl <em>Date Type</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.impl.DateTypeImpl
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getDateType()
         * @generated
         */
		EClass DATE_TYPE = eINSTANCE.getDateType();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.impl.DataTypeImpl <em>Data Type</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.impl.DataTypeImpl
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getDataType()
         * @generated
         */
		EClass DATA_TYPE = eINSTANCE.getDataType();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.impl.DocumentImpl <em>Document</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.impl.DocumentImpl
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getDocument()
         * @generated
         */
		EClass DOCUMENT = eINSTANCE.getDocument();

		/**
         * The meta object literal for the '<em><b>Default Value Id Of Document Store</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute DOCUMENT__DEFAULT_VALUE_ID_OF_DOCUMENT_STORE = eINSTANCE.getDocument_DefaultValueIdOfDocumentStore();

		/**
         * The meta object literal for the '<em><b>Mime Type</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference DOCUMENT__MIME_TYPE = eINSTANCE.getDocument_MimeType();

		/**
         * The meta object literal for the '<em><b>Url</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference DOCUMENT__URL = eINSTANCE.getDocument_Url();

		/**
         * The meta object literal for the '<em><b>Document Type</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute DOCUMENT__DOCUMENT_TYPE = eINSTANCE.getDocument_DocumentType();

		/**
         * The meta object literal for the '<em><b>Initial Multiple Content</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference DOCUMENT__INITIAL_MULTIPLE_CONTENT = eINSTANCE.getDocument_InitialMultipleContent();

		/**
         * The meta object literal for the '<em><b>Multiple</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute DOCUMENT__MULTIPLE = eINSTANCE.getDocument_Multiple();

		/**
         * The meta object literal for the '<em><b>Contract Input</b></em>' reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference DOCUMENT__CONTRACT_INPUT = eINSTANCE.getDocument_ContractInput();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.impl.DoubleTypeImpl <em>Double Type</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.impl.DoubleTypeImpl
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getDoubleType()
         * @generated
         */
		EClass DOUBLE_TYPE = eINSTANCE.getDoubleType();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.Element <em>Element</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.Element
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getElement()
         * @generated
         */
		EClass ELEMENT = eINSTANCE.getElement();

		/**
         * The meta object literal for the '<em><b>Documentation</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute ELEMENT__DOCUMENTATION = eINSTANCE.getElement_Documentation();

		/**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute ELEMENT__NAME = eINSTANCE.getElement_Name();

		/**
         * The meta object literal for the '<em><b>Text Annotation Attachment</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference ELEMENT__TEXT_ANNOTATION_ATTACHMENT = eINSTANCE.getElement_TextAnnotationAttachment();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.impl.EventImpl <em>Event</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.impl.EventImpl
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getEvent()
         * @generated
         */
		EClass EVENT = eINSTANCE.getEvent();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.impl.EndErrorEventImpl <em>End Error Event</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.impl.EndErrorEventImpl
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getEndErrorEvent()
         * @generated
         */
		EClass END_ERROR_EVENT = eINSTANCE.getEndErrorEvent();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.impl.EndEventImpl <em>End Event</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.impl.EndEventImpl
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getEndEvent()
         * @generated
         */
		EClass END_EVENT = eINSTANCE.getEndEvent();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.impl.EndMessageEventImpl <em>End Message Event</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.impl.EndMessageEventImpl
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getEndMessageEvent()
         * @generated
         */
		EClass END_MESSAGE_EVENT = eINSTANCE.getEndMessageEvent();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.impl.EndSignalEventImpl <em>End Signal Event</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.impl.EndSignalEventImpl
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getEndSignalEvent()
         * @generated
         */
		EClass END_SIGNAL_EVENT = eINSTANCE.getEndSignalEvent();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.impl.EndTerminatedEventImpl <em>End Terminated Event</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.impl.EndTerminatedEventImpl
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getEndTerminatedEvent()
         * @generated
         */
		EClass END_TERMINATED_EVENT = eINSTANCE.getEndTerminatedEvent();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.ErrorEvent <em>Error Event</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.ErrorEvent
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getErrorEvent()
         * @generated
         */
		EClass ERROR_EVENT = eINSTANCE.getErrorEvent();

		/**
         * The meta object literal for the '<em><b>Error Code</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute ERROR_EVENT__ERROR_CODE = eINSTANCE.getErrorEvent_ErrorCode();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.impl.EnumTypeImpl <em>Enum Type</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.impl.EnumTypeImpl
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getEnumType()
         * @generated
         */
		EClass ENUM_TYPE = eINSTANCE.getEnumType();

		/**
         * The meta object literal for the '<em><b>Literals</b></em>' attribute list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute ENUM_TYPE__LITERALS = eINSTANCE.getEnumType_Literals();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.impl.FloatTypeImpl <em>Float Type</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.impl.FloatTypeImpl
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getFloatType()
         * @generated
         */
		EClass FLOAT_TYPE = eINSTANCE.getFloatType();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.impl.FlowElementImpl <em>Flow Element</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.impl.FlowElementImpl
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getFlowElement()
         * @generated
         */
		EClass FLOW_ELEMENT = eINSTANCE.getFlowElement();

		/**
         * The meta object literal for the '<em><b>Dynamic Label</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference FLOW_ELEMENT__DYNAMIC_LABEL = eINSTANCE.getFlowElement_DynamicLabel();

		/**
         * The meta object literal for the '<em><b>Dynamic Description</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference FLOW_ELEMENT__DYNAMIC_DESCRIPTION = eINSTANCE.getFlowElement_DynamicDescription();

		/**
         * The meta object literal for the '<em><b>Step Summary</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference FLOW_ELEMENT__STEP_SUMMARY = eINSTANCE.getFlowElement_StepSummary();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.impl.FormMappingImpl <em>Form Mapping</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.impl.FormMappingImpl
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getFormMapping()
         * @generated
         */
		EClass FORM_MAPPING = eINSTANCE.getFormMapping();

		/**
         * The meta object literal for the '<em><b>Target Form</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference FORM_MAPPING__TARGET_FORM = eINSTANCE.getFormMapping_TargetForm();

		/**
         * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute FORM_MAPPING__TYPE = eINSTANCE.getFormMapping_Type();

		/**
         * The meta object literal for the '<em><b>Url</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute FORM_MAPPING__URL = eINSTANCE.getFormMapping_Url();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.impl.GatewayImpl <em>Gateway</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.impl.GatewayImpl
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getGateway()
         * @generated
         */
		EClass GATEWAY = eINSTANCE.getGateway();

		/**
         * The meta object literal for the '<em><b>Gateway Type</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute GATEWAY__GATEWAY_TYPE = eINSTANCE.getGateway_GatewayType();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.impl.InclusiveGatewayImpl <em>Inclusive Gateway</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.impl.InclusiveGatewayImpl
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getInclusiveGateway()
         * @generated
         */
		EClass INCLUSIVE_GATEWAY = eINSTANCE.getInclusiveGateway();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.impl.InputMappingImpl <em>Input Mapping</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.impl.InputMappingImpl
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getInputMapping()
         * @generated
         */
		EClass INPUT_MAPPING = eINSTANCE.getInputMapping();

		/**
         * The meta object literal for the '<em><b>Process Source</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference INPUT_MAPPING__PROCESS_SOURCE = eINSTANCE.getInputMapping_ProcessSource();

		/**
         * The meta object literal for the '<em><b>Subprocess Target</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute INPUT_MAPPING__SUBPROCESS_TARGET = eINSTANCE.getInputMapping_SubprocessTarget();

		/**
         * The meta object literal for the '<em><b>Assignation Type</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute INPUT_MAPPING__ASSIGNATION_TYPE = eINSTANCE.getInputMapping_AssignationType();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.impl.IntegerTypeImpl <em>Integer Type</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.impl.IntegerTypeImpl
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getIntegerType()
         * @generated
         */
		EClass INTEGER_TYPE = eINSTANCE.getIntegerType();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.impl.IntermediateErrorCatchEventImpl <em>Intermediate Error Catch Event</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.impl.IntermediateErrorCatchEventImpl
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getIntermediateErrorCatchEvent()
         * @generated
         */
		EClass INTERMEDIATE_ERROR_CATCH_EVENT = eINSTANCE.getIntermediateErrorCatchEvent();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.impl.IntermediateCatchSignalEventImpl <em>Intermediate Catch Signal Event</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.impl.IntermediateCatchSignalEventImpl
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getIntermediateCatchSignalEvent()
         * @generated
         */
		EClass INTERMEDIATE_CATCH_SIGNAL_EVENT = eINSTANCE.getIntermediateCatchSignalEvent();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.impl.IntermediateThrowSignalEventImpl <em>Intermediate Throw Signal Event</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.impl.IntermediateThrowSignalEventImpl
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getIntermediateThrowSignalEvent()
         * @generated
         */
		EClass INTERMEDIATE_THROW_SIGNAL_EVENT = eINSTANCE.getIntermediateThrowSignalEvent();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.impl.IntermediateCatchMessageEventImpl <em>Intermediate Catch Message Event</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.impl.IntermediateCatchMessageEventImpl
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getIntermediateCatchMessageEvent()
         * @generated
         */
		EClass INTERMEDIATE_CATCH_MESSAGE_EVENT = eINSTANCE.getIntermediateCatchMessageEvent();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.impl.IntermediateThrowMessageEventImpl <em>Intermediate Throw Message Event</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.impl.IntermediateThrowMessageEventImpl
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getIntermediateThrowMessageEvent()
         * @generated
         */
		EClass INTERMEDIATE_THROW_MESSAGE_EVENT = eINSTANCE.getIntermediateThrowMessageEvent();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.impl.IntermediateCatchTimerEventImpl <em>Intermediate Catch Timer Event</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.impl.IntermediateCatchTimerEventImpl
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getIntermediateCatchTimerEvent()
         * @generated
         */
		EClass INTERMEDIATE_CATCH_TIMER_EVENT = eINSTANCE.getIntermediateCatchTimerEvent();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.impl.JavaObjectDataImpl <em>Java Object Data</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.impl.JavaObjectDataImpl
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getJavaObjectData()
         * @generated
         */
		EClass JAVA_OBJECT_DATA = eINSTANCE.getJavaObjectData();

		/**
         * The meta object literal for the '<em><b>Class Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute JAVA_OBJECT_DATA__CLASS_NAME = eINSTANCE.getJavaObjectData_ClassName();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.impl.JavaTypeImpl <em>Java Type</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.impl.JavaTypeImpl
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getJavaType()
         * @generated
         */
		EClass JAVA_TYPE = eINSTANCE.getJavaType();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.impl.LaneImpl <em>Lane</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.impl.LaneImpl
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getLane()
         * @generated
         */
		EClass LANE = eINSTANCE.getLane();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.impl.LinkEventImpl <em>Link Event</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.impl.LinkEventImpl
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getLinkEvent()
         * @generated
         */
		EClass LINK_EVENT = eINSTANCE.getLinkEvent();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.impl.LongTypeImpl <em>Long Type</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.impl.LongTypeImpl
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getLongType()
         * @generated
         */
		EClass LONG_TYPE = eINSTANCE.getLongType();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.impl.MainProcessImpl <em>Main Process</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.impl.MainProcessImpl
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getMainProcess()
         * @generated
         */
		EClass MAIN_PROCESS = eINSTANCE.getMainProcess();

		/**
         * The meta object literal for the '<em><b>Bonita Version</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute MAIN_PROCESS__BONITA_VERSION = eINSTANCE.getMainProcess_BonitaVersion();

		/**
         * The meta object literal for the '<em><b>Bonita Model Version</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute MAIN_PROCESS__BONITA_MODEL_VERSION = eINSTANCE.getMainProcess_BonitaModelVersion();

		/**
         * The meta object literal for the '<em><b>Included Entries</b></em>' attribute list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute MAIN_PROCESS__INCLUDED_ENTRIES = eINSTANCE.getMainProcess_IncludedEntries();

		/**
         * The meta object literal for the '<em><b>Message Connections</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference MAIN_PROCESS__MESSAGE_CONNECTIONS = eINSTANCE.getMainProcess_MessageConnections();

		/**
         * The meta object literal for the '<em><b>Generated Libs</b></em>' attribute list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute MAIN_PROCESS__GENERATED_LIBS = eINSTANCE.getMainProcess_GeneratedLibs();

		/**
         * The meta object literal for the '<em><b>Enable Validation</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute MAIN_PROCESS__ENABLE_VALIDATION = eINSTANCE.getMainProcess_EnableValidation();

		/**
         * The meta object literal for the '<em><b>Config Id</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute MAIN_PROCESS__CONFIG_ID = eINSTANCE.getMainProcess_ConfigId();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.impl.MessageImpl <em>Message</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.impl.MessageImpl
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getMessage()
         * @generated
         */
		EClass MESSAGE = eINSTANCE.getMessage();

		/**
         * The meta object literal for the '<em><b>Throw Event</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute MESSAGE__THROW_EVENT = eINSTANCE.getMessage_ThrowEvent();

		/**
         * The meta object literal for the '<em><b>Ttl</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute MESSAGE__TTL = eINSTANCE.getMessage_Ttl();

		/**
         * The meta object literal for the '<em><b>Correlation</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference MESSAGE__CORRELATION = eINSTANCE.getMessage_Correlation();

		/**
         * The meta object literal for the '<em><b>Source</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference MESSAGE__SOURCE = eINSTANCE.getMessage_Source();

		/**
         * The meta object literal for the '<em><b>Target Process Expression</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference MESSAGE__TARGET_PROCESS_EXPRESSION = eINSTANCE.getMessage_TargetProcessExpression();

		/**
         * The meta object literal for the '<em><b>Target Element Expression</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference MESSAGE__TARGET_ELEMENT_EXPRESSION = eINSTANCE.getMessage_TargetElementExpression();

		/**
         * The meta object literal for the '<em><b>Message Content</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference MESSAGE__MESSAGE_CONTENT = eINSTANCE.getMessage_MessageContent();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.impl.MessageFlowImpl <em>Message Flow</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.impl.MessageFlowImpl
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getMessageFlow()
         * @generated
         */
		EClass MESSAGE_FLOW = eINSTANCE.getMessageFlow();

		/**
         * The meta object literal for the '<em><b>Target</b></em>' reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference MESSAGE_FLOW__TARGET = eINSTANCE.getMessageFlow_Target();

		/**
         * The meta object literal for the '<em><b>Source</b></em>' reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference MESSAGE_FLOW__SOURCE = eINSTANCE.getMessageFlow_Source();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.impl.MessageEventImpl <em>Message Event</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.impl.MessageEventImpl
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getMessageEvent()
         * @generated
         */
		EClass MESSAGE_EVENT = eINSTANCE.getMessageEvent();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.impl.MultiInstantiableImpl <em>Multi Instantiable</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.impl.MultiInstantiableImpl
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getMultiInstantiable()
         * @generated
         */
		EClass MULTI_INSTANTIABLE = eINSTANCE.getMultiInstantiable();

		/**
         * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute MULTI_INSTANTIABLE__TYPE = eINSTANCE.getMultiInstantiable_Type();

		/**
         * The meta object literal for the '<em><b>Test Before</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute MULTI_INSTANTIABLE__TEST_BEFORE = eINSTANCE.getMultiInstantiable_TestBefore();

		/**
         * The meta object literal for the '<em><b>Loop Condition</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference MULTI_INSTANTIABLE__LOOP_CONDITION = eINSTANCE.getMultiInstantiable_LoopCondition();

		/**
         * The meta object literal for the '<em><b>Loop Maximum</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference MULTI_INSTANTIABLE__LOOP_MAXIMUM = eINSTANCE.getMultiInstantiable_LoopMaximum();

		/**
         * The meta object literal for the '<em><b>Use Cardinality</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute MULTI_INSTANTIABLE__USE_CARDINALITY = eINSTANCE.getMultiInstantiable_UseCardinality();

		/**
         * The meta object literal for the '<em><b>Cardinality Expression</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference MULTI_INSTANTIABLE__CARDINALITY_EXPRESSION = eINSTANCE.getMultiInstantiable_CardinalityExpression();

		/**
         * The meta object literal for the '<em><b>Collection Data To Multi Instantiate</b></em>' reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference MULTI_INSTANTIABLE__COLLECTION_DATA_TO_MULTI_INSTANTIATE = eINSTANCE.getMultiInstantiable_CollectionDataToMultiInstantiate();

		/**
         * The meta object literal for the '<em><b>Iterator Expression</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference MULTI_INSTANTIABLE__ITERATOR_EXPRESSION = eINSTANCE.getMultiInstantiable_IteratorExpression();

		/**
         * The meta object literal for the '<em><b>Output Data</b></em>' reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference MULTI_INSTANTIABLE__OUTPUT_DATA = eINSTANCE.getMultiInstantiable_OutputData();

		/**
         * The meta object literal for the '<em><b>List Data Containing Output Results</b></em>' reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference MULTI_INSTANTIABLE__LIST_DATA_CONTAINING_OUTPUT_RESULTS = eINSTANCE.getMultiInstantiable_ListDataContainingOutputResults();

		/**
         * The meta object literal for the '<em><b>Completion Condition</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference MULTI_INSTANTIABLE__COMPLETION_CONDITION = eINSTANCE.getMultiInstantiable_CompletionCondition();

		/**
         * The meta object literal for the '<em><b>Store Output</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute MULTI_INSTANTIABLE__STORE_OUTPUT = eINSTANCE.getMultiInstantiable_StoreOutput();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.impl.NonInterruptingBoundaryTimerEventImpl <em>Non Interrupting Boundary Timer Event</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.impl.NonInterruptingBoundaryTimerEventImpl
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getNonInterruptingBoundaryTimerEvent()
         * @generated
         */
		EClass NON_INTERRUPTING_BOUNDARY_TIMER_EVENT = eINSTANCE.getNonInterruptingBoundaryTimerEvent();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.impl.OperationContainerImpl <em>Operation Container</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.impl.OperationContainerImpl
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getOperationContainer()
         * @generated
         */
		EClass OPERATION_CONTAINER = eINSTANCE.getOperationContainer();

		/**
         * The meta object literal for the '<em><b>Operations</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference OPERATION_CONTAINER__OPERATIONS = eINSTANCE.getOperationContainer_Operations();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.impl.OutputMappingImpl <em>Output Mapping</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.impl.OutputMappingImpl
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getOutputMapping()
         * @generated
         */
		EClass OUTPUT_MAPPING = eINSTANCE.getOutputMapping();

		/**
         * The meta object literal for the '<em><b>Subprocess Source</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute OUTPUT_MAPPING__SUBPROCESS_SOURCE = eINSTANCE.getOutputMapping_SubprocessSource();

		/**
         * The meta object literal for the '<em><b>Process Target</b></em>' reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference OUTPUT_MAPPING__PROCESS_TARGET = eINSTANCE.getOutputMapping_ProcessTarget();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.impl.PageFlowImpl <em>Page Flow</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.impl.PageFlowImpl
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getPageFlow()
         * @generated
         */
		EClass PAGE_FLOW = eINSTANCE.getPageFlow();

		/**
         * The meta object literal for the '<em><b>Form Mapping</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference PAGE_FLOW__FORM_MAPPING = eINSTANCE.getPageFlow_FormMapping();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.impl.PoolImpl <em>Pool</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.impl.PoolImpl
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getPool()
         * @generated
         */
		EClass POOL = eINSTANCE.getPool();

		/**
         * The meta object literal for the '<em><b>Documents</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference POOL__DOCUMENTS = eINSTANCE.getPool_Documents();

		/**
         * The meta object literal for the '<em><b>Search Indexes</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference POOL__SEARCH_INDEXES = eINSTANCE.getPool_SearchIndexes();

		/**
         * The meta object literal for the '<em><b>Display Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute POOL__DISPLAY_NAME = eINSTANCE.getPool_DisplayName();

		/**
         * The meta object literal for the '<em><b>Additional Resources</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference POOL__ADDITIONAL_RESOURCES = eINSTANCE.getPool_AdditionalResources();

        /**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.RecapFlow <em>Recap Flow</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.RecapFlow
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getRecapFlow()
         * @generated
         */
		EClass RECAP_FLOW = eINSTANCE.getRecapFlow();

		/**
         * The meta object literal for the '<em><b>Overview Form Mapping</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference RECAP_FLOW__OVERVIEW_FORM_MAPPING = eINSTANCE.getRecapFlow_OverviewFormMapping();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.impl.ReceiveTaskImpl <em>Receive Task</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.impl.ReceiveTaskImpl
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getReceiveTask()
         * @generated
         */
		EClass RECEIVE_TASK = eINSTANCE.getReceiveTask();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.impl.SequenceFlowImpl <em>Sequence Flow</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.impl.SequenceFlowImpl
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getSequenceFlow()
         * @generated
         */
		EClass SEQUENCE_FLOW = eINSTANCE.getSequenceFlow();

		/**
         * The meta object literal for the '<em><b>Is Default</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute SEQUENCE_FLOW__IS_DEFAULT = eINSTANCE.getSequenceFlow_IsDefault();

		/**
         * The meta object literal for the '<em><b>Condition Type</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute SEQUENCE_FLOW__CONDITION_TYPE = eINSTANCE.getSequenceFlow_ConditionType();

		/**
         * The meta object literal for the '<em><b>Decision Table</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference SEQUENCE_FLOW__DECISION_TABLE = eINSTANCE.getSequenceFlow_DecisionTable();

		/**
         * The meta object literal for the '<em><b>Condition</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference SEQUENCE_FLOW__CONDITION = eINSTANCE.getSequenceFlow_Condition();

		/**
         * The meta object literal for the '<em><b>Path Token</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute SEQUENCE_FLOW__PATH_TOKEN = eINSTANCE.getSequenceFlow_PathToken();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.SignalEvent <em>Signal Event</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.SignalEvent
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getSignalEvent()
         * @generated
         */
		EClass SIGNAL_EVENT = eINSTANCE.getSignalEvent();

		/**
         * The meta object literal for the '<em><b>Signal Code</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute SIGNAL_EVENT__SIGNAL_CODE = eINSTANCE.getSignalEvent_SignalCode();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.SourceElement <em>Source Element</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.SourceElement
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getSourceElement()
         * @generated
         */
		EClass SOURCE_ELEMENT = eINSTANCE.getSourceElement();

		/**
         * The meta object literal for the '<em><b>Outgoing</b></em>' reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference SOURCE_ELEMENT__OUTGOING = eINSTANCE.getSourceElement_Outgoing();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.impl.StringTypeImpl <em>String Type</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.impl.StringTypeImpl
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getStringType()
         * @generated
         */
		EClass STRING_TYPE = eINSTANCE.getStringType();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.impl.ScriptTaskImpl <em>Script Task</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.impl.ScriptTaskImpl
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getScriptTask()
         * @generated
         */
		EClass SCRIPT_TASK = eINSTANCE.getScriptTask();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.impl.SearchIndexImpl <em>Search Index</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.impl.SearchIndexImpl
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getSearchIndex()
         * @generated
         */
		EClass SEARCH_INDEX = eINSTANCE.getSearchIndex();

		/**
         * The meta object literal for the '<em><b>Name</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference SEARCH_INDEX__NAME = eINSTANCE.getSearchIndex_Name();

		/**
         * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference SEARCH_INDEX__VALUE = eINSTANCE.getSearchIndex_Value();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.impl.SendTaskImpl <em>Send Task</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.impl.SendTaskImpl
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getSendTask()
         * @generated
         */
		EClass SEND_TASK = eINSTANCE.getSendTask();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.impl.ServiceTaskImpl <em>Service Task</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.impl.ServiceTaskImpl
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getServiceTask()
         * @generated
         */
		EClass SERVICE_TASK = eINSTANCE.getServiceTask();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.impl.StartErrorEventImpl <em>Start Error Event</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.impl.StartErrorEventImpl
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getStartErrorEvent()
         * @generated
         */
		EClass START_ERROR_EVENT = eINSTANCE.getStartErrorEvent();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.impl.StartEventImpl <em>Start Event</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.impl.StartEventImpl
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getStartEvent()
         * @generated
         */
		EClass START_EVENT = eINSTANCE.getStartEvent();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.impl.StartMessageEventImpl <em>Start Message Event</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.impl.StartMessageEventImpl
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getStartMessageEvent()
         * @generated
         */
		EClass START_MESSAGE_EVENT = eINSTANCE.getStartMessageEvent();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.impl.StartSignalEventImpl <em>Start Signal Event</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.impl.StartSignalEventImpl
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getStartSignalEvent()
         * @generated
         */
		EClass START_SIGNAL_EVENT = eINSTANCE.getStartSignalEvent();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.impl.StartTimerEventImpl <em>Start Timer Event</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.impl.StartTimerEventImpl
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getStartTimerEvent()
         * @generated
         */
		EClass START_TIMER_EVENT = eINSTANCE.getStartTimerEvent();

		/**
         * The meta object literal for the '<em><b>From</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute START_TIMER_EVENT__FROM = eINSTANCE.getStartTimerEvent_From();

		/**
         * The meta object literal for the '<em><b>At</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute START_TIMER_EVENT__AT = eINSTANCE.getStartTimerEvent_At();

		/**
         * The meta object literal for the '<em><b>Month</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute START_TIMER_EVENT__MONTH = eINSTANCE.getStartTimerEvent_Month();

		/**
         * The meta object literal for the '<em><b>Day</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute START_TIMER_EVENT__DAY = eINSTANCE.getStartTimerEvent_Day();

		/**
         * The meta object literal for the '<em><b>Hours</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute START_TIMER_EVENT__HOURS = eINSTANCE.getStartTimerEvent_Hours();

		/**
         * The meta object literal for the '<em><b>Day Number</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute START_TIMER_EVENT__DAY_NUMBER = eINSTANCE.getStartTimerEvent_DayNumber();

		/**
         * The meta object literal for the '<em><b>Minutes</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute START_TIMER_EVENT__MINUTES = eINSTANCE.getStartTimerEvent_Minutes();

		/**
         * The meta object literal for the '<em><b>Seconds</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute START_TIMER_EVENT__SECONDS = eINSTANCE.getStartTimerEvent_Seconds();

		/**
         * The meta object literal for the '<em><b>Script Type</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute START_TIMER_EVENT__SCRIPT_TYPE = eINSTANCE.getStartTimerEvent_ScriptType();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.impl.SubProcessEventImpl <em>Sub Process Event</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.impl.SubProcessEventImpl
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getSubProcessEvent()
         * @generated
         */
		EClass SUB_PROCESS_EVENT = eINSTANCE.getSubProcessEvent();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.impl.TaskImpl <em>Task</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.impl.TaskImpl
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getTask()
         * @generated
         */
		EClass TASK = eINSTANCE.getTask();

		/**
         * The meta object literal for the '<em><b>Override Actors Of The Lane</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute TASK__OVERRIDE_ACTORS_OF_THE_LANE = eINSTANCE.getTask_OverrideActorsOfTheLane();

		/**
         * The meta object literal for the '<em><b>Priority</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute TASK__PRIORITY = eINSTANCE.getTask_Priority();

		/**
         * The meta object literal for the '<em><b>Expected Duration</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference TASK__EXPECTED_DURATION = eINSTANCE.getTask_ExpectedDuration();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.TargetElement <em>Target Element</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.TargetElement
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getTargetElement()
         * @generated
         */
		EClass TARGET_ELEMENT = eINSTANCE.getTargetElement();

		/**
         * The meta object literal for the '<em><b>Incoming</b></em>' reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference TARGET_ELEMENT__INCOMING = eINSTANCE.getTargetElement_Incoming();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.impl.TextAnnotationImpl <em>Text Annotation</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.impl.TextAnnotationImpl
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getTextAnnotation()
         * @generated
         */
		EClass TEXT_ANNOTATION = eINSTANCE.getTextAnnotation();

		/**
         * The meta object literal for the '<em><b>Text</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute TEXT_ANNOTATION__TEXT = eINSTANCE.getTextAnnotation_Text();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.impl.TextAnnotationAttachmentImpl <em>Text Annotation Attachment</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.impl.TextAnnotationAttachmentImpl
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getTextAnnotationAttachment()
         * @generated
         */
		EClass TEXT_ANNOTATION_ATTACHMENT = eINSTANCE.getTextAnnotationAttachment();

		/**
         * The meta object literal for the '<em><b>Source</b></em>' reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference TEXT_ANNOTATION_ATTACHMENT__SOURCE = eINSTANCE.getTextAnnotationAttachment_Source();

		/**
         * The meta object literal for the '<em><b>Target</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference TEXT_ANNOTATION_ATTACHMENT__TARGET = eINSTANCE.getTextAnnotationAttachment_Target();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.ThrowSignalEvent <em>Throw Signal Event</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.ThrowSignalEvent
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getThrowSignalEvent()
         * @generated
         */
		EClass THROW_SIGNAL_EVENT = eINSTANCE.getThrowSignalEvent();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.impl.ThrowLinkEventImpl <em>Throw Link Event</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.impl.ThrowLinkEventImpl
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getThrowLinkEvent()
         * @generated
         */
		EClass THROW_LINK_EVENT = eINSTANCE.getThrowLinkEvent();

		/**
         * The meta object literal for the '<em><b>To</b></em>' reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference THROW_LINK_EVENT__TO = eINSTANCE.getThrowLinkEvent_To();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.impl.ThrowMessageEventImpl <em>Throw Message Event</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.impl.ThrowMessageEventImpl
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getThrowMessageEvent()
         * @generated
         */
		EClass THROW_MESSAGE_EVENT = eINSTANCE.getThrowMessageEvent();

		/**
         * The meta object literal for the '<em><b>Events</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference THROW_MESSAGE_EVENT__EVENTS = eINSTANCE.getThrowMessageEvent_Events();

		/**
         * The meta object literal for the '<em><b>Outgoing Messages</b></em>' reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference THROW_MESSAGE_EVENT__OUTGOING_MESSAGES = eINSTANCE.getThrowMessageEvent_OutgoingMessages();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.impl.TimerEventImpl <em>Timer Event</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.impl.TimerEventImpl
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getTimerEvent()
         * @generated
         */
		EClass TIMER_EVENT = eINSTANCE.getTimerEvent();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.impl.XMLDataImpl <em>XML Data</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.impl.XMLDataImpl
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getXMLData()
         * @generated
         */
		EClass XML_DATA = eINSTANCE.getXMLData();

		/**
         * The meta object literal for the '<em><b>Namespace</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute XML_DATA__NAMESPACE = eINSTANCE.getXMLData_Namespace();

		/**
         * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute XML_DATA__TYPE = eINSTANCE.getXMLData_Type();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.impl.XMLTypeImpl <em>XML Type</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.impl.XMLTypeImpl
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getXMLType()
         * @generated
         */
		EClass XML_TYPE = eINSTANCE.getXMLType();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.impl.XORGatewayImpl <em>XOR Gateway</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.impl.XORGatewayImpl
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getXORGateway()
         * @generated
         */
		EClass XOR_GATEWAY = eINSTANCE.getXORGateway();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.impl.AdditionalResourceImpl <em>Additional Resource</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.impl.AdditionalResourceImpl
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getAdditionalResource()
         * @generated
         */
        EClass ADDITIONAL_RESOURCE = eINSTANCE.getAdditionalResource();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute ADDITIONAL_RESOURCE__NAME = eINSTANCE.getAdditionalResource_Name();

        /**
         * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute ADDITIONAL_RESOURCE__DESCRIPTION = eINSTANCE.getAdditionalResource_Description();

        /**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.CorrelationTypeActive <em>Correlation Type Active</em>}' enum.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.CorrelationTypeActive
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getCorrelationTypeActive()
         * @generated
         */
		EEnum CORRELATION_TYPE_ACTIVE = eINSTANCE.getCorrelationTypeActive();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.ContractInputType <em>Contract Input Type</em>}' enum.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.ContractInputType
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getContractInputType()
         * @generated
         */
		EEnum CONTRACT_INPUT_TYPE = eINSTANCE.getContractInputType();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.DocumentType <em>Document Type</em>}' enum.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.DocumentType
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getDocumentType()
         * @generated
         */
		EEnum DOCUMENT_TYPE = eINSTANCE.getDocumentType();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.FormMappingType <em>Form Mapping Type</em>}' enum.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.FormMappingType
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getFormMappingType()
         * @generated
         */
		EEnum FORM_MAPPING_TYPE = eINSTANCE.getFormMappingType();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.GatewayType <em>Gateway Type</em>}' enum.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.GatewayType
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getGatewayType()
         * @generated
         */
		EEnum GATEWAY_TYPE = eINSTANCE.getGatewayType();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.InputMappingAssignationType <em>Input Mapping Assignation Type</em>}' enum.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.InputMappingAssignationType
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getInputMappingAssignationType()
         * @generated
         */
		EEnum INPUT_MAPPING_ASSIGNATION_TYPE = eINSTANCE.getInputMappingAssignationType();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.MultiInstanceType <em>Multi Instance Type</em>}' enum.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.MultiInstanceType
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getMultiInstanceType()
         * @generated
         */
		EEnum MULTI_INSTANCE_TYPE = eINSTANCE.getMultiInstanceType();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.SequenceFlowConditionType <em>Sequence Flow Condition Type</em>}' enum.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.SequenceFlowConditionType
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getSequenceFlowConditionType()
         * @generated
         */
		EEnum SEQUENCE_FLOW_CONDITION_TYPE = eINSTANCE.getSequenceFlowConditionType();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.StartTimerScriptType <em>Start Timer Script Type</em>}' enum.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.StartTimerScriptType
         * @see org.bonitasoft.studio.model.process.impl.ProcessPackageImpl#getStartTimerScriptType()
         * @generated
         */
		EEnum START_TIMER_SCRIPT_TYPE = eINSTANCE.getStartTimerScriptType();

	}

} //ProcessPackage
