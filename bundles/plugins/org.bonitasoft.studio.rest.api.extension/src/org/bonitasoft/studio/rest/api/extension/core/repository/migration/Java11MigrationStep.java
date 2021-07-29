/**
 * Copyright (C) 2021 BonitaSoft S.A.
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
package org.bonitasoft.studio.rest.api.extension.core.repository.migration;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicReference;

import org.apache.maven.model.Build;
import org.apache.maven.model.Model;
import org.apache.maven.model.Plugin;
import org.bonitasoft.studio.common.repository.core.migration.MavenModelMigration;
import org.bonitasoft.studio.common.repository.core.migration.report.MigrationReport;
import org.codehaus.plexus.util.xml.Xpp3Dom;

public class Java11MigrationStep implements MavenModelMigration {

    private static final String JAVA_VERSION_PROPERTY = "java.version";
    private static final String JAVA_VERSION = "11";

    @Override
    public MigrationReport migrate(Model model) {
        MigrationReport report = new MigrationReport();
        Properties properties = model.getProperties();

        properties.setProperty("maven-compiler-plugin.version", "3.8.1");

        AtomicReference<String> existingJavaVersion = new AtomicReference<>("1.8");
        if (properties.containsKey(JAVA_VERSION_PROPERTY)) {
            existingJavaVersion.set(properties.getProperty(JAVA_VERSION_PROPERTY));
        }
        properties.setProperty(JAVA_VERSION_PROPERTY, JAVA_VERSION);

        if (properties.containsKey("maven.compiler.target")) {
            existingJavaVersion.set(properties.getProperty("maven.compiler.target"));
        }
        properties.setProperty("maven.compiler.target", "${java.version}");
        properties.setProperty("maven.compiler.source", "${java.version}");

        Build build = model.getBuild();
        if (build != null) {
            findPlugin(model.getBuild().getPlugins(), "org.apache.maven.plugins", "maven-compiler-plugin")
                    .ifPresent(p -> {
                        p.setVersion("${maven-compiler-plugin.version}");
                        Xpp3Dom configuration = (Xpp3Dom) p.getConfiguration();
                        removeNode("source", configuration);
                        removeNode("target", configuration);
                    });

            if (!JAVA_VERSION.equals(existingJavaVersion.get())) {
                report.updated(String.format("Java version has been updated from `%s` to `%s`",
                        existingJavaVersion.get(), JAVA_VERSION));
            }
        }

        return report;
    }

    private void removeNode(String nodeName, Xpp3Dom configuration) {
        int index = -1;
        for (var i = 0; i < configuration.getChildCount(); i++) {
            var node = configuration.getChild(i);
            if (nodeName.equals(node.getName())) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            configuration.removeChild(index);
        }
    }

    private Optional<Plugin> findPlugin(Collection<Plugin> plugins, String groupId, String artifactId) {
        return plugins.stream()
                .filter(p -> Objects.equals(groupId, p.getGroupId()))
                .filter(p -> Objects.equals(artifactId, p.getArtifactId()))
                .findFirst();
    }

    @Override
    public boolean appliesTo(Model model) {
        var hasJava8Source = findPlugin(model.getBuild().getPlugins(), "org.apache.maven.plugins",
                "maven-compiler-plugin")
                        .map(Plugin::getConfiguration)
                        .map(Xpp3Dom.class::cast)
                        .map(configuration -> configuration.getChild("source"))
                        .filter(Objects::nonNull)
                        .map(Xpp3Dom::getValue)
                        .map("1.8"::equals)
                        .orElse(false);
        var hasJava8Target = findPlugin(model.getBuild().getPlugins(), "org.apache.maven.plugins",
                "maven-compiler-plugin")
                        .map(Plugin::getConfiguration)
                        .map(Xpp3Dom.class::cast)
                        .map(configuration -> configuration.getChild("target"))
                        .filter(Objects::nonNull)
                        .map(Xpp3Dom::getValue)
                        .map("1.8"::equals)
                        .orElse(false);
        var notValidJavaVersionProperty = !Objects.equals(model.getProperties().getProperty(JAVA_VERSION),
                JAVA_VERSION);
        return notValidJavaVersionProperty || hasJava8Source || hasJava8Target;
    }

}
