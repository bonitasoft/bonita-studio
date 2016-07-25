/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.connectors.handler;

import org.bonitasoft.studio.connector.model.implementation.ConnectorImplementation;
import org.bonitasoft.studio.connectors.operation.TestConnectorOperation;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfiguration;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.operation.IRunnableWithProgress;


/**
 * @author Romain Bioteau
 *
 */
public class TestConnectorHandler extends AbstractHandler {

    /* (non-Javadoc)
     * @see org.eclipse.core.commands.IHandler#execute(org.eclipse.core.commands.ExecutionEvent)
     */
    @Override
    public IRunnableWithProgress execute(ExecutionEvent event) throws ExecutionException {
        ConnectorImplementation implementation =  (ConnectorImplementation) event.getParameters().get("IMPLEMENTATION") ;
        ConnectorConfiguration configuration =  (ConnectorConfiguration) event.getParameters().get("CONFIGURATION") ;

        final TestConnectorOperation operation = new TestConnectorOperation() ;
        operation.setImplementation(implementation) ;
        operation.setConnectorConfiguration(configuration) ;

        return operation;
    }

}
