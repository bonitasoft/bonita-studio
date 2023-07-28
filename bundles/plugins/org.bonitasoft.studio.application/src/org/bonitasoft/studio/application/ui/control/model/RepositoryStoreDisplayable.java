/**
 * Copyright (C) 2022 BonitaSoft S.A.
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
package org.bonitasoft.studio.application.ui.control.model;

import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.common.ui.IDisplayable;
import org.bonitasoft.studio.configuration.repository.EnvironmentRepositoryStore;
import org.bonitasoft.studio.connectors.ConnectorPlugin;
import org.bonitasoft.studio.connectors.repository.ConnectorConfRepositoryStore;
import org.bonitasoft.studio.connectors.repository.ConnectorDefRepositoryStore;
import org.bonitasoft.studio.connectors.repository.ConnectorImplRepositoryStore;
import org.bonitasoft.studio.connectors.repository.DatabaseConnectorPropertiesRepositoryStore;
import org.bonitasoft.studio.dependencies.repository.DependencyRepositoryStore;
import org.bonitasoft.studio.designer.core.repository.WebFragmentRepositoryStore;
import org.bonitasoft.studio.designer.core.repository.WebPageRepositoryStore;
import org.bonitasoft.studio.designer.core.repository.WebWidgetRepositoryStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.diagram.custom.repository.ProcessConfigurationRepositoryStore;
import org.bonitasoft.studio.groovy.repository.GroovyRepositoryStore;
import org.bonitasoft.studio.identity.IdentityPlugin;
import org.bonitasoft.studio.identity.actors.repository.ActorFilterConfRepositoryStore;
import org.bonitasoft.studio.identity.actors.repository.ActorFilterDefRepositoryStore;
import org.bonitasoft.studio.identity.actors.repository.ActorFilterImplRepositoryStore;
import org.bonitasoft.studio.identity.actors.repository.ActorFilterSourceRepositoryStore;
import org.bonitasoft.studio.identity.organization.repository.OrganizationRepositoryStore;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.eclipse.swt.graphics.Image;

/**
 * An {@link IDisplayable} implementation which works for most {@link IRepositoryStore} implementations.
 */
public class RepositoryStoreDisplayable implements IDisplayable {

    private IRepositoryStore<?> repositoryStore;

    public RepositoryStoreDisplayable(IRepositoryStore<?> repositoryStore) {
        this.repositoryStore = repositoryStore;
    }

    @Override
    public String getDisplayName() {
        if (repositoryStore instanceof ActorFilterImplRepositoryStore) {
            return org.bonitasoft.studio.identity.i18n.Messages.filterImplRepositoryName;
        } else if (repositoryStore instanceof ConnectorImplRepositoryStore) {
            return org.bonitasoft.studio.connectors.i18n.Messages.connectorImplRepositoryName;
        } else if (repositoryStore instanceof ActorFilterDefRepositoryStore) {
            return org.bonitasoft.studio.identity.i18n.Messages.filterDefRepositoryName;
        } else if (repositoryStore instanceof ConnectorDefRepositoryStore) {
            return org.bonitasoft.studio.connectors.i18n.Messages.connectorDefRepositoryName;
        } else if (repositoryStore instanceof ActorFilterConfRepositoryStore) {
            return org.bonitasoft.studio.identity.i18n.Messages.filterConfRepositoryName;
        } else if (repositoryStore instanceof ConnectorConfRepositoryStore) {
            return org.bonitasoft.studio.connectors.i18n.Messages.connectorConfRepositoryName;
        } else if (repositoryStore instanceof DiagramRepositoryStore) {
            return org.bonitasoft.studio.diagram.custom.i18n.Messages.diagrams;
        } else if (repositoryStore instanceof EnvironmentRepositoryStore) {
            return org.bonitasoft.studio.configuration.i18n.Messages.environments;
        } else if (repositoryStore instanceof OrganizationRepositoryStore) {
            return org.bonitasoft.studio.identity.i18n.Messages.organizations;
        } else if (repositoryStore instanceof ProcessConfigurationRepositoryStore) {
            return org.bonitasoft.studio.diagram.custom.i18n.Messages.configurations;
        } else if (repositoryStore instanceof WebFragmentRepositoryStore) {
            return org.bonitasoft.studio.designer.i18n.Messages.fragmentRepository;
        } else if (repositoryStore instanceof WebPageRepositoryStore) {
            return org.bonitasoft.studio.designer.i18n.Messages.formRepository;
        } else if (repositoryStore instanceof WebWidgetRepositoryStore) {
            return org.bonitasoft.studio.designer.i18n.Messages.widgetRepository;
        } else if (repositoryStore instanceof BusinessObjectModelRepositoryStore<?>) {
            return org.bonitasoft.studio.businessobject.i18n.Messages.businessObjectRepositoryStoreName;
        } else if (repositoryStore instanceof DatabaseConnectorPropertiesRepositoryStore) {
            return org.bonitasoft.studio.connectors.i18n.Messages.databaseConnectorsProperties;
        } else if (repositoryStore instanceof DependencyRepositoryStore) {
            return DependencyRepositoryStore.STORE_NAME;
        } else if (repositoryStore instanceof ActorFilterSourceRepositoryStore) {
            return org.bonitasoft.studio.identity.i18n.Messages.filtersSourceRepositoryName;
        } else if (repositoryStore instanceof GroovyRepositoryStore) {
            return org.bonitasoft.studio.groovy.Messages.groovyScriptRepository;
        }

        // by default, just use the name
        return repositoryStore.getName();
    }

