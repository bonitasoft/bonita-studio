/**
 * Copyright (C) 2013 BonitaSoft S.A.
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
package org.bonitasoft.studio.migration.utils;


import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edapt.spi.migration.Instance;
import org.eclipse.emf.edapt.spi.migration.Model;
import org.eclipse.gmf.runtime.notation.NotationPackage;

/**
 * @author Florine Boudin
 *
 */
public class CustomMigrationUtil {

	public static final int ACTUAL_INCLUSIVE_GATEWAY_VISUAL_ID = 3051;
	public static final int ACTUAL_XOR_GATEWAY_VISUAL_ID = 3008;
	public static final int ACTUAL_AND_GATEWAY_VISUAL_ID = 3009;
	public static final int ACTUAL_SEQUENCEFLOW_VISUAL_ID = 4001;


	public static final int DISTANCE_BETWEEN_GATEWAY = 60;
	public static final int DECORATION_NODE_LOCATION_Y = -10;
	public static final boolean SHOW_DECORATION_NODE = true;
	/**
	 *
	 */
	public CustomMigrationUtil() {
		// TODO Auto-generated constructor stub
	}


	/** Create a new Shape from an instance , with the VisualID.
	 *
	 * @param model
	 * @param element Instance related to this new shape
	 * @param typeID Visual ID of the Edit Part
	 * @return
	 */
	public static Instance createShape(final Model model, final Instance element, final int typeID){
	final Instance newXorGatewayShape = model.newInstance(NotationPackage.Literals.SHAPE);
	newXorGatewayShape.set(NotationPackage.Literals.VIEW__TYPE,String.valueOf(typeID));
	newXorGatewayShape.set(NotationPackage.Literals.VIEW__ELEMENT, element);
	return newXorGatewayShape;
	}


	/** Return true if one if the sequence flow in the list has a condition
	 *
	 * @param outgoingConnections
	 * @return
	 */
	public static boolean hasAtLeastOneCondition(final EList<Instance> outgoingConnections) {
		for(final Instance outgoing : outgoingConnections){
			if(outgoing.get("condition")!=null ){
				final Instance transition = outgoing.get("condition");
				final String condExpr = transition.get("content");
				return !condExpr.isEmpty();
			}
		}
		return false;
	}

	/** Change a gateway to an Inclusive Gateway
	 *
	 * @param gateway
	 * @param model
	 * @param theViewType
	 * @param visualID
	 */
	public static void migrateToInclusiveGateway(final Instance gateway, final Model model, final String theViewType, final int visualID){
		final String getUid = gateway.getUuid();
		gateway.migrate("process.InclusiveGateway");

	    for(final Instance shape :model.getAllInstances(NotationPackage.Literals.SHAPE) ){
	        final String viewType = shape.get(NotationPackage.Literals.VIEW__TYPE);
	        final Object shapeObject = shape.get("element");
	        if(theViewType.equals(viewType) && ((Instance)shapeObject).getUuid().equals(getUid)){
	            shape.set(NotationPackage.Literals.VIEW__TYPE,String.valueOf(visualID));
	            return;
	        }
	    }
	}

	/** Create a connector form a Sequence Flow Instance
	 *
	 * @param model
	 * @param modelInstance
	 * @param sourceShape
	 * @param targetShape
	 * @return
	 */
	public static Instance createConnector(final Model model, final Instance modelInstance, final Instance sourceShape, final Instance targetShape){
		final Instance newConnectorShape = model.newInstance(NotationPackage.Literals.CONNECTOR);
		newConnectorShape.set(NotationPackage.Literals.VIEW__TYPE, String.valueOf(ACTUAL_SEQUENCEFLOW_VISUAL_ID));
		newConnectorShape.set("element", modelInstance);
		newConnectorShape.set("target", targetShape);
		newConnectorShape.set("source", sourceShape);
		return newConnectorShape;
	}

	/** Create a Bounds instance with x and y values initialized
	 *
	 * @param model
	 * @param x
	 * @param y
	 * @return
	 */
	public static Instance createBounds(final Model model, final int x, final int y){
		final Instance newBoundsGateway = model.newInstance(NotationPackage.Literals.BOUNDS);
		newBoundsGateway.set(NotationPackage.Literals.LOCATION__X, x);
		newBoundsGateway.set(NotationPackage.Literals.LOCATION__Y, y);
		return newBoundsGateway;
	}

	/** Create a decoration node
	 *
	 * @param model
	 * @param modelInstance
	 * @param location Location Instance
	 * @param visible true is the name must be show, else false
	 * @return
	 */
	public static Instance createDecorationNode(final Model model, final Instance modelInstance, final Instance location, final boolean visible, final int typeID){
		final Instance newDecorationXorGateway = model.newInstance(NotationPackage.Literals.DECORATION_NODE);
		newDecorationXorGateway.set(NotationPackage.Literals.VIEW__TYPE, String.valueOf(typeID));
		newDecorationXorGateway.set(NotationPackage.Literals.VIEW__ELEMENT, modelInstance);
		newDecorationXorGateway.add(NotationPackage.Literals.NODE__LAYOUT_CONSTRAINT, location);
		newDecorationXorGateway.set(NotationPackage.Literals.VIEW__VISIBLE, visible);
		return newDecorationXorGateway;
	}

