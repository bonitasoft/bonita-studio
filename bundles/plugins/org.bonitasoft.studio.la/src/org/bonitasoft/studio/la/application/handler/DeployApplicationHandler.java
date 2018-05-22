/**
 * Copyright (C) 2017 Bonitasoft S.A.
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
package org.bonitasoft.studio.la.application.handler;

import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.la.application.core.DeployApplicationAction;
import org.bonitasoft.studio.la.application.repository.ApplicationRepositoryStore;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.swt.widgets.Shell;

public class DeployApplicationHandler {

    @Execute
    public void execute(RepositoryAccessor repositoryAccessor, Shell shell) {
        new DeployApplicationAction(repositoryAccessor).selectAndDeploy(shell);
    }

    @CanExecute
    public boolean canExecute(RepositoryAccessor repositoryAccessor) {
        return repositoryAccessor.getCurrentRepository().isLoaded()
                && !repositoryAccessor.getRepositoryStore(ApplicationRepositoryStore.class).getChildren().isEmpty();
    }

}
