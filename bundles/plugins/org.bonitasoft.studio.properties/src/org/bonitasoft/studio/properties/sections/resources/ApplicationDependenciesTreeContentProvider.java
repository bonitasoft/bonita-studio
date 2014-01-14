///**
// * Copyright (C) 2009 BonitaSoft S.A.
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
//import java.io.File;
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//import org.bonitasoft.studio.common.repository.AbstractRepositoryArtifact;
//import org.bonitasoft.studio.model.process.ResourceContainer;
//import org.bonitasoft.studio.properties.i18n.Messages;
//import org.bonitasoft.studio.repository.connectors.jar.AbstractJARArtifact;
//import org.bonitasoft.studio.repository.connectors.jar.JarArtifact;
//import org.bonitasoft.studio.repository.connectors.jar.JarRepository;
//import org.bonitasoft.studio.repository.connectors.jar.ProvidedJarRepository;
//import org.bonitasoft.studio.repository.validators.ValidatorRepository;
//import org.bonitasoft.studio.repository.validators.ValidatorRepositoryArtifact;
//import org.eclipse.jface.viewers.ITreeContentProvider;
//import org.eclipse.jface.viewers.Viewer;
//
///**
// * 
// * 
// * @author Baptiste Mesta
// * 
// */
//public class ApplicationDependenciesTreeContentProvider implements ITreeContentProvider {
//
//	public static final Object LIB_CATEGORY = Messages.Application_MyLibs;
//	public static final Object VALIDATOR_CATEGORY = Messages.Application_Validators;
//	public static final Object[] CATEGORYS = { LIB_CATEGORY, VALIDATOR_CATEGORY };
//	private ResourceContainer container;
//
//	/**
//	 * Gets the children of the specified object
//	 * 
//	 * @param parentElement
//	 *            the parent object
//	 * @return Object[]
//	 */
//	public Object[] getChildren(Object parentElement) {
//		if (parentElement.equals(LIB_CATEGORY)) {
//			return JarRepository.getInstance().getAllArtifacts().toArray();
//		} else if (parentElement.equals(VALIDATOR_CATEGORY)) {
//			return ValidatorRepository.getInstance().getAllArtifacts().toArray();
//		} else if (parentElement instanceof String) {
//			String folderBase = (String) parentElement;
//			return showFoldersAndArtifacts(ProvidedJarRepository.getInstance().getAllArtifactsStartingBy(folderBase), folderBase);
//		}
//		return new Object[0];
//
//	}
//
//	/**
//	 * @param parentElement
//	 * @return
//	 */
//	private Object[] showFoldersAndArtifacts(List<AbstractJARArtifact> artifacts, String baseFolder) {
//		Set<String> folders = new HashSet<String>();
//		List<Object> res = new ArrayList<Object>();
//		for (AbstractJARArtifact entry : artifacts) {
//			String folderRelativePath = entry.getId().substring(baseFolder.length());
//			if (folderRelativePath.contains("/")) {
//				folders.add(baseFolder + folderRelativePath.split("/")[0] + "/");
//			} else {
//				res.add(entry);
//			}
//		}
//		res.addAll(folders);
//		return res.toArray();
//	}
//
//	/**
//	 * Gets the parent of the specified object
//	 * 
//	 * @param parentElement
//	 *            the object
//	 * @return Object
//	 */
//	public Object getParent(Object element) {
//		if (element instanceof AbstractRepositoryArtifact) {
//			if (element instanceof JarArtifact) {
//				return LIB_CATEGORY;
//			} else if (element instanceof ValidatorRepositoryArtifact) {
//				return VALIDATOR_CATEGORY;
//			}
//		}
//		return null;
//	}
//
//	/**
//	 * Returns whether the passed object has children
//	 * 
//	 * @param arg0
//	 *            the parent object
//	 * @return boolean
//	 */
//	public boolean hasChildren(Object arg0) {
//		// Get the children
//		Object[] obj = getChildren(arg0);
//
//		// Return whether the parent has children
//		return obj == null ? false : obj.length > 0;
//	}
//
//	/**
//	 * Gets the root element(s) of the tree
//	 * 
//	 * @param arg0
//	 *            the input data
//	 * @return Object[]
//	 */
//	public Object[] getElements(Object arg0) {
//		if (arg0.equals(container)) {
//			return CATEGORYS;
//		} else {
//			return File.listRoots();
//		}
//	}
//
//	/**
//	 * Disposes any created resources
//	 */
//	public void dispose() {
//		// Nothing to dispose
//	}
//
//	/**
//	 * Called when the input changes
//	 * 
//	 * @param arg0
//	 *            the viewer
//	 * @param arg1
//	 *            the old input
//	 * @param arg2
//	 *            the new input
//	 */
//	public void inputChanged(Viewer arg0, Object arg1, Object arg2) {
//		this.container = (ResourceContainer) arg2;
//	}
//
//	/**
//	 * @return
//	 */
//	public ResourceContainer getResourceContainer() {
//		return this.container;
//	}
//
//}
