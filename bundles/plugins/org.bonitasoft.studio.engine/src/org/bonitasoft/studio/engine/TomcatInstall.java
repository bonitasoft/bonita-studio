/**
 * Copyright (C) 2026 Bonitasoft S.A.
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

package org.bonitasoft.studio.engine;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.Properties;
import java.util.zip.ZipFile;

import org.bonitasoft.studio.common.FileUtil;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.ui.PlatformUtil;
import org.bonitasoft.studio.engine.i18n.Messages;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.osgi.util.NLS;
import org.osgi.framework.Bundle;

/**
 * The Tomcat install in the workspace: where it lives, whether its content matches the WTP adapter
 * the Studio drives it with, how it is extracted from the shipped bundle, and how the engine log
 * survives a reset.
 * <p>
 * Takes the bundle directory as a constructor argument rather than reading the workspace itself.
 */
class TomcatInstall {

    private static final String SERVER_FOLDER = "server";
    private static final String SERVER_LIB_FOLDER = "lib";
    private static final String SERVER_LIB_EXT_FOLDER = "ext";
    private static final String SERVER_LOGS_FOLDER = "logs";
    private static final String VERSION_MARKER_JAR = "catalina.jar";
    private static final String SERVER_INFO_ENTRY = "org/apache/catalina/util/ServerInfo.properties";
    private static final String SERVER_INFO_KEY = "server.info";
    private static final String PREVIOUS_LOG_FILE = "tomcat-previous.log";

    private final File bundleDirectory;
    /** The only version state: both reported forms are derived from it by the statics below. */
    private final String tomcatMajorMinor;

    TomcatInstall(File bundleDirectory, String tomcatMajorMinor) {
        this.bundleDirectory = bundleDirectory;
        this.tomcatMajorMinor = tomcatMajorMinor;
    }

    /** The line a build with no patch component reports: "Apache Tomcat/10.1". */
    private static String serverInfoLine(String tomcatMajorMinor) {
        return "Apache Tomcat/" + tomcatMajorMinor;
    }

    /** Static because the prefix depends on the version alone, not on any install. */
    static String serverInfoPrefix(String tomcatMajorMinor) {
        return serverInfoLine(tomcatMajorMinor) + ".";
    }

    File bundleDirectory() {
        return bundleDirectory;
    }

    File installDirectory() {
        return new File(bundleDirectory, SERVER_FOLDER);
    }

    private File libDirectory() {
        return new File(installDirectory(), SERVER_LIB_FOLDER);
    }

    /** Where the launch classpath picks up the logging jars. */
    File extLibDirectory() {
        return new File(libDirectory(), SERVER_LIB_EXT_FOLDER);
    }

    private File logsDirectory() {
        return new File(installDirectory(), SERVER_LOGS_FOLDER);
    }

    File versionMarkerJar() {
        return new File(libDirectory(), VERSION_MARKER_JAR);
    }

    File previousLogFile() {
        return new File(bundleDirectory.getParentFile(), PREVIOUS_LOG_FILE);
    }

    String serverInfoPrefix() {
        return serverInfoPrefix(tomcatMajorMinor);
    }

