/**
 * Copyright (C) 2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.designer.core.resources;

import org.bonitasoft.studio.common.extension.BonitaStudioExtensionRegistryManager;
import org.bonitasoft.studio.common.repository.extension.IGetLockStatusOperation;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;

/**
 * @author Romain Bioteau
 */
public class LockStatusOperationFactory {

    static final String LOCK_STATUS_PROVIDER_EXTENSION_ID = "org.bonitasoft.studio.common.repository.lockStatusProvider";

    public IGetLockStatusOperation newLockStatusOperation() throws CoreException, LockStatusOperationNotFound {
        final IConfigurationElement[] configurationElements = extensionRegistry().getConfigurationElements(
                LOCK_STATUS_PROVIDER_EXTENSION_ID);
        if (configurationElements != null && configurationElements.length > 0) {
            return (IGetLockStatusOperation) configurationElements[0].createExecutableExtension("class");
        }
        throw new LockStatusOperationNotFound();
    }

    protected BonitaStudioExtensionRegistryManager extensionRegistry() {
        return BonitaStudioExtensionRegistryManager.getInstance();
    }

}
