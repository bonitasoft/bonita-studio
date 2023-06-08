/*******************************************************************************
 * Copyright (C) 2018 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.team.git.core;

import java.util.Objects;

import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.core.ImportBonitaProjectOperation;
import org.bonitasoft.studio.common.repository.core.migration.BonitaProjectMigrator;
import org.bonitasoft.studio.team.git.i18n.Messages;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.egit.core.op.CloneOperation.PostCloneTask;
import org.eclipse.egit.ui.internal.dialogs.BasicConfigurationDialog;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.jgit.lib.Repository;

public class ImportClonedRepository implements PostCloneTask {

    @Override
    public void execute(Repository repository, IProgressMonitor monitor) throws CoreException {
        monitor.subTask("");
        var subMonitor = SubMonitor.convert(monitor)
                .split(IProgressMonitor.UNKNOWN, SubMonitor.SUPPRESS_SUBTASK);
        subMonitor.beginTask(Messages.importingProject, IProgressMonitor.UNKNOWN);

        RepositoryManager.getInstance().installRequiredMavenDependencies(new NullProgressMonitor());

        var projectRoot = repository.getDirectory().getParentFile();
        var clonedVersion = BonitaProjectMigrator
                .readBonitaVersion(projectRoot.toPath().resolve(IProjectDescription.DESCRIPTION_FILE_NAME));
        var importBonitaProjectOperation = new ImportBonitaProjectOperation(projectRoot);
        importBonitaProjectOperation.run(new NullProgressMonitor());
        var report = importBonitaProjectOperation.getReport();
        var bonitaProject = importBonitaProjectOperation.getBonitaProject();
        var currentRepo = RepositoryManager.getInstance().switchToRepository(
                bonitaProject.getId(),
                new NullProgressMonitor());
        try {
            subMonitor = SubMonitor.convert(monitor)
                    .split(IProgressMonitor.UNKNOWN, SubMonitor.SUPPRESS_SUBTASK);
            currentRepo.migrate(report, subMonitor);
        } catch (MigrationException e) {
            throw new CoreException(Status.error("An error occured while migrating cloned project.", e));
        }
        BasicConfigurationDialog.show(bonitaProject.getAdapter(Repository.class));
        if (!Objects.equals(clonedVersion, ProductVersion.CURRENT_VERSION)) {
            bonitaProject.commitAll(
                    String.format("Bonita '%s' to '%s' automated migration", clonedVersion,
                            ProductVersion.CURRENT_VERSION),
                    monitor);
        }
        var bdmProject = bonitaProject.getBdmModelProject();
        if (bdmProject.exists()) {
            bdmProject.refreshLocal(IResource.DEPTH_INFINITE, new NullProgressMonitor());
            bdmProject.build(IncrementalProjectBuilder.INCREMENTAL_BUILD, new NullProgressMonitor());
        }
    }

}
