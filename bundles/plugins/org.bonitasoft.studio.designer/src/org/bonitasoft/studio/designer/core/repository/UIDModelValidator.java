/**
 * Copyright (C) 2019 Bonitasoft S.A.
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
package org.bonitasoft.studio.designer.core.repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.designer.core.PageDesignerURLFactory;
import org.bonitasoft.studio.designer.core.UIDesignerServerManager;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

public class UIDModelValidator implements IValidator<InputStream> {

    private PageDesignerURLFactory pageDesignerURLBuilder;
    private String incompatibleErrorMessage;
    private String migrationNeededMessage;

    public UIDModelValidator(PageDesignerURLFactory pageDesignerURLFactory, 
            String incompatibleErrorMessage,
            String migrationNeededMessage) {
        pageDesignerURLBuilder = pageDesignerURLFactory;
        this.incompatibleErrorMessage = incompatibleErrorMessage;
        this.migrationNeededMessage = migrationNeededMessage;
    }

    public UIDModelValidator(String incompatibleErrorMessage, String migrationNeededMessage) {
        this(new PageDesignerURLFactory(getPreferenceStore()), incompatibleErrorMessage, migrationNeededMessage);
    }

    @Override
    public IStatus validate(InputStream inputStream) {
        if(!UIDesignerServerManager.getInstance().isStarted()) {
            return ValidationStatus.ok();
        }
        String artifactContent = new BufferedReader(
                new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                        .lines()
                        .collect(Collectors.joining(System.lineSeparator()));
        try {
            Representation response = new ClientResource(pageDesignerURLBuilder.artifactStatus().toURI())
                    .post(artifactContent);
            String repsonseBody = new BufferedReader(
                    new InputStreamReader(response.getStream(), StandardCharsets.UTF_8))
                            .lines()
                            .collect(Collectors.joining(System.lineSeparator()));
            JSONObject status = new JSONObject(repsonseBody);
            if (!status.getBoolean("compatible")) {
                return ValidationStatus.error(incompatibleErrorMessage);
            }
            if (status.getBoolean("migration")) {
                return ValidationStatus.warning(migrationNeededMessage);
            }
        } catch (ResourceException | URISyntaxException | IOException | JSONException e) {
            BonitaStudioLog.error(e);
        }
        return ValidationStatus.ok();
    }

    static IEclipsePreferences getPreferenceStore() {
        return InstanceScope.INSTANCE.getNode(BonitaStudioPreferencesPlugin.PLUGIN_ID);
    }

}
