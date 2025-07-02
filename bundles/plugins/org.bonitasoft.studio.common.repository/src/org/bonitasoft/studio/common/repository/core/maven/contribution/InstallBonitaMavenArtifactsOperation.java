/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.common.repository.core.maven.contribution;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.maven.artifact.factory.DefaultArtifactFactory;
import org.apache.maven.artifact.repository.ArtifactRepository;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.Messages;
import org.bonitasoft.studio.common.repository.core.maven.MavenInstallFileOperation;
import org.bonitasoft.studio.common.repository.core.maven.repository.MavenRepositories;
import org.bonitasoft.studio.common.ui.PlatformUtil;
import org.bonitasoft.studio.common.ui.jface.BonitaErrorDialog;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.m2e.core.MavenPlugin;
import org.eclipse.m2e.core.embedder.IMaven;
import org.eclipse.swt.widgets.Display;

public class InstallBonitaMavenArtifactsOperation {

    private ArtifactRepository targetRepository;
    private static final ReentrantLock LOCK = new ReentrantLock();
    
    
    public InstallBonitaMavenArtifactsOperation(ArtifactRepository targetRepository) {
        this.targetRepository = targetRepository;
    }
    
    public void execute(IProgressMonitor monitor) {
        LOCK.lock();
        try {
            var localRepositoryContributor = newMavenLocalRepositoryContributor(targetRepository);
            if (localRepositoryContributor != null) {
                localRepositoryContributor.execute(monitor);
            }
        } catch (IOException | CoreException e) {
            if (!PlatformUtil.isHeadless()) {
                Display.getDefault().asyncExec(() ->
                        new BonitaErrorDialog(Display.getDefault().getActiveShell(), Messages.dependenciesInstallation,
                                Messages.dependenciesInstallationMsg, e).open());
            }
            BonitaStudioLog.error(e);
        }finally {
            LOCK.unlock();
        }
    }
    
    protected MavenLocalRepositoryContributor newMavenLocalRepositoryContributor(ArtifactRepository targetRepository) throws IOException, CoreException {
        final IMaven maven = maven();
        var bundledRepository = MavenRepositories.bundledRepository();
        if (bundledRepository == null) {
            return null;
        }
        File baseDir = new File(bundledRepository.getBasedir());
        return new MavenLocalRepositoryContributor(baseDir, targetRepository,
                new DependencyCatalog(baseDir,
                        new MavenArtifactParser((DefaultArtifactFactory) maven
                                .lookup(org.apache.maven.artifact.factory.ArtifactFactory.class))),
                new MavenInstallFileOperation(bundledRepository, targetRepository));
    }

    protected IMaven maven() {
        return MavenPlugin.getMaven();
    }
  
}
