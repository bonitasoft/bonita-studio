/**
 * Copyright (C) 2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.common.repository.core;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;

import org.bonitasoft.studio.common.FileUtil;
import org.bonitasoft.studio.common.ProjectUtil;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.bonitasoft.studio.common.repository.CommonRepositoryPlugin;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IProgressMonitor;

public class BonitaHomeHandler {

    public static final String BONITA_HOME = "bonita_home";
    private final IProject project;

    public BonitaHomeHandler(final IProject project) {
        this.project = project;
    }

    public void cleanTenant() {
        final File bonitaServerFile = Paths.get(getRoot(), "engine-server", "work", "tenants", "1").toFile();
        PlatformUtil.delete(bonitaServerFile, null);
        final File bonitaClientFile = Paths.get(getRoot(), "engine-client", "work", "tenants", "1").toFile();
        PlatformUtil.delete(bonitaClientFile, null);
        final File bonitaWebClientFile = Paths.get(getRoot(), "client", "tenants", "1").toFile();
        PlatformUtil.delete(bonitaWebClientFile, null);
        final File platformTomcatConfig = Paths.get(getRoot(), "client", "platform", "conf", "platform-tenant-config.properties").toFile();
        PlatformUtil.delete(platformTomcatConfig, null);
        try {
            FileUtil.copyFile(getDefaultPlatformTenantConfigFile(), platformTomcatConfig);
        } catch (final IOException e) {
            BonitaStudioLog.error(e, CommonRepositoryPlugin.PLUGIN_ID);
        }
        deleteBonitaDbFiles();
    }

    public void deleteBusinessDataDBFiles() {
        deleteDbFiles("business");
    }

    protected void deleteBonitaDbFiles() {
        deleteDbFiles("bonita");
    }

    protected void deleteDbFiles(final String fileStartName) {
        final File workDir = getPlatformWorkDir();
        if (workDir != null && workDir.exists()) {
            for (final File file : workDir.listFiles()) {
                final String fileName = file.getName();
                if (fileName.endsWith("h2.db") && fileName.contains(fileStartName)) {
                    PlatformUtil.delete(file, null);
                    if (file.exists()) {
                        BonitaStudioLog.info(fileName + " failed to be deleted", CommonRepositoryPlugin.PLUGIN_ID);
                    } else {
                        BonitaStudioLog.info(fileName + " has been deleted successfuly", CommonRepositoryPlugin.PLUGIN_ID);
                    }
                }
            }
        }
    }

    protected File getPlatformWorkDir() {
        return Paths.get(getRoot(), "engine-server", "work", "platform").toFile();
    }

    public String getRoot() {
        return project.getFile(BONITA_HOME).getLocation().toFile().getAbsolutePath();
    }

    public void initBonitaHome(final IProgressMonitor monitor) throws CoreException {
        final File bonitaHome = new File(getRoot());
        if (!bonitaHome.exists()) {
            final URL url = ProjectUtil.getConsoleLibsBundle().getResource("tomcat/bonita");
            try {
                final File originalBonitaHome = new File(FileLocator.toFileURL(url).getFile());
                PlatformUtil.copyResource(bonitaHome, originalBonitaHome, monitor);
                project.getFile("bonita").getLocation().toFile().renameTo(bonitaHome);
                BonitaStudioLog.info("Bonita Home initialized successfuly.", CommonRepositoryPlugin.PLUGIN_ID);
            } catch (final IOException e) {
                BonitaStudioLog.error(e);
            }
            project.getFile(BONITA_HOME).refreshLocal(IResource.DEPTH_INFINITE, monitor);
        }
    }

    public File getDefaultTenantSecurityConfigFile(final long tenantId) {
        return new File(getRoot(), "client" + File.separator + "tenants" + File.separator + String.valueOf(tenantId) + File.separator + "conf"
                + File.separator + "security-config.properties");
    }

    public File getDefaultTenantSecurityConfigStudioFile() {
        final URL url = ProjectUtil.getConsoleLibsBundle().getEntry("bonita-home");
        File bonitaFolder = null;
        try {
            bonitaFolder = new File(FileLocator.toFileURL(url).getFile());
            return new File(bonitaFolder, "client" + File.separator + "platform" + File.separator + "tenant-template" + File.separator + "conf" + File.separator
                    + "security-config.properties");
        } catch (final IOException e) {
            BonitaStudioLog.error(e);
        }

        return null;
    }

    public File getDefaultPlatformTenantConfigFile() {
        final URL url = ProjectUtil.getConsoleLibsBundle().getEntry("bonita-home");
        File bonitaFolder = null;
        try {
            bonitaFolder = new File(FileLocator.toFileURL(url).getFile());
            return new File(bonitaFolder,
                    "client" + File.separator + "platform" + File.separator + "conf" + File.separator + "platform-tenant-config.properties");
        } catch (final IOException e) {
            BonitaStudioLog.error(e);
        }

        return null;
    }

    public File getPortalI18NFolder() {
        return new File(getRoot(), "client"
                + File.separator + "platform"
                + File.separator + "work"
                + File.separator + "i18n");
    }

    public IFile getCustomPermissionMappingFile() {
        return project.getFile(BONITA_HOME + "/client/tenants/1/conf/custom-permissions-mapping.properties");
    }
}
