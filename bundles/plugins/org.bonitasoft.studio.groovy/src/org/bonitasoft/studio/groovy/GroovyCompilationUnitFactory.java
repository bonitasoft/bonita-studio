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

import javax.inject.Inject;

import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.groovy.repository.GroovyFileStore;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.e4.core.di.annotations.Creatable;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.JavaModelException;

@Creatable
public class GroovyCompilationUnitFactory {

    private final RepositoryAccessor repositoryAccessor;

    @Inject
    public GroovyCompilationUnitFactory(final RepositoryAccessor repositoryAccessor) {
        this.repositoryAccessor = repositoryAccessor;
    }

    public ICompilationUnit newCompilationUnit(final String script, final IProgressMonitor monitor) throws JavaModelException {
        final IJavaProject javaProject = javaProject();
        if (javaProject != null) {
            final IPackageFragment packageFragment = javaProject
                    .findPackageFragmentRoot(javaProject.getPath().append("src-providedGroovy"))
                    .getPackageFragment("");//default package
            return packageFragment.createCompilationUnit(GroovyFileStore.EXPRESSION_SCRIPT_NAME, script, true, monitor);
        }
        return null;
    }

    private IJavaProject javaProject() {
        return repositoryAccessor.getCurrentRepository() != null ? repositoryAccessor.getCurrentRepository().getJavaProject() : null;
    }

}
