/**
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.groovy;

import jakarta.inject.Inject;

import java.util.List;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.core.maven.model.AppProjectConfiguration;
import org.bonitasoft.studio.common.repository.model.IJavaContainer;
import org.bonitasoft.studio.groovy.repository.GroovyFileStore;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.e4.core.di.annotations.Creatable;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.internal.ui.util.CoreUtility;
import org.eclipse.m2e.core.ui.internal.UpdateMavenProjectJob;

@Creatable
public class GroovyCompilationUnitFactory {

    private final RepositoryAccessor repositoryAccessor;

    @Inject
    public GroovyCompilationUnitFactory(final RepositoryAccessor repositoryAccessor) {
        this.repositoryAccessor = repositoryAccessor;
    }

    public ICompilationUnit newCompilationUnit(final String script, final IProgressMonitor monitor)
            throws JavaModelException {
        final IJavaProject javaProject = javaProject();
        if (javaProject != null) {
            IPath generatedGroovySourcesFolder = javaProject.getPath()
                    .append(AppProjectConfiguration.GENERATED_GROOVY_SOURCES_FODLER);
            IPackageFragmentRoot generatedGroovySourcesRoot = javaProject
                    .findPackageFragmentRoot(
                            generatedGroovySourcesFolder);
            if (generatedGroovySourcesRoot == null) {
            	IFolder folder = javaProject.getProject().getFolder(AppProjectConfiguration.GENERATED_GROOVY_SOURCES_FODLER);
    			if (!folder.exists()) {
    				try {
						CoreUtility.createFolder(folder, true, true, monitor);
					} catch (CoreException e) {
						BonitaStudioLog.error(e);
						return null;
					}
    			}
    			new UpdateMavenProjectJob(List.of(javaProject.getProject()), false, false,
    	                true, true, true).run(monitor);
    			generatedGroovySourcesRoot = JavaCore.create(javaProject.getProject())
                        .findPackageFragmentRoot(generatedGroovySourcesFolder);
            }
            if (generatedGroovySourcesRoot != null) {
                final IPackageFragment packageFragment = generatedGroovySourcesRoot.getPackageFragment(""); //default package
                return packageFragment.createCompilationUnit(GroovyFileStore.tmpScriptName(), script, true,
                        monitor);
            }
        }
        return null;
    }

    private IJavaProject javaProject() {
        return repositoryAccessor.getCurrentRepository().map(IJavaContainer::getJavaProject).orElse(null);
    }

}
