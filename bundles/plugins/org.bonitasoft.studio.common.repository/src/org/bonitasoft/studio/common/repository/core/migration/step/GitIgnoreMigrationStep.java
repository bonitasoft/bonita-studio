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
package org.bonitasoft.studio.common.repository.core.migration.step;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.Messages;
import org.bonitasoft.studio.common.repository.core.BonitaProject;
import org.bonitasoft.studio.common.repository.core.migration.MigrationStep;
import org.bonitasoft.studio.common.repository.core.migration.StepDescription;
import org.bonitasoft.studio.common.repository.core.migration.report.MigrationReport;
import org.bonitasoft.studio.common.repository.core.team.GitProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.jgit.lib.Constants;

public class GitIgnoreMigrationStep implements MigrationStep {

    @Override
    public StepDescription getDescription() {
        return new StepDescription(Messages.gitIgnoreMigrationTitle, Messages.gitIgnoreMigrationDescription);
    }

    @Override
    public MigrationReport run(Path project, IProgressMonitor monitor) throws CoreException {
        monitor.subTask(Messages.gitIgnoreMigrationTitle);
        BonitaStudioLog.info(String.format("Starting %s...", GitIgnoreMigrationStep.class.getName()));
        var report = MigrationReport.emptyReport();
        try {
            var parentEntries = updateGitIgnore(project, report, project.resolve(Constants.GITIGNORE_FILENAME),
                    GitProject.getParentGitIgnoreTemplate(), List.of());
            updateGitIgnore(project, report, project
                    .resolve(BonitaProject.APP_MODULE)
                    .resolve(Constants.GITIGNORE_FILENAME),
                    GitProject.getGitignoreTemplateFileURL(),
                    parentEntries);
        } catch (IOException e) {
            throw new CoreException(Status.error("Failed to update .gitignore file.", e));
        }
        BonitaStudioLog.info(String.format("%s completed.", GitIgnoreMigrationStep.class.getName()));
        return report;
    }

    private List<String> updateGitIgnore(Path project, MigrationReport report, Path gitIgnore, URL gitIgnoreTemplate,
            List<String> parentEntries)
            throws CoreException {
        if (Files.exists(gitIgnore)) {
            try (var is = Files.newInputStream(gitIgnore)) {
                var existingEntries = readEntries(is, StandardCharsets.UTF_8);
                var entriesToAdd = retrieveEntriesToAdd(existingEntries, gitIgnoreTemplate);
                if (!entriesToAdd.isEmpty()) {
                    existingEntries.add(System.lineSeparator());
                    BonitaStudioLog
                            .info(String.format("Adding following entries to %s", project.relativize(gitIgnore)));
                    existingEntries.addAll(entriesToAdd);
                }
                var entriesRemoved = existingEntries.removeIf(entry -> !entry.startsWith("/")
                        && !entry.isBlank()
                        && parentEntries.contains(entry));
                entriesRemoved = existingEntries.removeIf(entry -> !entry.isBlank()
                        && entry.startsWith("/lib/") || entry.equals("/template")) || entriesRemoved;
                if (entriesRemoved || !entriesToAdd.isEmpty()) {
                    String newContent = existingEntries.stream().reduce("",
                            (s1, s2) -> s1 + System.lineSeparator() + s2);
                    Files.writeString(gitIgnore, newContent, StandardCharsets.UTF_8);
                    BonitaStudioLog.info(String.format("`%s` file has been updated with the following content:%n%s",
                            project.relativize(gitIgnore), newContent));
                    report.updated(String.format("`%s` file has been updated.", project.relativize(gitIgnore)));
                }
            } catch (IOException e) {
                throw new CoreException(Status.error("Failed to update .gitignore file.", e));
            }
            try (var input = Files.newInputStream(gitIgnore)) {
                return readEntries(input, StandardCharsets.UTF_8);
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        }
        return List.of();
    }

    private List<String> retrieveEntriesToAdd(List<String> existingEntries, URL gitignoreTemplateUrl)
            throws IOException {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(gitignoreTemplateUrl.openStream(), StandardCharsets.UTF_8))) {
            List<String> entries = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                if (lineIsValid(line)
                        && !existingEntries.contains(line)
                        && !line.equals("/process_configurations")) {
                    entries.add(line);
                }
            }
            return entries;
        }
    }

    private boolean lineIsValid(String line) {
        return line != null && !line.isEmpty() && !line.startsWith("#");
    }

    private static List<String> readEntries(InputStream is, Charset encoding) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is, encoding))) {
            var entries = new ArrayList<String>();
            String line;
            while ((line = br.readLine()) != null) {
                entries.add(line);
            }
            return entries;
        }
    }

}
