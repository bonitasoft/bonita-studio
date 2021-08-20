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
package org.bonitasoft.studio.tests.preferences;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;

import org.apache.maven.settings.Mirror;
import org.apache.maven.settings.Profile;
import org.apache.maven.settings.Proxy;
import org.apache.maven.settings.Repository;
import org.apache.maven.settings.Server;
import org.apache.maven.settings.Settings;
import org.apache.maven.settings.io.DefaultSettingsReader;
import org.bonitasoft.studio.application.preference.RepositoriesComposite;
import org.bonitasoft.studio.swtbot.framework.application.BotApplicationWorkbenchWindow;
import org.bonitasoft.studio.swtbot.framework.preferences.BotPreferencesDialog;
import org.bonitasoft.studio.swtbot.framework.preferences.maven.BotMavenConfigurationPage;
import org.bonitasoft.studio.swtbot.framework.rule.SWTGefBotRule;
import org.eclipse.m2e.core.MavenPlugin;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;

@RunWith(SWTBotJunit4ClassRunner.class)
public class MavenConfigurationIT {

    private static final String REPOSITORY_ID = "testRepositoryId";
    private static final String REPOSITORY_NAME = "testRepositoryName";
    private static final String REPOSITORY_URL = "testRepositoryUrl";

    private static final String USERNAME = "testUsername";
    private static final String PASSWORD = "testPassword";

    private static final String PROXY_ID = "testProxyId";
    private static final String PROTOCOL = "http";
    private static final String HOST = "testHost";
    private static final String PORT = "8081";
    private static final String NON_PROXY_HOSTS = "testNonProxyHosts";

    private static final String MIRROR_ID = "testMirrorId";
    private static final String MIRROR_NAME = "testMirrorName";
    private static final String MIRROR_URL = "testMirrorURL";
    private static final String MIRROR_Of = "*";

    private final SWTGefBot bot = new SWTGefBot();

    @Rule
    public SWTGefBotRule rule = new SWTGefBotRule(bot);

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    private String defaultUserSettingFile;
    private File testConfigurationFile;
    private DefaultSettingsReader defaultSettingsReader;

    @Before
    public void setUp() throws Exception {
        var mavenConfiguration = MavenPlugin.getMavenConfiguration();
        defaultUserSettingFile = mavenConfiguration.getUserSettingsFile();

        testConfigurationFile = temporaryFolder.newFile("setting.xml");
        mavenConfiguration.setUserSettingsFile(testConfigurationFile.getAbsolutePath());

        defaultSettingsReader = new DefaultSettingsReader();
    }

    @After
    public void clean() throws Exception {
        MavenPlugin.getMavenConfiguration().setUserSettingsFile(defaultUserSettingFile);
    }

    @Test
    public void should_configure_maven_settings() throws Exception {
        BotApplicationWorkbenchWindow workBenchBot = new BotApplicationWorkbenchWindow(bot);
        BotPreferencesDialog botPreferencesDialog = workBenchBot.openPreferences();
        BotMavenConfigurationPage configurationPage = botPreferencesDialog.openMavenConfigurationPage();

        configurationPage.repositories()
                .addRepository(REPOSITORY_ID)
                .setName(REPOSITORY_ID, REPOSITORY_NAME)
                .setUrl(REPOSITORY_ID, REPOSITORY_URL)
                .setReleases(REPOSITORY_ID, true, "always", "fail")
                .setSnapshots(REPOSITORY_ID, false, "never", "ignore");

        configurationPage.servers()
                .createMasterPasswordIfEmpty()
                .addServer(REPOSITORY_ID)
                .selectUserPasswordAuthentication()
                .setUsername(USERNAME)
                .setPassword(PASSWORD)
                .encryptPassword()
                .selectSshAuthentication()
                .setPassphrase(PASSWORD);

        configurationPage.proxies()
                .addProxy(PROXY_ID)
                .setProtocol(PROXY_ID, PROTOCOL)
                .setHost(PROXY_ID, HOST)
                .setPort(PROXY_ID, PORT)
                .setNonProxyHosts(PROXY_ID, NON_PROXY_HOSTS)
                .setUsername(USERNAME)
                .setPassword(PASSWORD)
                .encryptPassword();

        configurationPage.mirrors()
                .addMirror(MIRROR_NAME)
                .setId(MIRROR_NAME, MIRROR_ID)
                .setUrl(MIRROR_NAME, MIRROR_URL)
                .setMirrorOf(MIRROR_NAME, MIRROR_Of);

        configurationPage
                .apply()
                .back()
                .close();

        Settings settings = defaultSettingsReader.read(testConfigurationFile, null);
        validateRepositories(settings);
        validateServers(settings);
        validateProxies(settings);
        validateMirrors(settings);
    }

