/**
 * Copyright (C) 2016 Bonitasoft S.A.
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
package org.bonitasoft.studio.team.ui.handler;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.team.core.SVNIgnoreRecommendations;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;

public class SVNSelectionProvider {

    private AbstractRepository repository;

    public SVNSelectionProvider(AbstractRepository repository) {
        this.repository = repository;
    }

    public IStructuredSelection getSelection() {
        final SVNIgnoreRecommendations svnIgnoreRecommendations = new SVNIgnoreRecommendations();
        final IProject parentProject = repository.getProject();
        List<IProject> selectedProjects = selectProjects(parentProject, svnIgnoreRecommendations);
        try {
            List<IResource> selectedResources = Stream.of(parentProject.members())
                    .filter(r -> {
                        try {
                            return !svnIgnoreRecommendations.isIgnoreRecommended(r);
                        } catch (CoreException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .filter(r -> !r.getName().equals("restAPIExtensions"))
                    .collect(Collectors.toList());
            selectedResources.addAll(selectedProjects);
            return new StructuredSelection(selectedResources);
        } catch (CoreException e) {
            return new StructuredSelection();
        }
    }

    private List<IProject> selectProjects(IProject mainProject, SVNIgnoreRecommendations svnIgnoreRecommendations) {
        return Stream.of(mainProject.getWorkspace().getRoot().getProjects())
                .filter(project -> mainProject.getLocation().isPrefixOf(project.getLocation()))
                .filter(project -> !Objects.equals(project, mainProject))
                .filter(project -> {
                    try {
                        return !svnIgnoreRecommendations.isIgnoreRecommended(project);
                    } catch (CoreException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList());
    }

}
