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
package org.bonitasoft.studio.dependencies.classpath;

import java.util.HashSet;
import java.util.Set;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.dependencies.i18n.Messages;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.ClasspathContainerInitializer;
import org.eclipse.jdt.core.IClasspathContainer;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;

/**
 * @author Romain Bioteau
 *
 */
public class UserDependenciesClasspathContainer extends ClasspathContainerInitializer implements IClasspathContainer {

	private static final IPath CONTAINER_ID = new Path("repositoryDependencies");
	private IJavaProject project;

	public UserDependenciesClasspathContainer(){
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jdt.core.IClasspathContainer#getClasspathEntries()
	 */
	@Override
	public IClasspathEntry[] getClasspathEntries() {
		Set<IClasspathEntry> entries = new HashSet<IClasspathEntry>() ;
		if(project != null && project.exists() && project.isOpen()){
			try {
				final IFolder libFolder = project.getProject().getFolder("lib");
				if(libFolder.exists()){
					for(IResource f : libFolder.members()){
						if(f instanceof IFile && ((IFile)f).getFileExtension() != null && ((IFile)f).getFileExtension().equalsIgnoreCase("jar")){
							entries.add(JavaCore.newLibraryEntry(f.getLocation(), null, null, true));
						}
					}
				}
			} catch (CoreException e) {
				BonitaStudioLog.error(e);
			}
		}
		return entries.toArray(new IClasspathEntry[]{});
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jdt.core.IClasspathContainer#getDescription()
	 */
	@Override
	public String getDescription() {
		return Messages.userClassPathDescription;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jdt.core.IClasspathContainer#getKind()
	 */
	@Override
	public int getKind() {
		return K_APPLICATION;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jdt.core.IClasspathContainer#getPath()
	 */
	@Override
	public IPath getPath() {
		return CONTAINER_ID;
	}

	@Override
	public void initialize(IPath containerPath, IJavaProject project) throws CoreException {
		if(containerPath.equals(CONTAINER_ID)){
			this.project = project ;
			JavaCore.setClasspathContainer(containerPath, new IJavaProject[] {project}, new IClasspathContainer[] {this}, null);
		}
	}

}
