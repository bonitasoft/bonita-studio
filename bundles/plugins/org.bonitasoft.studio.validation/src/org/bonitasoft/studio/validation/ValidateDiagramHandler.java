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
package org.bonitasoft.studio.validation;

import java.util.HashMap;
import java.util.Map;

import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.filestore.FileStoreFinder;
import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

public class ValidateDiagramHandler extends AbstractHandler {

    @Override
    public Object execute(ExecutionEvent event) {
        new FileStoreFinder().findSelectedFileStore(RepositoryManager.getInstance().getCurrentRepository())
                .filter(DiagramFileStore.class::isInstance)
                .map(DiagramFileStore.class::cast)
                .map(DiagramFileStore::getName)
                .ifPresent(name -> {
                    Map<String, String> parameters = new HashMap<>();
                    parameters.put("diagrams", String.format(" %s ", name));
                    ExecutionEvent ev = new ExecutionEvent(null, parameters, null, null);
                    try {
                        new BatchValidationHandler().execute(ev);
                    } catch (ExecutionException e) {
                        throw new RuntimeException(String.format("An error occurred while validating diagram %s", name), e);
                    }
                });
        return null;
    }

}
