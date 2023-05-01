/**
 * Copyright (C) 2020 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.connectors.ui.wizard.custom.webservice;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.connectors.extension.ICanBeUsedProvider;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorParameter;
import org.bonitasoft.studio.model.expression.AbstractExpression;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.process.Connector;
import org.eclipse.emf.common.util.EList;

/*******************************************************************************
 * Copyright (C) 2009, 2013 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/

/**
 * @author Florine Boudin
 */
public class IsConnectorEditing implements ICanBeUsedProvider {

    public static final List<String> PARAMS_TO_CHECK = new ArrayList<>();
    static {
        PARAMS_TO_CHECK.add("envelope");
        PARAMS_TO_CHECK.add("serviceNS");
        PARAMS_TO_CHECK.add("serviceName");
        PARAMS_TO_CHECK.add("portName");
        PARAMS_TO_CHECK.add("endpointAddress");
        PARAMS_TO_CHECK.add("binding");
        PARAMS_TO_CHECK.add("soapAction");
    }

    @Override
    public boolean canBeUsed(ConnectorDefinition connector,
            Connector modelConnector) {
        if (modelConnector != null) {
            EList<ConnectorParameter> parameters = modelConnector.getConfiguration().getParameters();
            for (ConnectorParameter param : parameters) {
                for (String paramToCheckName : PARAMS_TO_CHECK) {
                    if (paramToCheckName.equals(param.getKey()) && !hasNotInitializedValue(param)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean hasNotInitializedValue(ConnectorParameter param) {
        final AbstractExpression expression = param.getExpression();
        return expression instanceof Expression
                && (ExpressionConstants.CONSTANT_TYPE.equals(((Expression) expression).getType())
                        || ExpressionConstants.PATTERN_TYPE.equals(((Expression) expression).getType()))
                && !((Expression) expression).hasContent();
    }

}
