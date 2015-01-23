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
package org.bonitasoft.studio.importer.bar.custom.migration;


import org.bonitasoft.studio.importer.bar.i18n.Messages;
import org.bonitasoft.studio.migration.migrator.ReportCustomMigration;
import org.bonitasoft.studio.migration.utils.CustomMigrationUtil;
import org.bonitasoft.studio.model.process.diagram.edit.parts.SequenceFlowNameEditPart;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edapt.spi.migration.Instance;
import org.eclipse.emf.edapt.spi.migration.Metamodel;
import org.eclipse.emf.edapt.spi.migration.Model;
import org.eclipse.gmf.runtime.notation.NotationPackage;

/**
 * @author Florine Boudin
 *
 */
public class ParallelGatewayMigration extends ReportCustomMigration {

	public static final String OLD_ANDGATEWAY_VIEW_TYPE = "3009";


	/**
	 * 
	 */
	public ParallelGatewayMigration() {
	}

	@Override
	public void migrateBefore(Model model, Metamodel metamodel) throws MigrationException {

	}

	@Override
	public void migrateAfter(Model model, Metamodel metamodel) throws MigrationException {



		for(Instance andGateway : model.getAllInstances("process.ANDGateway")){

			Instance gatewayContainer = andGateway.getContainer();
			Instance poolContainer = gatewayContainer.instanceOf("process.Pool") ? gatewayContainer : gatewayContainer.getContainer();
			Instance gatewayContainerShape = CustomMigrationUtil.getDecorationNodeOfPoolOrLaneInstance(gatewayContainer, model);
			String andGatewayName = andGateway.get("name");
			Instance andGatewayShape = CustomMigrationUtil.getShapeInstance(andGateway, model);

			EList<Instance> outgoingConnections = andGateway.get("outgoing");
			EList<Instance> incomingConnections = andGateway.get("incoming");

			if(outgoingConnections!=null && incomingConnections!=null){

				boolean outgoingConnectionHasAtLeastOneCondition = CustomMigrationUtil.hasAtLeastOneCondition(outgoingConnections);
				boolean incomingConnectionHasAtLeastOneCondition = CustomMigrationUtil.hasAtLeastOneCondition(incomingConnections);

				// Instance Diagram Process
				Instance diagramProcess = null;
				for(Instance diagram : model.getAllInstances(NotationPackage.Literals.DIAGRAM)){
					String diagramType = diagram.get(NotationPackage.Literals.VIEW__TYPE);
					if(diagramType.equals("Process")){
						diagramProcess = diagram;
						break;
					}
				}

				// SIMPLE INCOMING
				if(incomingConnections.size()==1 ){


					// SIMPLE INCOMING WITH CONDITION
					if(incomingConnectionHasAtLeastOneCondition){

						CustomMigrationUtil.migrateToInclusiveGateway(andGateway,  model, ParallelGatewayMigration.OLD_ANDGATEWAY_VIEW_TYPE, CustomMigrationUtil.ACTUAL_INCLUSIVE_GATEWAY_VISUAL_ID);
						addReportChange(andGatewayName, andGateway.getEClass().getName(), andGateway.getUuid(), Messages.changeGatewayTypeToInclusiveGatewayMigrationDescription, Messages.generalProperty, IStatus.WARNING);

					}else{ // SIMPLE INCOMING WITHOUT CONDITION

						if(outgoingConnectionHasAtLeastOneCondition){
							// SINGLE OUTGOING
							if(outgoingConnections.size()==1 ){
								CustomMigrationUtil.migrateToInclusiveGateway(andGateway,  model, ParallelGatewayMigration.OLD_ANDGATEWAY_VIEW_TYPE, CustomMigrationUtil.ACTUAL_INCLUSIVE_GATEWAY_VISUAL_ID);
								addReportChange(andGatewayName, andGateway.getEClass().getName(), andGateway.getUuid(), Messages.changeGatewayTypeToInclusiveGatewayMigrationDescription, Messages.generalProperty, IStatus.WARNING);

								//MULTIPLE OUTGOING
							}else if(outgoingConnections.size()>1 ){
								CustomMigrationUtil.migrateToInclusiveGateway(andGateway,  model, ParallelGatewayMigration.OLD_ANDGATEWAY_VIEW_TYPE, CustomMigrationUtil.ACTUAL_INCLUSIVE_GATEWAY_VISUAL_ID);
								addReportChange(andGatewayName, andGateway.getEClass().getName(), andGateway.getUuid(), Messages.changeGatewayTypeToInclusiveGatewayMigrationDescription, Messages.generalProperty, IStatus.WARNING);
							}
						}
					}


				}else if(incomingConnections.size()>1){ // ENTREE MULTIPLE


					if(incomingConnectionHasAtLeastOneCondition){// ENTREE MULTIPLE AVEC CONDITION

						addInclusiveBeforeOriginalParallelGatewayAndExclusiveGatewayForEachIncomingSequenceFlow(
								model, 
								andGateway, 
								gatewayContainer,
								poolContainer, 
								gatewayContainerShape,
								andGatewayName, 
								andGatewayShape,
								outgoingConnections, 
								incomingConnections,
								diagramProcess);
						addReportChange(andGatewayName, andGateway.getEClass().getName(), andGateway.getUuid(), Messages.changeParallelGatewayWithIncomingConditionsMigrationDescription, Messages.generalProperty, IStatus.WARNING);



					}else{
						if(outgoingConnectionHasAtLeastOneCondition){
							if(outgoingConnections.size()==1   ){// ENTREE MULTIPLE SANS CONDITION - SORTIE SIMPLE AVEC CONDITION

								addExclusiveGatewayAfterParallelGateway(	model,
										andGateway, 
										gatewayContainer,
										poolContainer, 
										gatewayContainerShape,
										andGatewayName, 
										andGatewayShape,
										outgoingConnections, 
										diagramProcess);
								addReportChange(andGatewayName, andGateway.getEClass().getName(), andGateway.getUuid(), Messages.changeParallelGatewayWithOutgoingConditionsMigrationDescription, Messages.generalProperty, IStatus.WARNING);


							}else if(outgoingConnections.size()>1){		// ENTREE MULTIPLE SANS CONDITION- SORTIE MULTIPLE AVEC CONDITION


								addInclusiveGatewayAfterParallelGateway(	model,
										andGateway,
										gatewayContainer,
										poolContainer,
										gatewayContainerShape,
										andGatewayName,
										andGatewayShape,
										outgoingConnections, 
										diagramProcess);
								addReportChange(andGatewayName, andGateway.getEClass().getName(), andGateway.getUuid(), Messages.changeParallelGatewayWithOutgoingConditionsMigrationDescription, Messages.generalProperty, IStatus.WARNING);

							}
						}
					}
				}
			}
		}
	}

