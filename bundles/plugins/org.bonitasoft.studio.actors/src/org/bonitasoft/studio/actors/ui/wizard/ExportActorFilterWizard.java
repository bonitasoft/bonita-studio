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
package org.bonitasoft.studio.actors.ui.wizard;

import org.bonitasoft.studio.actors.ActorsPlugin;
import org.bonitasoft.studio.actors.i18n.Messages;
import org.bonitasoft.studio.actors.repository.ActorFilterDefRepositoryStore;
import org.bonitasoft.studio.actors.repository.ActorFilterImplRepositoryStore;
import org.bonitasoft.studio.actors.repository.ExportActorFilterArchiveOperation;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IDefinitionRepositoryStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.connector.model.implementation.ConnectorImplementation;
import org.bonitasoft.studio.connector.model.implementation.provider.ConnectorImplementationLabelProvider;
import org.bonitasoft.studio.connectors.ui.wizard.ExportConnectorWizard;
import org.eclipse.jface.viewers.LabelProvider;

/**
 * @author Romain Bioteau
 */
public class ExportActorFilterWizard extends ExportConnectorWizard {

    public ExportActorFilterWizard() {
        super();
        setWindowTitle(Messages.exportActorFilterTitle);
    }

    public ExportActorFilterWizard(ConnectorImplementation connectorToExport) {
        super(connectorToExport);
        setWindowTitle(Messages.exportActorFilterTitle);
    }

    public ExportActorFilterWizard(ConnectorDefinition connectorDefToExport) {
        super(connectorDefToExport);
        setWindowTitle(Messages.exportActorFilterTitle);
    }

    @Override
    protected ExportActorFilterArchiveOperation createExportOperation(ConnectorImplementation impl, boolean addSources,
            boolean addDependencies, String targetPath) {
        final ExportActorFilterArchiveOperation operation = new ExportActorFilterArchiveOperation();
        operation.setImplementation(impl);
        operation.setIncludeSources(addSources);
        operation.setTargetPath(targetPath);
        operation.setAddDependencies(addDependencies);
        return operation;
    }

    @Override
    protected LabelProvider getLabelProvider() {
        return new ConnectorImplementationLabelProvider(
                RepositoryManager.getInstance().getRepositoryStore(ActorFilterDefRepositoryStore.class),
                ActorsPlugin.getDefault().getBundle());
    }

    @Override
    protected IRepositoryStore getRepositoryStore() {
        return RepositoryManager.getInstance().getRepositoryStore(ActorFilterImplRepositoryStore.class);
    }

    @Override
    protected String getPageDescription() {
        return Messages.selectFilterImplementationToExportDesc;
    }

    @Override
    protected String getPageTitle() {
        return Messages.selectFilterImplementationToExportTitle;
    }

    @Override
    protected IDefinitionRepositoryStore getDefRepositoryStore() {
        return RepositoryManager.getInstance().getRepositoryStore(ActorFilterDefRepositoryStore.class);
    }

}
