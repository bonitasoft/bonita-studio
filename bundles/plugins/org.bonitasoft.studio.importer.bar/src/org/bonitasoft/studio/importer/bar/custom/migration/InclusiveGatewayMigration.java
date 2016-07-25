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
public class InclusiveGatewayMigration extends ReportCustomMigration {

	/**
	 * 
	 */
	public InclusiveGatewayMigration() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	@Override
	public void migrateBefore(Model model, Metamodel metamodel) throws MigrationException {


	}
	/**
	 * 
	 */
	@Override
	public void migrateAfter(Model model, Metamodel metamodel) throws MigrationException {



		for(Instance inclusiveGateway : model.getAllInstances("process.InclusiveGateway")){

			Instance gatewayContainer = inclusiveGateway.getContainer();
			String inclusiveGatewayName = inclusiveGateway.get("name");
			Instance inclusiveGatewayShape = CustomMigrationUtil.getShapeInstance(inclusiveGateway, model);
			Instance poolContainer = gatewayContainer.instanceOf("process.Pool") ? gatewayContainer : gatewayContainer.getContainer();
			Instance gatewayContainerShape = CustomMigrationUtil.getDecorationNodeOfPoolOrLaneInstance(gatewayContainer, model);


			EList<Instance> outgoingConnections = inclusiveGateway.get("outgoing");
			EList<Instance> incomingConnections = inclusiveGateway.get("incoming");

			if(outgoingConnections!=null && incomingConnections!=null){


				// Instance Diagram Process
				Instance diagramProcess = null;
				for(Instance diagram : model.getAllInstances(NotationPackage.Literals.DIAGRAM)){
					String diagramType = diagram.get(NotationPackage.Literals.VIEW__TYPE);
					if(diagramType.equals("Process")){
						diagramProcess = diagram;
						break;
					}
				}

				if(incomingConnections.size()>1){ // ENTREE MULTIPLE

					Instance newXorGateway = model.newInstance("process.XORGateway");
					newXorGateway.set("name", inclusiveGatewayName+"_add_migration_XOR");
					gatewayContainer.add("elements", newXorGateway);

					Instance newXorGatewayShape = CustomMigrationUtil.createShape(model, newXorGateway, CustomMigrationUtil.ACTUAL_XOR_GATEWAY_VISUAL_ID);
					gatewayContainerShape.add(NotationPackage.Literals.VIEW__PERSISTED_CHILDREN,newXorGatewayShape);

					Instance newLocationXorGateway = model.newInstance(NotationPackage.Literals.LOCATION);
					newLocationXorGateway.set(NotationPackage.Literals.LOCATION__Y, CustomMigrationUtil.DECORATION_NODE_LOCATION_Y);

					int xIncGateway =0;
					int yIncGateway =0;

					if(((Instance)(inclusiveGatewayShape.get(NotationPackage.Literals.NODE__LAYOUT_CONSTRAINT))).instanceOf(NotationPackage.Literals.BOUNDS)){
						xIncGateway=((Instance)((Instance)inclusiveGatewayShape.get(NotationPackage.Literals.NODE__LAYOUT_CONSTRAINT))).get("x");
						yIncGateway=((Instance)((Instance)inclusiveGatewayShape.get(NotationPackage.Literals.NODE__LAYOUT_CONSTRAINT))).get("y");
					}

					int newLocationX = (int)(Math.abs(xIncGateway-CustomMigrationUtil.DISTANCE_BETWEEN_GATEWAY));
					int newLocationY = (int)(yIncGateway);

					Instance newBoundsXorGateway = CustomMigrationUtil.createBounds(model, newLocationX, newLocationY); 

					Instance newDecorationXorGateway = CustomMigrationUtil.createXORDecorationNode(model, newXorGateway, newLocationXorGateway, CustomMigrationUtil.SHOW_DECORATION_NODE);
					//								
					newXorGatewayShape.add(NotationPackage.Literals.VIEW__PERSISTED_CHILDREN, newDecorationXorGateway);
					newXorGatewayShape.add(NotationPackage.Literals.NODE__LAYOUT_CONSTRAINT,  newBoundsXorGateway);

					do{
						Instance incoming = incomingConnections.get(0);
						incomingConnections.remove(0);
						incoming.set("target", newXorGateway);

						Instance incomingShape = CustomMigrationUtil.getConnectorInstance(incoming, model);
						incomingShape.set("target", newXorGatewayShape);

					}while(incomingConnections.size()>0);


					Instance newSequenceFlow = CustomMigrationUtil.createSequenceFlow(model, inclusiveGatewayName, newXorGateway, inclusiveGateway);
					poolContainer.add("connections", newSequenceFlow);
					Instance shape2add = CustomMigrationUtil.createConnector(model, newSequenceFlow, newXorGatewayShape, inclusiveGatewayShape);

					Instance shapeBendPoints = model.newInstance(NotationPackage.Literals.RELATIVE_BENDPOINTS);

					//shapeBendPoints.
					shape2add.add(NotationPackage.Literals.EDGE__BENDPOINTS, shapeBendPoints);

					if(diagramProcess!=null){
						diagramProcess.add(NotationPackage.Literals.DIAGRAM__PERSISTED_EDGES, shape2add);
					}

					addReportChange(inclusiveGatewayName, inclusiveGateway.getEClass().getName(), inclusiveGateway.getUuid(), Messages.changeInclusiveGatewayMigrationDescription, Messages.generalProperty, IStatus.WARNING);

				}
			}
		}
	}

}
