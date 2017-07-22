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
package org.bonitasoft.studio.tests.connectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.zip.ZipException;

import org.bonitasoft.studio.common.ProjectUtil;
import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.connector.model.i18n.Messages;
import org.bonitasoft.studio.connector.model.implementation.ConnectorImplementation;
import org.bonitasoft.studio.connectors.repository.ConnectorDefRepositoryStore;
import org.bonitasoft.studio.connectors.repository.ConnectorImplRepositoryStore;
import org.bonitasoft.studio.connectors.repository.ExportConnectorArchiveOperation;
import org.bonitasoft.studio.swtbot.framework.SWTBotConnectorTestUtil;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SWTBotJunit4ClassRunner.class)
public class SWTBotConnectorExportTests {

    private static final String CONNCTOR_EMAIL_IMPL_ID = "email-impl";
    private String emailConnectorVersion = "1.0.0";
    private SWTGefBot bot = new SWTGefBot();

    @Before
    public void initializeEmailVersion() {
        final ConnectorImplRepositoryStore store = RepositoryManager.getInstance()
                .getRepositoryStore(ConnectorImplRepositoryStore.class);
        for (final ConnectorImplementation impl : store.getImplementations()) {
            if (CONNCTOR_EMAIL_IMPL_ID.equals(impl.getImplementationId())) {
                emailConnectorVersion = impl.getImplementationVersion();
            }
        }
    }

    private void exportConnector(final String connector, final String fileName,
            final boolean hasDependencies, final boolean hasSources) throws Exception {
        final String exportPath = ProjectUtil.getBonitaStudioWorkFolder().getAbsolutePath();
        SWTBotConnectorTestUtil.activateExportConnectorShell(bot);
        bot.table().select(connector);
        assertFalse("Finish button should be disabled",
                bot.button(IDialogConstants.FINISH_LABEL).isEnabled());
        if (hasSources) {
            bot.checkBoxWithLabel(org.bonitasoft.studio.connectors.i18n.Messages.inculeSourcesLabel).select();
        } else {
            bot.checkBoxWithLabel(org.bonitasoft.studio.connectors.i18n.Messages.inculeSourcesLabel).deselect();
        }
        if (hasDependencies) {
            bot.checkBoxWithLabel(org.bonitasoft.studio.connectors.i18n.Messages.addDependencies).select();
        } else {
            bot.checkBoxWithLabel(org.bonitasoft.studio.connectors.i18n.Messages.addDependencies).deselect();
        }
        bot.textWithLabel(org.bonitasoft.studio.connectors.i18n.Messages.destinationLabel + " *").setText(exportPath);
        bot.button(IDialogConstants.FINISH_LABEL).click();
        bot.waitUntil(Conditions.shellIsActive(org.bonitasoft.studio.connectors.i18n.Messages.exportStatusTitle));
        bot.button(IDialogConstants.OK_LABEL).click();
        checkExportedFile(exportPath, fileName, hasDependencies, hasSources);
    }

    @Test
    public void testBonitaConnectorExport() throws Exception {
        final String emailConnector = "email-impl (" + emailConnectorVersion
                + ") -- org.bonitasoft.connectors.email.EmailConnector";
        final String fileName = "email-impl-" + emailConnectorVersion + ".zip";
        exportConnector(emailConnector, fileName, true, false);
    }

    @Test
    public void testRewriteBonitaConnectorExport() throws Exception {
        final String emailConnector = "email-impl (" + emailConnectorVersion
                + ") -- org.bonitasoft.connectors.email.EmailConnector";
        final String fileName = "email-impl-" + emailConnectorVersion + ".zip";
        exportConnector(emailConnector, fileName, true, false);
        exportConnector(emailConnector, fileName, true, false);
    }

