/**
 * Copyright (C) 2013 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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

import java.io.File;
import java.io.FileFilter;
import java.util.HashSet;
import java.util.Set;

import org.bonitasoft.studio.dependencies.i18n.Messages;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IClasspathContainer;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;

/**
 * @author Romain Bioteau
 *
 */
public class UserDependenciesContainer implements IClasspathContainer {

	private IJavaProject project;
	private IPath containerPath;

	public UserDependenciesContainer(IPath containerPath,IJavaProject project){
		this.project = project;
		this.containerPath = containerPath;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jdt.core.IClasspathContainer#getClasspathEntries()
	 */
	@Override
	public IClasspathEntry[] getClasspathEntries() {
		Set<IClasspathEntry> entries = new HashSet<IClasspathEntry>() ;
		if(project != null && project.exists()){
			File projectFolder = project.getProject().getLocation().toFile();
			File libFolder = new File(projectFolder,"lib");
			if(libFolder.exists()){
				File[] listFiles = libFolder.listFiles(new FileFilter() {

					@Override
					public boolean accept(File file) {
						return file.getName().endsWith(".jar");
					}
				});
				if(listFiles != null){
					for(File f :  listFiles){
						entries.add(JavaCore.newLibraryEntry(Path.fromOSString(f.getAbsolutePath()), null, null, true));
					}
				}
			}
		}
		return entries.toArray(new IClasspathEntry[entries.size()]);
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
		return containerPath;
	}


}