	/**
	 *
	 * @param model
	 * @param modelInstance
	 * @param location
	 * @param visible
	 * @return
	 */
	public static Instance createXORDecorationNode(final Model model, final Instance modelInstance, final Instance location, final boolean visible){
		return createDecorationNode( model,  modelInstance,  location,  visible, 5026);
	}

	/**
	 *
	 * @param model
	 * @param modelInstance
	 * @param location
	 * @param visible
	 * @return
	 */
	public static Instance createINCDecorationNode(final Model model, final Instance modelInstance, final Instance location, final boolean visible){
		return createDecorationNode( model,  modelInstance,  location,  visible, 3051);
	}

	/**
	 *
	 * @param model
	 * @param prefix
	 * @param source
	 * @param target
	 * @return
	 */
	public static Instance createSequenceFlow(final Model model, final String prefix, final Instance source, final Instance target){
		return createSequenceFlow( model,  prefix,  source,  target, -1);
	}

	/** Create a sequence flow with a prefix name, a source and a target
	 *
	 * @param model
	 * @param prefix
	 * @param source
	 * @param target
	 * @param index
	 * @return
	 */
	public static Instance createSequenceFlow(final Model model, final String prefix, final Instance source, final Instance target, final int index){
		final Instance newSequenceFlow = model.newInstance("process.SequenceFlow");
		newSequenceFlow.set("name", prefix+"_add_migration_transition"+(index<0 ? "" : "_"+index));
		newSequenceFlow.set("source", source);
		newSequenceFlow.set("target", target);

		final Instance decisionTable = model.newInstance("process.decision.DecisionTable");
		newSequenceFlow.set("decisionTable", decisionTable);
		return newSequenceFlow;
	}

	/** Get the instance of a connector from the original sequenceflowof the model
	 *
	 * @param instance
	 * @param model
	 * @return
	 */
	public static Instance getConnectorInstance(final Instance instance, final Model model){
		final String instanceUuid = instance.getUuid();
		for(final Instance connector :model.getAllInstances(NotationPackage.Literals.CONNECTOR)){
	        final Object shapeObject = connector.get("element");
			if(shapeObject!=null && ((Instance)shapeObject).getUuid().equals(instanceUuid)){
				return connector;
			}
		}
		return null;
	}

	/** Get the instance of a shape from the original instance of the model
	 *
	 * @param instance
	 * @param model
	 * @return
	 */
	public static Instance getShapeInstance(final Instance instance, final Model model){
		final String instanceUuid = instance.getUuid();
		if(instanceUuid!=null){
			for(final Instance shape :model.getAllInstances(NotationPackage.Literals.SHAPE)){

				final Object shapeObject = shape.get("element");
				if(shapeObject!=null && ((Instance)shapeObject).getUuid() !=null && ((Instance)shapeObject).getUuid().equals(instanceUuid)){
					return shape;
				}
			}
		}
		return null;
	}

	/**
	 *
	 * @param instance
	 * @param model
	 * @return
	 */
	public static Instance getDecorationNodeOfPoolOrLaneInstance(final Instance instance, final Model model){
		final String instanceUuid = instance.getUuid();
		for(final Instance shape :model.getAllInstances(NotationPackage.Literals.DECORATION_NODE)){
	        final Object shapeObject = shape.get("element");
	        final Object shapeID = shape.get(NotationPackage.Literals.VIEW__TYPE);
	        final String shapeObjectUuid = ((Instance)shapeObject).getUuid();
			if(shapeObject!=null && shapeObjectUuid.equals(instanceUuid) && ( shapeID.equals(String.valueOf(7001)) || shapeID.equals(String.valueOf(7002) ))){
				return shape;
			}
		}
		return null;
	}
	
	 public static Instance deepCopy(Instance instance) {
	        // mapping of originals to copies
	        final Map<Instance, Instance> map = new HashMap<Instance, Instance>();

	        // copy tree structure
	        return copyTree(instance, map);
	    }
	    
	    /** Copy the tree structure with an instance as root. */
	    private static Instance copyTree(Instance original, Map<Instance, Instance> map) {
	        final EClass eClass = original.getEClass();
	        final Instance copi = original.getType().getModel().newInstance(eClass);
	        for (final EReference reference : eClass.getEAllReferences()) {
	            if (reference.isContainment()) {
	                if (reference.isMany()) {
	                    for (final Instance child : original.getLinks(reference)) {
	                        copi.add(reference, copyTree(child, map));
	                    }
	                } else {
	                    final Instance child = original.get(reference);
	                    if (child != null) {
	                        copi.set(reference, copyTree(child, map));
	                    }
	                }
	            }else {
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
	        for (final EAttribute attribute : eClass.getEAllAttributes()) {
	            copi.set(attribute, original.get(attribute));
	        }
	        map.put(original, copi);
	        return copi;
	    }


}
