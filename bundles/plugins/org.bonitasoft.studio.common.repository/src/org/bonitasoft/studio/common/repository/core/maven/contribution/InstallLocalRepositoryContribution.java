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

    private static final String LOCAL_REPOSITORY_ID = "local";

    @Override
    public void execute() {
        try {
            MavenLocalRepositoryContributor newMavenLocalRepositoryContributor = newMavenLocalRepositoryContributor();
            if (newMavenLocalRepositoryContributor != null) {
                newMavenLocalRepositoryContributor.execute();
            }
        } catch (IOException | CoreException e) {
            if (!PlatformUtil.isHeadless()) {
                Display.getDefault().asyncExec(() ->
                        new BonitaErrorDialog(Display.getDefault().getActiveShell(), Messages.dependenciesInstallation,
                                Messages.dependenciesInstallationMsg, e).open());
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
        ArtifactRepository internalRepository = maven.createArtifactRepository(LOCAL_REPOSITORY_ID,
                rootFolder.toURI().toURL().toString());
        if(!LOCAL_REPOSITORY_ID.equals(internalRepository.getId())){ // Check if the repository is mirrored 
            internalRepository = internalRepository.getMirroredRepositories().stream().filter(repo -> LOCAL_REPOSITORY_ID.equals(repo.getId()))
                    .findFirst()
                    .orElseThrow();
        }
        return new MavenLocalRepositoryContributor(rootFolder, maven.getLocalRepository(),
                new DependencyCatalog(rootFolder,
                        new MavenArtifactParser((DefaultArtifactFactory) maven
                                .lookup(org.apache.maven.artifact.factory.ArtifactFactory.class))),
                new MavenInstallFileOperation(maven(), internalRepository, maven.getLocalRepository()));
    }

    protected IMaven maven() {
        return MavenPlugin.getMaven();
    }

    protected File getRootFolder() throws IOException {
        URL repositoryURL = CommonRepositoryPlugin.getDefault().getBundle().getResource("/repository/");
        return new File(FileLocator.toFileURL(repositoryURL).getFile());
    }

}
