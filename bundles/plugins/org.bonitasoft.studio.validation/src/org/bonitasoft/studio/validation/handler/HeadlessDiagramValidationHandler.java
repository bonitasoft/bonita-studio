/**
 * Copyright (C) 2019 Bonitasoft S.A.
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
package org.bonitasoft.studio.validation.handler;

import java.lang.reflect.InvocationTargetException;

import javax.inject.Named;

import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.validation.common.operation.BatchValidationOperation;
import org.bonitasoft.studio.validation.common.operation.OffscreenEditPartFactory;
import org.bonitasoft.studio.validation.common.operation.RunProcessesValidationOperation;
import org.bonitasoft.studio.validation.common.operation.ValidationMarkerProvider;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.e4.core.di.annotations.Execute;

public class HeadlessDiagramValidationHandler {

    @Execute
    public IStatus execute(RepositoryAccessor repositoryAccessor,
            @Named("fileName") String fileName) {
        DiagramFileStore diagramFileStore = repositoryAccessor.getRepositoryStore(DiagramRepositoryStore.class)
                .getChild(fileName, true);
        if (diagramFileStore != null) {
            RunProcessesValidationOperation validationAction = new RunProcessesValidationOperation(
                    new BatchValidationOperation(
                            new OffscreenEditPartFactory(
                                    org.eclipse.gmf.runtime.diagram.ui.OffscreenEditPartFactory.getInstance()),
                            new ValidationMarkerProvider()));
            validationAction.addProcess(diagramFileStore.getContent());
            try {
                validationAction.run(new NullProgressMonitor());
                return validationAction.getStatus();
            } catch (InvocationTargetException | InterruptedException e) {
                return ValidationStatus.error(String.format("An error occured while validating diagram %s", fileName), e);
            }
        }
        throw new IllegalArgumentException(String.format("The diagram `%s` doesn't exist", fileName));

    }

}
