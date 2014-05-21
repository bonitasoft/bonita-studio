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
package org.bonitasoft.studio.configuration.ui.wizard.page;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.configuration.ConfigurationPlugin;
import org.bonitasoft.studio.configuration.i18n.Messages;
import org.bonitasoft.studio.dependencies.repository.DependencyRepositoryStore;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.configuration.ConfigurationPackage;
import org.bonitasoft.studio.model.configuration.Fragment;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.graphics.Image;

/**
 * @author Romain Bioteau
 *
 */
public class ApplicationDependenciesConfigurationWizardPage extends AbstractDependenciesConfigurationWizardPage {

    public ApplicationDependenciesConfigurationWizardPage() {
        super(ApplicationDependenciesConfigurationWizardPage.class.getName());
        setTitle(Messages.applicationDependencies) ;
        setDescription(Messages.applicationDependenciesConfigurationDescription) ;
    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.configuration.extension.IProcessConfigurationWizardPage#isConfigurationPageValid()
     */
    @Override
    public String isConfigurationPageValid(Configuration configuration) {
        if(configuration !=null) {
            final DependencyRepositoryStore store = (DependencyRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(DependencyRepositoryStore.class) ;
            for(EObject f : ModelHelper.getAllItemsOfType(configuration,ConfigurationPackage.Literals.FRAGMENT)){
                if(f instanceof Fragment && ((Fragment) f).isExported() && isAnApplicationDependency(f)){
                    String jarName = ((Fragment) f).getValue() ;
                    if(jarName.endsWith(DependencyRepositoryStore.JAR_EXT)){
                        IRepositoryFileStore jarFile =  store.getChild(jarName) ;
                        if(jarFile == null){
                            return Messages.bind(Messages.missingJarFileInRepository, ((Fragment) f).getValue()) ;
                        }
                    }
                }
            }
        }
        return null;
    }

    protected boolean isAnApplicationDependency(EObject f) {
        return ConfigurationPackage.Literals.CONFIGURATION__APPLICATION_DEPENDENCIES.equals(getContainingFeature(f));
    }


    /* (non-Javadoc)
     * @see org.bonitasoft.studio.configuration.extension.IProcessConfigurationWizardPage#getConfigurationImage()
     */
    @Override
    public Image getConfigurationImage() {
        return Pics.getImage("dependencies.png",ConfigurationPlugin.getDefault());
    }

    @Override
    protected Object getViewerInput(Configuration configuration) {
        return configuration.getApplicationDependencies();
    }

    @Override
    public boolean isDefault() {
        return false;
    }



}
