/**
 * Copyright (C) 2018 Bonitasoft S.A.
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
package org.bonitasoft.studio.la.application.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.la.application.repository.ApplicationFileStore;
import org.bonitasoft.studio.la.application.repository.ApplicationRepositoryStore;

public class ImportApplicationAction {

    public Optional<ApplicationFileStore> importApplication(RepositoryAccessor repositoryAccessor, File file) {
        ApplicationRepositoryStore repositoryStore = repositoryAccessor.getRepositoryStore(ApplicationRepositoryStore.class);
        if (repositoryStore.getChildren().stream()
                .anyMatch(applicationFileStore -> Objects.equals(applicationFileStore.getName(), file.getName()))) {
            if (FileActionDialog.overwriteQuestion(file.getName())) {
                repositoryStore.getChild(file.getName(), true).delete();
            } else {
                return Optional.empty();
            }
        }
        try (FileInputStream fis = new FileInputStream(file)) {
            ApplicationFileStore applicationFileStore = repositoryStore.importInputStream(file.getName(), fis);
            return Optional.ofNullable(applicationFileStore);
        } catch (final IOException e) {
            throw new RuntimeException("Failed to import the application descriptor", e);
        }
    }

}
