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

import java.util.List;

import org.bonitasoft.engine.filter.AbstractUserFilter;
import org.bonitasoft.studio.actors.ActorsPlugin;
import org.bonitasoft.studio.actors.i18n.Messages;
import org.bonitasoft.studio.actors.repository.ActorFilterDefRepositoryStore;
import org.bonitasoft.studio.actors.repository.ActorFilterImplRepositoryStore;
import org.bonitasoft.studio.actors.repository.ActorFilterSourceRepositoryStore;
import org.bonitasoft.studio.actors.ui.wizard.page.FilterUniqueDefinitionContentProvider;
import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IDefinitionRepositoryStore;
import org.bonitasoft.studio.common.repository.provider.DefinitionResourceProvider;
import org.bonitasoft.studio.connector.model.implementation.ConnectorImplementation;
import org.bonitasoft.studio.connector.model.implementation.ConnectorImplementationPackage;
import org.bonitasoft.studio.connector.model.implementation.wizard.AbstractDefinitionSelectionImpementationWizardPage;
import org.bonitasoft.studio.connector.model.implementation.wizard.AbstractImplementationWizardPage;
import org.bonitasoft.studio.connectors.ui.wizard.ConnectorImplementationWizard;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.jface.databinding.viewers.IViewerObservableValue;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.wizard.IWizardPage;


/**
 * @author Romain Bioteau
 *
 */
public class FilterImplementationWizard extends ConnectorImplementationWizard {


    public FilterImplementationWizard(){
        super() ;
        setWindowTitle(Messages.newFilterImplementation) ;
        setNeedsProgressMonitor(true) ;
        setDefaultPageImageDescriptor(Pics.getWizban()) ;

    }

    public FilterImplementationWizard(ConnectorImplementation implementation){
        super(implementation) ;
        setWindowTitle(Messages.editFilterImplementation) ;
    }

    @Override
    protected void initialize() {
        implStore = RepositoryManager.getInstance().getRepositoryStore(ActorFilterImplRepositoryStore.class) ;
        if(getOriginalImplementation() != null){
            fileStore = implStore.getChild(NamingUtils.toConnectorImplementationFilename(getOriginalImplementation().getImplementationId(),getOriginalImplementation().getImplementationVersion(),true), true) ;
        }
        defStore =  RepositoryManager.getInstance().getRepositoryStore(ActorFilterDefRepositoryStore.class) ;
        sourceStore = (ActorFilterSourceRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(ActorFilterSourceRepositoryStore.class) ;
        messageProvider = DefinitionResourceProvider.getInstance(defStore, ActorsPlugin.getDefault().getBundle()) ;
    }

    @Override
    protected String getAbstractClassName() {
        return AbstractUserFilter.class.getName() ;
    }

    @Override
    protected String getPageTitle() {
        return Messages.actorFilterImplementationPageTitle;
    }

    @Override
    protected String getPageDescription() {
        return Messages.actorFilterImplementationPageDesc ;
    }
    
    @Override
    protected IWizardPage getDefinitionSelectionWizardPage(
    		List<ConnectorImplementation> existingImplementation) {
    	return new AbstractDefinitionSelectionImpementationWizardPage(implWorkingCopy,existingImplementation,((IDefinitionRepositoryStore) defStore).getDefinitions(),getPageTitle(),getPageDescription(),messageProvider){

			@Override
			protected ITreeContentProvider getContentProvider() {
				return new FilterUniqueDefinitionContentProvider();
			}
			
			@Override
			protected ITreeContentProvider getCustomContentProvider() {
				return  new FilterUniqueDefinitionContentProvider(true);
			}

			@Override
			protected void bindValue() {
				final IViewerObservableValue observeSingleSelection = ViewersObservables.observeSingleSelection(explorer.getRightTableViewer());
				context.bindValue(observeSingleSelection, EMFObservables.observeValue(implementation, ConnectorImplementationPackage.Literals.CONNECTOR_IMPLEMENTATION__DEFINITION_ID),defIdStrategy,defModelStrategy) ;
				context.bindValue(ViewersObservables.observeSingleSelection(versionCombo), EMFObservables.observeValue(implementation, ConnectorImplementationPackage.Literals.CONNECTOR_IMPLEMENTATION__DEFINITION_VERSION));
			}
			
		};
    }

    @Override
    protected IWizardPage getImplementationPage(
    		List<ConnectorImplementation> existingImplementation) {
    	return new AbstractImplementationWizardPage(implWorkingCopy,existingImplementation,((IDefinitionRepositoryStore) defStore).getDefinitions(),sourceStore,getPageTitle(),getPageDescription(),messageProvider){

			@Override
			protected ITreeContentProvider getContentProvider() {
				return new FilterUniqueDefinitionContentProvider();
			}
			
		};
    }

}
