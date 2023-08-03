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

import java.util.Optional;

import org.apache.maven.settings.Mirror;
import org.apache.maven.settings.Profile;
import org.apache.maven.settings.Proxy;
import org.apache.maven.settings.Repository;
import org.apache.maven.settings.RepositoryPolicy;
import org.apache.maven.settings.Server;
import org.apache.maven.settings.Settings;
import org.bonitasoft.studio.application.maven.BonitaMavenConfigurationManager;

public class MavenConfiguration {

    private Proxy proxy = new Proxy();
    private Mirror mirror = new Mirror();
    private Server barServer;
    private Profile bonitaProfile;
    private boolean enableProxy = false;
    private boolean enableMirror = false;

    public MavenConfiguration() {
        proxy.setActive(true);
        proxy.setNonProxyHosts("localhost|127.0.0.1");
    }

    public Proxy getProxy() {
        return proxy;
    }

    public void setProxy(Proxy proxy) {
        this.proxy = proxy;
    }

    public Mirror getMirror() {
        return mirror;
    }

    public void setMirror(Mirror mirror) {
        this.mirror = mirror;
    }

    public Server getBarServer() {
        return barServer;
    }

    public void setBarServer(Server barServer) {
        this.barServer = barServer;
    }

    public Profile getBonitaProfile() {
        return bonitaProfile;
    }

    public void setBonitaProfile(Profile bonitaProfile) {
        this.bonitaProfile = bonitaProfile;
    }

    public boolean isEnableProxy() {
        return enableProxy;
    }

    public void setEnableProxy(boolean enableProxy) {
        this.enableProxy = enableProxy;
    }

    public boolean isEnableMirror() {
        return enableMirror;
    }

    public void setEnableMirror(boolean enableMirror) {
        this.enableMirror = enableMirror;
    }

    public static MavenConfiguration create(Settings settings, boolean addBar) {
        var mavenConfiguration = new MavenConfiguration();
        var activeProxy = settings.getActiveProxy();
        if (activeProxy != null) {
            mavenConfiguration.setProxy(activeProxy);
            mavenConfiguration.setEnableProxy(true);
        }
        var mirrors = settings.getMirrors();
        if (!mirrors.isEmpty()) {
            Mirror mirror = mirrors.get(0);
            mavenConfiguration.setMirror(mirror);
            mavenConfiguration.setEnableMirror(true);
        }
        if (addBar) {
            var barServer = Optional.ofNullable(settings.getServer(BonitaMavenConfigurationManager.MAVEN_ID_BAR_SERVER))
                    .orElseGet(() -> {
                        var server = new Server();
                        server.setId(BonitaMavenConfigurationManager.MAVEN_ID_BAR_SERVER);
                        return server;
                    });
            mavenConfiguration.setBarServer(barServer);
        }
        return mavenConfiguration;
    }

    public static Repository createBonitaArtifactRepository() {
        var rep = new Repository();
        rep.setId(BonitaMavenConfigurationManager.MAVEN_ID_BAR_SERVER);
        rep.setName(BonitaMavenConfigurationManager.MAVEN_BAR_REPO_NAME);
        rep.setUrl(BonitaMavenConfigurationManager.MAVEN_URL_BAR_SERVER);
        var disabledPolicy = new RepositoryPolicy();
        disabledPolicy.setEnabled(false);
        rep.setSnapshots(disabledPolicy);
        var enabledPolicy = new RepositoryPolicy();
        enabledPolicy.setEnabled(true);
        rep.setReleases(enabledPolicy);
        return rep;
    }

}
