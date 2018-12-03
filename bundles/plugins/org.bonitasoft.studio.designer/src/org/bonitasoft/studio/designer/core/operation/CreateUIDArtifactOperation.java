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
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.designer.core.PageDesignerURLFactory;
import org.bonitasoft.studio.preferences.browser.OpenBrowserOperation;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

public abstract class CreateUIDArtifactOperation implements IRunnableWithProgress {

    public static final String DEFAULT_PAGE_NAME = "newPage";
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

    protected Optional<JSONObject> responseObject = Optional.empty();
    protected PageDesignerURLFactory pageDesignerURLBuilder;
    protected String artifactName = DEFAULT_PAGE_NAME;
    protected RepositoryAccessor repositoryAccessor;

    public CreateUIDArtifactOperation(PageDesignerURLFactory pageDesignerURLBuilder, RepositoryAccessor repositoryAccessor) {
        this.repositoryAccessor = repositoryAccessor;
        checkArgument(pageDesignerURLBuilder != null);
        this.pageDesignerURLBuilder = pageDesignerURLBuilder;
    }

    protected String doPost(URL url, Representation entity) throws InvocationTargetException {
        try {
            return new ClientResource(url.toURI()).post(entity).getText();
        } catch (final ResourceException e) {
            throw new InvocationTargetException(e, "Failed to post request.");
        } catch (final IOException e) {
            throw new InvocationTargetException(e, "Failed to retrieve POST response.");
        } catch (final URISyntaxException e) {
            throw new InvocationTargetException(e, "Failed to build POST URL.");
        }
    }

    protected JSONObject createBody() throws InvocationTargetException {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("type", getArtifactType());
            jsonObject.put("name", artifactName);
            if (getArtifactType() != ArtifactyType.WIDGET) {
                jsonObject.put("rows", Arrays.asList(new ArrayList<>()));
            }
            return jsonObject;
        } catch (JSONException e) {
            throw new InvocationTargetException(e, "An error occured while creating JSON body for the request");
        }
    }

    protected Optional<JSONObject> createArtifact(URL url, Representation body) throws InvocationTargetException {
        try {
            String response = doPost(url, body);
            return Optional.of(new JSONObject(response));
        } catch (final JSONException e) {
            throw new InvocationTargetException(e, "Failed to build a JSON representation of the response.");
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
        if (responseObject.isPresent()) {
            try {
                return responseObject.get().getString("id");
            } catch (final JSONException e) {
                throw new RuntimeException("Failed to retrieve attribute \'id\' from JsonObject", e);
            }
        }
        throw new IllegalStateException("Response is null.");
    }

    public String getNewPageName() {
        if (responseObject.isPresent()) {
            try {
                return responseObject.get().getString("name");
            } catch (final JSONException e) {
                throw new RuntimeException("Failed to retrieve attribute \'name\' from JsonObject", e);
            }
        }
        throw new IllegalStateException("Response is null.");
    }

    public void setArtifactName(String artifactName) {
        this.artifactName = artifactName;
    }

    protected abstract ArtifactyType getArtifactType();
}
