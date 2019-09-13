/**
 * Copyright (C) 2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.common.repository;

import java.io.File;
import java.util.Optional;
import java.util.stream.Stream;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.IInputValidator;

public class RepositoryNameValidator implements IInputValidator, IValidator {

    private static final String[] reservedChars = new String[] { "/", "\\", ":", "*", "?", "\"", "<", ">", ";", "|" };

    @Override
    public String isValid(final String newText) {
        if (newText == null || newText.trim().length() == 0) {
            return Messages.createNewProject_emptyText;
        }
        final Optional<String> invalidChar = isValidFileName(newText);
        if (invalidChar.isPresent()) {
            return String.format(Messages.createNewProject_invalidCharacter, invalidChar.get());
        }

        if (getRepositoryManager().getRepository(newText) != null) {
            return String.format(Messages.projectAlreadyExist, newText);
        }
        try {
            getRepositoryManager().getCurrentRepository()
                    .getProject()
                    .getWorkspace()
                    .getRoot().refreshLocal(IResource.DEPTH_ONE, Repository.NULL_PROGRESS_MONITOR);
            if (Stream.of(getRepositoryManager().getCurrentRepository()
                    .getProject()
                    .getWorkspace()
                    .getRoot()
                    .getLocation()
                    .toFile()
                    .listFiles())
                    .map(File::getName)
                    .map(String::toLowerCase)
                    .anyMatch(newText.toLowerCase()::equals)) {
                return String.format(Messages.projectAlreadyExist, newText);
            }
        } catch (CoreException e) {
            BonitaStudioLog.error(e);
        }
        return null;
    }

    protected Optional<String> isValidFileName(final String newText) {
        for (final String reservedChar : reservedChars) {
            if (newText.contains(reservedChar)) {
                return Optional.of(reservedChar);
            }
        }
        return Optional.empty();
    }

    protected RepositoryManager getRepositoryManager() {
        return RepositoryManager.getInstance();
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.core.databinding.validation.IValidator#validate(java.lang.Object)
     */
    @Override
    public IStatus validate(Object value) {
        final String errorMessage = isValid((String) value);
        return errorMessage == null ? ValidationStatus.ok() : ValidationStatus.error(errorMessage);
    }
}
