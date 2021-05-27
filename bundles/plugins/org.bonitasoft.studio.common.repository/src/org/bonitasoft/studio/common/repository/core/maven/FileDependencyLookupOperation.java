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
import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.Messages;
import org.bonitasoft.studio.common.repository.core.InputStreamSupplier;
import org.bonitasoft.studio.common.repository.core.maven.migration.model.DependencyLookup;
import org.bonitasoft.studio.common.repository.core.maven.migration.model.DependencyLookup.Status;
import org.bonitasoft.studio.common.repository.core.maven.migration.model.GAV;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.m2e.core.MavenPlugin;
import org.eclipse.m2e.core.embedder.IMaven;

public class FileDependencyLookupOperation implements IRunnableWithProgress {

    private DependencyLookup result;
    private List<String> repositories = new ArrayList<>();
    private InputStreamSupplier fileToLookup;

    public FileDependencyLookupOperation(InputStreamSupplier fileToLookup) {
        this.fileToLookup = fileToLookup;
    }

    public FileDependencyLookupOperation addRemoteRespository(String repositoryUrl) {
        this.repositories.add(repositoryUrl);
        return this;
    }
    
    public List<String> getRemoteRepositories() {
        return repositories;
    }

    @Override
    public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        JarLookupOperation jarLookupOperation = new JarLookupOperation(fileToLookup);
        repositories.stream()
                .forEach(jarLookupOperation::addRemoteRespository);
        monitor.setTaskName(String.format(Messages.lookupDependencyFor, fileToLookup.getName()));
        jarLookupOperation.run(new NullProgressMonitor());
        var status = jarLookupOperation.getStatus();
        if (status.isOK()) {
            result = jarLookupOperation.getResult();
            if (result.getStatus() == Status.FOUND
                    && DependencyLookup.guessClassifier(fileToLookup.getName(), result.getGAV()) != null) {
                GAV gavWithClassifier = result.getGAV();
                gavWithClassifier.setClassifier(DependencyLookup.guessClassifier(fileToLookup.getName(), result.getGAV()));
                var dependencyGetOperation = new DependencyGetOperation(gavWithClassifier);
                repositories.stream()
                        .forEach(dependencyGetOperation::addRemoteRespository);
                monitor.setTaskName(String.format(Messages.lookupDependencyFor, result.getGAV()));
                dependencyGetOperation.run(new NullProgressMonitor());
                status = dependencyGetOperation.getStatus();
                if (status.isOK() && dependencyGetOperation.getResult() != null) {
                    result = dependencyGetOperation.getResult();
                }
            } else if (result.getStatus() == Status.NOT_FOUND) {
                GAV gav = DependencyLookup.readPomProperties(fileToLookup.toTempFile())
                        .map(properties -> new GAV(properties.getProperty("groupId"),
                                properties.getProperty("artifactId"),
                                properties.getProperty("version")))
                        .orElse(null);
                if (gav != null) {
                    var dependencyGetOperation = new DependencyGetOperation(gav);
                    repositories.stream()
                            .forEach(dependencyGetOperation::addRemoteRespository);
                    monitor.setTaskName(String.format(Messages.lookupDependencyFor, gav));
                    dependencyGetOperation.run(new NullProgressMonitor());
                    status = dependencyGetOperation.getStatus();
                    if (status.isOK()) {
                        if (dependencyGetOperation.getResult() != null) {
                            result = dependencyGetOperation.getResult();
                        } else if (DependencyLookup.guessClassifier(fileToLookup.getName(), gav) != null) {
                            gav.setClassifier(DependencyLookup.guessClassifier(fileToLookup.getName(), gav));
                            dependencyGetOperation = new DependencyGetOperation(gav);
                            repositories.stream()
                                    .forEach(dependencyGetOperation::addRemoteRespository);
                            dependencyGetOperation.run(monitor);
                            status = dependencyGetOperation.getStatus();
                            if (status.isOK() && dependencyGetOperation.getResult() != null) {
                                result = dependencyGetOperation.getResult();
                            }
                        }
                    }
                }
                if(result.getStatus() == Status.NOT_FOUND && result.getFile().exists()) {
                    result.setStatus(Status.LOCAL);
                }
            }
        }
    }


    public DependencyLookup getResult() {
        return result;
    }

    IMaven maven() {
        return MavenPlugin.getMaven();
    }

}
