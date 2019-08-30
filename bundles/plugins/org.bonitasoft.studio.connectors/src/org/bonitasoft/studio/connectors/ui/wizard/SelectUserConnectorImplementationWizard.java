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
package org.bonitasoft.studio.connectors.ui.wizard;

import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.common.repository.store.SourceRepositoryStore;
import org.bonitasoft.studio.connector.model.definition.IDefinitionRepositoryStore;
import org.bonitasoft.studio.connector.model.implementation.provider.ConnectorImplementationContentProvider;
import org.bonitasoft.studio.connector.model.implementation.provider.ConnectorImplementationLabelProvider;
import org.bonitasoft.studio.connector.model.implementation.wizard.AbstractSelectImplementationWizard;
import org.bonitasoft.studio.connectors.ConnectorPlugin;
import org.bonitasoft.studio.connectors.i18n.Messages;
import org.bonitasoft.studio.connectors.repository.ConnectorDefRepositoryStore;
import org.bonitasoft.studio.connectors.repository.ConnectorImplRepositoryStore;
import org.bonitasoft.studio.connectors.repository.ConnectorSourceRepositoryStore;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.LabelProvider;


/**
 * @author Romain Bioteau
 *
 */
public class SelectUserConnectorImplementationWizard extends AbstractSelectImplementationWizard {


    public SelectUserConnectorImplementationWizard(){
        super() ;
        setWindowTitle(Messages.selectConnectorImplementationTitle);
    }

    @Override
    protected LabelProvider getLabelProvider() {
        return new ConnectorImplementationLabelProvider((IDefinitionRepositoryStore)RepositoryManager.getInstance().getRepositoryStore(ConnectorDefRepositoryStore.class), ConnectorPlugin.getDefault().getBundle());
    }

    @SuppressWarnings("unchecked")
	@Override
    protected IContentProvider getContentProvider() {
        return new ConnectorImplementationContentProvider(getImplementationStore(), true);
    }

    @Override
    protected String getPageDescripiton() {
        return Messages.selectConnectorImplementationDesc;
    }

    @Override
    protected String getPageTitle() {
        return Messages.selectConnectorImplementationTitle;
    }

    @Override
    protected SourceRepositoryStore getSourceStore() {
        return (SourceRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(ConnectorSourceRepositoryStore.class);
    }

    @Override
    protected IRepositoryStore getImplementationStore() {
        return RepositoryManager.getInstance().getRepositoryStore(ConnectorImplRepositoryStore.class);
    }





}
