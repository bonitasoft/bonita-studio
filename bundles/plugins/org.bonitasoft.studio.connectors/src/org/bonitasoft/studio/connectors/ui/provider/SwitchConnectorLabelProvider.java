///**
// * Copyright (C) 2010 BonitaSoft S.A.
// * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
// *
// * This program is free software: you can redistribute it and/or modify
// * it under the terms of the GNU General Public License as published by
// * the Free Software Foundation, either version 2.0 of the License, or
// * (at your option) any later version.
// *
// * This program is distributed in the hope that it will be useful,
// * but WITHOUT ANY WARRANTY; without even the implied warranty of
// * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// * GNU General Public License for more details.
// *
// * You should have received a copy of the GNU General Public License
// * along with this program.  If not, see <http://www.gnu.org/licenses/>.
// */
//
//package org.bonitasoft.studio.connectors.ui.provider;
//
//import org.bonitasoft.studio.connectors.i18n.Messages;
//import org.bonitasoft.studio.model.process.Connector;
//import org.bonitasoft.studio.model.process.Element;
//import org.eclipse.jface.viewers.IBaseLabelProvider;
//import org.eclipse.osgi.util.NLS;
///**
// * @author Romain Bioteau
// *
// */
//public class SwitchConnectorLabelProvider extends StyledConnectorLabelProvider implements IBaseLabelProvider {
//
//    @Override
//    public String getText(Object input) {
//        Connector connector = (Connector)input;
//        String name = null;
//        if (connector.getLabel() != null && connector.getLabel().length() > 0) {
//            name = connector.getLabel();
//        } else {
//            name = connector.getName();
//        }
//        ConnectorAPI connectorAPI = ConnectorRepository.getInstance().getConnectorAPI();
//        if (connectorAPI != null) {
//            try{
//                ConnectorDescription runtimeConnector = connectorAPI.getConnector(connector.getConnectorId());
//                if (connector.getEvent() != null) {
//                    return name + " -- " + Messages.getValue(connector.getEvent()) + " ("+((Element) connector.eContainer()).getLabel()+")";
//                } else if (runtimeConnector != null) {
//                    return name + " -- " + runtimeConnector.getConnectorLabel() + " ("+((Element) connector.eContainer()).getLabel()+")";
//                }
//            }catch (IllegalArgumentException e) {
//                return NLS.bind(Messages.connectorDoesntExists, connector.getConnectorId());
//            }
//        }
//        return name;
//    }
//
//}
