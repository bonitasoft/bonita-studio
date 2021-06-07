/**
 * Copyright (C) 2021 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.migration.report;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;

public class MigrationReport {
    
    private List<String> removed = new ArrayList<>();
    private List<String> added = new ArrayList<>();
    private List<String> updated = new ArrayList<>();
    private String title;

    public void removed(String message) {
        removed.add(message);
    }
    
    public void added(String message) {
        added.add(message);
    }
    
    public void updated(String message) {
        updated.add(message);
    }
    
    public MigrationReport merge(MigrationReport report) {
        removed.forEach(report::removed);
        added.forEach(report::added);
        updated.forEach(report::updated);
        return report;
    }

    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return ZonedDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG));
    }

    public boolean hasAdditions() {
        return !added.isEmpty();
    }
    
    public boolean hasUpdates() {
        return !updated.isEmpty();
    }
    
    public boolean hasRemovals() {
        return !removed.isEmpty();
    }

    public List<String> additions() {
        return added;
    }
    
    public List<String> updates() {
        return updated;
    }
    
    public List<String> removals() {
        return removed;
    }

}
