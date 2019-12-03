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

import org.bonitasoft.studio.actors.ActorsPlugin;
import org.bonitasoft.studio.actors.i18n.Messages;
import org.bonitasoft.studio.actors.repository.ActorFilterDefRepositoryStore;
import org.bonitasoft.studio.actors.repository.ActorFilterImplRepositoryStore;
import org.bonitasoft.studio.actors.repository.ActorFilterSourceRepositoryStore;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.filestore.AbstractFileStore;
import org.bonitasoft.studio.common.repository.model.IDefinitionRepositoryStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.common.repository.store.SourceRepositoryStore;
import org.bonitasoft.studio.connector.model.implementation.provider.ConnectorImplementationContentProvider;
import org.bonitasoft.studio.connector.model.implementation.provider.ConnectorImplementationLabelProvider;
import org.bonitasoft.studio.connector.model.implementation.wizard.AbstractSelectImplementationWizard;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.LabelProvider;


/**
 * @author Romain Bioteau
 *
 */
public class SelectUserFilterImplementationWizard extends AbstractSelectImplementationWizard {


    public SelectUserFilterImplementationWizard(){
        super() ;
        setWindowTitle(Messages.selectActorFitleImplementation);
    }

    @Override
    protected SourceRepositoryStore<AbstractFileStore> getSourceStore() {
        return RepositoryManager.getInstance().getRepositoryStore(ActorFilterSourceRepositoryStore.class);
    }

    @Override
    protected IRepositoryStore<? extends IRepositoryFileStore> getImplementationStore() {
        return  RepositoryManager.getInstance().getRepositoryStore(ActorFilterImplRepositoryStore.class);
    }

    @Override
    protected LabelProvider getLabelProvider() {
        return new ConnectorImplementationLabelProvider((IDefinitionRepositoryStore)RepositoryManager.getInstance().getRepositoryStore(ActorFilterDefRepositoryStore.class), ActorsPlugin.getDefault().getBundle());
    }

    @SuppressWarnings("unchecked")
	@Override
    protected IContentProvider getContentProvider() {
        return new ConnectorImplementationContentProvider((IRepositoryStore<IRepositoryFileStore>) getImplementationStore(), true);
    }

    @Override
    protected String getPageDescripiton() {
        return Messages.selectFilterImplementationDesc;
    }

    @Override
    protected String getPageTitle() {
        return Messages.selectFilterImplementationTitle;
    }




}
