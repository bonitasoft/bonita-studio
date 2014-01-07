///**
// * Copyright (C) 2009-2010 BonitaSoft S.A.
// * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
// *
// * This program is free software: you can redistribute it and/or modify
// * it under the terms of the GNU General Public License as published by
// * the Free Software Foundation, either version 2.0 of the License, or
// * (at your option) any later version.
// *
// * This program is distributed in the hope that it will be useful,
// * but WITHOUT ANY WARRANTY; without even the implied warranty of
// * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// * GNU General Public License for more details.
// *
// * You should have received a copy of the GNU General Public License
// * along with this program.  If not, see <http://www.gnu.org/licenses/>.
// */
//package org.bonitasoft.studio.properties.sections.resources;
//
//import org.bonitasoft.studio.model.process.ResourceContainer;
//import org.bonitasoft.studio.repository.connectors.jar.JarArtifact;
//import org.bonitasoft.studio.repository.validators.ValidatorRepositoryArtifact;
//import org.eclipse.jface.viewers.ICheckStateProvider;
//
///**
// * 
// * To be check a file need to be registered as a ResourceFolder or a
// * ResourceFile in the Form given to updateResourceFile
// * 
// * @author Baptiste Mesta
// * @author Aurelien Pupier
// * 				- have correct check/greyed state from the beginning
// */
//public class ApplicationDependenciesCheckStateProvider implements ICheckStateProvider {
//
//	private ApplicationDependenciesTreeContentProvider contentProvider;
//	private ResourceContainer resContainer;
//
//	public ApplicationDependenciesCheckStateProvider(ApplicationDependenciesTreeContentProvider appDepsContentProvider) {
//		this.contentProvider = appDepsContentProvider;
//		this.resContainer = appDepsContentProvider.getResourceContainer();
//	}
//
//	public boolean isChecked(Object element) {
//		if (resContainer == null) {
//			return false;
//		}
//		if(ApplicationDependenciesTreeContentProvider.LIB_CATEGORY.equals(element)
//				|| ApplicationDependenciesTreeContentProvider.VALIDATOR_CATEGORY.equals(element)){
//			if(contentProvider.hasChildren(element)){
//				return hasOneSubChecked(element);
//			} else {
//				return false;
//			}
//		} else if (element instanceof ValidatorRepositoryArtifact) {
//			return resContainer != null && resContainer.getResourceValidators() != null
//					&& resContainer.getResourceValidators().contains(((ValidatorRepositoryArtifact) element).getId());
//		} else if(element instanceof JarArtifact){
//			return resContainer != null && resContainer.getResourceJars() != null
//			&& resContainer.getResourceJars().contains(((JarArtifact) element).getId());
//		} else {
//			return false;
//		}
//	}
//
//	public boolean isGrayed(Object element) {
//		if(ApplicationDependenciesTreeContentProvider.LIB_CATEGORY.equals(element)
//				|| ApplicationDependenciesTreeContentProvider.VALIDATOR_CATEGORY.equals(element)){
//			return !isAllSubChecked(element)
//			&& hasOneSubChecked(element);
//		} else {
//			return false;
//		}
//		
//	}
//
//	private boolean hasOneSubChecked(Object element) {
//		if(contentProvider.hasChildren(element)){
//			for (Object el : contentProvider.getChildren(element)) {
//				if(hasOneSubChecked(el))
//					return true;
//			}
//			return false;
//		}else{
//			return isChecked(element);
//		}
//	}
//
//	private boolean isAllSubChecked(Object element) {
//		if(contentProvider.hasChildren(element)){
//			for (Object el : contentProvider.getChildren(element)) {
//				if(!isAllSubChecked(el))
//					return false;
//			}
//			return true;
//		}else{
//			return isChecked(element);
//		}
//	}
//
//	/**
//	 * @param eObject
//	 */
//	public void setResourceContainer(ResourceContainer eObject) {
//		this.resContainer = eObject;
//	}
//
//}
