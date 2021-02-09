/**
 * Copyright (C) 2009 BonitaSoft S.A.
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
package org.bonitasoft.studio.identity.actors.ui.wizard;

import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.common.repository.provider.DefinitionResourceProvider;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.identity.IdentityPlugin;
import org.bonitasoft.studio.identity.actors.repository.ActorFilterDefRepositoryStore;
import org.bonitasoft.studio.identity.actors.ui.wizard.page.SelectUserFilterDefinitionWizardPage;
import org.bonitasoft.studio.identity.i18n.Messages;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfigurationFactory;
import org.bonitasoft.studio.model.process.Connector;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.jface.wizard.Wizard;

public class SelectUserFilterDefinitionWizard extends Wizard {

    private SelectUserFilterDefinitionWizardPage page;
    private ConnectorDefinition definition;
    private final DefinitionResourceProvider messageProvider;

    public SelectUserFilterDefinitionWizard(){
        setDefaultPageImageDescriptor(Pics.getWizban()) ;
        setWindowTitle(Messages.selectActorFitlerDefinition);
        IRepositoryStore<? extends IRepositoryFileStore> store =RepositoryManager.getInstance().getRepositoryStore(ActorFilterDefRepositoryStore.class) ;
        messageProvider = DefinitionResourceProvider.getInstance(store, IdentityPlugin.getDefault().getBundle()) ;
    }

    @Override
    public void addPages() {
        Connector dummy =  ProcessFactory.eINSTANCE.createConnector() ;
        dummy.setConfiguration(ConnectorConfigurationFactory.eINSTANCE.createConnectorConfiguration()) ;
        page =  new SelectUserFilterDefinitionWizardPage(dummy,messageProvider);
        addPage(page) ;
    }

    @Override
    public boolean performFinish() {
        definition = page.getSelectedDefinition() ;
        return true;
    }

    public ConnectorDefinition getDefinition(){
        return definition ;
    }

}
