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
package org.bonitasoft.studio.application.maven;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.util.Optional;

import org.apache.maven.cli.configuration.SettingsXmlConfigurationProcessor;
import org.apache.maven.settings.Settings;
import org.apache.maven.settings.io.jdom.SettingsJDOMWriter;
import org.bonitasoft.studio.application.ApplicationPlugin;
import org.bonitasoft.studio.application.validator.MavenSettingsValidator;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.codehaus.plexus.util.WriterFactory;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.m2e.core.MavenPlugin;
import org.eclipse.m2e.core.embedder.IMavenConfiguration;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.Format.TextMode;

public class MavenSettingsIO {

    public static Settings read() {
        try {
            MavenPlugin.getMaven().reloadSettings();
            var settings = MavenPlugin.getMaven().getSettings();
            return settings.clone();
        } catch (CoreException e) {
            BonitaStudioLog.error(e);
            return new Settings();
        }
    }

    public static void write(Settings settings) throws CoreException {
        var userSettingsFile = getUserSettingsFile();
        var parentFile = userSettingsFile.getParentFile();
        if(!parentFile.exists()) {
            parentFile.mkdirs();
        }
        try (var os = Files.newOutputStream(userSettingsFile.toPath())) {
            MavenPlugin.getMaven().writeSettings(settings, os);
        } catch (IOException e) {
            throw new CoreException(
                    Status.error(String.format("Failed to update settings file %s", userSettingsFile), e));
        } finally {
            MavenPlugin.getMaven().reloadSettings();
        }
    }

    public static void writePreservingFormat(Settings settings) throws CoreException {
        var userSettingsFile = getUserSettingsFile();
        var parentFile = userSettingsFile.getParentFile();
        if(!parentFile.exists()) {
            parentFile.mkdirs();
        }
        try {
            SAXBuilder builder = new SAXBuilder();
            builder.setIgnoringBoundaryWhitespace(false);
            builder.setIgnoringElementContentWhitespace(false);
            if (!userSettingsFile.exists()) {
                userSettingsFile.createNewFile();
            }
            var doc = builder.build(userSettingsFile);
            String encoding = Optional.ofNullable(settings)
                    .map(Settings::getModelEncoding)
                    .orElse("UTF-8");
            try (Writer writer = WriterFactory.newWriter(userSettingsFile, encoding)) {
                    new SettingsJDOMWriter().write(settings, doc, writer,  Format.getRawFormat()
                            .setEncoding(encoding)
                            .setTextMode(TextMode.PRESERVE));
            } finally {
                MavenPlugin.getMaven().reloadSettings();
            }
        } catch (IOException | JDOMException e) {
            BonitaStudioLog.warning(String.format(
                    "File '%s' is empty / unreadable -> Content will be overwritten by in memory configuration.",
                    userSettingsFile.toString()), ApplicationPlugin.PLUGIN_ID);
            // Fallback to default writer
            write(settings);
        }
    }

    public static MultiStatus validate(Settings settings) {
        return (MultiStatus) new MavenSettingsValidator().validate(settings);
    }

    public static File getUserSettingsFile() {
        IMavenConfiguration mavenConfiguration = MavenPlugin.getMavenConfiguration();
        File userSettingsFile = SettingsXmlConfigurationProcessor.DEFAULT_USER_SETTINGS_FILE;
        if (mavenConfiguration.getUserSettingsFile() != null) {
            userSettingsFile = new File(mavenConfiguration.getUserSettingsFile());
        }
        return userSettingsFile;
    }

}
