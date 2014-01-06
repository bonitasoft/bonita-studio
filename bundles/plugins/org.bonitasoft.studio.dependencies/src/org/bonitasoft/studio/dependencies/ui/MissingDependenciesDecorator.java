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
package org.bonitasoft.studio.dependencies.ui;

import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.dependencies.i18n.Messages;
import org.bonitasoft.studio.dependencies.repository.DependencyRepositoryStore;
import org.bonitasoft.studio.model.configuration.Fragment;
import org.bonitasoft.studio.model.configuration.FragmentContainer;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.eclipse.jface.viewers.DecorationOverlayIcon;
import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.jface.viewers.ILabelDecorator;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;


/**
 * @author Romain Bioteau
 *
 */
public class MissingDependenciesDecorator implements ILabelDecorator {

    private Image icon;

    /* (non-Javadoc)
     * @see org.eclipse.jface.viewers.IBaseLabelProvider#addListener(org.eclipse.jface.viewers.ILabelProviderListener)
     */
    @Override
    public void addListener(ILabelProviderListener arg0) {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.viewers.IBaseLabelProvider#dispose()
     */
    @Override
    public void dispose() {
        if(icon != null){
            icon.dispose() ;
        }
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.viewers.IBaseLabelProvider#isLabelProperty(java.lang.Object, java.lang.String)
     */
    @Override
    public boolean isLabelProperty(Object arg0, String arg1) {
        return false;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.viewers.IBaseLabelProvider#removeListener(org.eclipse.jface.viewers.ILabelProviderListener)
     */
    @Override
    public void removeListener(ILabelProviderListener arg0) {


    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.viewers.ILabelDecorator#decorateImage(org.eclipse.swt.graphics.Image, java.lang.Object)
     */
    @Override
    public Image decorateImage(Image image, Object element) {
        if(element instanceof Fragment){
            Fragment fragment = (Fragment) element ;
            String lib = fragment.getValue() ;
            if(lib.endsWith(DependencyRepositoryStore.JAR_EXT)){
                final DependencyRepositoryStore store = (DependencyRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(DependencyRepositoryStore.class) ;
                IRepositoryFileStore file = store.getChild(lib) ;
                if(file == null && isGeneratedJar(lib,fragment)){//Check in custom connector
                	return null;
                }
                if (image != null && file == null) {
                    if(icon == null){
                        icon =  new DecorationOverlayIcon(image,Pics.getImageDescriptor(PicsConstants.error_decorator) , IDecoration.BOTTOM_RIGHT).createImage() ;
                    }
                    return icon;
                }
            }
        }
        return null;
    }

    private boolean isGeneratedJar(String lib, Fragment fragment) {
    	FragmentContainer container = (FragmentContainer) fragment.eContainer();
    	String id = container.getId();
    	if(lib.equals(id+".jar")){
    		return true;
    	}
    	return false;
	}

	/* (non-Javadoc)
     * @see org.eclipse.jface.viewers.ILabelDecorator#decorateText(java.lang.String, java.lang.Object)
     */
    @Override
    public String decorateText(String text, Object element) {
        if(element instanceof Fragment){
            Fragment fragment = (Fragment) element ;
            String lib = fragment.getValue() ;
            if(lib.endsWith(DependencyRepositoryStore.JAR_EXT)){
                final DependencyRepositoryStore store = (DependencyRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(DependencyRepositoryStore.class) ;
                IRepositoryFileStore file = store.getChild(lib) ;
                if(file == null && isGeneratedJar(lib, fragment) && text != null){
                	return text;
                }
                if (text != null && file == null) {
                    return text + " ("+Messages.missingDependenciesInRepository+")";
                }
            }
        }
        return null;
    }

}
