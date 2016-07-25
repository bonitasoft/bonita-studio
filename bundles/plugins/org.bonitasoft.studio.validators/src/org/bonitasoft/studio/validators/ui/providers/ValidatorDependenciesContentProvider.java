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
package org.bonitasoft.studio.validators.ui.providers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.form.Validator;
import org.bonitasoft.studio.validators.descriptor.validator.ValidatorDescriptor;
import org.bonitasoft.studio.validators.repository.ValidatorDescriptorFileStore;
import org.bonitasoft.studio.validators.repository.ValidatorDescriptorRepositoryStore;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;


/**
 * @author Romain Bioteau
 *
 */
public class ValidatorDependenciesContentProvider implements ITreeContentProvider {


    private final ValidatorDescriptorRepositoryStore validatorStore;
    public ValidatorDependenciesContentProvider(Configuration configuration){
        validatorStore = (ValidatorDescriptorRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(ValidatorDescriptorRepositoryStore.class) ;
    }
    /* (non-Javadoc)
     * @see org.eclipse.jface.viewers.IContentProvider#dispose()
     */
    @Override
    public void dispose() {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
     */
    @Override
    public void inputChanged(Viewer arg0, Object arg1, Object arg2) {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.Object)
     */
    @Override
    public Object[] getChildren(Object element) {
        //        if(element instanceof ValidatorDescriptorFileStore){
        //            ValidatorDescriptor descriptor = ((ValidatorDescriptorFileStore) element).getContent() ;
        //            List<Fragment> result = new ArrayList<Fragment>() ;
        //            for(Fragment fragment : configuration.getDependencies()){
        //                if(fragment.getType().equals(FragmentTypes.VALIDATOR)
        //                        && fragment.getKey().equals(descriptor.getClassName())){
        //                    result.add(fragment) ;
        //                }
        //            }
        //            return result.toArray() ;
        //        }
        return null;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.viewers.ITreeContentProvider#getElements(java.lang.Object)
     */
    @Override
    public Object[] getElements(Object element) {
        if(element instanceof Collection<?>){
            List<ValidatorDescriptorFileStore> result = new ArrayList<ValidatorDescriptorFileStore>() ;
            for(Object elem : ((Collection<?>) element)){
                if(elem instanceof Validator){
                    ValidatorDescriptor descriptor = validatorStore.getValidatorDescriptor(((Validator) elem).getValidatorClass()) ;
                    if(descriptor != null){
                        ValidatorDescriptorFileStore file = (ValidatorDescriptorFileStore) validatorStore.getChild(URI.decode(descriptor.eResource().getURI().lastSegment())) ;
                        if(file != null && file.canBeShared()){
                            result.add(file) ;
                        }
                    }

                }
            }
            return result.toArray() ;
        }
        return null;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang.Object)
     */
    @Override
    public Object getParent(Object arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang.Object)
     */
    @Override
    public boolean hasChildren(Object element) {
        if(element instanceof ValidatorDescriptorFileStore){
            ValidatorDescriptor descriptor = ((ValidatorDescriptorFileStore) element).getContent() ;
            return !descriptor.getDependencies().isEmpty() ;
        }
        return false;
    }

}
