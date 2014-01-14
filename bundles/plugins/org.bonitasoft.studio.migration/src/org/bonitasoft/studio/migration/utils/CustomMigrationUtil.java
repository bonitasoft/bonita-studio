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


import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.edapt.migration.Instance;
import org.eclipse.emf.edapt.migration.Model;
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
	public static Instance createShape(Model model, Instance element, int typeID){
	Instance newXorGatewayShape = model.newInstance(NotationPackage.Literals.SHAPE);
	newXorGatewayShape.set(NotationPackage.Literals.VIEW__TYPE,String.valueOf(typeID));
	newXorGatewayShape.set(NotationPackage.Literals.VIEW__ELEMENT, element);
	return newXorGatewayShape;
	}
	
	
	/** Return true if one if the sequence flow in the list has a condition
	 * 
	 * @param outgoingConnections
	 * @return
	 */
	public static boolean hasAtLeastOneCondition(EList<Instance> outgoingConnections) {
		for(Instance outgoing : outgoingConnections){
			if(outgoing.get("condition")!=null ){
				Instance transition = outgoing.get("condition");
				String condExpr = transition.get("content");
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
	public static void migrateToInclusiveGateway(Instance gateway, Model model, String theViewType, int visualID){
		String getUid = gateway.getUuid();
		gateway.migrate("process.InclusiveGateway");
	
	    for(Instance shape :model.getAllInstances(NotationPackage.Literals.SHAPE) ){
	        String viewType = shape.get(NotationPackage.Literals.VIEW__TYPE);
	        Object shapeObject = shape.get("element");
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
	public static Instance createConnector(Model model, Instance modelInstance, Instance sourceShape, Instance targetShape){
		Instance newConnectorShape = model.newInstance(NotationPackage.Literals.CONNECTOR);
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
	public static Instance createBounds(Model model, int x, int y){
		Instance newBoundsGateway = model.newInstance(NotationPackage.Literals.BOUNDS);
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
	public static Instance createDecorationNode(Model model, Instance modelInstance, Instance location, boolean visible, int typeID){
		Instance newDecorationXorGateway = model.newInstance(NotationPackage.Literals.DECORATION_NODE);
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
	public static Instance createXORDecorationNode(Model model, Instance modelInstance, Instance location, boolean visible){
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
	public static Instance createINCDecorationNode(Model model, Instance modelInstance, Instance location, boolean visible){
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
	public static Instance createSequenceFlow(Model model, String prefix, Instance source, Instance target){
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
	public static Instance createSequenceFlow(Model model, String prefix, Instance source, Instance target, int index){
		Instance newSequenceFlow = model.newInstance("process.SequenceFlow");
		newSequenceFlow.set("name", prefix+"_add_migration_transition"+(index<0 ? "" : "_"+index));
		newSequenceFlow.set("source", source);
		newSequenceFlow.set("target", target);
		
		Instance decisionTable = model.newInstance("process.decision.DecisionTable");
		newSequenceFlow.set("decisionTable", decisionTable);
		return newSequenceFlow;
	}

	/** Get the instance of a connector from the original sequenceflowof the model
	 * 
	 * @param instance
	 * @param model
	 * @return
	 */
	public static Instance getConnectorInstance(Instance instance, Model model){
		String instanceUuid = instance.getUuid();		
		for(Instance connector :model.getAllInstances(NotationPackage.Literals.CONNECTOR)){		
	        Object shapeObject = connector.get("element");
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
	public static Instance getShapeInstance(Instance instance, Model model){
		String instanceUuid = instance.getUuid();
		if(instanceUuid!=null){
			for(Instance shape :model.getAllInstances(NotationPackage.Literals.SHAPE)){

				Object shapeObject = shape.get("element");
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
	public static Instance getDecorationNodeOfPoolOrLaneInstance(Instance instance, Model model){
		String instanceUuid = instance.getUuid();
		for(Instance shape :model.getAllInstances(NotationPackage.Literals.DECORATION_NODE)){
	        Object shapeObject = shape.get("element");
	        Object shapeID = shape.get(NotationPackage.Literals.VIEW__TYPE);
	        String shapeObjectUuid = ((Instance)shapeObject).getUuid();
			if(shapeObject!=null && shapeObjectUuid.equals(instanceUuid) && ( shapeID.equals(String.valueOf(7001)) || shapeID.equals(String.valueOf(7002) ))){
				return shape;
			}
		}
		return null;
	}

}
