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

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.net.HttpClientFactory;
import org.bonitasoft.studio.designer.core.PageDesignerURLFactory;
import org.bonitasoft.studio.designer.core.UIDesignerServerManager;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;

import com.fasterxml.jackson.databind.ObjectMapper;

public class UIDModelValidator implements IValidator<InputStream> {

    private PageDesignerURLFactory pageDesignerURLBuilder;
    private String incompatibleErrorMessage;
    private String migrationNeededMessage;
    private ObjectMapper objectMapper = new ObjectMapper();

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
    public IStatus validate(final InputStream inputStream) {
        if (!UIDesignerServerManager.getInstance().isStarted()) {
            return ValidationStatus.ok();
        }
        try {
            var response = HttpClientFactory.INSTANCE.send(HttpRequest.newBuilder(pageDesignerURLBuilder.artifactStatus().toURI())
                    .timeout(Duration.ofSeconds(10))
                    .header("Content-Type", "application/json")
                    .POST(BodyPublishers.ofInputStream(() -> inputStream)).build(), BodyHandlers.ofInputStream());
            Status status = objectMapper.readValue(response.body(), Status.class);
            if (!status.isCompatible()) {
                return ValidationStatus.error(incompatibleErrorMessage);
            }
            if (status.isMigration()) {
                return ValidationStatus.warning(migrationNeededMessage);
            }
        } catch (IOException | InterruptedException | URISyntaxException e) {
            BonitaStudioLog.error(e);
        }

        return ValidationStatus.ok();
    }

    static IEclipsePreferences getPreferenceStore() {
        return InstanceScope.INSTANCE.getNode(BonitaStudioPreferencesPlugin.PLUGIN_ID);
    }
    
    static class Status {
        
        private boolean compatible;
        private boolean migration;
        
        public boolean isCompatible() {
            return compatible;
        }
        
        public void setCompatible(boolean compatible) {
            this.compatible = compatible;
        }
        
        public boolean isMigration() {
            return migration;
        }
        
        public void setMigration(boolean migration) {
            this.migration = migration;
        }
        
    }

}
