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
package org.bonitasoft.studio.groovy.ui.providers;

import java.util.List;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.FragmentTypes;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.configuration.extension.IConfigurationSynchronizer;
import org.bonitasoft.studio.groovy.repository.GroovyFileStore;
import org.bonitasoft.studio.groovy.repository.GroovyRepositoryStore;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.configuration.ConfigurationFactory;
import org.bonitasoft.studio.model.configuration.ConfigurationPackage;
import org.bonitasoft.studio.model.configuration.Fragment;
import org.bonitasoft.studio.model.configuration.FragmentContainer;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.domain.EditingDomain;


/**
 * @author Romain Bioteau
 *
 */
public abstract class AbstractGroovyScriptConfigurationSynchronizer implements IConfigurationSynchronizer {

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.configuration.extension.IConfigurationSynchronizer#synchronize(org.bonitasoft.studio.model.configuration.Configuration, org.bonitasoft.studio.model.process.AbstractProcess, org.eclipse.emf.common.command.CompoundCommand, org.eclipse.emf.edit.domain.EditingDomain)
     */
    @Override
    public void synchronize(Configuration configuration, AbstractProcess process, CompoundCommand cc, EditingDomain editingDomain) {
        GroovyRepositoryStore store = (GroovyRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(GroovyRepositoryStore.class) ;
        addNewPackage(configuration,process, store, cc, editingDomain) ;
        removeDeletedPackage(configuration, store, cc, editingDomain)  ;
    }

    private void addNewPackage(Configuration configuration,AbstractProcess process, GroovyRepositoryStore store,CompoundCommand cc, EditingDomain editingDomain) {
        List<GroovyFileStore> fileStores = store.getChildren() ;
        FragmentContainer groovyContainer = getContainer(configuration) ;
        Assert.isNotNull(groovyContainer) ;

        for(IRepositoryFileStore fileStore : fileStores){
            String name = fileStore.getName() ;
            boolean exists = false ;
            for(Fragment f : groovyContainer.getFragments()){
                if(f.getValue().equals(name)){
                    exists = true ;
                    break ;
                }
            }
            if(!exists){
                Fragment newFragment = ConfigurationFactory.eINSTANCE.createFragment() ;
                newFragment.setType(FragmentTypes.GROOVY_SCRIPT) ;
                newFragment.setKey(name) ;
                newFragment.setValue(name) ;
                List<Expression> expressions = ModelHelper.getAllItemsOfType(process, ExpressionPackage.Literals.EXPRESSION) ;
                newFragment.setExported(false) ;
                if(name.indexOf(".") != -1){
                    name = name.substring(0, name.lastIndexOf(".")) ;
                }

                for(Expression exp : expressions){
                    if(exp.getType() != null && exp.getType().equals(ExpressionConstants.SCRIPT_TYPE)){
                        if(exp.getContent() != null && exp.getContent().contains(name)){
                            newFragment.setExported(true) ;
                            break ;
                        }
                    }
                }
                cc.append(AddCommand.create(editingDomain,groovyContainer,ConfigurationPackage.Literals.FRAGMENT_CONTAINER__FRAGMENTS, newFragment)) ;
            }
        }

    }

    protected abstract FragmentContainer getContainer(Configuration configuration) ;


    private void removeDeletedPackage(Configuration configuration, GroovyRepositoryStore store,CompoundCommand cc, EditingDomain editingDomain) {
        List<GroovyFileStore> fileStores = store.getChildren() ;
        FragmentContainer container = getContainer(configuration) ;
        for(Fragment f : container.getFragments()){
            boolean exists = false ;
            for(IRepositoryFileStore fileStore : fileStores){
                if(f.getValue().equals(fileStore.getName())){
                    exists = true ;
                    break ;
                }
            }
            if(!exists){
                cc.append(RemoveCommand.create(editingDomain, container, ConfigurationPackage.Literals.FRAGMENT_CONTAINER__FRAGMENTS, f)) ;
            }
        }
    }

    @Override
    public String getFragmentContainerId() {
        return FragmentTypes.GROOVY_SCRIPT ;
    }


}
