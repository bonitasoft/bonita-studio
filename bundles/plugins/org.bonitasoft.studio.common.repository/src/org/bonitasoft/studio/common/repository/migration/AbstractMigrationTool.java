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
//package org.bonitasoft.studio.common.repository.migration;
//
//import org.bonitasoft.studio.common.repository.AbstractRepositoryArtifact;
//import org.eclipse.core.runtime.IProgressMonitor;
//import org.eclipse.core.runtime.IStatus;
//
///**
// * @author Romain Bioteau
// *
// */
//public abstract class AbstractMigrationTool<T extends AbstractRepositoryArtifact> implements IMigrationTool<T> {
//
//	/* (non-Javadoc)
//	 * @see org.bonitasoft.studio.common.repository.migration.IMigrationTool#moveToCurrentVersion(org.eclipse.core.resources.IFile, org.eclipse.core.runtime.IProgressMonitor)
//	 */
//	public abstract IStatus moveToCurrentVersion(T artifact, IProgressMonitor monitor) ;
//	
//	
//
//	/**
//	 * @param bonitaVersion
//	 * @return
//	 */
//	protected static boolean versionExists(String bonitaVersion) {
//		for (String existingVersion : SORTED_VERSIONS) {
//			if (bonitaVersion.equals(existingVersion)) {
//				return true;
//			}
//		}
//		return false;
//	}
//
//	/**
//	 * @param versionRank
//	 * @return
//	 */
//	protected static boolean isLatestVersion(String bonitaVersion) {
//		return bonitaVersion.equals(SORTED_VERSIONS[SORTED_VERSIONS.length - 1]);
//	}
//
//	/**
//	 * @param version
//	 * @return the index of version in SORTED_VERSIONS, or -1 if not found
//	 */
//	protected int getVersionRank(String version) {
//		int i = 0;
//		for (i = 0; i < SORTED_VERSIONS.length; i++) {
//			if (SORTED_VERSIONS[i].equals(version)) {
//				return i;
//			}
//		}
//		return -1;
//	}
//	
//
//	public Object getResult() {
//		return null;
//	}
//
//}
