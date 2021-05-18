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
package org.bonitasoft.studio.connector.model.implementation.wizard;

import org.bonitasoft.studio.common.repository.filestore.AbstractFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.common.repository.store.SourceRepositoryStore;
import org.bonitasoft.studio.connector.model.implementation.ConnectorImplementation;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.wizard.Wizard;


/**
 * @author Romain Bioteau
 *
 */
public abstract class AbstractSelectImplementationWizard extends Wizard {

    private SelectConnectorImplementationWizardPage page ;
    private ConnectorImplementation implemenetation;

    public AbstractSelectImplementationWizard(){
        setDefaultPageImageDescriptor(Pics.getWizban()) ;
    }

    @Override
    public void addPages() {
        page =  new SelectConnectorImplementationWizardPage(getPageTitle(),getPageDescripiton(),getContentProvider(),getLabelProvider(),getImplementationStore(),getSourceStore()) ;
        addPage(page) ;
    }

    protected abstract SourceRepositoryStore<? extends AbstractFileStore> getSourceStore() ;

    protected abstract IRepositoryStore<? extends IRepositoryFileStore> getImplementationStore() ;

    protected abstract LabelProvider getLabelProvider() ;

    protected abstract IContentProvider getContentProvider() ;

    protected abstract String getPageDescripiton() ;

    protected abstract String getPageTitle() ;

    @Override
    public boolean performFinish() {
        implemenetation = page.getSelectedImplementation() ;
        return true;
    }

    public ConnectorImplementation getConnectorImplementation(){
        return implemenetation ;
    }


}
