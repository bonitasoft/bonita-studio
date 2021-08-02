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
package org.bonitasoft.studio.groovy;

import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.IBonitaProjectListener;
import org.bonitasoft.studio.groovy.library.FunctionsRepositoryFactory;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

public class GroovyFunctionListener implements IBonitaProjectListener {

    @Override
    public void projectOpened(AbstractRepository repository, IProgressMonitor monitor) {
        new Job("Build Groovy code proposals...") {

            @Override
            protected IStatus run(IProgressMonitor monitor) {
                FunctionsRepositoryFactory.reset();
                FunctionsRepositoryFactory.getFunctionCatgories(repository);
                return Status.OK_STATUS;
            }
        }.schedule();
    }

    @Override
    public void projectClosed(AbstractRepository repository, IProgressMonitor monitor) {

    }

}
