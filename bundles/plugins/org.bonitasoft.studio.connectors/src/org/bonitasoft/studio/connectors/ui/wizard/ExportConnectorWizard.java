
/**
 * Copyright (C) 2009 BonitaSoft S.A.
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

import java.io.File;
import java.util.Optional;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IDefinitionRepositoryStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.connector.model.implementation.ConnectorImplementation;
import org.bonitasoft.studio.connector.model.implementation.provider.ConnectorImplementationContentProvider;
import org.bonitasoft.studio.connector.model.implementation.provider.ConnectorImplementationLabelProvider;
import org.bonitasoft.studio.connectors.ConnectorPlugin;
import org.bonitasoft.studio.connectors.i18n.Messages;
import org.bonitasoft.studio.connectors.repository.ConnectorDefRepositoryStore;
import org.bonitasoft.studio.connectors.repository.ConnectorImplRepositoryStore;
import org.bonitasoft.studio.connectors.repository.ExportConnectorArchiveOperation;
import org.bonitasoft.studio.connectors.ui.wizard.page.ExportConnectorWizardPage;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.widgets.Display;

/**
 * @author Mickael Istria
 */
public class ExportConnectorWizard extends Wizard {

    private ExportConnectorWizardPage exportConnectorWizardPage;
    protected Optional<ConnectorImplementation> connectorImplToExport = Optional.empty();
    protected Optional<ConnectorDefinition> connectorDefToExport = Optional.empty();

    public ExportConnectorWizard() {
        setDefaultPageImageDescriptor(Pics.getWizban());
        setWindowTitle(Messages.exportConnectorTitle);
        setNeedsProgressMonitor(true);
    }

    public ExportConnectorWizard(ConnectorImplementation connectorToExport) {
        this();
        this.connectorImplToExport = Optional.ofNullable(connectorToExport);
    }

    public ExportConnectorWizard(ConnectorDefinition connectorDefToExport) {
        this();
        this.connectorDefToExport = Optional.ofNullable(connectorDefToExport);
    }

    @Override
    public void addPages() {
        exportConnectorWizardPage = new ExportConnectorWizardPage(getPageTitle(), getPageDescription(), getContentProvider(),
                getLabelProvider(), getDefRepositoryStore());
        addPage(exportConnectorWizardPage);
    }

    protected LabelProvider getLabelProvider() {
        return new ConnectorImplementationLabelProvider(
                RepositoryManager.getInstance().getRepositoryStore(ConnectorDefRepositoryStore.class),
                ConnectorPlugin.getDefault().getBundle());
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    protected IContentProvider getContentProvider() {
        IRepositoryStore repositoryStore = getRepositoryStore();
        if (connectorImplToExport.isPresent()) {
            return (IStructuredContentProvider) inputElement -> new ConnectorImplementation[] {
                    connectorImplToExport.get() };
        }
        if (connectorDefToExport.isPresent()) {
            ConnectorDefinition definition = connectorDefToExport.get();
            return new ConnectorImplementationContentProvider(repositoryStore, definition.getId(),
                    definition.getVersion());
        }
        return new ConnectorImplementationContentProvider(repositoryStore, true);
    }

    protected IRepositoryStore getRepositoryStore() {
        return RepositoryManager.getInstance().getRepositoryStore(ConnectorImplRepositoryStore.class);
    }

    protected String getPageDescription() {
        return Messages.selectConnectorImplementationToExportDesc;
    }

    protected String getPageTitle() {
        return Messages.selectConnectorImplementationToExportTitle;
    }

    protected IDefinitionRepositoryStore getDefRepositoryStore() {
        return RepositoryManager.getInstance().getRepositoryStore(ConnectorDefRepositoryStore.class);
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.wizard.Wizard#performFinish()
     */
    @Override
    public boolean performFinish() {
        try {
            getContainer().run(true, false, progressMonitor -> {
                progressMonitor.beginTask(Messages.exporting, IProgressMonitor.UNKNOWN);
                String destPathFile = exportConnectorWizardPage.getDestFilePath();
                if (destPathFile.endsWith(File.separator)) {
                    destPathFile = destPathFile + exportConnectorWizardPage.getDestFileName();
                } else {
                    destPathFile = destPathFile + File.separator + exportConnectorWizardPage.getDestFileName();
                }
                final ExportConnectorArchiveOperation operation = createExportOperation(
                        exportConnectorWizardPage.getSelectedImplementation(), exportConnectorWizardPage.isIncludeSources(),
                        exportConnectorWizardPage.isAddDependencies(), destPathFile);
                final IStatus status = operation.run(progressMonitor);
                displayResult(status);
            });

        } catch (Exception e) {
            BonitaStudioLog.error(e);
        }

        return true;
    }

    protected void displayResult(final IStatus status) {
        Display.getDefault().syncExec(() -> {
            if (!status.isOK()) {
                MessageDialog.openError(Display.getDefault().getActiveShell(), Messages.exportStatusTitle,
                        status.getMessage());
            } else {
                MessageDialog.openInformation(Display.getDefault().getActiveShell(), Messages.exportStatusTitle,
                        Messages.exportSuccessfulMsg);
            }
        });
    }

    protected ExportConnectorArchiveOperation createExportOperation(ConnectorImplementation impl, boolean addSources,
            boolean addDependencies, String targetPath) {
        final ExportConnectorArchiveOperation operation = new ExportConnectorArchiveOperation();
        operation.setImplementation(impl);
        operation.setIncludeSources(addSources);
        operation.setTargetPath(targetPath);
        operation.setAddDependencies(addDependencies);
        return operation;
    }

}