    private void validateMirrors(Settings settings) {
        assertThat(settings.getMirrors()).hasSize(1);

        Mirror mirror = settings.getMirrors().get(0);
        assertThat(mirror.getId()).isEqualTo(MIRROR_ID);
        assertThat(mirror.getName()).isEqualTo(MIRROR_NAME);
        assertThat(mirror.getUrl()).isEqualTo(MIRROR_URL);
        assertThat(mirror.getMirrorOf()).isEqualTo(MIRROR_Of);
    }

    private void validateProxies(Settings settings) {
        assertThat(settings.getProxies()).hasSize(1);

        Proxy proxy = settings.getProxies().get(0);
        assertThat(proxy.getId()).isEqualTo(PROXY_ID);
        assertThat(proxy.getProtocol()).isEqualTo(PROTOCOL);
        assertThat(proxy.getHost()).isEqualTo(HOST);
        assertThat(proxy.getPort()).isEqualTo(Integer.valueOf(PORT));
        assertThat(proxy.getNonProxyHosts()).isEqualTo(NON_PROXY_HOSTS);
        assertThat(proxy.getUsername()).isEqualTo(USERNAME);
        assertThat(proxy.getPassword()).isNotEmpty();
        assertThat(proxy.getPassword()).isNotEqualTo(PASSWORD);
        assertThat(proxy.getPassword()).startsWith("{"); // Encryption method 'decorate' the encrypted pwd with brackets at the beggining and at the end.
    }

    private void validateServers(Settings settings) {
        assertThat(settings.getServers()).hasSize(1);

        Server server = settings.getServers().get(0);
        assertThat(server.getId()).isEqualTo(REPOSITORY_ID);
        assertThat(server.getUsername()).isEqualTo(USERNAME);
        assertThat(server.getPassword()).isNotEmpty();
        assertThat(server.getPassword()).isNotEqualTo(PASSWORD);
        assertThat(server.getPassword()).startsWith("{"); // Encryption method 'decorate' the encrypted pwd with brackets at the beggining and at the end.
        assertThat(server.getPassphrase()).isEqualTo(PASSWORD);
    }

    private void validateRepositories(Settings settings) {
        assertThat(settings.getProfiles()).hasSize(1);

        Profile bonitaProfile = settings.getProfiles().get(0);
        assertThat(bonitaProfile.getId()).isEqualTo(RepositoriesComposite.BONITA_PROFILE_ID);
        assertThat(bonitaProfile.getRepositories()).hasSize(1);

        Repository repository = bonitaProfile.getRepositories().get(0);

        assertThat(repository.getId()).isEqualTo(REPOSITORY_ID);
        assertThat(repository.getName()).isEqualTo(REPOSITORY_NAME);
        assertThat(repository.getUrl()).isEqualTo(REPOSITORY_URL);

        assertThat(repository.getReleases().isEnabled()).isTrue();
        assertThat(repository.getReleases().getUpdatePolicy()).isEqualTo("always");
        assertThat(repository.getReleases().getChecksumPolicy()).isEqualTo("fail");

        assertThat(repository.getSnapshots().isEnabled()).isFalse();
        assertThat(repository.getSnapshots().getUpdatePolicy()).isEqualTo("never");
        assertThat(repository.getSnapshots().getChecksumPolicy()).isEqualTo("ignore");
    }

}
