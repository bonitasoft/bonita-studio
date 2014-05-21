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
package org.bonitasoft.studio.actors.ui.wizard;

import org.bonitasoft.studio.actors.repository.ActorFilterConfRepositoryStore;
import org.bonitasoft.studio.actors.repository.ActorFilterDefRepositoryStore;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.connector.model.definition.dialog.AbstractDefinitionWizardDialog;
import org.bonitasoft.studio.connector.model.definition.dialog.ITestConfigurationListener;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfiguration;
import org.bonitasoft.studio.model.process.Connector;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.swt.widgets.Shell;


/**
 * @author Romain Bioteau
 *
 */
public class ActorFilterDefinitionWizardDialog extends AbstractDefinitionWizardDialog {

    public ActorFilterDefinitionWizardDialog(Shell parentShell, IWizard newWizard) {
        super(parentShell, newWizard, RepositoryManager.getInstance().getRepositoryStore(ActorFilterConfRepositoryStore.class),RepositoryManager.getInstance().getRepositoryStore(ActorFilterDefRepositoryStore.class), null);
    }

	@Override
	protected ITestConfigurationListener getTestListener(
			ConnectorConfiguration configuration, Connector connector) {
		return null;
	}

	@Override
	protected ITestConfigurationListener getTestListener(
			ConnectorConfiguration configuration, IWizard wizard) {
		return null;
	}


}
