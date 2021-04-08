/**
 * Copyright (C) 2014-2015 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.connectors.ui.wizard;

import java.util.Set;

import org.bonitasoft.engine.bpm.connector.ConnectorEvent;
import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.expression.editor.filter.AvailableExpressionTypeFilter;
import org.bonitasoft.studio.expression.editor.provider.IExpressionProvider;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.process.Activity;
import org.bonitasoft.studio.model.process.Connector;
import org.bonitasoft.studio.model.process.Pool;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.Viewer;

/**
 * @author Romain Bioteau
 */
public class ConnectorAvailableExpressionTypeFilter extends
        AvailableExpressionTypeFilter {

    public ConnectorAvailableExpressionTypeFilter(final String[] contentTypes) {
        super(contentTypes);
    }

    public ConnectorAvailableExpressionTypeFilter() {
        super(new String[] { ExpressionConstants.CONSTANT_TYPE,
                ExpressionConstants.VARIABLE_TYPE,
                ExpressionConstants.DOCUMENT_TYPE,
                ExpressionConstants.SCRIPT_TYPE,
                ExpressionConstants.PARAMETER_TYPE });
    }

    @Override
    public boolean select(final Viewer viewer, final Object context, final Object element) {
        if (viewer != null) {
            final Connector connector = getParentConnector(viewer.getInput());
            if (isSupportingContractInput(connector)) {
                final Set<String> contentTypes = allowedExpressionTypes();
                contentTypes.add(ExpressionConstants.CONTRACT_INPUT_TYPE);
                return isExpressionAllowed(element) ||
                        ((element instanceof Expression)
                                && ExpressionConstants.CONTRACT_INPUT_TYPE.equals(((Expression) element).getType()))
                        ||
                        ((element instanceof IExpressionProvider) && ExpressionConstants.CONTRACT_INPUT_TYPE
                                .equals(((IExpressionProvider) element).getExpressionType()));
            }
        }
        return super.select(viewer, context, element);
    }

    /**
     * @param connector
     * @return
     */
    private boolean isSupportingContractInput(final Connector connector) {
        if (connector != null) {
            return isSupportingContractInputOnActivity(connector)
                    || isSupportingContractInputOnPool(connector);
        } else {
            return false;
        }
    }

    /**
     * @param connector
     * @return
     */
    private boolean isSupportingContractInputOnPool(final Connector connector) {
        return isConnectorIsOnPool(connector)
                && ConnectorEvent.ON_ENTER.name().equals(
                        connector.getEvent());
    }

    /**
     * @param connector
     * @return
     */
    private boolean isSupportingContractInputOnActivity(final Connector connector) {
        return isConnectorIsOnActivity(connector)
                && ConnectorEvent.ON_FINISH.name().equals(
                        connector.getEvent());
    }

    protected boolean isConnectorIsOnPool(final Connector connector) {
        return connector.eContainer() instanceof Pool;
    }

    protected boolean isConnectorIsOnActivity(final Connector connector) {
        return connector.eContainer() instanceof Activity;
    }

    private Connector getParentConnector(final Object context) {
        if (context instanceof EObject) {
            EObject current = (EObject) context;
            while (current != null && !(current instanceof Connector)) {
                current = current.eContainer();
            }
            return (Connector) current;
        }
        return null;
    }

}
