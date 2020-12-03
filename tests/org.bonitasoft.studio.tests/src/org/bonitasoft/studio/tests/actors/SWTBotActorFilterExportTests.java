/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.tests.actors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Properties;
import java.util.zip.ZipException;

import org.bonitasoft.studio.common.ProjectUtil;
import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.bonitasoft.studio.connectors.repository.ConnectorDefRepositoryStore;
import org.bonitasoft.studio.connectors.repository.ConnectorImplRepositoryStore;
import org.bonitasoft.studio.connectors.repository.ExportConnectorArchiveOperation;
import org.bonitasoft.studio.identity.actors.repository.ExportActorFilterArchiveOperation;
import org.bonitasoft.studio.swtbot.framework.conditions.ShellIsActiveWithThreadSTacksOnFailure;
import org.bonitasoft.studio.swtbot.framework.rule.SWTGefBotRule;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;

@RunWith(SWTBotJunit4ClassRunner.class)
public class SWTBotActorFilterExportTests {

    private static final String INITIATOR_ACTORFILTER_IMPL_FILENAME = "bonita-actorfilter-initiator-impl-1.0.0.zip";

    private static final String INITIATOR_ACTORFILTER_IMPL_LABEL = "bonita-actorfilter-initiator-impl (1.0.0) -- org.bonitasoft.actorfilter.initiator.ProcessInitiatorUserFilter";

    private SWTGefBot bot = new SWTGefBot();

    @Rule
    public SWTGefBotRule rule = new SWTGefBotRule(bot);

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    private void exportActorFilter(final String connector, final String fileName,
            final boolean hasDependencies, final boolean hasSources) throws Exception {
        SWTBotActorFilterUtil.activateExportActorFilterShell(bot);
        bot.table().select(connector);
        assertFalse("Finish button should be disabled", bot.button(IDialogConstants.FINISH_LABEL).isEnabled());
        if (hasSources) {
            bot.checkBoxWithLabel("Include sources").select();
        } else {
            bot.checkBoxWithLabel("Include sources").deselect();
        }
        if (hasDependencies) {
            bot.checkBoxWithLabel("Add dependencies").select();
        } else {
            bot.checkBoxWithLabel("Add dependencies").deselect();
        }
        bot.textWithLabel("Destination *").setText(ProjectUtil.getBonitaStudioWorkFolder().getAbsolutePath());
        bot.waitUntil(Conditions.widgetIsEnabled(bot.button(IDialogConstants.FINISH_LABEL)), 5000);
        bot.button(IDialogConstants.FINISH_LABEL).click();
        bot.waitUntil(
                new ShellIsActiveWithThreadSTacksOnFailure(org.bonitasoft.studio.common.repository.Messages.exportLabel),
                15000);
        bot.button(IDialogConstants.OK_LABEL).click();
        checkExportedFile(ProjectUtil.getBonitaStudioWorkFolder().getAbsolutePath(), fileName, hasDependencies, hasSources);
    }

    @Test
    public void testBonitaActorFilterExport() throws Exception {
        final String usermanager = INITIATOR_ACTORFILTER_IMPL_LABEL;
        final String fileName = INITIATOR_ACTORFILTER_IMPL_FILENAME;
        exportActorFilter(usermanager, fileName, true, false);
    }

    @Test
    public void testRewriteBonitaActorFilterExport() throws Exception {
        final String usermanager = INITIATOR_ACTORFILTER_IMPL_LABEL;
        final String fileName = INITIATOR_ACTORFILTER_IMPL_FILENAME;
        exportActorFilter(usermanager, fileName, true, false);
        exportActorFilter(usermanager, fileName, true, false);
    }

    @Test
    public void testUserActorFilterExport() throws Exception {
        final String id = "actorFilterTest1";
        final String version = "1.0.0";
        final String className = "MyActorFilterTest1";
        final String packageName = "org.bonitaSoft.studioTests";
        final String userActorFilter = id + "-impl (" + version + ")" + " -- "
                + packageName + "." + className;
        final String fileName = "actorFilterTest1-impl-1.0.0.zip";
        SWTBotActorFilterUtil.createActorFilterDefAndImpl(bot, id, version,
                className, packageName);
        exportActorFilter(userActorFilter, fileName, false, true);
    }

