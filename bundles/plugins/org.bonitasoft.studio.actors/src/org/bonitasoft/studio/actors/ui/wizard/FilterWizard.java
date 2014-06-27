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

import java.util.Collections;
import java.util.Set;

import org.bonitasoft.studio.actors.ActorsPlugin;
import org.bonitasoft.studio.actors.i18n.Messages;
import org.bonitasoft.studio.actors.repository.ActorFilterDefRepositoryStore;
import org.bonitasoft.studio.actors.ui.wizard.page.SelectAdvancedFilterDefinitionWizardPage;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.connector.model.definition.IConnectorDefinitionContainer;
import org.bonitasoft.studio.connector.model.definition.IDefinitionRepositoryStore;
import org.bonitasoft.studio.connector.model.definition.wizard.SelectNameAndDescWizardPage;
import org.bonitasoft.studio.connector.model.i18n.DefinitionResourceProvider;
import org.bonitasoft.studio.connector.model.implementation.ConnectorImplementation;
import org.bonitasoft.studio.connector.model.implementation.wizard.AbstractDefinitionSelectionImpementationWizardPage;
import org.bonitasoft.studio.connectors.ui.wizard.ConnectorWizard;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfigurationFactory;
import org.bonitasoft.studio.model.process.ActorFilter;
import org.bonitasoft.studio.model.process.Connector;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.wizard.IWizardPage;

/**
 * @author Romain Bioteau
 * 
 */
public class FilterWizard extends ConnectorWizard implements IConnectorDefinitionContainer {

	public FilterWizard(EObject container, EStructuralFeature connectorContainmentFeature, Set<EStructuralFeature> featureToCheckForUniqueID) {
		super(container, connectorContainmentFeature, featureToCheckForUniqueID);
		setWindowTitle(Messages.actorFilters);
	}

	public FilterWizard(ActorFilter filter, EStructuralFeature connectorContainmentFeature, Set<EStructuralFeature> featureToCheckForUniqueID) {
		super(filter, connectorContainmentFeature, featureToCheckForUniqueID);
	}

	@Override
	protected void initialize() {
		if (!(connectorWorkingCopy instanceof ActorFilter)) {
			connectorWorkingCopy = ProcessFactory.eINSTANCE.createActorFilter();
			connectorWorkingCopy.setConfiguration(ConnectorConfigurationFactory.eINSTANCE.createConnectorConfiguration());
		}
		setUseEvents(false);
		super.initialize();
	}

	@Override
	protected DefinitionResourceProvider initMessageProvider() {
		IRepositoryStore<? extends IRepositoryFileStore> store = RepositoryManager.getInstance().getRepositoryStore(ActorFilterDefRepositoryStore.class);
		return DefinitionResourceProvider.getInstance(store, ActorsPlugin.getDefault().getBundle());
	}

	@Override
	protected IDefinitionRepositoryStore getDefinitionStore() {
		return (IDefinitionRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(ActorFilterDefRepositoryStore.class);
	}

	@Override
	public ConnectorDefinition getDefinition() {
		ActorFilterDefRepositoryStore defStore = (ActorFilterDefRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(ActorFilterDefRepositoryStore.class);
		if (getOriginalConnector() != null) {
			return defStore.getDefinition(getOriginalConnector().getDefinitionId(), getOriginalConnector().getDefinitionVersion());
		} else {
			if (connectorWorkingCopy.getDefinitionId() != null && !connectorWorkingCopy.getDefinitionId().isEmpty()) {
				return defStore.getDefinition(connectorWorkingCopy.getDefinitionId(), connectorWorkingCopy.getDefinitionVersion());
			}
		}
		return null;
	}

	@Override
	protected AbstractDefinitionSelectionImpementationWizardPage getSelectionPage(Connector connectorWorkingCopy, DefinitionResourceProvider resourceProvider) {
		return new SelectAdvancedFilterDefinitionWizardPage(connectorWorkingCopy, Collections.<ConnectorImplementation>emptyList(), definitions, Messages.selectFilterDefinitionTitle, Messages.selectFilterDefinitionDesc, resourceProvider);
	}
	
	@Override
	public IWizardPage getNextPage(IWizardPage page) {
	    IWizardPage p = super.getNextPage(page);
	    if(p instanceof SelectNameAndDescWizardPage){
	        ConnectorDefinition def = selectionPage.getSelectedConnectorDefinition();
	        String connectorDefinitionLabel = messageProvider.getConnectorDefinitionLabel(def);
	        if (connectorDefinitionLabel == null && def != null) {
	            connectorDefinitionLabel = def.getId();
	        }
	        p.setTitle(Messages.bind(Messages.filterWizardTitle,connectorDefinitionLabel,connectorWorkingCopy.getDefinitionVersion()));
	        p.setDescription(Messages.filterWizardMessage);
	    }
	    return p;
	}

}
