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
package org.bonitasoft.studio.validators.provider;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bonitasoft.studio.common.FragmentTypes;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.configuration.extension.IConfigurationSynchronizer;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.configuration.ConfigurationFactory;
import org.bonitasoft.studio.model.configuration.ConfigurationPackage;
import org.bonitasoft.studio.model.configuration.Fragment;
import org.bonitasoft.studio.model.configuration.FragmentContainer;
import org.bonitasoft.studio.model.form.FormPackage;
import org.bonitasoft.studio.model.form.Validator;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.validators.descriptor.validator.ValidatorDescriptor;
import org.bonitasoft.studio.validators.repository.ValidatorDescriptorFileStore;
import org.bonitasoft.studio.validators.repository.ValidatorDescriptorRepositoryStore;
import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.domain.EditingDomain;


/**
 * @author Romain Bioteau
 *
 */
public class ValidatorConfigurationSynchronizer implements IConfigurationSynchronizer {

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.configuration.extension.IConfigurationSynchronizer#synchronize(org.eclipse.emf.common.command.CompoundCommand)
	 */
	@Override
	public void synchronize(Configuration configuration, AbstractProcess process, CompoundCommand cc, EditingDomain editingDomain) {

		addNewValidators(configuration, process, cc, editingDomain) ;
		removeDeletedValidators(configuration, process, cc, editingDomain) ;


	}