    private void checkExportedFile(final String path, final String fileName, final boolean hasDependencies,
            final boolean hasSources) throws Exception {
        final File zipFile = new File(path + File.separator + fileName);
        assertTrue("actor filter zip file was not created", zipFile.exists());
        final File destDir = new File(ProjectUtil.getBonitaStudioWorkFolder().getAbsolutePath());
        destDir.deleteOnExit();
        final IProgressMonitor monitor = new NullProgressMonitor();
        try {
            PlatformUtil.unzipZipFiles(zipFile, destDir, monitor);
        } catch (final Exception e) {
            if (e instanceof IOException) {
                assertTrue("IO error while unzip file " + zipFile.getName(), false);
            }
            if (e instanceof ZipException) {
                assertTrue("ZIP error while unzip file " + zipFile.getName(), false);
            }
        }
        assertTrue("actor filter zip file was not unzipped correctly",
                destDir.exists());
        testDescriptorFileExistence(destDir);
        testSourceFileExistence(destDir, hasSources);
        testDefinitionFileExistence(destDir);
        testImplementationFileExistence(destDir);
        testClasspathDirExistence(destDir, hasDependencies);
        testMessageFilesExistence(destDir);
        zipFile.delete();
    }

    private void testSourceFileExistence(final File destDir, final boolean hasSources) {
        final File sourceDir = new File(destDir,
                ExportConnectorArchiveOperation.SRC_DIR);
        if (hasSources) {

            assertTrue(
                    "source folder should be contained in exported Actor Filter zip",
                    sourceDir.exists());
            final File[] sourceFiles = sourceDir.listFiles();
            assertNotNull("source dir should contain at least one file",
                    sourceFiles);
        } else {
            assertFalse("connector zip should not contain source files",
                    sourceDir.exists());
        }
        sourceDir.deleteOnExit();
    }

    private void testDescriptorFileExistence(final File destDir) throws IOException {
        final File descriptor = new File(destDir,
                ExportConnectorArchiveOperation.DESCRIPTOR_FILE);
        assertTrue("descriptor should be contained in exported Actor Filter",
                descriptor.exists());
        final Properties p = new Properties();
        final FileInputStream fis = new FileInputStream(descriptor);
        p.load(fis);
        fis.close();
        final String version = p
                .getProperty(ExportConnectorArchiveOperation.VERSION);
        assertFalse("version is missing in the descriptor",
                (version == null || version.isEmpty()));
        final String type = p
                .getProperty(ExportActorFilterArchiveOperation.TYPE);
        assertFalse("type is missing in the descriptor",
                (type == null || type.isEmpty()));
        descriptor.deleteOnExit();
    }

    private void testDefinitionFileExistence(final File destDir) {
        final FilenameFilter defFilter = new FilenameFilter() {

            @Override
            public boolean accept(final File dir, final String name) {
                return name.endsWith("."
                        + ConnectorDefRepositoryStore.CONNECTOR_DEF_EXT);
            }
        };
        final File[] defFiles = destDir.listFiles(defFilter);
        assertNotNull("def file should exist in exported Actor Filter zip file",
                defFiles);
        assertEquals(
                "only one def file should exist in exported Actor Filter zip file",
                defFiles.length, 1);
    }

    private void testImplementationFileExistence(final File destDir) {
        final FilenameFilter implFilter = new FilenameFilter() {

            @Override
            public boolean accept(final File dir, final String name) {
                return name.endsWith("."
                        + ConnectorImplRepositoryStore.CONNECTOR_IMPL_EXT);
            }
        };
        final File[] implFiles = destDir.listFiles(implFilter);
        assertNotNull("impl file should exist in exported Actor Filter zip file",
                implFiles);
        assertEquals(
                "only one impl file should exist in exported Actor Filter zip file",
                implFiles.length, 1);
    }

    private void testClasspathDirExistence(final File destDir, final boolean hasDependencies) {
        final File classpathDir = new File(destDir,
                ExportConnectorArchiveOperation.CLASSPATH_DIR);
        assertTrue(
                "classPath folder should be contained in exported Actor Filter",
                classpathDir.exists());
        final File[] depfiles = classpathDir.listFiles();
        assertNotNull("classpath should countain at least one jar file",
                depfiles);
        if (hasDependencies) {
            assertTrue("classpath should contain at least one dependency",
                    depfiles.length >= 1);
        }
        classpathDir.deleteOnExit();
    }

    private void testMessageFilesExistence(final File destDir) {
        final FilenameFilter messageFilter = new FilenameFilter() {

            @Override
            public boolean accept(final File dir, final String name) {
                return name.endsWith(".properties")
                        && !name.equals(ExportConnectorArchiveOperation.DESCRIPTOR_FILE);
            }
        };
        final File[] messageFiles = destDir.listFiles(messageFilter);
        assertNotNull(
                "message property file(s) should exist in exported connector zip file",
                messageFiles);
    }

    @After
    public void deleteFiles() {
        PlatformUtil.delete(ProjectUtil.getBonitaStudioWorkFolder(), null);
        ProjectUtil.getBonitaStudioWorkFolder().mkdir();
    }
}
