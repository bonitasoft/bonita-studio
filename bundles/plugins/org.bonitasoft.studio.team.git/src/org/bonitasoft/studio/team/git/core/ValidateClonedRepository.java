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
import org.bonitasoft.studio.common.repository.FakeRepository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.core.BonitaProject;
import org.bonitasoft.studio.team.git.TeamGitPlugin;
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
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.swt.widgets.Display;

public class ValidateClonedRepository implements PostCloneTask {

    private String version;
    private boolean migrationRequired = false;
    
    @Override
    public void execute(Repository repository, IProgressMonitor monitor) throws CoreException {
        //Do some check about version and valid cloned content
        File projectFile = new File(repository.getDirectory().getParentFile(), IProjectDescription.DESCRIPTION_FILE_NAME);
        if (!projectFile.exists()) {
            throw new CoreException(new Status(IStatus.ERROR, TeamGitPlugin.PLUGIN_ID,
                    String.format(Messages.noBonitaProjectDescriptorFound, projectFile.getParentFile())));
        }
        try (InputStream newInputStream = Files.newInputStream(projectFile.toPath())) {
            var projectDesc = ResourcesPlugin.getWorkspace()
                    .loadProjectDescription(newInputStream);
            version = projectDesc.getComment();
            if (version == null || version.isEmpty()) {
                throw new CoreException(new Status(IStatus.ERROR, TeamGitPlugin.PLUGIN_ID,
                        String.format(Messages.noVersionFoundInBonitaProjectDescriptor, projectFile)));
            }
            // Legacy project structure
            if (Stream.of(projectDesc.getNatureIds()).noneMatch(BonitaProjectNature.NATURE_ID::equals)) {
                // Current project structure
                var appProjectFile = repository.getDirectory().getParentFile().toPath()
                        .resolve(BonitaProject.APP_MODULE)
                        .resolve(IProjectDescription.DESCRIPTION_FILE_NAME);
                if(Files.exists(appProjectFile)) {
                    try(var is = Files.newInputStream(appProjectFile)){
                        projectDesc = ResourcesPlugin.getWorkspace()
                                .loadProjectDescription(is);
                        if(Stream.of(projectDesc.getNatureIds()).noneMatch(BonitaProjectNature.NATURE_ID::equals)) {
                            throw new CoreException(new Status(IStatus.ERROR, TeamGitPlugin.PLUGIN_ID,
                                    String.format(Messages.noNatureFoundInBonitaProjectDescriptor, projectFile.getParent())));
                        }
                    }
                }else {
                    throw new CoreException(new Status(IStatus.ERROR, TeamGitPlugin.PLUGIN_ID,
                            String.format(Messages.noNatureFoundInBonitaProjectDescriptor, projectFile.getParent())));
                }
               
            }
        } catch (IOException e) {
            throw new CoreException(new Status(IStatus.ERROR, TeamGitPlugin.PLUGIN_ID,
                    String.format("Failed to read .project file '%s'", projectFile), e));
        }
        if (!ProductVersion.sameMinorVersion(version)) {
            if (!ProductVersion.canBeMigrated(version)) {
                throw new CoreException(new Status(IStatus.ERROR, TeamGitPlugin.PLUGIN_ID,
                        String.format(Messages.cannotMigrateRepository,
                                version, ProductVersion.CURRENT_VERSION, ProductVersion.CURRENT_VERSION)));
            }
            confirmMigration();
        }
        if (ProductVersion.sameMinorVersion(version)) {
            try {
                var currentRepository = RepositoryManager.getInstance()
                        .getCurrentRepository()
                        .orElseGet(() -> new FakeRepository());
                ModelFileCompatibilityValidator validateModelCompatibility = new ModelFileCompatibilityValidator(
                        repository.getDirectory().getParentFile(),currentRepository);
                validateModelCompatibility.run(monitor);
                if (validateModelCompatibility.getStatus().getSeverity() == IStatus.ERROR) {
                    throw new CoreException(ValidationStatus
                            .error(String.format(Messages.clonedBranchContentIsNotCompatible, repository.getBranch())));
                } else if (validateModelCompatibility.getStatus().getSeverity() == IStatus.WARNING) {
                    confirmMigration();
                }
            } catch (InvocationTargetException | InterruptedException | IOException e) {
                throw new CoreException(
                        new Status(IStatus.ERROR, TeamGitPlugin.PLUGIN_ID, "Failed to check models version", e));
            }
        }
    }

    private void confirmMigration() throws CoreException {
        ConfirmMigrationRunnable confirmMigrationRunnable = new ConfirmMigrationRunnable();
        Display.getDefault().syncExec(confirmMigrationRunnable);
        if (confirmMigrationRunnable.isCancelled()) {
            throw new CoreException(
                    new Status(IStatus.CANCEL, TeamGitPlugin.PLUGIN_ID, Messages.cloneOperationCancelled));
        }
        migrationRequired = true;
    }

    public String getVersion() {
        return version;
    }

    public boolean migrationRequired() {
        return migrationRequired;
    }

}

class ConfirmMigrationRunnable implements Runnable {

    boolean cancelled = false;

    @Override
    public void run() {
        if (!MessageDialog.openQuestion(Display.getDefault().getActiveShell(), Messages.confirmMigratonTitle,
                Messages.confirmMigraton)) {
            cancelled = true;
        }
    }

    public boolean isCancelled() {
        return cancelled;
    }
}
