/**
 * Copyright (C) 2016 Bonitasoft S.A.
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
package org.bonitasoft.studio.importer.ui.wizard;

import org.bonitasoft.studio.importer.ImporterFactory;

public class ImportFileData {

    private String filePath;
    private ImporterFactory importerFactory;

    public enum RepositoryMode {
        CURRENT, NEW, EXISTING
    }

    private String newRepositoryName;

    private String selectedRepositoryName;

    private RepositoryMode mode = RepositoryMode.CURRENT;

    public String getNewRepositoryName() {
        return newRepositoryName;
    }

    public void setNewRepositoryName(String newRepositoryName) {
        this.newRepositoryName = newRepositoryName;
    }

    public String getSelectedRepositoryName() {
        return selectedRepositoryName;
    }

    public void setSelectedRepositoryName(String selectedRepositoryName) {
        this.selectedRepositoryName = selectedRepositoryName;
    }

    public RepositoryMode getMode() {
        return mode;
    }

    public void setMode(RepositoryMode mode) {
        this.mode = mode;
    }

    public String getRepositoryName() {
        switch (getMode()) {
            case NEW:
                return getNewRepositoryName();
            case EXISTING:
                return getSelectedRepositoryName();
            case CURRENT:
            default:
                return null;
        }
    }

    public ImporterFactory getImporterFactory() {
        return importerFactory;
    }

    public void setImporterFactory(ImporterFactory importerFactory) {
        this.importerFactory = importerFactory;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

}
