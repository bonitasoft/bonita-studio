/**
 * Copyright (C) 2018 Bonitasoft S.A.
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
package org.bonitasoft.studio.designer.core.operation;

import static com.google.common.base.Preconditions.checkArgument;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.bonitasoft.studio.common.net.HttpClientFactory;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.designer.core.PageDesignerURLFactory;
import org.bonitasoft.studio.designer.core.UIDesignerServerManager;
import org.bonitasoft.studio.designer.i18n.Messages;
import org.bonitasoft.studio.preferences.browser.OpenBrowserOperation;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.osgi.util.NLS;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class CreateUIDArtifactOperation implements IRunnableWithProgress {

    public static final String DEFAULT_PAGE_NAME = "newPage";
    public static final String DEFAULT_FORM_NAME = "newForm";
    public static final String DEFAULT_LAYOUT_NAME = "newLayout";
    public static final String DEFAULT_WIDGET_NAME = "newWidget";
    public static final String DEFAULT_FRAGMENT_NAME = "newFragment";

    public enum ArtifactyType {

        PAGE("page"), FORM("form"), WIDGET("widget"), LAYOUT("layout"), FRAGMENT("fragment");

        private String type;

        private ArtifactyType(String type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return type;
        }
    }

    protected Optional<Map<String, Object>> responseObject = Optional.empty();
    protected PageDesignerURLFactory pageDesignerURLBuilder;
    protected String artifactName = DEFAULT_PAGE_NAME;
    protected RepositoryAccessor repositoryAccessor;
    protected ObjectMapper objectMapper = new ObjectMapper();

    protected CreateUIDArtifactOperation(PageDesignerURLFactory pageDesignerURLBuilder,
            RepositoryAccessor repositoryAccessor) {
        this.repositoryAccessor = repositoryAccessor;
        checkArgument(pageDesignerURLBuilder != null);
        this.pageDesignerURLBuilder = pageDesignerURLBuilder;
    }

    @Override
    public final void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        monitor.beginTask(getTaskName(), IProgressMonitor.UNKNOWN);
        if (!UIDesignerServerManager.getInstance().isStarted()) {
            monitor.subTask(NLS.bind(Messages.waitingForUIDesigner,
                    org.bonitasoft.studio.common.Messages.uiDesignerModuleName));
            UIDesignerServerManager.getInstance().start(repositoryAccessor.getCurrentRepository(),
                    new NullProgressMonitor());
            monitor.subTask("");
        }
        doRun(monitor);
    }

    protected abstract String getTaskName();

    protected abstract void doRun(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException;

    protected RepositoryAccessor getRepositoryAccessor() {
        return repositoryAccessor;
    }

    protected Map<String, Object> doPost(URL url, String entity) throws InvocationTargetException {
        try {
            HttpResponse<InputStream> response = HttpClientFactory.INSTANCE.send(
                    HttpRequest.newBuilder(url.toURI())
                    .header("Content-Type", "application/json")
                    .POST(BodyPublishers.ofString(entity)).build(),
                    BodyHandlers.ofInputStream());
            try (var is = response.body()) {
                return objectMapper.readValue(is, new TypeReference<Map<String, Object>>() {
                });
            }
        } catch (IOException | InterruptedException | URISyntaxException e) {
            throw new InvocationTargetException(e, "Failed to post request.");
        }
    }

    protected Map<String, Object> createBody() {
        Map<String, Object> body = new HashMap<>();
        body.put("type", getArtifactType());
        body.put("name", artifactName);
        if (getArtifactType() != ArtifactyType.WIDGET) {
            body.put("rows", Arrays.asList(new ArrayList<>()));
        }
        return body;
    }

    protected Optional<Map<String, Object>> createArtifact(URL url, Map<String, Object> body)
            throws InvocationTargetException {
        try {
            return Optional.of(doPost(url, objectMapper.writeValueAsString(body)));
        } catch (JsonProcessingException | InvocationTargetException e) {
            throw new InvocationTargetException(e);
        }
    }

    protected void openArtifact(String artifactId) throws InvocationTargetException {
        try {
            switch (getArtifactType()) {
                case FORM:
                case LAYOUT:
                case PAGE:
                    new OpenBrowserOperation(pageDesignerURLBuilder.openPage(artifactId)).execute();
                    break;
                case WIDGET:
                    new OpenBrowserOperation(pageDesignerURLBuilder.openWidget(artifactId)).execute();
                    break;
                case FRAGMENT:
                    new OpenBrowserOperation(pageDesignerURLBuilder.openFragment(artifactId)).execute();
                    break;
                default:
                    break;
            }
        } catch (final MalformedURLException e) {
            throw new InvocationTargetException(e, "Failed to create open artifact URL.");
        }
    }

    public String getNewArtifactId() {
        return (String) responseObject.map(response -> response.get("id"))
                .orElseThrow(() -> new IllegalStateException("Failed to retrieve attribute \'id\' from response."));
    }

    public String getNewPageName() {
        return (String) responseObject.map(response -> response.get("name"))
                .orElseThrow(() -> new IllegalStateException("Failed to retrieve attribute \'name\' from response."));
    }

    public void setArtifactName(String artifactName) {
        this.artifactName = artifactName;
    }

    protected abstract ArtifactyType getArtifactType();
}
