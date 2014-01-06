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
//import org.bonitasoft.studio.repository.connectors.jar.AbstractJARArtifact;
//import org.eclipse.jface.viewers.IBaseLabelProvider;
//import org.eclipse.jface.viewers.LabelProvider;
//
///**
// * @author Mickael Istria
// *
// */
//public class ClasspathEntriesLabelProvider extends LabelProvider implements IBaseLabelProvider {
//
//	@Override
//	public String getText(Object item) {
//		if (item instanceof String) {
//			String[] segments = ((String)item).split("/");
//			return segments[segments.length - 1];
//		} else if (item instanceof AbstractJARArtifact) {
//			AbstractJARArtifact jar = (AbstractJARArtifact)item;
//			if (jar.getName().contains("/")) {
//				String[] segments = jar.getName().split("/");
//				return segments[segments.length - 1];
//			} else {
//				return jar.getName();
//			}
//		} else if (item instanceof GroovyScriptArtifact) {
//			return ((GroovyScriptArtifact)item).getName() ;
//		} else if(item instanceof AbstractRepositoryArtifact){
//			return ((AbstractRepositoryArtifact) item).getDisplayNameForLabelProvider();
//		} else {
//			return super.getText(item);
//		}
//	}
//}
