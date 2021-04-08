/**
 * Copyright (C) 2021 BonitaSoft S.A.
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
package org.bonitasoft.studio.common.repository.core.maven;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.model.Model;
import org.bonitasoft.studio.common.repository.Messages;
import org.bonitasoft.studio.common.repository.core.LocalDependencyInputStreamSupplier;
import org.bonitasoft.studio.common.repository.core.maven.migration.model.DependencyLookup;
import org.bonitasoft.studio.common.repository.core.maven.migration.model.DependencyLookup.Status;
import org.bonitasoft.studio.common.repository.core.maven.migration.model.GAV;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;

public class ProjectDependenciesLookupOperation implements IRunnableWithProgress {

    private Set<String> repositories = new HashSet<>();
    private Model mavenProject;
    private Set<DependencyLookup> result = new HashSet<>();
    private LocalDependencyInputStreamSupplier localDependencyInputStreamSupplier;

    public ProjectDependenciesLookupOperation(Model mavenProject,
            LocalDependencyInputStreamSupplier localDependencyInputStreamSupplier) {
        this.mavenProject = mavenProject;
        this.localDependencyInputStreamSupplier = localDependencyInputStreamSupplier;
    }

    public ProjectDependenciesLookupOperation addRemoteRespository(String repositoryUrl) {
        this.repositories.add(repositoryUrl);
        return this;
    }

    @Override
    public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        List<DependencyGetOperation> operations = mavenProject.getDependencies()
                .stream()
                .filter(dep -> !Objects.equals(dep.getScope(), Artifact.SCOPE_PROVIDED))
                .map(dep -> {
                    var op = new DependencyGetOperation(new GAV(dep));
                    repositories.stream()
                            .forEach(op::addRemoteRespository);
                    return op;
                })
                .collect(Collectors.toList());

        monitor.beginTask("", operations.size());
        for (DependencyGetOperation op : operations) {
            monitor.setTaskName(String.format(Messages.lookupDependencyFor, op.getGav().toString()));
            op.run(monitor);
            DependencyLookup lookup = op.getResult();
            if (lookup == null) {
                lookup = localDependencyInputStreamSupplier.find(op.getGav())
                        .map(inputStreamSupplier -> new DependencyLookup(inputStreamSupplier,
                                Status.LOCAL,
                                op.getGav()))
                        .orElse(new DependencyLookup(null,
                                null,
                                Status.NOT_FOUND,
                                op.getGav(),
                                null));
            }
            result.add(lookup);
            monitor.worked(1);
        }

    }
    
    public Set<DependencyLookup> getResult() {
        return result;
    }

}
