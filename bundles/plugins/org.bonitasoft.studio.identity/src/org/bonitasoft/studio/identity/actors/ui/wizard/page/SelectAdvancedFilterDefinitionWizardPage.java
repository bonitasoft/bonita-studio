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
package org.bonitasoft.studio.identity.actors.ui.wizard.page;

import java.util.List;

import org.bonitasoft.studio.common.repository.provider.ExtendedConnectorDefinition;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.connector.model.implementation.ConnectorImplementation;
import org.bonitasoft.studio.connectors.ui.wizard.page.SelectAdvancedConnectorDefinitionWizardPage;
import org.bonitasoft.studio.identity.i18n.Messages;
import org.bonitasoft.studio.model.process.Connector;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.jface.viewers.ITreeContentProvider;

/**
 * @author Romain Bioteau
 */
public class SelectAdvancedFilterDefinitionWizardPage extends SelectAdvancedConnectorDefinitionWizardPage {

    public SelectAdvancedFilterDefinitionWizardPage(Connector workingCopy,
            List<ConnectorImplementation> existingImpl,
            List<ExtendedConnectorDefinition> definitions, 
            String pageTitle,
            String pageDescription) {
        super(workingCopy, existingImpl, definitions, pageTitle, pageDescription);
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
    protected IValidator<ConnectorDefinition> selectionValidator() {
        return def -> def != null ? ValidationStatus.ok() : ValidationStatus.error(Messages.selectAFilterDefWarning);

    }

    @Override
    protected String getRightHeaderMessage() {
        return Messages.filterDefRepositoryName;
    }

}
