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

import java.util.Objects;
import java.util.Properties;
import java.util.function.Predicate;

import org.apache.maven.model.Build;
import org.apache.maven.model.Model;
import org.apache.maven.model.Plugin;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.core.maven.model.ProjectMetadata;
import org.bonitasoft.studio.common.repository.core.migration.MavenModelMigration;
import org.bonitasoft.studio.common.repository.core.migration.report.MigrationReport;

public class GroovyCompilerPluginStep implements MavenModelMigration {

    private static final String GROOVY_COMPILER_VERSION_PROPERTY = "groovy-eclipse-batch.version";
    private static final String GROOVY_ECLIPSE_COMPILER_PROPERTY = "groovy-eclipse-compiler.version";
    private static final String MAVEN_COMPILER_ID_PROPERTY = "maven.compiler.compilerId";

    @Override
    public MigrationReport migrate(Model model, ProjectMetadata metadata) {
        MigrationReport report = new MigrationReport();
        BonitaStudioLog.info(String.format("Starting %s on %s...", GroovyCompilerPluginStep.class.getName(), metadata.getArtifactId()));
        Properties properties = model.getProperties();

        properties.setProperty(MAVEN_COMPILER_ID_PROPERTY, "groovy-eclipse-compiler");
        properties.remove(GROOVY_COMPILER_VERSION_PROPERTY);
        properties.remove(GROOVY_ECLIPSE_COMPILER_PROPERTY);

        Build build = model.getBuild();
        if (build != null) {
            model.getBuild().getPlugins().removeIf(isMavenCompilerPlugin());
        }
        
        report.updated("Groovy compiler plugin configuration has been updated to support Java 17.");
        BonitaStudioLog.info(String.format("%s completed for %s.", GroovyCompilerPluginStep.class.getName(), metadata.getArtifactId()));
        return report;
    }

    @Override
    public boolean appliesTo(Model model, ProjectMetadata metadata) {
       return hasGroovyEclipseCompiler(model);
    }

    private boolean hasGroovyEclipseCompiler(Model model) {
        return model.getProperties().containsKey(GROOVY_COMPILER_VERSION_PROPERTY)
                || (model.getBuild() != null && model.getBuild().getPlugins().stream()
                            .filter(isMavenCompilerPlugin())
                            .findFirst()
                            .filter(p -> !p.getDependencies().isEmpty())
                            .isPresent());
    }

    private Predicate<? super Plugin> isMavenCompilerPlugin() {
        return p -> Objects.equals("org.apache.maven.plugins", p.getGroupId()) 
                && Objects.equals("maven-compiler-plugin", p.getArtifactId());
    }

}
