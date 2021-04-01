/**
 * Copyright (C) 2020 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.businessobject.maven;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.bonitasoft.studio.businessobject.core.operation.GenerateBDMOperation;
import org.bonitasoft.studio.businessobject.core.repository.BDMArtifactDescriptor;
import org.bonitasoft.studio.common.CommandExecutor;
import org.bonitasoft.studio.common.FileUtil;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.m2e.core.MavenPlugin;
import org.eclipse.m2e.core.embedder.IMaven;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;

public class InstallBDMDependenciesEventHandler implements EventHandler {

    private static final String JAR_TYPE = "jar";
    private static final String BDM_ARTIFACT_DESCRIPTOR = "artifactDescriptor";
    private static final String UPDATE_PROJECTS_COMMAND = "org.bonitasoft.studio.rest.api.extension.updatemavenprojects.command";
    private static boolean enableProjectUpdateJob = true;
    
    @Override
    public void handleEvent(final Event event) {
        execute(event);
    }

    private void execute(final Event event) {
        final MavenInstallFileCommand installFileCommand = newInstallCommand();
        BDMArtifactDescriptor artifactDescriptor = (BDMArtifactDescriptor) event.getProperty(BDM_ARTIFACT_DESCRIPTOR);
        String groupId = artifactDescriptor.getGroupId();
        String version = artifactDescriptor.getVersion();
        final byte[] bdmClientJarContent = (byte[]) event.getProperty(GenerateBDMOperation.BDM_CLIENT);
        File tmpFile = null;
        try {
            tmpFile = tmpFile(GenerateBDMOperation.BDM_CLIENT, bdmClientJarContent);
            installFileCommand.installFile(groupId, GenerateBDMOperation.BDM_CLIENT, version, JAR_TYPE, null, tmpFile);
        } catch (final CoreException | IOException e) {
            BonitaStudioLog.error(e);
        } finally {
            if (tmpFile != null) {
                tmpFile.delete();
            }
        }

        final byte[] bdmDaoJarContent = (byte[]) event.getProperty(GenerateBDMOperation.BDM_DAO);
        try {
            tmpFile = tmpFile(GenerateBDMOperation.BDM_DAO, bdmDaoJarContent);
            installFileCommand.installFile(groupId, GenerateBDMOperation.BDM_DAO, version, JAR_TYPE, null, tmpFile,
                    daoPomFile(groupId, version));
        } catch (final IOException | CoreException e) {
            BonitaStudioLog.error(e);
        } finally {
            if (tmpFile != null) {
                tmpFile.delete();
            }
        }
        
        if(shouldRunProjectUpateJob()) {
        updateProjectMavenConfiguration();
        updateMavenProjects();
        }
    }
    
    private boolean shouldRunProjectUpateJob() {
        return enableProjectUpdateJob;
    }

    public static void enableProjectUpateJob() {
        enableProjectUpdateJob = true;
    }

    public static void disableProjectUpateJob() {
        enableProjectUpdateJob = false;
    }

    protected void updateProjectMavenConfiguration() {
        new WorkspaceJob("Update project") {
            
            @Override
            public IStatus runInWorkspace(IProgressMonitor monitor) throws CoreException {
                MavenPlugin.getProjectConfigurationManager().updateProjectConfiguration(RepositoryManager.getInstance().getCurrentRepository().getProject(), monitor);    
                return Status.OK_STATUS;
            }
        }.schedule();
    }

    private File daoPomFile(String groupId, String version) throws IOException {
        File templateFile = new File(URLDecoder.decode(FileLocator
                .toFileURL(InstallBDMDependenciesEventHandler.class.getResource("bdm-dao.pom.template"))
                .getFile(), "UTF-8"));
        Path pomFile = Paths.get(System.getProperty("java.io.tmpdir"), String.format("bdm-dao-%s.pom", version));
        if (!pomFile.toFile().exists()) {
            pomFile = Files.createFile(pomFile);
        }
        Files.copy(templateFile.toPath(), pomFile, StandardCopyOption.REPLACE_EXISTING);
        FileUtil.replaceStringInFile(pomFile.toFile(), "%GROUP_ID%", groupId);
        return pomFile.toFile();
    }

    protected void updateMavenProjects() {
        CommandExecutor commandExecutor = new CommandExecutor();
        if (commandExecutor.canExecute(UPDATE_PROJECTS_COMMAND, null)) {
            commandExecutor.executeCommand(UPDATE_PROJECTS_COMMAND, null);
        }
    }

    protected MavenInstallFileCommand newInstallCommand() {
        return new MavenInstallFileCommand(maven());
    }

    protected File tmpFile(String name, byte[] content) throws IOException {
        File tmpFile = File.createTempFile(name, ".jar");
        try (InputStream stream = new ByteArrayInputStream(content)) {
            Files.copy(stream, tmpFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }
        return tmpFile;
    }

    protected IMaven maven() {
        return MavenPlugin.getMaven();
    }

}
