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
package org.bonitasoft.studio.importer.bos.operation;

import java.io.IOException;
import java.util.Optional;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.core.InputStreamSupplier;
import org.bonitasoft.studio.common.repository.core.LocalDependencyInputStreamSupplier;
import org.bonitasoft.studio.common.repository.core.maven.migration.model.GAV;
import org.bonitasoft.studio.importer.bos.model.ArchiveInputStreamSupplier;
import org.bonitasoft.studio.importer.bos.model.BosArchive;


public class ArchiveLocalDependencyInputStreamSupplier implements LocalDependencyInputStreamSupplier {
    
    private BosArchive bosArchive;

    public ArchiveLocalDependencyInputStreamSupplier(BosArchive bosArchive) {
        this.bosArchive = bosArchive;
    }

    @Override
    public Optional<InputStreamSupplier> find(GAV gav) {
        try {
            return bosArchive.getZipFile()
                    .stream()
                    .filter(entry -> entry.getName().matches(localDependencyFilePattern(gav)))
                    .findFirst()
                    .map(entry -> new ArchiveInputStreamSupplier(bosArchive.getArchiveFile(), entry));
        } catch (IOException e) {
            BonitaStudioLog.error(e);
            return null;
        }
    }

  

}