    /**
     * Checks the workspace install against the adapter version, reading what WTP itself validates:
     * ServerInfo.properties inside server/lib/catalina.jar.
     * <p>
     * Only a fully ABSENT bundle directory counts as fresh. A present bundle without server/lib is a
     * partially deleted or partially extracted tree, and the bundle copy would merge the new Tomcat
     * into it, mixing two generations.
     */
    boolean matchesAdapter() {
        if (!bundleDirectory().exists()) {
            // fresh workspace: the bundle copy will extract
            return true;
        }
        var lib = libDirectory();
        if (!lib.exists()) {
            // partial install, or a partially failed purge
            return false;
        }
        var catalinaJar = versionMarkerJar();
        if (!catalinaJar.exists()) {
            return false;
        }
        try (var jar = new ZipFile(catalinaJar)) {
            var entry = jar.getEntry(SERVER_INFO_ENTRY);
            if (entry == null) {
                // uninformative rather than wrong: defer to WTP validation, which reads the same
                // entry and fails visibly if the install is genuinely broken
                BonitaStudioLog.info(String.format(
                        "No ServerInfo.properties in %s, deferring the version check to the WTP validation",
                        catalinaJar), EnginePlugin.PLUGIN_ID);
                return true;
            }
            var serverInfo = new Properties();
            try (var stream = jar.getInputStream(entry)) {
                serverInfo.load(stream);
            }
            var found = serverInfo.getProperty(SERVER_INFO_KEY, "");
            if (found.isBlank()) {
                // same as a missing entry: reading it as stale would purge, re-extract, and then
                // still tell the user to reinstall the Studio
                BonitaStudioLog.info(String.format(
                        "No %s in %s, deferring the version check to the WTP validation",
                        SERVER_INFO_KEY, catalinaJar), EnginePlugin.PLUGIN_ID);
                return true;
            }
            // the patch separator is what keeps 10.11 from matching 10.1. A build reporting the
            // line with no patch at all is accepted explicitly: read as stale it would purge,
            // re-extract and fail again on every start
            if (!found.startsWith(serverInfoPrefix()) && !found.equals(serverInfoLine(tomcatMajorMinor))) {
                // the only branch that triggers the purge and re-extraction, so it logs the version
                // found - without it an unexpected bundle is an undiagnosable reset loop
                BonitaStudioLog.info(String.format(
                        "Installed Tomcat is '%s', expected %s* - purging the workspace install",
                        found, serverInfoPrefix()), EnginePlugin.PLUGIN_ID);
                return false;
            }
            return true;
        } catch (IOException e) {
            // a transient read failure (an AV scanner holding the jar) must not cost a full
            // re-extraction: report a match and let WTP validation fail visibly instead
            BonitaStudioLog.error(String.format(
                    "Cannot read %s to check the installed Tomcat version, deferring to the WTP validation",
                    catalinaJar), e, EnginePlugin.PLUGIN_ID);
            return true;
        }
    }

    BundleCopy copyBundle(final IProgressMonitor monitor) {
        try {
            final File tomcatLib = libDirectory();
            if (!tomcatLib.exists()) {
                BonitaStudioLog.debug("Copying tomcat bundle in workspace...", EnginePlugin.PLUGIN_ID);
                final URL url = shippedBundle().getResource("tomcat");
                if (url == null) {
                    BonitaStudioLog.error("The Tomcat bundle resource is missing from the engine plugin.",
                            EnginePlugin.PLUGIN_ID);
                    return BundleCopy.FAILED;
                }
                var tomcatFolder = new File(FileLocator.toFileURL(url).getFile());
                PlatformUtil.copyResource(bundleDirectory(), tomcatFolder, monitor);
                BonitaStudioLog.debug("Tomcat bundle copied in workspace.",
                        EnginePlugin.PLUGIN_ID);
                return BundleCopy.EXTRACTED;
            }
        } catch (final IOException e) {
            BonitaStudioLog.error(e, EnginePlugin.PLUGIN_ID);
            return BundleCopy.FAILED;
        }
        return BundleCopy.KEPT;
    }

    void deleteStaleBundle() throws CoreException {
        var bundleDir = bundleDirectory();
        if (bundleDir.exists()) {
            BonitaStudioLog.info(String.format(
                    "Resetting the Tomcat bundle at %s to match the current Tomcat adapter version.",
                    bundleDir), EnginePlugin.PLUGIN_ID);
            warnIfRelocated(bundleDir);
            // marker jar first, so a partial deleteDir leaves the install detectably stale whatever
            // order File.list() yielded
            FileUtil.tryAndDeleteFile(versionMarkerJar());
            boolean deleted;
            try {
                // inside the try: it deletes an earlier generation, so it can fail the same way
                keepPreviousLogFile();
                deleted = deleteDirectory(bundleDir);
            } catch (RuntimeException e) {
                // FileUtil.deleteDir raises a RuntimeException for a directory it cannot enumerate,
                // which is the locked-tree case this message exists for. Callers only catch
                // CoreException, so letting it through would skip the recovery dialog
                throw new CoreException(
                        Status.error(NLS.bind(Messages.staleTomcatBundleDeletionFailed, bundleDir), e));
            }
            if (!deleted) {
                throw new CoreException(
                        Status.error(NLS.bind(Messages.staleTomcatBundleDeletionFailed, bundleDir)));
            }
        }
    }

