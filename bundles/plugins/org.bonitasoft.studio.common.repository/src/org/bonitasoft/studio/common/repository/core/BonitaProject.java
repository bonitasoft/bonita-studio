/**
 * Copyright (C) 2022 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.common.repository.core;

import java.io.File;
import java.util.Collection;
import java.util.List;

import org.bonitasoft.studio.common.repository.BonitaProjectNature;
import org.bonitasoft.studio.common.repository.core.maven.BonitaProjectBuilder;
import org.bonitasoft.studio.common.repository.core.maven.model.ProjectMetadata;
import org.bonitasoft.studio.common.repository.core.team.GitProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.m2e.core.internal.IMavenConstants;

public interface BonitaProject extends GitProject, IAdaptable {

    Collection<String> NATRUES = List.of(IMavenConstants.NATURE_ID, BonitaProjectNature.NATURE_ID, JavaCore.NATURE_ID, "org.eclipse.jdt.groovy.core.groovyNature");
    Collection<String> BUILDERS = List.of(IMavenConstants.BUILDER_ID, BonitaProjectBuilder.ID, JavaCore.BUILDER_ID);

    String getId();
    
    String getDisplayName();
    
    ProjectMetadata getProjectMetadata(IProgressMonitor monitor) throws CoreException;

    void update(ProjectMetadata metadata, IProgressMonitor monitor) throws CoreException;
    
    void open(IProgressMonitor monitor) throws CoreException;
    
    void close(IProgressMonitor monitor) throws CoreException;
    
    void delete(IProgressMonitor monitor) throws CoreException;

    Collection<? extends IResource> getExportableResources();

    void refresh(IProgressMonitor monitor) throws CoreException;



}