    @Test
    public void testUserConnectorExport() throws Exception {
        final String id = "connectorTest1";
        final String version = "1.0.0";
        final String className = "MyConnectorTest1";
        final String packageName = "org.bonitaSoft.studioTests";
        final String userConnector = id + "-impl (" + version + ")" + " -- " + packageName + "." + className;
        final String fileName = "connectorTest1-impl-1.0.0.zip";
        SWTBotConnectorTestUtil.createConnectorDefAndImpl(bot, id, version, className, packageName);
        exportConnector(userConnector, fileName, false, true);
    }

    @Test
    public void testUserConnectorExportWithTranslations() throws Exception {
        final int nbEditorsBefore = bot.editors().size();
        final String id = "connectorTest2";
        final String version = "1.0.0";
        final String className = "MyConnectorTest2";
        final String packageName = "org.bonitaSoft.studioTests";
        final String userConnector = id + "-impl (" + version + ")" + " -- " + packageName + "." + className;
        final String fileName = "connectorTest2-impl-1.0.0.zip";
        final String language1 = "de";
        final String language2 = "sq";
        SWTBotConnectorTestUtil.createConnectorDefAndImpl(bot, id, version, className, packageName);
        SWTBotConnectorTestUtil.activateConnectorDefEditionShell(bot);
        bot.tree().expandNode("Uncategorized").select(id + " (" + version + ")");
        bot.button(Messages.Edit).click();
        bot.waitUntil(Conditions.shellIsActive("Edit connector definition"));
        bot.button(IDialogConstants.NEXT_LABEL).click();
        bot.button(IDialogConstants.NEXT_LABEL).click();
        bot.button(IDialogConstants.NEXT_LABEL).click();
        bot.button(IDialogConstants.NEXT_LABEL).click();
        int line = bot.table().indexOf(language1, "Locale");
        bot.table().getTableItem(line).check();
        line = bot.table().indexOf(language2, "Locale");
        bot.table().getTableItem(line).check();
        bot.button(IDialogConstants.FINISH_LABEL).click();
        bot.waitUntil(new ICondition() {

            @Override
            public boolean test() throws Exception {
                return nbEditorsBefore + 3 == bot.editors().size();
            }

            @Override
            public void init(final SWTBot bot) {
            }

            @Override
            public String getFailureMessage() {
                return "Editor for implementation has not been opened.";
            }
        }, 30000);
        exportConnector(userConnector, fileName, false, true);
        final File destDir = new File(ProjectUtil.getBonitaStudioWorkFolder().getAbsolutePath());
        destDir.deleteOnExit();
        assertNotNull(" unzip exported connector dir does not exist", destDir);
        assertTrue("dest dir doesn't exist", destDir.exists());
        final Set<String> fileNames = new HashSet<String>();
        fileNames.add("connectorTest2-1.0.0.properties");
        fileNames.add("connectorTest2-1.0.0_sq.properties");
        fileNames.add("connectorTest2-1.0.0_de.properties");
        checkMessageFiles(destDir, fileNames);
    }

    private void checkMessageFiles(final File destDir, final Set<String> fileNames) {
        final FilenameFilter messageFilter = new FilenameFilter() {

            @Override
            public boolean accept(final File dir, final String name) {
                return name.endsWith(".properties")
                        && !name.equals(ExportConnectorArchiveOperation.DESCRIPTOR_FILE);
            }

        };
        final File[] messageFiles = destDir.listFiles(messageFilter);
        assertNotNull("there should be at least one message file", messageFiles);
        assertTrue(
                " 3 message property file(s) should exist in exported connector zip file",
                messageFiles.length == 3);
        for (final File mf : messageFiles) {
            assertTrue(mf.getName() + "", fileNames.contains(mf.getName()));
        }
    }

