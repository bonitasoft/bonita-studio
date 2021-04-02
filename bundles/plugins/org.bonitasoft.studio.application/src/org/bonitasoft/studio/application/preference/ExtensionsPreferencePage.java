/**
 * Copyright (C) 2021 Bonitasoft S.A.
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
package org.bonitasoft.studio.application.preference;

import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;

import org.apache.maven.cli.configuration.SettingsXmlConfigurationProcessor;
import org.apache.maven.settings.Settings;
import org.apache.maven.settings.io.DefaultSettingsReader;
import org.apache.maven.settings.io.SettingsParseException;
import org.apache.maven.settings.io.jdom.SettingsJDOMWriter;
import org.apache.maven.settings.io.xpp3.SettingsXpp3Writer;
import org.bonitasoft.studio.application.ApplicationPlugin;
import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.ui.widget.NativeTabFolderWidget;
import org.bonitasoft.studio.ui.widget.NativeTabItemWidget;
import org.codehaus.plexus.util.WriterFactory;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.m2e.core.MavenPlugin;
import org.eclipse.m2e.core.embedder.IMavenConfiguration;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.Format.TextMode;

public class ExtensionsPreferencePage extends PreferencePage implements
        IWorkbenchPreferencePage {

    private File userSettingsFile;
    private Settings settings;

    private NativeTabItemWidget repositoriesTabItem;
    private NativeTabItemWidget serversTabItem;
    private NativeTabItemWidget proxiesTabItem;
    private NativeTabItemWidget mirrorsTabItem;

    private DefaultSettingsReader defaultSettingsReader = new DefaultSettingsReader();
    private SettingsJDOMWriter settingsJDOMWriter = new SettingsJDOMWriter();

    @Override
    public void init(IWorkbench workbench) {
        try {
            IMavenConfiguration mavenConfiguration = MavenPlugin.getMavenConfiguration();
            userSettingsFile = mavenConfiguration.getUserSettingsFile() != null
                    ? new File(mavenConfiguration.getUserSettingsFile())
                    : SettingsXmlConfigurationProcessor.DEFAULT_USER_SETTINGS_FILE;
            if (!userSettingsFile.exists()) {
                try {
                    userSettingsFile.createNewFile();
                } catch (IOException e) {
                    throw new RuntimeException(
                            String.format("An error occured while creating '%s'.", userSettingsFile.toString()),
                            e);
                }
            } else {
                settings = defaultSettingsReader.read(userSettingsFile, null);
            }
        } catch (EOFException | SettingsParseException e) {
            settings = new Settings();
            BonitaStudioLog.warning(String.format(
                    "File '%s' is empty / unreadable -> An empty configuration will be created in memory, and saved if the user apply changes.",
                    userSettingsFile.toString()), ApplicationPlugin.PLUGIN_ID);
        } catch (IOException e) {
            settings = new Settings();
            MessageDialog.openError(getShell(), Messages.error, e.getMessage());
            BonitaStudioLog.error(e);
        }
    }

    @Override
    protected Control createContents(Composite parent) {
        var mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().create());

        NativeTabFolderWidget tabFolder = new NativeTabFolderWidget.Builder().createIn(mainComposite);
        tabFolder.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        createRepositoriesTabItem(tabFolder);
        createServersTabItem(tabFolder);
        createProxiesTabItem(tabFolder);
        createMirrorsTabItem(tabFolder);

        tabFolder.getItems().addAll(Arrays.asList(repositoriesTabItem, serversTabItem, proxiesTabItem, mirrorsTabItem));
        tabFolder.setSelection(repositoriesTabItem);

        return mainComposite;
    }

    private void createRepositoriesTabItem(NativeTabFolderWidget parent) {
        repositoriesTabItem = new NativeTabItemWidget.Builder()
                .withText(Messages.repositories)
                .withControl(new RepositoriesComposite(parent.getTabFolder(), settings))
                .createIn(parent);
    }

    private void createServersTabItem(NativeTabFolderWidget parent) {
        serversTabItem = new NativeTabItemWidget.Builder()
                .withText(Messages.servers)
                .withControl(new ServersComposite(parent.getTabFolder(), settings))
                .createIn(parent);
    }

    private void createProxiesTabItem(NativeTabFolderWidget parent) {
        proxiesTabItem = new NativeTabItemWidget.Builder()
                .withText(Messages.proxies)
                .withControl(createProxiesComposite(parent.getTabFolder()))
                .createIn(parent);
    }

    private Control createProxiesComposite(Composite parent) {
        var proxiesComposite = new Composite(parent, SWT.NONE);
        proxiesComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        proxiesComposite.setLayout(GridLayoutFactory.fillDefaults().create());

        return proxiesComposite;
    }

    private void createMirrorsTabItem(NativeTabFolderWidget parent) {
        mirrorsTabItem = new NativeTabItemWidget.Builder()
                .withText(Messages.mirrors)
                .withControl(createMirrorsComposite(parent.getTabFolder()))
                .createIn(parent);
    }

    private Control createMirrorsComposite(Composite parent) {
        var mirrorsComposite = new Composite(parent, SWT.NONE);
        mirrorsComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        mirrorsComposite.setLayout(GridLayoutFactory.fillDefaults().create());

        return mirrorsComposite;
    }

    @Override
    public boolean performOk() {
        try {
            SAXBuilder builder = new SAXBuilder();
            builder.setIgnoringBoundaryWhitespace(false);
            builder.setIgnoringElementContentWhitespace(false);

            Document doc = null;
            try {
                doc = builder.build(userSettingsFile);
            } catch (JDOMException e) {
                BonitaStudioLog.warning(String.format(
                        "File '%s' is empty / unreadable -> Content will be overwritten by in memory configuration.",
                        userSettingsFile.toString()), ApplicationPlugin.PLUGIN_ID);
            }
            String encoding = settings.getModelEncoding();
            if (encoding == null) {
                encoding = "UTF-8";
            }

            Format format = Format.getRawFormat().setEncoding(encoding).setTextMode(TextMode.PRESERVE);

            try (Writer writer = WriterFactory.newWriter(userSettingsFile, encoding)) {
                if (doc != null) {
                    settingsJDOMWriter.write(settings, doc, writer, format);
                } else {
                    new SettingsXpp3Writer().write(writer, settings);
                }
            }

        } catch (IOException e) {
            MessageDialog.openError(getShell(), Messages.error, e.getMessage());
            BonitaStudioLog.error(e);
        }
        return true;
    }

}
