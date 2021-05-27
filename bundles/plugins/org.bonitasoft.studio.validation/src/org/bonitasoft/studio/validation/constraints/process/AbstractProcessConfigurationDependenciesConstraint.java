/**
 * Copyright (C) 2009 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.validation.constraints.process;

import org.bonitasoft.studio.common.FragmentTypes;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.core.maven.ProjectDependenciesResolver;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.configuration.Fragment;
import org.bonitasoft.studio.validation.constraints.AbstractLiveValidationMarkerConstraint;
import org.bonitasoft.studio.validation.i18n.Messages;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.validation.IValidationContext;

public abstract class AbstractProcessConfigurationDependenciesConstraint
        extends AbstractLiveValidationMarkerConstraint {

    private ProjectDependenciesResolver dependenciesResolver = new ProjectDependenciesResolver(RepositoryManager.getInstance().getAccessor());

    protected IStatus validateConfigurationDependencies(IValidationContext ctx, Configuration configuration,
            String processName) {
        if (configuration == null) {
            return ctx.createSuccessStatus();
        }
        var multiStatus = new MultiStatus(getClass(), 0, null);

        configuration.getProcessDependencies().stream()
                .filter(fc -> fc.getId().equals(FragmentTypes.OTHER))
                .flatMap(fc -> fc.getFragments().stream())
                .map(Fragment::getValue)
                .filter(this::notInClasspath)
                .map(jarName -> ctx.createFailureStatus(String.format(Messages.dependencyNotFoundInClasspath,
                        jarName, processName, configurationName(configuration))))
                .forEach(multiStatus::add);

        return multiStatus.isOK() ? ctx.createSuccessStatus() : multiStatus;
    }

    private String configurationName(Configuration configuration) {
        return configuration.getName() == null ? Messages.local : configuration.getName();
    }

    private boolean notInClasspath(String jarName) {
        try {
            return dependenciesResolver.findCompileDependency(jarName, new NullProgressMonitor())
                    .isEmpty();
        } catch (CoreException e) {
            BonitaStudioLog.error(e);
            return false;
        }
    }

}