    @Override
    public Image getIcon() {
        if (repositoryStore instanceof ActorFilterImplRepositoryStore) {
            return Pics.getImage(PicsConstants.filterImpl);
        } else if (repositoryStore instanceof ConnectorImplRepositoryStore) {
            return Pics.getImage(PicsConstants.connectorImpl);
        } else if (repositoryStore instanceof ActorFilterDefRepositoryStore) {
            return Pics.getImage(PicsConstants.filterDef);
        } else if (repositoryStore instanceof ConnectorDefRepositoryStore) {
            return Pics.getImage(PicsConstants.connectorDef);
        } else if (repositoryStore instanceof ActorFilterConfRepositoryStore) {
            return Pics.getImage("conf.png", IdentityPlugin.getDefault());
        } else if (repositoryStore instanceof ConnectorConfRepositoryStore) {
            return Pics.getImage("conf.png", ConnectorPlugin.getDefault());
        } else if (repositoryStore instanceof DiagramRepositoryStore) {
            return Pics.getImage(PicsConstants.diagram);
        } else if (repositoryStore instanceof EnvironmentRepositoryStore) {
            return Pics.getImage(PicsConstants.environment);
        } else if (repositoryStore instanceof OrganizationRepositoryStore) {
            return Pics.getImage(PicsConstants.organization);
        } else if (repositoryStore instanceof ProcessConfigurationRepositoryStore) {
            return Pics.getImage(PicsConstants.configuration);
        } else if (repositoryStore instanceof WebFragmentRepositoryStore) {
            return Pics.getImage(PicsConstants.fragment);
        } else if (repositoryStore instanceof WebPageRepositoryStore) {
            return Pics.getImage(PicsConstants.page);
        } else if (repositoryStore instanceof WebWidgetRepositoryStore) {
            return Pics.getImage(PicsConstants.widget);
        } else if (repositoryStore instanceof BusinessObjectModelRepositoryStore<?>) {
            return Pics.getImage(PicsConstants.bdm);
        } else if (repositoryStore instanceof DatabaseConnectorPropertiesRepositoryStore) {
            return Pics.getImage("databases_driver.png", ConnectorPlugin.getDefault());
        } else if (repositoryStore instanceof DependencyRepositoryStore) {
            return null;
        } else if (repositoryStore instanceof ActorFilterSourceRepositoryStore) {
            return Pics.getImage("java.gif", IdentityPlugin.getDefault());
        }else if (repositoryStore instanceof GroovyRepositoryStore) {
            return Pics.getImage(PicsConstants.groovyScript);
        }

        // by default, no icon
        return null;
    }

}
