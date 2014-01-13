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
//package org.bonitasoft.studio.properties.sections.classpath;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Collections;
//import java.util.Comparator;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//import org.bonitasoft.studio.groovy.repository.GroovyScriptRepository;
//import org.bonitasoft.studio.model.process.MainProcess;
//import org.bonitasoft.studio.properties.i18n.Messages;
//import org.bonitasoft.studio.repository.CompositeRepository;
//import org.bonitasoft.studio.repository.connectors.connector.ConnectorRepository;
//import org.bonitasoft.studio.repository.connectors.connector.ConnectorRepositoryArtifact;
//import org.bonitasoft.studio.repository.connectors.connector.ErrorConnectorArtifact;
//import org.bonitasoft.studio.repository.connectors.jar.AbstractJARArtifact;
//import org.bonitasoft.studio.repository.connectors.jar.JarRepository;
//import org.bonitasoft.studio.repository.connectors.jar.ProvidedJarRepository;
//import org.bonitasoft.studio.repository.validators.ValidatorRepository;
//import org.bonitasoft.studio.repository.validators.ValidatorRepositoryArtifact;
//import org.eclipse.jface.viewers.ITreeContentProvider;
//import org.eclipse.jface.viewers.Viewer;
//
///**
// * @author Mickael Istria
// *
// */
//public class ClasspathEntriesContentProvider implements ITreeContentProvider {
//
//	public static final String MY_LIB_CATEGORY = Messages.myLibs;
//	public static final String PROVIDED_LIB_CATEGORY = Messages.providedLibs;
//	public static final String CONNECTOR_CATEGORY = Messages.connectors;
//	protected static final String GROOVYSCRIPT_CATEGORY = Messages.groovyScript;
//	public static final String UNRESOLVED_DEPENDENCIES_CATEGORY = Messages.unresolvedDependencies;
//	public static final String VALIDATOR_CATEGORY = Messages.validators;
//	
//	protected MainProcess process;
//
//	public ClasspathEntriesContentProvider() {
//	}
//
//	/* (non-Javadoc)
//	 * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.Object)
//	 */
//	public Object[] getChildren(Object parentElement) {
//		if (parentElement.equals(MY_LIB_CATEGORY)) {
//			return JarRepository.getInstance().getAllArtifacts().toArray();
////		} else if (parentElement.equals(MY_GENERATED_LIBS)) {
////			return ComplexTypesJavaRepository.getInstance().getAllArtifacts().toArray();
//		} else if (parentElement.equals(CONNECTOR_CATEGORY)) {
//			List<? extends ConnectorRepositoryArtifact> list = ConnectorRepository.getInstance().getAllArtifacts();
//			List<ConnectorRepositoryArtifact> result = new ArrayList<ConnectorRepositoryArtifact>() ;
//			for(ConnectorRepositoryArtifact conn : list){
//				if(conn != null && !(conn instanceof ErrorConnectorArtifact)){
//					result.add(conn);
//				}
//			}
//			return result.toArray();
//		} else if (parentElement.equals(VALIDATOR_CATEGORY)) {
//			return ValidatorRepository.getInstance().getAllArtifacts().toArray();
//		} else if (parentElement.equals(PROVIDED_LIB_CATEGORY)) {
//			return showFoldersAndArtifacts(ProvidedJarRepository.getInstance().getAllArtifacts(), "");
//		} else if(parentElement.equals(GROOVYSCRIPT_CATEGORY)){
//			return GroovyScriptRepository.getInstance().getUserSciptsArtifact().toArray();
//		} else if (parentElement.equals(UNRESOLVED_DEPENDENCIES_CATEGORY)) {
//			List<UnresolvedDependency> res = new ArrayList<UnresolvedDependency>();
//			for (String dependency : process.getIncludedEntries()) {
//				if (CompositeRepository.getInstance().getArtifact(dependency) == null) {
//					res.add(new UnresolvedDependency(dependency));
//				}
//			}
//			return res.toArray();
//		} else if (parentElement instanceof String) { // Connector and deps category
//			String folderBase = (String)parentElement;
//			return showFoldersAndArtifacts(ProvidedJarRepository.getInstance().getAllArtifactsStartingBy(folderBase), folderBase);
//		} else {
//			return null;
//		}
//	}
//
//
//	/**
//	 * @param parentElement
//	 * @return
//	 */
//	private Object[] showFoldersAndArtifacts(List<AbstractJARArtifact> artifacts, String baseFolder) {
//		Set<String> folders = new HashSet<String>();
//		List<Object> res = new ArrayList<Object>();
//	
//		for (AbstractJARArtifact entry : artifacts) {
//			String folderRelativePath = entry.getId().substring(baseFolder.length());
//			if (folderRelativePath.contains("/")) {
//				folders.add(baseFolder + folderRelativePath.split("/")[0] + "/");
//			} else {
//				res.add(entry);
//			}
//		}
//		res.addAll(folders);
//		Collections.sort(res, new Comparator<Object>() {
//
//			public int compare(Object arg0,
//					Object arg1) {
//				String a1 = arg0 instanceof AbstractJARArtifact ? ((AbstractJARArtifact)arg0).getName() : (String)arg0; 
//				String a2 = arg1 instanceof AbstractJARArtifact ? ((AbstractJARArtifact)arg1).getName() : (String)arg1; 
//				return a1.compareTo(a2);
//			}
//		}) ;
//		return res.toArray();
//	}
//
//
//	/* (non-Javadoc)
//	 * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang.Object)
//	 */
//	public boolean hasChildren(Object element) {
//		return element instanceof String;
//	}
//
//	/* (non-Javadoc)
//	 * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
//	 */
//	public Object[] getElements(Object inputElement) {
//		setInput(inputElement);
//		for (String dependency : process.getIncludedEntries()) {
//			if (CompositeRepository.getInstance().getArtifact(dependency) == null) {
//				return new Object[] { CONNECTOR_CATEGORY, VALIDATOR_CATEGORY, MY_LIB_CATEGORY,  PROVIDED_LIB_CATEGORY, GROOVYSCRIPT_CATEGORY, UNRESOLVED_DEPENDENCIES_CATEGORY };
//			}
//		}
//		return new Object[] { CONNECTOR_CATEGORY, VALIDATOR_CATEGORY, MY_LIB_CATEGORY, PROVIDED_LIB_CATEGORY, GROOVYSCRIPT_CATEGORY };
//	}
//
//	/* (non-Javadoc)
//	 * @see org.eclipse.jface.viewers.IContentProvider#dispose()
//	 */
//	public void dispose() {
//
//	}
//
//	/* (non-Javadoc)
//	 * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
//	 */
//	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
//	}
//
//
//	/* (non-Javadoc)
//	 * @see org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang.Object)
//	 */
//	public Object getParent(Object element) {
//		if (element instanceof ConnectorRepositoryArtifact) {
//			return CONNECTOR_CATEGORY;
//		} else if (element instanceof AbstractJARArtifact) {
//			return MY_LIB_CATEGORY;
//		} else if (element instanceof ValidatorRepositoryArtifact){
//			return VALIDATOR_CATEGORY;
//		} else {
//			return null;
//		}
//	}
//
//	/**
//	 * @param process2
//	 */
//	public void setInput(Object process) {
//		this.process = (MainProcess)process;
//	}
//
//}
