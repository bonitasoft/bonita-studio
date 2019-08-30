/**
 * Copyright (C) 2018 Bonitasoft S.A.
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
package org.bonitasoft.studio.condition.ui.expression;

import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtext.ui.resource.XtextResourceSetProvider;

import com.google.inject.Injector;

public class ProjectXtextResourceProvider implements IXtextResourceProvider {

    private Injector injector;

    public ProjectXtextResourceProvider(Injector injector) {
        this.injector = injector;
    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.condition.ui.expression.IXtextResourceProvider#getResource(org.eclipse.emf.ecore.EObject)
     */
    @Override
    public Resource getResource(EObject context) {
        final XtextResourceSetProvider xtextResourceSetProvider = injector.getInstance(XtextResourceSetProvider.class);
        final IProject project = RepositoryManager.getInstance().getCurrentRepository().getProject();
        final ResourceSet resourceSet = xtextResourceSetProvider.get(project);
        return resourceSet.createResource(URI.createFileURI("somefile.cmodel"));
    }

}