	private void addNewValidators(Configuration configuration, AbstractProcess process,CompoundCommand cc, EditingDomain editingDomain) {
		ValidatorDescriptorRepositoryStore store = (ValidatorDescriptorRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(ValidatorDescriptorRepositoryStore.class) ;
		List<Validator> validators = ModelHelper.getAllItemsOfType(process, FormPackage.Literals.VALIDATOR) ;
		FragmentContainer container = getContainer(configuration) ;
		Assert.isNotNull(container) ;

		Set<String> existingIds = new HashSet<String>();
		for(Validator validator : validators){
			boolean exists = false ;
			for(FragmentContainer validatorContainer : container.getChildren()){
				ValidatorDescriptor descriptor = store.getValidatorDescriptor(validator.getValidatorClass()) ;
				if(validatorContainer.getId().equals(descriptor.getName())){
					exists = true ;
					existingIds.add(validatorContainer.getId());
					updateFragment(configuration, validatorContainer,cc,editingDomain);
					break ;
				}
			}
			if(!exists){
				if(isAUserValidatorClass(validator)){
					FragmentContainer validatorContainer = ConfigurationFactory.eINSTANCE.createFragmentContainer() ;
					ValidatorDescriptor descriptor = store.getValidatorDescriptor(validator.getValidatorClass()) ;
					if(!existingIds.contains(descriptor.getName())){
						validatorContainer.setId(descriptor.getName()) ;
						cc.append(AddCommand.create(editingDomain, container, ConfigurationPackage.Literals.FRAGMENT_CONTAINER__CHILDREN, validatorContainer)) ;
						existingIds.add(descriptor.getName());
						updateFragment(configuration, validatorContainer, cc, editingDomain) ;
					}
				}
			}
		}
	}

	private FragmentContainer getContainer(Configuration configuration) {
		for(FragmentContainer container: configuration.getApplicationDependencies()){
			if(container.getId().equals(FragmentTypes.VALIDATOR)){
				return container ;
			}
		}
		return null;
	}

	private boolean isAUserValidatorClass(Validator validator) {
		ValidatorDescriptorRepositoryStore store = (ValidatorDescriptorRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(ValidatorDescriptorRepositoryStore.class) ;
		ValidatorDescriptor desc = store.getValidatorDescriptor(validator.getValidatorClass()) ;
		if(desc != null){
			String fileName = URI.decode(desc.eResource().getURI().lastSegment());
			IRepositoryFileStore file = store.getChild(fileName) ;
			return file != null && file.canBeShared();
		}
		return false;
	}

	private void updateFragment(Configuration configuration, FragmentContainer validatorContainer,CompoundCommand cc, EditingDomain editingDomain) {
		final ValidatorDescriptorRepositoryStore store = (ValidatorDescriptorRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(ValidatorDescriptorRepositoryStore.class) ;
		ValidatorDescriptorFileStore file =  (ValidatorDescriptorFileStore) store.getChild(validatorContainer.getId()+"."+ValidatorDescriptorRepositoryStore.VALIDATOR_EXT) ;
		if(file != null){
			ValidatorDescriptor descriptor = file.getContent() ;
			if(descriptor != null){
				String validatorJar = descriptor.getClassName() ;
				for(Fragment fragment :  validatorContainer.getFragments()){
					if(!descriptor.getDependencies().contains(fragment.getValue()) && !validatorJar.equals(fragment.getValue())){ //Remove unused dependencies
						cc.append(RemoveCommand.create(editingDomain, validatorContainer,ConfigurationPackage.Literals.FRAGMENT_CONTAINER__FRAGMENTS,fragment)) ;
					}
				}

				boolean jarExists = false ;
				for(Fragment fragment :  validatorContainer.getFragments()){
					if(fragment.getValue().equals(validatorJar)){
						jarExists = true ;
						break ;
					}
				}

				if(!jarExists){
					Fragment newFragment = ConfigurationFactory.eINSTANCE.createFragment() ;
					newFragment.setType(FragmentTypes.VALIDATOR) ;
					newFragment.setKey(validatorContainer.getId()) ;
					newFragment.setValue(validatorJar) ;
					cc.append(AddCommand.create(editingDomain,validatorContainer,ConfigurationPackage.Literals.FRAGMENT_CONTAINER__FRAGMENTS, newFragment)) ;
				}

				for(String dep : descriptor.getDependencies()){
					boolean depExists = false ;
					for(Fragment fragment :  validatorContainer.getFragments()){
						if(fragment.getValue().equals(dep)){
							depExists = true ;
							break ;
						}
					}

					if(!depExists){
						Fragment newFragment = ConfigurationFactory.eINSTANCE.createFragment() ;
						newFragment.setType(FragmentTypes.JAR) ;
						newFragment.setKey(validatorContainer.getId()) ;
						newFragment.setValue(dep) ;
						cc.append(AddCommand.create(editingDomain,validatorContainer,ConfigurationPackage.Literals.FRAGMENT_CONTAINER__FRAGMENTS, newFragment)) ;
					}
				}
			}
		}
	}



	private void removeDeletedValidators(Configuration configuration, AbstractProcess process,CompoundCommand cc, EditingDomain editingDomain) {
		List<Validator> processValidators = ModelHelper.getAllItemsOfType(process, FormPackage.Literals.VALIDATOR) ;
		FragmentContainer container = getContainer(configuration) ;
		Assert.isNotNull(container) ;
		final ValidatorDescriptorRepositoryStore store = (ValidatorDescriptorRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(ValidatorDescriptorRepositoryStore.class) ;

		for(FragmentContainer validatorContainer : container.getChildren()){
			boolean exists = false ;
			for(Validator v : processValidators){
				ValidatorDescriptor descriptor = store.getValidatorDescriptor(v.getValidatorClass()) ;
				if(validatorContainer.getId().equals(descriptor.getName())){
					exists = true ;
					break ;
				}
			}
			if(!exists){//Remove unused validator
				cc.append(RemoveCommand.create(editingDomain, container, ConfigurationPackage.Literals.FRAGMENT_CONTAINER__CHILDREN, validatorContainer)) ;
			}
		}
	}

	@Override
	public String getFragmentContainerId() {
		return FragmentTypes.VALIDATOR;
	}

	@Override
	public EStructuralFeature getDependencyKind() {
		return APPLICATION_DEPENDENCY;
	}


}
