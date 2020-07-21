/*******************************************************************************
 * Copyright (C) 2018 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.team.git.core;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.util.stream.Stream;

import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.common.repository.BonitaProjectNature;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.team.TeamPlugin;
import org.bonitasoft.studio.team.git.i18n.Messages;
import org.bonitasoft.studio.validation.ModelFileCompatibilityValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.egit.core.op.CloneOperation.PostCloneTask;
import org.eclipse.jgit.lib.Repository;

public class ValidateClonedRepository implements PostCloneTask {

    private String version;

    @Override
    public void execute(Repository repository, IProgressMonitor monitor) throws CoreException {
        //Do some check about version and valid cloned content
        File projectFile = new File(repository.getDirectory().getParentFile(), ".project");
        if (!projectFile.exists()) {
            throw new CoreException(new Status(IStatus.ERROR, TeamPlugin.PLUGIN_ID,
                    String.format(Messages.noBonitaProjectDescriptorFound, projectFile.getParentFile())));
        }
        try (InputStream newInputStream = Files.newInputStream(projectFile.toPath())) {
            final IProjectDescription projectDesc = ResourcesPlugin.getWorkspace()
                    .loadProjectDescription(newInputStream);
            version = projectDesc.getComment();
            if (version == null || version.isEmpty()) {
                throw new CoreException(new Status(IStatus.ERROR, TeamPlugin.PLUGIN_ID,
                        String.format(Messages.noVersionFoundInBonitaProjectDescriptor, projectFile)));
            }
            if (Stream.of(projectDesc.getNatureIds()).noneMatch(BonitaProjectNature.NATURE_ID::equals)) {
                throw new CoreException(new Status(IStatus.ERROR, TeamPlugin.PLUGIN_ID,
                        String.format(Messages.noNatureFoundInBonitaProjectDescriptor, projectFile.getParent())));
            }
        } catch (IOException e) {
            throw new CoreException(new Status(IStatus.ERROR, TeamPlugin.PLUGIN_ID,
                    String.format("Failed to read .project file '%s'", projectFile), e));
        }
        if (!ProductVersion.sameMinorVersion(version)) {
            if (!ProductVersion.canBeMigrated(version)) {
               throw new CoreException(new Status(IStatus.ERROR, TeamPlugin.PLUGIN_ID,
                        String.format(Messages.cannotMigrateRepository,
                                version, ProductVersion.CURRENT_VERSION, ProductVersion.CURRENT_VERSION)));
            }
        }
        if (ProductVersion.sameMinorVersion(version)) {
            try {
                ModelFileCompatibilityValidator validateModelCompatibility = new ModelFileCompatibilityValidator(repository.getDirectory().getParentFile(), RepositoryManager.getInstance().getCurrentRepository());
                validateModelCompatibility.run(monitor);
                if(validateModelCompatibility.getStatus().getSeverity() == IStatus.ERROR) {
                    throw new CoreException(ValidationStatus.error(String.format(Messages.clonedBranchContentIsNotCompatible, repository.getBranch())));
                }
            } catch (InvocationTargetException | InterruptedException | IOException e) {
                throw new CoreException(new Status(IStatus.ERROR, TeamPlugin.PLUGIN_ID, "Failed to check models version", e));
            }
        }
    }
 

    public String getVersion() {
        return version;
    }

}
