/**
 * Copyright (C) 2013 BonitaSoft S.A.
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
package org.bonitasoft.studio.actors.ui.wizard.page;

import java.util.List;

import org.bonitasoft.studio.actors.i18n.Messages;
import org.bonitasoft.studio.connector.model.definition.Category;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.connector.model.i18n.DefinitionResourceProvider;
import org.bonitasoft.studio.connector.model.implementation.ConnectorImplementation;
import org.bonitasoft.studio.connectors.ConnectorPlugin;
import org.bonitasoft.studio.connectors.ui.wizard.page.SelectAdvancedConnectorDefinitionWizardPage;
import org.bonitasoft.studio.model.process.Connector;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.ITreeContentProvider;

/**
 * @author Romain Bioteau
 */
public class SelectAdvancedFilterDefinitionWizardPage extends SelectAdvancedConnectorDefinitionWizardPage {

    public SelectAdvancedFilterDefinitionWizardPage(Connector workingCopy,
            List<ConnectorImplementation> existingImpl,
            List<ConnectorDefinition> definitions, String pageTitle,
            String pageDescription, DefinitionResourceProvider messageProvider) {
        super(workingCopy, existingImpl, definitions, pageTitle, pageDescription, messageProvider);
    }

    @Override
    protected ITreeContentProvider getContentProvider() {
        return new FilterUniqueDefinitionContentProvider();
    }

    @Override
    protected ITreeContentProvider getCustomContentProvider() {
        return new FilterUniqueDefinitionContentProvider(true);
    }

    @Override
    protected IStatus validateSelection(Object value) {
        if (value == null || value instanceof Category) {
            return new Status(IStatus.ERROR, ConnectorPlugin.PLUGIN_ID, Messages.selectAFilterDefWarning);
        }
        return Status.OK_STATUS;

    }

    @Override
    protected String getRightHeaderMessage() {
        return Messages.filterDefRepositoryName;
    }

}
