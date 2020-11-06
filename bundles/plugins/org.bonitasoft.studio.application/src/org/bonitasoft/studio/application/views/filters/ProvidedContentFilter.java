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
package org.bonitasoft.studio.application.views.filters;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.BonitaProjectNature;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.designer.core.repository.WebWidgetFileStore;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.internal.ui.packageview.ClassPathContainer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

public class ProvidedContentFilter extends ViewerFilter {

    private static final Set<IPath> HIDDEN_RESOURCES = new HashSet<>();
    static {
        HIDDEN_RESOURCES.add(Path.fromPortableString("database_connectors_properties"));
        HIDDEN_RESOURCES.add(Path.fromPortableString("META-INF"));
        HIDDEN_RESOURCES.add(Path.fromPortableString("h2_database"));
        HIDDEN_RESOURCES.add(Path.fromPortableString("process_configurations"));
        HIDDEN_RESOURCES.add(Path.fromPortableString("template"));
        HIDDEN_RESOURCES.add(Path.fromPortableString("src-providedGroovy"));
        HIDDEN_RESOURCES.add(Path.fromOSString("org.eclipse.pde.core.requiredPlugins"));
        HIDDEN_RESOURCES.add(Path.fromOSString("GROOVY_DSL_SUPPORT"));
        HIDDEN_RESOURCES.add(Path.fromOSString("build.properties"));
        HIDDEN_RESOURCES.add(Path.fromOSString("web_widgets/.metadata"));
        HIDDEN_RESOURCES.add(Path.fromOSString("web_pages/.metadata"));
        HIDDEN_RESOURCES.add(Path.fromOSString("web_fragments/.metadata"));
    }

    @Override
    public boolean select(Viewer viewer, Object parentElement, Object element) {
        if (element instanceof IPackageFragmentRoot) {
            IResource resource = ((IPackageFragmentRoot) element).getAdapter(IResource.class);
            if (resource instanceof IResource) {
                return !HIDDEN_RESOURCES
                        .contains(resource.getProjectRelativePath());
            }
        }
        if (element instanceof ClassPathContainer) {
            return !HIDDEN_RESOURCES
                    .contains(((ClassPathContainer) element).getClasspathEntry().getPath());
        }

        if (element instanceof IProject
                && ((IProject) element).getName().equals("server_configuration")) {
            return false;
        }

        //Hide closed projects
        if (element instanceof IJavaElement) {
            return ((IJavaElement) element).getJavaProject().getProject().isOpen();
        }

        if (element instanceof IResource && RepositoryManager.getInstance().hasActiveRepository()) {
            try {
                IRepositoryFileStore fStore = RepositoryManager.getInstance().getCurrentRepository()
                        .asRepositoryFileStore(((IResource) element).getLocation().toFile().toPath(), false);
                if (fStore instanceof WebWidgetFileStore && fStore.getName().startsWith("pb")) { //Hide provided widgets
                    return false;
                }
            } catch (IOException | CoreException e) {
                BonitaStudioLog.error(e);
            }
            IProject project = ((IResource) element).getProject();
            try {
                return (project.isOpen() && !project.hasNature(BonitaProjectNature.NATURE_ID)) || (project.isOpen()
                        && !HIDDEN_RESOURCES.contains(((IResource) element).getProjectRelativePath()));
            } catch (CoreException e) {
                BonitaStudioLog.error(e);
            }
        }
        return true;
    }

}
