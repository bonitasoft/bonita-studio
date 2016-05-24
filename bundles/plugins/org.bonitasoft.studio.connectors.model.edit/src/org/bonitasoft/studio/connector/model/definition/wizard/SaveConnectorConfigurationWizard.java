/**
 * Copyright (C) 2009 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 *
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
package org.bonitasoft.studio.connector.model.definition.wizard;

import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.connector.model.definition.IDefinitionRepositoryStore;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfiguration;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.wizard.Wizard;

/**
 * @author Romain Bioteau
 *
 */
public class SaveConnectorConfigurationWizard extends Wizard  {


    private final ConnectorConfiguration currentConfiguraiton;
    private SaveConnectorConfigurationWizardPage page;
    private final IRepositoryStore<? extends IRepositoryFileStore> configurationStore;
    private final IDefinitionRepositoryStore definitionRepositoryStore;

    public SaveConnectorConfigurationWizard(ConnectorConfiguration currentConfiguraiton, IRepositoryStore<? extends IRepositoryFileStore> configurationStore,
            IDefinitionRepositoryStore definitionRepositoryStore) {
        setDefaultPageImageDescriptor(Pics.getWizban()) ;
        this.currentConfiguraiton = currentConfiguraiton ;
        this.configurationStore = configurationStore ;
        this.definitionRepositoryStore = definitionRepositoryStore;
    }


    @Override
    public void addPages() {
        page = new SaveConnectorConfigurationWizardPage(currentConfiguraiton, configurationStore, definitionRepositoryStore);
        addPage(page);
    }


    /* (non-Javadoc)
     * @see org.eclipse.jface.wizard.Wizard#performFinish()
     */
    @Override
    public boolean performFinish() {
        final IRepositoryFileStore fileStore =  configurationStore.createRepositoryFileStore(page.getConfName()+".connectorconfig") ;
        final ConnectorConfiguration savedConf = EcoreUtil.copy(currentConfiguraiton) ;
        savedConf.setName(page.getConfName()) ;
        fileStore.save(savedConf) ;
        return true;
    }



}
