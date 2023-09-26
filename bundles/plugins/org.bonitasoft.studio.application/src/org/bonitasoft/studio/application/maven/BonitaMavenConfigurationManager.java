/**
 * Copyright (C) 2022 BonitaSoft S.A.
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
package org.bonitasoft.studio.application.maven;

import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Base64;
import java.util.Map;

import org.apache.maven.settings.Repository;
import org.bonitasoft.studio.application.maven.dialog.TestMavenConnectionMessageDialog;
import org.bonitasoft.studio.application.maven.handler.TestMavenRepositoriesConnectionHandler;
import org.bonitasoft.studio.application.maven.security.MavenPasswordManager;
import org.bonitasoft.studio.application.maven.wizard.MavenConfigurationWizard;
import org.bonitasoft.studio.common.CommandExecutor;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.core.maven.DependencyGetOperation;
import org.bonitasoft.studio.common.repository.core.maven.migration.model.DependencyLookup.Status;
import org.bonitasoft.studio.common.repository.core.maven.model.GAV;
import org.bonitasoft.studio.common.ui.PlatformUtil;
import org.bonitasoft.studio.preferences.dialog.BonitaPreferenceDialog;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.m2e.core.MavenPlugin;
import org.eclipse.m2e.core.repository.IRepository;
import org.eclipse.m2e.core.repository.IRepositoryRegistry;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

public class BonitaMavenConfigurationManager {

    public static final String BONITA_PROFILE_ID = "Bonita";
    public static final String MAVEN_ID_BAR_SERVER = "bonitasoft-releases";
    public static final String MAVEN_BAR_REPO_NAME = "Bonitasoft Releases";
    public static final String MAVEN_URL_BAR_SERVER = "https://bonitasoft.jfrog.io/artifactory/maven";
    public static final String SKIP_MAVEN_CONFIGURATION_CKECK_PREFERENCE = "SKIP_MAVEN_CONFIGURATION_CKECK";

    private static final String ARTIFACTORY_PING_URL = "https://bonitasoft.jfrog.io/artifactory/api/system/ping";
    private static final String TEST_CONNECTION_COMMAND_ID = "org.bonitasoft.studio.application.maven.testConnection.command";

    public static void check(IProgressMonitor monitor)
            throws InvocationTargetException, InterruptedException {
        var result = checkRemoteAccess(monitor);
        openMavenConfigurationStatusDialog(result, monitor);
    }

    public static void openMavenConfigurationStatusDialog(Result result, IProgressMonitor monitor) {
        if (!result.hasRemoteAccess()) {
            Display.getDefault().syncExec(() -> {
                int buttonId = new TestMavenConnectionMessageDialog(
                        PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
                        result).open();
                if (buttonId == TestMavenConnectionMessageDialog.CONFIGURE_ID) {
                    if (hasExistingConfigurations()) {
                        BonitaPreferenceDialog dialog = new BonitaPreferenceDialog(
                                new Shell(Display.getDefault()));
                        dialog.create();
                        dialog.setSelectedPreferencePage(BonitaPreferenceDialog.MAVEN_PAGE_ID);
                        dialog.open();
                        testConnection(true);
                    } else {
                        new WizardDialog(
                                PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
                                new MavenConfigurationWizard(MavenSettingsIO.read())).open();
                    }
                } else if (buttonId == IDialogConstants.RETRY_ID) {
                    testConnection(true);
                }
            });
        }
    }

    public static void testConnection(boolean showResult) {
        new CommandExecutor().executeCommand(TEST_CONNECTION_COMMAND_ID,
                Map.of(TestMavenRepositoriesConnectionHandler.SHOW_RESULT_PARAM, String.valueOf(showResult)));
    }

    public static Result checkRemoteAccess(IProgressMonitor monitor)
            throws InvocationTargetException, InterruptedException {
        var result = new Result();
        if (!isMavenCentralAccessAvailable(monitor)) {
            result.setRemoteAccess(false);
            result.setCanReachMavenCentral(false);
        }
        if (!PlatformUtil.isACommunityBonitaProduct()
                && !isBARAccessAvailable(monitor)) {
            if (hasExistingConfigurations()) {
                try {
                    var server = MavenPlugin.getMaven().getSettings().getServer(MAVEN_ID_BAR_SERVER);
                    if (server != null) {
                        var response = testDirectConnection();
                        result.setInvalidBarCredentials(response.statusCode() == 403 || response.statusCode() == 401);
                        if (response.statusCode() != 200) {
                            BonitaStudioLog.warning(
                                    String.format("Bonita Artifact Repository connection test failed: %s",
                                            response.body()),
                                    BonitaMavenConfigurationManager.class);
                        }
                    } else {
                        result.setMissingBarCredentials(true);
                    }
                } catch (CoreException e) {
                    throw new InvocationTargetException(e);
                }
            }
            result.setRemoteAccess(false);
            result.setCanReachBonitaArtifactRepository(false);
        }
        return result;
    }

    private static HttpResponse<String> testDirectConnection() throws CoreException {
        var settings = MavenPlugin.getMaven().getSettings();
        try {
            var server = settings.getServer(MAVEN_ID_BAR_SERVER);
            var pwdManager = new MavenPasswordManager();
            var client = HttpClient.newBuilder()
                    .build();
            return client.send(HttpRequest.newBuilder()
                    .GET()
                    .uri(new URI(ARTIFACTORY_PING_URL))
                    .header("Authorization", basicAuth(server.getUsername(), pwdManager.decryptPassword(server.getPassword())))
                    .build(), BodyHandlers.ofString());
        } catch (Exception e) {
            throw new CoreException(
                    org.eclipse.core.runtime.Status.error(String.format("Failed to request %s", MAVEN_URL_BAR_SERVER), e));
        }
    }
    
    private static String basicAuth(String username, String password) {
        return "Basic " + Base64.getEncoder().encodeToString((username + ":" + password).getBytes());
    }

    private static boolean hasExistingConfigurations() {
        try {
            var settings = MavenPlugin.getMaven().getSettings();
            // Check if settings contains a Bonita profile with the bonitasoft-releases configured
            return settings.getProfiles().stream()
                    .filter(p -> p.getId().equals(BONITA_PROFILE_ID))
                    .findFirst()
                    .filter(bonitaProfile -> bonitaProfile.getRepositories().stream().map(Repository::getId)
                            .anyMatch(MAVEN_ID_BAR_SERVER::equals))
                    .isPresent();
        } catch (CoreException e) {
            return false;
        }
    }

    private static boolean isMavenCentralAccessAvailable(IProgressMonitor monitor)
            throws InvocationTargetException, InterruptedException {
        // Use an arbitrary artifact (small) to test maven central access
        var op = new DependencyGetOperation(
                new GAV("org.bonitasoft.engine", "bonita-engine", "7.12.1", null, "pom", null));
        MavenPlugin.getRepositoryRegistry().getRepositories(IRepositoryRegistry.SCOPE_SETTINGS).stream()
                .map(IRepository::getUrl)
                .forEach(op::addRemoteRespository);
        op.run(monitor);

        return op.getResult() != null
                && op.getResult().getStatus() == Status.FOUND;
    }

    public static boolean isBARAccessAvailable(IProgressMonitor monitor)
            throws InvocationTargetException, InterruptedException {
        // Testing Bonita artifact repository
        var op = new DependencyGetOperation(
                new GAV("com.bonitasoft", "bonita-test-toolkit", "1.0.2", null, "pom", null));
        MavenPlugin.getRepositoryRegistry().getRepositories(IRepositoryRegistry.SCOPE_SETTINGS).stream()
                .map(IRepository::getUrl)
                .forEach(op::addRemoteRespository);
        op.run(monitor);

        return op.getResult() != null
                && op.getResult().getStatus() == Status.FOUND;
    }

    public static class Result {

        private boolean mavenCentral = true;
        private boolean bonitaArtifactRepository = true;
        private boolean remoteAccess = true;
        private boolean invalidBarCredentials = false;
        private boolean missingBarCredentials = false;

        public void setRemoteAccess(boolean remoteAccess) {
            this.remoteAccess = remoteAccess;
        }

        public boolean hasRemoteAccess() {
            return remoteAccess;
        }

        public boolean canReachMavenCentral() {
            return mavenCentral;
        }

        public void setCanReachMavenCentral(boolean canReachMavenCentral) {
            this.mavenCentral = canReachMavenCentral;
        }

        public boolean canReachBonitaArtifactRepository() {
            return bonitaArtifactRepository;
        }

        public void setCanReachBonitaArtifactRepository(boolean canReachBonitaArtifactRepository) {
            this.bonitaArtifactRepository = canReachBonitaArtifactRepository;
        }

        public boolean hasInvalidBarCredentials() {
            return invalidBarCredentials;
        }

        public void setInvalidBarCredentials(boolean invalidBarCredentials) {
            this.invalidBarCredentials = invalidBarCredentials;
        }

        public boolean isMissingBarCredentials() {
            return missingBarCredentials;
        }

        public void setMissingBarCredentials(boolean missingBarCredentials) {
            this.missingBarCredentials = missingBarCredentials;
        }

    }
}
