/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.omg.spec.bpmn.di;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
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
 * @see org.omg.spec.bpmn.di.DiFactory
 * @model kind="package"
 * @generated
 */
public interface DiPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "di";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.omg.org/spec/BPMN/20100524/DI";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "di";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	DiPackage eINSTANCE = org.omg.spec.bpmn.di.impl.DiPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.di.impl.BPMNDiagramImpl <em>BPMN Diagram</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.di.impl.BPMNDiagramImpl
	 * @see org.omg.spec.bpmn.di.impl.DiPackageImpl#getBPMNDiagram()
	 * @generated
	 */
	int BPMN_DIAGRAM = 0;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BPMN_DIAGRAM__DOCUMENTATION = org.omg.spec.dd.di.DiPackage.DIAGRAM__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BPMN_DIAGRAM__ID = org.omg.spec.dd.di.DiPackage.DIAGRAM__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BPMN_DIAGRAM__NAME = org.omg.spec.dd.di.DiPackage.DIAGRAM__NAME;

	/**
	 * The feature id for the '<em><b>Resolution</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BPMN_DIAGRAM__RESOLUTION = org.omg.spec.dd.di.DiPackage.DIAGRAM__RESOLUTION;

	/**
	 * The feature id for the '<em><b>BPMN Plane</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BPMN_DIAGRAM__BPMN_PLANE = org.omg.spec.dd.di.DiPackage.DIAGRAM_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>BPMN Label Style</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BPMN_DIAGRAM__BPMN_LABEL_STYLE = org.omg.spec.dd.di.DiPackage.DIAGRAM_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>BPMN Diagram</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BPMN_DIAGRAM_FEATURE_COUNT = org.omg.spec.dd.di.DiPackage.DIAGRAM_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.di.impl.BPMNEdgeImpl <em>BPMN Edge</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.di.impl.BPMNEdgeImpl
	 * @see org.omg.spec.bpmn.di.impl.DiPackageImpl#getBPMNEdge()
	 * @generated
	 */
	int BPMN_EDGE = 1;

	/**
	 * The feature id for the '<em><b>Extension</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BPMN_EDGE__EXTENSION = org.omg.spec.dd.di.DiPackage.LABELED_EDGE__EXTENSION;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BPMN_EDGE__ID = org.omg.spec.dd.di.DiPackage.LABELED_EDGE__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BPMN_EDGE__ANY_ATTRIBUTE = org.omg.spec.dd.di.DiPackage.LABELED_EDGE__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Waypoint</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BPMN_EDGE__WAYPOINT = org.omg.spec.dd.di.DiPackage.LABELED_EDGE__WAYPOINT;

	/**
	 * The feature id for the '<em><b>BPMN Label</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BPMN_EDGE__BPMN_LABEL = org.omg.spec.dd.di.DiPackage.LABELED_EDGE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Bpmn Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BPMN_EDGE__BPMN_ELEMENT = org.omg.spec.dd.di.DiPackage.LABELED_EDGE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Message Visible Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BPMN_EDGE__MESSAGE_VISIBLE_KIND = org.omg.spec.dd.di.DiPackage.LABELED_EDGE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Source Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BPMN_EDGE__SOURCE_ELEMENT = org.omg.spec.dd.di.DiPackage.LABELED_EDGE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Target Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BPMN_EDGE__TARGET_ELEMENT = org.omg.spec.dd.di.DiPackage.LABELED_EDGE_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>BPMN Edge</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BPMN_EDGE_FEATURE_COUNT = org.omg.spec.dd.di.DiPackage.LABELED_EDGE_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.di.impl.BPMNLabelImpl <em>BPMN Label</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.di.impl.BPMNLabelImpl
	 * @see org.omg.spec.bpmn.di.impl.DiPackageImpl#getBPMNLabel()
	 * @generated
	 */
	int BPMN_LABEL = 2;

	/**
	 * The feature id for the '<em><b>Extension</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BPMN_LABEL__EXTENSION = org.omg.spec.dd.di.DiPackage.LABEL__EXTENSION;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BPMN_LABEL__ID = org.omg.spec.dd.di.DiPackage.LABEL__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BPMN_LABEL__ANY_ATTRIBUTE = org.omg.spec.dd.di.DiPackage.LABEL__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Bounds</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BPMN_LABEL__BOUNDS = org.omg.spec.dd.di.DiPackage.LABEL__BOUNDS;

	/**
	 * The feature id for the '<em><b>Label Style</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BPMN_LABEL__LABEL_STYLE = org.omg.spec.dd.di.DiPackage.LABEL_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>BPMN Label</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BPMN_LABEL_FEATURE_COUNT = org.omg.spec.dd.di.DiPackage.LABEL_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.di.impl.BPMNLabelStyleImpl <em>BPMN Label Style</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.di.impl.BPMNLabelStyleImpl
	 * @see org.omg.spec.bpmn.di.impl.DiPackageImpl#getBPMNLabelStyle()
	 * @generated
	 */
	int BPMN_LABEL_STYLE = 3;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BPMN_LABEL_STYLE__ID = org.omg.spec.dd.di.DiPackage.STYLE__ID;

	/**
	 * The feature id for the '<em><b>Font</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BPMN_LABEL_STYLE__FONT = org.omg.spec.dd.di.DiPackage.STYLE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>BPMN Label Style</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BPMN_LABEL_STYLE_FEATURE_COUNT = org.omg.spec.dd.di.DiPackage.STYLE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.di.impl.BPMNPlaneImpl <em>BPMN Plane</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.di.impl.BPMNPlaneImpl
	 * @see org.omg.spec.bpmn.di.impl.DiPackageImpl#getBPMNPlane()
	 * @generated
	 */
	int BPMN_PLANE = 4;

	/**
	 * The feature id for the '<em><b>Extension</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BPMN_PLANE__EXTENSION = org.omg.spec.dd.di.DiPackage.PLANE__EXTENSION;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BPMN_PLANE__ID = org.omg.spec.dd.di.DiPackage.PLANE__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BPMN_PLANE__ANY_ATTRIBUTE = org.omg.spec.dd.di.DiPackage.PLANE__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Diagram Element Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BPMN_PLANE__DIAGRAM_ELEMENT_GROUP = org.omg.spec.dd.di.DiPackage.PLANE__DIAGRAM_ELEMENT_GROUP;

	/**
	 * The feature id for the '<em><b>Diagram Element</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BPMN_PLANE__DIAGRAM_ELEMENT = org.omg.spec.dd.di.DiPackage.PLANE__DIAGRAM_ELEMENT;

	/**
	 * The feature id for the '<em><b>Bpmn Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BPMN_PLANE__BPMN_ELEMENT = org.omg.spec.dd.di.DiPackage.PLANE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>BPMN Plane</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BPMN_PLANE_FEATURE_COUNT = org.omg.spec.dd.di.DiPackage.PLANE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.di.impl.BPMNShapeImpl <em>BPMN Shape</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.di.impl.BPMNShapeImpl
	 * @see org.omg.spec.bpmn.di.impl.DiPackageImpl#getBPMNShape()
	 * @generated
	 */
	int BPMN_SHAPE = 5;

	/**
	 * The feature id for the '<em><b>Extension</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BPMN_SHAPE__EXTENSION = org.omg.spec.dd.di.DiPackage.LABELED_SHAPE__EXTENSION;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BPMN_SHAPE__ID = org.omg.spec.dd.di.DiPackage.LABELED_SHAPE__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BPMN_SHAPE__ANY_ATTRIBUTE = org.omg.spec.dd.di.DiPackage.LABELED_SHAPE__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Bounds</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BPMN_SHAPE__BOUNDS = org.omg.spec.dd.di.DiPackage.LABELED_SHAPE__BOUNDS;

	/**
	 * The feature id for the '<em><b>BPMN Label</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BPMN_SHAPE__BPMN_LABEL = org.omg.spec.dd.di.DiPackage.LABELED_SHAPE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Bpmn Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BPMN_SHAPE__BPMN_ELEMENT = org.omg.spec.dd.di.DiPackage.LABELED_SHAPE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Choreography Activity Shape</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BPMN_SHAPE__CHOREOGRAPHY_ACTIVITY_SHAPE = org.omg.spec.dd.di.DiPackage.LABELED_SHAPE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Is Expanded</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BPMN_SHAPE__IS_EXPANDED = org.omg.spec.dd.di.DiPackage.LABELED_SHAPE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Is Horizontal</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BPMN_SHAPE__IS_HORIZONTAL = org.omg.spec.dd.di.DiPackage.LABELED_SHAPE_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Is Marker Visible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BPMN_SHAPE__IS_MARKER_VISIBLE = org.omg.spec.dd.di.DiPackage.LABELED_SHAPE_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Is Message Visible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BPMN_SHAPE__IS_MESSAGE_VISIBLE = org.omg.spec.dd.di.DiPackage.LABELED_SHAPE_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Participant Band Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BPMN_SHAPE__PARTICIPANT_BAND_KIND = org.omg.spec.dd.di.DiPackage.LABELED_SHAPE_FEATURE_COUNT + 7;

	/**
	 * The number of structural features of the '<em>BPMN Shape</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BPMN_SHAPE_FEATURE_COUNT = org.omg.spec.dd.di.DiPackage.LABELED_SHAPE_FEATURE_COUNT + 8;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.di.impl.DocumentRootImpl <em>Document Root</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.di.impl.DocumentRootImpl
	 * @see org.omg.spec.bpmn.di.impl.DiPackageImpl#getDocumentRoot()
	 * @generated
	 */
	int DOCUMENT_ROOT = 6;

	/**
	 * The feature id for the '<em><b>Mixed</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__MIXED = 0;

	/**
	 * The feature id for the '<em><b>XMLNS Prefix Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__XMLNS_PREFIX_MAP = 1;

	/**
	 * The feature id for the '<em><b>XSI Schema Location</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__XSI_SCHEMA_LOCATION = 2;

	/**
	 * The feature id for the '<em><b>BPMN Diagram</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__BPMN_DIAGRAM = 3;

	/**
	 * The feature id for the '<em><b>BPMN Edge</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__BPMN_EDGE = 4;

	/**
	 * The feature id for the '<em><b>BPMN Label</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__BPMN_LABEL = 5;

	/**
	 * The feature id for the '<em><b>BPMN Label Style</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__BPMN_LABEL_STYLE = 6;

	/**
	 * The feature id for the '<em><b>BPMN Plane</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__BPMN_PLANE = 7;

	/**
	 * The feature id for the '<em><b>BPMN Shape</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__BPMN_SHAPE = 8;

	/**
	 * The number of structural features of the '<em>Document Root</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT_FEATURE_COUNT = 9;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.di.MessageVisibleKind <em>Message Visible Kind</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.di.MessageVisibleKind
	 * @see org.omg.spec.bpmn.di.impl.DiPackageImpl#getMessageVisibleKind()
	 * @generated
	 */
	int MESSAGE_VISIBLE_KIND = 7;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.di.ParticipantBandKind <em>Participant Band Kind</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.di.ParticipantBandKind
	 * @see org.omg.spec.bpmn.di.impl.DiPackageImpl#getParticipantBandKind()
	 * @generated
	 */
	int PARTICIPANT_BAND_KIND = 8;

	/**
	 * The meta object id for the '<em>Message Visible Kind Object</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.di.MessageVisibleKind
	 * @see org.omg.spec.bpmn.di.impl.DiPackageImpl#getMessageVisibleKindObject()
	 * @generated
	 */
	int MESSAGE_VISIBLE_KIND_OBJECT = 9;

	/**
	 * The meta object id for the '<em>Participant Band Kind Object</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.di.ParticipantBandKind
	 * @see org.omg.spec.bpmn.di.impl.DiPackageImpl#getParticipantBandKindObject()
	 * @generated
	 */
	int PARTICIPANT_BAND_KIND_OBJECT = 10;


	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.di.BPMNDiagram <em>BPMN Diagram</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>BPMN Diagram</em>'.
	 * @see org.omg.spec.bpmn.di.BPMNDiagram
	 * @generated
	 */
	EClass getBPMNDiagram();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.di.BPMNDiagram#getBPMNPlane <em>BPMN Plane</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>BPMN Plane</em>'.
	 * @see org.omg.spec.bpmn.di.BPMNDiagram#getBPMNPlane()
	 * @see #getBPMNDiagram()
	 * @generated
	 */
	EReference getBPMNDiagram_BPMNPlane();

	/**
	 * Returns the meta object for the containment reference list '{@link org.omg.spec.bpmn.di.BPMNDiagram#getBPMNLabelStyle <em>BPMN Label Style</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>BPMN Label Style</em>'.
	 * @see org.omg.spec.bpmn.di.BPMNDiagram#getBPMNLabelStyle()
	 * @see #getBPMNDiagram()
	 * @generated
	 */
	EReference getBPMNDiagram_BPMNLabelStyle();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.di.BPMNEdge <em>BPMN Edge</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>BPMN Edge</em>'.
	 * @see org.omg.spec.bpmn.di.BPMNEdge
	 * @generated
	 */
	EClass getBPMNEdge();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.di.BPMNEdge#getBPMNLabel <em>BPMN Label</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>BPMN Label</em>'.
	 * @see org.omg.spec.bpmn.di.BPMNEdge#getBPMNLabel()
	 * @see #getBPMNEdge()
	 * @generated
	 */
	EReference getBPMNEdge_BPMNLabel();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.di.BPMNEdge#getBpmnElement <em>Bpmn Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Bpmn Element</em>'.
	 * @see org.omg.spec.bpmn.di.BPMNEdge#getBpmnElement()
	 * @see #getBPMNEdge()
	 * @generated
	 */
	EAttribute getBPMNEdge_BpmnElement();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.di.BPMNEdge#getMessageVisibleKind <em>Message Visible Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Message Visible Kind</em>'.
	 * @see org.omg.spec.bpmn.di.BPMNEdge#getMessageVisibleKind()
	 * @see #getBPMNEdge()
	 * @generated
	 */
	EAttribute getBPMNEdge_MessageVisibleKind();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.di.BPMNEdge#getSourceElement <em>Source Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Source Element</em>'.
	 * @see org.omg.spec.bpmn.di.BPMNEdge#getSourceElement()
	 * @see #getBPMNEdge()
	 * @generated
	 */
	EAttribute getBPMNEdge_SourceElement();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.di.BPMNEdge#getTargetElement <em>Target Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Target Element</em>'.
	 * @see org.omg.spec.bpmn.di.BPMNEdge#getTargetElement()
	 * @see #getBPMNEdge()
	 * @generated
	 */
	EAttribute getBPMNEdge_TargetElement();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.di.BPMNLabel <em>BPMN Label</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>BPMN Label</em>'.
	 * @see org.omg.spec.bpmn.di.BPMNLabel
	 * @generated
	 */
	EClass getBPMNLabel();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.di.BPMNLabel#getLabelStyle <em>Label Style</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Label Style</em>'.
	 * @see org.omg.spec.bpmn.di.BPMNLabel#getLabelStyle()
	 * @see #getBPMNLabel()
	 * @generated
	 */
	EAttribute getBPMNLabel_LabelStyle();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.di.BPMNLabelStyle <em>BPMN Label Style</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>BPMN Label Style</em>'.
	 * @see org.omg.spec.bpmn.di.BPMNLabelStyle
	 * @generated
	 */
	EClass getBPMNLabelStyle();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.di.BPMNLabelStyle#getFont <em>Font</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Font</em>'.
	 * @see org.omg.spec.bpmn.di.BPMNLabelStyle#getFont()
	 * @see #getBPMNLabelStyle()
	 * @generated
	 */
	EReference getBPMNLabelStyle_Font();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.di.BPMNPlane <em>BPMN Plane</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>BPMN Plane</em>'.
	 * @see org.omg.spec.bpmn.di.BPMNPlane
	 * @generated
	 */
	EClass getBPMNPlane();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.di.BPMNPlane#getBpmnElement <em>Bpmn Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Bpmn Element</em>'.
	 * @see org.omg.spec.bpmn.di.BPMNPlane#getBpmnElement()
	 * @see #getBPMNPlane()
	 * @generated
	 */
	EAttribute getBPMNPlane_BpmnElement();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.di.BPMNShape <em>BPMN Shape</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>BPMN Shape</em>'.
	 * @see org.omg.spec.bpmn.di.BPMNShape
	 * @generated
	 */
	EClass getBPMNShape();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.di.BPMNShape#getBPMNLabel <em>BPMN Label</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>BPMN Label</em>'.
	 * @see org.omg.spec.bpmn.di.BPMNShape#getBPMNLabel()
	 * @see #getBPMNShape()
	 * @generated
	 */
	EReference getBPMNShape_BPMNLabel();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.di.BPMNShape#getBpmnElement <em>Bpmn Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Bpmn Element</em>'.
	 * @see org.omg.spec.bpmn.di.BPMNShape#getBpmnElement()
	 * @see #getBPMNShape()
	 * @generated
	 */
	EAttribute getBPMNShape_BpmnElement();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.di.BPMNShape#getChoreographyActivityShape <em>Choreography Activity Shape</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Choreography Activity Shape</em>'.
	 * @see org.omg.spec.bpmn.di.BPMNShape#getChoreographyActivityShape()
	 * @see #getBPMNShape()
	 * @generated
	 */
	EAttribute getBPMNShape_ChoreographyActivityShape();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.di.BPMNShape#isIsExpanded <em>Is Expanded</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Expanded</em>'.
	 * @see org.omg.spec.bpmn.di.BPMNShape#isIsExpanded()
	 * @see #getBPMNShape()
	 * @generated
	 */
	EAttribute getBPMNShape_IsExpanded();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.di.BPMNShape#isIsHorizontal <em>Is Horizontal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Horizontal</em>'.
	 * @see org.omg.spec.bpmn.di.BPMNShape#isIsHorizontal()
	 * @see #getBPMNShape()
	 * @generated
	 */
	EAttribute getBPMNShape_IsHorizontal();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.di.BPMNShape#isIsMarkerVisible <em>Is Marker Visible</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Marker Visible</em>'.
	 * @see org.omg.spec.bpmn.di.BPMNShape#isIsMarkerVisible()
	 * @see #getBPMNShape()
	 * @generated
	 */
	EAttribute getBPMNShape_IsMarkerVisible();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.di.BPMNShape#isIsMessageVisible <em>Is Message Visible</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Message Visible</em>'.
	 * @see org.omg.spec.bpmn.di.BPMNShape#isIsMessageVisible()
	 * @see #getBPMNShape()
	 * @generated
	 */
	EAttribute getBPMNShape_IsMessageVisible();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.di.BPMNShape#getParticipantBandKind <em>Participant Band Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Participant Band Kind</em>'.
	 * @see org.omg.spec.bpmn.di.BPMNShape#getParticipantBandKind()
	 * @see #getBPMNShape()
	 * @generated
	 */
	EAttribute getBPMNShape_ParticipantBandKind();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.di.DocumentRoot <em>Document Root</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Document Root</em>'.
	 * @see org.omg.spec.bpmn.di.DocumentRoot
	 * @generated
	 */
	EClass getDocumentRoot();

	/**
	 * Returns the meta object for the attribute list '{@link org.omg.spec.bpmn.di.DocumentRoot#getMixed <em>Mixed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Mixed</em>'.
	 * @see org.omg.spec.bpmn.di.DocumentRoot#getMixed()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_Mixed();

	/**
	 * Returns the meta object for the map '{@link org.omg.spec.bpmn.di.DocumentRoot#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>XMLNS Prefix Map</em>'.
	 * @see org.omg.spec.bpmn.di.DocumentRoot#getXMLNSPrefixMap()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_XMLNSPrefixMap();

	/**
	 * Returns the meta object for the map '{@link org.omg.spec.bpmn.di.DocumentRoot#getXSISchemaLocation <em>XSI Schema Location</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>XSI Schema Location</em>'.
	 * @see org.omg.spec.bpmn.di.DocumentRoot#getXSISchemaLocation()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_XSISchemaLocation();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.di.DocumentRoot#getBPMNDiagram <em>BPMN Diagram</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>BPMN Diagram</em>'.
	 * @see org.omg.spec.bpmn.di.DocumentRoot#getBPMNDiagram()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_BPMNDiagram();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.di.DocumentRoot#getBPMNEdge <em>BPMN Edge</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>BPMN Edge</em>'.
	 * @see org.omg.spec.bpmn.di.DocumentRoot#getBPMNEdge()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_BPMNEdge();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.di.DocumentRoot#getBPMNLabel <em>BPMN Label</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>BPMN Label</em>'.
	 * @see org.omg.spec.bpmn.di.DocumentRoot#getBPMNLabel()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_BPMNLabel();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.di.DocumentRoot#getBPMNLabelStyle <em>BPMN Label Style</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>BPMN Label Style</em>'.
	 * @see org.omg.spec.bpmn.di.DocumentRoot#getBPMNLabelStyle()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_BPMNLabelStyle();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.di.DocumentRoot#getBPMNPlane <em>BPMN Plane</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>BPMN Plane</em>'.
	 * @see org.omg.spec.bpmn.di.DocumentRoot#getBPMNPlane()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_BPMNPlane();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.di.DocumentRoot#getBPMNShape <em>BPMN Shape</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>BPMN Shape</em>'.
	 * @see org.omg.spec.bpmn.di.DocumentRoot#getBPMNShape()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_BPMNShape();

	/**
	 * Returns the meta object for enum '{@link org.omg.spec.bpmn.di.MessageVisibleKind <em>Message Visible Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Message Visible Kind</em>'.
	 * @see org.omg.spec.bpmn.di.MessageVisibleKind
	 * @generated
	 */
	EEnum getMessageVisibleKind();

	/**
	 * Returns the meta object for enum '{@link org.omg.spec.bpmn.di.ParticipantBandKind <em>Participant Band Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Participant Band Kind</em>'.
	 * @see org.omg.spec.bpmn.di.ParticipantBandKind
	 * @generated
	 */
	EEnum getParticipantBandKind();

	/**
	 * Returns the meta object for data type '{@link org.omg.spec.bpmn.di.MessageVisibleKind <em>Message Visible Kind Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Message Visible Kind Object</em>'.
	 * @see org.omg.spec.bpmn.di.MessageVisibleKind
	 * @model instanceClass="org.omg.spec.bpmn.di.MessageVisibleKind"
	 *        extendedMetaData="name='MessageVisibleKind:Object' baseType='MessageVisibleKind'"
	 * @generated
	 */
	EDataType getMessageVisibleKindObject();

	/**
	 * Returns the meta object for data type '{@link org.omg.spec.bpmn.di.ParticipantBandKind <em>Participant Band Kind Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Participant Band Kind Object</em>'.
	 * @see org.omg.spec.bpmn.di.ParticipantBandKind
	 * @model instanceClass="org.omg.spec.bpmn.di.ParticipantBandKind"
	 *        extendedMetaData="name='ParticipantBandKind:Object' baseType='ParticipantBandKind'"
	 * @generated
	 */
	EDataType getParticipantBandKindObject();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	DiFactory getDiFactory();

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
		 * The meta object literal for the '{@link org.omg.spec.bpmn.di.impl.BPMNDiagramImpl <em>BPMN Diagram</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.di.impl.BPMNDiagramImpl
		 * @see org.omg.spec.bpmn.di.impl.DiPackageImpl#getBPMNDiagram()
		 * @generated
		 */
		EClass BPMN_DIAGRAM = eINSTANCE.getBPMNDiagram();

		/**
		 * The meta object literal for the '<em><b>BPMN Plane</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BPMN_DIAGRAM__BPMN_PLANE = eINSTANCE.getBPMNDiagram_BPMNPlane();

		/**
		 * The meta object literal for the '<em><b>BPMN Label Style</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BPMN_DIAGRAM__BPMN_LABEL_STYLE = eINSTANCE.getBPMNDiagram_BPMNLabelStyle();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.di.impl.BPMNEdgeImpl <em>BPMN Edge</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.di.impl.BPMNEdgeImpl
		 * @see org.omg.spec.bpmn.di.impl.DiPackageImpl#getBPMNEdge()
		 * @generated
		 */
		EClass BPMN_EDGE = eINSTANCE.getBPMNEdge();

		/**
		 * The meta object literal for the '<em><b>BPMN Label</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BPMN_EDGE__BPMN_LABEL = eINSTANCE.getBPMNEdge_BPMNLabel();

		/**
		 * The meta object literal for the '<em><b>Bpmn Element</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BPMN_EDGE__BPMN_ELEMENT = eINSTANCE.getBPMNEdge_BpmnElement();

		/**
		 * The meta object literal for the '<em><b>Message Visible Kind</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BPMN_EDGE__MESSAGE_VISIBLE_KIND = eINSTANCE.getBPMNEdge_MessageVisibleKind();

		/**
		 * The meta object literal for the '<em><b>Source Element</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BPMN_EDGE__SOURCE_ELEMENT = eINSTANCE.getBPMNEdge_SourceElement();

		/**
		 * The meta object literal for the '<em><b>Target Element</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BPMN_EDGE__TARGET_ELEMENT = eINSTANCE.getBPMNEdge_TargetElement();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.di.impl.BPMNLabelImpl <em>BPMN Label</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.di.impl.BPMNLabelImpl
		 * @see org.omg.spec.bpmn.di.impl.DiPackageImpl#getBPMNLabel()
		 * @generated
		 */
		EClass BPMN_LABEL = eINSTANCE.getBPMNLabel();

		/**
		 * The meta object literal for the '<em><b>Label Style</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BPMN_LABEL__LABEL_STYLE = eINSTANCE.getBPMNLabel_LabelStyle();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.di.impl.BPMNLabelStyleImpl <em>BPMN Label Style</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.di.impl.BPMNLabelStyleImpl
		 * @see org.omg.spec.bpmn.di.impl.DiPackageImpl#getBPMNLabelStyle()
		 * @generated
		 */
		EClass BPMN_LABEL_STYLE = eINSTANCE.getBPMNLabelStyle();

		/**
		 * The meta object literal for the '<em><b>Font</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BPMN_LABEL_STYLE__FONT = eINSTANCE.getBPMNLabelStyle_Font();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.di.impl.BPMNPlaneImpl <em>BPMN Plane</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.di.impl.BPMNPlaneImpl
		 * @see org.omg.spec.bpmn.di.impl.DiPackageImpl#getBPMNPlane()
		 * @generated
		 */
		EClass BPMN_PLANE = eINSTANCE.getBPMNPlane();

		/**
		 * The meta object literal for the '<em><b>Bpmn Element</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BPMN_PLANE__BPMN_ELEMENT = eINSTANCE.getBPMNPlane_BpmnElement();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.di.impl.BPMNShapeImpl <em>BPMN Shape</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.di.impl.BPMNShapeImpl
		 * @see org.omg.spec.bpmn.di.impl.DiPackageImpl#getBPMNShape()
		 * @generated
		 */
		EClass BPMN_SHAPE = eINSTANCE.getBPMNShape();

		/**
		 * The meta object literal for the '<em><b>BPMN Label</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BPMN_SHAPE__BPMN_LABEL = eINSTANCE.getBPMNShape_BPMNLabel();

		/**
		 * The meta object literal for the '<em><b>Bpmn Element</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BPMN_SHAPE__BPMN_ELEMENT = eINSTANCE.getBPMNShape_BpmnElement();

		/**
		 * The meta object literal for the '<em><b>Choreography Activity Shape</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BPMN_SHAPE__CHOREOGRAPHY_ACTIVITY_SHAPE = eINSTANCE.getBPMNShape_ChoreographyActivityShape();

		/**
		 * The meta object literal for the '<em><b>Is Expanded</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BPMN_SHAPE__IS_EXPANDED = eINSTANCE.getBPMNShape_IsExpanded();

		/**
		 * The meta object literal for the '<em><b>Is Horizontal</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BPMN_SHAPE__IS_HORIZONTAL = eINSTANCE.getBPMNShape_IsHorizontal();

		/**
		 * The meta object literal for the '<em><b>Is Marker Visible</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BPMN_SHAPE__IS_MARKER_VISIBLE = eINSTANCE.getBPMNShape_IsMarkerVisible();

		/**
		 * The meta object literal for the '<em><b>Is Message Visible</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BPMN_SHAPE__IS_MESSAGE_VISIBLE = eINSTANCE.getBPMNShape_IsMessageVisible();

		/**
		 * The meta object literal for the '<em><b>Participant Band Kind</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BPMN_SHAPE__PARTICIPANT_BAND_KIND = eINSTANCE.getBPMNShape_ParticipantBandKind();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.di.impl.DocumentRootImpl <em>Document Root</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.di.impl.DocumentRootImpl
		 * @see org.omg.spec.bpmn.di.impl.DiPackageImpl#getDocumentRoot()
		 * @generated
		 */
		EClass DOCUMENT_ROOT = eINSTANCE.getDocumentRoot();

		/**
		 * The meta object literal for the '<em><b>Mixed</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT_ROOT__MIXED = eINSTANCE.getDocumentRoot_Mixed();

		/**
		 * The meta object literal for the '<em><b>XMLNS Prefix Map</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__XMLNS_PREFIX_MAP = eINSTANCE.getDocumentRoot_XMLNSPrefixMap();

		/**
		 * The meta object literal for the '<em><b>XSI Schema Location</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__XSI_SCHEMA_LOCATION = eINSTANCE.getDocumentRoot_XSISchemaLocation();

		/**
		 * The meta object literal for the '<em><b>BPMN Diagram</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__BPMN_DIAGRAM = eINSTANCE.getDocumentRoot_BPMNDiagram();

		/**
		 * The meta object literal for the '<em><b>BPMN Edge</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__BPMN_EDGE = eINSTANCE.getDocumentRoot_BPMNEdge();

		/**
		 * The meta object literal for the '<em><b>BPMN Label</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__BPMN_LABEL = eINSTANCE.getDocumentRoot_BPMNLabel();

		/**
		 * The meta object literal for the '<em><b>BPMN Label Style</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__BPMN_LABEL_STYLE = eINSTANCE.getDocumentRoot_BPMNLabelStyle();

		/**
		 * The meta object literal for the '<em><b>BPMN Plane</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__BPMN_PLANE = eINSTANCE.getDocumentRoot_BPMNPlane();

		/**
		 * The meta object literal for the '<em><b>BPMN Shape</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__BPMN_SHAPE = eINSTANCE.getDocumentRoot_BPMNShape();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.di.MessageVisibleKind <em>Message Visible Kind</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.di.MessageVisibleKind
		 * @see org.omg.spec.bpmn.di.impl.DiPackageImpl#getMessageVisibleKind()
		 * @generated
		 */
		EEnum MESSAGE_VISIBLE_KIND = eINSTANCE.getMessageVisibleKind();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.di.ParticipantBandKind <em>Participant Band Kind</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.di.ParticipantBandKind
		 * @see org.omg.spec.bpmn.di.impl.DiPackageImpl#getParticipantBandKind()
		 * @generated
		 */
		EEnum PARTICIPANT_BAND_KIND = eINSTANCE.getParticipantBandKind();

		/**
		 * The meta object literal for the '<em>Message Visible Kind Object</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.di.MessageVisibleKind
		 * @see org.omg.spec.bpmn.di.impl.DiPackageImpl#getMessageVisibleKindObject()
		 * @generated
		 */
		EDataType MESSAGE_VISIBLE_KIND_OBJECT = eINSTANCE.getMessageVisibleKindObject();

		/**
		 * The meta object literal for the '<em>Participant Band Kind Object</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.di.ParticipantBandKind
		 * @see org.omg.spec.bpmn.di.impl.DiPackageImpl#getParticipantBandKindObject()
		 * @generated
		 */
		EDataType PARTICIPANT_BAND_KIND_OBJECT = eINSTANCE.getParticipantBandKindObject();

	}

} //DiPackage
