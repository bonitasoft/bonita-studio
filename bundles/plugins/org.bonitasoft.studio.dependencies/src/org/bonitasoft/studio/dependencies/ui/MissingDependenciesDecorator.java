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

import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.dependencies.i18n.Messages;
import org.bonitasoft.studio.dependencies.repository.DependencyFileStore;
import org.bonitasoft.studio.dependencies.repository.DependencyRepositoryStore;
import org.bonitasoft.studio.model.configuration.Fragment;
import org.bonitasoft.studio.model.configuration.FragmentContainer;
import org.eclipse.jface.fieldassist.FieldDecorationRegistry;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.DecorationOverlayIcon;
import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.jface.viewers.ILabelDecorator;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;


/**
 * @author Romain Bioteau
 *
 */
public class MissingDependenciesDecorator implements ILabelDecorator {

	private Image errorIcon;
	private Image warningIcon;
	private DependencyRepositoryStore store;
	private Image warningDecoratorImage = FieldDecorationRegistry.getDefault().getFieldDecoration(FieldDecorationRegistry.DEC_WARNING).getImage();
	private Image errorDecoratorImage = FieldDecorationRegistry.getDefault().getFieldDecoration(FieldDecorationRegistry.DEC_ERROR).getImage();
	
	public MissingDependenciesDecorator(DependencyRepositoryStore store){
		this.store = store;
	}
	
	public MissingDependenciesDecorator(){
		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IBaseLabelProvider#addListener(org.eclipse.jface.viewers.ILabelProviderListener)
	 */
	@Override
	public void addListener(ILabelProviderListener arg0) {

	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IBaseLabelProvider#dispose()
	 */
	@Override
	public void dispose() {
		if(errorIcon != null){
			errorIcon.dispose() ;
		}
		if(warningIcon != null){
			warningIcon.dispose() ;
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
		if(element instanceof Fragment && image != null){
			Fragment fragment = (Fragment) element ;
			String lib = fragment.getValue() ;
			if(lib.endsWith(DependencyRepositoryStore.JAR_EXT)){
				if (isDependencyMissing(lib, fragment)) {
					return getErrorDecoratedImage(image);
				}else if(isInRuntimeContainer(lib,fragment)){
					return getWarningDecoratedImage(image);
				}else if(isInRuntimeContainerWithAnotherVersion(lib,fragment)){
					return getWarningDecoratedImage(image);
				}else{
					return image;
				}
			}
		}
		return null;
	}

	protected Image getErrorDecoratedImage(Image image) {
		if(errorIcon == null){
			errorIcon =  new DecorationOverlayIcon(image,new ImageDescriptor() {
				
				@Override
				public ImageData getImageData() {
					return errorDecoratorImage.getImageData();
				}
			} , IDecoration.BOTTOM_RIGHT).createImage() ;
		}
		return errorIcon;
	}
	protected Image getWarningDecoratedImage(Image image) {
		if(warningIcon == null){
			
			warningIcon =  new DecorationOverlayIcon(image,new ImageDescriptor() {
				
				@Override
				public ImageData getImageData() {
					return warningDecoratorImage.getImageData();
				}
			}, IDecoration.BOTTOM_RIGHT).createImage() ;
		}
		return warningIcon;
	}

	protected boolean isInRuntimeContainerWithAnotherVersion(String lib,Fragment fragment) {
		DependencyFileStore fileStore = store.getChild(lib);
		if(fileStore != null){
			return fileStore.existsInRuntimeContainerWithAnotherVersion();
		}
		return false;
	}

	protected boolean isInRuntimeContainer(String lib, Fragment fragment) {
		DependencyFileStore fileStore = store.getChild(lib);
		if(fileStore != null){
			return fileStore.existsInRuntimeContainer();
		}
		return false;
	}

	protected boolean isDependencyMissing(String libName, Fragment fragment) {
		IRepositoryFileStore file = store.getChild(libName) ;
		if(file == null && isGeneratedJar(libName,fragment)){//Check in custom connector
			return false;
		}
		return file == null;
	}

	protected boolean isGeneratedJar(String lib, Fragment fragment) {
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
		if(element instanceof Fragment && text != null){
			Fragment fragment = (Fragment) element ;
			String lib = fragment.getValue() ;
			if(lib.endsWith(DependencyRepositoryStore.JAR_EXT)){
				if(isDependencyMissing(lib, fragment)){
					return text + " ("+Messages.missingDependenciesInRepository+")";
				}else if(isInRuntimeContainer(lib, fragment)){
					return text + " ("+Messages.dependencyExistsInRuntimeContainer+")";
				}else if(isInRuntimeContainerWithAnotherVersion(lib, fragment)){
					return text + " ("+Messages.dependencyExistsInRuntimeContainerWithAnotherVersion+")";
				}else {
					return text;
				}
			}
		}
		return null;
	}

}
