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
package org.bonitasoft.studio.application.maven.preference;

import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

import org.apache.maven.settings.Settings;
import org.apache.maven.settings.io.DefaultSettingsReader;
import org.apache.maven.settings.io.SettingsParseException;
import org.bonitasoft.studio.application.ApplicationPlugin;
import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.application.maven.MavenSettingsIO;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.ui.dialog.MultiStatusDialog;
import org.bonitasoft.studio.ui.widget.NativeTabFolderWidget;
import org.bonitasoft.studio.ui.widget.NativeTabItemWidget;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.PlatformUI;

public class MavenPreferencePage extends PreferencePage implements
        IWorkbenchPreferencePage {

    private IObservableValue<Settings> settingsObservable = new WritableValue<>();

    private NativeTabItemWidget repositoriesTabItem;
    private NativeTabItemWidget serversTabItem;
    private NativeTabItemWidget proxiesTabItem;
    private NativeTabItemWidget mirrorsTabItem;

    private DefaultSettingsReader defaultSettingsReader = new DefaultSettingsReader();

    private IObservableValue<String> masterPwdObservable = new WritableValue<>();

    private File userSettingsFile;

    @Override
    public void init(IWorkbench workbench) {
        userSettingsFile = MavenSettingsIO.getUserSettingsFile(); 
        settingsObservable.setValue(MavenSettingsIO.read());
    }

    @Override
    protected Control createContents(Composite parent) {
        var mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().create());

        NativeTabFolderWidget tabFolder = new NativeTabFolderWidget.Builder().createIn(mainComposite);
        tabFolder.setLayoutData(GridDataFactory.fillDefaults().hint(500, 510).grab(true, true).create());

        createRepositoriesTabItem(tabFolder);
        createServersTabItem(tabFolder);
        createProxiesTabItem(tabFolder);
        createMirrorsTabItem(tabFolder);

        tabFolder.getItems().addAll(Arrays.asList(repositoriesTabItem, serversTabItem, proxiesTabItem, mirrorsTabItem));
        tabFolder.setSelection(repositoriesTabItem);

        return mainComposite;
    }

    private void createRepositoriesTabItem(NativeTabFolderWidget parent) {
        var repositoriesComposite = new RepositoriesComposite(parent.getTabFolder(), settingsObservable);
        repositoriesTabItem = new NativeTabItemWidget.Builder()
                .withText(Messages.repositories)
                .withControl(repositoriesComposite)
                .createIn(parent);
    }

    private void createServersTabItem(NativeTabFolderWidget parent) {
        var serversComposite = new ServersComposite(parent.getTabFolder(), settingsObservable, masterPwdObservable);
        serversTabItem = new NativeTabItemWidget.Builder()
                .withText(Messages.servers)
                .withControl(serversComposite)
                .createIn(parent);
    }

    private void createProxiesTabItem(NativeTabFolderWidget parent) {
        var proxiesComposite = new ProxiesComposite(parent.getTabFolder(), settingsObservable, masterPwdObservable);
        proxiesTabItem = new NativeTabItemWidget.Builder()
                .withText(Messages.proxies)
                .withControl(proxiesComposite)
                .createIn(parent);
    }

    private void createMirrorsTabItem(NativeTabFolderWidget parent) {
        var mirrorsComposite = new MirrorsComposite(parent.getTabFolder(), settingsObservable);
        mirrorsTabItem = new NativeTabItemWidget.Builder()
                .withText(Messages.mirrors)
                .withControl(mirrorsComposite)
                .createIn(parent);
    }

    @Override
    public boolean performOk() {
        var currentSettingsFile = MavenSettingsIO.getUserSettingsFile();
        if(!Objects.equals(userSettingsFile, currentSettingsFile)) {
            // Eclipse preference dialog
            return true;
        }
        MultiStatus status = MavenSettingsIO.validate(settingsObservable.getValue());
        var shellProvider = PlatformUI.getWorkbench().getModalDialogShellProvider();
        if (!status.isOK()) {
            new MultiStatusDialog(shellProvider.getShell(), Messages.invalidMavenConfigurationTitle,
                    String.format(Messages.invalidMavenConfiguration, userSettingsFile.getPath()),
                    new String[] { IDialogConstants.OK_LABEL }, status).open();
            return false;
        }
        try {
            MavenSettingsIO.writePreservingFormat(settingsObservable.getValue());
        } catch (CoreException e) {
            MessageDialog.openError(shellProvider.getShell(), Messages.error, e.getMessage());
            BonitaStudioLog.error(e);
            return false;
        }
        MessageDialog.openInformation(shellProvider.getShell(), Messages.mavenConfigurationUpdatedTitle,
                String.format(Messages.mavenConfigurationUpdated, userSettingsFile.getPath()));
        return true;
    }

    @Override
    protected void performDefaults() {
        if (MessageDialog.openConfirm(getShell(), Messages.restoreDefaultConfirmationTitle,
                Messages.restoreDefaultConfirmation)) {
            try {
                settingsObservable.setValue(defaultSettingsReader.read(userSettingsFile, null));
            } catch (EOFException | SettingsParseException e) {
                settingsObservable.setValue(new Settings());
                BonitaStudioLog.warning(String.format(
                        "File '%s' is empty / unreadable -> An empty configuration will be created in memory, and saved if the user apply changes.",
                        userSettingsFile.toString()), ApplicationPlugin.PLUGIN_ID);
            } catch (IOException e) {
                settingsObservable.setValue(new Settings());
                MessageDialog.openError(getShell(), Messages.error, e.getMessage());
                BonitaStudioLog.error(e);
            }
            super.performDefaults();
        }
    }

}
