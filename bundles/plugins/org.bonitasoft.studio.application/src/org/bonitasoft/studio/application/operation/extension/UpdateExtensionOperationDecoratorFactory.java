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
package org.bonitasoft.studio.application.operation.extension;

import java.util.List;

import javax.inject.Inject;

import org.bonitasoft.studio.common.CommandExecutor;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.extension.update.DependencyUpdate;
import org.eclipse.e4.core.di.annotations.Creatable;

@Creatable
public class UpdateExtensionOperationDecoratorFactory {

    private RepositoryAccessor repositoryAccessor;
    private CommandExecutor commandExecutor;

    @Inject
    public UpdateExtensionOperationDecoratorFactory(RepositoryAccessor repositoryAccessor, CommandExecutor commandExecutor) {
        this.repositoryAccessor = repositoryAccessor;
        this.commandExecutor = commandExecutor;
    }

    public UpdateExtensionOperationDecorator create(List<DependencyUpdate> dependenciesUpdates) {
        return new UpdateExtensionOperationDecorator(dependenciesUpdates,
                repositoryAccessor.getCurrentRepository(), commandExecutor);
    }

}
