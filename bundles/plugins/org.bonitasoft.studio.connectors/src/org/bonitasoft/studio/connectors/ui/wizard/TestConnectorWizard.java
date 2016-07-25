/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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

import java.util.List;

import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.connector.model.implementation.ConnectorImplementation;
import org.bonitasoft.studio.connector.model.implementation.IImplementationRepositoryStore;
import org.bonitasoft.studio.connectors.configuration.SelectConnectorImplementationWizard;
import org.bonitasoft.studio.connectors.i18n.Messages;
import org.bonitasoft.studio.connectors.repository.ConnectorDefRepositoryStore;
import org.bonitasoft.studio.connectors.repository.ConnectorImplRepositoryStore;
import org.bonitasoft.studio.connectors.ui.wizard.page.AbstractConnectorOutputWizardPage;
import org.bonitasoft.studio.connectors.ui.wizard.page.TestConnectorOutputWizardPage;
import org.bonitasoft.studio.connectors.ui.wizard.page.TestDatabaseConnectorOutputWizardPage;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfiguration;
import org.bonitasoft.studio.model.process.Connector;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;

/**
 * @author Romain Bioteau
 */
public class TestConnectorWizard extends ConnectorWizard {

    public TestConnectorWizard() {
        super((EObject) null, null, null);
        setForcePreviousAndNextButtons(true);
        setWindowTitle(Messages.testConnectorTitle);
    }

    @Override
    protected void initializeContainment() {
        //KEEP IT EMPTY
    }

    @Override
    protected void addOuputPage(final ConnectorDefinition definition) {
        if (!definition.getOutput().isEmpty()) {
            AbstractConnectorOutputWizardPage outputPage = null;
            if (supportsDatabaseOutputMode(definition)) {
                outputPage = new TestDatabaseConnectorOutputWizardPage();
            } else {
                outputPage = new TestConnectorOutputWizardPage();
                createDefaultOutputs(definition);
            }
            outputPage.setElementContainer(container);
            outputPage.setConnector(connectorWorkingCopy);
            outputPage.setDefinition(definition);
            addAdditionalPage(outputPage);
        }
    }

    @Override
    protected IWizardPage getNameAndDescriptionPage() {
        return null;
    }

    @Override
    protected IWizardPage getOutputPageFor(final ConnectorDefinition definition) {
        return null;
    }

    @Override
    protected void clearConnectorConfiguration(final ConnectorDefinition definition) {
        final ConnectorConfiguration configuration = connectorWorkingCopy.getConfiguration();
        configuration.getParameters().clear();
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.wizard.Wizard#performFinish()
     */
    @Override
    public boolean performFinish() {
        final ConnectorConfiguration configuration = connectorWorkingCopy.getConfiguration();
        final String defId = connectorWorkingCopy.getDefinitionId();
        final String defVersion = connectorWorkingCopy.getDefinitionVersion();
        return TestConnectorUtil.testConnectorWithConfiguration(configuration, defId, defVersion, connectorWorkingCopy, getShell(), getContainer());
    }

    protected ConnectorImplementation openImplementationSelection(final String defId, final String defVersion) {
        final SelectConnectorImplementationWizard wizard = new SelectConnectorImplementationWizard(defId, defVersion);
        final WizardDialog dialog = new WizardDialog(Display.getDefault().getActiveShell(), wizard);
        if (dialog.open() == Dialog.OK) {
            return wizard.getConnectorImplementation();
        }
        return null;
    }

    protected IImplementationRepositoryStore getImplementationStore() {
        return RepositoryManager.getInstance().getRepositoryStore(ConnectorImplRepositoryStore.class);
    }

    @Override
    public Connector getOriginalConnector() {
        return null;
    }

    @Override
    public ConnectorDefinition getDefinition() {
        final ConnectorDefRepositoryStore defStore = RepositoryManager.getInstance().getRepositoryStore(ConnectorDefRepositoryStore.class);
        if (connectorWorkingCopy.getDefinitionId() != null && !connectorWorkingCopy.getDefinitionId().isEmpty()) {
            return defStore.getDefinition(connectorWorkingCopy.getDefinitionId(), connectorWorkingCopy.getDefinitionVersion());
        }
        return null;
    }

    @Override
    public IWizardPage getNextPage(final IWizardPage page) {
        if (page.equals(selectionPage)) {
            final ConnectorDefinition definition = selectionPage.getSelectedConnectorDefinition();
            if (definition != null) {
                extension = findCustomWizardExtension(definition);
                recreateConnectorConfigurationPages(definition, false);
            }
        }

        final List<IWizardPage> pages = getAllPageList();
        final int index = pages.indexOf(page);
        if (index == pages.size() - 1 || index == -1) {
            // last page or page not found
            return null;
        }
        return pages.get(index + 1);
    }

    @Override
    public boolean isEditMode() {
        return false;
    }

}
