/*******************************************************************************
 * Copyright (C) 2018 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.team.git.core;

import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.core.BonitaProject;
import org.bonitasoft.studio.common.repository.core.ImportBonitaProjectOperation;
import org.bonitasoft.studio.common.repository.core.MultiModuleProject;
import org.bonitasoft.studio.common.repository.core.migration.report.MigrationReport;
import org.eclipse.core.runtime.Adapters;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.egit.core.internal.indexdiff.IndexDiffCache;
import org.eclipse.egit.core.internal.util.ResourceUtil;
import org.eclipse.egit.core.op.CloneOperation.PostCloneTask;
import org.eclipse.egit.core.project.RepositoryMapping;
import org.eclipse.egit.ui.internal.dialogs.BasicConfigurationDialog;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.team.core.RepositoryProvider;

public class ImportClonedRepository implements PostCloneTask {

    @Override
    public void execute(Repository repository, IProgressMonitor monitor) throws CoreException {
        var report = MigrationReport.emptyReport();
        var importBonitaProjectOperation = new ImportBonitaProjectOperation(repository.getDirectory().getParentFile());
        importBonitaProjectOperation.run(monitor);
        report = importBonitaProjectOperation.getReport();
        var project = importBonitaProjectOperation.getProject();
        var currentRepo = RepositoryManager.getInstance().switchToRepository(
                project.getName(),
                monitor);
       
        try {
            currentRepo.migrate(report, monitor);
        } catch (MigrationException e) {
            throw new CoreException(Status.error("An error occured while migrating cloned project.", e));
        }
        
        var mapping =  RepositoryMapping.getMapping(project);
        if (mapping != null && mapping.getRepository() != null) {
            IndexDiffCache.INSTANCE.remove(mapping.getGitDirAbsolutePath().toFile());
            IndexDiffCache.INSTANCE
                    .getIndexDiffCacheEntry(mapping.getRepository());
        }
        BasicConfigurationDialog.show(repository);
    }

}
