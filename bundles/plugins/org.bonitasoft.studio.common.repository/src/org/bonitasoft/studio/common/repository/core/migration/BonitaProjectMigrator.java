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
package org.bonitasoft.studio.common.repository.core.migration;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.bonitasoft.studio.common.Strings;
import org.bonitasoft.studio.common.repository.core.migration.report.MigrationReport;
import org.bonitasoft.studio.common.repository.core.migration.step.BdmModelArtifactMigrationStep;
import org.bonitasoft.studio.common.repository.core.migration.step.CreatePomMigrationStep;
import org.bonitasoft.studio.common.repository.core.migration.step.DeleteProjectSettingsMigrationStep;
import org.bonitasoft.studio.common.repository.core.migration.step.GitIgnoreMigrationStep;
import org.bonitasoft.studio.common.repository.core.migration.step.JavaDependenciesMigrationStep;
import org.bonitasoft.studio.common.repository.core.migration.step.MultiModuleMigrationStep;
import org.bonitasoft.studio.common.repository.core.migration.step.RemoveLegacyFolderStep;
import org.bonitasoft.studio.common.repository.core.migration.step.SplitGroovyAllIntoModulesStep;
import org.bonitasoft.studio.common.repository.core.migration.step.UpdateBonitaProjectMavenPluginVersionInPomStep;
import org.bonitasoft.studio.common.repository.core.migration.step.UpdateBonitaRuntimeVersionInPomStep;
import org.bonitasoft.studio.common.repository.core.migration.step.UpdateProjectDescriptionMigrationStep;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class BonitaProjectMigrator {

    private static final List<MigrationStep> STEPS = List.of(
            new CreatePomMigrationStep(),
            new RemoveLegacyFolderStep(),
            new UpdateBonitaRuntimeVersionInPomStep(),
            new UpdateBonitaProjectMavenPluginVersionInPomStep(),
            new SplitGroovyAllIntoModulesStep(),
            new JavaDependenciesMigrationStep(),
            new BdmModelArtifactMigrationStep(true),
            new MultiModuleMigrationStep(),
            new GitIgnoreMigrationStep(),
            new DeleteProjectSettingsMigrationStep(),
            new UpdateProjectDescriptionMigrationStep());

    private Path project;

    public BonitaProjectMigrator(Path project) {
        this.project = project;
    }

    public BonitaProjectMigrator(IProject project) {
        this(project.getLocation().toFile().toPath());
    }

    public MigrationReport run(IProgressMonitor monitor) throws CoreException {
        var sourceVersion = readBonitaVersion();
        var report = new MigrationReport();
        for (var step : STEPS) {
            if (Strings.hasText(sourceVersion) && step.appliesTo(sourceVersion)) {
                step.run(project, monitor).merge(report);
            }
        }
        return report;
    }

    public String readBonitaVersion() throws CoreException {
        var projectDescriptor = project.resolve(IProjectDescription.DESCRIPTION_FILE_NAME);
        return readBonitaVersion(projectDescriptor);
    }

    public static String readBonitaVersion(Path projectDescriptor) throws CoreException {
        if (!Files.exists(projectDescriptor)) {
            throw new CoreException(Status.error("Project descriptor not found !"));
        }
        var version = readDescriptor(projectDescriptor).getComment();
        if (Strings.isNullOrEmpty(version)) {
            throw new CoreException(
                    Status.error(String.format("%s is not a valid Bonita Project descriptor.", projectDescriptor)));
        }
        return version;
    }

    public boolean requireCleanImport() throws CoreException {
        var sourceVersion = readBonitaVersion();
        return STEPS.stream()
                .filter(step -> step.appliesTo(sourceVersion))
                .anyMatch(MigrationStep::requireCleanImport);
    }

    public static IProjectDescription readDescriptor(Path projectDescriptor) throws CoreException {
        try (var is = Files.newInputStream(projectDescriptor)) {
            return ResourcesPlugin.getWorkspace().loadProjectDescription(is);
        } catch (IOException e) {
            throw new CoreException(Status.error("Failed to read project descriptor.", e));
        }
    }

    private static Document asXMLDocument(InputStream source) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
        factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");
        factory.setNamespaceAware(true);
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            return builder.parse(new InputSource(source));
        } catch (SAXException | IOException | ParserConfigurationException e) {
            return null;
        }
    }

}