	/**
	 * @param model
	 * @param andGateway
	 * @param gatewayContainer
	 * @param poolContainer
	 * @param gatewayContainerShape
	 * @param andGatewayName
	 * @param andGatewayShape
	 * @param outgoingConnections
	 * @param incomingConnections
	 * @param diagramProcess
	 */
	private void addInclusiveBeforeOriginalParallelGatewayAndExclusiveGatewayForEachIncomingSequenceFlow(
			Model model, Instance andGateway, Instance gatewayContainer,
			Instance poolContainer, Instance gatewayContainerShape,
			String andGatewayName, Instance andGatewayShape,
			EList<Instance> outgoingConnections,
			EList<Instance> incomingConnections, Instance diagramProcess) {


		// get position of the original andGateway
		int xAndGateway = ((Instance)((Instance)andGatewayShape.get(NotationPackage.Literals.NODE__LAYOUT_CONSTRAINT))).get("x");
		int yAndGateway = ((Instance)((Instance)andGatewayShape.get(NotationPackage.Literals.NODE__LAYOUT_CONSTRAINT))).get("y");



		int i=1;
		do{

			Instance incoming = incomingConnections.get(0);
			incomingConnections.remove(0);
			Instance  newIncomingEntry= model.newInstance("process.XORGateway");
			newIncomingEntry.set("name", andGatewayName+"_add_migration_XOR_"+i);

			Instance newXorGatewayShape = CustomMigrationUtil.createShape(model, newIncomingEntry, CustomMigrationUtil.ACTUAL_XOR_GATEWAY_VISUAL_ID);
			gatewayContainerShape.add(NotationPackage.Literals.VIEW__PERSISTED_CHILDREN,newXorGatewayShape);

			Instance newLocationXorGateway = model.newInstance(NotationPackage.Literals.LOCATION);
			newLocationXorGateway.set(NotationPackage.Literals.LOCATION__Y, CustomMigrationUtil.DECORATION_NODE_LOCATION_Y);



			incoming.set("target", newIncomingEntry);

			Instance newOutGoing = CustomMigrationUtil.createSequenceFlow( model, andGatewayName, newIncomingEntry, andGateway, i);

			// get position on the source shape of the andGateway
			int previousX = ((Instance)((Instance)(CustomMigrationUtil.getShapeInstance((Instance)(incoming.get("source")), model)).get(NotationPackage.Literals.NODE__LAYOUT_CONSTRAINT))).get("x");
			int previousY = ((Instance)((Instance)(CustomMigrationUtil.getShapeInstance((Instance)(incoming.get("source")), model)).get(NotationPackage.Literals.NODE__LAYOUT_CONSTRAINT))).get("y");

			// position of the new XOR shape
			int xNewXorGateway = (int)(previousX+CustomMigrationUtil.DISTANCE_BETWEEN_GATEWAY);
			int yNewXorGateway = (int)(previousY);

			gatewayContainer.add("elements", newIncomingEntry);
			poolContainer.add("connections", newOutGoing);


			Instance newBoundsXorGateway = CustomMigrationUtil.createBounds(model, xNewXorGateway, yNewXorGateway); 

			Instance newDecorationXorGateway = CustomMigrationUtil.createXORDecorationNode(model, newIncomingEntry, newLocationXorGateway, false);
			//							
			newXorGatewayShape.add(NotationPackage.Literals.VIEW__PERSISTED_CHILDREN, newDecorationXorGateway);
			newXorGatewayShape.add(NotationPackage.Literals.NODE__LAYOUT_CONSTRAINT,  newBoundsXorGateway);


			Instance newLocationSequenceFlow = model.newInstance(NotationPackage.Literals.LOCATION);
			newLocationSequenceFlow.set(NotationPackage.Literals.LOCATION__Y, CustomMigrationUtil.DECORATION_NODE_LOCATION_Y);
			Instance shape2add = CustomMigrationUtil.createConnector(model, newOutGoing, newXorGatewayShape, andGatewayShape) ;
			Instance newDecorationSequenceFlow = CustomMigrationUtil.createDecorationNode(model, newOutGoing, newLocationSequenceFlow, false, SequenceFlowNameEditPart.VISUAL_ID);


			Instance shapeBendPoints = model.newInstance(NotationPackage.Literals.RELATIVE_BENDPOINTS);

			//shapeBendPoints.
			shape2add.add(NotationPackage.Literals.EDGE__BENDPOINTS, shapeBendPoints);
			shape2add.add(NotationPackage.Literals.VIEW__PERSISTED_CHILDREN, newDecorationSequenceFlow);

			if(diagramProcess!=null){
				diagramProcess.add(NotationPackage.Literals.DIAGRAM__PERSISTED_EDGES, shape2add);
			}



			i++;

		}while(incomingConnections.size()>0);


		// Add the Includsive gateway
		Instance newInclusiveGateway = model.newInstance("process.InclusiveGateway");
		newInclusiveGateway.set("name", andGatewayName+"_add_migration_INC");

		Instance newIncGatewayShape = CustomMigrationUtil.createShape(model, newInclusiveGateway, CustomMigrationUtil.ACTUAL_INCLUSIVE_GATEWAY_VISUAL_ID);
		gatewayContainerShape.add(NotationPackage.Literals.VIEW__PERSISTED_CHILDREN,newIncGatewayShape);


		do{
			Instance output = outgoingConnections.get(0);
			outgoingConnections.remove(0);
			output.set("source", newInclusiveGateway);

			Instance outputShape = CustomMigrationUtil.getConnectorInstance(output, model);
			outputShape.set("source", newIncGatewayShape);

		}while(outgoingConnections.size()>0);



		Instance newLocationIncGateway = model.newInstance(NotationPackage.Literals.LOCATION);
		newLocationIncGateway.set(NotationPackage.Literals.LOCATION__Y, CustomMigrationUtil.DECORATION_NODE_LOCATION_Y);




		int xNewXorGate = (int)(xAndGateway+CustomMigrationUtil.DISTANCE_BETWEEN_GATEWAY);
		int yNewXorGate = (int)(yAndGateway);

		Instance newBoundsIncGateway = CustomMigrationUtil.createBounds(model, xNewXorGate, yNewXorGate) ;

		Instance newDecorationIncGateway = CustomMigrationUtil.createXORDecorationNode(model, newInclusiveGateway, newLocationIncGateway, CustomMigrationUtil.SHOW_DECORATION_NODE);
		//						
		newIncGatewayShape.add(NotationPackage.Literals.VIEW__PERSISTED_CHILDREN, newDecorationIncGateway);
		newIncGatewayShape.add(NotationPackage.Literals.NODE__LAYOUT_CONSTRAINT,  newBoundsIncGateway);


		Instance sf = CustomMigrationUtil.createSequenceFlow(model, andGatewayName, andGateway, newInclusiveGateway);

		Instance newLocationSequenceFlow = model.newInstance(NotationPackage.Literals.LOCATION);
		newLocationSequenceFlow.set(NotationPackage.Literals.LOCATION__Y, CustomMigrationUtil.DECORATION_NODE_LOCATION_Y);
		Instance newDecorationSequenceFlow = CustomMigrationUtil.createDecorationNode(model, sf, newLocationSequenceFlow, CustomMigrationUtil.SHOW_DECORATION_NODE, SequenceFlowNameEditPart.VISUAL_ID);

		Instance sfShape = CustomMigrationUtil.createConnector(model, sf, andGatewayShape, newIncGatewayShape);

		Instance shapeBendPoints = model.newInstance(NotationPackage.Literals.RELATIVE_BENDPOINTS);

		//shapeBendPoints.
		sfShape.add(NotationPackage.Literals.EDGE__BENDPOINTS, shapeBendPoints);
		sfShape.add(NotationPackage.Literals.VIEW__PERSISTED_CHILDREN, newDecorationSequenceFlow);

		if(diagramProcess!=null){
			diagramProcess.add(NotationPackage.Literals.DIAGRAM__PERSISTED_EDGES, sfShape);
		}

		gatewayContainer.add("elements", newInclusiveGateway);
		poolContainer.add("connections", sf);

		for(Instance outGoingFromAndGateway : model.getAllInstances(NotationPackage.Literals.CONNECTOR)){
			if(((Instance)outGoingFromAndGateway.get("source"))!=null && ((Instance)outGoingFromAndGateway.get("source")).equals(andGatewayShape)){
				outGoingFromAndGateway.set("source", newIncGatewayShape);
				break;
			}
		}
	}