    private void checkExportedFile(final String path, final String fileName, final boolean hasDependencies,
            final boolean hasSources) throws Exception {
        final File zipFile = new File(path + File.separator + fileName);
        zipFile.deleteOnExit();
        final File destDir = new File(ProjectUtil.getBonitaStudioWorkFolder().getAbsolutePath());
        try {
            PlatformUtil.unzipZipFiles(zipFile, destDir, new NullProgressMonitor());
        } catch (final Exception e) {
            if (e instanceof IOException) {
                assertTrue("IO error while unzip file " + zipFile.getName(), false);
            }
            if (e instanceof ZipException) {
                assertTrue("ZIP error while unzip file " + zipFile.getName(), false);
            }
        }
        testDescriptorFileExistence(destDir);
        testSourcesDirExistence(destDir, hasSources);
        testClasspathDirExistence(destDir, hasDependencies);
        testDefinitionFileExistence(destDir);
        testImplementationFileExistence(destDir);
        testMessageFilesExistence(destDir);
    }

    private void testSourcesDirExistence(final File destDir, final boolean hasSources) {
        final File sourceDir = new File(destDir,
                ExportConnectorArchiveOperation.SRC_DIR);
        sourceDir.deleteOnExit();
        if (hasSources) {

            assertTrue("source folder should be contained in exported connector zip", sourceDir.exists());
            final File[] sourceFiles = sourceDir.listFiles();
            assertNotNull("source dir should contain at least one file", sourceFiles);
        } else {
            assertFalse("connector zip should not contain source files", sourceDir.exists());
        }
    }

    private void testDescriptorFileExistence(final File destDir) throws IOException {
        final File descriptor = new File(destDir,
                ExportConnectorArchiveOperation.DESCRIPTOR_FILE);
        descriptor.deleteOnExit();
        assertTrue("descriptor should be contained in exported connector", descriptor.exists());
        final Properties p = new Properties();
        final FileInputStream fis = new FileInputStream(descriptor);
        p.load(fis);
        fis.close();
        final String version = p.getProperty(ExportConnectorArchiveOperation.VERSION);
        assertFalse("version is missing in the descriptor", (version == null || version.isEmpty()));
        final String type = p.getProperty(ExportConnectorArchiveOperation.TYPE);
        assertFalse("type is missing in the descriptor", (type == null || type.isEmpty()));
    }

    private void testDefinitionFileExistence(final File destDir) {
        final FilenameFilter defFilter = new FilenameFilter() {

            @Override
            public boolean accept(final File dir, final String name) {
                return name.endsWith("." + ConnectorDefRepositoryStore.CONNECTOR_DEF_EXT);
            }
        };
        final File[] defFiles = destDir.listFiles(defFilter);
        assertNotNull("def file should exist in exported connector zip file", defFiles);
        assertEquals("only one def file should exist in exported connector zip file", defFiles.length, 1);
    }

    private void testImplementationFileExistence(final File destDir) {
        final FilenameFilter implFilter = new FilenameFilter() {

            @Override
            public boolean accept(final File dir, final String name) {
                return name.endsWith("." + ConnectorImplRepositoryStore.CONNECTOR_IMPL_EXT);
            }
        };
        final File[] implFiles = destDir.listFiles(implFilter);
        assertNotNull("impl file should exist in exported connector zip file", implFiles);
        assertEquals("only one impl file should exist in exported connector zip file", implFiles.length, 1);
    }

    private void testClasspathDirExistence(final File destDir, final boolean hasDependencies) {
        final File classpathDir = new File(destDir, ExportConnectorArchiveOperation.CLASSPATH_DIR);
        classpathDir.deleteOnExit();
        assertTrue("classPath folder should be contained in exported connector", classpathDir.exists());
        final File[] depfiles = classpathDir.listFiles();
        assertNotNull("classpath should countain at least one jar file", depfiles);
        if (hasDependencies) {
            assertTrue("classpath should contain at least one dependency", depfiles.length >= 1);
        }
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
        assertNotNull("message property file(s) should exist in exported connector zip file", messageFiles);
    }

    @After
    public void deleteFiles() {
        PlatformUtil.delete(ProjectUtil.getBonitaStudioWorkFolder(), null);
        ProjectUtil.getBonitaStudioWorkFolder().mkdir();
    }

}
