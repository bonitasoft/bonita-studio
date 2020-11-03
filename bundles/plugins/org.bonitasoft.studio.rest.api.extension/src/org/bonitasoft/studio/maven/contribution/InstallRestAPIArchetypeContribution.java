/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.maven.contribution;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.apache.maven.artifact.factory.DefaultArtifactFactory;
import org.apache.maven.artifact.installer.ArtifactInstallationException;
import org.apache.maven.artifact.repository.ArtifactRepository;
import org.bonitasoft.studio.businessobject.maven.MavenInstallFileCommand;
import org.bonitasoft.studio.common.extension.IPostStartupContribution;
import org.bonitasoft.studio.common.jface.BonitaErrorDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.bonitasoft.studio.maven.DependencyCatalog;
import org.bonitasoft.studio.maven.MavenArtifactParser;
import org.bonitasoft.studio.maven.MavenLocalRepositoryContributor;
import org.bonitasoft.studio.maven.i18n.Messages;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;
import org.eclipse.m2e.core.MavenPlugin;
import org.eclipse.m2e.core.internal.embedder.MavenImpl;
import org.eclipse.swt.widgets.Display;
import org.osgi.framework.Bundle;

public class InstallRestAPIArchetypeContribution implements IPostStartupContribution {

    private static final String DEPENDENCIES_PLUGIN_ID = "org.bonitasoft.studio.rest.api.extension.dependencies";

    @Override
    public void execute() {
        try {
            MavenLocalRepositoryContributor newMavenLocalRepositoryContributor = newMavenLocalRepositoryContributor();
            if(newMavenLocalRepositoryContributor != null) {
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
        final MavenImpl maven = maven();
        File rootFolder = getRootFolder();
        if(rootFolder == null) {
            return null;
        }
        final ArtifactRepository internalRepository = maven.createArtifactRepository("studio-internal-repository", rootFolder.toURI().toURL().toString());
        return new MavenLocalRepositoryContributor(internalRepository, maven.getLocalRepository(),
                new DependencyCatalog(getRootFolder(),
                        new MavenArtifactParser((DefaultArtifactFactory) maven.lookupComponent(org.apache.maven.artifact.factory.ArtifactFactory.class))),
                new MavenInstallFileCommand(maven()));
    }

    protected MavenImpl maven() {
        return (MavenImpl) MavenPlugin.getMaven();
    }

    protected File getRootFolder() throws IOException {
       Bundle dependenciesBundle = dependenciesBundle();
       return dependenciesBundle != null ? new File( FileLocator.toFileURL(dependenciesBundle.getResource("/lib/")).getFile()).getCanonicalFile() : null;
    }

    protected Bundle dependenciesBundle() {
        return Platform.getBundle(DEPENDENCIES_PLUGIN_ID);
    }

}
