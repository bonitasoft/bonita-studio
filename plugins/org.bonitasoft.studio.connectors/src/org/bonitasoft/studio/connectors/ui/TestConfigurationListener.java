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
package org.bonitasoft.studio.connectors.ui;

import org.bonitasoft.studio.connector.model.definition.dialog.ITestConfigurationListener;
import org.bonitasoft.studio.connectors.ui.wizard.TestConnectorUtil;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfiguration;
import org.bonitasoft.studio.model.process.Connector;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;


/**
 * @author Romain Bioteau
 *
 */
public class TestConfigurationListener implements ITestConfigurationListener {

    private final ConnectorConfiguration configuration;
    private final Connector connector;
    private final WizardDialog dialog;

    public TestConfigurationListener(ConnectorConfiguration configuration,WizardDialog dialog, Connector connector) {
        this.configuration = configuration;
        this.dialog = dialog;
        this.connector= connector;
    }



    /* (non-Javadoc)
     * @see org.eclipse.swt.widgets.Listener#handleEvent(org.eclipse.swt.widgets.Event)
     */
    @Override
    public void handleEvent(Event event) {
        final String defId = configuration.getDefinitionId() ;
        final String defVersion = configuration.getVersion() ;       
        TestConnectorUtil.testConnectorWithConfiguration(configuration, defId, defVersion, connector, Display.getDefault().getActiveShell(), dialog);
    }
}
