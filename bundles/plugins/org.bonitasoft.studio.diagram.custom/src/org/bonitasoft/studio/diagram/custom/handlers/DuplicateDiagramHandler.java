/**
 * Copyright (C) 2018 Bonitasoft S.A.
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
package org.bonitasoft.studio.diagram.custom.handlers;

import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.filestore.FileStoreFinder;
import org.bonitasoft.studio.diagram.custom.actions.DuplicateDiagramAction;
import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.eclipse.e4.core.di.annotations.Execute;

public class DuplicateDiagramHandler {

    private FileStoreFinder fileStoreFinder;

    public DuplicateDiagramHandler() {
        fileStoreFinder = new FileStoreFinder();
    }

    @Execute
    public void execute(RepositoryAccessor repositoryAccessor) {
        DiagramFileStore fileStore = fileStoreFinder
                .findSelectedFileStore(repositoryAccessor.getCurrentRepository())
                .filter(DiagramFileStore.class::isInstance)
                .map(DiagramFileStore.class::cast)
                .orElseThrow(() -> new IllegalArgumentException("The selected element should be a diagram"));
        new DuplicateDiagramAction(repositoryAccessor).duplicate(fileStore.getContent());
    }

}
