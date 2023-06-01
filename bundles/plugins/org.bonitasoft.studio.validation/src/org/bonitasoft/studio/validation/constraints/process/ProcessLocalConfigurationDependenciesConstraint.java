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
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.diagram.custom.repository.ProcessConfigurationFileStore;
import org.bonitasoft.studio.diagram.custom.repository.ProcessConfigurationRepositoryStore;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.process.Pool;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.validation.IValidationContext;

public class ProcessLocalConfigurationDependenciesConstraint extends AbstractProcessConfigurationDependenciesConstraint {

    @Override
    protected IStatus performBatchValidation(final IValidationContext ctx) {
        final Pool pool = (Pool) ctx.getTarget();
        Configuration configuration = findLocalConfiguration(pool);
        return validateConfigurationDependencies(ctx, configuration, String.format("%s (%s)", pool.getName(), pool.getVersion()));
    }

    Configuration findLocalConfiguration(Pool pool) {
        ProcessConfigurationFileStore localConfigurationStore = RepositoryManager.getInstance().getAccessor()
                .getRepositoryStore(ProcessConfigurationRepositoryStore.class)
                .getChild(ModelHelper.getEObjectID(pool) + ".conf", false);
        try {
            return localConfigurationStore != null ? localConfigurationStore.getContent() : null;
        } catch (ReadFileStoreException e) {
            return null;
        }
    }

    @Override
    protected String getConstraintId() {
        return "org.bonitasoft.studio.validation.constraints.process.localconfiguration.dependencies";
    }

}
