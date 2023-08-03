/**
 * Copyright (C) 2023 BonitaSoft S.A.
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
package org.bonitasoft.studio.application.maven.wizard;

import java.util.Objects;

import org.apache.maven.settings.Profile;
import org.apache.maven.settings.Proxy;
import org.apache.maven.settings.Repository;
import org.apache.maven.settings.Settings;
import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.application.maven.BonitaMavenConfigurationManager;
import org.bonitasoft.studio.application.maven.MavenSettingsIO;
import org.bonitasoft.studio.common.Strings;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.ui.PlatformUtil;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.ui.dialog.MultiStatusDialog;
import org.bonitasoft.studio.ui.wizard.WizardPageBuilder;
import org.eclipse.core.databinding.beans.typed.PojoProperties;
import org.eclipse.core.internal.net.ProxyData;
import org.eclipse.core.internal.net.ProxyManager;
import org.eclipse.core.net.proxy.IProxyData;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.Wizard;

public class MavenConfigurationWizard extends Wizard {

    private Settings userSettings;
    private MavenConfiguration mavenConfiguration;

    public MavenConfigurationWizard(Settings settings) {
        super();
        setDefaultPageImageDescriptor(Pics.getWizban());
        setWindowTitle(Messages.wizardConnectionMainTitle);
        this.userSettings = settings;
        mavenConfiguration = MavenConfiguration.create(userSettings, !PlatformUtil.isACommunityBonitaProduct());
    }
    
    @Override
    public void addPages() {
        var enableMirrorObservable = PojoProperties.value("enableMirror", Boolean.class).observe(mavenConfiguration);
        addPage(WizardPageBuilder.newPage()
                .withTitle(Messages.wizardConnectionStartTitle)
                .withDescription(Messages.wizardConnectionStartDescription)
                .withControl(new StartConfigurationPageControl())
                .asPage());
        addPage(WizardPageBuilder.newPage()
                .withTitle(Messages.wizardConnectionProxyTitle)
                .withDescription(Messages.wizardConnectionProxyDescription)
                .withControl(new ProxyConfigurationPageControl(mavenConfiguration))
                .asPage());
        addPage(WizardPageBuilder.newPage()
                .withTitle(Messages.wizardConnectionMirrorTitle)
                .withDescription(Messages.wizardConnectionMirrorDescription)
                .withControl(new MirrorConfigurationPageControl(mavenConfiguration.getMirror(), enableMirrorObservable))
                .asPage());
        if (mavenConfiguration.getBarServer() != null) {
            addPage(WizardPageBuilder.newPage()
                    .withTitle(Messages.wizardConnectionBARTitle)
                    .withDescription(Messages.wizardConnectionBARDescription)
                    .withControl(new BARConfigurationPageControl(mavenConfiguration.getBarServer(), enableMirrorObservable))
                    .asPage());
        }
    }

    @Override
    public boolean performFinish() {
        if (mavenConfiguration.isEnableProxy()) {
            Proxy proxy = mavenConfiguration.getProxy();
            if(userSettings.getActiveProxy() == null) {
                userSettings.addProxy(proxy);
            }
            configureProxyManager(proxy);
        }
        if (mavenConfiguration.isEnableMirror()) {
            userSettings.addMirror(mavenConfiguration.getMirror());
        }
        if (mavenConfiguration.getBarServer() != null 
                && Strings.hasText(mavenConfiguration.getBarServer().getUsername())
                && Strings.hasText(mavenConfiguration.getBarServer().getPassword())) {
            userSettings.getServers()
                    .removeIf(s -> Objects.equals(BonitaMavenConfigurationManager.MAVEN_ID_BAR_SERVER, s.getId()));
            userSettings.addServer(mavenConfiguration.getBarServer());
            var profile = userSettings.getProfiles().stream()
                    .filter(p -> p.getId().equals(BonitaMavenConfigurationManager.BONITA_PROFILE_ID))
                    .findAny()
                    .orElseGet(() -> {
                        var newProfile = new Profile();
                        newProfile.setId(BonitaMavenConfigurationManager.BONITA_PROFILE_ID);
                        userSettings.addProfile(newProfile);
                        return newProfile;
                    });

            var bar = MavenConfiguration.createBonitaArtifactRepository();
            if (profile.getRepositories().stream().map(Repository::getId)
                    .noneMatch(BonitaMavenConfigurationManager.MAVEN_ID_BAR_SERVER::equals)) {
                profile.addRepository(bar);
            }
            if (profile.getPluginRepositories().stream().map(Repository::getId)
                    .noneMatch(BonitaMavenConfigurationManager.MAVEN_ID_BAR_SERVER::equals)) {
                profile.addPluginRepository(bar);
            }
            
            // Add Bonita profile to active profiles if not
            if (userSettings.getActiveProfiles().stream()
                    .noneMatch(BonitaMavenConfigurationManager.BONITA_PROFILE_ID::equals)) {
                userSettings.addActiveProfile(BonitaMavenConfigurationManager.BONITA_PROFILE_ID);
            }
        }

        boolean finish = true;
        if(mavenConfiguration.isEnableMirror() 
                || mavenConfiguration.isEnableProxy() 
                || mavenConfiguration.getBarServer() != null) {
            finish = saveUserSettings(userSettings);
        }

        if(finish) {
            BonitaMavenConfigurationManager.testConnection(true);
        }

        return finish;
    }

    /**
     * Applies the proxy configuration as the default proxy configuration for the Studio
     * @param proxy
     */
    private void configureProxyManager(Proxy proxy) {
        try {
            var data = new ProxyData(proxy.getProtocol().toUpperCase(), 
                    proxy.getHost(), 
                    proxy.getPort(), 
                    proxy.getUsername() != null, 
                    null);
            if(Strings.hasText(proxy.getUsername())) {
                data.setUserid(proxy.getUsername());
            }
            if(Strings.hasText(proxy.getPassword())) {
                data.setPassword(proxy.getPassword());
            }
            var proxyManager = ProxyManager.getProxyManager();
            proxyManager.setProxyData(new IProxyData[] {data });
            String nonProxyHosts = proxy.getNonProxyHosts();
            if(Strings.hasText(nonProxyHosts)) {
                if(nonProxyHosts.contains("|")) {
                    proxyManager.setNonProxiedHosts(nonProxyHosts.split("\\|"));
                }else if(nonProxyHosts.contains(",")) {
                    proxyManager.setNonProxiedHosts(nonProxyHosts.split(","));
                }else {
                    proxyManager.setNonProxiedHosts(new String[] {nonProxyHosts});
                }
            }
        } catch (CoreException e) {
           BonitaStudioLog.error(e);
        }
    }

    private boolean saveUserSettings(Settings userSettings) {
        MultiStatus status = MavenSettingsIO.validate(userSettings);
        if (!status.isOK()) {
            new MultiStatusDialog(getShell(), Messages.invalidMavenConfigurationTitle,
                    String.format(Messages.invalidMavenConfiguration, MavenSettingsIO.getUserSettingsFile()),
                    new String[] { IDialogConstants.OK_LABEL }, status).open();
            return false;
        }
        try {
            MavenSettingsIO.writePreservingFormat(userSettings);
        } catch (CoreException e) {
            MessageDialog.openError(getShell(), Messages.error, e.getMessage());
            BonitaStudioLog.error(e);
            return false;
        }
        return true;
    }

}
