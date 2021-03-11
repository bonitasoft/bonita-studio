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
package org.bonitasoft.studio.team.git.ui.wizard;

import java.util.stream.Stream;

import org.bonitasoft.studio.common.repository.RepositoryNameValidator;
import org.bonitasoft.studio.team.git.i18n.Messages;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;

public class ShareableRepositoryValidator extends RepositoryNameValidator {

    private String[] availableRepositories;

    public ShareableRepositoryValidator(String[] availableRepositories) {
        super(true);
        this.availableRepositories = availableRepositories;
    }

    @Override
    public IStatus validate(Object value) {
        String repoName = value == null ? "" : value.toString();
        IStatus status = Stream.of(availableRepositories).anyMatch(repoName::equalsIgnoreCase)
                ? ValidationStatus.ok()
                : super.validate(repoName);
        if (org.bonitasoft.studio.team.i18n.Messages.repoAlreadyExist.equals(status.getMessage())) {
            status = ValidationStatus.error(Messages.repoAlreadyShared);
        }
        return status;
    }

}
