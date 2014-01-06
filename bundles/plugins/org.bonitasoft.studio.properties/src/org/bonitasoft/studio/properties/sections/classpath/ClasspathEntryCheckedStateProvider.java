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
//import org.bonitasoft.studio.common.repository.AbstractRepositoryArtifact;
//import org.bonitasoft.studio.groovy.repository.GroovyScriptArtifact;
//import org.bonitasoft.studio.model.process.MainProcess;
//import org.bonitasoft.studio.repository.connectors.jar.JarArtifact;
//import org.bonitasoft.studio.repository.connectors.jar.ProvidedJarArtifact;
//import org.eclipse.jface.viewers.ICheckStateProvider;
//
///**
// * @author Mickael Istria
// *
// */
//public class ClasspathEntryCheckedStateProvider implements ICheckStateProvider {
//
//	protected MainProcess process;
//	private ClasspathEntriesContentProvider contentProvider;
//	
//	/**
//	 * @param contentProvider
//	 */
//	public ClasspathEntryCheckedStateProvider(ClasspathEntriesContentProvider contentProvider) {
//		this.contentProvider = contentProvider;
//	}
//
//	public void setProcess(MainProcess process) {
//		this.process = process;
//	}
//	
//	/* (non-Javadoc)
//	 * @see org.eclipse.jface.viewers.ICheckStateProvider#isChecked(java.lang.Object)
//	 */
//	public boolean isChecked(Object element) {
//		if (process == null) {
//			return false;
//		}
//		if (element instanceof AbstractRepositoryArtifact) {
//			AbstractRepositoryArtifact artifact = (AbstractRepositoryArtifact)element;
//			return (process.getIncludedEntries().contains(artifact.getId()));
//		}
//		
//		if (element instanceof GroovyScriptArtifact) {
//			GroovyScriptArtifact artifact = (GroovyScriptArtifact)element;
//			return (process.getIncludedEntries().contains(artifact.getId()));
//		}
//		
//		if (element instanceof UnresolvedDependency) {
//			return process.getIncludedEntries().contains(((UnresolvedDependency)element).getId());
//		}
//		
//		if (element instanceof String) {
//			boolean oneChecked = false;
//			Object[] children = contentProvider.getChildren(element);
//			int i = 0;
//			while (i < children.length && !oneChecked) {
//				if (isChecked(children[i])) {
//					oneChecked = true;
//				}
//				i++;
//			}
//			return oneChecked;
//		}
//		
//		return false;
//	}
//
//	/* (non-Javadoc)
//	 * @see org.eclipse.jface.viewers.ICheckStateProvider#isGrayed(java.lang.Object)
//	 */
//	public boolean isGrayed(Object element) {
//		if (process == null || !(element instanceof String)) {
//			return false;
//		}
//		
//		if (element instanceof AbstractRepositoryArtifact) {
//			AbstractRepositoryArtifact artifact = (AbstractRepositoryArtifact)element;
//			return (process.getIncludedEntries().contains(artifact.getId()));
//		}
//		
//		if (element instanceof String) {
//			boolean allChecked = true;
//			Object[] children = contentProvider.getChildren(element);
//			int i = 0;
//			while (i < children.length && allChecked) {
//				if (!isChecked(children[i]) || isGrayed(children[i])) {
//					allChecked = false;
//				}
//				i++;
//			}
//			return !allChecked && children.length > 0;
//		}
//		
//		String category = (String) element;
//		for (Object item : contentProvider.getChildren(category)) {
//			if (item instanceof AbstractRepositoryArtifact) {
//				AbstractRepositoryArtifact artifact = (AbstractRepositoryArtifact)item;
//				if (category.equals(ClasspathEntriesContentProvider.CONNECTOR_CATEGORY) 
//						&& !artifact.getId().endsWith(".jar") 
//						&& !process.getIncludedEntries().contains(artifact.getId())) {
//					return true;
//				} else if (category.equals(ClasspathEntriesContentProvider.VALIDATOR_CATEGORY) 
//						&& !artifact.getId().endsWith(".jar") 
//						&& !process.getIncludedEntries().contains(artifact.getId())){
//					return true;
//				}else if (category.equals(ClasspathEntriesContentProvider.MY_LIB_CATEGORY) 
//						/*&& artifact.getId().endsWith(".jar")*/
//						&& !process.getIncludedEntries().contains(artifact.getId())
//						&& artifact instanceof JarArtifact) {
//					return true;
//				} else if (category.equals(ClasspathEntriesContentProvider.PROVIDED_LIB_CATEGORY) 
//						/*&& artifact.getId().endsWith(".jar")*/
//						&& !process.getIncludedEntries().contains(artifact.getId())
//						&& artifact instanceof ProvidedJarArtifact) {
//					return true;
//				}
//			}
//		}
//		return false;
//	}
//
//}
