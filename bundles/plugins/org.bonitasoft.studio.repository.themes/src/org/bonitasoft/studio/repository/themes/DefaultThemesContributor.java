///**
// * Copyright (C) 2011 BonitaSoft S.A.
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
//package org.bonitasoft.studio.repository.themes;
//
//import java.io.InputStream;
//import java.net.URL;
//import java.util.Enumeration;
//
//import org.bonitasoft.studio.common.log.BonitaStudioLog;
//import org.bonitasoft.studio.common.repository.RepositoryInitializationContributor;
//import org.eclipse.core.runtime.IProgressMonitor;
//
//
///**
// * @author Romain Bioteau
// *
// */
//public class DefaultThemesContributor implements RepositoryInitializationContributor {
//
//
//	/* (non-Javadoc)
//	 * @see org.bonitasoft.studio.common.repository.RepositoryInitializationContributor#mustProcess()
//	 */
//	public boolean mustProcess() {
//		return ThemeRepository.getInstance().getProject().findMember("default") == null;
//	}
//
//	/* (non-Javadoc)
//	 * @see org.bonitasoft.studio.common.repository.RepositoryInitializationContributor#contributeToRepo(org.eclipse.core.runtime.IProgressMonitor)
//	 */
//	public void contributeToRepo(IProgressMonitor monitor) {
//		Enumeration<?> resources = ThemeRepositoryPlugin.getDefault().getBundle().findEntries("themes/", "*.zip", false);
//		while (resources.hasMoreElements()) {
//			try {
//				URL url = (URL)resources.nextElement();
//				InputStream stream = url.openStream();
//				String fileName = url.getPath().substring(url.getPath().lastIndexOf("/"));
//				if (fileName.startsWith("/")) {
//					fileName = fileName.substring(1);
//				}
//
//				ThemeRepository.getInstance().importArtifact(fileName,stream,monitor);
//				stream.close();
//
//			} catch (Exception ex) {
//				BonitaStudioLog.log(ex);
//			}
//		}
//	}
//
//}