	/**
	 * @param model
	 * @param andGateway
	 * @param gatewayContainer
	 * @param poolContainer
	 * @param gatewayContainerShape
	 * @param andGatewayName
	 * @param andGatewayShape
	 * @param outgoingConnections
	 * @param diagramProcess
	 */
	private void addExclusiveGatewayAfterParallelGateway(Model model,
			Instance andGateway, Instance gatewayContainer,
			Instance poolContainer, Instance gatewayContainerShape,
			String andGatewayName, Instance andGatewayShape,
			EList<Instance> outgoingConnections, Instance diagramProcess) {
		// ENTREE MULTIPLE SANS CONDITION- SORTIE SIMPLE AVEC CONDITION


		/* IN THE MODEL */
		Instance newXorGateway = model.newInstance("process.XORGateway");
		newXorGateway.set("name", andGatewayName+"_add_migration_XOR");
		gatewayContainer.add("elements", newXorGateway);

		Instance newXorGatewayShape = CustomMigrationUtil.createShape(model, newXorGateway, CustomMigrationUtil.ACTUAL_XOR_GATEWAY_VISUAL_ID);
		gatewayContainerShape.add(NotationPackage.Literals.VIEW__PERSISTED_CHILDREN,newXorGatewayShape);

		Instance newLocationXorGateway = model.newInstance(NotationPackage.Literals.LOCATION);
		newLocationXorGateway.set(NotationPackage.Literals.LOCATION__Y, CustomMigrationUtil.DECORATION_NODE_LOCATION_Y);

		//int posX = Math.abs(andGateway.get(feature))

		int xAndGateway =0;
		int yAndGateway =0;

		if(((Instance)(andGatewayShape.get(NotationPackage.Literals.NODE__LAYOUT_CONSTRAINT))).instanceOf(NotationPackage.Literals.BOUNDS)){
			xAndGateway=((Instance)((Instance)andGatewayShape.get(NotationPackage.Literals.NODE__LAYOUT_CONSTRAINT))).get("x");
			yAndGateway=((Instance)((Instance)andGatewayShape.get(NotationPackage.Literals.NODE__LAYOUT_CONSTRAINT))).get("y");
		}

		Instance outGoing = outgoingConnections.get(0);

		int newLocationX = (int)xAndGateway +CustomMigrationUtil.DISTANCE_BETWEEN_GATEWAY;
		int newLocationY = (int)yAndGateway ;

		Instance newBoundsXorGateway = CustomMigrationUtil.createBounds(model, newLocationX, newLocationY); 

		Instance newDecorationXorGateway = CustomMigrationUtil.createXORDecorationNode(model, newXorGateway, newLocationXorGateway, CustomMigrationUtil.SHOW_DECORATION_NODE);
		//							
		newXorGatewayShape.add(NotationPackage.Literals.VIEW__PERSISTED_CHILDREN, newDecorationXorGateway);
		newXorGatewayShape.add(NotationPackage.Literals.NODE__LAYOUT_CONSTRAINT,  newBoundsXorGateway);

		outgoingConnections.remove(0);
		outGoing.set("source", newXorGateway);

		Instance outGoingShape = CustomMigrationUtil.getConnectorInstance(outGoing, model);
		outGoingShape.set("source",newXorGatewayShape);

		Instance newSequenceFlow = CustomMigrationUtil.createSequenceFlow(model, andGatewayName, andGateway, newXorGateway);

		poolContainer.add("connections", newSequenceFlow);

		/* IN THE GRAPHIC */
		Instance newLocationSequenceFlow = model.newInstance(NotationPackage.Literals.LOCATION);
		newLocationSequenceFlow.set(NotationPackage.Literals.LOCATION__Y, CustomMigrationUtil.DECORATION_NODE_LOCATION_Y);
		Instance newDecorationSequenceFlow = CustomMigrationUtil.createDecorationNode(model, newSequenceFlow, newLocationSequenceFlow, CustomMigrationUtil.SHOW_DECORATION_NODE, SequenceFlowNameEditPart.VISUAL_ID);

		Instance shape2add = CustomMigrationUtil.createConnector(model, newSequenceFlow, andGatewayShape, newXorGatewayShape);

		Instance shapeBendPoints = model.newInstance(NotationPackage.Literals.RELATIVE_BENDPOINTS);

		//shapeBendPoints.
		shape2add.add(NotationPackage.Literals.EDGE__BENDPOINTS, shapeBendPoints);
		shape2add.add(NotationPackage.Literals.VIEW__PERSISTED_CHILDREN, newDecorationSequenceFlow);


		if(diagramProcess!=null){
			diagramProcess.add(NotationPackage.Literals.DIAGRAM__PERSISTED_EDGES, shape2add);
		}
	}

