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

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.bpm.model.configuration.Configuration;
import org.bonitasoft.bpm.model.process.Pool;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.validation.IValidationContext;

public class ProcessConfigurationDependenciesConstraint extends AbstractProcessConfigurationDependenciesConstraint {

    @Override
    protected IStatus performBatchValidation(final IValidationContext ctx) {
        final Configuration configuration = (Configuration) ctx.getTarget();
        Pool pool = ModelHelper.getParentPool(configuration);
        return validateConfigurationDependencies(ctx, configuration, String.format("%s (%s)", pool.getName(), pool.getVersion()));
    }

    @Override
    protected String getConstraintId() {
        return "org.bonitasoft.studio.validation.constraints.process.configuration.dependencies";
    }

}
