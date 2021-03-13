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
import java.net.URL;

import org.apache.maven.artifact.factory.DefaultArtifactFactory;
import org.apache.maven.artifact.installer.ArtifactInstallationException;
import org.apache.maven.artifact.repository.ArtifactRepository;
import org.bonitasoft.studio.common.extension.IPostStartupContribution;
import org.bonitasoft.studio.common.jface.BonitaErrorDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.bonitasoft.studio.common.repository.CommonRepositoryPlugin;
import org.bonitasoft.studio.common.repository.Messages;
import org.bonitasoft.studio.common.repository.core.maven.MavenInstallFileOperation;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.m2e.core.MavenPlugin;
import org.eclipse.m2e.core.embedder.IMaven;
import org.eclipse.swt.widgets.Display;

public class InstallLocalRepositoryContribution implements IPostStartupContribution {

    @Override
    public void execute() {
        try {
            MavenLocalRepositoryContributor newMavenLocalRepositoryContributor = newMavenLocalRepositoryContributor();
            if (newMavenLocalRepositoryContributor != null) {
                newMavenLocalRepositoryContributor.execute();
            }
        } catch (IOException | CoreException | ArtifactInstallationException e) {
            if (!PlatformUtil.isHeadless()) {
                Display.getDefault().asyncExec(new Runnable() {

                    @Override
                    public void run() {
                        new BonitaErrorDialog(Display.getDefault().getActiveShell(), Messages.dependenciesInstallation,
                                Messages.dependenciesInstallationMsg, e).open();
                    }
                });
            }
            BonitaStudioLog.error(e);
        }
    }

    protected MavenLocalRepositoryContributor newMavenLocalRepositoryContributor() throws IOException, CoreException {
        final IMaven maven = maven();
        File rootFolder = getRootFolder();
        if (rootFolder == null) {
            return null;
        }
        final ArtifactRepository internalRepository = maven.createArtifactRepository("studio-internal-repository",
                rootFolder.toURI().toURL().toString());
        return new MavenLocalRepositoryContributor(internalRepository, maven.getLocalRepository(),
                new DependencyCatalog(rootFolder,
                        new MavenArtifactParser((DefaultArtifactFactory) maven
                                .lookup(org.apache.maven.artifact.factory.ArtifactFactory.class))),
                new MavenInstallFileOperation(maven()));
    }

    protected IMaven maven() {
        return MavenPlugin.getMaven();
    }

    protected File getRootFolder() throws IOException {
        URL repositoryURL = CommonRepositoryPlugin.getDefault().getBundle().getResource("/repository/");
        return new File(FileLocator.toFileURL(repositoryURL).getFile());
    }

}
