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
package org.bonitasoft.studio.common.repository.core.migration.report;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class AsciidocMigrationReportWriter implements MigrationReportWriter {

    @Override
    public void write(MigrationReport report, Path reportFile) throws IOException {
        var content = "";
        if(!Files.exists(reportFile.getParent())) {
            reportFile.getParent().toFile().mkdirs();
        }
        if(Files.exists(reportFile)) {
            content = Files.readString(reportFile);
        }
        content = buildReport(report) 
                + content;
        Files.writeString(reportFile, 
                content,
                StandardCharsets.UTF_8,
                StandardOpenOption.CREATE, StandardOpenOption.WRITE);
    }

    private String buildReport(MigrationReport report) {
        var asciidoc = new StringBuilder();

        asciidoc.append("== ");
        asciidoc.append(report.getTitle());
        asciidoc.append(" (");
        asciidoc.append(report.getDate());
        asciidoc.append(")");

        asciidoc.append(System.lineSeparator());
        asciidoc.append(System.lineSeparator());

        if (report.hasAdditions()) {
            asciidoc.append("=== Additions");
            asciidoc.append(System.lineSeparator());
            asciidoc.append(System.lineSeparator());
            report.additions().forEach(addition -> {
                asciidoc.append("* ");
                asciidoc.append(addition);
                asciidoc.append(System.lineSeparator());
            });
            asciidoc.append(System.lineSeparator());
        }

        if (report.hasUpdates()) {
            asciidoc.append("=== Updates");
            asciidoc.append(System.lineSeparator());
            asciidoc.append(System.lineSeparator());
            report.updates().forEach(update -> {
                asciidoc.append("* ");
                asciidoc.append(update);
                asciidoc.append(System.lineSeparator());
            });
            asciidoc.append(System.lineSeparator());
        }

        if (report.hasRemovals()) {
            asciidoc.append("=== Removals");
            asciidoc.append(System.lineSeparator());
            asciidoc.append(System.lineSeparator());
            report.removals().forEach(removal -> {
                asciidoc.append("* ");
                asciidoc.append(removal);
                asciidoc.append(System.lineSeparator());
            });
            asciidoc.append(System.lineSeparator());
        }

        return asciidoc.toString();
    }

}
