/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.dependencies.repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.maven.artifact.Artifact;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.core.maven.ProjectDependenciesResolver;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.dependencies.DependenciesPlugin;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.swt.graphics.Image;

public class MavenDependencyFileStore extends DependencyFileStore {

    private Artifact artifact;

    public MavenDependencyFileStore(Artifact artifact, final DependencyRepositoryStore parentStore) {
        super(artifact.getFile().getName(), parentStore);
        this.artifact = artifact;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.model.IRepositoryFileStore#getIcon()
     */
    @Override
    public Image getIcon() {
        return Pics.getImage("jar.gif", DependenciesPlugin.getDefault());
    }

    @Override
    protected InputStream doGetContent() throws ReadFileStoreException {
        try {
            return new FileInputStream(artifact.getFile());
        } catch (FileNotFoundException e) {
            throw new ReadFileStoreException("Failed to read file.", e);
        }
    }

    @Override
    public boolean canBeDeleted() {
        return false;
    }

    @Override
    public boolean canBeExported() {
        return false;
    }

    @Override
    protected void doSave(final Object content) {
    }


    @Override
    protected void doClose() {

    }

    @Override
    protected void doDelete() {

    }

    @Override
    public IFile getResource() {
        return null;
    }
    
    @Override
    public File getFile() {
        return artifact.getFile();
    }
    
    @Override
    public List<File> getTransitiveDependencies() {
        try {
            return new ProjectDependenciesResolver()
                .getTransitiveDependencies(getRepository().getProject(), artifact, AbstractRepository.NULL_PROGRESS_MONITOR)
                .stream()
                .map(Artifact::getFile)
                .filter(Objects::nonNull)
                .filter(File::exists)
                .collect(Collectors.toList());
        } catch (CoreException e) {
            return super.getTransitiveDependencies();
        }
    }

}
