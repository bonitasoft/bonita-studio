/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.team.core;

import java.io.File;
import java.util.Objects;
import java.util.regex.Pattern;

import org.bonitasoft.studio.common.RestAPIExtensionNature;
import org.bonitasoft.studio.common.repository.BonitaProjectNature;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.core.DatabaseHandler;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.team.svn.core.extension.options.IIgnoreRecommendations;
import org.eclipse.team.svn.core.utility.FileUtility;

public class SVNIgnoreRecommendations implements IIgnoreRecommendations {

    @Override
    public boolean isAcceptableNature(IResource resource) throws CoreException {
        return FileUtility.hasNature(resource.getProject(), BonitaProjectNature.NATURE_ID)
                || FileUtility.hasNature(resource.getProject(), RestAPIExtensionNature.NATURE_ID);
    }

    @Override
    public boolean isIgnoreRecommended(IResource resource) throws CoreException {
        return isNotASharedRepository(resource)
                || isFolder(resource, "target")
                || isFolder(resource, "bin")
                || isFolder(resource, "node")
                || isFolder(resource, "node_modules")
                || isFolder(resource, ".settings")
                || isFolder(resource, "META-INF")
                || isFolder(resource, DatabaseHandler.H2_DATABASE_FOLDER_NAME)
                || isFile(resource, ".classpath")
                || isFile(resource, "build.properties")
                || isMatching(resource, "web_widgets/pb.*")
                || isMatching(resource, "web_widgets/.metadata")
                || isMatching(resource, "web_fragments/.metadata")
                || isMatching(resource, "web_page/.metadata");
    }

    private boolean isNotASharedRepository(IResource resource) {
        final AbstractRepository currentRepository = currentRepository();
        if (currentRepository.isLoaded()) {
            final IRepositoryStore<?> repositoryStore = currentRepository().getRepositoryStore(resource);
            if (repositoryStore != null) {
                return !repositoryStore.canBeShared();
            }
        }
        return false;
    }

    private AbstractRepository currentRepository() {
        return RepositoryManager.getInstance().getCurrentRepository();
    }

    private boolean isFile(IResource resource, String fileName) {
        final IProject project = resource.getProject();
        if (project == null) {
            return false;
        }
        return resource instanceof IFile && Objects.equals(fileName, resource.getName());
    }

    private boolean isMatching(IResource resource, String regex) {
        final IProject project = resource.getProject();
        if (project == null) {
            return false;
        }
        return Pattern.matches(regex, resource.getProjectRelativePath().toString());
    }

    private boolean isFolder(IResource resource, String folder) {
        final IProject project = resource.getProject();
        if (project == null) {
            return false;
        }
        return resource instanceof IFolder && Objects.equals(folder, resource.getName())
                || resource.getLocation().toFile().getAbsolutePath().contains(File.separator + folder + File.separator);
    }

    @Override
    public boolean isOutput(IResource resource) throws CoreException {
        return false;
    }

}