	/**
	 * @param model
	 * @param andGateway
	 * @param gatewayContainer
	 * @param poolContainer
	 * @param gatewayContainerShape
	 * @param andGatewayName
	 * @param andGatewayShape
	 * @param outgoingConnections
	 * @param diagramProcess
	 */
	private void addInclusiveGatewayAfterParallelGateway(Model model,
			Instance andGateway, Instance gatewayContainer,
			Instance poolContainer, Instance gatewayContainerShape,
			String andGatewayName, Instance andGatewayShape,
			EList<Instance> outgoingConnections, Instance diagramProcess) {


		Instance newIncGateway = model.newInstance("process.InclusiveGateway");
		newIncGateway.set("name", andGatewayName+"_add_migration_INC");

		Instance newIncGatewayShape = CustomMigrationUtil.createShape(model, newIncGateway, CustomMigrationUtil.ACTUAL_INCLUSIVE_GATEWAY_VISUAL_ID);
		gatewayContainerShape.add(NotationPackage.Literals.VIEW__PERSISTED_CHILDREN,newIncGatewayShape);

		Instance newLocationIncGateway = model.newInstance(NotationPackage.Literals.LOCATION);
		newLocationIncGateway.set(NotationPackage.Literals.LOCATION__Y, CustomMigrationUtil.DECORATION_NODE_LOCATION_Y);



		int xAndGateway =0;
		int yAndGateway =0;

		if(((Instance)(andGatewayShape.get(NotationPackage.Literals.NODE__LAYOUT_CONSTRAINT))).instanceOf(NotationPackage.Literals.BOUNDS)){
			xAndGateway=((Instance)((Instance)andGatewayShape.get(NotationPackage.Literals.NODE__LAYOUT_CONSTRAINT))).get("x");
			yAndGateway=((Instance)((Instance)andGatewayShape.get(NotationPackage.Literals.NODE__LAYOUT_CONSTRAINT))).get("y");
		}

		do{
			Instance outGoing = outgoingConnections.get(0);
			outgoingConnections.remove(0);
			outGoing.set("source", newIncGateway);

		}while(outgoingConnections.size()>0);


		int newLocationX = (int)xAndGateway +CustomMigrationUtil.DISTANCE_BETWEEN_GATEWAY;
		int newLocationY = (int)yAndGateway ;

		Instance newBoundsIncGateway = CustomMigrationUtil.createBounds(model, newLocationX, newLocationY); 

		newIncGatewayShape.add(NotationPackage.Literals.NODE__LAYOUT_CONSTRAINT,  newBoundsIncGateway);


		Instance newDecorationIncGateway = CustomMigrationUtil.createINCDecorationNode(model, newIncGateway, newLocationIncGateway, CustomMigrationUtil.SHOW_DECORATION_NODE);
		newIncGatewayShape.add(NotationPackage.Literals.VIEW__PERSISTED_CHILDREN, newDecorationIncGateway);


		gatewayContainer.add("elements", newIncGateway);

		Instance newSequenceFlow =  CustomMigrationUtil.createSequenceFlow( model, andGatewayName, andGateway, newIncGateway);
		poolContainer.add("connections", newSequenceFlow);


		for(Instance outGoingFromAndGateway : model.getAllInstances(NotationPackage.Literals.CONNECTOR)){
			if(((Instance)outGoingFromAndGateway.get("source"))!=null && ((Instance)outGoingFromAndGateway.get("source")).equals(andGatewayShape)){
				outGoingFromAndGateway.set("source", newIncGatewayShape);
			}
		}


		Instance newLocationSequenceFlow = model.newInstance(NotationPackage.Literals.LOCATION);
		newLocationSequenceFlow.set(NotationPackage.Literals.LOCATION__Y, CustomMigrationUtil.DECORATION_NODE_LOCATION_Y);
		Instance newDecorationSequenceFlow = CustomMigrationUtil.createDecorationNode(model, newSequenceFlow, newLocationSequenceFlow, CustomMigrationUtil.SHOW_DECORATION_NODE, SequenceFlowNameEditPart.VISUAL_ID);


		Instance shape2add = CustomMigrationUtil.createConnector(model, newSequenceFlow, andGatewayShape, newIncGatewayShape);


		Instance shapeBendPoints = model.newInstance(NotationPackage.Literals.RELATIVE_BENDPOINTS);
		shape2add.add(NotationPackage.Literals.EDGE__BENDPOINTS, shapeBendPoints);
		shape2add.add(NotationPackage.Literals.VIEW__PERSISTED_CHILDREN, newDecorationSequenceFlow);
		if(diagramProcess!=null){
			diagramProcess.add(NotationPackage.Literals.DIAGRAM__PERSISTED_EDGES, shape2add);
		}
	}
}