    /**
     * The engine log from the install, or the copy a reset kept. Between a purge and the first
     * successful start the install has no log, and that copy is the one describing what went wrong.
     */
    File engineLogFile() {
        var current = newestBonitaLog(logsDirectory());
        if (current != null) {
            return current;
        }
        var kept = previousLogFile();
        return kept.isFile() ? kept : null;
    }

    /**
     * Names the link target, because the reset does not stop at the link: FileUtil.deleteDir recurses
     * through isDirectory() and list(), which both follow links, so the relocated tree's CONTENTS are
     * deleted and only the link itself is unlinked. The fresh install then goes to the workspace, so
     * the moved location stops being used - a user who relocated the install needs both halves.
     */
    private static void warnIfRelocated(File bundleDir) {
        var path = bundleDir.toPath();
        if (!Files.isSymbolicLink(path)) {
            return;
        }
        String target;
        try {
            target = Files.readSymbolicLink(path).toString();
        } catch (IOException e) {
            target = "its target";
            BonitaStudioLog.debug(e.getMessage(), EnginePlugin.PLUGIN_ID);
        }
        BonitaStudioLog.warning(String.format(
                "%s is a symbolic link to %s: resetting the Tomcat install deletes the contents of that"
                        + " location as well, and the fresh install is extracted in the workspace, so the link"
                        + " target is no longer used by the Studio - check it after the reset.",
                bundleDir, target), EnginePlugin.PLUGIN_ID);
    }

    /**
     * Copies the engine log out of the tree about to be deleted, so the purge does not destroy the
     * evidence for the failure it reports.
     * <p>
     * One fixed file, overwritten rather than a directory of generations: clearing such a directory
     * can fail on a locked file, which would destroy the current logs and then serve the previous
     * generation as if it were current.
     */
    private void keepPreviousLogFile() {
        var current = newestBonitaLog(logsDirectory());
        if (current == null) {
            return;
        }
        var kept = previousLogFile();
        try {
            Files.copy(current.toPath(), kept.toPath(), StandardCopyOption.REPLACE_EXISTING);
            BonitaStudioLog.info(String.format("Engine log copied to %s before the reset.", kept),
                    EnginePlugin.PLUGIN_ID);
        } catch (IOException e) {
            // best effort: the purge itself must not fail because a log file is locked
            BonitaStudioLog.warning(String.format(
                    "Could not copy the engine log %s: it is dropped by the reset.", current),
                    EnginePlugin.PLUGIN_ID);
            BonitaStudioLog.debug(e.getMessage(), EnginePlugin.PLUGIN_ID);
        }
    }

    private static File newestBonitaLog(File logDirectory) {
        final File[] list = logDirectory.listFiles((file, fileName) -> fileName.contains("bonita"));
        if (list == null) {
            return null;
        }
        Date newest = new Date(0);
        File newestLog = null;
        for (final File f : list) {
            final Date modified = new Date(f.lastModified());
            if (modified.after(newest)) {
                newest = modified;
                newestLog = f;
            }
        }
        return newestLog;
    }

    /** Test seam: the recursive delete, so a failure can be simulated without a locked file. */
    boolean deleteDirectory(File directory) {
        return FileUtil.deleteDir(directory);
    }

    /** Test seam: the plugin carrying the shipped tomcat/ resource. */
    Bundle shippedBundle() {
        return EnginePlugin.getDefault().getBundle();
    }
}
